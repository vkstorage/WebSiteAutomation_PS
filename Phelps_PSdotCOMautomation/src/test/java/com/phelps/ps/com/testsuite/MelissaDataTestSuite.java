package com.phelps.ps.com.testsuite;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.MelissaDataLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;

public class MelissaDataTestSuite extends BasicTestSuite implements MelissaDataLocators, SelfCareContactInfoLocators, CommonSearchTextLocators {

	/**
	 * Verify melissa data popup on contact info screen
	 */
	/*
	 * Verify melissa data pop-up which comes when address matches with system
	 * database address
	 */
	@Test
	public void melissaDataPopUpTest() {
		String address1 = envTestData.get("address1");
		String address2 = envTestData.get("address2");
		String phone = envTestData.get("phone");
		String state = envTestData.get("state");
		String zip = envTestData.get("zip");
		String city = envTestData.get("city");
		
		String wrongCity = new UniqueId(new UniqueId().id).charId;
		String suggestedStandarizedAddress = envTestData.get("suggestedStandarizedAddress");
		String addressEntered = MessageFormat.format(envTestData.get("addressEntered"), wrongCity);
		navigator.gotoSelfCareContactInfo(envTestData.get("UserForMelissa"), envTestData.get("PasswordForMelissa"));
		selfcareContactInfoActions.enterCity(wrongCity, 1);
		//selfcareContactInfoActions.editCity(wrongCity);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		//webAppDriver.switchtoiFrameByXpath(iframeMelissaDataPopupXpath);
		webAppDriver.verifyElementPresentByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyElementPresentByXpath(lbAddressEnteredXpath);
		webAppDriver.verifyPresenceOfTextInDivTagText(suggestedStandarizedAddress);
		webAppDriver.verifyPresenceOfTextInDivTagText(addressEntered);
		selfcareContactInfoActions.clickEditMelissaData();
		
		String verifyAddress =selfcareContactInfoActions.getAddress1(1);
		System.out.println("@@@@@@@@@@@@@@@@@@"+verifyAddress);
		String verifyZip = selfcareContactInfoActions.getZip(1);
		String verifyPrimaryPhone = selfcareContactInfoActions.getPhoneNumber(1);
		String verifyState= selfcareContactInfoActions.getState(1);
		//String verifyZip = webAppDriver.findElementById(tbZipId).getText();
		//String verifyPrimaryPhone = webAppDriver.findElementById(tbPrimaryPhoneId).getText();
		
		webAppDriver.verifyElementIsInvisibleByXpath(lbSuggestedStandardizedAddressXpath);
		
		webAppDriver.verifyEqualsString(address1, verifyAddress);
		webAppDriver.verifyEqualsString(zip, verifyZip);
		webAppDriver.verifyEqualsString(phone, verifyPrimaryPhone);
		webAppDriver.verifyEqualsString(city, wrongCity);
		webAppDriver.verifyEqualsString(state, verifyState);
		
		/*webAppDriver.verifyAttributeContainsValueById(tbAddressId, "value",verifyAddress);
		webAppDriver.verifyAttributeContainsValueById(tbCityId, "value", wrongCity);
		webAppDriver.verifySelectedOptionEqualsTextById(ddStateId, "CA");
		webAppDriver.verifyAttributeContainsValueById(tbZipId, "value", verifyZip);
		webAppDriver.verifyAttributeContainsValueById(tbPrimaryPhoneId, "value", verifyPrimaryPhone);*/
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		webAppDriver.verifyPresenceOfTextInPTagText("Contact Information");
		selfcareContactInfoActions.enterFullContactInfo(address1, address2, city, state, zip, phone);
		selfcareContactInfoActions.clickSaveChangesContactInfo();

	}

	/**
	 * Verify melissa data popup on complete your express check-in screen
	 */
	@Test
	public void ECIMelissaDataPopUpTest() {
		String searchText = searchText37;
		String fname = envTestData.get("fname");
		String lname = new UniqueId(new UniqueId().id).charId;
		String phone = envTestData.get("phone");
		String ext = envTestData.get("ext");
		String email = envTestData.get("email");
		String password = envTestData.get("password");
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String city = envTestData.get("city");
		String address1 = envTestData.get("address1");
		String address2 = envTestData.get("address2");
		String wrongCity = new UniqueId(new UniqueId().id).charId;
		String state = envTestData.get("state");
		String zip = envTestData.get("zip");
		String idType = envTestData.get("idType");
		String idNumber = envTestData.get("idNumber");
		String emgCntFirstname = envTestData.get("emgCntFirstname");
		String emgCntLastName = new UniqueId(new UniqueId().id).charId;
		String relationship = envTestData.get("relationship");
		String emgCntPhoneNo = envTestData.get("emgCntPhoneNo");

		String SuggestedStandarizedAddress = envTestData.get("suggestedStandarizedAddress");
		String AddressEntered = MessageFormat.format(envTestData.get("addressEntered"), wrongCity);

		navigator.intoECI_RDP(searchText, fname, lname, phone, ext, email, date,0,0);

		eciDetailsActions.enterAllMandatoryDetails(address1, address2, wrongCity, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		//webAppDriver.switchtoiFrameByXpath(iframeMelissaDataPopupXpath);
		webAppDriver.verifyElementPresentByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyElementPresentByXpath(lbAddressEnteredXpath);
		webAppDriver.verifyPresenceOfTextInDivTagText(SuggestedStandarizedAddress);
		webAppDriver.verifyPresenceOfTextInDivTagText(AddressEntered);
		selfcareContactInfoActions.clickEditMelissaData();
		webAppDriver.verifyElementIsInvisibleByXpath(lbSuggestedStandardizedAddressXpath);
		
		eciDetailsActions.enterCity(city);
		eciDetailsActions.clickComplete();
		webAppDriver.verifyPresenceOfTextInSpanTag("Reservation Confirmation");
	}

	/*
	 * Verify melissa data for address matching with system address on contact
	 * info screen
	 */
	@Test
	public void melissaDataMatchingAddressTest() {
		String address = envTestData.get("address1");
		String Apt = envTestData.get("address2");
		String city = envTestData.get("city");
		String State = envTestData.get("state");
		String zip = envTestData.get("zip");
		String phone = envTestData.get("phone");
		navigator.gotoSelfCareContactInfo(envTestData.get("UserForMelissa"), envTestData.get("PasswordForMelissa"));
		selfcareContactInfoActions.enterFullContactInfo(address, Apt, city, State, zip, phone);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		webAppDriver.verifyElementIsInvisibleByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyPresenceOfTextInPTagText("Contact Information");
	}

	/*
	 * Verify melissa data for address matching with system address on complete
	 * express checkin screen
	 */
	@Test
	public void MelissaDataECIMatchingAddressTest() {

		String searchText = searchText38;
		String fname = envTestData.get("fname");
		String lname = new UniqueId(new UniqueId().id).charId;
		String phone = envTestData.get("phone");
		String ext = envTestData.get("ext");
		String email = envTestData.get("email");
		String password = envTestData.get("password");
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));

