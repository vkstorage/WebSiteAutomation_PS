package com.phelps.ps.com.testsuite;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.SelfcareContactInfoActions;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;
import com.phelps.ps.com.locators.InsuranceLocators;

public class Self_CareTestSuite extends BasicTestSuite implements HomeLocators, LoginLocators, SelfcareSummaryLocators,
		PaymentsLocators, InsuranceLocators, SelfCareContactInfoLocators {

	private String creditCardNumber = "5534684335886797";
	private String selfCareMyRequestUser = "";

	String username;
	String oldPassword;
	boolean isWC1User = true;

	@BeforeClass(alwaysRun = true)
	public void setupTest() {
		navigator.login(envTestData.get("userForSelfCare"), "test123");
		// envTestData.get("passwordForSelfCare"));
	//	navigator.login("jcgray404@yahoo.com", "test123");
		selfCareMyRequestUser = envTestData.get("userSelfCareMyRequest");
		username = envTestData.get("userForSelfCare");

		oldPassword = "test123";
		

	}

	/**
	 * PS-165:TC-Selfcare-1.1.1-Verify log in functionality and page view when
	 * logged in
	 */
	
	@Test(priority=0)
	public void verifyAccountLoginFunctionalityTest() {
		String fullName = envTestData.get("fullName");
		String email = envTestData.get("email");
		String nameToCheck = envTestData.get("nameToCheck");
		navigator.gotoMyAccountLoggedIn();
		if (webAppDriver.verifyElementPresentBy(By.xpath(iframeRememberMeLaterXpath))) {
			webAppDriver.switchtoiFrameByXpath(iframeRememberMeLaterXpath);
			selfcareContactInfoActions.clickOnRememberMeLaterLink();
			webAppDriver.switchTo().defaultContent();
		}
		webAppDriver.verifyElementPresentById(linkLogoutId);
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbNameCss, fullName);
		// webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailCss, email);
		webAppDriver.checkAllElementsHavingTextByXpath(lbHelloUserXpath, nameToCheck);

	}

	/**
	 * PS-166:TC-Selfcare-1.2.1-Verify summary page view and functionality
	 */
	 @Test(priority = 1)
	public void verifySummaryPageViewTest() {
		// WC2 Modification My Payment
		String payments = envTestData.get("payments");
		String myAccount = envTestData.get("myAccount");
		String myStorageUnits = envTestData.get("myStorageUnits");
		String nextPayment = envTestData.get("nextPayment");
		String unitNumberSize = envTestData.get("unitNumberSize");
		String gateCode = envTestData.get("gateCode");
		String propertyAddress = envTestData.get("propertyAddress");
		String scheduleAMoveOutLink = "Schedule a Move-out Date";
		String numberOfMovedInUnits = "You have 1 moved in unit";
		String accountNumber = envTestData.get("accountNumber");
		String contactInformation = "Contact Information";
		navigator.gotoMyAccountLoggedIn();
		webAppDriver.verifyPresenceOfTextInDivTagText(payments);
		webAppDriver.verifyPresenceOfTextInDivTagText(myAccount);
		webAppDriver.verifyPresenceOfTextInDivTagText(myStorageUnits);
		// webAppDriver.checkAllElementsHavingTextByCss(lbNextPaymentCss,
		// nextPayment);
		// added for WC2

		// added for merge
		// Added for Bug 3228
		if (!isWC1User)
			webAppDriver.verifyElementNotPresntByLinkText("Reservations");
		else
			webAppDriver.verifyElementPresentByLinkText("Reservations");

		webAppDriver.verifyElementNotPresntByLinkText("Reserve a Unit");
		webAppDriver.verifyElementNotPresntById("sc_reservations");
		webAppDriver.verifyElementNotPresntByXpath(hlinkReservationDetailsXpath);
		webAppDriver.verifyElementPresentByCss(btnPaymentCss);
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbUnitNumberSizeCss, unitNumberSize);
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbGateCodeCss, gateCode);
		// webAppDriver.verifyPresenceOfTextInDivTagText(nextPayment);
		webAppDriver.checkAllElementsHavingTextByCss(lbAddressHoursCss, propertyAddress);
		// webAppDriver.verifyElementPresentByXpath(lbReviewLinkXpath);
		webAppDriver.verifyPresenceOfTextInATag(scheduleAMoveOutLink);
		webAppDriver.verifyPresenceOfTextInDivTagText(numberOfMovedInUnits);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbAccountNumberXpath, accountNumber);
		selfcareSummaryActions.clickPublicStorageOnlineAccountFeatures();
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbContactInformationXpath, contactInformation);
		selfcareSummaryActions.clickPublicStorageOnlineAccountFeatures();
		webAppDriver.verifyElementIsInvisibleByXpath(lbContactInformationXpath);
		selfcareSummaryActions.clickFrequentlyAskedQuestions();
		webAppDriver.verifyPresenceOfTextInATag("How do I change my password?");
		selfcareSummaryActions.clickFrequentlyAskedQuestions();
		webAppDriver.verifyElementIsInvisibleByXpath(lbHowDoIXpath);

	}

	/**
	 * PS-167:TC-Selfcare-1.3.1-Verify default view of ‘Contact Info page’
	 */
	 @Test(priority=2)
	public void defaultViewOfContactInfoPageTest() {
		String fullName = envTestData.get("fullName");
		String accountNumber = envTestData.get("accountNumberUserOne");

		String personalInfo = "Personal Info";
		String loginDetails = "Login Details";

		String notifications = "Notifications";
		String emergencyContactAndAuthorizedUser = "Emergency Contact & Authorized User";
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbContactInfoCss, fullName);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbAccountNumberOnContactInfoLinkXpath, accountNumber);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(hlnkPersonalInfoXpath, personalInfo);
		webAppDriver.verifyPresenceOfTextInATag(loginDetails);
		webAppDriver.verifyPresenceOfTextInATag(notifications);
		webAppDriver.verifyPresenceOfTextInATag(emergencyContactAndAuthorizedUser);

	}

	 @Test(priority = 3)
	public void viewFunctionalitiesAvailableOnPaymentsPageTest() {
		// loginActions.clickOnLogOut();

		// Remove following commented code and login with a user which has
		// previously saved payment methods
		/*
		 * String username = (new ECIFlowTestSuite()).veriyECIFlow1Test();
		 * navigator.login(username,"test123");
		 */

		/*
		 * String scMoveOutUser = envTestData.get("scMoveOutUser"); String
		 * scMoveOutPassword = envTestData.get("scMoveOutPassword");
		 * navigator.gotoMyAccountPage(scMoveOutUser, scMoveOutPassword);
		 */

		navigator.gotoSelfCarePaymentPage();
		webAppDriver.clickElementByLinkText("View full history");
		webAppDriver.verifyPresenceOfTextInPTagText("Payment History");
		selfcareSummaryActions.clickPayment();
		selfcareSummaryActions.clickAddToMyCalender();
		webAppDriver.verifyElementPresentByXpath(lbAddPaymentDateToMyCalender);
		webAppDriver.clickElementByXpath(btnCloseOnAddPaymentDateToMyCalender);
		selfcareSummaryActions.clickManagePaymentMethod();
		webAppDriver.verifyElementPresentByXpath(lbMyPaymentMethods);
		webAppDriver.clickElementByXpath(hlnkBackOfEditAndManagePayment);
		selfcareSummaryActions.clickEditNextToPayment();
		webAppDriver.verifyElementPresentByXpath(lbMyPaymentMethods);

	}

	/**
	 * PS-168:TC-Selfcare-1.4.1-Verify view and functionality of ‘Contact Info’
	 * page when sub tab ‘Address & Personal Info’ is selected canada portal code
	 * 295 Forest Hill Rd, Toronto, ON M5P 2N7, Canada M5P 2N7 Toronto, ON M5P
	 * 2N7, Canada M5P 2N7 Toronto, ON M5P, Canada M5P
	 */
	@Test(priority=4)
	public void contactInfoFunctionalityOnPersonalInfoTabSelectionTest() {
		HashMap<String, Boolean> oldLabelStatus = new HashMap<String, Boolean>();
		HashMap<String, Boolean> newLabelStatus = new HashMap<String, Boolean>();
		String address = envTestData.get("selfCareAddress");
		String cityAndState = envTestData.get("cityAndState");
		String zip = envTestData.get("selfCareZip");
		String state = envTestData.get("selfCareState");

		String country = "United States";

		String newAddress = envTestData.get("selfCareSecondaryAddress");// "1444 S. Alameda Street";
		String newZip = envTestData.get("selfCareSecondaryZip");// "90021";
		String newCity = envTestData.get("secondaryCityAndState");// "Los Angeles";

		String invalidEmail = "1\n";

		String newPhn = "7656787654";
		String newState = envTestData.get("selfCareSecondaryState");// "California";
		String newCountry = envTestData.get("selfCareSecondaryCountry");// "United States";

		// wc2 additions
		String secondaryAddress = envTestData.get("selfCareSecondaryAddress");
		String secondaryCityAndState = envTestData.get("secondaryCityAndState");
		String secondaryZip = envTestData.get("selfCareSecondaryZip");

		String secondaryState = envTestData.get("selfCareSecondaryState");

		String secondaryCountry = envTestData.get("selfCareSecondaryCountry");

		// till here

		String phone = envTestData.get("selfCarePhone");
		String addressError = "Please provide your Address.";
		String cityError = "No Numbers/Special Characters in City";
		String zipError = "Invalid Postal Code";
		String phoneError = "Invalid";
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();

		// WC2 Modify
		oldLabelStatus.put("City & State", webAppDriver.verifyTextContainInAnyTag("City & State"));
		oldLabelStatus.put("Zip Code", webAppDriver.verifyTextContainInAnyTag("Zip Code"));
		// WC2 addition

		newLabelStatus.put("Primary Address", webAppDriver.verifyTextContainInAnyTag("Primary Address"));
		newLabelStatus.put("Secondary Address", webAppDriver.verifyTextContainInAnyTag("Alternate Address"));
		newLabelStatus.put("City", webAppDriver.verifyTextContainInAnyTag("City"));
		newLabelStatus.put("Country", webAppDriver.verifyTextContainInAnyTag("Country"));
		newLabelStatus.put("State", webAppDriver.verifyTextContainInAnyTag("State/Province/Region"));
		newLabelStatus.put("Zip", webAppDriver.verifyTextContainInAnyTag("Zip/Postal Code"));
		// newLabelStatus.put("City",
		// webAppDriver.verifyTextContainInAnyTag("City"));

		// till here

		// Commenting for WC2 since the locators changed

		// Commented for unit testing since the functionality is not ready yet
		/*
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbAddressContactInfoId
		 * , address);
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbCityAndStateId,
		 * cityAndState);
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbZipContactInfoId,
		 * zip); webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbPhoneId,
		 * phone);
		 */
		webAppDriver.verifyEqualsString(webAppDriver.findElementByXpath(tbAddressContactInfoXpath).getAttribute("value"), address);
		webAppDriver.verifyEqualsString(webAppDriver.findElementByXpath(tbCityAndStateXpath).getAttribute("value"), cityAndState);
		webAppDriver.verifyEqualsString(webAppDriver.findElementByXpath(tbZipContactInfoXPath).getAttribute("value"), zip);
		webAppDriver.verifyEqualsString(webAppDriver.findElementByXpath(tbPhoneXpath).getAttribute("value"), phone);

		String invalidData = "";

		selfcareContactInfoActions.enterPrimaryAddress(invalidData);
		invalidData = "11111";
		selfcareContactInfoActions.enterPrimaryCity(invalidData);
		invalidData = "a";
		selfcareContactInfoActions.enterPrimaryZip(invalidData);
		// invalidData = "";
		/*
		 * webAppDriver.clearInputByXpath(tbAddressContactInfoXpath);
		 * webAppDriver.enterTextToElementByXpath(tbAddressContactInfoXpath,
		 * invalidData);
		 * 
		 * invalidData = "1"; webAppDriver.clearInputByXpath(tbCityAndStateXpath);
		 * webAppDriver.enterTextToElementByXpath(tbCityAndStateXpath, invalidData);
		 * 
		 * invalidData = "a"; webAppDriver.clearInputByXpath(tbZipContactInfoXPath);
		 * webAppDriver.enterTextToElementByXpath(tbZipContactInfoXPath,
		 * invalidData);
		 */
		invalidData = "s";
		webAppDriver.clearInputByXpath(tbPhoneXpath);
		webAppDriver.enterTextToElementByXpath(tbPhoneXpath, invalidData);

		/*
		 * invalidData = ""; webAppDriver.enterTextToElementById(tbAlternatePhoneId,
		 * invalidData);
		 */
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbAddressErrorXpath, addressError);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbCityErrorXpath, cityError);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbZipErrorXpath, zipError);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbNumberErrorXpath, phoneError);
		selfcareContactInfoActions.clickCancelContactInfo();
		selfcareContactInfoActions.clickCancelContactInfo();

		// tull here
		/*
		 * selfcareSummaryActions.clickContactInfoLink(); String oldNumber =
		 * webAppDriver.findElementById(tbPhoneId).getText();
		 * webAppDriver.enterTextToElementById(tbPhoneId, phone);
		 * selfcareSummaryActions.clickCancel();
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbPhoneId,
		 * oldNumber);
		 * 
		 * // Test with invalid and valid data for zip when united states is //
		 * selected-3.3.19 webAppDriver.selectByVisibleTextByLocatorId(ddCountryId,
		 * "United States"); invalidData = "9040";
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, invalidData);
		 * webAppDriver.verifyPresenceOfTextLocatedById(lbZipErrorId, zipError);
		 * invalidData = "90404-12";
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, invalidData);
		 * webAppDriver.verifyPresenceOfTextLocatedById(lbZipErrorId, zipError);
		 * webAppDriver.selectByVisibleTextByLocatorId(ddCountryId,
		 * "United States");
		 * 
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, zip); if
		 * (webAppDriver.verifyTextContainInAnyTag(zipError)) {
		 * Reporter.log("alpha numeric zip code is not accepted for US primary address"
		 * ); webAppDriver.captureScreenshot(); throw new AssertionError(
		 * "alpha numeric zip code is not accepted for US primary address"); }
		 * 
		 * // Test with invalid and valid data for zip when Canada is
		 * selected-3.3.19
		 * 
		 * invalidData = "M5P 2N";
		 * webAppDriver.selectByVisibleTextByLocatorId(ddCountryId, "Canada");
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, invalidData);
		 * webAppDriver.verifyPresenceOfTextLocatedById(lbZipErrorId, zipError);
		 * 
		 * String validData = "M5P 2N7";
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, validData); if
		 * (webAppDriver.verifyTextContainInAnyTag(zipError)) { Reporter.log(
		 * "alpha numeric zip code is not accepted for canada for primary address");
		 * webAppDriver.captureScreenshot(); throw new AssertionError(
		 * "alpha numeric zip code is not accepted for canada primary address"); }
		 * // added for changes made in wc1
		 * webAppDriver.enterTextToElementById(tbAddressContactInfoId, newAddress);
		 * webAppDriver.enterTextToElementById(tbCityAndStateId, newCity);
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, newZip); //
		 * webAppDriver.enterTextToElementById(tbPhoneId, newPhn);
		 * webAppDriver.selectByVisibleTextByLocatorId(ddStateId, "CA");
		 * webAppDriver.selectByVisibleTextByLocatorId(ddCountryId,
		 * "United States"); selfcareContactInfoActions.clickSaveChanges(); //
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbPhoneId, newPhn);
		 * 
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbAddressContactInfoId
		 * , newAddress);
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbCityAndStateId,
		 * newCity);
		 * webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbZipContactInfoId,
		 * newZip);
		 */

		// Changing back to the original address
		selfcareContactInfoActions.enterAddress1(address, 1);
		selfcareContactInfoActions.enterCity(cityAndState, 1);
		selfcareContactInfoActions.enterZip(zip, 1);
		selfcareContactInfoActions.SelectCountries(country, 1);
		selfcareContactInfoActions.SelectStates(state, 1);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		// webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");

		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAddress1(1), address);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCity(1), cityAndState);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getZip(1), zip);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCountry(1), "US");
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getState(1), "CA");

		// WC2 addition for email
		/*
		 * webAppDriver.enterTextToElementById(hlnkEmailToEditId, invalidEmail);
		 * webAppDriver.verifyPresenceOfTextInDivTagText(emailError);
		 */
		// WC2

		// Secondary address

		selfcareContactInfoActions.clickOnAddAlternateAddressLink();

		// Validations for secondary address
		// Blank
		selfcareContactInfoActions.enterAddress1(" ", 2);
		selfcareContactInfoActions.enterCity(" ", 2);
		selfcareContactInfoActions.enterZip(" ", 2);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		webAppDriver.verifyPresenceOfTextInDivTagText("Please provide your Address.");
		webAppDriver.verifyPresenceOfTextInDivTagText("Please provide your City");
		webAppDriver.verifyPresenceOfTextInDivTagText("Please provide your Zip/Postal Code.");

		// invalid data
		invalidData = " ";
		selfcareContactInfoActions.enterAddress1(invalidData, 2);
		invalidData = "1";
		selfcareContactInfoActions.enterCity(invalidData, 2);
		invalidData = "a";
		selfcareContactInfoActions.enterZip(invalidData, 2);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbAddressError2Xpath, addressError);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbCityError2Xpath, cityError);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbZipError2Xpath, zipError);
		selfcareContactInfoActions.clickCancelContactInfo();
		selfcareContactInfoActions.clickCancelContactInfo();

		/*
		 * webAppDriver.verifyPresenceOfTextInDivTagText(
		 * "No Numbers/Special Characters in City");
		 * webAppDriver.verifyPresenceOfTextInDivTagText("Invalid Postal Code");
		 */

		// Enter valid secondary address


		selfcareContactInfoActions.clickOnAddAlternateAddressLink();
		selfcareContactInfoActions.enterAddress1(newAddress, 2);
		selfcareContactInfoActions.enterCity(newCity, 2);
		selfcareContactInfoActions.enterZip(newZip, 2);
		selfcareContactInfoActions.SelectCountries(newCountry, 2);
		selfcareContactInfoActions.SelectStates(newState, 2);

		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");

		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAddress1(2), newAddress);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCity(2), newCity);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getZip(2), newZip);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCountry(2), "US");
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getState(2), "CA");

		// Test with invalid and valid data for secondary address for zip when
		// Canada is selected-3.3.19

		/*
		 * invalidData = "M5P 2N";
		 * webAppDriver.selectByVisibleTextByXpath(ddSecondaryCountryXpath,
		 * "Canada"); selfcareContactInfoActions.enterSecondaryAddress(invalidData);
		 * webAppDriver.verifyPresenceOfTextLocatedById(lbSecondaryZipErrorId,
		 * zipError);
		 * 
		 * invalidData = "M5P 2N7";
		 * webAppDriver.enterTextToElementById(tbZipContactInfoId, invalidData); if
		 * (webAppDriver.verifyTextContainInAnyTag(zipError)) { Reporter.log(
		 * "alpha numeric zip code is not accepted for canada for secondary address"
		 * ); webAppDriver.captureScreenshot(); throw new AssertionError(
		 * "alpha numeric zip code is not accepted for canada for secondary address"
		 * ); }
		 */

		// To verify the primary and secondary addresses interchange when make
		// primary chkbx is checked scenario #5
		/*
		 * selfcareContactInfoActions.enterSecondaryAddress(secondaryAddress);
		 * selfcareContactInfoActions.enterSecondaryCity(secondaryCityAndState);
		 * selfcareContactInfoActions.enterSecondaryZip(secondaryZip);
		 * selfcareContactInfoActions.enterSecondaryAptSuite("");
		 * selfcareContactInfoActions.selectSecondaryState(secondaryState);
		 * selfcareContactInfoActions.selectSecondaryCountry(secondaryCountry);
		 */
		selfcareContactInfoActions.clickOnMakePrimaryAddressLink();
	//	selfcareContactInfoActions.clickOnMakePrimaryAddressLink();

		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.relax(500);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAddress1(1), secondaryAddress);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCity(1), secondaryCityAndState);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getZip(1), secondaryZip);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getState(1), "CA");
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAddress1(2), address);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCity(2), cityAndState);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getZip(2), zip);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getState(2), "CA");

		// Changing back to the original address

		selfcareContactInfoActions.clickOnMakePrimaryAddressLink();
		
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAddress1(1), address);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCity(1), cityAndState);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getZip(1), zip);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getState(1), "CA");
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAddress1(2), secondaryAddress);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getCity(2), secondaryCityAndState);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getZip(2), secondaryZip);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getState(2), "CA");

		// To verify the Remove secondary address functionality

		selfcareContactInfoActions.clickOnRemovePrimaryAddressLink();
		

		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		
		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");
	
	}

	// To verify the contact info phone functionality WC2 Account Experience
	@Test(priority=5)
	public void contactInfoFunctionalityOnPersonalInfoTabPhoneTest() {
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();

		String mobileError = "tbd";
		String workError = "tbd";
		String otherError = "tbd";
		String homeError = "tbd";
		// To verify Add 5 phone numbers functionality scenario #5
		String mobile = "7867678734";
		String work = "4567891230";
		String workExtention = "8901";
		String other = "5678901234";
		String home = "2345678901";

		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);

		// validations
		// no phone numbers
		String invalid = "";
		selfcareContactInfoActions.selectMobilePhone(invalid, false, false);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectWorkPhone(invalid, "");
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectOtherPhone(invalid);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectHomePhone(invalid);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath1);
		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath2);
		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath3);
		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath4);

		// invalid phone number
		invalid = "  ";

		selfcareContactInfoActions.selectMobilePhone(invalid, false, true);
		invalid = "abc";

		selfcareContactInfoActions.selectWorkPhone(invalid, workExtention);
		invalid = ",,,,134";
		selfcareContactInfoActions.selectOtherPhone(invalid);
		invalid = "@@@1123";
		selfcareContactInfoActions.selectHomePhone(invalid);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyElementPresentByXpath(lbPhoneInvalidErrorXpath1);
		webAppDriver.verifyElementPresentByXpath(lbPhoneInvalidErrorXpath2);
		webAppDriver.verifyElementPresentByXpath(lbPhoneInvalidErrorXpath3);
		webAppDriver.verifyElementPresentByXpath(lbPhoneInvalidErrorXpath4);

		webAppDriver.navigate().refresh();
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectMobilePhone(mobile, false, false);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectWorkPhone(work, workExtention);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectOtherPhone(other);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);

		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(false);
		selfcareContactInfoActions.selectHomePhone(home);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");

		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getMobilePhoneText(), mobile);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getWorkPhoneText(), work);
		// webAppDriver.verifyEqualsString(selfcareContactInfoActions.getExtNumber(3),workExtention);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getOtherPhoneText(), other);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getHomePhoneText(), home);

		// Remove link functionality
		
				
		selfcareContactInfoActions.clickRemovePhoneLink(5);
		webAppDriver.verifyElementNotPresntByXpath(ddFifthPhoneXpath);		
		selfcareContactInfoActions.clickRemovePhoneLink(4);
		webAppDriver.verifyElementNotPresntByXpath(ddFourthPhoneXpath);		
		selfcareContactInfoActions.clickRemovePhoneLink(3);
		webAppDriver.verifyElementNotPresntByXpath(ddThirdPhoneXpath);
		selfcareContactInfoActions.clickRemovePhoneLink(2);
		webAppDriver.verifyElementNotPresntByXpath(ddSecondPhoneXpath);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");

		if (!webAppDriver.verifyElementNotPresntByXpath("(//input[@name='phonespreferred'][@type='radio'])[2]")) {
			webAppDriver.captureScreenshot();
			Reporter.log("(//input[@name='phonespreferred'][@type='radio'])[2]  is present");
			throw new AssertionError("//input[@name='phonespreferred'][@type='radio'])[2]  is present");
		}

	}

	// To verify the contact info phone functionality for WC1 experience
	// Others option should not be displayed in phone drop down
	// preferred numbers drop down should not be displayed
	// Mobile Text message Ok checkbox should not be displayed
	// //@Test
	public void contactInfoFunctionalityOnPersonalInfoTabPhoneForWC1Test() {
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();

		String mobileError = "tbd";
		String workError = "tbd";
		String homeError = "tbd";
		// To verify Add 5 phone numbers functionality scenario #5
		String mobile = "7867678734";
		String work = "4567891230";
		String workExtention = "8901";
		String other = "5678901234";
		String home = "2345678901";
		String finalXpath = "";

		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(true);

		// To Check Others option is not displayed

		List<String> allValuesPhoneDropDown = selfcareContactInfoActions.getPhoneDropdownAllValues();
		for (String values : allValuesPhoneDropDown) {
			if (values.equalsIgnoreCase("other")) {
				Reporter.log("WC1 phone drop down showns OTHER as value");
				webAppDriver.captureScreenshot();
				throw new AssertionError("WC1 phone drop down shows OTHER as value");

			}
		}

		// validations
		String invalid = "";

		selfcareContactInfoActions.selectMobilePhone(invalid, true, false);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(true);
		selfcareContactInfoActions.selectWorkPhone(invalid, "");
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(true);
		selfcareContactInfoActions.selectPhoneType("Home", 4);
		selfcareContactInfoActions.enterPhoneNumber(invalid, 4);
		selfcareContactInfoActions.clickOnAddAdditionalPhoneNumbersLink(true);
		selfcareContactInfoActions.selectHomePhone(invalid);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath1);
		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath2);
		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath3);
		webAppDriver.verifyElementPresentByXpath(lbPhoneRequiredErrorXpath4);

		selfcareContactInfoActions.selectMobilePhone(mobile, true, false);

		selfcareContactInfoActions.selectWorkPhone(work, workExtention);
		selfcareContactInfoActions.selectPhoneType("Home", 4);
		selfcareContactInfoActions.enterPhoneNumber(other, 4);
		selfcareContactInfoActions.selectHomePhone(home);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		// to verify preferred preferred phone numbers are not present for wc1
		// accounts
		webAppDriver.verifyElementPresentByXpath(lbPreferredNumberWC1Xpath);
		finalXpath = MessageFormat.format(rbtnAllPreferredNumberWC1Xpath, 1);
		webAppDriver.verifyElementPresentByXpath(finalXpath);
		finalXpath = MessageFormat.format(rbtnAllPreferredNumberWC1Xpath, 2);
		webAppDriver.verifyElementPresentByXpath(finalXpath);
		finalXpath = MessageFormat.format(rbtnAllPreferredNumberWC1Xpath, 3);
		webAppDriver.verifyElementPresentByXpath(finalXpath);

		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getMobilePhoneText(), mobile);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getWorkPhoneText(), work);
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getHomePhoneText(), home);

		// Remove link functionality
		selfcareContactInfoActions.clickRemovePhoneLink(2);
		webAppDriver.verifyElementNotPresntByXpath(ddSecondPhoneXpath);
		selfcareContactInfoActions.clickRemovePhoneLink(3);
		webAppDriver.verifyElementNotPresntByXpath(ddThirdPhoneXpath);
		selfcareContactInfoActions.clickRemovePhoneLink(4);
		webAppDriver.verifyElementNotPresntByXpath(ddFourthPhoneXpath);
		selfcareContactInfoActions.clickRemovePhoneLink(5);
		webAppDriver.verifyElementNotPresntByXpath(ddFifthPhoneXpath);

	}

	/**
	 * PS-169:TC-Selfcare-1.5.1-Verify view and functionality of ‘Contact Info’
	 * page when sub tab ‘Username, Email & Password’ is selected
	 */
	 @Test(description = "")
	public void contactInfoFunctionalityOnLoginDetailsTabPasswordValidations() {

		String shortPassword = "test";
		String strongpassword = "Testpassword@123";

		// WC2 removed email address info from here and added to personal info tab
		// String invalidEmail = "1\n";
		// String emailError = "Invalid email format.";
		// String blankEmailError = "Please provide your email address.";
		navigator.gotoContactInfoLoginDetailsTab();
		// WC2
		//webAppDriver.verifyElementNotPresntByLinkText("Why can't I edit my name?");
		/*if (webAppDriver.verifyTextContainInAnyTag("Email address")) {
			Reporter.log("Email address field present in Login Details tab");
			webAppDriver.captureScreenshot();
			throw new AssertionError("Email address field present in Login Details tab");
		}*/
		// till here

		// webAppDriver.checkAllElementsHavingTextByXpath(lbUsernameXpath,
		// username);

		// WC2 added
		// Password validations
		// No passwords

		selfcareContactInfoActions.clickOnPasswordEditLink();
		selfcareContactInfoActions.enterCurrentPassword("");
		selfcareContactInfoActions.enterPassword("");
		selfcareContactInfoActions.enterConfrimPassword("");
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyElementPresentByXpath(lbErrorCurrentPasswordRequired);
		webAppDriver.verifyElementPresentByXpath(lbErrorNewPasswordRequired);
		webAppDriver.verifyElementPresentByXpath(lbErrorNewConfirmPasswordRequired);

		// short password
		selfcareContactInfoActions.enterCurrentPassword(oldPassword);
		selfcareContactInfoActions.enterPassword(shortPassword);
		selfcareContactInfoActions.enterConfrimPassword(shortPassword);
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyElementPresentByXpath(lbErrorNewPassword7CharactersXpath);
		webAppDriver.verifyElementPresentByXpath(lbErrorNewConfirmPassword7CharactersXpath);

		// password mismatch
		selfcareContactInfoActions.enterPassword(strongpassword);
		selfcareContactInfoActions.enterConfrimPassword(oldPassword);
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyElementPresentByXpath(lbErrorPasswordNotMatchXpath);

		// WC2 Remove

		// WC2 removed email address info from here and added to personal info tab
		/*
		 * webAppDriver.enterTextToElementById(hlnkEmailToEditId, invalidEmail);
		 * webAppDriver.verifyPresenceOfTextInDivTagText(emailError);
		 */

		// selfcareSummaryActions.clickCancel();

		// WC2

	}

	// To test the functionality of the password strength
	 @Test(priority=6)
	public void contactInfoLoginDetailsPasswordStrengthMeterTest() {
		String weakPassword = "testpassword";
		String normalPassword = "testpassword1";
		String mediumPassword = "testpassword123";
		String goodPassword = "testpassword@123";
		String strongpassword = "Testpassword@123";
		navigator.gotoContactInfoLoginDetailsTab();
		selfcareContactInfoActions.clickOnPasswordEditLink();
		selfcareContactInfoActions.enterCurrentPassword(oldPassword);
		selfcareContactInfoActions.enterPassword(weakPassword);
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthBarWeak);
		selfcareContactInfoActions.enterPassword(normalPassword);
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthBarNormal);
		selfcareContactInfoActions.enterPassword(strongpassword);
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthBarStrong);
		selfcareContactInfoActions.enterPassword(weakPassword);
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthBarWeak);
		selfcareSummaryActions.clickCancel();
		webAppDriver.verifyElementPresentById(hlinkPasswordEditLinkId);

	}

	// To test the successful change of password
	@Test(priority=11)
	public void contactInfoLoginDetailsPasswordUpdateTest() {
		String oldPassword = "test123";
		String normalPassword = "testpassword1";
		navigator.gotoContactInfoLoginDetailsTab();
		selfcareContactInfoActions.clickOnPasswordEditLink();
		// valid password
		selfcareContactInfoActions.enterCurrentPassword(oldPassword);
		selfcareContactInfoActions.enterPassword(normalPassword);
		selfcareContactInfoActions.enterConfrimPassword(normalPassword);
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyDivTagTextEquals("Password updated.");
		loginActions.clickOnLogOut();
		navigator.gotoMyAccountLoggedIn(username, normalPassword);
		navigator.gotoContactInfoLoginDetailsTab();
		selfcareContactInfoActions.clickOnPasswordEditLink();

		selfcareContactInfoActions.enterCurrentPassword(normalPassword);
		selfcareContactInfoActions.enterPassword(oldPassword);
		selfcareContactInfoActions.enterConfrimPassword(oldPassword);
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyDivTagTextEquals("Password updated.");

	}

	// WC2-unit testig done
 @Test(description = "To test the new username functionality",priority=12)
	public void contactInfoUsernameTabNewUsernameFunctionalityTest() {


		String newUsername = "wc2UserProdDecDeskNew-1";

		// navigator.gotoMyAccountLoggedIn(username, "testpassword3"); 
		navigator.gotoContactInfoLoginDetailsTab();
		selfcareContactInfoActions.clickOnUsernameEditLink();
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		// TBD as it is not clear how the errors will get displayed.
		webAppDriver.verifyElementPresentByXpath(lbUsernameRequiredErrorXpath);
		webAppDriver.verifyElementPresentByXpath(lbConfirmUsernameRequiredErrorXpath);
		selfcareContactInfoActions.enterNewUsername(newUsername);
		selfcareContactInfoActions.enterNewConfirmUsername(newUsername);
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyDivTagTextEquals("Username updated.");
		if (!webAppDriver.verifyTextContainInAnyTag(newUsername)) {
			Reporter.log("new username not saved in Login Details tab");
			webAppDriver.captureScreenshot();
			throw new AssertionError("new username not saved in Login Details tab");
		}
		loginActions.clickOnLogOut();
		navigator.gotoMyAccountLoggedIn(newUsername,"test123");
		navigator.gotoContactInfoLoginDetailsTab();
		// to change to the original username
		selfcareContactInfoActions.clickOnUsernameEditLink();
		selfcareContactInfoActions.enterNewUsername(username);
		selfcareContactInfoActions.enterNewConfirmUsername(username);
		selfcareContactInfoActions.clickSaveChangesLoginDetails();
		webAppDriver.verifyDivTagTextEquals("Username updated.");

	}

	public List<String> getAternateEmailAddress() {
		List<String> alternateEmail = new ArrayList<String>();
		for (int i = 1; i <= 4; i++) {
			alternateEmail.add(new UniqueId(new UniqueId().id).charId + i+"@gmail.com");
		}
		return alternateEmail;

	}

	@Test(description = "To test the alternate emails functionality",priority=7)
	public void contactInfoUsernameTabAlternateEmailTest() {
		List<String> alternateEmailAdd = new ArrayList<String>();
		String invalidEmail = "1\n";
		String emailError = "Invalid email format.";
		String blankEmailError = "Please provide your email address.";
		alternateEmailAdd = getAternateEmailAddress();
		String finalXpath="";

		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();

		String oldEmail = webAppDriver.findElementByXpath(lbEmailAddressXcode).getText();

		selfcareSummaryActions.clickEdit();
		finalXpath=MessageFormat.format(tbAllEmailAddressXpath, 1);
		webAppDriver.verifyElementPresentByXpath(finalXpath);
		finalXpath=MessageFormat.format(tbConfirmEmailAddressXpath, 1);
		webAppDriver.verifyElementPresentByXpath(finalXpath);
    finalXpath=MessageFormat.format(tbAllEmailAddressXpath, 2);

		if(!webAppDriver.verifyElementIsPresentByXpath(finalXpath))
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		
		/*selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();*/
		webAppDriver.relax(500);
		while(!webAppDriver.verifyElementIsPresentByXpath("//a[text()='+ Add additional email'][@style='display: none;']")){
			selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		}

		

		selfcareContactInfoActions.enterEmailAddress(invalidEmail,2);
		selfcareContactInfoActions.enterEmailAddress(invalidEmail,3);
		selfcareContactInfoActions.enterEmailAddress(invalidEmail,4);
		selfcareContactInfoActions.enterEmailAddress(invalidEmail,5);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbInvalidEmailErrorXpath, 4);

		selfcareContactInfoActions.enterEmailAddress("",2);
		selfcareContactInfoActions.enterEmailAddress("",3);
		selfcareContactInfoActions.enterEmailAddress("",4);
		selfcareContactInfoActions.enterEmailAddress("",5);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		
		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");

		// no error since these are not mandatory
		selfcareSummaryActions.clickEdit();
     finalXpath=MessageFormat.format(tbAllEmailAddressXpath, 2);
		if(!webAppDriver.verifyElementIsPresentByXpath(finalXpath))
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();

		selfcareContactInfoActions.enterEmailAddress(alternateEmailAdd.get(0),2);
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		
		selfcareContactInfoActions.enterEmailAddress(alternateEmailAdd.get(1),3);
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		selfcareContactInfoActions.enterEmailAddress(alternateEmailAdd.get(2),4);
		selfcareContactInfoActions.clickOnAddAdditionalEmailAddressLink();
		selfcareContactInfoActions.enterEmailAddress(alternateEmailAdd.get(3),5);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		selfcareContactInfoActions.clickSaveChangesContactInfo();
		
		if(webAppDriver.verifyElementIsPresentByXpath("//span[text()='Sign In to Your Account']")){
			loginActions.enterUserName(username);
			loginActions.enterUserName(oldPassword);
			loginActions.clickOnLogin();
		}

		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");

		

		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAllEmailAddress(2), alternateEmailAdd.get(0));
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAllEmailAddress(3), alternateEmailAdd.get(1));
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAllEmailAddress(4), alternateEmailAdd.get(2));
		webAppDriver.verifyEqualsString(selfcareContactInfoActions.getAllEmailAddress(5), alternateEmailAdd.get(3));

		
		
		// check edit functionality
		// Removed this functionality

		selfcareSummaryActions.clickEdit();

		selfcareContactInfoActions.clickOnPrefferedEmail(2);

		selfcareContactInfoActions.enterConfirmEmailAddress(alternateEmailAdd.get(0), 2);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		//selfcareContactInfoActions.clickSaveChangesContactInfo();

		navigator.gotoMyAccountLoggedIn();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailCss, alternateEmailAdd.get(0));
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();
		selfcareSummaryActions.clickEdit();
		selfcareContactInfoActions.clickOnPrefferedEmail(3);

		selfcareContactInfoActions.enterConfirmEmailAddress(alternateEmailAdd.get(2),3);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		//selfcareContactInfoActions.clickSaveChangesContactInfo();
		navigator.gotoMyAccountLoggedIn();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailCss, alternateEmailAdd.get(2));
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();
		selfcareSummaryActions.clickEdit();
		selfcareContactInfoActions.clickOnPrefferedEmail(4);
	
		selfcareContactInfoActions.enterConfirmEmailAddress(oldEmail,4);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		//selfcareContactInfoActions.clickSaveChangesContactInfo();

		navigator.gotoMyAccountLoggedIn();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailCss, oldEmail);
	/*	selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();
		selfcareSummaryActions.clickEdit();
		selfcareContactInfoActions.clickOnPrefferedEmail(5);
		webAppDriver.relax(500);
		selfcareContactInfoActions.enterConfirmEmailAddress(alternateEmailAdd.get(3),5);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		navigator.gotoMyAccountLoggedIn();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailCss, alternateEmailAdd.get(3));
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();
		selfcareContactInfoActions.clickOnPrefferedEmail(1);
		selfcareContactInfoActions.enterConfirmEmailAddress(oldEmail,1);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		selfcareContactInfoActions.clickSaveChangesContactInfo();

		
		navigator.gotoMyAccountLoggedIn();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbEmailCss, oldEmail);*/

		// Remove functionality
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickPersonalInfoLink();
		
		selfcareSummaryActions.clickEdit();


		/* alternateEmailAdd = getAternateEmailAddress(); */
		selfcareContactInfoActions.clickRemoveEmailLink(5);
		selfcareContactInfoActions.clickRemoveEmailLink(4);
		selfcareContactInfoActions.clickRemoveEmailLink(3);
		selfcareContactInfoActions.clickRemoveEmailLink(2);
		
		//selfcareContactInfoActions.clickSaveChangesContactInfo();

		selfcareContactInfoActions.clickSaveChangesContactInfo();

		webAppDriver.verifyPTagTextContains("Address & Personal Info Updated");
		
		if(!webAppDriver.verifyElementNotPresntByXpath(hlinkRemoveEmail2LinkXpath))
				{
			 webAppDriver.captureScreenshot();
			 Reporter.log("remove email link present "+hlinkRemoveEmail2LinkXpath);
			 throw new AssertionError("remove email link present "+hlinkRemoveEmail2LinkXpath);
				}


	/*	webAppDriver.verifyElementNotPresntByXpath(tbAlternate1EmailXpath);
		webAppDriver.verifyElementNotPresntByXpath(tbAlternate2EmailXpath);
		webAppDriver.verifyElementNotPresntByXpath(tbAlternate3EmailXpath);
		webAppDriver.verifyElementNotPresntByXpath(tbAlternate4EmailXpath);*/
		/*
		 * selfcareContactInfoActions.enterAlternateEmail_1(alternateEmailAdd.get(0))
		 * ;
		 * selfcareContactInfoActions.enterAlternateEmail_2(alternateEmailAdd.get(1
		 * ));
		 * selfcareContactInfoActions.enterAlternateEmail_3(alternateEmailAdd.get
		 * (2));
		 * selfcareContactInfoActions.enterAlternateEmail_4(alternateEmailAdd.get
		 * (3)); selfcareContactInfoActions.clickSaveChanges();
		 * webAppDriver.verifyEqualsString
		 * (selfcareContactInfoActions.getAlternateEmail_1(),
		 * alternateEmailAdd.get(0));
		 * webAppDriver.verifyEqualsString(selfcareContactInfoActions
		 * .getAlternateEmail_2(), alternateEmailAdd.get(1));
		 * webAppDriver.verifyEqualsString
		 * (selfcareContactInfoActions.getAlternateEmail_3(),
		 * alternateEmailAdd.get(2));
		 * webAppDriver.verifyEqualsString(selfcareContactInfoActions
		 * .getAlternateEmail_4(), alternateEmailAdd.get(3));
		 * 
		 * // to clear all the alternate email addresses
		 * selfcareContactInfoActions.clickOnAlternateEmail_1_Removeink();
		 * selfcareContactInfoActions.clickOnAlternateEmail_2_RemoveLink();
		 * selfcareContactInfoActions.clickOnAlternateEmail_3_RemoveLink();
		 * selfcareContactInfoActions.clickOnAlternateEmail_4_RemoveLink();
		 * selfcareContactInfoActions.enterAlternateEmail_1("");
		 * selfcareContactInfoActions.enterAlternateEmail_2("");
		 * selfcareContactInfoActions.enterAlternateEmail_3("");
		 * selfcareContactInfoActions.enterAlternateEmail_4("");
		 * selfcareContactInfoActions.clickSaveChanges();
		 * webAppDriver.verifyElementPresentByLinkText(hlinkAdd5EmailAddressLink);
		 */

	}

	/**
	 * PS-170:TC-Selfcare-1.6.1-Verify view and functionality of ‘Contact Info’
	 * page when sub tab ‘Notifications’ is selected page when sub tab
	 * ‘Notification’ is selected
	 */
	@Test(priority=8)
	public void contactInfoFunctionalityOnNotificationTest() {
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickOnNotificationSubTab();
		selfcareSummaryActions.selectRadioButton(rdbtnPaymentReceiptsName, true);
		selfcareSummaryActions.selectRadioButton(rdbtnMonthyInvoicesName, false);
		selfcareSummaryActions.selectRadioButton(rdbtnSpecialOfferesName, true);
		webAppDriver.clickElementByLinkText(lbCancle);
		webAppDriver.verifyElementPresentByXpath(btnSaveChanges1Xpath);
		selfcareSummaryActions.selectRadioButton(rdbtnPaymentReceiptsName, true);
		selfcareSummaryActions.selectRadioButton(rdbtnMonthyInvoicesName, false);
		selfcareSummaryActions.selectRadioButton(rdbtnSpecialOfferesName, true);
		webAppDriver.clickElementByXpath(btnSaveChanges1Xpath);
		webAppDriver.verifyElementPresentByXpath(btnSaveChanges1Xpath);
		selfcareSummaryActions.verifyRadioButtonChecked(rdbtnPaymentReceiptsName, true);
		selfcareSummaryActions.verifyRadioButtonChecked(rdbtnMonthyInvoicesName, false);
		selfcareSummaryActions.verifyRadioButtonChecked(rdbtnSpecialOfferesName, true);

	}

	/**
	 * PS-171:TC-Selfcare-1.7.1-Verify view and functionality of ‘Contact Info’
	 * page when sub tab 'Emergency contact & Authorised User' is selected page
	 * when sub tab ‘Emergency Contact & Authorised User’ is selected
	 */
