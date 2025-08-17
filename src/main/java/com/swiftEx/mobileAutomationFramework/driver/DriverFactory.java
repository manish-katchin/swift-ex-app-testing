package com.swiftEx.mobileAutomationFramework.driver;

import com.swiftEx.mobileAutomationFramework.utils.PlatformConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static final ThreadLocal<AppiumDriver> THREAD_DRIVER = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        // Always create a fresh driver for each scenario when fullReset is enabled
        AppiumDriver drv = THREAD_DRIVER.get();
        if (drv != null) {
            // Clean up existing driver first
            try {
                drv.quit();
            } catch (Exception ignored) {
            }
            THREAD_DRIVER.remove();
        }
        
        drv = createDriver();
        THREAD_DRIVER.set(drv);
        return drv;
    }

    public static void quitDriver() {
        AppiumDriver drv = THREAD_DRIVER.get();
        if (drv != null) {
            try {
                logger.info("Terminating app session...");
                
                // Try to terminate the app if it's Android
                try {
                    if (drv instanceof AndroidDriver) {
                        AndroidDriver androidDriver = (AndroidDriver) drv;
                        String appPackage = System.getProperty("appPackage", "com.app.swiftEx.app");
                        androidDriver.terminateApp(appPackage);
                        logger.info("Android app terminated: {}", appPackage);
                        
                        // Force close any app processes
                        androidDriver.closeApp();
                        logger.info("App force closed");
                    }
                } catch (Exception termError) {
                    logger.warn("Could not terminate app (continuing with quit): {}", termError.getMessage());
                }
                
                // Longer wait to ensure app is completely terminated
                Thread.sleep(3000);
                
                // Get session ID for logging before quitting
                String sessionId = drv.getSessionId() != null ? drv.getSessionId().toString() : "unknown";
                
                // Then quit the driver session
                logger.info("Quitting driver session: {}", sessionId);
                drv.quit();
                
                logger.info("Driver session terminated successfully: {}", sessionId);
            } catch (Exception e) {
                logger.warn("Error during driver cleanup: {}", e.getMessage());
                try {
                    // Force quit if normal quit fails
                    drv.quit();
                } catch (Exception ignored) {
                    logger.warn("Force quit also failed, continuing...");
                }
            } finally {
                // Always clear the ThreadLocal
                THREAD_DRIVER.remove();
                logger.info("ThreadLocal driver cleared");
            }
        } else {
            logger.info("No driver to quit - ThreadLocal was already empty");
        }
    }

    // Force cleanup all drivers - used in global cleanup
    public static void forceQuitAllDrivers() {
        try {
            quitDriver(); // Clean current thread's driver
            logger.info("Force cleanup completed");
        } catch (Exception e) {
            logger.error("Force cleanup failed: {}", e.getMessage());
        }
    }

    private static AppiumDriver createDriver() {
        logger.info("=== CREATING APPIUM DRIVER ===");
        
        // Print configuration summary
        PlatformConfig.printConfigurationSummary();
        
        // Get platform-specific capabilities from PlatformConfig
        Properties platformCaps = PlatformConfig.getPlatformCapabilities();
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Convert Properties to DesiredCapabilities
        platformCaps.forEach((key, value) -> caps.setCapability(key.toString(), value.toString()));
        
        // Additional Android-specific capabilities if needed
        if (PlatformConfig.isAndroid()) {
            // Android-specific optimizations
            caps.setCapability("androidInstallTimeout", 120000); // 2 minutes
            caps.setCapability("uiautomator2ServerInstallTimeout", 60000); // 1 minute
            caps.setCapability("uiautomator2ServerLaunchTimeout", 60000); // 1 minute
            caps.setCapability("autoLaunch", true);
            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("skipDeviceInitialization", false);
            caps.setCapability("skipServerInstallation", false);
            caps.setCapability("appWaitActivity", "*");
            caps.setCapability("appWaitDuration", 20000);
        }
        
        // Get Appium server URL
        String appiumServer = System.getProperty("appiumServer", 
                System.getProperty("appium.server", "http://127.0.0.1:4723"));
        
        logger.info("Appium server URL: {}", appiumServer);
        logger.info("Platform capabilities: {}", caps.asMap());
        
        try {
            AppiumDriver driver;
            
            if (PlatformConfig.isAndroid()) {
                logger.info("Creating Android driver...");
                driver = new AndroidDriver(new URL(appiumServer), caps);
            } else if (PlatformConfig.isIOS()) {
                logger.info("Creating iOS driver...");
                driver = new IOSDriver(new URL(appiumServer), caps);
            } else {
                throw new IllegalArgumentException("Unsupported platform: " + PlatformConfig.getCurrentPlatform());
            }
            
            logger.info("Driver created successfully!");
            logger.info("Session ID: {}", driver.getSessionId());
            logger.info("Platform: {}", PlatformConfig.getCurrentPlatform());
            logger.info("Device: {}", driver.getCapabilities().getCapability("deviceName"));
            
            return driver;
            
        } catch (MalformedURLException e) {
            logger.error("Invalid Appium server URL: {}", appiumServer, e);
            throw new RuntimeException("Invalid Appium server URL: " + appiumServer, e);
        } catch (Exception e) {
            logger.error("Failed to create driver for platform: {}", PlatformConfig.getCurrentPlatform(), e);
            throw new RuntimeException("Driver creation failed", e);
        }
    }
}
