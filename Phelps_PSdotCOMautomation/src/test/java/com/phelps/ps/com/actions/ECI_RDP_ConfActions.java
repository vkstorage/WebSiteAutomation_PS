package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;

public class ECI_RDP_ConfActions {

	CateredWebDriver webAppDriver;
	CommonActions commonActions;

	public ECI_RDP_ConfActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);
	}

	public void verifyECIRDPConfDetails(String[] storageDetails, String firstName, String lastName, String ext,String email, String phoneNumber, String moveInDate, String reservationNumber) {
		/* ECI ‘RDP Flow’ Confirm - https://psstaging.phelpsagency.com/eci-rdp-conf.aspx
		 * 1. Confirm that the ‘Your Reservation’ column on the left side of the page has the same data as you noted on the on the SRP:
		 * a. Property
		 * b. Size (shown in ‘Unit’)
		 * c. Promotion (shown in ‘Move-In Cost’)
		 * d. Monthly Price (shown in ‘Monthly Rent’)
		 */
		commonActions.verifyPropertyAddress(ECI_RDP_ConfLocators.lbStorageAddressXpath, storageDetails[0]);
/*		for (String add : storageDetails[0].split(" ")) {
			webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbStorageAddressXpath, add);
		}*/
		webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbStorageSizeXpath, storageDetails[1]);
		webAppDriver.verifyElementTextContainsById(ECI_RDP_ConfLocators.lbMonthlyRentId, storageDetails[2]);

		/*
		 * 3. Confirm that the confirmation page has the same data you entered on the RDP:
		 * a. Your full name
		 * b. E-mail address
		 * c. Phone number
		 */
		webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbUserInformationXpath, firstName + " " + lastName);
		webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbUserInformationXpath, email);
		webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbUserInformationXpath, ext);
		webAppDriver.verifyElementTextContainsByXpath(ECI_RDP_ConfLocators.lbUserInformationXpath, phoneNumber);
		//.replaceAll("-", "")
		webAppDriver.verifyPresenceOfTextLocatedByXpath(ECI_RDP_ConfLocators.lbMoveInDateXpath, moveInDate);
		//WC2
		webAppDriver.verifyPresenceOfTextLocatedByXpath(RDP_Conf_HoldActions.lbConfirmationReservationNumberXpath, reservationNumber);
	}

}
