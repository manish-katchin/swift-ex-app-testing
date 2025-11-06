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
    public void enterAmountInInputFieldBelowWETH(String amount) throws InterruptedException {
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
    public void enterAmountInInputFieldBelowAmount(String amount) throws InterruptedException {
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

    @Then("I should see the Scanner icon is displayed in the Recipient Address field")
    public void iShouldSeeTheScannerIconIsDisplayedInTheRecipientAddressField() {
        logger.info("Verifying scanner icon is displayed in Recipient Address field");
        Assert.assertTrue("Scanner icon is not displayed in Recipient Address field!",
                swapPage.isScannerIconDisplayed());
    }

    @Then("I click on the Scanner icon")
    public void iClickOnTheScannerIcon() throws InterruptedException {
        logger.info("Clicking on the Scanner icon in the Recipient Address field");
        swapPage.clickScannerIcon();
    }

    @Then("I verify Don't allow button")
    public void iVerifyDontAllowButton() {
        logger.info("Verifying 'Don't allow' button is displayed");
        Assert.assertTrue("'Don't allow' button should be visible", swapPage.isDontAllowButtonDisplayed());
    }

    @Then("I click Don't allow button")
    public void iClickDontAllowButton() {
        logger.info("Clicking 'Don't allow' button");
        swapPage.clickDontAllowButton();
    }

    @Then("I should see Scan QR Code header on the screen")
    public void iShouldSeeScanQRCodeHeaderOnTheScreen() {
        logger.info("Verifying 'Scan QR Code' header is displayed on the screen");
        Assert.assertTrue("'Scan QR Code' header should be visible", swapPage.isScanQRCodeHeaderDisplayed());
    }

    @And("I should see available balance")
    public void iShouldSeeAvailableBalance() {
        logger.info("Verifying available balance is displayed");
        Assert.assertTrue("Available balance should be visible", swapPage.isAvailableBalanceDisplayed());
    }

    @Then("I should see the Recipient Address input field")
    public void iShouldSeeRecipientAddressInputField() {
        logger.info("Verifying Recipient Address input field is displayed");
        Assert.assertTrue("Recipient Address input field should be visible",
                swapPage.isRecipientAddressInputDisplayed());
    }

    @Then("I should see the Amount input field")
    public void iShouldSeeAmountInputField() {
        logger.info("Verifying Amount input field is displayed");
        Assert.assertTrue("Amount input field should be visible", swapPage.isAmountInputDisplayed());
    }

    @And("I should enter {string} in the xlm Recipient Address input field")
    public void iShouldEnterInTheXlmRecipientAddressInputField(String address) {
        logger.info("Entering recipient address: {}", address);
        swapPage.enterxlmRecipientAddress(address);
    }

    @And("I should enter {string} in the Amount input field")
    public void iShouldEnterInTheAmountInputField(String amount) {
        logger.info("Entering amount: {}", amount);
        swapPage.enterxlmAmount(amount);
    }

    @Then("I click Claim 5 XLM Now! button")
    public void iClickClaim5XLMNowButton() throws InterruptedException {
        logger.info("Clicking 'Claim 5 XLM Now!' button");
        swapPage.clickClaim5XLMNowButton();
    }

    @And("I click \"Send\" button on xlm send page")
    public void iClickSendButtonOnXlmSendPage() {
        logger.info("Clicking 'Send' button on XLM send page");
        swapPage.clickXlmSendButton();
    }

    @And("I should see the transaction history on the screen")
    public void iShouldSeeTheTransactionHistoryOnTheScreen() {
        logger.info("Verifying transaction history is displayed on the screen");
        Assert.assertTrue("Transaction history should be visible", swapPage.isTransactionHistoryDisplayed());
    }

    @Then("I should see the Receive header on the screen")
    public void iShouldSeeTheReceiveHeaderOnTheScreen() {
        logger.info("Verifying 'Receive' header is displayed on the screen");
        Assert.assertTrue("'Receive' header should be visible", swapPage.isReceiveHeaderDisplayed());
    }

    @Then("I click Copy icon on screen")
    public void iClickCopyButtonOnReceiveScreen() throws InterruptedException {
        logger.info("Clicking 'Copy' button on receive screen");
        swapPage.clickCopyIconButton();
    }

        @Then("I click on wrong symbol")
        public void iClickWrongSymbolbesideReceiveScreen() throws InterruptedException {
            logger.info("Clicking 'Wrong symbol' button on receive screen");
            swapPage.clickWrongSymbolButton();
        }

       @Then("I tap on paste button in Recipient Address field")
       public void iTapOnPasteButtonInRecipientAddressField() throws InterruptedException {
           logger.info("Tapping 'Paste' button in Recipient Address field");
           swapPage.tapPasteButtonInRecipientAddressField();
       }
       @Then("The displayed address should match {string}")
public void verifyDisplayedAddress(String expectedAddress) {
    String actualAddress = swapPage.getDisplayedAddress();
    Assert.assertEquals("Displayed address does not match expected value!", expectedAddress, actualAddress);
}
}