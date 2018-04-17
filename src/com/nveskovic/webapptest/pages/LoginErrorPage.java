package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginErrorPage extends BasePage{

	public LoginErrorPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "dnn_ctr431_ContentPane")
	WebElement errorMessagePanel;

	public boolean isErrorLoginPanelDisplayed() {
		return errorMessagePanel.isDisplayed();
	}
	
	public String getErrorLoginPanelText() {
		return errorMessagePanel.getText();
	}
}
