package com.phelps.ps.com.locators;

/**
 * Basic home page
 * 
 * @author amodak
 * 
 */
public interface PaymentsLocators {

	String lnkPaymentsXpath = "//li[@id='menuPayment']/a";
	String lbPageVerificationXpath = "//div[contains(text(),'Payments')]";
	String lnkDeleteXpath = "//a[contains(text(),'Delete')]";
	String lnkManagePaymentMethodXpath = "//a[text()='Manage payment methods']";
	String lbPageVerificationMyPublicStorageAccountXpath = "//span[contains(text(),'My Public Storage Account')]";
	String lbPageVerificationManageMyPayementsXpath = "//p[contains(text(),'My Payment Methods')]";
	String btnMakePaymentXpath = "//span[@class='fRight']/a[@href='MakePay.aspx?pageid=makepay']";
	String lbPageVerificationMakePaymentXpath = "//p[contains(text(),'Make a Payment')]";

	String tbPayAmountId = "payvalue1";
	String lnkCardPaymentId = "ccard";
	String rbtnNewPaymentMethodId = "unsavedpaymethods";
	String rbtnSavedPaymentMethod = "savedpaymethods";
	String chboxSavePaymentMethodId = "chksavepaymethod";
	String chboxAutoPayId = "chkautopay";
	String lbCreditCardEntryXpath = "//p[contains(text(),'******')]";
	String lbCheckEntryXpath = "//p[contains(text(),'Checking')]";
	String btnPrintCopyId = "Printcopy";
	String btnMakePaymentSavedCreditCardXpath = "//*[@class='elementmakepaymentbutton']";
	String lbAmountLeftCss = ".fRight.totalHighlight";
	String btnManageAutoPayXpath = ".//*[@id='container']//span[1]//img";
	String btnTurnOffAutoPayXpath = ".//*[@id='autopay_turn_off']";
	String hlnkEditForPaymentMethod = ".//*[@id='paytable']//div[2]/a";
	String btnYesForAutoPayXpath = ".//*[@id='turnoffata']//input";
	String lbAutoPayPaymentMethodXpath = ".//*[@id='paytable']//div[1]//strong";
	String btnTurnOnAutoPayXpath = ".//*[@id='autopay_turn_on']";
	String hlnkCardPaymentAutoPayXpath = ".//*[@id='ccard']";
	String hlnkCheckingAccountAutoPayXpath = ".//*[@id='check']";
	String lbCardNumberXpath = ".//*[@id='cardNumber']";
	String drpDwnNewCardExpMonthXpath=".//*[@id='ddlExpirationMonth']";
	String drpDwnNewCardExpYearXpath=".//*[@id='ddlExpirationYear']";
	String tbNewCardCVVId="CVV";	
	String btniframeNewMakePaymentXpath=".//*[@id='submit']";
	String iframePaymentXpath=".//iframe[contains(@src,'https://certtransaction.hostedpayments.com/')]";
	String lbiframeErrorXpath="//span[@class='Error']";
	String lbUnitDetailsPaymentXpath=".//table[@class='tblMakePay']//td[@class='leftTableCell']/p[2]";	
	String lbLocationDetailsXpath=".//table[@class='tblMakePay']//td[@class='leftTableCell']/p[1]";
	String lbCheckErrorMsgId="errormsg";
	String btnAllMakePaymentXpath="//div[@class='mTop elementmakepaymentbuttoncontainer']/input[@class='elementmakepaymentbutton'][@value='Make Payment']";
	String chboxCheckAgreeTermid="termsagree";
	String lbNewCheckRoutingNumberId="routingnumber";
	String lbNewCheckConfRoutingNumberId="routingnumberReEnter";
	String lbNewCheckAccntNumberId="accountnumber";
	String lbNewCheckConfAccntNumberId="accountnumberReEnter";
	String hlnkAddPayMethodId="addPayMethod";
	String lbAllCreditCardAddedEntryXpath="//td[contains(text(),'******')]";
	String hlnkBackOnManagePayXpath="//a[text()='Back']";
	String btnModalTurnOnAutopayXpath=".//*[@id='checkbuttons']/a[@class='elementmakepaymentbutton']";
	
	
	
	
	
	//Delete Dialog Box
	String txtdeleteDialogXpath = "//div[@id='confirmdelete']/p";
	String btnDeleteButtonId = "cdeletenoautopay";

	String btnDeleteButtonAutoPayId = "cdelete";
	String btnDeleteModalXpath="//div[@id='confirmdelete']//div//input[@id='cdelete']";
	
	//Edit Payment
	String drpDwnExpMonthId = "ddlMonthCC";
	String drpDwnExpYearId = "ddlYearCC";
	String btnSaveName = "SaveEnable";
	String lnkEditName = "Edit";
	String lbCreditCardExpDateEditPageXpath = "//td[contains(text(),'Expires: 07/20')]";
	String lbCreditCardUpdatedId = "spansuccess";
	String lbPaymentAmountXpath = ".//*[@id='container']//td[4]//div[2]";
	String lbInsurenceCoverageAmountXpath = ".//*[@id='aspnetForm']//div[1]/p[3]/label[2]";
	
	//Aug Release
	
	String hlnkTOSXpath = ".//*[@id='payMethodContainer']//div[3]/a";
	String lbHeadingTOSXpath = ".//*[@id='termsAndConditions']/div/p[1]/strong";
	
}
