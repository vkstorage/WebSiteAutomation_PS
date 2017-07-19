package com.phelps.ps.com.dataprovider;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.common.base.Optional;
import com.phelps.ps.com.autotest.utils.PropertyLoader;
import com.phelps.ps.com.baseparams.BaseParams;

public class FileReader implements BaseParams {

	private static final String RQST_RESP_PROP_FILE = "/RequestResponse.properties";

	protected static List<String> getFileContentAsList(String filenamePath) {
		// Sample utility method to get the file content, any version of
		// this can be adapted, this is just one way of achieving the result.
		InputStream is;
		List<String> lines = new ArrayList<String>();
		try {
			is = new FileInputStream(new File(filenamePath));
			DataInputStream in = new DataInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				lines.add(strLine);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static Optional<String> loadProperty(String name) {
		Properties props = new Properties();
		try {
			props.load(PropertyLoader.class.getResourceAsStream(RQST_RESP_PROP_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Optional.of(props.getProperty(name));
	}

	/**
	 * Loads the property from the specified file name.
	 * 
	 * @param propFileName
	 *          - Property file name
	 * @param key
	 *          - Property name
	 * @return Property value
	 */
	public static String loadProperty(String propFileName, String key) {
		String PROP_FILE = "/" + propFileName + ".properties";
		Properties props = new Properties();
		try {
			props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.of(props.getProperty(key)).get();
	}

	public static String readFile(String absoluteFilePath) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.FileReader(absoluteFilePath));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				// sb.append(",");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static List<String> getExcelFileContentAsList(String filenamePath) {
		List<String> rows = new ArrayList<String>();
		try {
			HSSFSheet getSheet = null;
			HSSFWorkbook hssfWorkBook = null;
			FileInputStream file = new FileInputStream(new File(filenamePath));
			hssfWorkBook = new HSSFWorkbook(file);
			getSheet = hssfWorkBook.getSheetAt(0);
			int getSheetLastRow = hssfWorkBook.getSheetAt(0).getLastRowNum();
			int colIndex = 0;
			if (baseUrl.contains("devpsstaging.phelpsagency.com")) {
				colIndex = 0;
			} else if (baseUrl.contains("psstaging.phelpsagency.com")) {
				colIndex = 1;
			} else if (baseUrl.contains("test-web.ps.com")) {
				colIndex = 2;
			} else if (baseUrl.contains("stage-web.ps.com")) {
				colIndex = 3;
			} else if (baseUrl.contains("www.publicstorage.com")) {
				colIndex = 4;
			}
			for (int rowNum = 1; rowNum <= getSheetLastRow; rowNum++) {
				rows.add(getSheet.getRow(rowNum).getCell(colIndex).toString());
				/*if (getSheet.getRow(rowNum).getCell(0) != null && getSheet.getRow(rowNum).getCell(colIndex) != null) {
					envTestData.put(getSheet.getRow(rowNum).getCell(0).toString(), getSheet.getRow(rowNum).getCell(colIndex).toString());
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * Reads data from file
	 * 
	 * @param filePath
	 * @return
	 */
	public static Iterator<Object[]> getDataFromExcel(String filePath) {
		File workLoadFile = new File(filePath);
		List<String> testData = getExcelFileContentAsList(workLoadFile.getAbsolutePath());
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (String userData : testData) {
			dataToBeReturned.add(new Object[] { userData });
		}
		return dataToBeReturned.iterator();
	}

}
