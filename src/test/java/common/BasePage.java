package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = Driver.getDriverInstance().getDriver();
    }

    protected void click(By by) {
        driver.findElement(by).click();
    }

    protected void enterValue(By by, String value) {
        driver.findElement(by).sendKeys(value);
    }

    protected void clearTextBox(By by) {
        driver.findElement(by).clear();
    }

    protected String getText(By by) {
        return driver.findElement(by).getText();
    }

    protected boolean isElementPresent(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public void popUpHandler(){
        try
        {
            if (driver.findElement(By.xpath("//button[@aria-label='close']")).isDisplayed()) {
                driver.findElement(By.xpath("//button[@aria-label='close']")).click();
            }
        }
        catch (Exception e){

        }
    }
}