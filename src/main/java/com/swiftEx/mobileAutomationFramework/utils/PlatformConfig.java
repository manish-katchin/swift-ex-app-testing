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
    public static final String ANDROID_SAUCE = "androidsauce";
    public static final String IOS_SAUCE = "iossauce";
    
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
     * @return platform name (android/ios/androidSauce/iOSSauce)
     */
    public static String getCurrentPlatform() {
        // Use ConfigLoader for centralized configuration management
        String platform = ConfigLoader.getPlatformName();
        
        // Validate platform
        if (!ANDROID.equals(platform) && !IOS.equals(platform) && 
            !ANDROID_SAUCE.equals(platform) && !IOS_SAUCE.equals(platform)) {
            logger.warn("Invalid platform '{}' specified. Defaulting to Android.", platform);
            platform = ANDROID;
        }
        
        logger.info("Current platform: {}", platform.toUpperCase());
        return platform;
    }
    
    /**
     * Get the app path for current platform
     * @return absolute path to app file (or SauceLabs storage URL)
     */
    public static String getAppPath() {
        String platform = getCurrentPlatform();
        String appPath = null;
        
        // Handle SauceLabs platforms first
        if (ANDROID_SAUCE.equals(platform)) {
            appPath = ConfigLoader.getProperty("saucelabs.android.app", "sauce-storage:android.apk");
            logger.info("Using SauceLabs Android app: {}", appPath);
            return appPath; // SauceLabs URLs don't need validation
        } else if (IOS_SAUCE.equals(platform)) {
            appPath = ConfigLoader.getProperty("saucelabs.ios.app", "sauce-storage:SwiftExApp.app");
            logger.info("Using SauceLabs iOS app: {}", appPath);
            return appPath; // SauceLabs URLs don't need validation
        }
        
        // Handle local platforms
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
            // Local Android configuration
            capabilities.setProperty("platformName", "Android");
            capabilities.setProperty("automationName", "UiAutomator2");
            capabilities.setProperty("app", getAppPath());
            
            // Additional Android-specific capabilities from CLI
            addOptionalProperty(capabilities, "deviceName", "android.device.name", "Android Emulator");
            addOptionalProperty(capabilities, "platformVersion", "android.version", "12");
            addOptionalProperty(capabilities, "appPackage", "android.app.package", null);
            addOptionalProperty(capabilities, "appActivity", "android.app.activity", null);
            
        } else if (IOS.equals(platform)) {
            // Local iOS configuration
            capabilities.setProperty("platformName", "iOS");
            capabilities.setProperty("automationName", "XCUITest");
            capabilities.setProperty("app", getAppPath());
            
            // Additional iOS-specific capabilities from CLI
            addOptionalProperty(capabilities, "deviceName", "ios.device.name", "iPhone Simulator");
            addOptionalProperty(capabilities, "platformVersion", "ios.version", "15.0");
            addOptionalProperty(capabilities, "bundleId", "ios.bundle.id", null);
            addOptionalProperty(capabilities, "udid", "ios.udid", null);
            
        } else if (ANDROID_SAUCE.equals(platform)) {
            // SauceLabs Android configuration
            capabilities.setProperty("platformName", ConfigLoader.getProperty("saucelabs.android.platformName", "Android"));
            capabilities.setProperty("deviceName", ConfigLoader.getProperty("saucelabs.android.deviceName", "Google Pixel 4 GoogleAPI Emulator"));
            capabilities.setProperty("platformVersion", ConfigLoader.getProperty("saucelabs.android.platformVersion", "12.0"));
            capabilities.setProperty("automationName", ConfigLoader.getProperty("saucelabs.android.automationName", "UiAutomator2"));
            capabilities.setProperty("app", ConfigLoader.getProperty("saucelabs.android.app", "sauce-storage:android.apk"));
            capabilities.setProperty("appPackage", ConfigLoader.getProperty("saucelabs.android.appPackage", "com.app.swiftEx.app"));
            
            // SauceLabs specific capabilities as individual properties (not as JSON)
            capabilities.setProperty("username", ConfigLoader.getSauceLabsUsername());
            capabilities.setProperty("accessKey", ConfigLoader.getSauceLabsAccessKey());
            
            // Test metadata
            String testName = System.getProperty("sauce.test.name", 
                ConfigLoader.getProperty("saucelabs.android.name", "SwiftEx Android Test"));
            capabilities.setProperty("name", testName);
            
            String buildName = System.getProperty("sauce.build.name", 
                "SwiftEx-Build-" + System.currentTimeMillis());
            capabilities.setProperty("build", buildName);
            
            // Tags as comma-separated string
            String tags = System.getProperty("sauce.tags", "swiftex,mobile,automation,android");
            capabilities.setProperty("tags", tags);
            
            // Optional Android SauceLabs properties
            addOptionalProperty(capabilities, "appWaitActivity", "saucelabs.android.appWaitActivity", null);
            addOptionalProperty(capabilities, "appActivity", "saucelabs.android.appActivity", null);
            
        } else if (IOS_SAUCE.equals(platform)) {
            // SauceLabs iOS configuration
            capabilities.setProperty("platformName", ConfigLoader.getProperty("saucelabs.ios.platformName", "iOS"));
            capabilities.setProperty("deviceName", ConfigLoader.getProperty("saucelabs.ios.deviceName", "iPhone 14 Simulator"));
            capabilities.setProperty("platformVersion", ConfigLoader.getProperty("saucelabs.ios.platformVersion", "16.2"));
            capabilities.setProperty("automationName", ConfigLoader.getProperty("saucelabs.ios.automationName", "XCUITest"));
            capabilities.setProperty("app", ConfigLoader.getProperty("saucelabs.ios.app", "sauce-storage:SwiftExApp.app"));
            capabilities.setProperty("bundleId", ConfigLoader.getProperty("saucelabs.ios.bundleId", "com.app.swiftEx.ios"));
            
            // SauceLabs specific capabilities as individual properties (not as JSON)
            capabilities.setProperty("username", ConfigLoader.getSauceLabsUsername());
            capabilities.setProperty("accessKey", ConfigLoader.getSauceLabsAccessKey());
            
            // Test metadata
            String testName = System.getProperty("sauce.test.name", 
                ConfigLoader.getProperty("saucelabs.ios.name", "SwiftEx iOS Test"));
            capabilities.setProperty("name", testName);
            
            String buildName = System.getProperty("sauce.build.name", 
                "SwiftEx-Build-" + System.currentTimeMillis());
            capabilities.setProperty("build", buildName);
            
            // Tags as comma-separated string
            String tags = System.getProperty("sauce.tags", "swiftex,mobile,automation,ios");
            capabilities.setProperty("tags", tags);
        }
        
        // Common capabilities
        addOptionalProperty(capabilities, "newCommandTimeout", "appium.timeout", "60");
        
        // Reset capabilities - different for SauceLabs
        if (ANDROID_SAUCE.equals(platform) || IOS_SAUCE.equals(platform)) {
            // SauceLabs doesn't always support fullReset the same way
            addOptionalProperty(capabilities, "noReset", "saucelabs.no.reset", "false");
        } else {
            // Local execution
            addOptionalProperty(capabilities, "fullReset", "app.full.reset", "true");
            addOptionalProperty(capabilities, "noReset", "app.no.reset", "false");
        }
        
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
        logger.info("Base Platform: {}", getBasePlatform().toUpperCase());
        logger.info("Execution Location: {}", isSauceLabs() ? "SauceLabs Cloud" : "Local");
        logger.info("App Path: {}", getAppPath());
        logger.info("Project Root: {}", System.getProperty("user.dir"));
        
        if (isSauceLabs()) {
            logger.info("SauceLabs Hub URL: {}", ConfigLoader.getSauceLabsUrl());
            logger.info("SauceLabs Username: {}", ConfigLoader.getSauceLabsUsername());
        }
        
        Properties capabilities = getPlatformCapabilities();
        logger.info("Platform Capabilities:");
        capabilities.forEach((key, value) -> {
            // Hide sensitive information
            if (key.toString().toLowerCase().contains("accesskey") || 
                key.toString().toLowerCase().contains("sauce:options")) {
                logger.info("  {}: [HIDDEN]", key);
            } else {
                logger.info("  {}: {}", key, value);
            }
        });
        
        logger.info("=== CLI Parameters Available ===");
        logger.info("Platform switching:");
        logger.info("  -Dplatform=android|ios|androidSauce|iOSSauce");
        logger.info("Local app path configuration:");
        logger.info("  -Dapp.path=/path/to/app");
        logger.info("  -Dandroid.app.path=/path/to/android.apk");
        logger.info("  -Dios.app.path=/path/to/ios.app");
        logger.info("SauceLabs configuration:");
        logger.info("  -Dsaucelabs.username=YOUR_USERNAME");
        logger.info("  -Dsaucelabs.accessKey=YOUR_ACCESS_KEY");
        logger.info("  -Dsauce.test.name='Custom Test Name'");
        logger.info("  -Dsauce.build.name='Custom Build Name'");
        logger.info("  -Dsauce.tags=tag1,tag2,tag3");
        logger.info("Device configuration:");
        logger.info("  -Dandroid.device.name='Device Name'");
        logger.info("  -Dandroid.version='12'");
        logger.info("  -Dios.device.name='iPhone Simulator'");
        logger.info("  -Dios.version='15.0'");
        logger.info("========================================");
    }
    
    /**
     * Check if current platform is Android (local or SauceLabs)
     */
    public static boolean isAndroid() {
        String platform = getCurrentPlatform();
        return ANDROID.equals(platform) || ANDROID_SAUCE.equals(platform);
    }
    
    /**
     * Check if current platform is iOS (local or SauceLabs)
     */
    public static boolean isIOS() {
        String platform = getCurrentPlatform();
        return IOS.equals(platform) || IOS_SAUCE.equals(platform);
    }
    
    /**
     * Check if current platform is running on SauceLabs
     */
    public static boolean isSauceLabs() {
        String platform = getCurrentPlatform();
        return ANDROID_SAUCE.equals(platform) || IOS_SAUCE.equals(platform);
    }
    
    /**
     * Get base platform type (android/ios) regardless of execution location
     */
    public static String getBasePlatform() {
        String platform = getCurrentPlatform();
        if (ANDROID_SAUCE.equals(platform)) {
            return ANDROID;
        } else if (IOS_SAUCE.equals(platform)) {
            return IOS;
        }
        return platform;
    }
    
    /**
     * Create SauceLabs sauce:options map for proper W3C compliance
     */
    private static java.util.Map<String, Object> createSauceOptionsMap(String platformType) {
        return createSauceOptionsMap(platformType, null);
    }
    
    /**
     * Create SauceLabs sauce:options map with custom test name
     */
    private static java.util.Map<String, Object> createSauceOptionsMap(String platformType, String testName) {
        java.util.Map<String, Object> sauceOptions = new java.util.HashMap<>();
        
        // Authentication
        sauceOptions.put("username", ConfigLoader.getSauceLabsUsername());
        sauceOptions.put("accessKey", ConfigLoader.getSauceLabsAccessKey());
        
        // Test metadata - use provided test name or generate default
        String finalTestName;
        if (testName != null && !testName.trim().isEmpty()) {
            finalTestName = testName;
        } else {
            finalTestName = System.getProperty("sauce.test.name", 
                "SwiftEx " + platformType.toUpperCase() + " Test - " + 
                java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        sauceOptions.put("name", finalTestName);
        
        // Build name
        String buildName = System.getProperty("sauce.build.name", "SwiftEx-Build-" + System.currentTimeMillis());
        sauceOptions.put("build", buildName);
        
        // Tags as array
        String tagsString = System.getProperty("sauce.tags", "swiftex,mobile,automation," + platformType);
        java.util.List<String> tagsList = java.util.Arrays.asList(tagsString.split(","));
        sauceOptions.put("tags", tagsList);
        
        return sauceOptions;
    }
    
    /**
     * Get SauceLabs sauce:options capability as a Map (for proper W3C compliance)
     * This should be called separately and added to DesiredCapabilities directly
     */
    public static java.util.Map<String, Object> getSauceOptions() {
        return getSauceOptions(null);
    }
    
    /**
     * Get SauceLabs sauce:options capability with custom test name
     */
    public static java.util.Map<String, Object> getSauceOptions(String testName) {
        String platform = getCurrentPlatform();
        if (ANDROID_SAUCE.equals(platform)) {
            return createSauceOptionsMap("android", testName);
        } else if (IOS_SAUCE.equals(platform)) {
            return createSauceOptionsMap("ios", testName);
        }
        return null; // Not a SauceLabs platform
    }
}
