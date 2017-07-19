package com.phelps.ps.com.testsuite;

import java.awt.RenderingHints.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.ParseConversionEvent;

import org.apache.log4j.pattern.IntegerPatternConverter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.WebAssert;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.RecoverReservationNumberLocators;
import com.phelps.ps.com.locators.ReservationCareLoginLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;

public class ReservationCareTestSuite extends BasicTestSuite
		implements CommonLocators, ReservationCareLoginLocators, RecoverReservationNumberLocators,
		ReservationCarePageLocators, RDP_Conf_HoldLocators, ECIDetailsLocators, CommonSearchTextLocators {

	String emailId;
	String reservationNumber;
	String reservationNumber2;
	String reservationCareFullName;
	String reservationCareStreetAdd;
	String reservationCareUnitSize;
	String reservationCareState;
	String reservationCareCity;
	String reservationCareZip;
	String reservationCareFirstName;
	String reservationCareLastName;
	String firstName = "Mary";
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String password = "test123";
	String searchText = serchText47;

	String dayOfMoveInDate;
	String moveInDate;
	SimpleDateFormat sdt;
	Calendar c;
	String address1 = "3309";
	String address2 = "Alma Drive";
	String city = "Plano";
	String state = "TX";
	String zip = "75023";
	String idType = "US Passport";
	String idNumber = "XXX000111";
	String emgCntFirstname = "Bob";
	String emgCntLastName = "Smith";
	String relationship = "Employer";
	String emgCntPhoneNo = "705-285-4488";
	int position = 2;
	int propertyPosition = 7;
	int footerNumber = 1;
	
	@BeforeClass
	public void getUserData() {
		emailId = envTestData.get("emailForReservationCare");
		reservationNumber = "572157158";
		reservationCareFullName = envTestData.get("reservationCareFullName");
		reservationCareFirstName = reservationCareFullName.split(" ")[0];
		reservationCareLastName = reservationCareFullName.split(" ")[1];

		reservationCareUnitSize = envTestData.get("reservationCareUnitSize");
		reservationCareStreetAdd = envTestData.get("reservationCareStreetAddress");
		reservationCareState = envTestData.get("reservationCareState");
		reservationCareCity = envTestData.get("reservationCareCity");
		reservationCareZip = envTestData.get("reservationCareZip");
		sdt = new SimpleDateFormat("M/d/yyyy");
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 4);
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

	}
