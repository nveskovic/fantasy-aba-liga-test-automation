package com.nveskovic.webapptest.test;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nveskovic.webapptest.pages.HomePage;
import com.nveskovic.webapptest.pages.electronics.ElectronicsPage;
import com.nveskovic.webapptest.pages.electronics.ElectronicsSearchPage;
import com.nveskovic.webapptest.pages.electronics.ElectronicsSearchResultsPage;

public class HomeAssignementTest extends BaseTest {

	private static Logger logger = LoggerFactory.getLogger(HomeAssignementTest.class);

	private HomePage homePage;
	private ElectronicsPage electronicsPage;
	private ElectronicsSearchPage electronicsSearchPage;
	private ElectronicsSearchResultsPage searchResultsPage;

	/**
	 * 1. Open the browser and maximize it.
	 * 2. Open  ss.com
	 * 3. switch to Russian language.
	 * 4. Go to the section “Электротехника”, open search ('Поиск') and in Search enter the search phrase 
	 *    (eg. 'Computer') and select a different search parameters.
	 * 5. Click Search
	 * 6. Sort the results by price and select option 'Продажа' in "Тип сделки" dropdown.
	 * 7. Open “Расширенный поиск”. (advanced search)
	 * 8. Enter search option price between 160 and 300.
	 * 9. Choose at least 3 random ads.
	 * 10. Press “Добавить выбранные в закладки” ( = add to memo)
	 * 11. Open “Закладки” and check that the ads on the page match the previously selected
	 * 12. Close the browser.
	 */
	@Test (groups = {"P0"})
	public void homeAssignmentTest () {
		
		// 1. Open the browser and maximize it. - DONE in before suite
		
		// 2. Open  ss.com
		driver.get(baseURL);
		homePage = PageFactory.initElements(driver, HomePage.class);
		
		// 3. switch to Russian language.
		homePage.selectLanguage("RU");
		
		/* 4. Go to the section “Электротехника”, open search ('Поиск') and in Search enter the search phrase 
		      (eg. 'Computer') and select a different search parameters. */
		homePage.clickOnSectionHeaderByName("Электротехника");
		electronicsPage = PageFactory.initElements(driver, ElectronicsPage.class);
		
		electronicsPage.clickOnHeaderMenuItemByName("Поиск");
		electronicsSearchPage = PageFactory.initElements(driver, ElectronicsSearchPage.class);
		
		electronicsSearchPage.setNameOrPhrase("iphone");
		electronicsSearchPage.dismissSmartSearchList();
		electronicsSearchPage.selectSubsectionByValue("2"); //buying
		
		// 5. Click Search
		searchResultsPage = electronicsSearchPage.clickOnSearchButton();
		
		// 6. Sort the results by price and select option 'Продажа' in "Тип сделки" dropdown.
		searchResultsPage = searchResultsPage.clickOnPriceLinkInTableHeader();
		searchResultsPage = searchResultsPage.selectTypeOfItemsByName("Продажа");
		
		// 7. Open “Расширенный поиск”. (advanced search)
		searchResultsPage.clickOnLinkByLinkTest("Расширенный поиск");
		electronicsSearchPage = PageFactory.initElements(driver, ElectronicsSearchPage.class);

		// 8. Enter search option price between 160 and 300
		electronicsSearchPage.setMinPrice("160");
		electronicsSearchPage.setMaxPrice("300");
		searchResultsPage = electronicsSearchPage.clickOnSearchButton();

		int numOfResults = searchResultsPage.getNumberOfResultsInPage();
		logger.info(""+numOfResults);
		Assert.assertTrue(numOfResults >= 3, "More than 3 results are displayed");


		for(int i=0; i<3; i++) {
			logger.info(""+ (i*numOfResults/3 + numOfResults/6));
		}


	}
}
