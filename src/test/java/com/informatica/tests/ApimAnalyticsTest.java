package com.informatica.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.informatica.pages.APIMHomePage;
import com.informatica.pages.AnalyticsPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.LoggerUtil;
import com.informatica.web.util.MyLogger;
/*
############################################################################
Description:Test class to validate Analyst tab (Overview, ActivityLog and EventLog).
Author: cgandu
Date Created: 30/06/2022
JIRA TCs associated:
Coverage: Sanity
############################################################################ */
import com.informatica.web.util.ReportsTestValidator;

public class ApimAnalyticsTest extends BaseTest {

	LoginPage loginPage = null;
	APIMHomePage apimHomePage = null;
	AnalyticsPage analyticsPage = null;

	@BeforeTest
	public void init() throws Exception {

		LoggerUtil.getLogger("ApimAnalyticsTest");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.ClickonAPIMChiklet();
			apimHomePage = new APIMHomePage(page);
			analyticsPage = new AnalyticsPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	/**
	 * Test to validate overview tab under analytics navigation link.
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void validateAnalyticsOverViewTab() throws Exception {
		LoggerUtil.setloginfo(loginfo);
		try {

			LoggerUtil.info("Started validating analytics overview page", LoggerUtil.EXTENT_AUTO_LOG);
			validateOverviewTab();
			LoggerUtil.info("Completed validating analytics overview page", LoggerUtil.EXTENT_AUTO_LOG);
		} catch (Exception e) {
			//LoggerUtil.error("Exception while validating Analytics Overview Tab  : " + e, LoggerUtil.EXTENT_AUTO_LOG);
			Assert.fail();
		}
	}

	/**
	 * Test to validate activity log tab under analytics navigation link.
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void validateAnalyticsActivityLogTab() throws Exception {
		LoggerUtil.setloginfo(loginfo);
		try {
	
			LoggerUtil.info("Started validating analytics activity page", LoggerUtil.EXTENT_AUTO_LOG);
			validateActivityLogTab();
			LoggerUtil.info("Completed validating analytics activity page", LoggerUtil.EXTENT_AUTO_LOG);
		} catch (Exception e) {
			LoggerUtil.error("Exception while validating Analytics activity Tab  : " + e, LoggerUtil.EXTENT_AUTO_LOG);
			Assert.fail();
		}
	}

	/**
	 * Test to validate events log tab under analytics navigation link.
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void validateAnalyticsEventsLogTab() throws Exception {
		LoggerUtil.setloginfo(loginfo);
		try {
	
			LoggerUtil.info("Started validating analytics events log page", LoggerUtil.EXTENT_AUTO_LOG);
			validateEventsLogTab();
			LoggerUtil.info("Completed validating analytics events log page", LoggerUtil.EXTENT_AUTO_LOG);
		} catch (Exception e) {
			LoggerUtil.error("Exception while validating Analytics events log Tab  : " + e, LoggerUtil.EXTENT_AUTO_LOG);
			Assert.fail();
		}
	}

	/**
	 * Login to IICS
	 * 
	 * @throws Exception
	 */
	/*
	 * public void loginToIICS() throws Exception {
	 * 
	 * try { loginPage.navigateTo(baseUrl);
	 * loginPage.loginIntoIICSApp(adminUsername, adminPassword); } catch (Exception
	 * e) { throw e; } }
	 */

	/**
	 * Validates overview tab
	 */
	private void validateOverviewTab() {

		try {
			MyLogger.info("validateOverviewPage :: START");
			apimHomePage.clickAnalytics();
			loginfo.info("Navigated to analytics tab");
			analyticsPage.clickOverviewTab();
			loginfo.info("Navigated to overview tab");

			Assert.assertEquals(analyticsPage.isOverviewTabVisible(), true);
			Assert.assertEquals(analyticsPage.isDefaultNumberOfDaysVisible(), true);
			Assert.assertEquals(analyticsPage.isTopApisHeaderVisible(), true);
			Assert.assertEquals(analyticsPage.isTopUsersHeaderVisible(), true);

			// validating Top Api table headers
			List<String> topApiHeaders = analyticsPage.getTopApiTableHeaders();
			List<String> expectedTopApiHeaders = ReportsTestValidator.getExpectedTopApiHeaders();
			for (int i = 0; i < expectedTopApiHeaders.size(); i++) {
				assertEquals(topApiHeaders.get(i + 1), expectedTopApiHeaders.get(i));
			}

			// validating Top Users table headers
			List<String> topUsersHeaders = analyticsPage.getTopUsersTableHeaders();
			List<String> expectedTopUsersHeaders = ReportsTestValidator.getExpectedTopUsersHeaders();
			for (int i = 0; i < expectedTopUsersHeaders.size(); i++) {
				assertEquals(topUsersHeaders.get(i + 1), expectedTopUsersHeaders.get(i));
			}
			MyLogger.info("validateOverviewPage :: END");

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * validates activity log tab
	 * 
	 * @throws Exception
	 */
	private void validateActivityLogTab() throws Exception {
		try {
			MyLogger.info("validateActivityLogTab :: START");
			apimHomePage.clickAnalytics();
			loginfo.info("Navigated to analytics tab");
			analyticsPage.clickActivityLogTab();
			loginfo.info("Navigated to activity log tab");

			Assert.assertEquals(analyticsPage.isActivityLogTabVisible(), true);
			Assert.assertEquals(analyticsPage.isActivityLogSelectedDateRangeVisible(), true);
			Assert.assertEquals(analyticsPage.isShowLogButtonVisible(), true);
			Assert.assertEquals(analyticsPage.isApiInvocationsHeaderVisible(), true);
			Assert.assertEquals(analyticsPage.getApiInvocationsUpdatedLable().contains("Updated"), true);
			Assert.assertEquals(analyticsPage.getDownloadActivityLogIcon(),
					"/apimui/static/media/download.eb9d3a17.svg");
			Assert.assertEquals(analyticsPage.getSearchBoxPlaceholder(), "Find");

			// validating api invocations table headers
			List<String> apiInvocationsHeaders = analyticsPage.getApiInvocationsHeaders();
			List<String> expectedApiInvocationsHeaders = ReportsTestValidator.getExpectedApiInvocationsHeaders();
			for (int i = 0; i < expectedApiInvocationsHeaders.size(); i++) {
				assertEquals(apiInvocationsHeaders.get(i + 1), expectedApiInvocationsHeaders.get(i));
			}
			/*
			 * int apiInvocationRowCount = page.locator(apiInvocationsTableRows).count();
			 * Thread.sleep(5000); assertNotEquals(apiInvocationRowCount, 0);
			 */
			MyLogger.info("validateActivityLogTab :: END");
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * validates events log tab
	 * 
	 * @throws Exception
	 */
	private void validateEventsLogTab() throws Exception {
		try {
			MyLogger.info("validateEventsLogTab :: START");
			apimHomePage.clickAnalytics();
			loginfo.info("Navigated to analytics tab");
			analyticsPage.clickEventsLogTab();
			loginfo.info("Navigated to events log tab");

			Assert.assertEquals(analyticsPage.isEventLogTabVisible(), true);
			Assert.assertEquals(analyticsPage.isEventLogSelectedDateRangeVisible(), true);
			Assert.assertEquals(analyticsPage.isShowLogButtonVisible(), true);
			Assert.assertEquals(analyticsPage.isEventInvocationsHeaderVisible(), true);
			Assert.assertEquals(analyticsPage.getEventsUpdatedLable().contains("Updated"), true);
			Assert.assertEquals(analyticsPage.getDownloadEventLogIcon(), "/apimui/static/media/download.eb9d3a17.svg");
			Assert.assertEquals(analyticsPage.getEventsSearchBoxPlaceholder(), "Find");

			// validating events table headers
			List<String> eventsHeaders = analyticsPage.getEventsTableHeaders();
			List<String> expectedEventHeaders = ReportsTestValidator.getExpectedEventsHeaders();
			for (int i = 0; i < expectedEventHeaders.size(); i++) {
				assertEquals(eventsHeaders.get(i + 1), expectedEventHeaders.get(i));
			}
			/*
			 * int eventsTableRowCount = page.locator(eventsTableRows).count();
			 * Thread.sleep(5000); assertNotEquals(eventsTableRowCount, 0);
			 */
			MyLogger.info("validateEventsLogTab :: END");
		} catch (Exception e) {
			throw e;
		}
	}

}
