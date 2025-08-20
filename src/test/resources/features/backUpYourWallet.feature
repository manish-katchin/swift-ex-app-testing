Feature: Back Up Your Wallet

  Verify the wallet backup warnings and options are displayed and actionable

  @backupWarningDisplayed
  Scenario: Display backup warning messages
    Given the app is launched
    And I have created a new wallet
    Then I should see "If I lose my private key, my funds will be lost" warning
    And I should see "If I share my private key, my funds can get stolen" warning

  @backupWarningOptions
  Scenario: Interact with backup warning options
    Given the app is launched
    And I have created a new wallet
    When I tap on the option for "If I lose my private key, my funds will be lost"
    Then I should see a response or action for losing private key
    When I tap on the option for "If I share my private key, my funds can get stolen"
    Then I should see a response or action for sharing private key

  @continueBackup
  Scenario: Continue after reading backup warnings
    Given the app is launched
    And I have created a new wallet
    When I tap on "Continue" button
    Then I should proceed to the next step in wallet backup
