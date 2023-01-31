package com.informatica.pages;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

/**
	Description: This page class consists of methods for any Managed API -> Policies tab.
	Author: pdasari
	Date Created: 05th Jul 2022
	JIRA TCs associated: NA
	Coverage: NA
*/
public class ApiDetailsPolicyPage extends BasePage {

	private String policyDetailsPageTab = "//*[@id=\"root\"]/div/div[2]/main/div[3]/div/div[1]/ul/button[2]";
	private String rateLimitPolicyCheckbox = "//label[text()='Enable API-specific rate limit policy']/parent::*/parent::*/input";
	private String rateLimitTierSelectDropdownBtn = "//span[text()='Select a tier for the managed API']/../div/div/button";
	private String rateLimitTierSelectDropdownValues = "//div[@data-testid='dropdown-menu']/div";
	private String saveBtn = "//button[@title='Save']";
	private String selectedTierConfiguration = "//*[@class='d-section__content']/div/span[contains(text(), 'Number of Calls')]";

	public ApiDetailsPolicyPage(Page page) {
		super(page);
	}

	/**
	 * This method clicks on Policies tab of the Managed API from Managed API details page.
	 */
	public void clickPoliciesTab() {
		MyLogger.info("Switch to Policies Tab");
		page.locator(policyDetailsPageTab).click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
	}

	/**
	 * Method used to configure the Rate Limit by selecting the pre-created Tier.
	 * 
	 * @param tierName - Name of the Tier to be used to configure the Rate Limit policy for the API.
	 * 
	 * @return true/false on completion of configuration.
	 */
	public boolean configureRateLimit(String tierName) {
		MyLogger.info("Configuring Rate Limit...");
		boolean rateLimitConfigureStatus = true;
		try {
			if (!page.locator(rateLimitPolicyCheckbox).isChecked()) {
				page.locator(rateLimitPolicyCheckbox).click();
				page.locator(rateLimitTierSelectDropdownBtn).click();
				int numberOfTiers = page.locator(rateLimitTierSelectDropdownValues).count();
				boolean isRequestedTierAvailable = false;
				for (int index = 1; index <= numberOfTiers; index++) {
					String newRateLimitTierSelectDropdownValues = rateLimitTierSelectDropdownValues + "[" + index + "]";
					String tierNameFromDropdown = page.locator(newRateLimitTierSelectDropdownValues).innerText();
					if (tierName.equals(tierNameFromDropdown)) {
						MyLogger.info("Found the requested tier in the dropdown.");
						isRequestedTierAvailable = true;
						page.locator(newRateLimitTierSelectDropdownValues).click();
						page.locator(saveBtn).click();
						String saveMessage = getToastMessages();
						MyLogger.info("Save Message: " + saveMessage);
						closeToastMessage();
						waitForToastMessagesClose();
						if (!saveMessage.contains("updated successfully")) {
							rateLimitConfigureStatus = false;
						}
						break;
					}
				}
				if (!isRequestedTierAvailable) {
					rateLimitConfigureStatus = false;
				}
			}
		} catch (Exception e) {
			MyLogger.error("Exception while configuring the Rate Limit: " + e.getMessage(), e);
			rateLimitConfigureStatus = false;
		}
		return rateLimitConfigureStatus;
	}
	
	/**
	 * Method used to remove/undo the Rate Limit Configuration made to the Managed API.
	 * 
	 * @return true/false on completion of unconfiguration.
	 */
	public boolean unconfigureRateLimit() {
		MyLogger.info("Unconfiguring Rate Limit...");
		boolean rateLimitUnconfigureStatus = true;
		try {
			if (page.locator(rateLimitPolicyCheckbox).isChecked()) {
				page.locator(rateLimitPolicyCheckbox).click();
				page.locator(saveBtn).click();
				String saveMessage = getToastMessages();
				MyLogger.info("Save Message: " + saveMessage);
				closeToastMessage();
				waitForToastMessagesClose();
				if (!saveMessage.contains("updated successfully")) {
					rateLimitUnconfigureStatus = false;
				}
			}
		} catch (Exception e) {
			MyLogger.error("Exception while Unconfiguring the Rate Limit: " + e.getMessage(), e);
			rateLimitUnconfigureStatus = false;
		}
		return rateLimitUnconfigureStatus;
	}

	/**
	 * Method used to fetch the details of the selected Tier, which is placed just next to the Tier selection drop-down.
	 * 
	 * @return The Tier configuration details such as Number of Calls, Time Frame.
	 */
	public String getSelectedTierConfiguration() {
		MyLogger.info("Fetching the Selected Tier Configuration.");
		String tierConfiguration = page.locator(selectedTierConfiguration).innerText();
		MyLogger.info("The Selected Tier Configuration: " + tierConfiguration);
		return tierConfiguration;
	}
}
