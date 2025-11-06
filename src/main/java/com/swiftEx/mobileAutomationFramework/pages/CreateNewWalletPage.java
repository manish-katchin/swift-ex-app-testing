package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateNewWalletPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CreateNewWalletPage.class);

    public CreateNewWalletPage(AppiumDriver driver) {
        super(driver, "CreateNewWallet.yaml");
        logger.info("✅ CreateNewWalletPage ready for platform: {}", getPlatform().toUpperCase());
    }

  public boolean isCreateWalletButtonVisible() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(45));
        WebElement createWalletButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(getBy("create_wallet_button"))
        );
        boolean visible = createWalletButton.isDisplayed();
        logger.info("Create wallet button visibility: {}", visible);
        return visible;
    } catch (Exception e) {
        logger.error("Error waiting for create wallet button: {}", e.getMessage());
        return false;
    }
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
