package com.swiftEx.mobileAutomationFramework.steps;

import com.swiftEx.mobileAutomationFramework.pages.SwapPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
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
    public void pressCurrencySwitchArrow() throws InterruptedException {
        // driver.findElement(By.xpath("(//*[@text='To']/parent::android.view.ViewGroup/android.view.ViewGroup)[3]"))
        // .click();
        swapPage.tapCurrencySwitchArrow();
        logger.info("Pressed the currency switch arrow");
    }

    // new

    @When("I click on the \"Send\" button")
    public void clickOnSendButton() {
        swapPage.clickSendButton();
        logger.info("Clicked on Send button");
    }

    @Then("I should see the \"Choose Wallet\" header on the screen")
    public void shouldSeeChooseWalletHeaderOnScreen() {
        Assert.assertTrue("Choose Wallet header should be displayed", swapPage.isChooseWalletHeaderDisplayed());
        logger.info("Choose Wallet header is displayed");
    }

    @When("I click on Ethereum Wallet on choose wallet Screen")
    public void clickOnEthereumWalletOnChooseWalletScreen() throws InterruptedException {
        swapPage.clickEthereumWalletOnChooseWalletScreen();
        logger.info("Clicked on Ethereum Wallet on choose wallet Screen");
    }

    @Then("I should see the \"Send\" header on the screen")
    public void shouldSeeSendHeaderOnScreen() {
        Assert.assertTrue("Send header should be displayed", swapPage.isSendHeaderDisplayed());
        logger.info("Send header is displayed");
    }

    @When("I enter {string} in the input field below \"Amount\"")
    public void enterAmountInInputFieldBelowAmount(String amount) {
        swapPage.enterAmount(amount);
        logger.info("Entered amount {} in input field below Amount", amount);
    }

    @And("I enter {string} in the input field with label \"Recipient Address\"")
    public void enterRecipientAddressInInputFieldWithLabel(String recipientAddress) {
        swapPage.enterRecipientAddress(recipientAddress);
        logger.info("Entered recipient address {} in input field with label Recipient Address", recipientAddress);
    }

    @Then("I should see the \"Confirm Transaction\" header")
    public void shouldSeeConfirmTransactionHeaderOnScreen() {
        Assert.assertTrue("Confirm Transaction header should be displayed",
                swapPage.isConfirmTransactionHeaderDisplayed());
        logger.info("Confirm Transaction header is displayed");
    }

    @Then("I click on first Transaction On Transactions Page")
    public void clickOnFirstTransactionOnTransactionsPage() throws InterruptedException {
        swapPage.clickOnFirstTransaction();
        logger.info("Clicked on first Transaction On Transactions Page");
    }
   @Then("I should see \"Insufficient Balance\" message on the screen")
public void shouldSeeInsufficientBalanceMessageOnScreen() {
    Assert.assertTrue("Insufficient Balance message should be displayed",
            swapPage.isInsufficientBalanceMessageDisplayed());
    logger.info("Insufficient Balance message is displayed");
}
    @And("I should see {string} amount on the screen")
        public void shouldSeeAmountOnScreen(String expectedAmount) {
            String actualAmount = swapPage.getActualAmount();
            Assert.assertEquals("Amount on screen does not match", expectedAmount, actualAmount);
            logger.info("Verified amount on screen: {}", actualAmount);
        }

    @And("I should see {string} recipient address on the screen")
    public void shouldSeeRecipientAddressOnScreen(String expectedAddress) {
        String actualAddress = swapPage.getActualRecipientAddress();
        Assert.assertEquals("Recipient address on screen does not match", expectedAddress, actualAddress);
        logger.info("Verified recipient address on screen: {}", actualAddress);
    }

    @Then("I should see the \"Transactions\" header on the screen")
    public void shouldSeeTransactionsHeaderOnScreen() throws InterruptedException {
        Assert.assertTrue("Transactions header is not displayed!", swapPage.isTransactionsHeaderDisplayed());
    }
}
