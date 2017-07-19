package com.phelps.ps.com.actions;

import org.testng.Reporter;

//import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfStorageLocators;

public class HomeActions extends CommonActions implements HomeLocators, SearchResultsPageLocators, CommonLocators,
		SelfStorageLocators,LoginLocators ,CommonSearchTextLocators{

	CateredWebDriver webAppDriver;
	String usernae=username;

	public HomeActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	/**
	 * Mouse over Storage menu
	 */
	public void hoverStorage() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(mlStorageId));
		webAppDriver.verifySpanTagTextEquals("Storage");
		webAppDriver.clickElementById(mlStorageId);
	}

	/**
	 * Mouse over Moving Supplies menu
	 */
	public void hoverMovingSupplies() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(mlMovingSuppliesId));
		webAppDriver.verifySpanTagTextEquals("Moving Supplies");
		webAppDriver.clickElementById(mlMovingSuppliesId);
	}

	/**
	 * Mouse over MovinverifyMandatoryFieldsTestg Supplies menu
	 */
	public void hoverCompanyInfo() {
		webAppDriver.mouseOverOn(webAppDriver.findElementById(mlCompanyInfoId));
		webAppDriver.verifyPresenceOfTextInSpanTag("Company Info");
		webAppDriver.clickElementById(mlCompanyInfoId);
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
		//webAppDriver.clickElementById(btnSearchId);
		webAppDriver.clickElementByCss(btnSearchCss);
	}

	/**
	 * Mouse over Moving Supplies menu
	 */
	public void clickOnMyAccount() {
		webAppDriver.clickElementByLinkText(linkMyAccountText);
		webAppDriver.verifySpanTagTextEquals("Sign In to Your Account");
	}

	public void clickOnMyAccountLoggedIn() {
		try{
		webAppDriver.clickElementById(btnMyAccountLoggedInId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(webAppDriver.verifyElementIsPresentByXpath(btnMyAccountLoginXpath))		{
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
		//webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	//	webAppDriver.verifyPresenceOfTextLocatedByXpath(lbStoragemadeEasyTitleXpath, "Public Storage Videos");
		webAppDriver.verifyPresenceOfTextInDivTagText("How to Pack & Store");
	}

	public void clickStorageMadeReliableImage() {
		webAppDriver.clickElementById(imgStorageMadeReliableId);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	//	webAppDriver.verifyPresenceOfTextLocatedByXpath(lbStoragemadeEasyTitleXpath, "Public Storage Videos");
	}

	public void clickStorageMadeLocalImage() {
		webAppDriver.clickElementById(imgStorageMadeLocalId);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbFindPublicStorageLocationsCls, "Find Public Storage Locations");
	}

	public void clickOnBenefitsLink() {
		webAppDriver.clickElementByXpath(imgBenefitsXpath);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	}

	public void clickOnStorageMenu() {
		webAppDriver.clickElementByXpath(menuItmStorageXpath);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	}

	public void clickOnHowSelfStorageWorksMenu() {
		webAppDriver.clickElementByXpath(menuItmHowSelfStorageWorksXpath);
		webAppDriver.verifyPresenceOfTextLocatedByClassName(lbHowSelfStorageWorksCls, "How Self Storage Works");
	}

	public void clickOnSelfStorageMenu() {
		webAppDriver.clickElementByXpath(menuItmSelfStorageXpath);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbHowSelfStorageWorksPageVerificationXpath, "Self Storage: Reclaim Your Space");
	}

	public void clickOnBusinessStorageMenu() {
		webAppDriver.clickElementByXpath(menuItmBusinessStorageXpath);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbBusinessStorageWorksPageVerificationXpath,
				"Business Storage Solutions: Extend Your Office");
	}

	public void clickOnVehicleStorageMenu() {
		webAppDriver.clickElementByXpath(menuItmVehicleStorageXpath);
		webAppDriver.verifyElementPresentById(tbCarStorageId);
	}

	public void clickOnStorageBlogMenu() {
		webAppDriver.clickElementByXpath(menuItmStorageBlogXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageBlogPageXpath);
	}

	// Moving And Supplies Menu
	public void clickOnStorageBoxesMenu() {
		webAppDriver.clickElementByXpath(menuItemStorageBoxesXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageBoxesXpath);
	}

	public void clickOnStorageLocksMenu() {
		webAppDriver.clickElementByXpath(menuItemStorageLocksXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageLocksXpath);
	}

	public void clickOnPackingSuppliesMenu() {
		webAppDriver.clickElementByXpath(menuItemPackagingSuppliesXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationPackingSuppliesXpath);
	}

	public void clickOnTapeMenu() {
		webAppDriver.clickElementByXpath(menuItemTapeXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerifcationTapeXpath);
	}

	// Company Info
	public void clickOnAboutUsMenu() {
		webAppDriver.clickElementByXpath(menuItemAboutUsXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationAboutUsXpath);
	}

	public void clickOnInvestorRelationMenu() {
		webAppDriver.clickElementByXpath(menuItemInvestorRelationsXpath);
		webAppDriver.verifyPresenceOfTextInSpanTag("Corporate Profile");
	}

	public void clickOnGlobalLocationMenu() {
		webAppDriver.clickElementByXpath(menuItemGlobalLocationsXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationGlobaLocationsXpath);
	}

	public void clickOnContactUsMenu() {
		webAppDriver.clickElementByXpath(menuItemContactUsXpath);
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
	
	
	//WC2
	
	public void clickOnReservationsLink(){
	webAppDriver.clickElementById(btnReservationsId);
	//	webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
		//clickOnOldReservationsLink();
	}
	public void clickOnOldReservationsLink(){
		webAppDriver.clickElementById(btnOldReservationsId);
	//	webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
	}
	
	public void clickOnManageReservationPendingHomePage(){
		webAppDriver.clickElementByXpath(btnManageYourReservationPendingHomeXpath);
		
	//	webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
	}
	
	public void clickOnECIOnPendingHomePage(){
		webAppDriver.clickElementByXpath(btnECIPendingHomeXpath);
		//webAppDriver.verifySpanTagTextEquals("Complete Your Express Check-In Now.");
	}
	
	
	//Contact Us
	public void enterContactUsEmail(String email){
		webAppDriver.enterTextToElementById(tbContactUsEmailAddressId, email);
		
	}
	
	public void enterContactusName(String name){
		webAppDriver.enterTextToElementById(tbContactUsNameId, name);
	}
	
	public void enterContactusInquiry(String inquiry){
		webAppDriver.enterTextToElementById(taContactUsNaturOfInquiryId, inquiry);
	}
	
	public void enterContactUsPhone(String phone){
		
		String phone1,phone2,phone3;
		phone1=phone.substring(0, 3);
		phone2=phone.substring(3, 6);
		phone3=phone.substring(6, 10);
		
		webAppDriver.enterTextToElementById(tbContactInfoPhone1Id,phone1 );
		webAppDriver.enterTextToElementById(tbContactInfoPhone2Id, phone2);
		webAppDriver.enterTextToElementById(tbContactInfoPhone3Id, phone3);
		
	}
	
	public void enterContactUsExtension(String ext){
		webAppDriver.enterTextToElementById(tbConactUsPhoneExtid, ext);
	}
	
	public void selectContactUsTopic(String topic){
		webAppDriver.selectByVisibleTextByLocatorId(ddContactUsTopidId, topic);
	}
	
	public void clickContactUsSubmitbutton(){
		webAppDriver.clickElementById(btnContactUsSubmitId);
	}
	
	//Home Page Refresh
	
}
