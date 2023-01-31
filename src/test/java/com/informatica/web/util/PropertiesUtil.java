package com.informatica.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {

	static Properties prop = null;
	
	
	/**
	 * Load the properties from specified file.
	 * 
	 * @param fileName
	 */
	public static void loadProperties(String fileName) {

		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File(fileName)));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns all the loaded properties.
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		return prop;
	}

	/**
	 * Returns the value of given property.
	 * 
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) {
		String uri = prop.getProperty(property);
		return uri;
	}
}
