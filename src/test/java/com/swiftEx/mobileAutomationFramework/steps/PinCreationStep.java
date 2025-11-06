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
                .as("'Please create a pin' text should be visible on pin page")
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

    @Then("I click {string} button on screen")
    public void i_click_button_on_screen(String buttonText) throws InterruptedException {
        logger.info("Clicking button: {}", buttonText);
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

        buttonElement.click();
        logger.info("Button clicked successfully");
    }
  @Then("I see {string} popup on screen")
    public void i_see_popup_on_screen(String popupText) throws InterruptedException {
        logger.info("Verifying popup: {}", popupText);
        Thread.sleep(3000); // Wait for UI to update
        String dynamicXPath = String.format("//*[@text='%s']", popupText);
        logger.info("Using XPath: {}", dynamicXPath);

        // Wait for element to be present (with timeout) 
        WebDriverWait wait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(60));

        // Wait until the button is visible
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));

        assertThat(buttonElement.isDisplayed())
                .as("Popup with text '%s' should be visible on screen", popupText)
                .isTrue();

        assertThat(buttonElement.getText())
                .as("Popup text should match expected value")
                .isEqualTo(popupText);

        logger.info("Popup verified successfully");
    }
   @When("I click on FingerPrint Sensor")
   public void i_click_on_fingerprint_sensor() {
       logger.info("Clicking on FingerPrint Sensor");
       pinPage.tapFingerPrintSensor();
   }
@Then("I see {string} text on screen")
    public void i_see_text_on_screen(String text) throws InterruptedException {
        logger.info("Verifying text: {}", text);
        String dynamicXPath = String.format("//*[@text='%s']", text);
        logger.info("Using XPath: {}", dynamicXPath);

        // Wait for element to be present (with timeout) 
        WebDriverWait wait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(10));

        // Wait until the button is visible
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));

        assertThat(buttonElement.isDisplayed())
                .as("text '%s' should be visible on screen", text)
                .isTrue();

        assertThat(buttonElement.getText())
                .as("text should match expected value")
                .isEqualTo(text);

        logger.info("text verified successfully");
    }

}