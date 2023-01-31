package com.informatica.web.util;

import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.informatica.tests.BaseTest;

/*
############################################################################
Description: Wrapper class to log both automation log and extent report.
Author: cgandu
Date Created: 01/07/2022
JIRA TCs associated:
Coverage: Sanity
############################################################################ */

public class LoggerUtil {

	Logger logger = Logger.getLogger(LoggerUtil.class);
	
	public static final String EXTENT_LOG = "EXTENT_REPORT_LOG";
	public static final String AUTO_LOG = "AUTOMATION_LOG";
	public static final String EXTENT_AUTO_LOG = "EXTENT_REPORT_AND_AUTOMATION_LOG";
	public  static ExtentTest loginfo;

	
	public static void setloginfo( ExtentTest loginfotrack)
	{
		loginfo= loginfotrack;
	}
	
	
	public static void getLogger(String className){
		MyLogger.startLogger(className);
	}
	
	/**
	 * Method to log info messages
	 * @param message : message which needs to be logged in automation log and extent report.
	 * @param logType : indicate type of log (EXTENT_LOG : extent report, AUTO_LOG : automation log, EXTENT_AUTO_LOG : extent report and automation log)
	 */
	public static  void info(Object message, String logType)  {

		switch (logType) {
		case EXTENT_LOG:
			loginfo.info(message.toString());
			break;
		case AUTO_LOG:
			MyLogger.info(message);
			
		break;
		case EXTENT_AUTO_LOG:
			MyLogger.info(message);
			loginfo.info(message.toString());
			break;
		}
	}
	
	/**
	 * Method to log error messages
	 * @param message : message which needs to be logged in automation log and extent report.
	 * @param logType : indicate type of log (EXTENT_LOG : extent report, AUTO_LOG : automation log, EXTENT_AUTO_LOG : extent report and automation log)
	 */
	
	public static void error(Object message, String logType) {
		
		switch (logType) {
		case EXTENT_LOG:
			loginfo.error(message.toString());
			break;
		case AUTO_LOG:
			MyLogger.error(message);
			break;
		case EXTENT_AUTO_LOG:
			MyLogger.error(message);
			loginfo.error(message.toString());
			break;
		}
	}
	
	/**
	 * Method to print log type messages
	 * @param message : message which needs to be logged in automation log and extent report.
	 * @param logType : indicate type of log (EXTENT_LOG : extent report, AUTO_LOG : automation log, EXTENT_AUTO_LOG : extent report and automation log)
	 */
	
	public static  void log(Status status, Object message, String logType) {
		
		switch (logType) {
		case EXTENT_LOG:
			loginfo.log(status, message.toString());
			break;
		case AUTO_LOG:
			MyLogger.info(message);
			break;
		case EXTENT_AUTO_LOG:
			MyLogger.info(message);
			loginfo.log(status, message.toString());
			break;
		}
	}
}
