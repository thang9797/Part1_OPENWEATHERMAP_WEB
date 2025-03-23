package pagesObject;
import commons.BasePages;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pagesUI.HomePageUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class HomePageObject extends BasePages {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Search For City")
	public void searchForCity(String cityName) {
		waitForElementPresence(driver,HomePageUI.SEARCH_INPUT);
		sendkeyToElement(driver, HomePageUI.SEARCH_INPUT, cityName);
		clickOnElement(driver, HomePageUI.SEARCH_BUTTON);
	}
	@Step("Get City Name")
	public List<String> getResultSearch(){
		waitForAllElementsVisibility(driver,HomePageUI.RESULTS_SEARCH_CITY);
        return getAllTextElements(driver,HomePageUI.RESULTS_SEARCH_CITY);
    }

	@Step("Verify result search")
	public boolean isAllCitiesContain(String expectedCity) {
		List<String> citiesList = getResultSearch();
		boolean flag = false;
		for(String city:citiesList) {
			if(city.contains(expectedCity)) {
				flag = true;
			}
		}
		return flag;
	}

	@Step("Get Temperature")
	public List<String> getTemperature(){
		waitForAllElementsVisibility(driver,HomePageUI.RESULTS_SEARCH_TEMP);
		return getAllTextElements(driver,HomePageUI.RESULTS_SEARCH_TEMP);
	}
	@Step("Verify Temperature is Number")
	public boolean isNumber(List<String> temperatures) {
		for (String temp : temperatures) {
			// Remove the "°C" suffix
			String numberPart = temp.replace("°C", "");
			// Check if the remaining string is a valid number
			try {
				Double.parseDouble(numberPart);
			} catch (NumberFormatException e) {
				// If parsing fails, it's not a valid number
				return false;
			}
		}
		// All temperatures are valid numbers
		return true;
	}

	@Step("Click on Text Match")
	public void clickOnTextMatch(String matchText) {
		waitForElementPresence(driver,HomePageUI.RESULTS_SEARCH_CITY);
		clickOnElement(driver,HomePageUI.RESULTS_CITY,matchText);
	}
	@Step("Verify Current Date display corrected")
	public void verifyCurrentDate() {
		Allure.step("Verify the date and time", () -> {
		String dateTimeText = getTextElement(driver, HomePageUI.CURRENT_DATE);
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mma", Locale.ENGLISH);
		String formattedDateTime = currentDateTime.format(formatter);
		formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length() - 2)
				+ formattedDateTime.substring(formattedDateTime.length() - 2).toLowerCase();
		System.out.println(formattedDateTime);
		// Compare the date and time
			if (dateTimeText.equals(formattedDateTime)) {
				System.out.println("The date and time on the web page match the current system date and time.");
			} else {
				System.out.println("The date and time on the web page do NOT match the current system date and time.");
			}
		});
	}
}
