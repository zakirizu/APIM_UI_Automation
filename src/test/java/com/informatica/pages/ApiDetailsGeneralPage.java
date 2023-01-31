package com.informatica.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.informatica.web.util.ConfigPropertiesLoader;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

/**
 * Description: This page class consists of methods for any Managed API ->
 * General tab. Author: pdasari Date Created: 05th Jul 2022 JIRA TCs associated:
 * NA Coverage: NA
 */
public class ApiDetailsGeneralPage extends BasePage {

	private String generalDetailsPageTab = "//*[@id='root']/div/div[2]/main/div[3]/div/div[1]/ul/button[1]";
//	private String copyUrlBtn = "//*[@id='root']/div/div[2]/main/div[3]/div/div[2]/div/div[2]/div[7]/div[2]/div/div[2]/button";
//	private String copyUrlBtn = "//span[text()='Copy URL']";
	private String CopyURLTextBox = "//div[@data-bo='API Details General Managed URL']/div[1]";

	private String urlExecutionResult = "//html/body/pre";
	private String rateLimiExceedFault = "//*[@id='folder0']";
	private String clientSdkDropdownBtn = "//*[@id='root']/div/div[2]/main/div[3]/div/div[2]/div/div[2]/div[12]/div[2]/span/div/div/button";
	private String clientSdkDropdownValues = "//div[@data-testid='dropdown-menu']/div";
	private String clientSdkDownloadBtn = "//*[@id='root']/div/div[2]/main/div[3]/div/div[2]/div/div[2]/div[12]/div[2]/span/button";
	private String closeBtn = "//*[@id='root']/div/div[2]/main/header[2]/div/div/button";

	public ApiDetailsGeneralPage(Page page) {
		super(page);
	}

	/**
	 * This method clicks on General tab of the Managed API from Managed API details
	 * page.
	 */
	public void clickGeneralTab() {
		MyLogger.info("Switch to General Tab");
		page.locator(generalDetailsPageTab).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	/**
	 * This method copies the API URL from General tab of the Managed API.
	 * 
	 * @return Copied API URL
	 */
	/*
	 * public String copyApiUrl() { MyLogger.info("Copying API URL...");
	 * page.locator(copyUrlBtn).click(); String apiUrl = null; try {
	 * TimeUnit.SECONDS.sleep(3); Clipboard clipboard =
	 * Toolkit.getDefaultToolkit().getSystemClipboard(); Transferable contents =
	 * clipboard.getContents(null); apiUrl = (String)
	 * contents.getTransferData(DataFlavor.stringFlavor); } catch
	 * (InterruptedException e) { MyLogger.error(e.getMessage(), e); } catch
	 * (IOException e) { MyLogger.error(e.getMessage(), e); } catch
	 * (UnsupportedFlavorException e) { MyLogger.error(e.getMessage(), e); }
	 * MyLogger.info("Copied API URL: " + apiUrl); return apiUrl; }
	 */
	
	/**
	 * Added below method to read the URL from Text Fields instead of copy button, some times during parallel execution clipboard copy is getting mixed.
	 * @return
	 */
	public String copyApiUrl() {
		MyLogger.info("Copying API URL...");
		String apiUrl = page.locator(CopyURLTextBox).innerText();
		MyLogger.info("Copied API URL: " + apiUrl);
		return apiUrl;
	}

	/**
	 * This method opens a new browser, navigates to the provided URL, fetch the
	 * response of the page and closes the browser.
	 * 
	 * @param context - Customized BrowserContext.
	 * @param url     - URL to which user wishes to navigate.
	 * 
	 * @return Response found from the navigated URL.
	 * @throws InterruptedException
	 */
	public String executeUrlInNewTabCollectResponseAndClose(BrowserContext context, String url)
			throws InterruptedException {
		Page pageForRunningApiUrl = context.newPage();
		pageForRunningApiUrl.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));

