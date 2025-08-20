package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;

public class PopupPage extends BasePage {
    public PopupPage(AppiumDriver driver) {
        super(driver, "Popup.yaml");
    }

    /**
     * Action methods
     */
    public void clickCancel() {
        elementActions.click(getBy("cancel_button"));
    }

    public void clickContinue() {
        elementActions.click(getBy("continue_button"));
    }

    /**
     * Verification methods
     */
    public boolean isBiometricAuthPopupDisplayed() {
        try {
            return driver.findElement(getBy("biometric_auth_title")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getBiometricAuthTitle() {
        return elementActions.getText(getBy("biometric_auth_title"));
    }

    public String getBiometricDescription() {
        return elementActions.getText(getBy("biometric_description"));
    }

    public String getBiometricInstruction() {
        return elementActions.getText(getBy("biometric_instruction"));
    }

    /**
     * Handles the biometric authentication by clicking the appropriate button
     * In this case, we'll click 'Continue' to proceed with biometric setup
     */
    public void handleBiometricAuth() {
        if (isBiometricAuthPopupDisplayed()) {
            clickCancel();
            // Add a small wait for the biometric prompt to be processed
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
