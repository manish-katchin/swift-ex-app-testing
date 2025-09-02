Feature: Import Wallet 
  As a user
  I want to import an existing wallet
  So that I can access my funds securely


  @importWalletMultiChainPositive
  Scenario: Import wallet with valid mnemonic phrase
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


  @importWalletMultichainNegative
  Scenario: Import wallet with invalid mnemonic phrase
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


     @importWalletEthereumPositivewithPrivateKey
    Scenario: Import wallet with valid Ethereum private key
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
      When I enter name "Sam" in the input field with label "Name"
      And I enter phrase "0xd6d71ef95d22f96910d50a6d878f3bf2b1907838291247a3819f02c20eca7697" in the input field with label "Phrase"
      And I click on "Import" button
      Then I should see the biometric authentication popup
      When I handle the biometric authentication
      Then I should see the Receive button is displayed
      And I should see the Send button is displayed
      And I should see the Swap button is displayed
      And I should see the Buy button is displayed

    @importWalletEthereumNegativewithPrivateKey
    Scenario: Import wallet with invalid mnemonic phrase
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
      When I enter name "RandomName" in the input field with label "Name"
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
    When I enter name "Sameer" in the input field with label "Name"
    And I enter phrase "lend wreck slogan shrimp cradle december piece gallery limit blind south misery" in the input field with label "Phrase"
    And I click on "Import" button
    Then I should see the biometric authentication popup
    When I handle the biometric authentication
    Then I should see the Receive button is displayed    
    And I should see the Send button is displayed
    And I should see the Swap button is displayed
    And I should see the Buy button is displayed

@importWalletEthereumNegativewithMnemonic
    Scenario: Import wallet with invalid Ethereum mnemonic phrase
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
    When I enter name "Sameer" in the input field with label "Name"
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
    Scenario: Import wallet with invalid private key
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
    Scenario: Import wallet with invalid mnemonic phrase
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


    @SwappingCryptoCurrencyfromEthereumToUSDC
    Scenario: Import wallet with valid private key 
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
    When I enter name "Sam" in the input field with label "Name"
    And I enter phrase "0xd6d71ef95d22f96910d50a6d878f3bf2b1907838291247a3819f02c20eca7697" in the input field with label "Phrase"
    And I click on "Import" button
    Then I should see the biometric authentication popup
    When I handle the biometric authentication
    And I should see the Swap button is displayed
    When I click on the "Swap" button
    Then I should see the "Swap" header on the screen
    Then I press the currency switch arrow
    And I enter "0.00025" in the input field below "WETH"
    Then I click on the "Swap" button on Swap page
    Then I should see "Swap Success" message on the screen



   @SwappingCryptoCurrencyfromUSDCToEthereum
    Scenario: Import wallet with valid private key
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
    When I enter name "Sam" in the input field with label "Name"
    And I enter phrase "0xd6d71ef95d22f96910d50a6d878f3bf2b1907838291247a3819f02c20eca7697" in the input field with label "Phrase"
    And I click on "Import" button
    Then I should see the biometric authentication popup
    When I handle the biometric authentication
    And I should see the Swap button is displayed
    When I click on the "Swap" button
    Then I should see the "Swap" header on the screen
    Then I press the currency switch arrow
    And I enter "1" in the input field below "WETH"
    Then I click on the "Swap" button on Swap page
    Then I should see "Swap Success" message on the screen

  






  




