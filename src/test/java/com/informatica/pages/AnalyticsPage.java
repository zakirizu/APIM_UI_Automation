package com.informatica.pages;

import java.util.ArrayList;
import java.util.List;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

/*
############################################################################
Description:Page object which represents Api Manager Analytics Tab and it contains all web elements and actions that performed on those web elements.
Author: cgandu
Date Created: 30/06/2022
JIRA TCs associated:
Coverage: Sanity
############################################################################ */
public class AnalyticsPage extends BasePage {

	public AnalyticsPage(Page page) {

		super(page);
		MyLogger.info("Initiated ApimAnalyticsPage object");
	}

	// Overview Tab xpaths
	String overviewTab = "//div[@class='d-shell__page__content' and @data-bo='ANALYTICS RESTRICTION']//button/span[text()='Overview']";
	String defaultNumberOfDays = "//input[@class='d-dropdown__search' and @value='Last 7 days']";
	String topApisHeader = "//div[@data-bo='topApisSection']//div[@class='d-section__header']//span[contains(text(),'Top APIs')]";
	String topUsersHeader = "//div[@data-bo='topUsersSection']//div[@class='d-section__header']//span[contains(text(),'Top Users')]";
	String topApiTableHeaders = "//div[@data-bo='topApisSection']//div[@class='d-section__content']//tr[@class='d-table__header__row']/th/span";
	String topUsersTableHeaders = "//div[@data-bo='topUsersSection']//div[@class='d-section__content']//tr[@class='d-table__header__row']/th/span";

	// Activity Log Tab xpaths
	String activityLogTab = "//div[@class='d-shell__page__content' and @data-bo='ANALYTICS RESTRICTION']//button/span[text()='Activity Log']";
	String activityLogSelectDateRange = "//div[@data-bo='Activity log']//div/span[text()='Select date range']";
	String showLogButton = "//button/span[text()='Show Log']";
	String apiInvocationsHeader = "//div[@class='d-section__header']//span[contains(text(),'API Invocations')]";
	String apiInvocationsUpdatedLable = "//span[contains(text(),'API Invocations')]//ancestor::div[@class='d-section__header']//div[@class='d-toolbar__content']/span";
	String downloadActivityLogIcon = "//span[contains(text(),'API Invocations')]//ancestor::div[@class='d-section__header']//div[@class='d-toolbar__content']/span/img";
	String searchBox = "//span[contains(text(),'API Invocations')]//ancestor::div[@class='d-section__header']//div[@class='d-toolbar__content']/div/input[@type='text']";
	String apiInvocationsTableHeaders = "//span[contains(text(),'API Invocations ')]//ancestor::div[@class='d-section__header']//following::div[@class='d-section__content']//thead[@class='d-table__header']//tr/th";
	String apiInvocationsTableRows = "//span[contains(text(),'API Invocations ')]//ancestor::div[@class='d-section__header']//following::div[@class='d-section__content']//tbody[@class='d-table__body']//tr";

	// Event Log Tab xpaths
	String eventLogTab = "//div[@class='d-shell__page__content' and @data-bo='ANALYTICS RESTRICTION']//button/span[text()='Event Log']";
	String eventLogSelectDateRange = "//div[@data-bo='Event log']//div/span[text()='Select date range']";
	String eventInvocationsHeader = "//div[@class='d-section__header']//span[contains(text(),'Events')]";
	String eventsUpdatedLable = "//span[contains(text(),'Events')]//ancestor::div[@class='d-section__header']//div[@class='d-toolbar__content']/span";
	String downloadEventLogIcon = "//span[contains(text(),'Events')]//ancestor::div[@class='d-section__header']//div[@class='d-toolbar__content']/span/img";
	String eventsSearchBox = "//span[contains(text(),'Events')]//ancestor::div[@class='d-section__header']//div[@class='d-toolbar__content']/div/input[@type='text']";
	String eventsTableHeaders = "//span[contains(text(),'Events ')]//ancestor::div[@class='d-section__header']//following::div[@class='d-section__content']//thead[@class='d-table__header']//tr/th";
	String eventsTableRows = "//span[contains(text(),'Events ')]//ancestor::div[@class='d-section__header']//following::div[@class='d-section__content']//tbody[@class='d-table__body']//tr";
	String eventsHttpStatus = "//span[contains(text(),'Events ')]//ancestor::div[@class='d-section__header']//following::div[@class='d-section__content']//tbody[@class='d-table__body']//tr[1]//td[5]//span//span";
	String eventsDescription = "//span[contains(text(),'Events ')]//ancestor::div[@class='d-section__header']//following::div[@class='d-section__content']//tbody[@class='d-table__body']//tr[1]//td[6]//span//span";
	String showLog = "//span[text()='Show Log']";
	
	/**
	 * Click activity log tab in Analytics page.
	 * 
	 * @name
	 */
	public void clickActivityLogTab() {
		try {
			page.locator(activityLogTab).click();
		} catch (Exception e) {
			MyLogger.error("Exception while clicking on Analytics Activity Log Tab : " + e);
			throw e;
		}

	}

