package com.swiftEx.mobileAutomationFramework.pages;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WalletSelectionPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(WalletSelectionPage.class);

    public WalletSelectionPage(AppiumDriver driver) {
        super(driver, "walletSelection.yaml");
        logger.info("✅ WalletSelectionPage ready for platform: {}", getPlatform().toUpperCase());
    }

    // Actions
    public void selectWalletByName(String walletName) {
        By walletLocator = By.xpath(String.format("//android.widget.TextView[@text='%s']", walletName));
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        WebElement walletElement = wait.until(ExpectedConditions.elementToBeClickable(walletLocator));
        walletElement.click();
        logger.info("Selected wallet by name: {}", walletName);
    }

    public void clickAddWalletButton() {
        tap("add_wallet_button");
        logger.info("Clicked Add Wallet button");
    }

    public boolean isWalletNameDisplayed(String walletName) {
        try {
        By walletNameLocator = By.xpath("//android.widget.TextView[@content-desc='wallet_Name' and contains(@text, '" + walletName.trim() + "')]");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement walletNameElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(walletNameLocator));
            return walletNameElement != null && walletNameElement.getText().contains(walletName);
        } catch (Exception e) {
            logger.error("Wallet name not displayed: {}", walletName, e);
            return false;
        }
    }

    public boolean isWalletActive(String walletName) {
    try {
        String xpath = String.format("//android.view.ViewGroup[contains(@content-desc, '%s') and contains(@content-desc, 'Active')]", walletName);
        By activeWalletLocator = By.xpath(xpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement activeWalletElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeWalletLocator));
        return activeWalletElement != null;
    } catch (Exception e) {
        logger.error("Active wallet not found: {}", walletName, e);
        return false;
    }
}

public boolean isAllWalletsDisplayed() {
    return isDisplayed("All_Wallets_List");
}

    public boolean isWalletSelectedPopupDisplayed(String walletName) {
        try {
            String popupText = "Wallet Selected " + walletName;
            By popupLocator = By.xpath(String.format("//android.widget.TextView[contains(@text, '%s')]", popupText));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
            return popupElement != null && popupElement.getText().contains(popupText);
        } catch (Exception e) {
            logger.error("Wallet Selected popup not displayed for: {}", walletName, e);
            return false;
        }
    }
        /**
         * Checks if a specific area of a wallet card is clickable using dynamic XPath.
         * @param walletName The wallet name (e.g., "Sam", "Main")
         * @param areaType The area type: "card", "name", "icon", "active", "tick"
         * @return true if the area is clickable, false otherwise
         */
        public boolean isWalletAreaClickable(String walletName, String areaType) {
            String xpath = null;
            switch (areaType.toLowerCase()) {
                case "card":
                    xpath = String.format(
                            "//android.view.ViewGroup[contains(@content-desc, '%s') and @clickable='true']",
                            walletName);
                    break;
                case "name":
                    xpath = String.format("//android.widget.TextView[@text='%s']", walletName);
                    break;
                case "icon":
                    xpath = String.format(
                            "//android.view.ViewGroup[contains(@content-desc, '%s')]//android.widget.ImageView",
                            walletName);
                    break;
                case "active":
                    xpath = String.format(
                            "//android.view.ViewGroup[contains(@content-desc, '%s')]//android.widget.TextView[@text='Active']",
                            walletName);
                    break;
                case "tick":
                    xpath = String.format(
                            "//android.view.ViewGroup[contains(@content-desc, '%s')]//android.view.ViewGroup[@content-desc='check_decagram']",
                            walletName);
                    break;
                default:
                    logger.warn("Unknown areaType: {}", areaType);
                    return false;
            }
            try {
                WebElement element = driver.findElement(By.xpath(xpath));
                return element.isDisplayed() && element.isEnabled();
            } catch (Exception e) {
                logger.error("Area not clickable: {} for wallet {}", areaType, walletName, e);
                return false;
            }
        }
        
        public void clickBackButtonOnAllWalletsScreen() {
            tap("All_Wallets_BackButton");
            logger.info("Clicked Back Button on All Wallets screen");
        }

        public boolean isWalletHeaderDisplayed() {
            return isDisplayed("wallet_header_title");
        }

        public void clickMyWalletOption() {
            tap("my_wallet_option");
            logger.info("Clicked on My Wallet option");
        }

            /**
 * Checks if the wallet name is displayed on the My Wallet screen.
 * @param walletName The wallet name to check (e.g., "Sam", "Main")
 * @return true if the wallet name is displayed, false otherwise
 */
            public boolean isWalletNameDisplayedOnMyWallet(String walletName) {
                try {
                    String xpath = String.format("//android.widget.TextView[@text='%s']", walletName);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement walletNameElement = wait
                            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                    return walletNameElement != null && walletNameElement.isDisplayed();
                } catch (Exception e) {
                    logger.error("Wallet name not displayed on My Wallet screen: {}", walletName, e);
                    return false;
                }
            }
public boolean isWarningMessageDisplayed() {
     return isDisplayed("warning_message");
}

}



