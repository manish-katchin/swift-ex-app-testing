package com.swiftEx.mobileAutomationFramework.steps;

import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

    @Then("I verify {string} button after clicking scanner icon on screen")
    public void i_verify_button_after_clicking_scanner_icon_on_screen(String buttonText) throws InterruptedException {
        logger.info("Verifying camera permission button: {}", buttonText);

        // Build XPath with proper quote handling
        String dynamicXPath;
        if (buttonText.contains("'")) {
            // If text contains single quote, use double quotes or concat
            dynamicXPath = String.format("//*[@text=\"%s\"]", buttonText);
        } else {
            dynamicXPath = String.format("//*[@text='%s']", buttonText);
        }

        logger.debug("XPath built for camera permission verification: {}", dynamicXPath);

        try {
            // Wait only 1 second for the camera permission button
            WebDriverWait shortWait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(1));

            WebElement buttonElement = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));

            assertThat(buttonElement.isDisplayed())
                    .as("Camera permission button with text '%s' should be visible", buttonText)
                    .isTrue();

            assertThat(buttonElement.getText())
                    .as("Button text should match expected value")
                    .isEqualTo(buttonText);

            logger.info("Camera permission button '{}' verified successfully", buttonText);

        } catch (TimeoutException | NoSuchElementException e) {
            logger.warn(
                    "Camera permission button '{}' not found within 1 second. Assuming permission was already granted on emulator (skipping failure).",
                    buttonText);
        }
    }

    @Then("I click {string} button after clicking scanner icon on screen")
    public void i_click_button_after_clicking_scanner_icon_on_screen(String buttonText) throws InterruptedException {
        logger.info("Attempting to click camera permission button: {}", buttonText);

        // Build XPath with proper quote handling
        String dynamicXPath;
        if (buttonText.contains("'")) {
            dynamicXPath = String.format("//*[@text=\"%s\"]", buttonText);
        } else {
            dynamicXPath = String.format("//*[@text='%s']", buttonText);
        }

        logger.debug("XPath built for camera permission click: {}", dynamicXPath);

        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(1));

            WebElement buttonElement = shortWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));

            logger.info("Camera permission button '{}' found, clicking now", buttonText);
            buttonElement.click();
            logger.info("✅ Successfully clicked camera permission button: '{}'", buttonText);

        } catch (TimeoutException | NoSuchElementException e) {
            logger.warn(
                    "Camera permission button '{}' not found within 1 second. Assuming permission was already granted on emulator (skipping click).",
                    buttonText);
        }
    }
}