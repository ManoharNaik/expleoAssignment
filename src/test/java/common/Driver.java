package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {

    private static Driver driver;
    private static WebDriver webDriver;

    private Driver() {}

    public static Driver getDriverInstance() {
        if (driver == null) {
            driver = new Driver();
            initWebDriver();
        }

        return driver;
    }

    private static void initWebDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        webDriver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void quitDriver() {
        webDriver.quit();
    }
}