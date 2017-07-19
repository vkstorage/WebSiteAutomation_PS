package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.RecoverReservationNumberLocators;
import com.phelps.ps.com.locators.ReservationCareLoginLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;

public class ReservationCareLoginActions implements ReservationCareLoginLocators, RecoverReservationNumberLocators {

	private CateredWebDriver webAppDriver;
	private CommonActions commonActions;

	public ReservationCareLoginActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);

	}

	public void enterEmailAddress(String emailAddress) {
		webAppDriver.enterTextToElementById(tbEmailAddressId, emailAddress);
	}

	public void enterReservationNumber(String ReservationNo) {
		webAppDriver.findElementById(tbReservationNumberId).clear();
		webAppDriver.findElementById(tbReservationNumberId).sendKeys(ReservationNo);
	}


	public void clickOnSubmit() {
		webAppDriver.clickElementByXpath(btnRservationCareLoginXpath);
	}

	public void clickForgotReservationNumberLink(){
		webAppDriver.clickElementByLinkText(hlinkForgetReservationNumberLink);
		webAppDriver.verifySpanTagTextEquals(lbRecoverReservationSpanText);
	}
	
	public void clickOnHomeLink(){
		webAppDriver.clickElementByLinkText(hlinkHomeLink);
		webAppDriver.verifyElementPresentByXpath("//input[@class='hpSearchInput']");
	}
}
