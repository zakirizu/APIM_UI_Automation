/****************************************************************************
@Description: This Page class is created to track the web elements of API Portal Registry page and it's associated methods.
@Author:@nvaddadi
@Date Created:29-06-2022
@JIRA TCs associated: APIM-1501, APIM-1502, APIM-1503
@Coverage: UI,Sanity,Regression
*********************************************************************************/


package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.informatica.web.util.MyLogger;
import com.informatica.web.util.PODFileManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIPortalRegistryPage extends BasePage {

	public APIPortalRegistryPage(Page page) {
		super(page);

		MyLogger.info("Initiated APIRegistryPage object");

	}

	String ActiveStatusRecords = "//span[text()='Active']";
	String InActiveStatusRecords = "//span[text()='Inactive']";
	String ActiveServicenotAvailiable = "//span[text()='Active - Service not available']";
	String InActiveServicenotAvailiable = "//span[text()='Inactive - Service not available']";
	String APIRegisterPageHeaders = "//tr[@class='d-table__header__row']/th/span";
	String APIRegistryTab = "//span[text()='API Registry']";

	String APIName = "//tr[@data-id='$prot_API_1.0.0']";
	String APIRegNameColumn = "//th[@data-id='name']";
	
	String FullPage = "text=Items Per Page";
	String PageSelection = "//div[@class='d-pagination__page-size d-version-2']/select";
	String settingsIcon = "//span[@class='button-group clickable']";
	String popupmessage = "//div[@class='d-messagebubble__text']/p/p";
	String Kebabmenu = "(//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action'])[2]";
	String KeababMenuOptions = "//ul[@class='d-menu d-version-2']/li";
	String SortMenu = "//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action' and @title='Sort']";
	String findSearchBox = "//input[@placeholder='Find']";
	String API_Registery_findSearchBox="//div[@class='d-section__header']//span[contains(text(),'All Services')]/ancestor::div[@class='d-section__header']//input[@placeholder='Find']";
	String ActivateButton = "//ul[@class='d-menu d-version-2']/li[4]";
	
	String APIRegistry ="//span[text()='API Registry']";

	String APIGroupsbutton = "//span[text()='API Groups']";
	String Policiesbutton = "//span[text()='Policies']";
	String Analyticsbutton = "//span[text()='Analytics']";
	
	String Swaggerbutton = "//button/span[text()='Swagger']";
	String SwaggerUserName="//input[@data-testid='input' and @placeholder='Enter User Name']";
	String SwaggerPassword="//input[@data-testid='input' and @placeholder='Enter Password']";
	String SwaggerLogIn="//span[text()='Log In']";
	
	/**
	 * @Description - This method Refresh the page for 05 times, if there are any errors on landing the API portal page
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public void VerifyErrorOnLoad() throws InterruptedException {

	
		page.locator(settingsIcon).waitFor();

		for (int i = 1; i <= 5; i++) {
			if (!page.locator(FullPage).isVisible()) {
				MyLogger.info("Reloading the page if Error is Present");
				System.out.println("Reloading the page :" + i);
				page.reload();

				page.waitForLoadState(LoadState.NETWORKIDLE);
			} else
				break;
		}

	}

	/**
	 * @Description - SelectFullPage expands the page to get all records, if there are more API's are listed in the page.
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public void selectFullPage() {

		MyLogger.info("Selecting the Full Records");
		page.locator(FullPage).waitFor();
		page.locator(PageSelection).selectOption("1000");

	}

	/**
	 * @Description - This method returns Active api status records count
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public int SearchActiveStatus() {

		int ActiveStatusCount = page.locator(ActiveStatusRecords).count();

		return ActiveStatusCount;

	}


	/**
	 * @Description - This method returns InActive api status records count
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public int SearchInActiveStatus() {

		MyLogger.info("Getting In-Active Status Records");

		int InActiveStatusCount = page.locator(InActiveStatusRecords).count();

		return InActiveStatusCount;

	}
	
	/**
	 * @Description - This method returns Active status records with Not available status count
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public int Search_ActiveServicenotAvailiable_Status() {

		MyLogger.info("Getting Active Service Not Availiable Status Records");

		int ActiveServicenotAvailiableCnt = page.locator(ActiveServicenotAvailiable).count();

		return ActiveServicenotAvailiableCnt;

	}

	/**
	 * @Description - This method returns InActive status records with Not available status count
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public int Search_InActiveServicenotAvailiable_Status() {

		MyLogger.info("Getting In-Active Service Not Availiable Status Records");

		int InActiveServicenotAvailiableCnt = page.locator(InActiveServicenotAvailiable).count();

		return InActiveServicenotAvailiableCnt;

	}
	
	/**
	 * @Description - This method returns headers present in the API Registry page in list
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 * @reurns returns the list of strings
	 */
	public List<String> getAPIRegisteryHeaders() {

		MyLogger.info("Getting API Registery Page Hear Columns Data");
		page.locator(settingsIcon).waitFor();
		page.locator(FullPage).waitFor();

		List<String> headersList = new ArrayList<>();

		Locator headers = page.locator(APIRegisterPageHeaders);

		for (int i = 0; i < headers.count(); i++) {

			String headerName = headers.nth(i).innerText();
			if (headerName != null && !headerName.equals("")) {
				headersList.add(headerName);
			}

		}

		MyLogger.info("Actual report headers : " + headersList);
		return headersList;
	}
	
	
	/**
	 * @Description - This method click on KebabMenu items
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
    public void ClickonKebabMenu() throws InterruptedException, UnsupportedFlavorException, IOException {

		MyLogger.info("Clicking on  KebabMenu");
		page.locator(findSearchBox).fill("API_Automation_Basic");
		String APINameUpdatedName = APIName.replace("$protocal", "REST");
		Locator src = page.locator(APINameUpdatedName);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();

		List<String> KebabMenuEnabledOptions = new ArrayList<>();
		List<String> KebabMenuDisabledOptions = new ArrayList<>();
		Locator KebabOptions = page.locator(KeababMenuOptions);

		for (int i = 0; i < KebabOptions.count(); i++) {

			if (KebabOptions.nth(i).isEnabled()) {
				KebabMenuEnabledOptions.add(KebabOptions.nth(i).innerText());
			} else {
				KebabMenuDisabledOptions.add(KebabOptions.nth(i).innerText());
			}

		}

		System.out.println("Printg Menu Enabled Options using For each");
		KebabMenuEnabledOptions.forEach((n) -> System.out.println(n));

		System.out.println("Printg Menu Disbaled Options using For each");
		KebabMenuDisabledOptions.forEach((n) -> System.out.println(n));
		
		page.reload();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}
    
public void FindAPi(String APIName) {
		
		page.locator(findSearchBox).fill(APIName);

	}
public void API_FindAPi(String APIName) {
	
	page.locator(API_Registery_findSearchBox).fill(APIName);

}

    
	/**
	 * @Description - This method clicks on required API from the list of API's in API Registry portal
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public void clickOnApiInGrid(String apiName, String protocal) {		
		MyLogger.info("Clicking on API - "+apiName);		
		String APIUpdatedName = APIName.replace("$prot", protocal);
		APIUpdatedName = APIUpdatedName.replace("API", apiName);
		//MyLogger.info("API webelement is: "+APIUpdatedName);
		page.locator(APIRegNameColumn).click();	
		page.locator(APIUpdatedName).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	/**
	 * @Description - This method clicks on swagger tab present in the API
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public void clickOnSwaggerTab() {

		MyLogger.info("Clicking on Swagger Tab");
	
		page.locator(Swaggerbutton).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		
		try {
			page.fill(SwaggerUserName, PODFileManager.getProperty("UserName"));
			page.fill(SwaggerPassword, PODFileManager.getProperty("Password"));
			page.locator(SwaggerLogIn).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * @Description - This method clicks on API Registry tab
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	public void clickOnApiRegistry()
	{
		page.reload();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.locator(APIRegistryTab).click();
		
	}
	
}
