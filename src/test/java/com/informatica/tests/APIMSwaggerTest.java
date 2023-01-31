/**
 * ************************************************************************************************************************
 * @description This page contains all Test cases related to the Swagger functionality of Managed API in API Manager
 * @author abehera
 * @date_created  29-Jun-2022
 * @JIRA_TCs_Associated APIM-1490, APIM-1491, APIM-1492, APIM-1493
 * @coverage UI Sanity,PodValidation
 * ************************************************************************************************************************
 */

package com.informatica.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.APIMSwaggerPage;
import com.informatica.pages.LoginPage;
import com.informatica.pages.managedAPIPage;
import com.informatica.web.util.Constants;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;

public class APIMSwaggerTest extends BaseTest{

	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	APIMSwaggerPage apimSwaggerPage = null;
	managedAPIPage manageapipage = null;
	HashMap<String, HashMap<String, String>> testData = null;
	
	@BeforeTest
	public void init() throws Exception {


		MyLogger.startLogger("APIMSwaggerTest");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			// apimpage.VerifyErrorOnLoad();
			apimSwaggerPage = new APIMSwaggerPage(page);
			manageapipage = new managedAPIPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates Swagger tab functionality of Anonymous Managed API in API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @JIRA_TCs_Associated APIM-1490
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	
	@Test(dataProvider = "getData", priority = 1)
	public void validateAnonAPISwagger(Object data) {

		loginfo.log(Status.PASS,"Validating Swagger functionality of Anonymous API");
		MyLogger.info("================= validateAnonAPISwagger : START ================== ");
		try {
			
			
			/*
			 * String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			 * String expectedResponseBody = getAdditionalDetails(data, "ResponseBody");
			 */
			
			Map<String, String> parameters = getAdditionalDetails(data);
			String managedAPIinExcel = parameters.get("ManagedAPIName");
			String expectedResponseBody = parameters.get("ResponseBody");
			System.out.println("Anonymous API name from Excel Additional Details:" + managedAPIinExcel);
			
			
			apimSwaggerPage.switchToSwaggerTab();
			apimSwaggerPage.executeAPIonSwaggerTab();
			
			String StatusCode=apimSwaggerPage.getAPIExecutionResponseCode();
			loginfo.log(Status.INFO,"Status code in Swagger tab:"+StatusCode);
			Assert.assertEquals(StatusCode, "200");
			
			String RespCode=apimSwaggerPage.getAPIExecutionResponseBody();
			loginfo.log(Status.INFO,"Response code in Swagger tab:"+RespCode);
			Assert.assertEquals(RespCode, expectedResponseBody);
			
			loginfo.log(Status.PASS,"Able to Execute Anonymous API on Swagger tab");
			apimpage.closeOpenedAPI(managedAPIinExcel);
		
		} catch (Exception e) {
			MyLogger.error("Exception while validating Anonymous API Swagger tab : " + e);
			loginfo.log(Status.FAIL, "validating Anonymous API Swagger tab");
			Assert.fail("Exception while validating Anonymous API Swagger tab");
		}

		MyLogger.info("================= validateAnonAPISwagger : END ================== ");
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates Swagger tab functionality of Basic Managed API in API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @JIRA_TCs_Associated APIM-1491
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	
	@Test(dataProvider = "getData", priority = 3)
	public void validateBasicAPISwagger(Object data) {

		loginfo.log(Status.PASS,"Validating Swagger functionality of Basic API");
		MyLogger.info("================= validateBasicAPISwagger : START ================== ");
		try {
			
			//apimpage.ClickonAPIRegistry();
			/*String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			String expectedResponseBody = getAdditionalDetails(data, "ResponseBody");*/
			
			
			Map<String, String> parameters = getAdditionalDetails(data);
			String managedAPIinExcel = parameters.get("ManagedAPIName");
			String expectedResponseBody = parameters.get("ResponseBody");


			
			System.out.println("Basic API name from Excel Additional Details:" + managedAPIinExcel);
			
			//loginfo.log(Status.PASS, "Clicking on Managed API");
			//apimpage.ClickonAPIInGrid(managedAPIinExcel, "REST");
			
			
			
			
			
			
			apimSwaggerPage.switchToSwaggerTab();
			apimSwaggerPage.loginIntoSwaggerTab(adminUsername, adminPassword);
			apimSwaggerPage.executeAPIonSwaggerTab();
			
			String StatusCode=apimSwaggerPage.getAPIExecutionResponseCode();
			loginfo.log(Status.INFO,"Status code in Swagger tab:"+StatusCode);
			Assert.assertEquals(StatusCode, "200");
			
			
			
			String RespCode=apimSwaggerPage.getAPIExecutionResponseBody();
			loginfo.log(Status.INFO,"Response code in Swagger tab:"+RespCode);
			Assert.assertEquals(RespCode, expectedResponseBody);
			
			
			loginfo.log(Status.PASS,"Able to Execute Basic API on Swagger tab");
			apimpage.closeOpenedAPI(managedAPIinExcel);
			
		} catch (Exception e) {
			MyLogger.error("Exception while validating Basic API Swagger tab : " + e);
			loginfo.log(Status.FAIL, "validating Basic API Swagger tab");
			Assert.fail("Exception while validating Basic API Swagger tab");
		}

		MyLogger.info("================= validateBasicAPISwagger : END ================== ");
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates running of Swagger URL of Anonymous Managed API in API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @JIRA_TCs_Associated APIM-1492
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	
	@Test(dataProvider = "getData", priority = 0)
	public void validateAnonAPISwaggerURL(Object data) {

		loginfo.log(Status.PASS,"Validating Swagger URL of Anonymous API");
		MyLogger.info("================= validateAnonAPISwaggerURL : START ================== ");
		try {
			
			//apimpage.ClickonAPIRegistry();
			String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			System.out.println("Anonymous API name from Excel Additional Details:" + managedAPIinExcel);
			
			loginfo.log(Status.PASS, "Clicking on Managed API");
			apimpage.FindAPi(managedAPIinExcel);
			apimpage.clickOnApiInGrid(managedAPIinExcel, "REST");
			String swaggerURL = manageapipage.copySwaggerURL();
			//String swaggerBody = apimSwaggerPage.runAnonSwaggerURL(swaggerURL, context);
			String swaggerBody = apimSwaggerPage.runAnonSwaggerURL(swaggerURL);
			
			Assert.assertTrue(swaggerBody.contains("\"title\": \"" + managedAPIinExcel + "\""));
			
			loginfo.log(Status.PASS,"Able to Execute Anonymous API Swagger URL");
		
		} catch (Exception e) {
			MyLogger.error("Exception while validating Anonymous API Swagger URL : " + e);
			loginfo.log(Status.FAIL, "validating Anonymous API Swagger URL");
			Assert.fail("Exception while validating Anonymous API Swagger URL");
		}

		MyLogger.info("================= validateAnonAPISwaggerURL : END ================== ");
	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase validates running of Swagger URL of Basic Managed API in API Manager
	 * @author abehera
	 * @date_created  29-Jun-2022
	 * @JIRA_TCs_Associated APIM-1493
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */
	
	@Test(dataProvider = "getData", priority = 2)
	public void validateBasicAPISwaggerURL(Object data) {

		loginfo.log(Status.PASS,"Validating Swagger URL of Basic API");
		MyLogger.info("================= validateBasicAPISwaggerURL : START ================== ");
		try {
			
			//apimpage.ClickonAPIRegistry();
			String managedAPIinExcel = getAdditionalDetails(data, "ManagedAPIName");
			System.out.println("Basic API name from Excel Additional Details:" + managedAPIinExcel);
			
			loginfo.log(Status.PASS, "Clicking on Managed API");
			apimpage.FindAPi(managedAPIinExcel);
			apimpage.clickOnApiInGrid(managedAPIinExcel, "REST");
			String swaggerURL = manageapipage.copySwaggerURL();
			String swaggerBody = apimSwaggerPage.runBasicSwaggerURL(swaggerURL, adminUsername, adminPassword);
			
			Assert.assertTrue(swaggerBody.contains("\"title\": \"" + managedAPIinExcel + "\""));
			
			loginfo.log(Status.PASS,"Able to Execute Basic API Swagger URL");
		
		} catch (Exception e) {
			MyLogger.error("Exception while validating Basic API Swagger URL : " + e);
			loginfo.log(Status.FAIL, "validating Basic API Swagger URL");
			Assert.fail("Exception while validating Basic API Swagger URL");
		}

		MyLogger.info("================= validateBasicAPISwaggerURL : END ================== ");
	}

}
