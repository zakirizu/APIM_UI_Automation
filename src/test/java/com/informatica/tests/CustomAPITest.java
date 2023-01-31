package com.informatica.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.CustomAPIPage;
import com.informatica.pages.LoginPage;
import com.informatica.pages.managedAPIPage;
import com.informatica.web.util.Constants;
import com.informatica.web.util.MyLogger;

public class CustomAPITest extends BaseTest {

	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	CustomAPIPage custAPi = null;
	managedAPIPage managapi = null;

	@BeforeTest
	public void init() throws Exception {

		MyLogger.startLogger("CustomAPITest");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			managapi = new managedAPIPage(page);
			custAPi = new CustomAPIPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	@Test(dataProvider = "getData", priority = 1)
	public void ValdiateTestAndCreateCustomAPI(Object data) {

		MyLogger.info("================= ValdiateTestCustomAPI : START ================== ");
		try {
			apimpage.FindAPi(Constants.CUST_API_NAME);
			Boolean pre_APi_Existence_in_APIResgistery = apimpage.ValdiateAPIPresentInAPIRegistery(Constants.CUST_API_NAME, "REST");
		
			if(pre_APi_Existence_in_APIResgistery)
			{
				
				
				MyLogger.info("Custom API For Automation is already Exists, so deleting the Custom API ");
				apimpage.DeleteManagedAPI(Constants.CUST_API_NAME,"REST","1.0.0");
				apimpage.ReloadPage();

			
				
			}
				


			apimpage.ClickonCreateCustomAPI();

			MyLogger.info("Validating the Test URL Message");
			
			
			Assert.assertEquals(custAPi.TestCustomAPI(), Constants.CUST_TEST_BUTTON_PASS);
			loginfo.log(Status.PASS, "Validating the Test URL Message");
			String ActualMessage = custAPi.CreateCustomAPI();
			String ExpectedMessage = Constants.CUST_API_SAVE_MESSAGE;
			MyLogger.info("Actual Save Message :"+ ActualMessage);
			loginfo.log(Status.INFO, "Actual Save Message :"+ ActualMessage);

			MyLogger.info("Expected Save Message :"+ ExpectedMessage);
			loginfo.log(Status.INFO, "Expected Save Message :"+ ExpectedMessage);
			apimpage.ReloadPage();
			apimpage.FindAPi(Constants.CUST_API_NAME);
			Boolean APi_Existence_in_APIResgistery = apimpage.ValdiateAPIPresentInAPIRegistery(Constants.CUST_API_NAME, "REST");
			MyLogger.info("Checking the Created Custom API Present in Registery :"+ APi_Existence_in_APIResgistery);
			Assert.assertTrue(APi_Existence_in_APIResgistery );

			MyLogger.info("Validating the Create Custom API, Custom API is Created and Present in API Regsitery Page");
			loginfo.log(Status.PASS, "Validating the Create Custom API, Custom API is Created and Present in API Regsitery Page");
			loginfo.log(Status.PASS, "Able to Test and Create CustomAPI ");
			
			
		} catch (Exception e) {
			MyLogger.error("Exception while validating Create Custom API" + e);
			loginfo.log(Status.FAIL, "validating  Create Custom API");
			Assert.fail("Exception while validating  Create Custom API");
		}

		MyLogger.info("================= ValdiateTestCustomAPI : END ================== ");

	}

	@Test(dataProvider = "getData", priority = 2,dependsOnMethods = "ValdiateTestAndCreateCustomAPI")
	public void ValdiateAddRatelimitToCustomApi(Object data) {

		MyLogger.info("================= Validating the Update Custom API with RateLimit : START ================== ");
		try {

			apimpage.FindAPi(Constants.CUST_API_NAME);
			//apimpage.FindAPi("asdf");
			
			//apimpage.clickOnApiInGridv2("asdf", "REST");
			apimpage.clickOnApiInGrid(Constants.CUST_API_NAME, "REST");
			managapi.ClickonManagedAPIPolicies();
			String ActualMessage = custAPi.SaveCustomAPiwithRateLimit();
			String ExpectedMessage =Constants.CUST_API_UPD_MESSAGE;
			
			MyLogger.info("Actual Save Message :"+ ActualMessage);
			loginfo.log(Status.INFO, "Actual Save Message :"+ ActualMessage);

			MyLogger.info("Expected Save Message :"+ ExpectedMessage);
			loginfo.log(Status.INFO, "Expected Save Message :"+ ExpectedMessage);
			Assert.assertEquals(ActualMessage,ExpectedMessage);

			MyLogger.info("Validating the Update Custom API with RateLimit");

			loginfo.log(Status.PASS, "Validating the Update Custom API with RateLimit");

			loginfo.log(Status.PASS, "Able to update RateLimit for CustomAPI ");
		} catch (Exception e) {
			MyLogger.error("Exception while update RateLimit for CustomAPI" + e);
			loginfo.log(Status.FAIL, "validating update RateLimit for CustomAPI");
			Assert.fail("Exception while validating  update RateLimit for CustomAPI");
		}

		MyLogger.info("================= Validating the Update Custom API with RateLimit : END ================== ");

	}

	@Test(dataProvider = "getData", priority = 3,dependsOnMethods = "ValdiateTestAndCreateCustomAPI")
	public void ValdiateAddIpFilteringToCustomApi(Object data) {

		MyLogger.info("================= Validating the Update Custom API with IpFiltering : START ================== ");
		try {

		
			apimpage.FindAPi(Constants.CUST_API_NAME);
			apimpage.clickOnApiInGrid(Constants.CUST_API_NAME, "REST");
			managapi.ClickonManagedAPIPolicies();
			
			String ActualMessage = custAPi.SaveCustomAPiwithIPFiltering(Constants.IP_ADDRESS_FROM, Constants.IP_ADDRESS_TO);
			String ExpectedMessage = Constants.CUST_API_UPD_MESSAGE;
			
			MyLogger.info("Actual Save Message :"+ ActualMessage);
			loginfo.log(Status.INFO, "Actual Save Message :"+ ActualMessage);

			MyLogger.info("Expected Save Message :"+ ExpectedMessage);
			loginfo.log(Status.INFO, "Expected Save Message :"+ ExpectedMessage);

			
			Assert.assertEquals(ActualMessage,ExpectedMessage);

			MyLogger.info("Validating the Update Custom API with IpFiltering");

			loginfo.log(Status.PASS, "Validating the Update Custom API with IpFiltering");

			loginfo.log(Status.PASS, "Able to update IpFiltering for CustomAPI ");
		} catch (Exception e) {
			MyLogger.error("Exception while update IpFiltering for CustomAPI" + e);
			loginfo.log(Status.FAIL, "validating update IpFiltering for CustomAPI");
			Assert.fail("Exception while validating  update IpFiltering for CustomAPI");
		}

		MyLogger.info("================= Validating the Update Custom API with IpFiltering : END ================== ");

	}

	@Test(dataProvider = "getData", priority = 4,dependsOnMethods = "ValdiateTestAndCreateCustomAPI")
	public void ValdiateAddGroupToCustomApi(Object data) {

		MyLogger.info("================= Validating the Update Custom API with Groups: START ================== ");
		try {

	
			apimpage.FindAPi(Constants.CUST_API_NAME);
			apimpage.clickOnApiInGrid(Constants.CUST_API_NAME, "REST");
			
			String ActualMessage = custAPi.SaveCustomAPIwithGroup();
			String ExpectedMessage = Constants.CUST_API_GRP_SAVE;
			
			MyLogger.info("Actual Save Message :"+ ActualMessage);
			loginfo.log(Status.INFO, "Actual Save Message :"+ ActualMessage);

			MyLogger.info("Expected Save Message :"+ ExpectedMessage);
			loginfo.log(Status.INFO, "Expected Save Message :"+ ExpectedMessage);

			
			Assert.assertEquals(ActualMessage,ExpectedMessage);

			loginfo.log(Status.PASS, "Validating the Update Custom API with Policies");

			loginfo.log(Status.PASS, "Able to update Groups for CustomAPI ");
		} catch (Exception e) {
			MyLogger.error("Exception while update Groups for CustomAPI" + e);
			loginfo.log(Status.FAIL, "validating update Groups for CustomAPI");
			Assert.fail("Exception while validating  update Groups for CustomAPI");
		}

		MyLogger.info("================= Validating the Update Custom API with Groups: : END ================== ");

	}
	
	@Test(dataProvider = "getData", priority = 5,dependsOnMethods = "ValdiateTestAndCreateCustomAPI")
	public void ValdiateDeleteCustomAPI(Object data) {

		MyLogger.info("================= Validating the Delete Custom API: START ================== ");
		try {

			//page.reload();
			//apimpage.FindAPi(Constants.CUST_API_NAME);
			//apimpage.clickOnApiInGrid(Constants.CUST_API_NAME, "REST");
			apimpage.DeleteManagedAPI(Constants.CUST_API_NAME,"REST","1.0.0");
			apimpage.FindAPi(Constants.CUST_API_NAME);
			Boolean APi_Existence_in_APIResgistery = apimpage.ValdiateAPIPresentInAPIRegistery(Constants.CUST_API_NAME, "REST");
			Assert.assertFalse(APi_Existence_in_APIResgistery);
			
			MyLogger.info("Validating the Delete Custom API, Custom API is Deleted and Not Present in API Regsitery Page");
			loginfo.log(Status.PASS, "Validating the Delete Custom API, Custom API is Deleted and Not Present in API Regsitery Page");
		


		} catch (Exception e) {
			MyLogger.error("Exception while Delete Custom API" + e);
			loginfo.log(Status.FAIL, "validating Delete Custom API");
			Assert.fail("Exception while validating  Delete Custom API");
		}

		MyLogger.info("=================  Validating the Delete Custom API: : END ================== ");

	}
	
	

}
