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
}
