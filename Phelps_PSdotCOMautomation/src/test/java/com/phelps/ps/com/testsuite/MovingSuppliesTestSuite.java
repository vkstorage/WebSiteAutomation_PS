package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.javascript.host.Navigator;
import com.phelps.ps.com.actions.SelfcareSummaryActions;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.MovingSuppliesLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class MovingSuppliesTestSuite extends BasicTestSuite implements MovingSuppliesLocators, ECIDetailsLocators , CommonSearchTextLocators {
	Calendar c;
	String moveInDate;
	int tomorrowDate;
	String dayOfMoveInDate;
	String reservationNumber;
	String firstName = "Mary";
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String password = "test123";
	String email = "muzfera.naaz@phelpsagency.com";
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

	/*
	 * TC-MS-01.1-Test visibility and / or placement of upsell links for moving
	 * supplies on Reservation Confirmation page
	 */

	/*
	 * Date today = new Date(); SimpleDateFormat df = new
	 * SimpleDateFormat("M/d/yyyy"); Calendar c = Calendar.getInstance();
	 */
	// Method to generate move in date
	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 12);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

	}
	//Commented this test case since the same is getting verified in verifyFunctionalityOfUpsellLinksTest test
@Test
	public void verifyPlacementOfUpsellLinksOnRDPPageTest() {
	String lastName="Everest";
		// current date
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		navigator.intoGetStartedPageWithStorage(searchText39,1,0);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		webAppDriver.verifyPresenceOfTextInDivTagText("ESSENTIAL MOVING SUPPLIES");

	}

	@Test
	public void verifyFunctionalityOfUpsellLinksTest() {
		String lastName="Angel";
		// current date
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		navigator.intoGetStartedPageWithStorage(searchText39,1,1);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		webAppDriver.verifyPresenceOfTextInDivTagText("ESSENTIAL MOVING SUPPLIES");
		webAppDriver.clickElementByCss(imgbuttonShopNowCss);
		webAppDriver.clickElementByLinkText("Useful Packing Tips");
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack like a Pro");
		webAppDriver.switchToWindowWithTitle("Public Storage: Storage Boxes, Moving Boxes, Packing Supplies");
		webAppDriver.switchToWindowWithTitle("Public Storage - Self-Storage Units/Spaces At Over 2,200 Facilities");

	}

//Commented this test case since the same is getting verified in verifyFunctionalityOfUpsellLinkOnResConfirmExpressCheckinTest test
	@Test
	public void verifyVisibilityOfUpsellLinkOnResConfirmExpressCheckinTest() {
		String lastName="Kim";
		// Search by ZIP
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText40,0,1);
				// Fill up reservation details
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();

		/*
		 * ECI ‘RDP Flow’ Form
		 * -https://psstaging.phelpsagency.com/eci-rdp.aspx?src=res
		 */
		eciDetailsActions.verifyUserDetails(storageDetails,ext, email, firstName, lastName, phoneNumber, moveInDate);
		// 3. Complete all form fields. Confirm whether you can submit the form
		// without completing all required fields.
		// WC2 commenting to remove username
		// String username =
		// webAppDriver.findElementByXpath(RDP_Conf_HoldLocators.lbUsernameXpath).getText().split(":")[1].trim();
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		// 4. Click the ‘COMPLETE’ button.
		eciDetailsActions.clickComplete();

		// verify member is logged in
		webAppDriver.verifyElementTextContainsByXpath("//div[contains(text(),'Thank you for choosing Public Storage!')]",
				"Thank you for choosing Public Storage!");
		// webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		reservationNumber = webAppDriver.findElementByXpath(lbECIPageReservationNumberXpath).getText();

		eci_RDP_ConfActions.verifyECIRDPConfDetails(storageDetails, firstName, lastName, ext,email, phoneNumber, moveInDate,
				reservationNumber);

		webAppDriver.verifyPresenceOfTextInDivTagText("ESSENTIAL MOVING SUPPLIES");

	}

	@Test
	public void verifyFunctionalityOfUpsellLinkOnResConfirmExpressCheckinTest() {
		String lastName="Dawson";

		String email = envTestData.get("email");
		// Search by ZIP
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText40,0,0);
		// String date =
		// String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		// Generate a unique last name
		
		// Fill up reservation details
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();

		/*
		 * ECI ‘RDP Flow’ Form
		 * -https://psstaging.phelpsagency.com/eci-rdp.aspx?src=res
		 */
		eciDetailsActions.verifyUserDetails(storageDetails,ext, email, firstName, lastName, phoneNumber, moveInDate);
		// 3. Complete all form fields. Confirm whether you can submit the form
		// without completing all required fields.
		// WC2 commenting to remove username
		// String username =
		// webAppDriver.findElementByXpath(RDP_Conf_HoldLocators.lbUsernameXpath).getText().split(":")[1].trim();
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		// 4. Click the ‘COMPLETE’ button.
		eciDetailsActions.clickComplete();

		// verify member is logged in
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");

		// eci_RDP_ConfActions.verifyECIRDPConfDetails(storageDetails, firstName,
		// lastName, email, phoneNumber);

		// Click the ‘My Account’ button
		// commonActions.clickMyAccount();
		// commonActions.verifyMyAccountAfterECI(storageDetails, firstName,
		// lastName, email, currentDate, address1, address2, city,zip, phoneNumber);
	//	reservationNumber = webAppDriver.findElementByXpath(lbECIRCPReservationNumberXpath).getText();

		eci_RDP_ConfActions.verifyECIRDPConfDetails(storageDetails, firstName, lastName,ext, email, phoneNumber, moveInDate,
				reservationNumber);

		webAppDriver.verifyPresenceOfTextInDivTagText("ESSENTIAL MOVING SUPPLIES");

		webAppDriver.clickElementByCss(imgbuttonShopNowCss);
		webAppDriver.clickElementByLinkText("Useful Packing Tips");
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack like a Pro");
		webAppDriver.switchToWindowWithTitle("Public Storage: Storage Boxes, Moving Boxes, Packing Supplies");
		webAppDriver.switchToWindowWithTitle("Public Storage - Self-Storage Units/Spaces At Over 2,200 Facilities");

	}
