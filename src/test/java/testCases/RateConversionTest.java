package testCases;

import common.ExcelUtils;
import org.testng.annotations.Test;
import pages.XEHomePage;

public class RateConversionTest extends BaseTest {

    static boolean isPopupPresent = true;

    @Test(testName = "Rate Conversion", dataProvider = "CurrenciesData", dataProviderClass = ExcelUtils.class)
    public void rateConversion(String amount, String fromCurrency, String toCurrency) throws InterruptedException {
        XEHomePage homePage = new XEHomePage();
        homePage.enterAmountFromAndToCurrencies(amount, fromCurrency, toCurrency, isPopupPresent)
                .validateRateConversion(amount, fromCurrency, toCurrency);

        isPopupPresent = false;
    }
}