package com.informatica.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import com.informatica.tests.LoginTest;

public class ReportsTestValidator {

	//static Logger logger = Logger.getLogger(ReportsTestValidator.class);

	
	
	public static List<String> getExpectedHeadersAPIMRegistery(){
		
		List<String> expectedHeaders = new ArrayList<String>();
		expectedHeaders.add("Name");
		expectedHeaders.add("Version");
		expectedHeaders.add("Service Name");
		expectedHeaders.add("Protocol");
		expectedHeaders.add("Status");
		expectedHeaders.add("Authentication Method");
		expectedHeaders.add("Group");
		expectedHeaders.add("Description");
		MyLogger.info("Expected Response of APIM Registery headers : " + expectedHeaders);
		return expectedHeaders;
	}
	
public static List<String> getExpectedHeadersAPIMGroups(){
		
		List<String> expectedHeaders = new ArrayList<String>();
		expectedHeaders.add("Name");
		expectedHeaders.add("Context");
		expectedHeaders.add("Description");
		MyLogger.info("Expected Response of APIM Registery headers : " + expectedHeaders);
		return expectedHeaders;
	}
	

/**
 * Method to return "Top Api" expected headers.
 * 
 * @return
 */
public static  List<String> getExpectedTopApiHeaders() {
	List<String> headers = new ArrayList<>();
	headers.add("API Name");
	headers.add("API URL");
	headers.add("Protocol");
	headers.add("Invocations");
	return headers;
}

/**
 * Method to return "Top Api" expected headers.
 * 
 * @return
 */
public  static List<String> getExpectedTopUsersHeaders() {
	List<String> headers = new ArrayList<>();
	headers.add("User Name");
	headers.add("Invocations");
	return headers;
}

/**
 * Method to return "API Invocations" expected headers.
 * 
 * @return
 */
public static  List<String> getExpectedApiInvocationsHeaders() {
	List<String> headers = new ArrayList<>();
	headers.add("Timestamp");
	headers.add("API Name");
	headers.add("API Version");
	headers.add("API URL");
	headers.add("Protocol");
	headers.add("Method");
	headers.add("Authentication");
	headers.add("HTTP Response");
	headers.add("Consumer");
	headers.add("IP Address");
	headers.add("Duration (ms)");
	return headers;
}

/**
 * Method to return "Events" expected headers.
 * 
 * @return
 */
public static List<String> getExpectedEventsHeaders() {
	List<String> headers = new ArrayList<>();
	headers.add("Timestamp");
	headers.add("API URL");
	headers.add("Authentication");
	headers.add("HTTP Response");
	headers.add("Description");
	headers.add("Consumer");
	headers.add("IP Address");
	return headers;
}

/**
 * ************************************************************************************************************************
 * @description This method is used to return the expected Dropdown options in API Registry of API Manager
 * @author abehera
 * @date_created  29-Jun-2022
 * @return List of expected dropdown options
 * ************************************************************************************************************************
 */

public static List<String> getExpectedAPIRegistryDropdownList(){

    List<String> expectedList = new ArrayList<String>();
    expectedList.add("All Services");
    expectedList.add("Available Services");
    expectedList.add("Managed APIs");
    MyLogger.info("Expected Response of APIM Registery dropdown list : " + expectedList);
    return expectedList;
}

/**
 * ************************************************************************************************************************
 * @description This method is used to return the expected Dropdown options in Client SDK
 * @author pdasari
 * @date_created  06-Jul-2022
 * @return List of expected Client SDK dropdown options
 * ************************************************************************************************************************
 */
public static final List<String> CLIENT_SDK_LIST = new ArrayList<String>() {
	private static final long serialVersionUID = 1L;
	{
		add("Java");
		add("Android");
		add("Javascript");
		add("Nodejs-Server");
		add("Python");
		add("Ruby");
		add("Csharp");
		add("Csharp-Dotnet2");
		add("Aspnetcore");
	}
};

	
}
