@Regression @WalletCreationFeature
Feature: Wallet Creation 

  # @createWallet  
  # Scenario: Validate Creation of a New Wallet by Handling Backup Wallet Screen, Private key page , Mnemonics. Post creation handling biometric Authentication screen
  # Given the app is launched
  # When I enter a new PIN "123456"
  # And I confirm the PIN "123456"
  # Then I should be on Create A new wallet Page
  # When I click Create a new wallet button
  # Then I should be on the "Backup your wallet now" screen
  # When I select the option If I lose my private keys, my funds will be lost
  # And I select the option If I share my private key, my funds can be lost
  # And I click on the "Continue" button
  # Then I should be on "Private key" page
  # When I enter account name "TestAccount" in the Account Name field
  # And I click on the Next button
  # When I click on the "Done" button
  # Then I see error "Please provide all answers before submitting"
  # And I verify and select the requested mnemonic words on Check Mnemonic page
  # And I verify all mnemonic words are correctly selected
  # When I click on the "Done" button
  # Then I should see the biometric authentication popup
  # When I handle the biometric authentication
  # And I should see the Send button is displayed
  # And I should see the Swap button is displayed
  # And I should see the Buy button is displayed

  

  # @defaultWallet 
  # Scenario: Validate Create wallet using default option and verify post-authentication Homescreen elements
  # Given the app is launched
  # When I enter a new PIN "123456"
  # And I confirm the PIN "123456"
  # Then I should be on Create A new wallet Page
  # When I click use default button
  # Then I should see the biometric authentication popup
  # When I handle the biometric authentication
  # And I should see the Send button is displayed
  # And I should see the Swap button is displayed
  # And I should see the Buy button is displayed
  # And I should see the Assets tab is displayed
  # And I should see the Add Assets tab is displayed
  # And I should see the Home tab is displayed in bottom navigation
  # And I should see the Wallet tab is displayed in bottom navigation
  # And I should see the Market tab is displayed in bottom navigation
  # And I should see the Exchange tab is displayed in bottom navigation
  # And I should see the Settings tab is displayed in bottom navigation


 @navigation 
  Scenario: Verify navigation to Wallet and Market tabs screen after default wallet setup
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
Scenario: Verify Exchange tab navigation and UI elements post wallet setup
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



 @verificationOfSettingsTab @scroll
Scenario: Verify Settings tab navigation and UI elements post wallet Setup
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

 @logout  @scroll 
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
  And I should see the Help Center option
  When I click on the Logout option in Settings
  #Then I am on pin page

  @verificationOfWalletCreationOptions
  Scenario: Verify that after setting up the PIN, the "Create Wallet" and "Import Wallet" options are displayed.
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  Then I verify "Import Wallet" button on screen


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


  @VerifyBackupMnemonicPhrasesonScreen
  Scenario: Verify that the mnemonic phrases are visible on the screen.
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
  And I verify mnemonic phrases are visible on the screen



  @VerifyDoneButtonEnabled
  Scenario: Verify that the "Done" button is enabled when name is entered.
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
  And I verify mnemonic phrases are visible on the screen
  When I enter account name "TestAccount" in the Account Name field
  Then I should see the "Done" button is enabled

  @VerifyDoneButtonNavigationToSecretPhraseScreen
  Scenario: Verify that clicking "DoneButton" after entering a valid account name navigates to the "Verify Secret Phrase" screen
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
  When I enter account name "TestAccount" in the Account Name field
  When I click on the "Done" button
  Then I should be on "Verify Secret Phrase" screen


  @VerifyJumbledMnemonicPhraseScreen
  Scenario: Verify that the jumbled mnemonic phrases appear for verification.
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
  When I enter account name "TestAccount" in the Account Name field
  When I click on the "Done" button
  Then I should be on "Verify Secret Phrase" screen
  Then I verify jumbled mnemonic phrases appear for verification

@VerifyCompleteBackupProcess @rerun
  Scenario:  Verify that selecting all four correct words successfully completes the backup process.
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
  And I store mnemonic phrases from the backup screen
  When I enter account name "TestAccount" in the Account Name field
  When I click on the "Done" button
  Then I should be on "Verify Secret Phrase" screen
  Then I verify jumbled mnemonic phrases appear for verification
  And I verify and select the requested mnemonic words on Check Mnemonic page
  And I verify all mnemonic words are correctly selected
  Then I click on the "Import" button on verify secret phrase screen
  
  @VerifyDoneButtonDisabledWhenNoNameEntered
  Scenario: Verify that clicking "Done" without entering an account name does not navigate to the next screen.
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
  Then I should see the "Done" button is disabled
  Then I should be on "Backup Mnemonic Phrase" page


