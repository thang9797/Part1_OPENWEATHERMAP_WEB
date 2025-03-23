package pagesUI;
public class HomePageUI {
	public static final String SEARCH_INPUT = "//input[@placeholder='Search city']";
	public static final String SEARCH_BUTTON = "//button[@class='button-round dark']";
	public static final String RESULTS_SEARCH_CITY = "//img[@class='flag']//parent::span";
	public static final String RESULTS_SEARCH_TEMP = "//img[@class='flag']//parent::span//following-sibling::span[1]";
    public static final String RESULTS_CITY = "//span[contains(text(),'%s')]";
	public static final String CURRENT_DATE = "//div[@class='section-content']//h2//preceding-sibling::span";
}
