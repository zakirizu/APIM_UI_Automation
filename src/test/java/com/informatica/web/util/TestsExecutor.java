package com.informatica.web.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.xml.XmlSuite;

import com.informatica.bo.ModuleInfo;

public class TestsExecutor {

	private List<ModuleInfo> moduleInfoList;
	private List<XmlSuite> suites = new ArrayList<XmlSuite>();	


	public void init() throws IOException {
		
		PropertiesUtil.loadProperties(Constants.CONFIGURATION_FILE_PATH);
		String sanityTestDataFilePath = Constants.PROJECT_PATH + File.separator + "TestCases" + File.separator+ PropertiesUtil.getProperty("sanityTestDataFilePath");
		String testDataFilePath = Constants.PROJECT_PATH + File.separator + "TestCases" + File.separator +PropertiesUtil.getProperty("testDataFilePath");
		String runOnlySanityTests = PropertiesUtil.getProperty("runOnlySanityTests");
		String dataFilePath = Boolean.valueOf(runOnlySanityTests) ? sanityTestDataFilePath : testDataFilePath;
		moduleInfoList = ExcelUtil.loadModulesData(dataFilePath, "Modules");
	}
	
	public void processTests() throws Exception{

		XmlSuite suite = TestNGBuilder.buildTests(moduleInfoList);
		System.out.println("Suite :: " + suite.toXml());
		try {
			FileWriter fw = new FileWriter(Constants.TESTNG_FOLDER+File.separator+"testng.xml");			
			fw.flush();
			fw.append(suite.toXml());
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		suites.add(suite);
	}
	
	public void executeTests() {
		

		
	
		TestNGBuilder.launchTestNg(suites);
	}
	
	 
	

}
