package com.phelps.ps.com.testsuite;

import java.util.Set;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.HowSelfStorageWorksLocators;

public class PendingHomeRefreshTestSuite extends BasicTestSuite implements HomeLocators, CommonLocators, ECI_RDP_ConfLocators ,CommonSearchTextLocators ,HowSelfStorageWorksLocators{

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
		email = "testlinkps.comguest@gmail.com";//envTestData.get("email");
		reservationNumber=navigator.gotoPendingHomePage(searchText, firstName, lastName, phoneNumber, ext, email, dayOfMoveIn);

	}

	@Test
	public void verifyHomePageHeaderTest() {

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


	 @Test
		public void verifyMyAccountPayMyBillLink() {
			// Scenario 2
			navigator.redirectToHomePageNotLoggedIn();
			// scenario 3-Presence of header links orange in color
			webAppDriver.verifyPresenceOfTextLocatedByXpath("(//*[@class='avant_menu orangelink'])[1]", "My Account");
			webAppDriver.verifyPresenceOfTextLocatedByXpath("(//*[@class='avant_menu orangelink'])[2]", "Reservations");
			webAppDriver.verifyPresenceOfTextLocatedByXpath("(//*[@class='avant_menu orangelink'])[3]", "Pay My Bill");

			// Verification Login and Create Account drop down options
			webAppDriver.clickElementById(btnMyAccountNewId);
			webAppDriver.verifyElementPresentByXpath(hlinkLoginActiveXpath);
			webAppDriver.verifyElementPresentByXpath(hlinkCreateAccountActiveXpath);
			navigator.redirectToHomePageNotLoggedIn();
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

	// Author -mnaaz
		// Added on 27th Dec
		// 0.0.2.10. Benefits Icons-Referrence
		 @Test
		public void verifyBenefitIconsTest() {
			webAppDriver.verifyElementPresentByCss(iconBenefitUsCss);
			webAppDriver.verifyElementPresentByCss(iconBenefitDollarCss);
			webAppDriver.verifyElementPresentByCss(iconBenefitNoCommitmentCss);
			webAppDriver.verifyDivTagTextContains("Thousands of locations nationwide");
			webAppDriver.verifyDivTagTextContains("Free to reserve");
			webAppDriver.verifyDivTagTextContains("No long-term commitment");

		}

		// Author -mnaaz
		// Added on 27th Dec
		// 0.0.4. How To Video-Referrence
		 @Test
		public void verifyHowToVideosTest() {
			webAppDriver.verifyElementPresentByXpath(lb1HowToVideosXpath);
			webAppDriver.verifyPresenceOfTextInH3Tag(lb2HowToVideosXpath);
			webAppDriver.verifyElementPresentByXpath(imgHowToVideosXpath);
			webAppDriver.clickElementByXpath(hlnkHowToVideoButtonXpath);
			webAppDriver.clickElementByXpath(hlnkHowToVideosAllVideosXpath);
			webAppDriver.verifyPresenceOfTextInDivTagText("How to Pack & Store");
		}

		// Author -mnaaz
		// Added on 27th Dec
		//
		// 0.0.5. Size Guide-Referrence
		 @Test
		public void verifySizeGuideTest() {
			webAppDriver.verifyPresenceOfTextInH2Tag("Ditch the guessing game");
			if (!webAppDriver.verifyTextContainInAnyTag("Figure out exactly how much storage")) {
				webAppDriver.captureScreenshot();
				Reporter.log("Figure out exactly how much storage not present ");
				throw new AssertionError("Figure out exactly how much storage not present ");
			}
			if (!webAppDriver.verifyTextWithSpacePresentInAnyTag("space you need with our size guide.")) {
				webAppDriver.captureScreenshot();
				Reporter.log("Figure out exactly how much storage not present ");
				throw new AssertionError("Figure out exactly how much storage not present ");
			}
			webAppDriver.verifyElementPresentById(lbSizeGuideId);
			webAppDriver.verifyElementPresentByXpath(imgSizeGuideXpath);
			webAppDriver.clickElementByXpath(hlnkSizeGuideTryNowXpath);
			webAppDriver.verifyElementPresentById(canvasSizeGuideId);
			navigator.redirectToHomePageNotLoggedIn();

		}

		// Author -mnaaz
		// Added on 27th Dec
		//
		// 0.0.6. How Storage Works-Referrences
		 @Test
		public void verifyHowStorageWorksTest() {
			webAppDriver.verifyPresenceOfTextInH2Tag("How storage works");
			webAppDriver.verifyElementPresentByXpath(
					"//h3[text()=\"First timer? Don’t worry. We'll walk you through the details and arm you \"]");
			webAppDriver.verifyElementPresentById(lbHowStorageWorkId);
			if (!webAppDriver.verifyTextWithSpacePresentInAnyTag("with tons of packing, moving and storing tips.")) {
				webAppDriver.captureScreenshot();
				Reporter.log("with tons of packing, moving and storing tips. not present");
				throw new AssertionError("with tons of packing, moving and storing tips. not present");
			}
			webAppDriver.verifyElementPresentByXpath(
					".//*[@id='boxes-txt'][contains(text(),\"We'll walk you through the details and give you tons of tips for packing, moving and storing.\")]");

			webAppDriver.clickElementByXpath(btnHowStorageWorksLearnMoreXpath);
			webAppDriver.verifyElementPresentByXpath(subTabHowToStore);
			navigator.redirectToHomePageNotLoggedIn();
		}

		// Author -mnaaz
		// Added on 28th Dec
		//
		// 0.0.7. Blog-Referrences
		// Removed verifyStorageMadeImagesLinksTest
		 @Test
		public void verifyBlogImagesLinksTest() {
			// Scenario 5,6,7
			navigator.redirectToHomePageNotLoggedIn();
			homePageRefreshActions.clickBlogRecentNewsImage();

			navigator.redirectToHomePageNotLoggedIn();
			homePageRefreshActions.clickBlogSecondlImage();
			navigator.redirectToHomePageNotLoggedIn();
			homePageRefreshActions.clickBlogThirdImage();
			navigator.redirectToHomePageNotLoggedIn();
		}

		// Author -mnaaz
		// Added on 28th Dec
		//
		// 0.0.7. Blog-Referrences
		// Removed verifyStorageMadeImagesLinksTest
		// Removed verifyStorageMadeLinksTest
		 @Test
		public void verifyBlogLinksTest() {

			navigator.redirectToHomePageNotLoggedIn();
			webAppDriver.verifyElementPresentByXpath("//h2[contains(text(),'The Organized Life')]");
			webAppDriver.verifyElementPresentByXpath(
					"//h3[contains(text(),'For tips on decluttering, moving and enjoying check out our')]");
			String parentHandle = webAppDriver.getWindowHandle();
			homePageRefreshActions.clickBlogLink();
			navigator.redirectToHomePageNotLoggedIn();
			webAppDriver.verifyElementPresentByXpath("//a[contains(.,'Storage Near Manhattan at New Public Storage')]");
			webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Jersey City')]");
			webAppDriver.verifyElementPresentByXpath("//a[contains(.,'Expert Advice on How to Store a Mattress for')]");
			webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Restful Nights')]");
			webAppDriver.verifyElementPresentByXpath("//a[contains(.,'How to Organize for a Move –')]");
			webAppDriver.verifyElementPresentByXpath("//span[contains(.,'The Best Moving')]");
			webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Checklist Ever')]");
			homePageRefreshActions.clickBlogReadMoreLinks(1);
			webAppDriver.verifyElementPresentByXpath(
					".//*[@id='breadcrumbs']//strong[text()='Storage Near Manhattan at New Public Storage Jersey City']");
			navigator.redirectToHomePageNotLoggedIn();
			homePageRefreshActions.clickBlogReadMoreLinks(2);
			Set<String> windowHandles = webAppDriver.getWindowHandles();
			windowHandles.remove(parentHandle);
			for (String winHandle : windowHandles) {
				webAppDriver.switchTo().window(winHandle);
				webAppDriver.verifyElementPresentByXpath(
						"//*[@id='breadcrumbs']//strong[text()='Expert Advice on How to Store a Mattress for Restful Nights']");
				webAppDriver.close();
				webAppDriver.switchTo().window(parentHandle);

			}
			homePageRefreshActions.clickBlogReadMoreLinks(3);
			windowHandles = webAppDriver.getWindowHandles();
			windowHandles.remove(parentHandle);
			for (String winHandle : windowHandles) {
				webAppDriver.switchTo().window(winHandle);
				webAppDriver.verifyElementPresentByXpath(
						"//*[@id='breadcrumbs']//strong[text()='How to Organize for a Move – The Best Moving Checklist Ever']");
				webAppDriver.close();
				webAppDriver.switchTo().window(parentHandle);
			}
			navigator.redirectToHomePageNotLoggedIn();

		}

		 @Test
		public void verifyBenefitSectionTest() {
			// Scenario 11
			webAppDriver.verifyPresenceOfTextInH2Tag("Why store with us?");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'FREE')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'RESERVATIONS')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'NO LONG-TERM')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'COMMITMENT')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'OVER 40 YEARS')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'OF EXPERIENCE')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'CONVENIENT')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'ACCESS HOURS')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'CLIMATE-CONTROLLED')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'UNITS')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'VARIETY OF')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='title'][contains(.,'UNIT SIZES')]");
			webAppDriver.verifyElementPresentByXpath(iconBlogFreeReservationsXpath);
			webAppDriver.verifyElementPresentByXpath(iconBlogNoLongTermXpath);
			webAppDriver.verifyElementPresentByXpath(iconBlog40YearsXpath);
			webAppDriver.verifyElementPresentByXpath(iconBlogCovenientXpath);
			webAppDriver.verifyElementPresentByXpath(iconBlogClimateControllXpath);
			webAppDriver.verifyElementPresentByXpath(iconBlogUnitSizesXpath);
			homePageRefreshActions.clickBenefitSection();

			navigator.redirectToHomePageNotLoggedIn();
		}

		 @Test
		public void verifySocialLinkstest() {
			navigator.redirectToHomePageNotLoggedIn();
			webAppDriver.verifyDivTagTextEquals("KEEP UP WITH THE LASTEST PUBLIC STORAGE NEWS");
			homePageRefreshActions.clickFacebookIcon();
			homePageRefreshActions.clickTwitterIcon();
			homePageRefreshActions.clickGooglePlusIcon();
			homePageRefreshActions.clickInstragramIcon();
			homePageRefreshActions.clickYoutubeIcon();

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
