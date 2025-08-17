# SwiftEx Mobile Testing Framework

A comprehensive mobile automation testing framework built with Java, Cucumber, and Appium for testing mobile applications on Android and iOS platforms.

## Project Architecture

```
swiftExMobileApp/
├── src/
│   ├── main/java/com/swiftEx/mobileAutomationFramework/
│   │   ├── driver/                     # Driver management
│   │   │   ├── DriverFactory.java      # Appium driver creation
│   │   │   └── MinimalDriverFactory.java # Lightweight driver factory
│   │   ├── pages/                      # Page Object Model
│   │   │   ├── BasePage.java          # Base page with common methods
│   │   │   └── PinCreationPage.java   # PIN creation functionality
│   │   ├── steps/                     # Step definitions for main logic
│   │   │   └── PinCreationStepDefinitions.java
│   │   └── utils/                     # Utility classes
│   │       ├── ElementActions.java     # Centralized UI interactions
│   │       ├── LocatorLoader.java      # YAML locator loading
│   │       ├── LocatorUtils.java       # Locator utilities
│   │       └── UiAutomator2Recovery.java # Recovery mechanisms
│   └── test/java/com/swiftEx/mobileAutomationFramework/
│       ├── runner/                    # Test runners
│       │   └── TestRunner.java        # Cucumber test runner
│       ├── steps/                     # Cucumber step definitions
│       │   ├── Hooks.java            # Setup and teardown
│       │   └── PinCreationStep.java  # PIN creation steps
│       ├── support/                  # Test support utilities
│       │   └── BaseTest.java         # Base test setup
│       └── utils/                    # Test utilities
│           └── AllureUtils.java      # Allure reporting utilities
└── src/test/resources/
    ├── features/                     # Cucumber feature files
    │   └── pinCreation.feature       # PIN creation scenarios
    ├── locators/                     # Element locators
    │   ├── android/                  # Android-specific locators
    │   │   └── pinCreation.yaml
    │   └── ios/                      # iOS-specific locators
    │       └── sample_page.yaml
    ├── apps/                         # Application binaries
    │   ├── android/                  # Android APK files
    │   └── ios/                      # iOS APP/IPA files
    └── *.properties                  # Configuration files
```

## 📱 Automated PIN Creation Testing

This framework includes comprehensive automated testing for mobile PIN creation functionality with both **positive** and **negative** test scenarios.

### 🎯 Test Scenarios Covered

#### ✅ **PIN Validation Success (@pinValidationSuccess)**
```gherkin
@pinValidationSuccess @createPin 
Scenario: Setup PIN on first launch
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "CREATE A NEW WALLET" button on screen
```
**What it validates:**
- App launches successfully
- User can enter a 6-digit PIN
- PIN confirmation matches the original PIN
- Successful PIN creation leads to wallet creation screen

#### ❌ **PIN Validation Failure (@pinValidationFailed)**
```gherkin
@pinValidationFailed @createPin
Scenario Outline: PIN Validation with incorrect confirmation
  Given the app is launched
  When I enter a new PIN "<pin>"
  And I confirm the PIN "<confirmPin>"
  Then I should see "<result>"

  Examples:
  | pin    | confirmPin | result                               |
  | 123456 | 654321     | PIN did not match. Please try again. |
```
**What it validates:**
- App properly validates PIN confirmation
- Error message displays when PINs don't match
- User is prompted to try again after failed validation
- App maintains security by preventing mismatched PIN acceptance

### 🚀 Running PIN Creation Tests

#### **Android PIN Testing**
```bash
# Run all PIN creation scenarios on Android
mvn test -Dplatform=android -Dcucumber.filter.tags="@createPin"

# Run only successful PIN creation
mvn test -Dplatform=android -Dcucumber.filter.tags="@pinValidationSuccess"

# Run only failed PIN validation
mvn test -Dplatform=android -Dcucumber.filter.tags="@pinValidationFailed"
```

#### **iOS PIN Testing**
```bash
# Run all PIN creation scenarios on iOS  
mvn test -Dplatform=ios -Dcucumber.filter.tags="@createPin"

# Run only successful PIN creation on iOS
mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationSuccess"

# Run only failed PIN validation on iOS
mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationFailed"
```

