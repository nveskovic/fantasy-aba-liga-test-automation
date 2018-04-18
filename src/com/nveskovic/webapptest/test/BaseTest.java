package com.nveskovic.webapptest.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.nveskovic.webapptest.utils.BrowserType;
import com.nveskovic.webapptest.utils.OsUtils;

public class BaseTest {

	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	private static Properties prop = new Properties();
	private final String defaultConfigPropertiesFile = "resources/config/test.properties";
	
	protected static WebDriver driver;
	protected static String baseURL;
	protected static String username, password, teamName, teamName2, privateLeagueName;
	
	private void loadProperties(String path) throws FileNotFoundException, IOException {
		File configProperties = new File(defaultConfigPropertiesFile);
		if(!configProperties.exists() || !configProperties.isFile()) {
			logger.error("config-file does not exist or is not a file!");
			System.exit(-1);
		}
		
		// load properties
		prop.load(new FileInputStream(defaultConfigPropertiesFile));
	}
	
	public WebDriver initWebDriver(BrowserType bt, boolean visibleBrowser) {
		/*
		 * Due to problem described here https://github.com/SeleniumHQ/selenium/issues/5453
		 * Setting http.factory to apache
		 */
		System.setProperty("webdriver.http.factory", "apache");

		WebDriver driver = null;

		String currentDir = System.getProperty("user.dir");
		String driverPath = Paths.get(currentDir,"/data/drivers").toString();
		String driverPathSuffix = (OsUtils.getOsType() == OsUtils.OsType.WINDOWS ? ".exe" : "");

		switch(bt) {

		case FIREFOX : {
			driverPath = Paths.get(driverPath, "geckodriver"+driverPathSuffix).toString();
			System.setProperty("webdriver.gecko.driver",driverPath);

			if(!visibleBrowser){
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setBinary(firefoxBinary);
				driver = new FirefoxDriver(firefoxOptions);
			} else {
				driver = new FirefoxDriver();
			}
		} break;

		default : {
			driverPath = Paths.get(driverPath, "chromedriver"+driverPathSuffix).toString();

			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions chromeOptions = new ChromeOptions();
			if(!visibleBrowser){
				chromeOptions.addArguments("--headless"); // not visible == headless
			}

			driver = new ChromeDriver(chromeOptions);
			
		}
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);

		return driver;
	}
	
	protected void closeWebDriver() {
		if(driver != null)
			driver.quit();
	}
	
	/*
	 * TestNG test prepare and tear-down
	 */
	@BeforeSuite(alwaysRun = true)
	public void beforeBaseSuite() throws IOException {
		
		loadProperties(defaultConfigPropertiesFile);
		
		BrowserType browserType = BrowserType.valueOf(prop.getProperty("browser.type"));
		boolean visibleBrowser = Boolean.parseBoolean(prop.getProperty("browser.visible"));
		
		baseURL = prop.getProperty("app.url");
		username = prop.getProperty("app.username");
		password = prop.getProperty("app.password");
		teamName = prop.getProperty("app.teamName");
		teamName2 = prop.getProperty("app.teamName2");
		privateLeagueName = prop.getProperty("app.private.league.name");
		
		driver = initWebDriver(browserType, visibleBrowser);
		driver.manage().window().maximize();
	}

	@AfterSuite(alwaysRun = true)
	public void afterBaseSuite() {
		this.closeWebDriver();
	}
}
