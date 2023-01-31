package com.informatica.tests;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.ApiDetailsGeneralPage;
import com.informatica.pages.ApiDetailsPolicyPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.Constants;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;


/**
	Description: This class consists of test cases to validate the Rate Limit policies applied to various kinds of Managed APIs.
	Author: pdasari
	Date Created: 05th Jul 2022
	JIRA TCs associated: APIM-1478, APIM-1479
	Coverage: UI,Sanity,Regression,PodValidation
*/
public class RateLimitTest extends BaseTest {
	private LoginPage loginPage = null;
	private APIMRegistryPage apimRegistryPage = null;
	private ApiDetailsGeneralPage apiDetailsGeneralPage = null;
	private ApiDetailsPolicyPage apiDetailsPolicyPage = null;

	private final String RATE_LIMIT_TIER_NAME = "APIM_Automation_Tier";

	@BeforeClass
	public void init() throws Exception {
		MyLogger.startLogger("RateLimitTest");
		try {
			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage.ClickonAPIMChiklet();
			apimRegistryPage = new APIMRegistryPage(page);
		//	apimRegistryPage.selectFullPage();
			apiDetailsGeneralPage = new ApiDetailsGeneralPage(page);
			apiDetailsPolicyPage = new ApiDetailsPolicyPage(page);
		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	private int getNumberOfCalls(String tierConfiguration) {
		int numberOfCalls = parseTierConfiguration(tierConfiguration).get("calls");
		MyLogger.info("Number of Calls: " + numberOfCalls);
		return numberOfCalls;
	}

	@SuppressWarnings("unused")
	private int getTimeframe(String tierConfiguration) {
		int timeFrame = parseTierConfiguration(tierConfiguration).get("timeframe");
		MyLogger.info("Time Frame: " + timeFrame + " seconds");
		return timeFrame;
	}

	private Map<String, Integer> parseTierConfiguration(String tierConfiguration) {
		Map<String, Integer> callAndTimeFrameMap = new HashMap<>();
		tierConfiguration = tierConfiguration.replace("Number of Calls: ", "");
		tierConfiguration = tierConfiguration.replace("Time Frame: ", "");
		tierConfiguration = tierConfiguration.replace("seconds", "");
		tierConfiguration = tierConfiguration.trim();
		String[] tokens = tierConfiguration.split(";");
		if (tokens.length != 2) {
			throw new RuntimeException("Invalid Tier Configuration format.");
		} else {
			callAndTimeFrameMap.put("calls", Integer.parseInt(tokens[0].trim()));
			callAndTimeFrameMap.put("timeframe", Integer.parseInt(tokens[1].trim()));
		}
		return callAndTimeFrameMap;
	}


	/*
	 * private void verifyRateLimitPolicy(String apiName, String protocol,
	 * BrowserContext context) throws InterruptedException { try {
	 * 
	 * apimRegistryPage.clickonAPIRegistry(); apimRegistryPage.filterManagedApis();
	 * apimRegistryPage.find(apiName); apimRegistryPage.clickOnApiInGrid(apiName,
	 * protocol); apiDetailsPolicyPage.clickPoliciesTab(); boolean
	 * isRateLimitConfigurationSuccess =
	 * apiDetailsPolicyPage.configureRateLimit(RATE_LIMIT_TIER_NAME);
	 * Assert.assertTrue(isRateLimitConfigurationSuccess,
	 * "Failed to configure Rate Limit for the Managed API - " + apiName); String
	 * tierConfiguration = apiDetailsPolicyPage.getSelectedTierConfiguration(); int
	 * numberOfCalls = getNumberOfCalls(tierConfiguration);
	 * apiDetailsGeneralPage.clickGeneralTab(); String apiUrl =
	 * apiDetailsGeneralPage.copyApiUrl(); Assert.assertNotNull(apiUrl,
	 * "Failed to copy the API URL."); String responseBody =
	 * apiDetailsGeneralPage.executeUrlTillRateLimitExceed(context, apiUrl,
	 * (numberOfCalls + 2)); Assert.assertTrue((responseBody.contains("Forbidden"))
	 * && (responseBody.contains("Rate Limit Reached")),
	 * "Rate Limit is not reached.");
	 * 
	 * } finally { apiDetailsPolicyPage.clickPoliciesTab();
	 * apiDetailsPolicyPage.unconfigureRateLimit();
	 * apiDetailsGeneralPage.closeApiDetailsPage(); } }
	 * 
	 */
private void verifyRateLimitPolicy(String apiName, String protocol, BrowserContext RateLimitcontext) throws InterruptedException {
	try {
		
		apimRegistryPage.clickonAPIRegistry();
	//	apimRegistryPage.filterManagedApis();
		apimRegistryPage.find(apiName);
		apimRegistryPage.clickOnApiInGrid(apiName, protocol);
		apiDetailsPolicyPage.clickPoliciesTab();
		boolean isRateLimitConfigurationSuccess = apiDetailsPolicyPage.configureRateLimit(RATE_LIMIT_TIER_NAME);
		Assert.assertTrue(isRateLimitConfigurationSuccess,"Failed to configure Rate Limit for the Managed API - " + apiName);
		String tierConfiguration = apiDetailsPolicyPage.getSelectedTierConfiguration();
		int numberOfCalls = getNumberOfCalls(tierConfiguration);
		apiDetailsGeneralPage.clickGeneralTab();
		String apiUrl = apiDetailsGeneralPage.copyApiUrl();
		Assert.assertNotNull(apiUrl, "Failed to copy the API URL.");
		String responseBody = apiDetailsGeneralPage.executeUrlTillRateLimitExceed(RateLimitcontext, apiUrl,(numberOfCalls + 5));
		loginfo.log(Status.INFO, "Response body After Tier Rate Limits completed  :"+ responseBody);

		Assert.assertTrue((responseBody.contains("Forbidden")) && (responseBody.contains("Rate Limit Reached")),
				"Rate Limit is not reached.");

	} finally {
		apiDetailsPolicyPage.clickPoliciesTab();
		apiDetailsPolicyPage.unconfigureRateLimit();
		apiDetailsGeneralPage.closeApiDetailsPage();
	}
}


	/**
	 * @Description - APIM_UI_Sanity_TC005_Verify the rate limit policy applied to an anonymous API
	 * @date created 06-07-2022
	 * @author pdasari
	 * @throws InterruptedException 
	 */
	@Test(priority = 1, description="APIM_UI_Sanity_TC005_Verify the rate limit policy applied to an anonymous API", dataProvider = "getData")
	public void verifyRateLimitPolicyAppliedToAnonymousApi(Object data) throws InterruptedException {
		
		MyLogger.info("================= verifyRateLimitPolicyAppliedToAnonymousApi : START ================== ");

		try {
			MyLogger.info("Testcase Start: verifyRateLimitPolicyAppliedToAnonymousApi");
			String managedApiWithAnonymousAuth = getAdditionalDetails(data, "Processname");
			BrowserContext context = page.context();
	//		verifyRateLimitPolicy(managedApiWithAnonymousAuth, "REST", page.context());
			verifyRateLimitPolicy(managedApiWithAnonymousAuth, "REST", context);

			MyLogger.info("Testcase End: verifyRateLimitPolicyAppliedToAnonymousApi");
		} catch (IOException e) {
			MyLogger.error("Exception while validating RateLimitPolicyAppliedToAnonymousApi: "+ e);
			Assert.fail("Exception while validating RateLimitPolicyAppliedToAnonymousApi");
		}
		MyLogger.info("================= verifyRateLimitPolicyAppliedToAnonymousApi : END ================== ");
	
	}

	/**
	 * @Description - APIM_UI_Sanity_TC006_Verify the rate limit policy applied to a Basic API
	 * @date created 06-07-2022
	 * @author pdasari 
	 * @throws InterruptedException 
	 */
	@Test(priority = 2, description="APIM_UI_Sanity_TC006_Verify the rate limit policy applied to a Basic API", dataProvider = "getData")
	public void verifyRateLimitPolicyAppliedToBasicApi(Object data) throws InterruptedException {
		
		MyLogger.info("================= verifyRateLimitPolicyAppliedToBasicApi : START ================== ");

		try {
			MyLogger.info("Testcase Start: verifyRateLimitPolicyAppliedToBasicApi");
			String managedApiWithBasicAuth = getAdditionalDetails(data, "Processname");
			BrowserContext context = page.context().browser().newContext(
					(new Browser.NewContextOptions().setHttpCredentials(adminUsername, adminPassword).setViewportSize(null)));
			verifyRateLimitPolicy(managedApiWithBasicAuth, "REST", context);
			MyLogger.info("Testcase End: verifyRateLimitPolicyAppliedToBasicApi");
		}catch (IOException e) {
			MyLogger.error("Exception while validating RateLimitPolicyAppliedToBasicApi: "+ e);
			Assert.fail("Exception while validating RateLimitPolicyAppliedToBasicApi");
		}
		
		MyLogger.info("================= verifyRateLimitPolicyAppliedToBasicApi : START ================== ");

		
	}
	
}
