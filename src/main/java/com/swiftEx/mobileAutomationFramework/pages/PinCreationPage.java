package com.swiftEx.mobileAutomationFramework.pages;

import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PinCreationPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PinCreationPage.class);

    public PinCreationPage(AppiumDriver driver) {
        super(driver, "pinCreation.yaml"); 
        logger.info("✅ PinCreationPage ready for platform: {}", platform.toUpperCase());
    }

    /**
     * Enter PIN with automatic platform handling
     * 
     * @param pin PIN to enter (e.g., "123456")
     */
    public void enterPIN(String pin) {
        logger.info("📝 Entering PIN: {}", pin);

        for (char digit : pin.toCharArray()) {
            tapDigit(String.valueOf(digit));
            sleepBetweenTaps();
        }

        logger.info("✅ PIN entry completed: {}", pin);
    }

    /**
     * Confirm PIN with automatic platform handling
     * 
     * @param pin PIN to confirm
     */
    public void confirmPIN(String pin) {
        logger.info("🔐 Confirming PIN: {}", pin);

        for (char digit : pin.toCharArray()) {
            tapDigit(String.valueOf(digit));
            sleepBetweenTaps();
        }

        logger.info("✅ PIN confirmation completed: {}", pin);
    }

    /**
     * Get error message text (for validation scenarios)
     * 
     * @return Error message text
     */
    public String getErrorMessage() {

        return elementActions.getText(getBy("error_message"));

    }


    /**
     * Tap a digit with smart fallback logic
     * 
     * @param digit Single digit as string (e.g., "1", "2")
     */
    private void tapDigit(String digit) {
        logger.debug("🔢 Tapping digit: {}", digit);

        String digitKey = "digit_" + digit;

        if (hasLocator(digitKey)) {
            // Use locator from YAML file
            click(digitKey);
            logger.debug("✅ Used YAML locator for digit {}", digit);
        } else {
            // Smart platform fallback
            By fallbackLocator = createDigitFallbackLocator(digit);
            clickElementWithRetry(fallbackLocator, 3);
            logger.debug("⚡ Used fallback locator for digit {}", digit);
        }
    }

    /**
     * Create platform-specific fallback locator for digits
     * 
     * @param digit The digit to find
     * @return By locator for the digit
     */
    private By createDigitFallbackLocator(String digit) {
        if (isAndroid()) {
            // Android fallback: content-desc or XPath
            return By.xpath("//android.view.ViewGroup[@content-desc=\"" + digit + "\"]");
        } else {
            // iOS fallback: accessibility ID
            return AppiumBy.accessibilityId("digit-" + digit);
        }
    }

    /**
     * Small sleep between digit taps for stability
     */
    private void sleepBetweenTaps() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Sleep interrupted during PIN entry");
        }
    }
}
