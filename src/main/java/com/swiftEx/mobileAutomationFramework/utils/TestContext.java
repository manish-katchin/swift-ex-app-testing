package com.swiftEx.mobileAutomationFramework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to store and retrieve test context information
 * This includes scenario name, feature name, and other test metadata
 */
public class TestContext {
    private static final Logger logger = LoggerFactory.getLogger(TestContext.class);
    
    // Thread-safe storage for test context
    private static final ThreadLocal<String> scenarioName = new ThreadLocal<>();
    private static final ThreadLocal<String> featureName = new ThreadLocal<>();
    private static final ThreadLocal<String[]> tags = new ThreadLocal<>();
    
    /**
     * Set the current scenario name
     */
    public static void setScenarioName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            scenarioName.set(name.trim());
            logger.debug("Scenario name set: {}", name.trim());
        }
    }
    
    /**
     * Get the current scenario name
     */
    public static String getScenarioName() {
        return scenarioName.get();
    }
    
    /**
     * Set the current feature name
     */
    public static void setFeatureName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            featureName.set(name.trim());
            logger.debug("Feature name set: {}", name.trim());
        }
    }
    
    /**
     * Get the current feature name
     */
    public static String getFeatureName() {
        return featureName.get();
    }
    
    /**
     * Set the current scenario tags
     */
    public static void setTags(String[] tagArray) {
        if (tagArray != null) {
            tags.set(tagArray);
            logger.debug("Tags set: {}", String.join(", ", tagArray));
        }
    }
    
    /**
     * Get the current scenario tags
     */
    public static String[] getTags() {
        return tags.get();
    }
    
    /**
     * Get a formatted test name for SauceLabs (Feature: Scenario)
     */
    public static String getFormattedTestName() {
        String scenario = getScenarioName();
        String feature = getFeatureName();
        
        if (scenario != null && feature != null) {
            return feature + ": " + scenario;
        } else if (scenario != null) {
            return scenario;
        } else if (feature != null) {
            return feature;
        }
        
        return null; // No context available
    }
    
    /**
     * Check if scenario has specific tag
     */
    public static boolean hasTag(String tag) {
        String[] currentTags = getTags();
        if (currentTags == null || tag == null) {
            return false;
        }
        
        for (String currentTag : currentTags) {
            if (tag.equals(currentTag) || tag.equals("@" + currentTag) || ("@" + tag).equals(currentTag)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clear all test context (called after test completion)
     */
    public static void clear() {
        scenarioName.remove();
        featureName.remove();
        tags.remove();
        logger.debug("Test context cleared");
    }
    
    /**
     * Print current test context for debugging
     */
    public static void printContext() {
        logger.info("=== TEST CONTEXT ===");
        logger.info("Feature: {}", getFeatureName() != null ? getFeatureName() : "Not set");
        logger.info("Scenario: {}", getScenarioName() != null ? getScenarioName() : "Not set");
        logger.info("Formatted Name: {}", getFormattedTestName() != null ? getFormattedTestName() : "Not available");
        String[] currentTags = getTags();
        if (currentTags != null && currentTags.length > 0) {
            logger.info("Tags: {}", String.join(", ", currentTags));
        } else {
            logger.info("Tags: Not set");
        }
        logger.info("==================");
    }
}
