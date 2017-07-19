package com.phelps.ps.com.locators;

public interface ECIDetailsLocators {
	String hlinkTopClickHereXpath = ".//*[@id='confirmreservationlink']/a";
	String hlinkBottomClickHereXpath ="//div[@class='rdp_cc rdp_mc']/div[2]//a";
	//".//*[@id='confirmreservationlink2']/a";
	String tbAddress1Id = "address";
	String tbAddress2Id = "address2";
	String tbCityId = "city";
	String ddStateId = "state";
	String tbZIPId = "zip";
	String ddIdTypeId = "idtype";
	String ddIdentificationStateId = "ddlIdentificationState";
	String tbIDNumberId = "idnumber";
	String lbEmailXpath = ".//*[@id='container']//table[@class='eci_info']//span[text()='Email: ']/..";
	String lbFullNameXpath=".//*[@id='container']//table[@class='eci_info']//td[@class='bold']";
	String lbPhoneXpath = ".//*[@id='container']//span[text()='Phone: ']/..";
	String lbMoveInDateXpath = ".//*[@id='container']//*[text()='Move-In Date:']/..";
	String lbSuccessMessageXpath = "//div[contains(text(),'Thank you for choosing Public Storage')]";
	// Error
	//WC2 changed css
	String lbAddress1ErrorCss = ".rdp_val_msg_l.rdp_val_msg[data-input='address']";
	String lbCityErrorCss = ".rdp_val_msg_l.rdp_val_msg[data-input='city']";
	String lbStateErrorCss = ".rdp_val_msg.error-split[data-input='state']";
	String lbZipErrorCss = ".rdp_val_msg.error-split[data-input='zip']";
	String lbIdentificationTypeErrorCss = ".rdp_val_msg_l.rdp_val_msg[data-input='idtype']";
	String lbIdNumberErrorCss = ".rdp_val_msg_l.rdp_val_msg[data-input='idnumber']";
	String lbEmgContactFnameErrorCss = ".rdp_val_msg_l.rdp_val_msg[data-input='firstName']";
	String lbEmgContactLnameErrorCss = ".rdp_val_msg_r.rdp_val_msg[data-input='lastName']";
	String lbRelationshpErrorCss = ".rdp_val_msg_l.rdp_val_msg[data-input='relationship']";
	String lbPhoneNumnerErrorCss = ".rdp_val_msg_r.rdp_val_msg[data-input='phone']";

	// Emergency contact
	String tbEmCntFirstNameId = "firstName";
	String tbEMCntLastNameId = "lastName";
	String ddEmCntRelationShipId = "relationship";
	String tbEmCntPhoneId = "phone";
	String btnCompleteXpath = ".//*[@id='aspnetForm']//input[@type='submit']";

	// Left pane
	String lbStorageAddressXpath = ".//*[@id='sc_payments']//*[contains(text(),'Public Storage')]";
	String lbSpaceSizeXpath = ".//*[@id='sc_payments']//*[contains(text(),'Sq. Ft.')]";
	String lbMonthlyRentId = "monthly_rent";
	String lbSpecialSummerRentXpath="//span[text()='Summer Kick-Off Sale']/following-sibling::span";

	// View location
	String iframeViewLocationInfo = "cboxIframe";
	String linkViewLocationInforId = "view_location_info";
	
//WC2
	String lbECIPagePhoneNumberXpath="//span[text()='Phone: ']/..";
	String lbECIPageReservationNumberXpath="//span[text()='Reservation Number: ']/..";
	String lbECIRCPReservationNumberXpath="//span[text()='Your Reservation Number:']/../span[2]";
	String lbECIPageMoveINDateXpath="//span[text()='Move-In Date: ']/..";
	String lbECIPageEmailXpath="//span[text()='Email: ']/..";

}
