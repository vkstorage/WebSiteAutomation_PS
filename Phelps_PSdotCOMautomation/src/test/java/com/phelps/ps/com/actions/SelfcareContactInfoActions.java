package com.phelps.ps.com.actions;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

//import org.apache.xerces.util.MessageFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.MelissaDataLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class SelfcareContactInfoActions implements SelfCareContactInfoLocators, MelissaDataLocators, SelfcareSummaryLocators {

	private CateredWebDriver webAppDriver;

	public SelfcareContactInfoActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	public void clickUsernameEmailPasswordLink() {
		webAppDriver.clickElementByLinkText(hlinkUsernameEmailPasswordLink);
		webAppDriver.verifyElementPresentByXpath(lbSelfcareUsernameXpath);
	}

	public void clickSuggestedStandardizedAddress() {
		webAppDriver.clickElementByXpath(opSuggestedStandardizedAddressXpath);
	}

	public void clickAddressEntered() {
		webAppDriver.clickElementByXpath(opAddressEnteredXpath);
	}

	public void clickSaveChangesLoginDetails() {
		webAppDriver.clickElementByCss(btnSaveChangesCss);
	}

	public void clickSaveChangesContactInfo() {
		webAppDriver.clickElementById(btnSaveChangesContactInfoId);
	}
	
	public void clickCancelContactInfo() {
		webAppDriver.clickElementByLinkText(btnCancelChangesLink);
	}

	public void editCity(String text) {
		webAppDriver.enterTextToElementById(tbCityId, text);
	}

	// Melissa Data Modal

	public void editaddress(String text) {
		webAppDriver.enterTextToElementById(tbAddressId, text);
	}

	public void editApt(String text) {
		webAppDriver.enterTextToElementById(tbAptId, text);
	}

	public void editstate(String text) {
		webAppDriver.selectByVisibleTextByLocatorId(ddStateId, text);
	}

	public void editzip(String text) {
		webAppDriver.enterTextToElementById(tbZipId, text);
	}

	public void editphone(String text) {
		webAppDriver.enterTextToElementById(tbPrimaryPhoneId, text);
	}

	public void clickSaveandComplete() {
		//webAppDriver.clickElementByXpath(btnSaveCompleteXpath);
		webAppDriver.clickElementByCss(btnSaveCompleteCss);
	}

	public void clickEditMelissaData() {
		webAppDriver.clickElementByLinkText("(edit)");

	}

	// WC2

	// Summary page
	public void clickOnRememberMeLaterLink() {
		webAppDriver.clickElementByLinkText(hlinkRemindMeLaterLink);
		webAppDriver.relax(500);
		if (webAppDriver.verifyElementPresentBy(By.xpath(iframeRememberMeLaterXpath))) {
			webAppDriver.captureScreenshot();
			Reporter.log(hlinkRemindMeLaterLink + " link still present ");
			throw new AssertionError(hlinkRemindMeLaterLink + " link still present ");
		}
	}

	// Address & Personal Info Sub-section
	public void clickOnMakePrimaryAddressLink() {

		webAppDriver.clickElementByLinkText(hlinkMakePrimaryAddressLink);
	}

	public void clickOnRemovePrimaryAddressLink() {
		webAppDriver.clickElementByXpath(hlinkRemoveSecondaryAddressXpath);
		String finalXpath=MessageFormat.format(tbAllAddress1Xpath, 2);
		webAppDriver.verifyElementNotPresntByXpath(finalXpath);

	}

	public void enterPrimaryAddress(String address) {
		webAppDriver.findElementByXpath(tbAddressContactInfoXpath).clear();
		webAppDriver.findElementByXpath(tbAddressContactInfoXpath).sendKeys(address);
	}

	public void enterPrimaryCity(String City) {
		webAppDriver.findElementByXpath(tbCityAndStateXpath).clear();
		webAppDriver.findElementByXpath(tbCityAndStateXpath).sendKeys(City);
	}

	public void enterPrimaryZip(String Zip) {
		webAppDriver.findElementByXpath(tbZipContactInfoXPath).clear();
		webAppDriver.findElementByXpath(tbZipContactInfoXPath).sendKeys(Zip);

	}

	public void enterSecondaryAddress(String address) {
		webAppDriver.findElementByXpath(tbSecondaryAddressContactInfoXpath).clear();
		webAppDriver.findElementByXpath(tbSecondaryAddressContactInfoXpath).sendKeys(address);
	}

	public void enterSecondaryCity(String address) {
		webAppDriver.findElementByXpath(tbSecondaryCityAndStateXpath).clear();
		webAppDriver.findElementByXpath(tbSecondaryCityAndStateXpath).sendKeys(address);
	}

	public void enterSecondaryZip(String address) {
		webAppDriver.findElementByXpath(tbSecondaryZipContactInfoXpath).clear();
		webAppDriver.findElementByXpath(tbSecondaryZipContactInfoXpath).sendKeys(address);
	}

	public void enterSecondaryAptSuite(String aptSuite) {
		webAppDriver.findElementByXpath(tbSecondaryAptSuiteXpath).clear();
		webAppDriver.findElementByXpath(tbSecondaryAptSuiteXpath).sendKeys(aptSuite);
	}

	public void selectSecondaryState(String value) {
		webAppDriver.selectByValueByXpath(ddSecondaryStateXpath, value);
	}

	public void selectSecondaryCountry(String value) {
		webAppDriver.selectByValueByXpath(ddSecondaryCountryXpath, value);
	}

	public String getPrimaryAddressText() {
		return webAppDriver.findElementByXpath(tbAddressContactInfoXpath).getText();
	}

	public String getPrimaryCityText() {
		return webAppDriver.findElementById(tbCityAndStateId).getText();
	}

	public String getPrimaryStateText() {
		Select ddElement = new Select(webAppDriver.findElementById(ddStateId));
		return ddElement.getFirstSelectedOption().getText();
	}

	public String getPrimaryZipText() {
		return webAppDriver.findElementById(tbZipContactInfoId).getText();
	}

	public String getSecondaryAddressText() {
		return webAppDriver.findElementByXpath(tbSecondaryCityAndStateXpath).getText();
	}

	public String getSecondaryCityText() {
		return webAppDriver.findElementByXpath(tbSecondaryCityAndStateXpath).getText();
	}

	public String getSecondaryZipText() {
		return webAppDriver.findElementByXpath(tbSecondaryCityAndStateXpath).getText();
	}

	public String getSecondaryAptSuiteText() {
		return webAppDriver.findElementByXpath(tbSecondaryAptSuiteXpath).getText();
	}

	public String getSelectedTextSecondaryState() {
		Select ddElement = new Select(webAppDriver.findElementByXpath(ddSecondaryStateXpath));
		return ddElement.getFirstSelectedOption().getText();
	}

	public void enterFullContactInfo(String address, String Apt, String city, String state, String zip, String phone) {
		/*editaddress(address);
		editApt(Apt);
		editCity(city);
		editstate(state);
		editzip(zip);
		editphone(phone);*/
		
		
		enterAddress1(address, 1);
		enterAddress2(Apt,1);
		enterCity(city, 1);
		enterZip(zip, 1);
		SelectCountries("United States", 1);
		SelectStates(state, 1);
	}

	public void clickOnAddAlternateAddressLink() {
		webAppDriver.clickElementByLinkText(hlnkAlternateAddressLink);
		webAppDriver.verifyElementPresentByXpath("//span[text()='Alternate Address']");
		//webAppDriver.verifySpanTagTextEquals("Alternate Address");
	}

	public void clickOnAddAdditionalPhoneNumbersLink(boolean isWC1Account) {
		webAppDriver.findElementByLinkText(hlinkAddAdditionalPhnNumbersLink).click();

		/*if (isWC1Account) {
			if (webAppDriver.verifyTextContainInAnyTag("Preferred") || webAppDriver.verifyTextContainInAnyTag("Number") ) {
				Reporter.log("Preferred Number is displayed for WC1");
				webAppDriver.captureScreenshot();
				throw new AssertionError("Preferred Number is displayed for WC1");
			}
		} else {
			if (!webAppDriver.verifyTextContainInAnyTag("Preferred") ){
				Reporter.log("Preferred not displayed ");
				webAppDriver.captureScreenshot();
				throw new AssertionError("Prefered  not displayed");
			}
			if (!webAppDriver.verifyTextContainInAnyTag("Number")){
				Reporter.log(" Number not displayed ");
				webAppDriver.captureScreenshot();
				throw new AssertionError("Prefered number not displayed");
			}
			

		}*/

	}

	public void enterMobilePhoneNumber(String mobile) {
		webAppDriver.findElementByXpath(tbMobilePhoneXpath).clear();
		webAppDriver.findElementByXpath(tbMobilePhoneXpath).sendKeys(mobile);
	}

	public void enterWorkPhoneNumber(String work) {
		webAppDriver.findElementByXpath(tbWorkPhoneXpath).clear();
		webAppDriver.findElementByXpath(tbWorkPhoneXpath).sendKeys(work);
	}

	/*public void enterWorkExt(String ext) {
		webAppDriver.findElementByXpath(tbWorkExtXpath).clear();
		webAppDriver.findElementByXpath(tbWorkExtXpath).sendKeys(ext);
	}*/

	public void enterHomePhoneNumber(String home) {
		webAppDriver.findElementByXpath(tbHomePhoneXpath).clear();
		webAppDriver.findElementByXpath(tbHomePhoneXpath).sendKeys(home);
	}

	public void enterOtherPhoneNumber(String other) {
		webAppDriver.findElementByXpath(tbOtherPhoneXpath).clear();
		webAppDriver.findElementByXpath(tbOtherPhoneXpath).sendKeys(other);
	}

	public String getMobilePhoneText() {
		return webAppDriver.findElementByXpath(tbMobilePhoneXpath).getAttribute("value");
	}

	public String getWorkPhoneText() {
		return webAppDriver.findElementByXpath(tbWorkPhoneXpath).getAttribute("value");
	}

	public void clickOnAddUpto5EmailAddressLink() {
		webAppDriver.clickElementByLinkText(hlinkAdditionalEmailAddressLink);
	}

	public void enterEmailAddress(String email, int value) {
		String finalXpath = MessageFormat.format(tbAllEmailAddressXpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, email);
	}

	public void enterConfirmEmailAddress(String Confirmemail, int value) {
		String finalXpath = MessageFormat.format(tbConfirmEmailAddressXpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, Confirmemail);
	}

	public void enterAddress1(String address1, int value) {
		String finalXpath = MessageFormat.format(tbAllAddress1Xpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, address1);
	}

	public String getAddress1(int value) {
		String finalXpath = MessageFormat.format(tbAllAddress1Xpath, value);
		String returnText = webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
		return returnText;
	}

	public void enterAddress2(String address2, int value) {
		String finalXpath = MessageFormat.format(tbAllAddress2Xpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, address2);
	}

	public String getAddress2(int value) {
		String finalXpath = MessageFormat.format(tbAllAddress2Xpath, value);
		return webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
	}

	public void enterCity(String city, int value) {
		String finalXpath = MessageFormat.format(tbAllCityXpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, city);
	}

	public String getCity(int value) {
		String finalXpath = MessageFormat.format(tbAllCityXpath, value);
		return webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
	}

	public void enterZip(String zip, int value) {
		String finalXpath = MessageFormat.format(tbAllZipXpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, zip);
	}

	public String getZip(int value) {
		String finalXpath = MessageFormat.format(tbAllZipXpath, value);
		return webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
	}

	public void SelectCountries(String country, int value) {
		String finalXpath = MessageFormat.format(ddAllCountriesXpath, value);
		webAppDriver.selectByVisibleTextByXpath(finalXpath, country);
	}

	public String getCountry(int value) {
		String finalXpath = MessageFormat.format(ddAllCountriesXpath, value);
		Select select = new Select(webAppDriver.findElementByXpath(finalXpath));
		return select.getFirstSelectedOption().getAttribute("value");
	}

	public void SelectStates(String states, int value) {
		String finalXpath = MessageFormat.format(ddAllStatesXpath, value);
		webAppDriver.selectByValueByXpath(finalXpath, states);
	}

	public String getState(int value) {
		String finalXpath = MessageFormat.format(ddAllStatesXpath, value);
		Select select = new Select(webAppDriver.findElementByXpath(finalXpath));
		return select.getFirstSelectedOption().getAttribute("value");
	}

	public void clickAlternateAddressRemoveLink() {
		webAppDriver.clickElementByXpath(hlnkAlternateAddressRemoveXpath);
		webAppDriver.verifyElementNotPresntByXpath("//span[text()='Alternate Address']");
	}

	public void clickEmailRemoveLinks(int value) {
		String finalXpath = MessageFormat.format(hlinkAllEmailRemoveXpath, value);
		webAppDriver.clickElementByXpath(finalXpath);
	}

	public String getPhoneNumber(int value) {
		String finalXpath = MessageFormat.format(tbAllPhoneNumbersXpath, value);
		return webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
	}

	public void enterPhoneExt(String Ext, int value) {
		String finalXpath = MessageFormat.format(tbAllWorkExtensionXpath, value);
		webAppDriver.findElementByXpath(finalXpath).clear();
		webAppDriver.enterTextToElementByXpath(finalXpath, Ext);
	}

	public void checkTextMessageOk(int value) {
		String finalXpath = MessageFormat.format(chboxAllMobileTextOkXpath, value);
		webAppDriver.clickElementByXpath(finalXpath);
	}

	public void clickPhoneRemoveLinks(int value) {
		String finalXpath = MessageFormat.format(hlinkAllPhoneRemoveXpath, value);
		webAppDriver.clickElementByXpath(finalXpath);
	}

	public String getHomePhoneText() {
		return webAppDriver.findElementByXpath(tbHomePhoneXpath).getAttribute("value");
	}

	public String getOtherPhoneText() {
		return getPhoneNumber(4);
	}

	public void selectPhoneType(String phone, int value) {

		String finalXpath = MessageFormat.format(ddAllPhoneTypeXpath, value);
		webAppDriver.selectByValueByXpath(finalXpath, phone);
	}

	public void selectMobilePhone(String phone, boolean isWC1, boolean isTicked) {
	//	webAppDriver.selectByValueByXpath(ddSecondPhoneXpath, "Other");
		//Commented for WC1
		webAppDriver.selectByValueByXpath(ddSecondPhoneXpath, "Mobile");

		if (isWC1) {
			webAppDriver.verifyElementNotPresntByXpath(chBoxTextMessageOkXpath);
			if (webAppDriver.verifyTextContainInAnyTag("Text Message OK")) {
				Reporter.log("text message ok check box is  displayed after selecting mobile for WC1");
				webAppDriver.captureScreenshot();
				throw new AssertionError("text message ok check box is  displayed after selecting mobile for WC1");
			}
		} else  {
			webAppDriver.verifyElementPresentByXpath("(//span[@data-bind=\"visible: phoneType() === 'Mobile'\"][contains(.,'Text Message Ok')])[2]");
		/*	Reporter.log("text message ok check box not displayed after selecting mobile");
			webAppDriver.captureScreenshot();
			throw new AssertionError("text message ok check box not displayed after selecting mobile");*/
		}
		if (isTicked) {
			String finalXpath=MessageFormat.format(chBoxTextMessageOkXpath, 2);
			webAppDriver.clickElementByXpath(finalXpath);
		}
		enterMobilePhoneNumber(phone);
	}

	public void selectWorkPhone(String phone, String ext) {
		webAppDriver.selectByValueByXpath(ddThirdPhoneXpath, "Work");
		if (!webAppDriver.verifyTextContainInAnyTag("Ext.")) {
			Reporter.log("Ext. not displayed after selecting work");
			webAppDriver.captureScreenshot();
			throw new AssertionError("Ext. not displayed after selecting work");
		}
		enterWorkPhoneNumber(phone);
		//enterExtensionNumber(ext, 3);
	}

	public void selectHomePhone(String phone) {
		webAppDriver.selectByValueByXpath(ddFifthPhoneXpath, "Home");

		//enterHomePhoneNumber(phone);
		enterPhoneNumber(phone, 5);

	}

	public void selectOtherPhone(String phone) {
		webAppDriver.selectByValueByXpath(ddFourthPhoneXpath, "Other");

		enterPhoneNumber(phone, 4);
		//enterOtherPhoneNumber(phone);

	}

	public void clickRemovePhoneLink(int removeNumber) {
		switch (removeNumber) {
		case 2:
			webAppDriver.clickElementByXpath(hlinkRemovePhone2LinkXpath);
			break;
		case 3:
			webAppDriver.clickElementByXpath(hlinkRemovePhone3LinkXpath);
			break;
		case 4:
			webAppDriver.clickElementByXpath(hlinkRemovePhone4LinkXpath);
			break;
		case 5:
			webAppDriver.clickElementByXpath(hlinkRemovePhone5LinkXpath);
			break;
		}

	}

	// Username, Email & Password Sub-section
	public void clickOnUsernameEditLink() {
		webAppDriver.clickElementById(hlinkUsernameEditId);

		webAppDriver.verifyElementPresentById(tbNewUsernameId);

		// WC2 removed as per BRD v 1.3
		/*
		 * if (webAppDriver.verifyTextContainInAnyTag("New Username *")) {
		 * Reporter.log("new username text not present");
		 * webAppDriver.captureScreenshot(); throw new
		 * AssertionError("new username text not present"); }
		 */
	}

	public void enterPassword(String password) {
		webAppDriver.findElementById(tbPasswordId).clear();
		webAppDriver.enterTextToElementById(tbPasswordId, password);
		/*
		 * if(webAppDriver.verifyTextContainInAnyTag("Password Strength")){
		 * Reporter.log("Password Strength meter not present");
		 * webAppDriver.captureScreenshot(); throw new
		 * AssertionError("Password Strength meter not present"); }
		 */
	}

	public void enterConfrimPassword(String confPassword) {
		webAppDriver.findElementById(tbConfirmPasswordId).clear();
		webAppDriver.enterTextToElementById(tbConfirmPasswordId, confPassword);

	}

	public void enterNewUsername(String newUsername) {

		webAppDriver.findElementById(tbNewUsernameId).clear();
		webAppDriver.findElementById(tbNewUsernameId).sendKeys(newUsername);
	}

	public void clickOnPasswordEditLink() {
		webAppDriver.clickElementById(hlnkPasswordEditId);
		webAppDriver.verifyElementPresentById(tbCurrentPasswordId);
	}

	public void enterCurrentPassword(String currentPassword) {

		webAppDriver.findElementById(tbCurrentPasswordId).clear();
		webAppDriver.findElementById(tbCurrentPasswordId).sendKeys(currentPassword);
	}

	public void enterNewPassword(String newPassword) {

		webAppDriver.findElementById(tbNewPasswordId).clear();
		webAppDriver.findElementById(tbNewPasswordId).sendKeys(newPassword);
	}

	public void enterConfirmPassword(String confirmPassword) {

		webAppDriver.findElementById(tbConfirmPasswordId).clear();
		webAppDriver.findElementById(tbConfirmPasswordId).sendKeys(confirmPassword);
	}

	public void enterNewConfirmUsername(String confirmUsername) {
		webAppDriver.findElementById(tbConfirmNewUsernameId).clear();
		webAppDriver.findElementById(tbConfirmNewUsernameId).sendKeys(confirmUsername);

	}

	public void enterAlternateEmail_1(String email_1) {
		webAppDriver.findElementByXpath(tbAlternate1EmailXpath).clear();
		webAppDriver.findElementByXpath(tbAlternate1EmailXpath).sendKeys(email_1);
	}

	public void enterAlternateEmail_2(String email_2) {
		webAppDriver.findElementByXpath(tbAlternate2EmailXpath).clear();
		webAppDriver.findElementByXpath(tbAlternate2EmailXpath).sendKeys(email_2);
	}

	public void enterAlternateEmail_3(String email_3) {
		webAppDriver.findElementByXpath(tbAlternate3EmailXpath).clear();
		webAppDriver.findElementByXpath(tbAlternate3EmailXpath).sendKeys(email_3);
	}

	public void enterAlternateEmail_4(String email_4) {
		webAppDriver.findElementByXpath(tbAlternate4EmailXpath).clear();
		webAppDriver.findElementByXpath(tbAlternate4EmailXpath).sendKeys(email_4);
	}
	
	public String getAllEmailAddress(int value) {
		String finalXpath = MessageFormat.format(tbAllEmailAddressXpath, value);
		String returnText = webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
		return returnText;
	}

	public String getAlternateEmail_1() {
		return webAppDriver.findElementByXpath(tbAlternate1EmailXpath).getText();
	}

	public String getAlternateEmail_2() {
		return webAppDriver.findElementByXpath(tbAlternate2EmailXpath).getText();
	}

	public String getAlternateEmail_3() {
		return webAppDriver.findElementByXpath(tbAlternate3EmailXpath).getText();
	}

	public String getAlternateEmail_4() {
		return webAppDriver.findElementByXpath(tbAlternate4EmailXpath).getText();
	}

	public String getEmailAddress() {
		return webAppDriver.findElementByXpath(lbEmailAddressXcode).getText();

	}

	public void clickOnAlternateEmail_1_Removeink() {
		webAppDriver.clickElementByXpath(hlinkAlternate1EmailRemoveXpath);
	}

	public void clickOnAlternateEmail_2_RemoveLink() {
		webAppDriver.clickElementByXpath(hlinkAlternate2EmailRemoveXpath);
	}

	public void clickOnAlternateEmail_3_RemoveLink() {
		webAppDriver.clickElementByXpath(hlinkAlternate3EmailRemoveXpath);
	}

	public void clickOnAlternateEmail_4_RemoveLink() {
		webAppDriver.clickElementByXpath(hlinkAlternate4EmailRemoveXpath);
	}

	public void clickOnAddAdditionalEmailAddressLink() {
		webAppDriver.clickElementByLinkText(hlinkAddAdditionalEmailAddressLink);
	}

	public void clickRemoveEmailLink(int removeNumber) {
		switch (removeNumber) {
		case 2:
			webAppDriver.clickElementByXpath(hlinkRemoveEmail2LinkXpath);
			break;
		case 3:
			webAppDriver.clickElementByXpath(hlinkRemoveEmail3LinkXpath);
			break;
		case 4:
			webAppDriver.clickElementByXpath(hlinkRemoveEmail4LinkXpath);
			break;
		case 5:
			webAppDriver.clickElementByXpath(hlinkRemoveEmail5LinkXpath);
			break;
		}

	}

	public void clickOnPrefferedPhone(int value) {

		String finalXpath = MessageFormat.format(rbtnAllPreferredNumberXpath, value);
		webAppDriver.clickElementByXpath(finalXpath);
	}

	public void enterPhoneNumber(String phoneNumber, int value) {
		String finalXpath = MessageFormat.format(tbAllPhoneNumberXpath, value);
		webAppDriver.enterTextToElementByXpath(finalXpath, phoneNumber);
	}

	public void enterExtensionNumber(String extensionNumber, int value) {
		String finalXpath = MessageFormat.format(tbPhoneExtXpath, value);
		webAppDriver.enterTextToElementByXpath(finalXpath, extensionNumber);
	}
	
	public String getExtNumber(int value){
		String finalXpath = MessageFormat.format(tbPhoneExtXpath, value);
		return webAppDriver.findElementByXpath(finalXpath).getAttribute("value");
	}

	public List<String> getPhoneDropdownAllValues() {
		Select select = new Select(
				webAppDriver.findElementByXpath("//select[contains(@data-bind,'options: ps.contactInfo.phoneTypes')]"));

		List<WebElement> allOptions = select.getOptions();
		List<String> allValues = new ArrayList<String>();
		for (WebElement element : allOptions) {
			allValues.add(element.getText());
		}
		return allValues;
	}
	
	public void clickOnPrefferedEmail(int value) {

		String finalXpath = MessageFormat.format(rbtnAllPreferredEmailXpath, value);
		webAppDriver.clickElementByXpath(finalXpath);
	}
	
	

}
