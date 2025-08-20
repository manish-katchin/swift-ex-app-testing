package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    
    public HomePage(AppiumDriver driver) {
        super(driver, "HomePage.yaml");
    }

    // Balance Section
    public String getBalanceAmount() {
        return elementActions.getText(getBy("balance_amount"));
    }

    public String getBalanceCurrency() {
        return elementActions.getText(getBy("balance_currency"));
    }

    // Action Methods
    public void clickReceive() {
        elementActions.click(getBy("receive_button"));
    }

    public void clickSend() {
        elementActions.click(getBy("send_button"));
    }

    public void clickSwap() {
        elementActions.click(getBy("swap_button"));
    }

    public void clickBuy() {
        elementActions.click(getBy("buy_button"));
    }

    // Tab Navigation Methods
    public void clickAssetsTab() {
        elementActions.click(getBy("assets_tab"));
    }

    public void clickAddAssetsTab() {
        elementActions.click(getBy("add_assets_tab"));
    }

    // Bottom Navigation Methods
    public void navigateToHome() {
        elementActions.click(getBy("home_tab"));
    }

    public void navigateToWallet() {
        elementActions.click(getBy("wallet_tab"));
    }

    public void navigateToMarket() {
        elementActions.click(getBy("market_tab"));
    }

    public void navigateToExchange() {
        elementActions.click(getBy("exchange_tab"));
    }

    public void navigateToSettings() {
        elementActions.click(getBy("settings_tab"));
    }

    // Asset List Methods
    public void selectEthereumAsset() {
        elementActions.click(getBy("ethereum_item"));
    }

    public void selectXLMAsset() {
        elementActions.click(getBy("xlm_item"));
    }

    public void selectBinanceAsset() {
        elementActions.click(getBy("binance_item"));
    }

    // Asset Action Methods
    public void clickTradeOnAsset() {
        elementActions.click(getBy("trade_button"));
    }

    public void clickBuyOnAsset() {
        elementActions.click(getBy("asset_buy_button"));
    }

    // Verification Methods
    public boolean isOnHomePage() {
        try {
            Thread.sleep(5000);
            wait.until(driver -> driver.findElement(getBy("assets_tab")).isDisplayed());
            return driver.findElement(getBy("assets_tab")).isDisplayed() &&
                   driver.findElement(getBy("home_tab")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAssetVisible(String assetName) {
        try {
            return driver.findElement(getBy(assetName.toLowerCase() + "_item")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
