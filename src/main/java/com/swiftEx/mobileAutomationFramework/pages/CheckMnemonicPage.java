package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Thread;

public class CheckMnemonicPage extends BasePage {
    public CheckMnemonicPage(AppiumDriver driver) {
        super(driver, "CheckMnemonic.yaml");
    }

    /**
     * Clicks the mnemonic word button under the given index label (e.g., Word #1)
     * @param index the index number (e.g., 1)
     * @param word the mnemonic word to click
     */
    /**
     * Get all indexes that need verification on the Check Mnemonic page
     * @return List of indexes that need to be verified
     */
    public List<Integer> getRequiredMnemonicIndexes() {
        List<Integer> indexes = new ArrayList<>();
        List<WebElement> labels = driver.findElements(org.openqa.selenium.By.xpath("//android.widget.TextView[contains(@text,'Word #')]"));
        for (WebElement label : labels) {
            String text = label.getText();
            try {
                int index = Integer.parseInt(text.replaceAll("Word #", "").trim());
                indexes.add(index);
            } catch (NumberFormatException e) {
                // Skip if we can't parse the index number
            }
        }
        return indexes;
    }

    /**
     * Clicks the mnemonic word button under the given index label (e.g., Word #1)
     * @param index the index number (e.g., 1)
     * @param word the mnemonic word to click
     */
    public void clickMnemonicWordForIndex(int index, String word) {
        try {
            // Give the page time to load
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // First, get all the section containers
        List<WebElement> sections = driver.findElements(org.openqa.selenium.By.xpath("//android.view.ViewGroup[.//android.widget.TextView[contains(@text,'Word #')]]"));
        
        // Find the correct section containing our word index
        for (WebElement section : sections) {
            try {
                WebElement label = section.findElement(org.openqa.selenium.By.xpath(".//android.widget.TextView[contains(@text,'Word #" + index + "')]"));
                if (label != null) {
                    // Now find all word buttons in this section
                    List<WebElement> wordButtons = section.findElements(org.openqa.selenium.By.xpath(".//android.view.ViewGroup[@content-desc]"));
                    for (WebElement button : wordButtons) {
                        String buttonText = button.getAttribute("contentDescription");
                        if (buttonText != null && buttonText.trim().equalsIgnoreCase(word.trim())) {
                            button.click();
                            return;
                        }
                    }
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                // This section doesn't contain our target word index, continue to next section
                continue;
            }
        }
        
        // If we get here, we didn't find and click the word
        throw new RuntimeException("Mnemonic word button not found for word: " + word + " under index: " + index);
    }

    /**
     * Verifies and selects all requested mnemonic words on the Check Mnemonic page
     * @param mnemonicMap Map of index to word mappings from the Private Key page
     */
    public void verifyAndSelectMnemonicWords(Map<Integer, String> mnemonicMap) {
        System.out.println("Starting mnemonic word verification with map: " + mnemonicMap);
        
        // Wait for the page to be fully loaded
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Integer> requiredIndexes = getRequiredMnemonicIndexes();
        System.out.println("Found indexes requiring verification: " + requiredIndexes);
        
        for (Integer index : requiredIndexes) {
            String word = mnemonicMap.get(index);
            if (word == null) {
                throw new RuntimeException("No mnemonic word found for index: " + index + ". Available words: " + mnemonicMap);
            }
            System.out.println("Selecting word '" + word + "' for index " + index);
            clickMnemonicWordForIndex(index, word);
            
            // Small wait between selections to ensure UI is stable
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifies if all required mnemonic words have been correctly selected
     * @return true if all words are correctly selected, false otherwise
     */
    public boolean verifyAllWordsSelected() {
        try {
            // Wait a bit for any animations to complete
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<WebElement> wordSections = driver.findElements(org.openqa.selenium.By.xpath("//android.widget.TextView[contains(@text,'Word #')]"));
        System.out.println("DEBUG: Found " + wordSections.size() + " word sections to verify");

        for (WebElement section : wordSections) {
            try {
                String wordNum = section.getText().replaceAll("Word #", "").trim();
                System.out.println("DEBUG: Checking Word #" + wordNum);

                // Get the parent section containing this word number
                WebElement parentSection = section.findElement(org.openqa.selenium.By.xpath("./ancestor::android.view.ViewGroup[2]"));
                
                // Find selected button within this section
                List<WebElement> selectedButtons = parentSection.findElements(org.openqa.selenium.By.xpath(".//android.view.ViewGroup[@selected='true' or contains(@content-desc, 'selected')]"));
                
                System.out.println("DEBUG: Word #" + wordNum + " has " + selectedButtons.size() + " selected options");
                
                // If no selected button is found in this section
                if (selectedButtons.isEmpty()) {
                    System.out.println("DEBUG: No selected word found for Word #" + wordNum);
                    return false;
                }

                // Get the selected word text for debugging
                String selectedWord = selectedButtons.get(0).getAttribute("content-desc");
                System.out.println("DEBUG: Selected word for #" + wordNum + " is: " + selectedWord);

            } catch (Exception e) {
                System.out.println("DEBUG: Exception while checking word section: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        // All sections have a selected word
        System.out.println("DEBUG: All word sections have selected words");
        return true;
    }

    /**
     * Taps the Done button on the Check Mnemonic page
     */
    public void tapDone() {
        elementActions.click(getBy("done_button"));
    }
}
