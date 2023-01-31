package com.informatica.pages;


import com.aventstack.extentreports.Status;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class LoginPage extends BasePage {

	/*
	 * String userName = "//input[@type='text']"; String passWord =
	 * "#password > .infaField"; String loginBtn =
	 * "//button[@class='infaButton infaButton-1']";
	 */
	String IICSTitle = "//span[@class='title']";
	String APIMManagerChiklet = "//span[text()='API Manager']";
	String APIPortalChiklet = "//span[text()='API Portal']";
	
	APIMRegistryPage apimpage;

	public LoginPage(Page page) {

		super(page);
		System.out.println(page);
		MyLogger.info("Initiated loginPage object");
	}

	public String getTitle() {

		MyLogger.info("Getting Page Title");
		
		String PageTitle = page.locator(IICSTitle).innerText();
		return PageTitle;

	}

	public String ValidateAPIMChiklet() {

		MyLogger.info("Getting APIM Chikclet");
		String APIMCHiklet = page.locator(APIMManagerChiklet).innerText();
		return APIMCHiklet;

	}
	
	public void  ClickonAPIMChiklet() {

		MyLogger.info("Clicking on  APIM Chikclet");
		page.locator(APIMManagerChiklet).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		

	}

	/*************************************************************************
	 *@author nvaddadi
	 *@date 29-06-2022
	 *@Description This method is created to click on API Portal chicklet from IICS Home page
	 **************************************************************************/
	public void  clickOnApiChiklet() {

		MyLogger.info("Clicking on  API Portal chicklet");
		page.locator(APIPortalChiklet).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		

	}

}
