package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateNewWalletPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CreateNewWalletPage.class);

    public CreateNewWalletPage(AppiumDriver driver) {
        super(driver, "wallet.yaml"); // Assumes locators are in createNewWallet.yaml
        logger.info("✅ CreateNewWalletPage ready for platform: {}", platform.toUpperCase());
    }

    public void tapCreateNewWalletButton() {
        logger.info("📝 Tapping 'CREATE A NEW WALLET' button");
        click("createNewWalletBtn");
    }

    public boolean isBackupWalletScreenVisible() {
        logger.info("🔍 Checking if backup wallet screen is visible");
        return isVisible("privateKeyTitle");
    }

    public boolean isPrivateKeyLostWarningVisible() {
        logger.info("🔍 Checking for 'If I lose my private key, my funds will be lost' warning");
        return isVisible("losePrivateKeyWarning");
    }

    public boolean isPrivateKeyStolenWarningVisible() {
        logger.info("🔍 Checking for 'If I share my private key, my funds can get stolen' warning");
        return isVisible("sharePrivateKeyWarning");
    }

    public boolean isContinueButtonVisible() {
        logger.info("🔍 Checking for 'Continue' button");
        return isVisible("continueBtn");
    }
}
