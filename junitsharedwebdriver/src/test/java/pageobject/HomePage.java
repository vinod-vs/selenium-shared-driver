package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    public WebElement signInLink;

    @FindBy(id = "gh-eb-My")
    public WebElement myEbayLink;

    @FindBy(linkText = "Recently viewed")
    public WebElement recentlyViewedLink;

    @FindBy(xpath = "//a[@id='gh-cart-1']")
    public WebElement cart;

    public void navigateToPage(String page) throws InterruptedException {
        WebElement pageElement;
        if (page.equals("MyEbay")) {
            myEbayLink.click();
        } else if (page.equals("Recently viewed")) {
            recentlyViewedLink.click();
        }

    }
}
