package com.swiftEx.mobileAutomationFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swiftEx.mobileAutomationFramework.utils.LocatorUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public HomePage(AppiumDriver driver) {
        super(driver, "HomePage.yaml");
        logger.info("✅ HomePage ready for platform: {}", getPlatform().toUpperCase());
    }

    // Action to click on Settings tab
    public void clickSettingsTab() throws InterruptedException {
        Thread.sleep(5000);
        tap("settings_tab");
        logger.info("Clicked Settings tab in bottom navigation");
    }

    // Verifications for Settings page elements
    public boolean isSettingsHeaderDisplayed() {
        boolean displayed = isDisplayed("settings_header");
        logger.info("Settings header visibility: {}", displayed);
        return displayed;
    }

    public boolean isChooseWalletOptionDisplayed() {
        boolean displayed = isDisplayed("choose_wallet_option");
        logger.info("Choose Wallet option visibility: {}", displayed);
        return displayed;
    }

    public boolean isDarkModeOptionDisplayed() {
        boolean displayed = isDisplayed("dark_mode_option");
        logger.info("Dark Mode option visibility: {}", displayed);
        return displayed;
    }

    public boolean isExchangeOptionDisplayed() {
        boolean displayed = isDisplayed("exchange_option");
        logger.info("Exchange option visibility: {}", displayed);
        return displayed;
    }

    public boolean isTransactionsOptionDisplayed() {
        boolean displayed = isDisplayed("transactions_option");
        logger.info("Transactions option visibility: {}", displayed);
        return displayed;
    }

    public boolean isBiometricAuthenticationOptionDisplayed() {
        boolean displayed = isDisplayed("biometric_authentication_option");
        logger.info("Biometric Authentication option visibility: {}", displayed);
        return displayed;
    }

    public boolean isPreferenceOptionDisplayed() {
        boolean displayed = isDisplayed("preference_option");
        logger.info("Preference option visibility: {}", displayed);
        return displayed;
    }

    public boolean isPushNotificationOptionDisplayed() {
        boolean displayed = isDisplayed("push_notification_option");
        logger.info("Push Notification option visibility: {}", displayed);
        return displayed;
    }

    public boolean isHelpCenterOptionDisplayed() {
        boolean scroll = scrollDownUntilVisible("logout_option", 0);
        boolean displayed = isDisplayed("help_center_option");

        logger.info("Help Center option visibility: {}", displayed);
        return displayed;
    }

  public boolean isLogoutOptionDisplayed() {
    logger.info("Scrolling to find Logout option");
    
    try {
        // Use UiScrollable to scroll and find the element
        By logoutLocator = AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().textContains(\"Log Out\"))"
        );
        
        // Wait for element to be visible after scrolling
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        WebElement logoutElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLocator));
        
        boolean displayed = logoutElement.isDisplayed();
        logger.info("Logout option displayed after scrolling: {}", displayed);
        return displayed;
        
    } catch (Exception e) {
        logger.error("Failed to find Logout option after scrolling: {}", e.getMessage());
        return false;
    }
}

    // Balance Section
    public String getBalanceAmount() {
        String balance = getText("balance_amount");
        logger.info("Retrieved balance amount: {}", balance);
        return balance;
    }

    public String getBalanceCurrency() {
        String currency = getText("balance_currency");
        logger.info("Retrieved balance currency: {}", currency);
        return currency;
    }

    // Action Methods
    public void clickReceive() {
        tap("receive_button");
        logger.info("Clicked receive button");
    }

    public void clickSend() {
        tap("send_button");
        logger.info("Clicked send button");
    }

    public void clickSwap() {
        tap("swap_button");
        logger.info("Clicked swap button");
    }

    public void clickBuy() {
        tap("buy_button");
        logger.info("Clicked buy button");
    }

    // Tab Navigation Methods
    public void clickAssetsTab() {
        tap("assets_tab");
        logger.info("Clicked assets tab");
    }

    public void clickAddAssetsTab() {
        tap("add_assets_tab");
        logger.info("Clicked add assets tab");
    }

    // Bottom Navigation Methods
    public void navigateToHome() {
        tap("home_tab");
        logger.info("Navigated to home tab");
    }

    public void navigateToWallet() {
        tap("wallet_tab");
        logger.info("Navigated to wallet tab");
    }

    public void navigateToMarket() {
        tap("market_tab");
        logger.info("Navigated to market tab");
    }

    public void navigateToExchange() {
        tap("exchange_tab");
        logger.info("Navigated to exchange tab");
    }

    public void navigateToSettings() {
        tap("settings_tab");
        logger.info("Navigated to settings tab");
    }

    // Asset List Methods
    public void selectBitcoinAsset() {
        tap("bitcoin_asset");
        logger.info("Selected Bitcoin asset");
    }

    public void selectUsdcAsset() {
        tap("usdc_asset");
        logger.info("Selected USDC asset");
    }

    public void selectUsdtAsset() {
        tap("usdt_asset");
        logger.info("Selected USDT asset");
    }

    public void selectXLMAsset() {
        tap("xlm_item");
        logger.info("Selected XLM asset");
    }

    public void selectBinanceAsset() {
        tap("binance_item");
        logger.info("Selected Binance asset");
    }

    // Asset Action Methods
    public void clickTradeOnAsset() {
        tap("trade_button");
        logger.info("Clicked trade button on asset");
    }

    public void clickBuyOnAsset() {
        tap("asset_buy_button");
        logger.info("Clicked buy button on asset");
    }

    // Verification Methods
    public boolean isOnHomePage() {
        logger.info("🏠 Verifying if on home page");
        return isDisplayed("home_tab");
    }

    public boolean isAssetVisible(String assetName) {
        String assetLocatorKey = assetName.toLowerCase() + "_item";
        boolean visible = isDisplayed(assetLocatorKey);
        logger.info("Asset '{}' visibility: {}", assetName, visible);
        return visible;
    }

    // Additional Balance Section Validation Methods
    public boolean isBalanceAmountDisplayed() {
        boolean displayed = isDisplayed("balance_amount");
        logger.info("Balance amount visibility: {}", displayed);
        return displayed;
    }

    public boolean isBalanceCurrencyDisplayed() {
        boolean displayed = isDisplayed("balance_currency");
        logger.info("Balance currency visibility: {}", displayed);
        return displayed;
    }

    public boolean isBalanceSectionComplete() {
        boolean amountVisible = isBalanceAmountDisplayed();
        boolean currencyVisible = isBalanceCurrencyDisplayed();
        boolean complete = amountVisible && currencyVisible;
        logger.info("Balance section complete: {} (Amount: {}, Currency: {})", complete, amountVisible,
                currencyVisible);
        return complete;
    }

    // Action Buttons Validation Methods
    public boolean isReceiveButtonDisplayed() {
        boolean displayed = isDisplayed("receive_button");
        logger.info("Receive button visibility: {}", displayed);
        return displayed;
    }

    public boolean isSendButtonDisplayed() {
        boolean displayed = isDisplayed("send_button");
        logger.info("Send button visibility: {}", displayed);
        return displayed;
    }

    public boolean isSwapButtonDisplayed() {
        boolean displayed = isDisplayed("swap_button");
        logger.info("Swap button visibility: {}", displayed);
        return displayed;
    }

    public boolean isBuyButtonDisplayed() {
        boolean displayed = isDisplayed("buy_button");
        logger.info("Buy button visibility: {}", displayed);
        return displayed;
    }

    public boolean areAllActionButtonsDisplayed() {
        boolean receiveVisible = isReceiveButtonDisplayed();
        boolean sendVisible = isSendButtonDisplayed();
        boolean swapVisible = isSwapButtonDisplayed();
        boolean buyVisible = isBuyButtonDisplayed();
        boolean allVisible = receiveVisible && sendVisible && swapVisible && buyVisible;
        logger.info("All action buttons displayed: {} (Receive: {}, Send: {}, Swap: {}, Buy: {})",
                allVisible, receiveVisible, sendVisible, swapVisible, buyVisible);
        return allVisible;
    }

    // Asset Tab Validation Methods
    public boolean isAssetsTabDisplayed() {
        boolean displayed = isDisplayed("assets_tab");
        logger.info("Assets tab visibility: {}", displayed);
        return displayed;
    }

    public boolean isAddAssetsTabDisplayed() {
        boolean displayed = isDisplayed("add_assets_tab");
        logger.info("Add assets tab visibility: {}", displayed);
        return displayed;
    }

    public boolean areAssetTabsDisplayed() {
        boolean assetsVisible = isAssetsTabDisplayed();
        boolean addAssetsVisible = isAddAssetsTabDisplayed();
        boolean bothVisible = assetsVisible && addAssetsVisible;
        logger.info("Asset tabs displayed: {} (Assets: {}, Add Assets: {})", bothVisible, assetsVisible,
                addAssetsVisible);
        return bothVisible;
    }

    // Bottom Navigation Validation Methods
    public boolean isHomeTabDisplayed() {
        boolean displayed = isDisplayed("home_tab");
        logger.info("Home tab visibility: {}", displayed);
        return displayed;
    }

    public boolean isWalletTabDisplayed() {
        boolean displayed = isDisplayed("wallet_tab");
        logger.info("Wallet tab visibility: {}", displayed);
        return displayed;
    }

    public void tapWalletTab() {
        tap("wallet_tab");
    }

    public boolean isMarketTabDisplayed() {
        boolean displayed = isDisplayed("market_tab");
        logger.info("Market tab visibility: {}", displayed);
        return displayed;
    }

    public void tapMarketTab() {
        tap("market_tab");
    }

    public boolean isExchangeTabDisplayed() {
        boolean displayed = isDisplayed("exchange_tab");
        logger.info("Exchange tab visibility: {}", displayed);
        return displayed;
    }

    public boolean isSettingsTabDisplayed() {
        boolean displayed = isDisplayed("settings_tab");
        logger.info("Settings tab visibility: {}", displayed);
        return displayed;
    }

    public boolean isBottomNavigationComplete() {
        boolean homeVisible = isHomeTabDisplayed();
        boolean walletVisible = isWalletTabDisplayed();
        boolean marketVisible = isMarketTabDisplayed();
        boolean exchangeVisible = isExchangeTabDisplayed();
        boolean settingsVisible = isSettingsTabDisplayed();
        boolean allVisible = homeVisible && walletVisible && marketVisible && exchangeVisible && settingsVisible;
        logger.info("Bottom navigation complete: {} (Home: {}, Wallet: {}, Market: {}, Exchange: {}, Settings: {})",
                allVisible, homeVisible, walletVisible, marketVisible, exchangeVisible, settingsVisible);
        return allVisible;
    }

    // Asset Action Buttons Validation Methods
    public boolean isTradeButtonDisplayed() {
        boolean displayed = isDisplayed("trade_button");
        logger.info("Trade button visibility: {}", displayed);
        return displayed;
    }

    public boolean isAssetBuyButtonDisplayed() {
        boolean displayed = isDisplayed("asset_buy_button");
        logger.info("Asset buy button visibility: {}", displayed);
        return displayed;
    }

    public boolean areAssetActionButtonsDisplayed() {
        boolean tradeVisible = isTradeButtonDisplayed();
        boolean buyVisible = isAssetBuyButtonDisplayed();
        boolean bothVisible = tradeVisible && buyVisible;
        logger.info("Asset action buttons displayed: {} (Trade: {}, Buy: {})", bothVisible, tradeVisible, buyVisible);
        return bothVisible;
    }

    // Asset List Validation Methods
    public boolean isBitcoinAssetDisplayed() {
        boolean displayed = isDisplayed("bitcoin_asset");
        logger.info("Bitcoin asset visibility: {}", displayed);
        return displayed;
    }

    public boolean isUsdcAssetDisplayed() {
        boolean displayed = isDisplayed("usdc_asset");
        logger.info("USDC asset visibility: {}", displayed);
        return displayed;
    }

    public boolean isUsdtAssetDisplayed() {
        boolean displayed = isDisplayed("usdt_asset");
        logger.info("USDT asset visibility: {}", displayed);
        return displayed;
    }

    public boolean areMainAssetsDisplayed() {
        boolean bitcoinVisible = isBitcoinAssetDisplayed();
        boolean usdcVisible = isUsdcAssetDisplayed();
        boolean usdtVisible = isUsdtAssetDisplayed();
        boolean allVisible = bitcoinVisible && usdcVisible && usdtVisible;
        logger.info("Main assets displayed: {} (Bitcoin: {}, USDC: {}, USDT: {})", allVisible, bitcoinVisible,
                usdcVisible, usdtVisible);
        return allVisible;
    }

    // Comprehensive Page Validation Methods
    public boolean isHomePageFullyLoaded() {
        boolean balanceSection = isBalanceSectionComplete();
        boolean actionButtons = areAllActionButtonsDisplayed();
        boolean assetTabs = areAssetTabsDisplayed();
        boolean bottomNav = isBottomNavigationComplete();
        boolean mainAssets = areMainAssetsDisplayed();
        boolean fullyLoaded = balanceSection && actionButtons && assetTabs && bottomNav && mainAssets;

        logger.info("HomePage fully loaded: {} (Balance: {}, Actions: {}, Asset Tabs: {}, Bottom Nav: {}, Assets: {})",
                fullyLoaded, balanceSection, actionButtons, assetTabs, bottomNav, mainAssets);
        return fullyLoaded;
    }

    public void validateHomePageElements() {
        logger.info("🔍 Starting comprehensive HomePage validation");

        // Validate each section
        isBalanceSectionComplete();
        areAllActionButtonsDisplayed();
        areAssetTabsDisplayed();
        isBottomNavigationComplete();
        areMainAssetsDisplayed();

        // Check if asset action buttons are available (may not always be visible)
        if (isElementPresent("trade_button")) {
            areAssetActionButtonsDisplayed();
        }

        boolean pageValid = isHomePageFullyLoaded();
        logger.info("✅ HomePage validation completed. Page valid: {}", pageValid);
    }

    // Helper method to check element presence without logging
    private boolean isElementPresent(String locatorKey) {
        try {
            return isDisplayed(locatorKey);
        } catch (Exception e) {
            return false;
        }
    }

    // Click Exchange tab in bottom navigation
    public void clickExchangeTab() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        WebElement exchangeTab = wait.until(ExpectedConditions.elementToBeClickable(getBy("exchange_tab")));
        exchangeTab.click();
        logger.info("Clicked Exchange tab in bottom navigation");
    }

    // Verifications for Exchange page elements
    public boolean isTradeWalletHeaderDisplayed() {
        boolean displayed = isDisplayed(getBy("trade_wallet_header"), 30);
        logger.info("Trade Wallet header visibility: {}", displayed);
        return displayed;
    }

    public boolean isHomeIconDisplayed() {
        boolean displayed = isDisplayed("home_icon");
        logger.info("Home icon visibility: {}", displayed);
        return displayed;
    }

    public boolean isOffersIconDisplayed() {
        boolean displayed = isDisplayed("offers_icon");
        logger.info("Offers icon visibility: {}", displayed);
        return displayed;
    }

    public boolean isTransactionsIconDisplayed() {
        boolean displayed = isDisplayed("transactions_icon");
        logger.info("Transactions icon visibility: {}", displayed);
        return displayed;
    }

    public boolean isOnOffRampIconDisplayed() {
        boolean displayed = isDisplayed("on_off_ramp_icon");
        logger.info("On/Off Ramp icon visibility: {}", displayed);
        return displayed;
    }

    public boolean isProfileIconDisplayed() {
        return isDisplayed(LocatorUtils.getUIAutomatorTextLocatorBy("Profile"), 20);
    }

    // Action to click on back button
    public void clickBackButton() {
        tap("back_button");
        logger.info("Clicked back button");
    }

    // Click the Receive button
    public void clickReceiveButton() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        WebElement receiveBtn = wait.until(ExpectedConditions.elementToBeClickable(getBy("receive_button")));
        receiveBtn.click();
        logger.info("Clicked Receive button");
    }

    // Verify Receive screen is displayed (can be customized with a header or unique element)
    public boolean isOnReceiveScreen() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            WebElement receiveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(getBy("receive_button")));
            boolean displayed = receiveBtn.isDisplayed();
            logger.info("Receive screen displayed: {}", displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Receive screen not displayed: {}", e.getMessage());
            return false;
        }
    }

    // Click the Home tab
    public void tapHomeTab() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        WebElement homeTab = wait.until(ExpectedConditions.elementToBeClickable(getBy("home_tab")));
        homeTab.click();
        logger.info("Clicked Home tab");
    }

    // Click Back button of Trade Wallet Page
    public void clickTradeWalletBackButton() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
        WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(getBy("TradeWallet_back_button")));
        backBtn.click();
        logger.info("Clicked Back button of Trade Wallet Page");
    }

    public void tapBuyButton() {
        tap("buy_button");
    }

    // isBuyHeaderDisplayed
    public boolean isBuyHeaderDisplayed() {
        boolean displayed = isDisplayed(getBy("buy_button"), 30);
        logger.info("Buy header visibility: {}", displayed);
        return displayed;
    }

    // Verifications for asset options on HomePage
    public boolean isEthereumOptionDisplayed() {
        boolean displayed = isDisplayed("ethereum");
        logger.info("Ethereum option visibility: {}", displayed);
        return displayed;
    }

    public boolean isXLMOptionDisplayed() {
        boolean displayed = isDisplayed("xlm");
        logger.info("XLM option visibility: {}", displayed);
        return displayed;
    }

    public boolean isBinanceOptionDisplayed() {
        boolean displayed = isDisplayed("binance");
        logger.info("Binance option visibility: {}", displayed);
        return displayed;
    }

    public boolean isBitcoinOptionDisplayed() {
        boolean displayed = isDisplayed("bitcoin");
        logger.info("Bitcoin option visibility: {}", displayed);
        return displayed;
    }

    public boolean isEthereumTradeButtonDisplayed() {
        boolean displayed = isDisplayed("ethereum_trade_button");
        logger.info("Ethereum Trade button visibility: {}", displayed);
        return displayed;
    }

    public boolean isEthereumBuyButtonDisplayed() {
        boolean displayed = isDisplayed("ethereum_buy_button");
        logger.info("Ethereum Buy button visibility: {}", displayed);
        return displayed;
    }

    public boolean isXLMTradeButtonDisplayed() {
        boolean displayed = isDisplayed("xlm_trade_button");
        logger.info("XLM Trade button visibility: {}", displayed);
        return displayed;
    }

    public boolean isXLMBuyButtonDisplayed() {
        boolean displayed = isDisplayed("xlm_buy_button");
        logger.info("XLM Buy button visibility: {}", displayed);
        return displayed;
    }

    public boolean isBinanceTradeButtonDisplayed() {
        boolean displayed = isDisplayed("binance_trade_button");
        logger.info("Binance Trade button visibility: {}", displayed);
        return displayed;
    }

    public boolean isBinanceBuyButtonDisplayed() {
        boolean displayed = isDisplayed("binance_buy_button");
        logger.info("Binance Buy button visibility: {}", displayed);
        return displayed;
    }

    public boolean isBitcoinArrivingSoonButtonDisplayed() {
        boolean displayed = isDisplayed("bitcoin_arrivingsoon_button");
        logger.info("Bitcoin Arriving soon button visibility: {}", displayed);
        return displayed;
    }

    public void clickOnAddAssetsTab() {
        tap("add_assets_tab");
        logger.info("Clicked on Add Assets tab");
    }

    public boolean isUSDCOptionDisplayed() {
        boolean displayed = isDisplayed("usdc_asset");
        logger.info("USDC option visibility: {}", displayed);
        return displayed;
    }

    public void clickOnAssetsTab() {
        tap("assets_tab");
        logger.info("Clicked on Assets tab");
    }

    public void tapOnEthereumBuyButton() {
        tap("ethereum_buy_button");
        logger.info("Tapped on Buy button for Ethereum");
    }

    public boolean isDepositWithdrawalHeaderDisplayed() {
        boolean displayed = isDisplayed("buy_page_header");
        logger.info("Deposit/Withdrawal header visibility: {}", displayed);
        return displayed;
    }

    public void tapEyeballIcon() {
        tap("eyeball_icon");
        logger.info("Tapped on eyeball icon");
    }

    public boolean isWalletBalanceHidden() {
        boolean hidden = isDisplayed("wallet_balance_hidden");
        logger.info("Wallet balance hidden: {}", hidden);
        return hidden;
    }

    public boolean isWalletBalanceVisible() {
        boolean visible = isDisplayed("wallet_balance_visible");
        logger.info("Wallet balance visible: {}", visible);
        return visible;
    }

    public void tapDropdownIcon() {
        tap("dropdown_icon");
        logger.info("Tapped on dropdown icon next to wallet name");
    }

    public boolean isChooseWalletHeaderDisplayed() {
        boolean displayed = isDisplayed("choose_wallet_header");
        logger.info("Choose wallet header displayed: {}", displayed);
        return displayed;
    }

    public boolean isAddWalletOptionDisplayed() {
        boolean displayed = isDisplayed("Add_Wallet_button");
        logger.info("Add Wallet option displayed in dropdown: {}", displayed);
        return displayed;
    }

    public boolean areAllWalletsPresentInDropdown() {
        boolean displayed = isDisplayed("all_wallets_in_dropdown");
        logger.info("All wallets option displayed in dropdown: {}", displayed);
        return displayed;
    }

    public void tapAddWalletOption() {
        tap("Add_Wallet_button");
        logger.info("Tapped on Add Wallet option");
    }
        // Clicks the Swap button specifically on the Swap page
    public void clickRandomCoordinate() throws InterruptedException {
        tapOnCoordinates(driver, 200, 500);
        logger.info("Clicked random coordinate on Swap page via coordinates");
    }

}