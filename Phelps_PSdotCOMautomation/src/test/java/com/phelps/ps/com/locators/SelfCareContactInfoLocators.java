package com.phelps.ps.com.locators;

public interface SelfCareContactInfoLocators {

	String hlinkUsernameEmailPasswordLink = "Username, Email & Password";

	/*
	 * Address & Personal Info
	 */

	String lbCustomerNameXpath = ".//*[@id='aspnetForm']//table[@class='tblContactInfo']/tbody/tr[1]/td[2]";
	String tbAddressId = "Street1";
	String tbAptId = "Street2";
	String tbCityId = "City";
	String tbZipId = "Zip";
	String tbPrimaryPhoneId = "PrimaryPhone";
	String ddStateId = "contactState";
	String btnSaveChangesContactInfoId = "contactInfoSubmitButton";
	String btnSaveChangesCss= ".btnSaveChanges";
	String btnCancelChangesLink="Cancel";
	
	

	/*
	 * Username, Email & Password
	 */
	String lbSelfcareUsernameXpath = ".//form[@id='aspnetForm']//table/tbody/tr[1]/td[2]";
	String lbSelfcareEmailXpath = ".//*[@id='aspnetForm']//tr[@class='emailDisplayContainer']/td[2]";
	String tbSelfcareEmailId = "Email";// .//form[@id='aspnetForm']//table/tbody/tr[2]/td[2]";

	// WC2 locators
	// Address & Personal Info Sub-section

	String hlnkMakePrimaryAddressLink = "Make this my primary address";

	String hlinkEmailEditXpath = "//a[@data-bind='click: $root.setEmailEdited']";
	String hlinkAdditionalEmailAddressLink = "+ Add additional email";
	String hlinkAdditionalPhnNumbersLink = "+ Add additional phone";
	String hlnkAlternateAddressLink = "+ Add alternate address";
	String tbCellPhoneXpath = "";

	String tbAllAddress1Xpath = "(//input[@data-bind=''value: Address1''])[{0}]";
	String tbAllAddress2Xpath = "(//input[@data-bind=''value: Address2''])[{0}]";
	String tbAllCityXpath = "(//input[@data-bind=''value: City''])[{0}]";
	String tbAllZipXpath = "(//input[@data-bind=''value: Zip''])[{0}]";
	String ddAllCountriesXpath = "(//select[contains(@data-bind,''options: ps.contactInfo.countries'')])[{0}]";
	String ddAllStatesXpath = "(//select[contains(@data-bind,''options: getStatesOrProvinces()'')])[{0}]";
	String hlnkAlternateAddressRemoveXpath = "//a[@data-bind=''click: $root.removeAddress'']";
	String ddAllPhoneTypesXpath = "(//select[contains(@data-bind,''options: ps.contactInfo.phoneTypes'')])[{0}]";
	String tbAllPhoneNumbersXpath = "(//input[contains(@data-bind,''value: Number'')])[{0}]";
	String tbAllWorkExtensionXpath = "(//input[@data-bind=''value: Extension''])[{0}]";
	String chboxAllMobileTextOkXpath = "(//input[@data-bind=''checked: TextOK''])[{0}]";
	String rbtnAllPreferredNumberXpath = "(//input[@name='phonespreferred'])[{0}]";
	String rbtnAllPreferredNumberWC1Xpath="(//input[@name=''phonespreferred''][@style=''display: none;''])[{0}]";
	String hlinkAllPhoneRemoveXpath = "(//a[@data-bind=''click: $root.removePhone''])[{0}]";
	String tbAllEmailAddressXpath = "(//input[@data-bind=''value: Address''])[{0}]";
	String tbConfirmEmailAddressXpath = "(//input[@data-bind=''value: AddressConfirm''])[{0}]";
	String hlinkAllEmailRemoveXpath = "(//a[@data-bind=''click: $root.removeEmail''])[{0}]";
	String rbtnAllPreferredEmailXpath = "(//input[contains(@data-bind,''checked: $root.PreferredEmailIndex()'')])[{0}]";
	String lbAddressErrorXpath="//div[contains(@data-bind,'validationMessage:Address1')]";
	String lbAddressError2Xpath="(//div[contains(@data-bind,'validationMessage:Address1')])[2]";


	String lbCityErrorXpath="//div[contains(@data-bind,'validationMessage:City')]";
	String lbZipErrorXpath="//div[contains(@data-bind,'validationMessage:Zip')]";
	String lbCityError2Xpath="(//div[contains(@data-bind,'validationMessage:City')])[2]";
	String lbZipError2Xpath="(//div[contains(@data-bind,'validationMessage:Zip')])[2]";
	
