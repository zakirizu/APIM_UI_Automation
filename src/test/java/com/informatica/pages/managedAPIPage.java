package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import com.informatica.web.util.ConfigPropertiesLoader;
import com.informatica.web.util.Constants;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

public class managedAPIPage extends BasePage {

	public managedAPIPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	String APIName = "//input[@placeholder='Enter Name']";
	String APIVersion = "//input[@placeholder='Enter Version']";
	String APINameHover = "(//i[@class='aicon aicon__help'])[1]";
	String hover1Text = "//div[@class='d-version-2 d-bubble d-bubble--dark d-bubble--left d-tooltip--trigger-not-truncated']";
	String GeneralTab = "//span[text()='General']";
	String APIURLText = "//div[@data-bo='API Details General Managed URL']/div[1]";
	String copybutton = "//span[text()='Copy URL']";
	String ManagedPolicies="//button/span[text()='Policies']";
	
	String PrivacyTab = "//span[text()='Privacy']";
	String Privacy = "//div[@class='PrivacyTab']//div[1]//input[@type='checkbox']";
	String CreditCardReqDropDown = "//span[text()='Credit Card']/parent::td/following-sibling::td[1]//button";
	String CreditCardResDropDown = "//span[text()='Credit Card']/parent::td/following-sibling::td[2]//button";
	String EmailAddressReqDropDown = "//span[text()='Email Address']/parent::td/following-sibling::td[1]//button";
	String PrivacyWarning = "//div[@class='d-dropdown__item']//span[text()='Warning']";
	String PrivacyBlock = "//div[@class='d-dropdown__item']//span[text()='Block']";
	String EmailAddressResDropDown = "//span[text()='Email Address']/parent::td/following-sibling::td[2]//button";
	String IPAddressReqDropDown = "//span[text()='IP Address']/parent::td/following-sibling::td[1]//button";
	String IPAddressResDropDown = "//span[text()='IP Address']/parent::td/following-sibling::td[2]//button";
	String PhoneNumberReqDropDown = "//span[text()='Phone Number']/parent::td/following-sibling::td[1]//button";
	String PhoneNumberResDropDown = "//span[text()='Phone Number']/parent::td/following-sibling::td[2]//button";
	String SSNReqDropDown = "//span[text()='SSN']/parent::td/following-sibling::td[1]//button";
	String SSNResDropDown = "//span[text()='SSN']/parent::td/following-sibling::td[2]//button";
	String USAddressDropDown = "//span[text()='US Address']/parent::td/following-sibling::td[1]//button";
	String USAddressResDropDown = "//span[text()='US Address']/parent::td/following-sibling::td[2]//button";
	String Save = "//button[@title='Save']";
	String CopiedAPIURL_Updated;
	
	private APIRequestContext request;

	public String APINameToolTip() throws InterruptedException, UnsupportedFlavorException, IOException {

		String ActualNameHelpHover;
		page.locator(GeneralTab).waitFor();
		page.locator(APIName).getAttribute("value");
		// System.out.println(page.locator(APIName).getAttribute("value"));
		page.locator(APIName).isDisabled();
		Locator src = page.locator(APINameHover);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		ActualNameHelpHover = page.locator(hover1Text).innerText();
		// System.out.println(ActualNameHelpHover);
		return ActualNameHelpHover;

	}

	public Boolean CopiedURLLink() throws InterruptedException, UnsupportedFlavorException, IOException {

		String ActualAPIURL;
		String CopiedAPIURL;
		Boolean flag=false;;
		page.locator(GeneralTab).waitFor();
		ActualAPIURL = page.locator(APIURLText).innerText();
		// System.out.println("Actual API URL Text :" + ActualAPIURL);
		page.locator(copybutton).click();

		CopiedAPIURL = GetClipBoardData();
		
		String CustomApiSave_Success_Message1 = getToastMessages();
		System.out.println(CustomApiSave_Success_Message1);
		closeToastMessage();
		waitForToastMessagesClose();
		

		
		CopiedAPIURL_Updated = CopiedAPIURL;
		// System.out.println("Copied API URL Text :" + CopiedAPIURL);

		if (ActualAPIURL.equalsIgnoreCase(CopiedAPIURL)) {
			System.out.println("Actual and Copied URLS are same");
			flag = true;
		}

		return flag;

	}