#### **Cross-Platform PIN Testing**
```bash
# Test PIN functionality on both platforms
mvn test -Dplatform=android -Dcucumber.filter.tags="@createPin"
mvn test -Dplatform=ios -Dcucumber.filter.tags="@createPin"

# Comprehensive PIN security testing
mvn test -Dcucumber.filter.tags="@createPin" # Android default
mvn test -Dplatform=ios -Dcucumber.filter.tags="@createPin" # Then iOS
```

### 🔒 Security Testing Features

- **PIN Mismatch Detection**: Validates that non-matching PINs are rejected
- **Error Message Verification**: Ensures proper user feedback on failures  
- **Fresh App State**: Each scenario starts with a clean app launch
- **Cross-Platform Consistency**: Same PIN logic tested on both Android and iOS
- **Recovery Mechanisms**: Framework handles app crashes and UI recovery

Key Features

### 🏗️ Architecture Patterns
- **Page Object Model (POM)**: Organized page classes with reusable methods
- **Centralized Element Actions**: `ElementActions` utility for consistent UI interactions
- **YAML-based Locators**: Platform-specific locator management
- **Recovery Mechanisms**: Robust UiAutomator2 recovery for stability
- **Allure Integration**: Rich HTML reporting with screenshots

### 🔧 Framework Components

#### Driver Management
- **DriverFactory**: Creates Appium drivers for Android/iOS
- **Platform Detection**: Automatic platform-specific configurations
- **Session Management**: Proper driver lifecycle handling

#### Element Interactions
- **ElementActions**: Centralized utility for all UI interactions
  - Click, double-click, send keys
  - Text retrieval and verification
  - Element visibility and state checks
  - Dropdown selections and swipe actions

#### Locator Management
- **YAML-based**: Platform-specific locator files
- **Dynamic Resolution**: Support for YAML maps, By objects, and string locators
- **Fallback Mechanisms**: Multiple locator strategies for reliability

## Quick Start

### Prerequisites
- Java 8 or higher
- Maven 3.6+
- Appium Server 2.0+
- Android SDK (for Android testing)
- Xcode (for iOS testing on macOS)

### Installation
```bash
git clone https://github.com/manish-katchin/swift-ex-app-testing.git
cd swift-ex-app-testing
mvn clean compile
```

### Configuration
1. **Place your app binaries:**
   - Android APKs: `src/test/resources/apps/android/`
   - iOS Apps: `src/test/resources/apps/ios/`

2. **Update configuration files:**
   - `src/test/resources/framework.properties`
   - `src/test/resources/allure.properties`

## Running Tests

### 📱 Cross-Platform Testing Support

This framework supports both **Android** and **iOS** platforms with easy switching capabilities.

#### Platform Configuration
The framework is configured with Android as default in `src/test/resources/framework.properties`:
```properties
# Default: Android Configuration
platformName=Android
appium.deviceName=Android Emulator
appium.app=src/test/resources/apps/android/android.apk

# iOS Configuration (uncomment for iOS testing)
# platformName=iOS
# appium.deviceName=iPhone 15 Pro
# appium.app=src/test/resources/apps/ios/SwiftExApp.app
```

### 🚀 Platform Switching Commands

#### **Android Testing (Default)**
```bash
# Run with default Android configuration
mvn test

# Explicit Android platform
mvn test -Dplatform=android

# Android with specific tags
mvn test -Dplatform=android -Dcucumber.filter.tags="@createPin"

# Android PIN creation tests
mvn test -Dplatform=android -Dcucumber.filter.tags="@pinValidationSuccess"
```

#### **iOS Testing**
```bash
# Switch to iOS platform
mvn test -Dplatform=ios

# iOS with specific tags
mvn test -Dplatform=ios -Dcucumber.filter.tags="@createPin"

# iOS PIN creation tests
mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationSuccess"

# iOS with custom device
mvn test -Dplatform=ios -DdeviceName="iPhone 14 Pro"
```

#### **🌐 SauceLabs Cloud Testing**
Execute tests on SauceLabs cloud devices for broader coverage and parallel execution.

