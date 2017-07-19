package com.phelps.ps.com.locators;

public interface ReservationCarePageLocators {
	
	String lbReservationNoText="Reservation Number: ";
	String lbMyReservationNumberText="Manage Reservation Number ";
	String hLinkReservationCareChangeSizeLink="Change size";
	String hLinkReservationCareChangeLocationLink="Change location";
	String hLinkReservationCareChangeDateLink="Change date";
	String hLinkReservationCareCancelReservationLink="Cancel reservation";
	String hLinkReservationCareChooseAnotherReservationLink="Choose another reservation";
	String imgReserveAnotherUnitXpath="//img[@alt='Reserve Another Unit']";
	String lbReservationDetailsXpath = ".//*[@id='container']//p[contains(text(),'Reservation Details')]";
	String btnlLeftExpressCheckInXpath = ".//*[@id='container']//table[1]/tbody/tr[2]/td[5]/a";
	String btnRightExpressCheckInXpath = ".//*[@id='btn_express_checkin_pending']/a/img";
	String lbNotReservationLookingForXpath="//span[contains(.,\"Not the reservation you're looking for?\")]";
    String lbPageTitleXpath="//li[text()='Manage Your Reservation']";
	// Reservation details
	//String lbMoveInDateXpath = ".//*[contains(@id,'moveInDateSpan')]";
	String lbStorageSizeXpath = "//a[text()='Change size']/../../p[1]/strong";//"//a[text()='Change size']";
	String lbStorageAddressReservationCareXpath = ".//*[@id='container']//table[1]/tbody/tr[2]/td[3]/p[1]";
	String lbMonthlyRentXpath = ".//*[contains(text(),'Monthly Rent:')]/../p[2]";

	// right offer
	String lbPromotionXpath = ".//*[@id='newsticker-selfcare']/ul/li/p";
	
	String iFrameReservationCareXpath="//iframe[@class='cboxIframe']";
	String btnIframeCloseId="cboxClose";
	String lbAddMoveInDateText="Add Move-In Date to My Calendar";
	String lbMoveInDateReservationCareXpath=".//*[@id='home_container']/div[4]/div[2]/table[1]/tbody/tr[2]/td[1]/p[1]";
	String hlnkAddToCalendarId="ExportCal";
	String hlnkReserveAnotherUnitXpath="//img[@alt='Reserve Another Unit']/..";
	
	

}
