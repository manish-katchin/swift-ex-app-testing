@Regression
Feature: Create PIN

Verify the app launches and forces the user to set a PIN before using the app
@pinValidationSuccess @createPin  @11
Scenario: Setup PIN on first launch
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I verify "CREATE A NEW WALLET" button on screen


@pinValidationFailed  @11
Scenario Outline: PIN Validation with incorrect confirmation
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123465"
Then I should see "PIN did not match. Please try again."


#Test sheet test cases
@VerifyConfirmationPage
Scenario: Verify that the PIN accepts only 6 digits and proceeds to confirmation only when 6 digits are entered.
Given the app is launched
When I enter a new PIN "12345"
Then I am on pin page

@verifyPopupOnClickingFingerIdOnEnterPin
Scenario: Verify that the user must re-enter the PIN for confirmation
Given the app is launched
When I click on FingerPrint Sensor 
And I should see "Enable biometrics in SwiftEx app settings."


@verifyPopupOnClickingFingerIdOnReEnterPin
Scenario: Verify that when clicking on Finger ID on the "Re-enter Your PIN" page, the same error appears as in the PIN creation screen.
Given the app is launched
When I enter a new PIN "123456"
When I click on FingerPrint Sensor 
And I should see "Enable biometrics in SwiftEx app settings."

@VerifyCannotProceedWithoutSettingPin
Scenario: Verify that the user cannot proceed without setting up a PIN.
Given the app is launched
Then I am on pin page
When I enter a new PIN "1234"
Then I am on pin page
When I enter a new PIN "123456"


@VerifyNavigationToCreateOrImportWalletScreen
Scenario: Verify that after successfully setting up the PIN, the app navigates to the "Create Wallet" or "Import Wallet" screen.
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
Then I verify "Import Wallet" button on screen


@VerifyReEnterPinPage
Scenario: Verify that the user must re-enter the PIN for confirmation
Given the app is launched
When I enter a new PIN "123456"
Then I see "Please Re-enter your pin" text on screen
And I confirm the PIN "123455"
Then I am on pin page

