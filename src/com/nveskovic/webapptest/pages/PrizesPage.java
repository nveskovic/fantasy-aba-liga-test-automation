package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrizesPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(PrizesPage.class);
	
	public PrizesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "dnn_ctr431_ContentPane")
	WebElement prizesContent;
	
	public boolean isPrizesContentDisplayed () {
		try{
			return prizesContent.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
}
