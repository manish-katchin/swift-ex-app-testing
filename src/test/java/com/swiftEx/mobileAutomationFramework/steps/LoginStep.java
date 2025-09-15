package com.swiftEx.mobileAutomationFramework.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;

import com.swiftEx.mobileAutomationFramework.pages.HomePage;
import com.swiftEx.mobileAutomationFramework.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends BaseStep {
    private static final Logger logger = LoggerFactory.getLogger(LoginStep.class);
    private final LoginPage loginPage = page(LoginPage.class);


    @When("I click on the \"Profile\" icon on Trade Wallet page")
    public void iClickOnProfileIcon() {
        loginPage.clickProfileIcon();
    }

    @Then("I should see the Profile header")
    public void iShouldSeeProfileHeader() {
        Assert.assertTrue("Profile header is not displayed!", loginPage.isProfileHeaderDisplayed());
    }

    @And("I should see Guest user header")
    public void iShouldSeeGuestUserHeader() {
        Assert.assertTrue("Guest user header is not displayed!", loginPage.isGuestUserHeaderDisplayed());
    }

    @And("I should see the Login option")
    public void iShouldSeeLoginOption() {
        Assert.assertTrue("Login option is not displayed!", loginPage.isLoginOptionDisplayed());
    }

    @When("I click on the Login option")
    public void iClickOnLoginOption() {
        loginPage.clickLoginOption();
    }

    @Then("I should see \"Hi, Welcome back!\" header")
    public void iShouldSeeWelcomeBackHeader() {
        Assert.assertTrue("Welcome back header is not displayed!", loginPage.isWelcomeBackHeaderDisplayed());
    }

    @And("I should see the Email input field")
    public void iShouldSeeEmailInputField() {
        Assert.assertTrue("Email input field is not displayed!", loginPage.isEmailInputFieldDisplayed());
    }

    @And("I should see the Password input field")
    public void iShouldSeePasswordInputField() {
        Assert.assertTrue("Password input field is not displayed!", loginPage.isPasswordInputFieldDisplayed());
    }

    @And("I should see the Forgot Password? option")
    public void iShouldSeeForgotPasswordOption() {
        Assert.assertTrue("Forgot Password? option is not displayed!", loginPage.isForgotPasswordOptionDisplayed());
    }

    @And("I should see the Login button")
    public void iShouldSeeLoginButton() {
        Assert.assertTrue("Login button is not displayed!", loginPage.isLoginButtonDisplayed());
    }

  @When("I enter {string} into the Email input field")
    public void iEnterEmailIntoEmailInputField(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter {string} into the Password input field")
    public void iEnterPasswordIntoPasswordInputField(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click the Login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("I should see the user entered email {string}")
    public void iShouldSeeUserEnteredEmail(String expectedEmail) {
        String actualEmail = loginPage.getDisplayedEmailValue();
        logger.info("Verifying displayed email value. Expected: {}, Actual: {}", expectedEmail, actualEmail);
        Assert.assertEquals("Displayed email value does not match entered email!", expectedEmail, actualEmail);
    }

    @And("I should see \"MyWalletLabel\" on Profile page")
    public void iShouldSeeMyWalletLabel() {
        Assert.assertTrue("MyWalletLabel is not displayed!", loginPage.isMyWalletLabelDisplayed());
    }

    @And("I should see \"GuestModeMessage\" on Profile page")
    public void iShouldSeeGuestModeMessage() {
        Assert.assertTrue("GuestModeMessage is not displayed!", loginPage.isGuestModeMessageDisplayed());
    }

    @And("I should see \"ActivatedSubscriptionLabel\" on Profile page")
    public void iShouldSeeActivatedSubscriptionLabel() {
        Assert.assertTrue("ActivatedSubscriptionLabel is not displayed!", loginPage.isActivatedSubscriptionLabelDisplayed());
    }

    @And("I should see \"InvalidSubscriptionMessage\" on Profile page")
    public void iShouldSeeInvalidSubscriptionMessage() {
        Assert.assertTrue("InvalidSubscriptionMessage is not displayed!", loginPage.isInvalidSubscriptionMessageDisplayed());
    }

    @Then("I should see login \"Error\" message")
    public void iShouldSeeLoginErrorMessage() {
        Assert.assertTrue("Login error message is not displayed!", loginPage.isLoginErrorMessageDisplayed());
    }
}