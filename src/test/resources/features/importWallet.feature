@Regression
Feature: Import Wallet 
  As a user
  I want to import an existing wallet
  So that I can access my funds securely


  @importWalletMultiChainPositive
  Scenario: Verify that a user can successfully import an multi-chain wallet using a valid Mnemonic

    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Multi-Chain Wallet" 
    Then I should see "Multi-Chain Wallet" header
    When I enter name "Sam" in the input field with label "Name"
    And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
    And I click on "Import" button
    Then I should see the biometric authentication popup
    When I handle the biometric authentication
    Then I should see the Receive button is displayed    
    And I should see the Send button is displayed
    And I should see the Swap button is displayed
    And I should see the Buy button is displayed


  @importWalletMultichainNegative @1
  Scenario: Validate error message for invalid mnemonic during Multi-Chain wallet import
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Multi-Chain Wallet"
    Then I should see "Multi-Chain Wallet" header
    When I enter name "RandomName" in the input field with label "Name"
    And I enter phrase "random invalid phrase" in the input field with label "Phrase"
    Then I should see error message "Please enter a valid mnemonic"


    @importWalletEthereumPositivewithPrivateKey @1
    Scenario: Verify that a user can successfully import an Ethereum wallet using a valid private key.
      Given the app is launched
      When I enter a new PIN "123456"
      And I confirm the PIN "123456"
      Then I verify "Import Wallet" button on screen
      When I click Import Wallet button
      And I should see "Import Wallet" header
      And I should see "Multi-Chain Wallet" section
      And I should see "Binance Smart Chain" option
      And I should see "Ethereum" option
      When I click on "Ethereum"
      When I enter name "Sam" in the input field
      And I enter phrase "0xd6d71ef95d22f96910d50a6d878f3bf2b1907838291247a3819f02c20eca7697" in the input field with label "Phrase"
      And I click on "Import" button
      Then I should see the biometric authentication popup
      When I handle the biometric authentication
      Then I should see the Receive button is displayed
      And I should see the Send button is displayed
      And I should see the Swap button is displayed
      And I should see the Buy button is displayed

    @importWalletEthereumNegativewithPrivateKey 
    Scenario: Validate error with invalid private key
      Given the app is launched
      When I enter a new PIN "123456"
      And I confirm the PIN "123456"
      Then I verify "Import Wallet" button on screen
      When I click Import Wallet button
      And I should see "Import Wallet" header
      And I should see "Multi-Chain Wallet" section
      And I should see "Binance Smart Chain" option
      And I should see "Ethereum" option
      When I click on "Ethereum"
      When I enter name "RandomName" in the input field
      And I enter phrase "random invalid phrase" in the input field with label "Phrase"
      Then I should see error message "Please enter a valid private key"

    @importWalletEthereumPositivewithMnemonic
    Scenario: Import wallet with valid Ethereum mnemonic phrase
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Ethereum"
    When I click on "Mnemonic" button after import wallet Screen
    When I enter name "Sameer" in the input field
    And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
    And I click on "Import" button
    Then I should see the biometric authentication popup
    When I handle the biometric authentication
    Then I should see the Receive button is displayed    
    And I should see the Send button is displayed
    And I should see the Swap button is displayed
    And I should see the Buy button is displayed

@importWalletEthereumNegativewithMnemonic
    Scenario: Validate error message for invalid mnemonic during  Ethereum wallet import
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Ethereum"
    When I click on "Mnemonic" button after import wallet Screen
    When I enter name "Sameer" in the input field
    And I enter phrase "lend limit blind south misery" in the input field with label "Phrase"
    Then I should see error message "Please enter a valid mnemonic"

