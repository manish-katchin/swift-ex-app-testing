package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateNewWalletStep {

    @When("I tap on \"CREATE A NEW WALLET\" button")
    public void i_tap_on_create_new_wallet_button() {
        // Tap button logic (use driver to find and click the button)
    }

    @Then("I should see the backup wallet screen")
    public void i_should_see_backup_wallet_screen() {
        // Verify backup wallet screen logic (check for expected UI elements)
    }

    @And("I should see \"If I lose my private key, my funds will be lost\" warning")
    public void i_should_see_private_key_lost_warning() {
        // Verify warning logic (check for warning text on screen)
    }

    @And("I should see \"If I share my private key, my funds can get stolen\" warning")
    public void i_should_see_private_key_stolen_warning() {
        // Verify warning logic (check for warning text on screen)
    }

    @And("I should see the \"Continue\" button")
    public void i_should_see_continue_button() {
        // Verify continue button logic (check for button presence)
    }
}