##### **SauceLabs Android Testing**
```bash
# Android on SauceLabs cloud
mvn test -Dplatform=androidSauce -Dcucumber.filter.tags="@createPin"

# Android PIN creation on SauceLabs
mvn test -Dplatform=androidSauce -Dcucumber.filter.tags="@pinValidationSuccess"

# Android with SauceLabs credentials
mvn test -Dplatform=androidSauce \
         -Dsaucelabs.username=your_username \
         -Dsaucelabs.accessKey=your_access_key \
         -Dcucumber.filter.tags="@createPin"

# Custom Android device on SauceLabs
mvn test -Dplatform=androidSauce \
         -Dsaucelabs.android.deviceName="Samsung Galaxy S22 GoogleAPI Emulator" \
         -Dsaucelabs.android.platformVersion="12.0"
```

##### **SauceLabs iOS Testing**
```bash
# iOS on SauceLabs cloud
mvn test -Dplatform=iOSSauce -Dcucumber.filter.tags="@createPin"

# iOS PIN creation on SauceLabs
mvn test -Dplatform=iOSSauce -Dcucumber.filter.tags="@pinValidationSuccess"

# iOS with SauceLabs credentials
mvn test -Dplatform=iOSSauce \
         -Dsaucelabs.username=your_username \
         -Dsaucelabs.accessKey=your_access_key \
         -Dcucumber.filter.tags="@createPin"

# Custom iOS device on SauceLabs
mvn test -Dplatform=iOSSauce \
         -Dsaucelabs.ios.deviceName="iPhone 14 Pro Simulator" \
         -Dsaucelabs.ios.platformVersion="16.4"
```

##### **🔧 SauceLabs Configuration**
Before running SauceLabs tests, configure your credentials:

```bash
# Method 1: Environment Variables (Recommended)
export SAUCE_USERNAME=your_username
export SAUCE_ACCESS_KEY=your_access_key

# Method 2: Command Line Parameters
mvn test -Dplatform=androidSauce \
         -Dsaucelabs.username=your_username \
         -Dsaucelabs.accessKey=your_access_key

# Method 3: Update framework.properties
# Edit src/test/resources/framework.properties:
# saucelabs.username=your_username
# saucelabs.accessKey=your_access_key
```

##### **📱 SauceLabs App Upload**
Upload your apps to SauceLabs storage before testing:

```bash
# Upload Android APK
curl -u "$SAUCE_USERNAME:$SAUCE_ACCESS_KEY" \
     -X POST \
     -F "payload=@src/test/resources/apps/android/android.apk" \
     -F "name=android.apk" \
     "https://api.us-west-1.saucelabs.com/rest/v1/storage/upload"

# Upload iOS App
curl -u "$SAUCE_USERNAME:$SAUCE_ACCESS_KEY" \
     -X POST \
     -F "payload=@src/test/resources/apps/ios/SwiftExApp.app" \
     -F "name=SwiftExApp.app" \
     "https://api.us-west-1.saucelabs.com/rest/v1/storage/upload"
```

##### **🚀 SauceLabs Quick Start Script**
Use the provided script for easy SauceLabs testing:

```bash
# Make script executable
chmod +x run-saucelabs-tests.sh

# Run with credentials as parameters
./run-saucelabs-tests.sh your_username your_access_key

# Run with environment variables
export SAUCE_USERNAME=your_username
export SAUCE_ACCESS_KEY=your_access_key
./run-saucelabs-tests.sh
```

### 🎯 Test Execution Examples

#### **PIN Creation Testing**
```bash
# Android PIN creation (success scenario)
mvn test -Dplatform=android -Dcucumber.filter.tags="@pinValidationSuccess"

# iOS PIN creation (success scenario)  
mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationSuccess"

# Android PIN validation (failure scenario)
mvn test -Dplatform=android -Dcucumber.filter.tags="@pinValidationFailed"

# iOS PIN validation (failure scenario)
mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationFailed"
```

