package commons;

import java.io.File;

public class GlobalConstants {
	public static final String USER_URL = "https://demo.nopcommerce.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String FIREFOX_DRIVER_PATH = PROJECT_PATH + File.separator + "browserDrivers" + File.separator + "geckodriver.exe";
	public static final String CHROME_DRIVER_PATH = PROJECT_PATH + File.separator + "browserDrivers" + File.separator + "chromedriver.exe";
	public static final String EDGE_DRIVER_PATH = PROJECT_PATH + File.separator + "browserDrivers" + File.separator + "edgedriver.exe";
	
	public static final long SHORT_TIMEOUT = 10;
	public static final long LONG_TIMEOUT = 30;
	public static final int RETRY_TEST_FAIL = 3;
}
