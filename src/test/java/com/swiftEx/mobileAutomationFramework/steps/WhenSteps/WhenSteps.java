// package com.swiftEx.mobileAutomationFramework.steps.WhenSteps;

// import io.cucumber.java.en.When;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import com.swiftEx.mobileAutomationFramework.pages.PinCreationPage;
// import com.swiftEx.mobileAutomationFramework.pages.CreateNewWalletPage;
// import com.swiftEx.mobileAutomationFramework.pages.PrivateKeyPage;
// import com.swiftEx.mobileAutomationFramework.pages.BackupYourWalletPage;
// import com.swiftEx.mobileAutomationFramework.pages.CheckMnemonicPage;
// import com.swiftEx.mobileAutomationFramework.pages.WalletScreenPage;
// import com.swiftEx.mobileAutomationFramework.steps.BaseStep;

// public class WhenSteps extends BaseStep {
// private static final Logger logger =
// LoggerFactory.getLogger(WhenSteps.class);
// private PinCreationPage pinPage = page(PinCreationPage.class);
// private CreateNewWalletPage createNewWalletPage =
// page(CreateNewWalletPage.class);
// private PrivateKeyPage privateKeyPage = page(PrivateKeyPage.class);
// private BackupYourWalletPage backupYourWalletPage =
// page(BackupYourWalletPage.class);
// private CheckMnemonicPage checkMnemonicPage = page(CheckMnemonicPage.class);
// private WalletScreenPage walletScreenPage = page(WalletScreenPage.class);

// @When("I enter a new PIN {string}")
// public void i_enter_a_new_pin(String pin) {
// logger.info("Entering new PIN");
// pinPage.enterPIN(pin);
// }

// @When("I confirm the PIN {string}")
// public void i_confirm_the_pin(String pin) {
// logger.info("Confirming PIN");
// pinPage.enterPIN(pin);
// }

// @When("I click Create a new wallet button")
// public void iClickCreateANewWalletButton() {
// logger.info("Clicking Create a new wallet button");
// createNewWalletPage.tapCreateWallet();
// }

// @When("I click Import wallet button")
// public void clickImportWallet() {
// logger.info("Clicking Import wallet button");
// createNewWalletPage.tapImportWallet();
// }

// @When("I click use default button")
// public void i_click_use_default_button() {
// logger.info("Clicking Use default wallet button");
// createNewWalletPage.tapUseDefaultWallet();
// }

// @When("I select the option \"If I lose my private keys, my funds will be
// lost\"")
// public void iSelectOptionPrivateKeysLost() {
// logger.info("Selecting private keys lost option");
// backupYourWalletPage.selectLosePrivateKeyOption();
// }

// @When("I click on the Next button")
// public void iClickOnNextButton() {
// logger.info("Clicking Next button");
// privateKeyPage.tapNext();
// }

// @When("I enter account name {string} in the Account Name field")
// public void iEnterAccountNameInField(String accountName) {
// logger.info("Entering account name: {}", accountName);
// privateKeyPage.enterAccountName(accountName);
// }

// @When("I click on the \"Done\" button")
// public void iClickOnDoneButton() {
// logger.info("Clicking Done button");
// checkMnemonicPage.tapDone();
// }

// @When("I handle the biometric authentication")
// public void iHandleBiometricAuthentication() {
// logger.info("Handling biometric authentication");
// // Implement biometric handling logic here
// }

// @When("I click on Wallet tab")
// public void iClickOnWalletTab() {
// logger.info("Clicking on Wallet tab");
// walletScreenPage.clickWalletTab();
// }
// }
