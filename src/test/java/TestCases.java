import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCases {
    private WebDriver driver;
    HomePage homePage;
    ProductDetaildPage productToCart;
    CartPage cartPage;

    @BeforeTest
    public void Setup()
    {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/index.html");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @DataProvider (name = "loginData")
    public static Object [][] loginDataProvider()
    {
     return new Object[][]{
             {"kanilox104@dusyum.com", "kareem12345"}
     };
    }

    /* Login to the website */
    @Test (priority = 1 , dataProvider = "loginData")
    public void LoginWithData(String email , String pass)
    {
        homePage.ClickOnLoginButton();
        // Initial WebDriverWait with a reasonable timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        homePage.EnterEmail(email);
        homePage.EnterPassword(pass);
        homePage.ClickOnLoginButtonForm();
    }

    /* Navigate through the categories list */
    @Test (priority = 2)
    public void NavigateToCategory ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        homePage.ClickOnCategory();
    }

    /* Pick one of the products and open the product details page */
    @Test (priority = 3)
    public void PickProduct ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook Pro")));
        // Initialize productToCart object here
        productToCart = homePage.ClickOnProduct();
    }

    /* Add the product to the cart */
    @Test (priority = 4)
    public void AddTheProductToCart ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
        productToCart.ClickOnAddToCartButton();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /* Go to Cart page and place an order */
    @Test (priority = 5)
    public void GoToCartPage ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Cart")));
        // Initialize cartPage object here
        cartPage = productToCart.ClickOnCartButtonInNavBar();
        cartPage.ClickOnPlaceOrderButton();
    }

    /* Click on purchase button without filling the order form */
    @Test (priority = 6)
    public void ClickOnPurchaseWithotFill ()
    {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Purchase')]")));
       cartPage.ClickOnPurchaseButton();
    }

    /* Assert that an Error message is displayed */
    @Test (priority = 7)
    public void AssertErrorMsgDisplayed ()
    {
        String ErrorMsg = driver.switchTo().alert().getText();
        Assert.assertEquals(ErrorMsg, "Please fill out Name and Creditcard.");
        driver.switchTo().alert().accept();
    }

    @DataProvider (name = "PurchaseFormData")
    public Object [][] purchaseData ()
    {
        return new Object[][]
                {
                        {"Kareem Mohamed","Egypt","Cairo","21453698145657","March","2023"}
                };
    }

    /* Fill the form then click on purchase button */
    @Test (priority = 8, dataProvider = "PurchaseFormData")
    public void ClickOnPurchaseWithFillData (String name, String country, String city, String creditCard, String month, String year)
    {
        cartPage.EnterName(name);
        cartPage.EnterCountry(country);
        cartPage.EnterCity(city);
        cartPage.EnterCreditCard(creditCard);
        cartPage.EnterMonth(month);
        cartPage.EnterYear(year);
        cartPage.ClickOnPurchaseButton();
    }

    /* Assert the display of the success message */
    @Test (priority = 9)
    public void AssertSuccessMsgDisplayed ()
    {
        Assert.assertEquals(cartPage.ShowSuccessMsg(), "Thank you for your purchase!");
    }

    @AfterTest
    public void CloseBrowser(){driver.quit();}



}