	String lbNumberErrorXpath="//span[contains(@data-bind,'validationMessage: Number')]";
	// Username, Email & Password Sub-section

	String tbConfirmUsernameId = "confirmUsername";
	String hlnkPasswordEditId = "passwordEdit";

	String tbNewPasswordId = "password";

	// String
	// chBoxMakePrimaryAddressXpath=".//label[contains(text(),'Make this my primary address')]/../input[@type='checkbox']";
	String hlinkMakePrimaryAddressLink = "Make this my primary address";
	String hlinkRemoveSecondaryAddressXpath = "(//a[@data-bind='click: $root.removeAddress'])[2]";
	String tbSecondaryAddressContactInfoXpath = "(//input[@data-bind='value: Address1'])[2]";// changed
	String tbSecondaryCityAndStateXpath = "(//input[@data-bind='value: Address2'])[2]";
	String tbSecondaryZipContactInfoXpath = "(//input[@data-bind='value: Zip'])[2]";
	String tbSecondaryAptSuiteXpath = "(//input[@data-bind='value: Address2'])[2]";// changed
																																									// form
																																									// tbd
	String ddSecondaryStateXpath = "tbd";
	String ddSecondaryCountryXpath = "tbd";
	String lbSecondaryAddressErrorId = "tbd";
	String lbSecondaryCityErrorId = "tbd";
	String lbSecondaryZipErrorId = "tbd";
	String lbSecondaryPhoneErrorId = "tbd";
	String hlinkAddAdditionalPhnNumbersLink = "+ Add additional phone";
	String tbMobilePhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[2]/div[4]/input";
	String ddCountryId = "tbd";
	String tbWorkPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[3]/div[4]/input";
	String tbAllPhoneNumberXpath = "(//input[contains(@data-bind,''value: Number'')])[{0}]";
	//String tbWorkExtXpath = "tbd";
	String tbHomePhoneXpath = "(//input[contains(@data-bind,'value: Number')])[5]";
	String tbOtherPhoneXpath = "tbd";
	String hlinkAddAlternateAddressLink = "+ Add alternate address";
	String ddAllPhoneTypeXpath = "(//select[contains(@data-bind,''options: ps.contactInfo.phoneTypes'')])[{0}]";
	String ddFirstPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[1]/div[3]/select";
	String ddSecondPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[2]/div[3]/select";
	String ddThirdPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[3]/div[3]/select";
	String ddFourthPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[4]/div[3]/select";
	String ddFifthPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[5]/div[3]/select";
	String chBoxTextMessageOkXpath = "(.//*[@id=''contact-info'']//input[@type=''checkbox''])[{0}]";
	String hlinkRemovePhone2LinkXpath = ".//*[@id='contact-info']/div/div[10]/div[2]/div[6]/a";
	String hlinkRemovePhone3LinkXpath = ".//*[@id='contact-info']/div/div[10]/div[3]/div[6]/a";
	String hlinkRemovePhone4LinkXpath = ".//*[@id='contact-info']/div/div[10]/div[4]/div[6]/a";
	String hlinkRemovePhone5LinkXpath = ".//*[@id='contact-info']/div/div[10]/div[5]/div[6]/a";
	//String rbtnPreferredPhoneXpath = ".//*[@id='contact-info']/div/div[10]/div[{0}]/div[2]/input";
	String rbtnPreferredEmailXpath="(//input[@name='emailpreferred'])[{0}]";
	String hlinkRemovePhoneLinkXpath = ".//*[@id='contact-info']/div/div[10]/div[{0}]/div[6]/a";
	String tbPhoneExtXpath = "(//input[@data-bind=''value: Extension''])[{0}]";
	String lbPhoneRequiredErrorXpath1="(//span[@data-bind='validationMessage: Number'][text()='Required'])[1]";
	String lbPhoneRequiredErrorXpath2="(//span[@data-bind='validationMessage: Number'][text()='Required'])[2]";

	

