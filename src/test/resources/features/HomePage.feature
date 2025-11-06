@home
Feature: Home Page

  
  @VerifyActiveWalletIndicator
  Scenario: Verify that the "Activate ID" popup appears after creating a new wallet.
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup


@VerifyReceivePageNavigation
Scenario: Verify clicking Receive navigates to the receive screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should see the Receive button is displayed
When I click the Receive button 
Then I should be on the Receive screen 

@VerifyCryptoOptionsDisplayed
Scenario: Verify Ethereum, XLM, Binance, and Bitcoin are displayed with correct options.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see Ethereum option on homePage
And I should see XLM option on homePage
And I should see Binance option on homePage
And I should see Bitcoin option on homePage
And I should see Trade button for ethereum 
And I should see buy button for ethereum
And I should see Trade button for xlm 
And I should see buy button for xlm
And I should see Trade button for binance 
And I should see buy button for binance
And I should see Arriving soon button for bitcoin


@VerifyAssetTokenTabSwitch
Scenario: Verify Assets and Tokens tabs switch correctly
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see Ethereum option on homePage
And I should see XLM option on homePage
And I should see Binance option on homePage
And I should see Bitcoin option on homePage
And I should see Trade button for ethereum 
When I click on the Add Assets tab
Then I should see the USDC option
When I click on the Assets tab
And I should see Ethereum option on homePage
And I should see XLM option on homePage
And I should see Binance option on homePage
And I should see Bitcoin option on homePage
And I should see Trade button for ethereum

@VerifyBuyPageNavigationforEthereum
Scenario: Verify clicking Buy navigates to the buy screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see Ethereum option on homePage
And I should see XLM option on homePage
And I should see Binance option on homePage
And I should see Bitcoin option on homePage
And I should see Trade button for ethereum 
And I should see buy button for ethereum
When I click on the Buy button for ethereum
Then I should see Deposit/Withdrawal header

@VerifyBalanceVisibilityToggle
Scenario: Verify clicking the Eye Icon toggles balance visibility.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I click on eye ball icon 
Then I should see the wallet balance is hidden
Then I click on eye ball icon
Then I should see the wallet balance is visible

@VerifyClickingWalletNameOpensDropdown
Scenario: Verify clicking wallet name opens the wallet selection dropdown.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see wallet name "Main" displayed
Then I click on dropdown icon next to wallet name
Then I should see choose wallet header

@VerifyAddWalletOptionInDropdown
Scenario: Verify Add Wallet option is present in the dropdown.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see wallet name "Main" displayed
Then I click on dropdown icon next to wallet name
Then I should see choose wallet header
Then I should see Add Wallet option in the dropdown
And I should see All Present Wallets in the dropdown


@VerifyAllWalletListed
Scenario: Verify all wallets are listed in the dropdown.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see wallet name "Main" displayed
Then I click on dropdown icon next to wallet name
Then I should see choose wallet header
Then I should see Add Wallet option in the dropdown
And I should see All Present Wallets in the dropdown


@VerifyActiveWalletIndicatoronSelectedWalletinDropdown
Scenario: Verify currently active wallet is highlighted with "Active" and a tick.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see wallet name "Main" displayed
Then I click on dropdown icon next to wallet name
Then I should see choose wallet header
Then I should see Add Wallet option in the dropdown
Then I should see wallet "Main" has active wallet indication

@VerifySwitchingWalletUpdatesUI
Scenario: Verify switching wallets updates the UI accordingly
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I click on Wallet tab
Then I see My Wallet option  
And I see Create Wallet option
And I see Import Wallet option   
And I see Choose Wallet option
Then I verify "Import Wallet" button on screen
And I click on "Import" button
When I click on "Multi-Chain Wallet" 
Then I should see "Multi-Chain Wallet" header
When I enter name "Sam" in the input field
And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
And I click on "Import" button
Then I should see "All Wallets" header on the screen
Then I click "Back Button" on All Wallets screen
Then I click on Home Tab
Then I should see wallet name "Sam" displayed
Then I click on dropdown icon next to wallet name
Then I should see wallet "Sam" has active wallet indication
And I select the wallet named "Main"
Then I should see wallet name "Main" displayed

@VerifyAddWalletNavigation
Scenario: Verify clicking Add Wallet navigates to the add wallet screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see wallet name "Main" displayed
Then I click on dropdown icon next to wallet name
Then I should see choose wallet header
Then I should see Add Wallet option in the dropdown
And I tap on Add Wallet option
And I see Create Wallet option


@VerifydropdownClosesOnClickingOutside
Scenario: Verify dropdown closes when clicking outside
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see wallet name "Main" displayed
Then I click on dropdown icon next to wallet name
Then I should see choose wallet header
Then I should see Add Wallet option in the dropdown
And I click randomly outside the dropdown
And I should see the Home icon

