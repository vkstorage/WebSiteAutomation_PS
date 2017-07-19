package com.phelps.ps.com.actions;

//import com.gargoylesoftware.htmlunit.WebAssert;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.SelfCareMyStorageUnitLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class SelfCareMyStorageUnitsActions implements SelfcareSummaryLocators, SelfCareMyStorageUnitLocators{
	CateredWebDriver webAppDriver;

	public SelfCareMyStorageUnitsActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	public void clickReviewThisProperty(){
		webAppDriver.clickElementByLinkText("Review this property");
	}
	
	public void clickLogo(){
		webAppDriver.clickElementByXpath(imgLogo);
	}
	
	public void ClickCalloutNext(){
		webAppDriver.clickElementByXpath(btnNextCalloutXpath);
	}
	public void clickSaveOfScheduleMoveOut(){
		webAppDriver.clickButtonWithText("SAVE");
	}
	public void clickContinueOfScheduleMoveOut(){
		webAppDriver.clickElementByXpath(btnContinueMoveOutXpath);
	}
	public void clickMoveOutCancel(){
		webAppDriver.clickElementByLinkText("Cancel");
	}
	public void clickMoveOutHome(){
		webAppDriver.clickButtonWithText("Home");
	}
	public void clickCancelOnMoveOutPage(){
		webAppDriver.clickButtonWithText("CANCEL");
	}
	public void clickLocationDetails(){
		webAppDriver.clickElementByLinkText("Location details");
		//webAppDriver.verifyPresenceOfTextInH3Tag("Public Storage");
	}
	
	public void clickBackOnScheduleMoveOutDate(){
		webAppDriver.clickElementByXpath(btnBackXpath);
	}
	
}
