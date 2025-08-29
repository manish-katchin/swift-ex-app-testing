package com.swiftEx.mobileAutomationFramework.steps;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.pages.BasePage;

import io.appium.java_client.AppiumDriver;

public class BaseStep {

    private static final Logger logger = LoggerFactory.getLogger(BaseStep.class);
    private static final Map<Class<?>, BasePage> pageCache = new ConcurrentHashMap<>();
    private static AppiumDriver driver;

    @SuppressWarnings("unchecked")
    protected <T extends BasePage> T page(Class<T> pageClass) {
        return (T) pageCache.computeIfAbsent(pageClass, clazz -> createPage(pageClass, getDriver()));
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Make sure DriverFactory.setDriver() is called first.");
        }
        return driver;
    }

    public static void setDriver(AppiumDriver appiumDriver) {
        logger.info(appiumDriver.toString());
        driver = appiumDriver;
        logger.info("Driver set in BaseStep: {}", driver.getSessionId());
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver quit successfully");
        }
    }

    private <T extends BasePage> T createPage(Class<T> pageClass, AppiumDriver driver) {
        try {
            if (driver == null) {
                throw new IllegalStateException(
                        "Driver is not initialized. Make sure DriverFactory.setDriver() is called first.");
            }

            logger.debug("Creating page: {}", pageClass.getSimpleName());

            // Find constructor that takes AppiumDriver and String (locator file)
            Constructor<T> constructor = findPageConstructor(pageClass);

            T page = constructor.newInstance(driver);
            logger.debug("Created page: {}", pageClass.getSimpleName());

            return page;

        } catch (Exception e) {
            logger.error("Failed to create page: {}", pageClass.getSimpleName(), e);
            throw new RuntimeException("Could not create page: " + pageClass.getSimpleName(), e);
        }
    }

    private <T extends BasePage> Constructor<T> findPageConstructor(Class<T> pageClass)
            throws NoSuchMethodException {
        try {
            // Try constructor with AppiumDriver and String (locator file)
            return pageClass.getConstructor(AppiumDriver.class, String.class);
        } catch (NoSuchMethodException e) {
            // Fallback to constructor with just AppiumDriver
            return pageClass.getConstructor(AppiumDriver.class);
        }
    }

    protected void clearPageCache() {
        pageCache.clear();
        logger.debug("🧹 Page cache cleared");
    }
}