#### **Cross-Platform Test Suite**
```bash
# Run all PIN creation tests on Android
mvn test -Dplatform=android -Dcucumber.filter.tags="@createPin"

# Run the same tests on iOS
mvn test -Dplatform=ios -Dcucumber.filter.tags="@createPin"

# Run multiple scenarios
mvn test -Dplatform=android -Dcucumber.filter.tags="@createPin or @pinValidation"
```

### 📋 Platform Requirements

#### **Android Setup**
```bash
# 1. Check connected Android devices
adb devices

# 2. Start Android emulator (if needed)
emulator -avd Pixel_4_API_30

# 3. Verify APK file exists
ls src/test/resources/apps/android/android.apk

# 4. Run Android tests
mvn test -Dplatform=android
```

#### **iOS Setup** (macOS only)
```bash
# 1. Check available iOS simulators
xcrun simctl list devices

# 2. Start iOS simulator (if needed)
xcrun simctl boot "iPhone 15 Pro"

# 3. Verify iOS app file exists
ls src/test/resources/apps/ios/SwiftExApp.app

# 4. Run iOS tests
mvn test -Dplatform=ios
```

### 🔄 Advanced Platform Switching

#### **Environment Variables**
```bash
# Set platform via environment variable
export PLATFORM=ios
mvn test

# Override with command line
PLATFORM=android mvn test -Dplatform=ios  # iOS wins
```

#### **Configuration File Method**
To permanently switch platforms, edit `src/test/resources/framework.properties`:

**For Android (default):**
```properties
platformName=Android
appium.deviceName=Android Emulator
appium.platformVersion=12
appium.automationName=UiAutomator2
appium.app=src/test/resources/apps/android/android.apk
appPackage=com.app.swiftEx.app
```

**For iOS:**
```properties
platformName=iOS
appium.deviceName=iPhone 15 Pro
appium.platformVersion=17.0
appium.automationName=XCUITest
appium.app=src/test/resources/apps/ios/SwiftExApp.app
appium.bundleId=com.app.swiftEx.ios
```

### 🎪 Parallel Execution
```bash
# Run tests in parallel (single platform)
mvn test -Dcucumber.execution.parallel.enabled=true \
         -Dcucumber.execution.parallel.config.strategy=dynamic \
         -Dplatform=android

# Sequential execution with fresh driver per scenario (recommended)
mvn test -Dplatform=android -Dcucumber.filter.tags="@createPin"
```

### 📚 Quick Command Reference

| **Scenario** | **Android (Local)** | **iOS (Local)** | **Android (SauceLabs)** | **iOS (SauceLabs)** |
|--------------|---------------------|-----------------|-------------------------|---------------------|
| **All Tests** | `mvn test` | `mvn test -Dplatform=ios` | `mvn test -Dplatform=androidSauce` | `mvn test -Dplatform=iOSSauce` |
| **PIN Creation** | `mvn test -Dcucumber.filter.tags="@createPin"` | `mvn test -Dplatform=ios -Dcucumber.filter.tags="@createPin"` | `mvn test -Dplatform=androidSauce -Dcucumber.filter.tags="@createPin"` | `mvn test -Dplatform=iOSSauce -Dcucumber.filter.tags="@createPin"` |
| **PIN Success** | `mvn test -Dcucumber.filter.tags="@pinValidationSuccess"` | `mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationSuccess"` | `mvn test -Dplatform=androidSauce -Dcucumber.filter.tags="@pinValidationSuccess"` | `mvn test -Dplatform=iOSSauce -Dcucumber.filter.tags="@pinValidationSuccess"` |
| **PIN Failure** | `mvn test -Dcucumber.filter.tags="@pinValidationFailed"` | `mvn test -Dplatform=ios -Dcucumber.filter.tags="@pinValidationFailed"` | `mvn test -Dplatform=androidSauce -Dcucumber.filter.tags="@pinValidationFailed"` | `mvn test -Dplatform=iOSSauce -Dcucumber.filter.tags="@pinValidationFailed"` |
| **Custom Device** | `mvn test -DdeviceName="Pixel 6"` | `mvn test -Dplatform=ios -DdeviceName="iPhone 14 Pro Max"` | `mvn test -Dplatform=androidSauce -Dsaucelabs.android.deviceName="Samsung Galaxy S22"` | `mvn test -Dplatform=iOSSauce -Dsaucelabs.ios.deviceName="iPhone 14 Pro"` |

