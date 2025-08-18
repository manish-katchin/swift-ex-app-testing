package com.swiftEx.mobileAutomationFramework.support;

import com.swiftEx.mobileAutomationFramework.driver.DriverFactory;
import com.swiftEx.mobileAutomationFramework.pages.PinCreationPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    // Static variables to share driver and page objects across all step definition classes
    protected static AppiumDriver driver;
    protected static PinCreationPage pinPage;
    
    @BeforeAll
    public static void globalSetup() {
        // global setup before any scenarios (optional)
        logger.info("Global setup - initializing test framework");
    }

    @Before
    public void setUp() {
        logger.info("Test setup - Initializing driver and page objects...");
        try {
            // Initialize the driver and page objects for each scenario
            logger.info("Creating driver with DriverFactory...");
            driver = DriverFactory.getDriver();
            logger.info("SUCCESS: Driver created successfully: " + driver.getSessionId());
            
            pinPage = new PinCreationPage(driver);
            logger.info("SUCCESS: Page objects initialized successfully!");
        } catch (Exception e) {
            System.err.println("ERROR: Driver initialization failed:");
            System.err.println("   Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("   Cause: " + e.getCause().getMessage());
            }
            e.printStackTrace();
            logger.info(" Check:");
            logger.info("   1. Appium server running on http://127.0.0.1:4723");
            logger.info("   2. Android emulator connected: adb devices");
            logger.info("   3. APK file exists at: src/test/resources/apps/android/android.apk");
        }
    }

    @After
    public void tearDown() {
        logger.info("Test cleanup - framework completed!");
        try {
            if (driver != null) {
                DriverFactory.quitDriver();
                driver = null;
                pinPage = null;
            }
        } catch (Exception e) {
            logger.warn("Driver cleanup skipped: " + e.getMessage());
        }
    }

    @AfterAll
    public static void globalTearDown() {
        // ensure driver cleanup at the end of all tests
        logger.info("Global cleanup - ensuring all drivers are closed");
        DriverFactory.quitDriver();
    }

    // Getter methods to access page objects from step definition classes
    public static AppiumDriver getDriver() {
        return driver;
    }
    
    public static PinCreationPage getPinPage() {
        return pinPage;
    }
}
