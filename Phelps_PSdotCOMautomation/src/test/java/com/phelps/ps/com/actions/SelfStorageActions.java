package com.phelps.ps.com.actions;

import org.openqa.selenium.Keys;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfStorageLocators;

public class SelfStorageActions implements HomeLocators, SearchResultsPageLocators, CommonLocators, SelfStorageLocators {

	CateredWebDriver webAppDriver;

	public SelfStorageActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	//Storage For moving Tab
	public void enterSearchCriteriaStorageForMovingTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbSearchStorageSelfStorageId, searchCriteria);
		webAppDriver.findElementById(tbSearchStorageSelfStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	
	//Storage For Travelling 
	public void clickOnStorageForTravellingTab(){
		webAppDriver.clickElementByXpath(lnkTravellingeStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbSearchTravellingTabId);
	}
	public void enterSearchCriteriaStorageForTravellingTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbSearchTravellingTabId, searchCriteria);
		webAppDriver.findElementById(tbSearchTravellingTabId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Storage for College
	public void clickOnCollegeStorageTab(){
		webAppDriver.clickElementByXpath(lnkCollegeStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbSearchCollegeStorageTabId);
	}
	public void enterSearchCriteriaCollegeStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbSearchCollegeStorageTabId, searchCriteria);
		webAppDriver.findElementById(tbSearchCollegeStorageTabId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Storage for Extra room
	public void clickOnExtraRoomTab(){
		webAppDriver.clickElementByXpath(lnkExtraRoomTabXpath);
		webAppDriver.verifyElementPresentById(tbSearchExtraRoomTabId);
	}
	public void enterSearchCriteriaExtraRoomTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbSearchExtraRoomTabId, searchCriteria);
		webAppDriver.findElementById(tbSearchExtraRoomTabId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Pharmaceutical Storage
	public void enterSearchCriteriaPharmaceuticalStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbPharmaStorageId, searchCriteria);
		webAppDriver.findElementById(tbPharmaStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Construction Storage
	public void clickOnConstructionStorageTab(){
		webAppDriver.clickElementByXpath(lnkConstructionStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbConstructionStorageId);
	}
	public void enterSearchCriteriaConstructionStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbConstructionStorageId, searchCriteria);
		webAppDriver.findElementById(tbConstructionStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Real estate storage
	public void clickOnRealEstateStorageTab(){
		webAppDriver.clickElementByXpath(lnkRealEstateStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbRealEstateStorageId);
	}
	public void enterSearchCriteriaRealEstateStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbRealEstateStorageId, searchCriteria);
		webAppDriver.findElementById(tbRealEstateStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Retail Storage
	public void clickOnRetailStorageTab(){
		webAppDriver.clickElementByXpath(lnkRetailStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbRetailStorageId);
	}
	public void enterSearchCriteriaRetailStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbRetailStorageId, searchCriteria);
		webAppDriver.findElementById(tbRetailStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Archive Storage
	public void clickOnArchiveStorageTab(){
		webAppDriver.clickElementByXpath(lnkArchiveStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbArchiveStorageId);
	}
	public void enterSearchCriteriaArchiveStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbArchiveStorageId, searchCriteria);
		webAppDriver.findElementById(tbArchiveStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Office Storage
	public void clickOnOfficeStorageTab(){
		webAppDriver.clickElementByXpath(lnkOfficeStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbOfficeStorageId);
	}
	public void enterSearchCriteriaOfficeStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbOfficeStorageId, searchCriteria);
		webAppDriver.findElementById(tbOfficeStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Car Storage
	public void enterSearchCriteriaCarStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbCarStorageId, searchCriteria);
		webAppDriver.findElementById(tbCarStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Trailer Storage
	public void clickOnTrailerStorageTab(){
		webAppDriver.clickElementByXpath(lnkTrailerStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbTrailerStorageId);
	}
	public void enterSearchCriteriaTrailerStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbTrailerStorageId, searchCriteria);
		webAppDriver.findElementById(tbTrailerStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
	//Boat Storage
	public void clickOnBoatStorageTab(){
		webAppDriver.clickElementByXpath(lnkBoatStorageTabXpath);
		webAppDriver.verifyElementPresentById(tbBoatStorageId);
	}
	public void enterSearchCriteriaBoatStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbBoatStorageId, searchCriteria);
		webAppDriver.findElementById(tbBoatStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
		
	}
	//Motor Cycle Storage
	public void clickOnMotorCycleStorageTab(){
		webAppDriver.clickElementByXpath(lnkMotorcycleStorageXpath);
		webAppDriver.verifyElementPresentById(tbMotorCycleStorageId);
	}
	public void enterSearchCriteriaMotorCycleStorageTab(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbMotorCycleStorageId, searchCriteria);
		webAppDriver.findElementById(tbMotorCycleStorageId).sendKeys(Keys.ENTER);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
	}
}
