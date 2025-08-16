package com.swiftEx.mobileAutomationFramework.pages;

import com.swiftEx.mobileAutomationFramework.utils.ElementActions;
import com.swiftEx.mobileAutomationFramework.utils.LocatorLoader;
import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * PinCreationPage with YAML locators and instrumentation crash recovery
 */
public class PinCreationPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PinCreationPage.class);
    private final Map<String, Map<String, String>> locators;
    private final String platform;
    private final ElementActions elementActions;

    public PinCreationPage(AppiumDriver driver) {
        super(driver); // Initialize base page with recovery capabilities
        this.platform = System.getProperty("platform", "android").toLowerCase();
        String resource = "/locators/" + platform + "/pinCreation.yaml";
        this.locators = LocatorLoader.load(resource);
        this.elementActions = new ElementActions(driver); // Initialize ElementActions with driver
    }

    // ===== UTILITY METHOD FOR YAML LOCATOR ACCESS =====
    private Map<String, String> getLocator(String locatorKey) {
        Map<String, String> locator = locators.get(locatorKey);
        if (locator == null) {
            throw new RuntimeException("ERROR: Locator '" + locatorKey + "' not found in YAML file");
        }
        return locator;
    }

    public void enterPIN(String pin) {
        logger.info("Starting PIN entry with recovery support: {}", pin);

        for (char c : pin.toCharArray()) {
            logger.info("Entering digit: {}", c);
            tapDigitWithRecovery(String.valueOf(c));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        logger.info("PIN entry completed successfully: {}", pin);
    }

    public void confirmPIN(String pin) {
        logger.info("Confirming PIN with recovery support: {}", pin);

        for (char c : pin.toCharArray()) {
            logger.info("Confirming digit: {}", c);
            tapDigitWithRecovery(String.valueOf(c));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        logger.info("PIN confirmation completed successfully: {}", pin);
    }

    private void tapDigitWithRecovery(String digit) {
        logger.info("Tapping digit with recovery support: {}", digit);

        Map<String, String> key = locators.get("digit_" + digit);
        By by;

        if (key != null) {
            by = LocatorUtils.toBy(key);
            logger.info("Using YAML locator for digit {}", digit);
        } else {
            if ("android".equals(platform)) {
                Map<String, String> fallbackLocator = new HashMap<>();
                fallbackLocator.put("strategy", "xpath");
                fallbackLocator.put("value", "//android.view.ViewGroup[@content-desc=\"" + digit + "\"]");
                by = LocatorUtils.toBy(fallbackLocator);
                logger.info("Using fallback XPath locator for digit {}", digit);
            } else {
                Map<String, String> fallbackLocator = new HashMap<>();
                fallbackLocator.put("strategy", "accessibilityId");
                fallbackLocator.put("value", digit);
                by = LocatorUtils.toBy(fallbackLocator);
                logger.info("Using fallback accessibility ID for digit {}", digit);
            }
        }

        clickElementWithRetry(by, 3);
        logger.info("Successfully tapped digit: {}", digit);
    }

    public String getErrorMessageText() {
        return elementActions.getText(getLocator("incorrectPIN"));
    }

}
