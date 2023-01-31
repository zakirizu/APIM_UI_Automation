/****************************************************************************
Description: This Page class is created to track the web elements of API Portal Home/Analytics page and it's associated methods.
             User can navigate to required tab/page in API Portal chicklet using this page class. 
Author:@nvaddadi
Date Created:30-06-2022
JIRA TCs associated: APIM-1500
Coverage: UI,Sanity,Regression
*********************************************************************************/

package com.informatica.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIPortalHomePage extends BasePage {

	public APIPortalHomePage(Page page) {

		super(page);
		MyLogger.info("Initiated API Portal HomePage object");

	}

	String HomeTab = "//span[text()='Home']";
	String APIRegistryTab = "//span[text()='API Registry']";
	String APIGroupsTab = "//span[text()='API Groups']";

	// Home Tab WebElement
	String overviewTab = "//div[@class='d-shell__page__content' and @data-bo='ANALYTICS RESTRICTION']//button/span[text()='Overview']";
	String defaultNumberOfDays = "//input[@class='d-dropdown__search' and @value='Last 7 days']";
	String topApisHeader = "//div[@data-bo='topApisSection']//div[@class='d-section__header']//span[contains(text(),'My Top APIs')]";
	String topApiTableHeaders = "//div[@data-bo='topApisSection']//div[@class='d-section__content']//tr[@class='d-table__header__row']/th/span";

    // String datevalue="svg > g.highcharts-axis-labels.highcharts-xaxis-labels > text";
	// String datevalue="//*[local-name()='svg']/*[local-name()='g'][7]/*";
	//String datevalue = "//*[local-name()='svg']/*[local-name()='g'][7]/*[local-name()='text']";
	//String test1 = "//*[local-name()='svg']/*[local-name()='g'][7]/*[local-name()='text'][1]";
	
	String datevalue = "//*[name()='g']/*[name()='text']";
	String apiCount = "//div[@data-bo='topApisSection']//tbody/tr";
	String spinner = "//div[@class='d-loader__spinner']";
	
	/**
	 * @Description - This method navigates the Application to apiPortal Home page
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @throws Exception 
	 *  
	 */
	public void clickApiPortalHomePage() throws Exception {
		try {
		//	page.reload();
		
			page.locator(HomeTab).click();
			CheckPageisLoading();
			page.waitForLoadState(LoadState.NETWORKIDLE);
	
	
			MyLogger.info("Clicked on API portal home page");
		} catch (Exception e) {
			MyLogger.error("Exception while clicking on API Portal home page : " + e);
			throw e;
		}

	}
	
	public void WaiforPageGridLoad()
	{
	page.locator(Grid_Count).waitFor();
	}


	
	
	/**
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method navigates the Application to  apiPortal Registry page
	 * 
	 */
	public void clickApiRegistryTab() {
		try {
			page.reload();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.locator(APIRegistryTab).click();
			
			MyLogger.info("Clicked on API Registry tab");
		} catch (Exception e) {
			MyLogger.error("Exception while clicking on API Registry page : " + e);
			throw e;
		}

	}

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method navigates the Application to apiPortal Groups page
	 * 
	 */
	public void clickApiGroupsTab() {
		try {
		//	page.reload();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.locator(APIGroupsTab).click();
		
			MyLogger.info("Clicked on API Groups tab");
		} catch (Exception e) {
			MyLogger.error("Exception while clicking on API Groups page : " + e);
			throw e;
		}

	}

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method has the list of expected dates in API Usage graph
	 * @return - returns list of dates in string format
	 * 
	 */
	public List<String> expectedGetDates() {
		List<String> expectedDates = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd");

		for (int i = 0; i < 7; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -i);
			Date date = cal.getTime();
			String exdate = dateFormat.format(date);
			char[] ch = exdate.toCharArray();
			if(ch[0]=='0')
				ch[0]=' ';
			if(ch[3]=='0')
				ch[3]=' ';
			exdate=String.valueOf(ch);
			exdate=exdate.replaceAll(" ", "");
			expectedDates.add(exdate);
		}
		return expectedDates;
	}

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method validates the dates populated in API Usage Graph
	 * @return - returns list of dates in string format
	 * 
	 */
	public List<String> validateApiUsageGraphDates() {
		
		
		WaitforElement(datevalue);
		List<String> actualDates = page.locator(datevalue).allTextContents();
		//removing the additional element value, which came from the application
	//	actualDates.remove(7);
		MyLogger.info("Actual dates populated on the Graph are: "+actualDates);
		System.out.println("Actual dates populated on the Graph are:"+actualDates);
		
		List<String> expectedDates = expectedGetDates();
		MyLogger.info(" Expected date values should be populated on the Graph are: "+expectedDates);
		
		System.out.println("Expected date values are: " + expectedDates);
		System.out.println("Actual date values are: " + actualDates);
		expectedDates.removeAll(actualDates);
		return expectedDates;
	}

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method validates the overview tab existence in API Portal Home page
	 * @return - returns boolean value as true, if overview tab is exist else returns false
	 * 
	 */
	public boolean validateOverviewTab() {
		MyLogger.info("validateOverviewTab :: START");
		boolean b = page.locator(overviewTab).isVisible();
		return b;
	}

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method validates whether topAPI table has the API's or not
	 * @return - returns boolean value as true, if API's are available in topAPI's tab
	 * @throws InterruptedException 
	 * 
	 */
	public boolean validateTopApisCount() throws InterruptedException {
		MyLogger.info("validateTopApis :: START");
		
		CheckPageisLoading();
		
		Locator api = page.locator(apiCount);
		MyLogger.info("count of api's are :" + api.count());
		if (api.count() > 1)
			return true;
		else
			return false;

	}
	
	public void CheckPageisLoading() throws InterruptedException {
		
		MyLogger.info("Checking Page is Loading :: START");
		
		while(page.locator(spinner).count()>0)
			
		{
			System.out.println("Page is loading");
			Thread.sleep(5000);
		}
		
	
	}
	
	

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method validates topAPI tab header names
	 * @return - returns List of Strings of actual API headers
	 * 
	 */
	public List<String> validateTopApiHeaders() {

		// List<String> topApiHeaders =
		// page.locator(topApiTableHeaders).allTextContents();
		List<String> topApiHeaders = page.locator(topApiTableHeaders).allInnerTexts();
		List<String> expectedTopApiHeaders = getExpectedTopApiHeaders();
		System.out.println("Expected API headers are: " + expectedTopApiHeaders);
		System.out.println("actual API headers are: " + topApiHeaders);
		expectedTopApiHeaders.removeAll(topApiHeaders);
		return expectedTopApiHeaders;

	}

	/**
	 * @date 30-06-2022
	 * @author nvaddadi 
	 * @Description - This method has the list of API header names in top API table
	 * @return - returns List of Strings of expected API headers
	 * 
	 */
	private List<String> getExpectedTopApiHeaders() {
		List<String> headers = new ArrayList<>();
		headers.add("API Name");
		headers.add("API URL");
		headers.add("Protocol");
		headers.add("Invocations");
		return headers;
	}
	
	

}
