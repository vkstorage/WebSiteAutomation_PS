package com.phelps.ps.com.testsuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.MSWordReader;
import com.phelps.ps.com.autotest.utils.VerificationStatus;

public class FebRelease2017BugsTestSuite extends BasicTestSuite {
	
	static boolean storageStatisticsFlag = false;
	MSWordReader wordReader = new MSWordReader();
	VerificationStatus verifyStatus;
	List<String> failedTexts = new ArrayList<String>();
	List<String> hyperLinkErrorList = new ArrayList<String>();
	List<String> imagesErrorList = new ArrayList<String>();
	boolean isHyperlinkCountEqual = true, isImagesCountEqual = true;
	protected static XSSFWorkbook excelWBook;
	protected XSSFSheet excelWSheet;
	protected XSSFRow row;
	DateFormat dateFormat;
	Date date;
	static int rowCount = -1;
	static int colCount;
	@BeforeClass
	public void generateExcelFormat() {

		dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH.mm");
		date = new Date();
		excelWBook = new XSSFWorkbook();
		// Create report sheet
		excelWSheet = excelWBook.createSheet("PLPText");
		XSSFRow headerRow = excelWSheet.createRow(++rowCount);
		colCount = -1;
		headerRow.createCell(++colCount).setCellValue("URL");
		headerRow.createCell(++colCount).setCellValue("QA File Name");
		headerRow.createCell(++colCount).setCellValue("PLP Failed texts");

	}
	
	//Bug 3381 - PLP Content Additions (PS-0501) 
  @Test(priority = 1, dataProvider = "PLPContentProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
  public void verifyPLPContentAdditionTest(String PLPURL, String PLPFiles) {
	  List<String> cityDocPara = new ArrayList<String>();
		int countHyperlink = 0;
		int countHLinkElementsURl=0;
		
		verifyStatus = new VerificationStatus();
		String allFailedTexts = "";

		/*
		 * To get the count of hyperlinks for verifyCLPCityInformationHyperLinkTest
		 * and images verifyCLPCityInformationImageTest
		 */

		cityDocPara = wordReader.readMyDocument("src/test/resources/PLPFileconverted/" + PLPFiles);
		System.out.println("THe length of the array is " + cityDocPara.size());
		for (int i = 0; i < cityDocPara.size(); i++) {
			String eachPara = cityDocPara.get(i);
			String eachParaImage = cityDocPara.get(i);
			int index = eachPara.indexOf("HYPERLINK");
			

			while (index != -1) {
				countHyperlink = countHyperlink + 1;
				eachPara = eachPara.substring(index + 1);
				index = eachPara.indexOf("HYPERLINK");
			}

			
		}
		PLPURL = baseUrl + PLPURL;
		navigator.gotoCLPPage(PLPURL);
		webAppDriver.clickElementByLinkText("SEE LOCATION FEATURES");
		if(webAppDriver.verifyElementIsPresentByXpath(".//*[@id='plp_features']//a")){
		countHLinkElementsURl = webAppDriver.findAllElementsByXpath(".//*[@id='plp_features']//a").size();// to
		}
		else
			countHLinkElementsURl=0;
																																																								// make
																																																								// clpAction
		

		if (countHyperlink != countHLinkElementsURl) {
			isHyperlinkCountEqual = false;
			hyperLinkErrorList.add(" hyperlink count for " + PLPURL + " expected - " + countHyperlink + " Actual -  "
					+ countHLinkElementsURl + "<br>");

		}

		

		

		

		for (int i = 0; i < cityDocPara.size(); i++) {
			String eachPara = cityDocPara.get(i);
			if (eachPara.contains("Article Title"))
				continue;
			if (eachPara.contains("Article Content"))
				continue;
			if (eachPara.contains("<div class"))
				continue;
			if (eachPara.contains("\r"))
				eachPara = eachPara.replaceAll("\r", "");
			if (eachPara.contains("HYPERLINK")) {
				int firstIndex = eachPara.indexOf("HYPERLINK") - 2;
				int lastIndex = eachPara.lastIndexOf("") + 1;
				eachPara = eachPara.replace(eachPara.substring(firstIndex, lastIndex), ".");
			}

			if (eachPara.contains("INCLUDEPICTURE"))
				continue;
			if (eachPara.contains(".")) {

				String[] sentences = eachPara.split("\\.");
				for (int k = 0; k < sentences.length; k++) {
					System.out.println("the arrays " + sentences.length + "  sentence " + sentences[k]);
					sentences[k]=sentences[k].trim();

					verifyStatus = clpActions.getStatusMessage(sentences[k], verifyStatus);
				}

			} else {

				System.out.println("the paragraph " + eachPara);
				eachPara=eachPara.trim();
				verifyStatus = clpActions.getStatusMessage(eachPara, verifyStatus);

			}
		}
		System.out.println("before the verification status");
		if (!VerificationStatus.isStatusPass) {
			failedTexts = verifyStatus.getStatusMessage();
			for (String texts : failedTexts) {
				allFailedTexts += texts + "\n";
				row = excelWSheet.createRow(++rowCount);
				colCount = -1;
				row.createCell(++colCount).setCellValue(PLPURL);
				row.createCell(++colCount).setCellValue(PLPFiles);
				row.createCell(++colCount).setCellValue(texts + "\n");

			}

			webAppDriver.captureScreenshot();
			Reporter.log("Not present in HTML:" + allFailedTexts);
			throw new AssertionError("[NOT FOUND]" + allFailedTexts);

		}

	}

	/*
	 * Test compare the number of hyperlinks on the URL and the corresponding city
	 * doc file
	 */
	@Test(priority = 2)
	public void verifyCLPCityInformationHyperLinkTest() {
		if (!isHyperlinkCountEqual) {
			for (int i = 0; i < hyperLinkErrorList.size(); i++) {
				Reporter.log(hyperLinkErrorList.get(i));
			}

			throw new AssertionError(" Count of " + hyperLinkErrorList.size() + " hyperlinks does not match");
		}

	}

	/*
	 * Test compare the number of images on the URL and the corresponding city doc
	 * file
	 */
	@Test(priority = 3)
	public void verifyCLPCityInformationImagesTest() {

		if (!isImagesCountEqual) {
			for (int i = 0; i < imagesErrorList.size(); i++) {
				Reporter.log(imagesErrorList.get(i));
			}

			throw new AssertionError(" Count of " + imagesErrorList.size() + " images does not match");
		}

	}
	
	//@Test
	public void verifyHardCodeBestPricesTest(){
		String[] zip={"","",""};
		navigator.gotoDefaultSearchResults(zip[0]);
		webAppDriver.verifyElementPresentByCss("#best_prices_widget");
	}
	

	@AfterClass(alwaysRun = true)
	public void writeExcel() throws IOException {
		File fileName = new File("CLPReports\\CLPAutomationReport_" + dateFormat.format(date) + ".xlsx");
		FileOutputStream fos = new FileOutputStream(fileName);
		excelWBook.write(fos);
		fos.flush();
		fos.close();
	}

	  
	  
  
}
