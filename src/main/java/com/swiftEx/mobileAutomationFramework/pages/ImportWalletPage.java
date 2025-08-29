package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

public class ImportWalletPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ImportWalletPage.class);

    public ImportWalletPage(AppiumDriver driver) {
        super(driver, "importWallet.yaml");
        logger.info("✅ ImportWalletPage ready for platform: {}", getPlatform().toUpperCase());
    }

    // Actions
    public void clickImportWalletButton() {
        tap("import_wallet_button");
        logger.info("Clicked Import Wallet button");
    }

    public void clickMultiChainWallet() {
        tap("multi_chain_wallet_section");
        logger.info("Clicked Multi-Chain Wallet section");
    }

    public void enterWalletName(String name) {
        sendKeys("wallet_name_input", name);
        logger.info("Entered wallet name: {}", name);
    }

    public void enterMnemonicPhrase(String phrase) {
        sendKeys("mnemonic_phrase_input", phrase);
        logger.info("Entered mnemonic phrase: {}", phrase);
    }

    public void enterJsonPassword(String password) {
        sendKeys(LocatorUtils.getUIAutomatorTextLocatorBy("json_password_input"), password);
        logger.info("Entered JSON password: {}", password);
    }

    public void clickBinanceSmartChain() {
        tap("binance_smart_chain_option");
        logger.info("Clicked Binance Smart Chain option");
    }

    // Verifications
    public boolean isOnImportWalletScreen() {
        boolean displayed = isDisplayed("import_wallet_screen");
        logger.info("Import Wallet screen visibility: {}", displayed);
        return displayed;
    }

    public boolean isImportWalletHeaderDisplayed() {
       return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("Import Wallet"), 20);
    }

    public boolean isMultiChainWalletSectionDisplayed() {
        return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("Multi-Chain Wallet"), 20);
    }

    public boolean isBinanceSmartChainOptionDisplayed() {
        boolean displayed = isDisplayed("binance_smart_chain_option");
        logger.info("Binance Smart Chain option visibility: {}", displayed);
        return displayed;
    }

    public boolean isEthereumOptionDisplayed() {
        boolean displayed = isDisplayed("ethereum_option");
        logger.info("Ethereum option visibility: {}", displayed);
        return displayed;
    }

    public boolean isMultiChainWalletHeaderDisplayed() {
       return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("Multi-Chain Wallet"), 20);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("Please enter a valid mnemonic"), 20);
    }

    public boolean isPrivateKeyErrorMessageDisplayed() {
       return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("Please enter a valid private key"), 20);
    }


    public void clickonEthereumWallet() {
        tap("ethereum_option");
        logger.info("Clicked Ethereum Wallet option");
    }

    // Public wrappers for protected BasePage methods
    public boolean isElementDisplayed(String locatorKey) {
        return super.isDisplayed(locatorKey);
    }

    public void tapElement(String locatorKey) {
        super.tap(locatorKey);
    }
    public boolean isEthereumWalletHeaderDisplayed() {
        boolean displayed = isDisplayed("ethereum_wallet_header");
        logger.info("Ethereum Wallet header visibility: {}", displayed);
        return displayed;
    }

     public void enterName(String name) {
        sendKeys("wallet_name_field", name); 
        logger.info("Entered wallet name: {}", name);
    }
}