//Commented this test case since the same is getting verified in verifyFunctionalityOfUpsellLinksOnSelfCareReservationTest test
	@Test
	public void verifyVisibilityOfUpsellLinksOnSelfCareReservationTest() {
		String lastName="Kim";
		// current date
		
		navigator.intoGetStartedPageWithStorage(searchText41,0,0);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, dayOfMoveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		// WC2
		navigator.gotoReservationCareLogin();
		webAppDriver.verifyElementIsInvisibleByClassname("shop-now");
		// webAppDriver.clickElementByCss(imgbuttonShopNowCss);
	}

	
	//@Test
	public void verifyFunctionalityOfUpsellLinksOnSelfCareReservationTest() {
		String lastName="Dawson";
		navigator.intoGetStartedPageWithStorage(searchText41,0,1);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, moveInDate);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		// WC2
		navigator.gotoReservationCareLogin();
		// webAppDriver.clickElementByCss(imgbuttonShopNowCss);
		webAppDriver.verifyElementIsInvisibleByClassname("shop-now");
		webAppDriver.clickElementByLinkText("Useful Packing Tips");
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack like a Pro");
		webAppDriver.switchToWindowWithTitle("Public Storage: Storage Boxes, Moving Boxes, Packing Supplies");
		webAppDriver.switchToWindowWithTitle("Public Storage - Self-Storage Units/Spaces At Over 2,200 Facilities");
	}

//Commented this test case since the same is getting verified in //Commented this test case since the same is getting verified in verifyFunctionalityOfUpsellLinksOnSelfCareReservationTest test test
	//@Test
	public void verifyVisibilityOfUpsellLinksHomePagePendingCustomerTest() {
		String lastName="Kim";

		// current date
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		navigator.intoGetStartedPageWithStorage(searchText42,1,2);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		navigator.gotoHomePage();
		webAppDriver.verifyPresenceOfTextInDivTagText("ESSENTIAL MOVING SUPPLIES");
	}

	//@Test
	public void verifyFunctionalityOfUpsellLinksHomePagePendingCustomerTest() {
		String lastName="George";
		// current date
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		navigator.intoGetStartedPageWithStorage(searchText42,2,1);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		navigator.gotoHomePage();
		webAppDriver.verifyPresenceOfTextInDivTagText("ESSENTIAL MOVING SUPPLIES");
		webAppDriver.clickElementByCss(imgbuttonShopNowCss);
		webAppDriver.clickElementByLinkText("Useful Packing Tips");
		// webAppDriver.clickElementByXpath(hlnkUsefulPackingTipsXpath);
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack like a Pro");
		webAppDriver.switchToWindowWithTitle("Public Storage: Storage Boxes, Moving Boxes, Packing Supplies");
		webAppDriver.switchToWindowWithTitle("Public Storage - Self-Storage Units/Spaces At Over 2,200 Facilities");

	}
	
	@AfterMethod
	public void clearChache() {
		commonActions.clearChache();
	}
}
