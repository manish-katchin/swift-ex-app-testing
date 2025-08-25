package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;

public class BackupYourWalletPage extends BasePage {
    public BackupYourWalletPage(AppiumDriver driver) {
        super(driver, "BackupYourwallet.yaml");
    }

    public void selectLosePrivateKeyOption() {
        elementActions.click(getBy("lose_private_key_option"));
    }

    public void selectSharePrivateKeyOption() {
        elementActions.click(getBy("share_private_key_option"));
    }

    public void tapContinue() {
        elementActions.click(getBy("continue_button"));
    }

    /**
     * Checks if the Backup Your Wallet screen is displayed
     * @return true if the screen is displayed, false otherwise
     */
    public boolean isBackupScreenDisplayed() {
        try {
            // Add a wait for the elements to be visible
            elementActions.waitForElement(getBy("backup_title"), 10);
            elementActions.waitForElement(getBy("lose_private_key_option"), 10);
            
            // Log the page source if elements are not found
            if (!elementActions.isElementVisible(getBy("backup_title"))) {
                System.out.println("DEBUG: Backup title not found. Current page source:");
                System.out.println(driver.getPageSource());
                return false;
            }

            return elementActions.isElementVisible(getBy("backup_title")) &&
                   elementActions.isElementVisible(getBy("lose_private_key_option"));
        } catch (Exception e) {
            System.out.println("DEBUG: Exception in isBackupScreenDisplayed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
