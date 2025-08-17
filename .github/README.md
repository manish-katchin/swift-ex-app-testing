# 📱 SwiftEx Mobile Test Automation Pipeline

## 🚀 GitHub Actions Workflow Overview

This repository includes a comprehensive GitHub Actions pipeline for automated mobile testing across multiple platforms and environments.

## 🎯 How to Run Tests with Input Prompts

### Manual Execution (with Input Prompts)

1. **Navigate to Actions Tab**
   - Go to your repository in GitHub
   - Click on the **"Actions"** tab
   - Select **"SwiftEx Mobile Test Automation"** workflow

2. **Run Workflow**
   - Click **"Run workflow"** button
   - You'll see an interactive form with the following options:

### 📝 Input Parameters

#### 🤖 Platform Selection
Choose your test execution platform:
- **`androidSauce`** _(Recommended)_ - Android tests on SauceLabs cloud
- **`iOSSauce`** - iOS tests on SauceLabs cloud  
- **`android`** - Local Android emulator (GitHub runner)
- **`ios`** - Local iOS simulator _(Limited support on Linux runners)_

#### 🏷️ Test Tags
Select predefined test suites or use custom tags:
- **`@createPin`** - PIN creation functionality tests
- **`@pinValidationSuccess`** - Successful PIN validation tests
- **`@pinValidationFailed`** - Failed PIN validation tests
- **`@smoke`** - Quick smoke tests
- **`custom`** - Enter your own tags in the next field

#### ✏️ Custom Tags _(Optional)_
If you selected "custom" above, enter your custom Cucumber tags:
```
Examples:
@createPin and @smoke
@pinValidationSuccess or @pinValidationFailed
not @slow
```

#### ☁️ SauceLabs Device Selection
Choose your cloud testing device (for SauceLabs execution):
- **Android Devices:**
  - Google Pixel 4 GoogleAPI Emulator _(Default)_
  - Google Pixel 6 GoogleAPI Emulator
  - Samsung Galaxy S22 GoogleAPI Emulator

- **iOS Devices:**
  - iPhone 14 Simulator
  - iPhone 13 Simulator
  - iPad Pro (12.9 inch) (6th generation) Simulator

#### 📱 Platform Version
Select the OS version for your tests:
- `12.0` (Android 12, iOS 12)
- `13.0` (Android 13, iOS 13)
- `14.0` (Android 14, iOS 14)
- `15.0` (iOS 15)
- `16.0` (iOS 16)

#### 💻 Local Device Name _(For Local Execution Only)_
Device identifier for local testing:
- `emulator-5554` _(Default Android emulator)_
- `iPhone 14` _(iOS simulator)_
- Custom device name

#### 🌍 Environment Selection
- **`dev`** - Development environment _(Default)_
- **`staging`** - Staging environment
- **`prod`** - Production environment

#### ⚡ Advanced Options
- **📊 Generate Allure Report** - Create detailed test reports _(Default: Yes)_
- **⚡ Enable Parallel Execution** - Run tests in parallel _(Default: No)_

## 🔧 Setup Requirements

### 1. Repository Secrets Configuration
Add these secrets in **Settings > Secrets and Variables > Actions**:

```
SAUCELABS_USERNAME=your_saucelabs_username
SAUCELABS_ACCESS_KEY=your_saucelabs_access_key
```

### 2. SauceLabs Account Setup
1. Sign up at [SauceLabs](https://saucelabs.com/)
2. Get your username and access key from your account settings
3. Add them as GitHub repository secrets

## 📊 Automated Triggers

### Automatic Execution
The pipeline runs automatically on:

- **Push to branches:** `pin-creation`, `main`, `develop`
- **Pull Requests** to these branches
- **Nightly Schedule** (2:00 AM UTC daily)

### Default Settings for Auto-runs
- **Platform:** `androidSauce` (SauceLabs cloud)
- **Tags:** `@createPin`
- **Device:** `Google Pixel 4 GoogleAPI Emulator`
- **Environment:** `dev`

## 📈 Test Results & Reports

### Artifacts Generated
After each test run, the following artifacts are available:

1. **📊 Allure Reports** - Interactive test reports with:
   - Test execution timeline
   - Screenshots on failures
   - Step-by-step execution details
   - Environment information

2. **📄 Surefire Reports** - Standard Maven test reports
3. **🖼️ Screenshots** - Captured on test failures
4. **📋 Raw Results** - JSON and XML test results

### Accessing Results
1. Go to the completed workflow run
2. Scroll down to **"Artifacts"** section
3. Download the relevant report package
4. Extract and open `index.html` for Allure reports

## 🎯 Example Execution Scenarios

### Scenario 1: Quick Smoke Test on Cloud
```
Platform: androidSauce
Tags: @smoke
Device: Google Pixel 4 GoogleAPI Emulator
Environment: dev
```

### Scenario 2: Comprehensive PIN Testing
```
Platform: androidSauce  
Tags: @createPin
Device: Samsung Galaxy S22 GoogleAPI Emulator
Platform Version: 13.0
Environment: staging
```

### Scenario 3: Cross-Platform Testing
Run the pipeline twice with:
1. `androidSauce` + `@pinValidationSuccess`
2. `iOSSauce` + `@pinValidationSuccess`

### Scenario 4: Custom Tag Testing
```
Platform: androidSauce
Tags: custom
Custom Tags: @createPin and not @slow
Device: Google Pixel 6 GoogleAPI Emulator
```

## 🚨 Troubleshooting

### Common Issues

**❌ "Context access might be invalid" warnings**
- These are expected if secrets haven't been added yet
- Add the required SauceLabs secrets to resolve

**❌ Test failures on local execution**
- Android emulator startup can be slow on GitHub runners
- Prefer cloud execution (`androidSauce`/`iOSSauce`) for reliability

**❌ SauceLabs authentication errors**
- Verify your SauceLabs username and access key
- Ensure secrets are properly configured in GitHub

### Getting Help

1. **Check Workflow Logs** - Detailed execution logs in Actions tab
2. **Review Artifacts** - Download test reports for failure analysis  
3. **Local Testing** - Run tests locally first to isolate issues
4. **SauceLabs Dashboard** - Check your SauceLabs account for session details

## 🔄 CI/CD Integration

### Branch Protection
Consider adding branch protection rules that require:
- ✅ Status check: "Mobile Tests - androidSauce"
- ✅ Up-to-date branches before merging

### PR Comments
The pipeline automatically adds test result comments to pull requests with:
- ✅/❌ Test status
- 🔗 Links to detailed reports
- 📊 Execution summary

## 📚 Additional Resources

- **[Cucumber Documentation](https://cucumber.io/docs/)**
- **[SauceLabs Platform Configurator](https://saucelabs.com/platform/platform-configurator)**
- **[Appium Documentation](https://appium.io/docs/)**
- **[Allure Reports](https://docs.qameta.io/allure/)**

---

🎉 **Happy Testing!** Your SwiftEx mobile app testing pipeline is ready to deliver quality assurance at scale.
