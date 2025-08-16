package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import com.swiftEx.mobileAutomationFramework.utils.UiAutomator2Recovery;

/**
 * Base page class with UiAutomator2 instrumentation crash recovery
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected AppiumDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Find element with instrumentation crash recovery
     */
    protected WebElement findElementWithRetry(By locator, int maxRetries) {
        WebDriverException lastException = null;
        
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                logger.info("INFO: Finding element (attempt {}/{}): {}", attempt, maxRetries, locator);
                
                // Try to find the element
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                logger.info("SUCCESS: Element found successfully: {}", locator);
                return element;
                
            } catch (WebDriverException e) {
                lastException = e;
                String errorMessage = e.getMessage();
                
                if (errorMessage.contains("instrumentation process is not running") || 
                    errorMessage.contains("cannot be proxied to UiAutomator2 server")) {
                    
                    logger.warn("WARNING: UiAutomator2 instrumentation crashed (attempt {}/{})", attempt, maxRetries);
                    logger.warn("   Error: {}", errorMessage);
                    
                    if (attempt < maxRetries) {
                        logger.info("🔄 Attempting UiAutomator2 recovery...");
                        
                        // Wait for potential recovery
                        try {
                            Thread.sleep(3000); // 3 second wait
                            
                            // Test if UiAutomator2 is responsive again
                            if (testUiAutomator2Connectivity()) {
                                logger.info("SUCCESS: UiAutomator2 appears to have recovered");
                                continue; // Retry the element finding
                            } else {
                                logger.warn(" UiAutomator2 still not responsive, trying alternative approaches...");
                                
                                // Try alternative recovery methods
                                if (attemptInstrumentationRecovery()) {
                                    logger.info("SUCCESS: Alternative recovery successful");
                                    continue;
                                }
                            }
                            
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("Recovery interrupted", ie);
                        }
                    }
                } else {
                    logger.error(" Non-instrumentation error: " + errorMessage);
                    // For non-instrumentation errors, don't retry as much
                    if (attempt >= 2) {
                        break;
                    }
                }
                
                // Brief wait between retries for other errors
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        
        // All retries failed
        logger.error(" Element finding failed after " + maxRetries + " attempts: " + locator);
        if (lastException != null) {
            throw new RuntimeException("Element not found after retries: " + locator, lastException);
        } else {
            throw new RuntimeException("Element not found after retries: " + locator);
        }
    }
    
    /**
     * Test if UiAutomator2 is responsive
     */
    protected boolean testUiAutomator2Connectivity() {
        try {
            logger.info(" Testing UiAutomator2 connectivity...");
            
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                String activity = androidDriver.currentActivity();
                
                if (activity != null && !activity.isEmpty()) {
                    logger.info("SUCCESS: UiAutomator2 is responsive - current activity: " + activity);
                    return true;
                }
            } else {
                // For non-Android drivers, test basic responsiveness
                String title = driver.getTitle();
                logger.info("SUCCESS: Driver is responsive - title: " + (title != null ? title : "null"));
                return true;
            }
            
            logger.warn(" UiAutomator2 connectivity test failed - no activity");
            return false;
            
        } catch (Exception e) {
            logger.error(" UiAutomator2 connectivity test failed: " + e.getMessage());
            return false;
        }
    }    /**
     * Attempt alternative recovery methods for instrumentation
     */
    private boolean attemptInstrumentationRecovery() {
        logger.info(" Attempting instrumentation recovery using UiAutomator2Recovery utility...");
        
        // Use the specialized recovery utility with comprehensive recovery methods
        boolean recovered = UiAutomator2Recovery.attemptFullRecovery(driver, 3);
        
        if (recovered) {
            logger.info("SUCCESS: Instrumentation recovery successful");
        } else {
            logger.error(" Instrumentation recovery failed - manual intervention may be required");
        }
        
        return recovered;
    }
    
    /**
     * Click element with retry logic
     */
    protected void clickElementWithRetry(By locator, int maxRetries) {
        WebElement element = findElementWithRetry(locator, maxRetries);
        
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                logger.info(" Clicking element (attempt " + attempt + "/" + maxRetries + "): " + locator);
                element.click();
                logger.info("SUCCESS: Element clicked successfully: " + locator);
                return;
                
            } catch (WebDriverException e) {
                logger.warn(" Click failed (attempt " + attempt + "/" + maxRetries + "): " + e.getMessage());
                
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(1000);
                        // Re-find element for next attempt
                        element = findElementWithRetry(locator, 2);
                    } catch (Exception re) {
                        logger.error(" Could not re-find element for retry: " + re.getMessage());
                        break;
                    }
                }
            }
        }
        
        throw new RuntimeException("Failed to click element after " + maxRetries + " attempts: " + locator);
    }
}
