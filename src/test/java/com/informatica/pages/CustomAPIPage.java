package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.informatica.web.util.Constants;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class CustomAPIPage extends BasePage {

	public CustomAPIPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	String CustAPIName = "//input[@name='name']";
	String CustAPIVersion = "//input[@name='version']";
	String CustAPIURL = "//input[@name='apiurl']";
	String CustAPIREST = "(//input[@type='radio'])[1]";
	String CustAPISOAP = "(//input[@type='radio'])[2]";
	String CustAPIDescription = "//textarea[@data-testid='textarea']";
	String CustAPIAvailiablePortal = "//div[@data-bo='Available on API Portal']/div";
	String CustAPISave = "//button[@title='Save']";
	String CustAPIClose = "//button[@aria-label='Close page']";
	String CustAPITest = "//button[@title='Test']";
	String CustAPIDeactivate = "//button[@title='Deactivate']";
	String Ratelimitcheckbox = "//label[text()='Enable API-specific rate limit policy']/parent::*/parent::*/input";
	String SelectTier = "(//div[@data-testid='dropdown-bar'])[2]";
	String SelectTierOption = "//div[@class='d-dropdown__item d-dropdown__item--active']/span[text()='APIM_Automation_Tier']";

	String dropdownListXpath = "//div[contains(@class,'d-dropdown__item')]/span";
	String dropdownButton = "(//button[@data-testid='dropdown-button'])[2]";
	String ipAddressBoxes = "(//span[@class='IpInputPart'])[$i]/div/input";
	String AddipButton = "//span[text()='Add']";
	String AddtoGroup1 = "//span[text()='Add to Group']";
	String AddtoGroup2 = "(//span[text()='Add to Group'])[2]";
	


	public String CreateCustomAPI() throws InterruptedException, UnsupportedFlavorException, IOException {
		page.locator(CustAPIName).fill(Constants.CUST_API_NAME);
		TestCustomAPI();
		page.locator(CustAPIDescription).fill(Constants.CUST_API_DESC);
		page.locator(CustAPISave).click();
		String Test_Success_Message = getToastMessages();
		 closeToastMessage();

		waitForToastMessagesClose();
		page.waitForLoadState(LoadState.NETWORKIDLE);

		page.locator(CustAPIDeactivate).waitFor();
		page.locator(CustAPIClose).click();

		return Test_Success_Message;
	}

	public String TestCustomAPI() throws InterruptedException, UnsupportedFlavorException, IOException {
		page.locator(CustAPIURL).fill(Constants.CUST_API_URL);
		page.locator(CustAPITest).click();
		String AuthHeader_popup_Message = getToastMessages();
		System.out.println(AuthHeader_popup_Message);
		closeToastMessage();
		waitForToastMessagesClose();
		return AuthHeader_popup_Message;
	}

	public void SelectRateLimit() throws InterruptedException, UnsupportedFlavorException, IOException {
		page.locator(Ratelimitcheckbox).check();

	}

	public void SelectRateLimitTier() throws InterruptedException, UnsupportedFlavorException, IOException {
		selectAPIRegistryDropdownOption("APIM_Automation_Tier");

	}

	public String SaveCustomAPiwithRateLimit() throws InterruptedException, UnsupportedFlavorException, IOException {
		SelectRateLimit();
		// waitForMouseActions();
		SelectRateLimitTier();
		page.locator(CustAPISave).click();
		String CustomApiSave_Success_Message = getToastMessages();
		System.out.println(CustomApiSave_Success_Message);
		closeToastMessage();
		waitForToastMessagesClose();
		page.locator(CustAPIClose).click();
		return CustomApiSave_Success_Message;
	}

	public String SaveCustomAPiwithIPFiltering(String FromRange, String ToRange)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		String[] From_str = FromRange.split("[.]");

		int temp = 5;

		for (int j = 1; j <= From_str.length; j++) {

			page.locator(ipAddressBoxes.replace("$i", Integer.toString(j))).fill(From_str[j - 1]);
		}

		String[] To_str = ToRange.split("[.]");
		for (int j = 1; j <= To_str.length; j++) {
			page.locator(ipAddressBoxes.replace("$i", Integer.toString(temp))).fill(To_str[j - 1]);
			temp++;
		}

		page.locator(AddipButton).click();
		page.locator(CustAPISave).click();
		String CustomApiSave_Success_Message = getToastMessages();
		System.out.println(CustomApiSave_Success_Message);
		closeToastMessage();
		waitForToastMessagesClose();
		page.locator(CustAPIClose).click();
		return CustomApiSave_Success_Message;
	}

	public void selectAPIRegistryDropdownOption(String dropdownOption) {
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
	}

	public String SaveCustomAPIwithGroup() throws InterruptedException, UnsupportedFlavorException, IOException {
		page.locator(AddtoGroup1).click();
		selectAPIRegistryDropdownOption("APIM_Automation_Grp");
		page.locator(AddtoGroup2).click();
		String CustomApiSaveGrp_Success_Message = getToastMessages();
		System.out.println(CustomApiSaveGrp_Success_Message);
		closeToastMessage();
		page.locator(CustAPIClose).click();
		return CustomApiSaveGrp_Success_Message;

	}

}
