package com.pheps.ps.com.mobile.locators;

public interface MobilePaymentLocators {
	String tbCheckingRoutingNumberId="routing_number";
	String tbCheckingAccountNumberId="account_number";
	String chkboxAgreeTermsId="terms_check";
	String chkboxSaveCheckingAccountId="save_payment_method_check";
	String chkboxTurnAutopayOnId="autopay_check";
	String hlnkCheckingMakePaymentId="payment-btn";
	String hlnkCheckingCancelXpath="//div[@class='ui-cancel-container']/a";
	String tbCheckingPaymentAmountId="pay_amount";
	String lbPaymentCOnfirmationNumberXpath="//div[@class='r confnum']";
	String hlnkHomeId="home-btn";
	String lbMakePaymentId="makepayment";
	String rbtnNewCheckingAccountXpath="//input[@type='radio'][@value='new-checking']/..";
	String hlnkAllDeleteCheckingAccountsXpath="//a[@class='delete-icon']";
	
}
