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