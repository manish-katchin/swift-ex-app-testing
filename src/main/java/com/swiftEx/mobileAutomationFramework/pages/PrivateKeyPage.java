package com.swiftEx.mobileAutomationFramework.pages;
import java.util.ArrayList;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class PrivateKeyPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PrivateKeyPage.class);
    private static Map<Integer, String> staticMnemonicMap = new HashMap<>();

    public PrivateKeyPage(AppiumDriver driver) {
        super(driver, "privateKey.yaml");
        logger.info("✅ PrivateKeyPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public void enterAccountName(String name) {
        sendKeys("account_name_field", name);
        logger.debug("Entered account name: {}", name);
    }

    public void tapNext() {
        tap("next_button");
        logger.debug("Tapped next button");
    }

    public boolean isOnPrivateKeyPage() {
        return isDisplayed("private_key_title");
    }

    // /**
    //  * Extracts the digit-to-word mnemonic mapping from the Private Key screen.
    //  * 
    //  * @return Map<Integer, String> where key is digit and value is mnemonic word
    //  */
    // public Map<Integer, String> getMnemonicDigitWordMap() {
    //     // If we already have the map populated, return it
    //     if (!staticMnemonicMap.isEmpty()) {
    //         logger.debug("Returning cached mnemonic map with {} words", staticMnemonicMap.size());
    //         return staticMnemonicMap;
    //     }

    //     logger.info("📝 Extracting mnemonic digit-word mapping");

    //     int retries = 3;
    //     for (int attempt = 1; attempt <= retries; attempt++) {
    //         try {
    //             List<WebElement> elements = driver.findElements(getBy("mnemonic_digit_word"));
    //             logger.debug("Found {} mnemonic elements on attempt {}", elements.size(), attempt);

    //             for (WebElement element : elements) {
    //                 extractMnemonicFromElement(element);
    //             }

    //             if (!staticMnemonicMap.isEmpty()) {
    //                 logger.info("✅ Mnemonic map populated with {} words", staticMnemonicMap.size());
    //                 logMnemonicMap();
    //                 return staticMnemonicMap;
    //             }

    //         } catch (org.openqa.selenium.StaleElementReferenceException e) {
    //             logger.warn("⚠️ StaleElementReferenceException on attempt {}, retrying...", attempt);
    //             waitBetweenRetries();
    //         } catch (Exception e) {
    //             logger.error("❌ Error extracting mnemonic on attempt {}: {}", attempt, e.getMessage());
    //         }
    //     }

    //     logger.error("❌ Failed to extract mnemonic digit-word map after {} attempts", retries);
    //     return staticMnemonicMap;
    // }

    /**
 * SIMPLIFIED: Get mnemonic map using direct XPath
 */
public Map<Integer, String> getMnemonicDigitWordMap() {
    if (!staticMnemonicMap.isEmpty()) {
        logger.debug("Returning cached mnemonic map with {} words", staticMnemonicMap.size());
        return staticMnemonicMap;
    }

    logger.info("📝 Extracting mnemonic digit-word mapping");

    try {
        // Get all word containers in one query
        List<WebElement> wordContainers = driver.findElements(
            By.xpath("//android.view.ViewGroup[starts-with(@content-desc, 'word_')]")
        );
        
        logger.debug("Found {} word containers", wordContainers.size());
        
        for (WebElement container : wordContainers) {
            extractMnemonicFromElement(container);
        }
        
        if (!staticMnemonicMap.isEmpty()) {
            logger.info("✅ Mnemonic map populated with {} words", staticMnemonicMap.size());
            logMnemonicMap();
        } else {
            logger.error("❌ Failed to extract any mnemonic words");
        }
        
    } catch (Exception e) {
        logger.error("❌ Error extracting mnemonic: {}", e.getMessage());
        e.printStackTrace();
    }

    return staticMnemonicMap;
}
    /**
     * Clears the static mnemonic map (useful for cleanup or between tests)
     */
    public static void clearMnemonicMap() {
        staticMnemonicMap.clear();
        logger.debug("🧹 Mnemonic map cleared");
    }

    // private void extractMnemonicFromElement(WebElement element) {
    //     try {
    //         // The content-desc is in format "digit, word"
    //         String description = element.getAttribute("contentDescription");
    //         if (description != null && description.contains(",")) {
    //             String[] parts = description.split(",");
    //             if (parts.length >= 2) {
    //                 int digit = Integer.parseInt(parts[0].trim());
    //                 String word = parts[1].trim();
    //                 staticMnemonicMap.put(digit, word);
    //                 logger.debug("Mapped digit {} to word '{}'", digit, word);
    //             }
    //         }
    //     } catch (NumberFormatException e) {
    //         logger.warn("⚠️ Could not parse digit from mnemonic element: {}",
    //                 element.getAttribute("contentDescription"));
    //     } catch (Exception e) {
    //         logger.warn("⚠️ Error processing mnemonic element: {}", e.getMessage());
    //     }
    // }

    private void logMnemonicMap() {
        if (logger.isDebugEnabled()) {
            staticMnemonicMap.forEach((digit, word) -> logger.debug("🔢 Index {}: {}", digit, word));
        }
    }

    private void waitBetweenRetries() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Sleep interrupted during retry wait");
        }
    }

    public String getAccountNameFieldText() {
        WebElement accountNameField = driver.findElement(getBy("account_name_field"));
        return accountNameField.getText();
    }
/**
 * Extract mnemonic word from a word container element.
 * Works with content-desc="word_1", "word_2", etc.
 */
private void extractMnemonicFromElement(WebElement element) {
    try {
        // Get content-desc (e.g., "word_1", "word_2", etc.)
        String contentDesc = element.getAttribute("content-desc");
        
        if (contentDesc == null || !contentDesc.startsWith("word_")) {
            logger.debug("Skipping element with content-desc: {}", contentDesc);
            return;
        }
        
        // Extract position number from content-desc (e.g., "word_1" -> 1)
        String positionStr = contentDesc.replace("word_", "");
        int position = Integer.parseInt(positionStr);
        
        // Find the two TextViews inside this container
        List<WebElement> textViews = element.findElements(By.className("android.widget.TextView"));
        
        if (textViews.size() >= 2) {
            // Second TextView = actual mnemonic word
            String mnemonicWord = textViews.get(1).getText().trim();
            
            // Store in map
            staticMnemonicMap.put(position, mnemonicWord);
            logger.debug("Mapped position {} to word '{}'", position, mnemonicWord);
        }
        
    } catch (Exception e) {
        logger.warn("⚠️ Error extracting from element: {}", e.getMessage());
    }
}
}
