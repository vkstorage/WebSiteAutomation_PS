package com.phelps.ps.com.locators;

public interface SelfcareSummaryLocators {

	String hlinkReservationsId = "menuReservations";
	String hlinkSummaryId = "menuSummary";

	String hlinkReservationsLink = "Reservations";
	String hlinkContactInfoId = "menuContact";
	
	String lbNameId = "at_loggedin";
	String lbHelloUserXpath = ".//*[@id='at_loggedin']";
	
	String hlinlPublicStoraheOnlineAccountFeaturesId = "sc_oaf";
	String lbContactInformationXpath = ".//*[@id='sc_oaf_slide']/ul/li[1]/h4/a";
	String hlnkFrequentlyAskedQuestionsId = "sc_faq";
	String lbHowDoIXpath = ".//*[@id='sc_faq_slide']/ul";
	
	/*
	 * Payment section
	 */
	 
	 String lbPaymentsClass = "fLeft sc_mhi";
	 String lbNextPaymentCss = "#sc_next_payment_info";
	 String btnPaymentCss = "#sc_pay_autopay>a>img";
	 String hlnkPaymentXpath = ".//*[@id='menuPayment']/a";
	 String hlnkViewFullHistoryXpath = ".//*[@id='aspnetForm']/div[3]/div[2]/div[3]/div[1]/p/span[3]/a";
	 String hlnkMakeThisPaymentXpath = ".//*[@id='aspnetForm']/div[3]/div[2]/div[2]/span/a[1]";
	 String hlnkAddToMyCalenderXpath = ".//*[@id='A3']";
	 String hlnkEditNextToPaymentXpath = "//a[contains(@href,'PaymentMethodsAdd.aspx')][contains(.,'Edit')]";
	 String hlnkManagePaymentMethodXpath = ".//*[@id='aspnetForm']/div[3]/div[2]/div[3]/div[2]/p/span[3]/a";
	 String lbPaymentHistory =".//*[@id='aspnetForm']/div[3]/p";
	 String lbAddPaymentDateToMyCalender =".//*[@id='AddToCalendarDiv']/p/strong";
	 String lbMyPaymentMethods =".//*[@id='aspnetForm']/div[3]/div[3]/p[1]";
	 String hlnkbackOfHistory =".//*[@id='container']/div[4]/div[1]/div[3]/a";
	 String btnCloseOnAddPaymentDateToMyCalender =".//*[@id='cboxClose']";
	 String hlnkBackOfEditAndManagePayment =".//*[@id='aspnetForm']/div[3]/div[2]/a";
	 

	/*
	 * My Account section
	 */
	String lbNameCss = ".sc_uname";
	String lbEmailCss = ".sc_em>strong";
	String lbAccountNumberXpath = ".//*[@id='sc_an_em']/div[1]";
	
	/*
	 * Storage Unit Section
	 */
	String lbUnitNumberSizeCss=".sc_grey_bold_14.fLeft";
	String lbGateCodeCss=".sc_grey_bold_14.fRight";
	String lbAddressHoursCss=".address_hours";
	String lbReviewLinkXpath=".//*[@class='reviews_link']";

	/*
	 * Reservations
	 */
	String lbMoveInDateXpath = "//*[text()='Move In:']/..";
	String lbStorageAddressXpath = ".//*[@id='sc_reservations']//*[@class='address_hours']";
	String lbStorageAddressAndSizeCss = ".sc_grey_bold_14.fLeft";
	String hlinkReservationDetailsXpath = ".//*[@id='sc_reservations']//*[text()='Details']";
	
	String imgLargePhotoXpath = ".//*[@id='slideshow']/img";
	String lbAddressForPhotoXpath = ".//*[@id='aspnetForm']//table[1]//td[3]/p[1]";
	
	String imgPhotoXpath = ".//*[@id='sc_reservations']//div[1]/img";
	String lbAddressXpath = ".//*[@id='sc_reservations']//div[@class='address_hours']";
	
	String imgPhotoThumb1Xpath = ".//*[@id='slideshow']//a[1]/img";
	String imgPhotoThumb2Xpath = ".//*[@id='slideshow']//a[2]/img";
	String imgPhotoThumb3Xpath = ".//*[@id='slideshow']//a[3]/img";
	String imgPhotoThumb4Xpath = ".//*[@id='slideshow']//a[4]/img";
	String imgPhotoThumb5Xpath = ".//*[@id='slideshow']//a[5]/img";
	
	/*
	 * Contact Info Link
	 */
	String lbContactInfoCss = ".sectionTitle.fLeft";
	String lbAccountNumberOnContactInfoLinkXpath = ".//*[@id='home_container']/div[4]/p/span[3]";

	String hlnkPersonalInfoXpath = "//li[text()='Personal Info']";

