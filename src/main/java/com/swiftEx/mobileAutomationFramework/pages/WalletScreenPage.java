package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletScreenPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(WalletScreenPage.class);

    public WalletScreenPage(AppiumDriver driver) {
        super(driver, "WalletScreen.yaml");
        logger.info("✅ WalletScreenPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public boolean isMyWalletOptionDisplayed() {
        return isDisplayed("my_wallet_card");
    }

    public boolean isCreateWalletOptionDisplayed() {
        return isDisplayed("create_wallet_card");
    }

    public boolean isImportWalletOptionDisplayed() {
        return isDisplayed("import_wallet_card");
    }

    public boolean isChooseWalletOptionDisplayed() {
        return isDisplayed("choose_wallet_card");
    }

    public void clickCreateWalletOption() {
        tap("create_wallet_card");
        logger.info("Clicked on Create Wallet option");
    }
    
}
