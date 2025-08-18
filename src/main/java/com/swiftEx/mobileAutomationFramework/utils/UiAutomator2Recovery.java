package com.swiftEx.mobileAutomationFramework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * UiAutomator2 Recovery Utility for handling instrumentation crashes
 */
public class UiAutomator2Recovery {
    private static final Logger logger = LoggerFactory.getLogger(UiAutomator2Recovery.class);
    
    /**
     * Attempt comprehensive UiAutomator2 recovery
     */
    public static boolean attemptFullRecovery(AppiumDriver driver, int maxAttempts) {
        logger.info(" Starting comprehensive UiAutomator2 recovery (max attempts: " + maxAttempts + ")");
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            logger.info(" Recovery attempt " + attempt + "/" + maxAttempts);
            
            try {
                // Step 1: Test basic connectivity
                if (testBasicConnectivity(driver)) {
                    logger.info("SUCCESS: UiAutomator2 appears to be working - recovery successful");
                    return true;
                }
                
                // Step 2: Try app reactivation
                if (attempt <= 2 && tryAppReactivation(driver)) {
                    logger.info(" App reactivated, testing connectivity...");
                    Thread.sleep(2000);
                    if (testBasicConnectivity(driver)) {
                        logger.info("SUCCESS: Recovery successful after app reactivation");
                        return true;
                    }
                }
                
                // Step 3: Force page source retrieval
                if (attempt <= 3 && tryForcePageSourceRetrieval(driver)) {
                    logger.info(" Page source retrieved, testing connectivity...");
                    Thread.sleep(1000);
                    if (testBasicConnectivity(driver)) {
                        logger.info("SUCCESS: Recovery successful after page source retrieval");
                        return true;
                    }
                }
                
                // Step 4: Wait for natural recovery
                if (attempt < maxAttempts) {
                    int waitTime = attempt * 2000; // Progressive backoff: 2s, 4s, 6s, etc.
                    logger.info(" Waiting " + waitTime + "ms for natural UiAutomator2 recovery...");
                    Thread.sleep(waitTime);
                }
                
            } catch (Exception e) {
                logger.warn(" Recovery attempt " + attempt + " failed: " + e.getMessage());
            }
        }
        
        logger.error(" UiAutomator2 recovery failed after " + maxAttempts + " attempts");
        return false;
    }
    
    /**
     * Test basic UiAutomator2 connectivity
     */
    private static boolean testBasicConnectivity(AppiumDriver driver) {
        try {
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                
                // Test 1: Get current activity
                String activity = androidDriver.currentActivity();
                if (activity == null || activity.isEmpty()) {
                    logger.warn(" Current activity is null/empty");
                    return false;
                }
                
                // Test 2: Get window size (tests instrumentation communication)
                driver.manage().window().getSize();
                
                logger.info("SUCCESS: Basic connectivity test passed - activity: " + activity);
                return true;
            } else {
                // For non-Android drivers, just test if driver is responsive
                driver.getTitle(); // This should work for most drivers
                return true;
            }
        } catch (Exception e) {
            logger.error(" Basic connectivity test failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Try to reactivate the app
     */
    private static boolean tryAppReactivation(AppiumDriver driver) {
        try {
            logger.info(" Attempting app reactivation...");
            
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                
                // Get app package name
                String appPackage = (String) driver.getCapabilities().getCapability("appPackage");
                if (appPackage == null || appPackage.isEmpty()) {
                    appPackage = "com.app.swiftEx.app"; // Fallback to our known app package
                }
                
                logger.info(" Reactivating app: " + appPackage);
                androidDriver.activateApp(appPackage);
                
                return true;
            }
            
            return false;
            
        } catch (Exception e) {
            logger.error(" App reactivation failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Try to force page source retrieval to reestablish instrumentation
     */
    private static boolean tryForcePageSourceRetrieval(AppiumDriver driver) {
        try {
            logger.info(" Attempting forced page source retrieval...");
            
            // Set a longer timeout for this operation
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            String pageSource = driver.getPageSource();
            
            if (pageSource != null && pageSource.length() > 100) {
                logger.info("SUCCESS: Page source retrieved successfully (" + pageSource.length() + " characters)");
                return true;
            } else {
                logger.warn(" Page source is too short or null");
                return false;
            }
            
        } catch (Exception e) {
            logger.error(" Forced page source retrieval failed: " + e.getMessage());
            return false;
        } finally {
            // Reset timeout
            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            } catch (Exception ignored) {
            }
        }
    }
    
    /**
     * Quick connectivity test for lightweight checking
     */
    public static boolean isUiAutomator2Responsive(AppiumDriver driver) {
        try {
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                String activity = androidDriver.currentActivity();
                return activity != null && !activity.isEmpty();
            }
            return true; // Assume non-Android drivers are responsive
        } catch (Exception e) {
            return false;
        }
    }
}
