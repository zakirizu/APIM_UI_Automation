package com.informatica.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.ApiDetailsGeneralPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.MyLogger;
import com.informatica.web.util.ReportsTestValidator;

/**
 * Description: This class consists of test cases to validate the various
 * operations on Client SDKs like the default Client SDK, available SDKs,
 * download the SDK etc. Author: pdasari Date Created: 05th Jul 2022 JIRA TCs
 * associated: APIM-1495 Coverage: UI,Sanity,Regression,PodValidation
 */
public class ClientSdkTest extends BaseTest {

	private LoginPage loginPage = null;
	private APIMRegistryPage apimRegistryPage = null;
	private ApiDetailsGeneralPage apiDetailsGeneralPage = null;
	private final String DEFAULT_CLIENT_SDK = "Java";

	@BeforeClass
	public void init() throws Exception {
		MyLogger.startLogger("ClientSdkTest");
		// MyLogger.startLogger("Initializing ClientSdk Tests");
		try {
			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.ClickonAPIMChiklet();
			apimRegistryPage = new APIMRegistryPage(page);
		//	apimRegistryPage.selectFullPage();
			apiDetailsGeneralPage = new ApiDetailsGeneralPage(page);
		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	/**
	 * @Description - APIM_UI_Sanity_TC022_Verify that the Download SDK option is
	 *              working for a Managed API
	 * @date created 06-07-2022
	 * @author pdasari
	 */
	@Test(priority = 1, description = "APIM_UI_Sanity_TC022_Verify that the Download SDK option is working for a Managed API", dataProvider = "getData")
	public void verifyClientSdkItemsAndDownload(Object data) {
		try {
			MyLogger.info("Testcase Start: verifyClientSdkItemsAndDownload");
			String managedApiWithAnonymousAuth = getAdditionalDetails(data, "Processname");
			apimRegistryPage.clickonAPIRegistry();
			//apimRegistryPage.filterManagedApis();
			apimRegistryPage.find(managedApiWithAnonymousAuth);
			apimRegistryPage.clickOnApiInGrid(managedApiWithAnonymousAuth, "REST");
			apiDetailsGeneralPage.clickGeneralTab();
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(apiDetailsGeneralPage.getClientSdkCount(),
					ReportsTestValidator.CLIENT_SDK_LIST.size(), "Client SDK Items count mismatched.");
			softAssert.assertEquals(apiDetailsGeneralPage.getClientSdkList(), ReportsTestValidator.CLIENT_SDK_LIST,
					"Client SDK dropdown list of items are not matched.");
			softAssert.assertEquals(apiDetailsGeneralPage.getSelectedClientSdk(), DEFAULT_CLIENT_SDK,
					"'" + DEFAULT_CLIENT_SDK + "' is not the default selected Client SDK.");

			softAssert.assertAll();

			String downloadPopupMessage = apiDetailsGeneralPage.downloadClientSdk();

			if (!downloadPopupMessage.contains("successfully")) {
				

				for (int i = 0; i <= 3; i++) {
					loginfo.log(Status.INFO, "JDK Dowload was Failed in Previous Attempt so trying once Again");

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					downloadPopupMessage = apiDetailsGeneralPage.downloadClientSdk();
					if (downloadPopupMessage.contains("successfully"))
						break;

				}
			}

			apiDetailsGeneralPage.closeApiDetailsPage();
			Assert.assertEquals(downloadPopupMessage.toLowerCase(),
					(DEFAULT_CLIENT_SDK + " SDK generated successfully").toLowerCase());
			MyLogger.info("Testcase End: verifyClientSdkItemsAndDownload");
		} catch (IOException e) {
			MyLogger.error("Exception while validating ClientSdkItemsAndDownload: " + e);
			Assert.fail("Exception while validating ClientSdkItemsAndDownload");
		}

	}
}