@importWalletBinanceSmartChainPositivewithPrivateKey
    Scenario: Import wallet with valid Binance Smart Chain private key
      Given the app is launched
      When I enter a new PIN "123456"
      And I confirm the PIN "123456"
      Then I verify "Import Wallet" button on screen
      When I click Import Wallet button
      And I should see "Import Wallet" header
      And I should see "Multi-Chain Wallet" section
      And I should see "Binance Smart Chain" option
      And I should see "Ethereum" option
      When I click on "Binance Smart Chain"
      When I enter name "Sam" in the input field
      And I enter phrase "0xd6d71ef95d22f96910d50a6d878f3bf2b1907838291247a3819f02c20eca7697" in the input field with label "Phrase"
      And I click on "Import" button
      Then I should see the biometric authentication popup
      When I handle the biometric authentication
      Then I should see the Receive button is displayed
      And I should see the Send button is displayed
      And I should see the Swap button is displayed
      And I should see the Buy button is displayed



    @importWalletBinanceSmartChainNegativeWithPrivateKey
    Scenario: Validate error for Binance Smart Chain import with invalid private key
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Binance Smart Chain"
    When I enter name "XYZ" in the input field 
    And I enter phrase "random invalid phrase" in the input field with label "Phrase"
    Then I should see error message "Please enter a valid private key"



    @importWalletBinancePositivewithMnemonic
    Scenario: Import wallet with valid Binance Smart Chain mnemonic phrase
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Binance Smart Chain"
    When I click on "Mnemonic" button after import wallet Screen
    When I enter name "Sameer" in the input field
    And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
    And I click on "Import" button
    Then I should see the biometric authentication popup
    When I handle the biometric authentication
    Then I should see the Receive button is displayed    
    And I should see the Send button is displayed
    And I should see the Swap button is displayed
    And I should see the Buy button is displayed

@importWalletBinanceSmartChainNegativeWithMnemonic
    Scenario:  Validate error message for invalid mnemonic, private key, during Binance smart chain wallet import
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Binance Smart Chain"
    When I click on "Mnemonic" button after import wallet Screen
    When I enter name "XYZ" in the input field 
    And I enter phrase "random invalid phrase" in the input field with label "Phrase"
    Then I should see error message "Please enter a valid mnemonic"

  @multipleWallet
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

# Test sheet import Wallet test cases:

  @verifyImportWalletScreen
  Scenario: Verify Navigation to Import Wallet Screen
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header


  @VerifyWalletImportOptions
  Scenario: Verify the Presence of Wallet Import Options
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option



  @VerifyNavigationToMultiChainWalletScreen
  Scenario: Verify Navigation to Multi-Chain Wallet Screen
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Multi-Chain Wallet" 
    Then I should see "Multi-Chain Wallet" header
  
  
  @VerifyFieldsOnMultiChainWalletScreen
  Scenario: Verify Fields on Multi-Chain Wallet Screen
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    When I click on "Multi-Chain Wallet"
    Then I should see "Multi-Chain Wallet" header
    And I should see "Name" input field on multiwallet Page
    And I should see "Phrase" input field on multiwallet Page
    And I should see "Import" button on multiwallet Page
    And I should see "Import" button is disabled on multiwallet Page


  # @VerifyImportButtonEnabledAfterEnteringDetails
  # Scenario: Verify Fields on Multi-Chain Wallet Screen
  #   Given the app is launched
  #   When I enter a new PIN "123456"
  #   And I confirm the PIN "123456"
  #   Then I verify "Import Wallet" button on screen
  #   When I click Import Wallet button
  #   And I should see "Import Wallet" header
  #   And I should see "Multi-Chain Wallet" section
  #   When I click on "Multi-Chain Wallet"
  #   Then I should see "Multi-Chain Wallet" header
  #   When I enter name "Sam" in the input field with label "Name"
  #   And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
  #   And I should see "Import" button is enabled on multiwallet Page


@VerifyBinanceWalletPageElements
  Scenario: Verify Elements on Binance Smart Chain Wallet Screen
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "Import Wallet" button on screen
  When I click Import Wallet button
  And I should see "Import Wallet" header
  And I should see "Binance Smart Chain" option
  When I click on "Binance Smart Chain"
  And I should see Privatekey option
  And I should see Mnemonic option
  And I should see JSON key option
      

