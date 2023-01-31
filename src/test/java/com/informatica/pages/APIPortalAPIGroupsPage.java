/****************************************************************************************************
 * @author sragilla
 * @createdDate 28-06-2022
 * @description This class is created to track the API Portal web elements and it's associated methods
 ******************************************************************************************************/


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

public class APIPortalAPIGroupsPage extends BasePage {

	public APIPortalAPIGroupsPage(Page page) {
		super(page);

		MyLogger.info("Initiated APIRegistryPage object");

	}

	String ActiveStatusRecords = "//span[text()='Active']";
	String InActiveStatusRecords = "//span[text()='Inactive']";
	String ActiveServicenotAvailiable = "//span[text()='Active - Service not available']";
	String InActiveServicenotAvailiable = "//span[text()='Inactive - Service not available']";
	String APIRegisterPageHeaders = "//tr[@class='d-table__header__row']/th/span";

	String APIName = "//tr[@data-id='$prot_API_1.0.0']";
	String APIRegNameColumn = "(//th[@data-id='context'])";
	String APIRegNameGrpColumn = "//span[contains(text(),'APIs in Group')]/ancestor::div[@class='d-section__header']/following-sibling::div[@class='d-section__content']//span[text()='Name']";

	
	String FullPage = "text=Items Per Page";
	String PageSelection = "//div[@class='d-pagination__page-size d-version-2']/select";
	String settingsIcon = "//span[@class='button-group clickable']";
	String popupmessage = "//div[@class='d-messagebubble__text']/p/p";
	String Kebabmenu = "(//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action'])[2]";
	String KeababMenuOptions = "//ul[@class='d-menu d-version-2']/li";
	String SortMenu = "//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action' and @title='Sort']";
	String findSearchBox = "//input[@placeholder='Find']";
	String ActivateButton = "//ul[@class='d-menu d-version-2']/li[4]";
	
	String APIRegistry ="//span[text()='API Registry']";

	String APIGroupsbutton = "//span[text()='API Groups']";
	String Policiesbutton = "//span[text()='Policies']";
	String Analyticsbutton = "//span[text()='Analytics']";
	
	String Swaggerbutton = "//button/span[text()='Swagger']";
	String SwaggerUserName="//input[@data-testid='input' and @placeholder='Enter User Name']";
	String SwaggerPassword="//input[@data-testid='input' and @placeholder='Enter Password']";
	String SwaggerLogIn="//span[text()='Log In']";
	
	
	//Added by Sragilla
	String apiGroupFindInAPIGroupsPath="//div[@class='d-section__header']//span[contains(text(),'API Groups')]/ancestor::div[@class='d-section__header']//input[@placeholder='Find']";
	String apiGroupNameInAPIGroupsPath="//div[@class='d-section__header']//span[contains(text(),'API Groups')]/ancestor::div[@class='d-section__header']/following-sibling::div//span[text()='<GROUPNAME>']";
	String apiGroupNameContextInAPIGroupsPath="//div[@class='d-section__header']//span[contains(text(),'API Groups')]/ancestor::div[@class='d-section__header']/following-sibling::div//span[text()='<GROUPNAME>']/ancestor::td/following-sibling::td//span[text()='<CONTEXTNAME>']";
	String findAPIsSearch = "//div//span[contains(text(),'APIs in Group')]/ancestor::div[@class='d-section__header']//div//input[@placeholder='Find']";
	String apisInGroupButtonPath = "//button//span[normalize-space()='APIs in Group']";
	String apiInApiGroupPath = "//div//span[contains(text(),'APIs in Group')]//ancestor::div[@class='d-section__header']/following-sibling::div//tr[contains(@data-id,'<PROTOCOL>_')]//td//span[@class='IconAndText']//span[text()='<APINAME>']";
	String urlOFAPIInAPIGroupPath = "//div//span[contains(text(),'API URL')]/../following-sibling::div//div[contains(text(),'<CONTEXTNAME>/<GROUPNAME>')]";
	String copyUrlOFAPIInAPIGroupButtonPath = "//div//span[contains(text(),'API URL')]/../following-sibling::div//span[text()='Copy URL']";
	
	

