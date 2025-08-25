package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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
     * 
     * @param index the index number (e.g., 1)
     * @param word  the mnemonic word to click
     */
    public void clickMnemonicWordForIndex(int index, String word) {
        logger.info("🔤 Clicking mnemonic word '{}' for index {}", word, index);

        try {
            // Wait for page to load
            waitForElement("Word #" + index, 3);

            // Find the correct section containing our word index
            List<WebElement> sections = driver.findElements(
                    By.xpath("//android.view.ViewGroup[.//android.widget.TextView[contains(@text,'Word #')]]"));

            for (WebElement section : sections) {
                if (findAndClickWordInSection(section, index, word)) {
                    logger.info("✅ Successfully clicked word '{}' for index {}", word, index);
                    return;
                }
            }

            throw new RuntimeException("Mnemonic word button not found for word: " + word + " under index: " + index);

        } catch (Exception e) {
            logger.error("❌ Error clicking mnemonic word '{}' for index {}: {}", word, index, e.getMessage());
            throw e;
        }
    }

    /**
     * Verifies and selects all requested mnemonic words on the Check Mnemonic page
     * 
     * @param mnemonicMap Map of index to word mappings from the Private Key page
     */
    public void verifyAndSelectMnemonicWords(Map<Integer, String> mnemonicMap) {
        logger.info("📝 Starting mnemonic word verification with {} words", mnemonicMap.size());

        try {
            // Wait for page to be fully loaded
            waitForElement("Word #1", 5);

            List<Integer> requiredIndexes = getRequiredMnemonicIndexes();
            logger.info("Found {} indexes requiring verification", requiredIndexes.size());

            for (Integer index : requiredIndexes) {
                String word = mnemonicMap.get(index);
                if (word == null) {
                    throw new RuntimeException("No mnemonic word found for index: " + index + ". Available words: "
                            + mnemonicMap.keySet());
                }

                logger.info("Selecting word '{}' for index {}", word, index);
                clickMnemonicWordForIndex(index, word);

                // Small wait between selections
                waitBetweenSelections();
            }

            logger.info("✅ Successfully verified and selected all mnemonic words");

        } catch (Exception e) {
            logger.error("❌ Error during mnemonic word verification: {}", e.getMessage());
            throw e;
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
    private boolean findAndClickWordInSection(WebElement section, int index, String word) {
        try {
            WebElement label = section
                    .findElement(By.xpath(".//android.widget.TextView[contains(@text,'Word #" + index + "')]"));
            if (label != null) {
                List<WebElement> wordButtons = section
                        .findElements(By.xpath(".//android.view.ViewGroup[@content-desc]"));
                for (WebElement button : wordButtons) {
                    String buttonText = button.getAttribute("contentDescription");
                    if (buttonText != null && buttonText.trim().equalsIgnoreCase(word.trim())) {
                        button.click();
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            // This section doesn't contain our target word index
            logger.debug("Section doesn't contain index {}: {}", index, e.getMessage());
        }
        return false;
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

    private void waitForElement(String text, int timeoutSeconds) {
        try {
            // Simple wait implementation - could be enhanced
            Thread.sleep(timeoutSeconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Wait interrupted for element: {}", text);
        }
    }

    private void waitBetweenSelections() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Sleep interrupted between word selections");
        }
    }

    public boolean verifyErrorMessage(String errorMessage) {
        try {
            Thread.sleep(1000); // Wait for error to appear
            WebElement errorElement = driver.findElement(LocatorUtils.getUIAutomatorTextLocatorBy(errorMessage));
            return errorElement.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.warn("Error message not found: {}", errorMessage);
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
