Here's a comprehensive README.md for Selenium TestNG project, along with explanations of key concepts:

# Qkart E2E Automation Test Suite

![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=Selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-009639?style=for-the-badge)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

This project contains automated end-to-end tests for the Qkart web application using Selenium WebDriver with TestNG framework in Java.

## Key Features
- User registration and login flow
- Product search and cart management
- Address management and checkout process
- Data-driven testing using TestNG parameters
- Page Object Model (POM) design pattern
- Soft and hard assertions
- Parallel test execution support

## Prerequisites
- Java JDK "21.0.2"
- Chrome Browser "137"
- Gradle 8.8

## Test Configuration
Configure test parameters in `src/test/resources/testng.xml`:
```xml
<suite name="Qkart Test Suite">
    <test name="Regression Tests">
        <classes>
            <class name="tests.QkartTest"/>
        </classes>
    </test>
</suite>
```

## Key Technologies Explained

###  Page Object Model (POM)
POM is a design pattern that creates an abstraction layer for web pages:
- Each page has its own class (e.g., `Home.java`, `Login.java`)
- Page classes contain:
  - WebElement locators
  - Page-specific methods
  - Navigation logic
- Benefits:
  - Improved code maintainability
  - Reduced code duplication
  - Enhanced test readability

###  Gradle (Build Tool)
- Manages project dependencies and builds
- Key components:
  - `build.gradle` - Configuration file
  - Dependency management through repositories
  - Task-based build system
  - Parallel test execution support

###  Selenium WebDriver
- Browser automation framework
- Features used:
  - WebDriver interface for browser control
  - WebElement interactions (click, sendKeys)
  - Explicit waits (`WebDriverWait`)
  - Browser management (maximize, quit)

###  TestNG (Testing Framework)
- Advanced testing framework inspired by JUnit
- Features used:
  - Annotations (`@Test`, `@BeforeSuite`, `@AfterSuite`)
  - Priority-based test execution
  - Parameterized tests (`@DataProvider`)
  - Test grouping (`groups`)
  - Soft assertions (`SoftAssert`)

###  Java
- Primary programming language
- Object-oriented structure
- Exception handling
- Strong typing system

## Test Flow
1. User registration
2. Login with new credentials
3. Product search and cart addition
4. Checkout process:
   - Address management
   - Order placement
5. Order confirmation verification
6. User logout
