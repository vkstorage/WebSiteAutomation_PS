package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.ReservationCareActions;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class ECIFlowTestSuite extends BasicTestSuite implements ECIDetailsLocators, CommonLocators, ReservationCarePageLocators,
		SelfcareSummaryLocators, RDP_Conf_HoldLocators, ReservationDetailsLocators, ECI_RDP_ConfLocators, HomeLocators,
		CommonSearchTextLocators {

	/*
	 * Reservation details
	 */
	String firstName = "Mary";
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String password = "test123";
	
	// current date

	/*
	 * ECI details
	 */

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
	Calendar c;
	String moveInDate;
	int tomorrowDate;
	String dayOfMoveInDate;
	List<CateredWebElement> allCoupons = new ArrayList<CateredWebElement>();
	String newURL;
	int positionRDPProperty;
	int positionPLPUnit;
	String reservationNumber;
	String email = "muzfera.naaz@phelpsagency.com";

	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 6);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		positionRDPProperty = 0;
		positionPLPUnit = 0;

	}

	/**
	 * RDP & ECI Flows: Testing paths TEST 1: RDP Flow Path 1: RDP > ECI
	 */

	// Changed return type to void from string
	 @Test
	public void veriyECIFlow1Test() {
		


		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText1, 0, positionPLPUnit);


		// Generate a unique last name
		// String lastName = new UniqueId(new UniqueId().id).charId;
		String lastName = "Garfield";
		// Fill up reservation details

		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();

		/*
		 * ECI ‘RDP Flow’ Form
		 */
		eciDetailsActions.verifyUserDetails(storageDetails, ext, email, firstName, lastName, phoneNumber, moveInDate);
		// 3. Complete all form fields. Confirm whether you can submit the form
		// without completing all required fields.

		// WC2
		reservationNumber = webAppDriver.findElementByXpath(lbECIPageReservationNumberXpath).getText();
		String[] reservationNo = reservationNumber.split(" ");
		reservationNumber = reservationNo[2];
		// WC2 commented below line
		// String username =
		// webAppDriver.findElementByXpath(RDP_Conf_HoldLocators.lbUsernameXpath).getText().split(":")[1].trim();
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		// 4. Click the ‘COMPLETE’ button.
		eciDetailsActions.clickComplete();

		// Removed for WC2
		// verify member is logged in
		/*
		 * webAppDriver.verifyElementTextContains(By.id(lbLoginNameId), firstName);
		 * webAppDriver.verifyPresenceOfTextLocatedById(hlinkLogoutId, "Logout");
		 */

		eci_RDP_ConfActions.verifyECIRDPConfDetails(storageDetails, firstName, lastName, ext, email, phoneNumber, moveInDate,
				reservationNumber);

		navigator.gotoReservationCareLogin();

		// Wc2 modified
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);
		// webAppDriver.verifyTextContainInAnyTag(lbReservationNoText +
		// reservationNumber);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);

		commonActions.clickPublicStorageIcon();

		// commonActions.getURLWithOptimizelyTrue();
		//webAppDriver.get(baseUrl+"/index3.aspx");

		// To verify the Express checkin not present inthe call out on pending home
		// page
		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);

	}

	/**
	 * RDP & ECI Flows: Testing paths TEST 2: Return Path 1a: RDP > Hold Conf:
	 * upsell > ECI
	 */
	//@Test
	public void veriyECIFlow2Test() {
		String lastName = "green";
		//String email = envTestData.get("email");
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText2, 0, positionPLPUnit);
		Calendar now = GregorianCalendar.getInstance();
		int dayNumber = now.get(Calendar.DAY_OF_MONTH);

		// commonActions.getURLWithOptimizelyTrue();
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();

		// WC2 added
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();

		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber, moveInDate);

		/*
		 * 4. Click the ‘EXPRESS CHECK-IN’ button in the middle of the page.
		 */

		// commonActions.getURLWithOptimizelyTrue();
		rdp_Conf_HoldActions.clickExpressCheckIn();
		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);

		/*
		 * 2. Complete all form fields. Confirm whether you can submit the form
		 * without completing all required fields.
		 */
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		//eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss, "Thank you for completing your Express Check-in.");
		// verify member is logged in

		// Wc2 removed verification of username

		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);
		// 4. Click the ‘My Account’ button in the top right corner of the page.
		// WC2 removed clicking on my account and verification

		// To verify unit size appears correctly on summary page Bug-2752
		// webAppDriver.verifyTextMatchesElementByXpath(storageDetails[1],
		// ".//*[@id='sc_reservations']//div[@class='sc_grey_bold_14 fLeft']");

		// To verify the move in date and unit size on reservations Bug-2752
		// WC2
		// selfcareSummaryActions.clickReservations();
		navigator.gotoReservationCareLogin();

		// commonActions.getURLWithOptimizelyTrue();
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);
		// webAppDriver.verifyTextContainInAnyTag(lbReservationNoText +
		// reservationNumber);
		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);
		// To verify the move in date on pending customer home page Bug-2752

		commonActions.clickPublicStorageIcon();
		//webAppDriver.get(baseUrl+"/index3.aspx");
		// commonActions.getURLWithOptimizelyTrue();
		// To verify the Express checkin not present inthe call out on pending home
		// page

		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);

		// Wc2 remove verification of move in date
		/*
		 * String[] moveInMonth = moveInDate.split("/"); String
		 * pendingHomeMoveInDate = "January " + moveInMonth[1] + ", 2016";
		 * System.out.println("" + pendingHomeMoveInDate);
		 * webAppDriver.verifyElementTextContainsByXpath
		 * (".//*[@id='context_column']//*[text()='Move-in Date']/..",
		 * pendingHomeMoveInDate);
		 */

	}

	/**
	 * RDP & ECI Flows: Testing paths TEST 3: Return Path 1b: RDP > ECI > Hold
	 * Conf: upsell > ECI
	 */
	// @Test
	public void veriyECIFlow3Test() {
		// Search by ZIP
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText3, 3, positionPLPUnit);
		// String date =
		// String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = "Ray";
		//String email = envTestData.get("email");
		// Fill up reservation details
		// commonActions.getURLWithOptimizelyTrue();
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);

		/*
		 * 1. Check the ‘Express Check-in’ radio button. 2. Click the ‘COMPLETE’
		 * button.
		 */
		webAppDriver.relax(500);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		// WC2
		reservationNumber = webAppDriver.findElementByXpath(lbECIPageReservationNumberXpath).getText();
		String[] reservationNo = reservationNumber.split(" ");
		reservationNumber = reservationNo[2];
		eciDetailsActions.verifyUserDetails(storageDetails, ext, email, firstName, lastName, phoneNumber, moveInDate);

		/*
		 * 4. Click the “Don't have time to complete this now? CLICK HERE to view
		 * your reservation confirmation.” links at the top and bottom of the page.
		 */
		eciDetailsActions.clickTopClickHere();
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber, moveInDate);

		// 4. Click the ‘EXPRESS CHECK-IN’ button in the middle of the page.

		// commonActions.getURLWithOptimizelyTrue();

		// rdp_Conf_HoldActions.clickExpressCheckIn();
		webAppDriver.navigate().back();

		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);
		eciDetailsActions.clickBottomClickHere();
		// commonActions.getURLWithOptimizelyTrue();
		rdp_Conf_HoldActions.clickExpressCheckIn();
		/*
		 * 4. Complete all form fields. Confirm whether you can submit the form
		 * without completing all required fields.
		 */
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		/*
		 * 5. Click the ‘COMPLETE’ button.
		 */
		eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss, "Thank you for completing your Express Check-in.");

		// WC2 removed
		/*
		 * webAppDriver.verifyElementTextContains(By.id(lbLoginNameId), firstName);
		 * webAppDriver.verifyPresenceOfTextLocatedById(hlinkLogoutId, "Logout");
		 */

		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);

		// Click the ‘My Account’ button
		// WC2 removed
		/*
		 * commonActions.clickMyAccount();
		 * commonActions.verifyMyAccountAfterECI(storageDetails, firstName,
		 * lastName, email, moveInDate, address1, address2, city, zip, phoneNumber);
		 */

		// To verify unit size appears correctly on summary page Bug-2752
		// webAppDriver.verifyTextMatchesElementByXpath(storageDetails[1],
		// ".//*[@id='sc_reservations']//div[@class='sc_grey_bold_14 fLeft']");

		// To verify the move in date and unit size on reservations Bug-2752
		// WC2 Removed clicking on clicking on reservations
		/* selfcareSummaryActions.clickReservations(); */
		// webAppDriver.verifyTextMatchesElementByXpath(moveInDate,
		// ".//*[@id='moveInDateSpan126155058']");
		// webAppDriver.verifyTextMatchesElementByXpath(storageDetails[1],
		// ".//*[@id='aspnetForm']//table[@class='tblMyReservations fLeft mTop']//td[2]/p[1]/strong");

		// To verify express checkin link not present on call out and as a link on
		// reservations Bug-2752
		// commonActions.getURLWithOptimizelyTrue();

		homeActions.clickOnReservationsLink();
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);
		// webAppDriver.verifyTextContainInAnyTag(lbReservationNoText +
		// reservationNumber);
		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRdpExpressCheckinId);
		// To verify the move in date on pending customer home page Bug-2752

		commonActions.clickPublicStorageIcon();
		//webAppDriver.get(baseUrl+"/index3.aspx");
		// commonActions.getURLWithOptimizelyTrue();

		// To verify the Express checkin not present inthe call out on pending home
		// page
		// WC2 remove verification of move in date and adding verification of eci
		// and manage yur reservation
		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);
		/*
		 * String[] moveInMonth = moveInDate.split("/"); String
		 * pendingHomeMoveInDate = "January " + moveInMonth[1] + ", 2016";
		 * System.out.println("" + pendingHomeMoveInDate);
		 * webAppDriver.verifyElementTextContainsByXpath
		 * (".//*[@id='context_column']//*[text()='Move-in Date']/..",
		 * pendingHomeMoveInDate);
		 */

	}

	/**
	 * RDP & ECI Flows: Testing paths TEST 4: Return Path 2a: RDP > Home page >
	 * ECI
	 */
	//@Test
	public void veriyECIFlow4Test() {
		// Search by ZIP
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText4, 1, positionPLPUnit);

		String lastName = "Ginger";
		//String email = envTestData.get("email");
		// Fill up reservation details

		// commonActions.getURLWithOptimizelyTrue();
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);

		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();

		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber, moveInDate);
		/*
		 * reservationDetailsActions.enterAllReservationDetails(firstName, lastName,
		 * phoneNumber, ext, email, email, password, password, dayOfMoveInDate);
		 */
		// Click public storage logo
		commonActions.clickPublicStorageIcon();
		// WC2 removed

		// commonActions.getURLWithOptimizelyTrue();
		webAppDriver.clickElementByXpath(btnECIPendingHomeXpath);
		//webAppDriver.get(baseUrl+"/index3.aspx");

		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss, "Thank you for completing your Express Check-in.");
		// WC2 removed

		/*
		 * webAppDriver.verifyElementTextContains(By.id(lbLoginNameId), firstName);
		 * webAppDriver.verifyPresenceOfTextLocatedById(hlinkLogoutId, "Logout");
		 */

		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);
		// 4. Click the ‘My Account’ button in the top right corner of the page.

		// Wc2 removed
		/*
		 * commonActions.clickMyAccount();
		 * commonActions.verifyMyAccountAfterECI(storageDetails, firstName,
		 * lastName, email, moveInDate, address1, address2, city, zip, phoneNumber);
		 */
		// To verify unit size appears correctly on summary page Bug-2752
		// webAppDriver.verifyTextMatchesElementByXpath(storageDetails[1],
		// ".//*[@id='sc_reservations']//div[@class='sc_grey_bold_14 fLeft']");

		// To verify the move in date and unit size on reservations Bug-2752

		// Wc2 removed
		// selfcareSummaryActions.clickReservations();
		// webAppDriver.verifyTextMatchesElementByXpath(moveInDate,
		// ".//*[@id='moveInDateSpan126155058']");
		// webAppDriver.verifyTextMatchesElementByXpath(storageDetails[1],
		// ".//*[@id='aspnetForm']//table[@class='tblMyReservations fLeft mTop']//td[2]/p[1]/strong");

		// To verify express checkin link not present on call out and as a link on
		// reservations Bug-2752

		// commonActions.getURLWithOptimizelyTrue();
		homeActions.clickOnReservationsLink();
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);
		// webAppDriver.verifyTextContainInAnyTag(lbReservationNoText +
		// reservationNumber);
		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);

		// To verify the move in date on pending customer home page Bug-2752

		commonActions.clickPublicStorageIcon();
		//webAppDriver.get(baseUrl+"/index3.aspx");

		// commonActions.getURLWithOptimizelyTrue();

		// To verify the Express checkin not present inthe call out on pending home
		// page
		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);

		// WC2 removed
		/*
		 * String[] moveInMonth = moveInDate.split("/"); String
		 * pendingHomeMoveInDate = "January " + moveInMonth[1] + ", 2016";
		 * System.out.println("" + pendingHomeMoveInDate);
		 * webAppDriver.verifyElementTextContainsByXpath
		 * (".//*[@id='context_column']//*[text()='Move-in Date']/..",
		 * pendingHomeMoveInDate);
		 */
	}

	/**
	 * RDP & ECI Flows: Testing paths TEST 5: Return Path 2b: RDP > Self Care >
	 * ECI
	 */
	// @Test
	public void veriyECIFlow5Test() {
		// Search by ZIP
		// String storageDetails[] = navigator.intoGetStartedPageWithStorage(zip);
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText5, 0, positionPLPUnit);
		// String date =
		// String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = "Kumar";
		//String email = envTestData.get("email");
		// Fill up reservation details

		// commonActions.getURLWithOptimizelyTrue();
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();

		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");

		/*
		 * ECI ‘RDP Flow’ Form
		 * -https://psstaging.phelpsagency.com/eci-rdp.aspx?src=res
		 */
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber, moveInDate);
		// Click My Account

		// WC2 remove and add
		// commonActions.clickMyAccount();

		/*
		 * Confirm that the ‘Reservations’ module has the same data you chose /
		 * entered on the on the SRP: a. Property b. Size c. Move-In Date
		 */
		/*
		 * webAppDriver.verifyElementTextContainsByXpath(SelfcareSummaryLocators.
		 * lbMoveInDateXpath, moveInDate); for (String add :
		 * storageDetails[0].replaceAll("\n", " ").split(" ")) {
		 * webAppDriver.verifyElementTextContainsByXpath
		 * (SelfcareSummaryLocators.lbStorageAddressXpath, add); }
		 * webAppDriver.verifyElementTextContainsByCss
		 * (SelfcareSummaryLocators.lbStorageAddressAndSizeCss, storageDetails[1]);
		 * webAppDriver.verifyPresenceOfTextInSpanTag("My Public Storage Account");
		 */

		// selfcareSummaryActions.clickReservations();
		homeActions.clickOnReservationsLink();
		// WC2 removed
		/*
		 * Self-Care > Reservation Details -
		 * https://psstaging.phelpsagency.com/selfcare/myreservationdetails.aspx 1.
		 * Confirm that the Reservation Details page contains the same data as you
		 * chose / entered on the on the SRP: a. Property b. Size c. Move-In Date d.
		 * Promotion e. Monthly Price
		 */
		for (String add : storageDetails[0].replaceAll("\n", " ").split(" ")) {
			webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbStorageAddressReservationCareXpath, add);
		}
		webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbStorageSizeXpath, storageDetails[1]);

		// webAppDriver.verifyElementTextContainsByXpath(SelfcareMyReservationsDetailsLocators.lbMoveInDateXpath,
		// currentDate); date format to be discussed. Bug 2273
		// webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbMonthlyRentXpath,
		// storageDetails[2]);

		// commonActions.getURLWithOptimizelyTrue();

		reservationCareActions.clickLeftExpressCheckIn();

		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);
		webAppDriver.navigate().back();

		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");

		// commonActions.getURLWithOptimizelyTrue();
		reservationCareActions.clickRightExpressCheckIn();

		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbEmailXpath, email);

		if (!webAppDriver.verifyTextContainInAnyTag(firstName)) {
			Reporter.log("first name not found " + firstName);
			webAppDriver.captureScreenshot();
			throw new AssertionError("first name not found " + firstName);
		}
		if (!webAppDriver.verifyTextContainInAnyTag(lastName)) {
			Reporter.log("first name not found " + lastName);
			webAppDriver.captureScreenshot();
			throw new AssertionError("first name not found " + lastName);
		}

		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss, "Thank you for completing your Express Check-in.");

		// WC2 removed
		/*
		 * webAppDriver.verifyElementTextContains(By.id(lbLoginNameId), firstName);
		 * webAppDriver.verifyPresenceOfTextLocatedById(hlinkLogoutId, "Logout");
		 */
		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);
		// 4. Click the ‘My Account’ button in the top right corner of the page.

		// Wc2 removed
		/*
		 * commonActions.clickMyAccount();
		 * commonActions.verifyMyAccountAfterECI(storageDetails, firstName,
		 * lastName, email, moveInDate, address1, address2, city, zip, phoneNumber);
		 */
		homeActions.clickOnReservationsLink();

		// To verify express checkin link not present on call out and as a link on
		// reservations Bug-2752

		// commonActions.getURLWithOptimizelyTrue();
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);
		//webAppDriver.verifyTextContainInAnyTag(lbReservationNoText + reservationNumber);
		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);
		// To verify the move in date on pending customer home page Bug-2752

		commonActions.clickPublicStorageIcon();
		// commonActions.getURLWithOptimizelyTrue();
		//webAppDriver.get(baseUrl+"/index3.aspx");
		// To verify the Express checkin not present inthe call out on pending home
		// page
		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);
		// wc2 remove
		/*
		 * String[] moveInMonth = moveInDate.split("/"); String
		 * pendingHomeMoveInDate = "January " + moveInMonth[1] + ", 2016";
		 * System.out.println("" + pendingHomeMoveInDate);
		 * webAppDriver.verifyElementTextContainsByXpath
		 * (".//*[@id='context_column']//*[text()='Move-in Date']/..",
		 * pendingHomeMoveInDate);
		 */

	}

	/**
	 * RDP & ECI Flows: Testing paths TEST 6: Return Path 2c: RDP > E‐mail >
	 * Self‐Care > ECI Note: As Email can not be automated so after enter
	 * resevation details test case will logout and login with the credentials
	 */
	//@Test
	public void veriyECIFlow6Test() {
		// Search by ZIP
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText6, positionRDPProperty, positionPLPUnit);
		// String date =
		// String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = "Galagher";
		String email = envTestData.get("email");
		// Fill up reservation details
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext,email, phoneNumber, moveInDate);
		homeActions.clickOnReservationsLink();
		// Read user name from reservation confirmation page

		// WC2 commented
		// String username =
		// webAppDriver.findElementByXpath(RDP_Conf_HoldLocators.lbUsernameXpath).getText().split(":")[1].trim();
		/*
		 * testLogger.info("Username :" + username);
		 * navigator.gotoSelfcareMyReservationDetails(username, password);
		 */
		/*
		 * Self-Care > Reservation Details -
		 * https://psstaging.phelpsagency.com/selfcare/myreservationdetails.aspx 1.
		 * Confirm that the Reservation Details page contains the same data as you
		 * chose / entered on the on the SRP: a. Property b. Size c. Move-In Date d.
		 * Promotion e. Monthly Price
		 */
		for (String add : storageDetails[0].replaceAll("\n", " ").split(" ")) {
			webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbStorageAddressReservationCareXpath, add);
		}
		webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbStorageSizeXpath, storageDetails[1]);

		reservationCareActions.clickLeftExpressCheckIn();
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbEmailXpath, email);
		if (!webAppDriver.verifyTextContainInAnyTag(firstName)) {
			Reporter.log("first name not found " + firstName);
			webAppDriver.captureScreenshot();
			throw new AssertionError("first name not found " + firstName);
		}
		if (!webAppDriver.verifyTextContainInAnyTag(lastName)) {
			Reporter.log("first name not found " + lastName);
			webAppDriver.captureScreenshot();
			throw new AssertionError("first name not found " + lastName);
		}
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss, "Thank you for completing your Express Check-in.");
		// eci_ReturnsActions.verifyECIDetails(storageDetails, firstName,
		// lastName,
		// email, phoneNumber);
		webAppDriver.verifyElementTextContains(By.id(lbLoginNameId), firstName);
		webAppDriver.verifyPresenceOfTextLocatedById(hlinkLogoutId, "Logout");
		// 4. Click the ‘My Account’ button in the top right corner of the page.
		commonActions.clickMyAccount();
		// commonActions.verifyMyAccountAfterECI(storageDetails, firstName,
		// lastName, email, moveInDate, address1, address2, city, zip,
		// phoneNumber);
	}

	@BeforeMethod
	public void clearChache() {
		commonActions.clearChache();
	}

}
