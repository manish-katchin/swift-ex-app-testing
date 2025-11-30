@Regression @WalletSelection
Feature: Wallet Selection
As a user, I want to select and manage wallets in the app


  @Selectbyname
  Scenario:   Import multiple wallets and verify seamless navigation across wallet instances
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I verify "Import Wallet" button on screen
  And I click on "Import" button
  And I should see "Multi-Chain Wallet" section 
  And I should see "Binance Smart Chain" option
  And I should see "Ethereum" option
  When I click on "Multi-Chain Wallet" 
  Then I should see "Multi-Chain Wallet" header
  When I enter name "Sam" in the input field
  And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  And I click on "Import" button
  Then I should see "All Wallets" header on the screen
  When I click on any wallet card
  Then I should see the Receive button is displayed    
  And I should see the Send button is displayed
  And I should see the Swap button is displayed
  And I should see the Buy button is displayed




  @multiple1
  Scenario:   Import multiple wallets and verify seamless navigation across wallet instances
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I verify "Import Wallet" button on screen
  And I click on "Import" button
  And I should see "Multi-Chain Wallet" section 
  And I should see "Binance Smart Chain" option
  And I should see "Ethereum" option
  When I click on "Multi-Chain Wallet" 
  Then I should see "Multi-Chain Wallet" header
  When I enter name "Sam" in the input field
  And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  And I click on "Import" button
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Sam" has active wallet indication
  And I select the wallet named "Main"
  Then I should see the Receive button is displayed    
  And I should see the Send button is displayed
  And I should see the Swap button is displayed
  And I should see the Buy button is displayed
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  And I see Choose Wallet option
  When I click on Choose Wallet option
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Main" has active wallet indication
  And I see All available Wallets


  @VerifyAvailableWallets
  Scenario: The user should be navigated to the "All Wallets" screen displaying available wallets
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  When I click on Choose Wallet option
  Then I should see "All Wallets" header on the screen
  And I see All available Wallets

@VerifyActiveWalletIndicator
  Scenario: The currently active wallet should have a tick mark on the right and be highlighted.
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  When I click on Choose Wallet option
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Main" has active wallet indication



  
  @VerifyActiveWalletIndicatoronSelectedWallet @J
  Scenario: Verify Selecting a Different Wallet and Ensuring the Active Wallet Indicator Updates Accordingly
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  Then I verify "Import Wallet" button on screen
  And I click on "Import" button
  And I should see "Multi-Chain Wallet" section 
  And I should see "Binance Smart Chain" option
  And I should see "Ethereum" option
  When I click on "Multi-Chain Wallet" 
  Then I should see "Multi-Chain Wallet" header
  When I enter name "Sam" in the input field
  And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  And I click on "Import" button
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Sam" has active wallet indication
  And I select the wallet named "Main"
  Then I should see the Receive button is displayed
  And I click on Wallet tab
  And I see Choose Wallet option
  When I click on Choose Wallet option
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Main" has active wallet indication




  @VerifyWalletNameUpdateonHomepage @J
  Scenario: Verify Wallet Name Update on Homepage
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I verify "Import Wallet" button on screen
  And I click on "Import" button
  And I should see "Multi-Chain Wallet" section 
  When I click on "Multi-Chain Wallet" 
  Then I should see "Multi-Chain Wallet" header
  When I enter name "Sam" in the input field
  And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  And I click on "Import" button
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Sam" has active wallet indication
  And I select the wallet named "Main"
  Then I should see the Receive button is displayed
  Then I should see wallet name "Main" displayed


#TC-95 
 @VerifyPopupOnSelectingWallet @11 @FC
  Scenario: Verify Popup on Selecting a Wallet
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  Then I verify "Import Wallet" button on screen
  And I click on "Import" button
  And I should see "Multi-Chain Wallet" section 
  And I should see "Binance Smart Chain" option
  And I should see "Ethereum" option
  When I click on "Multi-Chain Wallet" 
  Then I should see "Multi-Chain Wallet" header
  When I enter name "Sam" in the input field
  And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  And I click on "Import" button
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Sam" has active wallet indication
  And I select the wallet named "Main"
  And I see the popup with message "Wallet Selected 'Main'"



