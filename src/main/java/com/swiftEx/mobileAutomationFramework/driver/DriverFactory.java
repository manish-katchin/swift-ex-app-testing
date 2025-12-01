package com.swiftEx.mobileAutomationFramework.driver;

import static java.lang.Math.log;

import com.swiftEx.mobileAutomationFramework.driver.creators.AndroidDriverCreator;
import com.swiftEx.mobileAutomationFramework.driver.creators.DriverCreator;
import com.swiftEx.mobileAutomationFramework.driver.creators.IOSDriverCreator;
import com.swiftEx.mobileAutomationFramework.driver.creators.SauceLabsDriverCreator;
import com.swiftEx.mobileAutomationFramework.utils.PlatformConfig;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class DriverFactory {

    private AppiumDriver driver;
    private DriverCreator creator;

    public AppiumDriver getDriver() {
        {
            driver = createNewDriver();
        }
        return driver;
    }

    public void quitDriver() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            log.error("Error quitting driver: {}", e.getMessage());
        }
    }

    // --- internals ---

    private AppiumDriver createDriver() {
        log.info("=== CREATING APPIUM DRIVER (Singleton) ===");
        PlatformConfig.printConfigurationSummary();

        try {
            creator = getDriverCreator();

            DesiredCapabilities capabilities = creator.buildCapabilities();
            URL serverUrl = new URL(creator.getServerUrl());

            log.info("Platform capabilities: {}", capabilities.asMap());

            AppiumDriver newDriver = creator.createDriver(serverUrl, capabilities);

            log.info("Driver created successfully!");
            log.info("Session ID: {}", newDriver.getSessionId());
            log.info("Platform: {}", PlatformConfig.getCurrentPlatform());
            log.info("Device: {}", newDriver.getCapabilities().getCapability("deviceName"));

            return newDriver;

        } catch (MalformedURLException e) {
            log.error("Invalid server URL", e);
            throw new RuntimeException("Invalid server URL", e);
        } catch (Exception e) {
            log.error("Failed to create driver for platform: {}", PlatformConfig.getCurrentPlatform(), e);
            throw new RuntimeException("Driver creation failed", e);
        }
    }

    private DriverCreator getDriverCreator() {
        if (PlatformConfig.isSauceLabs()) {
            log.info("Using SauceLabs driver creator");
            return new SauceLabsDriverCreator();
        } else if (PlatformConfig.isAndroid()) {
            log.info("Using Android driver creator");
            return new AndroidDriverCreator();
        } else if (PlatformConfig.isIOS()) {
            log.info("Using iOS driver creator");
            return new IOSDriverCreator();
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + PlatformConfig.getCurrentPlatform());
        }
    }

 private AppiumDriver createNewDriver() {
    log.info("=== CREATING NEW APPIUM DRIVER ===");
    PlatformConfig.printConfigurationSummary();

    try {
        creator = getDriverCreator();

        DesiredCapabilities capabilities = creator.buildCapabilities();
        URL serverUrl = new URL(creator.getServerUrl());

        log.info("Platform capabilities: {}", capabilities.asMap());

        AppiumDriver newDriver = creator.createDriver(serverUrl, capabilities);

        log.info("Driver created successfully!");
        log.info("Session ID: {}", newDriver.getSessionId());
        log.info("Platform: {}", PlatformConfig.getCurrentPlatform());
        log.info("Device: {}", newDriver.getCapabilities().getCapability("deviceName"));

        return newDriver;

    } catch (MalformedURLException e) {
        log.error("Invalid server URL", e);
        throw new RuntimeException("Invalid server URL", e);
    } catch (Exception e) {
        log.error("Failed to create driver for platform: {}", PlatformConfig.getCurrentPlatform(), e);
        throw new RuntimeException("Driver creation failed", e);
    }
}
}