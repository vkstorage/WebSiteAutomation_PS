package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;

public class ECI_RetConfActions {

	CateredWebDriver webAppDriver;
	CommonActions commonActions;

	public ECI_RetConfActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);
	}

	public void verifyECIReturnDetails(String[] storageDetails, String ext,String firstName, String lastName, String email,String phoneNumber,String moveInDate) {
		/* ECI ‘Return Flow’ Confirm - https://psstaging.phelpsagency.com/eci-ret-conf.aspx
		 * 1. Confirm that the ‘Your Reservation’ column on the left side of the page has the same data as you noted on the on the SRP:
		 * a. Property
		 * b. Size (shown in ‘Unit’)
		 * c. Promotion (shown in ‘Move-In Cost’)
		 * d. Monthly Price (shown in ‘Monthly Rent’)
		 */
		commonActions.verifyPropertyAddress(ECI_RDP_ConfLocators.lbStorageAddressXpath, storageDetails[0]);
		/*	for (String add : storageDetails[0].split(" ")) {
				webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbStorageAddressXpath, add);
			}*/
		webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbStorageSizeXpath, storageDetails[1]);
		webAppDriver.verifyElementTextContainsById(ECI_RDP_ConfLocators.lbMonthlyRentId, storageDetails[2]);
		webAppDriver.verifyTextMatchesElementByXpath(moveInDate, ECI_RDP_ConfLocators.lbMoveInDateXpath);
		webAppDriver.verifyElementPresentByXpath("//div[@class='user_overview'][contains(.,'"+phoneNumber+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@class='user_overview'][contains(.,'ext "+ext+"')]");

		/*
		 * 3. Confirm that the confirmation page has the same data you entered on the RDP:
		 * a. Your full name
		 * b. E-mail address
		 * c. Phone number
		 */
		webAppDriver.verifyPageSourceContains(firstName );
		webAppDriver.verifyPageSourceContains(lastName);
		webAppDriver.verifyPageSourceContains(email);
		// Bug
		// webAppDriver.verifyPageSourceContains(phoneNumber.replaceAll("-", ""));
	}

}
