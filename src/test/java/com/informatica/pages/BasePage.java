package com.informatica.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Base64;

import com.informatica.web.util.ConfigPropertiesLoader;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class BasePage {
//	static Logger logger = Logger.getLogger(Util.class);

	String userName = "//input[@type='text']";
	String passWord = "#password > .infaField";
	String loginBtn = "//button[@class='infaButton infaButton-1']";
	int MouseSleepWait = 2000;
	int ToastmsgpWait = 1000;
	String popupmessage = "//div[@class='d-messagebubble__text']/p/p";
	String closepopup = "//i[@class='d-messagebubble__close']";

	String copySwaggerURLLink = "//span/a[text()='Copy Swagger URL']";
	String swaggerURLBody = "//pre";
	String copyWSDLURLLink = "//span/a[text()='Copy WSDL URL']";
	String wsdlBodyId = "webkit-xml-viewer-source-xml";
	String Grid_Count= "(//tbody[@class='d-table__body']/tr)[1]";
	String spinner = "//div[@class='d-loader__spinner']";

	String dropdownListXpath = "//div[contains(@class,'d-dropdown__item')]/span";
	String dropdownButton = "//button[@data-testid='dropdown-button']";

	protected Page page;

	public BasePage(Page page) {

		this.page = page;
		System.out.println(page);

	}

	public void navigateTo(String url) {
		try {

			System.out.println("Page URL is " + url);
			page.navigate(url);
			// page.waitForLoadState(LoadState.NETWORKIDLE);

		} catch (Exception e) {
			MyLogger.error("Exception while loading :: " + e.getStackTrace());
		}
	}

	public void loginIntoIICSApp(String username, String PassWord) {

		MyLogger.info("entering username and password");
		page.fill(userName, username);
		page.fill(passWord, PassWord);
		MyLogger.info("Clicking on Login Button");
		page.waitForNavigation(() -> {
			page.press("//button[@class='infaButton infaButton-1']", "Enter");
		});
		// page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public String BasicAuthToken(String username, String Password) {
		String temp = username + ':' + Password;
		String Encoded = Base64.getEncoder().encodeToString(temp.toString().getBytes());
		return "Basic " + Encoded;
	}

	public String GetClipBoardData() throws UnsupportedFlavorException, IOException {
		Toolkit t = Toolkit.getDefaultToolkit();
		Clipboard cp = t.getSystemClipboard();
		String CopiedClipboardData = (String) cp.getData(DataFlavor.stringFlavor);
		return CopiedClipboardData;

	}

	public void waitForMouseActions() throws InterruptedException, UnsupportedFlavorException, IOException {

		Thread.sleep(MouseSleepWait);

	}

	public void waitForToastMessagesClose() throws InterruptedException, UnsupportedFlavorException, IOException {

		Thread.sleep(ToastmsgpWait);

	}

	public String getToastMessages() throws InterruptedException, UnsupportedFlavorException, IOException {
		String Msg;
		page.locator(popupmessage).waitFor();
		
		

		if (page.locator(popupmessage).isVisible())
		{
			Msg=page.locator(popupmessage).innerText();
		}
		else
		{
			Msg = "Toast Message popup does not Turn on";
		}
		
		
		return Msg;
	}
	
	public Boolean CheckforToastMessageVisability()
	{
		
		Boolean flag = false;
		page.locator(popupmessage).waitFor();
		if (page.locator(popupmessage).count()>0)
			flag= true;
		return flag;
		
	}

	public void closeToastMessage() {
		if (page.locator(closepopup).isVisible()) {
			page.locator(closepopup).click();
		}
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to click the "Copy Swagger URL" link in
	 *              Managed API Page of API Manager and API Portal
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @return copiedSwaggerURL: Swagger URL that is copied to clipboard
	 *         ************************************************************************************************************************
	 */

	public String copySwaggerURL() throws UnsupportedFlavorException, IOException {

		MyLogger.info("Copying Swagger URL");
		String copiedSwaggerURL;
		page.locator(copySwaggerURLLink).click();
		copiedSwaggerURL = GetClipBoardData();
		MyLogger.info("Copied Swagger URL Text :" + copiedSwaggerURL);
		return copiedSwaggerURL;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to run the Swagger URL of an anonymous API
	 *              in a new tab of the browser
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @param url: Swagger URL copied to clipboard
	 * @return swaggerBody: Swagger definition displayed
	 *         ************************************************************************************************************************
	 */

	public String runAnonSwaggerURL(String url) {
		String swaggerBody = "";
		try {
			MyLogger.info("Running Swagger URL of Anonymous API");
			Page newTab = page.context().newPage();
			newTab.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));

			newTab.navigate(url);
			swaggerBody = newTab.locator(swaggerURLBody).innerText();
			newTab.close();
		} catch (Exception e) {
			MyLogger.error("Exception while running Swagger URL of Anonymous API" + e.getStackTrace());
			throw e;
		}
		return swaggerBody;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to run the Swagger URL of a basic API in a
	 *              new tab of the browser
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @param url:      Swagger URL copied to clipboard
	 * @param username: username to run the basic API
	 * @param password: password to run the basic API
	 * @return swaggerBody: Swagger definition displayed
	 *         ************************************************************************************************************************
	 */

	public String runBasicSwaggerURL(String url, String username, String password) {
		String swaggerBody = "";
		try {
			MyLogger.info("Running Swagger URL of Basic API");
			Page newTab = page.context().newPage();
			newTab.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));

			newTab.navigate("https://" + username + ":" + password + "@" + url.substring(url.indexOf(':') + 3));
			swaggerBody = newTab.locator(swaggerURLBody).innerText();
			newTab.close();
		} catch (Exception e) {
			MyLogger.error("Exception while running Swagger URL of Basic API" + e.getStackTrace());
			throw e;
		}
		return swaggerBody;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to click the "Copy WSDL URL" link in Managed
	 *              API Page of API Manager and API Portal
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @return copiedWSDLURL: WSDL URL that is copied to clipboard
	 *         ************************************************************************************************************************
	 */

	public String copyWSDLURL() throws UnsupportedFlavorException, IOException {

		MyLogger.info("Copying WSDL URL");
		String copiedWSDLURL;
		page.locator(copyWSDLURLLink).click();
		copiedWSDLURL = GetClipBoardData();
		MyLogger.info("Copied WSDL URL Text :" + copiedWSDLURL);
		return copiedWSDLURL;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to run the WSDL URL of an anonymous API in a
	 *              new tab of the browser
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @param url: WSDL URL that is copied to clipboard
	 * @return WSDLBody: WSDL definition displayed
	 *         ************************************************************************************************************************
	 */

	public String runAnonWSDLURL(String url) {
		String WSDLBody = "";
		try {
			MyLogger.info("Running WSDL URL of Anonymous API");
			Page newTab = page.context().newPage();
			newTab.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));

			newTab.navigate(url);
			
			WSDLBody = newTab.locator("id=" + wsdlBodyId).innerHTML();
			newTab.close();
		} catch (Exception e) {
			MyLogger.error("Exception while running WSDL URL of Anonymous API" + e.getStackTrace());
			throw e;
		}
		return WSDLBody;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to run the WSDL URL of an anonymous API in a
	 *              new tab of the browser
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param element: webelement for clicking *
	 *                 ************************************************************************************************************************
	 */
	public void clickElement(String element) {
		if (page.locator(element).isVisible()) {
			page.locator(element).click();
		} else {
			MyLogger.error("the Element is not Visible" + element);
		}
	}

	public void WaitforElement(String element) {
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		endTime = startTime + 30000; // 30 seconds time

		System.out.println(startTime);
		System.out.println(endTime);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		if (endTime < System.currentTimeMillis() || page.locator(element).count()>0) {

			System.out.println("Element Visible");

		}
		else
		{
			System.out.println("Element not Visible");
		}

	}
	
	public void selectAPIRegistryDropdownOption(String dropdownOption) {

		try {
			MyLogger.info("Selecting Dropdown Option" + dropdownOption + "from API registry");
			if (page.locator(dropdownListXpath).count() == 0) {
				page.locator(dropdownButton).click();
			}
			Locator options = page.locator(dropdownListXpath);
			for (int i = 0; i < options.count(); i++) {
				if (page.locator(dropdownListXpath).nth(i).innerText().equalsIgnoreCase(dropdownOption)) {
					page.locator(dropdownListXpath).nth(i).click();
					break;
				}
			}
		} catch (Exception e) {
			MyLogger.error("Failed to select " + dropdownOption + "from API registry" + e.getStackTrace());
			throw e;
		}
	}
	
	
public void CheckPageisLoading() throws InterruptedException {
		
		MyLogger.info("Checking Page is Loading :: START");
		
		while(page.locator(spinner).isVisible())
			
		{
			System.out.println("Page is loading");
			Thread.sleep(5000);
		}
		
	
	}
}