    /************************************************************************************************
     *Refresh the page for 05 times, if there are any errors on landing the API portal page 
     ************************************************************************************************
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


	/*******************************************************************************************************
	 * SelectFullPage expands the page to get all records, if there are more API's are listed in the page.
	 * 
	 ******************************************************************************************************
	 */
	public void selectFullPage() {

		MyLogger.info("Selecting the Full Records");
		page.locator(FullPage).waitFor();
		page.locator(PageSelection).selectOption("1000");

	}

	
	public void clickOnAPIGroup(String apiGroupName) {		
		try {
			MyLogger.info("Filtering the APIGroup Name");
			page.fill(apiGroupFindInAPIGroupsPath, apiGroupName);
			MyLogger.info("clicking on apiGroup : "+apiGroupName);		
			String apiGroupNamePath = apiInApiGroupPath.replace("<GROUPNAME>", apiGroupName);
			page.locator(apiGroupNamePath).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		} catch (Exception e) {
			MyLogger.info("clickOnApiGroup Failed");
			throw e;
		}

	}
	
	public void clickOnAPIGroup(String apiGroupName,String apiGroupContext) {		
		try {
			MyLogger.info("Filtering the APIGroup Name");
			page.fill(apiGroupFindInAPIGroupsPath, apiGroupName);
			page.locator(APIRegNameColumn).click();	
		
			MyLogger.info("clicking on apiGroup with apiGroupNam : "+apiGroupName + " and ApiGroupContext" + apiGroupContext);		
			String apiGroupNamePath = apiGroupNameContextInAPIGroupsPath.replace("<GROUPNAME>", apiGroupName);
			apiGroupNamePath = apiGroupNamePath.replace("<CONTEXTNAME>", apiGroupContext);
			
			MyLogger.info("APIGroupWithContext webelement is: "+apiGroupNamePath);
			clickElement(apiGroupNamePath);
		//	clickElement(apiGroupNameInAPIGroupsPath.replace("<GROUPNAME>", apiGroupName));
			//page.locator(apiGroupNamePath).click();
			//page.locator(apiGroupNameInAPIGroupsPath.replace("<GROUPNAME>", apiGroupName)).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		} catch (Exception e) {
			MyLogger.info("clickOnApiGroup Failed");
			throw e;
		}

	}
	
	
	public void clickOnAPIInAPIGroup(String apiName,String apiProtocol) {		
		try {
			MyLogger.info("clicking on the APIs in Group");
			page.locator(apisInGroupButtonPath).click();
			MyLogger.info("Filtering the APIGroup Name");
			page.fill(findAPIsSearch, apiName);
			page.locator(APIRegNameGrpColumn).click();	
			MyLogger.info("clicking on api : "+apiName);		
			String apiNamePath = apiInApiGroupPath.replace("<APINAME>", apiName);
			apiNamePath = apiNamePath.replace("<PROTOCOL>", apiProtocol);
			MyLogger.info("API webelement is: "+apiNamePath);
			clickElement(apiNamePath);
			//clickElement(apiNamePath);
			} catch (Exception e) {
			MyLogger.info("clickOnApiGroup Failed");
			throw e;
		}

	}
	
	
	public String getURLFromAPIInAPIGroup(String apiName,String apiGroupContext) {		
		try {
			MyLogger.info("gettingUrl From APIInAPIGroup");
			String urlAPIInAPIGroup=null;
			if (apiGroupContext.length() > 0) {
				urlAPIInAPIGroup = urlOFAPIInAPIGroupPath.replace("<CONTEXTNAME>/<GROUPNAME>",
						apiGroupContext + "/" + apiName);
				//MyLogger.info("framing API Url for withContext");
			} else {
				urlAPIInAPIGroup = urlOFAPIInAPIGroupPath.replace("<CONTEXTNAME>/<GROUPNAME>", apiName);
				//MyLogger.info("framing API Url for withoutContext");
				
			}
			return page.locator(urlAPIInAPIGroup).innerText();
			} catch (Exception e) {
			MyLogger.info("clickOnApiGroup Failed");
			throw e;
		}

	}
	
	public String clikCopyURLOFAPIInAPIGroup() throws Exception {		
		try {
			MyLogger.info("clicking the copy URL Button From APIInAPIGroup");
			page.locator(copyUrlOFAPIInAPIGroupButtonPath).click();
			String urlFromCopyURLButton = GetClipBoardData();
			return urlFromCopyURLButton;
			} catch (Exception e) {
			MyLogger.info("clickOnApiGroup Failed");
			throw e;
		}

	}
	
   public void clickElement(String element) {
	   if(page.locator(element).isVisible()) {
		   page.locator(element).click();
	   }
   }
	
	public void clickOnApiGroups()
	{
		page.reload();
	}


}
