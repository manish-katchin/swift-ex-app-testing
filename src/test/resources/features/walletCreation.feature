Feature: Wallet Creation 

  @createWallet  
  Scenario: Create a new wallet and proceed to the private key page
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should be on the "Backup your wallet now" screen
  When I select the option If I lose my private keys, my funds will be lost
  And I select the option If I share my private key, my funds can be lost
  And I click on the "Continue" button
  Then I should be on "Private key" page
  When I enter account name "TestAccount" in the Account Name field
  And I click on the Next button
  When I click on the "Done" button
  Then I see error "Please provide all answers before submitting"
  And I verify and select the requested mnemonic words on Check Mnemonic page
  And I verify all mnemonic words are correctly selected
  When I click on the "Done" button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I should see the Send button is displayed
  And I should see the Swap button is displayed
  And I should see the Buy button is displayed

  

  @defaultWallet 
  Scenario: Create a new wallet and proceed to the private key page
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click use default button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I should see the Send button is displayed
  And I should see the Swap button is displayed
  And I should see the Buy button is displayed
  And I should see the Assets tab is displayed
  And I should see the Add Assets tab is displayed
  And I should see the Home tab is displayed in bottom navigation
  And I should see the Wallet tab is displayed in bottom navigation
  And I should see the Market tab is displayed in bottom navigation
  And I should see the Exchange tab is displayed in bottom navigation
  And I should see the Settings tab is displayed in bottom navigation


 @navigation 
  Scenario: Navigate to Wallet page
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
  When I click on Market tab
  Then I see market page header
  And I see bitcoin option on market page

 @verificationExchangeTab
Scenario: Navigate to Exchange page
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on the Exchange tab in bottom navigation
  Then I should see the Trade Wallet header
  And I should see the Home icon
  And I should see the On-Off Ramp icon
  And I should see the Transactions icon
  And I should see the Profile icon



 @verificationOfSettingsTab
Scenario: Navigate to Settings page
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on the Settings tab in bottom navigation
  Then I should see the Settings header
  And I should see the Choose Wallet option
  And I should see the Dark Mode option
  And I should see the Exchange option
  And I should see the Transactions option
  And I should see the Biometric Authentication option
  And I should see the Preference option
  And I should see the Push Notification option
  And I should see the Help Center option
  And I should see the Logout option

 @logout 
Scenario: Logout of the application
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on the Settings tab in bottom navigation
  Then I should see the Settings header
  When I click on the Logout option in Settings
  Then I am on pin page

  @verificationOfWalletCreationOptions
  Scenario: Verify that after setting up the PIN, the "Create Wallet" and "Import Wallet" options are displayed.
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  Then I verify "Import Wallet" button on screen
  And I see Import Wallet option


 @VerifyBackupwalletScreen
  Scenario: Verify that clicking on "Create Wallet" navigates to the "Back Up Your Wallet" screen.
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
  Then I click on Create Wallet option
  Then I should be on the "Backup your wallet now" screen

 @VerifyContinueButtonDisabled
  Scenario: Verify that both backup conditions must be selected to enable the "Continue" button.
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
  Then I click on Create Wallet option
  Then I should be on the "Backup your wallet now" screen
  When I select the option If I lose my private keys, my funds will be lost
  And I click on the "Continue" button
  And I see "Continue" button is disabled on the screen



 @VerifyBackupMnemonicPhraseScreen
  Scenario: Verify that clicking "Continue" after selecting both conditions navigates to the "Backup Mnemonic Phrase" screen.
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
  Then I click on Create Wallet option
  Then I should be on the "Backup your wallet now" screen
  When I select the option If I lose my private keys, my funds will be lost
  And I select the option If I share my private key, my funds can be lost
  And I click on the "Continue" button
  Then I should be on "Backup Mnemonic Phrase" page


 @VerifyContinueButtonDisabledWhenNoConditionsSelected
  Scenario: Verify that clicking "Continue" without selecting both conditions does not navigate to the next screen.
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
  Then I click on Create Wallet option
  Then I should be on the "Backup your wallet now" screen
  And I click on the "Continue" button
  And I see "Continue" button is disabled on the screen

