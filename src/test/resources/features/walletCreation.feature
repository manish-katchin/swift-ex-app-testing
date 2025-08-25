Feature: Wallet Creation

  @createWallet
  Scenario: Create a new wallet and proceed to the private key page
  Given the app is launched
  When I enter a new PIN "123456"
  Then I should be on Create A new wallet Page
  When I click Create a new wallet button
  Then I should be on the "Backup your wallet now" screen
  When I select the option "If I lose my private keys, my funds will be lost"
  And I select the option "If I share my private key, my funds can be lost"
  And I click on the "Continue" button
  Then I should be on the "Private key" page
  When I enter account name "TestAccount" in the Account Name field
  And I click on the Next button
  And I verify and select the requested mnemonic words on Check Mnemonic page
  And I verify all mnemonic words are correctly selected
  When I click on the "Done" button
  Then I should see the biometric authentication popup
  When I handle the biometric authentication