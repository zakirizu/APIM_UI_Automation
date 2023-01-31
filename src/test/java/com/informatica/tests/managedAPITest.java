package com.informatica.tests;



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.AnalyticsPage;
import com.informatica.pages.LoginPage;
import com.informatica.pages.managedAPIPage;
import com.informatica.web.util.Constants;
import com.informatica.web.util.MyLogger;


public class managedAPITest extends BaseTest {



	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	managedAPIPage manageapipage = null;
	AnalyticsPage analyticsPage = null;
	String CopiedURL;


	@BeforeTest
	public void init() throws Exception {
		MyLogger.startLogger("managedAPITest");

		try {
			
			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			analyticsPage = new AnalyticsPage(page);
		//	apimpage.VerifyErrorOnLoad();
		//	apimpage.selectFullPage();
		// apimpage.clickOnApiInGrid(Constants.APIName, Constants.Protocol);
			manageapipage = new managedAPIPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	@Test(dataProvider = "getData",priority = 0)
	public void ManagedAPINameToolTip(Object data) {

		loginfo.log(Status.PASS, "Getting API Tool tip from Name Field");
		MyLogger.info("================= ManagedAPINameToolTipValdiations : START ================== ");
		try {
	
			// apimpage.clickOnAPIRegistry();
			String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			loginfo.log(Status.PASS, "Clicking on Managed API");
			apimpage.FindAPi(managedAPIinExcel);
			apimpage.clickOnApiInGrid(managedAPIinExcel, "REST");
			String ApiTooltip = manageapipage.APINameToolTip();
			loginfo.log(Status.INFO, "Actual Tooltip :" +ApiTooltip);
			loginfo.log(Status.INFO, "Expected Tooltip :" +Constants.MANAGED_API_NAME_HOVER);
			// System.out.println("Copied URL in calling method :" + ApiTooltip);
			// Assert.assertTrue((CopiedURL != null));
			Assert.assertEquals(ApiTooltip, Constants.MANAGED_API_NAME_HOVER);
		} catch (Exception e) {
			MyLogger.error("Exception while validating ManagedAPINameToolTip : " + e);
			loginfo.log(Status.FAIL, "validating ManagedAPINameToolTip");
			Assert.fail("Exception while validating ManagedAPINameToolTip");
		}
		MyLogger.info("================= ManagedAPINameToolTiptusValdiations : END ================== ");

	}

	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates copying URL of Anonymous Managed API in API Manager
	 * @author abehera
	 * @date_created  30-Jun-2022
	 * @JIRA_TCs_Associated APIM-1476
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */

	@Test(dataProvider = "getData",priority = 1)
	public void accessAnonManagedAPI(Object data) {

		loginfo.log(Status.PASS, "Getting Anonymous API URL");
		MyLogger.info("================= AccessAnonManagedAPiValdiations : START ================== ");
		try {
			
			apimpage.clickOnAPIRegistry();
			String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			loginfo.log(Status.PASS, "Clicking on Managed API");
			apimpage.FindAPi(managedAPIinExcel);
			apimpage.clickOnApiInGrid(managedAPIinExcel, "REST");
			
			Boolean flag = manageapipage.CopiedURLLink();
			Assert.assertTrue(flag);
		
			/*
			 * System.out.println("First Hit :"); System.out.println("First Hit Status :" +
			 * manageapipage.SendAPiRequest(playwright, CopiedURL, adminUsername,
			 * adminPassword)); System.out.println("Second Hit :");
			 * System.out.println("Second Hit Status :" +
			 * manageapipage.SendAPiRequest(playwright, CopiedURL, adminUsername,
			 * adminPassword)); Assert.assertEquals(manageapipage.SendAPiRequest(playwright,
			 * CopiedURL, adminUsername, adminPassword), 200);
			 */
			apimpage.closeOpenedAPI(managedAPIinExcel);
			
		} catch (Exception e) {
			MyLogger.error("Exception while validating AccessAnonManagedAPi : " + e);
			loginfo.log(Status.FAIL, "validating AccessAnonManagedAPi");
			Assert.fail("Exception while validating AccessAnonManagedAPi");
		}
		MyLogger.info("================= AccessAnonManagedAPiValdiations : END ================== ");

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates copying URL of Basic Managed API in API Manager
	 * @author abehera
	 * @date_created  30-Jun-2022
	 * @JIRA_TCs_Associated APIM-1477
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */

	@Test(dataProvider = "getData",priority = 2)
	public void accessBasicManagedAPI(Object data) {

		loginfo.log(Status.PASS, "Getting Basic API URL");
		MyLogger.info("================= AccessBasicManagedAPIValdiations : START ================== ");
		try {
			
			apimpage.clickOnAPIRegistry();
			String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			loginfo.log(Status.PASS, "Clicking on Managed API");
			apimpage.FindAPi(managedAPIinExcel);
			apimpage.clickOnApiInGrid(managedAPIinExcel, "REST");
			
			Boolean flag = manageapipage.CopiedURLLink();
			Assert.assertTrue(flag);
			
			//Have not implemented running the API part of UI automation
			apimpage.closeOpenedAPI(managedAPIinExcel);
		} catch (Exception e) {
			MyLogger.error("Exception while validating AccessBasicManagedAPI : " + e);
			loginfo.log(Status.FAIL, "validating AccessBasicManagedAPI");
			Assert.fail("Exception while validating AccessBasicManagedAPI");
		}
		MyLogger.info("================= AccessBasicManagedAPIValdiations : END ================== ");

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates running WSDL URL of Anonymous Managed API in API Manager
	 * @author abehera
	 * @date_created  30-Jun-2022
	 * @JIRA_TCs_Associated APIM-1494
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	
	@Test(dataProvider = "getData",priority = 3)
	public void validateWSDLURL(Object data) {

		loginfo.log(Status.PASS,"Getting WSDL URL");
		MyLogger.info("================= validateWSDLURL : START ================== ");
		try {
			
			apimpage.clickOnAPIRegistry();
			String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			System.out.println("SOAP Managed API name from Excel Additional Details:" + managedAPIinExcel);
			
			loginfo.log(Status.PASS, "Clicking on Managed API");
			apimpage.FindAPi(managedAPIinExcel);
			apimpage.clickOnApiInGrid(managedAPIinExcel, "SOAP");
			String wsdlURL = manageapipage.copyWSDLURL();
			String wsdlBody = manageapipage.runAnonWSDLURL(wsdlURL);
			loginfo.log(Status.INFO,"Checking the " + managedAPIinExcel + " present in  Response Body");

			Assert.assertTrue(wsdlBody.contains("soapAction=\"" + managedAPIinExcel + "\""));
			
			loginfo.log(Status.PASS,"Able to Execute Anonymous API WSDL URL");
		
		} catch (Exception e) {
			MyLogger.error("Exception while validating WSDL URL : " + e);
			loginfo.log(Status.FAIL, "validating WSDL URL");
			Assert.fail("Exception while validating WSDL URL");
		}

		MyLogger.info("================= validateWSDLURL : END ================== ");

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates the enabling of privacy settings and selecting Warning for Email and verifying the http status code(200) and description(Warning) in Analytics Event log
	 * It takes APIName(CAI Process Name) and the Protocol(REST/SOAP) and the API Version as parameters from the data provider
	 * @author mbalaji
	 * @date_created  06-Jul-2022
	 * @JIRA_TCs_Associated APIM-1485
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	@Test(dataProvider = "getData",priority = 4)
	public void ClickOnEnablePrivacyPolicyWarning(Object data) {

		
		MyLogger.info("================= ClickOnEnablePrivacyPolicyWarningValidation : START ================== ");
		apimpage.clickOnAPIRegistry();
		try {
			int activeRecordStatusCount = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"),getAdditionalDetails(data, "Protocol"));
			if(activeRecordStatusCount == 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				apimpage.ReloadPage();

			}
			else {
			//	apimpage.ReloadPage();
			}
				
				apimpage.CreateManagedAPI(getAdditionalDetails(data, "APIName"));
				Assert.assertTrue(true);
				apimpage.EditManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"));
				manageapipage.CheckEnablePrivacyPolicy("Warning");
				System.out.println("Checked Enable Privacy Policy Checkbox and email privacy settings set to warning");
				Assert.assertTrue(true);
				loginfo.log(Status.PASS, "Getting CopY URL for privacy execution");
				MyLogger.info("================= PrivacyManagedAPIWarningExecutionValdiations : START ================== ");
				CopiedURL = manageapipage.getCopiedURL();
				System.out.println("Copied Managed API URL :" + CopiedURL);
				int ResponseCode = manageapipage.SendAPiRequestAnon(playwright, CopiedURL);
				loginfo.log(Status.INFO, "First Hit Status:"+ResponseCode);
				ResponseCode = manageapipage.SendAPiRequestAnon(playwright, CopiedURL);
				loginfo.log(Status.INFO, "Second Hit Status:"+ResponseCode);
				Assert.assertEquals(ResponseCode, 200);
				apimpage.ClickonAnalytcis();
				analyticsPage.clickEventsLogTab();
				analyticsPage.clickShowLog();
				loginfo.log(Status.INFO, "Checking log in Event logs");

				List<String> HttpStatus = analyticsPage.validateTheRecordsInEventLog("Email");
				int i=0;
				while(!(HttpStatus.get(0).equalsIgnoreCase("200 OK")) || i==20 ) {

					HttpStatus = analyticsPage.validateTheRecordsInEventLog("Email");
					i++;
				}
				loginfo.log(Status.INFO, "Actual status code and Message:"+HttpStatus.get(0) +" " +HttpStatus.get(1));
				Assert.assertEquals(HttpStatus.get(0), "200 OK");
				Assert.assertEquals(HttpStatus.get(1), Constants.WARNNG_MESSAGE);
				apimpage.clickOnAPIRegistry();
				apimpage.ReloadPage();
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				apimpage.ReloadPage();
				loginfo.log(Status.PASS, "Clicked on Enable Privacy policy checkbox and validated Warning privacy setting");
			
		} catch (Exception e) {
			MyLogger.error("Exception while clicking ClickOnEnablePrivacyPolicyWarningValidation : " + e);
			loginfo.log(Status.FAIL, "clicking ClickOnEnablePrivacyPolicyWarningValidation");
			Assert.fail("Exception while clicking ClickOnEnablePrivacyPolicyWarningValidation");
		}
		MyLogger.info("================= ClickOnEnablePrivacyPolicyWarningValidation : END ================== ");

	}

	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates the enabling of privacy settings and selecting Privacy for Email and verifying the http status code(403) and description(Blocked) in Analytics Event log
	 * It takes APIName(CAI Process Name) and the Protocol(REST/SOAP) and the API Version as parameters from the data provider
	 * @author mbalaji
	 * @date_created  06-Jul-2022
	 * @JIRA_TCs_Associated APIM-1486
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	@Test(dataProvider = "getData",priority = 5)
	public void ClickOnEnablePrivacyPolicyBlock(Object data) {

		apimpage.clickOnAPIRegistry();
		MyLogger.info("================= ClickOnEnablePrivacyPolicyBlockValidation : START ================== ");
		try {

			int activeRecordStatusCount = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"),getAdditionalDetails(data, "Protocol"));
			if(activeRecordStatusCount == 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				apimpage.ReloadPage();

			}
			else {
			//	apimpage.ReloadPage();
			}
			
				apimpage.CreateManagedAPI(getAdditionalDetails(data, "APIName"));
				Assert.assertTrue(true);
				apimpage.EditManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"));
				manageapipage.CheckEnablePrivacyPolicy("Block");
				System.out.println("Checked Enable Privacy Policy Checkbox and email privacy settings set to warning");
				Assert.assertTrue(true);
				loginfo.log(Status.PASS, "Getting CopY URL for privacy execution");
				MyLogger.info("================= PrivacyManagedAPIBlockExecutionValdiations : START ================== ");
				CopiedURL = manageapipage.getCopiedURL();
				System.out.println("Copied Managed API URL :" + CopiedURL);
				
				int ResponseCode = manageapipage.SendAPiRequestAnon(playwright, CopiedURL);
				loginfo.log(Status.INFO, "First Hit Status:"+ResponseCode);
				ResponseCode = manageapipage.SendAPiRequestAnon(playwright, CopiedURL);
				loginfo.log(Status.INFO, "Second Hit Status:"+ResponseCode);
				
				Assert.assertEquals(ResponseCode, 403);
				apimpage.ClickonAnalytcis();
				analyticsPage.clickEventsLogTab();
				analyticsPage.clickShowLog();
				loginfo.log(Status.INFO, "Checking log in Event logs");
				List<String> HttpStatus = analyticsPage.validateTheRecordsInEventLog("Email");
				int i=0;
				while(!(HttpStatus.get(0).equalsIgnoreCase("403 Forbidden")) || i==20) {

					HttpStatus = analyticsPage.validateTheRecordsInEventLog("Email");
					i++;
				}	
				
				loginfo.log(Status.INFO, "Actual status code and Message:"+HttpStatus.get(0) +" " +HttpStatus.get(1));

				Assert.assertEquals(HttpStatus.get(0), "403 Forbidden");
				Assert.assertEquals(HttpStatus.get(1), Constants.BLOCK_MESSAGE);
				apimpage.clickOnAPIRegistry();
				apimpage.ReloadPage();
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				loginfo.log(Status.PASS, "Clicked on Enable Privacy policy checkbox and validated Block privacy setting");

		} catch (Exception e) {
			MyLogger.error("Exception while clicking ClickOnEnablePrivacyPolicyBlockValidation : " + e);
			loginfo.log(Status.FAIL, "clicking ClickOnEnablePrivacyPolicyBlockValidation");
			Assert.fail("Exception while clicking ClickOnEnablePrivacyPolicyBlockValidation");
		}
		MyLogger.info("================= ClickOnEnablePrivacyPolicyBlockValidation : END ================== ");

	}

}
