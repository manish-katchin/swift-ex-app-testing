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
}



