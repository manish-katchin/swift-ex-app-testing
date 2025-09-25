package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateNewWalletPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CreateNewWalletPage.class);

    public CreateNewWalletPage(AppiumDriver driver) {
        super(driver, "CreateNewWallet.yaml");
        logger.info("✅ CreateNewWalletPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public boolean isCreateWalletButtonVisible() {
        boolean visible = isDisplayed("create_wallet_button");
        logger.info("Create wallet button visibility: {}", visible);
        return visible;
    }

    public void tapCreateWallet() {
        tap("create_wallet_button");
        logger.info("Tapped create wallet button");
    }

    public void tapImportWallet() {
        tap("import_wallet_button");
        logger.info("Tapped import wallet button");
    }

    public void tapUseDefaultWallet() {
        tap("use_default_wallet_button");
        logger.info("Tapped use default wallet button");
    }
}
