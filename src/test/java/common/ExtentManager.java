package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent_reports/extent_report.html");
        reporter.config().setReportName("Rate Conversion Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}