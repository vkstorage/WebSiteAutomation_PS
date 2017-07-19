package com.phelps.ps.com.nevigation;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.phelps.ps.com.actions.AccountCreationActions;
import com.phelps.ps.com.actions.CommonActions;
import com.phelps.ps.com.actions.HomeActions;
import com.phelps.ps.com.actions.HowSelfStorageWorksActions;
import com.phelps.ps.com.actions.InsuranceActions;
import com.phelps.ps.com.actions.LoginActions;
import com.phelps.ps.com.actions.PLPActions;
import com.phelps.ps.com.actions.PaymentsActions;
import com.phelps.ps.com.actions.ReservationCareLoginActions;
import com.phelps.ps.com.actions.ReservationDetailsActions;
import com.phelps.ps.com.actions.SearchResultsPageActions;
import com.phelps.ps.com.actions.SelfcareContactInfoActions;
import com.phelps.ps.com.actions.SelfcareSummaryActions;
import com.phelps.ps.com.actions.SpanishHomeActions;
import com.phelps.ps.com.actions.SpanishSRPActions;
import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.baseparams.BaseParams;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.Phase5Locators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.RecoverReservationNumberLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;
import com.phelps.ps.com.locators.SelfCareMyStorageUnitLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;
import com.phelps.ps.com.mobile.actions.MobileAccountInfoActions;
import com.phelps.ps.com.mobile.actions.MobileLoginActions;
import com.phelps.ps.com.mobile.actions.MobilePaymentActions;
import com.pheps.ps.com.mobile.locators.MobileAccountInfoLocators;
import com.pheps.ps.com.mobile.locators.MobileLoginLocators;
import com.pheps.ps.com.mobile.locators.MobilePaymentLocators;

