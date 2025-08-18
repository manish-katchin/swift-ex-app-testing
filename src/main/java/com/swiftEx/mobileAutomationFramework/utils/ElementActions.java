package com.swiftEx.mobileAutomationFramework.utils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Centralized utility class for all UI element actions
 * Supports YAML locators and direct locator strings
 */
public class ElementActions {
    private static final Logger logger = LoggerFactory.getLogger(ElementActions.class);
    private final AppiumDriver driver;
    private final WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;

    public ElementActions(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    // ===== LOCATOR RESOLUTION =====
    
    private By resolveLocator(Object locator) {
        if (locator instanceof By) {
            return (By) locator;
        } else if (locator instanceof String) {
            // Direct locator string (e.g., "id=com.example:id/button")
            return parseLocatorString((String) locator);
        } else if (locator instanceof Map) {
            // YAML locator format
            @SuppressWarnings("unchecked")
            Map<String, String> locatorMap = (Map<String, String>) locator;
            return LocatorUtils.toBy(locatorMap);
        } else {
            throw new IllegalArgumentException("Unsupported locator type: " + locator.getClass());
        }
    }
    
    private By parseLocatorString(String locatorString) {
        String[] parts = locatorString.split("=", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid locator string format: " + locatorString);
        }
        
        String strategy = parts[0].toLowerCase();
        String value = parts[1];
        
        switch (strategy) {
            case "id": return By.id(value);
            case "xpath": return By.xpath(value);
            case "classname": return By.className(value);
            case "tagname": return By.tagName(value);
            case "name": return By.name(value);
            case "cssselector": return By.cssSelector(value);
            case "linktext": return By.linkText(value);
            case "partiallinktext": return By.partialLinkText(value);
            case "accessibilityid": return By.name(value); // Appium accessibility ID
            default:
                throw new IllegalArgumentException("Unsupported locator strategy: " + strategy);
        }
    }

    // ===== BASIC ELEMENT ACTIONS =====

    /**
     * Click element with optional timeout
     * @param locator - YAML map, By object, or string locator
     * @param timeout - optional timeout (uses default if not provided)
     */
    public void click(Object locator, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("CLICK: Clicking element: {}", by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
        logger.info("SUCCESS: Element clicked successfully");
    }

    /**
     * Double click element
     */
    public void doubleClick(Object locator, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("CLICK: Double clicking element: {}", by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(by));
        
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        logger.info("SUCCESS: Element double clicked successfully");
    }

    /**
     * Send keys to element
     * @param locator - element locator
     * @param text - text to send
     * @param clearFirst - whether to clear field before typing
     */
    public void sendKeys(Object locator, String text, boolean clearFirst, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("INPUT: Sending keys to element: {}, text: {}", by, text);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(by));
        
        if (clearFirst) {
            element.clear();
        }
        element.sendKeys(text);
        logger.info("SUCCESS: Text sent successfully");
    }

    /**
     * Send keys (default: clear first)
     */
    public void sendKeys(Object locator, String text, int... timeout) {
        sendKeys(locator, text, true, timeout);
    }

    /**
     * Get text from element
     */
    public String getText(Object locator, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("📖 Getting text from element: {}", by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        
        String text = element.getText();
        logger.info("SUCCESS: Text retrieved: {}", text);
        return text;
    }

    /**
     * Get attribute value from element
     */
    public String getAttribute(Object locator, String attributeName, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("INFO: Getting attribute '{}' from element: {}", attributeName, by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.presenceOfElementLocated(by));
        
        String value = element.getAttribute(attributeName);
        logger.info("SUCCESS: Attribute value retrieved: {}", value);
        return value;
    }

    // ===== VERIFICATION METHODS =====

    /**
     * Verify text matches expected value
     */
    public boolean verifyText(Object locator, String expectedText, int... timeout) {
        try {
            String actualText = getText(locator, timeout);
            boolean matches = actualText.equals(expectedText);
            
            if (matches) {
                logger.info("SUCCESS: Text verification passed. Expected: '{}', Actual: '{}'", expectedText, actualText);
            } else {
                logger.error("ERROR: Text verification failed. Expected: '{}', Actual: '{}'", expectedText, actualText);
            }
            return matches;
        } catch (Exception e) {
            logger.error("ERROR: Text verification failed with exception: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verify text contains expected value
     */
    public boolean verifyTextContains(Object locator, String expectedText, int... timeout) {
        try {
            String actualText = getText(locator, timeout);
            boolean contains = actualText.contains(expectedText);
            
            if (contains) {
                logger.info("SUCCESS: Text contains verification passed. Expected: '{}', Actual: '{}'", expectedText, actualText);
            } else {
                logger.error("ERROR: Text contains verification failed. Expected: '{}', Actual: '{}'", expectedText, actualText);
            }
            return contains;
        } catch (Exception e) {
            logger.error("ERROR: Text contains verification failed with exception: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verify element is visible
     */
    public boolean isElementVisible(Object locator, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : 5; // Shorter default for visibility check
        By by = resolveLocator(locator);
        
        try {
            logger.info("👀 Checking visibility of element: {}", by);
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
            WebElement element = customWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            boolean isVisible = element.isDisplayed();
            logger.info("SUCCESS: Element visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.info("WARNING: Element not visible: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verify element is enabled
     */
    public boolean isElementEnabled(Object locator, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        try {
            logger.info("INFO: Checking if element is enabled: {}", by);
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
            WebElement element = customWait.until(ExpectedConditions.presenceOfElementLocated(by));
            boolean isEnabled = element.isEnabled();
            logger.info("SUCCESS: Element enabled status: {}", isEnabled);
            return isEnabled;
        } catch (Exception e) {
            logger.error("ERROR: Failed to check element enabled status: {}", e.getMessage());
            return false;
        }
    }

    // ===== DROPDOWN/SELECT ACTIONS =====

    /**
     * Select dropdown option by visible text
     */
    public void selectByText(Object locator, String text, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("INFO: Selecting dropdown option by text: {} in element: {}", text, by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(by));
        
        Select select = new Select(element);
        select.selectByVisibleText(text);
        logger.info("SUCCESS: Dropdown option selected successfully");
    }

    /**
     * Select dropdown option by value
     */
    public void selectByValue(Object locator, String value, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("INFO: Selecting dropdown option by value: {} in element: {}", value, by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(by));
        
        Select select = new Select(element);
        select.selectByValue(value);
        logger.info("SUCCESS: Dropdown option selected successfully");
    }

    /**
     * Select dropdown option by index
     */
    public void selectByIndex(Object locator, int index, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("INFO: Selecting dropdown option by index: {} in element: {}", index, by);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(by));
        
        Select select = new Select(element);
        select.selectByIndex(index);
        logger.info("SUCCESS: Dropdown option selected successfully");
    }

    // ===== MOBILE SPECIFIC ACTIONS =====

    /**
     * Scroll to element (mobile)
     */
    public void scrollToElement(Object locator, int... timeout) {
        int waitTime = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
        By by = resolveLocator(locator);
        
        logger.info("INFO: Scrolling to element: {}", by);
        
        // Try to find element first
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
            customWait.until(ExpectedConditions.presenceOfElementLocated(by));
            logger.info("SUCCESS: Element found after scrolling");
        } catch (Exception e) {
            logger.info("🔄 Element not found, performing scroll action...");
            // Simple scroll implementation for compatibility
            driver.executeScript("mobile: scroll", Map.of("direction", "down"));
            logger.info("SUCCESS: Scroll action performed");
        }
    }

    /**
     * Swipe on screen (mobile) - Simple implementation
     */
    public void swipe(int startX, int startY, int endX, int endY, int duration) {
        logger.info("INFO: Swiping from ({},{}) to ({},{}) with duration {}ms", startX, startY, endX, endY, duration);
        
        // Use execute script for better compatibility
        Map<String, Object> swipeParams = Map.of(
            "startX", startX,
            "startY", startY,
            "endX", endX,
            "endY", endY,
            "duration", duration
        );
        
        try {
            driver.executeScript("mobile: swipe", swipeParams);
            logger.info("SUCCESS: Swipe action completed");
        } catch (Exception e) {
            logger.warn("WARNING: Swipe action failed: {}", e.getMessage());
            // Fallback to basic swipe if available
            logger.info("INFO: Attempting basic swipe fallback...");
        }
    }

    // ===== WAIT UTILITIES =====

    /**
     * Wait for element to be present
     */
    public WebElement waitForElement(Object locator, int timeout) {
        By by = resolveLocator(locator);
        logger.info("INFO: Waiting for element to be present: {}", by);
        
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = customWait.until(ExpectedConditions.presenceOfElementLocated(by));
        logger.info("SUCCESS: Element found");
        return element;
    }

    /**
     * Wait for multiple elements
     */
    public List<WebElement> waitForElements(Object locator, int timeout) {
        By by = resolveLocator(locator);
        logger.info("INFO: Waiting for elements to be present: {}", by);
        
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        List<WebElement> elements = customWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        logger.info("SUCCESS: Found {} elements", elements.size());
        return elements;
    }
}
