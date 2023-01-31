/****************************************************************************
@Description: This Page class is created to  track the API page web elements and it's associated methods
@Author:@nvaddadi
@Date Created:29-06-2022
@JIRA TCs associated: APIM-1501, APIM-1502, APIM-1503
@Coverage: UI,Sanity,Regression
*********************************************************************************/


package com.informatica.pages;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIPage extends BasePage {

	public APIPage(Page page) {
		super(page);

		MyLogger.info("Initiated API Page object");

	}

	String post = "//span[text()='POST']";
	String tryButton = "//button[text()='Try it out ']";
	String execute = "//button[text()='Execute']";
	String responseCode = "//td[@class='response-col_status']";
	String response = "//*[text()='\"Testing\"']";	
	String APIURL="//div[@data-bo='API Details General Managed URL']/div[1]";
	String copyURL="//span[text()='Copy URL']";

	/**
	 * @Description - This method executes the api from swagger tab present in the api page (will not exclusively open the swagger in another tab)
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public void executeApi() {
		MyLogger.info("Click on Post button ");
		page.locator(post).click();
		MyLogger.info("Click on Try It Out button ");
		page.locator(tryButton).click();
		MyLogger.info("Click on Execute button ");
		page.locator(execute).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	/**
	 * @Description - This method returns the response code post executing the url in swagger tab of api page
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 * @return returns the response code
	 */
	public String getResponseCode() {
		return page.locator(responseCode).first().innerText();

	}

	/**
	 * @Description - This method returns the response body post executing the url in swagger tab of api page
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 * @return returns the response body
	 */
	public String getResponse() {
		return page.locator(response).first().innerText();
	}
	
	
	/**
	 * @Description - This method gets the url from api page
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 * @return returns the url
	 */
	public String getApiUrl()
	{
		String url=page.locator(APIURL).first().innerText();
		return url;
	}
	
	
	/**
	 * @Description - This method gets the url copied from browser
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 * @return returns the url
	 */
	public String copyApiUrl()
	{
		String CopiedAPIURL = null;
		try {
			page.locator(copyURL).click();
			CopiedAPIURL = GetClipBoardData();
		}
		catch(Exception e)
		{
			MyLogger.info("Exception while copying the URL : " + e);
		}
		return CopiedAPIURL;
		
	}

}
