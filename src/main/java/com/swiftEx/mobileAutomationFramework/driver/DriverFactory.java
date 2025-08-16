package com.swiftEx.mobileAutomationFramework.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

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

    private static String resolveAppPath(String appPath) {
        if (appPath == null || appPath.isEmpty())
            return appPath;
        java.io.File f = new java.io.File(appPath);
        if (f.isAbsolute())
            return appPath;
        // resolve relative to project root (assume current working dir is project root)
        java.io.File cwd = new java.io.File(System.getProperty("user.dir"));
        java.io.File resolved = new java.io.File(cwd, appPath);
        return resolved.getAbsolutePath();
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
        // support either -Dplatform=android or -DplatformName=Android
        String platform = System.getProperty("platform", System.getProperty("platformName", "android")).toLowerCase();
        String appiumServer = System.getProperty("appiumServer",
                System.getProperty("appium.server", "http://127.0.0.1:4723"));
        // load defaults from src/test/resources/framework.properties if present
        java.util.Properties defaults = new java.util.Properties();
        try (java.io.InputStream in = DriverFactory.class.getResourceAsStream("/framework.properties")) {
            if (in != null)
                defaults.load(in);
        } catch (java.io.IOException ignored) {
        }
        DesiredCapabilities caps = new DesiredCapabilities();

        // Common capabilities (can be overridden via -Dproperty=value)
        // accept both plain and appium: prefixed properties
        String deviceName = System.getProperty("deviceName", System.getProperty("appium:deviceName",
                System.getProperty("appium.deviceName", defaults.getProperty("appium.deviceName", "emulator"))));
        String platformVersion = System.getProperty("platformVersion", System.getProperty("appium:platformVersion",
                System.getProperty("appium.platformVersion", defaults.getProperty("appium.platformVersion", ""))));

        caps.setCapability("platformName", platform.equals("ios") ? "iOS" : "Android");
        caps.setCapability("deviceName", deviceName);
        if (!platformVersion.isEmpty())
            caps.setCapability("platformVersion", platformVersion);

        try {
            if (platform.equals("android")) {
                // Android-specific
                String app = System.getProperty("app", System.getProperty("appium:app",
                        System.getProperty("appium.app", defaults.getProperty("appium.app", ""))));
                String appPackage = System.getProperty("appPackage", "");
                String appActivity = System.getProperty("appActivity", "");

                if (!app.isEmpty())
                    caps.setCapability("app", resolveAppPath(app));
                if (!appPackage.isEmpty())
                    caps.setCapability("appPackage", appPackage);
                if (!appActivity.isEmpty())
                    caps.setCapability("appActivity", appActivity);

                // also accept appium:... properties and copy them into caps if provided
                String appiumApp = System.getProperty("appium:app", "");
                if (!appiumApp.isEmpty())
                    caps.setCapability("app", resolveAppPath(appiumApp));
                String appiumDeviceName = System.getProperty("appium:deviceName", "");
                if (!appiumDeviceName.isEmpty())
                    caps.setCapability("deviceName", appiumDeviceName);
                String appiumPlatformVersion = System.getProperty("appium:platformVersion", System
                        .getProperty("appium.platformVersion", defaults.getProperty("appium.platformVersion", "")));
                if (!appiumPlatformVersion.isEmpty())
                    caps.setCapability("platformVersion", appiumPlatformVersion);

                // Use UiAutomator2 with minimal capabilities for maximum compatibility
                // Try UiAutomator2 as primary automation engine (best for Android 12)
                caps.setCapability("automationName", System.getProperty("automationName",
                        System.getProperty("appium:automationName", "UiAutomator2")));

                // Standard capabilities for Android 12 - UiAutomator2 works well
                caps.setCapability("newCommandTimeout", 60);
                caps.setCapability("androidInstallTimeout", 120000); // 2 minutes
                caps.setCapability("uiautomator2ServerInstallTimeout", 60000); // 1 minute (faster on Android 12)
                caps.setCapability("uiautomator2ServerLaunchTimeout", 60000); // 1 minute

                // Basic app launch settings - Enhanced for fresh app launch
                caps.setCapability("autoLaunch", true);
                
                // Reset settings - Force fresh app launch for each scenario
                String fullReset = System.getProperty("fullReset", System.getProperty("appium:fullReset", 
                    defaults.getProperty("appium.fullReset", "true")));
                String noReset = System.getProperty("noReset", System.getProperty("appium:noReset", 
                    defaults.getProperty("appium.noReset", "false")));
                
                caps.setCapability("fullReset", Boolean.parseBoolean(fullReset));
                caps.setCapability("noReset", Boolean.parseBoolean(noReset));
                
                // Additional settings to ensure fresh app state
                if (Boolean.parseBoolean(fullReset)) {
                    caps.setCapability("autoGrantPermissions", true); // Auto-grant permissions after reset
                    caps.setCapability("skipDeviceInitialization", false); // Ensure proper device setup
                    caps.setCapability("skipServerInstallation", false); // Ensure server is properly installed
                }
                
                caps.setCapability("appWaitActivity", "*"); // Wait for any activity
                caps.setCapability("appWaitDuration", 20000); // 20 seconds should be enough

                return new AndroidDriver(new URL(appiumServer), caps);
            } else {
                // iOS-specific
                String udid = System.getProperty("udid", "");
                String bundleId = System.getProperty("bundleId", "");
                String app = System.getProperty("app", "");

                if (!udid.isEmpty())
                    caps.setCapability("udid", udid);
                if (!bundleId.isEmpty())
                    caps.setCapability("bundleId", bundleId);
                if (!app.isEmpty())
                    caps.setCapability("app", app);

                caps.setCapability("automationName", System.getProperty("automationName", "XCUITest"));

                return new IOSDriver(new URL(appiumServer), caps);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed Appium server URL: " + appiumServer, e);
        }
    }
}
