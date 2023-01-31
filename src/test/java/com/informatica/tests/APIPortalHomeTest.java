/****************************************************************************
Description: This Test class is created to track the test methods written on API Portal home page             
Author:@nvaddadi
Date Created:30-06-2022
JIRA TCs associated: APIM-1500
Coverage: UI,Sanity,Regression
*********************************************************************************/


package com.informatica.tests;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIPortalHomePage; 
import com.informatica.pages.LoginPage;
import com.informatica.web.util.ConfigPropertiesLoader;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.options.LoadState;



public class APIPortalHomeTest extends BaseTest {

	LoginPage loginPage = null;
	APIPortalHomePage apiPortalPage = null;

	@BeforeTest
	public void init() throws Exception {
		MyLogger.startLogger("APIPortalHomeTest");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.clickOnApiChiklet();
			apiPortalPage = new APIPortalHomePage(page);
		} catch (Exception e) {
			MyLogger.error("Exception while navigating to API  : " + e);
			throw e;
		}
	}

	/**
	 * @Description - This test method validates the Analytics overview tab (like
	 *              headers, date range, dates etc)
	 * @date created 30-06-2022
	 * @author nvaddadi
	 */
	@Test(priority = 1)
	public void validateAnalyticsOverviewTab() throws Exception {

		MyLogger.info("================= validateAnalyticsOverviewTab : START ================== ");
		loginfo.info("================= validateAnalyticsOverviewTab : START ================== ");

		try {
			apiPortalPage.clickApiPortalHomePage();
			loginfo.info("Clicked on API Portal Analytics Tab");
			Assert.assertTrue(apiPortalPage.validateOverviewTab(), "Analytics Overviview tab is not present");
			loginfo.info("Overview tab with API usage trend graph is avaialble");

			loginfo.info("Verifying the dates present in the API Usgae Trend Graph");
			List<String> result = apiPortalPage.validateApiUsageGraphDates();
			Assert.assertTrue(result.isEmpty(),
					"Expected headers and actual headers/dates are not matching on the Graph usage");

		} catch (Exception e) {
			MyLogger.error("Exception while validateAnalyticsOverviewTab  : " + e);
			loginfo.info("Exception while validateAnalyticsOverviewTab  : " + e);
			Assert.fail();

		}
		MyLogger.info("================= validateAnalyticsOverviewTab : END ================== ");
		loginfo.info("================= validateAnalyticsOverviewTab : END ================== ");
	}

	/**
	 * @Description - This test method validates the topAPI tab (like headers, api
	 *              count etc)
	 * @date created 30-06-2022
	 * @author nvaddadi
	 */
	@Test(priority = 2)
	public void validateTopApisTab() throws Exception {
		MyLogger.info("================= validateTopApisTab : START ================== ");
		loginfo.info("================= validateTopApisTab : START ================== ");

		try {
			apiPortalPage.clickApiPortalHomePage();

			loginfo.info("Clicked on API Portal Analytics Tab");
			Assert.assertTrue(apiPortalPage.validateTopApiHeaders().isEmpty(),
					"Top API tab Headers are not matching with expected headers ");
			loginfo.info("Top API's tab headers are populated correctly");
			
		
			loginfo.info("Verify the Top API's tab to check whether API's are populated or not");
			if (ConfigPropertiesLoader.getProperty("Env_name").trim().equalsIgnoreCase("rel1") || ConfigPropertiesLoader.getProperty("Env_name").trim().equalsIgnoreCase("rel") || ConfigPropertiesLoader.getProperty("Env_name").trim().equalsIgnoreCase("mrel")) {
				loginfo.info("Ignoring the Top API's tab validation in this environment: "
						+ ConfigPropertiesLoader.getProperty("Env_name"));
			}
			else {
			
				Boolean APICount=apiPortalPage.validateTopApisCount();
				
				if (!APICount)
				{
					loginfo.log(Status.INFO,"Records are not present for 7 days so selecting the Last 90 days");

	
				apiPortalPage.selectAPIRegistryDropdownOption("Last 90 days");
				APICount=apiPortalPage.validateTopApisCount();
				loginfo.info("API count present in the Top API's tab is: " + APICount);
				Assert.assertTrue(APICount, "Top API's Tab is not populated correctly");
			
				MyLogger.info("API count present in the Top API's tab is " + APICount);
				loginfo.info("Top API's tab is listed correctly");
				}
				else
				{
					Assert.assertTrue(APICount, "Top API's Tab is not populated correctly");
					loginfo.info("API count present in the Top API's tab is: " + APICount);
					MyLogger.info("API count present in the Top API's tab is " + APICount);
					loginfo.info("Top API's tab is listed correctly");
				
				}
				
			}

		} catch (Exception e) {
			MyLogger.error("Exception while validating validateTopApisTab : " + e);
			Assert.fail();
		}

		MyLogger.info("================= validateTopApisTab : END ================== ");
		loginfo.info("================= validateTopApisTab : END ================== ");
	}

}
