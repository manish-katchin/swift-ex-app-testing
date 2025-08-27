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
