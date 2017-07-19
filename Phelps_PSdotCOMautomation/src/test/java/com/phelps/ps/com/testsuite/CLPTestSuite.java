package com.phelps.ps.com.testsuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Driver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.MSWordReader;
import com.phelps.ps.com.autotest.utils.VerificationStatus;
import com.phelps.ps.com.locators.CLPLocators;

public class CLPTestSuite extends BasicTestSuite implements CLPLocators {

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

	/**
	 * PS-314:TC-CLP-2.0- Test only listed CLP pages showing 4 tabs
	 * 
	 * @Test public void verifyOnlyCLPPagesShows4TabsTest() {
	 * 
	 *       }
	 */

	@BeforeClass
	public void generateExcelFormat() {

		dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH.mm");
		date = new Date();
		excelWBook = new XSSFWorkbook();
		// Create report sheet
		excelWSheet = excelWBook.createSheet("ReviewsMobile");
		XSSFRow headerRow = excelWSheet.createRow(++rowCount);
		colCount = -1;
		headerRow.createCell(++colCount).setCellValue("Property URL");
		headerRow.createCell(++colCount).setCellValue("QA File Name");
		headerRow.createCell(++colCount).setCellValue("City Information Failed texts");

	}

	/**
	 * PS-318:TC-CLP-6.0-Test different features of Storage Statistics Tab
	 * PS-317:TC-CLP-5.0-Test different features of Storage Units Tab
	 * PS-316:TC-CLP-4.0-Test different features of FAQ's Tab
	 * PS-315:TC-CLP-3.0-Test different features of Storage Unit Tab
	 * PS-313:TC-CLP-1.0-Test features of tabs of CLP pages
	 * 
	 * 
	 */
	@Test(priority = 1, dataProvider = "CLPDataProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyCLPPageTest(String CLPURL, String cityInformationFileName) {
		List<String> cityDocPara = new ArrayList<String>();
		int countHyperlink = 0;
		int countImages = 0;
		verifyStatus = new VerificationStatus();
		String allFailedTexts = "";

		/*
		 * To get the count of hyperlinks for verifyCLPCityInformationHyperLinkTest
		 * and images verifyCLPCityInformationImageTest
		 */

		cityDocPara = wordReader.readMyDocument("src/test/resources/CLPCityInformationFiles/" + cityInformationFileName + ".doc");
		System.out.println("THe length of the array is " + cityDocPara.size());
		for (int i = 0; i < cityDocPara.size(); i++) {
			String eachPara = cityDocPara.get(i);
			String eachParaImage = cityDocPara.get(i);
			int index = eachPara.indexOf("HYPERLINK");
			int indexImage = eachParaImage.indexOf("INCLUDEPICTURE");

			while (index != -1) {
				countHyperlink = countHyperlink + 1;
				eachPara = eachPara.substring(index + 1);
				index = eachPara.indexOf("HYPERLINK");
			}

			while (indexImage != -1) {
				countImages = countImages + 1;
				eachParaImage = eachParaImage.substring(indexImage + 1);
				indexImage = eachParaImage.indexOf("INCLUDEPICTURE");
			}
		}
		CLPURL = baseUrl + CLPURL;
		navigator.gotoCLPPage(CLPURL);
		clpActions.clickCityInformationTab();
		int countHLinkElementsURl = webAppDriver.findAllElementsByXpath(hLinkStorageUnitsAllHyperlinksXpath).size();// to
																																																								// make
																																																								// clpAction
		int countImagesElementsURL = webAppDriver.findAllElementsByXpath(imageHlinkStorageUnitsAllImagesXpath).size(); // countHyperLinkFromURL.add(countHLinkElementsURl);

		if (countHyperlink != countHLinkElementsURl) {
			isHyperlinkCountEqual = false;
			hyperLinkErrorList.add(" hyperlink count for " + CLPURL + " expected - " + countHyperlink + " Actual -  "
					+ countHLinkElementsURl + "<br>");

		}

		if (countImages != countImagesElementsURL) {
			isImagesCountEqual = false;
			imagesErrorList.add(" Images count for " + CLPURL + " Expected - " + countImages + " Actual -  " + countImagesElementsURL
					+ "<br>");

		}

		/*
		 * To test the features of Storage Statistics tab PS-318:TC-CLP-6.0-Test
		 * different features of Storage Statistics Tab
		 */

		String[] storageStatisticsText = { "Our lowest priced units in", "people have reserved units in", "city limits",
				"offer AC and Heating features", "offer 24 hour access" };

		clpActions.clickStorageStatisticsTab();
		webAppDriver.verifyLiTagTextContains(storageStatisticsText[0]);
		webAppDriver.verifyLiTagTextContains(storageStatisticsText[1]);
		webAppDriver.verifyLiTagTextContains(storageStatisticsText[2]);

		/*
		 * To test the features of FAQs tab PS-313:TC-CLP-1.0-Test features of tabs
		 * of CLP pages
		 */

		/*
		 * To test the features of Storage units tab Reviews , Features data and New
		 * layout of units, promotions, and price PS-315:TC-CLP-3.0-Test different
		 * features of Storage Unit Tab
		 */
		clpActions.clickStorageUnitsTab();
		int couponCount = webAppDriver.getTotalNumberOfElementsByXpath(divStorageUnitsAllCouponsXpath);
		if (couponCount == 1) {
			webAppDriver.verifyPresenceOfTextInDivTagText("LOCATION");
			webAppDriver.verifyPresenceOfTextInDivTagText("MONTHLY PRICE");
		} else {
			webAppDriver.verifyPresenceOfTextInATag("DISTANCE");
			webAppDriver.verifyPresenceOfTextInATag("MONTHLY PRICE");
		}

		webAppDriver.verifyPresenceOfTextInDivTagText("Size");
		webAppDriver.verifyPresenceOfTextInDivTagText("Promotion");

		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbStorageUnitsAllUnitSizeToXpath, couponCount);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbStorageUnitsAllPromotionsXpath, couponCount);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbStorageUnitsAllFromPriceXpath, couponCount);

		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbStorageUnitsAllUnitsFeaturesXpath, couponCount);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(imgLinkStorageUnitsAllContinueXpath, couponCount);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(hLinkStorageUnitsAllReviewRatingStarsXpath, couponCount);

		// To test the features of city information tab text

		clpActions.clickCityInformationTab();

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

					verifyStatus = clpActions.getStatusMessage(sentences[k].trim(), verifyStatus);
				}

			} else {

				System.out.println("the paragraph " + eachPara);
				verifyStatus = clpActions.getStatusMessage(eachPara.trim(), verifyStatus);

			}
		}
		System.out.println("before the verification status");
		if (!VerificationStatus.isStatusPass) {
			failedTexts = verifyStatus.getStatusMessage();
			for (String texts : failedTexts) {
				allFailedTexts += texts + "\n";
				row = excelWSheet.createRow(++rowCount);
				colCount = -1;
				row.createCell(++colCount).setCellValue(CLPURL);
				row.createCell(++colCount).setCellValue(cityInformationFileName);
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

	@AfterClass(alwaysRun = true)
	public void writeExcel() throws IOException {
		File fileName = new File("CLPReports\\CLPAutomationReport_" + dateFormat.format(date) + ".xlsx");
		FileOutputStream fos = new FileOutputStream(fileName);
		excelWBook.write(fos);
		fos.flush();
		fos.close();
	}

}
