# Mobile Automation Framework

## Overview
* The Mobile automation Framework is providing a solid foundation for building and executing automated tests for mobile applications. The framework is designed to be flexible, scalable, and easily adaptable to different mobile platforms and technologies.

## Getting started  

* To get started with mobile automation using Appium, follow these steps

##Prerequisites
* Programming language: Java
* Build automation tool: Maven
* Test framework tool: TestNG,BDD Cucumber
* Version control system: Git
* Integrated Development Environment: Intellij
* Mobile Automation Tool:  Appium
* Appium Server
* Android studio
* Mobile device emulator/simulator or physical device connected

##   Steps to install the java
* Download java 1.8 from the link and install java.
  -After installation, Go to location of java as mentioned below
* Example : C:\Program Files\Java\jdk1.8.0_291\bin.
  -Search environment variables in Windows search.
  -Click on the environment variable button.
  -Select the Path variable and Click on the edit in the window.
  -Click on the new and add the  C:\Program Files\Java\jdk1.8.0_291\bin.

##   Steps to install the IntelliJ idea
* Open the browser and search IntelliJ download for Windows.
  -Select “https://www.jetbrains.com/idea/download/?section=windows” link and community edition to download.
  ~~NOTE-Don't download the ultimate edition.~~
  -Install the IntelliJ from the downloads folder from the laptop.
  -After the installation, a desktop icon will appear, click to open it.
  -Now click on the file menu select the path of the project and click on OK.
  -Now from the Right side, click on the maven icon which displays the project, click on plugins, and then click on clean to clean the project.
  -Then Click on Install then script execution will start.

##Installation and Setup
* Install Appium Server:We can install the Appium server using npm (Node Package Manager) by running the following command in your terminal or command prompt:
[](npm install -g appium)
* Setup Android SDK: If we are testing Android applications, you need to set up the Android SDK on your system. You can download and install Android Studio, which includes the SDK, or you can download the standalone SDK tools from the Android developer website: Android SDK.
* Setup Appium: Install Appium on your machine. You can find the installation instructions on the Appium official website.
  [](https://appium.io/docs/en/latest/)
* Install Dependencies: Run mvn clean install to install all the dependencies specified in the pom.xml file.
* Set Up Mobile Devices: Ensure that the mobile devices or simulators/emulators you want to automate are set up properly. You can connect physical devices via USB or use emulators/simulators.
* Clone Repository: Clone this repository to your local machine using git clone.
* Run Tests: we can run the tests by executing the test scripts available in the src/test/java directory.

## Packages
* The src folder contains src Package and it contains different sub-packages like annotation.values, Pages, Runner, commonUtilities, config,model  and resources. These sub-packages have different functionality needed to support the framework.
* annotation.values - Annotation used to provide a description for a field.
* Pages -  This Class has all the Objects related to the particular Page.
* Runner-Test runner class to execute Cucumber scenarios.
* commonUtilities-It contain all the appium related classes and objects,Extent report related classes,it contain waiting related conditions and mobile related Gestures and operations.
* config - it contain all the mobile related configurations and config properties.
* model-it contain all the mobile platform related information like appPackage and appActivity and configurations.
* resources-this package contains all the Test data.
* Reports- It contains the extent reports. it's a customizable HTML report integrated with the framework for high-level reporting and visualization.
* Reports Configuration- The “Extent-config.xml” file contains the configuration details required to set up the extent report.

## Writing Test scripts
* Test scenarios are written in a human-readable format, which typically includes Given, When, and Then steps to describe the behavior of the system. These scenarios are written in feature files. Here the test cases are created as per the functionality of the application.
  -The step definition file, on the other hand, is where the actual implementation of each step mentioned in the feature file resides. It serves as a bridge between the human-readable syntax and the programming language used to automate.

## Test Execution
* Runner-this contains all the test runner class to run the test scripts.
  -We can run the test script with 2 methods.
  -via test runner class.
  -Right-click the Test Runner file and run it as testing file .
  -Also, we can run via maven commands
  -mvn clean test
  -Open the terminal in project directory and this command will start suite execution in the browser.

## Environment Setup
* while executing we can run test script in different environment based on capabilities.json file
  [](C:\Automation core  team\AutomationMobile-main\automationmobile\caps\capabilities.json)

## Test Data
* Test data are handled in Config property files and feature files.

## Reporting
* The framework generates detailed test reports in HTML format, These reports are  provide insights into test execution results, including pass/fail status, execution time, and error details.
  [](C:\Automation core  team\AutomationMobile-main\automationmobile\reports\extentReport\ExtentReport.html)
  <img src="C:\Automation core  team\AutomationMobile-main\automationmobile\src\main\resources\ExtentMobile.png" title="extentReport" width="500"/>

## Best Practices
* Write clear and descriptive test cases.
* Keep your tests independent and isolated.
* Standard naming conventions for test classes.
* Failed test cases handled by attaching screenshot and adding assertion log

## Contributing
* We are welcome to contributions! Please read our contribution guidelines before submitting pull requests.

## License
* This project is licensed under the MIT License.

## Contact
* For questions or support, contact us at email@example.com.