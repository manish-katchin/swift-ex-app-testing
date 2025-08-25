package com.swiftEx.mobileAutomationFramework.utils;

// cSpell:ignore androiduiautomator iospredicate iosclasschain Appium

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.Map;

public class LocatorUtils {
    public static By getUIAutomatorTextLocatorBy(String text) {
        String selector = "new UiSelector().textContains(\"" + text + "\")";
        return AppiumBy.androidUIAutomator(selector);
    }

    public static By toBy(Map<String, String> locator) {
        if (locator == null)
            return null;
        String strategy = locator.getOrDefault("strategy", "id").toLowerCase().replaceAll("[^a-z0-9]", "");
        String value = locator.get("value");
        switch (strategy) {
            case "id":
                return By.id(value);
            case "accessibility":
                return AppiumBy.accessibilityId(value);
            case "xpath":
                return By.xpath(value);
            case "class":
            case "classname":
                return By.className(value);
            case "androiduiautomator":
                return AppiumBy.androidUIAutomator(value);
            case "iospredicate":
                return AppiumBy.iOSNsPredicateString(value);
            case "iosclasschain":
                return AppiumBy.iOSClassChain(value);
            default:
                // fallback to xpath if unknown
                return By.xpath(value);
        }
    }
}
