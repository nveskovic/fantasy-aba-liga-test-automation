package com.nveskovic.webapptest.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	protected WebDriver driver;
	protected String title;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (className = "title2")
	WebElement title2;
	
	@FindBy (id = "dnn_ctr431_ContentPane")
	WebElement homePageContent;
	
	@FindBy(xpath = "//a[@href='index.php']")
	WebElement homeLink;
	
	@FindBy(xpath = "//a[@href='team.php']")
	WebElement myTeamLink;
	
	@FindBy(xpath = "//a[@href='standings.php']")
	WebElement standingsLink;
	
	@FindBy(xpath = "//a[@href='leagues.php']")
	WebElement privateLeaguesLink;
	
	@FindBy(xpath = "//a[@href='login.php']")
	WebElement loginLink;
	
	final String logoutLinkXPath = "//a[@href='logout.php']";
	@FindBy(xpath = logoutLinkXPath)
	WebElement logoutLink;
	
	@FindBy(xpath = "//a[@href='change_team.php']")
	WebElement changeTeamLink;
	
	@FindBy(xpath = "//a[@href='players_list2.php']")
	WebElement playersLink;
	
	@FindBy(xpath = "//a[@href='rules.php']")
	WebElement rulesLink;
	
	@FindBy(xpath = "//a[@href='how_to_play.php']")
	WebElement howToPlayLink;
	
	@FindBy(xpath = "//a[@href='prizes.php']")
	WebElement prizesLink;
	
	
	public String getInnerTitle() {
		return this.title2.getText();
	}
	
	public boolean isLoggedIn(int timeout){
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		try{
			return driver.findElement(By.xpath(logoutLinkXPath)).isDisplayed();
		} catch(Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}
	
	public BasePage logout() {
		logoutLink.click();
		return PageFactory.initElements(driver, BasePage.class);
	}
	
	public ChangeTeamPage clickOnChangeTeamLink(){
		this.changeTeamLink.click();
		return PageFactory.initElements(driver, ChangeTeamPage.class);
	}

	
	public BasePage clickOnHomeLink() {
		homeLink.click();
		return PageFactory.initElements(driver, BasePage.class);
	}
	
	public TeamPage clickOnMyTeamLink() {
		myTeamLink.click();
		return PageFactory.initElements(driver, TeamPage.class);
	}

	public LoginPage clickOnLoginLink() {
		loginLink.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public StandingsPage clickOnStandingsLink(){
		standingsLink.click();
		return PageFactory.initElements(driver, StandingsPage.class);
	}
	
	public PrivateLeaguesPage clickOnPrivateLeaguesLink(){
		privateLeaguesLink.click();
		return PageFactory.initElements(driver, PrivateLeaguesPage.class);
	}

	public PlayersPage clickOnPlayersLink() {
		playersLink.click();
		return PageFactory.initElements(driver, PlayersPage.class);
	}
	
	public RulesPage clickOnRulesLink() {
		rulesLink.click();
		return PageFactory.initElements(driver, RulesPage.class);
	}
	
	public HowToPlayPage clickOnHowToPlayLink() {
		howToPlayLink.click();
		return PageFactory.initElements(driver, HowToPlayPage.class);
	}
	
	public PrizesPage clickOnPrizesLink() {
		prizesLink.click();
		return PageFactory.initElements(driver, PrizesPage.class);
	}

	public final boolean isHomePageContentDisplayed() {
		return homePageContent.isDisplayed();
	}
}
