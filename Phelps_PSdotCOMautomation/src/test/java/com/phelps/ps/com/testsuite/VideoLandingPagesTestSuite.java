
package com.phelps.ps.com.testsuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
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
import com.phelps.ps.com.autotest.utils.PropertyLoader;
import com.phelps.ps.com.autotest.utils.VerificationStatus;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.VideoLandingPageLocators;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
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
import com.phelps.ps.com.autotest.utils.PropertyLoader;
import com.phelps.ps.com.autotest.utils.VerificationStatus;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.VideoLandingPageLocators;

public class VideoLandingPagesTestSuite extends BasicTestSuite
		implements CommonSearchTextLocators, VideoLandingPageLocators {

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
		excelWSheet = excelWBook.createSheet("VideoLanding");
		XSSFRow headerRow = excelWSheet.createRow(++rowCount);
		colCount = -1;
		headerRow.createCell(++colCount).setCellValue("Property URL");
		headerRow.createCell(++colCount).setCellValue("QA File Name");
		headerRow.createCell(++colCount).setCellValue("City Information Failed texts");

	}

	@Test
	public void verifyVideoLandingPageHeaderTest() {
		// Scenario 1
		// Public Storage icon

		// For production use the below
		// String url = "https://www." +
		// PropertyLoader.loadProperty("env.name").get();

		String url = "https://" + PropertyLoader.loadProperty("env.name").get();
		navigator.gotoVideoLandingPage();
		webAppDriver.verifyAttributeValueByXpath(CommonLocators.imgPSLogoXpath, "src", url + "/images/hp-refresh/nav-logo-retina.png");
		// Menu items: Storage, Moving Suppliers, Company Info
		webAppDriver.relax(500);
		homeActions.hoverStorage();
		webAppDriver.verifyPresenceOfTextInSpanTag("Self Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Business Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Vehicle Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Size Guide");
		webAppDriver.verifyPresenceOfTextInSpanTag("Storage Blog");
		// Clicking on each Storage menu options
		homeActions.clickOnHowSelfStorageWorksMenu();
		webAppDriver.navigate().back();
		homeActions.hoverStorage();
		homeActions.clickOnSelfStorageMenu();
		webAppDriver.navigate().back();
		homeActions.hoverStorage();
		homeActions.clickOnBusinessStorageMenu();
		webAppDriver.navigate().back();
		homeActions.hoverStorage();
		homeActions.clickOnVehicleStorageMenu();
		webAppDriver.navigate().back();
		homeActions.hoverStorage();
		homeActions.clickOnStorageBlogMenu();
		webAppDriver.navigate().back();
		homeActions.hoverMovingSupplies();
		// webAppDriver.verifyPresenceOfTextInSpanTag("Storage Locks");
		webAppDriver.verifyPresenceOfTextInSpanTag("Packing Supplies");
		webAppDriver.verifyPresenceOfTextInSpanTag("Tape");
		// Clicking on each Moving Supplies menu options
		homeActions.clickOnStorageBoxesMenu();
		webAppDriver.navigate().back();
		// navigator.gotoHomePageFromMovingSupplies();
		webAppDriver.relax(500);
		// homeActions.hoverMovingSupplies();
		// homeActions.clickOnStorageLocksMenu();
		navigator.gotoVideoLandingPage();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnPackingSuppliesMenu();
		navigator.gotoVideoLandingPage();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnTapeMenu();
		navigator.gotoVideoLandingPage();
		homeActions.hoverCompanyInfo();
		webAppDriver.verifyPresenceOfTextInSpanTag("Investor Relations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Global Locations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Careers");
		webAppDriver.verifyPresenceOfTextInSpanTag("Contact Us");
		// Clicking on each Company Info menu options
		homeActions.clickOnAboutUsMenu();
		navigator.gotoVideoLandingPage();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnGlobalLocationMenu();
		webAppDriver.navigate().back();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnContactUsMenu();
		// Need help text and phone number
		webAppDriver.verifyDivTagTextEquals("NEED HELP?");
		webAppDriver.navigate().back();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnInvestorRelationMenu();
	}

	@Test
	public void searchFunctionalityTest() {
		navigator.gotoVideoLandingPage();
		videoLandingPageActions.searchTest(searchText34);
		navigator.gotoVideoLandingPage();
		videoLandingPageActions.searchTest(searchText35);
		navigator.gotoVideoLandingPage();
		videoLandingPageActions.searchTest(searchText36);
	}

	@Test
	public void recentSearchTest() {
		int positionRDPProperty = 1;
		String address1, address2, address3;
		String recentAddress1, recentAddress2, recentAddress3;
		navigator.gotoVideoLandingPage();
		videoLandingPageActions.searchTest(searchText34);
		commonActions.getAllSearchResultShowAllUnits().get(positionRDPProperty).click();
		webAppDriver.relax(500);
		address1 = webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText();

		navigator.gotoVideoLandingPage();

		recentAddress1 = webAppDriver.findElementByXpath(hlnkRecentSearchlink1Xpath).getText();
		webAppDriver.verifyEqualsString(address1, recentAddress1);
		videoLandingPageActions.searchTest(searchText37);
		commonActions.getAllSearchResultShowAllUnits().get(positionRDPProperty).click();

		address2 = webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText();

		navigator.gotoVideoLandingPage();
		recentAddress1 = webAppDriver.findElementByXpath(hlnkRecentSearchlink1Xpath).getText();
		recentAddress2 = webAppDriver.findElementByXpath(hlnkRecentSearchlink2Xpath).getText();
		webAppDriver.verifyEqualsString(address2, recentAddress1);
		webAppDriver.verifyEqualsString(address1, recentAddress2);
		videoLandingPageActions.searchTest(searchText40);
		commonActions.getAllSearchResultShowAllUnits().get(positionRDPProperty).click();

		address3 = webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText();

		navigator.gotoVideoLandingPage();
		recentAddress1 = webAppDriver.findElementByXpath(hlnkRecentSearchlink1Xpath).getText();
		recentAddress2 = webAppDriver.findElementByXpath(hlnkRecentSearchlink2Xpath).getText();
		recentAddress3 = webAppDriver.findElementByXpath(hlnkRecentSearchlink3Xpath).getText();
		webAppDriver.verifyEqualsString(address3, recentAddress1);
		webAppDriver.verifyEqualsString(address2, recentAddress2);
		webAppDriver.verifyEqualsString(address1, recentAddress3);
		videoLandingPageActions.searchTest(searchText20);
		commonActions.getAllSearchResultShowAllUnits().get(positionRDPProperty).click();

		address1 = webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText();
		navigator.gotoVideoLandingPage();
		recentAddress1 = webAppDriver.findElementByXpath(hlnkRecentSearchlink1Xpath).getText();
		recentAddress2 = webAppDriver.findElementByXpath(hlnkRecentSearchlink2Xpath).getText();
		recentAddress3 = webAppDriver.findElementByXpath(hlnkRecentSearchlink3Xpath).getText();
		webAppDriver.verifyEqualsString(address1, recentAddress1);
		webAppDriver.verifyEqualsString(address3, recentAddress2);
		webAppDriver.verifyEqualsString(address2, recentAddress3);

	}

	@Test(dataProvider = "VideoPageProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyCategoriesTextAndImagesTest(String title, String description, String imagesExtensions) {
		String imagesPrefix = "//span[text()='" + title + "']/../..//img[@src='";
		List<String> categoriesText = new ArrayList<String>();
		categoriesText.add("How to Pack & Store");
		categoriesText.add("How to Move In");
		categoriesText.add("What to Expect");
		categoriesText.add("Why Us?");
		categoriesText.add("Commercials");
		navigator.gotoVideoLandingPage();

		for (String categories : categoriesText) {
			webAppDriver.verifyPresenceOfTextInDivTagText(categories);
		}

		// To check for titles,images and copy
		webAppDriver.verifyPresenceOfTextInSpanTag(title);

		if (description.contains("'")) {
			String xpath = "//*[contains(.,\"" + description + "\")]";
			webAppDriver.verifyElementPresentByXpath(xpath);
		} else if (!webAppDriver.verifyTextWithSpacePresentInAnyTag(description)) {
			webAppDriver.captureScreenshot();
			Reporter.log(description + "  not present on " + title);
			throw new AssertionError();
		}
		webAppDriver.verifyElementPresentByXpath(imagesPrefix + imagesExtensions + "']");

		// copy pending

	}

	@Test
	public void verifyRightRailOnVideoPageTest() {

		String sizeGuideText = "Not sure what size you need? We can help with that.";
		String packingStorageText = "Learn how to pack and store like a pro in 9 easy steps.";
		String howSelfStorageText = "New to self-storage? We’ve got answers to your questions.";

		navigator.gotoVideoLandingPage();
		webAppDriver.verifyPresenceOfTextInSpanTag(sizeGuideText);
		webAppDriver.verifyPresenceOfTextInSpanTag(packingStorageText);
		// webAppDriver.verifyPresenceOfTextInDivTagText(howSelfStorageText);

		webAppDriver.verifyElementPresentByXpath(imgPromoXpath);
		webAppDriver.verifyElementPresentByXpath(imgSizeGuideiconXpath);
		webAppDriver.verifyElementPresentByXpath(imgPackageAndStorageTipsIconsXpath);
		webAppDriver.verifyElementPresentByXpath(imgHowStorageWorksIconXpath);

		webAppDriver.clickElementByXpath(hlinkLearnMoreSizeGuideXpath);
		webAppDriver.verifyElementPresentById(btnSizeGuideLookInsideId);
		webAppDriver.navigate().back();
		webAppDriver.clickElementByXpath(hlinkLearnMorePackageAndStorageTipsXpath);
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack like a Pro");
		webAppDriver.navigate().back();
		webAppDriver.clickElementByXpath(hlinkLearnMoreHowStorageWorksXpath);
		webAppDriver.verifyElementPresentById(hlnkHowSelfStorageStartingOutId);

	}

	@Test(dataProvider = "VideoLandingPageProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyIndividualVideoTest(String imagesExtension, String videoLandingPageURL,
			String videoLandingPagebreadCrumbs, String hasDownloadTip, String pageTitle) {

		navigator.gotoVideoLandingPage();

		String elementToClick = "//img[@src='" + imagesExtension + "']/..";// "//img[@src='"+imagesExtension+"']/..//span[@class='playIcon']";
		webAppDriver.clickElementByXpath(elementToClick);

		// To verify the redirection to the correct URL
		String videoURLWithBaseURL = baseUrl + videoLandingPageURL;
		webAppDriver.relax(300);
		String currentVideoURL = webAppDriver.getCurrentUrl();
		webAppDriver.verifyEqualsString(currentVideoURL, videoURLWithBaseURL);

		// To verify the page title
		webAppDriver.verifyPresenceOfTextInH1Tag(pageTitle);

		// To verify video plays automatically
		webAppDriver.switchtoiFrameByXpath(iframeVideoIndividualXpath);
		webAppDriver.verifyVisibilityOfElementLocatedByXpath(btnVideoPauseXpath);
		webAppDriver.switchTo().defaultContent();

		// To verify the breadcrumbs
		String[] breadCrumbs = videoLandingPagebreadCrumbs.split(">");
		for (int i = 0; i < breadCrumbs.length; i++) {
			String singleBreadCrumbXpath;
			breadCrumbs[i] = breadCrumbs[i].trim();
			if (i == 0)
				singleBreadCrumbXpath = "//div[@class='breadcrumb']/a[contains(.,'" + breadCrumbs[i] + "')]";
			else if (i == 1)
				singleBreadCrumbXpath = "//div[@class='breadcrumb']/a[contains(.,'" + breadCrumbs[i] + "')]";
			else
				singleBreadCrumbXpath = "//div[@class='breadcrumb'][contains(.,'" + breadCrumbs[i] + "')]";
			webAppDriver.verifyElementPresentByXpath(singleBreadCrumbXpath);
		}

		// To verify the download button is present
		if (hasDownloadTip.equalsIgnoreCase("TRUE")) {
			webAppDriver.verifyElementPresentByXpath(btnDownloadTipsPDFXpath);
		}

		// to verify promo
		webAppDriver.verifyElementPresentByXpath(imgPromoXpath);

		webAppDriver.clickElementByLinkText("See all videos");
		webAppDriver.verifyPresenceOfTextInDivTagText("How to Pack & Store");
		webAppDriver.navigate().back();

		videoLandingPageActions.searchTest(searchText36);

	}

	@Test(dataProvider = "VideoPageCopyProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyIndividualVideoCopyTest(String videoCopyDocName, String URL) {
		List<String> cityDocPara = new ArrayList<String>();
		verifyStatus = new VerificationStatus();
		String allFailedTexts = "";
		String filePath = "src/test/resources/VideoLandingPageFiles/" + videoCopyDocName + ".doc";
		webAppDriver.get(baseUrl + URL);
		cityDocPara = wordReader.readMyDocument(filePath);
		System.out.println("THe length of the array is " + cityDocPara.size());
		for (int i = 0; i < cityDocPara.size(); i++) {
			String eachPara = cityDocPara.get(i);
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
				// for (String eachLine : eachPara.split("\n")) {
				String[] sentences = eachPara.split("\\.");

				for (int k = 0; k < sentences.length; k++) {
					System.out.println("the arrays " + sentences.length + "  sentence " + sentences[k]);
					if (sentences[k].equals("\n"))
						continue;
					sentences[k] = sentences[k].trim();
					verifyStatus = clpActions.getStatusMessage(sentences[k], verifyStatus);
				}

			} else {

				System.out.println("the paragraph " + eachPara);
				eachPara = eachPara.trim();
				verifyStatus = clpActions.getStatusMessage(eachPara, verifyStatus);

			}
		}

		System.out.println("bewfor the verification status");
		if (!VerificationStatus.isStatusPass) {
			failedTexts = verifyStatus.getStatusMessage();
			for (String texts : failedTexts) {
				allFailedTexts += texts + "\n";
				row = excelWSheet.createRow(++rowCount);
				colCount = -1;
				row.createCell(++colCount).setCellValue(URL);
				row.createCell(++colCount).setCellValue(videoCopyDocName);
				row.createCell(++colCount).setCellValue(texts + "\n");

			}

			webAppDriver.captureScreenshot();
			Reporter.log("Not present in HTML:" + allFailedTexts);
			throw new AssertionError("[NOT FOUND]" + allFailedTexts);

		}
	}

	@Test(dataProvider = "SiteMapVideoProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyVideosOnSiteMap(String pageTitle) {
		navigator.gotoSiteMapPage();
		if (pageTitle.contains("'")) {
			String xpath = "//a[text()=\"" + pageTitle + "\"]";
			webAppDriver.clickElementByXpath(xpath);
		} else
			webAppDriver.clickElementByLinkText(pageTitle.trim());
		if (pageTitle.equalsIgnoreCase("All Videos")) {
			webAppDriver.verifyPresenceOfTextInDivTagText("How to Pack & Store");
		} else if (pageTitle.contains("'")) {
			String xpath = "//h1[text()=\"" + pageTitle + "\")]";
			webAppDriver.verifyElementPresentByXpath(xpath);
		} else {
			webAppDriver.verifyPresenceOfTextInH1Tag(pageTitle);
		}

	}

	@AfterClass(alwaysRun = true)
	public void writeExcel() throws IOException {
		File fileName = new File(
				"VideoLandingReports\\VideoLandingDesktopAutomationReport_" + dateFormat.format(date) + ".xlsx");
		fileName.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(fileName);
		excelWBook.write(fos);
		fos.flush();
		fos.close();
	}

}

