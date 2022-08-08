package common;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

public class TestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", Driver.getDriverInstance().getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTestManager.startTest(getTestMethodName(iTestResult), iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        addScreenshot("Test Failed", Status.FAIL);
    }

    public static void addScreenshot(String name, Status status) {
        WebDriver driver = Driver.getDriverInstance().getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(status, name,
                ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
}