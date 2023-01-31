package com.informatica.tests;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.informatica.pages.APIGroupsPage;
import com.informatica.pages.APIMRegistryPage;
import com.informatica.pages.LoginPage;
import com.informatica.web.util.MyLogger;

public class APIMGroupsRegressionScenarios extends BaseTest {

	LoginPage loginPage = null;
	APIMRegistryPage apimpage = null;
	APIGroupsPage apigrp = null;

	@BeforeTest
	public void init() throws Exception {
		MyLogger.startLogger("APIGroupsTest");
	try {
			page = getPage(this.getClass().getSimpleName());
			loginToIICS(page);
			loginPage = new LoginPage(page);
			loginPage.ClickonAPIMChiklet();
			apimpage = new APIMRegistryPage(page);
			apigrp = new APIGroupsPage(page);
		}
	catch (Exception e) 
		{
			MyLogger.error("Exception while navigating to APIM  : " + e);
			throw e;
		}
	}

	
	@Test(dataProvider = "getData", enabled=false)
	public void creationOfGroupFromAPIGroupTab_TC01(Object data) {
		loginfo.log(Status.INFO, "================= Creation of Group From API Groups Tab with all Mandatory Fields : START ================== ");
		MyLogger.info("================= Creation of Group From API Groups Tab with all Mandatory Fields : START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext = apigrp.UniqueContextNameWithTimeStamp();
			String apiGroupDescription = parameters.get("apiGroupDescription");
			String toastMessageAfterCreationOfGroup = apigrp.CreationOfGroupWithNoAPIsAddedToGroup(apiGroupName, apiGroupContext,	apiGroupDescription );
			Assert.assertEquals(toastMessageAfterCreationOfGroup,"The Group \"" + apiGroupName + "\" was created successfully.","checking the ToastMessage of the Group Creation");
			MyLogger.info("Group Creation for " + apiGroupName + " is Completed Successfully");
			loginfo.log(Status.INFO, "Group Creation for " + apiGroupName + " is Completed Successfully");
			MyLogger.info("the Group creation is completed for Group" + apiGroupName);
			loginfo.log(Status.PASS, "Group Creation for " + apiGroupName + " is Completed Successfully");
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while validating Creation of Groups From API Grops Tab with all Mandatory Fields : " + e);
			loginfo.log(Status.FAIL, "Exception while validating Creation of Group From API Grops Tab with all Mandatory Fields : " + e);
			Assert.fail("Exception while validating Creation of Group From API Group Tab with all Mandatory Fields");
		}
			loginfo.log(Status.INFO, "================= Creation of Group From API Group Tab with all Mandatory Fields : END ================== ");
			MyLogger.info("================= Creation of Group From API Group Tab with all Mandatory Fields : END ================== ");

	}
	
	@Test(dataProvider = "getData", enabled = false)
	public void creationOfGroupFromAPIAddToGroupButton_TC02(Object data) {
		loginfo.log(Status.INFO, "================= Creation of Group From API Details Tab 'Add To Group' Button  with all Mandatory Fields : START ================== ");
		MyLogger.info("================= Creation of Group From API Details Tab 'Add To Group' Button  with all Mandatory Fields : START ================== ");
		try 
		{
			apimpage.clickonAPIRegistry();
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext = apigrp.UniqueContextNameWithTimeStamp();
			String apiGroupDescription = parameters.get("apiGroupDescription");
			String toastMessageAfterCreationOfGroup = apigrp.CreationOfGroupFromAPITabAddToGroupButton(apiGroupName, apiGroupContext,	apiGroupDescription );
			Assert.assertEquals(toastMessageAfterCreationOfGroup,"The Group \"" + apiGroupName + "\" was created successfully.","checking the ToastMessage of the Group Creation");
			MyLogger.info("Group Creation for " + apiGroupName + " is Completed Successfully");
			loginfo.log(Status.INFO, "Group Creation for " + apiGroupName + " is Completed Successfully");
			MyLogger.info("the Group creation is completed for Group" + apiGroupName);
			loginfo.log(Status.PASS, "Group Creation for " + apiGroupName + " is Completed Successfully");
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while validating Creation of Group From API Group Tab with all Mandatory Fields : " + e);
			loginfo.log(Status.FAIL, "Exception while validating Creation of Group From API Grops Tab with all Mandatory Fields : " + e);
			Assert.fail("Exception while validating Creation of Group From API Group Tab with all Mandatory Fields");
		}
			loginfo.log(Status.INFO, "================= Creation of Group From API Details Add To Group Button with all Mandatory Fields  : END ================== ");
			MyLogger.info("================= Creation of Group From API Details Add To Group Button with all Mandatory Fields  : END ================== ");

	}
	
