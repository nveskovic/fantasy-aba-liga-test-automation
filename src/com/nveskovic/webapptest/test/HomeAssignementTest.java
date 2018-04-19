package com.nveskovic.webapptest.test;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nveskovic.webapptest.pages.FavoritesPage;
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

	private FavoritesPage favoritesPage;

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
		
		// pause in order to get smart search to be displayed
		try{Thread.sleep(500);}catch(Exception e){};
		electronicsSearchPage.dismissSmartSearchList();
		
		// pause to give some time for smartsearch to disappear
		try{Thread.sleep(500);}catch(Exception e){};
		
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
		electronicsSearchPage.selectSubsectionByIndex(0); //no value
		searchResultsPage = electronicsSearchPage.clickOnSearchButton();

		ArrayList<String> itemsInPage = searchResultsPage.getAllResultsFromPage();
		Assert.assertTrue(itemsInPage.size() >= 3, "More than 3 results are displayed");

		// 9. Choose at least 3 random ads
		ArrayList<String> pickedItems = new ArrayList<String>();
		
		/* pick 3 elements from the list
		 * divide in three parts and pick from the middle of each part
		 * Example: if list has 30 items, parts will be 0-9, 10-19, 20-29,
		 * so picked items will be 5, 15, 25
		 */
		for(int i=0; i<3; i++) {
			int index = i*itemsInPage.size()/3 + itemsInPage.size()/6;
			searchResultsPage.clickOnItemCheckboxByIndex(index);
			Assert.assertTrue(searchResultsPage.getStateOfItemCheckboxByIndex(index));
			pickedItems.add(itemsInPage.get(index));
		}
		
		// 10. Press “Добавить выбранные в закладки” ( = add to memo)
		searchResultsPage.clickOnAddToMemoLink();
		searchResultsPage.clickOnAlertOKButton();

		// 11. Open “Закладки” and check that the ads on the page match the previously selected
		favoritesPage = searchResultsPage.clickOnFavoritesLink();
		ArrayList<String> favoriteItems = favoritesPage.getAllResultsFromPage();
		
		logger.debug("picked items");
		for(String s : pickedItems)logger.debug(s);
		logger.debug("fav items");
		for(String s : favoriteItems)logger.debug(s);
		
		Assert.assertTrue(favoriteItems.containsAll(pickedItems), "Picked and Favorited items must be the same");
		Assert.assertTrue(pickedItems.containsAll(favoriteItems), "Picked and Favorited items must be the same");
		
		
		// 12. Close the browser. - TestNg will do it in afterSuite metod
	}
}
