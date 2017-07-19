package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;


import com.phelps.ps.com.locators.SelfcareSummaryLocators;

/**
 * 
 * @author amodak
 * 
 */
public class ECIReservationConfirmationTestSuite extends BasicTestSuite implements  ECIDetailsLocators, CommonLocators,
ReservationCarePageLocators, SelfcareSummaryLocators, RDP_Conf_HoldLocators, ReservationDetailsLocators,
		ECI_RDP_ConfLocators ,CommonSearchTextLocators{

	/*
	 * Reservation details
	 */
	String firstName = "Mary";

	String phoneNumber = "705-869-3784";
	String ext = "111";
	String email = "testlinkps.comguest@gmail.com";
	String password = "1234567";
	Calendar c=Calendar.getInstance();
	// current date
String moveInDate;
String date ;
	String[] storageDetails;
	
	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 5);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		date = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		webAppDriver.manage().deleteAllCookies();
	}

	/**
	 * 1. Verify followings on header of “Let’s Get Started” page - Public Storage
	 * icon - Menu items: Storage, Moving Suppliers, Company Info - My Account,
	 * Pay my bill on the right top - Need help text and phone number
	 */
	@Test
	public void verifyHeadersOfReservationDetailsConfTest() {
		String lastName = "Paul";
		storageDetails = navigator.intoRDP_Conf_Hold(searchText11, firstName, lastName, phoneNumber, ext, email,  date,3,0);
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
	}
					
		/*homeActions.hoverStorage();
		webAppDriver.verifyPresenceOfTextInSpanTag("Self Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Business Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Vehicle Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Size Guide");
		webAppDriver.verifyPresenceOfTextInSpanTag("Storage Blog");
		homeActions.hoverMovingSupplies();
		//webAppDriver.verifyPresenceOfTextInSpanTag("Storage Locks");
		webAppDriver.verifyPresenceOfTextInSpanTag("Packing Supplies");
		webAppDriver.verifyPresenceOfTextInSpanTag("Tape");
		homeActions.hoverCompanyInfo();
		webAppDriver.verifyPresenceOfTextInSpanTag("Investor Relations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Global Locations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Careers");
		webAppDriver.verifyPresenceOfTextInSpanTag("Contact Us");
		// My Account, Pay my bill on the right top
		webAppDriver.verifyPresenceOfTextLocatedById(btnMyAccountId, "My Account");
		webAppDriver.verifyPresenceOfTextLocatedById(btnPayMyBillId, "Pay My Bill");
		//WC2
		webAppDriver.verifyPresenceOfTextLocatedById(btnReservationsId, "Reservations");
		// Need help text and phone number
		// tm_nh
		webAppDriver.verifyDivTagTextEquals("NEED HELP?");
		webAppDriver.verifySpanTagTextEquals("1-800-688-8057");
		
	// WC2 Modification 
			webAppDriver.verifyElementNotPresntByLinkText("Edit");
			webAppDriver.verifyElementNotPresntByXpath(lbRDPConfirmationAccountNumberXpath);
			webAppDriver.verifyElementNotPresntByXpath(lbRDPConfirmationConfirmationNumberXpath);
			webAppDriver.verifyElementNotPresntByXpath(lbRDPConfirmationUsernameXpath);
			webAppDriver.verifyElementNotPresntByXpath(lbRDPConfirmationPasswordXpath);
			webAppDriver.verifyElementNotPresntByXpath("//*[contains(text(),'An account has been created for you on PublicStorage.com.')]");
	}

	*//**
	 * 3. Verify “Get directions” link redirects to map page
	 */
	@Test
	public void verifyGetDirectionsTest() {
		String lastName = new UniqueId(new UniqueId().id).charId;
		storageDetails = navigator.intoRDP_Conf_Hold(searchText12, firstName, lastName, phoneNumber, ext, email, date,0,0);
		String parentHandle = webAppDriver.getWindowHandle();
		String expectedText =	"Directions to Public Storage Property";
		webAppDriver.clickElementByLinkText("Get Directions");
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			if (!webAppDriver.verifyTextContainInAnyTag(expectedText)) {
				Reporter.log(expectedText);
				webAppDriver.captureScreenshot();
				webAppDriver.close();
				webAppDriver.switchTo().window(parentHandle);
				throw new AssertionError(expectedText);
			}
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);
	}
	}

	/**
	 * 4. Verify click on “Edit” redirects to account info page
	 * Removed due to WC2 changes
	 */
	/*@Test
	public void verifEditLinkTest() {
		String lastName = new UniqueId(new UniqueId().id).charId;
		storageDetails = navigator.intoRDP_Conf_Hold(searchByZip, firstName, lastName, phoneNumber, ext, email, password, date);
		webAppDriver.clickElementByLinkText("Edit");
		webAppDriver.verifySpanTagTextEquals("My Public Storage Account");
	}*/
