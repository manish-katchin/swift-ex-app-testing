package com.swiftEx.mobileAutomationFramework.pages;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketScreenPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(WalletScreenPage.class);

    public MarketScreenPage(AppiumDriver driver) {
        super(driver, "MarketScreenPage.yaml");
        logger.info("MarketPage ready for platform: {}", getPlatform().toUpperCase());
    }

    public boolean isBitcoinAssetDisplayed() {
        return isDisplayed("bitcoin_asset");
    }

    public boolean isMarketScreenDisplayed() {
        return isDisplayed("market_header_title");
    }

    
}
