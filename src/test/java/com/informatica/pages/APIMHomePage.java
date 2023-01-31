package com.informatica.pages;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;

/*
############################################################################
Description:Page object which represents APIM home page and it contains all web elements and actions that performed on it.
Author: cgandu
Date Created: 30/06/2022
JIRA TCs associated:
Coverage: Sanity
############################################################################ */

public class APIMHomePage extends BasePage {

	public APIMHomePage(Page page) {
		
		super(page);
		MyLogger.info("Initiated APIMHomePage object");

	}
	
	String analyticsTab = "//div[@class='d-shell__nav__links_group']//li//span[text()='Analytics']";
	
	/**
	 * Clicks analytics navigation tab.
	 */
	public void clickAnalytics() {
		try {
			page.locator(analyticsTab).click();
			MyLogger.info("Clicked Analytics Tab");
		}catch(Exception e) {
			MyLogger.error("Exception while clicking on Analytics : " + e);
		}
		
	}
	
}
