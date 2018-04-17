package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayersPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(PlayersPage.class);
	
	public PlayersPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "aba_filter_table_players_list")
	WebElement playersTable;

	public boolean isPlayersTableDisplayed () {
		try{
			return playersTable.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
}
