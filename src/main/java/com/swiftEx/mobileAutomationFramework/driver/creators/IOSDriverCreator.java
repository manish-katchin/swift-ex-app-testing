package com.swiftEx.mobileAutomationFramework.driver.creators;

import com.swiftEx.mobileAutomationFramework.utils.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * iOS-specific driver creator
 */
@Slf4j
public class IOSDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(URL serverUrl, DesiredCapabilities capabilities) {
        log.info("Creating iOS driver");
        return new IOSDriver(serverUrl, capabilities);
    }

    @Override
    public DesiredCapabilities buildCapabilities() {
        log.info("Building iOS capabilities");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // W3C standard capabilities (no prefix needed)
        capabilities.setCapability("platformName", "iOS");

        // Appium-specific capabilities (require appium: prefix for W3C compliance)
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:platformVersion", ConfigLoader.getProperty("ios.platform.version", "15.0"));
        capabilities.setCapability("appium:deviceName",
                ConfigLoader.getProperty("ios.device.name", "iPhone Simulator"));
        capabilities.setCapability("appium:app",
                System.getProperty("user.dir") + "/"
                        + ConfigLoader.getProperty("ios.app.path", "src/test/resources/apps/ios/ios.app"));
        capabilities.setCapability("appium:bundleId", ConfigLoader.getProperty("ios.app.bundle.id"));
        capabilities.setCapability("appium:udid", ConfigLoader.getProperty("ios.device.udid"));
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("appium:autoDismissAlerts", false);
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:wdaLocalPort", ConfigLoader.getProperty("ios.wda.local.port", "8100"));
        capabilities.setCapability("appium:useNewWDA", false);
        capabilities.setCapability("appium:newCommandTimeout", 60);

        return capabilities;
    }

    @Override
    public String getServerUrl() {
        return ConfigLoader.getProperty("ios.server.url", "http://localhost:4723/wd/hub");
    }

    @Override
    public void cleanup(AppiumDriver driver) {
        if (driver != null) {
            try {
                log.info("Terminating iOS app");
                ((IOSDriver) driver).terminateApp(ConfigLoader.getProperty("ios.app.bundle.id"));
            } catch (Exception e) {
                log.warn("Failed to terminate iOS app: {}", e.getMessage());
            }
        }
    }
}
