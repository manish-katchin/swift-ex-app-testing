package com.swiftEx.mobileAutomationFramework.steps;

import com.swiftEx.mobileAutomationFramework.driver.DriverFactory;
import com.swiftEx.mobileAutomationFramework.pages.PinCreationPage;
import com.swiftEx.mobileAutomationFramework.utils.AllureUtils;
import com.swiftEx.mobileAutomationFramework.utils.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    
    // Add synchronization to ensure scenarios don't interfere with each other
    private static final Object DRIVER_LOCK = new Object();
    
    // Static variables to share driver and page objects across all step definition classes
    private static AppiumDriver driver;
    private static PinCreationPage pinPage;

        @Before
    public void setUp(Scenario scenario) {
        synchronized (DRIVER_LOCK) {
            logger.info("=== STARTING NEW SCENARIO: {} ===", scenario.getName());
            
            // Set test context for SauceLabs test naming
            TestContext.setScenarioName(scenario.getName());
            TestContext.setFeatureName("Create PIN"); // Can be extracted from scenario.getUri() if needed
            String[] tags = scenario.getSourceTagNames().toArray(new String[0]);
            TestContext.setTags(tags);
            TestContext.printContext();
            
            logger.info("INFO: Test setup - Initializing fresh driver and page objects...");
            
            try {
                // Force driver cleanup before creating new one (ensures fresh app launch)
                if (driver != null) {
                    logger.info("WARNING: Found existing driver - forcing cleanup before new scenario...");
                    DriverFactory.quitDriver();
                    driver = null;
                    pinPage = null;
                    try {
                        Thread.sleep(3000); // Longer pause after cleanup
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                
                // Initialize the driver and page objects for each scenario
                logger.info("INFO: Creating fresh driver session for scenario...");
                driver = DriverFactory.getDriver();
                logger.info("SUCCESS: Fresh driver created successfully - Session: {}", driver.getSessionId());

                // Initialize page objects
                pinPage = new PinCreationPage(driver);
                logger.info("SUCCESS: Page objects initialized successfully!");
                
                // Wait for app to launch properly
                waitForAppToLaunch();
                
                logger.info("SUCCESS: Fresh app launch completed for scenario: {}", scenario.getName());
                
            } catch (Exception e) {
                logger.error("ERROR: Driver initialization failed for scenario: {}", scenario.getName());
                logger.error("   Error: {}", e.getMessage());
                if (e.getCause() != null) {
                    logger.error("   Cause: {}", e.getCause().getMessage());
                }
                logger.error("Exception details:", e);
                logger.info("INFO: Troubleshooting checklist:");
                logger.info("   1. SUCCESS: Appium server running on http://127.0.0.1:4723");
                logger.info("   2. SUCCESS: Android emulator connected: adb devices");
                logger.info("   3. SUCCESS: APK file exists at: src/test/resources/apps/android/android.apk");
                logger.info("   4. Check UiAutomator2 server installation logs");
                logger.info("   5. Verify device has sufficient storage space");
                logger.info("   6. Check for Android system security restrictions");
                logger.info("   7. Try: adb uninstall io.appium.settings && adb uninstall io.appium.uiautomator2.server");
                throw e; // Re-throw to fail the scenario setup
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        synchronized (DRIVER_LOCK) {
            logger.info("INFO: Starting test cleanup for scenario: {}", scenario.getName());
            
            try {
                // Add screenshot to Allure report on failure
                if (driver != null && scenario.isFailed()) {
                    AllureUtils.attachScreenshot(driver, "Screenshot on Failure - " + scenario.getName());
                }
                
                // Add scenario result to Allure
                if (scenario.isFailed()) {
                    AllureUtils.attachText("Scenario Failed", scenario.getName());
                } else {
                    AllureUtils.attachText("Scenario Passed", scenario.getName());
                }
                
                // Clear TestContext to prevent memory leaks
                TestContext.clear();
                
                // Aggressive driver cleanup to ensure fresh start for next scenario
                if (driver != null) {
                    logger.info("INFO: Cleaning up driver session...");
                    
                    try {
                        // Get session ID for logging
                        String sessionId = driver.getSessionId() != null ? driver.getSessionId().toString() : "unknown";
                        logger.info("INFO: Terminating session: {}", sessionId);
                    } catch (Exception e) {
                        logger.warn("WARNING: Could not get session ID: {}", e.getMessage());
                    }
                    
                    // Quit driver and clean up
                    DriverFactory.quitDriver();
                    
                    // Clear all references
                    driver = null;
                    pinPage = null;
                    
                    logger.info("SUCCESS: Driver cleanup completed - session terminated");
                    
                    // Longer pause to ensure complete cleanup before next scenario
                    try {
                        Thread.sleep(5000); // Increased wait time
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    
                } else {
                    logger.info("INFO: No driver to cleanup");
                }
            } catch (Exception e) {
                logger.error("ERROR: Driver cleanup failed: {}", e.getMessage(), e);
                
                // Force cleanup even if error occurred
                try {
                    DriverFactory.quitDriver();
                    driver = null;
                    pinPage = null;
                    logger.info("INFO: Force cleanup completed");
                } catch (Exception cleanupError) {
                    logger.error("ERROR: Force cleanup also failed: {}", cleanupError.getMessage());
                }
            }
            
            logger.info("INFO: Test cleanup completed for scenario: {}", scenario.getName());
            logger.info("=== SCENARIO CLEANUP FINISHED: {} ===", scenario.getName());
        }
    }
    
    @AfterAll
    public static void globalCleanup() {
        logger.info("=== GLOBAL CLEANUP: Ensuring all drivers are terminated ===");
        try {
            DriverFactory.quitDriver();
            driver = null;
            pinPage = null;
            logger.info("SUCCESS: Global cleanup completed");
        } catch (Exception e) {
            logger.error("ERROR: Global cleanup failed: {}", e.getMessage());
        }
    }

    // Getter methods to access page objects from step definition classes
    public static AppiumDriver getDriver() {
        return driver;
    }
    
    public static PinCreationPage getPinPage() {
        return pinPage;
    }
    
    /**
     * Wait for the app to launch and be ready for interaction
     * This method tries multiple strategies to detect app readiness
     */
    private void waitForAppToLaunch() {
        if (driver == null) {
            logger.warn("WARNING: No driver available - skipping app launch wait");
            return;
        }
        
        logger.info("INFO: Waiting for app to launch and be ready...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Increased timeout for UiAutomator2 installation
            
            // Strategy 1: Wait for app to be in foreground by checking current activity
            logger.info("INFO: Checking if app is in foreground...");
            try {
            Thread.sleep(5000); // Give more time for UiAutomator2 to initialize
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
            
            // Strategy 2: Try to verify UiAutomator2 is responsive with multiple checks
            logger.info("INFO: Testing UiAutomator2 connectivity and stability...");
            boolean uiAutomator2Ready = false;
            int maxUiAutomatorAttempts = 6; // 6 attempts * 5 seconds = 30 seconds
            
            for (int i = 0; i < maxUiAutomatorAttempts; i++) {
                try {
                    // Try a simple command to test if UiAutomator2 is working
                    if (driver instanceof AndroidDriver) {
                        String activity = ((AndroidDriver) driver).currentActivity();
                        logger.info("📍 Current activity: {}", activity);
                        
                        // Additional stability test - get window size
                        driver.manage().window().getSize();
                        
                        logger.info("SUCCESS: UiAutomator2 server is responsive and stable!");
                        uiAutomator2Ready = true;
                        break;
                    } else {
                        logger.info("INFO: Non-Android driver, skipping activity check");
                        uiAutomator2Ready = true;
                        break;
                    }
                } catch (Exception e) {
                    logger.warn("WARNING: UiAutomator2 not ready yet (attempt {}/{}): {}", (i + 1), maxUiAutomatorAttempts, e.getMessage());
                    try {
                        Thread.sleep(5000); // Wait 5 seconds between attempts
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            
            if (!uiAutomator2Ready) {
                logger.error("ERROR: UiAutomator2 server readiness timeout after 30 seconds");
                logger.error("   This may cause element interaction failures");
            }
            
            // Strategy 3: Wait for any interactive element to appear (with timeout)
            logger.info("INFO: Waiting for any interactive elements...");
            try {
                // Try to find any common UI elements that indicate app is ready
                wait.until(ExpectedConditions.or(
                    // Common Android UI elements
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@clickable='true']")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@focusable='true']")),
                    // PIN specific elements
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'PIN') or contains(@content-desc,'PIN')]")),
                    // Welcome or main screen elements
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Welcome') or contains(@content-desc,'Welcome')]")),
                    // Any button or interactive element
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.ViewGroup[@clickable='true']"))
                ));
                logger.info("SUCCESS: App launched successfully - interactive elements found!");
            } catch (Exception e) {
                logger.warn("WARNING: No specific UI elements found, but continuing - app might have custom UI");
                logger.warn("   Error: {}", e.getMessage());
            }
            
            // Strategy 4: Additional wait for app stabilization
            logger.info("INFO: Allowing app to stabilize...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info("SUCCESS: App launch synchronization completed!");
            
        } catch (Exception e) {
            logger.warn("WARNING: App launch wait encountered issues: {}", e.getMessage());
            logger.warn("   Continuing with test execution...");
        }
    }
}
