package com.toptal.webapptest.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toptal.webapptest.pages.BasePage;
import com.toptal.webapptest.pages.ChangeTeamPage;
import com.toptal.webapptest.pages.TeamPage;

public class ChangeTeamTest extends BaseTest {
	
	BasePage basePage;
	ChangeTeamPage changeTeamPage;
	TeamPage teamPage;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(){
		
		basePage = loginToHomePage();
		changeTeamPage = basePage.clickOnChangeTeamLink();
	}
	
	
	/*
	 * "Steps:
	 * 1. Login to app using valid credentials
	 * 2. Click on ""Change team"" link
	 * 3. Select different team to manage
	 * 4. click on ""Change team"" button
	 * 
	 * Expected: After step 4. team selected in step 3. should be selected for managing"
	 */
	@Test(groups = {"P1"}, description = "Manage more than one team")
	public void switchToOtherTeam() {
		
		changeTeamPage.clickOnTeamRadioButtonByTeamName(teamName2);
		teamPage = changeTeamPage.clickOnChangeTeamButton();
		Assert.assertEquals(teamPage.getTeamName(), teamName2, "User changed to selected team");
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass(){
		/*
		 * Better practice is to perform rollback actions using API (if applicable)
		 * or by some other way quicker than clicking through web pages
		 */
		teamPage = basePage.clickOnMyTeamLink();
		if(!teamPage.getTeamName().trim().equals(teamName)) {
			changeTeamPage = basePage.clickOnChangeTeamLink();
			changeTeamPage.clickOnTeamRadioButtonByTeamName(teamName);
			teamPage = changeTeamPage.clickOnChangeTeamButton();
			Assert.assertEquals(teamPage.getTeamName(), teamName, "User reverted to original team");
		}
	}
}
