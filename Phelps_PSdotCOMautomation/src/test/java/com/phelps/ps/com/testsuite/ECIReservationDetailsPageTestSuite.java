package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.ECIDetailsActions;
import com.phelps.ps.com.actions.ReservationDetailsActions;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;

import com.phelps.ps.com.locators.SelfcareSummaryLocators;

/**
 * HTML: /reservationdetails.aspx
 * 
 * @author amodak
 * 
 */
public class ECIReservationDetailsPageTestSuite extends BasicTestSuite
		implements ECIDetailsLocators, CommonLocators, SelfcareSummaryLocators, RDP_Conf_HoldLocators,
		ReservationDetailsLocators, ECI_RDP_ConfLocators, SelfCareContactInfoLocators, CommonSearchTextLocators {

	Calendar c;
	String moveInDate;
	int tomorrowDate;
	String dayOfMoveInDate;
	List<CateredWebElement> allCoupons = new ArrayList<CateredWebElement>();
	String newURL;

	String reservationNumber;

	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 7);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

	}

	/**
	 * 1. Verify followings on header of “Let’s Get Started” page - Public
	 * Storage icon - Menu items: Storage, Moving Suppliers, Company Info - My
	 * Account, Pay my bill on the right top - Need help text and phone number
	 */
	@Test
	public void verifyHeaderOfReservationDetailsTest() {
		navigator.intoGetStartedPageWithStorage(searchText17, 1, 1);
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
		/*homeActions.hoverStorage();
		webAppDriver.verifyPresenceOfTextInSpanTag("Self Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Business Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Vehicle Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Size Guide");
		webAppDriver.verifyPresenceOfTextInSpanTag("Storage Blog");
		homeActions.hoverMovingSupplies();
		// webAppDriver.verifyPresenceOfTextInSpanTag("Storage Locks");
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
		// WC2
		webAppDriver.verifyPresenceOfTextLocatedById(btnReservationsId, "Reservations");
		// Need help text and phone number
		// tm_nh
		webAppDriver.verifyDivTagTextEquals("NEED HELP?");
		webAppDriver.verifySpanTagTextEquals("1-800-688-8057");*/
	}

	/**
	 * 2. Verify ‘View Location info’ link on “Your Reservation” section opens a
	 * new modal and verify the content. 3. Verify click on “Change Location”
	 * redirects back to search results page. 4. Verify click on “Change Unit”
	 * redirects to search results page 5. Verify “Already a customer? Login
	 * Here.” link redirects to Login page
	 */
	@Test
	public void verifyLinksOfReservationDetailsPageTest() {
		// Search by ZIP
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText18, 0, 1);
		/*
		 * Test scenarios of 1.2/1/2,3,4,5
		 */

		// switch

		eciDetailsActions.clickOnViewLocationInfoLink();
		webAppDriver.switchTo().frame(webAppDriver.findElementByXpath("//iframe[@class='cboxIframe']").getWebElement());
		System.out.println("The source is " + webAppDriver.getPageSource());
		webAppDriver.verifyPresenceOfTextInH3Tag("Public Storage");
		webAppDriver.switchToWindowWithTitle("Self Storage - Storage - Storage Units Near 10451 - Public Storage");

		webAppDriver.clickElementById("cboxClose");
		
		 for (String add : storageDetails[0].split(" ")) {
		 
		 webAppDriver.verifyLinkContent("View Location Info", add); }
		 
		
		 webAppDriver.verifyLinkContent("View Location Info",storageDetails[2]);
		 
		webAppDriver.verifyLinkContent("Change Location", "Search Results");
		webAppDriver.verifyLinkContent("Change Unit", "Search Results");
		// removed verification of Already a customer link verification

	}

	/**
	 * 6. Verify click on “Why create account?” opens a help text
	 */
	// Removed this test case since for WC2 does not have WHy create account
	// link
	/*
	 * //@Test public void verifyWhyCreateAccountPopUpTest() { // Search by ZIP
	 * navigator.intoGetStartedPageWithStorage(searchByZip);
	 * webAppDriver.clickElementById(hlinkWhyCreateAccountId);
	 * webAppDriver.verifyElementPresentByXpath(poWhyCreateAccountXpath);
	 * webAppDriver.clickElementByXpath(btnWhyCreatAccountCloseXpath);
	 * webAppDriver.verifyElementIsInvisibleByXpath(btnWhyCreatAccountCloseXpath
	 * ); }
	 */
	/**
	 * 8. Verify except Ext all fields are mandatory and marked with *.
	 */
	@Test
	public void verifymandatoryFieldsOfReservationDetailsTest() {
		// Search by ZIP
		navigator.intoGetStartedPageWithStorage(searchText19, 0, 2);
		webAppDriver.verifyAttributeValueById(tbFirstNameId, "placeholder", "First Name*");
		webAppDriver.verifyAttributeValueById(tbLastNameId, "placeholder", "Last Name*");
		webAppDriver.verifyAttributeValueById(tbPhoneNumberId, "placeholder", "Phone Number*");
		webAppDriver.verifyAttributeValueById(tbExtId, "placeholder", "Ext.");
		webAppDriver.verifyAttributeValueById(tbEmailId, "placeholder", "Email*");
		webAppDriver.verifyAttributeValueById(tbEmailConfirmId, "placeholder", "Confirm Email*");
		// Wc2 Removed password fields verification
		webAppDriver.verifyAttributeValueById(tbMoveInDateId, "placeholder", "Move-In Date*");
		reservationDetailsActions.clickHoldNowOrComplete();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbFirstNameErrorCss, "Required field.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbLastNameErrorCss, "Required field.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbPhoneNumberErrorCss, "Required field.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailErrorCss, "Required field.");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbConfirmEmailErrorCss, "Required field.");
		// Wc2 Removed password fields validations
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbMoveInDateErrorCss, "Required field.");

		// WC2 Modification
		webAppDriver.verifyElementNotPresntByXpath(tbAccountPasswordXpath);
		webAppDriver.verifyElementNotPresntByXpath(tbAccountConfirmPasswordXpath);
		webAppDriver.verifyElementNotPresntByXpath(lbWhyCreateAccountXpath);
		webAppDriver.verifyElementNotPresntByXpath(lbPasswordVelidationTextXpath);
		webAppDriver.verifyElementNotPresntByLinkText(hlinkAlreadyCustomerlinkName);
	}
	
	
	//@Test
	public void verifyRDPLeftPanelTest(){
		List<CateredWebElement> elements=new ArrayList<CateredWebElement>();
		String[] features=new String[2];
		int prop=6;
		int plpUnit=1;
		navigator.gotoDefaultSearchResults(searchText53);
		commonActions.getAllSearchResultShowAllUnits().get(prop).click();
		elements=webAppDriver.findAllElementsByXpath("(//ul[@class='srp_list'])["+plpUnit+"]/li");
		for(int i=0;i<elements.size();i++){
			features[i]=elements.get(i).getText();
			System.out.println(features[i]);
		}
		commonActions.getAllPLPUnits().get(plpUnit-1).click();
		for(int j=0;j<features.length;j++){
			webAppDriver.verifyElementPresentByXpath("//div[@class='rdp_unit_feat']//div[contains(text(),'"+features[j]+"')]");
		}
		
		
		//searchResultsPageActions.clickContinueWithPosition(prop, continueButton);
		
	}

	/*	*//**
			 * 10. Verify footer of the page
			 */
	/*
	 * //@Test(dataProvider = "homepagelinkprovider", dataProviderClass =
	 * com.phelps.ps.com.dataprovider.TestDataProvider.class) public void
	 * verifyFooterLinkOfReservationPageTest(String linkData) {
	 * navigator.intoGetStartedPageWithStorage(searchByZip); String linkName =
	 * linkData.split(",")[0]; String expectedText = linkData.split(",")[1];
	 * webAppDriver.verifyLinkContent(linkName, expectedText, false); }
	 */

	//@Test
	public void verifyRDPReservationDepositTest() {
		String rdpConvenientmonthText = "Convenient Month-to-Month Lease";
		String depositRDPText = "Deposit for reservation (due now)";
		String rdpPaymentText1 = "TBD";
		String rdpPaymentText2 = "TBD";
		String rdpPaymentText3 = "TBD";

		int positionRDPProperty = 0;
		int positionPLPUnit = 1;

		String totalAmountDueText = "Total cost (due at move-in)**";
		String depositTermsText1 = "TBD";
		String depositTermsText2 = "TBD";
		String depositTermsText3 = "TBD";
		String depositTermsText4 = "TBD";
		String depositTermsText5 = "TBD";
		String depositTermsText6 = "TBD";
		String depositTermsText7 = "TBD";

		String firstName = "Mary";
		String phoneNumber = "705-869-3784";
		String ext = "111";

		String depositAmount = "";
		String lastName = "green";

		double totalDueAmount;
		String reservationDepositText = "Reservation Deposit:  ";
		String email = envTestData.get("email");
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchText2, positionRDPProperty,
				positionPLPUnit);
		Calendar now = GregorianCalendar.getInstance();
		int dayNumber = now.get(Calendar.DAY_OF_MONTH);
		webAppDriver.relax(500);

		webAppDriver.verifyElementNotPresntByXpath(lbRDPForFreeTextXpath);
		webAppDriver.verifyElementNotPresntByXpath(lbRDPSubHeaderNoCCXpath);
		webAppDriver.verifyElementPresentByXpath(lbRDPHeaderTextXpath);
		webAppDriver.verifyElementPresentByXpath(lbRDPSubHeaderConvenientXpath);

		reservationDetailsActions.enterFirstName(firstName);
		reservationDetailsActions.enterLastName(lastName);
		reservationDetailsActions.enterPhoneNumber(phoneNumber);
		reservationDetailsActions.enterExt(ext);
		reservationDetailsActions.enterEmail(email);
		reservationDetailsActions.enterConfirmEmail(email);

		reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, true);
		webAppDriver.relax(500);
		depositAmount = reservationDetailsActions.getDepositAmount();
		totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
				depositAmount);
		webAppDriver.verifyDivTagTextContains(depositRDPText + depositAmount);
		webAppDriver.verifyDivTagTextContains(totalAmountDueText + totalDueAmount);

		// deposit terms to be added

		webAppDriver.verifyDivTagTextContains(depositTermsText1);
		webAppDriver.verifyDivTagTextContains(depositTermsText2);
		webAppDriver.verifyDivTagTextContains(depositTermsText3);
		webAppDriver.verifyDivTagTextContains(depositTermsText4);
		webAppDriver.verifyDivTagTextContains(depositTermsText5);
		webAppDriver.verifyDivTagTextContains(depositTermsText6);
		webAppDriver.verifyDivTagTextContains(depositTermsText7);

		webAppDriver.verifyElementNotPresntById(opHoldUnitId);
		webAppDriver.verifyElementNotPresntById(opECIId);

		// Verify payment frame is displayed

		webAppDriver.switchtoiFrameByXpath(PaymentsLocators.iframePaymentXpath);
		webAppDriver.findElementByXpath(PaymentsLocators.btniframeNewMakePaymentXpath).click();
		webAppDriver.verifyElementPresentByXpath(PaymentsLocators.lbiframeErrorXpath);

	}
}
