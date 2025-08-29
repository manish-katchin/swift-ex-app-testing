Feature: Import Wallet 
  As a user
  I want to import an existing wallet
  So that I can access my funds securely

  @importWalletNegative
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


    @importWalletEthereumNegative
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


    @importWalletBinanceSmartChainNegative
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
      When I enter name "XYZ" in the input field 
      And I enter phrase "random invalid phrase" in the input field with label "Phrase"
    # I enter JSON password "invalid password" in the input field "JSON password"
      Then I should see error message "Please enter a valid private key"


    @importWalletEthereumPositive
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


    @SwappingCryptoCurrency
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
      And I enter "0.00025" in the input field below "WETH"
      Then I click on the "Swap" button on Swap page
      Then I should see "Swap Success" message on the screen

  
