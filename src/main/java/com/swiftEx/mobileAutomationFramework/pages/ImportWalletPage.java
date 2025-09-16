package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        // tapElement(LocatorUtils.getUIAutomatorTextLocatorBy("SomeOtherElement"));
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

    public void clickMnemonicButton() {
        tapElement("mnemonic_button");
        logger.info("Clicked Mnemonic button");
    }

    public void clickAnyWalletCard() {
        driver.findElement(By.xpath(
                "(//android.widget.TextView[@text=\"All Wallets\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[@clickable='true'])[position()=1]"))
                .click();
        logger.info("Clicked first Wallet card");
    }

    public boolean isAllWalletsHeaderDisplayed() {
        return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("All Wallets"), 20);
    }

    // isHomeScreenWithWalletDisplayed
    public boolean isHomeScreenWithWalletDisplayed() {
        boolean displayed = isDisplayed("home_screen_with_wallet");
        logger.info("Home screen with Selected wallet visibility: {}", displayed);
        return displayed;
    }

    public boolean isWalletNameInputDisplayed() {
        boolean displayed = isDisplayed("wallet_name_input");
        logger.info("Wallet Name input displayed: {}", displayed);
        return displayed;
    }

    public boolean isMnemonicPhraseInputDisplayed() {
        boolean displayed = isDisplayed("mnemonic_phrase_input");
        logger.info("Mnemonic Phrase input displayed: {}", displayed);
        return displayed;
    }

    public boolean isImportWalletButtonDisplayed() {
        boolean displayed = isDisplayed("import_wallet_button");
        logger.info("Import Wallet button displayed: {}", displayed);
        return displayed;
    }
    public boolean isImportWalletButtonDisabled() {
        try {
            WebElement importButton = driver.findElement(getBy("import_wallet_button"));
            boolean disabled = !importButton.isEnabled();
            logger.info("Import Wallet button disabled: {}", disabled);
            return disabled;
        } catch (Exception e) {
            logger.error("Error checking Import Wallet button disabled state: {}", e.getMessage());
            return false;
        }
    }

    public boolean isImportWalletButtonEnabled() {
        try {
            WebElement importButton = driver.findElement(getBy("import_wallet_button"));
            boolean enabled = importButton.isEnabled();
            logger.info("Import Wallet button enabled: {}", enabled);
            return enabled;
        } catch (Exception e) {
            logger.error("Error checking Import Wallet button enabled state: {}", e.getMessage());
            return false;
        }
    }
    public boolean isPrivateKeyOptionDisplayed() {
    return isDisplayed("private_key_option");
    }

    public boolean isMnemonicOptionDisplayed() {
    return isDisplayed("mnemonic_key_option");
    }

public boolean isJsonKeyOptionDisplayed() {
    return isDisplayed("json_key_option");
    }

    public boolean isBinanceSmartChainHeaderDisplayed() {
        return isDisplayed("binance_smart_chain_header");
    }

    public boolean isPrivateKeyOptionSelectedByDefault() {
        try {
            Thread.sleep(2000); // Wait for UI to stabilize
            WebElement privateKeyOption = driver.findElement(getBy("private_key_option"));
            String bgColor = privateKeyOption.getCssValue("background-color");
            String color = privateKeyOption.getCssValue("color");
            logger.info("Privatekey option background-color: {}", bgColor);
            logger.info("Privatekey option color: {}", color);
            // Accept either property if it matches expected blue
            return "rgba(147, 200, 242, 1)".equals(bgColor) || "#93c8f2".equals(color);
        } catch (Exception e) {
            logger.error("Error checking Privatekey option selected state: {}", e.getMessage());
            return false;
        }
    }

    public boolean isPasteButtonOnPhraseInputDisplayed() {
        return isDisplayed("paste_button");
    }

    public void clickImportWalletBackButton() {
        tap("imp_wallet_back_btn");
    }
}

