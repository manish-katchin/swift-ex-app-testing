package com.swiftEx.mobileAutomationFramework.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class LocatorLoader {
    @SuppressWarnings("unchecked")
    public static Map<String, Map<String, String>> load(String resourcePath) {
        Yaml yaml = new Yaml();
        try (InputStream in = LocatorLoader.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new RuntimeException("Locator resource not found: " + resourcePath);
            }
            Object loaded = yaml.load(in);
            return (Map<String, Map<String, String>>) loaded;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load locators: " + resourcePath, e);
        }
    }

    /**
     * Platform-aware locator loading
     * Automatically detects platform from framework.properties with CLI override
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Map<String, String>> loadForPlatform(String fileName) {
        // Use ConfigLoader to get platform from framework.properties
        String platform = ConfigLoader.getPlatformName();

        // Construct platform-specific path
        String platformPath = platform.equals("ios") ? "ios" : "android";
        String resourcePath = "/locators/" + platformPath + "/" + fileName;

        return load(resourcePath);
    }

    /**
     * Load locators with fallback to default platform
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Map<String, String>> loadWithFallback(String fileName) {
        // Use ConfigLoader to get platform from framework.properties
        String platform = ConfigLoader.getPlatformName();

        String platformPath = platform.equals("ios") ? "ios" : "android";
        String resourcePath = "/locators/" + platformPath + "/" + fileName;

        try {
            return load(resourcePath);
        } catch (RuntimeException e) {
            // Fallback to android if iOS locators not found
            if (platform.equals("ios")) {
                System.out.println("WARNING: iOS locators not found, falling back to Android locators");
                return load("/locators/android/" + fileName);
            }
            throw e;
        }
    }
}
