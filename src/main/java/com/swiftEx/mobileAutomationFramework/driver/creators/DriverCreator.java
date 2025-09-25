package com.swiftEx.mobileAutomationFramework.driver.creators;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Interface for creating platform-specific drivers
 */
public interface DriverCreator {
    AppiumDriver createDriver(URL serverUrl, DesiredCapabilities capabilities);

    DesiredCapabilities buildCapabilities();

    String getServerUrl();

    void cleanup(AppiumDriver driver);
}
