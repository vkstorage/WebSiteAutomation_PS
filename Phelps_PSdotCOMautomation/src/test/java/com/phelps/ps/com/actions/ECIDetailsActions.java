package com.phelps.ps.com.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.ECIDetailsLocators;

public class ECIDetailsActions implements ECIDetailsLocators {

	private CateredWebDriver webAppDriver;
	private CommonActions commonActions;

	public ECIDetailsActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);
	}

	public void enterAddress1(String address1) {
		webAppDriver.enterTextToElementById(tbAddress1Id, address1);
	}

	public void enterAddress2(String address2) {
		webAppDriver.enterTextToElementById(tbAddress2Id, address2);
	}

	public void enterCity(String city) {
		webAppDriver.enterTextToElementById(tbCityId, city);
	}

	public void selectState(String state) {
		webAppDriver.selectByVisibleTextByLocatorId(ddStateId, state);
	}

	public void enterZip(String zip) {
		webAppDriver.enterTextToElementById(tbZIPId, zip);
	}

	public void selectIdType(String idType) {
		webAppDriver.selectByVisibleTextByLocatorId(ddIdTypeId, idType);
	}

	public void enterIdNumber(String idNumber) {
		webAppDriver.enterTextToElementById(tbIDNumberId, idNumber);
	}

	public void enterEmergenctContactFirstName(String emCntFirstName) {
		webAppDriver.enterTextToElementById(tbEmCntFirstNameId, emCntFirstName);
	}

	public void enterEmergenctContactLastName(String emCntLastName) {
		webAppDriver.enterTextToElementById(tbEMCntLastNameId, emCntLastName);
	}

	public void selectRelationShip(String relation) {
		webAppDriver.selectByVisibleTextByLocatorId(ddEmCntRelationShipId, relation);
	}

	public void enterEmergenctContactPhoneNo(String emCntPhoneNo) {
		webAppDriver.enterTextToElementById(tbEmCntPhoneId, emCntPhoneNo);
	}

	public void enterAllMandatoryDetails(String address1, String address2, String city, String state, String zip, String idType,
			String idNumber, String emgCntFirstname, String emgCntLastName, String relationship, String emgCntPhoneNo) {
		enterAddress1(address1);
		enterAddress2(address2);
		enterCity(city);
		selectState(state);
		enterZip(zip);
		selectIdType(idType);
		enterIdNumber(idNumber);
		enterEmergenctContactFirstName(emgCntFirstname);
		enterEmergenctContactLastName(emgCntLastName);
		selectRelationShip(relationship);
		enterEmergenctContactPhoneNo(emgCntPhoneNo);
	}

	public void clickComplete() {
		webAppDriver.clickElementByXpath(btnCompleteXpath);
	//	webAppDriver.verifyElementPresentByXpath(lbSuccessMessageXpath);
	}

	public void clickTopClickHere() {
		webAppDriver.clickElementByXpath(hlinkTopClickHereXpath);
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
	}

	public void clickBottomClickHere() {
		webAppDriver.clickElementByXpath(hlinkBottomClickHereXpath);
	}

	public void clickOnViewLocationInfoLink() {
		webAppDriver.clickElementById(linkViewLocationInforId);

	}

	public void verifyUserDetails(String[] storageDetails,String ext, String email, String firstName, String lastName, String phoneNumber,
			String moveInDate) {
		/*
		 * ECI ‘RDP Flow’ Form -
		 * https://psstaging.phelpsagency.com/eci-rdp.aspx?src=res 1. Confirm that
		 * the top of the ECI form contains the same data as you entered on the SRP:
		 * a. Your full name b. Phone number c. E-mail address d. Move-In Date
		 */
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbECIPageEmailXpath, email);

		// WC2
		webAppDriver.verifyTextContainInAnyTag(firstName);
		webAppDriver.verifyTextContainInAnyTag(lastName);
		/* webAppDriver.verifyStrongTagTextEquals(firstName + " " + lastName); */
		// String currentDate = new SimpleDateFormat("M/d/yyyy").format(new Date());
		webAppDriver.verifyPresenceOfTextLocatedByXpath(ECIDetailsLocators.lbECIPageMoveINDateXpath, moveInDate);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbECIPagePhoneNumberXpath, phoneNumber); 
		//Added on 26th Sep for ext verification
		webAppDriver.verifyElementPresentByXpath("//div[@class='user_overview'][contains(.,'ext "+ext+"')]");

		/*
		 * 3. Confirm that the ‘Your Reservation’ column on the left side of the
		 * page has the same data as you noted on the on the SRP: a. Property b.
		 * Size (shown in ‘Unit’) c. Promotion (shown in ‘Move-In Cost’) d. Monthly
		 * Price (shown in ‘Monthly Rent’)
		 */
		commonActions.verifyPropertyAddress(ECIDetailsLocators.lbStorageAddressXpath, storageDetails[0]);
		/*
		 * for (String add : storageDetails[0].split(" ")) {
		 * webAppDriver.verifyElementTextContainsByXpath
		 * (ECIDetailsLocators.lbStorageAddressXpath, add); }
		 */
		webAppDriver.verifyElementTextContainsByXpath(ECIDetailsLocators.lbSpaceSizeXpath, storageDetails[1]);
		webAppDriver.verifyElementTextContainsById(ECIDetailsLocators.lbMonthlyRentId, storageDetails[2]);

	}

}
