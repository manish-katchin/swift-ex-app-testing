package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    WebElement profileTab = wait.until(ExpectedConditions.elementToBeClickable(getBy("ProfileTab")));
    profileTab.click();
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
    try {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
        WebElement guestUserLabel = wait.until(
            ExpectedConditions.visibilityOfElementLocated(getBy("GuestUserLabel"))
        );
        boolean displayed = guestUserLabel.isDisplayed();
        logger.info("Guest user header displayed: {}", displayed);
        return displayed;
    } catch (Exception e) {
        logger.error("Error waiting for Guest user header: {}", e.getMessage());
        return false;
    }
}

    // Login option
    public boolean isLoginOptionDisplayed() {
        boolean displayed = isDisplayed("Login_Option");
        logger.info("Login option displayed: {}", displayed);
        return displayed;
    }

    public void clickLoginOption() {
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    WebElement loginOption = wait.until(
        ExpectedConditions.elementToBeClickable(getBy("Login_Option"))
    );
    loginOption.click();
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
    try {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(getBy("PasswordInputField")));
        boolean displayed = passwordField.isDisplayed();
        logger.info("Password input field displayed: {}", displayed);
        return displayed;
    } catch (Exception e) {
        logger.error("Password input field not visible: {}", e.getMessage());
        return false;
    }
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
    // Get displayed email value (for verification) with dynamic wait
    public String getDisplayedEmailValue() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(getBy("Email_Value")));
            return getText("Email_Value");
        } catch (Exception e) {
            logger.error("Email value not visible: {}", e.getMessage());
            return null;
        }
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
 public void clickRegisterNowOption() {
        tap("Register_Now_Option");
        logger.info("Tapped Register Now option");
    }

    public boolean isCreateAccountHeaderDisplayed() {
        boolean displayed = isDisplayed("CreateAccountHeader");
        logger.info("Create account header displayed: {}", displayed);
        return displayed;
    }

    public boolean isFirstNameInputDisplayed() {
        boolean displayed = isDisplayed("FirstNameInput");
        logger.info("First name input displayed: {}", displayed);
        return displayed;
    }

    public boolean isLastNameInputDisplayed() {
        boolean displayed = isDisplayed("LastNameInput");
        logger.info("Last name input displayed: {}", displayed);
        return displayed;
    }

    public boolean isEmailInputDisplayed() {
        boolean displayed = isDisplayed("EmailInput");
        logger.info("Email input displayed: {}", displayed);
        return displayed;
    }

    public boolean isPasswordInputDisplayed() {
        boolean displayed = isDisplayed("PasswordInput");
        logger.info("Password input displayed: {}", displayed);
        return displayed;
    }

    public boolean isRePasswordInputDisplayed() {
        boolean displayed = isDisplayed("RePasswordInput");
        logger.info("Re-Password input displayed: {}", displayed);
        return displayed;
    }

    public void enterFirstName(String firstName) {
        sendKeys("FirstNameInput", firstName);
        logger.info("Entered first name: {}", firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys("LastNameInput", lastName);
        logger.info("Entered last name: {}", lastName);
    }

    public void enterEmailOnRegister(String email) {
        sendKeys("EmailInput", email);
        logger.info("Entered email: {}", email);
    }

    public void enterPasswordOnRegister(String password) {
        sendKeys("PasswordInput", password);
        logger.info("Entered password: {}", password);
    }

    public void enterRePassword(String rePassword) {
        sendKeys("RePasswordInput", rePassword);
        logger.info("Entered re-password: {}", rePassword);
    }

    public void clickCreateAccountButton() {
        tap("CreateAccountButton");
        logger.info("Tapped Create my Account button");
    }
        // Checks if the email validation error is displayed
        public boolean isEmailValidationErrorDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
                WebElement errorElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(getBy("EmailValidationError")));
                boolean displayed = errorElement.isDisplayed();
                logger.info("Email validation error displayed: {}", displayed);
                return displayed;
            } catch (Exception e) {
                logger.error("Email validation error not found: {}", e.getMessage());
                return false;
            }
        }
    public void enterVerificationCode(String code) {
    sendKeys("VerificationCodeInput", code);
    logger.info("Entered verification code: {}", code);
    }

   public void clickVerifyButtonOnVerifyPage() {
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    WebElement verifyButton = wait.until(ExpectedConditions.elementToBeClickable(getBy("Verify_button")));
    verifyButton.click();
    logger.info("Clicked on Verify Button on Verify Page");
}
public void enterNewPasswordOnVerificationPage(String password) {
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(getBy("Verification_Password_Field")));
    emailField.clear();
    emailField.sendKeys(password);
    logger.info("Entered email in Forgot Password field: {}", password);
}

public void enterVerificationCodeOnVerificationPage(String code) {
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    WebElement codeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='verificationCodeInput']")));
    codeField.clear();
    codeField.sendKeys(code);
    logger.info("Entered verification code on verification page: {}", code);
}
public void clickForgotPasswordOption() {
    WebElement forgotPasswordOption = driver.findElement(getBy("ForgotPasswordOption"));
    forgotPasswordOption.click();
    logger.info("Clicked on Forgot Password? option");
}

public boolean isRecoverToYourAccountHeaderVisible() throws InterruptedException {
    Thread.sleep(5000); // Wait for 5 seconds to ensure the header is loaded
    WebElement header = driver.findElement(getBy("RecoverToYourAccountHeader"));
    boolean displayed = header.isDisplayed();
    logger.info("Recover to your account header displayed: {}", displayed);
    return displayed;
}

public void enterEmailonForgotPasscodePage(String email) {
    WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(getBy("ForgotPasscodeEmailInputField")));
    emailField.clear();
    emailField.sendKeys(email);
    logger.info("Entered email in Forgot Password field: {}", email);
}
}