	@Test(dataProvider = "getData", enabled=false)
	public void addAPIstoGroupCreatedFrom_TC03(Object data) {
		loginfo.log(Status.INFO, "================= Creation of Group From API Groups Tab with all Mandatory Fields : START ================== ");
		MyLogger.info("================= Creation of Group From API Groups Tab with all Mandatory Fields : START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext = apigrp.UniqueContextNameWithTimeStamp();
			String apiGroupDescription = parameters.get("apiGroupDescription");
			String managedAPI = parameters.get("");
			
			String toastMessageAfterCreationOfGroup = apigrp.CreationOfGroupWithNoAPIsAddedToGroup(apiGroupName, apiGroupContext,	apiGroupDescription );
			Assert.assertEquals(toastMessageAfterCreationOfGroup,"The Group \"" + apiGroupName + "\" was created successfully.","checking the ToastMessage of the Group Creation");
			MyLogger.info("Group Creation for " + apiGroupName + " is Completed Successfully");
			loginfo.log(Status.INFO, "Group Creation for " + apiGroupName + " is Completed Successfully");
			MyLogger.info("the Group creation is completed for Group" + apiGroupName);
			loginfo.log(Status.PASS, "Group Creation for " + apiGroupName + " is Completed Successfully");
		
			loginfo.log(Status.INFO, "Deleting Test Data - Removing Group " + apiGroupName);
			apigrp.addAPIstoGroup(apiGroupName, managedAPI);
			loginfo.log(Status.PASS, "Added APIs to " + apiGroupName);
			
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while validating Creation of Groups From API Grops Tab with all Mandatory Fields : " + e);
			loginfo.log(Status.FAIL, "Exception while validating Creation of Group From API Grops Tab with all Mandatory Fields : " + e);
			Assert.fail("Exception while validating Creation of Group From API Group Tab with all Mandatory Fields");
		}
			loginfo.log(Status.INFO, "================= Creation of Group From API Group Tab with all Mandatory Fields : END ================== ");
			MyLogger.info("================= Creation of Group From API Group Tab with all Mandatory Fields : END ================== ");

	}	
	@Test(dataProvider = "getData", enabled = false)
	public void groupNameDoesNotAcceptRestrictedCharecters_TC24(Object data) {
		loginfo.log(Status.INFO, "================= Validating Group Name Does Not Accept Special Characters : START ================== ");
		MyLogger.info("================= Validating Group Name Does Not Accept Special Characters : START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName = parameters.get("charsNotAllowed");
			apigrp.groupNameValidtionCharsNotAllowed(apiGroupName);			
		}
		catch (Exception e) {
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Validating Group Name should Not Accept Special Characters : " + e);
			loginfo.log(Status.FAIL, "Validating Group Name Does Not Accept Special Characters : " + e);
			Assert.fail("Validating Group Name Does Not Accept Special Characters");
		}
			loginfo.log(Status.INFO, "================= Validating Group Name Does Not Accept Special Characters  : END ================== ");
			MyLogger.info("================= Validating Group Name Does Not Accept Special Characters  : END ================== ");
		}
	
	@Test(dataProvider = "getData", enabled = false)
	public void groupConectDoesNotAcceptCharT_TC53(Object data) {
		loginfo.log(Status.INFO, "================= Validating System Restricts User using /t in context : START ================== ");
		MyLogger.info("================= Validating System Restricts User using /t in context : START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupContex = parameters.get("charsNotAllowed");
			apigrp.groupContextValidation(apiGroupContex);			
			}
		catch (Exception e) {
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception : System is accepting /t in Context Which is not expected : " + e);
			loginfo.log(Status.FAIL, "System is accepting /t in Context Which is not expected :  " + e);
			Assert.fail("System is accepting /t in Context Which is not expected");
		}
			loginfo.log(Status.INFO, "================= Validating System Restricts User using /t in context  : END ================== ");
			MyLogger.info("================= Validating System Restricts User using /t in context  : END ================== ");
		}
	
	
	
