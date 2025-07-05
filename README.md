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
- Java JDK 11+
- Chrome Browser
- Gradle 7+

## Project Structure
```
src/
├── main/java/
│   └── pages/              # Page Object classes
│       ├── Register.java
│       ├── Login.java
│       ├── Home.java
│       └── Checkout.java
│
└── test/java/
    ├── tests/              # Test classes
    │   └── QkartTest.java
    └── utilities/          # Helper classes
        └── DP.java         # Data Provider
```

## Installation
1. Clone the repository:
```bash
git clone https://github.com/your-username/qkart-automation.git
cd qkart-automation
```

2. Build the project:
```bash
gradle clean build
```

## Running Tests
Execute all tests:
```bash
gradle test
```

Run specific test group:
```bash
gradle -Dgroups=E2E_Test test
```

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

## Reports
Test results are generated in:
`build/reports/tests/test/index.html`

## Configuration Options
Customize in `config.properties`:
```properties
browser=chrome
base.url=https://crio-qkart-frontend-qa.vercel.app
timeout=30
```

## License
This project is licensed under the [MIT License](LICENSE)
```

## Key Concepts Deep Dive

### 1. Page Object Model (POM) Implementation
The POM structure in your code:

```java
// Example Page Object
public class Home {
    private final WebDriver driver;
    
    // Locators
    @FindBy(id = "search-bar") private WebElement searchBar;
    @FindBy(className = "product-card") private List<WebElement> products;
    
    public Home(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Page methods
    public boolean searchForProduct(String product) {
        searchBar.sendKeys(product);
        return !products.isEmpty();
    }
    
    public void addProductToCart(String productName) {
        // Implementation
    }
}
```

### 2. TestNG Features Used
- **Data Providers**: Externalize test data
```java
public class DP {
    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderMethod() {
        return new Object[][] {
            {"Pixel 3", "iPhone XR", "Addr1"},
            {"Galaxy S10", "Macbook Pro", "Addr2"}
        };
    }
}
```

- **Soft Assertions**: Collect multiple failures
```java
softAssert.assertEquals(actual, expected);
softAssert.assertAll(); // Verify all assertions
```

### 3. Selenium Best Practices
- Explicit waits over implicit waits
```java
new WebDriverWait(driver, Duration.ofSeconds(30))
    .until(ExpectedConditions.urlContains("thanks"));
```
- PageFactory pattern for element initialization
- CSS selectors over XPath for better performance

### 4. Gradle Configuration (build.gradle)
```groovy
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.seleniumhq.selenium:selenium-java:4.10.0'
    implementation 'org.testng:testng:7.8.0'
    implementation 'io.github.bonigarcia:webdrivermanager:5.4.1'
}

test {
    useTestNG()
    systemProperties System.getProperties()
}
```

