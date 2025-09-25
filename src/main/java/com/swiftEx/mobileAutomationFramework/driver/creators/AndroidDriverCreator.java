package com.swiftEx.mobileAutomationFramework.driver.creators;

import com.swiftEx.mobileAutomationFramework.utils.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Android-specific driver creator
 */
@Slf4j
public class AndroidDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(URL serverUrl, DesiredCapabilities capabilities) {
        log.info("Creating Android driver");
        return new AndroidDriver(serverUrl, capabilities);
    }

    @Override
    public DesiredCapabilities buildCapabilities() {
        log.info("Building Android capabilities");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // W3C standard capabilities (no prefix needed)
        capabilities.setCapability("platformName", "Android");

        // Appium-specific capabilities (require appium: prefix for W3C compliance)
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformVersion",
                ConfigLoader.getProperty("android.platform.version", "12"));
        capabilities.setCapability("appium:deviceName",
                ConfigLoader.getProperty("android.device.name", "Android Emulator"));
        capabilities.setCapability("appium:app",
                System.getProperty("user.dir") + "/"
                        + ConfigLoader.getProperty("android.app.path", "src/test/resources/apps/android/android.apk"));

        // Improved session management capabilities
        capabilities.setCapability("appium:appWaitForLaunch", false);
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:noReset", false); // Changed to true to prevent session issues
        capabilities.setCapability("appium:fullReset", false);
        capabilities.setCapability("appium:appWaitActivity", "*");
        capabilities.setCapability("appium:appWaitDuration", 30000);
        capabilities.setCapability("appium:newCommandTimeout", 300); // Increased timeout
        capabilities.setCapability("appium:sessionOverride", true); // Allow session override
        capabilities.setCapability("appium:clearSystemFiles", false); // Prevent system file cleanup

        // UiAutomator2 specific settings
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 60000);
        capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 60000);

        return capabilities;
    }

    @Override
    public String getServerUrl() {
        return ConfigLoader.getProperty("android.server.url", "http://localhost:4723/");
    }

    @Override
    public void cleanup(AppiumDriver driver) {
        if (driver != null) {
            try {
                log.info("Terminating Android app");
                ((AndroidDriver) driver).terminateApp(ConfigLoader.getProperty("android.app.bundle.id"));
            } catch (Exception e) {
                log.warn("Failed to terminate Android app: {}", e.getMessage());
            }
        }
    }
}
