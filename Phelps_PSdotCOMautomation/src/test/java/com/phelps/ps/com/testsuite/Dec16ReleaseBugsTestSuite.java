package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dec16ReleaseBugsTestSuite extends BasicTestSuite {

	Calendar c;
	String moveInDate;
	int tomorrowDate;
	String dayOfMoveInDate;
	int positionRDPProperty;
	int positionPLPUnit;
	String reservationNumber;
	static int j;

	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 6);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		positionRDPProperty = 0;
		positionPLPUnit = 0;

	}

	// Bug 3388 - Change 'All notifications will be sent to' link
	//@Test
	public void verifyNotificationEditTargetTest() {
		String userName = "insurancetest";
		String password = "test123";
		navigator.login(userName, password);
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickOnNotificationSubTab();
		webAppDriver.clickElementByLinkText("Edit");
		webAppDriver.verifyEqualsString(baseUrl + "/selfcare/addressandpersonalinfo.aspx",
				webAppDriver.getCurrentUrl());
	}

	// Bug 3379 - Extension for second reservation in the same session not saved

	//@Test
	public void verifyExtSecondReservationTest() {

		String fName = "Mike";
		String lName = "John";
		String phone = "765-676-5654";
		String email = "muzfera.naaz@phelpsagency.com";
		String ext = "234";
		String ext1 = "5678";
		String[] storageDetails = navigator.intoGetStartedPageWithStorage("la", 2, 1);
		reservationDetailsActions.enterAllReservationDetails(fName, lName, phone, ext, email, email, dayOfMoveInDate);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, fName, lName, ext, email, phone, dayOfMoveInDate);
		homeActions.clickOnReservationsLink();
		reservationCareActions.clickReserveAnotherUnit();
		storageDetails = navigator.intoGetStartedPageWithStorage("ga", 2, 1);
		reservationDetailsActions.enterAllReservationDetails(fName, lName, phone, ext, email, email, dayOfMoveInDate);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, fName, lName, ext, email, phone, dayOfMoveInDate);
	}
	
	//Bug 3351 - [INTERNAL] Copy update to zip level pages
	@Test
	public void verifyCopyUpdatesZLP(){
		webAppDriver.get(baseUrl+"/california/self-storage-martinez-ca/94553-self-storage");
		webAppDriver.verifyElementPresentByXpath("//h3[contains(text(),'Find other Zip Codes near')]");
		webAppDriver.verifyElementNotPresntByXpath("//h3[contains(text(),'Find other Zip Codes new')]");
		
	}
}