/*
 
	// header links test
	@Test(priority = 0)
	public void verifyReservationCareLoginPageHeaderTest() {
		commonActions.clearChache();
		navigator.gotoReservationCareLogin();
		webAppDriver.relax(500);
		// Scenario 1
		// Public Storage icon
		// webAppDriver.verifyAttributeValueByXpath(imgPSLogoXpath, "src",
		// "/images/nav_logo.png");
		// Menu items: Storage, Moving Suppliers, Company Info
		
		homePageRefreshActions.clickOnStorageMenu();

		// Clicking on each Storage menu options
		homePageRefreshActions.clickOnHowSelfStorageWorksMenu();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnStorageMenu();
		homePageRefreshActions.clickOnSelfStorageMenu();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnStorageMenu();
		homePageRefreshActions.clickOnBusinessStorageMenu();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnStorageMenu();
		homePageRefreshActions.clickOnVehicleStorageMenu();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnStorageMenu();
		homePageRefreshActions.clickOnStorageBlogMenu();
		webAppDriver.navigate().back();

		// Moving Supplies
		homePageRefreshActions.clickOnMovingSuppliesMenu();

		// Clicking on each Moving Supplies menu options
		homePageRefreshActions.clickOnStorageBoxesMenu();
		//navigator.gotoHomePage();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnMovingSuppliesMenu();
		homePageRefreshActions.clickOnPackingSuppliesMenu();
		//navigator.gotoHomePage();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnMovingSuppliesMenu();
		homePageRefreshActions.clickOnTapeMenu();
		//navigator.gotoHomePage();
		webAppDriver.navigate().back();
		
		// Compay Info
		homePageRefreshActions.clickOnCompanyInfoMenu();

		// Clicking on each Company Info menu options
		homePageRefreshActions.clickOnAboutUsMenu();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnCompanyInfoMenu();
		homePageRefreshActions.clickOnInvestorRelationMenu();
		//navigator.gotoHomePage();
		webAppDriver.navigate().back();

		homePageRefreshActions.clickOnCompanyInfoMenu();
		homePageRefreshActions.clickOnGlobalLocationMenu();
		webAppDriver.navigate().back();
		homePageRefreshActions.clickOnCompanyInfoMenu();
		homePageRefreshActions.clickOnContactUsMenu();
		webAppDriver.navigate().back();

		// Need help text and phone number
		webAppDriver.verifyDivTagTextContains("Need Help?");
		webAppDriver.verifyDivTagTextContains("800.688.8057");

		// To test the copy and bread crumb #1.5.1
		webAppDriver.verifyLiTagTextEquals(lbChangeUnitSizeLiText);
		webAppDriver.verifyLiTagTextEquals(lbChangeLocationLiText);
		webAppDriver.verifyLiTagTextEquals(lbChangeMoveInDateLiText);
		webAppDriver.verifyLiTagTextEquals(lbCancelReservationLiText);
		webAppDriver.verifyLiTagTextEquals(lbExpressCheckInLiText);
		webAppDriver.verifyLiTagTextEquals(lbManageYourReservationSpanText);
	}
*/
	// To test the login validations and functionality test plan #1.5.1
	@Test(priority = 1)
	public void verifyReservationCareRDPLoginFunctionalityTest() {
		webAppDriver.manage().deleteAllCookies();
		String wrongEmail = "sgshhshdshd";
		String wrongEmail1 = "sdg@jhkk";
		String wrongReservationNo = "abcd";
		String invalidReservationNo = "200702700";
		navigator.gotoReservationCareLogin();
		reservationCareLoginActions.enterEmailAddress(wrongEmail);
		reservationCareLoginActions.enterReservationNumber(wrongReservationNo);

		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyDivTagTextContains(lbValidEmailErrorDivText);
		webAppDriver.verifyDivTagTextContains(lbValidReservationNumberErrorDivText);

		reservationCareLoginActions.enterEmailAddress(emailId);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyDivTagTextContains(lbValidReservationNumberErrorDivText);
		webAppDriver.verifyElementPresentByXpath(
				"//*[contains(text(),'A valid email address is required.')][@style='display: none;']");
		reservationCareLoginActions.enterEmailAddress("");
		reservationCareLoginActions.enterReservationNumber(reservationNumber);
		webAppDriver.relax(500);
		WebDriverWait wait = new WebDriverWait(webAppDriver, 10);
		CateredWebElement element = (CateredWebElement) wait
				.until(ExpectedConditions.visibilityOf(webAppDriver.findElementByXpath(btnRservationCareLoginXpath)));
		reservationCareLoginActions.clickOnSubmit();
		// element.click();
		webAppDriver.verifyDivTagTextContains(lbValidEmailErrorDivText);
		webAppDriver.verifyElementPresentByXpath(
				"//*[contains(text(),'A valid reservation number is required.')][@style='display: none;']");

		reservationCareLoginActions.enterEmailAddress(wrongEmail1);
		reservationCareLoginActions.enterReservationNumber(reservationNumber);
		webAppDriver.relax(500);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyDivTagTextContains(lbEmailReservationNotMatchErrorDivText);
		navigator.gotoReservationCareLogin();

		reservationCareLoginActions.enterEmailAddress(emailId);
		// webAppDriver.findElementById(tbEmailAddressId).sendKeys(Keys.TAB);

		element = (CateredWebElement) wait
				.until(ExpectedConditions.visibilityOf(webAppDriver.findElementById(tbReservationNumberId)));
		element.sendKeys(invalidReservationNo);
		// reservationCareLoginActions.enterReservationNumber(wrongReservationNo);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyDivTagTextContains(lbEmailReservationNotMatchErrorDivText);
		navigator.gotoReservationCareLogin();

		reservationCareLoginActions.enterEmailAddress(emailId);

		reservationCareLoginActions.enterReservationNumber(reservationNumber);
		reservationCareLoginActions.clickOnSubmit();

		webAppDriver.relax(500);
		reservationCareActions.verifyUserLoggedInToReservationCare(reservationNumber, reservationCareFullName);

	}
