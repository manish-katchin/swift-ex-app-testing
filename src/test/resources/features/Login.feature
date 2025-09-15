Feature: Login
As a user I want to log in to the app and access my wallet

@loginPage1
Scenario: As a user I want to log in to the app and access my wallet
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
And I should see the On-Off Ramp icon
And I should see the Transactions icon
And I should see the Profile icon
When I click on the "Profile" icon on Trade Wallet page
Then I should see the Profile header
And I should see Guest user header
And I should see the Login option
When I click on the Login option
Then I should see "Hi, Welcome back!" header
And I should see the Email input field
And I should see the Password input field
And I should see the Forgot Password? option
And I should see the Login button
When I enter "hunny@katchintech.com" into the Email input field
And I enter "Test@12345" into the Password input field
And I click the Login button
When I click on the "Profile" icon on Trade Wallet page
Then I should see the Profile header
And I should see the user entered email "hunny@katchintech.com"


@VerificationOfProfilePage
Scenario: As a user I want to log in to the app and access my wallet
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I click on the Exchange tab in bottom navigation
And I should see the Profile icon
When I click on the "Profile" icon on Trade Wallet page
Then I should see the Profile header
And I should see Guest user header
And I should see the Login option
And I should see "MyWalletLabel" on Profile page
And I should see "GuestModeMessage" on Profile page
And I should see "ActivatedSubscriptionLabel" on Profile page
And I should see "InvalidSubscriptionMessage" on Profile page


@VerifyLoginPageElements
Scenario: As a user I want to log in to the app and access my wallet
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Home icon
And I should see the On-Off Ramp icon
And I should see the Transactions icon
And I should see the Profile icon
When I click on the "Profile" icon on Trade Wallet page
Then I should see the Profile header
And I should see Guest user header
And I should see the Login option
When I click on the Login option
Then I should see "Hi, Welcome back!" header
And I should see the Email input field
And I should see the Password input field
And I should see the Forgot Password? option
And I should see the Login button


@VerifyLoginErrorMessage
Scenario: As a user I want to log in to the app and access my wallet
Given the app is launched
When I enter a new PIN "123456"
And I confirm the PIN "123456"
Then I should be on Create A new wallet Page
When I click Create a new wallet button
Then I should see the biometric authentication popup
When I handle the biometric authentication
And I click on the Exchange tab in bottom navigation
Then I should see the Trade Wallet header
And I should see the Profile icon
When I click on the "Profile" icon on Trade Wallet page
Then I should see the Profile header
And I should see Guest user header
And I should see the Login option
When I click on the Login option
Then I should see "Hi, Welcome back!" header
And I click the Login button
Then I should see login "Error" message
