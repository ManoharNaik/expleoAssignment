package testCases;

import common.BasePage;
import common.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;

public class BaseTest {

    private WebDriver driver = Driver.getDriverInstance().getDriver();
    BasePage bp = new BasePage();

    @BeforeSuite
    public void beforeSuite() throws InterruptedException {
        File results = new File("build/allure-results");
        if (results.exists())
            results.delete();

        driver.get("https://www.xe.com/currencyconverter");
        bp.popUpHandler();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.xe.com/currencyconverter");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        bp.popUpHandler();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        Driver.getDriverInstance().quitDriver();
    }
}