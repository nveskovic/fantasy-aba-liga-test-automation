package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;

public class TeamPage extends BasePage{

	public TeamPage(WebDriver driver) {
		super(driver);
	}
	
	public String getTeamName(){
		return title2.getText().trim();
	}
}
