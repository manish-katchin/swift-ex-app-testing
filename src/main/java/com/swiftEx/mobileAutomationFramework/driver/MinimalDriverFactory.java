package com.swiftEx.mobileAutomationFramework.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Minimal DriverFactory for Android 14 compatibility testing
 */
public class MinimalDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(MinimalDriverFactory.class);
    private static final ThreadLocal<AppiumDriver> THREAD_DRIVER = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        AppiumDriver drv = THREAD_DRIVER.get();
        if (drv == null) {
            drv = createMinimalAndroidDriver();
            THREAD_DRIVER.set(drv);
        }
        return drv;
    }

    public static void quitDriver() {
        AppiumDriver drv = THREAD_DRIVER.get();
        if (drv != null) {
            try {
                drv.quit();
            } catch (Exception ignored) {
            }
            THREAD_DRIVER.remove();
        }
    }

    private static AppiumDriver createMinimalAndroidDriver() {
        String appiumServer = "http://127.0.0.1:4723";
        DesiredCapabilities caps = new DesiredCapabilities();

        // Absolute minimal capabilities for Android 14 compatibility
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");

        // App configuration
        String appPath = System.getProperty("user.dir") + "/src/test/resources/apps/android/android.apk";
        caps.setCapability("app", appPath);

        // Essential UiAutomator2 settings only
        caps.setCapability("newCommandTimeout", 60);
        caps.setCapability("uiautomator2ServerInstallTimeout", 90000); // 90 seconds
        caps.setCapability("uiautomator2ServerLaunchTimeout", 90000);

        // Don't force app installation, let Appium handle it naturally
        caps.setCapability("noReset", true);
        caps.setCapability("skipDeviceInitialization", true); // Skip device setup to avoid issues
        caps.setCapability("skipServerInstallation", true); // Skip server installation to see if existing works

        logger.info("Testing with minimal capabilities for Android 14 compatibility...");
        logger.info("App path: {}", appPath);

        try {
            return new AndroidDriver(new URL(appiumServer), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed Appium server URL: " + appiumServer, e);
        }
    }
}
