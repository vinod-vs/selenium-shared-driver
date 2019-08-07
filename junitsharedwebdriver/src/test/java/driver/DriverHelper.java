package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverHelper {

    private static WebDriver driver;

    public DriverHelper() {
        System.setProperty("webdriver.chrome.driver", "/path the driver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.manage().window().fullscreen();
        driver.manage().deleteAllCookies();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
