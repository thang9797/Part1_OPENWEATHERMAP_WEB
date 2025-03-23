package commons;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import pagesObject.HomePageObject;
import pagesUI.HomePageUI;

public class BasePages {
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	private WebDriver driver;
	
	public static BasePages getBasePages() {
		return new BasePages();
	}

	public void openPageURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void navigateToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardBrowser(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshBrowser(WebDriver driver) {
		driver.navigate().refresh();
	}

	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpathLocator(driver, xpathLocator));
	}
	public WebElement getElement(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		return driver.findElement(getByDynamicXpath(xpathLocator, valuesForXpathLocator));
	}
	public List<WebElement> getElements(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpathLocator(driver, xpathLocator));
	}
	public List<WebElement> getElements(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		return driver.findElements(getByDynamicXpath(xpathLocator, valuesForXpathLocator));
	}
	public By getByXpathLocator(WebDriver driver, String locator) {
		return By.xpath(locator);
	}

	public void clickOnElement(WebDriver driver, String xpathLocator) {
		getElement(driver, xpathLocator).click();
	}

	public void clickOnElement(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		getElement(driver, xpathLocator, valuesForXpathLocator).click();
	}
	public void sendkeyToElement(WebDriver driver, String xpathLocator, String sendkeyValue) {
		getElement(driver, xpathLocator).clear();
		getElement(driver, xpathLocator).sendKeys(sendkeyValue);
	}
	public void sendkeyToElement(WebDriver driver, String xpathLocator, String sendkeyText, String... valuesForXpathLocator) {
		getElement(driver, xpathLocator, valuesForXpathLocator).clear();
		getElement(driver, xpathLocator, valuesForXpathLocator).sendKeys(sendkeyText);
	}
	public String getTextElement(WebDriver driver, String xpathLocator) {
		System.out.println(getElement(driver, xpathLocator).getText().trim());
		return getElement(driver, xpathLocator).getText().trim();
	}
	public List<String> getAllTextElements(WebDriver driver, String xpathLocator) {
		List<WebElement> elements = getElements(driver, xpathLocator);
		List<String> textResults = new ArrayList<>();
		for (WebElement element : elements) {
			textResults.add(element.getText().trim());
		}
		return textResults;
	}
	public String getTextElement(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		return getElement(driver, xpathLocator, valuesForXpathLocator).getText().trim();
	}
	public String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
		return getElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName, String... valuesForXpathLocator) {
		return getElement(driver, xpathLocator, valuesForXpathLocator).getAttribute(attributeName);
	}

	public String getCSSValue(WebDriver driver, String xpathLocator, String cssAttributName) {
		return getElement(driver, xpathLocator).getCssValue(cssAttributName);
	}

	public String getCSSValue(WebDriver driver, String xpathLocator, String cssAttributName, String... valuesForXpathLocator) {
		return getElement(driver, xpathLocator, valuesForXpathLocator).getCssValue(cssAttributName);
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		overrideTimeOut(driver, shortTimeOut);
		if (!getElement(driver, xpathLocator).isDisplayed()) {
			overrideTimeOut(driver, longTimeOut);
			return false;
		}
		else {
			overrideTimeOut(driver, longTimeOut);
			return true;
		}
		 
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		return getElement(driver, xpathLocator, valuesForXpathLocator).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		return getElement(driver, xpathLocator, valuesForXpathLocator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		return getElement(driver, xpathLocator, valuesForXpathLocator).isSelected();
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		new Actions(driver).sendKeys(getElement(driver, xpathLocator), key).perform();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void waitForElementVisibility(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpathLocator(driver, xpathLocator)));
	}

	public void waitForElementVisibility(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByDynamicXpath(xpathLocator, valuesForXpathLocator)));
	}
	
	public void waitForElementPresence(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXpathLocator(driver, xpathLocator)));
	}
	
	public void waitForElementPresence(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByDynamicXpath(xpathLocator, valuesForXpathLocator)));
	}

	public void waitForAllElementsVisibility(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(getElements(driver, xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpathLocator(driver, xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByDynamicXpath(xpathLocator, valuesForXpathLocator)));
	}

	public void waitForElementInvisibility(WebDriver driver, String xpathLocator) {
		overrideTimeOut(driver, shortTimeOut);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeOut));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpathLocator(driver, xpathLocator)));
		overrideTimeOut(driver, longTimeOut);
	}

	public void waitForElementInvisibility(WebDriver driver, String xpathLocator, String... valuesForXpathLocator) {
		overrideTimeOut(driver, shortTimeOut);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeOut));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByDynamicXpath(xpathLocator, valuesForXpathLocator)));
		overrideTimeOut(driver, longTimeOut);
	}

	private By getByDynamicXpath(String xpathLocator, String... values) {
		By by = null;
		by = By.xpath(String.format(xpathLocator, (Object[]) values));
		return by;
	}
}