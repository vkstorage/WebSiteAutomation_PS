package com.phelps.ps.com.testsuite;

import java.util.Set;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.HomeLocators;

public class PendingHomeTestSuite extends BasicTestSuite implements HomeLocators, CommonLocators, ECI_RDP_ConfLocators ,CommonSearchTextLocators{

	String firstName = "Mary";
	/* String phoneNumber = "705-869-3784"; */
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String password = "test123";
	String moveInDate;
	String dayOfMoveIn;
	String searchText =searchText46;
	String lastName;
	String email;
	String[] dates = {};

	// ECI details for speacial run 29-10-2015
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
	String reservationNumber="";

	@BeforeClass
	public void gotoPendingHome() {
		dates = commonActions.generateMoveInDate(6);
		moveInDate = dates[0];
		dayOfMoveIn = dates[1];
		lastName = "eva";//new UniqueId(new UniqueId().id).charId;
		email = envTestData.get("email");
		reservationNumber=navigator.gotoPendingHomePage(searchText, firstName, lastName, phoneNumber, ext, email, dayOfMoveIn);

	}

	@Test(priority=0)
	public void verifyPendingHomePageHeaderTest() {
		// Scenario 1c
		// Public Storage icon

	//	webAppDriver.verifyAttributeValueByXpath(imgPSLogoXpath, "src", "https://wc2psstaging.phelpsagency.com/images/nav_logo.png");
		// Menu items: Storage, Moving Suppliers, Company Info
		homeActions.hoverStorage();
		webAppDriver.verifyPresenceOfTextInSpanTag("Self Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Business Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Vehicle Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Size Guide");
		webAppDriver.verifyPresenceOfTextInSpanTag("Storage Blog");
		// Clicking on each Storage menu options
		homeActions.clickOnHowSelfStorageWorksMenu();
		homeActions.hoverStorage();
		homeActions.clickOnSelfStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnBusinessStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnVehicleStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnStorageBlogMenu();
		webAppDriver.navigate().back();
		homeActions.hoverMovingSupplies();
		//webAppDriver.verifyPresenceOfTextInSpanTag("Storage Locks");
		webAppDriver.verifyPresenceOfTextInSpanTag("Packing Supplies");
		webAppDriver.verifyPresenceOfTextInSpanTag("Tape");
		// Clicking on each Moving Supplies menu options
		homeActions.clickOnStorageBoxesMenu();
		navigator.gotoHomePageFromMovingSupplies();
		//homeActions.hoverMovingSupplies();
		//homeActions.clickOnStorageLocksMenu();
		/*navigator.gotoHomePageFromMovingSupplies();*/
		homeActions.hoverMovingSupplies();
		homeActions.clickOnPackingSuppliesMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnTapeMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverCompanyInfo();
		webAppDriver.verifyPresenceOfTextInSpanTag("Investor Relations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Global Locations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Careers");
		webAppDriver.verifyPresenceOfTextInSpanTag("Contact Us");
		// Clicking on each Company Info menu options
		homeActions.clickOnAboutUsMenu();
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

@Test(priority=1)
	public void verifyMyAccountPayMyBillReservationLink() {
		// Scenario 2
		navigator.redirectToHomePageNotLoggedIn();
		// scenario 3

		webAppDriver.clickElementById(btnMyAccountId);
		webAppDriver.clickElementByXpath(btnMyAccountLoginXpath);
		webAppDriver.verifySpanTagTextEquals("Sign In to Your Account");
		commonActions.clickPublicStorageIcon();
		webAppDriver.clickElementByXpath(btnPayMyBillXpath);
		
		//webAppDriver.clickElementById(btnPayMyBillId);
		webAppDriver.verifySpanTagTextEquals("Sign In to Your Account");
		commonActions.clickPublicStorageIcon();
		webAppDriver.clickElementById(btnReservationsId);
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Your Reservation");
		commonActions.clickPublicStorageIcon();

	}

	// Added for WC2
	// To test the manage reservation and ECI
	@Test(priority=2)
	public void verifyManageReservationAndECITest() {
		commonActions.clickPublicStorageIcon();
		webAppDriver.verifyPresenceOfTextInPTagText("Change Unit Size, Location or Move-in Date.");
		homeActions.clickOnManageReservationPendingHomePage();
		if (webAppDriver.verifyElementIsPresentByXpath("//span[text()='Manage Your Reservation']")){
			reservationCareLoginActions.enterEmailAddress(email);
			reservationCareLoginActions.enterReservationNumber(reservationNumber);
			reservationCareLoginActions.clickOnSubmit();
			webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
		}
		else
			webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
		commonActions.clickPublicStorageIcon();
		webAppDriver.verifyPresenceOfTextInPTagText("Save time on your move today!");
		homeActions.clickOnECIOnPendingHomePage();
	}

	@Test(priority=3)
	public void verifyECILinkNotPresentTest() {
		commonActions.clickPublicStorageIcon();
		homeActions.clickOnECIOnPendingHomePage();
		if (webAppDriver.verifyElementIsPresentByXpath("//span[text()='Express Check-In']")){
			reservationCareLoginActions.enterEmailAddress(email);
			reservationCareLoginActions.enterReservationNumber(reservationNumber);
			reservationCareLoginActions.clickOnSubmit();
			webAppDriver.verifySpanTagTextEquals("Complete Your Express Check-In Now.");
		}
		else
			webAppDriver.verifySpanTagTextEquals("Complete Your Express Check-In Now.");
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss, "Thank you for completing your Express Check-in.");
		commonActions.clickPublicStorageIcon();
		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);
		webAppDriver.verifyPresenceOfTextInPTagText("Change Unit Size, Location or Move-in Date.");

	}

	@Test(priority=4)
	public void verifySearchTest() {
		// Scenario 4
		navigator.gotoDefaultSearchResults("CA");
		// navigator.redirectToHomePageNotLoggedIn();
		commonActions.clickPublicStorageIcon();
		navigator.gotoDefaultSearchResults("60064");
		// navigator.redirectToHomePageNotLoggedIn();
		commonActions.clickPublicStorageIcon();
		navigator.gotoDefaultSearchResults("Los Angeles");
		// navigator.redirectToHomePageNotLoggedIn();
		commonActions.clickPublicStorageIcon();

	}