/*
	@Test(priority = 2)
	public void verifyRecoverReservationNumberTest() {
		webAppDriver.manage().deleteAllCookies();
		String invalidEmail = "mmmm";
		navigator.gotoReservationCareLogin();
		navigator.gotoRecoverReservationNumber();
		reservationCareLoginActions.clickOnHomeLink();
		navigator.gotoReservationCareLogin();
		navigator.gotoRecoverReservationNumber();
		webAppDriver.verifyLiTagTextContains(lbRecoverReservationSpanText);
		webAppDriver.verifyDivTagTextContains(lbEnterEmailAddressDivText);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyDivTagTextContains(lbValidEmailErrorDivText);
		webAppDriver.navigate().refresh();
		reservationCareLoginActions.enterEmailAddress(invalidEmail);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyDivTagTextContains(lbValidEmailErrorDivText);
		reservationCareLoginActions.enterEmailAddress(emailId);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyElementPresentByXpath(lbEmailSentTextXpath);

	}

	@Test(priority = 3)
	public void verifyReservationCareLinksTest() {
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		if (!webAppDriver.verifyTextContainInAnyTag(reservationCareFirstName)) {
			throw new AssertionError(reservationCareFirstName + "  not found");
		}
		if (!webAppDriver.verifyTextContainInAnyTag(reservationCareLastName)) {
			throw new AssertionError(reservationCareLastName + "  not found");
		}

		if (!webAppDriver.verifyTextContainInAnyTag(reservationNumber)) {
			webAppDriver.captureScreenshot();
			throw new AssertionError(reservationNumber + "  not found");
		}

		webAppDriver.verifyElementPresentByLinkText("Cancel reservation");
		webAppDriver.verifyElementPresentByLinkText("Change size");
		webAppDriver.verifyElementPresentByLinkText("Change date");
		webAppDriver.verifyElementPresentByLinkText("Add to calendar");
		webAppDriver.verifyElementPresentByLinkText("Change location");
		webAppDriver.verifyElementPresentByXpath(imgReserveAnotherUnitXpath);

	}

	@Test(priority = 4)
	public void verifyChangeSizeLinkTest() {
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);

		int position = 1;
		reservationCareActions.clickChangeSizeLink();
		String newSpace = webAppDriver.findAllElementsByXpath(PLPLocators.lbStorageUnitAllUnitSizeXpath).get(position)
				.getText();
		commonActions.getAllPLPUnits().get(position).click();
		plpActions.clickChangeUnitConfirm();
		webAppDriver.verifyElementPresentByXpath(lbStorageSizeXpath + "[text()=\"" + newSpace + "\"]");
	}

	@Test(priority = 5)
	public void verifyChangeLocationLinkTest() {
		int position = 1;
		String newAddress[] = { "", "", "", "" };
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		reservationCareActions.clickChangeLocationLink();
		commonActions.getAllSearchResultShowAllUnits().get(position).click();
		newAddress[0] = webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText();
		newAddress[1] = webAppDriver.findElementByXpath(PLPLocators.lbSpaceAddressLocalityXpath).getText();
		newAddress[2] = webAppDriver.findElementByXpath(PLPLocators.lbSpaceAddressRegionXpath).getText();
		newAddress[3] = webAppDriver.findElementByXpath(PLPLocators.lbSpacePostalCodeXpath).getText();
		commonActions.getAllPLPUnits().get(position).click();

		plpActions.clickChangeUnitConfirm();
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[0].trim() + "')]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[1].trim() + "')]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[2].trim() + "')]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[3].trim() + "')]");
	}

	@Test(priority = 6)
	public void verifyAddCalendarLinkTest() {
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		webAppDriver.captureScreenshot();
		reservationCareActions.clickAddCalendarLink();
		reservationCareActions.closeAddCalendarIframe();

	}

	@Test(priority = 7)
	public void verifyChangeDateLinkTest() {
		commonActions.clearChache();
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		webAppDriver.captureScreenshot();
		webAppDriver.relax(500);
		String date = webAppDriver.findElementByXpath(lbMoveInDateReservationCareXpath).getText();
		String[] dates = date.split("/");
		String newDate = dates[1];
		date = reservationCareActions.clickChangeDateLink(newDate,1);
		if (!webAppDriver.verifyTextContainInAnyTag(Calendar.getInstance().get(Calendar.MONTH) + 1 + "/" + date + "/"
				+ Calendar.getInstance().get(Calendar.YEAR))) {
			Reporter.log(Calendar.getInstance().get(Calendar.MONTH) + 1 + "/" + date + "/"
					+ Calendar.getInstance().get(Calendar.YEAR));
			webAppDriver.captureScreenshot();
			throw new AssertionError(Calendar.getInstance().get(Calendar.MONTH) + 1 + "/" + date + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "is not displayed");
		}
	}

	@Test(priority = 8)
	public void verifyReserveAnotherUnitLinkTest() {
		navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		reservationCareActions.clickReserveAnotherUnit();
		searchResultsPageActions.clickContinueWithPosition(propertyPosition, 3);
		webAppDriver.relax(500);
		reservationDetailsActions.enterConfirmEmail(emailId);
		reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, false);
		reservationDetailsActions.clickHoldNowOrComplete();
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		homeActions.clickOnOldReservationsLink();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number " + reservationNumber);
	}

	@Test(priority = 9)
	public void verifyChooseAnotherReservationNoECITest() {
		commonActions.clearChache();
		String lastName = new UniqueId(new UniqueId().id).charId;
		navigator.intoRDP_Conf_Hold(searchText, firstName, lastName, phoneNumber, ext, emailId, dayOfMoveInDate, 0, 1);
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		navigator.intoGetStartedPageWithStorage("CA", 3, 2);
		reservationDetailsActions.enterConfirmEmail(emailId);
		reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, false);
		reservationDetailsActions.clickHoldNowOrComplete();
		reservationNumber2 = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		homeActions.clickOnReservationsLink();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number " + reservationNumber2);
		webAppDriver.verifyElementPresentByXpath(lbNotReservationLookingForXpath);
		reservationCareActions.clickChooseAnotherreservationLinkOnReservationCare();
		reservationCareLoginActions.enterEmailAddress(emailId);
		reservationCareLoginActions.enterReservationNumber(reservationNumber);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number " + reservationNumber);

	}

	@Test(priority = 10)
	public void verifyChooseAnotherReservationECIDirectTest() {
		commonActions.clearChache();

		String lastName = new UniqueId(new UniqueId().id).charId;
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText49, 0, 0);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, emailId, emailId,
				dayOfMoveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		// eciDetailsActions.verifyUserDetails(storageDetails, emailId,
		// firstName,
		// lastName, phoneNumber, moveInDate);
		reservationNumber = webAppDriver.findElementByXpath(lbECIPageReservationNumberXpath).getText();
		String[] reservationNo = reservationNumber.split(" ");
		reservationNumber = reservationNo[2];
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber,
				emgCntFirstname, emgCntLastName, relationship, emgCntPhoneNo);
		// 4. Click the ‘COMPLETE’ button.
		eciDetailsActions.clickComplete();
		navigator.intoGetStartedPageWithStorage(searchText50, 0, 1);
		reservationDetailsActions.enterConfirmEmail(emailId);
		reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, false);
		reservationDetailsActions.clickHoldNowOrComplete();
		reservationNumber2 = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		homeActions.clickOnReservationsLink();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number " + reservationNumber2);
		webAppDriver.verifyElementPresentByXpath(lbNotReservationLookingForXpath);
		reservationCareActions.clickChooseAnotherreservationLinkOnReservationCare();
		reservationCareLoginActions.enterEmailAddress(emailId);
		reservationCareLoginActions.enterReservationNumber(reservationNumber);
		reservationCareLoginActions.clickOnSubmit();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number " + reservationNumber);

	}

	@Test(priority = 11)
	public void verifyChooseAnotherReservationECIReturnTest() {
		commonActions.clearChache();

		String lastName = new UniqueId(new UniqueId().id).charId;
		String storageDetails[] = navigator.intoRDP_Conf_Hold(searchText51, firstName, lastName, phoneNumber, ext,
				emailId, dayOfMoveInDate, 0, 1);

		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		storageDetails = navigator.intoGetStartedPageWithStorage(searchByZip, propertyPosition, position);
		reservationDetailsActions.enterConfirmEmail(emailId);
		reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, false);
		reservationDetailsActions.clickHoldNowOrComplete();
		reservationNumber2 = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		homeActions.clickOnReservationsLink();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number " + reservationNumber2);
		webAppDriver.verifyElementPresentByXpath(lbNotReservationLookingForXpath);
		reservationCareActions.clickLeftExpressCheckIn();
		// eci_ReturnsActions.verifyECIDetails(storageDetails, firstName,
		// lastName,
		// emailId, phoneNumber, moveInDate)
		homeActions.clickOnReservationsLink();
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
		reservationCareActions.clickRightExpressCheckIn();
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbEmailXpath, emailId);
		webAppDriver.verifyTextContainInAnyTag(firstName);
		webAppDriver.verifyTextContainInAnyTag(lastName);
		webAppDriver.verifyElementPresentByXpath("//*[contains(text(),\"Not the reservation you're looking for?\")]");
		reservationCareActions.clickChooseAnotherreservationLinkOnECIPage();
		reservationCareLoginActions.enterEmailAddress(emailId);
		reservationCareLoginActions.enterReservationNumber(reservationNumber);
		reservationCareLoginActions.clickOnSubmit();
		String reservationNumberECI = webAppDriver.findElementByXpath(lbECIPageReservationNumberXpath).getText();
		String[] reservationNo = reservationNumberECI.split(" ");
		reservationNumberECI = reservationNo[2];
		webAppDriver.verifyEqualsString(reservationNumber, reservationNumberECI);

	}

	// @Test(priority = 12, dataProvider = "homepagelinkprovider",
	// dataProviderClass =
	// com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyReservationCarePageFooterLinksTest(String linkData) {
		// Scenario 15
		// navigator.redirectToHomePageNotLoggedIn();
		// navigator.gotoReservationCareLoggedIn(emailId, reservationNumber);
		if (footerNumber == 1) {
			homeActions.clickOnReservationsLink();
			footerNumber = 2;
		}
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.verifyLinkContent(linkName.trim(), expectedText.trim(), false);
	}
*/
}