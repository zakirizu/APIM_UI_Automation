/**
 * ************************************************************************************************************************
 * @description This page contains all methods related to the Swagger Tab in the Managed API page of API Manager
 * @author abehera
 * @date_created  29-Jun-2022
 * ************************************************************************************************************************
 */


package com.informatica.pages;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIMSwaggerPage extends BasePage{

	public APIMSwaggerPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	String apiSwaggerTab = "//span[text()='Swagger']";
	String apiExpandButton = "//button[@class='opblock-summary-control']";
	String tryItOutButton = "//button[text()='Try it out ']";
	String executeButton = "//button[text()='Execute']";
	String serverResponseCode = "//tr[@class='response']//td[@class='response-col_status']";
	String serverResponseBody = "//pre[@class=' microlight']/code[@class='language-json']";
	String inputUsername = "//input[@placeholder='Enter User Name']";
	String inputPassword = "//input[@placeholder='Enter Password']";
	String logInButton = "//span[text()='Log In']";
	
	/**
	 * ************************************************************************************************************************
	 * @description This method is to click on the Swagger Tab of an opened Managed API in API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * ************************************************************************************************************************
	 */
	
	public void switchToSwaggerTab()
	{
		try{
			MyLogger.info("Clicking on Swagger tab of Managed API");
			page.locator(apiSwaggerTab).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		}catch(Exception e) {
			MyLogger.error("Exception while loading Swagger tab" + e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This method is to execute a Managed API in the Swagger tab without giving any request body
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * ************************************************************************************************************************
	 */
	
	public void executeAPIonSwaggerTab() {
		try{
			MyLogger.info("Executing API in Swagger tab");
			page.locator(apiExpandButton).click();
			page.locator(tryItOutButton).click();
			page.locator(executeButton).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		}catch(Exception e) {
			MyLogger.error("Exception while Executing API in Swagger tab" + e.getStackTrace());
			throw e;
		}
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This method will return the response code after executing a Managed API in the Swagger tab of API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @return responseCode: API execution response code
	 * ************************************************************************************************************************
	 */
	
	public String getAPIExecutionResponseCode() {
		String responseCode = "";
		try {
			MyLogger.info("Getting response code of Swagger execution");
			responseCode = page.locator(serverResponseCode).innerText();
		}catch(Exception e) {
			MyLogger.error("Exception while getting response code from Swagger tab" + e.getStackTrace());
			throw e;
		}
		return responseCode;
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This method will return the response body after executing a Managed API in the Swagger tab of API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @return responseBody: API execution response body
	 * ************************************************************************************************************************
	 */
	
	public String getAPIExecutionResponseBody() {
		String responseBody = "";
		try {
			MyLogger.info("Getting response body of Swagger execution");
			responseBody = page.locator(serverResponseBody).innerText();
		}catch(Exception e) {
			MyLogger.error("Exception while getting response body from Swagger tab" + e.getStackTrace());
			throw e;
		}
		return responseBody;
	}

	/**
	 * ************************************************************************************************************************
	 * @description This method is used to login into the Swagger tab of a Basic Managed API in API Manager 
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @param username: username to run the Basic managed API
	 * @param password: password to run the Basic managed API
	 * ************************************************************************************************************************
	 */
	
	public void loginIntoSwaggerTab(String username, String password) {
		try{
			MyLogger.info("Logging into Swagger tab of Basic Managed API");
			page.fill(inputUsername, username);
			page.fill(inputPassword, password);
			page.locator(logInButton).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		}catch(Exception e) {
			MyLogger.error("Exception while logging in Swagger tab" + e.getStackTrace());
			throw e;
		}
	}

}

	
