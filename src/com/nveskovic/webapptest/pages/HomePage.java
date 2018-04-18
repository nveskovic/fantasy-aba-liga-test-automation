package com.nveskovic.webapptest.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (className = "menu_lang")
	WebElement languageSpan;
	
	private static Logger logger = LoggerFactory.getLogger(HomePage.class);
	
	/**
	 * Selects provided language
	 * @param lang Language to select. can be "RU" or "LV"
	 */
	public void selectLanguage(String lang) {
		this.languageSpan.findElement(By.linkText(lang.toUpperCase())).click();
	}
	
	public void clickOnSectionHeaderByName(String sectionName) {
		driver.findElement(By.xpath("//div[@class='main_head2']/h2/a[text()='" + sectionName + "']")).click();
	}
	
	
}
