package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HowToPlayPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(HowToPlayPage.class);
	
	public HowToPlayPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "dnn_ctr431_ContentPane")
	WebElement howToPlayContent;
	
	public boolean isHowToPlayContentDisplayed () {
		try{
			return howToPlayContent.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
}
