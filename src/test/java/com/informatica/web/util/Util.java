package com.informatica.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;




import com.informatica.bo.ModuleInfo;
import com.informatica.bo.TestInfo;
import com.informatica.tests.BaseTest;

public class Util {

//	static Logger logger = Logger.getLogger(Util.class);

	public static List<ModuleInfo> createModuleInfo(List<String> moduleList) {

		List<ModuleInfo> moduleInfoList = new ArrayList<>();
		for (String module : moduleList) {
			List<String> modules = Arrays.asList(module.split("##"));
			boolean isModuleEnabled = modules.get(0).equalsIgnoreCase("Yes") ? true : false;
			if (isModuleEnabled) {
				ModuleInfo moduleInfo = new ModuleInfo(isModuleEnabled, modules.get(1), modules.get(2), modules.get(3));
				BaseTest.setModuleName(moduleInfo.getModuleClassName(), moduleInfo.getModuleName());
				moduleInfoList.add(moduleInfo);
			}
		}
		return moduleInfoList;
	}
	
	public static List<TestInfo> createTestInfo(List<String> testsList) {

		List<TestInfo> testInfoList = new ArrayList<>();
		for (String test : testsList) {
			List<String> tests = Arrays.asList(test.split("##"));
			boolean isTestEnabled = tests.get(0).equalsIgnoreCase("Yes") ? true : false;
			TestInfo testInfo = new TestInfo(isTestEnabled, tests.get(1), tests.get(2),tests.get(3),tests.get(4));
			testInfoList.add(testInfo);
		}
		return testInfoList;
	}
	
	public static void waitForEventSyncIntoDAA() {
		try {
			Thread.sleep(60000*3);
		} catch (InterruptedException e) {
			MyLogger.error(e);
		}
	}
	
	public static String getCurrentDateTime()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
		Date date = new Date();
		return formatter.format(date).toString();

	}
}
