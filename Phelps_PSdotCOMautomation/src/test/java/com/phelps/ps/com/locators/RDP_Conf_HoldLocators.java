package com.phelps.ps.com.locators;

public interface RDP_Conf_HoldLocators {
	String btnExpressCheckInId = "rdp_ex_check";

	// left pane
	String lbHoleStorageAddressXpath = ".//*[@id='sc_payments']//*[contains(text(),'Public Storage')]";
	String lbHoleMoveInDateXpath = ".//*[@id='sc_payments']//*[contains(text(),'Move-In Date')]/../div[2]";
	String lbHoldStorageSizeXpath = ".//*[@id='sc_payments']//*[contains(text(),'Sq. Ft.')]";
	String lbHoldStorageAccountNumberXpath = ".//*[@id='aspnetForm']//*[contains(text(),'Account#')]/..";
	String lbHoldConfirmationNumberXpath = ".//*[@id='aspnetForm']/div[3]//*[contains(text(),'Confirmation#')]/..";
	String lbMonthlyRentXpath = "//div[contains(text(),'Rent')]";
	String lbMoveInDateXpath=".//*[@id='sc_payments']//*[text()='Move-In Date:']/..//div[2]";
	String hlinkShopNowCss=".shop-now";
	String linktextPackingTips="Useful Packing Tips";
	// User information
	String lbUserInformationXpath = "//div[text()='Your Information']/../*[@class='user_overview']";
	
	//WC2 modification (used in WC1 also)
	String lbRDPConfirmationAccountNumberXpath = ".//span[contains(text(),'Account#:')]/..";
	String lbRDPConfirmationConfirmationNumberXpath = ".//span[contains(text(),'Confirmation#:')]/..";
	String lbRDPConfirmationUsernameXpath = ".//span[contains(text(),'Username:')]/..";
	String lbRDPConfirmationPasswordXpath = ".//span[contains(text(),'Password:')]/..";

	// View location info modal

	String lbPublicStorageCss = "h3.topheading";
	
	//WC2
	
	String lbConfirmationReservationNumberXpath="//span[contains(text(),'Your Reservation Number:')]/../span[2]";
	
	
	String lbYourReservationNoSpanText="Your Reservation Number:";
	String hlinkManageReservationLink="Manage Reservation";
	
	//Reservation Deposit
	String lbDepositconfText="Deposit Payment Confirmation";
	String hlnkPrintConftext="print this confirmation";
	String lbReservationDeposit="Reservation Deposit : ";

}
