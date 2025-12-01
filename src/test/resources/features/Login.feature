@LoginScenarios @Regression  
Feature: Login
As a user I want to log in to the app and access my wallet

@VerificationOfProfilePage  @1
Scenario: Verifiy Profile page UI elements
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
Scenario: Verify Login Page Elements
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


@VerifyLoginErrorMessage @1
Scenario: To Verify Login Error Message
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


@VerifyLogin @1
Scenario: To verify log in from Profile screen and sees updated email after  setup
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
When I enter "sonushafauddin@gmail.com" into the Email input field
And I enter "Test@1234" into the Password input field
And I click the Login button
When I click on the "Profile" icon on Trade Wallet page
Then I should see the Profile header
And I should see the user entered email "sonushafauddin@gmail.com"


@VerifyRegisternowPage @rerun
Scenario: To verify log in from Profile screen 
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
When I click on register now option
Then I should see "Create your exchange account" header on Register page
And I should see the First name input field
And I should see the Last name input field
And I should see the Email input field on registration page
And I should see the Password input field on registration page
And I should see the Re-Password input field on registration page


@VerifyInvalidCaseForRegisterNowPage 
Scenario: To verify log in from Profile screen and see error message on invalid registration
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
When I click on register now option
Then I should see "Create your exchange account" header on Register page
And I should see the First name input field
And I should see the Last name input field
And I should see the Email input field on registration page
And I should see the Password input field on registration page
And I should see the Re-Password input field on registration page
When I enter "Ram" into the First name input field
And I enter "Kumar" into the Last name input field
Then I click on the "Create my Account" button
Then I should see error "Email must be a valid Email" on registration page

@VerifyValidCaseForRegisterNowPage
Scenario: To verify log in from Profile screen and sees updated email after setup
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
When I click on register now option
Then I should see "Create your exchange account" header on Register page
And I should see the First name input field
And I should see the Last name input field
And I should see the Email input field on registration page
And I should see the Password input field on registration page
And I should see the Re-Password input field on registration page
When I enter "Ram" into the First name input field
And I enter "Kumar" into the Last name input field
And I enter a random email into the Email input field on registration page
And I enter "Test@123" into the Password input field on registration page
And I enter "Test@123" into the Re-Password input field on registration page
Then I click on the "Create my Account" button
And I enter the verification code "987654" on verification page
Then I click on Verify Button on Verify Page
Then I should see the Trade Wallet header


@VerifyForgotPasscodeflow
Scenario: To verify reset password flow
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
When I click on Forgot Password? option
Then I should see "Recover your account" header on Forgot Password page
And I enter "sonushafauddin@gmail.com" into the Email input field on Forgot Password page
Then I click on Verify Button on Verify Page
And I enter the verification code "987654" on verification step
When I enter a new Password "Test@1234" into the New Password input field on Verification Page
Then I click on Verify Button on Verify Page
Then I should see "Hi, Welcome back!" header

@VerifyloginafterResetPassword
Scenario: To verify log in from Profile screen after newpassword setup and sees updated email
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
When I click on Forgot Password? option
Then I should see "Recover your account" header on Forgot Password page
And I enter "sonushafauddin@gmail.com" into the Email input field on Forgot Password page
Then I click on Verify Button on Verify Page
And I enter the verification code "987654" on verification step
When I enter a new Password "Test@1234" into the New Password input field on Verification Page
Then I click on Verify Button on Verify Page
Then I should see "Hi, Welcome back!" header
When I enter "sonushafauddin@gmail.com" into the Email input field
And I enter "Test@1234" into the Password input field
And I click the Login button


