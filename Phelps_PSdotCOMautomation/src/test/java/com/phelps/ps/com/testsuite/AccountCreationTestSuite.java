package com.phelps.ps.com.testsuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.phelps.ps.com.locators.AccountCreationLocators;
import com.phelps.ps.com.locators.CommonLocators;

public class AccountCreationTestSuite extends BasicTestSuite implements AccountCreationLocators, CommonLocators {

	/**
	 * @param args
	 */

	String email = "pdebevoise@mindspring.com";
	String firstName = "Phillip";
	String lastName = "Debevoise";
	String accountNumber = "26406726";

	// Removing unit info verification as per the new BRD
	/*
	 * String streetAddress = ""; String cityName = ""; String stateName = "";
	 * String zipCode = ""; String un	itNumber = "";
	 */
	String errorEmail = "Email Address is required.";
	String errorAccount = "Account Number is required.";
	String errorInvalidEmail = "Please enter a valid email address.";
	String errorInvalidUsername = "Username must be 7 or more characters.";
	String errorInvalidPassword = "Username must be 7 or more characters.";
	String errorUsernameRequired = "Please enter a Username.";
	String errorPasswordRequired = "Please enter a Password.";
	String errorConfPasswordRequired = "";
	String errorPasswordNotmatch = "Password and Confirm Password do not match.";
	String errorUsernameExists = "This username is already taken. Please try a different one.";
	String accountAlreadyExists="Already have an Online Account?";
	//String errorUsernameExists = "This username already taken. Please try a different one.";
	String existingUsername = "woodlumhoodlum";
	String password = "test123";
	String userName = "WC1PsstageDeskUser1";
	
	String accountCreationmessage = "Congratulations, you've created a Username and Password!";
	Map<String, Boolean> isTextPresent;
	StringBuilder failedText;
	static boolean allTextPresent;
	String existingAccountEmail = "muzfera.naaz@phelpsagency.com";
	String existingAccountNumber = "23378627";

	String streetAddress = "23811 Ventura Blvd";
	String cityName = "Calabasas";
	String stateName = "CA";
	String zipCode = "91302-1443";
	String unitNumber = "";

	// To verify the Create Account:Reservation Look Up functionalities
	@Test(priority = 0)
	public void verifyReservationLookUpTest() {
		failedText = new StringBuilder();
		allTextPresent = true;
		isTextPresent = new HashMap<String, Boolean>();
		Iterator<Map.Entry<String, Boolean>> iterator ;
		// webAppDriver.get(baseUrl + accountCreationEmailedLinkURL);
		navigator.gotoCreateAccountReservationLookUpPage();
		isTextPresent.put(lbCreateAccountText, webAppDriver.verifyTextContainInAnyTag(lbCreateAccountText));
		isTextPresent.put(accountAlreadyExists, webAppDriver.verifyTextContainInAnyTag(accountAlreadyExists));

		// Added new copy change on reservation look up on 03-June-2016

		webAppDriver.verifyElementPresentByXpath(lbReservationLookUpCopy1Xpath);
	webAppDriver.verifyElementPresentByXpath(lbReservationLookUpCopy2Xpath);		
		webAppDriver.verifyElementPresentByXpath(lbReservationLookUpCopy5Xpath);
		webAppDriver.verifyPresenceOfTextInLiTag("Make payments");
		webAppDriver.verifyPresenceOfTextInLiTag("Access gate code & location details");
		webAppDriver.verifyPresenceOfTextInLiTag("Manage AutoPay");
		webAppDriver.verifyPresenceOfTextInLiTag("Update your contact information");
		webAppDriver.verifyPresenceOfTextInLiTag("Purchase/manage insurance");
		webAppDriver.verifyPresenceOfTextInLiTag("Manage your email notifications");

		//Verify functionality of click here to login 
		webAppDriver.clickElementByLinkText("Click here to login");
		webAppDriver.verifyElementPresentById("loginname");
		
		
		navigator.gotoCreateAccountReservationLookUpPage();
		// Validations
		String invalid = "";
		accountCreationActions.enterEmailOnReservationLookUp(invalid);
		invalid = "";
		accountCreationActions.enterAccountNoReservationLookUp(invalid);
		accountCreationActions.clickOnCreateAccountSubmit();
		isTextPresent.put(errorEmail, webAppDriver.verifyTextContainInAnyTag(errorEmail));
		isTextPresent.put(errorAccount, webAppDriver.verifyTextContainInAnyTag(errorAccount));

		invalid = "test";
		accountCreationActions.enterEmailOnReservationLookUp(invalid);
		accountCreationActions.clickOnCreateAccountSubmit();
		isTextPresent.put(errorInvalidEmail, webAppDriver.verifyTextContainInAnyTag(errorInvalidEmail));

		accountCreationActions.enterEmailOnReservationLookUp(existingAccountEmail);
		accountCreationActions.enterAccountNoReservationLookUp(existingAccountNumber);
		accountCreationActions.clickOnCreateAccountSubmit();
		webAppDriver.verifyElementPresentByXpath(lbOnlineAccountExistErrorXpath);
		
 iterator = isTextPresent.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Boolean> entry = iterator.next();
			if (!entry.getValue()) {
				allTextPresent = false;
				failedText.append(entry.getKey());
				failedText.append(System.lineSeparator());
			}
		}

