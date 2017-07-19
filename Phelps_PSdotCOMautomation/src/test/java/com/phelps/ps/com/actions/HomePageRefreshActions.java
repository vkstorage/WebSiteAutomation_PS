package com.phelps.ps.com.actions;

import java.text.MessageFormat;
import java.util.Set;

import org.testng.Reporter;

//import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.HowSelfStorageWorksLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfStorageLocators;

public class HomePageRefreshActions extends CommonActions implements HomeLocators, SearchResultsPageLocators,
		CommonLocators, SelfStorageLocators, LoginLocators, CommonSearchTextLocators, HowSelfStorageWorksLocators {

	CateredWebDriver webAppDriver;
	String usernae = username;
	String hoverBackgroundColor = "rgba(78, 46, 116, 1)";
	String actualBackgroundColor = "";
	String hoverFontColor = "rgba(255, 255, 255, 1)";
	String actualFontColor = "";

	public HomePageRefreshActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	/**
	 * Mouse over Storage menu
	 */
	public void hoverStorage() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(mlStorageId));
		webAppDriver.verifySpanTagTextEquals("How Storage Works");
	}

	/**
	 * Mouse over Moving Supplies menu
	 */
	public void hoverMovingSupplies() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(mlMovingSuppliesId));
		webAppDriver.verifySpanTagTextEquals("Storage Boxes");
	}

	/**
	 * Mouse over MovinverifyMandatoryFieldsTestg Supplies menu
	 */
	public void hoverCompanyInfo() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(mlCompanyInfoId));
		webAppDriver.verifyPresenceOfTextInSpanTag("About Us");
	}

	/**
	 * navigate into search results page after searching by the given criteria
	 * 
	 * @param searchCriteria
	 */
	public void enterSearchCriteria(String searchCriteria) {

		enterSearchText(searchCriteria);
		clickSearch();
		webAppDriver.checkTextInListByCss(tabStorageTypesCss, "Storage Units");
	}

	public void enterSearchText(String searchCriteria) {
		webAppDriver.enterTextToElementByName(tbSearchName, searchCriteria);
	}

	public void clickSearch() {
		webAppDriver.clickElementById(btnSearchId);
	}

	/**
	 * Mouse over Moving Supplies menu
	 */
	public void clickOnMyAccount() {
		webAppDriver.clickElementByLinkText(linkMyAccountText);
		webAppDriver.verifySpanTagTextEquals("Sign In to Your Account");
	}

	public void clickOnMyAccountLoggedIn() {
		try {
			webAppDriver.clickElementById(btnMyAccountLoggedInId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (webAppDriver.verifyElementIsPresentByXpath(btnMyAccountLoginXpath)) {
			webAppDriver.clickElementByXpath(btnMyAccountLoginXpath);
			webAppDriver.enterTextToElementById(tbUserNameId, usernae);
			webAppDriver.enterTextToElementByXpath("//*[@id='loginpw']", "test123");
			webAppDriver.clickElementById(btnLoginId);
		}
		webAppDriver.relax(500);
		webAppDriver.verifySpanTagTextEquals("My Public Storage Account");
	}

	public void clickOnPayMyBill() {
		webAppDriver.clickElementByLinkText(linkPayMyBillText);
		webAppDriver.verifySpanTagTextEquals("Sign In to Your Account");
	}

	public void clickStorageMadeEasyImage() {
		webAppDriver.clickElementById(imgStorageMadeEasyId);
		// webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls,
		// "How Self Storage Works");
		// webAppDriver.verifyPresenceOfTextLocatedByXpath(lbStoragemadeEasyTitleXpath,
		// "Public Storage Videos");
		webAppDriver.verifyPresenceOfTextInDivTagText("How to Pack & Store");
	}

	public void clickStorageMadeReliableImage() {
		webAppDriver.clickElementById(imgStorageMadeReliableId);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
		// webAppDriver.verifyPresenceOfTextLocatedByXpath(lbStoragemadeEasyTitleXpath,
		// "Public Storage Videos");
	}

	public void clickStorageMadeLocalImage() {
		webAppDriver.clickElementById(imgStorageMadeLocalId);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbFindPublicStorageLocationsCls,
				"Find Public Storage Locations");
	}

	public void clickOnBenefitsLink() {
		webAppDriver.clickElementByXpath(imgBenefitsXpath);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	}

	public void clickOnStorageMenu() {
		webAppDriver.clickElementByXpath(menuItmStorageXpath);
		webAppDriver.verifyPresenceOfTextLocatedByCss(menuItmActiveHowSelfStorageWorksCss, "How Storage Works");
	}

	public void clickOnHowSelfStorageWorksMenu() {
		webAppDriver.clickElementById(menuItmHowStorageWorkId);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	}

	public void clickOnSelfStorageMenu() {
		webAppDriver.clickElementById(menuItmSelfStorageId);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbHowSelfStorageWorksPageVerificationXpath,
				"Self Storage: Reclaim Your Space");
	}

	public void clickOnBusinessStorageMenu() {
		webAppDriver.clickElementById(menuItmBusinessStorageId);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbBusinessStorageWorksPageVerificationXpath,
				"Business Storage Solutions: Extend Your Office");
	}

	public void clickOnVehicleStorageMenu() {
		webAppDriver.clickElementById(menuItmVehicleStorageId);
		webAppDriver.verifyElementPresentById(tbCarStorageId);
	}

	public void clickOnStorageBlogMenu() {
		webAppDriver.clickElementById(menuItmStorageBlogId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageBlogPageXpath);
	}

	// Moving And Supplies Menu
	public void clickOnStorageBoxesMenu() {
		webAppDriver.clickElementById(menuItmStorageBoxesId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageBoxesXpath);
	}

	public void clickOnStorageLocksMenu() {
		webAppDriver.clickElementByXpath(menuItemStorageLocksXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageLocksXpath);
	}

	public void clickOnPackingSuppliesMenu() {
		webAppDriver.clickElementById(menuItmPackagingSuppliesId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationPackingSuppliesXpath);
	}

	public void clickOnTapeMenu() {
		webAppDriver.clickElementById(menuItmTapeId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerifcationTapeXpath);
	}

	// Company Info
	public void clickOnAboutUsMenu() {
		webAppDriver.clickElementById(menuItmAboutUsId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationAboutUsXpath);
	}

	public void clickOnInvestorRelationMenu() {
		webAppDriver.clickElementById(menuItmInvestorRelationsId);
		webAppDriver.verifyElementPresentByXpath("//span[contains(text(),'Investor Relations')]");
	}

	public void clickOnGlobalLocationMenu() {
		webAppDriver.clickElementById(menuItmGlobalLocationsId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationGlobaLocationsXpath);
	}

	public void clickOnContactUsMenu() {
		webAppDriver.clickElementById(menuItmContactUsId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationContactUsXpath);
	}

	public void verifyHomePhotoSrcWithAddress(String imgSrc, String address) {

		if (!imgSrc.equals("nolocation_photo_93.gif")) {
			String expectedImgSrc = convertAddressIntoImagePrefix(address) + "-exterior_1_map.jpg";
			if (!imgSrc.equals(expectedImgSrc)) {
				Reporter.log("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
				webAppDriver.captureScreenshot();
				throw new AssertionError("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);

			}
		}

	}

	public void clickOnGetDirectionsLink() {

		webAppDriver.clickElementByXpath(linkGetDirectionsHomeXpath);
		webAppDriver.switchToWindowWithTitle("Directions to Public Storage Property");
		webAppDriver.verifyPresenceOfTextInH3Tag("Directions to Public Storage Property");
		webAppDriver.close();
		webAppDriver.switchToWindowWithTitle("Public Storage - Self-Storage Units/Spaces At Over 2,200 Facilities");

	}

	// WC2

	public void clickOnReservationsLink() {
		webAppDriver.clickElementById(btnReservationsId);
		// webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation
		// Number");
	}

	public void clickOnManageReservationPendingHomePage() {
		webAppDriver.clickElementByXpath(btnManageYourReservationPendingHomeXpath);

		// webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation
		// Number");
	}

	public void clickOnECIOnPendingHomePage() {
		webAppDriver.clickElementByXpath(btnECIPendingHomeXpath);
		// webAppDriver.verifySpanTagTextEquals("Complete Your Express Check-In
		// Now.");
	}

	// Contact Us
	public void enterContactUsEmail(String email) {
		webAppDriver.enterTextToElementById(tbContactUsEmailAddressId, email);

	}

	public void enterContactusName(String name) {
		webAppDriver.enterTextToElementById(tbContactUsNameId, name);
	}

	public void enterContactusInquiry(String inquiry) {
		webAppDriver.enterTextToElementById(taContactUsNaturOfInquiryId, inquiry);
	}

	public void enterContactUsPhone(String phone) {

		String phone1, phone2, phone3;
		phone1 = phone.substring(0, 3);
		phone2 = phone.substring(3, 6);
		phone3 = phone.substring(6, 10);

		webAppDriver.enterTextToElementById(tbContactInfoPhone1Id, phone1);
		webAppDriver.enterTextToElementById(tbContactInfoPhone2Id, phone2);
		webAppDriver.enterTextToElementById(tbContactInfoPhone3Id, phone3);

	}

	public void enterContactUsExtension(String ext) {
		webAppDriver.enterTextToElementById(tbConactUsPhoneExtid, ext);
	}

	public void selectContactUsTopic(String topic) {
		webAppDriver.selectByVisibleTextByLocatorId(ddContactUsTopidId, topic);
	}

	public void clickContactUsSubmitbutton() {
		webAppDriver.clickElementById(btnContactUsSubmitId);
	}

	// Homepage Refresh

	public void clickOnMovingSuppliesMenu() {
		webAppDriver.clickElementById(menuItmMovingSuppliesId);
		webAppDriver.verifyPresenceOfTextLocatedByCss(menuItmActiveStorageCss, "Storage Boxes");
	}

	public void clickOnCompanyInfoMenu() {
		webAppDriver.clickElementById(menuItmCompanytUsId);
		webAppDriver.verifyPresenceOfTextLocatedByCss(menuItmActiveAboutUsCss, "About Us");
	}

	public void hoverHowStorageWorks() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByCSS("#nav_hsw"));
		actualBackgroundColor = webAppDriver.findElementByCSS("#nav_hsw").getCssValue("background-color");
		actualFontColor = webAppDriver.findElementByCSS("#nav_hsw").getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on how storage works background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on how  storage works Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}

	}

	public void hoverSelfStorage() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByCSS("#nav_self"));
		actualBackgroundColor = webAppDriver.findElementByCSS("#nav_self").getCssValue("background-color");
		actualFontColor = webAppDriver.findElementByCSS("#nav_self").getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on how self storage background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}

		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on self storage Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}

	}

	public void hoverBusinessStorage() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByCSS("#nav_biz"));
		actualBackgroundColor = webAppDriver.findElementByCSS("#nav_biz").getCssValue("background-color");
		actualFontColor = webAppDriver.findElementByCSS("#nav_biz").getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on Business storage background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmSelfStorageId));

		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Business storage Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}

	}

	public void hoverVehicleStorage() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmVehicleStorageId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmVehicleStorageId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmVehicleStorageId).getCssValue("color");
		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on Vehicle storage background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Vehicle Storage Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}

	}

	public void hoverSizeGuide() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmSizeGuideId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmSizeGuideId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmSizeGuideId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Size Guide background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Size Guide Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverStorageBlog() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmStorageBlogId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmStorageBlogId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmStorageBlogId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Storage Blog background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Storage Blog Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverStorageBoxes() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmStorageBoxesId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmStorageBoxesId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmStorageBoxesId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Storage Boxes background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Storage Boxes Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverPackingSupplies() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmPackagingSuppliesId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmPackagingSuppliesId)
				.getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmPackagingSuppliesId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Packing Supplies background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Packing Supplies Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverTape() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmTapeId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmTapeId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmTapeId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Tape background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Tape Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverAboutUs() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmAboutUsId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmAboutUsId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmAboutUsId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  About Us background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on About Us Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverInvestorRelations() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmInvestorRelationsId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmInvestorRelationsId)
				.getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmInvestorRelationsId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Investor Relations background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Investor Relations Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverGlobalLocations() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmGlobalLocationsId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmGlobalLocationsId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmGlobalLocationsId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Global Locations background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Global Locations Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverCareers() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmCareersId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmCareersId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmCareersId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {

			Reporter.log("hover on  Careers background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Careers Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	public void hoverContactUs() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(menuItmContactUsId));
		actualBackgroundColor = webAppDriver.findElementById(menuItmContactUsId).getCssValue("background-color");
		actualFontColor = webAppDriver.findElementById(menuItmContactUsId).getCssValue("color");

		if (!(hoverBackgroundColor.equalsIgnoreCase(actualBackgroundColor))) {
			Reporter.log("hover on  Contact Us background not purple");
			webAppDriver.captureScreenshot();
			throw new AssertionError(actualBackgroundColor + " not equal to " + hoverBackgroundColor);
		}
		if (!(hoverFontColor.equalsIgnoreCase(actualFontColor))) {
			Reporter.log("hover on Contact Us Font not white");
			webAppDriver.captureScreenshot();
			throw new AssertionError(hoverFontColor + " not equal to " + actualFontColor);
		}
	}

	// Blog
	public void clickBlogRecentNewsImage() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.verifyElementPresentByXpath(imgBlogRecentNewsImageXpath);
		webAppDriver.clickElementByXpath(imgBlogRecentNewsXpath);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyElementPresentByXpath(
					".//*[@id='breadcrumbs']//strong[text()='Storage Near Manhattan at New Public Storage Jersey City']");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}

	}

	public void clickBlogSecondlImage() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.verifyElementPresentByXpath(imgBlogSecondImageXpath);
		webAppDriver.clickElementByXpath(imgBlogSecondXpath);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyElementPresentByXpath(
					".//*[@id='breadcrumbs']//strong[text()='Expert Advice on How to Store a Mattress for Restful Nights']");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}

	}

	public void clickBlogThirdImage() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.verifyElementPresentByXpath(imgBlogThirdImageXpath);
		webAppDriver.clickElementByXpath(imgBlogThirdXpath);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver
					.verifyElementPresentByXpath(".//*[@id='breadcrumbs']//strong[text()='How to Organize for a Move – The Best Moving Checklist Ever']");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}

	}

	public void clickBlogReadMoreLinks(int position) {
		String finalXpath = MessageFormat.format(hlnkBlogReadMoreXpath, position);
		webAppDriver.clickElementByXpath(finalXpath);
	}

	public void clickBlogLink() {
		webAppDriver.clickElementByXpath(hlnkBlogXpath);
		webAppDriver.verifyElementPresentByXpath("//strong[contains(text(),'The Organized Life')]");
	}

	public void clickBenefitSection() {
		webAppDriver.clickElementByXpath(hlnkBenefitSectionXpath);
		webAppDriver.verifyElementPresentByXpath(subTabHowToStore);
	}

	public void clickFacebookIcon() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.clickElementByCss(hlnkFacebookCss);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyEqualsString(webAppDriver.getCurrentUrl(), "https://www.facebook.com/PublicStorage/");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}
	}

	public void clickTwitterIcon() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.clickElementByCss(hlnkTwitterCss);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyEqualsString(webAppDriver.getCurrentUrl(), "https://twitter.com/PublicStorage");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}
	}

	public void clickGooglePlusIcon() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.clickElementByCss(hlnkGooglePlusCss);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyEqualsString(webAppDriver.getCurrentUrl(), "https://plus.google.com/+publicstorage");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}
	}

	public void clickInstragramIcon() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.clickElementByCss(hlnkInstragramCss);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyEqualsString(webAppDriver.getCurrentUrl(), "https://www.instagram.com/publicstorage/");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}
	}

	public void clickYoutubeIcon() {
		String parentHandle = webAppDriver.getWindowHandle();
		webAppDriver.clickElementByCss(hlnkYoutubeCss);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyEqualsString(webAppDriver.getCurrentUrl(), "https://www.youtube.com/user/PUBLICSTORAGE");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}
	}

	public String[] getSplitAdress(String[] address) {
		String[] finalAddress = { "", "", "", "" };
		// String[] splitAddress={"11102 S La Cienega Bl Lennox"," CA 90304"};
		if (address[0].contains("\n"))
			address[0] = address[0].replace("\n", " ");
		if (address[0].contains("Blvd")) {

		} else if (address[0].contains("Blv")) {
			address[0] = address[0].replace("Blv", "Blvd");
		} else if (address[0].contains("Bl"))
			address[0] = address[0].replace("Bl", "Blvd");
		System.out.println("After split " + address[0] + " and " + address[1]);

		if (address[0].contains("Los Angeles")) {
			int index = address[0].indexOf(" ");
			int[] spacePos = new int[5];
			int i = 0;
			while (index >= 0) {
				System.out.println(index);
				index = address[0].indexOf(" ", index + 1);
				spacePos[i] = index;
				i++;
			}
			finalAddress[0] = address[0].substring(0, spacePos[2]).trim();
			finalAddress[1] = address[0].substring(spacePos[2]).trim();
		} else if (address[0].contains("Culver City")) {
			int index = address[0].indexOf(" ");
			int[] spacePos = new int[5];
			int i = 0;
			while (index >= 0) {
				System.out.println(index);
				index = address[0].indexOf(" ", index + 1);
				spacePos[i] = index;
				i++;
			}
			finalAddress[0] = address[0].substring(0, spacePos[0]).trim();
			finalAddress[1] = address[0].substring(spacePos[0]).trim();
		} else {
			finalAddress[0] = address[0].substring(0, address[0].lastIndexOf(" ")).trim();
			finalAddress[1] = address[0].substring(address[0].lastIndexOf(" ")).trim();
		}
		System.out.println("street adress " + finalAddress);
		System.out.println("city -" + finalAddress[1]);
		address[1] = address[1].trim();
		System.out.println("after trim  -" + address[1]);
		finalAddress[2] = address[1].split(" ")[0].trim();
		finalAddress[3] = address[1].split(" ")[1].trim();
		System.out.println("state - " + finalAddress[2]);
		System.out.println("zip - " + finalAddress[3]);
		return finalAddress;
	}

	public void clickCarouselCoupon(int position) {
		String finalXpath = MessageFormat.format(hlnkAllRecommendedLocationXpath, position + 1);
		webAppDriver.clickElementByXpath(finalXpath);
	}

	public void clickOnRightCarouselArrow() {
		webAppDriver.clickElementByXpath(hlnkRightArrowRecommendationLocationXpath);
	}

}
