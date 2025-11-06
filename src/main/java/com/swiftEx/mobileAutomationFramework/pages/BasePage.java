package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import com.swiftEx.mobileAutomationFramework.utils.LocatorLoader;
import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

public abstract class BasePage {
    protected static final int DEFAULT_TIMEOUT = 10;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    // protected final AppiumDriver driver;
    public final AppiumDriver driver;

    protected final WebDriverWait wait;
    protected final Map<String, Map<String, String>> locators;

    public BasePage(AppiumDriver driver, String locatorFileName) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        String locatorFolder = isAndroid() ? "android" : "ios";
        this.locators = LocatorLoader.load("/locators/" + locatorFolder + "/" + locatorFileName);
    }

    protected By getBy(String locatorKey) {
        Map<String, String> locator = locators.get(locatorKey);
        if (locator == null) {
            throw new IllegalArgumentException("Locator not found: " + locatorKey);
        }
        return LocatorUtils.toBy(locator);
    }

    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findElement(String locatorKey) {
        return findElement(getBy(locatorKey));
    }

    protected void tap(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        logger.debug("Tapped element: {}", locator);
    }

    protected void tap(String locatorKey) {
        tap(getBy(locatorKey));
        logger.debug("Tapped element: {}", locatorKey);
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
        logger.debug("Entered text: {}", text);
    }

    protected void sendKeys(String locatorKey, String text) {
        sendKeys(getBy(locatorKey), text);
        logger.debug("Entered text in: {}", locatorKey);
    }

    protected String getText(By locator) {
        return findElement(locator).getText();
    }

    protected String getText(String locatorKey) {
        return getText(getBy(locatorKey));
    }

    protected boolean isDisplayed(By locator) {
        return isDisplayed(locator, DEFAULT_TIMEOUT); // Default 10 seconds wait
    }

    protected boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            if (timeoutSeconds == DEFAULT_TIMEOUT) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            } else {
                WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
                return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            }
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForElement(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not visible after {} seconds: {}", timeoutSeconds, locator);
        }
    }

    protected boolean isDisplayed(String locatorKey) {
        return isDisplayed(getBy(locatorKey));
    }

    protected boolean isDisplayed(String locatorKey, int timeoutSeconds) {
        return isDisplayed(getBy(locatorKey), timeoutSeconds);
    }

    protected void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElement(String locatorKey) {
        waitForElement(getBy(locatorKey));
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToBeClickable(String locatorKey) {
        waitForElementToBeClickable(getBy(locatorKey));
    }

    public boolean isAndroid() {
        return getPlatform().toLowerCase().contains("android");
    }

    public boolean isIOS() {
        return getPlatform().toLowerCase().contains("ios");
    }

    protected String getPlatform() {
        return driver.getCapabilities().getCapability("platformName").toString();
    }

    public void tapOnCoordinates(AppiumDriver driver, int x, int y) {
        Map<String, Object> args = new HashMap<>();
        args.put("x", x);
        args.put("y", y);
        args.put("tapCount", 1);
        args.put("duration", 100); // duration in ms
        driver.executeScript("mobile: clickGesture", args);
    }


    public boolean scrollDownUntilVisible(String locatorKey, int maxScrolls) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        scrollObject.put("strategy", "-android uiautomator");
        scrollObject.put("selector", "new UiSelector().text(\"Help Center\")");
        js.executeScript("mobile: scroll", scrollObject);
        By locator = getBy(locatorKey);
        Map<String, Object> rect = (Map<String, Object>) driver.executeScript("mobile: viewportRect");

        int left = ((Number) rect.get("left")).intValue();
        int top = ((Number) rect.get("top")).intValue();
        int width = ((Number) rect.get("width")).intValue();
        int height = ((Number) rect.get("height")).intValue();

        for (int i = 0; i < maxScrolls; i++) {
            if (isDisplayed(locator, 2)) {
                logger.info("Element '{}' found after {} scrolls", locatorKey, i);
                return true;
            }

            if (isAndroid()) {
                Map<String, Object> args = new HashMap<>();
                args.put("left", left / 2);
                args.put("top", top / 2);
                args.put("width", width / 2);
                args.put("height", height / 2);
                args.put("direction", "up");
                args.put("percent", 0.7);
                driver.executeScript("mobile: swipeGesture", args);
            } else if (isIOS()) {
                Map<String, Object> args = new HashMap<>();
                args.put("direction", "down");
                driver.executeScript("mobile: scroll", args);
            }

            logger.debug("Scrolled down (attempt {}) for locator '{}'", i + 1, locatorKey);
        }

        logger.warn("Element '{}' not found after {} scrolls", locatorKey, maxScrolls);
        return false;
    }
/**
     * Sets clipboard text on the device (Android/iOS).
     * @param text The text to set in clipboard
     */
    public void setClipboardText(String text) {
        if (isAndroid()) {
            // Use Appium Java client API for clipboard
            try {
                io.appium.java_client.clipboard.HasClipboard clipboardDriver = (io.appium.java_client.clipboard.HasClipboard) driver;
                clipboardDriver.setClipboardText(text);
                logger.info("Set clipboard text on Android: {}", text);
            } catch (Exception e) {
                logger.error("Failed to set clipboard text on Android: {}", e.getMessage());
            }
        } else if (isIOS()) {
            Map<String, Object> args = new HashMap<>();
            args.put("content", text);
            args.put("label", "text clipboard");
            driver.executeScript("mobile: setPasteboard", args);
            logger.info("Set clipboard text on iOS: {}", text);
        } else {
            logger.warn("Clipboard set not supported for this platform");
        }
    }

}