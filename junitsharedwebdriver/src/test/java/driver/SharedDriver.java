package driver;

import org.openqa.selenium.chrome.ChromeDriver;

public class SharedDriver {

	public SharedDriver() {
		System.out.println(Thread.currentThread().getId() + " CONST");
		if (DriverFactory.getDriver() == null) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chrome/chromedriver");
			DriverFactory.addDriver(new ChromeDriver());
		}
	}
}
