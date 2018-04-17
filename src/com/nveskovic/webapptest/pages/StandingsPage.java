package com.nveskovic.webapptest.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandingsPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(StandingsPage.class);
	
	public StandingsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (id = "aba_filter_table_standings")
	WebElement standingsTable;
	
	@FindBy (name = "round")
	WebElement roundSelectBox;
	
	@FindBy (className = "aba_fantasy_btnsubmit")
	WebElement applyFilterButton;
	
	@FindBy(id = "aba_filter_table_standings")
	WebElement teamsTable;
	
	public boolean isStandingsTableDisplayed () {
		try{
			return standingsTable.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}

	public String getNumberOfPointsInTableByTeamName(String teamName) {
		logger.debug("teamsTable.isDisplayed(): " + teamsTable.isDisplayed());
		
		WebElement td = teamsTable.findElement(By.xpath(
				"tbody/tr/td[text()='" + teamName + "']")); // locate cell with team name
		logger.debug("td.isDisplayed(): " + td.isDisplayed());
		
		WebElement elem = td.findElement(By.xpath("../td[5]")); // 5th column are points
		logger.debug("elem.isDisplayed(): " + elem.isDisplayed());
		return elem.getText();
	}

	public void selectRound(String option) {
		Select roundSelect = new Select(roundSelectBox);
		roundSelect.selectByVisibleText(option);
	}

	public StandingsPage clickOnApplyFilterButton() {
		applyFilterButton.click();
		return PageFactory.initElements(driver, StandingsPage.class);
	}

	public List<String> getAllOptionsFromRoundsSelectBox() {
		List<String> options = new ArrayList<String>();
		
		Select roundSelect = new Select(roundSelectBox);
		List<WebElement> optionsWebElements = roundSelect.getOptions();
		for(WebElement elem : optionsWebElements) {
			options.add(elem.getText());
		}
		
		return options;
	}
}
