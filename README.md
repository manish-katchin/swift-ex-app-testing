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

## Key Features

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

### Command Line Options

#### Run All Tests
```bash
mvn test
```

#### Run Specific Tags
```bash
# Run PIN creation tests
mvn test -Dcucumber.filter.tags="@createPin"

# Run PIN validation tests
mvn test -Dcucumber.filter.tags="@pinValidation"

# Run multiple tags
mvn test -Dcucumber.filter.tags="@createPin or @pinValidation"
```

#### Platform-Specific Execution
```bash
# Android execution
mvn test -Dplatform=android \
         -DappiumServer=http://127.0.0.1:4723/wd/hub \
         -DdeviceName="Android Emulator" \
         -Dapp=src/test/resources/apps/android/android.apk

# iOS execution
mvn test -Dplatform=ios \
         -DappiumServer=http://127.0.0.1:4723/wd/hub \
         -DdeviceName="iPhone 14" \
         -Dudid=<device_udid> \
         -DbundleId=com.swiftEx.app
```

#### Parallel Execution
```bash
mvn test -Dcucumber.execution.parallel.enabled=true \
         -Dcucumber.execution.parallel.config.strategy=dynamic
```

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