//@Test
	public void authorisedAccessTest() {
		LinkedHashMap<String, String> authorisedAccess = new LinkedHashMap<String, String>();
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickOnEmergencyContact();
		webAppDriver.clickElementByXpath(hlnkUnitXpath);
		webAppDriver.verifyDivTagTextContains("You may authorize access for up to 5 people.");
		webAppDriver.clickElementByXpath(hlnkAuthorizedAccessEditXpath);
		authorisedAccess = selfcareSummaryActions.getAuthorisedAccess();
		webAppDriver.clickElementByLinkText(lbCancle);
		selfcareSummaryActions.verifyAuthorisedUsers(authorisedAccess);
		webAppDriver.clickElementByXpath(hlnkAuthorizedAccessEditXpath);
		authorisedAccess = selfcareSummaryActions.enterValuesForAuthorisedUser();
		webAppDriver.clickElementByXpath(hlnkUnitXpath);
		selfcareSummaryActions.verifyAuthorisedUsers(authorisedAccess);

	}

	/**
	 * PS-172:TC-Selfcare-1.8.1-Verify view and functionalities available on
	 * payments page
	 */
	@Test(priority=9)
	public void verifyFunctionalityAvailableOnPaymentPageTest() {
		navigator.gotoPayments();
		webAppDriver.clickElementByLinkText("View full history");
		webAppDriver.verifyElementPresentByLinkText("View details");
		webAppDriver.clickElementByLinkText("Back");

		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		// paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.makePaymentWithCreditCard(creditCardNumber, "226","6797", true,true);
		paymentActions.verifyCreditCardAdded(creditCardNumber);
		navigator.gotoPayments();
		selfcareSummaryActions.clickManagePaymentMethod();
		// webAppDriver.clickElementByLinkText("Manage payment methods");
		webAppDriver.verifyElementPresentByLinkText("Add payment method");
		webAppDriver.clickElementByLinkText("Edit");
		webAppDriver.verifyElementPresentById(drpDwnExpMonthId);
		webAppDriver.verifyElementPresentById(drpDwnExpYearId);
		webAppDriver.clickElementByLinkText("Back");
		webAppDriver.clickElementByLinkText("Back");
		selfcareSummaryActions.verifyPaymentDetails();

	}

	/**
	 * PS-173:TC-Selfcare-1.9.1-Verify view and functionalities available on My
	 * Storage Units page (User has already set move out date)
	 */
	@Test(priority = 10)
	public void verifyMyStorageUnitsTest() {
		String scMoveOutUser = envTestData.get("scMoveOutUser");
		String scMoveOutPassword = envTestData.get("scMoveOutPassword");
		navigator.gotoMyAccountPage(scMoveOutUser, scMoveOutPassword);
		navigator.gotoMyStorageUnits();
		webAppDriver.clickElementByXpath(hlinkChangeMoveOutDateXpath);
		selfcareSummaryActions.changeCalenderDate();
		navigator.gotoMyStorageUnits();
	//	selfcareSummaryActions.verifyViewFullDetails();

	}

	/**
	 * PS-173:TC-Selfcare-1.11.1-Verify view and functionalities available on My
	 * Storage Units page (User has not set move out date)
	 */
	//@Test(priority = 4)
	public void verifyMyStorageUnitsNoMoveOutDateTest() {
		String scUserForMelissa = envTestData.get("UserForMelissa");
		String scPasswordForMelissa = envTestData.get("PasswordForMelissa");
		navigator.gotoMyStorageUnits(scUserForMelissa, scPasswordForMelissa);
		selfcareSummaryActions.changeCalenderDate();

		/*
		 * webAppDriver.clickElementByXpath(lbChangeXpath);
		 * webAppDriver.verifyPresenceOfTextInPTagText ("Schedule a Move-Out Date");
		 * selfcareSummaryActions.selectMoveOutDateAndClickCancleButton();
		 * selfcareSummaryActions.selectMoveOutDate();
		 * navigator.gotoMyStorageUnits();
		 */

	}

	//@Test
	public void bug2408Test() {
		String scUserForMelissa = envTestData.get("UserForMelissa");
		String scPasswordForMelissa = envTestData.get("PasswordForMelissa");
		navigator.gotoMyStorageUnits(scUserForMelissa, scPasswordForMelissa);
		selfcareSummaryActions.changeCalenderDate();
		selfCareMyStorageUnitsActions.clickBackOnScheduleMoveOutDate();
		webAppDriver.verifyPresenceOfTextInPTagText("Schedule a Move-Out");
	}

	// E. Self-Care
	/*
	 * 1. Log in to Self-Care. 2. Wait 10 minutes until you are logged out. 3.
	 * Attempt to use your browser's back button to go back to Self-Care. You
	 * should not be able to.
	 */

	// //@Test(priority = 5)
	public void verifyAutoLogoutSelfcareTest() {
		navigator.gotoSelfCareContactInfo(envTestData.get("userForSelfCare"), envTestData.get("passwordForSelfCare"));
		webAppDriver.relax(960000);
		webAppDriver.navigate().back();
		webAppDriver.verifyPresenceOfTextInSpanTag("Sign In to Your Account");

	}

	// //@Test
	public void verifyInsuranceButtonIsNotVisibleForDTMCustomerTest() {
		String dtmUsername = "godboldsclean@gmail.com";
		String dtmPassword = "test123";
		navigator.gotoMyStorageUnits(dtmUsername, dtmPassword);
		webAppDriver.verifyElementIsInvisibleByXpath(btnClickHereInsuranceXpath);
	}

	// WC2 Addition
	// use case 3.2.2
	// FRn# 3.3.8 , 3.3.9 ,3.3.12

	// //@Test

	public void verifyMyRequestSectionTest() {

		navigator.gotoMyAccountLoggedIn(selfCareMyRequestUser, "test123");
		selfcareSummaryActions.clickMyAccount();

		/*
		 * if(!webAppDriver.verifyTextContainInAnyTag("My Request")){
		 * webAppDriver.captureScreenshot();
		 * Reporter.log("My Request section not present"); throw new
		 * AssertionError("My Request section not present "); }
		 */
		webAppDriver.verifyDivTagTextContains("My Request");

		navigator.gotoMyAccountLoggedIn(envTestData.get("userForSelfCare"), "test123");
		selfcareSummaryActions.clickMyAccount();
		if (webAppDriver.verifyTextContainInAnyTag("My Request")) {
			webAppDriver.captureScreenshot();
			Reporter.log("My Request section not present");
			throw new AssertionError("My Request section not present ");
		}
	}

}
