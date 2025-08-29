package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

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
            return isDisplayed(com.swiftEx.mobileAutomationFramework.utils.LocatorUtils.getUIAutomatorTextLocatorBy("Swap"), 20);
            } 
    

    // Enters amount in the input field below WETH
    public void enterSwapAmount(String amount) {
        sendKeys("swap_amount_input", amount);
        logger.info("Entered swap amount: {}", amount);
    }

        // Verifies the Swap Success message is displayed
        public boolean isSwapSuccessMessageDisplayed() {
            logger.info("Checking for Swap Success message");
            return isDisplayed(com.swiftEx.mobileAutomationFramework.utils.LocatorUtils.getUIAutomatorTextLocatorBy("Swap Success"), 15);
        }

        // Clicks the Swap button specifically on the Swap page
    public void clickSwapButtonOnSwapPage() throws InterruptedException {
        logger.info("Waiting 10 seconds before clicking Swap button on Swap page");
        Thread.sleep(10000);
        tap(LocatorUtils.getUIAutomatorTextLocatorBy("Swap"));
        logger.info("Clicked Swap button on Swap page");
    }

         
}
