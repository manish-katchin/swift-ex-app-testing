Feature: Create New Wallet

  Verify the app allows the user to create a new wallet after PIN setup

  @createWalletSuccess
  Scenario: Create a new wallet after PIN setup
    Given the app is launched
    And I have set up a valid PIN
    When I tap on "CREATE A NEW WALLET" button
    Then I should see the backup wallet screen
    And I should see "If I lose my private key, my funds will be lost" warning
    And I should see "If I share my private key, my funds can get stolen" warning
    And I should see the "Continue" button

  # @importWallet
  # Scenario: Import an existing wallet
  #   Given the app is launched
  #   And I have set up a valid PIN
  #   When I tap on "Import Wallet" button
  #   Then I should see the import wallet screen
  #   And I should see "Import Wallet" title
  #   And I should see "Multi-Chain Wallet" option
  #   And I should see "Binance Smart Chain" option
  #   And I should see "Ethereum" option

  # @useDefaultWallet
  # Scenario: Use default wallet
  #   Given the app is launched
  #   And I have set up a valid PIN
  #   When I tap on "Use default wallet" button
  #   Then I should see the biometric authentication screen
  #   And I should see "Activate biometric authentication" title
  #   And I should see "Make your wallet even more secure with biometric login." description
  #   And I should see "Use your fingerprint or facial recognition for fast and secure access." info
  #   And I should see the "Cancel" button
  #   And I should see the "Continue" button
