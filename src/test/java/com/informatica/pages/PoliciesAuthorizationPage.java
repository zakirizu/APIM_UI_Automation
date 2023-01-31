package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Page;

public class PoliciesAuthorizationPage extends BasePage {

	public PoliciesAuthorizationPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	String PoliciesTitle = "//div[@title='Policies']";
	String AuthrozationTabTitle = "//span[text()='Authorization']";
	String Oauth2Heading = "//span[@class='d-section__header__title__tooltip']/div";
	String Oauth2HeadingHelptolltip = "//span[@class='d-section__header__title__tooltip']/div/i";
	String OAuth2CopyURLButton = "//button[@title='Copy URL']";
	String OAuathActualUrl = "(//div[@class='d-section__content'])[2]/p";
	String Oauth2GridHeader = "(//span[@class='d-section__header__title__tooltip']/span)[2]";
	String findSearchBox = "(//input[@placeholder='Find'])[2]";
	String AddOAuth2Button = "//button[@title='Add OAuth 2.0 Client...']";
	String ExportDeletedClient = "//button[@title='Export Deleted Clients']";
	String Auth_password = "//input[@type='password']";
	String NextButton = "//button[@title='Next >']";
	String ClientName = "//input[@placeholder='Enter Client Name']";
	String ClienttipError = "(//div[@data-testid='input-error'])[1]";
	String ClientDesc = "//textarea[@placeholder='Enter Client Description']";
	String AccessTokenTimeOut = "//input[@placeholder='Enter Timeout']";
	String Timetipinmin = "//div[@class='color-dark-gray']";
	String TImetipError = "(//div[@data-testid='input-error'])[2]";
	String APIsAndGropsRadio = "(//input[@type='radio'])[2]";
	String findsearchApis = "(//input[@placeholder='Find'])[4]";
	String OAuthClientID = "(//input[@data-testid='input'])[1]";
	String OAuthClientSecret = "(//input[@data-testid='input'])[2]";
	String OAuthClientCopy = "(//button[@title='Copy'])[1]";
	String OAuthClientSecretCopy = "(//button[@title='Copy'])[2]";
	String OAuthcopyAuthHeader = "//button[@title='Copy Authorization Header Value']";
	String Finishbutton = "//button[@title='Finish']";
	String APIName = "(//tr[@data-id='$protocal_$APIName'])[2]";
	String APiCheckbox = "(//tr[@data-id='REST_APIM_Automation_Basic_OAuth_1.0.0'])[2]/td[2]";
	String ProcessRecordinGrid = "(//span[text()='$APIName'])[1]";
	String APiCheckbox1 = "(//tr[@data-id='$protocal_$APIName_1.0.0'])[2]/td[2]";
	
	
	String OAuth2SercResult="//span[text()='$ClientName']";

	String AuthHeader_popup_Message;
	String OAuth_Client_SaveMsg;
	Boolean Clientidflag = false;
	Boolean Clientsecretflag = false;
	Boolean AuthHeaderflag = false;

	public void Create_OAUTH2_Client(String Pwd, String APIname, String Clientname)
			throws UnsupportedFlavorException, IOException, InterruptedException {
		String Actual_ClientID;
		String Actual_ClientSecret;
		String Copied_ClientID;
		String Copied_ClientSecret;
		String Copied_AuthHeader_Token;
		String APICheckboxUpdated = APiCheckbox1.replace("$APIName", APIname).replace("$protocal", "REST");

		page.locator(AddOAuth2Button).click();
		page.locator(Auth_password).fill(Pwd);
		page.locator(NextButton).click();
		page.locator(ClientName).fill(Clientname);
		page.locator(ClientDesc).fill("OAuth2_Client_Automation Creation");
		page.locator(NextButton).click();
		page.locator(APIsAndGropsRadio).click();
		page.locator(findsearchApis).fill(APIname);

		waitForToastMessagesClose();

		System.out.println(APICheckboxUpdated);

		page.locator(APICheckboxUpdated).click();
		page.locator(NextButton).click();

		OAuth_Client_SaveMsg = getToastMessages();
		System.out.println(OAuth_Client_SaveMsg);
		page.locator(closepopup).click();
		waitForToastMessagesClose();

		Actual_ClientID = page.locator(OAuthClientID).getAttribute("value");
		System.out.println("Actual_ClientID" + Actual_ClientID);

		page.locator(OAuthClientCopy).click();
		Copied_ClientID = GetClipBoardData();
		System.out.println("Copied_ClientID:" + Copied_ClientID);
		closeToastMessage();
		waitForToastMessagesClose();

		Actual_ClientSecret = page.locator(OAuthClientSecret).getAttribute("value");
		System.out.println("Actual_ClientSecret" + Actual_ClientSecret);

		page.locator(OAuthClientSecretCopy).click();
		Copied_ClientSecret = GetClipBoardData();
		closeToastMessage();

		waitForToastMessagesClose();

		System.out.println("Copied_ClientSecret" + Copied_ClientSecret);

		page.locator(OAuthcopyAuthHeader).click();
		Copied_AuthHeader_Token = GetClipBoardData();
		System.out.println("Copied_AuthHeader_Token" + Copied_AuthHeader_Token);

		AuthHeader_popup_Message = getToastMessages();
		System.out.println(AuthHeader_popup_Message);
		closeToastMessage();

		if (Actual_ClientSecret.equalsIgnoreCase(Copied_ClientSecret)) {
			Clientsecretflag = true;
		}

		if (Actual_ClientID.equalsIgnoreCase(Copied_ClientID)) {

			Clientidflag = true;
		}

		if (!Copied_AuthHeader_Token.isEmpty()) {

			AuthHeaderflag = true;
		}

		page.locator(Finishbutton).click();

	}

	public String GetOAuth2SaveMessage() {
		return OAuth_Client_SaveMsg;
	}
	
	public String GetAuthHeaderMessage() {
		return AuthHeader_popup_Message;
	}

	
	public Boolean GetActualandCopiedClientIDFlag() {
		return Clientidflag;
	}

	public Boolean GetActualandCopiedClientSecretFlag() {
		return Clientsecretflag;
	}
	
	public Boolean GetAuthheaderFlag() {
		return AuthHeaderflag;
	}


	public Boolean SearchCreated_OAUTH2_Client(String ClientName)
	{
		Boolean flag=false;
		page.locator(findSearchBox).fill(ClientName);
		String ActualLocator = OAuth2SercResult.replace("$ClientName", ClientName);
		if (page.locator(ActualLocator).count() > 0 )
		{
			flag= true;
		}
		page.locator(findSearchBox).fill("");
		return flag;
	}

	

	
}
