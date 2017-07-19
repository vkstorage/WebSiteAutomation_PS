package com.phelps.ps.com.testsuite;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.HowSelfStorageWorksLocators;
import com.phelps.ps.com.locators.PLPLocators;
import org.openqa.selenium.JavascriptExecutor;

public class HomePageRefreshNotLoggedInTestSuite extends BasicTestSuite
		implements HomeLocators, CommonLocators, CommonSearchTextLocators, HowSelfStorageWorksLocators {

	Map<String, Boolean> isTextPresent;
	Map<String, Boolean> isPricePresent;
	Iterator<Map.Entry<String, Boolean>> iterator, iterator1;
	static boolean allTextPresent;
	static boolean allPricePresent;
	StringBuilder failedText=new StringBuilder("");
	StringBuilder allFailedPrice=new StringBuilder("");
	String email;
	HashMap<Integer, String> allLowestPrice = new HashMap<Integer, String>();
	
	@BeforeMethod
	public void clearHistory(){
		commonActions.clearChache();
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

	/*
	 * @author- mnaaz Added on 07th Dec 16 For Home Page Refresh
	 */
	 @Test
	public void verifyHomepageHeaderHoverEffectTest() {
		navigator.gotoHomePage();
		webAppDriver.relax(500);

		// Storage Menu
		homePageRefreshActions.clickOnStorageMenu();
		// homePageRefreshActions.hoverHowStorageWorks();
		homePageRefreshActions.hoverSelfStorage();
		homePageRefreshActions.hoverBusinessStorage();
		homePageRefreshActions.hoverVehicleStorage();
		homePageRefreshActions.hoverSizeGuide();
		homePageRefreshActions.hoverStorageBlog();

		// Moving Supplies
		homePageRefreshActions.clickOnMovingSuppliesMenu();
		homePageRefreshActions.hoverStorageBoxes();
		homePageRefreshActions.hoverPackingSupplies();
		homePageRefreshActions.hoverTape();

		// Company Info
		homePageRefreshActions.clickOnCompanyInfoMenu();

		homePageRefreshActions.hoverAboutUs();
		homePageRefreshActions.hoverInvestorRelations();
		homePageRefreshActions.hoverGlobalLocations();
		homePageRefreshActions.hoverCareers();
		homePageRefreshActions.hoverContactUs();

	}

	// Author -mnaaz
	// Added on 6th -Oct
	 @Test
	public void verifyContactUsFunctionalityTest() {
		email = envTestData.get("email");
		String name = "Test User";
		String topic = "Reservations";
		String inquiry = "Reservations Inquiry";
		String phone = "7656765465";
		String ext = "674";
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.get(baseUrl + "/storage-contact.aspx");
		homePageRefreshActions.clickContactUsSubmitbutton();
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Name");
		webAppDriver.verifyDivTagTextEquals("Please select a Topic");
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Email Address");
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Phone Number");
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Nature of Inquiry");

		// successful submition
		homePageRefreshActions.enterContactusName(name);
		homePageRefreshActions.enterContactUsEmail(email);
		homePageRefreshActions.enterContactusInquiry(inquiry);
		homePageRefreshActions.selectContactUsTopic(topic);
		homePageRefreshActions.enterContactUsPhone(phone);
		homePageRefreshActions.enterContactUsExtension(ext);
		homePageRefreshActions.clickContactUsSubmitbutton();

		webAppDriver.verifyDivTagTextEquals("Your request has been registered from the email " + email + ".");

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

	 @Test
	public void verifySearchTest() {
		// Scenario 4
		navigator.gotoDefaultSearchResults(searchText33);
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoDefaultSearchResults(searchText34);
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoDefaultSearchResults(searchText35);
		navigator.redirectToHomePageNotLoggedIn();

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

	// Removed verifyBlogImageLink

	// Removed verifyInYourSpaceLinksTest

	//// @Test
	public void verifyRecommendedLocationTest() {
		commonActions.switchOnGeoLocation();
		webAppDriver.get(baseUrl);

		String[] allCouponsAddress = { "", "", "", "", "", "" };
		int i = 0;
		int totalNumberOfCoupons = webAppDriver.getTotalNumberOfElementsByCss(lbRecommendedLocationsCouponsCss);
		if (totalNumberOfCoupons != 6) {
			webAppDriver.captureScreenshot();
			Reporter.log("Number of coupons on homepage is not 6 it is " + totalNumberOfCoupons);
			throw new AssertionError("Number of coupons on homepage is not 6 it is " + totalNumberOfCoupons);
		}
		while (i < 6) {
			String finalXpath = MessageFormat.format(lbAllCouponAddressXpath, i + 1);
			allCouponsAddress[i] = webAppDriver.findElementByXpath(finalXpath).getText();
			i++;
		}

		for (int j = 0; j < 6; j++) {
			navigator.redirectToHomePageNotLoggedIn();
			if (i == 2 || i == 4)
				homePageRefreshActions.clickOnRightCarouselArrow();
			if (allCouponsAddress[j].contains("Bl"))
				allCouponsAddress[j].replace("Bl", "Blvd");
			String[] splitAddress = allCouponsAddress[j].split(",");
			String[] address = homePageRefreshActions.getSplitAdress(splitAddress);
			homePageRefreshActions.clickCarouselCoupon(j);
			webAppDriver.verifyEqualsString(address[0].trim(),
					webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText().trim());
			webAppDriver.verifyEqualsString(address[1].trim(),
					webAppDriver.findElementByXpath(PLPLocators.lbSpaceAddressLocalityXpath).getText().trim());
			webAppDriver.verifyEqualsString(address[2].trim(),
					webAppDriver.findElementByXpath(PLPLocators.lbSpaceAddressRegionXpath).getText().trim());
			webAppDriver.verifyEqualsString(address[3].trim(),
					webAppDriver.findElementByXpath(PLPLocators.lbSpacePostalCodeXpath).getText().trim());

		}

	}

	// @Test
	public void verifyRecommendedLocationUnitTest() {

		boolean isSmallPresent = false;
		boolean isMediumPresent = false;
		boolean isLargePresent = false;

		isTextPresent = new HashMap<String, Boolean>();
		isPricePresent = new HashMap<String, Boolean>();

		List<Integer> allSmallUnitPrice = new ArrayList<Integer>();
		List<Integer> allMediumUnitPrice = new ArrayList<Integer>();
		List<Integer> allLargeUnitPrice = new ArrayList<Integer>();
		navigator.redirectToHomePageNotLoggedIn();
		commonActions.switchOnGeoLocation();
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.verifyElementPresentByXpath(lb1RecommendedLocationTextXpath);
		webAppDriver.verifyPresenceOfTextInH3Tag(lb2RecommendedLocationText);

		for (int i = 1; i < 7; i++) {
			navigator.redirectToHomePageNotLoggedIn();
			/*if (i == 3 || i == 5)
				homePageRefreshActions.clickOnRightCarouselArrow();*/
			String finalXpath = MessageFormat.format(hlnkAllRecommendedLocationXpath, i);
			String finalPriceXpath;
			webAppDriver.clickElementByXpath(finalXpath);
			isSmallPresent = webAppDriver.verifyElementPresentBy(By.xpath(PLPLocators.lbAllSmallUnitStorageTabXpath));
			isMediumPresent = webAppDriver.verifyElementPresentBy(By.xpath(PLPLocators.lbAllMediumUnitStorageTabXpath));
			isLargePresent = webAppDriver.verifyElementPresentBy(By.xpath(PLPLocators.lbAllLargeUnitStorageTabXpath));
			if (isSmallPresent) {
				allSmallUnitPrice = plpActions.getSortedPriceOfAllUnit("Small");

			}
			if (isMediumPresent) {
				allMediumUnitPrice = plpActions.getSortedPriceOfAllUnit("Medium");
			}
			if (isLargePresent) {
				allLargeUnitPrice = plpActions.getSortedPriceOfAllUnit("Large");
			}

			webAppDriver.navigate().back();

			if (isSmallPresent) {
				finalXpath = MessageFormat.format(lbAllUnitSingleSmallUnitSizesXpath, i, 1);
				finalPriceXpath = MessageFormat.format(lbAllUnitPricesXpath, i, 1, allSmallUnitPrice.get(0));
				isTextPresent.put("carousel " + i + " unit 1 text not small ",
						webAppDriver.verifyElementPresentBy(By.xpath(finalXpath)));
				isPricePresent.put("carousel " + i + " Small unit 1 price should be " + allSmallUnitPrice.get(0),
						webAppDriver.verifyElementPresentBy(By.xpath(finalPriceXpath)));

			}
			if (isMediumPresent) {
				finalXpath = MessageFormat.format(lbAllUnitSingleMediumUnitSizesXpath, i, 2);
				finalPriceXpath = MessageFormat.format(lbAllUnitPricesXpath, i, 2, allMediumUnitPrice.get(0));
				isTextPresent.put("carousel " + i + " unit 2 text not medium ",
						webAppDriver.verifyElementPresentBy(By.xpath(finalXpath)));
				isPricePresent.put("carousel " + i + " Medium unit 2 price should be  " + allMediumUnitPrice.get(0),
						webAppDriver.verifyElementPresentBy(By.xpath(finalPriceXpath)));
			}
			if (isLargePresent) {
				finalXpath = MessageFormat.format(lbAllUnitSingleLargeUnitSizesXpath, i, 3);
				finalPriceXpath = MessageFormat.format(lbAllUnitPricesXpath, i, 3, allLargeUnitPrice.get(0));
				isTextPresent.put("carousel " + i + " unit 3 text not Large ",
						webAppDriver.verifyElementPresentBy(By.xpath(finalXpath)));
				isPricePresent.put("carousel " + i + " Large unit 3 price should be " + allLargeUnitPrice.get(0),
						webAppDriver.verifyElementPresentBy(By.xpath(finalPriceXpath)));
			}

		}

		iterator = isTextPresent.entrySet().iterator();
		iterator1 = isPricePresent.entrySet().iterator();

		while (iterator.hasNext()) {
			if (!iterator.next().getValue()) {
				allTextPresent = false;
				failedText.append(iterator.next().getKey());
				failedText.append(System.lineSeparator());
			}
		}

		while (iterator1.hasNext()) {
			if (!iterator1.next().getValue()) {
				allPricePresent = false;
				allFailedPrice.append(iterator1.next().getKey());
				allFailedPrice.append(System.lineSeparator());
			}
		}

		if (!allTextPresent) {
			webAppDriver.captureScreenshot();
			Reporter.log(failedText.toString() + " not displayed on the Home page");
		}
		if (!allPricePresent) {
			webAppDriver.captureScreenshot();
			Reporter.log(allFailedPrice.toString() + " not displayed on the Home page");
		}

		if (!allTextPresent && !allPricePresent) {
			throw new AssertionError(failedText.toString() + " not displayed on the on the Home page and  price "
					+ allFailedPrice.toString() + " not displayed");
		} else if (!allTextPresent) {
			throw new AssertionError(failedText.toString() + " not displayed on the on the Home page ");
		} else if (!allPricePresent) {
			throw new AssertionError(" price " + allFailedPrice.toString() + " not displayed");
		}

	}

	@Test(dataProvider = "homepagelinkproviderNewWindow", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class, priority = -1)
	public void verifyHomePageNewWindowLinksTest(String linkData) {
		// Scenario 13,14
		navigator.redirectToHomePageNotLoggedIn();
		String parentHandle = webAppDriver.getWindowHandle();
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.clickElementByLinkText(linkName);
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

	@Test(dataProvider = "homepagelinkprovider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class, priority = -1)
	public void verifyHomePageLinksTest(String linkData) {
		// Scenario 15
		navigator.redirectToHomePageNotLoggedIn();
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.clickElementByLinkText(linkName);
		 webAppDriver.relax(300);
		if (!webAppDriver.verifyTextContainInAnyTag(expectedText)) {
			webAppDriver.captureScreenshot();
			Reporter.log(expectedText);
			throw new AssertionError(expectedText + " not found");
		}
		/*
		 * navigator.redirectToHomePageNotLoggedIn(); String linkName =
		 * linkData.split(",")[0]; String expectedText = linkData.split(",")[1];
		 * webAppDriver.verifyLinkContent(linkName.trim(), expectedText.trim(),
		 * false);
		 */
	}
}
