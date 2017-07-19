package com.phelps.ps.com.locators;

public interface ReservationDetailsLocators {
	String tbFirstNameId = "fname";
	String tbLastNameId = "lname";
	String tbPhoneNumberId = "phone";
	String tbExtId = "ext";
	String tbEmailId = "emailaddress";
	String tbEmailConfirmId = "emailconfirm";
	String tbPasswordId = "password-fake";
	String tbConfirmPassowrdId = "passwordconfirm-fake";
	String tbMoveInDateId = "moveindate";
	String opHoldUnitId = "checkInType_hold";
	String opECIId = "checkInType_express";
	String opHoldUnitDisplayNoneXpath = "//div[@class='rdp_hold_eci_rb'][contains(@style,'display: none;')]";
	String btnHoldNowCompleteClass = "rdp_hold_sbmt";
	String btnHoldNowDisplayYesXpath="//input[@class='rdp_hold_sbmt'][@style='display: block;']";
	String btnHoldNowDisplayNoXpath="//input[@class='rdp_hold_sbmt'][@style='display: none;']";
	String propertyphotoXpath = ".//*[@id='propertyimage']";
	String propertyPhotoOnECIXpath = ".//*[@id='slideshow']/img";
	String ViewLocationIndoIframeXpath = ".//*[@class='cboxIframe']";
	String address1Id = "lbl_address";
	String adrress2Id = "lbl_address2";
	String imageEciRdpXpath = ".//*[@id='sc_payments']/div[2]/div[1]/table/tbody/tr[1]/td[1]/img";
	String imageRdpConfHoldXpath = ".//*[@id='sc_payments']/div[2]/div[1]/table/tbody/tr[1]/td[1]/img";
	String btnRdpExpressCheckinId = "rdp_ex_check";
	String imageEciReturnXpath = ".//*[@id='sc_payments']/div[2]/div[1]/table/tbody/tr[1]/td[1]/img";
	String imageEciRdpConfXpath = ".//*[@id='sc_payments']/div[2]/div[1]/table/tbody/tr[1]/td[1]/img";

	// Error
	String lbFirstNameErrorCss = ".rdp_val_msg.FirstNameErrorMessage";
	String lbLastNameErrorCss = ".rdp_val_msg.LastNameErrorMessage";
	String lbPhoneNumberErrorCss = ".rdp_val_msg.PhoneErrorMessage";
	String lbEmailErrorCss = ".rdp_val_msg.EmailErrorMessage";
	String lbConfirmEmailErrorCss = ".rdp_val_msg.EmailConfirmErrorMessage";
	String lbPasswordErrorCss = ".rdp_val_msg.PasswordErrorMessage";
	String lbConfirmPasswordCss = ".rdp_val_msg.PasswordConfirmErrorMessage";
	String lbMoveInDateErrorCss = ".rdp_val_msg.MIDErrorMessage";

	String tbPasswordXpath = ".//*[@id='password']";

	String lbStorageAddressXpath = ".//*[@id='sc_payments']/div[2]/div[2]";
	String lbSpaceSizeXpath = "//*[contains(text(),'Sq. Ft.')]";
	String hlinkWhyCreateAccountId = "whycreateaccount";
	String poWhyCreateAccountXpath = ".//*[@id='whycreateaccount_popup']/img[1]";
	String btnWhyCreatAccountCloseXpath = ".//*[@id='whycreateaccount_popup_x']";

	// View location info modal
	String lbStorageAddressOnLocationInfoXpath = "//*[text()='Public Storage']/..";
	String btnCloseLocationInfoId = "cboxClose";
	String lbStoragePriceXpath = ".//*[@id='col_left']/h3";

	// leftPane

	String hlnkChangeLocationId = "rdp_change_location_link";
	String hlnkChangeUnitId = "change_unit_link";

	// WC2