	@Test(dataProvider = "getData", enabled=false)
	public void validateAPIURLincludesContext_TC27(Object data) {
		loginfo.log(Status.INFO, "================= Validating context is part of API URL : START ================== ");
		MyLogger.info("=================Validating context is part of API URL: START ================== ");
		try
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 		= apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext 		= apigrp.UniqueContextNameWithTimeStamp();
			String managedAPIName 		= parameters.get("apiName");
			String managedAPIprotocol = parameters.get("apiProtocol");
			String urlFromCopyURLButton = apigrp.createGroupWithContextAddAPIsAndReturnAPIurlPartOfGroup(apiGroupName, apiGroupContext,	managedAPIName, managedAPIprotocol );
			Assert.assertEquals(true,urlFromCopyURLButton.contains(apiGroupContext) , "Group Context is part of the API URL");
			MyLogger.info("Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			loginfo.log(Status.PASS, "Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) {
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Checking if context is part of API URL : " + e);
			loginfo.log(Status.FAIL, "Exception while Checking if context is part of API URL : " + e);
			Assert.fail("Exception while Checking if context is part of API URL");
			}
			loginfo.log(Status.INFO, "================= Validating context is part of API URL: END ================== ");
			MyLogger.info("================= Validating context is part of API URL : END ================== ");
		}
	
	
	@Test(dataProvider = "getData", enabled=false)
	public void validateAPIURLDoesNotincludesContext_TC29(Object data) {
		loginfo.log(Status.INFO, "================= Validating context is part of API URL : START ================== ");
		MyLogger.info("=================Validating context is part of API URL: START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 		= apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext 		= apigrp.UniqueContextNameWithTimeStamp();
			String managedAPIName 		= parameters.get("apiName");
			String managedAPIprotocol = parameters.get("apiProtocol");
			String urlFromCopyURLButton = apigrp.createGroupWithContextAddAPIsAndReturnAPIurlPartOfGroup(apiGroupName, apiGroupContext,	managedAPIName, managedAPIprotocol );
			Assert.assertEquals(true,urlFromCopyURLButton.contains(apiGroupContext) , "Group Context is part of the API URL");
			MyLogger.info("Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			loginfo.log(Status.INFO, "Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			MyLogger.info("Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			loginfo.log(Status.PASS, "Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);			
			apigrp.clickOnRemoveFromGroup();
			apigrp.clickOnRemoveButtonOnWarningPopUp();
			String APIurl = apigrp.returnCopiedURLFromCopyURL();
			Assert.assertEquals(false,APIurl.contains(apiGroupContext) , "Group Context is Not part of the API URL");
			MyLogger.info("Group Context " + apiGroupContext + " is Not Part of API URL "+ APIurl);
			loginfo.log(Status.INFO, "Group Context " + apiGroupContext + " is Not Part of API URL "+ APIurl);
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);				
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Checking if context is part of API URL : " + e);
			loginfo.log(Status.FAIL, "Exception while Checking if context is part of API URL : " + e);
			Assert.fail("Exception while Checking if context is part of API URL");
		}
			loginfo.log(Status.INFO, "================= Validating context is part of API URL: END ================== ");
			MyLogger.info("================= Validating context is part of API URL : END ================== ");
		}
	
