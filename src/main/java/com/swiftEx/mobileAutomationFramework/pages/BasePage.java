package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Map;
import com.swiftEx.mobileAutomationFramework.utils.LocatorLoader;
import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

public abstract class BasePage {
    protected static final int DEFAULT_TIMEOUT = 10;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected final AppiumDriver driver;
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
}