package com.informatica.tests;

import org.testng.log4testng.Logger;


import com.informatica.web.util.Constants;
import com.informatica.web.util.JIRAUpdate;
import com.informatica.web.util.MailHelper;
import com.informatica.web.util.PropertiesFileManager;
import com.informatica.web.util.ReportGenerator;
import com.informatica.web.util.TestsExecutor;
import com.informatica.web.util.PODFileManager;


public class Launcher {

	final static Logger logger = Logger.getLogger(Launcher.class);
	public static double startTime;
	public static double EndTime;

	public static void main(String[] args) throws Exception {
	

		try {
			logger.info("Launching testng");
			startTime= System.currentTimeMillis();
			PropertiesFileManager.configurationProperties(Constants.CONFIGURATION_FILE_PATH);
			PODFileManager.PodEnvProperties(Constants.ENVIRONEMNT_DETAILS, "POD_Details");
			TestsExecutor testsExecutor = new TestsExecutor();
			testsExecutor.init();
			testsExecutor.processTests();
			testsExecutor.executeTests();
			ReportGenerator reportGenerator = new ReportGenerator();
			reportGenerator.generateEmailableReport();
			JIRAUpdate jiraUpdate=new JIRAUpdate();
			jiraUpdate.updateJIRA();
			EndTime= System.currentTimeMillis();

			System.out.println("Total Execution Time:" + String.format("%.3f", ( EndTime- startTime)/1000/60F )+" Minutes");

			MailHelper Mail=new MailHelper();
			Mail.sendMail();
			
			
		} catch (Exception e) {
			logger.error("Exception occured while lauching testng : " + e);
		}
	}

}