	String hlnkAddressAndPersonalInfoXpath = ".//*[@id='container']/div[4]/div[2]/div[2]/ul/li[1]";
	//.//*[@id='container']/div[4]/div[2]/div[2]/ul/li[1]/u changed for WC2
	String tbAddressContactInfoXpath = "(//input[@data-bind='value: Address1'])[1]";//Street1-changed for WC2
  String tbAddressContactInfoId="Street1";
	String tbCityAndStateXpath = "(//input[@data-bind='value: City'])[1]";// was City
	String tbCityAndStateId="City";
	String tbZipContactInfoXPath = "(//input[@data-bind='value: Zip'])[1]";//was zip
	 String tbZipContactInfoId="zip";
	String tbPhoneXpath = "(//input[contains(@data-bind,'value: Number')])[1]";//Was PrimaryPhone
	String tbPhoneId="PrimaryPhone";

	String tbAlternatePhoneId = "AlternaticePhone1";
	String lbAddressErrorId = "Street1ErrorMessage";
	String lbCityErrorId = "CityErrorMessage";
	String lbZipErrorId = "ZipErrorMessage";
	String lbPhoneErrorId = "PrimaryPhoneErrorMessage";
	String hlnkCancelXpath = ".//*[@class='aSmall']";
	String btnProcessingId = "btnProcessing";
	String lbUsernameXpath = ".//*[@id='aspnetForm']//tbody/tr[1]/td[2]";
	String hlnkEmailEditXpath= "//a[@data-bind='click: $root.setEmailEdited']";
	String hlnkEmailToEditId = "Email";
	String hlnkConfirmEmailId = "ConfirmEmail";
	String btnSaveChangesClass = "btnSaveChanges";
	String lbInvalidEmailMessageId = "EmailErrorMessage";
	String lbEmailAddressXcode = "//div[@data-bind='text: Address']";
	String btnSaveChanges1Xpath = "//input[@class='btnSaveChanges']";
	String rdbtnPaymentReceiptsName = "IsPaymentReceiptsOn";
	String rdbtnMonthyInvoicesName = "IsMonthlyInvoicesOn";
	String rdbtnSpecialOfferesName = "IsSpecialOffersOn";
	String hlnkUnitXpath = "//div[@class='sc_unit_lbl']";
	String hlnkEmergencyContactEditXpath = "//span[text()='Emergency Contact']/a";
	String hlnkAuthorizedAccessEditXpath = "//span[text()='Authorized Access']/a";
	String tbFirstNameName = "FirstName";
	String tbLastNameName = "LastName";
	String tbAddressName = "Street1";
	String tbCityName = "City";
	String tbZipCodeName = "Zip";
	String tbPhoneName = "PrimaryPhone";
	String drpdwnStateName ="State";
	String lbAddressInfo = "//div[@class='sc_ecaa_item_dc sc_ecaa_item_st']";
	String btnSaveXpath = "//div[@class='sc_ecaa_svcn']/span/input";
	String tbAuthorisedAccessFName = "authorizedFirstName";
	String tbAuthorisedAccesLName = "authorizedLastName";
	String lbAuthorisedNameXpath = "//div[@class='sc_ecaa_item_st']";
	String lbAuthorisedUser = "//div[@class='sc_ecaa_item_dc sc_ecaa_item_dch sc_ecaa_item_st']";
	String lbMyStorageUnitXpath = "//p[contains(text(), 'My Storage Units')]";
	String lbScheduleMoveOutXpath = "//p[contains(text(), 'Schedule a Move-Out')]";
	String btnDateSaveXpath = "//p[@id='btnSave']/a[1]/img";
	String btnDateCancelXpath = ".//*[@id='btnSave']/a[2]/img";
	String btnContinueXpath = "//div[@id='autopaymsg']/input";
	String lbConfirmDateXpath = "//div[@class='confMessage']";
	String lbNexPaymentXpath = "//strong[contains(text(),'Next Payment')]";
	String lbMontlySummeryXpath = "//strong[contains(text(),'Monthly Summary')]";
	String lnkReviewXpath = "//input[@class='reviews_link2']";
	String lbUnitDetailsXpath = "//th[contains(text(), 'Unit Details')]";
	String lbDueNowXpath = "//th[contains(text(), 'Due Now')]";
	String lbNextPaymentXpath = "//th[contains(text(), 'Next Payment')]";
	String lbPaymentAmountXpath = "//span[contains(text(), 'Payment Amount') and @class='mLeftMargin']";
	String lbCancle = "Cancel";
	String lbChangeXpath = ".//*[@class='lnkChange']";
	String hlinkChangeMoveOutDateXpath="//a[@href='ChangeMoveOutDateList.aspx']";
			String lbHomeXpath = ".//*[@id='selectDate36920640']/a";
	
	
	// MyStorageUnit
	
		String imgPhotoMyStorageUnitXpath = ".//*[@id='sc_my_units']//div[1]/img";
		String lbAddressMyStorageUnitXpath = ".//*[@id='sc_my_units']//div[@class='address_hours']";
	
}
