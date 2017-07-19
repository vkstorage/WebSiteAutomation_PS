package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.BrowserType;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;
//import com.phelps.ps.com.locators.SelfcareMyReservationsDetailsLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

/**
 * HTML: /rdp-conf-hold.aspx
 * 
 * @author amodak
 * 
 */
public class ECIRDPTestSuite extends BasicTestSuite implements ECIDetailsLocators, CommonLocators, ReservationCarePageLocators,
		SelfcareSummaryLocators, RDP_Conf_HoldLocators, ReservationDetailsLocators, ECI_RDP_ConfLocators, SelfCareContactInfoLocators,
		CommonSearchTextLocators {
	/*
	 * Reservation details
	 */
	String firstName = "Mary";
	String lastName = new UniqueId(new UniqueId().id).charId;
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String email = "testlinkps.comguest@gmail.com";
	String password = "test123";

	// current date
	Calendar c;
	String moveInDate;
	String dayOfMoveInDate;
	// String date =
	// String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH+3));

	CateredWebDriver secondDriver;

	/**
	 * 5. Verify Header
	 */

	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 2);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		webAppDriver.manage().deleteAllCookies();
	}

	@Test
	public void verifyHeadderOfRDPTest() {
	navigator.intoECI_RDP(searchText7, firstName, lastName, phoneNumber, ext, email, dayOfMoveInDate,0,0);
	// Scenario 1
			// Public Storage icon
			// webAppDriver.verifyAttributeValueByXpath(imgPSLogoXpath, "src",
			// "/images/nav_logo.png");
			// Menu items: Storage, Moving Suppliers, Company Info
			navigator.gotoHomePage();
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
			navigator.gotoHomePage();
			homePageRefreshActions.clickOnMovingSuppliesMenu();
			homePageRefreshActions.clickOnPackingSuppliesMenu();
			navigator.gotoHomePage();
			homePageRefreshActions.clickOnMovingSuppliesMenu();
			homePageRefreshActions.clickOnTapeMenu();
			navigator.gotoHomePage();

			// Compay Info
			homePageRefreshActions.clickOnCompanyInfoMenu();

			// Clicking on each Company Info menu options
			homePageRefreshActions.clickOnAboutUsMenu();
			webAppDriver.navigate().back();
			homePageRefreshActions.clickOnCompanyInfoMenu();
			homePageRefreshActions.clickOnInvestorRelationMenu();
			navigator.gotoHomePage();

			homePageRefreshActions.clickOnCompanyInfoMenu();
			homePageRefreshActions.clickOnGlobalLocationMenu();
			webAppDriver.navigate().back();
			homePageRefreshActions.clickOnCompanyInfoMenu();
			homePageRefreshActions.clickOnContactUsMenu();
			webAppDriver.navigate().back();

			// Need help text and phone number
			webAppDriver.verifyDivTagTextContains("Need Help?");
			webAppDriver.verifyDivTagTextContains("800.688.8057");
//		homeActions.hoverStorage();
//		webAppDriver.verifyPresenceOfTextInSpanTag("Self Storage");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Business Storage");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Vehicle Storage");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Size Guide");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Storage Blog");
//		homeActions.hoverMovingSupplies();
//		// webAppDriver.verifyPresenceOfTextInSpanTag("Storage Locks");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Packing Supplies");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Tape");
//		homeActions.hoverCompanyInfo();
//		webAppDriver.verifyPresenceOfTextInSpanTag("Investor Relations");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Global Locations");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Careers");
//		webAppDriver.verifyPresenceOfTextInSpanTag("Contact Us");
//		// My Account, Pay my bill on the right top
//		webAppDriver.verifyPresenceOfTextLocatedById(btnMyAccountId, "My Account");
//		webAppDriver.verifyPresenceOfTextLocatedById(btnPayMyBillId, "Pay My Bill");
//		webAppDriver.verifyPresenceOfTextLocatedById(btnReservationsId, "Reservations");
//		// Need help text and phone number
//		// tm_nh
//		webAppDriver.verifyDivTagTextEquals("NEED HELP?");
//		webAppDriver.verifySpanTagTextEquals("1-800-688-8057");
	}

	/**
	 * 3. Check the functionality of an application when clicked on “Change Unit”
	 * link on “Search Results” screen
	 */
	@Test
	public void verifyChangeUnitLinksOfRDPTest() {
		
		navigator.intoECI_RDP(searchText7, firstName, lastName, phoneNumber, ext, email,dayOfMoveInDate,0,0);
		webAppDriver.verifyLinkContent("Change Unit", "Manage Your Reservation");
	}

	/*	*//**
	 * 5. Verify footer
	 */
	/*
	 * @Test(dataProvider = "homepagelinkprovider", dataProviderClass =
	 * com.phelps.ps.com.dataprovider.TestDataProvider.class) public void
	 * verifyFooterLinkOfReservationPageTest(String linkData) {
	 * navigator.intoECI_RDP(searchByZip, firstName, lastName, phoneNumber, ext,
	 * email, password, date); String linkName = linkData.split(",")[0]; String
	 * expectedText = linkData.split(",")[1];
	 * webAppDriver.verifyLinkContent(linkName, expectedText, false); }
	 */

	// Added for WC2 Test Plan 1.3.1 point number 3
	@Test
	public void verifyLinksOnECIPageTest() {
		navigator.intoECI_RDP(searchText8, firstName, lastName, phoneNumber, ext, email, dayOfMoveInDate,0,0);
		webAppDriver.clickElementByLinkText("Change Unit");
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
		webAppDriver.navigate().back();
		webAppDriver.clickElementByLinkText("Manage Reservation");
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
	}

	/**
	 * 1. Mandatory fields on “Complete Your Express Check-In Now” screen :- •
	 * Address • City • State • Zip code • ID type • ID number • Emergency contact
	 * • Relationship • Phone number
	 */

	@Test
	public void verifyMandatoryFieldsTest() {
		navigator.intoECI_RDP(searchText9, firstName, lastName, phoneNumber, ext, email, dayOfMoveInDate,0,0);
		webAppDriver.verifyAttributeValueById(tbAddress1Id, "placeholder", "Address 1*");
		webAppDriver.verifyAttributeValueById(tbAddress2Id, "placeholder", "Address 2");
		webAppDriver.verifyAttributeValueById(ECIDetailsLocators.tbCityId, "placeholder", "City*");
		webAppDriver.verifyAttributeValueById(tbZIPId, "placeholder", "Zip code*");
		webAppDriver.verifyAttributeValueById(tbIDNumberId, "placeholder", "ID Number*");
		// Emergency contact
		webAppDriver.verifyAttributeValueById(tbEmCntFirstNameId, "placeholder", "First Name*");
		webAppDriver.verifyAttributeValueById(tbEMCntLastNameId, "placeholder", "Last Name*");
		webAppDriver.verifyAttributeValueById(tbEmCntPhoneId, "placeholder", "Phone Number*");
		eciDetailsActions.clickComplete();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbAddress1ErrorCss, "Please provide an address.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbCityErrorCss, "Please enter a valid city.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbStateErrorCss, "Please select a state.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbZipErrorCss, "Please provide a zip code.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbIdentificationTypeErrorCss, "Please select an identification type.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbIdNumberErrorCss, "Please provide a valid ID number.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmgContactFnameErrorCss, "Please enter a valid name.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmgContactLnameErrorCss, "Please enter a valid name.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbRelationshpErrorCss, "Please select a relationship.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbPhoneNumnerErrorCss, "Please enter a valid phone number.");
	}

	@AfterMethod(alwaysRun = true)
	public void closeSecondBroser() {

		if (secondDriver != null) {
			secondDriver.quit();

		}
	}

}
