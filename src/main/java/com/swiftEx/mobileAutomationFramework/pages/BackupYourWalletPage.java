package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class BackupYourWalletPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BackupYourWalletPage.class);

    public BackupYourWalletPage(AppiumDriver driver) {
        super(driver, "BackupYourwallet.yaml");
        logger.info("✅ BackupYourWalletPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public void selectLosePrivateKeyOption() {
        tap("lose_private_key_option");
        logger.info("Selected lose private key option");
    }

    public void selectSharePrivateKeyOption() {
        tap("share_private_key_option");
        logger.info("Selected share private key option");
    }

    public void tapContinue() {
        tap("continue_button");
        logger.info("Tapped continue button");
    }

        // Verifies if the Continue button is disabled
        public boolean isContinueButtonDisabled() {
            try {
                // Find parent ViewGroup with content-desc="Continue"
                WebElement element = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Continue']"));
                boolean disabled = !element.isEnabled();
                logger.info("Parent ViewGroup (Continue button) is {}", disabled ? "disabled" : "enabled");
                return disabled;
            } catch (Exception e) {
                logger.error("Error checking if parent ViewGroup (Continue button) is disabled: {}", e.getMessage());
                return false;
            }
        }

        public boolean isBackupScreenDisplayed() {
            logger.info("🔍 Verifying backup screen is displayed");

            try {
                // Wait for elements to be visible
                waitForElement("backup_title");

                boolean titleVisible = isDisplayed("backup_title");
                boolean optionVisible = isDisplayed("lose_private_key_option");
                boolean backupScreenDisplayed = titleVisible && optionVisible;

                logger.info("✅ Backup screen verification: {}", backupScreenDisplayed ? "SUCCESS" : "FAILED");
                logger.debug("Backup title visible: {}, Option visible: {}", titleVisible, optionVisible);

                return backupScreenDisplayed;

            } catch (Exception e) {
                logger.error("❌ Error verifying backup screen: {}", e.getMessage());
                return false;
            }
        }

    public boolean isBackupMnemonicPhrasePageDisplayed() {
        return isDisplayed("backup_mnemonic_phrase");
    }
}
