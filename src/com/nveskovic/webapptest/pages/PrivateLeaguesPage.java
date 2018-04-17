package com.nveskovic.webapptest.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateLeaguesPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(PrivateLeaguesPage.class);
	
	public PrivateLeaguesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (name = "privateleaguename")
	WebElement createNewPrivateLeagueNameTextField;
	
	public boolean isCreateNewPrivateLeagueTextFieldDisplayed () {
		try{
			return createNewPrivateLeagueNameTextField.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
	
	public StandingsPage clickOnPrivateLeagueLinkByLeagueName(String leagueName) {
		driver.findElement(By.linkText(leagueName)).click();
		return PageFactory.initElements(driver, StandingsPage.class);
	}
}
