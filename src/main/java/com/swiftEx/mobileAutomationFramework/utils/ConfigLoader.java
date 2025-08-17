package com.swiftEx.mobileAutomationFramework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to load configuration from framework.properties file
 * Provides centralized access to framework configuration with CLI override support
 */
public class ConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
    private static final String FRAMEWORK_PROPERTIES = "/framework.properties";
    private static Properties frameworkConfig;
    
    // Load properties once at startup
    static {
        loadFrameworkProperties();
    }
    
    /**
     * Load framework.properties file from classpath
     */
    private static void loadFrameworkProperties() {
        frameworkConfig = new Properties();
        
        try (InputStream input = ConfigLoader.class.getResourceAsStream(FRAMEWORK_PROPERTIES)) {
            if (input == null) {
                logger.warn("Could not find {} in classpath, using defaults", FRAMEWORK_PROPERTIES);
                return;
            }
            
            frameworkConfig.load(input);
            logger.info("Loaded framework configuration from {}", FRAMEWORK_PROPERTIES);
            logger.debug("Loaded {} properties", frameworkConfig.size());
            
        } catch (IOException e) {
            logger.error("Failed to load framework.properties", e);
            throw new RuntimeException("Failed to load framework configuration", e);
        }
    }
    
    /**
     * Get property value with CLI override support
     * Priority: 1) CLI parameter (-Dproperty=value), 2) framework.properties, 3) default
     * 
     * @param key Property key
     * @param defaultValue Default value if not found
     * @return Property value
     */
    public static String getProperty(String key, String defaultValue) {
        // First check CLI system properties (highest priority)
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            logger.debug("Using CLI override for {}: {}", key, systemValue);
            return systemValue;
        }
        
        // Then check framework.properties
        String configValue = frameworkConfig.getProperty(key);
        if (configValue != null) {
            logger.debug("Using framework.properties value for {}: {}", key, configValue);
            return configValue;
        }
        
        // Finally use default
        logger.debug("Using default value for {}: {}", key, defaultValue);
        return defaultValue;
    }
    
    /**
     * Get property value (no default)
     * @param key Property key
     * @return Property value or null if not found
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }
    
    /**
     * Get platform name with intelligent resolution
     * Supports both 'platform' and 'platformName' properties
     * @return Platform name (android/ios)
     */
    public static String getPlatformName() {
        // Check CLI parameters first
        String platform = System.getProperty("platform");
        if (platform != null) {
            return platform.toLowerCase();
        }
        
        String platformName = System.getProperty("platformName");
        if (platformName != null) {
            return platformName.toLowerCase();
        }
        
        // Check framework.properties
        platform = frameworkConfig.getProperty("platform");
        if (platform != null) {
            return platform.toLowerCase();
        }
        
        platformName = frameworkConfig.getProperty("platformName");
        if (platformName != null) {
            return platformName.toLowerCase();
        }
        
        // Default to android
        logger.info("No platform specified, defaulting to Android");
        return "android";
    }
    
    /**
     * Check if current platform is Android
     */
    public static boolean isAndroid() {
        return "android".equals(getPlatformName());
    }
    
    /**
     * Check if current platform is iOS
     */
    public static boolean isIOS() {
        return "ios".equals(getPlatformName());
    }
    
    /**
     * Get all loaded properties (for debugging)
     */
    public static Properties getAllProperties() {
        return new Properties(frameworkConfig);
    }
    
    /**
     * Print configuration summary for debugging
     */
    public static void printConfigSummary() {
        logger.info("=== FRAMEWORK CONFIGURATION ===");
        logger.info("Platform: {}", getPlatformName().toUpperCase());
        logger.info("Device: {}", getProperty("appium.deviceName", "Not specified"));
        logger.info("App: {}", getProperty("appium.app", "Not specified"));
        logger.info("Total properties loaded: {}", frameworkConfig.size());
        logger.info("==============================");
    }
}
