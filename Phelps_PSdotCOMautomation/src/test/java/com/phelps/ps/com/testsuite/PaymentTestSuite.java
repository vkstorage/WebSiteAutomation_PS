package com.phelps.ps.com.testsuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.PaymentsActions;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PaymentsLocators;

public class PaymentTestSuite extends BasicTestSuite
		implements HomeLocators, CommonLocators, PaymentsLocators, CommonSearchTextLocators {
	private String creditCardNumber = "5534684335886797";
	private String debitCardNumber = "4900770005624870";
	String routingNumber = "0632771003";
	String accountNumber = "123456789";

	@BeforeClass
	public void setUp() {
		navigator.login(paymentLoginUsername, "test123");
	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-1.1-Verify payment flow for
	 * new, not saved credit card
	 */
	@Test(priority = 1)
	public void verifyPaymentFlowForNewCreditCardTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.makePaymentWithCreditCard(creditCardNumber, "226", "6797", false, true);

	}

	/**
	 * Refers to test link TC - PS-45:TC-Payment-2.1-Verify payment flow and
	 * save credit card
	 */
	@Test(priority = 2)
	public void verifySavePaymentMethodCreditCardTest() {
		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		// paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.makePaymentWithCreditCard(creditCardNumber, "226", "6797", true, true);
		paymentActions.verifyCreditCardAdded(creditCardNumber);
	}

	/**
	 * Refers to test link TC -PS-46:TC-Payment-2.2-Verify that user is able to
	 * save multiple credit cards when saves payment method
	 */
	@Test(priority = 3)
	public void verifyMultipleSaveCreditCardsTest() {
		String creditCardNumber1 = "375484151689299";
		navigator.gotoMyAccountLoggedIn();
		paymentActions.clickOnPayments();

		paymentActions.clickOnManageMyPayments();
		paymentActions.clickOnAddPayMethod();
		paymentActions.clickOnCardPayment();
		paymentActions.enterCardDetails(creditCardNumber1);
		paymentActions.verifyCreditCardAddedInPaymentMethods(creditCardNumber1);
	}

	/**
	 * Refers to test Link TC - PS-47:TC-Payment-2.3-Verify that user is able to
	 * edit expiration date of credit card
	 */
	@Test(priority = 4)
	public void verifyEditCreditCardDetailsTest() {

		navigator.gotoPayments();
		paymentActions.clickEditPaymentLink();
		webAppDriver.selectByVisibleTextByLocatorId(drpDwnExpMonthId, "July");
		webAppDriver.selectByVisibleTextByLocatorId(drpDwnExpYearId, "2020");
		paymentActions.clickOnSaveButtonFromEditCreditCard();
		paymentActions.verifyCreditCardExpDate();
	}

	/**
	 * Refers to test Link TC - PS-48:TC-Payment-3.1-Verify payment flow for
	 * already saved credit card
	 */
	@Test(priority = 5)
	public void verifyPaymentUsingSaveCreditCardTest() {
		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.makePaymentWithSavedCreditCard("6797", true);

	}

	/**
	 * Refers to test Link TC - PS-49:TC-Payment-4.1-Verify that already saved
	 * payment method (Credit card) can be deleted
	 */

	@Test(priority = 6)
	public void verifyPaymentSavedCreditCardDeleteTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
	}

	// DEBIT CARD TCs
	/**
	 * Refers to test Link TC - PS-50:TC-Payment-5.1-Verify payment flow for
	 * new, not saved debit card
	 */
	@Test(priority = 7)
	public void verifyPaymentFlowForNewDebitCardTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.makePaymentWithCreditCard(debitCardNumber, "226", "4870", false, false);
	}

	/**
	 * Refers to test link TC - PS-51:TC-Payment-6.1-Verify payment flow and
	 * save debit card
	 */
	@Test(priority = 8)
	public void verifySavePaymentMethodDebitCardTest() {
		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		// paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.makePaymentWithCreditCard(debitCardNumber, "226", "4870", true, false);
		paymentActions.verifyCreditCardAdded(debitCardNumber);
	}

	/**
	 * Refers to test Link TC - PS-53:TC-Payment-6.3-Verify that user is able to
	 * edit expiration date of credit card
	 */
	@Test(priority = 9)
	public void verifyEditDebitCardDetailsTest() {

		navigator.gotoPayments();
		paymentActions.clickEditPaymentLink();
		webAppDriver.selectByVisibleTextByLocatorId(drpDwnExpMonthId, "July");
		webAppDriver.selectByVisibleTextByLocatorId(drpDwnExpYearId, "2020");
		paymentActions.clickOnSaveButtonFromEditCreditCard();
		paymentActions.verifyCreditCardExpDate();
	}

	/**
	 * Refers to test Link TC - PS-54:TC-Payment-7.1-Verify payment flow for
	 * already saved debit card
	 */
	@Test(priority = 10)
	public void verifyPaymentUsingSaveDebitCardTest() {
		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.makePaymentWithSavedCreditCard("4870", false);

	}

	/**
	 * Refers to test Link TC - PS-55:TC-Payment-8.1-Verify that already saved
	 * payment method (debit card) can be deleted
	 */
	@Test(priority = 11)
	public void verifyPaymentSavedDebitCardDeleteTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
	}

	// CHECK TCs
	/**
	 * Refers to test Link TC - PS-56:TC-Payment-9.1-Verify payment flow for
	 * new, not saved checking account
	 */
	@Test(priority = 12)
	public void verifyPaymentFlowForNewChequeTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.makePaymentWithCheque(routingNumber, accountNumber, "6789");
	}

	/**
	 * Refers to test Link TC - PS-57:TC-Payment-10.1-Verify payment flow and
	 * save checking account card
	 */
	@Test(priority = 13)
	public void verifyPaymentFlowAndSaveChequeTest() {
		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.makePaymentWithCheque(routingNumber, accountNumber, "6789");
		paymentActions.verifyCheckAdded(accountNumber);
	}

	/**
	 * Refers to test Link TC - PS-58:TC-Payment-11.1-Verify payment flow for
	 * already saved checking account
	 */
	 @Test(priority = 14)
	public void verifyPaymentUsingSavedCheckTest() {
		navigator.gotoPayments();
		paymentActions.clickOnMakePaymentButton();
		webAppDriver.findElementById(chboxCheckAgreeTermid).click();
		try {
			paymentActions.makePaymentWithSavedCheckingAccount("6789");
		} catch (Exception e) {

		}

	}

	/**
	 * Refers to test Link TC - PS-59:TC-Payment-12.1-Verify that already saved
	 * payment method (Checking account) can be deleted
	 */
	@Test(priority = 15)
	public void verifyPaymentSavedCheckDeleteTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
	}

	/**
	 * Refers to test Link TC - PS-266:TC-Payment-1.1-Autopay-Verify payment
	 * flow for new, not saved credit card
	 */
	@Test(priority = 16)
	public void verifyAutoPayPaymentFlowForNewCreditCardTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithCreditCard(creditCardNumber, "226", "6797", false, true);
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.enterCardDetails(creditCardNumber);
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-2.1-Autopay-Verify payment flow
	 * and save credit card
	 */
	@Test(priority = 17)
	public void verifyAutoPayPaymentFlowAndSaveCreditCardTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		// paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithCreditCard(creditCardNumber, "227", "6797", true, true);
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.clickSavedPaymentMethod();
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();

	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-3.1-Autopay-Verify payment flow
	 * for already saved credit card
	 */
	@Test(priority = 18)
	public void verifyAutoPayPaymentFlowAlreadySavedCreditCardTest() {
		navigator.gotoPayments();
		// paymentActions.clickManageAutoPayButton();
		// paymentActions.clickTurnOffAutoPayButton();
		// paymentActions.clickYesForAutoPay();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithSavedCreditCard("6797", true);
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.clickSavedPaymentMethod();
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();

	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-5.1-Autopay-Verify payment flow
	 * for new, not saved debit card
	 */
	@Test(priority = 19)
	public void verifyAutoPayPaymentFlowForNewDebitCardTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithCreditCard(debitCardNumber, "991", "4870", false, false);
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.enterCardDetails(debitCardNumber);
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
	}

	/**
	 * Refers to test Link TC - PS-270:TC-Payment-6.1-Autopay-Verify payment
	 * flow and save debit card
	 */
	@Test(priority = 20)
	public void verifyAutoPayPaymentFlowAndSaveDebitCardTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		// paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithCreditCard(debitCardNumber, "222", "4870", true, false);
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.clickSavedPaymentMethod();
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-7.1-Autopay-Verify payment flow
	 * for already saved debit card
	 */
	@Test(priority = 21)
	public void verifyAutoPayPaymentFlowAlreadySavedDebitCardTest() {
		navigator.gotoPayments();
		// paymentActions.clickManageAutoPayButton();
		// paymentActions.clickTurnOffAutoPayButton();
		// paymentActions.clickYesForAutoPay();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithSavedCreditCard("4870", false);
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.clickSavedPaymentMethod();
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-9.1-Autopay-Verify payment flow
	 * for new, not saved checking account
	 */
	@Test(priority = 22)
	public void verifyAutoPayPaymentFlowForNewCheckingAccountTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithCheque(routingNumber, accountNumber, "6789");
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.enterCheckingAccountDetails(routingNumber, accountNumber);
		// paymentActions.enterCardDetails(creditCardNumber);
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-10.1-Autopay-Verify payment
	 * flow and save checking account
	 */
	@Test(priority = 23)
	public void verifyAutoPayPaymentFlowAndSaveCheckingAccountTest() {
		navigator.gotoPayments();
		paymentActions.deletePaymentMethods();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.makePaymentWithCheque(routingNumber, accountNumber, "6789");
		// paymentActions.makePaymentWithCreditCard(creditCardNumber,"227");
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.clickSavedPaymentMethod();
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();

	}

	/**
	 * Refers to test Link TC - PS-44:TC-Payment-11.1-Autopay-Verify payment
	 * flow for already saved checking account
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 24)
	public void verifyAutoPayPaymentFlowAlreadySavedCheckingAccountTest() throws InterruptedException {
		navigator.gotoPayments();
		// paymentActions.clickManageAutoPayButton();
		// paymentActions.clickTurnOffAutoPayButton();
		// paymentActions.clickYesForAutoPay();
		paymentActions.clickOnPayments();
		paymentActions.clickOnMakePaymentButton();
		paymentActions.clickAutoPayCheckBox();
		paymentActions.clickAgreeTermsCheckBox();
		paymentActions.makePaymentWithSavedCheckingAccount("6789");
		webAppDriver.verifyElementPresentByXpath(
				"//p[contains(text(),'You have also successfully signed up for AutoPay.')]");
		paymentActions.clickOnPayments();
		paymentActions.verifyManageAutoPayButtonIsPresent();
		paymentActions.clickManageAutoPayButton();
		webAppDriver.verifyPresenceOfTextInPTagText("AutoPay Settings");
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
		paymentActions.clickTurnOnAutoPay();
		paymentActions.clickSavedPaymentMethod();
		paymentActions.verifyTurnOffAutoPayButtonIsPresent();
		paymentActions.verifyEditLinkForPaymentIsPresent();
		paymentActions.clickEditLink();
		paymentActions.clickCloseEditAutoPayModal();
		paymentActions.clickTurnOffAutoPayButton();
		paymentActions.clickYesForAutoPay();
		paymentActions.verifyAutoPayPaymentMethodIsNotVisible();

	}

	@Test(priority = 25)
	public void verifyPaymentMethodNotSavedTwice() {
		String creditCardNumber1 = "370448837568024";
		navigator.gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickPayment();
		paymentActions.clickOnManageMyPayments();
		paymentActions.clickOnAddPayMethod();
		paymentActions.enterCardDetails(creditCardNumber1);
		selfcareSummaryActions.clickPayment();
		paymentActions.verifyCreditCardAddedInPaymentMethods(creditCardNumber1);
		paymentActions.clickOnMakePaymentButton();
		// paymentActions.clickSavePaymentMethodCheckBox();
		paymentActions.makePaymentWithCreditCard(creditCardNumber1, "222", "6797", true, true);
		paymentActions.verifyCreditCardNotAddedTwice(creditCardNumber1);

	}
}
