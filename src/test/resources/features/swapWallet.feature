@SwapWalletFeature
Feature: Swap Wallet 
As a user I want to Swap wallet
    
    @SwappingCryptoCurrencyfromEthereumToUSDC
    Scenario: Verify successful Crypto Swap from Ether to USDC
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
    And I should see the Swap button is displayed
    When I click on the "Swap" button
    Then I should see the "Swap" header on the screen
    And I enter "0.0001" in the input field below "WETH"
    Then I click on the "Swap" button on Swap page
    Then I should see "Swap Success" message on the screen
 
    @SwappingCryptoCurrencyfromUSDCToEthereum
    Scenario: Verify successful Crypto Swap from USDC to Ethereum
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

@sendCryptoCurrency
Scenario:To verify user is able to perform transaction
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
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I should see the "Send" header on the screen
And I enter "0x05cBb7CbEEE7C8f1B2DBf0Bb4bb820ac918D7c0e" in the input field with label "Recipient Address"
When I enter "0.0001" in the input field below "Amount"
And I click on the "Send" button
Then I should see the "Confirm Transaction" header
And I click on the "Send" button
When I enter a new PIN "123456"
Then I click on first Transaction On Transactions Page


@SwappingCryptoCurrencyfromUSDCToEthereumwithInsufficientBalance
Scenario: Swap USDC to Ethereum - Show Insufficient Balance When Funds Are Low
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
And I should see the Swap button is displayed
When I click on the "Swap" button
Then I should see the "Swap" header on the screen
Then I press the currency switch arrow
And I enter "100" in the input field below "WETH"
Then I should see "Insufficient Balance" message on the screen

  @SwappingCryptoCurrencyfromEthereumToUSDCwithInsufficientBalance
    Scenario: Import wallet with valid private key Swap Ethereum to USDC - Display Insufficient Balance on Low Funds
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
    And I should see the Swap button is displayed
    When I click on the "Swap" button
    Then I should see the "Swap" header on the screen
    Then I press the currency switch arrow
    And I enter "100" in the input field below "WETH"
    Then I should see "Insufficient Balance" message on the screen

@ValidateSendTransaction
Scenario:Send ETH Transaction - Validate Amount and Recipient Details
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
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I should see the "Send" header on the screen
And I enter "0x05cBb7CbEEE7C8f1B2DBf0Bb4bb820ac918D7c0e" in the input field with label "Recipient Address"
When I enter "0.0001" in the input field below "Amount"
And I click on the "Send" button
Then I should see the "Confirm Transaction" header
And I click on the "Send" button
When I enter a new PIN "123456"
Then I click on first Transaction On Transactions Page
Then I should see the "Transactions" header on the screen
And I should see "0.0001" amount on the screen
And I should see "0x05cBb7CbEEE7C8f1B2DBf0Bb4bb820ac918D7c0e" recipient address on the screen