#TC-96
@VerifyClickableAreaForWalletSelection @11
 Scenario: Verify Clickable Area for Wallet Selection
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  When I click on Choose Wallet option
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Main" has active wallet indication
  Then I verify "card" area of wallet "Main" is clickable
  And I verify "icon" area of wallet "Main" is clickable
  And I verify "active" area of wallet "Main" is clickable
  And I verify "tick" area of wallet "Main" is clickable


#TC-97
@VerifyNavigationtoMyWalletPage @11
 Scenario: Verify Navigation to My Wallet Page
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen 

#TC-98
 @VerifyActiveWalletNameDisplay @11
  Scenario: Verify Active Wallet Name Display
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I see My Wallet option  
  Then I verify "Import Wallet" button on screen
  And I click on "Import" button
  And I should see "Multi-Chain Wallet" section 
  And I should see "Binance Smart Chain" option
  And I should see "Ethereum" option
  When I click on "Multi-Chain Wallet" 
  Then I should see "Multi-Chain Wallet" header
  When I enter name "Sam" in the input field
  And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  And I click on "Import" button
  Then I should see "All Wallets" header on the screen
  Then I should see wallet "Sam" has active wallet indication
  Then I click "Back Button" on All Wallets screen
  Then I see My Wallet option  
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen 
  Then I verify Active wallet card with name "Sam" is Present


#TC-99
@VerifyWarningMessageDisplay @11
 Scenario: Verify Warning Message Display
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen 
  And I verify warning message is displayed


#TC-100
@VerifySecretPhraseOptionAvailability 
 Scenario: Verify Secret Phrase Option Availability
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen 
  Then I verify Show Secret Phrase option is available 
  Then I verify Show Secret Phrase option is clickable

#TC-101
@VerifyBackupWalletPopupforWalletflow
 Scenario: Verify Backup Wallet Popup Appears
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen
  Then I click on Show Secret Phrase option
  Then I should be on the "Backup your wallet now" screen 


  #TC-102
@VerifyContinueButtonDisabledWithoutSelectingConditions
 Scenario: Verify Continue Button Remains Disabled Without Selecting Conditions
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen
  Then I click on Show Secret Phrase option
  Then I should be on the "Backup your wallet now" screen 
  And I see "Continue" button is disabled on the screen

#TC-103
@VerifyContinueButtonEnabledAfterSelectingConditions
 Scenario: Verify Continue Button Becomes Enabled After Selecting Conditions
 Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen
  Then I click on Show Secret Phrase option
  Then I should be on the "Backup your wallet now" screen
  When I select the option If I lose my private keys, my funds will be lost
  And I select the option If I share my private key, my funds can be lost
  Then I see "Continue" button is enabled on the screen 



#TC-105
@VerifyContinueButtonEnabledAfterSelectingConditions
 Scenario: Verify Continue Button Becomes Enabled After Selecting Conditions
 Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  Then I should see wallet name "Main" displayed
  And I click on Wallet tab
  Then I see My Wallet option  
  And I see Create Wallet option
  And I see Import Wallet option   
  And I see Choose Wallet option
  Then I click on My Wallet option
  Then I should see "Wallet" header on the screen
  Then I click on Show Secret Phrase option
  Then I should be on the "Backup your wallet now" screen
  When I select the option If I lose my private keys, my funds will be lost
  And I select the option If I share my private key, my funds can be lost
  And I click on the "Continue" button
  Then I should be on "Backup Mnemonic Phrase" page
  And I click on Copy button for mnemonic phrase
  Then I see "Copied" toast message is displayed
  And I click on Copy button for private key
  Then I see "Copied" toast message is displayed
