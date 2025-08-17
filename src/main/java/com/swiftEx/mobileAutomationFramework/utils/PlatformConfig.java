package com.swiftEx.mobileAutomationFramework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * Utility class for handling platform-specific configurations and CLI parameters
 * Supports dynamic platform switching between Android and iOS via command line
 */
public class PlatformConfig {
    private static final Logger logger = LoggerFactory.getLogger(PlatformConfig.class);
    
    // Platform constants
    public static final String ANDROID = "android";
    public static final String IOS = "ios";
    
    // Default build paths
    private static final String DEFAULT_ANDROID_APP = "src/test/resources/apps/android/android.apk";
    private static final String DEFAULT_IOS_APP = "src/test/resources/apps/ios/ios.app";
    
    // System properties for CLI parameters
    private static final String PLATFORM_PROPERTY = "platform";
    private static final String APP_PATH_PROPERTY = "app.path";
    private static final String ANDROID_PATH_PROPERTY = "android.app.path";
    private static final String IOS_PATH_PROPERTY = "ios.app.path";
    
    /**
     * Get the current platform from CLI parameter or framework.properties
     * @return platform name (android/ios)
     */
    public static String getCurrentPlatform() {
        // Use ConfigLoader for centralized configuration management
        String platform = ConfigLoader.getPlatformName();
        
        // Validate platform
        if (!ANDROID.equals(platform) && !IOS.equals(platform)) {
            logger.warn("Invalid platform '{}' specified. Defaulting to Android.", platform);
            platform = ANDROID;
        }
        
        logger.info("Current platform: {}", platform.toUpperCase());
        return platform;
    }
    
    /**
     * Get the app path for current platform
     * @return absolute path to app file
     */
    public static String getAppPath() {
        String platform = getCurrentPlatform();
        String appPath = null;
        
        // Check for generic app.path parameter first
        appPath = System.getProperty(APP_PATH_PROPERTY);
        if (appPath != null) {
            logger.info("Using generic app path from CLI: {}", appPath);
            return validateAndGetAbsolutePath(appPath);
        }
        
        // Check for platform-specific paths
        if (ANDROID.equals(platform)) {
            appPath = System.getProperty(ANDROID_PATH_PROPERTY, DEFAULT_ANDROID_APP);
            logger.info("Using Android app path: {}", appPath);
        } else if (IOS.equals(platform)) {
            appPath = System.getProperty(IOS_PATH_PROPERTY, DEFAULT_IOS_APP);
            logger.info("Using iOS app path: {}", appPath);
        }
        
        return validateAndGetAbsolutePath(appPath);
    }
    
    /**
     * Validate app path and convert to absolute path
     * @param appPath relative or absolute path
     * @return absolute path
     */
    private static String validateAndGetAbsolutePath(String appPath) {
        if (appPath == null) {
            throw new IllegalArgumentException("App path cannot be null");
        }
        
        File appFile = new File(appPath);
        
        // If relative path, make it absolute from project root
        if (!appFile.isAbsolute()) {
            String projectRoot = System.getProperty("user.dir");
            appFile = new File(projectRoot, appPath);
        }
        
        String absolutePath = appFile.getAbsolutePath();
        
        // Validate file exists
        if (!appFile.exists()) {
            logger.error("App file not found at path: {}", absolutePath);
            throw new IllegalArgumentException("App file not found: " + absolutePath);
        }
        
        logger.info("Validated app path: {}", absolutePath);
        return absolutePath;
    }
    
    /**
     * Get platform-specific capability properties
     * @return Properties object with platform-specific settings
     */
    public static Properties getPlatformCapabilities() {
        Properties capabilities = new Properties();
        String platform = getCurrentPlatform();
        
        if (ANDROID.equals(platform)) {
            capabilities.setProperty("platformName", "Android");
            capabilities.setProperty("automationName", "UiAutomator2");
            capabilities.setProperty("app", getAppPath());
            
            // Additional Android-specific capabilities from CLI
            addOptionalProperty(capabilities, "deviceName", "android.device.name", "Android Emulator");
            addOptionalProperty(capabilities, "platformVersion", "android.version", "12");
            addOptionalProperty(capabilities, "appPackage", "android.app.package", null);
            addOptionalProperty(capabilities, "appActivity", "android.app.activity", null);
            
        } else if (IOS.equals(platform)) {
            capabilities.setProperty("platformName", "iOS");
            capabilities.setProperty("automationName", "XCUITest");
            capabilities.setProperty("app", getAppPath());
            
            // Additional iOS-specific capabilities from CLI
            addOptionalProperty(capabilities, "deviceName", "ios.device.name", "iPhone Simulator");
            addOptionalProperty(capabilities, "platformVersion", "ios.version", "15.0");
            addOptionalProperty(capabilities, "bundleId", "ios.bundle.id", null);
            addOptionalProperty(capabilities, "udid", "ios.udid", null);
        }
        
        // Common capabilities
        addOptionalProperty(capabilities, "newCommandTimeout", "appium.timeout", "60");
        addOptionalProperty(capabilities, "fullReset", "app.full.reset", "true");
        addOptionalProperty(capabilities, "noReset", "app.no.reset", "false");
        
        return capabilities;
    }
    
    /**
     * Add optional property from system property
     */
    private static void addOptionalProperty(Properties props, String capabilityName, 
                                          String systemPropertyName, String defaultValue) {
        String value = System.getProperty(systemPropertyName, defaultValue);
        if (value != null) {
            props.setProperty(capabilityName, value);
        }
    }
    
    /**
     * Print platform configuration summary
     */
    public static void printConfigurationSummary() {
        logger.info("=== PLATFORM CONFIGURATION SUMMARY ===");
        logger.info("Platform: {}", getCurrentPlatform().toUpperCase());
        logger.info("App Path: {}", getAppPath());
        logger.info("Project Root: {}", System.getProperty("user.dir"));
        
        Properties capabilities = getPlatformCapabilities();
        logger.info("Platform Capabilities:");
        capabilities.forEach((key, value) -> 
            logger.info("  {}: {}", key, value));
        
        logger.info("=== CLI Parameters Available ===");
        logger.info("Platform switching:");
        logger.info("  -Dplatform=android|ios");
        logger.info("App path configuration:");
        logger.info("  -Dapp.path=/path/to/app");
        logger.info("  -Dandroid.app.path=/path/to/android.apk");
        logger.info("  -Dios.app.path=/path/to/ios.app");
        logger.info("Device configuration:");
        logger.info("  -Dandroid.device.name='Device Name'");
        logger.info("  -Dandroid.version='12'");
        logger.info("  -Dios.device.name='iPhone Simulator'");
        logger.info("  -Dios.version='15.0'");
        logger.info("========================================");
    }
    
    /**
     * Check if current platform is Android
     */
    public static boolean isAndroid() {
        return ANDROID.equals(getCurrentPlatform());
    }
    
    /**
     * Check if current platform is iOS
     */
    public static boolean isIOS() {
        return IOS.equals(getCurrentPlatform());
    }
}
