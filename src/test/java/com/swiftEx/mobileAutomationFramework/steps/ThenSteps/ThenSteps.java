// package com.swiftEx.mobileAutomationFramework.steps.ThenSteps;

// import io.cucumber.java.en.Then;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import com.swiftEx.mobileAutomationFramework.pages.PinCreationPage;
// import com.swiftEx.mobileAutomationFramework.pages.HomePage;
// import com.swiftEx.mobileAutomationFramework.pages.WalletScreenPage;
// import com.swiftEx.mobileAutomationFramework.steps.BaseStep;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// import static org.assertj.core.api.Assertions.assertThat;
// import org.junit.Assert;

// public class ThenSteps extends BaseStep {
// private static final Logger logger =
// LoggerFactory.getLogger(ThenSteps.class);
// private PinCreationPage pinPage = page(PinCreationPage.class);
// private HomePage homePage = page(HomePage.class);
// private WalletScreenPage walletScreenPage = page(WalletScreenPage.class);

// @Then("I verify {string} button on screen")
// public void i_verify_button_on_screen(String buttonText) {
// logger.info("Verifying button: {}", buttonText);
// String dynamicXPath = String.format("//*[@text='%s']", buttonText);
// logger.info("Using XPath: {}", dynamicXPath);
// WebElement buttonElement = driver.findElement(By.xpath(dynamicXPath));
// assertThat(buttonElement.isDisplayed())
// .as("Button with text '%s' should be visible on screen", buttonText)
// .isTrue();
// assertThat(buttonElement.getText())
// .as("Button text should match expected value")
// .isEqualTo(buttonText);
// logger.info("Button verified successfully");
// }

// @Then("I should see {string}")
// public void i_should_see(String expectedError) {
// logger.info("Verifying error message: {}", expectedError);
// String actualErrorString = pinPage.getErrorMessage();
// assertThat(actualErrorString)
// .as("Error message should match expected value")
// .isEqualTo(expectedError);
// logger.info("Error message verified successfully");
// }

// @Then("I should be on Create A new wallet Page")
// public void iShouldBeOnCreateANewWalletPage() {
// logger.info("Verifying Create New Wallet page");
// // Implement actual check
// }

// @Then("I should be on the \"Backup your wallet now\" screen")
// public void iShouldBeOnBackupYourWalletNowScreen() {
// logger.info("Verifying Backup Your Wallet screen");
// // Implement actual check
// }

// @Then("I should be on the \"Private key\" page")
// public void iShouldBeOnPrivateKeyPage() {
// logger.info("Verifying Private Key page");
// // Implement actual check
// }

// @Then("I verify I am on the Home page")
// public void iVerifyIAmOnHomePage() {
// logger.info("Verifying Home page");
// Assert.assertTrue("Not on Home page!", homePage.isOnHomePage());
// }

// @Then("I see My Wallet option")
// public void iSeeMyWalletOption() {
// logger.info("Verifying My Wallet option is displayed");
// Assert.assertTrue("My Wallet option is not displayed!",
// walletScreenPage.isMyWalletOptionDisplayed());
// }

// }
