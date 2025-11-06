package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.swiftEx.mobileAutomationFramework.pages.WalletSelectionPage;

public class WalletSelectionStep extends BaseStep {
    private static final Logger logger = LoggerFactory.getLogger(WalletSelectionStep.class);
    private final WalletSelectionPage walletSelectionPage = page(WalletSelectionPage.class);
    private final com.swiftEx.mobileAutomationFramework.pages.WalletScreenPage walletScreenPage = page(com.swiftEx.mobileAutomationFramework.pages.WalletScreenPage.class);

    @Then("I should see wallet name {string} displayed")
    public void iShouldSeeWalletNameDisplayed(String walletName) {
        Assert.assertTrue("Wallet name '" + walletName + "' is not displayed!",
                walletSelectionPage.isWalletNameDisplayed(walletName));
        logger.info("Verified wallet name '{}' is displayed", walletName);
        }

        @When("I click on Choose Wallet option")
        public void iClickOnChooseWalletOption() {
            walletScreenPage.clickChooseWalletOption();
            logger.info("Clicked on Choose Wallet option");
        }
    
    @And("I select the wallet named {string}")
    public void iSelectTheWalletNamed(String walletName) {
        walletSelectionPage.selectWalletByName(walletName);
    }

    @Then("I should see wallet {string} has active wallet indication")
    public void iShouldSeeWalletAsActive(String walletName) {
        Assert.assertTrue("Wallet '" + walletName + "' is not active!", walletSelectionPage.isWalletActive(walletName));
    }
    @And("I see All available Wallets")
    public void iSeeAllAvailableElements() {
        Assert.assertTrue("All wallets are not displayed!", walletSelectionPage.isAllWalletsDisplayed());
        logger.info("All available wallet elements are displayed");
    }

        @And("I see the popup with message \"Wallet Selected '{word}'\"")
        public void iSeeThePopupWithMessageWalletSelected(String walletName) {
            Assert.assertTrue("Wallet Selected popup for '" + walletName + "' is not displayed!",
                    walletSelectionPage.isWalletSelectedPopupDisplayed(walletName));
            logger.info("Verified Wallet Selected popup for wallet: {}", walletName);
        }

            @Then("I verify {string} area of wallet {string} is clickable")
            public void iVerifyWalletAreaIsClickable(String areaType, String walletName) {
                Assert.assertTrue(String.format("%s area of wallet '%s' is not clickable!", areaType, walletName),
                        walletSelectionPage.isWalletAreaClickable(walletName, areaType));
                logger.info("Verified {} area of wallet '{}' is clickable", areaType, walletName);
            }

            @Then("I click \"Back Button\" on All Wallets screen")
            public void iClickBackButtonOnAllWalletsScreen() {
                walletSelectionPage.clickBackButtonOnAllWalletsScreen();
                logger.info("Clicked Back Button on All Wallets screen");
            }
            @Then("I click on My Wallet option")
            public void iClickOnMyWalletOption() {
                walletSelectionPage.clickMyWalletOption();
                logger.info("Clicked on My Wallet option");
            }

    @Then("I should see \"Wallet\" header on the screen")
    public void iShouldSeeWalletHeaderOnScreen() {
        Assert.assertTrue("Wallet header is not displayed!", walletSelectionPage.isWalletHeaderDisplayed());
        logger.info("Verified Wallet header is displayed");
    }
    @Then("I verify Active wallet card with name {string} is Present")
    public void iVerifyWalletCardIsActive(String walletName) {
        Assert.assertTrue("Wallet card '" + walletName + "' is not active!",
                walletSelectionPage.isWalletNameDisplayedOnMyWallet(walletName));
        logger.info("Verified wallet card '{}' is active", walletName);
    }

    @And("I verify warning message is displayed")
    public void iVerifyWarningMessageIsDisplayed() {
        Assert.assertTrue("Warning message is not displayed!", walletSelectionPage.isWarningMessageDisplayed());
        logger.info("Verified warning message is displayed");
    }
@Then("I verify Show Secret Phrase option is available")
public void iVerifyShowSecretPhraseOptionIsAvailable() {
    Assert.assertTrue("Show Secret Phrase option is not available!",
            walletSelectionPage.isShowSecretPhraseOptionAvailable());
    logger.info("Show Secret Phrase option is available");
}

@Then("I verify Show Secret Phrase option is clickable")
public void iVerifyShowSecretPhraseOptionIsClickable() {
    Assert.assertTrue("Show Secret Phrase option is not clickable!",
            walletSelectionPage.isShowSecretPhraseOptionClickable());
    logger.info("Show Secret Phrase option is clickable");
}
@Then("I click on Show Secret Phrase option")
public void iClickOnShowSecretPhraseOption() {
    walletSelectionPage.clickShowSecretPhraseOption();
    logger.info("Clicked on Show Secret Phrase option");
}

@And("I click on Copy button for mnemonic phrase")
public void iClickOnCopyButtonForMnemonicPhrase() {
    walletSelectionPage.clickCopyMnemonicButton();
}

@And("I click on Copy button for private key")
public void iClickOnCopyButtonForPrivateKey() {
    walletSelectionPage.clickCopyPrivateKeyButton();
}
@Then("I see \"Copied\" toast message is displayed")
public void iSeeCopiedToastMessageIsDisplayed() {
    Assert.assertTrue("Copied toast message is not displayed!", walletSelectionPage.isCopiedToastDisplayed());
    logger.info("Verified 'Copied' toast message is displayed");
}
}
