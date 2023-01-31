package com.informatica.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.MyLogger;

public class LoginTest extends BaseTest {

	LoginPage loginPage = null;
	// HashMap<String, HashMap<String, String>> testData = null;

	@BeforeTest
	public void init() throws Exception {

		// MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		MyLogger.startLogger("LoginTest");

		try {
	
			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			System.out.println(page);
			

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to IICS  : " + e);
			throw e;
		}
	}

	@Test(dataProvider = "getData", priority = 1)
	public void validateIICSLogin(Object data) {

		loginfo.log(Status.PASS, "Login into IICS");
		MyLogger.info("================= validateIICSLogin : START ================== ");

		try {
		//	super.JIRA_KEY = getJIRAKey(data);

			System.out
					.println("Process name from Excel Additional Details:" + getAdditionalDetails(data, "Processname"));
			System.out.println("Process name from Excel Additional Details:" + getAdditionalDetails(data, "GroupName"));
		//	System.out.println("JIRA Key from Excel:" + getJIRAKey(data));

			loginfo.log(Status.PASS, "Getting Page Title");
			Assert.assertEquals(loginPage.getTitle(), "Intelligent Cloud Services");

		} catch (Exception e) {
			MyLogger.error("Exception while validating IICSLogin : " + e);
			loginfo.log(Status.FAIL, "validating IICSLogin");
			Assert.fail("Exception while validating IICSLogin");
		}

		MyLogger.info("================= validateIICSLogin : END ================== ");

	}

	@Test(priority = 2)
	public void valdiateAPIMChiklet() {

		MyLogger.info("================= valdiateAPIMChiklet : START ================== ");
		loginfo.log(Status.PASS, "Validating on APIM Chiklet");
		try {
			// super.JIRA_KEY = getJIRAKey(data);
			Assert.assertEquals(loginPage.ValidateAPIMChiklet(), "API Manager");
			loginfo.log(Status.PASS, "APIM Chiklet is Availibale");
		} catch (Exception e) {
			MyLogger.error("Exception while validating APIMChiklet : " + e);
			loginfo.log(Status.FAIL, "validating APIMChiklet");
			Assert.fail("Exception while validating APIMChiklet");
		}

		MyLogger.info("================= valdiateAPIMChiklet : END ================== ");

	}

	/*
	 * public void loginToIICS() throws Exception {
	 * 
	 * 
	 * String userName = ConfigPropertiesLoader.getProperty("adminUsername"); String
	 * password = ConfigPropertiesLoader.getProperty("adminPassword");
	 * loginPage.navigateTo(ConfigPropertiesLoader.getProperty("baseUrl"));
	 * 
	 * 
	 * 
	 * System.out.println("URL from excel :" + baseUrl);
	 * System.out.println("username from excel :" + adminUsername);
	 * 
	 * 
	 * loginPage.navigateTo(baseUrl); loginPage.loginIntoIICSApp(adminUsername,
	 * adminPassword); }
	 */

	/*
	 * @DataProvider public Object[][] getData(Method m) {
	 * 
	 * Object[][] data = null; try { data = new Object[1][1]; if (testData == null)
	 * { testData =
	 * ExcelReader.loadTestData(classToModuleMap.get(this.getClass().getName()));
	 * System.out.println("testData" + testData); } for (Map.Entry<String,
	 * HashMap<String, String>> entry : testData.entrySet()) { if
	 * (entry.getKey().equalsIgnoreCase(m.getName())) { data[0][0] =
	 * entry.getValue(); break; } }
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return data; }
	 */

}
