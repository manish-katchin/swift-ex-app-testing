package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopupPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PopupPage.class);

    public PopupPage(AppiumDriver driver) {
        super(driver, "Popup.yaml");
        logger.info("✅ PopupPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public void clickCancel() {
        tap("cancel_button");
        logger.debug("Clicked cancel button");
    }

    public void clickContinue() {
        tap("continue_button");
        logger.debug("Clicked continue button");
    }

    public boolean isBiometricAuthPopupDisplayed() {
        return isDisplayed("biometric_auth_title");
    }

    public String getBiometricAuthTitle() {
        return getText("biometric_auth_title");
    }

    public String getBiometricDescription() {
        return getText("biometric_description");
    }

    public String getBiometricInstruction() {
        return getText("biometric_instruction");
    }

    public void handleBiometricAuth() {
        if (isBiometricAuthPopupDisplayed()) {
            logger.info("📱 Biometric auth popup detected, clicking cancel");
            clickCancel();
            return;
        }
        logger.info("📱 Biometric auth popup not detected");
    }
}
