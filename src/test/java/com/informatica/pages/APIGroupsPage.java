package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIGroupsPage extends BasePage {

	public APIGroupsPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	String apiGrouppage = "//div[@title='API Groups']";
	String apiGroupTableHeaders = "(//tr[@class='d-table__header__row'])[2]/th/span";
	String registryPage = "//span[text()='API Registry']";

	// Xpaths in the API Groups page
	String apiGroupsButton = "//span[text()='API Groups']";
	String apiGroupList = "//div[@class='d-shell__page__content' and @data-bo='API GROUPS RESTRICTION']//tbody[@class='d-table__body']/tr";
	String apiGroupSelectPageSize = "//select[@id='d_paginationMenu_1_BYnLkYAo']";
	String apiGroupInAPIGroupsPage = "//div//span[contains(text(),'API Groups')]//ancestor::div[@class='d-section__header']/following-sibling::div//tr//td//span[@class='IconAndText']//span[text()='<GROUPNAME>']";
	String apisInGroupButton = "//button//span[text()='APIs in Group']";
	String findAPIGroupInAPIGroupPage = "//div//span[contains(text(),'API Groups')]//ancestor::div[@class='d-section__header']//input[@placeholder='Find']";

	// XPaths for creating new APIGroup
	String apiGroupCreateButton = "//button//span[text()='New API Group']";
	String inputAPIGroupName = "//input[@placeholder='Enter Name']";
	String inputAPIGroupContext = "//input[@placeholder='Enter Context']";
	String inputAPIGroupDescription = "//textarea[@placeholder='Enter Description']";
	String iPIsInGroupButton = "//button//span[normalize-space()='APIs in Group']";
	String addAPIsToGroupButton = "//button//span[normalize-space()='Add APIs...']";
	String findAPIsSearch = "//div//span[contains(text(),'Add APIs to Group')]/ancestor::div//input[@placeholder='Find']"; // APIM_APIGroups_Automation_Anon1
	String checkboxToSelectAPIToGroup = "//div//span[contains(text(),'Add APIs to Group')]/ancestor::div[@class='d-section__header']/following-sibling::div//tr[contains(@data-id,'<PROTOCOL>_')]//td//span[@class='IconAndText']//span[text()='<API_NAME>']//ancestor::td/preceding-sibling::td[1]//input[@type='checkbox']";
	String addTheSelectedApisButton = "//button[@title='Add']";
	String policiestab = "//button[2][@class='d-tabs__tab']";
	String groupdetailstab = "//button//span[text()='Group Details']";
	String saveTheGroupButton = "//button[@title='Save']";
	String closeasset = "//button[@class='d-version-2 d-icon-button d-shell__page__title__close-icon d-icon-button--cfcn--on-grey']//i";
	String finalSaveFourGroupCreation = "//div[@class='d-dialog__footer__buttons button-group']//button//span[text()='Save']";
	String apiGroupCreateMessage = "//div[@class='d-messagebubble__text']/p/p[text()='The Group \"<GROUPNAME>\" was created successfully.']";
	String apiInApiGroup = "//div//span[contains(text(),'APIs in Group')]//ancestor::div[@class='d-section__header']/following-sibling::div//tr[contains(@data-id,'<PROTOCOL>_')]//td//span[@class='IconAndText']//span[text()='<API_NAME>']";
	String urlOFAPIInAPIGroup = "//div//span[contains(text(),'API URL')]/../following-sibling::div//div[contains(text(),'<CONTEXTNAME>/<GROUPNAME>')]";
	String copyurlOFAPIInAPIGroupButton = "//div//span[contains(text(),'API URL')]/../following-sibling::div//span[text()='Copy URL']";
	String closeTheCreateAPIGroupPage = "//button[@aria-label='Close page']";
	String APIGroupsbutton = "//span[text()='API Groups']";
	String closeAPIGroupPage = "//button//i[@class='aicon aicon__close-solid']";

	// Xpaths for renaming and Deleting the apiGroup
	String apiThreeDotButton = "//i[@class='d-row-actions-menu__trigger']";
	String renameGroupInput = "//input[@placeholder='Enter Group Name']";
	String renameGroupButton = "//li[text()='Rename Group']";
	String deleteGroupButton = "//li[text()='Delete Group']";
	String apiGroupRenameMessage = "//div[@class='d-messagebubble__text']/p/p[text()='The Group \"<GROUPNAME>\" was updated successfully.']";
	String apiGroupDeleteMessage = "//div[@class='d-messagebubble__text']/p/p[text()='The Group \"<GROUPNAME>\" was deleted successfully.']";
	String renameConfirmationButton = "//button//span[text()='Rename']";
	String deleteConfirmationButton = "//button//span[text()='Delete']";
	// Xpath for ORG Rate limit
	String enablegrpratelimit = "//div[@class='TiersRateLimit']//input[@type='checkbox']";
	String selratelimit = "//button[@data-testid='dropdown-button']";
	String selectratelimit = "//span[text()='APIM_Automation_Group']";
	String ratelimitdrpdown = "//div[@class='d-section__content']//div[3]//div[@class='d-dropdown d-version-2']";

	// Xpath for ORG IP Filter
	String ipfrom1 = "//span[@class='IpRange']//span[1]//span[@class='IpInputPart'][1]//input";
	String ipfrom2 = "//span[@class='IpRange']//span[1]//span[@class='IpInputPart'][2]//input";
	String ipfrom3 = "//span[@class='IpRange']//span[1]//span[@class='IpInputPart'][3]//input";
	String ipfrom4 = "//span[@class='IpRange']//span[1]//span[@class='IpInputPart'][4]//input";
	String ipto1 = "//span[@class='IpRange']//span[3]//span[@class='IpInputPart'][1]//input";
	String ipto2 = "//span[@class='IpRange']//span[3]//span[@class='IpInputPart'][2]//input";
	String ipto3 = "//span[@class='IpRange']//span[3]//span[@class='IpInputPart'][3]//input";
	String ipto4 = "//span[@class='IpRange']//span[3]//span[@class='IpInputPart'][4]//input";

	String ipfilteraddbutton = "//div[@class='DescriptionAndAddButtonContainer']//button";
	String denyipfilter = "//div[@class='d-version-2 d-section']//Select";
	String saveNewGroupButton = "//button[@class='d-version-2 d-button d-button--primary']//span[text()='Save']/..";
	String groupHeader = "//span[text()='Group']";
	String firstManagedAPI = "(//span[text()='REST'])[1]";
	String addToGroup = "//button//span[normalize-space()='Add to Group']/..";
	String newGroupButton = "//button//span[normalize-space()='New Group...']";
	String saveOnWarning = "//h1[@title='Warning']/following::button//span[text()='Save']/..";
	String inValidGroupErrorMessage = "//div[contains(@title,'A group cannot contain the following characters')]";
	String contextWithTerrorMessage = "//div[@title='Context cannot be \"t\" because it is reserved for Informatica internal use.']";
	String APItoSelectFromAPIinGroup = "//span//span[contains(text(),'APIs in Group')]/following::span[17]//span[contains(text(),'<API_NAME>')]";
	String removeFromGroup = "//span[@title='Remove from Group']";
	String removeButtononWarningPopUp = "//h1[@title='Warning']/following::span[text()='Remove']/.."; 
	String clickOnGroupName = "//table//th[@data-id='context']/following::span[text()='<GROUPNAME>']";
	String findTextBoxAPIonAPIRegistry = "//div[@data-bo='API REGISTRY RESTRICTION']//input[@placeholder='Find']";
	String searchAPI = "//span[text()='<APINAME>']/following::td[3]//span[text()='REST']/..";
	String contextCannotExceedErrMsg = "//div[text()='Context cannot exceed 65 characters']";
	String contextAlreadExistErrorMsg = "//input[@value='<GROUPNAME>']/following-sibling::div";
	
	
	
	// END
	// END

	public String getPageTitle() {

		MyLogger.info("Getting Page Title on  API Group");
		String APiGrpPage = page.locator(apiGrouppage).innerText();
		return APiGrpPage;
	}

	public List<String> getAPIGroupHeaders() {

		MyLogger.info("Getting API Group Page header Data");
		// page.reload();
		// page.locator(APIGrouppage).waitFor();

		List<String> headersList = new ArrayList<>();

		Locator headers = page.locator(apiGroupTableHeaders);

		for (int i = 0; i < headers.count(); i++) {

			String headerName = headers.nth(i).innerText();
			if (headerName != null && !headerName.equals("")) {
				headersList.add(headerName);
			}

		}

		MyLogger.info("Actual API Group headers : " + headersList);
		return headersList;
	}

	public boolean isElementPreset(String element) {
		if (page.locator(element).count() > 0)
			return true;
		else
			return false;

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used to set the pageSize of the Groups Page
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 **/
	public void setAPIGroupPageSize() {
		if (page.locator(apiGroupSelectPageSize).count() > 0) {

			page.locator(apiGroupSelectPageSize).selectOption("1000");
		} else {
			MyLogger.info("Groups avaialble are less than 10, so not changing the page size");
		}

	}

	public void getAPIGroup(String apiGroupName) {

		MyLogger.info("Getting the mentioned API Group");
		setAPIGroupPageSize();
		String apGroupName = apiGroupInAPIGroupsPage.replace("<GROUPNAME>", apiGroupName);

		List<String> a = new ArrayList<>();

		System.out.println(page.locator(apGroupName).innerText());
		page.locator(apGroupName).click();

		page.locator(apGroupName).count();
		page.locator(apisInGroupButton).click();

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used for checking if the groupExist in the
	 *              APIGroups List
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param element: apiGroupName is the group which is searched *
	 */
	public String checkIFAPIGroupExist(String apiGroupName) {

		MyLogger.info("finding the group -->" + apiGroupName + "<-- from API Groups");
		clickElement(apiGroupsButton);
		page.fill(findAPIGroupInAPIGroupPage, "");
		page.locator(apiGrouppage).waitFor();
		// page.waitForTimeout(10000);
		setAPIGroupPageSize();
		String apGroupName = apiGroupInAPIGroupsPage.replace("<GROUPNAME>", apiGroupName);
		MyLogger.info("finding the Group :" + apiGroupName + "in APIGroups Page");
		page.fill(findAPIGroupInAPIGroupPage, apiGroupName);

		if (page.locator(apGroupName).count() > 0) {
			return page.locator(apGroupName).innerText();
		} else {
			return "NotFound";
		}
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used for checking if the groupExist in the
	 *              APIGroups List
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param apiGroupName: apiGroupName is the group Name to be created
	 *                      apiGroupContext : Group Context Name apiGroupDescription
	 *                      : Api Group Description to be filled managedAPIName :
	 *                      MangedAPI Name to be assigned to the group
	 *                      managedAPIProtocol : The Protocol of the API to be
	 *                      selected(either REST/SOAP)
	 * @returns : Toast message of the CreateGroup *
	 */
	public String createAPIGroup(String apiGroupName, String apiGroupContext, String apiGroupDescription,
			String managedAPIName, String managedAPIProtocol) throws Exception {

		String toastMessageAfterCreationOfGroup = null;
		try {
			String checkboxToSelectAPIToGroup_1 = null;
			String checkboxToSelectAPIToGroup_2 = null;
			String apGroupCreateMessage = null;

			MyLogger.info("creating the APIGroup :" + apiGroupName);
			// Traversing to the Groups Section in the API Registry
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			clickElement(apiGroupCreateButton);
			page.fill(inputAPIGroupName, apiGroupName);
			page.fill(inputAPIGroupContext, apiGroupContext);
			page.fill(inputAPIGroupDescription, apiGroupDescription);
			// clickElement(apisInGroupButton);
			page.locator(apisInGroupButton).click();
			// clickElement(addAPIsToGroupButton);
			page.locator(addAPIsToGroupButton).click();
			MyLogger.info("group Details have been filled successfully for Group :" + apiGroupName);
			MyLogger.info("adding APIs to Group :" + apiGroupName);
			checkboxToSelectAPIToGroup_1 = checkboxToSelectAPIToGroup.replace("<API_NAME>", managedAPIName);
			checkboxToSelectAPIToGroup_2 = checkboxToSelectAPIToGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			page.fill(findAPIsSearch, managedAPIName);
			// clickElement(checkboxToSelectAPIToGroup_2);
			page.locator(checkboxToSelectAPIToGroup_2).click();
			MyLogger.info("adding APIs to Group is completed for Group :" + apiGroupName);
			// clickElement(addTheSelectedApisButton);
			page.locator(addTheSelectedApisButton).click();
			// clickElement(saveTheGroupButton);
			page.locator(saveTheGroupButton).click();
			// if (page.locator(finalSaveFourGroupCreation).isVisible()) {
			// page.locator(finalSaveFourGroupCreation).click();
			// }
			clickElement(finalSaveFourGroupCreation);
			apGroupCreateMessage = apiGroupCreateMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterCreationOfGroup = page.locator(apGroupCreateMessage).innerText();
			MyLogger.info("closing the Group Creation  page for Group :" + apiGroupName);
			closeToastMessage();
			waitForToastMessagesClose();
			MyLogger.info("the message after creation of Group : " + toastMessageAfterCreationOfGroup);
			clickElement(closeAPIGroupPage);

		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return toastMessageAfterCreationOfGroup;

	}
	

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used for the validating the managedAPI url which
	 *              is under the Group
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param apiGroupName: under which apiGroup the Managed API to be searched.
	 *                      apiGroupContext : Group Context which need to be checked
	 *                      in the ManagedAPI URL managedAPIName : which ManagedAPI
	 *                      url to look for managedAPIProtocol : The Protocol of the
	 *                      API to be selected(either REST/SOAP)
	 * @return returns the map with the managedAPI urls --> 1) copyURL and the 2)
	 *         managedAPIURLFromGroup
	 */
	public Map<String, String> validateAPIURLInAPIGroup(String apiGroupName, String apiGroupContext,
			String managedAPIName, String managedAPIProtocol) throws Exception {

		String apiInApiGroup_1 = null;
		String apiInApiGroup_2 = null;
		String managedAPIURLFromGroup = null;
		String urlFromCopyURLButton = null;
		String urlAPIInAPIGroup = null;
		HashMap<String, String> map = new HashMap<>();

		try {

			String apGroupName = apiGroupInAPIGroupsPage.replace("<GROUPNAME>", apiGroupName);
			// Traversing to the Groups Section in the API Registry
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			MyLogger.info("finding the Group :" + apiGroupName + "in APIGroups Page");
			page.fill(findAPIGroupInAPIGroupPage, apiGroupName);
			clickElement(apGroupName);
			clickElement(apGroupName);
			MyLogger.info("found the Group :" + apiGroupName);
			// clickElement(apisInGroupButton);
			page.locator(apisInGroupButton).click();
			apiInApiGroup_1 = apiInApiGroup.replace("<API_NAME>", managedAPIName);
			apiInApiGroup_2 = apiInApiGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			// clickElement(apiInApiGroup_2);
			page.locator(apiInApiGroup_2).click();
			MyLogger.info("navigated to the APIsINGroup Section for Group :" + apiGroupName);
			// String ContextGroupName=apiGroupContext;

			if (apiGroupContext.length() > 0) {
				urlAPIInAPIGroup = urlOFAPIInAPIGroup.replace("<CONTEXTNAME>/<GROUPNAME>",
						apiGroupContext + "/" + managedAPIName);
				MyLogger.info("framing API Url for withContext");
			} else {
				urlAPIInAPIGroup = urlOFAPIInAPIGroup.replace("<CONTEXTNAME>/<GROUPNAME>", managedAPIName);
				MyLogger.info("framing API Url for withoutContext");
				// managedAPIURLFromGroup=page.locator(urlAPIInAPIGroup).innerText();
			}
			MyLogger.info("clicking the copyURL Button ");
			clickElement(copyurlOFAPIInAPIGroupButton);
			// page.locator(copyurlOFAPIInAPIGroupButton).click();
			urlFromCopyURLButton = GetClipBoardData();
			closeToastMessage();
			waitForToastMessagesClose();
			managedAPIURLFromGroup = page.locator(urlAPIInAPIGroup).innerText();
			MyLogger.info("managedAPIURLFromGroup-->" + managedAPIURLFromGroup);
			MyLogger.info("urlFromCopyURLButton-->" + urlFromCopyURLButton);
			map.put("managedAPIURLFromGroup", managedAPIURLFromGroup);
			map.put("urlFromCopyURLButton", urlFromCopyURLButton);
			clickElement(closeAPIGroupPage);
			clickElement(closeAPIGroupPage);
			MyLogger.info("created the APIGroup");
		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return map;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used for renaming the existing APIGroup
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param apiGroupName: Existing group Name which needs to be Renamed.
	 *                      newGroupName : NewGroupName
	 * @return returns the toast message after Renaming
	 */
	public String reNameAPIGroup(String apiGroupName, String newGroupName) throws Exception {
		String toastMessageAfterRenameOfGroup = null;
		try {

			MyLogger.info("renaming the APIGroup :" + apiGroupName);

			String apGroupName = apiGroupInAPIGroupsPage.replace("<GROUPNAME>", apiGroupName);
			// Traversing to the Groups Section in the API Registry
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			MyLogger.info("finding the Group :" + apiGroupName + "in APIGroups Page");
			page.fill(findAPIGroupInAPIGroupPage, apiGroupName);
			Locator src = page.locator(apGroupName);
			waitForMouseActions();
			page.mouse().move(src.boundingBox().x, src.boundingBox().y);
			page.locator(apiThreeDotButton).click();
			MyLogger.info("clicked on the three dots button on the Group :" + apiGroupName);
			page.locator(renameGroupButton).click();
			MyLogger.info("clicked on the Rename button on the Group :" + apiGroupName);
			page.fill(renameGroupInput, newGroupName);
			String apGroupRenameMessage = apiGroupRenameMessage.replace("<GROUPNAME>", apiGroupName);
			page.locator(renameConfirmationButton).click();
			MyLogger.info("renaming the Group is clicked");
			toastMessageAfterRenameOfGroup = page.locator(apGroupRenameMessage).innerText();
			closeToastMessage();
			waitForToastMessagesClose();
			MyLogger.info("the message after Rename of Group: " + toastMessageAfterRenameOfGroup);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			MyLogger.info("exception occcured in the renameAPIGroup " + e);
			throw e;
		}

		return toastMessageAfterRenameOfGroup;
	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This method is used for deleting the existing APIGroup
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param apiGroupName: Existing group Name which needs to be Deleted.
	 * @return returns the toast message after deleting
	 */
	public String removeAPIGroup(String apiGroupName) throws Exception {
		String toastMessageAfterDeletionOfGroup = null;
		try {
			MyLogger.info("remvoing the APIGroup :" + apiGroupName);

			String apGroupName = apiGroupInAPIGroupsPage.replace("<GROUPNAME>", apiGroupName);
			// Traversing to the Groups Section in the API Registry
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			MyLogger.info("finding the Group :" + apiGroupName + "in APIGroups Page");
			page.fill(findAPIGroupInAPIGroupPage, apiGroupName);
			Locator src = page.locator(apGroupName);
			waitForMouseActions();
			page.mouse().move(src.boundingBox().x, src.boundingBox().y);
			page.locator(apiThreeDotButton).click();
			MyLogger.info("clicked on the three dots button on the Group :" + apiGroupName);
			page.locator(deleteGroupButton).click();
			MyLogger.info("clicked on the delete button on the Group :" + apiGroupName);
			page.locator(deleteConfirmationButton).click();
			String apGroupDeleteMessage = apiGroupDeleteMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterDeletionOfGroup = page.locator(apGroupDeleteMessage).innerText();
			closeToastMessage();
			waitForToastMessagesClose();
			page.waitForLoadState(LoadState.NETWORKIDLE);
		} catch (Exception e) {
			MyLogger.info("exception occcured in the deleteAPIGroup " + e);
			throw e;
		}

		return toastMessageAfterDeletionOfGroup;
	}

	/**
	 * Description: This Method with create a new group with org ipfilter, after
	 * navigating to pages and checks the data is retained Author: pmansabd Date
	 * Created: 07th Jul 2022 Coverage: UI POD SANITY
	 */

	public String createAPIGroupwithipfilter(String apiGroupName, String apiGroupDescription, String managedAPIName,
			String managedAPIProtocol, String fromip1, String fromip2, String fromip3, String fromip4, String toip1,
			String toip2, String toip3, String toip4) throws Exception {
		String createdGroupName = null;
		try {
			String urlAPIInAPIGroup = null;
			String checkboxToSelectAPIToGroup_1 = null;
			String checkboxToSelectAPIToGroup_2 = null;
			String apGroupCreateMessage = null;
			String toastMessageAfterCreationOfGroup = null;
			String apiInApiGroup_1 = null;
			String apiInApiGroup_2 = null;
			String managedAPIURLFromGroup = null;
			String urlFromCopyURLButton = null;
			MyLogger.info("creating the APIGroup :" + apiGroupName);
			String checkGroup = checkIFAPIGroupExist(apiGroupName);
			if (checkGroup != "NotFound") {
				MyLogger.info("Deleting the Group: " + apiGroupName + " as its already exist");
				removeAPIGroup(apiGroupName);
			}

			clickElement(apiGroupCreateButton);

			page.fill(inputAPIGroupName, apiGroupName);
			// page.fill(InputAPIGroupContext, apiGroupContext);
			page.fill(inputAPIGroupDescription, apiGroupDescription);
			page.locator(policiestab).click();
			page.selectOption(denyipfilter, "Deny");
			page.fill(ipfrom1, fromip1);
			page.fill(ipfrom2, fromip2);
			page.fill(ipfrom3, fromip1);
			page.fill(ipfrom4, fromip4);
			page.fill(ipto1, toip1);
			page.fill(ipto2, toip2);
			page.fill(ipto3, toip3);
			page.fill(ipto4, toip4);
			page.locator(ipfilteraddbutton).click();
			page.locator(iPIsInGroupButton).click();
			page.locator(addAPIsToGroupButton).click();
			checkboxToSelectAPIToGroup_1 = checkboxToSelectAPIToGroup.replace("<API_NAME>", managedAPIName);
			checkboxToSelectAPIToGroup_2 = checkboxToSelectAPIToGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			page.fill(findAPIsSearch, managedAPIName);
			page.locator(checkboxToSelectAPIToGroup_2).click();
			page.locator(addTheSelectedApisButton).click();
			page.locator(saveTheGroupButton).click();
			if (page.locator(finalSaveFourGroupCreation).isVisible()) {
				page.locator(finalSaveFourGroupCreation).click();
			}
			apGroupCreateMessage = apiGroupCreateMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterCreationOfGroup = page.locator(apGroupCreateMessage).innerText();
			MyLogger.info("the message after creation of Group : " + toastMessageAfterCreationOfGroup);
			/*
			 * assertEquals(toastMessageAfterCreationOfGroup, "The Group \"" + apiGroupName
			 * + "\" was created successfully.", "Checking the successful ToastMessage");
			 */

			page.locator(iPIsInGroupButton).click();
			apiInApiGroup_1 = apiInApiGroup.replace("<API_NAME>", managedAPIName);
			apiInApiGroup_2 = apiInApiGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			page.locator(apiInApiGroup_2).click();
			page.locator(copyurlOFAPIInAPIGroupButton).click();
			page.locator(closeasset).click();
			page.locator(groupdetailstab).click();
			page.locator(closeasset).click();
			page.locator(APIGroupsbutton).click();

			createdGroupName = checkIFAPIGroupExist(apiGroupName);

		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return createdGroupName;

	}

	/**
	 * Description: This Method with create a new group with rate limit, after
	 * navigating to pages and checks the data is retained Author: pmansabd Date
	 * Created: 07th Jul 2022 JIRA TCs associated: NA Coverage: UI POD SANITY
	 */
	public String createAPIGroupwithratelimit(String apiGroupName, String apiGroupDescription, String managedAPIName,
			String managedAPIProtocol) throws Exception {
		String createdGroupName = null;
		try {
			// String urlAPIInAPIGroup = null;
			String checkboxToSelectAPIToGroup_1 = null;
			String checkboxToSelectAPIToGroup_2 = null;
			String apGroupCreateMessage = null;
			String toastMessageAfterCreationOfGroup = null;
			String apiInApiGroup_1 = null;
			String apiInApiGroup_2 = null;
			// String managedAPIURLFromGroup = null;
			// String urlFromCopyURLButton = null;
			MyLogger.info("creating the APIGroup :" + apiGroupName);
			/*
			 * String checkGroup=checkIFAPIGroupExist(apiGroupName); if(checkGroup !=
			 * "NotFound") {
			 * MyLogger.info("Deleting the Group: "+apiGroupName+" as its alred exist");
			 * removeAPIGroup(apiGroupName); }
			 */

			page.locator(apiGroupCreateButton).click();

			page.fill(inputAPIGroupName, apiGroupName);
			// page.fill(InputAPIGroupContext, apiGroupContext);
			page.fill(inputAPIGroupDescription, apiGroupDescription);
			page.locator(policiestab).click();
			page.locator(enablegrpratelimit).click();
			page.locator(ratelimitdrpdown).click();
			page.locator(selectratelimit).click();
			page.locator(apisInGroupButton).click();
			page.locator(addAPIsToGroupButton).click();
			checkboxToSelectAPIToGroup_1 = checkboxToSelectAPIToGroup.replace("<API_NAME>", managedAPIName);
			checkboxToSelectAPIToGroup_2 = checkboxToSelectAPIToGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			page.fill(findAPIsSearch, managedAPIName);
			page.locator(checkboxToSelectAPIToGroup_2).click();
			page.locator(addTheSelectedApisButton).click();
			page.locator(saveTheGroupButton).click();
			if (page.locator(finalSaveFourGroupCreation).isVisible()) {
				page.locator(finalSaveFourGroupCreation).click();
			}
			apGroupCreateMessage = apiGroupCreateMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterCreationOfGroup = page.locator(apGroupCreateMessage).innerText();
			MyLogger.info("the message after creation of Group : " + toastMessageAfterCreationOfGroup);
			/*
			 * assertEquals(toastMessageAfterCreationOfGroup, "The Group \"" + apiGroupName
			 * + "\" was created successfully.", "Checking the successful ToastMessage");
			 */

			page.locator(apisInGroupButton).click();
			apiInApiGroup_1 = apiInApiGroup.replace("<API_NAME>", managedAPIName);
			apiInApiGroup_2 = apiInApiGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			page.locator(apiInApiGroup_2).click();

			page.locator(copyurlOFAPIInAPIGroupButton).click();
			page.locator(closeasset).click();
			page.locator(groupdetailstab).click();
			page.locator(closeasset).click();
			page.locator(APIGroupsbutton).click();
			createdGroupName = checkIFAPIGroupExist(apiGroupName);
			createdGroupName = checkIFAPIGroupExist(apiGroupName);
		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return createdGroupName;

	}
	
	public String CreationOfGroupWithNoAPIsAddedToGroup(String apiGroupName, String apiGroupContext, String apiGroupDescription) throws Exception {

		String toastMessageAfterCreationOfGroup = null;
		try {
			String apGroupCreateMessage = null;
			MyLogger.info("creating the APIGroup :" + apiGroupName);
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			clickElement(apiGroupCreateButton);
			page.fill(inputAPIGroupName, apiGroupName);
			page.fill(inputAPIGroupContext, apiGroupContext);
			page.fill(inputAPIGroupDescription, apiGroupDescription);	
			clickElement(saveNewGroupButton);
			
			apGroupCreateMessage = apiGroupCreateMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterCreationOfGroup = page.locator(apGroupCreateMessage).innerText();
			MyLogger.info("closing the Group Creation  page for Group :" + apiGroupName);
			closeToastMessage();
			waitForToastMessagesClose();
			MyLogger.info("the message after creation of Group : " + toastMessageAfterCreationOfGroup);
			clickElement(closeAPIGroupPage);

		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return toastMessageAfterCreationOfGroup;

	}
	
	
	public String CreationOfGroupFromAPITabAddToGroupButton(String apiGroupName, String apiGroupContext, String apiGroupDescription) throws Exception {
		String toastMessageAfterCreationOfGroup = null;
		try {
			String apGroupCreateMessage = null;
			MyLogger.info("creating the APIGroup :" + apiGroupName);
			clickElement(groupHeader);
			clickElement(firstManagedAPI);
			clickElement(addToGroup);
			clickElement(newGroupButton);
			page.fill(inputAPIGroupName, apiGroupName);
			page.fill(inputAPIGroupContext, apiGroupContext);
			page.fill(inputAPIGroupDescription, apiGroupDescription);	
			clickElement(saveNewGroupButton);
			clickElement(saveOnWarning);			
			apGroupCreateMessage = apiGroupCreateMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterCreationOfGroup = page.locator(apGroupCreateMessage).innerText();
			MyLogger.info("closing the Group Creation  page for Group :" + apiGroupName);
			closeToastMessage();
			waitForToastMessagesClose();
			MyLogger.info("the message after creation of Group : " + toastMessageAfterCreationOfGroup);
			clickElement(closeAPIGroupPage);
		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return toastMessageAfterCreationOfGroup;

	}
	
	/**
	 * @author jashaik
	 * Description: This method is used to Validate whether system displays Error Message when Invalid data is entered into the TextBox
	 * @param TextBoxelement
	 */	
	public void groupNameValidtionCharsNotAllowed(String apiGroupName) throws Exception {

		try {
			MyLogger.info("Validating System Throws Error messagew when Group Name Contains any of the chars :" + apiGroupName);
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			clickElement(apiGroupCreateButton);
			GroupNameErrorMessageValidator(inputAPIGroupName, apiGroupName, inValidGroupErrorMessage);
			} 
	catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
			}
	}
	
	/**
	 * @author jashaik
	 * Description: This method is used to Validate whether system displays Error Message when Invalid data is entered into the TextBox
	 * @param TextBoxelement
	 */	
	public void groupContextValidation(String groupContext) throws Exception {

		try {
			MyLogger.info("Validating System Throws Error messagew when Context Contains char t :" + groupContext);
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			clickElement(apiGroupCreateButton);
			ErrorMessageValidator(inputAPIGroupContext, groupContext, contextWithTerrorMessage);
			} 
	catch (Exception e) {
			MyLogger.info("Exception While Adding char t in context " + e);
			throw e;
			}
	}
	
	/**
	 * @author JaShaik
	 * Description: This method is used to Validate whether system displays Error Message when Invalid data is entered into the TextBox
	 * @param TextBoxelement
	 * @param text To be Entered
	 * @throws InterruptedException 
	 */
	public void GroupNameErrorMessageValidator(String Inpuelement, String text, String ErroMessageElement) throws InterruptedException {
		String temp= null;
		
		for( int i=0;i<text.length();i++)
		{
			temp = ""+text.charAt(i);
			page.fill(Inpuelement, temp);
			if(page.isVisible(ErroMessageElement))
			{
				MyLogger.info("System is not accepting Invalid Character : "+text.charAt(i)+" in Group Name ");
				Assert.assertEquals(page.isVisible(ErroMessageElement), true, "System is Not Accepting Character"+text.charAt(i));
			}
			else
			{
			MyLogger.info("System is accepting Invalid Character : "+text.charAt(i)+" in Group Name ");
			Assert.assertEquals(false, true, "System is Accepting Invalid Characters");
			break;
			
			
			}
			page.fill(Inpuelement, "");
			
		}
		
		
	}
	
	
	public void ErrorMessageValidator(String Inpuelement, String text, String ErroMessageElement) throws InterruptedException {
	
			page.fill(Inpuelement, text);
			if(page.isVisible(ErroMessageElement))
			{
				MyLogger.info("System is not accepting Invalid Character : "+text);
				Assert.assertEquals(page.isVisible(ErroMessageElement), true, "System is Not Accepting Character"+text);
			}
			else
			{
				MyLogger.info("System is accepting Invalid Character : "+text);
				Assert.assertEquals(false, true, "System is Accepting Invalid Characters");
			}
			page.fill(Inpuelement, "");
			
		}
		

	

	public void addAPIstoGroup(String apiGroupName, String managedAPIName) throws InterruptedException, UnsupportedFlavorException, IOException
		{	
		String checkboxToSelectAPIToGroup_1 = null;
		String checkboxToSelectAPIToGroup_2 = null;
	try
		{
		MyLogger.info("START:Adding APIs to the Group "+apiGroupName);
		if(page.locator(closeAPIGroupPage).isVisible())
		{
			page.locator(closeAPIGroupPage).click();
		}
		MyLogger.info("START: finding the group -->" + apiGroupName + "<-- from API Groups");
		clickElement(apiGroupsButton);
		page.fill(findAPIGroupInAPIGroupPage, "");
		page.fill(findAPIGroupInAPIGroupPage, apiGroupName);
		page.locator(apiGrouppage).waitFor();
		setAPIGroupPageSize();
		String apGroupName = apiGroupInAPIGroupsPage.replace("<GROUPNAME>", apiGroupName);
		page.fill(findAPIGroupInAPIGroupPage, apiGroupName);
		page.dblclick(apGroupName);
		MyLogger.info("END: finding the group -->" + apiGroupName + "<-- from API Groups");

	
		
		MyLogger.info("START: Adding APIs to the group -->" + apiGroupName);
		page.locator(apisInGroupButton).click();
		page.locator(addAPIsToGroupButton).click();
		checkboxToSelectAPIToGroup_1 = checkboxToSelectAPIToGroup.replace("<API_NAME>", managedAPIName);
		checkboxToSelectAPIToGroup_2 = checkboxToSelectAPIToGroup_1.replace("<PROTOCOL>", "REST");
		page.fill(findAPIsSearch, "");
		page.fill(findAPIsSearch, managedAPIName);
		page.locator(checkboxToSelectAPIToGroup_2).click();
		page.locator(addTheSelectedApisButton).click();
		page.locator(saveTheGroupButton).click();
		if (page.locator(finalSaveFourGroupCreation).isVisible()) 
		{page.locator(finalSaveFourGroupCreation).click();}
		closeToastMessage();
		waitForToastMessagesClose();
		if(page.locator(closeAPIGroupPage).isVisible())
		{page.locator(closeAPIGroupPage).click();}
		MyLogger.info("END: Adding APIs to the group -->" + apiGroupName);
		}
	catch(Exception e)
		{
		MyLogger.info("Exception occcured while adding APIs to Group " + e);
		throw e;
		}
	
	


		}
	public String createGroupWithContextAddAPIsAndReturnAPIurlPartOfGroup(String apiGroupName, String apiGroupContext,String managedAPIName, String managedAPIProtocol) throws Exception {
		String urlFromCopyURLButton = null;
		String checkboxToSelectAPIToGroup_1 = null;
		String checkboxToSelectAPIToGroup_2 = null;
		String APIName = null;

		try {
			
			MyLogger.info("Creation of Group with Context Started");
			String toastMessageAfterCreationOfGroup = null;				
			String apGroupCreateMessage = null;
			MyLogger.info("creating the APIGroup :" + apiGroupName);
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			clickElement(apiGroupCreateButton);
			page.fill(inputAPIGroupName, apiGroupName);
			page.fill(inputAPIGroupContext, apiGroupContext);
			clickElement(saveNewGroupButton);			
			apGroupCreateMessage = apiGroupCreateMessage.replace("<GROUPNAME>", apiGroupName);
			toastMessageAfterCreationOfGroup = page.locator(apGroupCreateMessage).innerText();
			MyLogger.info("closing the Group Creation  page for Group :" + apiGroupName);
			closeToastMessage();
			waitForToastMessagesClose();
			MyLogger.info("the message after creation of Group : " + toastMessageAfterCreationOfGroup);
			
			MyLogger.info("Adding APIs to the Group "+apiGroupName);
			page.locator(apisInGroupButton).click();
			page.locator(addAPIsToGroupButton).click();
			checkboxToSelectAPIToGroup_1 = checkboxToSelectAPIToGroup.replace("<API_NAME>", managedAPIName);
			checkboxToSelectAPIToGroup_2 = checkboxToSelectAPIToGroup_1.replace("<PROTOCOL>", managedAPIProtocol);
			page.fill(findAPIsSearch, "");
			page.fill(findAPIsSearch, managedAPIName);
			page.locator(checkboxToSelectAPIToGroup_2).click();
			page.locator(addTheSelectedApisButton).click();
			page.locator(saveTheGroupButton).click();
			if (page.locator(finalSaveFourGroupCreation).isVisible()) 
			{page.locator(finalSaveFourGroupCreation).click();}
			closeToastMessage();
			waitForToastMessagesClose();
			MyLogger.info("Successully Added APIs to the Group "+apiGroupName);
			
			MyLogger.info("Getting the URL of the API that is part of Group "+apiGroupName +"With Context "+apiGroupContext);			
			APIName = APItoSelectFromAPIinGroup.replace("<API_NAME>", managedAPIName);
			page.locator(APIName).click();
			MyLogger.info("navigated to the APIsINGroup Section for Group :" + apiGroupName);
			clickElement(copyurlOFAPIInAPIGroupButton);
			MyLogger.info("Copied the API Url for withContext");
			waitForToastMessagesClose();
			urlFromCopyURLButton = GetClipBoardData();
			waitForToastMessagesClose();
			MyLogger.info("urlFromCopyURLButton-->" + urlFromCopyURLButton);
			waitForMouseActions();
			MyLogger.info("Got the URL of the API that is part of Group "+apiGroupName +"With Context "+apiGroupContext);
			page.click(closeAPIGroupPage);
			
		} 
		catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup Wiht Context " + e);
			throw e;
		}
		return urlFromCopyURLButton;
		
	}
	
	
	public void clickOnRemoveFromGroup() throws InterruptedException {
		MyLogger.info("Clicking on Remove From Group link");
		page.locator(removeFromGroup).click();
		CheckPageisLoading();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}
	
	
	public void clickOnRemoveButtonOnWarningPopUp() throws InterruptedException {

		MyLogger.info("Clicking on Remove Button On the Warning Pop Up Window");
		page.locator(removeButtononWarningPopUp).click();
		CheckPageisLoading();
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}
	
	/**
	 * @author JaShaik
	 * Description: This method is used to update the Group Context. If Give 'remove' as as the updatedContext then it will remove total Context. 
	 *				Else it will append the context text with given update context value 
	 * @param groupName 
	 * @param existingText
	 * @param updatedContext
	 * @throws InterruptedException 
	 */		
	public void updateGroupContext(String groupName,String existingText,  String updatedContext) throws InterruptedException {

		try
		{MyLogger.info("Updating the Group Context");
		if(page.locator(closeAPIGroupPage).isVisible())
		{
			
			page.locator(closeAPIGroupPage).click();
		}
		clickElement(apiGroupsButton);
		page.fill(findAPIGroupInAPIGroupPage, "");
		page.fill(findAPIGroupInAPIGroupPage,groupName );
		String targetGroupName = clickOnGroupName.replace("<GROUPNAME>", groupName);
		page.dblclick(targetGroupName);
		if(!updatedContext.equalsIgnoreCase("remove"))
		{
			page.fill(inputAPIGroupContext, existingText+updatedContext);
			clickElement(saveNewGroupButton);	
			page.click(finalSaveFourGroupCreation);
			CheckPageisLoading();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.click(closeAPIGroupPage);
			MyLogger.info("Group Context is Updated from: "+existingText +" to: "+existingText+updatedContext);
		}
		else
		{
			page.fill(inputAPIGroupContext, "");	
			clickElement(saveNewGroupButton);	
			page.click(finalSaveFourGroupCreation);
			CheckPageisLoading();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.click(closeAPIGroupPage);
			MyLogger.info("Group Context :"+existingText +"is now Removed Sucessfully");
		}
	
		}
		catch(Exception e)
		{
			MyLogger.info("Exception while updating the Group Context "+e);
		}
		

	}
	
	
	
	

	
	public String returnCopiedURLFromCopyURL() throws InterruptedException, UnsupportedFlavorException, IOException {
		
		MyLogger.info("Copying the URL of the copy URL Button");
		String urlFromCopyURLButton = null;
		clickElement(copyurlOFAPIInAPIGroupButton);
		MyLogger.info("Copied the API Url for withContext");
		waitForToastMessagesClose();
		urlFromCopyURLButton = GetClipBoardData();
		waitForToastMessagesClose();
		MyLogger.info("urlFromCopyURLButton-->" + urlFromCopyURLButton);
		waitForMouseActions();
		return urlFromCopyURLButton;
	}
	
	
	public String returnURLofAPI(String apiName) throws InterruptedException, UnsupportedFlavorException, IOException {
		try 
		{
		MyLogger.info("Copying the API Url");		
		String urlFromCopyURLButton = null;
		page.click(registryPage);
		page.fill(findTextBoxAPIonAPIRegistry,"");
		page.fill(findTextBoxAPIonAPIRegistry, apiName);
		String target = searchAPI.replace("<APINAME>", apiName);
		page.dblclick(target);		
		clickElement(copyurlOFAPIInAPIGroupButton);
		MyLogger.info("Copied the API Url of API"+apiName);
		waitForToastMessagesClose();
		urlFromCopyURLButton = GetClipBoardData();
		waitForToastMessagesClose();
		MyLogger.info("urlFromCopyURLButton--->" + urlFromCopyURLButton);
		waitForMouseActions();
		page.locator(closeAPIGroupPage).click();
		
		return urlFromCopyURLButton;
		}
		catch (Exception e) 
		{
		MyLogger.info("exception occuried while copying url " + e);
		throw e;
		}}
/**
 * @param apiName
 * @Description: This method is used to remove API from the given Group
 * @throws InterruptedException
 * @throws UnsupportedFlavorException
 * @throws IOException
 */
	public void removeAPIFromGroup(String apiName) throws InterruptedException, UnsupportedFlavorException, IOException {
		try 
	{
		MyLogger.info("Removing API From Group");		
		page.click(registryPage);
		page.fill(findTextBoxAPIonAPIRegistry,"");
		page.fill(findTextBoxAPIonAPIRegistry, apiName);
		String target = searchAPI.replace("<APINAME>", apiName);
		page.dblclick(target);	
		//page.locator(removeFromGroup).waitFor();
	if(page.locator(removeFromGroup).isVisible())
	{
		MyLogger.info("Removed API"+apiName+" from the Group");
		clickOnRemoveFromGroup();
		clickOnRemoveButtonOnWarningPopUp();
		page.locator(closeAPIGroupPage).click();
	}
	else
	{
		page.locator(closeAPIGroupPage).click();	
		MyLogger.info("API "+apiName+" is not part of any Group");	
	}
	
	}
	
	catch (Exception e) 
		{
		MyLogger.info("exception while Removing API From Group while copying url " + e);
		throw e;
		}
	
	
	}
	/**
	 * @author JaShaik
	 * Description:This method returns a Unique Text that is appended with Automation_ along with the time stamp 
	 */			
	public String UniqueGroupNameWithTimeStamp() {
		Date date = new Date();		
		SimpleDateFormat DateFor = new SimpleDateFormat("MMM dd yyy_HH:mm:ss:SSS");
		String groupName = (DateFor.format(date)).replace(" ", "").replace(":", "_");
		return "Automation_"+groupName;
	}
	/**
	 * @author JaShaik
	 * Description:This method returns a Unique Text that is appended with /AutomationContext_ along with the time stamp 
	 */			
	public String UniqueContextNameWithTimeStamp() {
		Date date = new Date();		
		SimpleDateFormat DateFor = new SimpleDateFormat("MMM dd yyy_HH:mm:ss:SSS");
		String contextName = (DateFor.format(date)).replace(" ", "").replace(":", "_");
		return "/AutomationContext_"+contextName;
	}
	
	/**
	 * @author JaShaik
	 * Description: Validated whehter context accets the values within a range of 1 to 52 chars
	 * @param groupName 
	 * @param existingText
	 * @param updatedContext
	 * @throws InterruptedException 
	 */		
	public void checkGroupContextAccepts1to65(String groupName,String existingText,  String updatedContext) throws InterruptedException {

		try
		{
		MyLogger.info("Updating the Group Context");
		if(page.locator(closeAPIGroupPage).isVisible())
		{page.locator(closeAPIGroupPage).click();}
		clickElement(apiGroupsButton);
		page.fill(findAPIGroupInAPIGroupPage, "");
		page.fill(findAPIGroupInAPIGroupPage,groupName );
		String targetGroupName = clickOnGroupName.replace("<GROUPNAME>", groupName);
		page.dblclick(targetGroupName);
		
			
			page.fill(inputAPIGroupContext, "");
			page.fill(inputAPIGroupContext, existingText);
			MyLogger.info("Valdiating Error message is not displayed when user enter single char");
			if(page.isVisible(contextCannotExceedErrMsg))
			{
				System.out.println("true");
				MyLogger.info("System throws error message when user enterd within  defined range of context : "+existingText);
				Assert.assertTrue(false);
			}
			else
			{				
				System.out.println("false");
				MyLogger.info("System accepts chars within defined range "+existingText);
			}
			
			
			
			page.fill(inputAPIGroupContext, "");
			page.fill(inputAPIGroupContext, existingText+updatedContext);
			MyLogger.info("Valdiating Error message is displayed when user enter more than 65 char");
			if(page.isVisible(contextCannotExceedErrMsg))
			{
				MyLogger.info("System display error message when text in context exceeds 65 chars "+existingText+updatedContext);
			
				
			}
			else
			{
				MyLogger.info("System throws error message when user enterd within  defined range of context : "+existingText+updatedContext);
				Assert.assertTrue(false);
			}
			

			page.fill(inputAPIGroupContext, "");
			String uniqueContextWithinRange = UniqueContextNameWithTimeStamp();
			page.fill(inputAPIGroupContext, uniqueContextWithinRange);
			MyLogger.info("Valdiating Error message is Not displayed when user enter less than 65 char");
			if(page.isVisible(contextCannotExceedErrMsg))
			{
				MyLogger.info("System throws error message when user enterd within  defined range of context ");
				Assert.assertTrue(false);
				
			}
			else
			{
				MyLogger.info("System accepts context withing range "+uniqueContextWithinRange);	
			}
			
			clickElement(saveNewGroupButton);	
			CheckPageisLoading();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.click(closeAPIGroupPage);
			MyLogger.info("Group Context is Verified within a range of 1 to 52 chars succesfully");
		}
	
		catch(Exception e)
		{
			MyLogger.info("Exception while updating the Group Context "+e);
		}
		

	}	
	
	public String checkContextIsNotSameAsGroupName(String groupName) throws Exception {

		String toastMessageAfterCreationOfGroup = null;
		String tempcontextAlreadExistErrorMsg = null;
			try
			{
			tempcontextAlreadExistErrorMsg = contextAlreadExistErrorMsg.replace("<GROUPNAME>", groupName);
			System.out.println("updated xpath "+tempcontextAlreadExistErrorMsg);
			
			MyLogger.info("Updating the Group Context");
			if(page.locator(closeAPIGroupPage).isVisible())
			{page.locator(closeAPIGroupPage).click();}
			clickElement(apiGroupsButton);
			page.fill(findAPIGroupInAPIGroupPage, "");
			page.fill(findAPIGroupInAPIGroupPage,groupName );
			String targetGroupName = clickOnGroupName.replace("<GROUPNAME>", groupName);
			page.dblclick(targetGroupName);
			
			MyLogger.info("Valdiating Error message is displayed when context is same as Group Name");
			page.fill(inputAPIGroupContext, "");
			page.fill(inputAPIGroupContext, groupName);
			
			if(page.isVisible(tempcontextAlreadExistErrorMsg))
			{}
			else
			{}
			
			
			
		if(page.isVisible(contextCannotExceedErrMsg))
			{
			System.out.println("true");
			MyLogger.info("System throws error message when user enterd within  defined range of context : "+groupName);
			Assert.assertTrue(false);
			}
		else
			{				
			System.out.println("false");
			MyLogger.info("System accepts chars within defined range "+groupName);
			}
		
			clickElement(saveNewGroupButton);	
			CheckPageisLoading();
			page.waitForLoadState(LoadState.NETWORKIDLE);
			page.click(closeAPIGroupPage);
			MyLogger.info("Group Context is Verified within a range of 1 to 52 chars succesfully");
				
				
			

		} catch (Exception e) {
			MyLogger.info("exception occcured in the createAPIGroup " + e);
			throw e;
		}
		return toastMessageAfterCreationOfGroup;

	}
}

