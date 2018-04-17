package com.nveskovic.webapptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeTeamPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(ChangeTeamPage.class);
			
	public ChangeTeamPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "aba_filter_table_standings")
	WebElement teamsTable;
	
	@FindBy(name = "change_button")
	WebElement changeTeamButton;
	
	public void clickOnTeamRadioButtonByTeamName(String teamName) {
		logger.debug("teamsTable.isDisplayed(): " + teamsTable.isDisplayed());
		
		WebElement td = teamsTable.findElement(By.xpath(
				"tbody/tr/td/b[text()='" + teamName + "']/..")); // locate cell with team name
		logger.debug("td.isDisplayed(): " + td.isDisplayed());
		
		WebElement elem = td.findElement(By.xpath("../td/input[@name='team_id']")); // locate radio button within the table row
		logger.debug("elem.isDisplayed(): " + elem.isDisplayed());
		elem.click();
	}
	
	public TeamPage clickOnChangeTeamButton(){
		this.changeTeamButton.click();
		return PageFactory.initElements(driver, TeamPage.class);
	}

}
