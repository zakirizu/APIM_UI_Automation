package com.informatica.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static XSSFWorkbook excelWBook = null;
	
	ExcelReader(String path) throws IOException{
		InputStream inputStream = new FileInputStream(new File(path));
		excelWBook = new XSSFWorkbook(inputStream);
	}
	
	public List<String> getData(String sheetName) {
		List<String> rowList = new ArrayList<>();
		XSSFSheet excelWSheet = excelWBook.getSheet(sheetName);
		Iterator<Row> excelWRows = excelWSheet.iterator();
		excelWRows.next();
		while (excelWRows.hasNext()) {
			Row excelWRow = excelWRows.next();
			StringBuilder row = new StringBuilder();
			Iterator<Cell> cells = excelWRow.cellIterator();
			while (cells.hasNext()) {

				Cell cell = (Cell) cells.next();
				if (cell.getCellType() == CellType.STRING) {
					row.append(cell.getStringCellValue() + "##");
				} else if (cell.getCellType() == CellType.NUMERIC) {
					row.append((int) cell.getNumericCellValue() + "##");
				} else if (cell.getCellType() == CellType.BOOLEAN) {
					row.append(cell.getBooleanCellValue() + "##");
				}
			}
			;
			rowList.add(row.toString());
		}
		return rowList;
	}
	
	public static HashMap<String, HashMap<String, String>> loadTestData(String sheetName) throws IOException {

		HashMap<String, HashMap<String, String>> testData = new HashMap<String, HashMap<String, String>>();
		try {
			XSSFSheet excelWSheet = excelWBook.getSheet(sheetName);
			int totalRows = excelWSheet.getLastRowNum();
			int totalColumns = getColumnCount(excelWSheet);
			for (int i = 0; i < totalRows; i++) {
				String isTestCaseEnabled = excelWSheet.getRow(i+1).getCell(0).getStringCellValue();
				String methodName = excelWSheet.getRow(i+1).getCell(1).getStringCellValue();
				if (isTestCaseEnabled.equalsIgnoreCase("Yes") || isTestCaseEnabled.equalsIgnoreCase("Y")) {
					HashMap<String, String> columnData = new HashMap<String, String>();
					for (int j = 0; j < totalColumns; j++) {
						getCellData(excelWSheet, i + 1, j, columnData);
					}
					testData.put(methodName, columnData);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			excelWBook.close();
		}
		return testData;
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
	private static void getCellData(XSSFSheet excelWSheet, int rowNumber, int columnNumber, Map<String, String> columnData) throws Exception {

		try {
			String columnHeader = excelWSheet.getRow(0).getCell(columnNumber).getStringCellValue();
			String cellValue="";
			XSSFCell cell = excelWSheet.getRow(rowNumber).getCell(columnNumber);
			if(cell.getCellType() == CellType.STRING)
				cellValue = cell.getStringCellValue();
			else if(cell.getCellType() == CellType.NUMERIC)
				cellValue = (int)cell.getNumericCellValue()+"";
			columnData.put(columnHeader, cellValue);

		} catch (Exception e) {
			throw e;
		}

	}
}
