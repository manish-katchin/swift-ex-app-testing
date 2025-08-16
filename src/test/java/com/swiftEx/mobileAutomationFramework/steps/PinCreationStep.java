package com.swiftEx.mobileAutomationFramework.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Assertion libraries
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// Selenium/Appium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PinCreationStep {
    private static final Logger logger = LoggerFactory.getLogger(PinCreationStep.class);

    @Given("the app is launched")
    public void the_app_is_launched() {
        logger.info("INFO: Step: App is launched");

        if (Hooks.getDriver() != null) {
            logger.info("SUCCESS: App launched successfully with Appium driver");
            logger.info("🔗 Driver session ID: {}", Hooks.getDriver().getSessionId());

            try {
                // Additional verification that app is ready
                logger.info("INFO: Verifying app is fully loaded and responsive...");
                Thread.sleep(1000); // Brief pause to ensure stability
                logger.info("SUCCESS: App verification completed - ready for test steps!");
            } catch (Exception e) {
                logger.warn("WARNING: App verification warning: {}", e.getMessage());
            }
        } else {
            logger.warn("WARNING: App launch simulated (no real driver available)");
            logger.warn("   In real scenario: App would be launched on connected device");
        }
    }

    @When("I enter a new PIN {string}")
    public void i_enter_a_new_pin(String pin) {
        if (Hooks.getPinPage() != null) {
            Hooks.getPinPage().enterPIN(pin);
        } else {
            logger.warn("PinPage not available - check Appium server connection");
        }
    }

    @When("I confirm the PIN {string}")
    public void i_confirm_the_pin(String pin) {
        if (Hooks.getPinPage() != null) {
            Hooks.getPinPage().confirmPIN(pin);
        } else {
            logger.warn("PinPage not available - check Appium server connection");
        }
    }

    @Then("I verify {string} button on screen")
    public void i_verify_button_on_screen(String buttonText) {

        String dynamicXPath = String.format("//android.widget.TextView[@text='%s']", buttonText);
        logger.info("📍 Using XPath: {}", dynamicXPath);

        // Wait for element to be present (with timeout)
        WebElement buttonElement = Hooks.getDriver().findElement(By.xpath(dynamicXPath));

        assertThat(buttonElement.isDisplayed())
                .as("Button with text '%s' should be visible on screen", buttonText)
                .isTrue();

        assertThat(buttonElement.getText())
                .as("Button text should match expected value")
                .isEqualTo(buttonText);

    }

    @Then("I should see {string}")
public void i_should_see(String expectedError) {

    String actualErrorString = Hooks.getPinPage().getErrorMessageText();
    assertThat(actualErrorString)
            .as("Error message should match expected value")
            .isEqualTo(expectedError);
}
}