//
	//Added for WC2
	@Test
	public void verifyChangeReservationLinkTest(){
		String lastName = new UniqueId(new UniqueId().id).charId;
		//navigator.gotoReservationCareLoggedIn(email, "126308207");
		 storageDetails=navigator.intoRDP_Conf_Hold(searchText13, firstName, lastName, phoneNumber, ext, email, date,3,0);
		 String confirmationNo=webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
			webAppDriver.clickElementByLinkText(hlinkManageReservationLink);
			webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Manage Reservation Number "+confirmationNo+"')]");
			webAppDriver.navigate().back();
			
			
	}

	/**
	 * 6. Verify “Packing tips” redirects to “How Self Storage Works” page
	 */
	@Test
	public void verifPackingTipsLinkTest() {
		String lastName = new UniqueId(new UniqueId().id).charId;
		storageDetails = navigator.intoRDP_Conf_Hold(searchText14, firstName, lastName, phoneNumber, ext, email, date,1,1);
		webAppDriver.clickElementByLinkText(linktextPackingTips);
		//webAppDriver.verifyTextWithSpacePresentInAnyTag("Packing and Storage Tips");
		 webAppDriver.verifyPresenceOfTextInH1Tag("Packing and Storage Tips You'll Love from Public Storage");
		
	}

	/**
	 * 6. Verify “Moving Supplies” redirects to “How Self Storage Works” page
	 */
	@Test
	public void verifMovingSuppliesPackingTipsLinkTest() {
		String lastName = new UniqueId(new UniqueId().id).charId;
		storageDetails = navigator.intoRDP_Conf_Hold(searchText15, firstName, lastName, phoneNumber, ext, email, date,0,1);
		String parentHandle=webAppDriver.getWindowHandle();
		webAppDriver.clickElementByCss(hlinkShopNowCss);
		Set<String> allHandles=webAppDriver.getWindowHandles();
		allHandles.remove(parentHandle);
		for(String handle:allHandles){
			webAppDriver.switchTo().window(handle);
			webAppDriver.verifyPresenceOfTextInH2Tag("Shopping Cart");
			
		}
		webAppDriver.close();
		webAppDriver.switchTo().window(parentHandle);	
		// Verify “Packing tips” redirects to “How Self Storage Works” page
		webAppDriver.clickElementByLinkText(linktextPackingTips);
		//webAppDriver.verifyTextWithSpacePresentInAnyTag("Packing and Storage Tips");
		// webAppDriver.verifyPresenceOfTextInH1Tag("\"Packing and Storage Tips You'll Love from Public Storage\"");
		webAppDriver.verifyElementPresentByXpath("//h1[contains(text(),\"Packing and Storage Tips You'll Love from Public Storage\")]");
		//webAppDriver.clickElementByLinkText("Shop Now");
		
		
	}

	/**
	 * 8. Verify following links are available under Additional Links section -
	 * Change Move-In Date - Add Outlook Reminder of Move-In Date - Reserve
	 * Another Unit - Print This Page
	 * 
	 * 9. Verify clicking on “Change Move-In Date” redirects to “My Public Storage
	 * Account” page 11. Verify clicking on “Reserve another unit” redirects to
	 * “Reserve Today: Month-To-Month Rent, No Obligations” page
	 */
	@Test
	public void verifyAdditionalLinksTest() {
		String lastName = new UniqueId(new UniqueId().id).charId;
		storageDetails = navigator.intoRDP_Conf_Hold(searchText16, firstName, lastName, phoneNumber, ext, email, date,0,1);
		webAppDriver.verifyPresenceOfTextLocatedByLink("Add Outlook Reminder of Move-In Date", "Add Outlook Reminder of Move-In Date");
		webAppDriver.verifyPresenceOfTextLocatedByLink("Print This Page", "Print This Page");
		webAppDriver.verifyLinkContent("Reserve Another Unit", "Search Results", false);
		webAppDriver.clickElementByLinkText("Change Move-In Date");
		
		//WC2
		//webAppDriver.verifyPresenceOfTextInPTagText("Reservation Details");
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number"
				);
	}

	
}
