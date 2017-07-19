package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class ReservationDepositsTestSuite extends BasicTestSuite implements ECIDetailsLocators, CommonLocators,
		ReservationCarePageLocators, SelfcareSummaryLocators, RDP_Conf_HoldLocators, ReservationDetailsLocators,
		ECI_RDP_ConfLocators, HomeLocators, CommonSearchTextLocators ,SearchResultsPageLocators,PLPLocators,PaymentsLocators{

	/*
	 * Reservation details
	 */
	String firstName = "Mary";
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String password = "test123";
	
	
	
	String depositRDPText = "Deposit Amount Due Now";
	
	String depositPaidText="Deposit Amount Paid";
	String paymentConfText = "Deposit Payment Confirmation";
	String AmountDueRDPText = "Amount Due at Move-In:";
	String AmountDueRCPText = "Amount Due at Move-In:";
	String TotalAmountDueECIText="Total cost to move in:";
	String depositTermsFooterText1="Terms and Conditions - Reservation Deposits:";
	String depositTermsSizeGuideText1="If applicable for your selected unit, see below for deposit";
	String depositTermsText1="A non-refundable reservation deposit is required for some reservations.";
	String depositTermsText2="This deposit will be applied as a credit to rent after move-in. Reservations are valid only for the reservation period specified.";
	String depositTermsText3="Payment of a reservation deposit guarantees a unit's availability during the reservation period for the price listed at the time the reservation is made.";
	String depositTermsText4="In the event a previously reserved unit or similar unit becomes unavailable, you agree that your only remedy is a refund of the reservation deposit.";
	String depositTermsText5="Otherwise, failure to enter a lease for a Public Storage unit within the reservation period results in forfeiture of the reservation deposit.";
String step1Text="Step 1 of 3";
String step2Text="Step 2 of 3";
String step3Text="Step 3 of 3";
	

	// current date

	/*
	 * ECI details
	 */

	String address1 = "3309";
	String address2 = "Alma Drive";
	String city = "Plano";
	String state = "TX";
	String zip = "75023";
	String idType = "US Passport";
	String idNumber = "XXX000111";
	String emgCntFirstname = "Bob";
	String emgCntLastName = "Smith";
	String relationship = "Employer";
	String emgCntPhoneNo = "705-285-4488";
	Calendar c;
	String moveInDate,moveInDate1;
	int tomorrowDate;
	String dayOfMoveInDate;
	String dayOfMoveInCalendar;
	List<CateredWebElement> allCoupons = new ArrayList<CateredWebElement>();
	String newURL;
	int positionRDPProperty;
	int positionPLPUnit;
	String reservationNumber;
	private String creditCardNumber = "5534684335886797";
	private String debitCardNumber = "4900770005624870";

	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		c.add(Calendar.DAY_OF_MONTH, 5);
		moveInDate1 = sdt.format(c.getTime());
		dayOfMoveInCalendar = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		positionRDPProperty = 0;
		positionPLPUnit = 4;

	}

	//@Test
	public void InventoryCreditDepositTest() {
		String depositAmount = "";
		String lastName = "green";
		
		double totalDueAmount;
		
		String email = envTestData.get("email");
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchTextInventoryRD1, 3, 1);
		Calendar now = GregorianCalendar.getInstance();
		webAppDriver.relax(500);
		
