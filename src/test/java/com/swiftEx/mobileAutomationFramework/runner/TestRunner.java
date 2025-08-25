package com.swiftEx.mobileAutomationFramework.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME, value = "com.swiftEx.mobileAutomationFramework.steps")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "pretty,html:target/cucumber-html-report.html,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class TestRunner {
}

