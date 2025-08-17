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
import java.util.HashMap;
import java.util.Map;
import com.swiftEx.mobileAutomationFramework.utils.UiAutomator2Recovery;
import com.swiftEx.mobileAutomationFramework.utils.ElementActions;
import com.swiftEx.mobileAutomationFramework.utils.LocatorLoader;
import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;
import com.swiftEx.mobileAutomationFramework.utils.ConfigLoader;

/**
 * Enhanced Base page class with automatic platform detection, locator
 * management, and recovery mechanisms
 * Provides a clean, user-friendly foundation for all page classes
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected final AppiumDriver driver;
    protected final WebDriverWait wait;
    protected final ElementActions elementActions;
    protected final String platform;
    protected final Map<String, Map<String, String>> locators;

    /**
     * Enhanced constructor that handles all common initialization
     * 
     * @param driver          AppiumDriver instance
     * @param locatorFileName YAML locator file name (e.g., "pinCreation.yaml")
     */
    public BasePage(AppiumDriver driver, String locatorFileName) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.elementActions = new ElementActions(driver);

        // Auto-detect platform from framework.properties with CLI override
        this.platform = ConfigLoader.getPlatformName();
        logger.debug("BasePage initialized for platform: {}", platform.toUpperCase());

        // Auto-load platform-specific locators
        this.locators = LocatorLoader.loadWithFallback(locatorFileName);
        logger.debug("Loaded {} locators from {}", locators.size(), locatorFileName);
    }

    /**
     * Backward compatibility constructor
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.elementActions = new ElementActions(driver);
        this.platform = ConfigLoader.getPlatformName();
        this.locators = new HashMap<>(); // Empty locators for backward compatibility
    }

    // ===== LOCATOR MANAGEMENT METHODS =====

    /**
     * Get locator by key from YAML file
     * 
     * @param locatorKey Key from YAML file
     * @return Locator map with strategy and value
     * @throws RuntimeException if locator not found
     */
    protected Map<String, String> getLocator(String locatorKey) {
        Map<String, String> locator = locators.get(locatorKey);
        if (locator == null) {
            throw new RuntimeException("ERROR: Locator '" + locatorKey + "' not found in YAML file. " +
                    "Available locators: " + locators.keySet());
        }
        return locator;
    }

    /**
     * Get By object from locator key
     * 
     * @param locatorKey Key from YAML file
     * @return Selenium By object
     */
    protected By getBy(String locatorKey) {
        return LocatorUtils.toBy(getLocator(locatorKey));
    }

    /**
     * Check if locator exists in YAML file
     * 
     * @param locatorKey Key to check
     * @return true if exists, false otherwise
     */
    protected boolean hasLocator(String locatorKey) {
        return locators.containsKey(locatorKey);
    }

    // ===== PLATFORM-AWARE HELPER METHODS =====

    /**
     * Create platform-specific fallback locator
     * 
     * @param androidLocator Android locator (XPath, ID, etc.)
     * @param iosLocator     iOS locator (accessibility ID, XPath, etc.)
     * @return By object for current platform
     */
    protected By createPlatformLocator(String androidLocator, String iosLocator) {
        if ("android".equals(platform)) {
            return By.xpath(androidLocator);
        } else {
            return By.id(iosLocator); // or accessibilityId
        }
    }

    /**
     * Check if current platform is Android
     */
    protected boolean isAndroid() {
        return "android".equals(platform);
    }

    /**
     * Check if current platform is iOS
     */
    protected boolean isIOS() {
        return "ios".equals(platform);
    }

    // ===== ENHANCED ELEMENT INTERACTION METHODS =====

    /**
     * Click element by locator key with automatic retry and recovery
     * 
     * @param locatorKey Key from YAML file
     */
    protected void click(String locatorKey) {
        By by = getBy(locatorKey);
        clickElementWithRetry(by, 3);
        logger.debug("Clicked element: {}", locatorKey);
    }

    /**
     * Send keys to element by locator key
     * 
     * @param locatorKey Key from YAML file
     * @param text       Text to send
     */
    protected void sendKeys(String locatorKey, String text) {
        By by = getBy(locatorKey);
        WebElement element = findElementWithRetry(by, 3);
        element.clear();
        element.sendKeys(text);
        logger.debug("Sent keys to {}: {}", locatorKey, text);
    }

    /**
     * Get text from element by locator key
     * 
     * @param locatorKey Key from YAML file
     * @return Element text
     */
    protected String getText(String locatorKey) {
        By by = getBy(locatorKey);
        WebElement element = findElementWithRetry(by, 3);
        String text = element.getText();
        logger.debug("Got text from {}: {}", locatorKey, text);
        return text;
    }

    /**
     * Check if element is visible by locator key
     * 
     * @param locatorKey Key from YAML file
     * @return true if visible, false otherwise
     */
    protected boolean isVisible(String locatorKey) {
        try {
            By by = getBy(locatorKey);
            WebElement element = findElementWithRetry(by, 1);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for element to be clickable by locator key
     * 
     * @param locatorKey     Key from YAML file
     * @param timeoutSeconds Timeout in seconds
     * @return WebElement when clickable
     */
    protected WebElement waitForClickable(String locatorKey, int timeoutSeconds) {
        By by = getBy(locatorKey);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.elementToBeClickable(by));
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
    }

    /**
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
