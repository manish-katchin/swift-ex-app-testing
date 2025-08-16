Feature: Sample mobile interaction

  Verify the app launches and forces the user to set a PIN before using the app
  @pinValidationSuccess @createPin 
  Scenario: Setup PIN on first launch
    Given the app is launched
    When I enter a new PIN "123456"
    And I confirm the PIN "123456"
    Then I verify "CREATE A NEW WALLET" button on screen


  @pinValidationFailed  @createPin
  Scenario Outline: PIN Validation with incorrect confirmation
    Given the app is launched
    When I enter a new PIN "<pin>"
    And I confirm the PIN "<confirmPin>"
    Then I should see "<result>"

    Examples:
    | pin    | confirmPin | result                               |
    | 123456 | 654321     | PIN did not match. Please try again. |


# @walletCreation
# Scenario: Create new wallet after PIN setup
#   Given the app is launched
#   And I enter a new PIN "123456"
#   And I confirm the PIN "123456"
#   When I click on "CREATE A NEW WALLET" button
#   Then I verify wallet creation is successful