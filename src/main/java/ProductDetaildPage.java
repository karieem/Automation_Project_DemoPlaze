import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetaildPage {
    private WebDriver driver;
    By AddToCartButton = By.xpath("//a[contains(text(),'Add to cart')]");
    By CartButton = By.id("cartur");
    public ProductDetaildPage (WebDriver driver){this.driver = driver;}
    public void ClickOnAddToCartButton(){driver.findElement(AddToCartButton).click();}

    public CartPage ClickOnCartButtonInNavBar (){

        driver.findElement(CartButton).click();
        return new CartPage(driver);
    }




}
