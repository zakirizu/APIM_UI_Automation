/****************************************************************************************************
 * @author Sragilla
 * @createdDate 30-06-2022
 * @description This class is created to track the test methods on API Portal
 ******************************************************************************************************/

package com.informatica.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.informatica.pages.APIPage;
import com.informatica.pages.APIPortalAPIGroupsPage;
import com.informatica.pages.APIPortalHomePage;
import com.informatica.pages.APIPortalRegistryPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.MyLogger;

public class APIPortalAPIGroupsTest extends BaseTest {

	LoginPage loginPage = null;
	APIPortalHomePage apiPortalPage = null;
	APIPortalAPIGroupsPage apiGroupsPage = null;
	APIPage apiPage = null;
	HashMap<String, HashMap<String, String>> testData = null;

	String basicAPIName = "APIM_Automation_Basic";
	String anonymousAPI = "APIM_Automation_Anon1";

	@BeforeTest
	public void init() throws Exception {
		MyLogger.startLogger("APIPortalAPIGroupsTest");

		try {

			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.clickOnApiChiklet();
			apiPortalPage = new APIPortalHomePage(page);
			apiPortalPage.clickApiGroupsTab();
			apiGroupsPage = new APIPortalAPIGroupsPage(page);
			apiPage = new APIPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to API  : " + e);
			throw e;
		}
	}

	/**
	 * @author sragilla
	 * @date_created 07-Jul-2022
	 * @description :This methodValidates the managedAPI in the APIGroup from API Portal
	 * @param data
	 */
	@Test(dataProvider = "getData", priority = 1)
	public void validateAPIURLInAPIGroup(Object data) throws Exception {
		MyLogger.info("================= validateAPIURLInAPIGroup From APIPortal : START ================== ");
		try {
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("apiGroupName");
			String apiName = parameters.get("apiName");
			String apiProtocol = parameters.get("apiProtocol");
			String apiGroupContext = parameters.get("apiGroupContext");
			apiGroupsPage.clickOnAPIGroup(apiGroupName, apiGroupContext);
			loginfo.pass("click on apiGroup withContext");
			apiGroupsPage.clickOnAPIInAPIGroup(apiName, apiProtocol);
			loginfo.pass("click on API IN APIGroup ");
			String urlFromAPIPage = apiGroupsPage.getURLFromAPIInAPIGroup(apiName, apiGroupContext);
			String urlFromCopyURLButton = apiGroupsPage.clikCopyURLOFAPIInAPIGroup();
			Assert.assertEquals(urlFromAPIPage, urlFromCopyURLButton,
					"The Urls from the APIPage and CopyURL Button are not same.");

		} catch (Exception e) {
			MyLogger.error("Exception while validating url in the APIGroup : " + e);
			throw e;
		}
		MyLogger.info("================= validateAPIURLInAPIGroup From APIPortal : END ================== ");
	}

}