		if (!allTextPresent) {

			webAppDriver.captureScreenshot();
			Reporter.log(failedText.toString() + " not displayed on the account creation screen");
			throw new AssertionError(failedText.toString() + " not displayed on the account creation screen");
		}
		accountCreationActions.enterEmailOnReservationLookUp(email);
		accountCreationActions.enterAccountNoReservationLookUp(accountNumber);
		accountCreationActions.clickOnCreateAccountSubmit();
		webAppDriver.verifyElementPresentById(tbCreateAccountUsernameId);

		/*
		 * SoftAssert sa = new SoftAssert(); sa.assertEquals(actual, expected);
		 * sa.assertNull(object);
		 */

		// sa.assertAll();

		/*
		 * SoftAssert sa = new SoftAssert(); sa.assertEquals(actual, expected);
		 * sa.assertNull(object);
		 */

		// sa.assertAll();

	}
	
	@Test(priority=1)
	public void verifyRecoverAccountNumberTest(){
		
		navigator.gotoCreateAccountReservationLookUpPage();
		accountCreationActions.clickOnAccountNumberToolTip();
			accountCreationActions.clickOnRecoverAccountNumberLink();
		
	}
	
	
	@Test(priority=2)
	public void verifyToolTipTest(){
		failedText = new StringBuilder();
		allTextPresent = true;
		isTextPresent = new HashMap<String, Boolean>();
		Iterator<Map.Entry<String, Boolean>> iterator ;
		String accountNoToolTipCopy1="Your Account Number can be found in the Welcome email you received after you moved in.";
		String accountNoToolTipCopy2="to resend your Welcome email.";
		String emailAddressTootlTipCopy="Enter the Email Address you provided when you moved in.";
		String usernameToolTipCopy="Your Username should be 7 characters or more.";
		String passwordToolTipCopy="Your Password should be 7 characters or more.";
		navigator.gotoCreateAccountReservationLookUpPage();
		accountCreationActions.clickOnEmailAddressToolTip();
		isTextPresent.put(emailAddressTootlTipCopy, webAppDriver.verifyTextContainInAnyTag(emailAddressTootlTipCopy));
		accountCreationActions.clickOnAccountNumberToolTip();
		isTextPresent.put(accountNoToolTipCopy1, webAppDriver.verifyTextContainInAnyTag(accountNoToolTipCopy1));
		isTextPresent.put(accountNoToolTipCopy2, webAppDriver.verifyTextWithSpacePresentInAnyTag(accountNoToolTipCopy2));
		navigator.gotoCreateAccountEnterDetailsPage(email, accountNumber);
		accountCreationActions.clickOnUsernameToolTip();
		accountCreationActions.clickOnPasswordToolTip();
		
		isTextPresent.put(usernameToolTipCopy, webAppDriver.verifyTextWithSpacePresentInAnyTag(usernameToolTipCopy));
		isTextPresent.put(passwordToolTipCopy, webAppDriver.verifyTextWithSpacePresentInAnyTag(passwordToolTipCopy));
		
		
		iterator = isTextPresent.entrySet().iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, Boolean> entry = iterator.next();
				if (!entry.getValue()) {
					allTextPresent = false;
					failedText.append(entry.getKey());
					failedText.append(System.lineSeparator());
				}
			}

			if (!allTextPresent) {

				webAppDriver.captureScreenshot();
				Reporter.log(failedText.toString() + " not displayed on the account creation screen");
				throw new AssertionError(failedText.toString() + " not displayed on the account creation screen");
			}
		
	}

	@Test(priority = 1)
	public void verifyAccountCreationValidationTest() {
		isTextPresent = new HashMap<String, Boolean>();
		allTextPresent = true;
		failedText = new StringBuilder();
		Iterator<Map.Entry<String, Boolean>> iterator;

		navigator.gotoCreateAccountEnterDetailsPage(email, accountNumber);
		isTextPresent.put(firstName, webAppDriver.verifyTextContainInAnyTag(firstName));
		isTextPresent.put(lastName, webAppDriver.verifyTextContainInAnyTag(lastName));
		isTextPresent.put(accountNumber, webAppDriver.verifyTextContainInAnyTag(accountNumber));
		webAppDriver.verifyElementPresentByXpath(lbEnterDetailsCopy1Xpath);
		// Removing unit info verification as per the new BRD

		/*
		 * isTextPresent.put(streetAddress,
		 * webAppDriver.verifyTextContainInAnyTag(streetAddress));
		 * isTextPresent.put(cityName,
		 * webAppDriver.verifyTextContainInAnyTag(cityName));
		 * isTextPresent.put(stateName,
		 * webAppDriver.verifyTextContainInAnyTag(stateName));
		 * isTextPresent.put(zipCode,
		 * webAppDriver.verifyTextContainInAnyTag(zipCode));
		 * isTextPresent.put(unitNumber,
		 * webAppDriver.verifyTextContainInAnyTag(unitNumber));
		 */

		// Validations -Required
		String invalid = "";
		accountCreationActions.enterAccountCreationUserName(invalid);
		invalid = "";
		accountCreationActions.enterAccountCreationPassword(invalid);
		invalid = "";
		accountCreationActions.enterAccountCreationConfPassword(invalid);
		accountCreationActions.clickOnAccountCreationEnterDetailsSubmit();

		isTextPresent.put(errorUsernameRequired, webAppDriver.verifyTextContainInAnyTag(errorUsernameRequired));

		isTextPresent.put(errorPasswordRequired, webAppDriver.verifyTextContainInAnyTag(errorInvalidPassword));
		isTextPresent.put(errorUsernameRequired, webAppDriver.verifyTextContainInAnyTag(errorInvalidPassword));

		// Validations -Invalid
		invalid = "";
		accountCreationActions.enterAccountCreationUserName(invalid);

		accountCreationActions.enterAccountCreationPassword(password);

		accountCreationActions.enterAccountCreationConfPassword(password);
		accountCreationActions.clickOnAccountCreationEnterDetailsSubmit();

		isTextPresent.put(errorInvalidUsername, webAppDriver.verifyTextContainInAnyTag(errorUsernameRequired));

		// Validations-username exists
		accountCreationActions.enterAccountCreationUserName(existingUsername);
		accountCreationActions.enterAccountCreationPassword(password);
		accountCreationActions.enterAccountCreationConfPassword(password);
		accountCreationActions.clickOnAccountCreationEnterDetailsSubmit();

		isTextPresent.put(errorUsernameExists, webAppDriver.verifyTextContainInAnyTag(errorUsernameExists));

		// Validations-password mismatch
		accountCreationActions.enterAccountCreationUserName(userName);
		accountCreationActions.enterAccountCreationPassword(password);

		accountCreationActions.enterAccountCreationConfPassword("abc");
		accountCreationActions.clickOnAccountCreationEnterDetailsSubmit();
		isTextPresent.put(errorPasswordNotmatch, webAppDriver.verifyTextContainInAnyTag(errorPasswordNotmatch));

		// Validations-password strength

		String passwordStrength = "test12";
		accountCreationActions.enterAccountCreationUserName(userName);
		accountCreationActions.enterAccountCreationPassword(passwordStrength);
		accountCreationActions.enterAccountCreationConfPassword(passwordStrength);
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthWeakXpath);
		webAppDriver.verifyElementPresentByXpath(barPasswordWeakXpath);
		passwordStrength = "test123";
		accountCreationActions.enterAccountCreationUserName(userName);
		accountCreationActions.enterAccountCreationPassword(passwordStrength);
		accountCreationActions.enterAccountCreationConfPassword("passwordStrength");
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthNormalXpath);
		webAppDriver.verifyElementPresentByXpath(barPasswordNormalXpath);
		
		passwordStrength = "Test@123";
		accountCreationActions.enterAccountCreationUserName(userName);
		accountCreationActions.enterAccountCreationPassword(passwordStrength);
		accountCreationActions.enterAccountCreationConfPassword("passwordStrength");
		webAppDriver.verifyElementPresentByXpath(lbPasswordStrengthStrongXpath);
		webAppDriver.verifyElementPresentByXpath(barPasswordStrongXpath);

		// Displaying all the failures

		iterator = isTextPresent.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Boolean> entry = iterator.next();
			if (!entry.getValue()) {
				allTextPresent = false;
				failedText.append(entry.getKey());
				failedText.append(System.lineSeparator());
			}
		}
		if (!allTextPresent) {
			webAppDriver.captureScreenshot();
			Reporter.log(failedText.toString() + " not displayed on the Create Account:Enter Details screen");
			throw new AssertionError(failedText.toString() + " not displayed on the Create Account:Enter Details screen");
		}

	}

	// To verify account creation functionality
//	@Test(priority = 2)
	public void verifyAccountCreationLoginTest() {

		accountCreationActions.enterAccountCreationUserName(userName);
		accountCreationActions.enterAccountCreationPassword(password);
		accountCreationActions.enterAccountCreationConfPassword(password);
		accountCreationActions.clickOnAccountCreationEnterDetailsSubmit();
		webAppDriver.verifyElementPresentById(hlinkLogoutId);
		if (webAppDriver.verifyTextContainInAnyTag(accountCreationmessage)) {
			webAppDriver.captureScreenshot();
			Reporter.log(accountCreationmessage + " not displayed on the Create Account:Enter Details screen");
			throw new AssertionError(accountCreationmessage + " not displayed on the Create Account:Enter Details screen");
		}

	}
}
