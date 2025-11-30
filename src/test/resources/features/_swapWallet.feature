@Regression
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
And I see "Swap Success" popup on screen


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
When I enter name "Sam" in the input field
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
And I see "Swap Success" popup on screen

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
And I enter "200" in the input field below "WETH"
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
    And I enter "200" in the input field below "WETH"
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

#Test sheet test cases

@VerifySendButtonOnHomePage     
Scenario: Verify the presence of the Send button on the homepage.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed


@VerifyChooseWalletPopup
Scenario: Verify the Choose Wallet popup appears
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I verify "BNB" button on screen
Then I verify "Ethereum" button on screen

@VerifyClickingOnXLMNavigatesToSendPage
Scenario: Verify clicking on XLM navigates to the Send page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I should see the "Send" header on the screen


@VerifyPresenceofScannerIcon @31
Scenario: Verify the presence of Scanner Icon in the recipient field
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click "Claim 5 XLM Now!" button on screen
Then I should see the Scanner icon is displayed in the Recipient Address field

@VerifyDismissPopup  @31
Scenario: Verify clicking Don't Allow dismisses the popup
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click "Claim 5 XLM Now!" button on screen
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I verify "Only this time" button after clicking scanner icon on screen
Then I verify "Don't allow" button after clicking scanner icon on screen
Then I click "Don't allow" button after clicking scanner icon on screen

@VerifyCameraPermissionPopup
Scenario: Verify camera permission popup appears on first scanner click
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click "Claim 5 XLM Now!" button on screen
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I verify "Only this time" button after clicking scanner icon on screen
Then I verify "Don't allow" button after clicking scanner icon on screen

@VerifyDismissPopup  @31
Scenario: Verify clicking Don't Allow dismisses the popup
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click "Claim 5 XLM Now!" button on screen
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I verify "Only this time" button after clicking scanner icon on screen
Then I verify "Don't allow" button after clicking scanner icon on screen
Then I click "Don't allow" button after clicking scanner icon on screen


@VerifyCameraScannerAppearsAfterGrantingPermission @31
Scenario: Verify camera opens after granting permission
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I click "While using the app" button after clicking scanner icon on screen
Then I should see Scan QR Code header on the screen

@VerifyAvailableBalance @31
Scenario: Verify Available Balance calculation
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
And I should see available balance

@VerifyErrorWhenAmountExceedsAvailableBalance  @31
Scenario: Verify error when amount exceeds available balance
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
Then I should see the Recipient Address input field
Then I should see the Amount input field
And I should enter "GDQ5NT344AS6VVKG7B3IWRO7Y73BVRDOEVVVJ4ERBW5TXTWX7R4O4IA2" in the xlm Recipient Address input field
And I should enter "100" in the Amount input field
And I see "Low Balance" popup on screen


@VerifySuccessfulTransaction  @31
Scenario: Verify successful transaction
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
Then I should see the Recipient Address input field
Then I should see the Amount input field
And I should enter "GDQ5NT344AS6VVKG7B3IWRO7Y73BVRDOEVVVJ4ERBW5TXTWX7R4O4IA2" in the xlm Recipient Address input field
And I should enter "0.00001" in the Amount input field
And I click "Send" button on xlm send page
And I see "Transaction successful!" popup on screen

@VerifyCopyPasteFunctionality
Scenario: Verify copy-paste functionality in Recipient Address field
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
And I should see available balance
Then I should see the Recipient Address input field
Then I should see the Amount input field
And I copy phrase "GDQ5NT344AS6VVKG7B3IWRO7Y73BVRDOEVVVJ4ERBW5TXTWX7R4O4IA2" in the input field with label Phrase using Paste button

@VerifyTransactionAppearsInTransactionHistory @31
Scenario: Verify transaction appears in Transaction History
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
And I should see available balance
Then I should see the Recipient Address input field
Then I should see the Amount input field
And I should enter "GDQ5NT344AS6VVKG7B3IWRO7Y73BVRDOEVVVJ4ERBW5TXTWX7R4O4IA2" in the xlm Recipient Address input field
And I should enter "0.000001" in the Amount input field
And I click "Send" button on xlm send page
Then I should see the "Transactions" header on the screen
And I should see the transaction history on the screen


#TC-23
@VerifyClickingOnBNBNavigatesToSendPage
Scenario: Verify clicking on BNB navigates to the Send page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I should see the "Send" header on the screen

#TC-24
@VerifyRecipientAddressInputFieldAndScannerIconOnBNB  @31
Scenario: Verify recipient address input field and scanner icon
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I should see the "Send" header on the screen
Then I should see the Scanner icon is displayed in the Recipient Address field


#TC-25
@VerifyScannerPermissionRequestPopupforBNB  @31
Scenario: Verify scanner permission request popup for BNB
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I should see the "Send" header on the screen
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I verify "Only this time" button after clicking scanner icon on screen
Then I verify "Don't allow" button after clicking scanner icon on screen


#TC-26
@VerifyDismissPopupforBNB  @31
Scenario: Verify clicking Don't Allow dismisses the popup for BNB
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I should see the "Send" header on the screen
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I verify "Only this time" button after clicking scanner icon on screen
Then I verify "Don't allow" button after clicking scanner icon on screen
Then I click "Don't allow" button after clicking scanner icon on screen


#TC-30
@VerifyerrorWithoutSendingAmount @31
Scenario: Verify error when sending without amount
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
Then I should see the Recipient Address input field
Then I should see the Amount input field
And I should enter "GDQ5NT344AS6VVKG7B3IWRO7Y73BVRDOEVVVJ4ERBW5TXTWX7R4O4IA2" in the xlm Recipient Address input field
And I should enter "0.0" in the Amount input field
And I click "Send" button on xlm send page
#And I see "Invalid Amount" popup on screen



