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

        public boolean isContinueButtonEnabled() {
            try {
                WebElement continueButton = driver.findElement(getBy("continue_button"));
                boolean enabled = continueButton.isEnabled();
                logger.info("Continue button enabled: {}", enabled);
                return enabled;
            } catch (Exception e) {
                logger.error("Error checking Continue button enabled state: {}", e.getMessage());
                return false;
            }
        }
  /**
     * Taps the Back button on the Backup Your Wallet screen
     */
  public void clickBackButtonOnBackupScreen() {
      tap("backup_screen_back_button");
      logger.info("Tapped Back button on Backup Your Wallet screen");
  }
      /**
     * Verifies the first mnemonic instruction is displayed with correct text
     */
    public boolean isMnemonicInstruction1Displayed(String expectedText) {
        try {
            WebElement instruction = driver.findElement(getBy("mnemonic_instruction_1"));
            boolean displayed = instruction.isDisplayed() && instruction.getText().trim().equals(expectedText.trim());
            logger.info("Mnemonic instruction 1 displayed: {} | Text matches: {}", instruction.isDisplayed(), displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Error verifying mnemonic instruction 1: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verifies the second mnemonic instruction is displayed with correct text
     */
    public boolean isMnemonicInstruction2Displayed(String expectedText) {
        try {
            WebElement instruction = driver.findElement(getBy("mnemonic_instruction_2"));
            boolean displayed = instruction.isDisplayed() && instruction.getText().trim().equals(expectedText.trim());
            logger.info("Mnemonic instruction 2 displayed: {} | Text matches: {}", instruction.isDisplayed(), displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Error verifying mnemonic instruction 2: {}", e.getMessage());
            return false;
        }
    }
}
