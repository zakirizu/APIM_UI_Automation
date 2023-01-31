package com.informatica.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIGroupsPage;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.MyLogger;
import com.informatica.web.util.ReportsTestValidator;
import com.informatica.web.util.LoggerUtil;

public class APIGroupsTest extends BaseTest {

	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	APIGroupsPage apigrp = null;

	@BeforeTest
	public void init() throws Exception {

		MyLogger.startLogger("APIGroupsTest");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			// apimpage.VerifyErrorOnLoad();
			apimpage.ClickonAPIGroups();
			apigrp = new APIGroupsPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	@Test(priority = 1)
	public void validateUserNavigatedtoAPiGroups() {

		loginfo.log(Status.PASS, "Cliking on API Group Tab Page");
		MyLogger.info("================= validateUserNavigatedtoAPiGroups : START ================== ");
		try {

			Assert.assertEquals(apigrp.getPageTitle(), "API Groups");
			loginfo.log(Status.PASS, "Able to Navigate to API Groups Page");
		} catch (Exception e) {
			MyLogger.error("Exception while validating SearchActiveStatus : " + e);
			loginfo.log(Status.FAIL, "validating SearchActiveStatus");
			Assert.fail("Exception while validating SearchActiveStatus");
		}

		MyLogger.info("================= validateUserNavigatedtoAPiGroups : END ================== ");

	}

	@Test(priority = 2)
	public void ValidateHeadersAPiGroups() {

		loginfo.log(Status.PASS, "Cliking on Login Page");
		MyLogger.info("================= ValidateHeadersAPiGroups : START ================== ");
		try {

			loginfo.log(Status.PASS, "Getting Report HEaders from Application");
			List<String> actualReportHeaders = apigrp.getAPIGroupHeaders();
			loginfo.log(Status.PASS, "Getting Report HEaders from Expected Value");
			List<String> expectedReportHeaders = ReportsTestValidator.getExpectedHeadersAPIMGroups();
			Assert.assertEquals(actualReportHeaders.equals(expectedReportHeaders), true, " Headers are not same");

		} catch (Exception e) {
			MyLogger.error("Exception while validating HeadersAPiGroupHeaders : " + e);
			loginfo.log(Status.FAIL, "validating ValidateHeadersAPiGroups");
			Assert.fail("Exception while validating HeadersAPiGroupHeaders");
		}

		MyLogger.info("================= ValidateHeadersAPiGroups : END ================== ");

	}

	/**
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @description :This methodValidates the creation of Group 1) checks if the
	 *              group already exist, if it exist deletes it. 2) created new
	 *              group withContext by assigning managedAPIs to it 3) validates
	 *              the urls of the ManagedApis, if context is present in it
	 * @param data
	 */
	@Test(dataProvider = "getData", priority = 7)
	public void validateCreateAPIGroupWithContext(Object data) {
		// LoggerUtil.info("================= CreateAPIGroupWithContext : START
		// ================== ",
		// LoggerUtil.EXTENT_AUTO_LOG);
		loginfo.log(Status.INFO, "================= CreateAPIGroupWithContext : START ================== ");
		MyLogger.info("================= CreateAPIGroupWithContext : START ================== ");
		try {
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String managedAPIName = parameters.get("managedAPIName");
			String apiProtocol = parameters.get("apiProtocol");
			String apiGroupContext = parameters.get("apiGroupContextName");
			String apiGroupDescription = parameters.get("apiGroupDescription");

			MyLogger.info("Checking if Group -->" + apiGroupName + "<-- already Exist");
			loginfo.log(Status.INFO, "checking if " + apiGroupName + " the Group Already Exist");
			String checkGroup = apigrp.checkIFAPIGroupExist(apiGroupName);
			if (checkGroup != "NotFound") {
				MyLogger.info("Group Exist " + apiGroupName + "so deleting it before trying to Create");
				loginfo.log(Status.INFO, "Group Exist " + apiGroupName + "so deleting it before trying to Create");
				apigrp.removeAPIGroup(apiGroupName);
			}
			String toastMessageAfterCreationOfGroup = apigrp.createAPIGroup(apiGroupName, apiGroupContext,
					apiGroupDescription, managedAPIName, apiProtocol);
			Assert.assertEquals(toastMessageAfterCreationOfGroup,
					"The Group \"" + apiGroupName + "\" was created successfully.",
					"checking the ToastMessage of the Group Creation");
			MyLogger.info("Group Creation for " + apiGroupName + " is Completed Successfully");
			loginfo.log(Status.INFO, "Group Creation for " + apiGroupName + " is Completed Successfully");
			MyLogger.info("validating the managedAPI url in the Group " + apiGroupName);
			loginfo.log(Status.INFO, "validating the managedAPI url in the Group " + apiGroupName);
			Map<String, String> urls = apigrp.validateAPIURLInAPIGroup(apiGroupName, apiGroupContext, managedAPIName,
					apiProtocol);
			Assert.assertEquals(urls.get("managedAPIURLFromGroup"), urls.get("urlFromCopyURLButton"),
					"the MangedAPI urls are not matching ");
			String groupNameCreated = apigrp.checkIFAPIGroupExist(apiGroupName);
			Assert.assertEquals(groupNameCreated, apiGroupName);
			MyLogger.info("the Group creation is completed for Group" + apiGroupName);
			loginfo.log(Status.PASS, "Group Creation for " + apiGroupName + " is Completed Successfully");
		} catch (Exception e) {
			page.reload();
			MyLogger.error("Exception while validating CreateAPIGroupWithContext : " + e);
			loginfo.log(Status.FAIL, "Exception while validating CreateAPIGroupWithContext : " + e);
			Assert.fail("Exception while validating CreateAPIGroupWithContext");
		}
		loginfo.log(Status.INFO, "================= CreateAPIGroupWithContext : END ================== ");
		MyLogger.info("================= CreateAPIGroupWithContext : END ================== ");

	}

	/**
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @description : This methodValidates the creation of Group 1) checks if the
	 *              group already exist, if it exist deletes it. 2) created new
	 *              group with out Context by assigning managedAPIs to it 3)
	 *              validates the urls of the ManagedApis, if context is present in
	 *              it
	 * @param data
	 */
	@Test(dataProvider = "getData", priority = 8)
	public void validateCreateAPIGroupWithOutContext(Object data) {
		// LoggerUtil.info("================= CreateAPIGroupWithOutContext : START
		// ================== ",
		// LoggerUtil.EXTENT_AUTO_LOG);
		loginfo.log(Status.INFO, "================= CreateAPIGroupWithOutContext : START ================== ");
		MyLogger.info("================= CreateAPIGroupWithOutContext : START ================== ");

		try {

			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String managedAPIName = parameters.get("managedAPIName");
			String apiProtocol = parameters.get("apiProtocol");
			String apiGroupDescription = parameters.get("apiGroupDescription");

			MyLogger.info("Checking if Group -->" + apiGroupName + "<-- already Exist");
			loginfo.log(Status.INFO, "checking if " + apiGroupName + " the Group Already Exist");
			String checkGroup = apigrp.checkIFAPIGroupExist(apiGroupName);
			if (checkGroup != "NotFound") {
				MyLogger.info("Group Exist " + apiGroupName + "so deleting it before trying to Create");
				loginfo.log(Status.INFO, "Group Exist " + apiGroupName + "so deleting it before trying to Create");
				apigrp.removeAPIGroup(apiGroupName);
			}
			String toastMessageAfterCreationOfGroup = apigrp.createAPIGroup(apiGroupName, "", apiGroupDescription,
					managedAPIName, apiProtocol);
			Assert.assertEquals(toastMessageAfterCreationOfGroup,
					"The Group \"" + apiGroupName + "\" was created successfully.",
					"checking the ToastMessage of the GroupCreation");
			MyLogger.info("Group Creation for " + apiGroupName + " is Completed Successfully");
			loginfo.log(Status.INFO, "Group Creation for " + apiGroupName + " is Completed Successfully");
			MyLogger.info("validating the managedAPI url in the Group " + apiGroupName);
			loginfo.log(Status.INFO, "validating the managedAPI url in the Group " + apiGroupName);
			Map<String, String> urls = apigrp.validateAPIURLInAPIGroup(apiGroupName, "", managedAPIName, apiProtocol);
			Assert.assertEquals(urls.get("managedAPIURLFromGroup"), urls.get("urlFromCopyURLButton"),
					"the MangedAPI urls are not matching ");
			String groupNameCreated = apigrp.checkIFAPIGroupExist(apiGroupName);
			Assert.assertEquals(groupNameCreated, apiGroupName);
			MyLogger.info("the Group creation is completed for Group" + apiGroupName);
			loginfo.log(Status.PASS, "Group Creation for " + apiGroupName + " is Completed Successfully");
		} catch (Exception e) {
			page.reload();
			MyLogger.error("Exception while validating CreateAPIGroupWithOutContext : " + e);
			loginfo.log(Status.FAIL, "Exception while validating CreateAPIGroupWithOutContext : " + e);
			Assert.fail("Exception while validating CreateAPIGroupWithOutContext");
		}
		loginfo.log(Status.INFO, "================= CreateAPIGroupWithOutContext : END ================== ");
		MyLogger.info("================= CreateAPIGroupWithOutContext : END ================== ");

	}

	/**
	 * @description : This methodValidates the renaming of the existing Group
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 */
	@Test(dataProvider = "getData", priority = 9, dependsOnMethods = "validateCreateAPIGroupWithContext")
	public void validateRenameAPIGroup(Object data) {
		loginfo.log(Status.INFO, "=================  renameAPIGroup : START ================== ");
		MyLogger.info("=================  renameAPIGroup : START ================== ");

		try {

			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String newApiGroupName = parameters.get("newApiGroupName");

			String toastMessageAfterRenameOfGroup = apigrp.reNameAPIGroup(apiGroupName, newApiGroupName);
			Assert.assertEquals(toastMessageAfterRenameOfGroup,
					"The Group \"" + apiGroupName + "\" was updated successfully.",
					"Renaming of the Group " + apiGroupName + " Failed");
			MyLogger.info("checking if the updatedGroupName " + newApiGroupName + " is present in the APIGroup List ");
			String renamedGroup = apigrp.checkIFAPIGroupExist(newApiGroupName);
			MyLogger.info("the apigroup name returned is :" + renamedGroup);
			Assert.assertEquals(renamedGroup, newApiGroupName);
		} catch (Exception e) {
			page.reload();
			MyLogger.error("Exception while validating renameAPIGroup : " + e);
			Assert.fail("Exception while validating renameAPIGroup");
		}
		loginfo.log(Status.INFO, "=================  renameAPIGroup : END ================== ");
		MyLogger.info("=================  renameAPIGroup : END ================== ");

	}

	/**
	 * @description : This methodValidates the renaming of the existing Group
	 * @author sragilla
	 * @date_created 06-Jul-2022
	 * @param data
	 */
	@Test(dataProvider = "getData", priority = 10, dependsOnMethods = "validateCreateAPIGroupWithContext")
	public void validateRemoveAPIGroup(Object data) {
		loginfo.log(Status.INFO, "=================  removeAPIGroup : START ================== ");
		MyLogger.info("=================  removeAPIGroup : START ================== ");

		try {
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,
					"The Group \"" + apiGroupName + "\" was deleted successfully.",
					"Deletion of the Group " + apiGroupName + " Failed");
			String deleteGroupName = apigrp.checkIFAPIGroupExist(apiGroupName);
			MyLogger.info("the apigroup name returned is :" + apiGroupName);
			Assert.assertEquals(deleteGroupName, "NotFound");
		} catch (Exception e) {
			page.reload();
			MyLogger.error("Exception while validating removeAPIGroup : " + e);
			loginfo.log(Status.FAIL, "Exception while validating removeAPIGroup : " + e);
			Assert.fail("Exception while validating removeAPIGroup");
		}
		loginfo.log(Status.INFO, "=================  removeAPIGroup : END ================== ");
		MyLogger.info("=================  removeAPIGroup : END ================== ");

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This testcase Validates creating a group with ipfilter and
	 *              adding API to it
	 * @author pmansabd
	 * @date_created 07-Jul-2022
	 * @JIRA_TCs_Associated APIM-1509
	 * @coverage UI Sanity,PodValidation
	 *           ************************************************************************************************************************
	 */
	@Test(dataProvider = "getData", priority = 11)
	public void validateCreateAPIGroupWithipfilter(Object data) {
		MyLogger.info("================= CreateAPIGroupWithContext : START ================== ");
		try {
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String managedAPIName = parameters.get("managedAPIName");
			String apiProtocol = parameters.get("apiProtocol");
			String apiGroupDescription = parameters.get("apiGroupDescription");

			String checkGroup = apigrp.checkIFAPIGroupExist(apiGroupName);
			if (checkGroup != "NotFound") {
				MyLogger.info("Group Exist " + apiGroupName + "so deleting it before trying to Create");
				loginfo.log(Status.INFO, "Group Exist " + apiGroupName + "so deleting it before trying to Create");
				apigrp.removeAPIGroup(apiGroupName);
			}

			apigrp.createAPIGroupwithipfilter(apiGroupName, apiGroupDescription, managedAPIName, apiProtocol, "0", "0",
					"0", "0", "255", "255", "255", "255");
			MyLogger.info("the apigroup name returned is :" + apiGroupName);
			Assert.assertEquals(apiGroupName, "APIM_Automation_APIGroupipfilter");
		} catch (Exception e) {
			MyLogger.error("Exception while validating groupipfilter : " + e);
			Assert.fail("Exception while validating CreateAPIGroupWithipfilter");
		}
		MyLogger.info("================= CreateAPIGroupWithipfilter : END ================== ");

	}

	/**
	 * ************************************************************************************************************************
	 * 
	 * @description This is used to 
	 * @author pmansabd
	 * @date_created 07-Jul-2022
	 * @JIRA_TCs_Associated APIM-1510
	 * @coverage UI Sanity,PodValidation
	 *           ************************************************************************************************************************
	 */

	@Test(dataProvider = "getData")
	public void validateCreateAPIGroupWithratelimit(Object data) {
		MyLogger.info("================= CreateAPIGroupWithRateLimit : START ================== ");
		try {

			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String managedAPIName = parameters.get("managedAPIName");
			String apiProtocol = parameters.get("apiProtocol");
			String apiGroupDescription = parameters.get("apiGroupDescription");

			apigrp.createAPIGroupwithratelimit(apiGroupName, apiGroupDescription, managedAPIName, apiProtocol);
			MyLogger.info("the apigroup name returned is :" + apiGroupName);
			Assert.assertEquals(apiGroupName, "APIM_Automation_APIGroupratelimit");
		} catch (Exception e) {
			MyLogger.error("Exception while validating CreateAPIGroupWithratelimit : " + e);
			Assert.fail("Exception while validating CreateAPIGroupWithratelimit");
		}
		MyLogger.info("================= CreateAPIGroupWithRateLimit : END ================== ");

	}

}