#TC-33
@VerifyClickingSendButtonOpensChooseWalletPopup
Scenario: Verify that clicking on the Send button opens the "Choose Wallet" popup
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen


#TC-34
@VerifyClickingOnEthereumNavigatesToEthereumPage
Scenario: Verify that clicking on Ethereum navigates to the Send page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I should see the "Send" header on the screen


#TC-35
@VerifyRecipientAddressInputFieldAndScannerIconOnETH
Scenario: Verify recipient address input field and scanner icon
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I should see the "Send" header on the screen
Then I should see the Scanner icon is displayed in the Recipient Address field


#TC-36
@VerifyScannerPermissionRequestPopuponETH  @31
Scenario: Verify scanner permission request popup on ETH
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I click "While using the app" button after clicking scanner icon on screen
Then I should see Scan QR Code header on the screen


#TC-37
@VerifyBehaviorWhenCameraAccessIsDeniedOnETH  @31
Scenario: Verify behavior when camera access is denied on ETH
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I should see the Scanner icon is displayed in the Recipient Address field
Then I click on the Scanner icon
Then I verify "While using the app" button after clicking scanner icon on screen
Then I verify "Only this time" button after clicking scanner icon on screen
Then I verify "Don't allow" button after clicking scanner icon on screen
Then I click "Don't allow" button after clicking scanner icon on screen



#Self made TC
@VerifyErrorWhenEnteringInvalidRecipientAddressOnETH
Scenario: Verify error when entering invalid recipient address on ETH
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
When I click on Ethereum Wallet on choose wallet Screen
Then I should see the "Send" header on the screen
And I enter "0x05cBb7CbEEE7C8f1B2DBf0Bb4" in the input field with label "Recipient Address"
And I see "Please enter a valid address" popup on screen



#TC-44
@VerifyReceivePopupAppearsWithWalletOptions
Scenario: Verify Receive popup appears with wallet options
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "XLM" button on screen
Then I verify "BNB" button on screen
Then I verify "Ethereum" button on screen
Then I see "Please select a wallet type" text on screen

#TC-45
@VerifyNavigationtoReceiveXLMPage
Scenario: Verify navigation to Receive XLM page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I see "Recieve XLM" text on screen
Then I verify "Copy" button on screen
Then I verify "Share" button on screen

#TC-46
@VerifyNavigationtoReceiveBNBPage
Scenario: Verify navigation to Receive BNB page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I see "Recieve BNB" text on screen
Then I verify "Copy" button on screen
Then I verify "Share" button on screen

#TC-47
@VerifyNavigationtoReceiveETHPage
Scenario: Verify navigation to Receive ETH page
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "Ethereum" button on screen
Then I click "Ethereum" button on screen
Then I see "Recieve ETH" text on screen
Then I verify "Copy" button on screen
Then I verify "Share" button on screen


#TC-48
@VerifyCopyButtonFunctionalityforXLM
Scenario: Verify Copy button functionality for XLM
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I see "Recieve XLM" text on screen
Then I verify "Copy" button on screen
Then I click Copy icon on screen
Then I should see the Receive header on the screen


@VerifyCopiedAddressIsPastedforXLM    @31
Scenario: Verify copied address can be pasted for XLM
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "XLM" button on screen
Then I click "XLM" button on screen
Then I see "Recieve XLM" text on screen
Then I verify "Copy" button on screen
Then I click Copy icon on screen
Then I should see the Receive header on the screen
Then I click on wrong symbol
And I should see the Send button is displayed
When I click on the "Send" button
Then I should see the "Choose Wallet" header on the screen
Then I click "XLM" button on screen
Then I click Claim 5 XLM Now! button
Then I should see the "Send" header on the screen
Then I should see the Recipient Address input field
And I copy phrase "GC722VBZUWBLFMKO7W5B6EQLD5V5U3HPVT3K65TGPLDKMBY2TE6EAHR2" in the input field with label Phrase using Paste button

#TC-50
@VerifyShareFunctionality
Scenario: Verify Share button functionality for XLM
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "Ethereum" button on screen
Then I click "Ethereum" button on screen
Then I see "Recieve ETH" text on screen
Then I verify "Copy" button on screen
Then I verify "Share" button on screen
Then I click "Share" button on screen
Then I see "Copy" text on screen


#TC-52
@VerifyCopyButtonFunctionalityforBNB
Scenario: Verify Copy button functionality for BNB
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I see "Recieve BNB" text on screen
Then I verify "Copy" button on screen
Then I click Copy icon on screen
Then I should see the Receive header on the screen

#TC-54  
@VerifyShareFunctionalityforBNB
Scenario: Verify Share button functionality for BNB
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "BNB" button on screen
Then I click "BNB" button on screen
Then I see "Recieve BNB" text on screen
Then I verify "Copy" button on screen
Then I verify "Share" button on screen
Then I click "Share" button on screen
Then I see "Copy" text on screen


#TC-56
@VerifyCopyButtonFunctionalityforEthereum
Scenario: Verify Copy button functionality for Ethereum
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "Ethereum" button on screen
Then I click "Ethereum" button on screen
Then I see "Recieve ETH" text on screen
Then I verify "Copy" button on screen
Then I click Copy icon on screen
Then I should see the Receive header on the screen


#TC-58
@VerifyShareFunctionalityforETH
Scenario: Verify Share button functionality for ETH
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
Then I should be on homePage
When I click "Receive" button on screen
Then I should see the Receive header on the screen
Then I verify "Ethereum" button on screen
Then I click "Ethereum" button on screen
Then I see "Recieve ETH" text on screen
Then I verify "Copy" button on screen
Then I verify "Share" button on screen
Then I click "Share" button on screen
Then I see "Copy" text on screen