	String lbPhoneRequiredErrorXpath3="(//span[@data-bind='validationMessage: Number'][text()='Required'])[3]";
	String lbPhoneRequiredErrorXpath4="(//span[@data-bind='validationMessage: Number'][text()='Required'])[4]";
	
	
	String lbPhoneInvalidErrorXpath1="(//span[@data-bind='validationMessage: Number'][text()='Invalid'])[1]";
	String lbPhoneInvalidErrorXpath2="(//span[@data-bind='validationMessage: Number'][text()='Invalid'])[2]";
	String lbPhoneInvalidErrorXpath3="(//span[@data-bind='validationMessage: Number'][text()='Invalid'])[3]";
	String lbPhoneInvalidErrorXpath4="(//span[@data-bind='validationMessage: Number'][text()='Invalid'])[4]";
	String lbPreferredNumberXpath="//div[@class='ci-row']//div[contains(.,'Preferred') and contains(.,'Number')]";
	String lbPreferredEmailXpath="//div[@class='ci-row']//div[contains(.,'Preferred') and contains(.,'Email')]";
	String lbPreferredEmailWC1Xpath="//div[@class='ci-row'][@style='display: none;']//div[contains(.,'Preferred') and contains(.,'Email')]";
	String lbPreferredNumberWC1Xpath="//div[@class='ci-row'][@style='clear: both; display: none;']//div[contains(.,'Preferred') and contains(.,'Number')]";
	

	// Username, Email & Password Sub-section Login details section
	String hlinkUsernameEditId = "userNameEdit";
	String tbNewUsernameId = "userName";
	String tbConfirmNewUsernameId = "confirmUsername";
	String hlinkPasswordEditLinkId = "passwordEdit";
	String tbCurrentPasswordId = "currentPassword";
	String tbPasswordId = "password";
	String tbConfirmPasswordId = "confirmPassword";
	String lbErrorCurrentPasswordRequired = "//label[@class='error'][text()='Please enter your current password']";
	String lbErrorNewPasswordRequired = "//label[@class='error'][text()='Please enter a new password']";
	String lbErrorNewConfirmPasswordRequired = "//label[@class='error'][text()='Please confirm new password']";
	String lbErrorNewPassword7CharactersXpath = "//label[@class='error'][@for='password'][text()='Password has to be 7 characters']";
	String lbErrorNewConfirmPassword7CharactersXpath = "//label[@for='confirmPassword'][text()='Password has to be 7 characters']";
	String lbErrorPasswordNotMatchXpath = "//label[@class='error'][text()='Passwords do not match']";
	String lbPasswordStrengthBarWeak = "//span[@class='passwordbar weak']";
	String lbPasswordStrengthBarNormal = "//span[@class='passwordbar normal']";
	String lbPasswordStrengthBarMedium = "//span[@class='passwordbar medium']";
	String lbPasswordStrengthBarGood = "//span[@class='passwordbar good']";
	String lbPasswordStrengthBarStrong = "//span[@class='passwordbar strong']";

	// Personal Info

	String hlinkAddAdditionalEmailAddressLink = "+ Add additional email";

	String tbAlternate1EmailXpath = "tbd";
	String tbAlternate2EmailXpath = "tbd";
	String tbAlternate3EmailXpath = "tbd";
	String tbAlternate4EmailXpath = "tbd";
	String hlinkAlternate1EmailRemoveXpath = "tbd";
	String hlinkAlternate2EmailRemoveXpath = "tbd";
	String hlinkAlternate3EmailRemoveXpath = "tbd";
	String hlinkAlternate4EmailRemoveXpath = "tbd";
	String lbInvalidEmailErrorXpath = "//*[text()='Please enter a valid email']";
	String lbBlankEmailErrorXpath = "//*[text()='Please provide your email address.']";
	String lbDuplicateEmailErrorXpath = "tbd";
	String lbUsernameRequiredErrorXpath = "//label[@class='error'][text()='Username required']";
	String lbConfirmUsernameRequiredErrorXpath = "//label[@class='error'][text()='Confirm Username required']";

	String hlinkContactInfoLink = "Contact Info";

	String hlinkRemoveEmail2LinkXpath = "(//a[@data-bind='click: $root.removeEmail'])[2]";
	String hlinkRemoveEmail3LinkXpath = "(//a[@data-bind='click: $root.removeEmail'])[3]";
	String hlinkRemoveEmail4LinkXpath = "(//a[@data-bind='click: $root.removeEmail'])[4]";
	String hlinkRemoveEmail5LinkXpath = "(//a[@data-bind='click: $root.removeEmail'])[5]";

	/*String rbtnEmailOption1Id = "tbd";
	String rbtnEmailOption2Id = "tbd";
	String rbtnEmailOption3Id = "tbd";
	String rbtnEmailOption4Id = "tbd";
	String rbtnEmailOption5Id = "tbd";*/

	// Summary Page
	String iframeRememberMeLaterXpath = "//iframe[@class='cboxIframe']";
	String hlinkRemindMeLaterLink = "Remind me later";

}
