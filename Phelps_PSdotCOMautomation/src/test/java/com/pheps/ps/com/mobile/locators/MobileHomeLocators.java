package com.pheps.ps.com.mobile.locators;

public interface MobileHomeLocators {
	
	String hlnkContactUsId="contactLink";
	
	//Contact Us
	String tbFullNameId="customer_name";
	String tbPhoneNumberId="phone_number";
	String tbEmailAddressId="email_address";
	String ddTopicId="topic";
	String taMessageId="message";
	String hlnkContactSubmitId="contact-submit";
	String lbContactUsErrorXpath="//*[@class='error-txt contact-error'][@style='display: block;']";
	String lbSuccessMessageXpath="//*[@id='success-msg'][@style='display: block;']";

}