@VerifyAppClosureRequiresStartingOver
  Scenario: Verify that closing the app before completing wallet creation requires starting over.
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication
  And I click on Wallet tab
  Then I click on Create Wallet option
  Then I should be on the "Backup your wallet now" screen
  When I select the option If I lose my private keys, my funds will be lost
  And I select the option If I share my private key, my funds can be lost
  And I click on the "Continue" button
  Then I should be on "Backup Mnemonic Phrase" page
  When I enter account name "TestAccount" in the Account Name field
  When I click on the "Done" button
  Then App is closed
  Then I launch the app with noReset true
  When I enter a new PIN "123456"
  Then I should see the biometric authentication popup

@VerifyAppClosureCannotBypassWalletCreation
  Scenario: Verify that a user cannot bypass wallet creation by force-closing and reopening the app.
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I should be on Create A new wallet Page  
  Then App is closed
  Then I launch the app with noReset true
  When I enter a new PIN "123456"
  Then I should be on Create A new wallet Page  



  @VerifyDoneButtonDisabledWhenSpaceEnteredInNameField
  Scenario: Verify that clicking "Done" without entering an account name does not navigate to the next screen.
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
  When I enter account name "    " in the Account Name field
  Then I should see the "Done" button is disabled
  Then I should be on "Backup Mnemonic Phrase" page


@VerifyAccountNameFieldCharacterLimit
  Scenario: Verify that the account name field does not accept more than 20 characters.
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
  When I enter account name "Twenty One Characters" in the Account Name field
  Then I verify only 20 characters are accepted in the Account Name field

 
@VerifyEmojiNotAcceptedInAccountNameField @rerun
  Scenario: Verify that the account name field does not accept only emojis.
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
  When I enter account name "🙂 🙂 🙂" in the Account Name field
  Then I should see the "Done" button is disabled
  Then I verify no characters are accepted in the Account Name field

@VerifyErrorWhenNoWordsSelected
  Scenario: Verify that submitting without selecting all four required words shows an error.
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
  When I enter account name "Sam" in the Account Name field
  When I click on the "Done" button
  Then I should be on "Verify Secret Phrase" screen
  Then I click on the "Import" button on verify secret phrase screen
  Then I see error "Please provide all answers before submitting"


@VerifyContinueButtonEnabledAfterSelectingBothConditions
  Scenario: TC-103 Verify Continue Button is Enabled After Selecting Both Conditions
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
  Then I see "Continue" button is enabled on the screen

@VerifyInstructionForKeepingSecretPhraseSafe
Scenario: TC-107 Verify Instruction for Keeping Secret Phrase Safe
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
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
Then I should see instruction first "Keep your mnemonic in a safe place, isolated from any network."
Then I should see instruction Second "Do not share it through email, photos, social media, apps, etc."


@VerifyMnemonicPhrase
  Scenario: Verify Display of Mnemonic Phrase
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
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
  And I verify mnemonic phrases are visible on the screen



@VerifyBackButtonFunctionalityforBackupScreen
  Scenario: TC-15 Verify that clicking "Back" from the "Back Up Your Wallet" screen returns to the Create Wallet/Import Wallet screen.
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
  Then I click on the Back button on Backup your wallet screen
  And I see Create Wallet option

# Depricated
# @VerifyMnemonicPhraseonbothscreen
#   Scenario: Verify Display of Mnemonic Phrase
#   Given the app is launched
#   When I enter a new PIN "123456"
#   And I confirm the PIN "123456"
#   When I click Create a new wallet button
#   Then I should see the biometric authentication popup
#   When I handle the biometric authentication
#   And I click on Wallet tab
#   Then I see My Wallet option  
#   And I see Create Wallet option
#   And I see Import Wallet option   
#   And I see Choose Wallet option
#   Then I click on Create Wallet option
#   Then I should be on the "Backup your wallet now" screen
#   When I select the option If I lose my private keys, my funds will be lost
#   And I select the option If I share my private key, my funds can be lost
#   And I click on the "Continue" button
#   Then I should be on "Backup Mnemonic Phrase" page
#   And I verify mnemonic phrases are visible on the screen
#   And I store mnemonic phrases from the backup screen
#   When I enter account name "TestAccount" in the Account Name field
#   When I click on the "Done" button
#   Then I should be on "Verify Secret Phrase" screen
#   Then I verify jumbled mnemonic phrases appear for verification
#   Then the mnemonic words on both screens should match


