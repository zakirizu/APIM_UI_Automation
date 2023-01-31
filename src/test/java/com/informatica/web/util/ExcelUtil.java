package com.informatica.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

import com.informatica.bo.ModuleInfo;
import com.informatica.bo.TestInfo;

public class ExcelUtil {
	
	static Logger logger = Logger.getLogger(ExcelUtil.class); 
	static Properties prop = null;
	private static XSSFWorkbook excelWBook;
	private static XSSFSheet excelWSheet;
	
	
	public static List<ModuleInfo> loadModulesData(String dataFilePath, String sheetName) throws IOException {

		List<ModuleInfo> moduleInfoList = null;
		try {
			ExcelReader excelReader = new ExcelReader(dataFilePath);
			List<String> modulesList = excelReader.getData(sheetName);
			moduleInfoList = Util.createModuleInfo(modulesList);
			for(ModuleInfo moduleInfo : moduleInfoList) {
				if(moduleInfo.isRun()) {
					List<String> testsList = excelReader.getData(moduleInfo.getModuleName());
					List<TestInfo> testInfoList = Util.createTestInfo(testsList);
					moduleInfo.getTestInfo().addAll(testInfoList);
				}
			}
		} catch (Exception e) {
			logger.error("Exception while loading modules data : " + e);
		}
		return moduleInfoList;
	}

	/**
	 * Reads the data from given xcel file and loads into an object.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Object[][] loadTestData(File path) throws IOException {

		Object[][] data = null;
		try {
			InputStream inputStream = new FileInputStream(path);

			excelWBook = new XSSFWorkbook(inputStream);
			excelWSheet = excelWBook.getSheetAt(0);
			int totalRows = excelWSheet.getLastRowNum();
//			int totalRows = getTotalEnabledTests();
			int totalColumns = getColumnCount(excelWSheet);
			data = new Object[totalRows][1];
			for (int i = 0; i < totalRows; i++) {
				String isTestCaseEnabled = excelWSheet.getRow(i+1).getCell(0).getStringCellValue();
				if (isTestCaseEnabled.equalsIgnoreCase("Yes") || isTestCaseEnabled.equalsIgnoreCase("Y")) {
					Map<String, String> columnData = new HashMap<String, String>();
					for (int j = 0; j < totalColumns; j++) {
						getCellData(i + 1, j, columnData);
					}
					data[i][0] = columnData;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			excelWBook.close();
		}
		return data;
	}
	
	/**
	 * Returns column count of given excelsheet.
	 * @param excelWSheet
	 * @return
	 */
	private static int getColumnCount(XSSFSheet excelWSheet) {
		return excelWSheet.getRow(0).getLastCellNum();
	}
	
	/**
	 * Stores data of given row and column into map.
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @param columnData
	 * @throws Exception
	 */
	private static void getCellData(int rowNumber, int columnNumber, Map<String, String> columnData) throws Exception {

		try {
			String columnHeader = excelWSheet.getRow(0).getCell(columnNumber).getStringCellValue();
			String columnValue = excelWSheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
			columnData.put(columnHeader, columnValue);

		} catch (Exception e) {
			throw e;
		}

	}
}
