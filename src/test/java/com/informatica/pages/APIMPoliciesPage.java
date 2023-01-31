package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.informatica.web.util.Constants;
import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class APIMPoliciesPage extends BasePage {

	public APIMPoliciesPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	String APIMpoliciespage = "//a[@href='/apimui/policies']";

	String allowonpoliciespage="//div[@class='IpFiltering']//span[text()='Allow']";
	String fromippoliciespage="//span[text()='0.0.0.0']";
	String toippoliciespage="//span[text()='255.255.255.255']";
	String noofcallsratelimit="//div[@class='RateLimitTextsAndInputsContainer']//div[1]//div[1]//input";
	String insecondsratelimit="//input[@value='60']";
	String clickratelimitupdate="//div[1]/button[@title='Update']";
	String validationmessage="//p[@class='d-messagebubble__text__child']";
	String ipfiltersave="//div[@class='IpFiltering']//div//div[1]//span//div//button[@title='Save']";
	private String rateLimitPolicyDefaultValue = "//div[@class='RateLimit']//div//div[2]//div[3]//div//div//input[@data-testid='dropdown-search']";
	private String saveBtn = "//div[@class='RateLimit']//div//div[1]//span//div//button[@title='Save']";
	private String tierSetup = "//div[@class='RateLimit']//div//div[1]//span//div//button[@title='Tier Setup']";
	private String tierFindTextbox = "//div[6]/div[3]/div/div[2]/div/div[6]/div/div[1]/span/div/div/div/input";
	private String userSpecificTier = "//div[@class='d-version-2 d-section d-section--collapsed']//button[@class='d-version-2 d-icon-button d-section__header__toggle-expansion-icon']";
	private String userNameTextBox = "//div[@class=' d-input InputWrapper']//input[@placeholder='Enter User Name']";
	private String userSpecificTierDropDownBtn = "//span[contains(text(), 'Tier')]/../div/div/button";
	private String searchedTier = "//div[6]/div[3]/div/div[2]/div/div[6]/div/div[2]/div/table/tbody/tr";
	private String deleteTier = "//ul[@class ='d-menu d-version-2']/li[contains(text(),'Delete')]";
	private String kebabmenuTier = "(//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action'])[6]";
	private String closebtn = "//div[6]/div[3]/div/div[3]/div/button";
	private String searchedTierdefault = "(//tr[@data-id='org_limit'])[2]";

	public String checkallow()
	{
		MyLogger.info("check ip filter set to allow/deny");
		String accesstype= page.locator(allowonpoliciespage).innerText();
		return accesstype;	

	}

	public String checkfromip()
	{
		MyLogger.info("get from range of ip filter");
		String fromip= page.locator(fromippoliciespage).innerText();
		return fromip;	

	}

	public String checktoip()
	{
		MyLogger.info("get to range of ip filter");
		String toip= page.locator(toippoliciespage).innerText();
		return toip;	
	}


	/**
	Description: This Method checks the Ratelimit at org level is not blank and user can update to other value
	Author: pmansabd
	Date Created: 07th Jul 2022
	Coverage: UI POD SANITY
	 */
	public void validatepoliciesatratelimitorg()
	{
		MyLogger.info("================= ORGLevel Rate Limit : START ================== ");
		try {
			MyLogger.info("set org level rate limit no. of call to 1500");
			page.locator(noofcallsratelimit).fill(Constants.UPDATE_ORG_RATELIMIT);
			page.locator(clickratelimitupdate).click();
			String messagecheck=page.locator(validationmessage).innerText();
			Assert.assertEquals(messagecheck, "The organizational rate limit policy was updated successfully.");
			MyLogger.info("set org level rate limit no. of call to "+ Constants.UPDATE_ORG_RATELIMIT);
			page.locator(noofcallsratelimit).fill(Constants.ORG_RATELIMIT);
			page.locator(clickratelimitupdate).click();
			MyLogger.info("orglevel Rate limit set to 1000");


			Assert.assertTrue(true);

		}catch (Exception e) {
			MyLogger.error("Exception while Updatign the value : " + e);

			Assert.fail("Exception while inseerting the value");
		}

	}
	/**
	Description: This Method checks the ipfilter at org level Values are retained and is not blank
	Author: pmansabd
	Date Created: 07th Jul 2022
	Coverage: UI POD SANITY
	 */
	public void ipfilterretained()
	{
		MyLogger.info("Test Method Started ");
		try {
			Assert.assertEquals(checkallow(), Constants.ORG_IPFILTER_ALLOW);
			Assert.assertEquals(checkfromip(),Constants.ORG_FROM_RANGE);
			MyLogger.info("ip Filter from is set to " + Constants.ORG_FROM_RANGE);
			Assert.assertEquals(checktoip(), Constants.ORG_TO_RANGE);
			MyLogger.info("ip Filter from is set to "+ Constants.ORG_TO_RANGE);
			MyLogger.info("Org Level ip Filter values are retained");
			MyLogger.info("check for update button");
			page.locator(ipfiltersave).click();
			//clickorgipfilterupdate();
			MyLogger.info("save button operation successfull");		

		}catch (Exception e) {
			MyLogger.error("Exception while validating the allow : " + e);
			Assert.fail("Exception while Validating the error");
		}
	}

	
	/**
	 * Method used to verify the deletion of tier at org level.
	 * @param tierName - Name of the Tier to be deleted at org level.
	 * Author: Mrudula
	 * Date Created: 11th Oct 2022
	 * @return true/false on completion of deletion.
	 */
	public boolean deleteOrgTier(String name) {
		MyLogger.info("Configuring new tier at org level Rate Limit...");
		boolean deleteExits = true;
		try {
			page.locator(tierSetup).click();
			page.locator(tierFindTextbox).fill(name);
			if(name.equalsIgnoreCase("Org_level_default_tier")) {
				page.locator(searchedTierdefault).click();
				Locator src = page.locator(searchedTier);
				waitForMouseActions();
				page.locator(kebabmenuTier).click();
				deleteExits = page.locator(deleteTier).isEnabled();
				if(deleteExits==false) {
					deleteExits= true;
					page.locator(closebtn).click();
				}
			}
			else {
				page.locator(searchedTier).click();
				Locator src = page.locator(searchedTier);
				waitForMouseActions();
				//page.mouse().move(src.boundingBox().x, src.boundingBox().y);
				page.locator(kebabmenuTier).click();
				page.locator(deleteTier).click();
				page.locator(closebtn).click();
				String saveMessage = getToastMessages();
				MyLogger.info("Save Message: " + saveMessage);
				closeToastMessage();
				waitForToastMessagesClose();
				if (!saveMessage.contains("Tier deleted successfully")) {
					deleteExits = false;
				}
			}
		} catch (Exception e) {
			MyLogger.error("Exception while configuring the Rate Limit: " + e.getMessage(), e);
			deleteExits = false;
		}
		return deleteExits;
	}
	
	/**
	 * Method used to validate the configured User specific tier at org level.
	 * @param tierName - Name of the Tier to be used to configure the Rate Limit policy at org level.
	 * Author: Mrudula
	 * Date Created: 11th Oct 2022
	 * @return true/false on completion of configuration.
	 */
	public boolean validateOrgRateLimitUI() {
		MyLogger.info("Validate Configured User Specific tier at org level Rate Limit...");
		boolean orgRateLimitUIstatus=false;
		try {

			boolean ratelimitDropDown = page.locator(rateLimitPolicyDefaultValue).isVisible();
			boolean orgSaveButton = page.locator(saveBtn).isVisible();
			boolean orgTierSetup = page.locator(userSpecificTier).isVisible();
			boolean userSpecific = page.locator(userSpecificTier).isVisible();
			boolean userspecifictextbox= page.locator(userNameTextBox).isVisible();
			boolean userspecificdropdown = page.locator(userSpecificTierDropDownBtn).isVisible();
			if (ratelimitDropDown && orgSaveButton && orgTierSetup && userSpecific && userspecifictextbox && userspecificdropdown)
			{
				orgRateLimitUIstatus = true;
			}
			else {
				orgRateLimitUIstatus= false;
			}
		} catch (Exception e) {
			MyLogger.error("Exception while configuring the Rate Limit: " + e.getMessage(), e);
		}
		return orgRateLimitUIstatus;
	}

}
