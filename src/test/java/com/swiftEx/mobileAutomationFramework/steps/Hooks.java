package com.swiftEx.mobileAutomationFramework.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.driver.DriverFactory;
import com.swiftEx.mobileAutomationFramework.pages.PrivateKeyPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    DriverFactory driverFactory = new DriverFactory();

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        BaseStep.setDriver(driverFactory.getDriver());
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Cleaning up scenario: {}", scenario.getName());

        try {
            // Clear static data only
            PrivateKeyPage.clearMnemonicMap();
            driverFactory.quitDriver();
            logger.debug("Scenario cleanup completed");

        } catch (Exception e) {
            logger.warn("Error during scenario cleanup: {}", e.getMessage());
        }

        logger.info("Scenario cleanup completed: {}", scenario.getName());
    }

}
