package com.phelps.ps.com.locators;

public interface CommonLocators {
	// Header
	String btnMyAccountId = "at_ma";
	String btnMyAccountXpath="//*[@id='nav_acct']";
	String btnMyAccountLoggedInId="nav_acct";//"at_macc";
	String btnMyAccountLoginXpath="//div[@class='at_ma_link at_ma_link_login']/a";
	String btnMyAccountLoginId="nav_login";
	String btnPayMyBillId = "at_pb";
	String btnPayMyBillXpath = "//*[@id='at_pb']/a";
	String imgPSLogoXpath = ".//*[@id='logo']//a/img";
	String mlStorageId = "nav_starting_t";
	String mlMovingSuppliesId = "nav_boxes_t";
	String mlCompanyInfoId = "nav_comp_info_t";
	String lbLoginNameId = "at_loggedin";
	String hlinkLogoutId = "logout";
	String lbSpanishId = "buscar";
//WC2
	String btnReservationsId="nav_resv";//"at_rc";
	String iframeSelfcareSummaryIframeXpath="//iframe[@class='cboxIframe']";
	String hlinkRemindMeLaterlink="Remind me later";
	String btnOldReservationsId="at_rc";
	
//HomePageRefresh
	String btnMyAccountNewId="nav_acct";
	String hlinkLoginActiveXpath=".//div[@class='submenu orange activem']//a[@id='nav_login']";
	String hlinkCreateAccountActiveXpath=".//div[@class='submenu orange activem']//a[@id='nav_ca']";
	
	

}
