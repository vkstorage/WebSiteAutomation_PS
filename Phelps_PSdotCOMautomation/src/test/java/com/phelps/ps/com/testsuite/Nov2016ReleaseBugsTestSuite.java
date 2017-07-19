package com.phelps.ps.com.testsuite;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.SearchResultsPageActions;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;

public class Nov2016ReleaseBugsTestSuite extends BasicTestSuite
		implements RDP_Conf_HoldLocators, ReservationCarePageLocators, PLPLocators {

	List<CateredWebElement> allLinks = new ArrayList<CateredWebElement>();
	List<CateredWebElement> allAddress = new ArrayList<CateredWebElement>();
	List<CateredWebElement> allSizes = new ArrayList<CateredWebElement>();
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

	// Bug 3313
	// @Test
	public void verifyUpdateVehicleUnitTabTest() {

		String searchText = "90230";
		commonActions.clearChache();
		navigator.gotoVehicleSearchResults(searchText);
		String currentURL = webAppDriver.getCurrentUrl();
		if (currentURL.contains("storage-search-vehicle.aspx")) {
			currentURL.replace("storage-search-vehicle.aspx", "storage-search-vehicle2.aspx");
			webAppDriver.get(currentURL);
		}

		webAppDriver.verifyDivTagTextEquals(
				"Available units that match your search criteria are limited in this area. Please change your search options or call 1-800-688-8057.");
		searchText = "ohio";
		navigator.gotoVehicleSearchResults(searchText);
		if (currentURL.contains("storage-search-vehicle.aspx")) {
			currentURL.replace("storage-search-vehicle.aspx", "storage-search-vehicle2.aspx");
			webAppDriver.get(currentURL);
		}

		allLinks = commonActions.getAllSearchResultShowAllUnits();
		allAddress = webAppDriver.findAllElementsByCss(".srp-address");

		for (int i = 0; i < allLinks.size(); i++) {
			String[] eachAddField = allAddress.get(i).getText().split(" ");
			allLinks.get(i).click();
			if (!webAppDriver.verifyTextContainInAnyTag("Storage Units Off " + eachAddField[1]))
				throw new AssertionError();
			webAppDriver.navigate().back();
		}
	}

	// @Test
	public void verifyWC1WC2SizeLocationChangeTest() {
		String[] wc1Properties = { "3440 S Carrollton Ave", "1334 Alaskan Way", "400 W Center Ave",
				"134 John Wesley Dobbs Ave NE", "1414 S Wabash Ave" };

		String[] wc2Properties = { "2902 Drake Ave SW", "2190 S Federal Blvd", "3400 S Congress Ave",
				"710 SE 8th Ave" };
		String firstName = "Mike";
		String lastName = "George";
		String phoneNumber = "7565456545";
		String ext = "432";
		String email = "muzfera.naaz@phelpsagency.com";
		String[] newAddress = { "", "", "", "" };
		String oldSize = "";
		int i = 0;
		boolean sizeFound = false;

		// WC1 to WC1
		String storageDetails[] = navigator.intoGetStartedPageWithStorage(wc1Properties[j++], positionRDPProperty, 2);
		reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email,
				dayOfMoveInDate);

		reservationDetailsActions.selectHoldNow();
		reservationDetailsActions.clickHoldNowOrComplete();
		reservationNumber = webAppDriver.findElementByXpath(lbConfirmationReservationNumberXpath).getText();
		navigator.gotoReservationCareLogin();
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);
		oldSize = webAppDriver.findElementByXpath(ReservationCarePageLocators.lbStorageSizeXpath).getText();
		reservationCareActions.clickChangeLocationLink();
		// searchResultsPageActions.clearAndReEnterSearch(wc1Properties[j++]);
		while (!sizeFound) {
			navigator.gotoDefaultSearchResults(wc1Properties[j++]);
			i = 0;
			commonActions.getAllSearchResultShowAllUnits().get(positionRDPProperty).click();
			allSizes = webAppDriver.findAllElementsByXpath(PLPLocators.lbStorageUnitAllUnitSizeXpath);
			for (; i < allSizes.size(); i++) {
				if (allSizes.get(i).getText().equals(oldSize)) {
					sizeFound = true;
					break;
				}
			}

		}
		newAddress[0] = webAppDriver.findElementByXpath(PLPLocators.lbSpaceStreetAddressXpath).getText();
		newAddress[1] = webAppDriver.findElementByXpath(PLPLocators.lbSpaceAddressLocalityXpath).getText();
		newAddress[2] = webAppDriver.findElementByXpath(PLPLocators.lbSpaceAddressRegionXpath).getText();
		newAddress[3] = webAppDriver.findElementByXpath(PLPLocators.lbSpacePostalCodeXpath).getText();
		String newSpace = webAppDriver.findAllElementsByXpath(PLPLocators.lbStorageUnitAllUnitSizeXpath).get(i)
				.getText();
		commonActions.getAllPLPUnits().get(i).click();

		plpActions.clickChangeUnitConfirm();
		webAppDriver.verifyElementPresentByXpath(lbStorageSizeXpath + "[text()=\"" + newSpace + "\"]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[0].trim() + "')]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[1].trim() + "')]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[2].trim() + "')]");
		webAppDriver.verifyElementPresentByXpath(
				lbStorageAddressReservationCareXpath + "[contains(.,'" + newAddress[3].trim() + "')]");
		webAppDriver.verifyStrongTagTextEquals(lbReservationNoText + reservationNumber);

		// Wc1 to Wc2

	}

	// Bug 3339 - Rel Alternative Tags (PS-0358)

	//@Test
	public void verifyRelAlternativeTagTest() {
		String searchText = "CO";
		navigator.intoPLPByPropertyPhoto(searchText);
		webAppDriver.verifyElementPresentByXpath("//link[@rel='alternate']");
		String mobileURL = webAppDriver.findElementByXpath("//link[@rel='alternate']").getAttribute("href");
		webAppDriver.get(mobileURL);
		webAppDriver.verifyElementPresentByXpath("//a[@data-whichtab='location-units']");
		webAppDriver.verifyElementPresentByXpath("//div[@itemprop='address']");

	}

	// Bug 3141 - AutoPay card details are not displayed on Manage AutoPay page
	// (SN#INC0039154; was SN#INC0035777)

	 @Test
	public void verifyAutopayCardDetailsTest() {
		String debitCardNumber = "4900770005624870";
		String creditCardNumber = "349194941318975";
		String paymentUser = "insurancetest";
		navigator.login(paymentUser, "test123");
		navigator.gotoPayments();
		if (webAppDriver.verifyElementIsPresentByXpath("//img[@src='../images/new/btn_signup.png']")) {
			webAppDriver.clickElementByXpath("//a[@href='ManageAutoPay.aspx']");
			paymentActions.clickTurnOnAutoPay();

			paymentActions.enterCardDetails(debitCardNumber);
			webAppDriver.verifyElementPresentByXpath("//strong[contains(text(),'Debit card')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(.,'********4870')]");
			paymentActions.clickTurnOffAutoPayButton();
			paymentActions.clickYesForAutoPay();
			paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
			paymentActions.clickTurnOnAutoPay();

			paymentActions.enterCardDetails(creditCardNumber);
			webAppDriver.verifyElementPresentByXpath("//strong[contains(text(),'Credit card')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(.,'********8975')]");
		} else {
			webAppDriver.clickElementByXpath("//a[@href='ManageAutoPay.aspx']");
			paymentActions.clickTurnOffAutoPayButton();
			paymentActions.clickYesForAutoPay();
			paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
			paymentActions.clickTurnOnAutoPay();

			paymentActions.enterCardDetails(debitCardNumber);
			webAppDriver.verifyElementPresentByXpath("//strong[contains(text(),'Debit card')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(.,'********4870')]");
			paymentActions.clickTurnOffAutoPayButton();
			paymentActions.clickYesForAutoPay();
			paymentActions.verifyAutoPayPaymentMethodIsNotVisible();
			paymentActions.clickTurnOnAutoPay();

			paymentActions.enterCardDetails(creditCardNumber);
			webAppDriver.verifyElementPresentByXpath("//strong[contains(text(),'Credit card')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(.,'********8975')]");
		}

	}

	// Bug 3340 - Schema Markup Updates (PS-0358)
	//@Test
	public void verifySchemaMarkUpUpdatesTest() {
		navigator.intoPLPByPropertyPhoto("LA");
		String pageSource = webAppDriver.getPageSource();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(pageSource);
		clipboard.setContents(strSel, null);
		webAppDriver.get("https://search.google.com/structured-data/testing-tool/");
		webAppDriver.clickElementById("new-test-code");
		webAppDriver.findElementByCSS(
				".CodeMirror.cm-s-default.cm-s-sdtt.CodeMirror-wrap.CodeMirror-empty.CodeMirror-focused>div>textarea");

		webAppDriver
				.findElementByCSS(
						".CodeMirror.cm-s-default.cm-s-sdtt.CodeMirror-wrap.CodeMirror-empty.CodeMirror-focused>div>textarea")
				.sendKeys(Keys.CONTROL + "v");
		;
		webAppDriver.clickElementById("new-test-submit-button");
		webAppDriver.relax(1000);
		webAppDriver.verifyElementPresentByXpath("//*[@id='results-cell']//span[text()='Detected']/../..//span[text()='0 WARNINGS']");

	}
}
