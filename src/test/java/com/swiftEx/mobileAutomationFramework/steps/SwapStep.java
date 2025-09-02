package com.swiftEx.mobileAutomationFramework.steps;

import com.swiftEx.mobileAutomationFramework.pages.SwapPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class SwapStep extends BaseStep {

    private static final Logger logger = LoggerFactory.getLogger(SwapStep.class);
    private final SwapPage swapPage = page(SwapPage.class);

    @When("I click on the \"Swap\" button")
    public void clickOnSwapButton() {
        swapPage.clickSwapButton();
        logger.info("Clicked on Swap button");
    }

    @Then("I should see the \"Swap\" header on the screen")
    public void shouldSeeSwapHeaderOnScreen() {
        Assert.assertTrue("Swap header should be displayed", swapPage.isSwapHeaderDisplayed());
        logger.info("Swap header is displayed");
    }

    @And("I enter {string} in the input field below \"WETH\"")
    public void enterAmountInInputFieldBelowWETH(String amount) {
        swapPage.enterSwapAmount(amount);
        logger.info("Entered amount {} in input field below WETH", amount);
    }

        @Then("I should see \"Swap Success\" message on the screen")
        public void shouldSeeSwapSuccessMessageOnScreen() {
            Assert.assertTrue("Swap Success message should be displayed", swapPage.isSwapSuccessMessageDisplayed());
            logger.info("Swap Success message is displayed");
        }

        @Then("I click on the \"Swap\" button on Swap page")
        public void clickOnSwapButtonOnSwapPage() throws InterruptedException {
            swapPage.clickSwapButtonOnSwapPage();
            logger.info("Clicked on Swap button on Swap page");
        }
        @Then("I press the currency switch arrow")
    public void pressCurrencySwitchArrow() {
        swapPage.tapCurrencySwitchArrow();
        logger.info("Pressed the currency switch arrow");
    }
}
