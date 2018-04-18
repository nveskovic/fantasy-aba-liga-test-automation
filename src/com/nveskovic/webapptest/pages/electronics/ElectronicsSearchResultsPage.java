package com.nveskovic.webapptest.pages.electronics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectronicsSearchResultsPage {
	private WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(ElectronicsSearchResultsPage.class);
	
	public ElectronicsSearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (className = "msg_column_td")
	WebElement priceTableHeader;
	
	@FindBy (className = "filter_second_line_dv")
	WebElement filtersHeader;

	public ElectronicsSearchResultsPage clickOnPriceLinkInTableHeader() {
		this.priceTableHeader.findElement(By.tagName("a")).click();
		return PageFactory.initElements(driver, ElectronicsSearchResultsPage.class);
	}

	public ElectronicsSearchResultsPage selectTypeOfItemsByName(String name) {
		WebElement elem = this.filtersHeader.findElements(By.tagName("select")) 	// locates all filter dropdowns
								.get(2);											// locates Type of items dropdown
		
		Select select = new Select(elem);
		select.selectByVisibleText(name);
		
		return PageFactory.initElements(driver, ElectronicsSearchResultsPage.class);
	}

}
