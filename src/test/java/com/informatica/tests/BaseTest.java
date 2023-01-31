package com.informatica.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.informatica.web.util.ConfigPropertiesLoader;
import com.informatica.web.util.Constants;
import com.informatica.web.util.ExcelReader;
import com.informatica.web.util.LoggerUtil;
import com.informatica.zapi.ApiException;
import com.informatica.zapi.ZAPIUtil;
import com.informatica.zapi.model.ExecutionStatusEnum;

import com.informatica.web.util.MyLogger;
import com.informatica.web.util.PODFileManager;
import com.informatica.web.util.TestNGBuilder;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.informatica.pages.BasePage;

/**
 * 
 * Base class for all test class which contains actual test cases which takes
 * care of loading properties and required extent reports objects
 *
 */
public class BaseTest {

	// final static Logger logger = Logger.getLogger(BaseTest.class);

	public static HashMap<String, String> classToModuleMap = new HashMap<String, String>();
	public static HashMap<String, String> MethodExectuinStatus = new HashMap<String, String>();

	/*
	 * private static ExtentReports reports; private ExtentHtmlReporter
	 * htmlReporter; public String JIRA_KEY;
	 */
	public static ExtentReports reports;
	public ExtentHtmlReporter htmlReporter;
//	public static ExtentTest loginfo;
	public  ExtentTest loginfo;


	public static Map<Long, String> threadToExtentTestMap = new HashMap<Long, String>();
	public static Map<String, ExtentTest> nameToTestMap = new HashMap<String, ExtentTest>();
	HashMap<String, HashMap<String, String>> testData = null;
	public static List<String> VedioFiles = new ArrayList<String>();
	

	// Shared between all tests in this class.
	public Playwright playwright;
	public Browser browser;

	// New instance for each test method.
	public BrowserContext context;
	public Page page;
	public static String baseUrl;
	public static String adminUsername;
	public static String adminPassword;
	public static String Environment_name ;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException, ApiException {

		getEnvironmentDetails();
		
		htmlReporter = new ExtentHtmlReporter(new File(Constants.REPORTS_PATH+File.separator+Environment_name+File.separator+"reports.html"));
		htmlReporter.loadXMLConfig(new File(Constants.CONFIG_PATH+File.separator+ "extentReportsConfig.xml"));
		reports = new ExtentReports();
		reports.setSystemInfo("Environment", Environment_name);
		reports.setSystemInfo("POD Url", baseUrl);
		reports.attachReporter(htmlReporter);

		purgeDirectory(new File(Constants.LOGGER_FOLDER+File.separator+Environment_name));
		purgeDirectory(new File(Constants.SCREENSHOTS_FOLDER+File.separator+Environment_name));
		purgeDirectory(new File(Constants.EXECUTION_RECORDING_VEDIO_FOLDER+File.separator+Environment_name));
		purgeDirectory(new File(Constants.REPORTS_PATH+File.separator+Environment_name));
		
		CreateDirectory(new File(Constants.LOGGER_FOLDER+File.separator+Environment_name));
		CreateDirectoryAndCopyFile(new File(Constants.REPORTS_PATH+File.separator+Environment_name));


		deleteFile(System.getProperty("user.dir") + File.separator + "ResultMailNew.html");

	}

	@BeforeMethod(alwaysRun = true)
	public void register(Method method) {

		String testName = method.getName();
		loginfo=createTest(testName, getModuleName(getClass().getName()));
	//	LoggerUtil.loginfo=this.loginfo;
	//	LoggerUtil.setloginfo(loginfo);
	}

	@AfterMethod(alwaysRun = true)
	public void captureStatus(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.SUCCESS) {
			getTest().log(Status.PASS, " Method with name : " + result.getName() + " is passed");

			System.out.println(result.getName() + "   :::::::::: " +result.getStatus());
			String TestClassName = result.getTestClass().getName();

			System.out.println("Test class Name:" +TestClassName.substring(TestClassName.lastIndexOf('.')+1));
			
			UpdateMethods_Executions(result.getName(), "PASS");
			// AddJIRA_Keys(Pass_Methods, result.getName());

		} else if (result.getStatus() == ITestResult.FAILURE) {
			getTest().log(Status.FAIL, result.getThrowable().getClass().getName());
			getTest().log(Status.FAIL, " Method with name : " + result.getName() + " is failed");
			System.out.println(result.getName() + "   :::::::::: " + result.getStatus());
			
			String TestClassName = result.getTestClass().getName();
			String TestClass=TestClassName.substring(TestClassName.lastIndexOf('.')+1);
			System.out.println("Test class Name:" +TestClass);
			VedioFiles.add(TestClass);
			
			System.out.println("Page inside the Screenshot:" + page);
			UpdateMethods_Executions(result.getName(), "FAIL");
			// AddJIRA_Keys(Fail_Methods, result.getName());

			page.screenshot(new Page.ScreenshotOptions()
					.setPath(Paths.get(Constants.SCREENSHOTS_FOLDER+File.separator+Environment_name + File.separator + result.getName() + ".png"))
					.setFullPage(true));

		}
		