		String address1 = envTestData.get("address1");
		String address2 = envTestData.get("address2");
		String city = envTestData.get("city");
		String state = envTestData.get("state");
		String zip = envTestData.get("zip");
		String idType = envTestData.get("idType");
		String idNumber = envTestData.get("idNumber");
		String emgCntFirstname = envTestData.get("emgCntFirstname");
		String emgCntLastName = envTestData.get("emgCntLastName");
		String relationship = envTestData.get("relationship");
		String emgCntPhoneNo = envTestData.get("emgCntPhoneNo");
		

		navigator.intoECI_RDP(searchText, fname, lname, phone, ext, email, date,0,0);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		webAppDriver.verifyElementIsInvisibleByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
	//webAppDriver.verifyPresenceOfTextInSpanTag("Reservation Confirmation");

	}

	/*
	 * Verify melissa data pop-up which comes when address not matches with
	 * system database address
	 */
	@Test
	public void melissaDataNotMatchingAddressTest() {
		
		String address1 = envTestData.get("address1");
		String address2 = envTestData.get("address2");
		String wrongCity = new UniqueId(new UniqueId().id).charId;
		String state = envTestData.get("state");
		String zip = envTestData.get("zip");
		String phone = envTestData.get("phone");
		String city = envTestData.get("city");
		
		
		navigator.gotoSelfCareContactInfo(envTestData.get("UserForMelissa"), envTestData.get("PasswordForMelissa"));
		selfcareContactInfoActions.enterCity(wrongCity, 1);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		//webAppDriver.switchtoiFrameByXpath(iframeMelissaDataPopupXpath);
		webAppDriver.verifyElementPresentByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyElementPresentByXpath(lbAddressEnteredXpath);
		webAppDriver.clickElementByXpath(lbSuggestedStandardizedAddressXpath);
		selfcareContactInfoActions.clickSaveandComplete();
		webAppDriver.verifyElementIsInvisibleByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyPresenceOfTextInPTagText("Contact Information");
		/*webAppDriver.verifyAttributeContainsValueById(tbAddressId, "value", "901 Wilshire Blvd");
		webAppDriver.verifyAttributeContainsValueById(tbCityId, "value", "Santa Monica");
		webAppDriver.verifySelectedOptionEqualsTextById(ddStateId, "CA");
		webAppDriver.verifyAttributeContainsValueById(tbZipId, "value", "90401-1854");
		webAppDriver.verifyAttributeContainsValueById(tbPrimaryPhoneId, "value", "7058693784");*/
	
		String verifyAddress =selfcareContactInfoActions.getAddress1(1);
		System.out.println("@@@@@@@@@@@@@@@@@@"+verifyAddress);
		String verifyZip = selfcareContactInfoActions.getZip(1);
		String verifyPrimaryPhone = selfcareContactInfoActions.getPhoneNumber(1);
		String verifyState= selfcareContactInfoActions.getState(1);
		webAppDriver.verifyEqualsString(address1, verifyAddress);
		webAppDriver.verifyEqualsString(zip, verifyZip);
		webAppDriver.verifyEqualsString(phone, verifyPrimaryPhone);
		webAppDriver.verifyEqualsString(city, city);
		webAppDriver.verifyEqualsString(state, verifyState);
	}

	/*
	 * Verify number of suggestions coming on the melissa data pop-up
	 */
	@Test
	public void MelissaDataSuggestionsTest() {
		String wrongCity = new UniqueId(new UniqueId().id).charId;
		navigator.gotoSelfCareContactInfo(envTestData.get("UserForMelissa"), envTestData.get("PasswordForMelissa"));
		selfcareContactInfoActions.enterPrimaryCity(wrongCity);
		selfcareContactInfoActions.clickSaveChangesContactInfo();
		//webAppDriver.switchtoiFrameByXpath(iframeMelissaDataPopupXpath);
		webAppDriver.verifyElementPresentByXpath(lbSuggestedStandardizedAddressXpath);
		webAppDriver.verifyElementPresentByXpath(lbAddressEnteredXpath);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(commonAddressXpath, 2);

	}
}
