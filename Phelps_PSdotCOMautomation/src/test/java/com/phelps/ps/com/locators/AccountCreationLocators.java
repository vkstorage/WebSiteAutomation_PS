package com.phelps.ps.com.locators;

public interface AccountCreationLocators {
	
	String accountCreationEmailedLinkURL="tbd";
	String tbEmailAddressId="emailAddress";
	String tbAccountNumberId="accountNumber";
	String lbCreateAccountText="Create Your Online Account";
	String btnCreateAccountSubmitXpath="//input[@class='submit-btn']";
	String tbCreateAccountUsernameId="username";
	String tbCreateAccountPasswordId="password";
	String tbCreateAccountConfirmPasswordId="confirmPassword";
	String tbCreateAccountConfPasswordXpath="tbd";
	String btnCreateAccountEnterDetailsSubmit="tbd";
	String imgPropertyPhotoXpath="tbd";
	String lbReservationLookUpCopy1Xpath="//div[@class='content_main_header_left'][contains(.,'Thank you for moving in! Welcome to Public Storage.')]";
	String lbReservationLookUpCopy2Xpath="//div[@class='content_main_header_left'][contains(.,'Please enter your Email Address and Account Number to create your Online Account.')]";
	String lbReservationLookUpCopy3Xpath="//div[@class='content_main_header_left'][contains(.,'to create your Online Account.')]";
	String lbReservationLookUpCopy4Xpath="//div[@class='content_right'][contains(.,\"With an Online Account you'll be able to:\")]";
	String lbReservationLookUpCopy5Xpath="//div[@class='secondary_header'][contains(.,'Enter Your Information')]";
  String lbOnlineAccountExistErrorXpath="//div[@class='form_error'][contains(.,'An Online Account already exists with that Email Address and/or Account Number. Please login')]";//needs to change
  String ttAccountNumberxpath="//img[@class='tooltip-interactive tooltipstered']";
  String ttAccountNumberCopyXpath="//div[@class='tooltipster-content']";
  String hlinkttAccountNumberXpath="//div[@class='tooltipster-content']/a";
  String ttEmailAddressXpath="//img[@class='tooltip tooltipstered']";
  String ttusernameXpath="//*[@id='username']/..//img[@class='tooltip tooltipstered']";
  String ttPasswordXpath="//*[@id='password']/..//img[@class='tooltip tooltipstered']";
  
  
  String lbEnterDetailsCopy1Xpath="//div[@class='clear content_input'][contains(.,'Please create a Username and Password for your Online Account:')]";
  String lbPasswordStrengthWeakXpath="//span[@class='passwordtext weak']";
  String lbPasswordStrengthNormalXpath="//span[@class='passwordtext normal']";
  String lbPasswordStrengthMediumXpath="//span[@class='passwordtext medium']";
  String lbPasswordStrengthGoodXpath="//span[@class='passwordtext good']";
  String lbPasswordStrengthStrongXpath="//span[@class='passwordtext strong']";
  String barPasswordWeakXpath="//span[@class='passwordbar weak']";
  String barPasswordNormalXpath="//span[@class='passwordbar normal']";
  String barPasswordMediumXpath="//span[@class='passwordbar medium']";
  String barPasswordGoodXpath="//span[@class='passwordbar good']";
  String barPasswordStrongXpath="//span[@class='passwordbar strong']";
	String tbCreateAccountConfPasswordId="password";

	


}
