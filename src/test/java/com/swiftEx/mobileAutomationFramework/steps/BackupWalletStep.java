package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.swiftEx.mobileAutomationFramework.pages.BackupYourWalletPage;

public class BackupWalletStep extends BaseStep {
    private static final Logger logger = LoggerFactory.getLogger(BackupWalletStep.class);
    private final BackupYourWalletPage backupYourWalletPage = page(BackupYourWalletPage.class);

    @Then("I see \"Continue\" button is enabled on the screen")
    public void iSeeContinueButtonIsEnabledOnTheScreen() {
        logger.info("Verifying Continue button is enabled on the screen");
        Assert.assertTrue("Continue button should be enabled!", backupYourWalletPage.isContinueButtonEnabled());
    }
      @Then("I click on the Back button on Backup your wallet screen")
      public void iClickOnBackButtonOnBackupYourWalletScreen() {
      logger.info("Clicking Back button on Backup Your Wallet screen");
       backupYourWalletPage.clickBackButtonOnBackupScreen();
      }
    @Then("I should see instruction first {string}")
    public void iShouldSeeInstructionFirst(String expectedText) {
        logger.info("Verifying first mnemonic instruction: {}", expectedText);
        Assert.assertTrue("First mnemonic instruction not displayed or text mismatch!",
                backupYourWalletPage.isMnemonicInstruction1Displayed(expectedText));
    }

    @Then("I should see instruction Second {string}")
    public void iShouldSeeInstructionSecond(String expectedText) {
        logger.info("Verifying second mnemonic instruction: {}", expectedText);
        Assert.assertTrue("Second mnemonic instruction not displayed or text mismatch!",
                backupYourWalletPage.isMnemonicInstruction2Displayed(expectedText));
    }
}