	String tbAccountPasswordXpath = "//input[@data-default-value='Create Account Password*']";
	String tbAccountConfirmPasswordXpath = "//input[@data-default-value='Confirm Password*']";
	String hlinkAlreadyCustomerlinkName = "Already a customer? Login Here.";
	String lbWhyCreateAccountXpath = ".//div[@class='sc_inner'][contains(.,'Why Create Account')]";
	String lbPasswordVelidationTextXpath = ".//div[@class='sc_inner'][contains(.,'Password must be at least')][contains(.,'7 characters.')]";

	String lbRDPHomeBreadCrumb = ".//*[@id='srp_brd_crmbs']//a[text()='Home']/..[contains(text(),'/ Search Results')]";
	String lbChangeUnitModalId = ".//*[@id='modal_change_unit']";
	String hlnkRDpStorageUnitsTabXpath = "//a[@class='ps-ui-tab left on']";

	String lbUncoveredParkingXpath = "//div[text()='Features:']/following-sibling::div[contains(text(),'Uncovered parking')]";
	String lbCoveredParkingXpath = "//div[text()='Features:']/following-sibling::div[contains(text(),'Covered parking')]";

	// Reservation Deposit
	String btnPaymentHoldNowXpath = ".//*[@id='submit']";
	String lbDepositAmount = "TBD";
	String lbTotalAmountXpath = "//span[text()='Total:']/following-sibling::span";
	String btnContinueCss = ".rdp_cnt_payment";
	String btnEditId="rdp_calendar_container_editLink";
	String lbRDDepositAmountXpath = "//span[text()='Deposit Amount Due Now:']/following-sibling::span";
	String lbRDDepositAmountCss = "#lblTotalValue";
	String lbRDPForFreeTextXpath = "//span[text()=\"Let's Get Started: Reserve Your Unit For FREE!\"]";
	String lbRDPHeaderTextXpath = "//span[text()=\"Let's Get Started: Reserve Your Unit\"]";
	String lbRDPSubHeaderNoCCXpath = "//*[contains(text(),'No Credit Card. No Commitment.')]";
	String lbRDPSubHeaderConvenientXpath = "//*[contains(text(),'Convenient Month-to-Month Lease.')]";
	String btnRDPInventoryContinueId = "TBD";
	String lbCalenderDepositTextXpath = "//div[contains(text(),'Dates in') and contains(.,'require a deposit')]//child::span[text()='green']";
	// String linkDepositDateXpath =
	// "//span[@class=''deposit-sign''][text()=''$'']/following-sibling::a[text()=''{0}'']";
	String linkDepositDateXpath = "//a[@class=''ui-state-default tooltipstered''][text()=''{0}'']";
	String lnkDepositTermsCss = ".deposit-terms-accordion-header>ul>li";
	String imgGreenCheckDepositXpath = "//img[@src='/images/rd_check_mark.png']";
	String lbDepositNowXpath = "//span[contains(text(),'Deposit Amount Due Now:')]";
	String optBtnsHoldECIDisplayNoXath="//div[@class='rdp_hold_eci_rb'][contains(@style,'display: none')]";
    String lbDepositFooterDaggerXpath="//*[@id='footer_deposit_terms_full']/sup[text()='†']";
    String lbDepositsizeGuideDaggerXpath="//span[@class='deposit_conditions_small']/sup[text()='†']";
    String lbDepositSizeGuideTermsTextXpath="//span[@class='deposit_conditions_small'][contains(.,'terms.')]";
//R$P
    String lbInventoryUnitTextXpath="//div[@class='rdp_deposit_info'][contains(.,\"You've chosen a unit that requires a small reservation deposit† due to high demand. This non-refundable deposit will be applied to your account after move-in.\")]";
    String lbPaymentMessageXpath="//div[@class='payment-message']/img[@src='/images/deposit-info.png']/following-sibling::span[text()='Please leave this window open until your payment is done processing.']";
    String imgCardTypeXpath="//img[@src='/images/credit-card.png']";
}