	public int SendAPiRequest(Playwright playwright, String CopiedURl,String adminUsername,String adminPassword) {
		Map<String, String> headers = new HashMap<>();

		headers.put("Authorization", BasicAuthToken(adminUsername,adminPassword));

		request = playwright.request()
				.newContext(new APIRequest.NewContextOptions().setBaseURL(CopiedURl).setExtraHTTPHeaders(headers));
		APIResponse issues = request.get("?id=1");
		//System.out.println(issues.status());
		// System.out.println(issues.text());
		return issues.status();
	}
	
	public void ClickonManagedAPIPolicies() {

		MyLogger.info("Clicking on  Policies tab");
		page.locator(ManagedPolicies).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This method is used to fetch the copied URL from managed API page. 
	 * @author mbalaji
	 * @date_created  06-July-2022
	 * ************************************************************************************************************************
	 */
	public String getCopiedURL() throws InterruptedException, UnsupportedFlavorException, IOException {
		CopiedURLLink();
		return CopiedAPIURL_Updated;
		
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This method is used to send API request for anonymous, it take playwright,CopiedURl as input
	 * @author mbalaji
	 * @param playwright
	 * @param CopiedURl - The URL which is copied form the managed API
	 * @date_created  06-July-2022
	 * ************************************************************************************************************************
	 */
	public int SendAPiRequestAnon(Playwright playwright, String CopiedURl) throws InterruptedException {

		request = playwright.request()
				.newContext(new APIRequest.NewContextOptions().setBaseURL(CopiedURl).setTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax"))));
		Thread.sleep(5000);
		APIResponse issues = request.get(Constants.QUERYPARAM);
	//	Thread.sleep(10000);
		System.out.println(issues.status());
		System.out.println(issues.text());
		
		return issues.status();
	}

	/**
	 * ************************************************************************************************************************
	 * @description This method is used to enable Privacy setting for Managed API and set the Email setting to Warning/Block based on the PrivacyValue input provided.
	 * @author mbalaji
	 * @param PrivacyValue - The value which is selected from privacy drop down( accepted values are Warning/Block)
	 * @date_created  06-July-2022
	 * ************************************************************************************************************************
	 */
	public void CheckEnablePrivacyPolicy(String PrivacyValue) throws InterruptedException, UnsupportedFlavorException, IOException {

		page.locator(PrivacyTab).waitFor();
		page.locator(PrivacyTab).click();
		page.locator(Privacy).isDisabled();
		page.locator(Privacy).click();
		if(PrivacyValue=="Warning"){
			page.locator(EmailAddressReqDropDown).click();
			page.locator(PrivacyWarning).click();
			page.locator(EmailAddressResDropDown).click();
			page.locator(PrivacyWarning).click();
		}
		else if(PrivacyValue=="Block")
		{
			page.locator(EmailAddressReqDropDown).click();
			page.locator(PrivacyBlock).click();
			page.locator(EmailAddressResDropDown).click();
			page.locator(PrivacyBlock).click();
		}
		page.locator(Save).click();
		
		String CustomApiSave_Success_Message1 = getToastMessages();
		System.out.println(CustomApiSave_Success_Message1);
		closeToastMessage();
		waitForToastMessagesClose();
		
	//	String SaveMessage = page.locator(popupmessage).innerText();
		
		while(!CustomApiSave_Success_Message1.contains("successfully")) {
			Thread.sleep(3000);
			page.locator(Save).click();	
			CustomApiSave_Success_Message1 = getToastMessages();
			System.out.println(CustomApiSave_Success_Message1);
			closeToastMessage();
			waitForToastMessagesClose();
			page.locator(GeneralTab).click();
			
		}
	//	System.out.println(page.locator(popupmessage).innerText());
		page.locator(GeneralTab).click();
			
	}
	

}
