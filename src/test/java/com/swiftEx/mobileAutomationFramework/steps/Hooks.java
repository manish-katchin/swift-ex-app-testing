package com.swiftEx.mobileAutomationFramework.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.driver.DriverFactory;
import com.swiftEx.mobileAutomationFramework.pages.PrivateKeyPage;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    DriverFactory driverFactory = new DriverFactory();

    //   @Before("@VerifyPresenceofScannerIcon")
    // public void clearAppCacheBeforeScenario(Scenario scenario) {
    //     String packageName = "com.app.swiftEx.app";
    //     try {
    //         // For Android: use adb shell command to clear cache
    //         Runtime.getRuntime().exec("adb shell pm clear " + packageName);
    //         // Optionally, add a short wait to ensure cache is cleared
    //         Thread.sleep(2000);
    //     } catch (Exception e) {
    //         System.err.println("Failed to clear app cache: " + e.getMessage());
    //     }
    // }
    // @Before
    // public void setUp(Scenario scenario) {
    //     logger.info("Starting scenario: {}", scenario.getName());
    //     BaseStep.setDriver(driverFactory.getDriver());
    // }

    @Before
    public void setUp(Scenario scenario) {
        // Clear cache if scenario has the tag
        if (scenario.getSourceTagNames().contains("@VerifyPresenceofScannerIcon")) {
            String packageName = "com.app.swiftEx.app";
            try {
                Runtime.getRuntime().exec("adb shell pm clear " + packageName);
                Thread.sleep(2000);
                logger.info("Cleared app cache for scenario: {}", scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to clear app cache: " + e.getMessage());
            }
        }
        logger.info("Starting scenario: {}", scenario.getName());
        BaseStep.setDriver(driverFactory.getDriver());
    }
    @After
    public void tearDown(Scenario scenario) {
        logger.info("Cleaning up scenario: {}", scenario.getName());

        try {
            // Clear static data only
            BaseStep.clearPageCache();
            BaseStep.quitDriver();
            logger.debug("Scenario cleanup completed");

        } catch (Exception e) {
            logger.warn("Error during scenario cleanup: {}", e.getMessage());
        }

        logger.info("Scenario cleanup completed: {}", scenario.getName());
    }

}
