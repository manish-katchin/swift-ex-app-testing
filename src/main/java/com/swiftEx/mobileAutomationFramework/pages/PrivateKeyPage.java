package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import com.swiftEx.mobileAutomationFramework.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PrivateKeyPage extends BasePage {
    private static Map<Integer, String> staticMnemonicMap = new HashMap<>();

    public AppiumDriver getDriver() {
        return driver;
    }
    public ElementActions getElementActions() {
        return elementActions;
    }
    public By getLocatorBy(String locatorKey) {
        return getBy(locatorKey);
    }
    public PrivateKeyPage(AppiumDriver driver) {
        super(driver, "privateKey.yaml");
    }

    public void enterAccountName(String name) {
        elementActions.sendKeys(getBy("account_name_field"), name);
    }

    public void tapNext() {
        elementActions.click(getBy("next_button"));
    }

    /**
     * Extracts the digit-to-word mnemonic mapping from the Private Key screen.
     * @return Map<Integer, String> where key is digit and value is mnemonic word
     */
    public Map<Integer, String> getMnemonicDigitWordMap() {
        // If we already have the map populated, return it
        if (!staticMnemonicMap.isEmpty()) {
            return staticMnemonicMap;
        }

        int retries = 3;
        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                List<WebElement> elements = driver.findElements(getBy("mnemonic_digit_word"));
                for (WebElement el : elements) {
                    // The content-desc is in format "digit, word"
                    String desc = el.getAttribute("contentDescription");
                    if (desc != null && desc.contains(",")) {
                        String[] parts = desc.split(",");
                        try {
                            int digit = Integer.parseInt(parts[0].trim());
                            String word = parts[1].trim();
                            staticMnemonicMap.put(digit, word);
                        } catch (NumberFormatException e) {
                            logger.warn("Could not parse digit from mnemonic element: {}", desc);
                        }
                    }
                }
                
                if (!staticMnemonicMap.isEmpty()) {
                    System.out.println("Mnemonic map populated with " + staticMnemonicMap.size() + " words");
                    staticMnemonicMap.forEach((k, v) -> System.out.println("Index " + k + ": " + v));
                    return staticMnemonicMap;
                }
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                logger.warn("StaleElementReferenceException on attempt {}: retrying...", attempt);
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
        logger.error("Failed to extract mnemonic digit-word map after {} attempts due to stale elements.");
        return staticMnemonicMap;
    }

    // Method to clear the static map (useful for cleanup or between tests)
    public static void clearMnemonicMap() {
        staticMnemonicMap.clear();
    }
}
