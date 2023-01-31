package com.informatica.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.informatica.pages.APIGroupsPage;
import com.informatica.pages.APIMPoliciesPage;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.MyLogger;

public class OrgLevelPoliciesTest extends BaseTest {
	
	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	APIMPoliciesPage orgpolociespage = null;
	
	@BeforeTest
	public void init() throws Exception {


	//	MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		MyLogger.startLogger("OrgLevelIPFiltering");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginPage = new LoginPage(page);
			loginToIICS(page);
			loginPage = new LoginPage(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			apimpage.ClickonPolicies();
			
			orgpolociespage = new APIMPoliciesPage(page);
			

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}
	
	/**
     * ************************************************************************************************************************
     * @description This testcase validates the Rate limit UI at ORG level
     * @author mbalaji
     * @date_created  11-Oct-2022
     * @JIRA_TCs_Associated APIM-1497
     * @coverage UI Sanity,PodValidation
     * ************************************************************************************************************************
     */
	
	@Test(priority = 1)
	public void validateOrgRateLimitUI()
	{
		MyLogger.info("================= validateOrgRateLimitUI : START ================== ");
		try {

			orgpolociespage.validateOrgRateLimitUI();	
			Assert.assertTrue(true);

		}catch (Exception e) {
			MyLogger.error("Exception while Verfying the Or Rate limti UI : " + e);
			Assert.fail("Exception while Verfying the Or Rate limti UI");
		}		

	}
	
	/**
	 * ************************************************************************************************************************
	 * @description This testcase to Verify deletion of default org tier
	 * @author mbalaji
	 * @date_created  11-Oct-2022
	 * @JIRA_TCs_Associated APIM-1496
	 * @coverage UI Sanity,PodValidation
	 * ************************************************************************************************************************
	 */

	@Test(priority = 2)
	public void deleteDefaultOrgRateLimitTier()
	{
		MyLogger.info("================= deleteDeafultOrgRateLimitTier : START ================== ");
		try {

			orgpolociespage.deleteOrgTier("Org_level_default_tier");	
			Assert.assertTrue(true);

		}catch (Exception e) {
			MyLogger.error("Exception while trying to deleteDeafultOrgRateLimitTier : " + e);
			Assert.fail("Exception while trying to deleteDeafultOrgRateLimitTier");
		}		

	}
	
	/**
     * ************************************************************************************************************************
     * @description This testcase validates the ORG level IPfilter value range from 0.0.0.0 to 255.255.255.255
     * @author pmansabd
     * @date_created  07-Jul-2022
     * @JIRA_TCs_Associated APIM-1496
     * @coverage UI Sanity,PodValidation
     * ************************************************************************************************************************
     */
	
	@Test(priority = 3)
	public void validateIpFilterPoliciesRetained()
	{
		MyLogger.info("================= ORGLevel IPfilter check : START ================== ");
		try {
			orgpolociespage.ipfilterretained();
			Assert.assertTrue(true);
			
			
		}catch (Exception e) {
			MyLogger.error("Exception while validating the allow : " + e);
			Assert.fail("Exception while Validating the error");
		}
		
	}
	


}