	/**
	 * Clicks overview tab in Analytics page.
	 */
	public void clickOverviewTab() {
		try {
			page.locator(overviewTab).click();
		} catch (Exception e) {
			MyLogger.error("Exception while clicking on Overview Tab : " + e);
			throw e;
		}

	}

	/**
	 * Clicks events log tab in Analytics page.
	 * @throws Exception 
	 */

	public void clickEventsLogTab() throws Exception {
		try {
			MyLogger.info("Clicking on  EventlogTab ");

			page.locator(eventLogTab).click();
			CheckPageisLoading();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		//	page.locator("("+eventsTableRows+")[1]").waitFor();

		} catch (Exception e) {
			MyLogger.error("Exception while clicking on Events log Tab : " + e);
			throw e;
		}

	}
	

	
	
	
	public void clickShowLog() throws Exception {
		try {
			MyLogger.info("Clicking on Show Log Button ");
			page.locator(showLog).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			CheckPageisLoading();
			int i=1;
			while(page.locator("("+eventsTableRows+")[1]").count()==0 || i==10)
			{
				MyLogger.info("Event log data is not present trying to Click showlog for :" +i +"time");
				page.locator(showLog).click();
				Thread.sleep(5000);
				i++;
			}

		} catch (Exception e) {
			MyLogger.error("Exception while clicking on Events log Tab : " + e);
			throw e;
		}

	}



	public boolean isEventLogTabVisible() {
		return page.locator(eventLogTab).isVisible();
	}

	public boolean isEventLogSelectedDateRangeVisible() {
		return page.locator(eventLogSelectDateRange).isVisible();
	}

	public boolean isEventInvocationsHeaderVisible() {
		return page.locator(eventInvocationsHeader).isVisible();
	}

	public String getEventsUpdatedLable() {
		return page.locator(eventsUpdatedLable).textContent();
	}

	public String getDownloadEventLogIcon() {
		return page.locator(downloadEventLogIcon).getAttribute("src");
	}

	public String getEventsSearchBoxPlaceholder() {
		return page.locator(eventsSearchBox).getAttribute("placeholder");
	}

	public List<String> getEventsTableHeaders() {
		return page.locator(eventsTableHeaders).allTextContents();
	}

	public boolean isActivityLogTabVisible() {
		return page.locator(activityLogTab).isVisible();
	}

	public boolean isActivityLogSelectedDateRangeVisible() {
		return page.locator(activityLogSelectDateRange).isVisible();
	}

	public boolean isShowLogButtonVisible() {
		return page.locator(showLogButton).isVisible();
	}

	public boolean isApiInvocationsHeaderVisible() {
		return page.locator(apiInvocationsHeader).isVisible();
	}

	public String getApiInvocationsUpdatedLable() {
		return page.locator(apiInvocationsUpdatedLable).textContent();
	}

	public String getDownloadActivityLogIcon() {
		return page.locator(downloadActivityLogIcon).getAttribute("src");
	}

	public String getSearchBoxPlaceholder() {
		return page.locator(searchBox).getAttribute("placeholder");
	}

	public List<String> getApiInvocationsHeaders() {
		return page.locator(apiInvocationsTableHeaders).allTextContents();
	}

	public boolean isOverviewTabVisible() {
		return page.locator(overviewTab).isVisible();
	}

	public boolean isDefaultNumberOfDaysVisible() {
		return page.locator(defaultNumberOfDays).isVisible();
	}

	public boolean isTopApisHeaderVisible() {
		return page.locator(topApisHeader).isVisible();
	}

	public boolean isTopUsersHeaderVisible() {
		return page.locator(topUsersHeader).isVisible();
	}

	public List<String> getTopApiTableHeaders() {
		return page.locator(topApiTableHeaders).allTextContents();
	}

	public List<String> getTopUsersTableHeaders() {
		return page.locator(topUsersTableHeaders).allTextContents();
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This method is used to verify the records(status code and description) in the Analytics event log tab, it takes searchText as an Input
	 * @author mbalaji
	 * @param searchText - The text which is used to search in the event log.
	 * @date_created  06-July-2022
	 * ************************************************************************************************************************
	 */
	public List<String> validateTheRecordsInEventLog(String searchText) throws Exception {
		try {
			MyLogger.info("validateTheRecordsInEventLog :: START");
			page.locator(eventLogTab).isVisible();
			page.locator(eventsSearchBox).isVisible();
			
			page.locator(showLogButton).click();
			page.locator(eventsSearchBox).fill(searchText);
			String Status = page.locator(eventsHttpStatus).textContent();
			String Description = page.locator(eventsDescription).textContent();
			MyLogger.info("Event status :: " + Status);
			MyLogger.info("Event description :: " + Description);
			List<String> StatusInfo=new ArrayList<String>(); 
			StatusInfo.add(Status);
			StatusInfo.add(Description);
			MyLogger.info("validateEventLogPage :: END");
			return StatusInfo;
			
		}catch(Exception e) {
			MyLogger.error("Exception while validating event log : " + e);
			throw e;
		}
	}

}
