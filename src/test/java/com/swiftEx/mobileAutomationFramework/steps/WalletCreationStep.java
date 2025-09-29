package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.swiftEx.mobileAutomationFramework.pages.CreateNewWalletPage;
import com.swiftEx.mobileAutomationFramework.pages.BackupYourWalletPage;
import com.swiftEx.mobileAutomationFramework.pages.PrivateKeyPage;
import com.swiftEx.mobileAutomationFramework.pages.CheckMnemonicPage;
import com.swiftEx.mobileAutomationFramework.pages.HomePage;
import com.swiftEx.mobileAutomationFramework.pages.MarketScreenPage;
import com.swiftEx.mobileAutomationFramework.pages.PopupPage;
import com.swiftEx.mobileAutomationFramework.pages.WalletScreenPage;
import com.swiftEx.mobileAutomationFramework.pages.SettingsScreenPage;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletCreationStep extends BaseStep {
    private final WalletScreenPage walletScreenPage = page(WalletScreenPage.class);

    private static final Logger logger = LoggerFactory.getLogger(WalletCreationStep.class);

    private final CreateNewWalletPage createNewWalletPage = page(CreateNewWalletPage.class);
    private final BackupYourWalletPage backupYourWalletPage = page(BackupYourWalletPage.class);
    private final PrivateKeyPage privateKeyPage = page(PrivateKeyPage.class);
    private final CheckMnemonicPage checkMnemonicPage = page(CheckMnemonicPage.class);
    private final HomePage homePage = page(HomePage.class);
    private final PopupPage popupPage = page(PopupPage.class);
    private final MarketScreenPage marketPage = page(MarketScreenPage.class);
    private final SettingsScreenPage settingsScreenPage = page(SettingsScreenPage.class);

    @And("I click on the Settings tab in bottom navigation")
    public void iClickOnSettingsTab() {
        logger.info("Clicking on Settings tab in bottom navigation");
        homePage.clickSettingsTab();
    }

    @Then("I should see the Settings header")
    public void iShouldSeeSettingsHeader() {
        logger.info("Verifying Settings header is displayed");
        Assert.assertTrue("Settings header is not displayed!", homePage.isSettingsHeaderDisplayed());
    }

    @And("I should see the Choose Wallet option")
    public void iShouldSeeChooseWalletOption() {
        logger.info("Verifying Choose Wallet option is displayed");
        Assert.assertTrue("Choose Wallet option is not displayed!", homePage.isChooseWalletOptionDisplayed());
    }

    @And("I should see the Dark Mode option")
    public void iShouldSeeDarkModeOption() {
        logger.info("Verifying Dark Mode option is displayed");
        Assert.assertTrue("Dark Mode option is not displayed!", homePage.isDarkModeOptionDisplayed());
    }

    @And("I should see the Exchange option")
    public void iShouldSeeExchangeOption() {
        logger.info("Verifying Exchange option is displayed");
        Assert.assertTrue("Exchange option is not displayed!", homePage.isExchangeOptionDisplayed());
    }

    @And("I should see the Transactions option")
    public void iShouldSeeTransactionsOption() {
        logger.info("Verifying Transactions option is displayed");
        Assert.assertTrue("Transactions option is not displayed!", homePage.isTransactionsOptionDisplayed());
    }

    @And("I should see the Biometric Authentication option")
    public void iShouldSeeBiometricAuthenticationOption() {
        logger.info("Verifying Biometric Authentication option is displayed");
        Assert.assertTrue("Biometric Authentication option is not displayed!",
                homePage.isBiometricAuthenticationOptionDisplayed());
    }

    @And("I should see the Preference option")
    public void iShouldSeePreferenceOption() {
        logger.info("Verifying Preference option is displayed");
        Assert.assertTrue("Preference option is not displayed!", homePage.isPreferenceOptionDisplayed());
    }

    @And("I should see the Push Notification option")
    public void iShouldSeePushNotificationOption() {
        logger.info("Verifying Push Notification option is displayed");
        Assert.assertTrue("Push Notification option is not displayed!", homePage.isPushNotificationOptionDisplayed());
    }

    @And("I should see the Help Center option")
    public void iShouldSeeHelpCenterOption() {
        logger.info("Verifying Help Center option is displayed");
        Assert.assertTrue("Help Center option is not displayed!", homePage.isHelpCenterOptionDisplayed());
    }

    @And("I should see the Logout option")
    public void iShouldSeeLogoutOption() {
        logger.info("Verifying Logout option is displayed");
        Assert.assertTrue("Logout option is not displayed!", homePage.isLogoutOptionDisplayed());
    }

    @And("I click on the back button")
    public void iClickOnBackButton() {
        logger.info("Clicking on back button");
        homePage.clickBackButton();
    }

    @And("I click on the Exchange tab in bottom navigation")
    public void iClickOnExchangeTab() {
        logger.info("Clicking on Exchange tab in bottom navigation");
        homePage.clickExchangeTab();
    }

    @Then("I should see the Trade Wallet header")
    public void iShouldSeeTradeWalletHeader() {
        logger.info("Verifying Trade Wallet header is displayed");
        Assert.assertTrue("Trade Wallet header is not displayed!", homePage.isTradeWalletHeaderDisplayed());
    }

    @And("I should see the Home icon")
    public void iShouldSeeHomeIcon() {
        logger.info("Verifying Home icon is displayed");
        Assert.assertTrue("Home icon is not displayed!", homePage.isHomeIconDisplayed());
    }

    @And("I should see the Offers icon")
    public void iShouldSeeOffersIcon() {
        logger.info("Verifying Offers icon is displayed");
        Assert.assertTrue("Offers icon is not displayed!", homePage.isOffersIconDisplayed());
    }

    @And("I should see the Transactions icon")
    public void iShouldSeeTransactionsIcon() {
        logger.info("Verifying Transactions icon is displayed");
        Assert.assertTrue("Transactions icon is not displayed!", homePage.isTransactionsIconDisplayed());
    }

    @And("I should see the On-Off Ramp icon")
    public void iShouldSeeOnOffRampIcon() {
        logger.info("Verifying On/Off Ramp icon is displayed");
        Assert.assertTrue("On/Off Ramp icon is not displayed!", homePage.isOnOffRampIconDisplayed());
    }

    @And("I should see the Profile icon")
    public void iShouldSeeProfileIcon() {
        logger.info("Verifying Profile icon is displayed");
        Assert.assertTrue("Profile icon is not displayed!", homePage.isProfileIconDisplayed());
    }

    @Then("I should be on Create A new wallet Page")
    public void iShouldBeOnCreateANewWalletPage() {
        logger.info("Verifying Create New Wallet page");
        Assert.assertTrue(createNewWalletPage.isCreateWalletButtonVisible());
    }

    @When("I click Create a new wallet button")
    public void iClickCreateANewWalletButton() {
        logger.info("Clicking Create a new wallet button");
        createNewWalletPage.tapCreateWallet();
    }

    @When("I click Import Wallet button")
    public void clickImportWallet() {
        logger.info("Clicking Import wallet button");
        createNewWalletPage.tapImportWallet();
    }

    @When("I click use default button")
    public void i_click_use_default_button() {
        logger.info("Clicking Use default wallet button");
        createNewWalletPage.tapUseDefaultWallet();
    }

    @Then("I should be on the \"Backup your wallet now\" screen")
    public void iShouldBeOnBackupYourWalletNowScreen() {
        logger.info("Verifying Backup Your Wallet screen");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue("Backup your wallet screen not displayed",
                backupYourWalletPage.isBackupScreenDisplayed());
    }

    @When("I select the option If I lose my private keys, my funds will be lost")
    public void iSelectOptionPrivateKeysLost() {
        logger.info("Selecting private keys lost option");
        backupYourWalletPage.selectLosePrivateKeyOption();
    }

    @And("I select the option If I share my private key, my funds can be lost")
    public void iSelectOptionPrivateKeyShared() {
        logger.info("Selecting private key shared option");
        backupYourWalletPage.selectSharePrivateKeyOption();
    }

    @And("I click on the \"Continue\" button")
    public void iClickOnContinueButton() {
        logger.info("Clicking Continue button");
        backupYourWalletPage.tapContinue();
    }

    @Then("I should be on \"Private key\" page")
    public void iShouldBeOnPrivateKeyPage() {
        logger.info("Verifying Private Key page");
        Assert.assertTrue("Private Key page not displayed!",
                privateKeyPage.isOnPrivateKeyPage());
    }

    @When("I enter account name {string} in the Account Name field")
    public void iEnterAccountNameInField(String accountName) {
        logger.info("Entering account name: {}", accountName);
        privateKeyPage.enterAccountName(accountName);

        // Print the mnemonic map
        var mnemonicMap = privateKeyPage.getMnemonicDigitWordMap();
        logger.info("Mnemonic Map:");
        mnemonicMap.forEach((index, word) -> logger.info("Index {}: {}", index,
                word));
    }

    @And("I click on the Next button")
    public void iClickOnNextButton() {
        logger.info("Clicking Next button");
        privateKeyPage.tapNext();
    }

    @And("I verify and select the requested mnemonic words on Check Mnemonic page")
    public void iVerifyAndSelectRequestedMnemonicWords() {
        logger.info("Verifying and selecting mnemonic words");
        var mnemonicMap = privateKeyPage.getMnemonicDigitWordMap();
        checkMnemonicPage.verifyAndSelectMnemonicWords(mnemonicMap);
    }

    @And("I verify all mnemonic words are correctly selected")
    public void iVerifyAllMnemonicWordsAreCorrectlySelected() {
        logger.info("Verifying all mnemonic words are selected");

        // Get the page source for debugging
        logger.debug("Current page source: {}", getDriver().getPageSource());

        // Add a small wait to ensure all selections are processed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean allSelected = checkMnemonicPage.verifyAllWordsSelected();

        if (!allSelected) {
            logger.debug("Verification failed, retrying after a short wait...");
            try {
                Thread.sleep(2000);
                allSelected = checkMnemonicPage.verifyAllWordsSelected();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @When("I click on the \"Done\" button")
    public void iClickOnDoneButton() throws InterruptedException {
        logger.info("Clicking Done button");
        Thread.sleep(2000);
        checkMnemonicPage.tapDone();
    }

    @Then("I should see the biometric authentication popup")
    public void iShouldSeeBiometricAuthenticationPopup() {
        logger.info("Verifying biometric authentication popup");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        PopupPage popupPage = page(PopupPage.class);
        Assert.assertTrue("Biometric authentication popup is not displayed!",
                popupPage.isBiometricAuthPopupDisplayed());
    }

    @When("I handle the biometric authentication")
    public void iHandleBiometricAuthentication() throws InterruptedException {
        logger.info("Handling biometric authentication");
        popupPage.handleBiometricAuth();
        Thread.sleep(3000);
    }

    @Then("I verify I am on the Home page")
    public void iVerifyIAmOnHomePage() {
        logger.info("Verifying Home page");
        Assert.assertTrue("Not on Home page!", homePage.isOnHomePage());
    }

    // Specific Balance Section Validation Steps
    @And("I should see the balance amount is displayed")
    public void iShouldSeeBalanceAmountDisplayed() {
        logger.info("Verifying balance amount is displayed");
        Assert.assertTrue("Balance amount is not displayed!",
                homePage.isBalanceAmountDisplayed());
    }

    @And("I should see the balance currency is displayed")
    public void iShouldSeeBalanceCurrencyDisplayed() {
        logger.info("Verifying balance currency is displayed");
        Assert.assertTrue("Balance currency is not displayed!",
                homePage.isBalanceCurrencyDisplayed());
    }

    // Specific Action Button Validation Steps
    @And("I should see the Receive button is displayed")
    public void iShouldSeeReceiveButtonDisplayed() {
        logger.info("Verifying Receive button is displayed");
        Assert.assertTrue("Receive button is not displayed!",
                homePage.isReceiveButtonDisplayed());
    }

    @And("I should see the Send button is displayed")
    public void iShouldSeeSendButtonDisplayed() {
        logger.info("Verifying Send button is displayed");
        Assert.assertTrue("Send button is not displayed!",
                homePage.isSendButtonDisplayed());
    }

    @And("I should see the Swap button is displayed")
    public void iShouldSeeSwapButtonDisplayed() {
        logger.info("Verifying Swap button is displayed");
        Assert.assertTrue("Swap button is not displayed!",
                homePage.isSwapButtonDisplayed());
    }

    @And("I should see the Buy button is displayed")
    public void iShouldSeeBuyButtonDisplayed() {
        logger.info("Verifying Buy button is displayed");
        Assert.assertTrue("Buy button is not displayed!",
                homePage.isBuyButtonDisplayed());
    }

    // Specific Asset Tab Validation Steps
    @And("I should see the Assets tab is displayed")
    public void iShouldSeeAssetsTabDisplayed() {
        logger.info("Verifying Assets tab is displayed");
        Assert.assertTrue("Assets tab is not displayed!",
                homePage.isAssetsTabDisplayed());
    }

    @And("I should see the Add Assets tab is displayed")
    public void iShouldSeeAddAssetsTabDisplayed() {
        logger.info("Verifying Add Assets tab is displayed");
        Assert.assertTrue("Add Assets tab is not displayed!",
                homePage.isAddAssetsTabDisplayed());
    }

    // Specific Bottom Navigation Validation Steps
    @And("I should see the Home tab is displayed in bottom navigation")
    public void iShouldSeeHomeTabDisplayed() {
        logger.info("Verifying Home tab in bottom navigation is displayed");
        Assert.assertTrue("Home tab is not displayed in bottom navigation!",
                homePage.isHomeTabDisplayed());
    }

    @And("I should see the Wallet tab is displayed in bottom navigation")
    public void iShouldSeeWalletTabDisplayed() {
        logger.info("Verifying Wallet tab in bottom navigation is displayed");
        Assert.assertTrue("Wallet tab is not displayed in bottom navigation!",
                homePage.isWalletTabDisplayed());
    }

    @And("I should see the Market tab is displayed in bottom navigation")
    public void iShouldSeeMarketTabDisplayed() {
        logger.info("Verifying Market tab in bottom navigation is displayed");
        Assert.assertTrue("Market tab is not displayed in bottom navigation!",
                homePage.isMarketTabDisplayed());
    }

    @And("I should see the Exchange tab is displayed in bottom navigation")
    public void iShouldSeeExchangeTabDisplayed() {
        logger.info("Verifying Exchange tab in bottom navigation is displayed");
        Assert.assertTrue("Exchange tab is not displayed in bottom navigation!",
                homePage.isExchangeTabDisplayed());
    }

    @And("I should see the Settings tab is displayed in bottom navigation")
    public void iShouldSeeSettingsTabDisplayed() {
        logger.info("Verifying Settings tab in bottom navigation is displayed");
        Assert.assertTrue("Settings tab is not displayed in bottom navigation!",
                homePage.isSettingsTabDisplayed());
    }

    // Comprehensive Validation Steps
    @And("I validate the balance section is complete")
    public void iValidateBalanceSectionComplete() {
        logger.info("Validating balance section is complete");
        Assert.assertTrue("Balance section is not complete!",
                homePage.isBalanceSectionComplete());
    }

    @And("I validate all action buttons are functional")
    public void iValidateAllActionButtonsFunctional() {
        logger.info("Validating all action buttons are displayed and functional");
        Assert.assertTrue("Not all action buttons are displayed!",
                homePage.areAllActionButtonsDisplayed());
    }

    @And("I validate bottom navigation is complete")
    public void iValidateBottomNavigationComplete() {
        logger.info("Validating bottom navigation is complete");
        Assert.assertTrue("Bottom navigation is not complete!",
                homePage.isBottomNavigationComplete());
    }

    @Then("I see bitcoin option on market page")
    public void iSeeBitcoinOptionOnMarketPage() {
        logger.info("Verifying Bitcoin option is displayed on Market page");
        Assert.assertTrue("Bitcoin option is not displayed on Market page!",
                marketPage.isBitcoinAssetDisplayed());
    }

    @And("I see Create Wallet option")
    public void iSeeCreateWalletOption() {
        logger.info("Verifying Create Wallet option is displayed");
        Assert.assertTrue("Create Wallet option is not displayed!",
                walletScreenPage.isCreateWalletOptionDisplayed());
    }

    @And("I see Import Wallet option")
    public void iSeeImportWalletOption() {
        logger.info("Verifying Import Wallet option is displayed");
        Assert.assertTrue("Import Wallet option is not displayed!",
                walletScreenPage.isImportWalletOptionDisplayed());
    }

    @And("I see Choose Wallet option")
    public void iSeeChooseWalletOption() {
        logger.info("Verifying Choose Wallet option is displayed");
        Assert.assertTrue("Choose Wallet option is not displayed!",
                walletScreenPage.isChooseWalletOptionDisplayed());
    }

    @And("I click on Market tab")
    public void iClickOnMarketTab() {
        logger.info("Clicking on Market tab");
        homePage.tapMarketTab();
    }

    @And("I click on Wallet tab")
    public void iClickOnWalletTab() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("Clicking on Wallet tab");
        homePage.tapWalletTab();
    }

    @Then("I see My Wallet option")
    public void iSeeMyWalletOption() {
        logger.info("Verifying My Wallet option is displayed");
        Assert.assertTrue("My Wallet option is not displayed!",
                walletScreenPage.isMyWalletOptionDisplayed());
    }

    @Then("I see market page header")
    public void iSeeMarketPageHeader() {
        logger.info("Verifying Market page header is displayed");
        Assert.assertTrue("Market page header is not displayed!",
                marketPage.isMarketScreenDisplayed());
    }

    @Then("I see error {string}")
    public void iSeeError(String errorMessage) {
        logger.info("Verifying error message is displayed");
        Assert.assertTrue("Error message is not displayed!",
                checkMnemonicPage.verifyErrorMessage(errorMessage));
    }

    @When("I click on the Logout option in Settings")
    public void iClickOnLogoutOptionInSettings() {
        logger.info("Clicking on Logout option in Settings");
        settingsScreenPage.clickLogoutOption();
    }

    @Then("I click on Create Wallet option")
    public void iClickOnCreateWalletOption() {
        logger.info("Clicking on Create Wallet option");
        walletScreenPage.clickCreateWalletOption();
    }

    @Then("I see \"Continue\" button is disabled on the screen")
    public void iSeeContinueButtonIsDisabledOnScreen() {
        logger.info("Verifying Continue button is disabled on the screen");
        Assert.assertTrue("Continue button should be disabled!", backupYourWalletPage.isContinueButtonDisabled());
    }

    @Then("I should be on \"Backup Mnemonic Phrase\" page")
    public void iShouldBeOnBackupMnemonicPhrasePage() {
        logger.info("Verifying Backup Mnemonic Phrase page");
        Assert.assertTrue("Backup Mnemonic Phrase page not displayed",
                backupYourWalletPage.isBackupMnemonicPhrasePageDisplayed());
    }

    @When("I click the Receive button")
    public void iClickTheReceiveButton() {
        logger.info("Clicking the Receive button");
        homePage.clickReceiveButton();
    }

    @Then("I should be on the Receive screen")
    public void iShouldBeOnTheReceiveScreen() {
        logger.info("Verifying Receive screen is displayed");
        Assert.assertTrue("Not on the Receive screen!", homePage.isOnReceiveScreen());
    }

    @Then("I should be on homePage")
    public void iShouldBeOnTheHomePage() {
        logger.info("Verifying Home page is displayed");
        Assert.assertTrue("Not on the Home page!", homePage.isOnHomePage());
    }

    @Then("I click on Home Tab")
    public void iClickOnHomeTab() {
        logger.info("Clicking on Home Tab");
        homePage.tapHomeTab();
    }
@Then("I click Back button of trade Wallet Page")
    public void iClickBackButtonOfTradeWalletPage() {
        logger.info("Clicking Back button of Trade Wallet Page");
        homePage.clickTradeWalletBackButton();
    }
}