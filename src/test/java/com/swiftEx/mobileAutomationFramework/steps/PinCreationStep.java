package com.swiftEx.mobileAutomationFramework.steps;

import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.pages.PinCreationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PinCreationStep extends BaseStep {
    private static final Logger logger = LoggerFactory.getLogger(PinCreationStep.class);
    private PinCreationPage pinPage;

    @Given("the app is launched")
    public void the_app_is_launched() {
        logger.info("Step: App is launched");
        pinPage = page(PinCreationPage.class);
    }

    @Given("I am on pin page")
    public void iAmOnPinPage() {
        logger.info("Verifying user is on pin page");
        pinPage = page(PinCreationPage.class);
        assertThat(pinPage.isEnterYourPinTextVisible())
                .as("'Please enter your pin' text should be visible on pin page")
                .isTrue();
    }

    @When("I enter a new PIN {string}")
    public void i_enter_a_new_pin(String pin) throws InterruptedException {
        logger.info("Entering new PIN");
        pinPage.enterPIN(pin);
    }

    @When("I confirm the PIN {string}")
    public void i_confirm_the_pin(String pin) throws InterruptedException {
        logger.info("Confirming PIN");
        pinPage.enterPIN(pin);
    }

    @Then("I verify {string} button on screen")
    public void i_verify_button_on_screen(String buttonText) throws InterruptedException {
        logger.info("Verifying button: {}", buttonText);
        Thread.sleep(3000); // Wait for UI to update
        String dynamicXPath = String.format("//*[@text='%s']", buttonText);
        logger.info("Using XPath: {}", dynamicXPath);

        // Wait for element to be present (with timeout) 
      WebDriverWait wait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(60));

        // Wait until the button is visible
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));

        assertThat(buttonElement.isDisplayed())
                .as("Button with text '%s' should be visible on screen", buttonText)
                .isTrue();

        assertThat(buttonElement.getText())
                .as("Button text should match expected value")
                .isEqualTo(buttonText);

        logger.info("Button verified successfully");
    }

    @Then("I should see {string}")
    public void i_should_see(String expectedError) {
        logger.info("Verifying error message: {}", expectedError);

        String actualErrorString = pinPage.getErrorMessage();

        assertThat(actualErrorString)
                .as("Error message should match expected value")
                .isEqualTo(expectedError);

        logger.info("Error message verified successfully");
    }
}