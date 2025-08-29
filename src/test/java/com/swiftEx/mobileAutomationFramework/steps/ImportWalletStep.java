package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.swiftEx.mobileAutomationFramework.pages.ImportWalletPage;
import com.swiftEx.mobileAutomationFramework.pages.HomePage;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportWalletStep extends BaseStep {
    private final ImportWalletPage importWalletPage = page(ImportWalletPage.class);
    private final HomePage homePage = page(HomePage.class);
    private static final Logger logger = LoggerFactory.getLogger(ImportWalletStep.class);

    @When("I click on \"Multi-Chain Wallet\"")
    public void iClickOnMultiChainWallet() {
        logger.info("Clicking on \"Multi-Chain Wallet\"");
        importWalletPage.clickMultiChainWallet();
    }

    @Then("I should see \"Multi-Chain Wallet\" header")
    public void iShouldSeeMultiChainWalletHeader() {
        logger.info("Verifying Multi-Chain Wallet header is displayed");
        Assert.assertTrue("Multi-Chain Wallet header is not displayed!", importWalletPage.isMultiChainWalletHeaderDisplayed());
    }

    @When("I enter name {string} in the input field with label Name")
    public void iEnterNameInInputField(String name) {
        logger.info("Entering name: {}", name);
        importWalletPage.enterWalletName(name);
    }

    @And("I enter phrase {string} in the input field with label Phrase")
    public void iEnterPhraseInInputField(String phrase) {
        logger.info("Entering phrase: {}", phrase);
        importWalletPage.enterMnemonicPhrase(phrase);
    }
// I should see error message \"Please enter a valid private key\""
    @Then("I should see error message \"Please enter a valid private key\"")
    public void isPrivateKeyErrorMessageDisplayed() {
        logger.info("Verifying private key error message is displayed");
        Assert.assertTrue("Private key error message is not displayed!", importWalletPage.isPrivateKeyErrorMessageDisplayed());
    }


    @Then("I should see error message \"Please enter a valid mnemonic\"")
    public void iShouldSeeErrorMessage() {
        logger.info("Verifying error message is displayed");
        Assert.assertTrue("Error message is not displayed!", importWalletPage.isErrorMessageDisplayed());
    }


    @And("I click on \"Import\" button")
    public void iClickImportWalletButton() {
        logger.info("Clicking Import Wallet button");
        importWalletPage.clickImportWalletButton();
    }

    // @Then("I should be on the {string} screen")
    // public void iShouldBeOnScreen(String screenName) {
    //     logger.info("Verifying screen: {}", screenName);
    //     Assert.assertTrue("Not on the '" + screenName + "' screen!", importWalletPage.isOnImportWalletScreen());
    // }

    @Then("I should see Import Wallet header")
    public void iShouldSeeImportWalletHeader() {
        logger.info("Verifying Import Wallet header is displayed");
        Assert.assertTrue("Import Wallet header is not displayed!", importWalletPage.isImportWalletHeaderDisplayed());
    }


    @And("I should see Binance Smart Chain option")
    public void iShouldSeeBinanceSmartChainOption() {
        logger.info("Verifying Binance Smart Chain option is displayed");
        Assert.assertTrue("Binance Smart Chain option is not displayed!", importWalletPage.isBinanceSmartChainOptionDisplayed());
    }

    @And("I should see Ethereum option")
    public void iShouldSeeEthereumOption() {
        logger.info("Verifying Ethereum option is displayed");
        Assert.assertTrue("Ethereum option is not displayed!", importWalletPage.isEthereumOptionDisplayed());
    }

    @When("I should see {string} option")
    public void iShouldSeeOption(String option) {
        logger.info("Verifying option: {}", option);
        Assert.assertTrue(option + " option is not displayed!", importWalletPage.isElementDisplayed(option.toLowerCase().replace(" ", "_") + "_option"));
    }


    @When("I enter name {string} in the input field with label {string}")
    public void iEnterNameWithLabel(String name, String label) {
        logger.info("Entering name '{}' in field labeled '{}'", name, label);
        importWalletPage.enterWalletName(name); // Assumes only one name field
    }

    @When("I enter phrase {string} in the input field with label {string}")
    public void iEnterPhraseWithLabel(String phrase, String label) {
        logger.info("Entering phrase '{}' in field labeled '{}'", phrase, label);
        importWalletPage.enterMnemonicPhrase(phrase); // Assumes only one phrase field
    }

    @And("I should see \"Import Wallet\" header")
    public void iShouldSeeImportWalletHeaderText() {
        logger.info("Verifying Import Wallet header is displayed");
        Assert.assertTrue("Import Wallet header is not displayed!", importWalletPage.isImportWalletHeaderDisplayed());
    }

    @And("I should see \"Multi-Chain Wallet\" section")
    public void iShouldSeeMultiChainWalletSectionText() {
        logger.info("Verifying Multi-Chain Wallet section is displayed");
        Assert.assertTrue("Multi-Chain Wallet section is not displayed!", importWalletPage.isMultiChainWalletSectionDisplayed());
    }

    // clickonEthereumWallet
     @When("I click on \"Ethereum\"")
    public void clickonEthereumWallet() {
        logger.info("Clicking Ethereum Wallet option");
        importWalletPage.clickonEthereumWallet();
    }

    @Then("I should see \"Ethereum Wallet\" header")
    public void iShouldSeeEthereumWalletHeader() {
        logger.info("Verifying Ethereum Wallet header is displayed");
        Assert.assertTrue("Ethereum Wallet header is not displayed!", importWalletPage.isEthereumWalletHeaderDisplayed());
    }

    @And("I enter JSON password {string} in the input field \"JSON password\"")
    public void iEnterJsonPasswordInInputField(String password) {
        logger.info("Entering JSON password: {}", password);
        importWalletPage.enterJsonPassword(password);
    }
       @When("I click on \"Binance Smart Chain\"")
    public void clickOnBinanceSmartChain() {
        logger.info("Clicking Binance Smart Chain option");
        importWalletPage.clickBinanceSmartChain();
    }

    @When("I enter name {string} in the input field")
    public void iEnterNameInInputFieldSimple(String name) {
        logger.info("Entering name: {}", name);
        importWalletPage.enterName(name);
    }
    }

