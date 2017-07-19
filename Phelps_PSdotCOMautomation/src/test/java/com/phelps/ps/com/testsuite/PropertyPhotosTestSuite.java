package com.phelps.ps.com.testsuite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECI_RetrunLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.Phase5Locators;
import com.phelps.ps.com.locators.PropertyPhotosLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfCareMyStorageUnitLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class PropertyPhotosTestSuite extends BasicTestSuite implements PLPLocators, HomeLocators, ReservationDetailsLocators,
		Phase5Locators, SelfcareSummaryLocators, SearchResultsPageLocators, SelfCareMyStorageUnitLocators, RDP_Conf_HoldLocators,
		ECI_RetrunLocators, PropertyPhotosLocators , CommonSearchTextLocators{

	String description1 = "-exterior_1";
	String description2 = "-units_2";
	String description3 = "-interior-office_3";
	String description4 = "-security-monitor_4";
	String description4New = "-security-gate_4";
	String description4NewCamera="-security-camera_4";
	String description5 = "-gate-keypad_5";
	String suffix1 = "_slideshow_full";
	String suffix2 = "_slideshow_thumb";
	String firstName = "Mary";
	String lastName = "Jones";
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String email = "muzfera.naaz@phelpsagency.com";
	String password = "test123";
	// current date
	String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
	public String userForECI=propertyPhotosUserForECI;

	@Test(dataProvider = "PLP_URLDetails", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyPLPPhotosTest(String url) {

		String suffix = description1 + suffix1 + ".jpg";
		String propertyUrl = baseUrl + url;
		System.out.println(propertyUrl);
		webAppDriver.get(propertyUrl);
		webAppDriver.verifyPresenceOfTextInDivTagText("LOCATION");

		// Image 1
		String prefix = plpActions.getPrefixPropertyAddress(lbSpaceAddressId);
		String srcString = plpActions.getImageName(imgXpath);
		if (!srcString.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Thumbnail 1
		suffix = description1 + suffix2 + ".jpg";
		srcString = plpActions.getThumbnailImageName(imgThumbnailOneXpath);
		if (!srcString.contains("nolocation_photo_74.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Thumbnail 2
		suffix = description2 + suffix2 + ".jpg";
		srcString = plpActions.getThumbnailImageName(imgThumbnailTwoXpath);
		if (!srcString.contains("nolocation_photo_74.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Thumbnail 3
		suffix = description3 + suffix2 + ".jpg";
		srcString = plpActions.getThumbnailImageName(imgThumbnailThreeXpath);
		if (!srcString.contains("nolocation_photo_74.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Thumbnail 4
		suffix = description4 + suffix2 + ".jpg";
		srcString = plpActions.getThumbnailImageName(imgThumbnailFourXpath);
		if (!srcString.contains("nolocation_photo_74.gif")) {
			if (srcString.contains("security-gate_4_slideshow_thumb.jpg")) {
				suffix = description4New + suffix2 + ".jpg";
			}
			else if(srcString.contains("security-camera_4_slideshow_thumb.jpg"))
				suffix = description4NewCamera + suffix2 + ".jpg";
				}
		else if(srcString.contains("security-monitor_4_slideshow_thumb.jpg"))
			suffix = description4 + suffix2 + ".jpg";
		plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Thumbnail 5
		suffix = description5 + suffix2 + ".jpg";
		srcString = plpActions.getThumbnailImageName(imgThumbnailFiveXpath);
		if (!srcString.contains("nolocation_photo_74.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Image 2
		plpActions.clickImageThumbnail(imgThumbnailTwoXpath);
		srcString = plpActions.getImageName(imgXpath);
		suffix = description2 + suffix1 + ".jpg";
		if (!srcString.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Image 3
		plpActions.clickImageThumbnail(imgThumbnailThreeXpath);
		srcString = plpActions.getImageName(imgXpath);
		suffix = description3 + suffix1 + ".jpg";
		if (!srcString.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Image 4
		plpActions.clickImageThumbnail(imgThumbnailFourXpath);
		srcString = plpActions.getImageName(imgXpath);
		suffix = description4 + suffix1 + ".jpg";
		if (!srcString.contains("nolocation_photo_340.gif")){
			if (srcString.contains("security-gate_4")) {
				suffix = description4New + suffix1 + ".jpg";
			}
			else if(srcString.contains("security-camera_4"))
				suffix = description4NewCamera + suffix2 + ".jpg";
			else
				suffix = description4 + suffix1 + ".jpg";
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);
			
		}

		// Image 5
		plpActions.clickImageThumbnail(imgThumbnailFiveXpath);
		srcString = plpActions.getImageName(imgXpath);
		suffix = description5 + suffix1 + ".jpg";
		if (!srcString.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

		// Map photo verification
		//srcString = plpActions.getImageName(".//*[@id='map_canvas']/div/div[1]/div[3]/div[4]/div/div[2]/div/div/div/div[1]/img");
		srcString = plpActions.getImageName("//img[@class='map_bubble_thumb']");
		suffix = "-exterior_1_map.jpg";
		plpActions.verifyEqualImageSourceStrings(prefix + suffix, srcString);

	}

	@Test(dataProvider = "PLP_URLDetails", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyPLPPhotosRDPTest(String url) {

		
		// current date
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));

		String suffix = description1 + "_map.jpg";
		String propertyUrl = baseUrl + url;
		webAppDriver.get(propertyUrl);
		String prefix = plpActions.getPrefixPropertyAddress(lbSpaceAddressId);
		webAppDriver.verifyPresenceOfTextInDivTagText("LOCATION");
		plpActions.clickContinueButtonForUnit();
		String reservationPagePhotoName = plpActions.getImageName(propertyphotoXpath);
		phase5Actions.verifyStateLevelPageImage(reservationPagePhotoName);
		String nameToVerify = prefix + suffix;
		if (!reservationPagePhotoName.contains("nolocation_photo_93.gif"))
			plpActions.verifyEqualImageSourceStrings(reservationPagePhotoName, nameToVerify);

		// Bug Number : 2438
		/*
		 * reservationDetailsActions.clickViewLocationInfoLink();
		 * webAppDriver.switchtoiFrameByXpath(ViewLocationIndoIframeXpath); String
		 * imgSrc = plpActions.getImageName(propertyPhotoOnECIXpath); prefix =
		 * webAppDriver.findElementById(address1Id).getText();
		 * prefix=prefix+"-"+webAppDriver.findElementById(adrress2Id).getText();
		 * prefix=plpActions.convertAddressIntoImagePrefix(prefix);
		 * suffix=description1+suffix1+".jpg"; nameToVerify = prefix + suffix;
		 * plpActions.verifyEqualImageSourceStrings(imgSrc, nameToVerify);
		 * 
		 * 
		 * //Thumbnail 1 suffix=description1+suffix2+".jpg"; imgSrc =
		 * plpActions.getImageNameSrc(imgThumbnailOneECIXpath);
		 * plpActions.verifyEqualImageSourceStrings(prefix+suffix, imgSrc);
		 */

		/*
		 * reservationDetailsActions.enterAllReservationDetails(firstName, lastName,
		 * phoneNumber, ext, email, email, password, password, date);
		 * reservationDetailsActions.selectExpressCheckIn();
		 * reservationDetailsActions.clickHoldNowOrComplete(); String username =
		 * webAppDriver
		 * .findElementByXpath(RDP_Conf_HoldLocators.lbUsernameXpath).getText
		 * ().trim(); String userParts[] = username.split(":"); userForECI =
		 * userParts[1];
		 */

	}

	@Test
	public void verifyRDPConfirmationImagesTest() {

		String searchText = searchText44;
		// current date
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String ret[] = navigator.intoECI_RDP(searchText, firstName, lastName, phoneNumber, ext, email, date,0,0);
		String imgSrc = plpActions.getImageNameSrc(imageEciRdpXpath);
		phase5Actions.verifyStateLevelPageImage(imgSrc);
		String addressToVerify = ret[0];
		addressToVerify = plpActions.convertAddressIntoImagePrefix(addressToVerify) + "-exterior_1_map.jpg";
		plpActions.verifyEqualImageSourceStrings(imgSrc, addressToVerify);

	}

	@Test
	public void verifyEciRdpConfHoldTest() {

		String searchText = searchText45;
		String ret[] = navigator.intoRDP_Conf_Hold(searchText, firstName, lastName, phoneNumber, ext, email, date,0,0);
		String imgSrc = plpActions.getImageName(imageRdpConfHoldXpath);
		String addressToVerify = ret[0];
		addressToVerify = plpActions.convertAddressIntoImagePrefix(addressToVerify) + "-exterior_1_map.jpg";
		plpActions.verifyEqualImageSourceStrings(imgSrc, addressToVerify);
		reservationDetailsActions.clickExpressCheckInButton();
		imgSrc = plpActions.getImageName(imageEciReturnXpath);
		if (!imgSrc.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(imgSrc, addressToVerify);

		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";

		navigator.intoECI_RDP(searchText, firstName, lastName, phoneNumber, ext, email, date,0,0);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		imgSrc = plpActions.getImageName(imageEciRdpConfXpath);
		if (!imgSrc.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(imgSrc, addressToVerify);

	}

	@Test(dataProvider = "StateNamesProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyStateLevelPageTest(String stateName) {
		webAppDriver.get(baseUrl + "/" + stateName);
		for (WebElement photo : webAppDriver.findElements(By.xpath(imgAllPropertyPhotosXpath))) {
			phase5Actions.verifyStateLevelPageImage(photo.getAttribute("src"));
		}
	}

	@Test(dataProvider = "SRPProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifySpanishSearchResultPageTest(String srpSearchCriteria) {
		navigator.gotoSpanishSearchResultPage(srpSearchCriteria);
		spanishSRPActions.verifyPropertyImageSrc();
	}

	@Test(dataProvider = "CityNamesProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyCityLevelPageTest(String cityName) {
		String cityNameArray[] = cityName.split(",");
		webAppDriver.get(baseUrl + "/" + cityNameArray[0].replaceAll(" ", "+") + "/self-storage-" + cityNameArray[2] + "-"
				+ cityNameArray[1]);
		for (WebElement photo : webAppDriver.findElements(By.xpath(imgAllPropertyPhotosXpath))) {
			phase5Actions.verifyStateLevelPageImage(photo.getAttribute("src"));
		}
	}

	@Test(dataProvider = "SRPProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifySRPLevelPageTest(String srpSearchCriteria) {
		navigator.gotoDefaultSearchResults(srpSearchCriteria);
		List<CateredWebElement> imgList = webAppDriver.findAllElementsByXpath(imgCommonPhtXpath);
		List<CateredWebElement> addressList = webAppDriver.findAllElementsByXpath(lbCommonAddrXpath);
		List<String> imgSrcList = new ArrayList<String>();
		List<String> addressTextList = new ArrayList<String>();
		for (WebElement photo : imgList) {
			int indexOfSlash = photo.getAttribute("src").lastIndexOf("/") + 1;
			imgSrcList.add(photo.getAttribute("src").substring(indexOfSlash));
		}
		for (WebElement addrText : addressList) {
			addressTextList.add(addrText.getText());
		}
		for (int i = 0; i < imgList.size(); i++) {
			searchResultsPageActions.verifyImageSrcWithAddress(imgSrcList.get(i), addressTextList.get(i));
		}
	}

	@Test(priority = 2)
	public void verifySelfCareSummaryPhotoTest() {
		String userName = userForECI;
		String password = "test123";
		navigator.gotoMyAccountLoggedIn(userName, password);
		String imgListSrc = webAppDriver.findElementByXpath(imgPhotoXpath).getAttribute("src");
		int indexOfSlash = webAppDriver.findElementByXpath(imgPhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		imgListSrc = imgListSrc.substring(indexOfSlash);
		String addressText = webAppDriver.findElementByXpath(lbAddressXpath).getText();
		int indexOfExtension = addressText.lastIndexOf("-");
		addressText = addressText.substring(0, indexOfExtension);
		selfcareSummaryActions.verifyPhotoSrcWithAddress(imgListSrc, addressText);
	}

	@Test
	public void verifySpanishSRPViewLocationDetailsTest() {
		boolean flag = false;
		navigator.gotoSpanishSearchResultPage("90566");
		spanishSRPActions.clickViewLocationDetailsLink();

		String prefix = webAppDriver.findElementById(address1Id).getText();
		prefix = prefix + "-" + webAppDriver.findElementById(adrress2Id).getText();
		prefix = plpActions.convertAddressIntoImagePrefix(prefix);
		String suffix = description1 + suffix1 + ".jpg";
		String nameToVerify = prefix + suffix;
		String imgSrc = plpActions.getImageName(imgSpanishXpath);
		if (!imgSrc.contains("nolocation_photo_340.gif")) {
			flag = true;
			plpActions.verifyEqualImageSourceStrings(imgSrc, nameToVerify);
		}

		// Thumbnail 1
		suffix = description1 + suffix2 + ".jpg";
		imgSrc = plpActions.getThumbnailImageName(imgThumbnailOneSpanishXpath);
		if (!imgSrc.contains("nolocation_photo_74.gif") && flag != true) {
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);
		}

		// Thumbnail 2
		suffix = description2 + suffix2 + ".jpg";
		imgSrc = plpActions.getThumbnailImageName(imgThumbnailTwoSpanishXpath);
		if (!imgSrc.contains("nolocation_photo_74.gif") && flag != true) {
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);
		}

		// Thumbnail 3
		suffix = description3 + suffix2 + ".jpg";
		imgSrc = plpActions.getThumbnailImageName(imgThumbnailThreeSpanishXpath);
		if (!imgSrc.contains("nolocation_photo_74.gif")) {
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);
		}

		// Thumbnail 4
		suffix = description4 + suffix2 + ".jpg";
		imgSrc = plpActions.getThumbnailImageName(imgThumbnailFourSpanishXpath);
		if (!imgSrc.contains("nolocation_photo_74.gif")) {
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);
		}

		// Thumbnail 5
		suffix = description5 + suffix2 + ".jpg";
		imgSrc = plpActions.getThumbnailImageName(imgThumbnailFiveSpanishXpath);
		if (!imgSrc.contains("nolocation_photo_74.gif")) {
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);
		}

		// Image 2
		plpActions.clickImageThumbnail(imgThumbnailTwoSpanishXpath);
		imgSrc = plpActions.getImageName(imgXpath);
		suffix = description2 + suffix1 + ".jpg";
		if (!imgSrc.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);

		// Image 3
		plpActions.clickImageThumbnail(imgThumbnailThreeSpanishXpath);
		imgSrc = plpActions.getImageName(imgXpath);
		suffix = description3 + suffix1 + ".jpg";
		if (!imgSrc.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);

		// Image 4
		plpActions.clickImageThumbnail(imgThumbnailFourSpanishXpath);
		imgSrc = plpActions.getImageName(imgXpath);
		suffix = description4 + suffix1 + ".jpg";
		if (!imgSrc.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);

		// Image 5
		plpActions.clickImageThumbnail(imgThumbnailFiveSpanishXpath);
		imgSrc = plpActions.getImageName(imgXpath);
		suffix = description5 + suffix1 + ".jpg";
		if (!imgSrc.contains("nolocation_photo_340.gif"))
			plpActions.verifyEqualImageSourceStrings(prefix + suffix, imgSrc);

	}

	@Test(priority = 2)
	public void verifyReservationDetailsPhotoTest() {
	/*	String userName = userForECI;
		String password = "test123";*/
		
		String emailId=envTestData.get("emailForReservationCare");
		String reservationNumber="127292489";
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		//navigator.gotoMyAccountLoggedIn(userName, password);
		//selfcareSummaryActions.clickReservations();
		String imgListSrc = webAppDriver.findElementByXpath(imgLargePhotoXpath).getAttribute("src");
		int indexOfSlash = webAppDriver.findElementByXpath(imgLargePhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		imgListSrc = imgListSrc.substring(indexOfSlash);
		String addressText = webAppDriver.findElementByXpath(lbAddressForPhotoXpath).getText();
		int indexOfExtension = addressText.lastIndexOf("-");
		addressText = addressText.substring(0, indexOfExtension);
		selfcareSummaryActions.verifyPhotoSrcWithAddressForReservation(imgListSrc, addressText);

		String imgThumb1Src = webAppDriver.findElementByXpath(imgPhotoThumb1Xpath).getAttribute("src");
		int indexOfSlash1 = webAppDriver.findElementByXpath(imgPhotoThumb1Xpath).getAttribute("src").lastIndexOf("/") + 1;
		imgThumb1Src = imgThumb1Src.substring(indexOfSlash1);

		/*
		 * String imgThumb2Src= webAppDriver.findElementByXpath(imgPhotoThumb2Xpath
		 * ).getAttribute("src"); int indexOfSlash2 =
		 * webAppDriver.findElementByXpath
		 * (imgPhotoThumb2Xpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb2Src = imgThumb2Src.substring(indexOfSlash2);
		 * 
		 * String imgThumb3Src= webAppDriver.findElementByXpath(imgPhotoThumb3Xpath
		 * ).getAttribute("src"); int indexOfSlash3 =
		 * webAppDriver.findElementByXpath
		 * (imgPhotoThumb3Xpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb3Src = imgThumb3Src.substring(indexOfSlash3);
		 * 
		 * String imgThumb4Src= webAppDriver.findElementByXpath(imgPhotoThumb4Xpath
		 * ).getAttribute("src"); int indexOfSlash4 =
		 * webAppDriver.findElementByXpath
		 * (imgPhotoThumb4Xpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb4Src = imgThumb4Src.substring(indexOfSlash4);
		 * 
		 * String imgThumb5Src= webAppDriver.findElementByXpath(imgPhotoThumb5Xpath
		 * ).getAttribute("src"); int indexOfSlash5 =
		 * webAppDriver.findElementByXpath
		 * (imgPhotoThumb5Xpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb5Src = imgThumb5Src.substring(indexOfSlash5);
		 */

		String Suffix1 = "-exterior_1_slideshow_thumb.jpg";
		/*
		 * String Suffix2 = "-units_2_slideshow_thumb.jpg"; String Suffix3 =
		 * "-interior-office_3_slideshow_thumb.jpg"; String Suffix4 =
		 * "-security-monitor_4_slideshow_thumb.jpg"; String Suffix5 =
		 * "-gate-keypad_5_slideshow_thumb.jpg";
		 */

		selfcareSummaryActions.verifyPhotoSrcWithAddressForThumb(imgThumb1Src, addressText, Suffix1);
	}

	@Test(priority = 2)
	public void verifyHomePhotoTest() {
		String userName = userForECI;
		String password = "test123";
		navigator.gotoMyAccountLoggedIn(userName, password);
		navigator.gotoHomePage();
		String imgListSrc = plpActions.getThumbnailImageName(imgHomePhotoXpath);

		String addressText = webAppDriver.findElementByXpath(lbHomeAddressXpath).getText();
		int index = addressText.indexOf("(");
		addressText = addressText.substring(0, index);
		int indexOfExtension = addressText.lastIndexOf("-");
		addressText = addressText.substring(0, indexOfExtension);
		homeActions.verifyHomePhotoSrcWithAddress(imgListSrc, addressText);
	}

	@Test(priority = 2)
	public void verifySelfCareSummaryMyStorageUnitPhotoTest() {
		String userName = userForECI;
		String password = "test123";
		navigator.gotoMyAccountLoggedIn(userName, password);
		String imgListSrc = webAppDriver.findElementByXpath(imgPhotoMyStorageUnitXpath).getAttribute("src");
		int indexOfSlash = webAppDriver.findElementByXpath(imgPhotoMyStorageUnitXpath).getAttribute("src").lastIndexOf("/") + 1;
		imgListSrc = imgListSrc.substring(indexOfSlash);
		String addressText = webAppDriver.findElementByXpath(lbAddressMyStorageUnitXpath).getText();
		int indexOfExtension = addressText.lastIndexOf("-");
		addressText = addressText.substring(0, indexOfExtension);
		selfcareSummaryActions.verifyPhotoSrcWithAddressForSelfCareMyStorageUnit(imgListSrc, addressText);
	}

	@Test
	public void verifyLocationDetailsPhotoTest() {
		String userName = "summnliv@aol.com";
		String password = "test123";
		navigator.gotoMyStorageUnits(userName, password);
		/*selfCareMyStorageUnitsActions.clickLocationDetails();
		webAppDriver.switchtoiFrameByXpath(iframeViewLocationInfo);*/
		String url = webAppDriver.findElementByXpath(btnLocationDetailsXpath).getAttribute("href");
		// System.out.println("*********************" + url +
		// "*********************");
		webAppDriver.get(baseUrl+"//"+url);

		String imgListSrc = webAppDriver.findElementByXpath(imgfullXpath).getAttribute("src");
		int indexOfSlash = webAppDriver.findElementByXpath(imgfullXpath).getAttribute("src").lastIndexOf("/") + 1;
		imgListSrc = imgListSrc.substring(indexOfSlash);

		String prefix = webAppDriver.findElementById(address1Id).getText();
		prefix = prefix + "-" + webAppDriver.findElementById(adrress2Id).getText();
		System.out.println("============================" + prefix + "============================");
		// prefix=commonActions.convertAddressIntoImagePrefix(prefix);
		selfcareSummaryActions.verifyPhotoSrcWithAddressForReservation(imgListSrc, prefix);

		String imgThumb1Src = webAppDriver.findElementByXpath(imgThumb1PhotoXpath).getAttribute("src");
		int indexOfSlash1 = webAppDriver.findElementByXpath(imgThumb1PhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		imgThumb1Src = imgThumb1Src.substring(indexOfSlash1);

		/*
		 * String imgThumb2Src= webAppDriver.findElementByXpath(imgThumb2PhotoXpath
		 * ).getAttribute("src"); int indexOfSlash2 =
		 * webAppDriver.findElementByXpath
		 * (imgThumb2PhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb2Src = imgThumb2Src.substring(indexOfSlash2);
		 * 
		 * String imgThumb3Src= webAppDriver.findElementByXpath(imgThumb3PhotoXpath
		 * ).getAttribute("src"); int indexOfSlash3 =
		 * webAppDriver.findElementByXpath
		 * (imgThumb3PhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb3Src = imgThumb3Src.substring(indexOfSlash3);
		 * 
		 * String imgThumb4Src= webAppDriver.findElementByXpath(imgThumb4PhotoXpath
		 * ).getAttribute("src"); int indexOfSlash4 =
		 * webAppDriver.findElementByXpath
		 * (imgThumb4PhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb4Src = imgThumb4Src.substring(indexOfSlash4);
		 * 
		 * String imgThumb5Src= webAppDriver.findElementByXpath(imgThumb5PhotoXpath
		 * ).getAttribute("src"); int indexOfSlash5 =
		 * webAppDriver.findElementByXpath
		 * (imgThumb5PhotoXpath).getAttribute("src").lastIndexOf("/") + 1;
		 * imgThumb5Src = imgThumb5Src.substring(indexOfSlash5);
		 */

		String Suffix1 = "-exterior_1_slideshow_thumb.jpg";
		/*
		 * String Suffix2 = "-units_2_slideshow_thumb.jpg"; String Suffix3 =
		 * "-interior-office_3_slideshow_thumb.jpg"; String Suffix4 =
		 * "-security-monitor_4_slideshow_thumb.jpg"; String Suffix5 =
		 * "-gate-keypad_5_slideshow_thumb.jpg";
		 */

		selfcareSummaryActions.verifyPhotoSrcWithAddressForThumb(imgThumb1Src, prefix, Suffix1);

	}

	@Test
	public void verifyHomePageMoveInsPhotoTest() {
		String userName = "summnliv@aol.com";
		String password = "test123";
		navigator.gotoMyAccountLoggedIn(userName, password);
		navigator.gotoHomePage();
		String imgListSrc = plpActions.getThumbnailImageName(imgHomePhotoXpath);
		String addressText = webAppDriver.findElementByXpath(lbHomeAddressXpath).getText();
		int index = addressText.indexOf("(");
		addressText = addressText.substring(0, index);
		int indexOfExtension = addressText.lastIndexOf("-");
		addressText = addressText.substring(0, indexOfExtension);
		homeActions.verifyHomePhotoSrcWithAddress(imgListSrc, addressText);

	}

	@Test
	public void verifyDeleteImageFolder20142() {
		String url = "/images/properties/20142/public-storage-985-Fairway-Drive-walnut-ca-91789-exterior_1_slideshow_full.jpg";
		String propertyUrl = baseUrl + url;
		System.out.println(propertyUrl);
		webAppDriver.get(propertyUrl);
		// webAppDriver.verifyPresenceOfTextLocatedByXpath("lbErrorXpath",
		// "The resource you are looking for might have been removed, had its name changed, or is temporarily unavailable.");
		webAppDriver
				.verifyPresenceOfTextInH3Tag("The resource you are looking for might have been removed, had its name changed, or is temporarily unavailable.");
	}

	@Test
	public void verifyBug2435Test() {
		String img2435url = baseUrl
				+ "/images/properties/62929/public-storage-155-e-sunnyoaks-ave-campbell-ca-95008-exterior_1_map.jpg";
		webAppDriver.get(img2435url);
		webAppDriver.verifyAttributeValueByXpath(img2435Xpath, "src", img2435url);
	}

	@Test
	public void verifybug2453Test() {
		String img2453url1 = baseUrl + "/images/properties/68585/exterior_1_map.jpg";
		String img2453url2 = baseUrl + "/images/properties/24502/exterior_1_map.jpg";
		String img2453url3 = baseUrl + "/images/properties/68585/1_map.jpg";
		String img2453url4 = baseUrl + "/images/properties/24502/1_map.jpg";
		webAppDriver.get(img2453url1);
		webAppDriver.verifyAttributeValueByXpath(img24531Xpath, "src", img2453url1);
		webAppDriver.get(img2453url2);
		webAppDriver.verifyAttributeValueByXpath(img24532Xpath, "src", img2453url2);
		webAppDriver.get(img2453url3);
		webAppDriver.verifyPresenceOfTextInH2Tag("404 - File or directory not found.");
		webAppDriver.get(img2453url4);
		webAppDriver.verifyPresenceOfTextInH2Tag("404 - File or directory not found.");
	}

}
