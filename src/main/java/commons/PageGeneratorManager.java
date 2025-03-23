package commons;

import org.openqa.selenium.WebDriver;

import pagesObject.HomePageObject;

public class PageGeneratorManager {
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
}
	
