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
}
