package com.informatica.web.util;

import java.io.File;



public interface Constants {
	
	String BASE_URL = "baseUrl";
	String PROJECT_PATH = System.getProperty("user.dir");
	String CONFIGURATION_FILE_PATH = PROJECT_PATH + File.separator + "Config" + File.separator + "configuration.properties";
	String LOG4J_FILE_PATH = PROJECT_PATH + File.separator+"src/test/resources/log4j2.properties";
	String LOG_FILES_LOCATION =  PROJECT_PATH+ File.separator +"Loggers"+File.separator;
	String LOGGER_FOLDER =  PROJECT_PATH+ File.separator +"logs";
	String SCREENSHOTS_FOLDER  = PROJECT_PATH + File.separator + "Screenshots";
	String EXECUTION_RECORDING_VEDIO_FOLDER  = PROJECT_PATH + File.separator + "Vedios";
	String TESTNG_FOLDER  = PROJECT_PATH + File.separator + "TestNg";
	String ENVIRONEMNT_DETAILS  = PROJECT_PATH + File.separator + "EnvironmentDetails" + File.separator+"POD_Details.xlsx";
	String REPORTS_PATH  = PROJECT_PATH + File.separator + "Reports";
	String CONFIG_PATH  = PROJECT_PATH + File.separator + "Config";
	
	

	
	String ENV_URL="" ;
	char quotes ='"';
	String APIName="APIM_Automation_Basic";
	String Protocol="REST";
	
	// Enable Privacy related constants
	String QUERYPARAM = "?Email=test@gmail.com";
	String BLOCK_MESSAGE = "Blocked: The message was blocked because of a potential privacy policy breach in the response. Details: email address. 1 item. first item: /EmailOp";
	String WARNNG_MESSAGE = "Warning: There was a privacy policy leakage in the response. Details: email address. 1 item. first item: /EmailOp";
	
	//App config messages or Help tooltips
	String MANAGED_API_NAME_HOVER = "You cannot rename the API because it is active, is the default version of a multi-version API, or both. To rename the API, verify that it is deactivated and that it is not the default version of a multi-version API.";
	//Dropdown Options in API Registry
	String ALL_SERVICES_DROPDOWN = "All Services";
	String AVAILABLE_SERVICES_DROPDOWN = "Available Services";
	String MANAGED_APIS_DROPDOWN = "Managed APIs";
String MANAGED_API_NAME= "";
	// OAuth 2.0 New Client Creation
	String OAUTH2_CLIENT_NAME="Oauth2_Client_Automation";
	String OAUTH2_CLIENT_DESCRIPTION ="Creating oauth2 client from Automation";

	String OAUTH2_CLIENT_SAVE_MESSAGE="Client "+quotes+Constants.OAUTH2_CLIENT_NAME+quotes+" created successfully.";
	
	String OAUTH2_AUTH_HEADER_MESSAGE="Encoded OAuth 2.0 Client ID, OAuth 2.0 Client Secret copied to clipboard.";
	
	// OAuth 2.0 Client Updation
	
	String OAUTH2_CLIENT_UPDATED_NAME="Oauth2_Client_Automation_updated";
	String OAUTH2_CLIENT_UPDATED_DESC="Updating oauth2 client description from Automation";
	String OAUTH2_CLIENT_UPDATED_ACCESSTOKEN="61";
	String OAUTH2_CLIENT_UPDATE_MESSAGE="Client "+quotes+Constants.OAUTH2_CLIENT_NAME+quotes+" updated successfully.";
	
	// OAuth2.0 Client Delete 
	String OAUTH2_CLIENT_DELETE_MESSAGE="Client "+quotes+Constants.OAUTH2_CLIENT_UPDATED_NAME+quotes+" deleted successfully.";
	
	
	// Custom API 
	String CUST_API_NAME= "CustomAPI_Automation";
	String CUST_API_URL= "https://reqres.in/api/users";
	String CUST_API_DESC= "Creating Custom API from Automation";
	String CUST_TEST_BUTTON_PASS= "URL test succeeded.";
	String CUST_API_SAVE_MESSAGE = "The Managed API "+quotes+Constants.CUST_API_NAME+quotes+" was created successfully.";
	String CUST_API_UPD_MESSAGE ="The Managed API "+quotes+Constants.CUST_API_NAME+quotes+" was updated successfully.";
	String IP_ADDRESS_FROM = "1.1.1.1";
	String IP_ADDRESS_TO = "2.2.2.2";
	String CUST_API_GRP_NAME= "APIM_Automation_Grp";
	String CUST_API_GRP_SAVE ="The selected Managed APIs was added successfully to Group "+quotes+Constants.CUST_API_GRP_NAME+quotes+".";
	
	//constants related to ORG Level ip filter
			String ORG_IPFILTER_ALLOW="Allow";
			String ORG_FROM_RANGE="0.0.0.0";
			String ORG_TO_RANGE="255.255.255.255";
			String ORG_RATELIMIT="1000";
			String UPDATE_ORG_RATELIMIT="1500";

	
	
}

