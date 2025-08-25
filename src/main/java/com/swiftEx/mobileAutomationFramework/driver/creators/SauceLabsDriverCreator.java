package com.swiftEx.mobileAutomationFramework.driver.creators;

import com.swiftEx.mobileAutomationFramework.utils.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * SauceLabs-specific driver creator
 */
@Slf4j
public class SauceLabsDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(URL serverUrl, DesiredCapabilities capabilities) {
        log.info("Creating SauceLabs driver");
        String platformName = (String) capabilities.getCapability("platformName");

        if ("Android".equalsIgnoreCase(platformName)) {
            return new AndroidDriver(serverUrl, capabilities);
        } else if ("iOS".equalsIgnoreCase(platformName)) {
            return new IOSDriver(serverUrl, capabilities);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }
    }

    @Override
    public DesiredCapabilities buildCapabilities() {
        log.info("Building SauceLabs capabilities");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String platformName = ConfigLoader.getProperty("sauce.platform.name");
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appium:platformVersion", ConfigLoader.getProperty("sauce.platform.version"));
        capabilities.setCapability("appium:deviceName", ConfigLoader.getProperty("sauce.device.name"));
        capabilities.setCapability("appium:app", ConfigLoader.getProperty("sauce.app.id"));
        capabilities.setCapability("appium:newCommandTimeout", 300);

        // SauceLabs specific options
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", ConfigLoader.getProperty("sauce.username"));
        sauceOptions.put("accessKey", ConfigLoader.getProperty("sauce.access.key"));
        sauceOptions.put("name", ConfigLoader.getProperty("sauce.test.name", "Mobile Automation Test"));
        sauceOptions.put("build", ConfigLoader.getProperty("sauce.build.name", "Build-" + System.currentTimeMillis()));
        sauceOptions.put("tags", ConfigLoader.getProperty("sauce.tags", "mobile,automation"));

        capabilities.setCapability("sauce:options", sauceOptions);

        // Platform specific capabilities
        if ("Android".equalsIgnoreCase(platformName)) {
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:autoGrantPermissions", true);
        } else if ("iOS".equalsIgnoreCase(platformName)) {
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:autoAcceptAlerts", true);
        }

        return capabilities;
    }

    @Override
    public String getServerUrl() {
        return String.format("https://ondemand.%s.saucelabs.com:443/wd/hub",
                ConfigLoader.getProperty("sauce.data.center", "us-west-1"));
    }

    @Override
    public void cleanup(AppiumDriver driver) {
        if (driver != null) {
            try {
                log.info("Cleaning up SauceLabs session");
                // SauceLabs handles cleanup automatically
                // Just log the session for reference
                String sessionId = driver.getSessionId().toString();
                log.info("SauceLabs session completed: {}", sessionId);
            } catch (Exception e) {
                log.warn("Failed to cleanup SauceLabs session: {}", e.getMessage());
            }
        }
    }
}
