package com.nveskovic.webapptest.pages.electronics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nveskovic.webapptest.utils.WebDriverUtils;

public class ElectronicsSearchPage {
	
	private WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(ElectronicsSearchPage.class);


	public ElectronicsSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy (id = "ptxt")
	WebElement nameOrPhraseField;
	
	@FindBy (name = "sid")
	WebElement subsectionDropdown;
	
	@FindBy (id = "sbtn")
	WebElement searchButton;

	@FindBy (name = "topt[8][min]")
	WebElement minPrice;

	@FindBy (name = "topt[8][max]")
	WebElement maxPrice;

	public void setNameOrPhrase(String text) {
		WebDriverUtils.setText(this.nameOrPhraseField, text);
	}

	public void selectSubsectionByValue(String value) {
		Select select = new Select(this.subsectionDropdown);
		select.selectByValue(value);
	}
	
	public ElectronicsSearchResultsPage clickOnSearchButton() {
		this.searchButton.click();
		return PageFactory.initElements(driver, ElectronicsSearchResultsPage.class);
	}

	public void dismissSmartSearchList() {
		// click on first row-column in the table should dismiss SmartSearch list
		driver.findElements(By.className("td6")).get(0).click();
	}

    public void setMinPrice(String minPrice) {
		WebDriverUtils.setText(this.minPrice, minPrice);
    }
	public void setMaxPrice(String maxPrice) {
		WebDriverUtils.setText(this.maxPrice, maxPrice);
	}
}
