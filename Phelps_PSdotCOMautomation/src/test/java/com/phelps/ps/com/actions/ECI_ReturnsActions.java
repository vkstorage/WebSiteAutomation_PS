package com.phelps.ps.com.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.ECI_RetrunLocators;

public class ECI_ReturnsActions implements ECI_RetrunLocators {

	CateredWebDriver webAppDriver;
	CommonActions commonActions;

	public ECI_ReturnsActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);
	}

	public void verifyECIDetails(String[] storageDetails,String ext, String firstName, String lastName, String email, String phoneNumber, String moveInDate) {
		/*
		 * ECI ‘Return Flow’ Form - https://psstaging.phelpsagency.com/eci-return.aspx?src=ret
		 * 1. Confirm that the top of the ECI form contains the same data as you entered on the SRP:
		 * a. Your full name
		 * b. Phone number
		 * c. E-mail address
		 * d. Move-In Date
		 */
		webAppDriver.verifyPresenceOfTextLocatedByXpath(ECI_RetrunLocators.lbEmailXpath, email);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbFullNameXpath,firstName + " " + lastName);
		//webAppDriver.verifyStrongTagTextEquals(firstName + " " + lastName);
		//String currentDate = new SimpleDateFormat("M/d/yyyy").format(new Date());
		webAppDriver.verifyPresenceOfTextLocatedByXpath(ECI_RetrunLocators.lbMoveInDateXpath, moveInDate);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(ECI_RetrunLocators.lbPhoneXpath, phoneNumber);
		//Added on 26th Sep for ext verification
		webAppDriver.verifyElementPresentByXpath("//div[@class='user_overview'][contains(.,'ext "+ext+"')]");

		/* 
		 * 3. Confirm that the ‘Your Reservation’ column on the left side of the page has the same data as you noted on the on the SRP:
		 * a. Property
		 * b. Size (shown in ‘Unit’)
		 * c. Promotion (shown in ‘Move-In Cost’)
		 * d. Monthly Price (shown in ‘Monthly Rent’)
		 */
		commonActions.verifyPropertyAddress(ECI_RetrunLocators.lbStorageAddressXpath, storageDetails[0]);
/*		for (String add : storageDetails[0].split(" ")) {
			webAppDriver.verifyElementTextContainsByXpath(ECI_RetrunLocators.lbStorageAddressXpath, add);
		}*/
		webAppDriver.verifyElementTextContainsByXpath(ECI_RetrunLocators.lbSpaceSizeXpath, storageDetails[1]);
		webAppDriver.verifyElementTextContainsById(ECI_RetrunLocators.lbMonthlyRentId, storageDetails[2]);
		//webAppDriver.verifyElementTextContainsByXpath(ECI_RetrunLocators.lbSpecialSummerRentXpath, storageDetails[2]);
	}
	
	/**
	 * This method clicks Complete Button on EciReturns page
	 */
	public void clickCompleteButton(){
		webAppDriver.clickElementByClassname(btnEciReturnsCompleteClass);
	}
}
