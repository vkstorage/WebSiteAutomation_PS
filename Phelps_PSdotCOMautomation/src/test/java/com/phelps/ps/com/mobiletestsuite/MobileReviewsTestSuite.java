package com.phelps.ps.com.mobiletestsuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.util.Args;
//import org.apache.bcel.generic.NEWARRAY;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.UrlFetchWebConnection;
import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.utils.BrowserType;
import com.phelps.ps.com.autotest.utils.DeviceType;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.MobileReviewLocators;
import com.sun.mail.iap.Argument;

public class MobileReviewsTestSuite implements MobileReviewLocators/*
																																		 * extends
																																		 * BaseTest
																																		 */{
	MobileWebDriver webAppDriver;

	protected static XSSFWorkbook excelWBook, excelWBook_10;
	protected XSSFSheet excelWSheet, excelWSheet_10;
	protected XSSFRow row, row_10;
	DateFormat dateFormat;
	Date date;
	static int rowCount = -1;
	static int rowCount_10 = -1;
	static int colCount;
	static int colCount_10;
	protected List<String> URLNotFound = new ArrayList<String>();
	protected List<String> URLNotFound_10 = new ArrayList<String>();
	protected boolean isBothURLMatching = true;
	protected boolean isBothURLMatching_10 = true;
	protected static int countOfFailedURls = 0;
	protected static int countOfFailedURls_10 = 0;
	protected boolean isGoogleURLMatch, isGoogleURLMatch_10;
	protected boolean isYelpURLMatch, isYelpURLMatch_10;
	String allURLNotFound, allURLNotFound_10;
	String GoogleURL, Yelp1URL;
	List<String> hrefURLNotFound = new ArrayList<String>();
	List<String> hrefURLNotFound_10 = new ArrayList<String>();
	String hrefValue;
	boolean googlefound;

	@BeforeClass
	public void launchMobileBroserTest() throws MalformedURLException {
		webAppDriver = new MobileWebDriver(DeviceType.ANDROID, BrowserType.chrome);
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH.mm");
		date = new Date();
		excelWBook = new XSSFWorkbook();
		// Create report sheet
		excelWSheet = excelWBook.createSheet("ReviewsMobile");
		XSSFRow headerRow = excelWSheet.createRow(++rowCount);
		

		// create header row SOCalGas URL, GatherContent URL, Result on
		colCount = -1;
		headerRow.createCell(++colCount).setCellValue("Property ID");
		headerRow.createCell(++colCount).setCellValue("Address");
		headerRow.createCell(++colCount).setCellValue("City");
		headerRow.createCell(++colCount).setCellValue("State");
		headerRow.createCell(++colCount).setCellValue("Zip");
		headerRow.createCell(++colCount).setCellValue(" Expected GPlusURL");
		headerRow.createCell(++colCount).setCellValue("Expected YelpURL");
		headerRow.createCell(++colCount).setCellValue("RESULT-" + dateFormat.format(date));
		headerRow.createCell(++colCount).setCellValue("Comment");
		
	// Creating sheet for less than 10 reviews
	/*	colCount_10 = -1;
		XSSFRow headerRow_10 = excelWSheet_10.createRow(++rowCount_10);
		headerRow_10.createCell(++colCount_10).setCellValue("Property ID");
		headerRow_10.createCell(++colCount_10).setCellValue("Expected GMapURL");
*/
		
	
		
	/*	headerRow_10.createCell(++colCount_10).setCellValue("Address");
		headerRow_10.createCell(++colCount_10).setCellValue("City");
		headerRow_10.createCell(++colCount_10).setCellValue("State");
		headerRow_10.createCell(++colCount_10).setCellValue("Zip");*/
		// if yelp urls provided for les than 10 reviews
		// headerRow_10.createCell(++colCount_10).setCellValue("Expected YelpURL");
	/*	headerRow_10.createCell(++colCount_10).setCellValue("RESULT-" + dateFormat.format(date));
		headerRow_10.createCell(++colCount_10).setCellValue("Comment");
*/
		
	}

	/*
	 * To verify the URL for Google plus and Yelp for the properties given for
	 * review
	 */
	
	
	
//@Test(priority = 0, dataProvider = "ReviewsLessThanTenPropertiesProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void googleLessThanTenReviewsTest(String propertyNumber, String GMapsURL) {

		isGoogleURLMatch_10 = false;
		isYelpURLMatch_10 = false;

		row_10 = excelWSheet_10.createRow(++rowCount_10);
		colCount_10 = -1;
		row_10.createCell(++colCount_10).setCellValue(propertyNumber);

		row_10.createCell(++colCount_10).setCellValue(GMapsURL);

		webAppDriver.get("https://psreviewsstaging.phelpsagency.com/");
		System.out.println("the property details are " + propertyNumber + " , " + GMapsURL);
		String firstDigit, secondDigit, thirdDigit, fourthDigit, fifthDigit;
		String[] propertyID = propertyNumber.split("\\.");
		String[] singlePropertydigit = propertyID[0].split("");
		if (singlePropertydigit.length == 3) {
			firstDigit = "0";
			secondDigit = "0";
			thirdDigit = singlePropertydigit[2];
			fourthDigit = singlePropertydigit[3];
			fifthDigit = singlePropertydigit[4];

		}

		else if (singlePropertydigit.length == 4) {
			firstDigit = "0";
			secondDigit = singlePropertydigit[1];
			thirdDigit = singlePropertydigit[2];
			fourthDigit = singlePropertydigit[3];
			fifthDigit = singlePropertydigit[4];

		} else {
			firstDigit = singlePropertydigit[0];
			secondDigit = singlePropertydigit[1];
			thirdDigit = singlePropertydigit[2];
			fourthDigit = singlePropertydigit[3];
			fifthDigit = singlePropertydigit[4];
		}

		webAppDriver.getDriver().findElement(By.id("propertyid1")).sendKeys(firstDigit);

		webAppDriver.enterTextToElementById("propertyid2", secondDigit);
		webAppDriver.enterTextToElementById("propertyid3", thirdDigit);
		webAppDriver.enterTextToElementById("propertyid4", fourthDigit);
		webAppDriver.enterTextToElementById("propertyid5", fifthDigit);
		webAppDriver.clickElementById("reviewlink");
		
		webAppDriver.verifyPresenceOfTextInATag("Click here");

		String hrefValue = webAppDriver.findElementByXpath("//img[@class='square']/..").getAttribute("href");

		// If Yelp Properties for less than 10 reviews is sent then comment above
		// line and uncomment below line
		// String
		// hrefValue_yelp=webAppDriver.findElementByXpath(linkYelpReviewLessThanTenxPath).getAttribute("href");
		if (hrefValue.equalsIgnoreCase(GMapsURL)) {
			isGoogleURLMatch_10 = true;

		}
		/*
		 * else if (hrefValue.equalsIgnoreCase(yelpURL)) { //
		 * Reporter.log(" Yelp URL found "); isYelpURLMatch=true; }
		 */
		else
			hrefURLNotFound_10.add(hrefValue);
		
		//change below to isYelpURLMatch_10
		if (isGoogleURLMatch_10) {
			row_10.createCell(++colCount_10).setCellValue("DesktopPass");
			// row.createCell(++colCount).setCellValue("Both URL found");
			row_10.createCell(++colCount_10).setCellValue("URL found");
		}
		/*
		 * else if((!isGoogleURLMatch && !isYelpURLMatch) ) {
		 * allURLNotFound="BOTH NOT FOUND \n"
		 * +hrefURLNotFound.get(0)+"\n"+hrefURLNotFound.get(1);
		 * row.createCell(++colCount).setCellValue("AndroidFail");
		 * row.createCell(++colCount).setCellValue(allURLNotFound);
		 * URLNotFound.add(propertyNumber+"  "+ GMapsURL+"  , "+ yelpURL+"\n");
		 * isBothURLMatching = false; countOfFailedURls++; }
		 */else if (!isGoogleURLMatch_10) {
			allURLNotFound_10 = "GMAPLINK NOT FOUND \n" + hrefURLNotFound.get(0);
			row_10.createCell(++colCount_10).setCellValue("DesktopFail");
			row_10.createCell(++colCount_10).setCellValue(allURLNotFound_10);
			URLNotFound_10.add(propertyNumber + "  " + GMapsURL + "\n");
			isBothURLMatching_10 = false;
			countOfFailedURls_10++;

			System.out.println("The end of the google review less than 10 test");
			hrefURLNotFound_10.clear();
			URLNotFound_10.clear();

		}

	}
	
	@Test(priority = 1, dataProvider = "ReviewMobilePropertiesProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void googleReviewTest(String propertyNumber, String address, String city, String State, String zip, String GMapsURL,
			String yelpURL) {
		
		// /OSPass ,iOSFail ,AndroidPass, AndroidFail .
		row = excelWSheet.createRow(++rowCount);
		colCount = -1;
		isGoogleURLMatch = false;
		isYelpURLMatch = false;
		row.createCell(++colCount).setCellValue(propertyNumber);
		row.createCell(++colCount).setCellValue(address);
		row.createCell(++colCount).setCellValue(city);
		row.createCell(++colCount).setCellValue(State);
		row.createCell(++colCount).setCellValue(zip);
		row.createCell(++colCount).setCellValue(GMapsURL);
		row.createCell(++colCount).setCellValue(yelpURL);

	webAppDriver.get("https://reviews.publicstorage.com/home/mobile");
		 //webAppDriver.get("http://psreviewsstaging.phelpsagency.com/");
		System.out.println("the property details are " + propertyNumber + " , " + address + " , " + city + " , " + zip + " , "
				+ GMapsURL + " , " + yelpURL);
		String firstDigit, secondDigit, thirdDigit, fourthDigit, fifthDigit;
		String[] propertyID = propertyNumber.split("\\.");
		String[] singlePropertydigit = propertyID[0].split("");
		if (singlePropertydigit.length == 3) {
			firstDigit = "0";
			secondDigit = "0";
			thirdDigit = singlePropertydigit[1];
			fourthDigit = singlePropertydigit[2];
			fifthDigit = singlePropertydigit[3];

		}

		else if (singlePropertydigit.length == 4) {
			firstDigit = "0";
			secondDigit = singlePropertydigit[1];
			thirdDigit = singlePropertydigit[2];
			fourthDigit = singlePropertydigit[3];
			fifthDigit = singlePropertydigit[4];

		} else {
			firstDigit = singlePropertydigit[0];
			secondDigit = singlePropertydigit[1];
			thirdDigit = singlePropertydigit[2];
			fourthDigit = singlePropertydigit[3];
			fifthDigit = singlePropertydigit[4];
		}

		webAppDriver.getDriver().findElement(By.id("propertyid1")).sendKeys(firstDigit);

		webAppDriver.enterTextToElementById("propertyid2", secondDigit);
		webAppDriver.enterTextToElementById("propertyid3", thirdDigit);
		webAppDriver.enterTextToElementById("propertyid4", fourthDigit);
		webAppDriver.enterTextToElementById("propertyid5", fifthDigit);
		webAppDriver.clickElementById("reviewlink");
		webAppDriver.verifyPresenceOfTextInATag("Click here");
		List<CateredWebElement> urls1 = webAppDriver.findAllElementsByXpath("//*[@id='container-mobile']//a");

		int i = 0;
		for (CateredWebElement e : urls1) {
			System.out.println(e.getAttribute("href"));
			String hrefValue = e.getAttribute("href");

			if (hrefValue.equalsIgnoreCase(GMapsURL)) {
				// Reporter.log(" Google URL found ");
				isGoogleURLMatch = true;

			} else if (hrefValue.equalsIgnoreCase(yelpURL)) {
				// Reporter.log(" Yelp URL found ");
				isYelpURLMatch = true;
			} else
				hrefURLNotFound.add(hrefValue);

		}

		if (isGoogleURLMatch && isYelpURLMatch) {
			row.createCell(++colCount).setCellValue("AndroidPass");
			row.createCell(++colCount).setCellValue("Both URL found");
		} else if ((!isGoogleURLMatch && !isYelpURLMatch)) {
			allURLNotFound = "BOTH NOT FOUND \n" + hrefURLNotFound.get(0) + "\n" + hrefURLNotFound.get(1);
			row.createCell(++colCount).setCellValue("AndroidFail");
			row.createCell(++colCount).setCellValue(allURLNotFound);
			URLNotFound.add(propertyNumber + "  " + GMapsURL + "  , " + yelpURL + "\n");
			isBothURLMatching = false;
			countOfFailedURls++;
		} else if (!isGoogleURLMatch) {
			allURLNotFound = "GMAPLINK NOT FOUND \n" + hrefURLNotFound.get(0);
			row.createCell(++colCount).setCellValue("AndroidFail");
			row.createCell(++colCount).setCellValue(allURLNotFound);
			URLNotFound.add(propertyNumber + "  " + GMapsURL + "\n");
			isBothURLMatching = false;
			countOfFailedURls++;
		} else {
			allURLNotFound = "YELPINK NOT FOUND \n" + hrefURLNotFound.get(0);
			row.createCell(++colCount).setCellValue("AndroidFail");
			row.createCell(++colCount).setCellValue(allURLNotFound);
			URLNotFound.add(propertyNumber + "  " + yelpURL + "\n");
			isBothURLMatching = false;
			countOfFailedURls++;
		}

		webAppDriver.verifyPresenceOfTextInH1Tag("Choose where you would like to leave a review:");

		System.out.println("The end of the google review test");
		hrefURLNotFound.clear();
		URLNotFound.clear();

	}

	@Test(priority = 2)
	public void verifyURLMatchesTest() {
		String failedURLs = "";

		if (!isBothURLMatching) {
			for (String failUrls : URLNotFound) {
				failedURLs = failedURLs + failUrls;
			}
			Reporter.log("Count of failed Gmap and Yelp URLs  " + countOfFailedURls);
			throw new AssertionError("Failed URLS  " + failedURLs + Thread.currentThread().getStackTrace().toString());

		}
		
		// For less than 10 google links

		/*String failedURLs_10 = "";
		if (!isBothURLMatching_10) {
			for (String failUrls : URLNotFound_10) {
				failedURLs_10 = failedURLs_10 + failUrls;
			}
			Reporter.log("Count of failed Gmap and Yelp URLs for less than 10 reviews  " + countOfFailedURls_10);
			throw new AssertionError("Failed URLS  " + failedURLs_10 + Thread.currentThread().getStackTrace().toString());

		}*/


	}

	@AfterClass(alwaysRun = true)
	public void writeExcel() throws IOException {
		File fileName = new File("ReviewReports\\ReviewMobileAutomationReport_" + dateFormat.format(date) + ".xlsx");
		FileOutputStream fos = new FileOutputStream(fileName);
		excelWBook.write(fos);
		fos.flush();
		fos.close();
	/*	
		File fileName_10 = new File("ReviewsDesktopLessThanTenReport_" + dateFormat.format(date) + ".xlsx");
		FileOutputStream fos_10 = new FileOutputStream(fileName_10);
		excelWBook_10.write(fos_10);
		fos_10.flush();
		fos_10.close();*/
	}

}