### Test Execution Examples

```bash
# Run without failing on no tests
mvn test -DfailIfNoTests=false

# Run with specific test runner
mvn test -Dtest=TestRunner -DfailIfNoTests=false

# Run with custom thread count
mvn test -Dthread.count=3
```

## Reporting

### Allure Reports
```bash
# Generate Allure report
mvn allure:report

# Serve Allure report (opens browser automatically)
mvn allure:serve

# Clean previous results
mvn allure:clean
```

### Cucumber Reports
Reports are automatically generated in:
- `target/cucumber-reports/` - Cucumber HTML reports
- `target/allure-results/` - Allure test results
- `target/surefire-reports/` - Maven Surefire reports

## Test Data Management

### Feature Files
Located in `src/test/resources/features/`
```gherkin
@createPin
Scenario: Setup PIN on first launch
  Given the app is launched
  When I enter a new PIN "123456"
  And I confirm the PIN "123456"
  Then I verify "CREATE A NEW WALLET" button on screen
```

### Locator Files
Platform-specific YAML files in `src/test/resources/locators/`
```yaml
# android/pinCreation.yaml
digit1:
  id: com.app.swiftEx.app:id/digit_1
  xpath: //android.view.ViewGroup[@content-desc="1"]

incorrectPIN:
  id: com.app.swiftEx.app:id/snackbar_text
```

## Development Guidelines

### Adding New Tests
1. Create feature file in `src/test/resources/features/`
2. Add corresponding step definitions in `src/test/java/.../steps/`
3. Create page objects in `src/main/java/.../pages/`
4. Add locators in `src/test/resources/locators/`

### Page Object Pattern
```java
public class NewPage extends BasePage {
    private ElementActions elementActions;
    
    public NewPage(WebDriver driver) {
        super(driver);
        this.elementActions = new ElementActions(driver);
    }
    
    public void clickElement() {
        elementActions.click(getLocator("elementKey"));
    }
}
```

### Using ElementActions
```java
// Click element
elementActions.click(getLocator("buttonKey"));

// Send text
elementActions.sendKeys(getLocator("inputKey"), "test data");

// Verify text
elementActions.verifyText(getLocator("labelKey"), "expected text");

// Get text
String actualText = elementActions.getText(getLocator("textKey"));
```

## CI/CD Integration

### GitHub Actions (Recommended)
```yaml
name: Mobile Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Run tests
        run: mvn test -Dcucumber.filter.tags="@smoke"
```

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test -Dcucumber.filter.tags="@regression"'
            }
        }
        stage('Report') {
            steps {
                allure includeProperties: false, 
                       results: [[path: 'target/allure-results']]
            }
        }
    }
}
```

## Configuration Files

### framework.properties
```properties
# Appium Configuration
appium.server=http://127.0.0.1:4723/wd/hub
implicit.wait=10
explicit.wait=20

# Platform Configuration
platform=android
device.name=Android Emulator
app.path=src/test/resources/apps/android/android.apk
```

### allure.properties
```properties
allure.results.directory=target/allure-results
allure.link.issue.pattern=https://jira.company.com/browse/{}
allure.link.tms.pattern=https://testmanagement.company.com/{}
```

## Troubleshooting

### Common Issues

#### Driver Not Found
```bash
# Ensure Appium server is running
appium --address 127.0.0.1 --port 4723
```

#### Element Not Found
- Check locators in YAML files
- Verify app state and timing
- Use explicit waits

#### Test Failures
- Check Allure reports for screenshots
- Review logs in `target/surefire-reports/`
- Verify device/emulator availability

### Debugging Tips
1. Enable verbose logging in `framework.properties`
2. Use `elementActions.waitForElement()` for timing issues
3. Take screenshots with `AllureUtils.attachScreenshot()`
4. Review UiAutomator2 recovery logs

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/new-feature`
5. Create Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For questions and support:
- Create an issue on GitHub
- Review the documentation in `/docs`
- Check existing discussions and solutions