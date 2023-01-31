package com.informatica.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigPropertiesLoader {

	private static Properties properties;
	
	public ConfigPropertiesLoader(String fileName) {
		
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(fileName)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the value of given property.
	 * 
	 * @param property
	 * @return
	 */
	public static String getProperty(String property, int inputNumber) {
		String uri = properties.getProperty(property +  "_" + inputNumber);
		return uri;
	}
	
	/**
	 * Returns the value of given property.
	 * 
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) {
		String uri = properties.getProperty(property);
		return uri;
	}
	
	/**
	 * Returns all the loaded properties.
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		return properties;
	}
	
	
}
