package qkart_ecommerce;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import qkart_ecommerce.Pages.Checkout;
import qkart_ecommerce.Pages.Home;
import qkart_ecommerce.Pages.Login;
import qkart_ecommerce.Pages.Register;

public class QkartTest {

  static WebDriver driver;
  public static String lastGeneratedUserName;
  static Assertion hardAssert = new Assertion();
  static SoftAssert softAssert = new SoftAssert();

  @BeforeSuite(alwaysRun = true)
  public static void createDriver() throws MalformedURLException {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    System.out.println("createdDriver()");
  }

  @Test(priority = 1, description = "Verify that a new user can add multiple products in to the cart and Checkout", groups = "E2E_Test", dataProvider = "data-provider", dataProviderClass = DP.class)
  public void TestCase(String product_1, String product_2, String address)
      throws InterruptedException {
    Boolean status;

    // Go to the Register page
    Register registration = new Register(driver);
    registration.navigateToRegisterPage();

    // Register a new user
    status = registration.registerUser("testUser", "abc@123", true);

    Assert.assertTrue(status, "Test Case Failure. Happy Flow Test Failed");

    // Save the username of the newly registered user
    lastGeneratedUserName = registration.lastGeneratedUsername;

    // Go to the login page
    Login login = new Login(driver);
    login.navigateToLoginPage();

    // Login with the newly registered user's credentials
    status = login.PerformLogin(lastGeneratedUserName, "abc@123");
    Assert.assertTrue(status, "Step Failure User Perform Login Failed");

    // Go to the home page
    Home homePage = new Home(driver);
    homePage.navigateToHome();

    // Find required products by searching and add them to the user's cart
    status = homePage.searchForProduct(product_1);
    homePage.addProductToCart(product_1);
    status = homePage.searchForProduct(product_2);
    homePage.addProductToCart(product_2);

    // Click on the checkout button
    homePage.clickCheckout();

    // Add a new address on the Checkout page and select it
    Checkout checkoutPage = new Checkout(driver);
    checkoutPage.addNewAddress(address);
    checkoutPage.selectAddress(address);

    // Place the order
    checkoutPage.placeOrder();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions
        .urlToBe("https://crio-qkart-frontend-qa.vercel.app/thanks"));

    // Check if placing order redirected to the Thansk page
    status = driver.getCurrentUrl().endsWith("/thanks");

    // Go to the home page
    homePage.navigateToHome();

    // Log out the user
    homePage.PerformLogout();

  }

  @AfterSuite
  public static void quitDriver() {
    System.out.println("quit()");
    driver.quit();
  }
}
