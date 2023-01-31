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
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.Constants;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.MyLogger;
import com.informatica.web.util.ReportsTestValidator;


public class APIMRegisteryTest extends BaseTest {

	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;


	@BeforeTest
	public void init() throws Exception {
		MyLogger.startLogger("APIMRegisteryTest");

		try {

			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			// apimpage.VerifyErrorOnLoad();
			// apimpage.selectFullPage();

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	@Test(priority = 1)
	public void SearchActiveStatus() {

		loginfo.log(Status.PASS, "Searching for Active Status");
		MyLogger.info("================= SearchActiveStatusValdiations : START ================== ");
		try {
			
			apimpage.VerifyPageCompleteLoad();
			apimpage.selectFullPage();
			Assert.assertTrue(apimpage.SearchActiveStatus() > 0);
			loginfo.log(Status.PASS, "Active Status Records found");
		} catch (Exception e) {
			MyLogger.error("Exception while validating SearchActiveStatus : " + e);
			loginfo.log(Status.FAIL, "validating SearchActiveStatusValdiations");
			Assert.fail("Exception while validating SearchActiveStatus");
		}
		MyLogger.info("================= SearchActiveStatusValdiations : END ================== ");

	}

	@Test(priority = 2)
	public void SearchInActiveStatus() {

		loginfo.log(Status.PASS, "Searching for IN-Active Status");
		MyLogger.info("================= SearchINActiveStatusValdiations : START ================== ");
		try {
			
			Assert.assertTrue(apimpage.SearchInActiveStatus() > 0);
		} catch (Exception e) {
			MyLogger.error("Exception while validating SearchINActiveStatus : " + e);
			loginfo.log(Status.FAIL, "validating SearchINActiveStatusValdiations");
			Assert.fail("Exception while validating SearchINActiveStatus");
		}
		MyLogger.info("================= SearchINActiveStatusValdiations : END ================== ");

	}

	@Test(priority = 3)
	public void Search_ActiveServiceNot_AvailiableStatus() {

		loginfo.log(Status.PASS, "Searching for Active Not Availiable Status");
		MyLogger.info("================= SearchINActiveStatusValdiations : START ================== ");
		try {
		
			Assert.assertTrue(apimpage.Search_ActiveServicenotAvailiable_Status() > 0);
		} catch (Exception e) {
			MyLogger.error("Exception while validating SearchINActiveStatus : " + e);
			loginfo.log(Status.FAIL, "validating SearchINActiveStatusValdiations");
			Assert.fail("Exception while validating SearchINActiveStatus");
		}
		MyLogger.info("================= SearchINActiveStatusValdiations : END ================== ");

	}

	@Test(priority = 4)
	public void Search_InActiveServiceNot_AvailiableStatus() {

		loginfo.log(Status.PASS, "Searching for In Active Not Availiable Status");
		MyLogger.info("================= SearchINActiveStatusNotAvailiableValdiations : START ================== ");
		try {
			
			Assert.assertTrue(apimpage.Search_InActiveServicenotAvailiable_Status() > 0);
		} catch (Exception e) {
			MyLogger.error("Exception while validating SearchINActiveStatus : " + e);
			loginfo.log(Status.FAIL, "validating SearchINActiveStatus");
			Assert.fail("Exception while validating SearchINActiveStatus");
		}
		MyLogger.info("================= SearchINActiveStatusNotAvailiableValdiations : END ================== ");

	}

	@Test(priority = 5)
	public void ValidateHeadersAPiRegistry() {

		loginfo.log(Status.PASS, "Validating the API Registery column Names");
		MyLogger.info("================= ValidateHeadersAPiRegistry : START ================== ");
		try {
			
			apimpage.getAPIRegisteryHeaders();

			List<String> actualReportHeaders = apimpage.getAPIRegisteryHeaders();
			List<String> expectedReportHeaders = ReportsTestValidator.getExpectedHeadersAPIMRegistery();
			Assert.assertEquals(actualReportHeaders.equals(expectedReportHeaders), true, " Headers are not same");

		} catch (Exception e) {
			MyLogger.error("Exception while validating HeadersAPiRegistry : " + e);
			loginfo.log(Status.FAIL, "validating HeadersAPiRegistry");
			Assert.fail("Exception while validating HeadersAPiRegistry");
		}
		MyLogger.info("================= ValidateHeadersAPiRegistry : END ================== ");

	}

	/*
	 * @Test(priority = 6) public void ValidatekebabMenuOptions() {
	 * 
	 * loginfo.log(Status.PASS, "Getting Kebab Menu Options"); MyLogger.
	 * info("================= ValidatekebabMenuOptions : START ================== "
	 * ); try {
	 * 
	 * apimpage.ClickonKebabMenu();
	 * 
	 * Assert.assertTrue(true);
	 * 
	 * } catch (Exception e) {
	 * MyLogger.error("Exception while validating kebabMenuOptions : " + e);
	 * loginfo.log(Status.FAIL, "validating kebabMenuOptions");
	 * Assert.fail("Exception while validating kebabMenuOptions"); } MyLogger.
	 * info("================= ValidatekebabMenuOptions : END ================== ");
	 * 
	 * }
	 * 
	 * @Test(priority = 7) public void ValidateActivateAndDeactivate() {
	 * 
	 * loginfo.log(Status.PASS, "Valdiating the Activate Action"); MyLogger.
	 * info("================= ValidateActivateAndDeactivate : START ================== "
	 * ); try {
	 * 
	 * 
	 * apimpage.ClickonActiavteDeactivate();
	 * 
	 * Assert.assertTrue(true);
	 * 
	 * } catch (Exception e) {
	 * MyLogger.error("Exception while validating Activate and Deactivate : " + e);
	 * loginfo.log(Status.FAIL, "validating ActivateAndDeactivate");
	 * Assert.fail("Exception while validating ActivateAndDeactivate"); } MyLogger.
	 * info("================= ValidateActivateAndDeactivate : END ================== "
	 * );
	 * 
	 * }
	 */
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates the creation of Managed API, It takes APIName(CAI Process Name) and Protocol and Version as parameters from data provider
	 * @author mbalaji
	 * @date_created  06-Jul-2022
	 * @JIRA_TCs_Associated APIM-1506
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	@Test(dataProvider = "getData", priority = 8)
	public void CreateManagagedAPI(Object data) {

		loginfo.log(Status.PASS, "Managed API Creation");
		MyLogger.info("================= CreateManagagedAPIValidation : START ================== ");
		try {
			MyLogger.info("Check if the managed API exists");
			int activeRecordStatusCount = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"),getAdditionalDetails(data, "Protocol"));
			if(activeRecordStatusCount == 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				 apimpage.ReloadPage();
			}	
			else {
			// apimpage.ReloadPage();
			}
			apimpage.CreateManagedAPI(getAdditionalDetails(data, "APIName"));
			
			Assert.assertTrue(true);
		//	activeRecordStatusCount = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"));
			apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
			apimpage.ReloadPage();
			loginfo.log(Status.PASS,"Creation of Managed API CreateManagagedAPIValidation is successful");
			
		} catch (Exception e) {
			MyLogger.error("Exception while creating CreateManagagedAPIValidation : " + e);
			loginfo.log(Status.FAIL, "Creation of managed API CreateManagagedAPIValidation failed");
			Assert.fail("Exception while creating CreateManagagedAPIValidation");
		}
		MyLogger.info("================= CreateManagagedAPIValidation : END ================== ");

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates the creation of new Managed API and also checks create new version button should be disabled after creations of 3 versions.
	 *				It takes APIName(CAI Process Name), APINameV2(CAI Process name for new version selection) and Protocol, protocol1 for the managed API's protocols and Version,Version1,Version2 for versions as parameters from data provider
	 * @author mbalaji
	 * @date_created  06-Jul-2022
	 * @JIRA_TCs_Associated APIM-1507
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	@Test(dataProvider = "getData", priority = 9)
	public void CreateNewVersionAPI(Object data) {

		loginfo.log(Status.PASS, "New version creation of managed API");
		MyLogger.info("================= CreateNewVersionAPI : START ================== ");
		try {
			MyLogger.info("Check if the managed API exists in CreateNewVersionAPI");
			int activeRecordStatusCount1 = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"),getAdditionalDetails(data, "Protocol"));
			int activeRecordStatusCount2= apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APINameV2"),getAdditionalDetails(data, "Version2"),getAdditionalDetails(data, "Protocol"));
			int activeRecordStatusCount3= apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version1"),getAdditionalDetails(data, "Protocol2"));


			if(activeRecordStatusCount3 >= 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol2"),getAdditionalDetails(data, "Version1"));
				apimpage.ReloadPage();

		
			}
			if(activeRecordStatusCount2 >= 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APINameV2"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version2"));
				apimpage.ReloadPage();

		
			}
			if(activeRecordStatusCount1 >= 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				apimpage.ReloadPage();

			
			}
	
			
	

			apimpage.CreateManagedAPI(getAdditionalDetails(data, "APIName"));
			
			loginfo.log(Status.INFO, "Created managed API");
			
	//		Assert.assertTrue(true);
			apimpage.CreateNewVersion(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version1"),getAdditionalDetails(data, "APIName"));
			loginfo.log(Status.INFO, "Created managed API Version 1");
			apimpage.CreateNewVersion(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version2"),getAdditionalDetails(data, "APINameV2"));
			loginfo.log(Status.INFO, "Created managed API Version 2");
			boolean CreateVersionStatus = apimpage.CreateNewVersionStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"));
			loginfo.log(Status.INFO, "Create Version Status (post creation of 2 Versions it should be Disabled:False):" +CreateVersionStatus);

			Assert.assertEquals(false, CreateVersionStatus);
			apimpage.ReloadPage();
			
		
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol2"),getAdditionalDetails(data, "Version1"));
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APINameV2"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version2"));
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));	
			
			

			loginfo.log(Status.PASS,"Creation of three new versions of managed API's CreateNewVersionAPI is successful");
			
		} catch (Exception e) {
			MyLogger.error("Exception while creating CreateNewVersionAPI : " + e);
			loginfo.log(Status.FAIL, "creating CreateNewVersionAPI");
			Assert.fail("Exception while creating CreateNewVersionAPI");
		}
		MyLogger.info("================= CreateNewVersionAPI : END ================== ");
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates the deletion of Managed API It takes APIName(CAI Process Name) and Protocol and Version as parameters from data provider
	 * @author mbalaji
	 * @throws IOException 
	 * @throws UnsupportedFlavorException 
	 * @throws InterruptedException 
	 * @date_created  06-Jul-2022
	 * @JIRA_TCs_Associated APIM-1638
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	@Test(dataProvider = "getData", priority = 10)
	public void DeleteManagagedAPI(Object data) throws InterruptedException, IOException {


		loginfo.log(Status.PASS, "Delete API Creation");
		MyLogger.info("================= DeleteManagagedAPIValidation : START ================== ");
		try {
			MyLogger.info("Check if the managed API exists in DeleteManagagedAPI");
			int activeRecordStatusCount = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"),getAdditionalDetails(data, "Protocol"));
			if(activeRecordStatusCount == 1) {
				apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
				apimpage.ReloadPage();
			}
			else {
			// apimpage.ReloadPage();
			apimpage.CreateManagedAPI(getAdditionalDetails(data, "APIName"));
			activeRecordStatusCount = apimpage.CheckManagedAPIStatus(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Version"),getAdditionalDetails(data, "Protocol"));
			loginfo.log(Status.INFO,"Manged API Creation status for Deletion :"+activeRecordStatusCount);

			Assert.assertEquals(1, activeRecordStatusCount);
			
			apimpage.DeleteManagedAPI(getAdditionalDetails(data, "APIName"),getAdditionalDetails(data, "Protocol"),getAdditionalDetails(data, "Version"));
	

			loginfo.log(Status.PASS,"Deletion of managed API's DeleteManagagedAPI is successful");
			
			}
		} catch (Exception e) {
			MyLogger.error("Exception while deleting DeleteManagagedAPIValidation : " + e);
			loginfo.log(Status.FAIL, "deleting DeleteManagagedAPIValidation");
			Assert.fail("Exception while deleting DeleteManagagedAPIValidation");
		}
	
		MyLogger.info("================= DeleteManagagedAPIValidation : END ================== ");

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates the "Managed APIs" dropdown in API Registry of API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @JIRA_TCs_Associated APIM-1475
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	
	@Test(priority = 11)
	public void validateManagedAPIDropdown() {
		

		
		loginfo.log(Status.PASS, "Valdiating the Managed API dropdown Action");
		MyLogger.info("================= validateManagedAPIDropdown : START ================== ");
		try {
			
			List<String> actualDropdownList = apimpage.getServicesDropdownList();
			List<String> expectedDropdownList = ReportsTestValidator.getExpectedAPIRegistryDropdownList();
			loginfo.log(Status.INFO, "actualDropdownList:"+actualDropdownList);
			loginfo.log(Status.PASS, "expectedDropdownList:"+expectedDropdownList);

			Assert.assertEquals(actualDropdownList.equals(expectedDropdownList), true, " Dropdown options are not same");
			
			apimpage.selectAPIRegistryDropdownOption(Constants.MANAGED_APIS_DROPDOWN);
			Boolean checkmanagedapistatus=apimpage.checkStatusNotBlank();
			loginfo.log(Status.INFO, "Managed APIS have Proper Status:"+checkmanagedapistatus);

			Assert.assertTrue(checkmanagedapistatus);
			loginfo.log(Status.PASS,"Managed API dropdown displays only Managed APIs");

		} catch (Exception e) {
			MyLogger.error("Exception while validating managed API dropdown : " + e);
			loginfo.log(Status.FAIL, "validating managed API dropdown");
			Assert.fail("Exception while validating managed API dropdown");
		}
		MyLogger.info("================= validateManagedAPIDropdown : END ================== ");

	}


}
