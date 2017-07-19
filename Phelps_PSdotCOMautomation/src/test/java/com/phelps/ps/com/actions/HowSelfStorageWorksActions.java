package com.phelps.ps.com.actions;

//import com.gargoylesoftware.htmlunit.WebAssert;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.HowSelfStorageWorksLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfStorageLocators;

public class HowSelfStorageWorksActions implements HomeLocators, SearchResultsPageLocators, CommonLocators, SelfStorageLocators,
		HowSelfStorageWorksLocators {

	CateredWebDriver webAppDriver;

	public HowSelfStorageWorksActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	/**
	 * Mouse over Storage menu
	 */
	public void clickOnSubTabWhatWeOffer() {
		webAppDriver.clickElementByXpath(subTabWhatWeOfferXpath);
		webAppDriver.verifyDivTagTextContains("Unit Types");
	}

	public void clickOnSubTabHowToStore() {
		webAppDriver.clickElementByXpath(subTabHowToStore);
		webAppDriver.verifyATagTextEquals("\"How to Store in Five Easy Steps.\"");

	}

	public void hoverFindStepOne() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(stepOneXpath));
		webAppDriver.verifyATagTextEquals("Location Finder");
	}

	public void hoverFindStepTwo() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(stepTwoXpath));
		webAppDriver.verifyATagTextEquals("Size Guide");
	}

	public void hoverFindStepThree() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(stepThreeXpath));
		webAppDriver.verifyElementPresentById(lnkLearnMoreId);
	}

	public void hoverFindStepFour() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(stepFourXpath));
		webAppDriver.verifyATagTextEquals("Packing Tips");
	}

	public void hoverFindStepFive() {
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(stepFiveXpath));
		webAppDriver.verifyATagTextEquals("Storage Tips");
	}

	public void clickOnReservingTab() {
		webAppDriver.clickElementById(lnkReservingTabId);
		webAppDriver.verifyElementPresentById(lnkHowStoreInFiveStepsId);
	}

	public void searchOnBasicSubTab(String searchCriteria) {
		webAppDriver.enterTextToElementByXpath(tbSearchBasicXpath, searchCriteria);
		webAppDriver.clickElementByXpath(btnSearchBasicXpath);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}

	// Find Location Subtab
	public void clickOnFindLocationSubTab() {
		webAppDriver.clickElementByXpath(lnkFindLocationXpath);
		webAppDriver.verifyElementPresentById(lbTextVerificationFindLocationSubTabId);
	}

	public void searchOnFindLocationSubTab(String searchCriteria) {
		webAppDriver.enterTextToElementByClassname(tbSearchFindLocationSubTabClass, searchCriteria);
		webAppDriver.clickElementById(btnSearchFindLocationSubTabId);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}

	// Select A Unit Subtab
	public void clickOnSubTabSelectUnit() {
		webAppDriver.clickElementByXpath(lnkSelectUnitXpath);
		webAppDriver.verifyElementPresentById(lbTextVerificationSelectUnitSubTabId);
	}

	// Unit Prices Subtab
	public void clickOnSubTabUnitPrices() {
		webAppDriver.clickElementByXpath(lnkUnitPricesXpath);
		webAppDriver.verifyElementPresentById(lbTextVerificationUnitPricesSubTabId);
	}

	public void searchOnUnitPricesSubTab(String searchCriteria) {
		webAppDriver.enterTextToElementByXpath(tbSearchUnitPricesSubTabXpath, searchCriteria);
		webAppDriver.clickElementByXpath(btnSearchButtonUnitPricesSubTabXpath);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}

	// Reservations Subtab
	public void clickOnSubTabReservations() {
		webAppDriver.clickElementByXpath(lnkReservationsXpath);
		webAppDriver.verifyElementPresentById(lbTextVerificationReservationSubTabId);
	}

	public void searchReservationsSubTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbReservationsSubTabId, searchCriteria);
		webAppDriver.clickElementById(btnSearchReservationsSubTabId);
		webAppDriver.checkTextInListByCss(tabStorageTypesCss, "Storage Units");
		// webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}

	// TAB Moving In
	public void clickOnMovingInTab() {
		webAppDriver.clickElementById(linkMovingInTabId);
		webAppDriver.verifyElementPresentById(lbPageVerificationId);
	}

	// Be Rental Ready SubTab
	public void clickOnBeRentalReadySubTab() {
		webAppDriver.clickElementByXpath(lnkBeRentalReadySubTabXpath);
		webAppDriver.verifyElementPresentById(lbPageVerificationBeRentalReadySubTabId);
	}

	// Move In day SubTab
	public void clickOnMoveInDaySubTab() {
		webAppDriver.clickElementByXpath(lnkMoveInDaySubTabXpath);
		webAppDriver.verifyElementPresentById(lbPageVerificationMoveInDaySubTabId);
	}

	// Move In day SubTab
	public void clickOnLoadinYourUnitsSubTab() {
		webAppDriver.clickElementByXpath(lnkLoadingYourUnitsSubTabXpath);
		webAppDriver.verifyElementPresentById(lbPageVerificationLoadingYourUnitsSubTabId);
	}

	// TAB You Account
	public void clickOnYourAccountTab() {
		webAppDriver.clickElementById(lnkYourAccountTabId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationYourBillSubTabXpath);
	}

	// Your Account SubTab
	public void clickOnYourAccountSubTab() {
		webAppDriver.clickElementByXpath(lnkYourAccountSubTabXpath);
		webAppDriver.verifyElementPresentById(lbPageVerificationYourAccountSubTabId);
	}

	// Unit Access SubTab
	public void clickOnUnitAccessSubTab() {
		webAppDriver.clickElementByXpath(lnkUnitAccessSubTabXpath);
		webAppDriver.verifyElementPresentById(lbPageVerificationUnitAccessSubTabId);
	}

	// Moving Out SubTab
	public void clickOnMovingOutSubTab() {
		webAppDriver.clickElementByXpath(lnkMovingOutSubTabXpath);
		webAppDriver.verifyElementPresentById(lbPageVerificationMovingOutSubTabId);
	}

	// Your Account Tab Moving Out sub Tab
	public void clickOnMoveOutLinkOnline() {
		webAppDriver.clickElementById(linkYourAccountMovingOutOnline);
		webAppDriver.verifyPresenceOfTextInPTagText("Schedule a Move-Out");

	}
}
