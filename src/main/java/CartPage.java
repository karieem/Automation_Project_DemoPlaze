import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {


    private WebDriver driver;

    By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
    By purchaseButton = By.xpath("//button[contains(text(),'Purchase')]");
    By name = By.id("name");
    By country = By.id("country");
    By city = By.id("city");
    By creditCard = By.id("card");
    By month = By.id("month");
    By year = By.id("year");
    By successMsg = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
    public CartPage (WebDriver driver){this.driver = driver;}

    public void ClickOnPlaceOrderButton (){driver.findElement(placeOrderButton).click();}
    public void ClickOnPurchaseButton (){driver.findElement(purchaseButton).click();}
    public void EnterName (String n){driver.findElement(name).sendKeys(n);}
    public void EnterCountry (String c){driver.findElement(country).sendKeys(c);}
    public void EnterCity (String t){driver.findElement(city).sendKeys(t);}
    public void EnterCreditCard (String d){driver.findElement(creditCard).sendKeys(d);}
    public void EnterMonth (String m){driver.findElement(month).sendKeys(m);}
    public void EnterYear (String y){driver.findElement(year).sendKeys(y);}
    public String ShowSuccessMsg (){return driver.findElement(successMsg).getText();}


}
