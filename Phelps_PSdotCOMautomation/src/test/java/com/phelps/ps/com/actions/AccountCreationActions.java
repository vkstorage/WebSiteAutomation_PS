package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.AccountCreationLocators;

public class AccountCreationActions implements AccountCreationLocators {

	CateredWebDriver webAppDriver;
	CommonActions commonActions;

	public AccountCreationActions(CateredWebDriver webAppDriver) {

		this.webAppDriver = webAppDriver;

		commonActions = new CommonActions(webAppDriver);
	}

	public void enterEmailOnReservationLookUp(String email) {
		webAppDriver.enterTextToElementById(tbEmailAddressId, email);
	}

	public void enterAccountNoReservationLookUp(String accountNo) {
		webAppDriver.enterTextToElementById(tbAccountNumberId, accountNo);
	}

	public void clickOnCreateAccountSubmit() {
		
		webAppDriver.clickElementByXpath(btnCreateAccountSubmitXpath);
	}
	
	public void clickOnAccountNumberToolTip()
	{
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(ttAccountNumberxpath));
//		webAppDriver.clickElementByXpath(ttAccountNumberxpath);
		webAppDriver.verifyElementPresentByXpath(ttAccountNumberCopyXpath);
	}
	
	public void clickOnEmailAddressToolTip(){
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(ttEmailAddressXpath));
		//webAppDriver.verifyElementPresentByXpath(ttAccountNumberCopyXpath);
	}
	public void clickOnRecoverAccountNumberLink(){
		webAppDriver.clickElementByXpath(ttAccountNumberxpath);
		webAppDriver.clickElementByXpath(hlinkttAccountNumberXpath);
	}
	public void enterAccountCreationUserName(String username) {
		webAppDriver.enterTextToElementById(tbCreateAccountUsernameId, username);
	}
	
	public void clickOnUsernameToolTip()
	{
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(ttusernameXpath));
		
	}
	
	public void clickOnPasswordToolTip()
	{
		webAppDriver.mouseOverOn(webAppDriver.findElementByXpath(ttPasswordXpath));
	
	}

	public void enterAccountCreationPassword(String password) {
		webAppDriver.enterTextToElementById(tbCreateAccountPasswordId, password);
	}

	public void enterAccountCreationConfPassword(String confPassword){

		webAppDriver.enterTextToElementById(tbCreateAccountConfirmPasswordId, confPassword);
	}

	public void clickOnAccountCreationEnterDetailsSubmit(){
		webAppDriver.clickElementByXpath(btnCreateAccountSubmitXpath);

	}


}
