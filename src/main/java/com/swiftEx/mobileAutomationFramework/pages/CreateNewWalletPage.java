package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.util.Map;

public class CreateNewWalletPage extends BasePage {
    public boolean isCreateWalletButtonVisible() {
        try {
            waitForElementVisible(getBy("create_wallet_button"));
            return getBy("create_wallet_button") != null && elementActions.isElementVisible(getBy("create_wallet_button"));
        } catch (Exception e) {
            return false;
        }
    }
    public CreateNewWalletPage(AppiumDriver driver) {
        super(driver, "CreateNewWallet.yaml");
    }

    public void tapCreateWallet() {
        elementActions.click(getBy("create_wallet_button"));
    }

    public void tapImportWallet() {
        elementActions.click(getBy("import_wallet_button"));
    }

    public void tapUseDefaultWallet() {
        elementActions.click(getBy("use_default_wallet_button"));
    }
}
