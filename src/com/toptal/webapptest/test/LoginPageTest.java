package com.toptal.webapptest.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.toptal.webapptest.pages.LoginPage;
import com.toptal.webapptest.pages.PlayersPage;
import com.toptal.webapptest.pages.PrivateLeaguesPage;
import com.toptal.webapptest.pages.PrizesPage;
import com.toptal.webapptest.pages.RulesPage;
import com.toptal.webapptest.pages.StandingsPage;
import com.toptal.webapptest.pages.BasePage;
import com.toptal.webapptest.pages.HowToPlayPage;
import com.toptal.webapptest.pages.LoginErrorPage;
import com.toptal.webapptest.pages.TeamPage;

public class LoginPageTest extends BaseTest {
	
	BasePage basePage;
	LoginPage loginPage;
	LoginErrorPage loginErrorPage;
	TeamPage teamPage;
	private StandingsPage standingsPage;
	private PrivateLeaguesPage privateLeaguesPage;
	private PlayersPage playersPage;
	private RulesPage rulesPage;
	private HowToPlayPage howToPlayPage;
	private PrizesPage prizesPage;
	
	@BeforeMethod(alwaysRun = true)
	public void loguotIfNeeded() {
		basePage = PageFactory.initElements(driver, BasePage.class);
		
		if(basePage.isLoggedIn(0))
			basePage = basePage.logout();
		
		basePage.clickOnLoginLink(); 
		loginPage = PageFactory.initElements(driver, LoginPage.class);	
		
		Assert.assertFalse(loginPage.isLoggedIn(3), "User is not logged in");

	}
	
	@DataProvider(name = "invalidCredentialsDP")
	public Object[][] invalidCredentialsDP(){
		// same expected error message for login page
		String msg = "Invalid login credentials. Try again to log in or register for a new account.";
		
		Object[][] credentials = {
			
			{"","", msg}, // empty creds
			{"", password, msg}, // blank username
			{"blabla", "", msg}, // blank password
			/*have to turn this off because of the game rules
			{username, password+"1", msg} // wrong password*/
		};
		return credentials;
	}
	
	
	/*
	 * Steps:
	 * 1. Login to app using valid credentials
	 * 2. Navigate through all pages available in the menu and verify the pages are accessible 
	 * and main content of the page is displayed
	 * 
	 */
	@Test(groups = {"P0"}, description = "Login using valid credentials and verify main pages are available")
	public void loginWithValidCredentials() {
		
		// login and verify team page
		teamPage = loginPage.login(username, password, TeamPage.class);
		Assert.assertTrue(teamPage.isLoggedIn(3), "User is logged in");
		Assert.assertEquals(teamPage.getTeamName(), teamName, "User is logged in to expected team");
		
		// verify standings page
		standingsPage = teamPage.clickOnStandingsLink();
		Assert.assertTrue(standingsPage.isStandingsTableDisplayed(), "Standings table is displayed");
		Assert.assertEquals(standingsPage.getInnerTitle().trim(), "Standings", 
				"Inner title is as expected");
		
		
		// verify private leagues page
		privateLeaguesPage = standingsPage.clickOnPrivateLeaguesLink();
		Assert.assertTrue(privateLeaguesPage.isCreateNewPrivateLeagueTextFieldDisplayed(), 
				"Create new private league field is displayed");
		Assert.assertEquals(privateLeaguesPage.getInnerTitle().trim(), "Private Leagues", 
				"Inner title is as expected");
		
		// verify players page
		playersPage = privateLeaguesPage.clickOnPlayersLink();
		Assert.assertTrue(playersPage.isPlayersTableDisplayed(), 
				"Players table is displayed");
		Assert.assertEquals(playersPage.getInnerTitle().trim(), "Players list", 
				"Inner title is as expected");
		
		// verify rules page
		rulesPage = playersPage.clickOnRulesLink();
		Assert.assertTrue(rulesPage.isRulesContentDisplayed(), 
				"Rules content is displayed");
		Assert.assertEquals(rulesPage.getInnerTitle().trim(), "Rules", 
				"Inner title is as expected");
		
		// verify How To Play page
		howToPlayPage = rulesPage.clickOnHowToPlayLink();
		Assert.assertTrue(howToPlayPage.isHowToPlayContentDisplayed(), 
				"How To Play content is displayed");
		Assert.assertEquals(howToPlayPage.getInnerTitle().trim(), "How to play", 
				"Inner title is as expected");
		
		// verify prizes page
		prizesPage = rulesPage.clickOnPrizesLink();
		Assert.assertTrue(prizesPage.isPrizesContentDisplayed(), 
				"Prizes content is displayed");
		Assert.assertEquals(prizesPage.getInnerTitle().trim(), "Prizes", 
				"Inner title is as expected");
		
		// verify home page
		basePage = prizesPage.clickOnHomeLink();
		Assert.assertTrue(basePage.isHomePageContentDisplayed(), 
				"Prizes content is displayed");
		Assert.assertEquals(basePage.getInnerTitle().trim(), "Welcome to Mozzart Basketball Fantasy", 
				"Inner title is as expected");
	}
	
	
	/*
	 * Steps:
	 * 1. Try to Login to app using invalid credentials as mentioned below:
	 * 2a. Blank both username and password
	 * 2b. Blank username
	 * 2c. Blank password
	 * 2d. Proper username, wrong password
	 * 
	 * Expected: Whe trying to login with invalid credentials, 
	 * user should not be able to login and error message saying 
	 * "Invalid login credentials. Try again to log in or register for a new account." should be displayed
	 * 
	 */
	@Test(groups = {"P2"}, description = "Try to login with invalid credentials", 
			dataProvider = "invalidCredentialsDP")
	public void loginWithInvalidCredentials(String user, String pwd, String expectedErrorMessage) {
		loginErrorPage = loginPage.login(user, pwd, LoginErrorPage.class);
		Assert.assertFalse(loginErrorPage.isLoggedIn(0), "User is not logged in");
		Assert.assertTrue(loginErrorPage.isErrorLoginPanelDisplayed(), 
				"Error login message is displayed");
		Assert.assertEquals(loginErrorPage.getErrorLoginPanelText().trim(), 
				expectedErrorMessage,
				"Error login message text as expected.");
	}
}
