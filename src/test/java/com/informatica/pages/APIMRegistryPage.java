package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIMRegistryPage extends BasePage {

	public APIMRegistryPage(Page page) {
		super(page);

		MyLogger.info("Initiated APIMRegistryPage object");

	}

	String ActiveStatusRecords = "//span[text()='Active']";
	String InActiveStatusRecords = "//span[text()='Inactive']";
	String ActiveServicenotAvailiable = "//span[text()='Active - Service not available']";
	String InActiveServicenotAvailiable = "//span[text()='Inactive - Service not available']";
	String APIRegisterPageHeaders = "//tr[@class='d-table__header__row']/th/span";
	String APIRegNameColumn = "//div[@data-bo='API REGISTRY RESTRICTION']//span[text()='Name']";

	String APIName = "//tr[@data-id='$protocol_$APIName_1.0.0']";

	String Oauth2_APIName = "//tr[@data-id='$protocal_$ProcessName_1.0.0']";

	String FullPage = "text=Items Per Page";
	String PageSelection = "//div[@class='d-pagination__page-size d-version-2']/select";
	String settingsIcon = "//span[@class='button-group clickable']";
	String popupmessage = "//div[@class='d-messagebubble__text']/p/p";
	String Kebabmenu = "(//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action'])[2]";
	String KeababMenuOptions = "//ul[@class='d-menu d-version-2']/li";
	String SortMenu = "//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action' and @title='Sort']";
//	String findSearchBox = "//input[@placeholder='Find']";
	String ActivateButton = "//ul[@class='d-menu d-version-2']/li[4]";
	String findSearchBox="//div[@data-bo='API REGISTRY RESTRICTION']//input[@placeholder='Find']";


	 String apiRegistryButton = "//span[text()='API Registry']";
	String APIGroupsbutton = "//span[text()='API Groups']";
	String Policiesbutton = "//span[text()='Policies']";
	String Analyticsbutton = "//span[text()='Analytics']";
	String AuthorizationTab = "//span[text()='Authorization']";
	String CreateCustomAPI = "//span[text()='Create Custom API']";

	private String apiRegistryDropdownButton = "//*[@id='root']/div/div[2]/main/header/h1/ol/li/span/div[2]/div/button";
	private String apiRegistryDropdownValueAllServices = "//*[@id='downshift-0-item-0']";
	private String apiRegistryDropdownValueAvailableServices = "//*[@id='downshift-0-item-1']";
	private String apiRegistryDropdownValueManagedApis = "//*[@id='downshift-0-item-2']";
	private String apiResigtryHeaderTitle = "//*[@id='root']/div/div[2]/main/header/h1/ol/li/span/div[1]/div";

	String dropdownListXpath = "//div[contains(@class,'d-dropdown__item')]/span";
	String dropdownButton = "//button[@data-testid='dropdown-button']";
	String statusColumn = "//tr/td[7]";
	String closeAPIBanner = "//button[@aria-label='Close $ManagedAPIName/1.0.0']";
	String APIRegistrybutton = "//span[text()='API Registry']";

	String PrivacyAPIName = "//tr[@data-id='$protocal_$Apiname']";
	String ManagedAPIName = "//tr[@data-id='$protocal_$Apiname_$version']";
	String CreateManagedAPIButton = "//ul[@class='d-menu d-version-2']/li[1]";
	String CreateNewVersionButton = "//ul[@class='d-menu d-version-2']/li[3]";
	String CreateButton = "//button[.//span[text()='Create']]";
	String EditButton = "//ul[@class='d-menu d-version-2']/li[1]";
	String DeleteButton = "//ul[@class='d-menu d-version-2']/li[contains(text(),'Delete')]";

	//String DeleteButton = "//ul[@class='d-menu d-version-2']/li[text()='Delete Managed API']";
	String Delete = "//button[@class='d-version-2 d-button d-button--primary']//span[text()='Delete']";
	String Version = "//div[@class=' d-input InputWrapper']//input[@placeholder='Enter Version']";
	String SelectService = "//div[@class='d-version-2 d-section CustomTable  ']//tbody//tr[1]";
	String CreateNewButton = "//button[@class='d-version-2 d-button d-button--primary']//span[text()='Create']";
	String FindInput = "//div[@class='d-dialog__content']//div[@class='d-input d-version-2 d-input--with-icon d-toolbar__find']//input[@placeholder='Find']";
	String CreateVersionFind = "//div[text()='Select an available service for your new version']/following::input[@placeholder='Find']";

			
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

	public void VerifyPageCompleteLoad() throws InterruptedException {

		page.locator(settingsIcon).waitFor();

		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public void selectFullPage() {

		MyLogger.info("Selecting the Full Records");
		page.locator(FullPage).waitFor();
		page.locator(PageSelection).selectOption("1000");

	}

	public int SearchActiveStatus() {

		int ActiveStatusCount = page.locator(ActiveStatusRecords).count();

		return ActiveStatusCount;

	}

	public int SearchInActiveStatus() {

		MyLogger.info("Getting In-Active Status Records");

		int InActiveStatusCount = page.locator(InActiveStatusRecords).count();

		return InActiveStatusCount;

	}

	public int Search_ActiveServicenotAvailiable_Status() {

		MyLogger.info("Getting Active Service Not Availiable Status Records");

		int ActiveServicenotAvailiableCnt = page.locator(ActiveServicenotAvailiable).count();

		return ActiveServicenotAvailiableCnt;

	}

	public int Search_InActiveServicenotAvailiable_Status() {

		MyLogger.info("Getting In-Active Service Not Availiable Status Records");

		int InActiveServicenotAvailiableCnt = page.locator(InActiveServicenotAvailiable).count();

		return InActiveServicenotAvailiableCnt;

	}

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

	public void ClickonKebabMenu() throws InterruptedException, UnsupportedFlavorException, IOException {

		MyLogger.info("Clicking on  KebabMenu");
		page.locator(findSearchBox).fill("APIM_Automation_Basic");
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

	public void ClickonAPIInGrid(String APINAME) {

		MyLogger.info("Clicking on  API");
		String APINameUpdatedName = APIName.replace("$protocal", "REST").replace("$APIName", APINAME);
		page.locator(APINameUpdatedName).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	/**
	 * Method used to click on the 'API Registry' button from the left panel of the
	 * page.
	 */
	public void clickonAPIRegistry() {
		MyLogger.info("Clicking on  API Registry");
		page.locator(apiRegistryButton).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	public void ClickonAPIGroups() {

		MyLogger.info("Clicking on  API Group");
		page.locator(APIGroupsbutton).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public void ClickonPolicies() {

		MyLogger.info("Clicking on  Policies tab");
		page.locator(Policiesbutton).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public void ClickonAuthorization() {

		MyLogger.info("Clicking on  Authorization tab");
		page.locator(AuthorizationTab).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public void ClickonAnalytcis() throws InterruptedException {

		MyLogger.info("Clicking on  Analytics Tab");
		page.locator(Analyticsbutton).click();
		CheckPageisLoading();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	

	
	public void ClickonCreateCustomAPI() {

		MyLogger.info("Clicking on  Analytics Tab");
		page.locator(CreateCustomAPI).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public void ClickonActiavteDeactivate() throws InterruptedException, UnsupportedFlavorException, IOException {

		MyLogger.info("Clicking on Activate / Deactivate Button");
		page.locator(findSearchBox).fill("APIM_Automation_Basic");
		String APINameUpdated = APIName.replace("$protocal", "REST");
		Locator src = page.locator(APINameUpdated);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		page.locator(ActivateButton).click();
		System.out.println(page.locator(popupmessage).innerText());

	}

	public void FindAPi(String APIName) {
		
		page.locator(findSearchBox).fill(APIName);

	}

	/**
	 * Method used to fetch the title of the API Registry page.
	 * 
	 * @return Title of the API Registry page.
	 */
	public String getHeaderTitle() {
		return page.locator(apiResigtryHeaderTitle).innerText();
	}

	/**
	 * Method used to select 'All Services' from API Registry drop-down.
	 */
	public void filterAllServices() {
		MyLogger.info("Filtering All Services...");
		page.locator(apiRegistryDropdownButton).click();
		page.locator(apiRegistryDropdownValueAllServices).click();
	}

	/**
	 * Method used to select 'Available Services' from API Registry drop-down.
	 */
	public void filterAvailableServices() {
		MyLogger.info("Filtering Available Services...");
		page.locator(apiRegistryDropdownButton).click();
		page.locator(apiRegistryDropdownValueAvailableServices).click();
	}

	/**
	 * Method used to select 'Managed APIs' from API Registry drop-down.
	 */
	public void filterManagedApis() {
		MyLogger.info("Filtering Managed APIs...");
		page.locator(apiRegistryDropdownButton).click();
		page.locator(apiRegistryDropdownValueManagedApis).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	/**
	 * Method used to search from the Services/Managed APIs.
	 * 
	 * @param serviceOrManagedApi - Service or Managed API name to search.
	 */
	public void find(String serviceOrManagedApi) {
		MyLogger.info("Finding '" + serviceOrManagedApi + "'");
		page.locator(findSearchBox).fill(serviceOrManagedApi);
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to return to the API Registry page in API
	 *              Manager
	 * @author abehera
	 * @date_created 29-Jun-2022
	 *               ************************************************************************************************************************
	 */

	public void clickOnAPIRegistry() {

		try {
			MyLogger.info("Clicking on  API Registry");
			page.locator(APIRegistrybutton).click();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		} catch (Exception e) {
			MyLogger.error("Failed to click on API Registry" + e.getStackTrace());
			throw e;
		}
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to close an opened API from the Hamburger
	 *              menu
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @param apiName: name of the managed API
	 *                 ************************************************************************************************************************
	 */

	public void closeOpenedAPI(String apiName) {

		try {
			MyLogger.info("Closing API " + apiName);
			page.locator(closeAPIBanner.replace("$ManagedAPIName", apiName)).click();
		} catch (Exception e) {
			MyLogger.error("Failed to close Managed API " + apiName + e.getStackTrace());
			throw e;
		}
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to get the list of dropdown options in the
	 *              API Registry
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @return dropdownList: List of types of Services in dropdown menu options
	 *         ************************************************************************************************************************
	 */

	public List<String> getServicesDropdownList() {

		List<String> dropdownList = new ArrayList<>();
		try {
			MyLogger.info("Expanding Services Dropdown");
			page.locator(dropdownButton).click();

			MyLogger.info("Getting list of dropdown options");
			Locator options = page.locator(dropdownListXpath);

			for (int i = 0; i < options.count(); i++) {
				String optionName = options.nth(i).innerText();
				dropdownList.add(optionName);
			}
			MyLogger.info("Actual dropdown options : " + dropdownList);
		} catch (Exception e) {
			MyLogger.error("Failed to fetch Services dropdown list" + e.getStackTrace());
			throw e;
		}
		return dropdownList;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to select a particular dropdown option from
	 *              the All Services dropdown in API registry
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @param dropdownOption: dropdown option name (All Services/Available
	 *                        Services/Managed APIs)
	 *                        ************************************************************************************************************************
	 */

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

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to check the Status column of all Services
	 *              in API Registry of API Manager
	 * @author abehera
	 * @date_created 29-Jun-2022
	 * @return true: if no Service has a blank status, false: if any one Service has
	 *         a blank status
	 *         ************************************************************************************************************************
	 */

	public boolean checkStatusNotBlank() {

		try {
			MyLogger.info("Checking status visible for Managed APIs");
			Locator statuses = page.locator(statusColumn);
			for (int i = 0; i < statuses.count(); i++) {
				if (statuses.nth(i).innerText().equals("")) {
					MyLogger.error("Encountered blank status for Managed APIs");
					return false;
				}
			}
		} catch (Exception e) {
			MyLogger.error("Unable to check status of Managed APIs" + e.getStackTrace());
			throw e;
		}
		return true;
	}

	/*************************************************************************
	 * Method to click on required API from the list
	 * 
	 * @author nvaddadi
	 * @param apiName  - Name of the Managed API
	 * @param protocol - Name of the Protocol on which the Managed API runs on.
	 *                 Supported Protocols are: REST, SOAP.
	 * @date 29-06-2022
	 **************************************************************************/
	/*
	 * public void clickOnApiInGrid(String apiName, String protocol) {
	 * MyLogger.info("Clicking on API - " + apiName); String apiUpdatedName =
	 * APIName.replace("$protocol", protocol); apiUpdatedName =
	 * apiUpdatedName.replace("$APIName", apiName);
	 * System.out.println(apiUpdatedName); // page.locator(apiUpdatedName).click();
	 * try { Thread.sleep(5000);
	 * 
	 * page.locator(apiUpdatedName).click(); } catch (Exception e) {
	 * e.printStackTrace(); } page.waitForLoadState(LoadState.NETWORKIDLE); }
	 */
	
	
	
	public void clickOnApiInGrid(String apiName, String protocol) {
		MyLogger.info("Clicking on API - " + apiName);
		String apiUpdatedName = APIName.replace("$protocol", protocol);
		apiUpdatedName = apiUpdatedName.replace("$APIName", apiName);
		System.out.println(apiUpdatedName);
		page.locator(APIRegNameColumn).click();	
		page.locator(apiUpdatedName).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}
	
	public Boolean ValdiateAPIPresentInAPIRegistery(String apiName, String protocol) {
		page.locator(APIRegNameColumn).click();
		MyLogger.info("Clicking on API - " + apiName);
		String apiUpdatedName = APIName.replace("$protocol", protocol);
		apiUpdatedName = apiUpdatedName.replace("$APIName", apiName);
		System.out.println(apiUpdatedName);
		return page.locator(apiUpdatedName).isVisible();
			
	}
	
	
	
	public void clickOnApiInGridv2(String apiName, String protocol) {
		MyLogger.info("Clicking on API - " + apiName);


		String apiUpdatedName = APIName.replace("$protocol", protocol);
		apiUpdatedName = apiUpdatedName.replace("$APIName", apiName);
		System.out.println(apiUpdatedName);
	//	page.locator(apiUpdatedName).click();
		try {
		//	Thread.sleep(1000);
		
		//	page.addInitScript("document.querySelector('[data-id="+"\""+"REST_asdf_1.0.0"+"\""+"]').click()");
		//	page.evaluate("document.querySelector([data-id="REST_asdf_1.0.0"]).click()");
		
		page.locator(APIRegNameColumn).click();	
		//Thread.sleep(1000);
		//page.locator(apiUpdatedName).highlight();
		page.locator(apiUpdatedName).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to reload API registry page
	 * @author mbalaji
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public void ReloadPage() throws InterruptedException {

		MyLogger.info("Reloading the page");
		page.reload();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		WaiforPageGridLoad();


	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to create a ManagedAPI from the API registry
	 *              page, it take CAI processName as input
	 * @author mbalaji
	 * @param ProcessName - Name of the CAI Process for which we are creating
	 *                    managed API
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public void CreateManagedAPI(String ProcessName)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		MyLogger.info("Search an API");
		page.locator(findSearchBox).fill(ProcessName);
		String APINameUpdated = PrivacyAPIName.replace("$protocal", "REST");
		APINameUpdated = APINameUpdated.replace("$Apiname", ProcessName);
		page.locator(APIRegNameColumn).click();	
		Locator src = page.locator(APINameUpdated);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		page.locator(CreateManagedAPIButton).click();
		page.locator(CreateButton).click();
		
	//	System.out.println(page.locator(popupmessage).innerText());
		
	/*
	 * String CustomApiSave_Success_Message1 = getToastMessages();
	 * System.out.println(CustomApiSave_Success_Message1); closeToastMessage();
	 * waitForToastMessagesClose();
	 */
		
		
		ReloadPage();

		MyLogger.info("Created the Managed API");

	

	}
	
	public void WaiforPageGridLoad()
	{
	page.locator(Grid_Count).waitFor();
	}


	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to edit a ManagedAPI from the API registry
	 *              page, it take CAI processName and APIVersion as input as input
	 * @author mbalaji
	 * @param ProcessName - Name of the CAI Process for which need to be edited
	 * @param APIVersion  - Version of the managed API which needs to be edited
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public void EditManagedAPI(String ProcessName, String APIVersion)
			throws InterruptedException, UnsupportedFlavorException, IOException {

	//	ReloadPage();
		MyLogger.info("Search an API");
		page.locator(findSearchBox).fill(ProcessName);
		String APINameUpdated = ManagedAPIName.replace("$protocal", "REST");
		APINameUpdated = APINameUpdated.replace("$Apiname", ProcessName);
		APINameUpdated = APINameUpdated.replace("$version", APIVersion);
		page.locator(APIRegNameColumn).click();	
		Locator src = page.locator(APINameUpdated);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		page.locator(EditButton).click();
	//	page.locator(findSearchBox).fill("");

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to delete a ManagedAPI from the API registry
	 *              page, it take CAI processName, Protocol and APIVersion as input
	 * @author mbalaji
	 * @param ProcessName - Name of the CAI Process which needs to be deleted
	 * @param Protocol    - Protocol TYPE(Accepted values are REST,SOAP)
	 * @param APIVersion  - Version of the managed API which needs to be deleted
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public void DeleteManagedAPI(String ProcessName, String Protocol, String APIVersion)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		// ReloadPage();
		MyLogger.info("Search an API");
		
		page.locator(findSearchBox).fill(ProcessName);
		String APINameUpdated = ManagedAPIName.replace("$protocal", Protocol);
		APINameUpdated = APINameUpdated.replace("$Apiname", ProcessName);
		APINameUpdated = APINameUpdated.replace("$version", APIVersion);
		page.locator(APIRegNameColumn).click();	
		Locator src = page.locator(APINameUpdated);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		page.locator(DeleteButton).click();
		page.locator(Delete).click();
		String CustomApiSave_Success_Message1 = null;
		
	if(CheckforToastMessageVisability())
	{
		  CustomApiSave_Success_Message1 = getToastMessages();
		  System.out.println(CustomApiSave_Success_Message1); closeToastMessage();
		  waitForToastMessagesClose();
		MyLogger.info("Deactivate Message :" +CustomApiSave_Success_Message1);

	}
	
	if(CustomApiSave_Success_Message1.contains("cannot"))
	{
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		page.locator(DeleteButton).click();
		page.locator(Delete).click();

	
	}
	
	if(CheckforToastMessageVisability())
	{
		 
		  String CustomApiSave_Success_Message2 = getToastMessages();
		  System.out.println(CustomApiSave_Success_Message2);
		  closeToastMessage();
		  waitForToastMessagesClose();
		MyLogger.info("Deactivate Message :" +CustomApiSave_Success_Message2);

	}
	
	else
	{
		MyLogger.info("Toast Message with Returned Failed Message");

	}
	
	CheckPageisLoading();
	
		MyLogger.info("Deleted the Managed API post Validation");

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to check the ManagedAPI status, it take CAI
	 *              processName, and APIVersion as input
	 * @author mbalaji
	 * @param ProcessName - Name of the CAI Process for which Managed API status
	 *                    needs to be checked
	 * @param APIVersion  - Version of the managed API for which Managed API status
	 *                    needs to be checked
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public int CheckManagedAPIStatus(String ProcessName, String APIVersion,String Protocal)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		// ReloadPage();
		MyLogger.info("Search an API");
		page.locator(findSearchBox).fill(ProcessName);
		String APINameUpdated = ManagedAPIName.replace("$protocal", Protocal);
		APINameUpdated = APINameUpdated.replace("$Apiname", ProcessName);
		APINameUpdated = APINameUpdated.replace("$version", APIVersion);
	
		page.locator(APIRegNameColumn).click();	
		Locator src = page.locator(APINameUpdated);
	//	int ActiveStatusCount = page.locator(ActiveStatusRecords).count();
		int ActiveStatusCount = page.locator(APINameUpdated).count();
		return ActiveStatusCount;

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to create new version of ManagedAPI, it take
	 *              CAI processName,APIVersion and the APIToSearch as input
	 * @author mbalaji
	 * @param ProcessName - Name of the CAI Process for which new version of the
	 *                    Managed API needs to be created
	 * @param versionName - Name of version of API which needs to be created like
	 *                    "2.0.0", "abc"
	 * @param APIToSearch - Name of the CAI Process which needs to be searched in
	 *                    order to create new version of managed API
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public void CreateNewVersion(String ProcessName, String versionName, String APIToSearch)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		// ReloadPage();
		MyLogger.info("Search an API");
		page.locator(findSearchBox).fill(ProcessName);
		String APINameUpdated = ManagedAPIName.replace("$protocal", "REST");
		APINameUpdated = APINameUpdated.replace("$Apiname", ProcessName);
		APINameUpdated = APINameUpdated.replace("$version", "1.0.0");
		page.locator(APIRegNameColumn).click();	
		Locator src = page.locator(APINameUpdated);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		page.locator(CreateNewVersionButton).click();
		page.locator(Version).fill(versionName);
		page.locator(CreateVersionFind).fill(APIToSearch);
		page.locator(SelectService).click();
		page.locator(SelectService).click();
		page.locator(CreateNewButton).isEnabled();
		MyLogger.info("Create button is Enabled");
		page.locator(CreateNewButton).click();
		System.out.println(page.locator(popupmessage).innerText());

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to check new version of managed API status,
	 *              it take CAI processName,APIVersion as input
	 * @author mbalaji
	 * @param ProcessName - Name of the CAI Process for which new version of Managed
	 *                    API status needs to be checked
	 * @param APIVersion  - Version of the managed API for which new version of
	 *                    Managed API status needs to be checked
	 * @date_created 06-July-2022
	 *               ************************************************************************************************************************
	 */
	public boolean CreateNewVersionStatus(String ProcessName, String APIVersion)
			throws InterruptedException, UnsupportedFlavorException, IOException {

	//	ReloadPage();
		MyLogger.info("Search an API");
		page.locator(findSearchBox).fill(ProcessName);
		String APINameUpdated = ManagedAPIName.replace("$protocal", "REST");
		APINameUpdated = APINameUpdated.replace("$Apiname", ProcessName);
		APINameUpdated = APINameUpdated.replace("$version", APIVersion);
		page.locator(APIRegNameColumn).click();	
		Locator src = page.locator(APINameUpdated);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();
		boolean NewVersionStatus = page.locator(CreateNewVersionButton).isEnabled();
		return NewVersionStatus;

	}

}
