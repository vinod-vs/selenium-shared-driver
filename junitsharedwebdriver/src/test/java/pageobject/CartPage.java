package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Go to checkout')]")
    public WebElement checkoutButton;

    @FindBy(xpath = "//legend[contains(text(),'Select a payment option')]")
    public WebElement paymentOption;

    @FindBy(xpath = "//div[@class='app-cart']//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//div[2]//span[2]//button[1]")
    public WebElement removeItem;


}
