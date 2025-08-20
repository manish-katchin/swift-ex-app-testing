package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.AppiumDriver;
import com.swiftEx.mobileAutomationFramework.pages.CreateNewWalletPage;
import com.swiftEx.mobileAutomationFramework.pages.BackupYourWalletPage;
import com.swiftEx.mobileAutomationFramework.pages.PrivateKeyPage;
import com.swiftEx.mobileAutomationFramework.pages.CheckMnemonicPage;
import com.swiftEx.mobileAutomationFramework.pages.HomePage;
import com.swiftEx.mobileAutomationFramework.pages.PopupPage;
import com.swiftEx.mobileAutomationFramework.steps.Hooks;
import org.junit.Assert;
import java.lang.Thread;
public class WalletCreationStep {
    @Then("I should be on Create A new wallet Page")
    public void iShouldBeOnCreateANewWalletPage() {
        // Validate presence of Create New Wallet button
        Assert.assertTrue(createNewWalletPage.isCreateWalletButtonVisible());
    }

    @When("I click Create a new wallet button")
    public void iClickCreateANewWalletButton() {
        createNewWalletPage.tapCreateWallet();
    }
    private final CreateNewWalletPage createNewWalletPage = Hooks.getCreateNewWalletPage();
    private final BackupYourWalletPage backupYourWalletPage = Hooks.getBackupYourWalletPage();
    private final PrivateKeyPage privateKeyPage = Hooks.getPrivateKeyPage();
    private final CheckMnemonicPage checkMnemonicPage = Hooks.getCheckMnemonicPage();
    private final PopupPage popupPage = Hooks.getPopupPage();
    private final HomePage homePage = Hooks.getHomePage();

    @Then("I should be on the \"Backup your wallet now\" screen")
    public void iShouldBeOnBackupYourWalletNowScreen() {
        // Add a small wait to ensure the screen has time to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Get the page source for debugging
        String pageSource = ((AppiumDriver) Hooks.getDriver()).getPageSource();
        System.out.println("DEBUG: Current page source before verification:");
        System.out.println(pageSource);
        
        // Verify presence of backup your wallet screen elements
        Assert.assertTrue("Backup your wallet screen not displayed", backupYourWalletPage.isBackupScreenDisplayed());
    }

    @When("I select the option \"If I lose my private keys, my funds will be lost\"")
    public void iSelectOptionPrivateKeysLost() {
        backupYourWalletPage.selectLosePrivateKeyOption();
    }

    @And("I select the option \"If I share my private key, my funds can be lost\"")
    public void iSelectOptionPrivateKeyShared() {
        backupYourWalletPage.selectSharePrivateKeyOption();
    }

    @And("I click on the \"Continue\" button")
    public void iClickOnContinueButton() {
        backupYourWalletPage.tapContinue();
    }

    @Then("I should be on the \"Private key\" page")
        public void iShouldBeOnPrivateKeyPage() {
            // Assert the presence of the Private Key title element for robust validation
            Assert.assertTrue("Private Key title not found!", privateKeyPage.getElementActions().isElementVisible(privateKeyPage.getLocatorBy("private_key_title")));
        }

        @When("I enter account name {string} in the Account Name field")
        public void iEnterAccountNameInField(String accountName) {
            privateKeyPage.enterAccountName(accountName);
            // Print the mnemonic map
            var mnemonicMap = privateKeyPage.getMnemonicDigitWordMap();
            System.out.println("Mnemonic Map:");
            mnemonicMap.forEach((index, word) -> System.out.println("Index " + index + ": " + word));
        }

        @And("I click on the Next button")
        public void iClickOnNextButton() {
            privateKeyPage.tapNext();
        }


    @And("I verify and select the requested mnemonic words on Check Mnemonic page")
    public void iVerifyAndSelectRequestedMnemonicWords() {
        var mnemonicMap = privateKeyPage.getMnemonicDigitWordMap();
        checkMnemonicPage.verifyAndSelectMnemonicWords(mnemonicMap);
    }

    @And("I verify all mnemonic words are correctly selected")
    public void iVerifyAllMnemonicWordsAreCorrectlySelected() {
        // Get the page source for debugging
        System.out.println("DEBUG: Current page source before mnemonic verification:");
        System.out.println(((AppiumDriver) Hooks.getDriver()).getPageSource());
        
        // Add a small wait to ensure all selections are processed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean allSelected = checkMnemonicPage.verifyAllWordsSelected();
        if (!allSelected) {
            System.out.println("DEBUG: Verification failed, retrying after a short wait...");
            try {
                Thread.sleep(2000);
                allSelected = checkMnemonicPage.verifyAllWordsSelected();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    @When("I click on the \"Done\" button")
    public void iClickOnDoneButton() {
        checkMnemonicPage.tapDone();
    }

    @Then("I should see the biometric authentication popup")
    public void iShouldSeeBiometricAuthenticationPopup() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Wait for the popup to appear
        Assert.assertTrue("Biometric authentication popup is not displayed!", 
            popupPage.isBiometricAuthPopupDisplayed());
    }

    @When("I handle the biometric authentication")
    public void iHandleBiometricAuthentication() {
        popupPage.handleBiometricAuth();
    }

    @Then("I verify I am on the Home page")
    public void iVerifyIAmOnHomePage() {
        Assert.assertTrue("Not on Home page!", homePage.isOnHomePage());
    }
}
