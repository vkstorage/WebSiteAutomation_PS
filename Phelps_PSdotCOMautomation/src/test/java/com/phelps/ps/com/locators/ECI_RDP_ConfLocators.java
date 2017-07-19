package com.phelps.ps.com.locators;

public interface ECI_RDP_ConfLocators {

	String lbStorageAddressXpath = ".//*[@id='sc_payments']//*[contains(text(),'Public Storage')]";
	String lbStorageSizeXpath = ".//*[@id='sc_payments']//*[contains(text(),'Sq. Ft.')]";
	String lbMonthlyRentId = "monthly_rent";
	String lbUserInformationXpath = "//*[text()='Your Information']/../*[@class='user_overview']";
	String lbMoveInDateXpath=" .//*[@id='sc_payments']//*[text()='Move-In Date:']//..//div[2]";

	String lnSuccessMsgCss = ".rdp_form_top>div";
	
	


}
