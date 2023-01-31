package com.informatica.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.informatica.bo.ModuleInfo;
import com.informatica.bo.TestInfo;

public class TestNGBuilder {
	
	public static HashMap<String, String> MethodtoJiraKeymap = new HashMap<String, String>();

	

	public static XmlSuite buildTestSuite1(ModuleInfo moduleInfo) {

		XmlSuite suite = new XmlSuite();
//		List<XmlClass> classes = new ArrayList<XmlClass>();
		suite.setName(moduleInfo.getModuleName());
		// suite.setParallel(ParallelMode.CLASSES);
//		XmlTest test = new XmlTest(suite);
//		test.setName(moduleInfo.getModuleName());
//		XmlClass xmlClass = new XmlClass(moduleInfo.getModuleClassName());
//		List<XmlInclude> includedMethods = new ArrayList<>();
		for (TestInfo testInfo : moduleInfo.getTestInfo()) {
			if (testInfo.getIsRun()) {
				XmlTest test = new XmlTest(suite);
				List<XmlClass> classes = new ArrayList<XmlClass>();
				List<XmlInclude> includedMethods = new ArrayList<>();
				XmlClass xmlClass = new XmlClass(moduleInfo.getModuleClassName());
				test.setName(testInfo.getTestMethodName());
				test.setParallel(XmlSuite.ParallelMode.METHODS);
				test.setThreadCount(10);
				test.addParameter("testMethodName", testInfo.getTestMethodName());
				test.addParameter("moduleName", moduleInfo.getModuleName());
				test.addParameter("additionalDetails", testInfo.getAdditionalDetails());
				XmlInclude xmlInclude = new XmlInclude(testInfo.getTestMethodName());
				includedMethods.add(xmlInclude);
				xmlClass.setIncludedMethods(includedMethods);
				classes.add(xmlClass);
				test.setXmlClasses(classes);
			}
		}
		/*if (!includedMethods.isEmpty()) {
			xmlClass.setIncludedMethods(includedMethods);
		}*/
		/*classes.add(xmlClass);
		test.setXmlClasses(classes);*/
		System.out.println("Suite :: " + suite.toXml());
		return suite;
	}
	
	public static XmlSuite buildTestSuite(ModuleInfo moduleInfo) {

		XmlSuite suite = new XmlSuite();
		List<XmlClass> classes = new ArrayList<XmlClass>();

		suite.setName(moduleInfo.getModuleName());
		XmlTest test = new XmlTest(suite);
		test.setName(moduleInfo.getModuleName());
		XmlClass xmlClass = new XmlClass(moduleInfo.getModuleClassName());
		List<XmlInclude> includedMethods = new ArrayList<>();
		for (TestInfo testInfo : moduleInfo.getTestInfo()) {
			if (testInfo.getIsRun()) {
				/*test.addParameter("testMethodName", testInfo.getTestMethodName());
				test.addParameter("requestFileName", testInfo.getRequestFileName());
				test.addParameter("moduleName", moduleInfo.getModuleName());
				test.addParameter("dataSets", testInfo.getDataSets());
				test.addParameter("additionalDetails", testInfo.getAdditionalDetails());*/
				XmlInclude xmlInclude = new XmlInclude(testInfo.getTestMethodName());
				includedMethods.add(xmlInclude);
			}
		}
		if (!includedMethods.isEmpty()) {
			xmlClass.setIncludedMethods(includedMethods);
		}
		classes.add(xmlClass);
		test.setXmlClasses(classes);
		System.out.println("Suite :: " + suite.toXml());
		return suite;
	}

	public static XmlSuite buildTests(List<ModuleInfo> modules) {

		XmlSuite suite = new XmlSuite();
		suite.setParallel(ParallelMode.TESTS);
		suite.setThreadCount(Integer.parseInt(ConfigPropertiesLoader.getProperty("ThreadCount")));
		suite.setName("APIM Tests");
		for (ModuleInfo moduleInfo : modules) {
			XmlTest test = new XmlTest(suite);
			List<XmlClass> classes = new ArrayList<XmlClass>();
			test.setName(moduleInfo.getModuleName());
		//	test.setThreadCount(5);
		//	test.setParallel(XmlSuite.ParallelMode.METHODS);
			XmlClass xmlClass = new XmlClass(moduleInfo.getModuleClassName());
			List<XmlInclude> includedMethods = new ArrayList<>();
			for (TestInfo testInfo : moduleInfo.getTestInfo()) {
				if (testInfo.getIsRun()) {
					XmlInclude xmlInclude = new XmlInclude(testInfo.getTestMethodName());
					includedMethods.add(xmlInclude);
				MethodtoJiraKeymap.put(testInfo.getTestMethodName(),testInfo.getjirakey());

				}
			}
			if (!includedMethods.isEmpty()) {
				xmlClass.setIncludedMethods(includedMethods);
			}
			classes.add(xmlClass);
			test.setXmlClasses(classes);
		}
//		System.out.println("Suite :: " + suite.toXml());
		return suite;
	}
	
	
	public static void launchTestNg(List<XmlSuite> suites) {

		TestNG testNG = new TestNG();
		testNG.setXmlSuites(suites);
		testNG.run();
	}
	

	public static HashMap<String, String> getMethodtoJiraKeyMapping() 
	{
		
		return MethodtoJiraKeymap;
	}
	


	


	
}