@VerifyBalanceHiddenOnAppSwitch
Scenario: Verify clicking back does not briefly reveal balance.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see the wallet balance is hidden
And I click on Wallet tab
Then I click on Home Tab
Then I should see the wallet balance is hidden


@VerifysmoothTransitionBetweenAssetsAndTokensTabs
Scenario: Verify smooth transition between Assets and Tokens tabs.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see Ethereum option on homePage
And I should see XLM option on homePage
And I should see Binance option on homePage
And I should see Bitcoin option on homePage
And I should see Trade button for ethereum 
When I click on the Add Assets tab
Then I should see the USDC option
When I click on the Assets tab
And I should see Ethereum option on homePage
And I should see XLM option on homePage
When I click on the Add Assets tab
Then I should see the USDC option
When I click on the Assets tab
And I should see Ethereum option on homePage
And I should see XLM option on homePage

# TO check Smooth Navigation on homePage
@VerifyHomeTabNavigationToWalletPage
Scenario: Verify navigation from Home to Wallet and back to Home
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I click on Wallet tab
Then I see My Wallet option  
And I see Create Wallet option
And I see Import Wallet option   
And I see Choose Wallet option
Then I click on Home Tab
Then I should be on homePage
Then I should see the Receive button is displayed    
And I should see the Send button is displayed
And I should see the Swap button is displayed
And I should see the Buy button is displayed


@VerifyHomeTabNavigationToMarketPage
Scenario: Verify navigation from Home to Market 
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click on Market tab
Then I see market page header
And I see bitcoin option on market page
Then I click on Home Tab
Then I should be on homePage
Then I should see the Receive button is displayed   
When I click on Market tab
Then I see market page header
And I see bitcoin option on market page
Then I click on Home Tab
Then I should be on homePage
Then I should see the Receive button is displayed
When I click on Market tab
Then I see market page header
And I see bitcoin option on market page   


@VerifyHomeTabNavigationToExchangePage
Scenario: Verify navigation from Home to Exchange
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
Then I should see the Receive button is displayed
And I should see the Send button is displayed
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
Then I click Back button of trade Wallet Page
Then I should be on homePage
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
Then I click Back button of trade Wallet Page
Then I should be on homePage


@VerifyWalletTabNavigationToExchangePage
Scenario: Verify navigation from Wallet to Exchange Page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I click on Wallet tab
Then I see My Wallet option  
And I see Create Wallet option
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
Then I click Back button of trade Wallet Page
And I click on Wallet tab
And I see Create Wallet option
And I see Import Wallet option
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
Then I click Back button of trade Wallet Page
And I click on Wallet tab
Then I see My Wallet option

@VerifyWalletTabNavigationToSettingsPage
Scenario: Verify navigation from Wallet to Settings Page and back to Wallet
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I click on Wallet tab
Then I see My Wallet option  
And I see Create Wallet option
And I click on the Settings tab in bottom navigation
Then I should see the Settings header
And I should see the Choose Wallet option
And I should see the Dark Mode option
And I should see the Exchange option
And I click on Wallet tab
And I see Create Wallet option
And I see Import Wallet option
And I click on the Settings tab in bottom navigation
Then I should see the Settings header
And I should see the Choose Wallet option
And I should see the Dark Mode option
And I click on Wallet tab
Then I see My Wallet option


@VerifyExchangeTabNavigationToSettingsPage
Scenario: Verify navigation from exchange page to Settings Page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I click on Wallet tab
Then I see My Wallet option  
And I see Create Wallet option
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
Then I click Back button of trade Wallet Page
And I click on the Settings tab in bottom navigation
Then I should see the Settings header
And I should see the Choose Wallet option
And I should see the Dark Mode option
And I should see the Exchange option
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
Then I click Back button of trade Wallet Page
And I click on the Settings tab in bottom navigation
Then I should see the Settings header

@VerifyHomeTabNavigationToSettingsPage
Scenario: Verify navigation from Home page to Settings Page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I click on the Settings tab in bottom navigation
Then I should see the Settings header
And I should see the Choose Wallet option
And I should see the Dark Mode option
And I should see the Exchange option
Then I click on Home Tab
Then I should be on homePage
Then I should see the Receive button is displayed 



# Test sheet test cases
@VerifySendPageNavigation
Scenario:Verify clicking Send navigates to the send screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I verify "Import Wallet" button on screen
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen

@VerifySwapPageNavigation
Scenario: Verify clicking Swap navigates to the swap screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I should see the Swap button is displayed
When I click on the "Swap" button
Then I should see the "Swap" header on the screen

@VerifyBuyPage
Scenario: Verify clicking Buy navigates to the buy screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I should see the Buy button is displayed
When I click on the Buy button
Then I should see the Buy header and buy button on the screen



