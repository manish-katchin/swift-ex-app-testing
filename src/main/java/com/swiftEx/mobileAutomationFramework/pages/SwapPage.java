package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;
//import io.appium.java_client.MobileElement;

public class SwapPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SwapPage.class);

    public SwapPage(AppiumDriver driver) {
        super(driver, "swap.yaml");
        logger.info("✅ SwapPage ready for platform: {}", getPlatform().toUpperCase());
    }

    // Clicks the Swap button (by accessibility id or locator key)
    public void clickSwapButton() {
        tap("swap_button");
        logger.info("Clicked Swap button");
    }

    // Verifies the Swap header is displayed
    public boolean isSwapHeaderDisplayed() {
        return isDisplayed(com.swiftEx.mobileAutomationFramework.utils.LocatorUtils.getUIAutomatorTextLocatorBy("Swap"),
                20);
    }

    // Enters amount in the input field below WETH
    public void enterSwapAmount(String amount) throws InterruptedException {
        // sendKeys("swap_amount_input", amount);
        WebElement amountField = driver.findElement(By.xpath("//android.widget.EditText[contains(@text, '0.0')]"));
        amountField.click();
        Thread.sleep(2000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.PERIOD));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        logger.info("Entered swap amount: {}", amount);
    }

    // Verifies the Swap Success message is displayed
    public boolean isSwapSuccessMessageDisplayed() {
        long endTime = System.currentTimeMillis() + 80000; // 80 seconds from now
        while (System.currentTimeMillis() < endTime) {
            try {
                if (isDisplayed("SwapSuccessMessage")) {
                    return true;
                }
                Thread.sleep(1500); // Wait 1.5 seconds before retry
            } catch (Exception e) {
                // Optionally log or handle exception
            }
        }
        return false;
    }

        // Verifies the Transactions header is displayed
        public boolean isTransactionsHeaderDisplayed() throws InterruptedException {
            Thread.sleep(30000);
            return isDisplayed("Transactions_header");
        }

    // Clicks the Swap button specifically on the Swap page
    public void clickSwapButtonOnSwapPage() throws InterruptedException {
        logger.info("Waiting 10 seconds before clicking Swap button on Swap page");
        Thread.sleep(10000);
        tapOnCoordinates(driver, 333, 1245);
        logger.info("Clicked Swap button on Swap page via coordinates");
    }

    // Taps the currency switch arrow symbol
    public void tapCurrencySwitchArrow() throws InterruptedException {
        // Using accessibility id (content-desc) for the arrow symbol
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//*[@text='To']/parent::android.view.ViewGroup/android.view.ViewGroup)[3]"))
                .click();
        // tap("currency_switch_arrow");
        logger.info("Tapped currency switch arrow symbol");
    }

    // new
    // Clicks the Send button
    public void clickSendButton() {
        tap("send_button");
        logger.info("Clicked on the Send button");
    }

    // Verifies the Choose Wallet header is displayed
    public boolean isChooseWalletHeaderDisplayed() {
        return isDisplayed(By.xpath("//android.widget.TextView[@text='Choose Wallet']"), 15);
    }

    // Clicks on Ethereum Wallet on choose wallet Screen
    public void clickEthereumWalletOnChooseWalletScreen() throws InterruptedException {
        Thread.sleep(5000);
        tap("ethereum_wallet_card");
        logger.info("Clicked on Ethereum Wallet on choose wallet Screen");
    }

    // Verifies the Send header is displayed
    public boolean isSendHeaderDisplayed() {
        return isDisplayed(By.xpath("//android.widget.TextView[@text='Send']"), 15);
    }

    // Enters amount in the input field below Amount
    public void enterAmount(String amount) throws InterruptedException {
        driver.findElement(By.xpath("//android.widget.EditText[@hint='Amount']")).click();
        Thread.sleep(2000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.PERIOD));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        Thread.sleep(1000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));

        logger.info("Entered amount: {}", amount);
    }

    // Enters recipient address in the input field with label Recipient Address
    public void enterRecipientAddress(String recipientAddress) {
        WebElement addressField = driver.findElement(By.xpath("//android.widget.EditText[@text=\"Recipient Address\"]"));
        addressField.sendKeys(recipientAddress);
        logger.info("Entered recipient address: {}", recipientAddress);
        // Press Enter key (Android only)
        try {
            io.appium.java_client.android.AndroidDriver androidDriver = (io.appium.java_client.android.AndroidDriver) driver;
            androidDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(io.appium.java_client.android.nativekey.AndroidKey.ENTER));
            logger.info("Pressed Enter key after entering recipient address");
        } catch (ClassCastException e) {
            logger.warn("Driver is not AndroidDriver, skipping Enter key press");
        }
    }

    // Verifies the Confirm Transaction header is displayed
    public boolean isConfirmTransactionHeaderDisplayed() {
        return isDisplayed(By.xpath("//*[@text='Confirm Transaction']"), 15);
    }

    // Clicks on the second ETH element on the page
    public void clickOnFirstTransaction() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement secondEth = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//*[@text='ETH'])[2]")));
        secondEth.click();
        logger.info("Clicked on the second ETH element on the page");
    }

    public boolean isInsufficientBalanceMessageDisplayed() {
        return isDisplayed("Insufficient_balance_message");
    }
        // Gets the actual amount displayed on the screen
        public String getActualAmount() {
            return driver.findElement(By.xpath("//*[@resource-id='wrapperContent']/*[position()=2]"))
                    .getText();
        }

        // Gets the actual recipient address displayed on the screen
        public String getActualRecipientAddress() {
            return driver.findElement(By.xpath("//android.view.View[@resource-id='wrapperContent']/android.view.View/android.widget.TextView"))
                    .getText();
        }
}
