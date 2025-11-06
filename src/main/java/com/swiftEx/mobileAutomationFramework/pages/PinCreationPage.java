package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

public class PinCreationPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PinCreationPage.class);

    public PinCreationPage(AppiumDriver driver) {
        super(driver, "pinCreation.yaml");
        logger.info("✅ PinCreationPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public void enterPIN(String pin) throws InterruptedException {
        logger.info("📝 Entering PIN: {}", pin);

        for (char digit : pin.toCharArray()) {
            Thread.sleep(500); // Small delay to mimic user input speed
            tapDigit(String.valueOf(digit));
        }

        logger.info("✅ PIN entry completed: {}", pin);
    }

    public String getErrorMessage() {
        return getText("error_message");
    }

    public void tapDigit(String digit) {
        By locator;

        if (isAndroid()) {
            locator = LocatorUtils.getUIAutomatorTextLocatorBy(digit);
        } else if (isIOS()) {
            locator = AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == '" + digit + "'");
        } else {
            throw new IllegalStateException("Unsupported platform: " + getPlatform());
        }
        waitForElement(locator, 20);
        tap(locator);
        logger.debug("Tapped digit '{}' using locator: {}", digit, locator);
    }

    public boolean isEnterYourPinTextVisible() {
        By locator = LocatorUtils.getUIAutomatorTextLocatorBy("Please create a pin");
        boolean visible = isDisplayed(locator);
        logger.info("'Please enter your pin' text visibility: {}", visible);
        return visible;
    }
   public void tapFingerPrintSensor() {
        tap("fingerprint_option");
        logger.info("Tapped on fingerprint sensor");
    }

}
