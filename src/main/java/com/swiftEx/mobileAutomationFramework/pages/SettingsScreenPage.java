package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

public class SettingsScreenPage extends BasePage {
    public SettingsScreenPage(AppiumDriver driver) {
        super(driver, "HomePage.yaml");
        logger.info("✅ SettingsScreenPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public void clickLogoutOption() {
        tap(LocatorUtils.getUIAutomatorTextLocatorBy("Log Out"));
        logger.info("Clicked Logout option");
    }
    private static final Logger logger = LoggerFactory.getLogger(SettingsScreenPage.class);



    public boolean isSettingsHeaderDisplayed() {
        boolean displayed = isDisplayed("settings_header");
        logger.info("Settings header visibility: {}", displayed);
        return displayed;
    }

    public boolean isChooseWalletOptionDisplayed() {
        boolean displayed = isDisplayed("choose_wallet_option");
        logger.info("Choose Wallet option visibility: {}", displayed);
        return displayed;
    }

    public boolean isDarkModeOptionDisplayed() {
        boolean displayed = isDisplayed("dark_mode_option");
        logger.info("Dark Mode option visibility: {}", displayed);
        return displayed;
    }

    public boolean isExchangeOptionDisplayed() {
        boolean displayed = isDisplayed("exchange_option");
        logger.info("Exchange option visibility: {}", displayed);
        return displayed;
    }

    public boolean isTransactionsOptionDisplayed() {
        boolean displayed = isDisplayed("transactions_option");
        logger.info("Transactions option visibility: {}", displayed);
        return displayed;
    }

    public boolean isBiometricAuthenticationOptionDisplayed() {
        boolean displayed = isDisplayed("biometric_authentication_option");
        logger.info("Biometric Authentication option visibility: {}", displayed);
        return displayed;
    }

    public boolean isPreferenceOptionDisplayed() {
        boolean displayed = isDisplayed("preference_option");
        logger.info("Preference option visibility: {}", displayed);
        return displayed;
    }

    public boolean isPushNotificationOptionDisplayed() {
        boolean displayed = isDisplayed("push_notification_option");
        logger.info("Push Notification option visibility: {}", displayed);
        return displayed;
    }

    public boolean isHelpCenterOptionDisplayed() {
        boolean displayed = isDisplayed("help_center_option");
        logger.info("Help Center option visibility: {}", displayed);
        return displayed;
    }

    public boolean isLogoutOptionDisplayed() {
        boolean displayed = isDisplayed("logout_option");
        logger.info("Logout option visibility: {}", displayed);
        return displayed;
    }

    public void clickBackButton() {
        tap("back_button");
        logger.info("Clicked back button");
    }
}
