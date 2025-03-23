package web;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pagesObject.HomePageObject;

import java.util.List;

public class OpenWeatherMapTest extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void BeforeClass(String browserName, String pageURL) {
        driver = getBrowserName(browserName, pageURL);
        homePage = PageGeneratorManager.getHomePageObject(driver);
    }

    @Test
    public void testWeatherDetailsForLosAngeles() {
        String searchKeyword = "Los Angeles, US";
        String search1 = "Los Angeles";
        String search2 = "US";
        homePage.searchForCity(searchKeyword);
        List<String> actualCityName = homePage.getResultSearch();
        System.out.println(actualCityName);
        // Verify city name
        Assert.assertTrue(homePage.isAllCitiesContain(search1));
        Assert.assertTrue(homePage.isAllCitiesContain(search2));
        // Verify temperature is a number
        List<String> temperature = homePage.getTemperature();
        System.out.println(temperature);
        Assert.assertTrue(homePage.isNumber(temperature));
       // Verify current date
        homePage.searchForCity(search1);
        homePage.clickOnTextMatch(searchKeyword);
        homePage.verifyCurrentDate();
    }
    @AfterClass(alwaysRun = true)
    public void AfterClass() {
        closeBrowserDriver();
    }
}
