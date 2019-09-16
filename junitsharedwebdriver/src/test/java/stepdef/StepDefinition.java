package stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.SeleniumHelper;
import driver.SharedDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.GoogleHomePO;
import pageobject.GoogleSearchPO;
import pageobject.HomePage;
import pageobject.CartPage;

import java.net.MalformedURLException;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class StepDefinition {
	
	private GoogleHomePO ghPO;
	private GoogleSearchPO gsPO;
	private WebDriver driver;
	private SeleniumHelper seleniumHelper = new SeleniumHelper();
	
	public StepDefinition(SharedDriver driver, GoogleHomePO ghPO, GoogleSearchPO gsPO) {
		this.ghPO = ghPO;
		this.gsPO = gsPO;
	}
	
	@Given("Go to google page")
	public void given() {
		ghPO.get();
	}
	
	@When("Enter search {string}")
	public void when(String search) {
		gsPO = ghPO.performSearch(search);
		System.out.format("\nCount results for %s search is %d.\n", search, gsPO.searchResultCount());
	}

	@Given("^I am on the website with URL \"([^\"]*)\"$")
	public void i_am_on_the_page_on_URL(String url) {
		SeleniumHelper.baseUrl = url;
		try {
			driver = SeleniumHelper.openBrowser();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Opening the url " + url);
		driver.get(url);
	}


	@And("^when that user goes to \"([^\"]*)\" page$")
	public void whenThatUserGoesToPage(String page) throws Throwable {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToPage(page);
		homePage.navigateToPage("Recently viewed");
	}

	public void findAndClick(int index,List<WebElement> cartItems) {
		seleniumHelper.waitForPageLoaded();
		Actions actions = new Actions(driver);
		actions.moveToElement(cartItems.get(index)).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cartItems.get(index).findElement(By.xpath("(//span[contains(text(),'Add to cart')])["+(index+1)+"]")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("span[class*='continue']")).click();

	}


	@Then("^adds '(\\d+)' items in  basket and checkout$")
	public void addsItemsInBasketAndCheckout(int itemsInCart) {
		HomePage homePage = new HomePage(SeleniumHelper.driver);
		CartPage cartPage = new CartPage(SeleniumHelper.driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> cartItems = driver.findElements(By.cssSelector("#homefeatured>li"));
		for (int itemNo = 0; itemNo <= itemsInCart - 1; itemNo++) {
			findAndClick(itemNo,cartItems);
		}


		driver.findElement(By.cssSelector(".shopping_cart>a")).click();
		//driver.findElement(By.xpath("//span[contains(text(),'Check out')]")).click();
		seleniumHelper.waitForPageLoaded();
		driver.findElement(By.cssSelector("a[class*='button btn']")).click();
		seleniumHelper.waitForPageLoaded();
		driver.findElement(By.cssSelector("#email")).sendKeys("demouser2@gmail.com");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("demouser2");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();
		seleniumHelper.waitForPageLoaded();
		driver.findElement(By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")).click();
		seleniumHelper.waitForPageLoaded();
		driver.findElement(By.cssSelector("#cgv")).click();
		driver.findElement(By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")).click();
		driver.findElement(By.cssSelector(".bankwire")).click();
		driver.findElement(By.xpath("//span[contains(text(),'I confirm my order')]")).click();


	}

	@And("^Removes the items from the cart$")
	public void removesTheItemsFromTheCart() throws Throwable {
		seleniumHelper.waitForPageLoaded();
		CartPage cartPage = new CartPage(SeleniumHelper.driver);
		while (true) {
			try {
				cartPage.removeItem.isDisplayed();
			} catch (Exception e) {
				System.out.println("items are not present in the cart ");
				break;
			}
			cartPage.removeItem.click();
		}
	}





	@When("^I look up and receive the weather forecast$")
	public void iLookUpAndReceiveTheWeatherForecast() throws Throwable {

	}
}
