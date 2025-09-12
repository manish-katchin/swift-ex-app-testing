package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.swiftEx.mobileAutomationFramework.pages.CheckMnemonicPage;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.pages.PrivateKeyPage;

public class CheckMnemonicStep extends BaseStep {
    private static final Logger logger = LoggerFactory.getLogger(CheckMnemonicStep.class);
    private final CheckMnemonicPage checkMnemonicPage = page(CheckMnemonicPage.class);
    private final PrivateKeyPage privateKeyPage = page(PrivateKeyPage.class);

    @Then("I should be on \"Verify Secret Phrase\" screen")
    public void iShouldBeOnVerifySecretPhraseScreen() {
        logger.info("Verifying user is on 'Verify Secret Phrase' screen");
        Assert.assertTrue("Not on 'Verify Secret Phrase' screen!", checkMnemonicPage.isVerifySecretPhraseLabelVisible());
    }

    @Then("I should see the \"Done\" button is enabled")
    public void iShouldSeeDoneButtonIsEnabled() {
        logger.info("Verifying 'Done' button is enabled");
        Assert.assertTrue("Done button is not enabled!", checkMnemonicPage.isDoneButtonEnabled());
    }

    @And("I verify mnemonic phrases are visible on the screen")
    public void iVerifyMnemonicPhrasesAreVisibleOnTheScreen() {
        logger.info("Verifying mnemonic phrases are visible on the screen");
        Assert.assertTrue("Mnemonic phrases are not visible!", checkMnemonicPage.areMnemonicPhrasesVisible());
    }

      @Then("I verify jumbled mnemonic phrases appear for verification")
      public void iVerifyJumbledMnemonicPhrasesAppearForVerification() {
          logger.info("Verifying 12 jumbled mnemonic phrases appear for verification");
          Assert.assertTrue("12 jumbled mnemonic phrases are not visible!",
                  checkMnemonicPage.areJumbledMnemonicPhrasesVisible());
      }
 @Then("I click on the \"Import\" button on verify secret phrase screen")
    public void iClickOnImportButtonOnVerifySecretPhraseScreen() {
        logger.info("Clicking Import button on Verify Secret Phrase screen");
        checkMnemonicPage.clickImportButtonOnVerifySecretPhraseScreen();
    }

@Then("I should see the \"Done\" button is disabled")
        public void iShouldSeeDoneButtonIsDisabled() {
            logger.info("Verifying 'Done' button is disabled");
            Assert.assertTrue("Expected Done button to be disabled", checkMnemonicPage.isDoneButtonDisabled());
        }
    @Then("App is closed")
    public void appIsClosed() throws InterruptedException {
        logger.info("Closing the app via step definition");
        closeApp();
    }

     @Then("I launch the app with noReset true")
    public void iLaunchAppWithNoResetTrue() {
        logger.info("Launching the app with noReset=true via step definition");
        launchAppWithNoResetTrue();
    }

    @Then("I launch the app with noReset false")
    public void iLaunchAppWithNoResetFalse() {
        logger.info("Launching the app with noReset=false via step definition");
        launchAppWithNoResetFalse();
    }
    @Then("I verify only 20 characters are accepted in the Account Name field")
    public void iVerifyOnly20CharactersAcceptedInAccountNameField() {
        String actualText = privateKeyPage.getAccountNameFieldText();
        logger.info("Account Name field value: {}", actualText);
        Assert.assertTrue("Account Name field should accept only 20 characters, but was: " + actualText.length(),
                actualText.length() <= 20);
    }

    @Then("I verify no characters are accepted in the Account Name field")
    public void iVerifyNoCharactersAcceptedInAccountNameField() {
        String placeholder = "Enter your account name"; // adjust if your placeholder is different
        int maxWaitMs = 5000; // max wait time in ms
        int intervalMs = 200; // polling interval
        long start = System.currentTimeMillis();
        String actualText;
        boolean isEmptyOrPlaceholder;
        do {
            actualText = privateKeyPage.getAccountNameFieldText();
            isEmptyOrPlaceholder = actualText == null || actualText.trim().isEmpty() || actualText.equals(placeholder);
            if (isEmptyOrPlaceholder) break;
            try { Thread.sleep(intervalMs); } catch (InterruptedException ignored) {}
        } while (System.currentTimeMillis() - start < maxWaitMs);
        logger.info("Account Name field value after wait: '{}'", actualText);
        Assert.assertTrue("Account Name field should be empty or show only placeholder, but was: '" + actualText + "'", isEmptyOrPlaceholder);
    }
}
