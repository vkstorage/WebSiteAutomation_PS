package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.BrowserType;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;

import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class ECIAutoLogoutTestSuite extends BasicTestSuite implements ECIDetailsLocators, CommonLocators,
 SelfcareSummaryLocators, RDP_Conf_HoldLocators, ReservationDetailsLocators,
ECI_RDP_ConfLocators, SelfCareContactInfoLocators{
	
	String firstName = "Mary";
	String lastName = new UniqueId(new UniqueId().id).charId;
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String email = "testlinkwc2guest123@gmail.com";
	String password = "1234567";
	// current date
	String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));

	CateredWebDriver secondDriver;
//B. For Reservation Details Confirmation page (RDP conf):
	
	String dayOfMoveInDate;
	Calendar c;
	String moveInDate;
	
	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 12);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

	}

	@Test
	public void verifyAutoLogoutForRDPConfTest() {
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchByZip);
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		webAppDriver.relax(960000);
		webAppDriver.navigate().back();
		webAppDriver.verifyElementIsInvisibleByXpath(".//*[@id='lets_get_started']/span");
	}

	// C1. For Express Check-In Confirmation page (ECI conf):
	@Test
	public void verifyAutoLogoutForExpressCheckinRDPTest() {

		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchByZip);
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, 	date);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		eciDetailsActions.verifyUserDetails(storageDetails, ext,email, firstName, lastName, phoneNumber,moveInDate);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		webAppDriver.relax(960000);
		webAppDriver.navigate().back();
		webAppDriver
				.verifyElementIsInvisibleByXpath(".//*[@id='aspnetForm']/div[4]/div[1]/div[2]/div[1]/table/tbody/tr/td[1]/div/strong");
	}

	// C2: For Express Check-In Confirmation page (ECI conf):
	@Test
	public void verifyAutoLogoutForExpressCheckinRDPFirefoxTest() {
		secondDriver = new CateredWebDriver(BrowserType.firefox);
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchByZip);
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		eciDetailsActions.verifyUserDetails(storageDetails,ext, email, firstName, lastName, phoneNumber,moveInDate);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		secondDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		secondDriver.relax(960000);
		secondDriver.navigate().back();
		secondDriver
				.verifyElementIsInvisibleByXpath(".//*[@id='aspnetForm']/div[4]/div[1]/div[2]/div[1]/table/tbody/tr/td[1]/div/strong");
	}

	// D1. Home page
	/*1. Make a reservation. You will be logged in. 
	2. Go the Home page from the RDP conf. Wait there for 10 minutes until you are logged out. 
	3. Attempt to use your browser's back button to go back to the Home page. You should not be able to.*/
	@Test
	public void verifyAutoLogOutECIRDPTest() {
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchByZip);
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		eciDetailsActions.verifyUserDetails(storageDetails,ext, email, firstName, lastName, phoneNumber,moveInDate);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		secondDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		secondDriver.relax(960000);
		secondDriver.navigate().back();
		secondDriver
				.verifyElementIsInvisibleByXpath(".//*[@id='aspnetForm']/div[4]/div[1]/div[2]/div[1]/table/tbody/tr/td[1]/div/strong");
	}

	// D1. Home page
	/*
	 * 1. Make a reservation. You will be logged in. 
	2. Go the Home page from the RDP conf. Wait there for 10 minutes until you are logged out. 
	3. Attempt to use your browser's back button to go back to the Home page. You should not be able to.*/
	@Test
	public void verifyAutoLogOutECIRDP2Test() {
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchByZip);
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		eciDetailsActions.verifyUserDetails(storageDetails,ext, email, firstName, lastName, phoneNumber,moveInDate);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		commonActions.clickPublicStorageIcon();
		webAppDriver.relax(960000);
		webAppDriver.navigate().back();
		webAppDriver.verifyPresenceOfTextInSpanTag("Sign In to Your Account");

	}

	// D2. Home page
	/*
	 1. Use IE.
	2. Make a reservation. You will be logged in. 
	3. Go the Home page from the RDP conf. Wait there for 10 minutes until you are logged out. 
	4. Attempt to use your browser's back button to go back to the Home page. You should not be able to.*/
	@Test
	public void verifyAutoLogoutECIRDPForIETest() {
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchByZip);
		String date = String.valueOf(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		String address1 = "901";
		String address2 = "Wilshire Boulevard";
		String city = "Santa Monica";
		String state = "CA";
		String zip = "90401";
		String idType = "US Passport";
		String idNumber = "XXX000111";
		String emgCntFirstname = "Bob";
		String emgCntLastName = "Smith";
		String relationship = "Employer";
		String emgCntPhoneNo = "705-285-4488";
		secondDriver = new CateredWebDriver(BrowserType.IE);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email,	date);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		eciDetailsActions.verifyUserDetails(storageDetails,ext, email, firstName, lastName, phoneNumber,moveInDate);
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber, emgCntFirstname,
				emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		commonActions.clickPublicStorageIcon();
		secondDriver.relax(960000);
		secondDriver.navigate().back();
		secondDriver.verifyPresenceOfTextInSpanTag("Sign In to Your Account");
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeSecondBroser() {
		if (secondDriver != null) {
			secondDriver.quit();

		}
	}

}