	@Test(dataProvider = "getData", enabled=false)
	public void updatedContextIsIncludedinAPIURL_TC30(Object data) {
		loginfo.log(Status.INFO, "================= Validating Updated context is part of API URL : START ================== ");
		MyLogger.info("=================Validating Updated context is part of API URL: START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 		= apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext 		= apigrp.UniqueContextNameWithTimeStamp();
			String managedAPIName 		= parameters.get("apiName");
			String managedAPIprotocol = parameters.get("apiProtocol");
			String updatedContext = parameters.get("updatedContext");
			
			apigrp.removeAPIFromGroup(managedAPIName);
					
			String urlFromCopyURLButton = apigrp.createGroupWithContextAddAPIsAndReturnAPIurlPartOfGroup(apiGroupName, apiGroupContext,	managedAPIName, managedAPIprotocol );
			Assert.assertEquals(true,urlFromCopyURLButton.contains(apiGroupContext) , "Group Context is part of the API URL");			
			MyLogger.info("Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			loginfo.log(Status.PASS, "Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);	
			
			MyLogger.info("Updating the Group Context");
			apigrp.updateGroupContext(apiGroupName,apiGroupContext, updatedContext);
			
			String APIurl = apigrp.returnURLofAPI(managedAPIName);
			Assert.assertEquals(true,APIurl.contains(apiGroupContext+updatedContext) , "Group Context is Not part of the API URL");
			
			MyLogger.info("Updated Group Context: " + apiGroupContext+updatedContext + " is Now reflected in API URL: "+ APIurl);
			loginfo.log(Status.INFO, "Updated Group Context " + apiGroupContext+updatedContext + " is Now reflected in API URL "+ APIurl);
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);				
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Checking if context is part of API URL : " + e);
			loginfo.log(Status.FAIL, "Exception while Checking if context is part of API URL : " + e);
			Assert.fail("Exception while Checking if context is part of API URL");
		}
			loginfo.log(Status.INFO, "================= Validating Updated context is part of API URL: END ================== ");
			MyLogger.info("================= Validating Updated context is part of API URL : END ================== ");
		}	
	

	@Test(dataProvider = "getData", enabled=false)
	public void updatedContextIsNotIncludedinAPIURLNotPartOfGroup_TC31(Object data) {
		loginfo.log(Status.INFO, "================= Validating Updated context is Not part of API URL That is not that Group: START ================== ");
		MyLogger.info("================= Validating Updated context is Not part of API URL That is not that Group: START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 		= apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext 		= apigrp.UniqueContextNameWithTimeStamp();
			String managedAPIName 		= parameters.get("apiName");
			String managedAPIprotocol = parameters.get("apiProtocol");
			String updatedContext = parameters.get("updatedContext");
			
			apigrp.removeAPIFromGroup(managedAPIName);
					
			String urlFromCopyURLButton = apigrp.createGroupWithContextAddAPIsAndReturnAPIurlPartOfGroup(apiGroupName, apiGroupContext,	managedAPIName, managedAPIprotocol );
			Assert.assertEquals(true,urlFromCopyURLButton.contains(apiGroupContext) , "Group Context is part of the API URL");			
			MyLogger.info("Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);
			loginfo.log(Status.PASS, "Group Context " + apiGroupContext + " is Part of API URL "+ urlFromCopyURLButton);	
			
		
			MyLogger.info("Updating the Group Context");
			apigrp.updateGroupContext(apiGroupName,apiGroupContext, updatedContext);
			MyLogger.info("Removing API from the Group");
			apigrp.removeAPIFromGroup(managedAPIName);
			MyLogger.info("Successfully Removed API from the Group");
			

			MyLogger.info("Removing API from the Group");
			String APIurl = apigrp.returnURLofAPI(managedAPIName);
			Assert.assertEquals(false,APIurl.contains(apiGroupContext+updatedContext) , "Group Context is Not part of the API URL");
			
			MyLogger.info("Updated Group Context: " + apiGroupContext+updatedContext + " is not reflected in API URL that is not part of the group: "+ APIurl);
			loginfo.log(Status.INFO, "Updated Group Context  " + apiGroupContext+updatedContext + " is Not reflected in API URL that is Not Part of Group "+ APIurl);
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);				
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Checking if context is Not visible of API URL : " + e);
			loginfo.log(Status.FAIL, "Exception while Checking if context is Not part of API URL : " + e);
			Assert.fail("Exception while Checking if context is not part of API URL");
		}
			loginfo.log(Status.INFO, "================= Validating Updated context is Not part of API URL That is not that Group: END ================== ");
			MyLogger.info("================= Validating Updated context is Not part of API URL That is not that Group: END ================== ");
		}	
	
	

	@Test(dataProvider = "getData", enabled=false)
	public void ifContextIsRemovedNotIncludedinAPIURL_TC32(Object data) {
		loginfo.log(Status.INFO, "================= Validating removed Context is not part of API URL part of that group : START ================== ");
		MyLogger.info("================= Validating removed Context is not part of API URL part of that group : START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 			= apigrp.UniqueGroupNameWithTimeStamp();
			String apiGroupContext 			= apigrp.UniqueContextNameWithTimeStamp();
			String managedAPIName 			= parameters.get("apiName");
			String managedAPIprotocol		= parameters.get("apiProtocol");
			String updatedContext 			= "remove";
			
			apigrp.removeAPIFromGroup(managedAPIName);					
			apigrp.createGroupWithContextAddAPIsAndReturnAPIurlPartOfGroup(apiGroupName, apiGroupContext,	managedAPIName, managedAPIprotocol );
			apigrp.updateGroupContext(apiGroupName,apiGroupContext, updatedContext);
			MyLogger.info("Removing API from the Group");
			String APIurl = apigrp.returnURLofAPI(managedAPIName);
			
			Assert.assertEquals(false,APIurl.contains(apiGroupContext+updatedContext) , "Group Context is Not part of the API URL");
			
			MyLogger.info("Group Context "+apiGroupContext +" is not reflected in API URL" +APIurl);
			loginfo.log(Status.INFO, "Group Context "+apiGroupContext +"is not reflected in API URL" +APIurl);
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);				
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Checking if removed context is Not visible of API URL : " + e);
			loginfo.log(Status.FAIL, "Exception while Checking if removed context is Not part of API URL : " + e);
			Assert.fail("Exception while Checking if removed context is not part of API URL");
		}
			loginfo.log(Status.INFO,"================= Validating removed Context is not part of API URL part of that group : END ================== ");
			MyLogger.info("================= Validating removed Context is not part of API URL part of that group : END ================== ");
		}	
	
	@Test(dataProvider = "getData", enabled=true)
	public void groupConectAcceptsFrom1to65_TC20(Object data) {
		loginfo.log(Status.INFO, "================= Group Context Range Validation 1 to 65: START ================== ");
		MyLogger.info("================= Group Context Range Validation 1 to 65: START ================== ");
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 		= apigrp.UniqueGroupNameWithTimeStamp();
			String uniqueContext 		= apigrp.UniqueContextNameWithTimeStamp();
			String apiGroupContext 		= parameters.get("Context");
			String apiGroupDescription 	= parameters.get("groupDescription");
			String updatedContext = parameters.get("updatedContext");
			apigrp.CreationOfGroupWithNoAPIsAddedToGroup(apiGroupName, uniqueContext,	apiGroupDescription );			
			MyLogger.info("Validting the Group accepts chars in the given range ");
			apigrp.checkGroupContextAccepts1to65(apiGroupName,apiGroupContext, updatedContext);
			MyLogger.info("Removing API from the Group");
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);				
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Group Context Range Validation 1 to 65 : " + e);
			loginfo.log(Status.FAIL, "Group Context Range Validation 1 to 65 : " + e);
			Assert.fail("Exception Group Context Range Validation 1 to 65");
		}
			loginfo.log(Status.INFO, "================= Validating Group Context Range Validation 1 to 65: END ================== ");
			MyLogger.info("================= Group Context Range Validation 1 to 65: END ================== ");
		}	
	
	
	@Test(dataProvider = "getData", enabled=false)
	public void contextShouldNotBeSameAsGroupName_TC21(Object data) {
		loginfo.log(Status.INFO, "================= Group Context Range Validation 1 to 65: START ================== ");
		MyLogger.info("================= Group Context Range Validation 1 to 65: START ================== ");	
		try 
		{
			Map<String, String> parameters = getAdditionalDetails(data);
			String apiGroupName 		= apigrp.UniqueGroupNameWithTimeStamp();
			String uniqueContext 		= apigrp.UniqueContextNameWithTimeStamp();
			String apiGroupDescription 	= parameters.get("groupDescription");
			
			apigrp.CreationOfGroupWithNoAPIsAddedToGroup(apiGroupName, uniqueContext,	apiGroupDescription );			
			
			
			MyLogger.info("Removing API from the Group");
			String toastMessageAfterDeletionOfGroup = apigrp.removeAPIGroup(apiGroupName);				
			Assert.assertEquals(toastMessageAfterDeletionOfGroup,"The Group \"" + apiGroupName + "\" was deleted successfully.","Deletion of the Group " + apiGroupName + " Failed");
		}
		catch (Exception e) 
		{
			apimpage.clickonAPIRegistry();
			page.reload();
			MyLogger.error("Exception while Group Context Range Validation 1 to 65 : " + e);
			loginfo.log(Status.FAIL, "Group Context Range Validation 1 to 65 : " + e);
			Assert.fail("Exception Group Context Range Validation 1 to 65");
		}
			loginfo.log(Status.INFO, "================= Validating Group Context Range Validation 1 to 65: END ================== ");
			MyLogger.info("================= Group Context Range Validation 1 to 65: END ================== ");
		}
	
	
	
	
	}

