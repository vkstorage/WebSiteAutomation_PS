package com.phelps.ps.com.autotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
public class ExcelFileReader {
	final static Logger logger = Logger.getLogger(ExcelFileReader.class);

	/**
	 * 
	 * Function for reading the cell value of an excel sheet
	 * 
	 * @FilePath
	 * @FileName
	 * @SheetName
	 * @startCellRef
	 * 
	 */
	public static Object[][] readExcel(String filePath, String fileName, String sheetName, String startCellRef) {
		File file = new File(filePath + "/" + fileName);
		Workbook wb = null;
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			// Find the file extension by splitting file name in substring and
			// getting only extension name
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			// Check condition if the file is xlsx file
			if (fileExtensionName.equals(".xlsx")) {
				// If it is xlsx file then create object of XSSFWorkbook class
				try {
					wb = new XSSFWorkbook(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// Read sheet inside the workbook by its name
		Sheet nameOfSheet = wb.getSheet(sheetName);
		// Find the start Cell Reference
		CellReference cellReference = new CellReference(startCellRef);
		Row row = nameOfSheet.getRow(cellReference.getRow());
		int startRowNumber = row.getRowNum();
		// Find number of rows in excel file
		int rowCount = nameOfSheet.getLastRowNum() - (startRowNumber - 1);
		// int colCount = row.getLastCellNum();

		int colCnt = 0;
		for (int x = startRowNumber; x <= nameOfSheet.getLastRowNum(); x++) {
			Row rowNum = nameOfSheet.getRow(x);
			if (rowNum.getLastCellNum() > colCnt) {
				colCnt = rowNum.getLastCellNum();
			}
		}
		// Create a loop over all the rows of excel file to read it
		Object[][] cellValues = new Object[rowCount][colCnt];
		int returnRowCount = 0;
		for (int i = startRowNumber; i <= nameOfSheet.getLastRowNum(); i++) {
			Row rowNum = nameOfSheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < colCnt; j++) {
				Object cellValue = null;
				if (rowNum.getCell(j) != null) {
					switch (rowNum.getCell(j).getCellType()) {
					case Cell.CELL_TYPE_STRING:
						cellValue = rowNum.getCell(j).getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cellValue = rowNum.getCell(j).getNumericCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						cellValue = rowNum.getCell(j).getBooleanCellValue();
						break;
					}
				} else {
					cellValue = "";
				}

				if (cellValue != null) {
					if (cellValue!="") {
						// Print excel data in console
						logger.info(cellValue + "||");
						cellValues[returnRowCount][j] = String.valueOf(cellValue);
					}
				}

			}
			returnRowCount++;
			System.out.println();
		}
		System.out.println();
		return cellValues;
	}

	/**
	 * 
	 * Function for reading the cell value till the column number of an excel
	 * sheet
	 * 
	 * @FilePath
	 * @FileName
	 * @SheetName
	 * @StartCellRef
	 * @Column Number
	 * 
	 */
	public static Object[][] readExcel(String filePath, String fileName, String sheetName, String startCellRef, int endColNum) {
		File file = new File(filePath + "/" + fileName);
		Workbook wb = null;
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			// Find the file extension by splitting file name in substring and
			// getting only extension name
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			// Check condition if the file is xlsx file
			if (fileExtensionName.equals(".xlsx")) {
				// If it is xlsx file then create object of XSSFWorkbook class
				try {
					wb = new XSSFWorkbook(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Read sheet inside the workbook by its name
		Sheet nameOfSheet = wb.getSheet(sheetName);
		// Find the start Cell Reference
		CellReference cellReference = new CellReference(startCellRef);
		Row row = nameOfSheet.getRow(cellReference.getRow());
		int startRowNumber = row.getRowNum();
		// Find number of rows in excel file
		int rowCount = nameOfSheet.getLastRowNum() - (startRowNumber - 1);
		// Create a loop over all the rows of excel file to read it
		Object[][] cellValues = new Object[rowCount][endColNum];
		int returnRowCount = 0;
		for (int i = startRowNumber; i <= nameOfSheet.getLastRowNum(); i++) {
			Row rowNum = nameOfSheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < endColNum; j++) {
				Object cellValue = null;
				if (rowNum.getCell(j) != null) {
					switch (rowNum.getCell(j).getCellType()) {
					case Cell.CELL_TYPE_STRING:
						cellValue = rowNum.getCell(j).getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cellValue = rowNum.getCell(j).getNumericCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						cellValue = rowNum.getCell(j).getBooleanCellValue();
						break;
					}
				} else {
					cellValue = "";
				}
				// Print excel data in console
				System.out.print(cellValue);
				cellValues[returnRowCount][j] = cellValue;
			}
			returnRowCount++;
			System.out.println();
		}
		System.out.println();
		return cellValues;
	}

}
