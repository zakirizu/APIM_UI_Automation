package com.informatica.web.util;

public class PropertiesFileManager {

	private static ConfigPropertiesLoader configurationProperties;

	private InputDataPropertiesLoader inputDataProperties;

	public static ConfigPropertiesLoader configurationProperties(String fileName) {

		if (configurationProperties == null) {
			configurationProperties = new ConfigPropertiesLoader(fileName);
		}
		return configurationProperties;
	}

	public  InputDataPropertiesLoader loadInputDataProperties(String fileName) {

		if (inputDataProperties == null) {
			inputDataProperties = new InputDataPropertiesLoader(fileName);
		}
		return inputDataProperties;
	}

}
