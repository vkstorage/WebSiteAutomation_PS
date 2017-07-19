package com.phelps.ps.com.testsuite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.WebAssert;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.HowSelfStorageWorksLocators;
import com.phelps.ps.com.locators.ReservationCareLoginLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;

public class HomeTestSuiteLoggedIn extends BasicTestSuite implements HomeLocators, CommonLocators, HowSelfStorageWorksLocators,
		ReservationCareLoginLocators, ReservationCarePageLocators ,CommonSearchTextLocators{
	Map<String, Boolean> isTextPresent;
	Iterator<Map.Entry<String, Boolean>> iterator;
	static boolean allTextPresent;
	StringBuilder failedText;
	
	@BeforeClass(alwaysRun = true)
	public void loginToSystemTest() {
		navigator.login(homeLoginUsername, "test123");
		// webAppDriver.verifyPresenceOfTextInH3Tag("Move-in Date");
	}

	@Test
	public void verifyGeneralLinksLoggedInTest() {
		// scenario 2,3
		navigator.gotoHomePage();
		homeActions.clickOnMyAccountLoggedIn();

		// /webAppDriver.verifyTextMatchesElementByXpath("My Public Storage Account",
		// xpath)
		// webAppDriver.verifyLinkContent("My Account",
		// "My Public Storage Account");
		homeActions.clickOnPayMyBill();
		webAppDriver.verifyPresenceOfTextInPTagText("Make a Payment");
		// webAppDriver.verifyLinkContent("Pay My Bill",
		// "My Public Storage Account");
		homeActions.clickOnReservationsLink();
		webAppDriver.verifyTextContainInAnyTag(lbManageYourReservationSpanText);

		// webAppDriver.verifyLinkContent("Make A ",
		// "Reserve Today: Month-To-Month Rent, No Obligations");
		navigator.gotoHomePage();
		// //*[@id='property_info']/table/tbody/tr[1]/td[2]/span/a[text()='Get
		// Directions']webAppDriver.verifyLinkContent("Check Current Balance", "My
		// Public Storage Account");
		homeActions.clickOnGetDirectionsLink();
	}

	@Test
	public void verifyMakeReservationLinkTest() {
		// scenario 4
		isTextPresent = new HashMap<String, Boolean>();
		iterator = isTextPresent.entrySet().iterator();
		failedText=new StringBuilder();
		navigator.gotoPublicStorageMenu();
		webAppDriver.clickElementByLinkText("Location Finder");		
		isTextPresent.put("Location Finder - Find Public Storage Locations",webAppDriver.verifyTextContainInAnyTag("Find Public Storage Locations"));
		navigator.gotoPublicStorageMenu();
		webAppDriver.clickElementByLinkText("Packing & Storage Tips");		
		isTextPresent.put("Packing & Storage Tips-Packing and Storage Tips You'll Love from Public Storage",webAppDriver.verifyTextContainInAnyTag("Packing and Storage Tips You'll Love from Public Storage"));
		navigator.gotoPublicStorageMenu();
		webAppDriver.clickElementByLinkText("How To Videos");		
		isTextPresent.put("How To Videos-Public Storage Videos",webAppDriver.verifyTextContainInAnyTag("How to Pack & Store"));
		navigator.gotoPublicStorageMenu();
		webAppDriver.clickElementByLinkText("Size Guide");		
	//	isTextPresent.put("Size Guide-Storage Size Guide — Frequently Asked Questions",webAppDriver.verifyTextContainInAnyTag("Storage Size Guide — Frequently Asked Questions"));
		webAppDriver.verifyPresenceOfTextInDivTagText("Storage Size Guide — Frequently Asked Questions");
		while(iterator.hasNext()){
			if(!iterator.next().getValue()){
				allTextPresent = false;
				failedText.append(iterator.next().getKey());
				failedText.append(System.lineSeparator());
			}
		}
		
		if(!allTextPresent){
			webAppDriver.captureScreenshot();
			Reporter.log(failedText.toString() + " not displayed on the Home page");
			throw new AssertionError(failedText.toString() + " not displayed on the on the Home page");
		}
		/*webAppDriver.verifyLinkContent("Size Guide", "Storage Size Guide — Frequently Asked Questions");
		webAppDriver.verifyLinkContent("Location Finder", "Find Public Storage Locations");
		webAppDriver.verifyLinkContent("Packing & Storage Tips", "Packing and Storage Tips You'll Love from Public Storage");
		webAppDriver.verifyLinkContent("How To Videos", "Public Storage Videos");*/

	}

	@Test
	public void verifyHowSelfStorageMenuTest() {
		// scenario 6
		navigator.gotoHowStorageWorksMenu();
	}

	@Test
	public void verifyStartingOutTabSubtabWhoWeAreTest() {
		// scenario 7
		navigator.gotoPublicStorageMenu();
		webAppDriver.verifyLinkContent(imgPromoXpath, "Storage Boxes");
		webAppDriver.verifyLinkContent(lnkWhyChooseUs, "Public Storage Videos");
		webAppDriver.verifyLinkContent(lnkSee, "Public Storage Videos");
	}

	@Test
	public void verifyStartingOutTabSubtabWhatWeOfferTest() {
		// scenario 8
		navigator.gotoWhatWeOfferSubTab();
		webAppDriver.verifyLinkContent(lnkSizeGuideXpath, "Size");
		webAppDriver.verifyLinkContent(lnkReservingXpath, "How Self Storage Works");
		webAppDriver.verifyLinkContent(lnkReservingXpath, "How Self Storage Works");
		webAppDriver.verifyLinkContent(lnkYourAccountXpath, "Sign In to Your Account");
	}

	@Test
	public void verifyStartingOutTabSubtabHowToStoreTest() {
		// scenario 10
		
		navigator.gotoHowToStoreSubTab();
		webAppDriver.clickElementByXpath(lnkHowToStoreInFiveSteps);
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Store in 5 Easy Steps");
		navigator.gotoHowToStoreSubTab();
		howSelfStorageWorksActions.hoverFindStepOne();
		webAppDriver.clickElementByLinkText("Location Finder");
		webAppDriver.verifyPresenceOfTextInH1Tag("Find Public Storage Locations");
		navigator.gotoHowToStoreSubTab();
		howSelfStorageWorksActions.hoverFindStepOne();
		webAppDriver.clickElementByLinkText("Learn More");
		webAppDriver.verifyElementPresentByXpath("//img[@alt='Thousands of Locations Across the U.S.']");
		navigator.gotoHowToStoreSubTab();
		howSelfStorageWorksActions.hoverFindStepTwo();
		webAppDriver.clickElementByLinkText("Learn More");
		webAppDriver.verifyElementPresentByXpath("//img[@id='have1']");
		navigator.gotoHowToStoreSubTab();
		howSelfStorageWorksActions.hoverFindStepThree();
		webAppDriver.clickElementByLinkText("Learn More");
		webAppDriver.verifyElementPresentById("res67");
		navigator.gotoHowToStoreSubTab();
		howSelfStorageWorksActions.hoverFindStepFour();
		webAppDriver.verifyElementPresentByXpath(".//*[@id='pt1'][@href='/Public_Storage-Packing_TipsBook.pdf']");
		webAppDriver.verifyElementPresentByXpath(".//*[@id='lbox1'][@href='http://supplies.publicstorage.com/browse/moving-supplies-packing-supplies/boxes']");
		webAppDriver.verifyElementPresentByXpath(".//*[@id='ltape1'][@href='http://supplies.publicstorage.com/browse/moving-supplies-packing-supplies/tape']");
		webAppDriver.verifyElementPresentByXpath(".//*[@id='lpack1'][@href='http://supplies.publicstorage.com/browse/moving-supplies-packing-supplies/packing-supplies']");
		navigator.gotoHowToStoreSubTab();
		howSelfStorageWorksActions.hoverFindStepFive();
		webAppDriver.verifyElementPresentByXpath(".//*[@id='A3'][@href='/Public_Storage-Packing_TipsBook.pdf']");
		webAppDriver.clickElementByLinkText("Learn More");
		webAppDriver.verifyElementPresentById("move8");
		
		/*navigator.gotoHowToStoreSubTab();
		webAppDriver.verifyLinkContent(lnkHowToStoreInFiveSteps, "Public Storage Videos");
		howSelfStorageWorksActions.hoverFindStepOne();
		webAppDriver.verifyLinkContent("Location Finder", "Find Public Storage Locations");
		webAppDriver.verifyLinkContent("Learn More", "How Self Storage Works");
		howSelfStorageWorksActions.hoverFindStepTwo();
		webAppDriver.verifyLinkContent("Size Guide", "Size");
		webAppDriver.verifyLinkContent("Learn More", "How Self Storage Works");
		howSelfStorageWorksActions.hoverFindStepThree();
		webAppDriver.verifyLinkContent("Learn More", "How Self Storage Works");
		howSelfStorageWorksActions.hoverFindStepFour();
		webAppDriver.verifyLinkContent("tape", "Carton Sealing Tape");
		webAppDriver.verifyLinkContent("boxes", "Storage Boxes");
		webAppDriver.verifyLinkContent("packing supplies", "Packing Supplies");
		howSelfStorageWorksActions.hoverFindStepFive();
		webAppDriver.verifyLinkContent("Learn More", "How Self Storage Works");*/
	}

	@Test
	public void verifyReservingTabTheBasicSubTabTest() {
		// scenario 11
		
		navigator.gotoReservingTab();
		webAppDriver.clickElementByLinkText("\"How to Store in 5 Easy Steps,\"");
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Store in 5 Easy Steps");
		navigator.gotoReservingTab();
		howSelfStorageWorksActions.searchOnBasicSubTab(searchText6);
	/*	navigator.gotoReservingTab();
		webAppDriver.verifyLinkContent("\"How to Store in 5 Easy Steps,\"", "Public Storage Videos");
		howSelfStorageWorksActions.searchOnBasicSubTab(searchText6);*/
	}

	@Test
	public void verifyReservingTabTheFindLocationSubTabTest() {
		// scenario 11
		
		navigator.gotoReservingTab();
		howSelfStorageWorksActions.clickOnFindLocationSubTab();
		webAppDriver.clickElementByLinkText("Location Finder");
		webAppDriver.verifyPresenceOfTextInH1Tag("Find Public Storage Locations");
		navigator.gotoReservingTab();
		howSelfStorageWorksActions.clickOnFindLocationSubTab();
		howSelfStorageWorksActions.searchOnFindLocationSubTab(searchText6);
		/*navigator.gotoReservingTab();
		howSelfStorageWorksActions.clickOnFindLocationSubTab();
		webAppDriver.verifyLinkContent("Location Finder", "Find Public Storage Locations");
		howSelfStorageWorksActions.searchOnFindLocationSubTab(searchText6);*/
	}

	@Test
	public void verifyReservingTabSelectUnitSubTabTest() {
		// scenario 11
		navigator.gotoReservingTab();
		howSelfStorageWorksActions.clickOnSubTabSelectUnit();
		webAppDriver.verifyLinkContent("Size Guide.", "Size");
		webAppDriver.verifyLinkContent("Size Guide.", "Guide");
	}

	@Test
	public void verifyReservingTabUnitPricesSubTabTest() {
		// scenario 11
		navigator.gotoReservingTab();
		howSelfStorageWorksActions.clickOnSubTabUnitPrices();
		howSelfStorageWorksActions.searchOnUnitPricesSubTab(searchText6);
	}

	@Test
	public void verifyReservingTabReservationsSubTabTest() {
		// scenario 11
		navigator.gotoReservingTab();
		howSelfStorageWorksActions.clickOnSubTabReservations();
		howSelfStorageWorksActions.searchReservationsSubTab(searchText6);
	}

	@Test
	public void verifyMoingInTabPackItUpSubTabTest() {
		// scenario 12
		
		allTextPresent=true;
		isTextPresent = new HashMap<String, Boolean>();
		iterator = isTextPresent.entrySet().iterator();
		failedText=new StringBuilder();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByLinkText("moving supplies");		
		isTextPresent.put("moving supplies",webAppDriver.verifyTextContainInAnyTag("Moving Kits"));
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByLinkText("boxes");		
		isTextPresent.put("boxes",webAppDriver.verifyTextContainInAnyTag("Storage Boxes"));
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByLinkText("tape");		
		isTextPresent.put("tape",webAppDriver.verifyTextContainInAnyTag("Carton Sealing Tape"));
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByLinkText("packing materials");		
		isTextPresent.put("packing materials",webAppDriver.verifyTextContainInAnyTag("Packing Supplies"));
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByLinkText("Customer Testimonial video");		
		isTextPresent.put("Customer Testimonial video",webAppDriver.verifyTextContainInAnyTag("Public Storage Videos"));
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByXpath(lnkMovingSuppliesXpath);		
		isTextPresent.put("Moving supplies",webAppDriver.verifyTextContainInAnyTag("Moving Kits"));
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		navigator.redirectToHomeSameSession();
		navigator.gotoMovingInTab();
		webAppDriver.clickElementByXpath(lnkBoxesXpath);		
		isTextPresent.put("boxes",webAppDriver.verifyTextContainInAnyTag("Storage Boxes"));
		
		while(iterator.hasNext()){
			if(!iterator.next().getValue()){
				allTextPresent = false;
				failedText.append(iterator.next().getKey());
				failedText.append(System.lineSeparator());
			}
		}
		
		if(!allTextPresent){
			webAppDriver.captureScreenshot();
			Reporter.log(failedText.toString() + " not displayed on the Home page");
			throw new AssertionError(failedText.toString() + " not displayed on the on the Home page");
		}
		/*navigator.gotoMovingInTab();
		webAppDriver.verifyLinkContent("moving supplies", "Moving Kits");
		webAppDriver.verifyLinkContent("boxes", "Storage Boxes");
		webAppDriver.verifyLinkContent("tape", "Carton Sealing Tape");
		webAppDriver.verifyLinkContent("packing materials", "Packing Supplies");
		webAppDriver.verifyLinkContent("Customer Testimonial video", "Public Storage Videos");
		webAppDriver.verifyLinkContent(lnkMovingSuppliesXpath, "Moving Kits");
		webAppDriver.verifyLinkContent("locks", "Storage Locks");
		webAppDriver.verifyLinkContent(lnkBoxesXpath, "Storage Boxes");*/
	}

	@Test
	public void verifyMoingInTabBeRentalReadySubTabTest() {
		// scenario 12
		
		navigator.gotoMovingInTab();
		howSelfStorageWorksActions.clickOnBeRentalReadySubTab();
		webAppDriver.clickElementByXpath("//div[text()='Sign The Lease']/..//a[text()='login']");
		webAppDriver.verifyPresenceOfTextInSpanTag("Express Check-In");
		webAppDriver.clickElementByXpath(lnkYourAccountBeRentalReadySubTabXpath);
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Your Reservation");
		navigator.login(homeLoginUsername, "test123");
		/*navigator.gotoMovingInTab();
		howSelfStorageWorksActions.clickOnBeRentalReadySubTab();
		webAppDriver.verifyLinkContent("Reservation Care", "Manage Your Reservation");
		webAppDriver.verifyLinkContent(lnkYourAccountBeRentalReadySubTabXpath, "Manage Your Reservation");*/
	}

	@Test
	public void verifyMoingInTabMoveInDaySubTabTest() {
		// scenario 12
		navigator.gotoMovingInTab();
		howSelfStorageWorksActions.clickOnMoveInDaySubTab();
		webAppDriver.verifyLinkContent("www.perfectsolutionstorageinsurance.com.", "Perfect Solution Storage Insurance Program");

	}

	@Test
	public void verifyMoingInTabLoadingYourUnitsSubTabTest() {
		// scenario 12
		navigator.gotoMovingInTab();
		howSelfStorageWorksActions.clickOnLoadinYourUnitsSubTab();
		webAppDriver.verifyLinkContent("Your Account", "Sign In to Your Account");
	}

	@Test
	public void verifyYourAccountTabYourBillSubTabTest() {
		// scenario 13
		navigator.gotoYourAccountTab();

		webAppDriver.verifyLinkContent("AutoPay", "AutoPay Settings");
		webAppDriver.verifyLinkContent("Your Account", "My Public Storage Account");
		webAppDriver.verifyLinkContent("Payments", "Make a Payment");
		webAppDriver.verifyLinkContent(lnkYourAccountYourBillSubTabXpath, "My Public Storage Account");
	}

	@Test
	public void verifyYourAccountTabYourAccountSubTabTest() {
		// scenario 13
		navigator.gotoYourAccountTab();
		howSelfStorageWorksActions.clickOnYourAccountSubTab();
		webAppDriver.verifyLinkContent("\"Managing Your Rental Online,\"", "Managing Your Rental Online");
		webAppDriver.verifyLinkContent("Your Account", "Sign In to Your Account");
		webAppDriver.verifyLinkContent("Payments", "My Public Storage Account");
		webAppDriver.verifyLinkContent("contact information", "Contact Information");
		webAppDriver.verifyLinkContent("email notifications", "Please specify which email notifications you would like to receive:");
		webAppDriver.verifyLinkContent("AutoPay", "AutoPay Settings");
	}

	@Test
	public void verifyYourAccountTabUnitAccessSubTabTest() {
		// scenario 13
		navigator.gotoYourAccountTab();
		howSelfStorageWorksActions.clickOnUnitAccessSubTab();
		webAppDriver.verifyLinkContent("Your Account", "My Public Storage Account");
	}

	@Test
	public void verifyYourAccountTabMovingOutSubTabTest() {
		// scenario 13
		navigator.gotoYourAccountTab();
		howSelfStorageWorksActions.clickOnMovingOutSubTab();
		howSelfStorageWorksActions.clickOnMoveOutLinkOnline();

	}

	@Test
	public void verifySelfStorage_StorageForMovingTabTest() {
		// scenario 14
		navigator.gotoSelfStorageMenu();
		webAppDriver.verifyLinkContent("find the right space", "public storage, size guide");
		webAppDriver.verifyLinkContent("answers to common questions", "How Self Storage Works");
		webAppDriver.verifyLinkContent("How Storage Works", "How Self Storage Works");
		webAppDriver.verifyLinkContent("Size Guide", "public storage, size guide");
		webAppDriver.verifyLinkContent("Storage Tips", "Packing and Storage Tips You'll Love from Public Storage");
		webAppDriver.verifyLinkContent("About Public Storage", "About Public Storage");
		selfStorageActions.enterSearchCriteriaStorageForMovingTab(searchText6);
	}

	@Test
	public void verifySelfStorage_StorageForTravellingTabTest() {
		// scenario 14
		navigator.gotoSelfStorageMenu();
		selfStorageActions.clickOnStorageForTravellingTab();
		selfStorageActions.enterSearchCriteriaStorageForTravellingTab(searchText6);
	}

	@Test
	public void verifySelfStorage_StorageForCollegeTabTest() {
		// scenario 14
		navigator.gotoSelfStorageMenu();
		selfStorageActions.clickOnCollegeStorageTab();
		selfStorageActions.enterSearchCriteriaCollegeStorageTab(searchText6);
	}

	@Test
	public void verifySelfStorage_StorageForExtraRoomTabTest() {
		// scenario 14
		navigator.gotoSelfStorageMenu();
		selfStorageActions.clickOnExtraRoomTab();
		selfStorageActions.enterSearchCriteriaExtraRoomTab(searchText6);
	}

	@Test
	public void verifyBusinessStorage_PharmaceuticalStorageTest() {
		// scenario 15
		navigator.gotoBusinessStorageMenu();
		selfStorageActions.enterSearchCriteriaPharmaceuticalStorageTab(searchText6);
	}

	@Test
	public void verifyBusinessStorage_ConstructionStorageTest() {
		// scenario 15
		navigator.gotoBusinessStorageMenu();
		selfStorageActions.clickOnConstructionStorageTab();
		selfStorageActions.enterSearchCriteriaConstructionStorageTab(searchText6);
	}

	@Test
	public void verifyBusinessStorage_RealEstateStorageTest() {
		// scenario 15
		navigator.gotoBusinessStorageMenu();
		selfStorageActions.clickOnRealEstateStorageTab();
		selfStorageActions.enterSearchCriteriaRealEstateStorageTab(searchText6);
	}

	@Test
	public void verifyBusinessStorage_RetailStorageTest() {
		// scenario 15
		navigator.gotoBusinessStorageMenu();
		selfStorageActions.clickOnRetailStorageTab();
		selfStorageActions.enterSearchCriteriaRetailStorageTab(searchText6);
	}

	@Test
	public void verifyBusinessStorage_ArchiveStorageTest() {
		// scenario 15
		navigator.gotoBusinessStorageMenu();
		selfStorageActions.clickOnArchiveStorageTab();
		selfStorageActions.enterSearchCriteriaArchiveStorageTab(searchText6);
	}

	@Test
	public void verifyBusinessStorage_OfficeStorageTest() {
		// scenario 15
		navigator.gotoBusinessStorageMenu();
		selfStorageActions.clickOnOfficeStorageTab();
		selfStorageActions.enterSearchCriteriaOfficeStorageTab(searchText6);
	}

	@Test
	public void verifyVehicleStorage_CarStorageTest() {
		// scenario 16
		navigator.gotoVehicleStorageMenu();
		selfStorageActions.enterSearchCriteriaCarStorageTab(searchText6);
	}

	@Test
	public void verifyVehicleStorage_TrailerStorageTest() {
		// scenario 16
		navigator.gotoVehicleStorageMenu();
		selfStorageActions.clickOnTrailerStorageTab();
		selfStorageActions.enterSearchCriteriaTrailerStorageTab(searchText6);
	}

	@Test
	public void verifyVehicleStorage_BoatStorageTest() {
		// scenario 16
		navigator.gotoVehicleStorageMenu();
		selfStorageActions.clickOnBoatStorageTab();
		selfStorageActions.enterSearchCriteriaBoatStorageTab(searchText6);
	}

	@Test
	public void verifyVehicleStorage_MotorcycleStorageTest() {
		// scenario 16
		navigator.gotoVehicleStorageMenu();
		selfStorageActions.clickOnMotorCycleStorageTab();
		selfStorageActions.enterSearchCriteriaMotorCycleStorageTab(searchText6);
	}

	@Test
	public void verifyStorageBlogMenuItemTest() {
		// scenario 18
		navigator.gotoStorageBlogMenu();
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.verifyElementPresentByXpath(imgLogoImageXpath);
	}

	@Test
	public void verifyStorageBoxesMenuItemTest() {
		// scenario 19
		navigator.gotoStorageBoxesMenu();
	}

	//Removing the test case as Lock menu option is removed
	//30 sep 2016
	/*@Test
	public void verifyStorageLockMenuItemTest() {
		// scenario 19
		navigator.gotoStorageLocksMenu();
	}
*/
	@Test
	public void verifyPackingSuppliesMenuItemTest() {
		// scenario 19
		navigator.gotoPackingSuppliesMenu();
	}

	@Test
	public void verifyTapeMenuItemTest() {
		// scenario 19
		navigator.gotoTapeMenu();
	}

	@Test
	public void verifyAboutUsMenuItemTest() {
		// scenario 20
		navigator.gotoAboutUsMenu();
	}

	@Test
	public void verifyInvestorRelationsMenuItemTest() {
		// scenario 20
		navigator.gotoInvestorRelationsMenu();
	}

	@Test
	public void verifyGlobalRelationsMenuItemTest() {
		// scenario 20
		navigator.gotoGlobalLocationsMenu();
	}

	@Test
	public void verifyContactUsMenuItemTest() {
		// scenario 20
		navigator.gotoContactUsMenu();
	}

	@Test(dataProvider = "InvestorRelationlinkprovider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyInvestorRelationPageTest(String linkData) {
		// scenario 20
		navigator.gotoInvestorRelationsMenu();
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.verifyLinkContent(linkName.trim(), expectedText.trim(), false);
	}

	@Test
	public void tryal() {
		String hoverValue = webAppDriver.findElementById("nav_comp_info_s").getAttribute("href");
		System.out.println(hoverValue);
	}

}
