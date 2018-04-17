package com.nveskovic.webapptest.test;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nveskovic.webapptest.pages.BasePage;
import com.nveskovic.webapptest.pages.PrivateLeaguesPage;
import com.nveskovic.webapptest.pages.StandingsPage;

public class PrivateLeagueTest extends BaseTest{

	private static Logger logger = LoggerFactory.getLogger(PrivateLeagueTest.class);
	
	private PrivateLeaguesPage privateLeaguesPage;
	private StandingsPage standingsPage;
	
	private final String ALL_ROUNDS_OPTION = "All rounds";

	@BeforeClass(alwaysRun = true)
	public void beforeClass(){
		privateLeaguesPage = loginToHomePage().clickOnPrivateLeaguesLink();
	}
	
	/*
	 * Steps:
	 * 1. Login to app using valid credentials
	 * 2. Click on ""Private leagues"" link
	 * 3. Click on private league link
	 * 4. Select ""All rounds"" and get the number of points for your team
	 * 5. Select specific rounds (all available) and collect points for your team for selected round. 
	 * Sum up all points from specific rounds.
	 * 
	 * Expected: Number of points shown in step 4. should be equal to sum of points collected in step 5.
	 */
	@Test(groups = {"P1"}, 
			description = "Private leagues - Verify sum of points per round matches total number of points")
	public void verifySumOfRoundPointsMatchesTotalPoints() {

		int totalPoints = -1;
		
		standingsPage = privateLeaguesPage.clickOnPrivateLeagueLinkByLeagueName(privateLeagueName);
		List<String> availableRounds = standingsPage.getAllOptionsFromRoundsSelectBox();
		logger.debug("availableRounds: " + Arrays.toString(availableRounds.toArray()));
		/*
		 * Get total number of points for the team
		 */
		standingsPage.selectRound(ALL_ROUNDS_OPTION);
		standingsPage = standingsPage.clickOnApplyFilterButton();
		totalPoints = Integer.parseInt(standingsPage.getNumberOfPointsInTableByTeamName(teamName));
		
		/*
		 * Get number of points in each round and sum them up
		 */
		int sumOfAllRounds = 0;
		for(String round : availableRounds) {
			if(round.equals(ALL_ROUNDS_OPTION)) continue; // skip ALL_ROUNDS_OPTION
			
			standingsPage.selectRound(round);
			standingsPage = standingsPage.clickOnApplyFilterButton();
			sumOfAllRounds += Integer.parseInt(standingsPage.getNumberOfPointsInTableByTeamName(teamName));
		}
		
		Assert.assertEquals(sumOfAllRounds, totalPoints, "Sum of points is equal to points shown for all rounds");
	}
}
