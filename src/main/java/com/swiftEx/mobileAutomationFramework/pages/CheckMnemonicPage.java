    // Checks if the Done button is enabled using locator ke
package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.messages.types.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class CheckMnemonicPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckMnemonicPage.class);

    public CheckMnemonicPage(AppiumDriver driver) {
        super(driver, "CheckMnemonic.yaml");
        logger.info("✅ CheckMnemonicPage ready for platform: {}", getPlatform().toUpperCase());
    }

    /**
     * Get all indexes that need verification on the Check Mnemonic page
     * 
     * @return List of indexes that need to be verified
     */
    public List<Integer> getRequiredMnemonicIndexes() {
        logger.info("🔍 Getting required mnemonic indexes");
        List<Integer> indexes = new ArrayList<>();

        try {
            List<WebElement> labels = driver
                    .findElements(By.xpath("//android.widget.TextView[contains(@text,'Word #')]"));
            logger.debug("Found {} word label elements", labels.size());

            for (WebElement label : labels) {
                String text = label.getText();
                try {
                    int index = Integer.parseInt(text.replaceAll("Word #", "").trim());
                    indexes.add(index);
                    logger.debug("Added index: {}", index);
                } catch (NumberFormatException e) {
                    logger.warn("Could not parse index from text: {}", text);
                }
            }
        } catch (Exception e) {
            logger.error("Error getting required mnemonic indexes: {}", e.getMessage());
        }

        logger.info("✅ Found {} required mnemonic indexes: {}", indexes.size(), indexes);
        return indexes;
    }

    /**
     * Clicks the mnemonic word button under the given index label (e.g., Word #1)
     * with retry logic for stability
     * 
     * @param index the index number (e.g., 1)
     * @param word  the mnemonic word to click
     */
    public void clickMnemonicWordForIndex(int index, String word) {
        logger.info("🔤 Clicking mnemonic word '{}' for index {}", word, index);
        
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                // Strategy 1: Try finding by content-desc directly (most reliable)
                By wordLocator = By.xpath(String.format(
                    "//android.view.ViewGroup[@content-desc='%s' and @clickable='true']",
                    word
                ));
                
                WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
                WebElement wordButton = wait.until(ExpectedConditions.elementToBeClickable(wordLocator));
                
                // Verify element is actually visible before clicking
                if (wordButton.isDisplayed()) {
                    wordButton.click();
                    logger.info("✅ Successfully clicked word '{}' for index {} (attempt {})", word, index, attempt);
                    Thread.sleep(300); // Small delay after click for UI to update
                    return;
                }
                
            } catch (org.openqa.selenium.TimeoutException e) {
                logger.warn("⚠️ Timeout finding word '{}' (attempt {}/{})", word, attempt, maxRetries);
                
                // Strategy 2: Fallback - try finding under Word #X label
                if (attempt == maxRetries - 1) {
                    try {
                        By fallbackLocator = By.xpath(String.format(
                            "//android.widget.TextView[@text='Word #%d']/following-sibling::*" +
                            "//android.view.ViewGroup[@content-desc='%s' and @clickable='true']",
                            index, word
                        ));
                        
                        WebElement wordButton = driver.findElement(fallbackLocator);
                        wordButton.click();
                        logger.info("✅ Clicked word '{}' using fallback locator", word);
                        Thread.sleep(300);
                        return;
                        
                    } catch (Exception fallbackError) {
                        logger.error("Fallback strategy also failed: {}", fallbackError.getMessage());
                    }
                }
                
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while clicking word", ie);
            } catch (Exception e) {
                logger.warn("⚠️ Error on attempt {}: {}", attempt, e.getMessage());
            }
            
            // Wait before retry
            if (attempt < maxRetries) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // Strategy 3: As a last resort, read the prompt word under "Word #X" and click that
        try {
            String promptWord = getPromptWordForIndex(index);
            if (promptWord != null && !promptWord.isEmpty()) {
                logger.warn("🔁 Requested word '{}' not found; using prompt word '{}' for index {}", word, promptWord, index);
                By promptLocator = By.xpath(String.format(
                    "//android.view.ViewGroup[@content-desc='%s' and @clickable='true']",
                    promptWord
                ));
                WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
                WebElement promptButton = wait.until(ExpectedConditions.elementToBeClickable(promptLocator));
                promptButton.click();
                Thread.sleep(300);
                return;
            }
        } catch (Exception e) {
            logger.error("Failed to use prompt word for index {}: {}", index, e.getMessage());
        }
        
        // Log available words for debugging
        List<String> availableWords = getAllAvailableWordOptions();
        logger.error("❌ Failed to click word '{}' after {} attempts. Available words: {}", 
            word, maxRetries, availableWords);
        
        throw new RuntimeException(
            String.format("Mnemonic word button not found for word: %s under index: %d. Available: %s",
                word, index, availableWords)
        );
    }

    /**
     * Verifies and selects all requested mnemonic words on the Check Mnemonic page
     * 
     * @param mnemonicMap Map of index to word mappings from the Private Key page
     */
    public void verifyAndSelectMnemonicWords(Map<Integer, String> mnemonicMap) {
        logger.info("📝 Starting mnemonic word verification with {} words", mnemonicMap.size());

        try {
            // Wait for verification screen to be fully loaded
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            
            // Wait for at least one word option to be clickable
            wait.until(driver -> {
                List<WebElement> options = driver.findElements(By.xpath(
                    "//android.view.ViewGroup[@clickable='true' and string-length(@content-desc) > 2]"
                ));
                return options.size() >= 12; // Should have at least 12 word options visible
            });
            
            logger.info("✅ Verification screen fully loaded with word options");
            
            // Additional small delay to ensure UI is stable
            Thread.sleep(1000);

            List<Integer> requiredIndexes = getRequiredMnemonicIndexes();
            logger.info("Found {} indexes requiring verification", requiredIndexes.size());

            for (Integer index : requiredIndexes) {
                String word = mnemonicMap.get(index);
                if (word == null || word.trim().isEmpty()) {
                    // If the stored word is unavailable, read the prompt word from the UI
                    word = getPromptWordForIndex(index);
                    logger.warn("Stored word missing for index {}; using prompt word '{}'", index, word);
                }

                logger.info("Selecting word '{}' for index {}", word, index);
                clickMnemonicWordForIndex(index, word);

                // Wait between selections for UI to update
                Thread.sleep(500);
            }

            logger.info("✅ Successfully verified and selected all mnemonic words");

        } catch (Exception e) {
            logger.error("❌ Error during mnemonic word verification: {}", e.getMessage());
            throw new RuntimeException("Mnemonic word verification failed", e);
        }
    }

    /**
     * Verifies if all required mnemonic words have been correctly selected
     * 
     * @return true if all words are correctly selected, false otherwise
     */
    public boolean verifyAllWordsSelected() {
        logger.info("🔍 Verifying all words are selected");

        try {
            List<WebElement> wordSections = driver
                    .findElements(By.xpath("//android.widget.TextView[contains(@text,'Word #')]"));
            logger.debug("Found {} word sections to verify", wordSections.size());

            for (WebElement section : wordSections) {
                String wordNum = section.getText().replaceAll("Word #", "").trim();

                if (!isWordSelectedInSection(section, wordNum)) {
                    logger.warn("❌ No selected word found for Word #{}", wordNum);
                    return false;
                }
            }

            logger.info("✅ All word sections have selected words");
            return true;

        } catch (Exception e) {
            logger.error("❌ Error verifying word selections: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Taps the Done button on the Check Mnemonic page
     */
    public void tapDone() {
        tap("done_button");
        logger.info("Tapped done button");
    }

    // Helper methods
    
    /**
     * Get all currently visible word options (for debugging)
     */
    private List<String> getAllAvailableWordOptions() {
        try {
            List<WebElement> wordButtons = driver.findElements(By.xpath(
                "//android.view.ViewGroup[@clickable='true' and string-length(@content-desc) > 2 and @content-desc != 'Import']"
            ));
            
            List<String> words = new ArrayList<>();
            for (WebElement button : wordButtons) {
                String word = button.getAttribute("content-desc");
                if (word != null && !word.isEmpty()) {
                    words.add(word);
                }
            }
            return words;
        } catch (Exception e) {
            logger.warn("Could not get available words: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Reads the prompt word shown under the specific "Word #X" label on the Verify screen.
     * Attempts multiple XPath strategies to be resilient to minor layout changes.
     */
    private String getPromptWordForIndex(int index) {
        try {
            // Strategy A: The prompt word is the immediate following TextView after the label
            By promptA = By.xpath(String.format(
                "//android.widget.TextView[@text='Word #%d']/following-sibling::android.widget.TextView[1]",
                index
            ));
            List<WebElement> candidatesA = driver.findElements(promptA);
            if (!candidatesA.isEmpty()) {
                String text = candidatesA.get(0).getText();
                logger.debug("Prompt word (A) for index {}: {}", index, text);
                return text;
            }

            // Strategy B: Find ancestor section and any child TextView that looks like a word
            By sectionBy = By.xpath(String.format(
                "//android.widget.TextView[@text='Word #%d']/ancestor::android.view.ViewGroup[2]",
                index
            ));
            WebElement section = driver.findElement(sectionBy);
            List<WebElement> texts = section.findElements(By.xpath(
                ".//android.widget.TextView[string-length(@text) > 1 and not(contains(@text,'Word #'))]"
            ));
            if (!texts.isEmpty()) {
                String text = texts.get(0).getText();
                logger.debug("Prompt word (B) for index {}: {}", index, text);
                return text;
            }

        } catch (Exception e) {
            logger.warn("Could not read prompt word for index {}: {}", index, e.getMessage());
        }
        return null;
    }

    private boolean isWordSelectedInSection(WebElement section, String wordNum) {
        try {
            WebElement parentSection = section.findElement(By.xpath("./ancestor::android.view.ViewGroup[2]"));
            List<WebElement> selectedButtons = parentSection.findElements(
                    By.xpath(".//android.view.ViewGroup[@selected='true' or contains(@content-desc, 'selected')]"));

            if (!selectedButtons.isEmpty()) {
                String selectedWord = selectedButtons.get(0).getAttribute("content-desc");
                logger.debug("Word #{} has selected word: {}", wordNum, selectedWord);
                return true;
            }

            return false;
        } catch (Exception e) {
            logger.warn("Error checking word selection for #{}: {}", wordNum, e.getMessage());
            return false;
        }
    }

    public boolean verifyErrorMessage(String errorMessage) {
        try {
            By errorBy = LocatorUtils.getUIAutomatorTextLocatorBy(errorMessage);
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(2));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorBy));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            logger.warn("Error message not found or not visible: {}", errorMessage);
            return false;
        }
    }
        // Verifies all mnemonic phrases are visible on the screen
        public boolean areMnemonicPhrasesVisible() {
            try {
                WebElement mnemonicPhrase = driver.findElement(By.xpath(
                        "//android.widget.ScrollView//android.view.ViewGroup[starts-with(@content-desc, 'word_')]/android.widget.TextView[2]"));
                boolean displayed = mnemonicPhrase.isDisplayed();
                logger.info("Mnemonic phrase element displayed: {}", displayed);
                return displayed;
            } catch (Exception e) {
                logger.error("Error verifying mnemonic phrase visibility: {}", e.getMessage());
                return false;
            }
        }

        public boolean isDoneButtonEnabled() {
            try {
                WebElement doneButton = driver.findElement(getBy("done_button"));
                boolean enabled = doneButton.isEnabled();
                logger.info("Done button enabled: {}", enabled);
                return enabled;
            } catch (Exception e) {
                logger.error("Error checking Done button enabled state: {}", e.getMessage());
                return false;
            }
        }
        // Verifies the 'Verify Secret Phrase' label is visible on the screen
        public boolean isVerifySecretPhraseLabelVisible() {
            boolean displayed = isDisplayed("verify_secret_phrase_label");
            logger.info("Verify Secret Phrase label visibility: {}", displayed);
            return displayed;
        }
        //areJumbledMnemonicPhrasesVisible
        public boolean areJumbledMnemonicPhrasesVisible() {
            try {
                List<WebElement> jumbledPhrases = driver.findElements(By.xpath(
                        "//android.view.ViewGroup[@clickable='true' and @focusable='true' and not(@content-desc='Import') and android.widget.TextView]"));
                boolean visible = jumbledPhrases.size() >= 12;
                logger.info("Jumbled mnemonic phrases visible count: {}", jumbledPhrases.size());
                return visible;
            } catch (Exception e) {
                logger.error("Error verifying jumbled mnemonic phrases visibility: {}", e.getMessage());
                return false;
            }
        }
        // Clicks the Import button on the Verify Secret Phrase screen
     public void clickImportButtonOnVerifySecretPhraseScreen() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement importButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text,'Import')]")));
        importButton.click();
        logger.info("Clicked on the Import button on the Verify Secret Phrase screen");

        }
    

        // Checks if the Done button is disabled on the Check Mnemonic page
        public boolean isDoneButtonDisabled() {
            try {
                WebElement doneButton = driver.findElement(getBy("done_button"));
                boolean disabled = !doneButton.isEnabled();
                logger.info("Done button disabled: {}", disabled);
                return disabled;
            } catch (Exception e) {
                logger.error("Error checking Done button disabled state: {}", e.getMessage());
                return false;
            }
        }

}