# @VerifyBinanceWalletPageElement    
#   Scenario: Verify Elements on Binance Smart Chain Wallet Screen
#   Given the app is launched
#   When I enter a new PIN "123456"
#   And I confirm the PIN "123456"
#   Then I verify "Import Wallet" button on screen
#   When I click Import Wallet button
#   And I should see "Import Wallet" header
#   And I should see "Binance Smart Chain" option
#   When I click on "Binance Smart Chain"
#   And I should see Privatekey option is selected by default
  

  @VerifyBinanceWalletPagePasteButton
  Scenario: Verify Elements on Binance Smart Chain Wallet Screen
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "Import Wallet" button on screen
  When I click Import Wallet button
  And I should see "Import Wallet" header
  And I should see "Binance Smart Chain" option
  When I click on "Binance Smart Chain"
  And I should see Paste button on the Phrase input field
  
@VerifyBackNavigationFromImportWalletScreen
  Scenario: Verify Back Navigation from Import Wallet Screen
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "Import Wallet" button on screen
  When I click Import Wallet button
  And I should see "Import Wallet" header
  When I click on back button on Import Wallet screen
  Then I verify "Import Wallet" button on screen


@VerifyNavigationToEthereumWalletScreen
Scenario: TC-065 Verify Navigation to Ethereum Import Wallet Screen
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "Import Wallet" button on screen
  When I click Import Wallet button
  And I should see "Import Wallet" header
  And I should see "Ethereum" option
  When I click on "Ethereum"
  And I should see Privatekey option
  And I should see Mnemonic option
  And I should see JSON key option


  @VerifySpaceIsNotConsideredAsInput
  Scenario: TC-071 Verify Spaces Are Not Considered as Input
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "Import Wallet" button on screen
  When I click Import Wallet button
  And I should see "Import Wallet" header
  And I should see "Ethereum" option
  When I click on "Ethereum"
  And I enter name "   " in the input field
  And I enter phrase "     " in the input field with label "Phrase"
  Then I should see error message "Please enter a valid private key"
  And I should see "Import" button is disabled on Ethereum Wallet Page



  @VerifyPasteButtonIsPresentInEthereumMnemonicField
  Scenario: TC-078 Verify Paste Button is Present in the Mnemonic Field
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "Import Wallet" button on screen
  When I click Import Wallet button
  And I should see "Import Wallet" header
  And I should see "Ethereum" option
  When I click on "Ethereum"
  And I should see Paste button on the Phrase input field



@importWalletBinanceSmartChainNegativeWithJSONkey
    Scenario:  Validate error message for invalid mnemonic, JSON key, during Binance smart chain wallet import
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "Import Wallet" button on screen
    When I click Import Wallet button
    And I should see "Import Wallet" header
    And I should see "Multi-Chain Wallet" section
    And I should see "Binance Smart Chain" option
    And I should see "Ethereum" option
    When I click on "Binance Smart Chain"
    When I click on "JSON Key" button after import wallet Screen
    When I enter name "XYZ" in the input field 
    And I enter phrase "random invalid phrase" in the input field with label "Phrase"
    Then I should see error message "Please enter a valid mnemonic"


    @VerifyPastebuttonFunctionality
    Scenario: "User has copied a valid mnemonic phrase to the clipboard User is on the Binance Smart Chain Import Wallet screen, with Mnemonic selected."
      Given the app is launched
      When I enter a new PIN "123456"
      And I confirm the PIN "123456"
      Then I verify "Import Wallet" button on screen
      When I click Import Wallet button
      And I should see "Import Wallet" header
      And I should see "Multi-Chain Wallet" section
      And I should see "Binance Smart Chain" option
      And I should see "Ethereum" option
      When I click on "Binance Smart Chain"
      When I enter name "Sam" in the input field
      And I copy phrase "0xd6d71ef95d22f96910d50a6d878f3bf2b1907838291247a3819f02c20eca7697" in the input field with label Phrase using Paste button
      And I click on "Import" button
    