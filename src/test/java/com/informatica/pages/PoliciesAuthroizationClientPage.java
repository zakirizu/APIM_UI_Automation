package com.informatica.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.informatica.web.util.MyLogger;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PoliciesAuthroizationClientPage extends BasePage {

	public PoliciesAuthroizationClientPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	String OAuth2SercResult = "//span[text()='$ClientName']";
	String findSearchBox = "(//input[@placeholder='Find'])[2]";
	String Kebabmenu = "(//button[@class='d-version-2 d-icon-button d-icon-button--call-to-action'])[3]";
	String KeababMenuOptions = "//ul[@class='d-menu d-version-2']/li";
	String EditButton = "//ul[@class='d-menu d-version-2']/li[text()='Edit']";
	String DeleteButton = "//ul[@class='d-menu d-version-2']/li[text()='Delete Client']";
	String clientName_Editmode = "//input[@placeholder='Enter Name']";
	String clientDesc_Editmode = "//textarea[@placeholder='Enter Description']";
	String ClientAccessToken_Editmode = "//input[@placeholder='Enter Access Token Timeout']";
	String saveButton = "//button[@title='Save']";
	String EditClose  ="//i[@class='aicon aicon__close-solid']";
	String DeleteClientBUtton="//button[@title='Delete']";
	String Client_Edit_SaveMsg;
	String Client_Delete_Msg;

	public void ClickonKebabMenuEditOption(String ClientName,String EditOption)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		MyLogger.info("Clicking on  KebabMenu");
		page.locator(findSearchBox).fill(ClientName);
		String APINameUpdatedName = OAuth2SercResult.replace("$ClientName", ClientName);
		System.out.println(APINameUpdatedName);
		Locator src = page.locator(APINameUpdatedName);
		waitForMouseActions();
		page.mouse().move(src.boundingBox().x, src.boundingBox().y);
		page.locator(Kebabmenu).click();

		page.locator(EditOption).click();

		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

	public String EditClientDetailsAndSave(String ClientName, String updclientName, String updClientDesc, String updAccessToken)
			throws InterruptedException, UnsupportedFlavorException, IOException {
		ClickonKebabMenuEditOption(ClientName,EditButton);
		page.locator(clientName_Editmode).fill(updclientName);
		page.locator(clientDesc_Editmode).fill(updClientDesc);
		page.locator(ClientAccessToken_Editmode).fill(updAccessToken);
		page.locator(saveButton).click();
		Client_Edit_SaveMsg = getToastMessages();
		System.out.println(Client_Edit_SaveMsg);
		closeToastMessage();
		waitForToastMessagesClose();
		return Client_Edit_SaveMsg;

	}
	
	public String DeleteOAuth2(String ClientName)
			throws InterruptedException, UnsupportedFlavorException, IOException {
		ClickonKebabMenuEditOption(ClientName,DeleteButton);
		page.locator(DeleteClientBUtton).click();
	
		Client_Delete_Msg = getToastMessages();
		System.out.println(Client_Delete_Msg);
		closeToastMessage();
		waitForToastMessagesClose();
		return Client_Delete_Msg;

	}
	
	
	public String postupdateClientname()
	{
		String postupdateClientname=page.locator(clientName_Editmode).getAttribute("value");
		System.out.println(postupdateClientname);
		return postupdateClientname;
	}

	public String postupdateClientDescription()
	{
		String postupdateClientdesc=page.locator(clientDesc_Editmode).textContent();
		System.out.println(postupdateClientdesc);
		return postupdateClientdesc;
	}
	
	public String postupdateClientToken()
	{
		String postupdateClientToken=page.locator(ClientAccessToken_Editmode).getAttribute("value");
		System.out.println(postupdateClientToken);
		return postupdateClientToken;
	}
	
	public String postupdateSaveDisabled()
	{
		String postupdateSave=page.locator(saveButton).getAttribute("aria-disabled");
		return postupdateSave;
	}
	
	public void closeEditPage()
	{
		page.locator(EditClose).click();
	}
}