@Test
	public void verifyStorageMadeImagesLinksTest() {
		// Scenario 5,6,7
		navigator.gotoStorageMadeEasyContent();
		navigator.gotoPendingHomePage();
		commonActions.clickPublicStorageIcon();
		navigator.gotoStorageMadeReliableContent();
		// navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoPendingHomePage();
		navigator.gotoStorageMadeLocalContent();
		// navigator.redirectToHomePageNotLoggedIn();
		commonActions.clickPublicStorageIcon();
		navigator.gotoPendingHomePage();
	}

@Test
	public void verifyStorageMadeLinks() {
		// Scenario 8,9,10
		navigator.gotoPendingHomePage();
		webAppDriver.verifyLinkContent("Learn how to store with us", "Public Storage Videos", true);
		webAppDriver.verifyLinkContent("Learn more about us", "How Self Storage Works", true);
		webAppDriver.verifyLinkContent("Access our Location Finder", "Find Public Storage Locations", true);
	}

@Test
	public void verifyBenefitSection() {
		// Scenario 11
		navigator.gotoBenefitsContent();
		navigator.gotoPendingHomePage();
	}

@Test
	public void verifyBlogImageLink() {
		// Scenario 12
		webAppDriver.verifyLinkContent(imgBlogImageXpath, "Three Steps to Organize Your Kitchen Like a Pro", true);
	}

@Test
	public void verifyInYourSpaceLinksTest() {
		// Scenario 13,14
		navigator.gotoPendingHomePage();
		webAppDriver.verifyLinkContent("Read More", "Three Steps to Organize Your Kitchen Like a Pro", true);
		webAppDriver.verifyLinkContent("Visit our Storage Blog", "The Organized", true);

	}

@Test
public void verifyPHPReservationCareLogged(){
	commonActions.clearChache();
	navigator.gotoReservationCareLoggedIn(email, reservationNumber);
	homeActions.clickPublicStorageIcon();
	webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);
	
}

	//@Test(dataProvider = "homepagelinkproviderNewWindow", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class, priority = -1)
	public void verifyHomePageNewWindowLinksTest(String linkData) {
		// Scenario 13,14
		navigator.redirectToHomePageNotLoggedIn();
		String parentHandle = webAppDriver.getWindowHandle();
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.clickElementByLinkText(linkName);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		webAppDriver.relax(500);
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

	//@Test(dataProvider = "homepagelinkprovider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class, priority = -1)
	public void verifyHomePageLinksTest(String linkData) {
		// Scenario 15
		navigator.redirectToHomePageNotLoggedIn();
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.clickElementByLinkText(linkName);
		webAppDriver.relax(500);
		if (!webAppDriver.verifyTextContainInAnyTag(expectedText)) {
			webAppDriver.captureScreenshot();
			Reporter.log(expectedText);
			throw new AssertionError(expectedText + " not found");
		}
		// webAppDriver.verifyLinkContent(linkName.trim(), expectedText.trim(),
		// false);
	}
}


