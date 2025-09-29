package com.swiftEx.mobileAutomationFramework.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        Assert.assertTrue("ActivatedSubscriptionLabel is not displayed!",
                loginPage.isActivatedSubscriptionLabelDisplayed());
    }

    @And("I should see \"InvalidSubscriptionMessage\" on Profile page")
    public void iShouldSeeInvalidSubscriptionMessage() {
        Assert.assertTrue("InvalidSubscriptionMessage is not displayed!",
                loginPage.isInvalidSubscriptionMessageDisplayed());
    }

    @Then("I should see login \"Error\" message")
    public void iShouldSeeLoginErrorMessage() {
        Assert.assertTrue("Login error message is not displayed!", loginPage.isLoginErrorMessageDisplayed());
    }

    @When("I click on register now option")
    public void iClickOnRegisterNowOption() {
        loginPage.clickRegisterNowOption();
    }

    @Then("I should see \"Create your exchange account\" header on Register page")
    public void iShouldSeeCreateAccountHeader() {
        Assert.assertTrue("Create account header is not displayed!", loginPage.isCreateAccountHeaderDisplayed());
    }

    @And("I should see the First name input field")
    public void iShouldSeeFirstNameInputField() {
        Assert.assertTrue("First name input field is not displayed!", loginPage.isFirstNameInputDisplayed());
    }

    @And("I should see the Last name input field")
    public void iShouldSeeLastNameInputField() {
        Assert.assertTrue("Last name input field is not displayed!", loginPage.isLastNameInputDisplayed());
    }

    @And("I should see the Email input field on registration page")
    public void iShouldSeeEmailInputFieldOnRegister() {
        Assert.assertTrue("Email input field is not displayed!", loginPage.isEmailInputDisplayed());
    }

    @And("I should see the Password input field on registration page")
    public void iShouldSeePasswordInputFieldOnRegister() {
        Assert.assertTrue("Password input field is not displayed!", loginPage.isPasswordInputDisplayed());
    }

    @And("I should see the Re-Password input field on registration page")
    public void iShouldSeeRePasswordInputField() {
        Assert.assertTrue("Re-Password input field is not displayed!", loginPage.isRePasswordInputDisplayed());
    }

    @When("I enter {string} into the First name input field")
    public void iEnterFirstName(String firstName) {
        loginPage.enterFirstName(firstName);
    }

    @And("I enter {string} into the Last name input field")
    public void iEnterLastName(String lastName) {
        loginPage.enterLastName(lastName);
    }

    @And("I enter a random email into the Email input field on registration page")
    public void iEnterRandomEmailIntoEmailInputFieldOnRegistrationPage() {
        String randomEmail = "user" + System.currentTimeMillis() + "@example.com";
        loginPage.enterEmailOnRegister(randomEmail);
        logger.info("Entered random email: {}", randomEmail);
    }

    @And("I enter {string} into the Password input field on registration page")
    public void iEnterPasswordOnRegister(String password) {
        loginPage.enterPasswordOnRegister(password);
    }

    @And("I enter {string} into the Re-Password input field on registration page")
    public void iEnterRePassword(String rePassword) {
        loginPage.enterRePassword(rePassword);
    }

    @Then("I click on the \"Create my Account\" button")
    public void iClickCreateMyAccountButton() {
        loginPage.clickCreateAccountButton();
    }

    @Then("I should see error \"Email must be a valid Email\" on registration page")
    public void iShouldSeeErrorEmailMustBeAValidEmailOnRegistrationPage() {
        String expectedError = "Email must be a valid Email";
        Assert.assertTrue("Expected error message not displayed: " + expectedError,
                loginPage.isEmailValidationErrorDisplayed());
    }

    @And("I enter the verification code {string} on verification page")
    public void iEnterTheVerificationCodeOnVerificationPage(String code) {
        loginPage.enterVerificationCode(code);
    }

    @Then("I click on Verify Button on Verify Page")
    public void iClickOnVerifyButtonOnVerifyPage() {
        loginPage.clickVerifyButtonOnVerifyPage();
    }
    
@When("I click on Forgot Password? option")
public void iClickOnForgotPasswordOption() {
    loginPage.clickForgotPasswordOption();
}

@Then("I should see {string} header on Forgot Password page")
public void iShouldSeeHeaderOnForgotPasswordPage(String expectedHeader) throws InterruptedException {
    Assert.assertTrue("Recover to your account header not visible!", loginPage.isRecoverToYourAccountHeaderVisible());
}
    @When("I enter a new Password {string} into the New Password input field on Verification Page")
    public void iEnterNewPasswordIntoNewPasswordInputFieldOnVerificationPage(String password) {
        loginPage.enterNewPasswordOnVerificationPage(password);
    }

    @And("I enter the verification code {string} on verification step")
    public void iEnterVerificationCodeOnVerificationPage(String code) {
        loginPage.enterVerificationCodeOnVerificationPage(code);
    }

    @And("I enter {string} into the Email input field on Forgot Password page")
    public void iEnterEmailIntoEmailInputFieldOnForgotPasswordPage(String email) {
    loginPage.enterEmailonForgotPasscodePage(email);
}
}