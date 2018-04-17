package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RulesPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(RulesPage.class);
	
	public RulesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "dnn_ctr431_ContentPane")
	WebElement rulesContent;
	
	public boolean isRulesContentDisplayed () {
		try{
			return rulesContent.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
}
