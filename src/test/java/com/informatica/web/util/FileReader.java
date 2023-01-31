package com.informatica.web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileReader {
	private String fileName;
	private String sheetName;



	public FileReader(String fileName, String sheet) {
		this.fileName = fileName;
		this.sheetName = sheet;
	}

	public HashMap<String, HashMap<String, String>> readExcelData(String Key_Name) throws IOException {
		HashMap<String, HashMap<String, String>> readData = new HashMap<String, HashMap<String, String>>();
		FileInputStream inp = new FileInputStream(fileName);
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(inp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet(sheetName);


		Row row = null;
		Cell cell = null;
		Row nRow = sheet.getRow(0);
		Iterator<?> rows = sheet.rowIterator();
		rows.next();
		try {

			while (rows.hasNext()) {
				row = (Row) rows.next();
				HashMap<String, String> data = new HashMap<String, String>();
				String header_New = null;
				for (int i = 0; i < row.getLastCellNum(); i++) {
					String value = null;
					cell = row.getCell(i);
					if (cell.getCellType() == CellType.STRING) {
						value = cell.getStringCellValue();
					} else if (cell.getCellType() == CellType.NUMERIC) {
						value = String.valueOf(cell.getNumericCellValue());
					} else if (cell.getCellType() == CellType.BOOLEAN) {
						value = String.valueOf(cell.getBooleanCellValue());
					} else {

					}

					String header = nRow.getCell(i).getStringCellValue();
				//	System.out.println(header);

					if (header.contentEquals(Key_Name)) {
						header_New = row.getCell(i).getStringCellValue();
					}

					data.put(header, value);
				}
				try {
					readData.put(header_New, data);
				} catch (Exception ex) {

				}

			}
		} catch (Exception ex) {

		}
		finally {
			workbook.close();
			inp.close();
		}
	

		return readData;

	}
	
	

}