		/*
		 * else if (result.getStatus() == ITestResult.SKIP) { getTest().log(Status.SKIP,
		 * result.getThrowable().getClass().getName()); getTest().log(Status.SKIP,
		 * " Method with name : " + result.getName() + " is skipped");
		 * System.out.println(result.getName() + "   :::::::::: " + result.getStatus());
		 */



		
		

	}



	@AfterSuite(alwaysRun = true)
	public void cleanUp() throws ApiException {
		
	
		cleanDirectory();
		reports.flush();

	}

	public static String getModuleName(String className) {
		return BaseTest.classToModuleMap.get(className);
	}

	public static void setModuleName(String className, String moduleName) {
		BaseTest.classToModuleMap.put(className, moduleName);
	}

	public static Map<String, ExtentTest> getExtentTestMap() {
		return nameToTestMap;
	}

	public synchronized ExtentTest createTest(String testName, String categoryName) {

		if (!nameToTestMap.containsKey(testName)) {

			Long threadID = Thread.currentThread().getId();

			ExtentTest testInfo = reports.createTest(testName).assignCategory(categoryName);


			nameToTestMap.put(testName, testInfo);

			threadToExtentTestMap.put(threadID, testName);
		}
		return nameToTestMap.get(testName);
	}

	public synchronized static ExtentTest getTest() {
		Long threadID = Thread.currentThread().getId();

		if (threadToExtentTestMap.containsKey(threadID)) {
			String testName = threadToExtentTestMap.get(threadID);
			return nameToTestMap.get(testName);
		}
		return null;
	}

	public Page instantiateDriver(String CallingClassName) {
		String browsertype = ConfigPropertiesLoader.getProperty("browser");
		playwright = Playwright.create();

		try {
			if (browsertype.equalsIgnoreCase("Chrome")) {
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome")
						.setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
				// .setHeadless(false).setArgs(List.of("--start-maximized")));
				System.out.println(browser);
			} else if (browsertype.equalsIgnoreCase("Edge")) {
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge")
						.setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
			} else if (browsertype.equalsIgnoreCase("Firefox")) {
				browser = playwright.firefox().launch(
						new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
			}

		} catch (Exception e) {
			MyLogger.info("Exception while instantiating webdriver : " + e);
			throw e;
		}

		context = browser.newContext((new Browser.NewContextOptions().setViewportSize(null)
				.setRecordVideoDir(Paths.get(Constants.EXECUTION_RECORDING_VEDIO_FOLDER+File.separator+Environment_name+File.separator+CallingClassName))));
		


		page = context.newPage();

		page.setDefaultTimeout(Integer.parseInt(ConfigPropertiesLoader.getProperty("TimeoutMax")));
		return page;
	}

	public Page getPage(String CallingClassName) {
		if (page == null) {
			page = instantiateDriver(CallingClassName);

		}
		return page;
	}

	public void getEnvironmentDetails() throws IOException {

		baseUrl = PODFileManager.getProperty("Login_URL");
		adminUsername = PODFileManager.getProperty("UserName");
		adminPassword = PODFileManager.getProperty("Password");
		Environment_name = PODFileManager.getProperty("Environment_Name");
		System.setProperty("jira.username", ConfigPropertiesLoader.getProperty("JIRA_USERNAME").trim());
		System.setProperty("jira.password", ConfigPropertiesLoader.getProperty("JIRA_PASSWORD").trim());
		System.setProperty("PROJECT_NAME", ConfigPropertiesLoader.getProperty("PROJECT_NAME").trim());
		System.setProperty("PROJECT_VERSION", ConfigPropertiesLoader.getProperty("PROJECT_VERSION").trim());
		System.setProperty("Env_name", ConfigPropertiesLoader.getProperty("Env_name").trim());
		
		

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {

		System.out.println("---- Inside teardown Method-----");
		{
			System.out.println("page" + page);
			page.close();

			System.out.println("context" + context);
		
			context.close();
			System.out.println("browser" + browser);
			browser.close();
			System.out.println("playwright" + playwright);
			playwright.close();

		}
	}

	public static String getAdditionalDetails(Object data, String Key) throws IOException {
		HashMap<String, String> testDetails = (HashMap<String, String>) data;
		String additionalDetails = testDetails.get("Additional Details");
		Map<String, String> requestParamMap = getRequestParametersMap(additionalDetails);
		// String resrcNameFromXL = requestParamMap.get("RESOURCE_NAME");
		String resrcNameFromXL = requestParamMap.get(Key);
		return resrcNameFromXL;
	}

	private static HashMap<String, String> getRequestParametersMap(String requestParams) {

		HashMap<String, String> requestMap = new HashMap<>();
		String[] params = requestParams.split(",");
		for (String param : params) {
			requestMap.put(param.split("=")[0], param.split("=")[1]);
		}

		return requestMap;
	}

	/*
	 * public static String getJIRAKey(Object data) throws IOException {
	 * HashMap<String, String> JIRADetails = (HashMap<String, String>) data; String
	 * JIRAKey = JIRADetails.get("JIRA_KEY"); return JIRAKey; }
	 */

	public void UpdateMethods_Executions(String MethodName, String Status) {
		MethodExectuinStatus.put(MethodName, Status);
	}

	public static HashMap<String, String> getUpdateMethods_Execution() {

		return MethodExectuinStatus;
	}

	protected void deleteFile(String filePath) {
		try {
			Path path = Paths.get(filePath);
			Files.deleteIfExists(path);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	protected void cleanDirectory() {
	File dir=new File(Constants.EXECUTION_RECORDING_VEDIO_FOLDER+File.separator+Environment_name);
	if (VedioFiles.size()==0)
	{

		if(dir.exists()) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory())
					purgeDirectory(file);
				file.delete();
			}
			}
		
	}
	else
	{
		for (String Classname : VedioFiles) 
		{
		
		System.out.println("dir:"+dir);
		if(dir.exists()) {
		for (File file : dir.listFiles()) 
			{
			System.out.println("Classname:"+Classname);
			System.out.println("File Name in Directory :"+file.getName());
			if (file.isDirectory() && !file.getName().equals(Classname) )
				purgeDirectory(file);
			file.delete();
		}
		}
		
	}
	}
	}

	protected void purgeDirectory(File dir) {
	//	System.out.println("dir:"+dir);
		if(dir.exists()) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				purgeDirectory(file);
			file.delete();
		}
		}
	}
	
	protected void CreateDirectory(File dir) throws IOException {
		if (!dir.exists()){
		    dir.mkdirs();
		}
		

	}
	
	protected void CreateDirectoryAndCopyFile(File dir) throws IOException {
		if (!dir.exists()){
		    dir.mkdirs();
		}
		
		File source = new File(Constants.CONFIG_PATH+File.separator+"reports.html");

	    FileUtils.copyFileToDirectory(source, dir);


	}



	public void loginToIICS(Page page) throws Exception {

		BasePage login = new BasePage(page);
		login.navigateTo(baseUrl);
		login.loginIntoIICSApp(adminUsername, adminPassword);
	}

	@DataProvider
	public Object[][] getData(Method m) {
		// HashMap<String, HashMap<String, String>> testData = null;

		Object[][] data = null;
		try {
			data = new Object[1][1];
			if (testData == null) {
				testData = ExcelReader.loadTestData(classToModuleMap.get(this.getClass().getName()));
				System.out.println("testData" + testData);
			}
			for (Map.Entry<String, HashMap<String, String>> entry : testData.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(m.getName())) {
					data[0][0] = entry.getValue();
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static Map<String, String> getAdditionalDetails(Object data) throws IOException {
		HashMap<String, String> testDetails = (HashMap<String, String>) data;
		String additionalDetails = testDetails.get("Additional Details");
		Map<String, String> requestParamMap = getRequestParametersMap(additionalDetails);
		return requestParamMap;
	}

}
