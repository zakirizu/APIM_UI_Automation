/****************************************************************************
@Description: This Test class is created to track the test methods written on API Portal Registry page
@Author:@nvaddadi
@Date Created:29-06-2022
@JIRA TCs associated: APIM-1501, APIM-1502, APIM-1503
@Coverage: UI,Sanity,Regression
*********************************************************************************/


package com.informatica.tests;


import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIPage;
import com.informatica.pages.APIPortalHomePage;
import com.informatica.pages.APIPortalRegistryPage;
import com.informatica.pages.LoginPage;

import com.informatica.web.util.MyLogger;

public class APIPortalRegistryTest extends BaseTest {

	LoginPage loginPage = null;
	APIPortalHomePage apiPortalPage=null;
	APIPortalRegistryPage apiRegistryPage = null;
	APIPage apiPage = null;
	HashMap<String, HashMap<String, String>> testData = null;



	@BeforeTest
	public void init() throws Exception {
		MyLogger.startLogger("APIPortalRegistryTest");

		try {

			page = getPage(this.getClass().getSimpleName());

			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.clickOnApiChiklet();
			apiPortalPage=new APIPortalHomePage(page);
			apiRegistryPage = new APIPortalRegistryPage(page);
			apiPage = new APIPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to API  : " + e);
			throw e;
		}
	}
	

	/**
	 * @Description - This test method validates the swagger tab functionality after clicking on the api in api registry tab
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */

	@Test(dataProvider = "getData",priority = 1)
	public void ValidateSwaggerTab(Object data) throws Exception {

		MyLogger.info("================= ValidateSwaggerTab : START ================== ");
		try {						
			apiPortalPage.clickApiRegistryTab();
			apiRegistryPage.API_FindAPi(getAdditionalDetails(data, "APIName"));
			apiRegistryPage.clickOnApiInGrid(getAdditionalDetails(data, "APIName"), getAdditionalDetails(data, "Protocol"));
			loginfo.pass("Click on Swagger Tab");
			apiRegistryPage.clickOnSwaggerTab();
			apiPage.executeApi();
			loginfo.pass("Executing the API");
			loginfo.info("Verifying the response status code");
			Assert.assertEquals(apiPage.getResponseCode(), "200",
					"actual status code is populated as: " + apiPage.getResponseCode());
			loginfo.info("Verifying the response message");
			Assert.assertTrue(apiPage.getResponse().toLowerCase().contains("testing"),
					"Actual response is not populated correctly");

		} catch (Exception e) {
			MyLogger.error("Exception while validating swagger tab : " + e);
			Assert.fail();
		}
		MyLogger.info("================= ValidateSwaggerTab : END ================== ");
	}

	/**
	 * @Description - This test method validates the running managed api anonymously
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	@Test(dataProvider = "getData",priority = 2)
	public void run_anonymous_Managed_API(Object data) throws Exception {
		MyLogger.info("================= run_anonymous_Managed_API : START ================== ");

		try {		
		apiPortalPage.clickApiRegistryTab();
		//apiRegistryPage.clickOnApiRegistry();
		apiRegistryPage.API_FindAPi(getAdditionalDetails(data, "APIName"));
		apiRegistryPage.clickOnApiInGrid(getAdditionalDetails(data, "APIName"), getAdditionalDetails(data, "Protocol"));
		String URL = apiPage.copyApiUrl();
		loginfo.info("Copied URL is: " + URL);
		String ActualURL = apiPage.getApiUrl();
		loginfo.info("Actual API URL is: " + URL);
		Assert.assertTrue(URL.equals(ActualURL), "Copied URL and Actual URL are different");
		loginfo.pass("Both actual and copied URL are same ");
		}
		catch (Exception e) {
			MyLogger.error("Exception while validating run_anonymous_Managed_API : " + e);
			Assert.fail();
		}

		MyLogger.info("================= run_anonymous_Managed_API : END ================== ");
	}

	/**
	 * @Description - This test method validates the running managed api with basic auth.
	 * @date created 30-06-2022
	 * @author nvaddadi 
	 */
	@Test(dataProvider = "getData",priority = 4)
	public void run_basic_Managed_API(Object data) throws Exception {
		MyLogger.info("================= run_basic_Managed_API : START ================== ");
		
		try { 	
		apiPortalPage.clickApiRegistryTab();
		//apiRegistryPage.clickOnApiRegistry();
		apiRegistryPage.API_FindAPi(getAdditionalDetails(data, "APIName"));
		apiRegistryPage.clickOnApiInGrid(getAdditionalDetails(data, "APIName"), getAdditionalDetails(data, "Protocol"));
		String URL = apiPage.copyApiUrl();
		loginfo.info("Copied URL is: " + URL);
		String ActualURL = apiPage.getApiUrl();
		loginfo.info("Actual API URL is: " + URL);
		Assert.assertTrue(URL.equals(ActualURL), "Copied URL and Actual URL are different");
		loginfo.pass("Both actual and copied URL are same ");
		}
		catch (Exception e) {
			MyLogger.error("Exception while validating run_basic_Managed_API : " + e);
			Assert.fail();
		}
		MyLogger.info("================= run_basic_Managed_API : END ================== ");
	}

	@Test(dataProvider = "getData", priority = 3)
    public void validateSwaggerURLinAPIPortal(Object data) {
        loginfo.log(Status.PASS,"Validating swagger URL in API Portal");
        MyLogger.info("================= validateSwaggerURLinAPIPortal: START ================== ");
        try {

            String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
            System.out.println("Anonymous API name from Excel Additional Details:" + managedAPIinExcel);

            //loginfo.log(Status.PASS, "Clicking on Managed API");
            //apimpage.clickManagedAPIFromExcel(managedAPIinExcel);
            String swaggerURL = apiPage.copySwaggerURL();
            String swaggerBody = apiPage.runAnonSwaggerURL(swaggerURL);

            Assert.assertTrue(swaggerBody.contains("\"title\": \"" + managedAPIinExcel + "\""));

            loginfo.log(Status.PASS,"Able to Execute Anonymous API Swagger URL in API Portal");

        } catch (Exception e) {
            MyLogger.error("Exception while validating Anonymous API Swagger URL in API Portal : " + e);
            loginfo.log(Status.FAIL, "validating Anonymous API Swagger URL in API Portal");
            Assert.fail("Exception while validating Anonymous API Swagger URL in API Portal");
        }
        MyLogger.info("================= validateSwaggerURLinAPIPortal: END ================== ");
    }

	
	
}