public class Navigator implements BaseParams, LoginLocators, SearchResultsPageLocators, ReservationDetailsLocators, Phase5Locators,
		SelfcareSummaryLocators, SelfCareMyStorageUnitLocators, HomeLocators, RDP_Conf_HoldLocators, SelfCareContactInfoLocators,  MobileLoginLocators, MobileAccountInfoLocators, MobilePaymentLocators {

	public final static Logger logger = Logger.getLogger(Navigator.class);
	private CateredWebDriver webAppDriver;
	private HomeActions homeActions;
	private SearchResultsPageActions searchResultsActions;
	private ReservationDetailsActions reservationDetailsActions;
	private LoginActions loginAction;
	private HowSelfStorageWorksActions howSelfStorageWorksAction;
	private SelfcareSummaryActions selfcareSummaryActions;
	private PaymentsActions paymentsActions;
	private InsuranceActions insuranceActions;
	private CommonActions commonActions;
	private SearchResultsPageActions searchResultsPageActions;
	private SpanishHomeActions spanishHomeActions;
	private SpanishSRPActions spanishSRPActions;
	private PLPActions plpActions;
	private ReservationCareLoginActions reservationCareLoginActions;
	private MobileWebDriver mobileWebAppDriver;
	private AccountCreationActions accountCreationActions;

	private SelfcareContactInfoActions selfcareContactInfoActions;
	private MobileLoginActions mobileLoginActions;
	private MobilePaymentActions mobilePaymentActions;
	private MobileAccountInfoActions mobileAccountInfoActions;


	// private KnowledgeGraphActions KnowledgeGraphActions;

	public Navigator(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
		homeActions = new HomeActions(webappDriver);
		searchResultsActions = new SearchResultsPageActions(webappDriver);
		reservationDetailsActions = new ReservationDetailsActions(webappDriver);
		loginAction = new LoginActions(webappDriver);
		selfcareSummaryActions = new SelfcareSummaryActions(webappDriver);
		howSelfStorageWorksAction = new HowSelfStorageWorksActions(webappDriver);
		paymentsActions = new PaymentsActions(webappDriver);
		insuranceActions = new InsuranceActions(webappDriver);
		commonActions = new CommonActions(webappDriver);
		searchResultsPageActions = new SearchResultsPageActions(webappDriver);
		spanishHomeActions = new SpanishHomeActions(webappDriver);
		spanishSRPActions = new SpanishSRPActions(webappDriver);
		plpActions = new PLPActions(webappDriver);
		reservationCareLoginActions = new ReservationCareLoginActions(webappDriver);

		accountCreationActions = new AccountCreationActions(webappDriver);

		selfcareContactInfoActions = new SelfcareContactInfoActions(webappDriver);

	}
	
	public Navigator(MobileWebDriver mobileWebDriver) throws MalformedURLException{
		mobileWebAppDriver=mobileWebDriver;
		mobileLoginActions=new MobileLoginActions(mobileWebAppDriver);
		mobilePaymentActions=new MobilePaymentActions(mobileWebAppDriver);
		mobileAccountInfoActions=new MobileAccountInfoActions(mobileWebAppDriver);
	}


	/**
	 * Redirects to Home page. If any user account is logged in then it first
	 * logged out from that account.
	 */
	public void redirectToHomePageNotLoggedIn() {
		
		webAppDriver.get(baseUrl);//+"?optimizely_x8236533790=0"
		/*try {
			webAppDriver.verifyElementTextContains(By.id(linkLogoutId), "Logout", true);
			loginAction.clickOnLogOut();
		} catch (Exception e) {
			// this is just to do logout if customer is auto logged in during
			// reservation
		}
		*/
	}

	/**
	 * Redirects to home, it maintains the same session. if user is logged in then
	 * it remains active.
	 */
	public void redirectToHomeSameSession() {
		
		try{
		webAppDriver.get(baseUrl);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Search with given text and redirects into default search page
	 * 
	 * @param searchCriteria
	 */
	public void gotoDefaultSearchResults(String searchCriteria) {
		redirectToHomePageNotLoggedIn();
		homeActions.enterSearchCriteria(searchCriteria);
	}

	public void gotoVehicleSearchResults(String searchCriteria) {
		gotoDefaultSearchResults(searchCriteria);
		searchResultsPageActions.clickVehicleUnitTab();
	}

	/**
	 * Search with a given text and redirects to Get started page with storage
	 * unit
	 * 
	 * @param searchCriteria
	 */
	public String[] intoGetStartedPageWithStorage(String searchCriteria) {
		gotoDefaultSearchResults(searchCriteria);
		// commented to run bug 2752 need to uncomment the below 3 steps
		String address = webAppDriver.findElementByCSS(lbSpaceAddressCss).getText();
		//String spaceSize = webAppDriver.findElementByCSS(lbSpaceSizeCss).getText();
		String spaceSize = webAppDriver.findElementByXpath(lbSpacesSizeXpath).getText();
		String price = webAppDriver.readTextByCss(lbPriceCss);
		String actualPrice = price.substring(0, price.indexOf("/"));

		searchResultsActions.clickContinue();

		// verify the address and space size
		commonActions.verifyPropertyAddress(ReservationDetailsLocators.lbStorageAddressXpath, address);
		webAppDriver.verifyElementTextContainsByXpath(lbSpaceSizeXpath, spaceSize);
		return new String[] { address, spaceSize, actualPrice };
	}

	// Created method to test bug 2752
	public String[] intoGetStartedPageWithStorage(String searchCriteria, int positionRDPProperty, int positionPLPUnit) {
		gotoDefaultSearchResults(searchCriteria);
		// commented to run bug 2752 need to uncomment the below 3 steps
		
		//String element="";
		
		//need to uncomment
		commonActions.getAllSearchResultShowAllUnits().get(positionRDPProperty).click();
		
		//element="//li[contains(@id,'srp_item_')]["+positionRDPProperty+"]//li[3]/div[1]//div[@class='srp_label']";
		//webAppDriver.findAllElementsByXpath(".//*[contains(@id,'srp_item_')]").get(positionRDPProperty)
		
webAppDriver.relax(500);
		String address = webAppDriver.findElementById(PLPLocators.lbSpaceAddressId).getText();
		
		//String spaceSize=webAppDriver.findElementByXpath(element).getText();
		//need to uncomment
		String spaceSize = webAppDriver.findAllElementsByXpath(PLPLocators.lbStorageUnitAllUnitSizeXpath).get(positionPLPUnit)
				.getText();
		//element="//li[contains(@id,'srp_item_')]["+positionRDPProperty+"]//li[3]//div[@class='srp_label alt-price']";
		//String price = webAppDriver.findAllElementsByCss(lbPriceCss).get(positionPLPUnit).getText();
		String price = webAppDriver.findAllElementsByXpath(PLPLocators.lbStorageUnitAllUnitPriceXpath).get(positionPLPUnit).getText();
		String actualPrice = price.substring(0, price.indexOf("/"));
		// webAppDriver.clickElement(allCoupons.get((position)));
		//Need to comment
		//searchResultsActions.clickContinueWithPosition(positionRDPProperty, positionPLPUnit);
		//Need to uncomment
	commonActions.getAllPLPUnits().get(positionPLPUnit).click();

		// plpActions.clickContinueButtonForUnit();

		// searchResultsActions.clickContinue();

		// verify the address and space size
		commonActions.verifyPropertyAddress(ReservationDetailsLocators.lbStorageAddressXpath, address);
		webAppDriver.verifyElementTextContainsByXpath(lbSpaceSizeXpath, spaceSize);
		return new String[] { address, spaceSize, actualPrice };
	}

	public void intoPLPByPropertyPhoto(String searchCriteria) {
		gotoDefaultSearchResults(searchCriteria);
		// int itemIndex = searchResultsPageActions.getSrpItemIndex();
		// searchResultsActions.clickImageWithIndex(itemIndex);
		searchResultsActions.clickPropertyImage();

	}

	public void login(String userName, String password) {
		redirectToHomePageNotLoggedIn();
		loginAction.clickOnMyAccount();
		loginAction.enterUserName(userName);
		loginAction.enterPassword(password);
		loginAction.clickOnLogin();
	/*f (webAppDriver.verifyElementPresentBy(By.xpath(iframeRememberMeLaterXpath))) {
			webAppDriver.switchtoiFrameByXpath(iframeRememberMeLaterXpath);
			selfcareContactInfoActions.clickOnRememberMeLaterLink();
			webAppDriver.switchTo().defaultContent();
		}*/
		webAppDriver.relax(500);
	//	loginAction.clickOnLogo();
	}

	// WC2 added
	public void loginToReservationCare(String email, String reservationNumber) {
		redirectToHomePageNotLoggedIn();
		gotoReservationCareLogin();

		if (webAppDriver.verifyElementPresentBy(By.xpath("//span[@class='pageTitle fLeft'][text()='Manage Your Reservation'] "))) {
			reservationCareLoginActions.enterEmailAddress(email);
			reservationCareLoginActions.enterReservationNumber(reservationNumber);
			reservationCareLoginActions.clickOnSubmit();
		}
		webAppDriver.verifyPresenceOfTextInSpanTag("Manage Reservation Number");
	}

	public void gotoMyAccountLoggedIn() {
		redirectToHomeSameSession();
		homeActions.clickOnMyAccountLoggedIn();
	}

	public void gotoMyAccountLoggedIn(String userName, String password) {
		login(userName, password);
		if (webAppDriver.verifyElementPresentBy(By.xpath(iframeRememberMeLaterXpath))) {
			webAppDriver.switchtoiFrameByXpath(iframeRememberMeLaterXpath);
			selfcareContactInfoActions.clickOnRememberMeLaterLink();
			webAppDriver.switchTo().defaultContent();
		}
		homeActions.clickOnMyAccountLoggedIn();
	}

	public void gotoPayMyBill() {
		homeActions.clickOnPayMyBill();
	}

	public void gotoStorageMadeEasyContent() {
		homeActions.clickStorageMadeEasyImage();
	}

	public void gotoStorageMadeReliableContent() {
		homeActions.clickStorageMadeReliableImage();
	}

	public void gotoStorageMadeLocalContent() {
		homeActions.clickStorageMadeLocalImage();
	}

	public void gotoBenefitsContent() {
		homeActions.clickOnBenefitsLink();
	}

	public void gotoHomePage() {
		
		webAppDriver.get(baseUrl);//+"?optimizely_x8236533790=1"
	}

	public void gotoHomePageFromMovingSupplies() {
		loginAction.clickOnMovingSuppliesLogo();
	}

	public void gotoPublicStorageMenu() {
		homeActions.clickOnStorageMenu();
	}

	public void gotoHowStorageWorksMenu() {
		webAppDriver.get(baseUrl + "/self-storage-starting.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverStorage(); homeActions.clickOnHowSelfStorageWorksMenu();
		 */
	}

	// Starting Out Tab
	public void gotoWhatWeOfferSubTab() {
		gotoHowStorageWorksMenu();
		howSelfStorageWorksAction.clickOnSubTabWhatWeOffer();
	}

	public void gotoHowToStoreSubTab() {
		gotoHowStorageWorksMenu();
		howSelfStorageWorksAction.clickOnSubTabHowToStore();
	}

	public void gotoReservingTab() {
		gotoHowStorageWorksMenu();
		howSelfStorageWorksAction.clickOnReservingTab();
	}

	public void gotoMovingInTab() {
		gotoHowStorageWorksMenu();
		howSelfStorageWorksAction.clickOnMovingInTab();
	}

	public void gotoYourAccountTab() {
		gotoHowStorageWorksMenu();
		howSelfStorageWorksAction.clickOnYourAccountTab();
	}

	/**
	 * Do login if already not logged in and go to self case my reservation
	 * details page HTML: /selfcare/myreservationdetails.aspx
	 */

	// WC2 removed
	/*
	 * public void gotoSelfcareMyReservationDetails(String username, String
	 * password) { gotoMyAccountPage(username, password);
	 * selfcareSummaryActions.clickReservations(); }
	 * 
	 * public void gotoSelfcareMyReservationDetails() { gotoMyAccountLoggedIn();
	 * selfcareSummaryActions.clickReservations(); }
	 */
	/**
	 * /selfcare/summary.aspx
	 */
	public void gotoMyAccountPage(String username, String password) {
		gotoMyAccountLoggedIn(username, password);
		// login(username, password);
		//commonActions.clickMyAccount();
	}

	public void gotoSelfCarePaymentPage(String username, String password) {
		gotoMyAccountPage(username, password);
		selfcareSummaryActions.clickPayment();
	}

	// Self Storage Menu
	public void gotoSelfStorageMenu() {
		webAppDriver.get(baseUrl + "/self-storage.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverStorage(); homeActions.clickOnSelfStorageMenu();
		 */
	}

	// Business Storage Menu
	public void gotoBusinessStorageMenu() {
		webAppDriver.get(baseUrl + "/business-storage.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverStorage(); homeActions.clickOnBusinessStorageMenu();
		 */
	}

	// Vehicle Storage Menu
	public void gotoVehicleStorageMenu() {
		webAppDriver.get(baseUrl + "/vehicle-car-storage.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverStorage(); homeActions.clickOnVehicleStorageMenu();
		 */
	}

	// Storage Blog Menu
	public void gotoStorageBlogMenu() {
		webAppDriver.get("www.publicstorage.com/blog");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverStorage(); homeActions.clickOnStorageBlogMenu();
		 */
		// homeActions.clickOnStorageBlogMenu();
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationStorageBlogPageXpath);
	}

	// Menu Moving Supplies
	public void gotoStorageBoxesMenu() {

		webAppDriver.get("supplies.publicstorage.com");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverMovingSupplies(); homeActions.clickOnStorageBoxesMenu();
		 */
		gotoHomePageFromMovingSupplies();
	}

	public void gotoStorageLocksMenu() {
		webAppDriver.get("supplies.publicstorage.com/browse/moving-supplies-packing-supplies/boxes");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverMovingSupplies(); homeActions.clickOnStorageLocksMenu();
		 */
		gotoHomePageFromMovingSupplies();
	}

	public void gotoPackingSuppliesMenu() {
		webAppDriver.get("https://supplies.publicstorage.com/browse/moving-supplies-packing-supplies/packing-supplies");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverMovingSupplies();
		 * homeActions.clickOnPackingSuppliesMenu();
		 */
		gotoHomePageFromMovingSupplies();
	}

	public void gotoTapeMenu() {
		webAppDriver.get("supplies.publicstorage.com/browse/moving-supplies-packing-supplies/tape");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverMovingSupplies(); homeActions.clickOnTapeMenu();
		 */
		gotoHomePageFromMovingSupplies();
	}

	// Menu Company Info
	public void gotoAboutUsMenu() {
		webAppDriver.get(baseUrl + "/storage-company-info.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverCompanyInfo(); homeActions.clickOnAboutUsMenu();
		 */
	}

	public void gotoInvestorRelationsMenu() {
		webAppDriver.get("investors.publicstorage.com/home/default.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverCompanyInfo();
		 * homeActions.clickOnInvestorRelationMenu();
		 */
	}

	public void gotoGlobalLocationsMenu() {
		webAppDriver.get(baseUrl + "/global-location.aspx");
		// Commented as mouse hover not working for some cases
		homeActions.hoverCompanyInfo();
		homeActions.clickOnGlobalLocationMenu();
	}

	public void gotoContactUsMenu() {
		webAppDriver.get(baseUrl + "/storage-company-info.aspx");
		// Commented as mouse hover not working for some cases
		/*
		 * homeActions.hoverCompanyInfo(); homeActions.clickOnContactUsMenu();
		 */
	}

	// Payments
	public void gotoPayments() {
		gotoMyAccountLoggedIn();
		paymentsActions.clickOnPayments();
	}

	public void gotoInsurance() {
		gotoMyAccountLoggedIn();
		insuranceActions.clickOnMyStorageUnits();
	}

	public void gotoPhase5(String phase5String) {
		redirectToHomeSameSession();
		webAppDriver.clickElementByPartialLinkText(phase5String);
		webAppDriver.verifyElementPresentById(tbSearchId);
	}

	public void gotoPhase5(String linkName, String verificationComponent) {
		webAppDriver.clickElementByPartialLinkText(linkName);
		String[] arr = verificationComponent.split("_");
		if (verificationComponent.contains("XPATH")) {
			webAppDriver.verifyElementPresentByXpath("//h1[contains(text(),'" + arr[1] + "')]");
		} else {
			webAppDriver.verifyElementPresentById(tbSearchId);
		}

	}

	public String[] intoECI_RDP(String searchText, String firstName, String lastName, String phoneNumber, String ext, String email,
			String date,int positionRDPAllUnit,int positionPLPUnit) {
		// Search by ZIP
		String storageDetails[] = intoGetStartedPageWithStorage(searchText, positionRDPAllUnit, positionPLPUnit);

		// Fill up reservation details
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
		webAppDriver.relax(500);
		reservationDetailsActions.selectExpressCheckIn();
		reservationDetailsActions.clickHoldNowOrComplete();
		return storageDetails;
	}

	public String[] intoRDP_Conf_Hold(String searchText, String firstName, String lastName, String phoneNumber, String ext,
			String email, String date,int positionRDPAllUnits,int positionPLPUnit) {
		// Search by ZIP
		String storageDetails[] = intoGetStartedPageWithStorage(searchText, positionRDPAllUnits, positionPLPUnit);

		// Fill up reservation details
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, date);
	webAppDriver.relax(500);
		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();

		return storageDetails;

	}

	public void gotoSelfCareContactInfo(String userName, String password) {
		gotoMyAccountLoggedIn(userName, password);
		selfcareSummaryActions.clickContactInfoLink();
		webAppDriver.verifyPresenceOfTextInPTagText("Contact Information");
	}

	public void gotoContactInfoLoginDetailsTab() {
		gotoMyAccountLoggedIn();

		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickLoginDetailsTab();

	}

	public void gotoMyStorageUnits() {
		gotoMyAccountLoggedIn();
		webAppDriver.clickElementByLinkText("My Storage Units");
		// webAppDriver.verifyElementPresentByXpath(lbMyStorageUnitXpath);
		// webAppDriver.verifyPresenceOfTextInSpanTag("My Storage Unit: C007");

	}

	public void gotoManagePayments() {
		gotoPayments();
		paymentsActions.clickOnManageMyPayments();
	}

	public void gotoMyStorageUnits(String userName, String password) {
		gotoMyAccountLoggedIn(userName, password);
		selfcareSummaryActions.clickMyStorageUnits();
		webAppDriver.clickElementByXpath(btnViewFullDetailsXpath);
		//webAppDriver.clickElementByXpath(btnScheduleMoveOutXpath);

	}

	public void gotoSelfCarePaymentPage() {
		gotoMyAccountLoggedIn();
		selfcareSummaryActions.clickPayment();
	}

	public void gotoSpanishHome() {
		webAppDriver.get(baseUrl + "/es");
	}

	public void gotoSpanishSearchResultPage(String searchCriteria) {
		gotoSpanishHome();
		spanishHomeActions.enterSearchCriteria(searchCriteria);
		spanishHomeActions.clickSearchButton();

	}

	public void gotoPackingAndStorageTips() {
		String url = baseUrl + "/packing-for-storage-tips.aspx";
		webAppDriver.get(url);
	}

	public void gotoStorageTips() {
		String url = baseUrl + "/storage-space-tips.aspx ";
		webAppDriver.get(url);
	}

	public void gotoCLPPage(String clpURL) {
		webAppDriver.get(clpURL);
	}
	
	/*	Added for video landing pages 
	@author
	MNaaz*/
	
	public void gotoVideoLandingPage(){
		webAppDriver.get(baseUrl+"/videos.aspx");
		//need to add verification text
	}
	
	//Mobile
	public void gotoMobileLoginPage(){
		mobileWebAppDriver.get(baseUrl);
		mobileLoginActions.clickOnLogin();
	}
	
	public void gotoMobileAccountIntoPage(String username, String password){
		mobileWebAppDriver.get(baseUrl);
		mobileLoginActions.clickOnLogin();
		mobileLoginActions.enterUsername(username);
		mobileLoginActions.enterPassword(password);
		mobileLoginActions.clickOnLoginbutton();
		mobileWebAppDriver.verifyElementPresentById(lbFullNameId);
	}
	
	public void gotoMobilePaymentPage(String username, String password){
		gotoMobileAccountIntoPage(username, password);
		mobileAccountInfoActions.clickOnPayThisUnit();
		//webAppDriver.verifyElementPresentById(lbMakePaymentId);
		
	}
	
	public void gotoMobileVideoLandingPage(){
		
		mobileWebAppDriver.get(baseUrl+"/videos");
		mobileWebAppDriver.verifyPresenceOfTextInH2Tag("How to Pack & Store");
	}
	


	// WC2
	public void gotoReservationCareLogin() {
		homeActions.clickOnReservationsLink();
	}

	public void gotoReservationCareLoggedIn(String email, String reservationNumber) {
		loginToReservationCare(email, reservationNumber);
	}

	public void gotoRecoverReservationNumber() {
		reservationCareLoginActions.clickForgotReservationNumberLink();
	}

	public String gotoPendingHomePage(String searchText, String firstName, String lastName, String phoneNumber, String ext,
			String email, String date) {
		intoRDP_Conf_Hold(searchText, firstName, lastName, phoneNumber, ext, email, date,0,0);
		String reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();

		commonActions.clickPublicStorageIcon();
		return reservationNumber;
	}

	public void gotoPendingHomePage() {
		commonActions.clickPublicStorageIcon();
		webAppDriver.verifyElementPresentByXpath(btnManageYourReservationPendingHomeXpath);
	}

	public void gotoCreateAccountReservationLookUpPage() {

		webAppDriver.get(baseUrl + "/ps-create-account.aspx");
		webAppDriver.verifyPresenceOfTextInSpanTag("Create Your Online Account");
	}

	public void gotoCreateAccountEnterDetailsPage(String email, String accountNumber) {
		webAppDriver.get(baseUrl + "/ps-create-account.aspx");
		accountCreationActions.enterEmailOnReservationLookUp(email);
		accountCreationActions.enterAccountNoReservationLookUp(accountNumber);
		accountCreationActions.clickOnCreateAccountSubmit();

	}
	
	public void gotoSiteMapPage(){
		webAppDriver.get(baseUrl + "/site-map.aspx");
	}
	
	public void gotoECILoginPage(){
		webAppDriver.get(baseUrl+"/reservationcare/login.aspx?inactive=1&return=%2freservationcare%2feci.aspx%3fsrc%3dhome");
	}
	
	public void intoReservationDepositConfirmationPage(String searchText, String firstName, String lastName, String phoneNumber, String ext,
			String email, String date,int positionRDPAllUnits,int positionPLPUnit,String cardNumber,boolean isCreditCard,boolean isInventory,boolean isFromReservationCare){
		String depositAmount = "";
		
		
		double totalDueAmount;
		
		
		String storageDetails[] = intoGetStartedPageWithStorage(searchText, positionRDPAllUnits, positionPLPUnit);
		Calendar now = GregorianCalendar.getInstance();
		webAppDriver.relax(500);
		

		reservationDetailsActions.enterFirstName(firstName);
		reservationDetailsActions.enterLastName(lastName);
		reservationDetailsActions.enterPhoneNumber(phoneNumber);
		reservationDetailsActions.enterExt(ext);
		reservationDetailsActions.enterEmail(email);
		reservationDetailsActions.enterConfirmEmail(email);

		reservationDetailsActions.selectMoveInDate(date, true);
		webAppDriver.relax(1000);
		
		
		depositAmount = reservationDetailsActions.getDepositAmount();
		totalDueAmount = reservationDetailsActions.getTotalCostAfterDeposit(reservationDetailsActions.getTotalAmount(),
				depositAmount);
		
		

		reservationDetailsActions.clickContinueR$P();
		webAppDriver.relax(500);
		
		paymentsActions.makePaymentWithCreditCardReservationDeposit(cardNumber, "222", "6797", depositAmount,
				false, false, isCreditCard,isFromReservationCare);
	}
	
}