		pageForRunningApiUrl.navigate(url);
		pageForRunningApiUrl.waitForLoadState(LoadState.NETWORKIDLE);
		TimeUnit.SECONDS.sleep(1);
		pageForRunningApiUrl.locator(urlExecutionResult).highlight();
		String response = pageForRunningApiUrl.locator(urlExecutionResult).innerText();
		pageForRunningApiUrl.close();
		return response;
	}

	/**
	 * This method opens a new browser, navigates to the provided URL, fetch the
	 * value from the specified locator of the page and closes the browser.
	 * 
	 * @param context - Customized BrowserContext.
	 * @param url     - URL to which user wishes to navigate.
	 * @param locator - Locator of the element in HTML document.
	 * 
	 * @return The value fetched from the specified Locator.
	 */
	public String executeUrlInNewTabCollectResponseAndClose(BrowserContext context, String url, String locator) {
		Page pageForRunningApiUrl = null;
		String response = null;
		try {
			pageForRunningApiUrl = context.newPage();
			pageForRunningApiUrl.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));

			pageForRunningApiUrl.navigate(url);
			pageForRunningApiUrl.waitForLoadState(LoadState.NETWORKIDLE);
			TimeUnit.SECONDS.sleep(1);
			response = pageForRunningApiUrl.locator(locator).innerText();
		} catch (InterruptedException e) {
			MyLogger.info(e.getMessage(), e);
		} catch (com.microsoft.playwright.TimeoutError timeout) {
			MyLogger.info(timeout.getMessage(), timeout);
		} finally {
			pageForRunningApiUrl.close();
		}

		return response;
	}

	/**
	 * This method opens a new browser, navigates to the provided URL and closes the
	 * browser.
	 * 
	 * @param context - Customized BrowserContext.
	 * @param url     - URL to which user wishes to navigate.
	 * @return
	 * 
	 */
	public String executeUrlInNewTabAndClose(BrowserContext context, String url) {
		String Response = null;
		try {
			Page pageForRunningApiUrl = context.newPage();
			pageForRunningApiUrl.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));

			pageForRunningApiUrl.navigate(url);
			pageForRunningApiUrl.waitForLoadState(LoadState.NETWORKIDLE);
			TimeUnit.SECONDS.sleep(1);
			if (pageForRunningApiUrl.locator(urlExecutionResult).count() > 0) {
				Response = pageForRunningApiUrl.locator(urlExecutionResult).innerText();
				System.out.println(Response);

			}
			if (pageForRunningApiUrl.locator(rateLimiExceedFault).count() > 0) {
				Response = pageForRunningApiUrl.locator(rateLimiExceedFault).innerText();

				System.out.println(Response);

			}

			pageForRunningApiUrl.close();
		} catch (InterruptedException e) {
			MyLogger.info(e.getMessage(), e);
		}
		return Response;
	}

	/**
	 * This method opens a new browser, navigates to the provided URL, reload the
	 * same page multiple times, fetch the value from the specified locator of the
	 * page and closes the browser.
	 * 
	 * @param context - Customized BrowserContext.
	 * @param url     - URL to which user wishes to navigate.
	 * @param count   - Number of times to reload the page with the same URL.
	 * @param locator - Locator of the element in HTML document.
	 * 
	 * @return The value fetched from the specified Locator.
	 * @throws InterruptedException
	 */

	/*
	 * public String executeUrlInNewTabAndClose(BrowserContext context, String url,
	 * int count, String locator) throws InterruptedException {
	 * 
	 * for (int i = 1; i <= count+1; i++) { MyLogger.info("API Hit Number : " +
	 * (i));
	 * 
	 * s1=executeUrlInNewTabAndClose(context, url);
	 * 
	 * }
	 * 
	 * 
	 * return executeUrlInNewTabCollectResponseAndClose(context, url, locator);
	 * 
	 * 
	 * }
	 */
	
	/***
	 * Updated the below method to run the APis till we get proper message  
	 * 
	 * @param context
	 * @param url
	 * @param count
	 * @param locator
	 * @return
	 * @throws InterruptedException
	 */
	public String executeUrlInNewTabAndClose(BrowserContext context, String url, int count, String locator)
			throws InterruptedException {
		int i;
		String s1 = null;
		for (i = 1; i <= count + 1; i++) {
			MyLogger.info("API Hit Number : " + (i));
			Thread.sleep(2000);
			s1 = executeUrlInNewTabAndClose(context, url);
			if (s1.contains("Forbidden") && s1.contains("Rate Limit Reached"))
			{
				MyLogger.info("Got Forbidden Message on " + i + " times i.e., till Rate Limit exceeds.");
				break;

			}

		}

		return s1;

	}

	/**
	 * This method is used to rerun the same URL multiple time as specified in the
	 * count.
	 * 
	 * @param context - Customized BrowserContext.
	 * @param url     - URL to which user wishes to navigate.
	 * @param count   - Number of times to reload the page with the same URL.
	 * 
	 * @return The value fetched from the specified Locator.
	 * @throws InterruptedException
	 */
	public String executeUrlTillRateLimitExceed(BrowserContext context, String url, int count) throws InterruptedException {
		MyLogger.info("Running the API URL[" + url + "] for " + count + " times i.e., till Rate Limit exceeds.");
		String responseBody = executeUrlInNewTabAndClose(context, url, count, rateLimiExceedFault);

		if (Objects.isNull(responseBody)) {

			responseBody = executeUrlInNewTabAndClose(context, url, count, rateLimiExceedFault);
		}
		MyLogger.info("Response: " + responseBody);
		return responseBody;
	}

	/**
	 * Method used to click on the Client SDK drop-down button.
	 */
	private void clickClientSdkDropdownBtn() {
		page.locator(clientSdkDropdownBtn).click();
	}

	/**
	 * Method used to fetch the number of Client SDKs found in the drop-down.
	 * 
	 * @return Client SDKs count found in the drop-down.
	 */
	public int getClientSdkCount() {
		MyLogger.info("Getting Number of Client SDKs...");
		clickClientSdkDropdownBtn();
		int numberOfClientSdkItems = page.locator(clientSdkDropdownValues).count();
		clickClientSdkDropdownBtn();
		MyLogger.info("Total Number of Client SDKs: " + numberOfClientSdkItems);
		return numberOfClientSdkItems;
	}

	/**
	 * Method used to fetch the names of Client SDKs found in the drop-down.
	 * 
	 * @return List of Client SDK names found in the drop-down.
	 */
	public List<String> getClientSdkList() {
		MyLogger.info("Getting List of Client SDK Dropdown Items...");
		int numberOfClientSdkItems = getClientSdkCount();
		clickClientSdkDropdownBtn();
		List<String> clientSdkList = new ArrayList<>();
		for (int index = 1; index <= numberOfClientSdkItems; index++) {
			String newClientSdkDropdownValuesLocator = clientSdkDropdownValues + "[" + index + "]";
			String clientSdkValue = page.locator(newClientSdkDropdownValuesLocator).innerText();
			clientSdkList.add(clientSdkValue);
		}
		clickClientSdkDropdownBtn();
		MyLogger.info("Client SDK Dropdown Items are: " + clientSdkList);
		return clientSdkList;
	}

	/**
	 * Method used to fetch the name of the selected Client SDK from the drop-down.
	 * 
	 * @return The selected Client SDK name.
	 */
	public String getSelectedClientSdk() {
		MyLogger.info("Getting the Selected Client SDK...");
		int numberOfClientSdkItems = getClientSdkCount();
		clickClientSdkDropdownBtn();
		String clientSdkValue = null;
		for (int index = 1; index <= numberOfClientSdkItems; index++) {
			String newClientSdkDropdownValuesLocator = clientSdkDropdownValues + "[" + index + "]";
			String classAttributeValue = page.locator(newClientSdkDropdownValuesLocator).getAttribute("class");
			if (classAttributeValue.contains("d-dropdown__item--selected")) {
				clientSdkValue = page.locator(newClientSdkDropdownValuesLocator).innerText();
				break;
			}
		}
		clickClientSdkDropdownBtn();
		MyLogger.info("The Selected Client SDK is: " + clientSdkValue);
		return clientSdkValue;
	}

	/**
	 * Method used to download the selected Client SDK.
	 * 
	 * @return The toast message on completion of Client SDK download.
	 */
	public String downloadClientSdk() {
		String downloadClientSdkPopupMessage = null;
		try {
			MyLogger.info("Downloading Client SDK...");
			page.locator(clientSdkDownloadBtn).click();
			downloadClientSdkPopupMessage = getToastMessages();
			MyLogger.info(downloadClientSdkPopupMessage);
			closeToastMessage();
			waitForToastMessagesClose();
		} catch (Exception e) {
			MyLogger.error("Exception while downloading Client SDK: " + e.getMessage(), e);
		}
		return downloadClientSdkPopupMessage;
	}

	/**
	 * Method used to close the API Details page.
	 */
	public void closeApiDetailsPage() {
		MyLogger.info("Closing API Details Page...");
		page.locator(closeBtn).click();
	}
}
