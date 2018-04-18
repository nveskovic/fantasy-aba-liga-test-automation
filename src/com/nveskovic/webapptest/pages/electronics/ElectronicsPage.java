package com.nveskovic.webapptest.pages.electronics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectronicsPage {
	private WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(ElectronicsPage.class);
	
	public ElectronicsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (className = "page_header_menu")
	WebElement pageHeaderMenu;
	
	public void clickOnHeaderMenuItemByName(String linkText){
		this.pageHeaderMenu.findElement(By.linkText(linkText)).click();
	}
}
