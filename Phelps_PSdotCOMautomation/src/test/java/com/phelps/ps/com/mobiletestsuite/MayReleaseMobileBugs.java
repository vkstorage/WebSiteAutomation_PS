package com.phelps.ps.com.mobiletestsuite;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.phelps.ps.com.mobile.actions.MobileLoginActions;

public class MayReleaseMobileBugs extends BasicMobileTestSuite {

	// @Test(priority = 0)
	public void VerifyMobileHardCodeLedgerChangesNoDueTest() {
		

		List<String> noDueUsers = new ArrayList<String>();
		String decimalAmountValue = "";
		String expectedAmountValue;
		/*
		 * noDueUsers.add("debbygriffin84@yahoo.com");
		 * noDueUsers.add("bneman@aerovoice.com");
		 */

		noDueUsers.add("AlfredCasazza");
		noDueUsers.add("errolc");
		/*
		 * noDueUsers.add("debbygriffin84@yahoo.com");
		 * noDueUsers.add("debbygriffin84@yahoo.com");
		 */

		for (String nodue : noDueUsers) {
			navigator.gotoMobileAccountIntoPage(nodue, "test123");
			// homeActions.clickMyAccount();

			webAppDriver.verifyElementPresentByXpath("//div[text()='Next Payment:']");

			String nextPaymeny$ = webAppDriver.findElementByXpath(".//*[@id='account']//div[text()='Next Payment:']/../div[@class='r']")
					.getText().trim();
			String nextPaymeny = nextPaymeny$.replace("$", "");
			Float nextPaymentExactAmount = Float.parseFloat(nextPaymeny);
			Float totalPayment = nextPaymentExactAmount;

			String expectedPaymentAmount = "$" + totalPayment.toString();
			expectedAmountValue = expectedPaymentAmount.split("\\.")[0];

			decimalAmountValue = expectedPaymentAmount.split("\\.")[1];
			if (decimalAmountValue.length() < 2)
				decimalAmountValue = decimalAmountValue + "0";

			String actualPaymentAmount = webAppDriver.findElementByXpath("//div[@class='r total']").getText().trim();
			String decimalActual = actualPaymentAmount.split("\\.")[1];
			actualPaymentAmount = actualPaymentAmount.split("\\.")[0];
			webAppDriver.verifyEqualsString(expectedAmountValue, actualPaymentAmount);
			webAppDriver.verifyEqualsString(decimalAmountValue, decimalActual);
			mobileAccountInfoActions.clickOnLogout();

		}

		// to verify payment
		// webAppDriver.findElementById(tbPayAmountId).clear();
		// webAppDriver.enterTextToElementById(tbPayAmountId, totalPayment);
		//mobileAccountInfoActions.clickOnLogout();
	}

	// Bug Bug 2958 - Hard code ledger changes (PS-0438) (//p[@class='fRight
	// totalHighlight'])[1]
	//@Test(priority = 1)
	public void VerifyMobileHardCodeLedgerChangesPastDueTest() {
		List<String> pastDueUsers = new ArrayList<String>();
		String decimalAmountValue = "";
		String expectedAmountValue;
		pastDueUsers.add("darrenjwdanks@gmail.com");
		// pastDueUsers.add("buddhaspring81@gmail.com");
		/*
		 * NoDueUsers.add("debbygriffin84@yahoo.com");
		 * NoDueUsers.add("debbygriffin84@yahoo.com");
		 * NoDueUsers.add("debbygriffin84@yahoo.com");
		 */
		for (String nodue : pastDueUsers) {
			navigator.gotoMobileAccountIntoPage(nodue, "test123");
			// homeActions.clickMyAccount();

			webAppDriver.verifyElementPresentByXpath("//div[text()='Past Due Rent:']");
			webAppDriver.verifyElementPresentByXpath("//div[text()='Fees and/or Other Charges:']");

			/*
			 * String dueAmount$ = webAppDriver.findElementByXpath(
			 * ".//*[@id='account']//div[text()='Past Due Rent:']/../div[@class='r']"
			 * ).getText().trim(); String dueAmount = dueAmount$.replace("$", "");
			 * Float dueExactAmount = Float.parseFloat(dueAmount);
			 */

			String pastDueRent$ = webAppDriver.findElementByXpath(".//*[@id='account']//div[text()='Past Due Rent:']/../div[@class='r']")
					.getText().trim();
			String pastDueRent = pastDueRent$.replace("$", "");
			String feesExtraCharges$ = webAppDriver
					.findElementByXpath(".//*[@id='account']//div[text()='Fees and/or Other Charges:']/../div[@class='r']").getText().trim();
			String feesExtraCharges = feesExtraCharges$.replace("$", "");
			Float pastExactAmount = Float.parseFloat(pastDueRent);
			Float feesExtraChargesExact = Float.parseFloat(feesExtraCharges);
			Float totalDueAmount = pastExactAmount + feesExtraChargesExact;
			String totalDueAmount$ = "$" + totalDueAmount.toString();
			// String dueAmount$ =
			// webAppDriver.findElementByXpath("(//p[@class='fRight totalHighlight'])[1]").getText().trim();
			if (totalDueAmount$.split("\\.")[1].length() < 2)
				totalDueAmount$ = totalDueAmount$ + "0";
			// webAppDriver.verifyEqualsString(dueAmount$, totalDueAmount$);
			/*
			 * String nextPaymeny$ =
			 * webAppDriver.findElementByXpath("(//p[@class='fRight totalHighlight'])[2]"
			 * ).getText().trim(); String nextPaymeny = nextPaymeny$.replace("$", "");
			 * Float nextPaymentExactAmount = Float.parseFloat(nextPaymeny); Float
			 * totalPayment = dueExactAmount + nextPaymentExactAmount;
			 */
			String expectedPaymentAmount = totalDueAmount$.replace("$", "");
			expectedAmountValue = totalDueAmount$.split("\\.")[0];

			decimalAmountValue = expectedPaymentAmount.split("\\.")[1];
			if (decimalAmountValue.length() < 2)
				decimalAmountValue = decimalAmountValue + "0";

			String actualPaymentAmount = webAppDriver.findElementByXpath("//div[@class='r total']").getText().trim();
			String decimalActual = actualPaymentAmount.split("\\.")[1];
			actualPaymentAmount = actualPaymentAmount.split("\\.")[0];
			webAppDriver.verifyEqualsString(expectedAmountValue, actualPaymentAmount);
			webAppDriver.verifyEqualsString(decimalAmountValue, decimalActual);
		}
		mobileAccountInfoActions.clickOnLogout();


	}

	@Test
	public void verifyMobileVerifyHardCodeLedgerPaymentTest() {
		String username = "jonboyer1@hotmail.com";
		navigator.gotoMobilePaymentPage(username, "test123");
		mobilePaymentActions.clickOnCheckingPaymentMethod();
		mobilePaymentActions.clickOnNewCheckingPayment();
		mobilePaymentActions.enterCheckingPaymentAmount("1.00");
		mobilePaymentActions.enterCheckingRoutingNumber("063277100");
		mobilePaymentActions.enterCheckingAccountNumber("123456789");
		mobilePaymentActions.clickOnTermsCondition();
		mobilePaymentActions.clickOnMakePaymentButton();
		webAppDriver.verifyPresenceOfTextInDivTagText("Thank you for your payment");
		webAppDriver.verifyPresenceOfTextInDivTagText("Payment Successful");
		webAppDriver.verifyPresenceOfTextInH1Tag("Payment Confirmation");
		mobilePaymentActions.clickOnHomeOnPaymentConfirmationPage();
	}

}
