# GitHub Actions Workflows for SwiftEx Mobile Testing

This repository contains GitHub Actions workflows for automated mobile testing of the SwiftEx application.

## 📋 Available Workflows

### 1. **SwiftEx Mobile Test Automation** (`mobile-tests.yml`)
Comprehensive workflow with full features including Allure reporting, matrix testing, and nightly runs.

### 2. **Quick Mobile Test** (`quick-test.yml`)
Simplified workflow for quick manual testing with minimal setup.

## 🚀 Manual Test Execution

### Using the Quick Test Workflow

1. Go to **Actions** tab in your GitHub repository
2. Select **"Quick Mobile Test"** workflow
3. Click **"Run workflow"** button
4. Configure the inputs:
   - **Platform**: Choose from `android`, `ios`, `androidSauce`, `iOSSauce`
   - **Device Name**: (Optional) Specify device for local tests
   - **Test Tags**: Cucumber tags to run (e.g., `@createPin`, `@pinValidationSuccess`)

### Using the Main Test Workflow

1. Go to **Actions** tab in your GitHub repository
2. Select **"SwiftEx Mobile Test Automation"** workflow
3. Click **"Run workflow"** button
4. Configure the inputs:
   - **Platform**: Test platform selection
   - **Device Name**: Device name for local execution
   - **Test Tags**: Cucumber tags to filter tests
   - **Test Environment**: Environment selection (dev/staging/prod)
   - **Generate Allure Report**: Enable/disable Allure reporting

## 🔐 Required GitHub Secrets

For SauceLabs cloud testing, add these secrets to your repository:

1. Go to **Settings** → **Secrets and variables** → **Actions**
2. Add the following secrets:
   - `SAUCELABS_USERNAME`: Your SauceLabs username
   - `SAUCELABS_ACCESS_KEY`: Your SauceLabs access key

## 📱 Platform Options

### Local Testing
- **android**: Run tests on local Android emulator
- **ios**: Run tests on local iOS simulator (limited on GitHub runners)

### Cloud Testing (SauceLabs)
- **androidSauce**: Run tests on SauceLabs Android devices
- **iOSSauce**: Run tests on SauceLabs iOS devices

## 🔧 Device Name Examples

### For Local Android Tests
```
emulator-5554
Pixel_4_API_33
Galaxy_S21_API_33
```

### For SauceLabs Tests
Device names are managed in your `framework.properties` file:
```
sauce.android.device.name=Google Pixel 4 GoogleAPI Emulator
sauce.ios.device.name=iPhone 12 Simulator
```

## 🏷️ Test Tag Examples

### Single Tag
```bash
@createPin
@pinValidationSuccess
@pinValidationFailed
@smoke
```

### Multiple Tags (AND operation)
```bash
@createPin and @smoke
```

### Multiple Tags (OR operation)
```bash
@pinValidationSuccess or @pinValidationFailed
```

### Exclude Tags
```bash
@createPin and not @slow
```

## 📊 Test Reports

### Surefire Reports
- Generated in `target/surefire-reports/`
- Downloaded as workflow artifacts
- Retention: 30 days

### Allure Reports
- Generated in `target/allure-report/`
- Interactive HTML reports
- Retention: 7 days
- Access via workflow artifacts

## ⚡ Automatic Triggers

### Push Triggers
Workflows automatically run on:
- Pushes to `main`, `develop`, `pin-creation` branches
- Changes to `src/`, `pom.xml`, or workflow files

### Pull Request Triggers
- Automatic testing on PRs to `main`, `develop`
- Results posted as PR comments

### Scheduled Runs
- Nightly comprehensive testing at 2 AM UTC
- Matrix execution across platforms and test suites

## 🛠️ Workflow Features

### Environment Configuration
- Java 11 setup with Maven caching
- Android SDK and emulator setup (for local tests)
- Appium server installation and startup
- SauceLabs credential management

### Test Execution
- Parameterized platform selection
- Custom device configuration
- Cucumber tag filtering
- Parallel execution support
- Failure handling and reporting

### Reporting & Artifacts
- Surefire test reports
- Allure interactive reports
- Screenshot capture on failures
- Comprehensive test summaries
- PR comment integration

## 🔄 Example Workflow Commands

The workflows generate Maven commands like:

### Local Android Test
```bash
mvn clean test \
  -Dplatform=android \
  -Dcucumber.filter.tags="@createPin" \
  -Dandroid.device.name="emulator-5554"
```

### SauceLabs Android Test
```bash
mvn clean test \
  -Dplatform=androidSauce \
  -Dcucumber.filter.tags="@pinValidationSuccess" \
  -Dsaucelabs.username="your-username" \
  -Dsaucelabs.accessKey="your-access-key"
```

## 📝 Tips & Best Practices

### For Development
1. Use **Quick Test** workflow for rapid iteration
2. Test locally first, then validate on SauceLabs
3. Use specific tags to run targeted test suites

### For CI/CD
1. Configure automatic triggers for main branches
2. Use comprehensive workflow for release testing
3. Monitor nightly runs for regression detection

### For Debugging
1. Check workflow logs for detailed execution info
2. Download artifacts for local analysis
3. Use Allure reports for visual test results

## 🚨 Troubleshooting

### Common Issues

1. **SauceLabs Authentication**
   - Verify secrets are correctly set
   - Check username format (usually starts with `oauth-`)

2. **Local Emulator Issues**
   - Android emulator may take time to start
   - Increase timeout if needed

3. **Test Failures**
   - Check device compatibility
   - Verify app installation paths
   - Review Appium server logs

### Getting Help

1. Check workflow execution logs
2. Review downloaded artifacts
3. Examine Allure reports for test details
4. Verify Maven dependencies and configuration

## 📈 Monitoring & Maintenance

### Regular Tasks
- Update Java/Node.js versions in workflows
- Refresh SauceLabs device configurations
- Monitor test execution times and optimize
- Review and update test tag strategies
- Maintain artifact retention policies

### Performance Optimization
- Use Maven dependency caching
- Optimize test parallelization
- Monitor SauceLabs usage and costs
- Regular cleanup of old workflow runs
