package com.informatica.web.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class PODFileManager {
	
	private static FileReader FileReaderProperties;
	static HashMap<String, HashMap<String, String>> readData = new HashMap<String, HashMap<String, String>>();


	public static FileReader PodEnvProperties(String fileName,String Sheet) throws IOException {

		if (FileReaderProperties == null) {
			System.out.println(fileName);
			System.out.println(Sheet);
			FileReaderProperties = new FileReader(fileName, Sheet);
			readData=FileReaderProperties.readExcelData("Environment_Name");
			System.out.println("launcher Class URL load :"+ PODFileManager.getProperty("Login_URL"));
		}
		return FileReaderProperties;
	}
	
	public static String getProperty(String property) {
		String uri = readData.get(ConfigPropertiesLoader.getProperty("Env_name")).get(property);
		return uri;
	}
	
	
}
