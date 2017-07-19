package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;

public class RDP_Conf_HoldActions implements RDP_Conf_HoldLocators {

	private CateredWebDriver webAppDriver;
	private CommonActions commonActions;

	public RDP_Conf_HoldActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);
	}

	/**
	 * Click the Express check-in button
	 */
	public void clickExpressCheckIn() {
		webAppDriver.clickElementById(btnExpressCheckInId);
		webAppDriver.verifyPresenceOfTextInSpanTag("Complete Your Express Check-In Now.");
	}

	public void clickViewLocationInfoLink() {
		webAppDriver.clickElementByLinkText("View Location Info");

		System.out.println(webAppDriver.findElementById("address_info").getText());
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbPublicStorageCss, "Public Storage");
	}

	public void verifyRDPConfHoldDetails(String[] storageDetails, String firstName, String lastName, String ext,String email, String phoneNumber, String moveInDate) {
		/*
		 * Hold Confirmation - https://psstaging.phelpsagency.com/rdp-conf-hold.aspx
		 * 1. Confirm that the ‘Your Reservation’ column on the left side of the page has the same data as you noted on the on the SRP:
		 * a. Property
		 * b. Size (shown in ‘Unit’)
		 * c. Promotion (shown in ‘Move-In Cost’)
		 * d. Monthly Price (shown in ‘Monthly Rent’)
		 */
		commonActions.verifyPropertyAddress(lbHoleStorageAddressXpath, storageDetails[0]);
		/*for (String add : storageDetails[0].split(" ")) {
			webAppDriver.verifyElementTextContainsByXpath(lbHoleStorageAddressXpath, add);
		}*/
		webAppDriver.verifyElementTextContainsByXpath(lbHoldStorageSizeXpath, storageDetails[1]);
		// Monthly cost is not showing in the left pane.
		// webAppDriver.verifyElementTextContainsById(, storageDetails[2]);
		
		//To verify the Selected move in date is displayed on the left pane
		webAppDriver.verifyElementTextContainsByXpath(lbMoveInDateXpath, moveInDate);

		/*
		 * 3. Confirm that the confirmation page has the same data you entered on the RDP:
		 * a. Your full name
		 * b. E-mail address
		 * c. Phone number
		 */
		webAppDriver.verifyPageSourceContains(firstName );
		webAppDriver.verifyPageSourceContains(lastName);
		webAppDriver.verifyPageSourceContains(email);
		webAppDriver.verifyPageSourceContains(ext);
		webAppDriver.verifyPageSourceContains(phoneNumber);
		

		
			}

}
