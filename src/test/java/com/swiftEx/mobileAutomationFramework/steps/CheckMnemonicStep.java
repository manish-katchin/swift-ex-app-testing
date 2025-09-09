package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.swiftEx.mobileAutomationFramework.pages.CheckMnemonicPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckMnemonicStep extends BaseStep {

    private static final Logger logger = LoggerFactory.getLogger(CheckMnemonicStep.class);
    private final CheckMnemonicPage checkMnemonicPage = page(CheckMnemonicPage.class);

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

}
