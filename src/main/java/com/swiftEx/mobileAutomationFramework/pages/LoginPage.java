package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(AppiumDriver driver) {
        super(driver, "LoginPage.yaml");
        logger.info("✅ LoginPage ready for platform: {}", getPlatform().toUpperCase());
    }

   
    // Profile icon
    public void clickProfileIcon() {
        tap("ProfileTab");
        logger.info("Tapped Profile icon");
    }

    // Profile header
    public boolean isProfileHeaderDisplayed() {
        boolean displayed = isDisplayed("ProfileHeader");
        logger.info("Profile header displayed: {}", displayed);
        return displayed;
    }

    // Guest user header
    public boolean isGuestUserHeaderDisplayed() {
        boolean displayed = isDisplayed("GuestUserLabel");
        logger.info("Guest user header displayed: {}", displayed);
        return displayed;
    }

    // Login option
    public boolean isLoginOptionDisplayed() {
        boolean displayed = isDisplayed("Login_Option");
        logger.info("Login option displayed: {}", displayed);
        return displayed;
    }

    public void clickLoginOption() {
        tap("Login_Option");
        logger.info("Tapped Login option");
    }

    // Hi, Welcome back! header
    public boolean isWelcomeBackHeaderDisplayed() {
        boolean displayed = isDisplayed("WelcomeBackHeader");
        logger.info("Welcome back header displayed: {}", displayed);
        return displayed;
    }

    // Email input field
    public boolean isEmailInputFieldDisplayed() {
        boolean displayed = isDisplayed("EmailInputField");
        logger.info("Email input field displayed: {}", displayed);
        return displayed;
    }

    public void enterEmail(String email) {
        sendKeys("EmailInputField", email);
        logger.info("Entered email: {}", email);
    }

    // Password input field
    public boolean isPasswordInputFieldDisplayed() {
        boolean displayed = isDisplayed("PasswordInputField");
        logger.info("Password input field displayed: {}", displayed);
        return displayed;
    }

    public void enterPassword(String password) {
        sendKeys("PasswordInputField", password);
        logger.info("Entered password: {}", password);
    }

    // Forgot Password? option
    public boolean isForgotPasswordOptionDisplayed() {
        boolean displayed = isDisplayed("ForgotPasswordOption");
        logger.info("Forgot Password? option displayed: {}", displayed);
        return displayed;
    }

    // Login button
    public boolean isLoginButtonDisplayed() {
        boolean displayed = isDisplayed("LogInButton");
        logger.info("Login button displayed: {}", displayed);
        return displayed;
    }

    public void clickLoginButton() {
        tap("LogInButton");
        logger.info("Tapped Login button");
    }

        // Get displayed email value (for verification)
        public String getDisplayedEmailValue() {
            return getText("Email_Value");
        }
         public boolean isMyWalletLabelDisplayed() {
        boolean displayed = isDisplayed("MyWalletLabel");
        logger.info("MyWalletLabel displayed: {}", displayed);
        return displayed;
    }

    public boolean isGuestModeMessageDisplayed() {
        boolean displayed = isDisplayed("GuestModeMessage");
        logger.info("GuestModeMessage displayed: {}", displayed);
        return displayed;
    }

    public boolean isActivatedSubscriptionLabelDisplayed() {
        boolean displayed = isDisplayed("ActivatedSubscriptionLabel");
        logger.info("ActivatedSubscriptionLabel displayed: {}", displayed);
        return displayed;
    }
     public boolean isInvalidSubscriptionMessageDisplayed() {
        boolean displayed = isDisplayed("InvalidSubscriptionMessage");
        logger.info("InvalidSubscriptionMessage displayed: {}", displayed);
        return displayed;
    }

    public boolean isLoginErrorMessageDisplayed() {
        boolean displayed = isDisplayed("BothFieldsRequiredError");
        logger.info("Login error message displayed: {}", displayed);
        return displayed;
    }

}
