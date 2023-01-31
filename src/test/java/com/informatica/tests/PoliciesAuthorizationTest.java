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
import com.informatica.pages.APIGroupsPage;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.LoginPage;
import com.informatica.pages.PoliciesAuthorizationPage;
import com.informatica.pages.PoliciesAuthroizationClientPage;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.MyLogger;
import com.informatica.web.util.Constants;

public class PoliciesAuthorizationTest extends BaseTest {

	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	PoliciesAuthorizationPage policiesAuth = null;
	PoliciesAuthroizationClientPage policiesAuthClient = null;


	@BeforeTest
	public void init() throws Exception {

		MyLogger.startLogger("PoliciesAuthorizationTest");

		try {
			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			apimpage.ClickonPolicies();
			apimpage.ClickonAuthorization();
			policiesAuth = new PoliciesAuthorizationPage(page);
			policiesAuthClient = new PoliciesAuthroizationClientPage(page);

		} catch (Exception e) {
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	@Test(dataProvider = "getData", priority = 1)
	public void ValdiateCreateOAuth2Client(Object data) {

		loginfo.log(Status.PASS, "Cliking on Create OAuth2 Client ");
		MyLogger.info("================= ValdiateCreateOAuth2Client : START ================== ");
		try {
		
			policiesAuth.Create_OAUTH2_Client(adminPassword, getAdditionalDetails(data, "Processname"),
					Constants.OAUTH2_CLIENT_NAME);

			MyLogger.info("Validating the save Message");
			Assert.assertEquals(policiesAuth.GetOAuth2SaveMessage(), Constants.OAUTH2_CLIENT_SAVE_MESSAGE);
			loginfo.log(Status.PASS, "Validating Save Messages");

			MyLogger.info("Validating the Copy Id Button");
			Assert.assertTrue(policiesAuth.GetActualandCopiedClientIDFlag());
			loginfo.log(Status.PASS, "Validating the Copy Id Button");

			MyLogger.info("Validating the Copy Secret");
			Assert.assertTrue(policiesAuth.GetActualandCopiedClientSecretFlag());
			loginfo.log(Status.PASS, "Validating the Copy Secret");

			MyLogger.info("Validating the Header Message");
			Assert.assertEquals(policiesAuth.GetAuthHeaderMessage(), Constants.OAUTH2_AUTH_HEADER_MESSAGE);
			loginfo.log(Status.PASS, "Validating the Header Message");

			MyLogger.info("Validating the Oauth HEader token content");
			Assert.assertTrue(policiesAuth.GetAuthheaderFlag());
			loginfo.log(Status.PASS, "Validating the Oauth HEader token content");

			MyLogger.info("Validating the Search for Created Client");
			Assert.assertTrue(policiesAuth.SearchCreated_OAUTH2_Client(Constants.OAUTH2_CLIENT_NAME));
			loginfo.log(Status.PASS, "Validating the Search for Created Client");

			loginfo.log(Status.PASS, "Able to Create OAuth2 Client");
		} catch (Exception e) {
			MyLogger.error("Exception while validating Create OAuth2 Client : " + e);
			loginfo.log(Status.FAIL, "validating Create OAuth2 Client");
			Assert.fail("Exception while validating Create OAuth2 Client");
		}

		MyLogger.info("================= ValdiateCreateOAuth2Client : END ================== ");

	}

	@Test( priority = 2)
	public void ValdiateEditOAuth2Client() {

		loginfo.log(Status.PASS, "Updating the  OAuth2 Client ");
		MyLogger.info("================= ValdiateUpdateOAuth2Client : START ================== ");
		try {
		

			String ActualUpdatedMessage = policiesAuthClient.EditClientDetailsAndSave(Constants.OAUTH2_CLIENT_NAME,
					Constants.OAUTH2_CLIENT_UPDATED_NAME, Constants.OAUTH2_CLIENT_UPDATED_DESC,
					Constants.OAUTH2_CLIENT_UPDATED_ACCESSTOKEN);

			MyLogger.info("Validating the EditAndSaveOauth2 Message");
			Assert.assertEquals(ActualUpdatedMessage, Constants.OAUTH2_CLIENT_UPDATE_MESSAGE);
			loginfo.log(Status.PASS, "Validating the EditAndSaveOauth2 Message");

			MyLogger.info("Validating post update Save is Disabled ");
			Assert.assertEquals(policiesAuthClient.postupdateSaveDisabled(), "true");
			loginfo.log(Status.PASS, "Validating post update Save is Disabled ");

			MyLogger.info("Validating post update Client name Updated succesfully");
			Assert.assertEquals(policiesAuthClient.postupdateClientname(), Constants.OAUTH2_CLIENT_UPDATED_NAME);
			loginfo.log(Status.PASS, "Validating post update Client name Updated succesfully");

			MyLogger.info("Validating post update Client Description Updated succesfully");
			Assert.assertEquals(policiesAuthClient.postupdateClientDescription(), Constants.OAUTH2_CLIENT_UPDATED_DESC);
			loginfo.log(Status.PASS, "Validating post update Client Description Updated succesfully");

			MyLogger.info("Validating post update Client Token Updated succesfully");
			Assert.assertEquals(policiesAuthClient.postupdateClientToken(),
					Constants.OAUTH2_CLIENT_UPDATED_ACCESSTOKEN);
			loginfo.log(Status.PASS, "Validating post update Client Token Updated succesfully");

			policiesAuthClient.closeEditPage();

			loginfo.log(Status.PASS, "Able to Update OAuth2 Client");
		} catch (Exception e) {
			MyLogger.error("Exception while validating Create OAuth2 Client : " + e);
			loginfo.log(Status.FAIL, "validating Create OAuth2 Client");
			Assert.fail("Exception while validating Create OAuth2 Client");
		}

		MyLogger.info("================= ValdiateUpdateOAuth2Client : END ================== ");

	}

	@Test(priority = 3)
	public void ValdiateDeleteOAuth2Client() {

		loginfo.log(Status.PASS, "Deleting the  OAuth2 Client ");
		MyLogger.info("================= ValdiateCreateOAuth2Client : START ================== ");
		try {
		

			apimpage.ClickonPolicies();
			apimpage.ClickonAuthorization();
			String Actual_Delete_meesage = policiesAuthClient.DeleteOAuth2(Constants.OAUTH2_CLIENT_UPDATED_NAME);
			String Expected_Delete_meesage = Constants.OAUTH2_CLIENT_DELETE_MESSAGE;
			loginfo.log(Status.INFO, "Actual_Delete_meesage" + Actual_Delete_meesage);
			loginfo.log(Status.INFO, "Expected_Delete_meesage" + Expected_Delete_meesage);

			Assert.assertEquals(Actual_Delete_meesage, Expected_Delete_meesage);
			loginfo.log(Status.PASS, "Validating Delete Messages");

			loginfo.log(Status.PASS, "Able to Deleting OAuth2 Client");
		} catch (Exception e) {
			MyLogger.error("Exception while validating Deleting OAuth2 Client : " + e);
			loginfo.log(Status.FAIL, "validating Deleting OAuth2 Client");
			Assert.fail("Exception while validating Deleting OAuth2 Client");
		}

		MyLogger.info("================= ValdiateCreateOAuth2Client : END ================== ");

	}

}
