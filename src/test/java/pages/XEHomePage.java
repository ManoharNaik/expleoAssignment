package pages;

import com.aventstack.extentreports.Status;
import common.BasePage;
import common.Driver;
import common.ExtentTestManager;
import common.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

import java.io.WriteAbortedException;
import java.time.Duration;

public class XEHomePage extends BasePage {

    public XEHomePage() {

    }

    By btnClose = By.xpath("//button[@aria-label='close']");
    By inputAmount = By.id("amount");
    By selFrom = By.id("midmarketFromCurrency");
    By selTo = By.id("midmarketToCurrency");
    By btnConvert = By.xpath("//button[@type='submit']");
    By rateConversion = By.xpath("//div[contains(@class, 'unit-rates')]/p");
    By autoFromResult = By.xpath("//ul[@id='midmarketFromCurrency-listbox']/li[1]");
    By autoToResult = By.xpath("//ul[@id='midmarketToCurrency-listbox']/li[1]");
    By fromCurrencyValue = By.xpath("//div[contains(@class, 'unit-rates')]/p[1]");
    By toCurrencyValue = By.xpath("//div[contains(@class, 'unit-rates')]/p[2]");
    By txtHeader = By.xpath("//h1[contains(text(),'Convert')]");


    public XEHomePage enterAmountFromAndToCurrencies(String amount, String fromCurrency, String toCurrency, boolean isPopupPresent) throws InterruptedException {
        popUpHandler();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        enterValue(inputAmount, amount);
        enterValue(selFrom, fromCurrency);
        click(autoFromResult);
        enterValue(selTo, toCurrency);
        click(autoToResult);
        click(btnConvert);
        popUpHandler();
        Thread.sleep(2000);
        return this;
    }

    public void validateRateConversion(String amount, String fromCurrency, String toCurrency) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(3000);
        String fromCurr = getText(fromCurrencyValue);
        System.out.println("fromCurrfromCurr: "+fromCurr);
        String toCurr = getText(toCurrencyValue);
        System.out.println("toCurrtoCurrtoCurr: "+toCurr);
        String headerText = getText(txtHeader);
        System.out.println("headerTextheaderTextheaderText: "+headerText);
        if (fromCurr.contains(fromCurrency)){
            ExtentTestManager.getTest().log(Status.PASS, "From Currency Type is validated in the Result and its Matched");
        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "From Currency Type is validated and Failed");
        }

        if (toCurr.contains(toCurrency)){
            ExtentTestManager.getTest().log(Status.PASS, "To Currency Type is validated in the Result and its Matched");
        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "To Currency Type is validated and Failed");
        }
        if (headerText.contains(fromCurrency) || headerText.contains(toCurrency)){
            ExtentTestManager.getTest().log(Status.PASS, "To Currency Type and From Currency is validated in the Header and its Matched");
        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Currency Types validated Failed in Header");
        }
        Assert.assertTrue(isElementPresent(rateConversion));
        ExtentTestManager.getTest().log(Status.PASS, "From Currency is: '"+ fromCurrency + "', To Currency is '" + toCurrency + "' and Amount is '" + amount + "'");
        TestListener.addScreenshot("Currency Conversion - " +toCurrency+ " and " +toCurrency, Status.PASS);

    }
}