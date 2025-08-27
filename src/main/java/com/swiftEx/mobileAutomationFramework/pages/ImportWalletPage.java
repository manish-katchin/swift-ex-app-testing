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
        boolean displayed = isDisplayed("multi_chain_wallet_header");
        logger.info("Multi-Chain Wallet header visibility: {}", displayed);
        return displayed;
    }

    public boolean isErrorMessageDisplayed(String errorMessage) {
        boolean displayed = isDisplayed("mnemonic_error_message");
        logger.info("Error message visibility: {}", displayed);
        return displayed;
    }

    public boolean isPrivateKeyErrorMessageDisplayed(String errorMessage) {
        boolean displayed = isDisplayed("private_key_error_message");
        logger.info("Private key error message visibility: {}", displayed);
        return displayed;
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
}
