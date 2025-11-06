package com.swiftEx.mobileAutomationFramework.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllureUtils {
    private static final Logger logger = LoggerFactory.getLogger(AllureUtils.class);

    /**
     * Attach screenshot to Allure report
     */
    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(Object driverObj, String name) {
        try {
            if (driverObj instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driverObj).getScreenshotAs(OutputType.BYTES);
                logger.info("INFO: Screenshot attached to Allure report: {}", name);
                return screenshot;
            }
        } catch (Exception e) {
            logger.warn("Failed to capture screenshot for Allure: {}", e.getMessage());
        }
        return new byte[0];
    }

    /**
     * Add text attachment to Allure report
     */
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        logger.info("INFO: Text attachment added to Allure report: {}", name);
        return content;
    }

    /**
     * Add step to Allure report
     */
    @Step("{stepName}")
    public static void addStep(String stepName) {
        logger.info("INFO: Allure step: {}", stepName);
    }

    /**
     * Add step with parameter to Allure report
     */
    @Step("{stepName}: {parameter}")
    public static void addStepWithParameter(String stepName, String parameter) {
        logger.info("INFO: Allure step: {} - {}", stepName, parameter);
    }

    /**
     * Add environment information to Allure
     */
    public static void addEnvironmentInfo(String key, String value) {
        try {
            // This will be displayed in Allure report environment section
            Allure.addAttachment("Environment: " + key, value);
            logger.info("🌍 Environment info added: {} = {}", key, value);
        } catch (Exception e) {
            logger.warn("Failed to add environment info: {}", e.getMessage());
        }
    }
}
