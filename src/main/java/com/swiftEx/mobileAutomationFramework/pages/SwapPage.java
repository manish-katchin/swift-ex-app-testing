package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
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
            return isDisplayed(com.swiftEx.mobileAutomationFramework.utils.LocatorUtils.getUIAutomatorTextLocatorBy("Swap"), 20);
            } 
    

    // Enters amount in the input field below WETH
    public void enterSwapAmount(String amount) {
        sendKeys("swap_amount_input", amount);
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
                Thread.sleep(5000); // Wait 5 seconds before retry
            } catch (Exception e) {
                // Optionally log or handle exception
            }
        }
        return false;
    }

        // Clicks the Swap button specifically on the Swap page
        public void clickSwapButtonOnSwapPage() throws InterruptedException {
            logger.info("Waiting 10 seconds before clicking Swap button on Swap page");
            Thread.sleep(10000);
        //    List<MobileElement> element = (MobileElement) driver.findElementByAccessibilityId("SomeAccessibilityID");
        //    Point location = element.getLocation();
            tapOnCoordinates(driver, 333, 1245);
            logger.info("Clicked Swap button on Swap page via coordinates");
        }
    
        // Taps the currency switch arrow symbol
         public void tapCurrencySwitchArrow() {
        // Using accessibility id (content-desc) for the arrow symbol
        tap("currency_switch_arrow");
        logger.info("Tapped currency switch arrow symbol");
    }
}
