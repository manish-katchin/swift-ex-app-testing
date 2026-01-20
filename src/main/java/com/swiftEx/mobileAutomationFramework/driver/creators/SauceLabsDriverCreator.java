package com.swiftEx.mobileAutomationFramework.driver.creators;

import com.swiftEx.mobileAutomationFramework.utils.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * SauceLabs-specific driver creator
 */
@Slf4j
public class SauceLabsDriverCreator implements DriverCreator {
    private static final Logger log = LoggerFactory.getLogger(SauceLabsDriverCreator.class);
    private String scenarioName;

    // Constructor to accept scenario name
    public SauceLabsDriverCreator() {
        this.scenarioName = null;
    }

    public SauceLabsDriverCreator(String scenarioName) {
        this.scenarioName = scenarioName;
    }
    public AppiumDriver createDriver(URL serverUrl, DesiredCapabilities capabilities) {
        log.info("Creating SauceLabs driver");
        Object platformObj = capabilities.getCapability("platformName");
        String platformName = platformObj != null ? platformObj.toString() : null;
        
        log.info("Platform object type: {}, value: {}", 
            platformObj != null ? platformObj.getClass().getSimpleName() : "null", platformName);

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

        // Determine platform type - since we're in SauceLabs, check the platform config
        String basePlatform = ConfigLoader.getPlatformName();
        boolean isAndroid = basePlatform.toLowerCase().contains("android");
        
        String platformName = isAndroid ? "Android" : "iOS";
        String deviceName = isAndroid ? 
            ConfigLoader.getProperty("saucelabs.android.deviceName", "Google Pixel 4 GoogleAPI Emulator") :
            ConfigLoader.getProperty("saucelabs.ios.deviceName", "iPhone 13 Simulator");
        String platformVersion = isAndroid ?
            ConfigLoader.getProperty("saucelabs.android.platformVersion", "12.0") :
            ConfigLoader.getProperty("saucelabs.ios.platformVersion", "16.0");
        String appPath = isAndroid ?
            ConfigLoader.getProperty("saucelabs.android.app", "sauce-storage:android.apk") :
            ConfigLoader.getProperty("saucelabs.ios.app", "sauce-storage:ios_simulator.zip");
        
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appium:platformVersion", platformVersion);
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:app", appPath);
        capabilities.setCapability("appium:newCommandTimeout", 300);

        // SauceLabs specific options
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", ConfigLoader.getProperty("saucelabs.username"));
        sauceOptions.put("accessKey", ConfigLoader.getProperty("saucelabs.accessKey"));
        
        // Use scenario name if available, otherwise use default
        String testName = scenarioName != null ? scenarioName : 
            ConfigLoader.getProperty("saucelabs.android.name", "Mobile Automation Test");
        sauceOptions.put("name", testName);
        
        sauceOptions.put("build", ConfigLoader.getProperty("sauce.build.name", "Build-" + System.currentTimeMillis()));
        sauceOptions.put("tags", ConfigLoader.getProperty("sauce.tags", "mobile,automation"));

        capabilities.setCapability("sauce:options", sauceOptions);

        // Platform specific capabilities
        if ("Android".equalsIgnoreCase(platformName)) {
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:autoGrantPermissions", true);
            capabilities.setCapability("appium:appPackage", ConfigLoader.getProperty("saucelabs.android.appPackage"));
            capabilities.setCapability("appium:appWaitActivity", ConfigLoader.getProperty("saucelabs.android.appWaitActivity"));
        } else if ("iOS".equalsIgnoreCase(platformName)) {
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:autoAcceptAlerts", true);
            capabilities.setCapability("appium:bundleId", ConfigLoader.getProperty("saucelabs.ios.bundleId"));
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