webAppDriver.verifySpanTagTextEquals(step1Text);
		reservationDetailsActions.enterFirstName(firstName);
		reservationDetailsActions.enterLastName(lastName);
		reservationDetailsActions.enterPhoneNumber(phoneNumber);
		reservationDetailsActions.enterExt(ext);
		reservationDetailsActions.enterEmail(email);
		reservationDetailsActions.enterConfirmEmail(email);

		reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, true);
		webAppDriver.relax(1000);
		
		
		depositAmount = reservationDetailsActions.getDepositAmount();
		totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
				depositAmount);
		webAppDriver.verifyTextContainInAnyTag(depositRDPText );
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		
		webAppDriver.verifySpanTagTextEquals(AmountDueRDPText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

		webAppDriver.verifyElementPresentByXpath(opHoldUnitDisplayNoneXpath);
		

		reservationDetailsActions.clickContinueR$P();
		webAppDriver.relax(500);
		webAppDriver.verifySpanTagTextEquals(step2Text);
		paymentActions.makePaymentWithCreditCardReservationDeposit(creditCardNumber, "222", "6797", depositAmount,
				false, false, true,false);
		webAppDriver.verifyTextContainInAnyTag(paymentConfText);
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(AmountDueRCPText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		webAppDriver.verifySpanTagTextEquals(step3Text);
		
		
		

		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
		

		// WC2 added
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();

		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber,
				moveInDate);

		/*
		 * 4. Click the ‘EXPRESS CHECK-IN’ button in the middle of the page.
		 */

		// commonActions.getURLWithOptimizelyTrue();
		rdp_Conf_HoldActions.clickExpressCheckIn();
		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);

		// Verify Deposit details
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		// Go to Pending Home Page-> ECI
		commonActions.clickPublicStorageIcon();
		homePageRefreshActions.clickOnECIOnPendingHomePage();
		
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		

		// Go to Reservation Care
		homePageRefreshActions.clickOnReservationsLink();
		webAppDriver.verifyPTagTextContains("Deposit Amount Paid");
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifyTextWithSpacePresentInAnyTag("-$"+depositAmount);
				webAppDriver.verifyDivTagTextContains("$"+totalDueAmount+"0*");
		
	

		reservationCareActions.clickRightExpressCheckIn();
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

		homePageRefreshActions.clickOnReservationsLink();
		reservationCareActions.clickRightExpressCheckIn();
		
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		/*
		 * 2. Complete all form fields. Confirm whether you can submit the form
		 * without completing all required fields.
		 */
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber,
				emgCntFirstname, emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		// eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss,
				"Thank you for completing your Express Check-in.");

		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber,
				moveInDate);
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		
		navigator.gotoReservationCareLogin();

		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);

		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);

		commonActions.clickPublicStorageIcon();

		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);

	}
	
	
	//@Test
	public void InventoryDebitDepositTest() {
		
			String depositAmount = "";
			String lastName = "green";
			
			double totalDueAmount;
			
			String email = envTestData.get("email");
			String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchTextInventoryRD2, 3, 2);
			Calendar now = GregorianCalendar.getInstance();
			webAppDriver.relax(500);

			// deposit terms to be added
			webAppDriver.clickElementByCss(lnkDepositTermsCss);
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
			
			reservationDetailsActions.enterFirstName(firstName);
			reservationDetailsActions.enterLastName(lastName);
			reservationDetailsActions.enterPhoneNumber(phoneNumber);
			reservationDetailsActions.enterExt(ext);
			reservationDetailsActions.enterEmail(email);
			reservationDetailsActions.enterConfirmEmail(email);

			reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, true);
			webAppDriver.relax(500);
			
			
			depositAmount = reservationDetailsActions.getDepositAmount();
			totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
					depositAmount);
			webAppDriver.verifyTextContainInAnyTag(depositRDPText );
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			
			webAppDriver.verifySpanTagTextEquals(AmountDueRDPText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

			webAppDriver.verifyElementPresentByXpath(opHoldUnitDisplayNoneXpath);
			reservationDetailsActions.clickContinueR$P();
			webAppDriver.relax(500);
			webAppDriver.verifySpanTagTextEquals(step2Text);
			

			paymentActions.makePaymentWithCreditCardReservationDeposit(debitCardNumber, "222", "4870", depositAmount,
					false, false, false,false);
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifySpanTagTextEquals(AmountDueRCPText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
	
			
			
			

			// deposit terms to be added
			webAppDriver.clickElementByCss(lnkDepositTermsCss);
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
			
			

			// WC2 added
			reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();

			webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
			rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber,
					moveInDate);

			/*
			 * 4. Click the ‘EXPRESS CHECK-IN’ button in the middle of the page.
			 */

			// commonActions.getURLWithOptimizelyTrue();
			rdp_Conf_HoldActions.clickExpressCheckIn();
			eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate);

			// Verify Deposit details
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
			
			/*webAppDriver.verifyDivTagTextContains(depositRDPText + depositAmount);
			webAppDriver.verifyDivTagTextContains(totalAmountDueText + totalDueAmount);*/
			
			// deposit terms to be added
			webAppDriver.clickElementByCss(lnkDepositTermsCss);
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
			
			// Go to Pending Home Page-> ECI
			commonActions.clickPublicStorageIcon();
			homePageRefreshActions.clickOnECIOnPendingHomePage();
			
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
			

			// Go to Reservation Care
			homePageRefreshActions.clickOnReservationsLink();
			webAppDriver.verifyPTagTextContains("Deposit Amount Paid");
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifyTextWithSpacePresentInAnyTag("-$"+depositAmount);
					webAppDriver.verifyDivTagTextContains("$"+totalDueAmount+"0*");
			
		

			reservationCareActions.clickRightExpressCheckIn();
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

			homePageRefreshActions.clickOnReservationsLink();
			reservationCareActions.clickRightExpressCheckIn();
			
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
			/*
			 * 2. Complete all form fields. Confirm whether you can submit the form
			 * without completing all required fields.
			 */
			eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber,
					emgCntFirstname, emgCntLastName, relationship, emgCntPhoneNo);
			eciDetailsActions.clickComplete();
			// eciDetailsActions.clickComplete();

			webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss,
					"Thank you for completing your Express Check-in.");

			eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber,
					moveInDate);
			webAppDriver.verifyTextContainInAnyTag(depositPaidText);
			webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
			webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
			webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
			webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
			
			// deposit terms to be added
			webAppDriver.clickElementByCss(lnkDepositTermsCss);
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
			webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
			
			navigator.gotoReservationCareLogin();

			webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);

			webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
			webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);

			commonActions.clickPublicStorageIcon();

			webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
			webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);


		}
	
	
	//@Test
	public void CalendarCreditDepositTest() {
		String depositAmount = "";
		String lastName = "green";
		
		double totalDueAmount;
		
		String email = envTestData.get("email");
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchTextCalendarRD3, 0, 0);
		Calendar now = GregorianCalendar.getInstance();
		webAppDriver.relax(500);
		
		webAppDriver.verifyElementNotPresntByXpath(lbDepositNowXpath);
		webAppDriver.verifyElementPresentByClassName(btnHoldNowCompleteClass);

		reservationDetailsActions.enterFirstName(firstName);
		reservationDetailsActions.enterLastName(lastName);
		reservationDetailsActions.enterPhoneNumber(phoneNumber);
		reservationDetailsActions.enterExt(ext);
		reservationDetailsActions.enterEmail(email);
		reservationDetailsActions.enterConfirmEmail(email);

		webAppDriver.verifyElementPresentById(opHoldUnitId);
		webAppDriver.verifyElementPresentById(opECIId);
		
		//Select a deposit date
		reservationDetailsActions.selectMoveInDate(dayOfMoveInCalendar, true);
		webAppDriver.relax(500);
		
		webAppDriver.verifyElementPresentByXpath(btnHoldNowDisplayNoXpath);
		
		webAppDriver.verifyElementPresentByXpath(optBtnsHoldECIDisplayNoXath);
		
		depositAmount = reservationDetailsActions.getDepositAmount();
		totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
				depositAmount);
		webAppDriver.verifyTextContainInAnyTag(depositRDPText );
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		
		webAppDriver.verifySpanTagTextEquals(AmountDueRDPText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
				
		reservationDetailsActions.clickContinueR$P();
				webAppDriver.relax(500);
				webAppDriver.verifySpanTagTextEquals(step2Text);
		

		paymentActions.makePaymentWithCreditCardReservationDeposit(creditCardNumber, "222", "6797", depositAmount,
				false, false, true,false);
		webAppDriver.verifyTextContainInAnyTag(paymentConfText);
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(AmountDueRCPText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		webAppDriver.verifySpanTagTextEquals(step3Text);
	
		
		
		

		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		

		// WC2 added
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();

		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber,
				moveInDate1);

		/*
		 * 4. Click the ‘EXPRESS CHECK-IN’ button in the middle of the page.
		 */

		// commonActions.getURLWithOptimizelyTrue();
		rdp_Conf_HoldActions.clickExpressCheckIn();
		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate1);

		// Verify Deposit details
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		/*webAppDriver.verifyDivTagTextContains(depositRDPText + depositAmount);
		webAppDriver.verifyDivTagTextContains(totalAmountDueText + totalDueAmount);*/
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		// Go to Pending Home Page-> ECI
		commonActions.clickPublicStorageIcon();
		homePageRefreshActions.clickOnECIOnPendingHomePage();
		
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		

		// Go to Reservation Care
		homePageRefreshActions.clickOnReservationsLink();
		webAppDriver.verifyPTagTextContains("Deposit Amount Paid");
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifyTextWithSpacePresentInAnyTag("-$"+depositAmount);
				webAppDriver.verifyDivTagTextContains("$"+totalDueAmount+"0*");
		
	

		reservationCareActions.clickRightExpressCheckIn();
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

		homePageRefreshActions.clickOnReservationsLink();
		reservationCareActions.clickRightExpressCheckIn();
		
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		/*
		 * 2. Complete all form fields. Confirm whether you can submit the form
		 * without completing all required fields.
		 */
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber,
				emgCntFirstname, emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		// eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss,
				"Thank you for completing your Express Check-in.");

		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber,
				moveInDate1);
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		
		navigator.gotoReservationCareLogin();

		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);

		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);

		commonActions.clickPublicStorageIcon();

		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);
	}

	

	//@Test
	public void CalendarDebitDepositTest() {
		
		String depositAmount = "";
		String lastName = "green";
		
		double totalDueAmount;
		
		String email = envTestData.get("email");
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(searchTextCalendarRD4, 1, 0);
		Calendar now = GregorianCalendar.getInstance();
		webAppDriver.relax(500);
		
		webAppDriver.verifyElementNotPresntByXpath(lbDepositNowXpath);
		webAppDriver.verifyElementPresentByClassName(btnHoldNowCompleteClass);

		reservationDetailsActions.enterFirstName(firstName);
		reservationDetailsActions.enterLastName(lastName);
		reservationDetailsActions.enterPhoneNumber(phoneNumber);
		reservationDetailsActions.enterExt(ext);
		reservationDetailsActions.enterEmail(email);
		reservationDetailsActions.enterConfirmEmail(email);

		webAppDriver.verifyElementPresentById(opHoldUnitId);
		webAppDriver.verifyElementPresentById(opECIId);
		
		//Select a deposit date
		reservationDetailsActions.selectMoveInDate(dayOfMoveInCalendar, true);
		webAppDriver.relax(500);
		
		webAppDriver.verifyElementPresentByXpath(btnHoldNowDisplayNoXpath);
		
		webAppDriver.verifyElementPresentByXpath(optBtnsHoldECIDisplayNoXath);
		
		depositAmount = reservationDetailsActions.getDepositAmount();
		totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
				depositAmount);
		webAppDriver.verifyTextContainInAnyTag(depositRDPText );
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		
		webAppDriver.verifySpanTagTextEquals(AmountDueRDPText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
				
		reservationDetailsActions.clickContinueR$P();
				webAppDriver.relax(500);
				webAppDriver.verifySpanTagTextEquals(step2Text);
		

		paymentActions.makePaymentWithCreditCardReservationDeposit(debitCardNumber, "222", "4870", depositAmount,
				false, false, false,false);
		webAppDriver.verifyTextContainInAnyTag(paymentConfText);
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(AmountDueRCPText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		webAppDriver.verifySpanTagTextEquals(step3Text);
	
		
		
		

		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		

		// WC2 added
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();

		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for choosing Public Storage!");
		rdp_Conf_HoldActions.verifyRDPConfHoldDetails(storageDetails, firstName, lastName, ext, email, phoneNumber,
				moveInDate1);

		/*
		 * 4. Click the ‘EXPRESS CHECK-IN’ button in the middle of the page.
		 */

		// commonActions.getURLWithOptimizelyTrue();
		rdp_Conf_HoldActions.clickExpressCheckIn();
		eci_ReturnsActions.verifyECIDetails(storageDetails, ext, firstName, lastName, email, phoneNumber, moveInDate1);

		// Verify Deposit details
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		/*webAppDriver.verifyDivTagTextContains(depositRDPText + depositAmount);
		webAppDriver.verifyDivTagTextContains(totalAmountDueText + totalDueAmount);*/
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		// Go to Pending Home Page-> ECI
		commonActions.clickPublicStorageIcon();
		homePageRefreshActions.clickOnECIOnPendingHomePage();
		
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		

		// Go to Reservation Care
		homePageRefreshActions.clickOnReservationsLink();
		webAppDriver.verifyPTagTextContains("Deposit Amount Paid");
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifyTextWithSpacePresentInAnyTag("-$"+depositAmount);
				webAppDriver.verifyDivTagTextContains("$"+totalDueAmount+"0*");
		
	

		reservationCareActions.clickRightExpressCheckIn();
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

		homePageRefreshActions.clickOnReservationsLink();
		reservationCareActions.clickRightExpressCheckIn();
		
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		/*
		 * 2. Complete all form fields. Confirm whether you can submit the form
		 * without completing all required fields.
		 */
		eciDetailsActions.enterAllMandatoryDetails(address1, address2, city, state, zip, idType, idNumber,
				emgCntFirstname, emgCntLastName, relationship, emgCntPhoneNo);
		eciDetailsActions.clickComplete();
		// eciDetailsActions.clickComplete();

		webAppDriver.verifyPresenceOfTextLocatedByCss(lnSuccessMsgCss,
				"Thank you for completing your Express Check-in.");

		eci_RetConfActions.verifyECIReturnDetails(storageDetails, ext, firstName, lastName, email, phoneNumber,
				moveInDate1);
		webAppDriver.verifyTextContainInAnyTag(depositPaidText);
		webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
		webAppDriver.verifyElementPresentByXpath(imgGreenCheckDepositXpath);
		webAppDriver.verifySpanTagTextEquals(TotalAmountDueECIText);
		webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");
		
		// deposit terms to be added
		webAppDriver.clickElementByCss(lnkDepositTermsCss);
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
		webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");

		
		navigator.gotoReservationCareLogin();

		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);

		webAppDriver.verifyElementNotPresntByXpath(btnlLeftExpressCheckInXpath);
		webAppDriver.verifyElementNotPresntByXpath(btnRightExpressCheckInXpath);

		commonActions.clickPublicStorageIcon();

		webAppDriver.verifyElementNotPresntByXpath(btnECIPendingHomeXpath);
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);

		}
	
	
	//@Test
	public void verifyReservationDepositSRPPLPTest(){
		navigator.gotoDefaultSearchResults(searchTextInventoryRD1);
		int count=webAppDriver.getTotalNumberOfElementsByXpath(lbAllLocationsXpath);
		int i=0;
		for( ;i<count;i++){
			if(webAppDriver.verifyElementIsPresentByXpath(".//*[@id='srp_item_"+i+"']//div[@class='deposit-text']")){
				break;
			}
		}
		
		int depoCount=webAppDriver.getTotalNumberOfElementsByXpath(".//*[@id='srp_item_"+i+"']//div[@class='deposit-text']");
	    webAppDriver.verifyTotalNumberOfElementPresentByXpath(".//*[@id='srp_item_"+i+"']//div[@class='deposit-text'][contains(.,'Deposit')]", depoCount);
	    webAppDriver.verifyTotalNumberOfElementPresentByXpath(".//*[@id='srp_item_"+i+"']//div[@class='deposit-text'][contains(.,'Required')]", depoCount);
	    webAppDriver.verifyTotalNumberOfElementPresentByXpath(".//*[@id='srp_item_"+i+"']//div[@class='deposit-text'][contains(.,'†')]", depoCount);
	    webAppDriver.verifyTotalNumberOfElementPresentByXpath(".//*[@id='srp_item_"+i+"']//img[@src='/images/icon_question_orange.png']", depoCount);
	    webAppDriver.clickElementByXpath("(.//*[@id='srp_item_"+i+"']//img[@src='/images/icon_question_orange.png'])[1]");
      for(int j=1;j<=depoCount;j++){
    	  webAppDriver.clickElementByXpath("(.//*[@id='srp_item_"+i+"']//img[@src='/images/icon_question_orange.png'])["+j+"]");
	    webAppDriver.verifyElementNotPresntByXpath(imgDepositPopUpDisplayNoneXpath);
	    webAppDriver.verifyElementPresentByXpath(imgDepoPopXpath);	
        webAppDriver.clickElementById(imgCloseDepoPopUpId);
      }
        webAppDriver.clickElementByXpath(".//*[@id='srp_item_"+i+"']//a[@class='show-all']");
        int plpDepoCount=webAppDriver.getTotalNumberOfElementsByXpath(lbDepositTextXpath);
        webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbDepositXpath,plpDepoCount);
        webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbRequiredTextxpath,plpDepoCount);
        webAppDriver.verifyTotalNumberOfElementPresentByXpath(imgDepositToolTipXpath,plpDepoCount);
        for(int j=0;j<plpDepoCount;j++){        
        webAppDriver.findAllElementsByXpath(imgDepositToolTipXpath).get(j).click();
        webAppDriver.verifyElementNotPresntByXpath(imgDepositPopUpDisplayNoneXpath);
        webAppDriver.verifyElementPresentByXpath(imgDepoPopXpath);
        webAppDriver.clickElementById(imgCloseDepoPopUpId);
        }
	
	
	}
	
	@Test
	public void verifyReservationDepositFooterTest(){
		navigator.gotoDefaultSearchResults(searchTextInventoryRD1);
		webAppDriver.verifyPresenceOfTextInSpanTag(depositTermsSizeGuideText1);
		webAppDriver.verifyElementPresentByXpath(lbDepositsizeGuideDaggerXpath);
		webAppDriver.verifyElementPresentByXpath(lbDepositSizeGuideTermsTextXpath);
		webAppDriver.verifyPresenceOfTextInPTagText(depositTermsFooterText1);
		webAppDriver.verifyElementPresentByXpath(lbDepositFooterDaggerXpath);
		webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText1);
		webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText2);
		webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText4);
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),\""+depositTermsText3+"\")]");
		int count=webAppDriver.getTotalNumberOfElementsByXpath(lbAllLocationsXpath);
		int i=0;
		for( ;i<count;i++){
			if(webAppDriver.verifyElementIsPresentByXpath(".//*[@id='srp_item_"+i+"']//div[@class='deposit-text']")){
				break;
			}
		}	
		
		//PLP
		 webAppDriver.clickElementByXpath(".//*[@id='srp_item_"+i+"']//a[@class='show-all']");
		 webAppDriver.verifyPresenceOfTextInPTagText(depositTermsFooterText1);
		 webAppDriver.verifyElementPresentByXpath(lbDepositFooterDaggerXpath);
			webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText1);
			webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText2);
			webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText4);
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),\""+depositTermsText3+"\")]");
	
	}
	
	//R$P page verification
	@Test
	public void verifyReservationDepositR$PTest(){
		String depositAmount = "";
		String lastName = "green";
		
		double totalDueAmount;
		
		String email = envTestData.get("email");
		navigator.intoGetStartedPageWithStorage(searchTextInventoryRD1, 2, 1);
		// deposit terms to be added
					webAppDriver.clickElementByCss(lnkDepositTermsCss);
					webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText1+"')]");
					webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText2+"')]");
					webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),\""+depositTermsText3+"\")]");
					webAppDriver.verifyElementPresentByXpath("//div[@style='display: block;'][contains(text(),'"+depositTermsText4+"')]");
					webAppDriver.verifyElementPresentByXpath(lbInventoryUnitTextXpath);
					webAppDriver.verifyElementPresentByXpath(imgCardTypeXpath);
					
					//deposit footer terms
					 webAppDriver.verifyPresenceOfTextInPTagText(depositTermsFooterText1);
					 webAppDriver.verifyElementPresentByXpath(lbDepositFooterDaggerXpath);
						webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText1);
						webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText2);
						webAppDriver.verifyPresenceOfTextInPTagText(depositTermsText4);
						webAppDriver.verifyElementPresentByXpath("//p[contains(text(),\""+depositTermsText3+"\")]");
					
					
					reservationDetailsActions.enterFirstName(firstName);
					reservationDetailsActions.enterLastName(lastName);
					reservationDetailsActions.enterPhoneNumber(phoneNumber);
					reservationDetailsActions.enterExt(ext);
					reservationDetailsActions.enterEmail(email);
					reservationDetailsActions.enterConfirmEmail(email);

					reservationDetailsActions.selectMoveInDate(dayOfMoveInDate, true);
					webAppDriver.relax(500);
					
					//webAppDriver.verifyTextPresentInAnyTag(text)
					
					
					depositAmount = reservationDetailsActions.getDepositAmount();
					totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
							depositAmount);
					webAppDriver.verifyTextContainInAnyTag(depositRDPText );
					webAppDriver.verifySpanTagTextEquals("-$"+depositAmount);
					
					webAppDriver.verifySpanTagTextEquals(AmountDueRDPText);
					webAppDriver.verifySpanTagTextEquals("$"+totalDueAmount+"0");

					webAppDriver.verifyElementPresentByXpath(opHoldUnitDisplayNoneXpath);
					reservationDetailsActions.clickContinueR$P();
					webAppDriver.relax(500);
					webAppDriver.verifySpanTagTextEquals(step2Text);
					webAppDriver.verifyElementPresentByXpath(lbPaymentMessageXpath);
					webAppDriver.verifyElementPresentByXpath(iframePaymentXpath);
					reservationDetailsActions.clickEditR$P();
					webAppDriver.verifyElementNotPresntByXpath(iframePaymentXpath);
					


	}
	
	
	@AfterMethod
	public void clearCache(){
		commonActions.clearChache();
	}
	
	
	
	
	
}
