package com.nveskovic.webapptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "username")
	WebElement usernameField;
	
	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(name = "login_button")
	WebElement loginButton;

	public BasePage login(String user, String pwd){
		return this.login(user, pwd, BasePage.class);
	}
	
	public <T extends BasePage> T login(String user, String pwd, Class<T> p) {
		
		this.usernameField.clear();
		this.usernameField.sendKeys(user);
		
		this.passwordField.clear();
		this.passwordField.sendKeys(pwd);
		
		this.loginButton.click();
		
		return PageFactory.initElements(driver, p);
		
	}
}
