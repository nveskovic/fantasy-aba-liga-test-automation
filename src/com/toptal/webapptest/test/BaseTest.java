package com.toptal.webapptest.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.toptal.webapptest.pages.BasePage;
import com.toptal.webapptest.pages.LoginPage;

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
	
	public WebDriver initWebDriver() {
		if(driver == null) {
			driver =  new FirefoxDriver();
		}
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
		baseURL = prop.getProperty("app.url");
		username = prop.getProperty("app.username");
		password = prop.getProperty("app.password");
		teamName = prop.getProperty("app.teamName");
		teamName2 = prop.getProperty("app.teamName2");
		privateLeagueName = prop.getProperty("app.private.league.name");
		
		driver = initWebDriver();
		driver.manage().window().maximize();
	}
	
	protected BasePage loginToHomePage() {
		BasePage basePage = PageFactory.initElements(driver, BasePage.class);
		if(!basePage.isLoggedIn(0)) {
			LoginPage loginPage = basePage.clickOnLoginLink();
			basePage = loginPage.login(username, password, BasePage.class);
			Assert.assertTrue(basePage.isLoggedIn(3), "User is logged in");
		}
		
		return basePage;
	}

	@AfterSuite(alwaysRun = true)
	public void afterBaseSuite() {
		this.closeWebDriver();
	}
}
