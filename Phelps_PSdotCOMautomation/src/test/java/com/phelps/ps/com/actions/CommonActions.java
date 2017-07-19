package com.phelps.ps.com.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.lang.String;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.Augmentable;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.ECIDetailsLocators;
import com.phelps.ps.com.locators.ECI_RDP_ConfLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.SelfCareContactInfoLocators;

import com.phelps.ps.com.locators.SelfcareSummaryLocators;
import com.phelps.ps.com.nevigation.Navigator;

public class CommonActions implements ECIDetailsLocators, CommonLocators, SelfcareSummaryLocators,
		RDP_Conf_HoldLocators, ReservationDetailsLocators, ECI_RDP_ConfLocators, SelfCareContactInfoLocators,
		SearchResultsPageLocators, PLPLocators {

	CateredWebDriver webAppDriver;
	String newURL;
	String moveInDate;
	String dayOfMoveInDate;
	Calendar c;
	String state;

	SelfcareSummaryActions selfcareSummaryActions;
	SelfcareContactInfoActions selfcareContactInfoActions;

	public CommonActions() {
	}

	public CommonActions(CateredWebDriver driver) {
		webAppDriver = driver;
		selfcareSummaryActions = new SelfcareSummaryActions(driver);
		selfcareContactInfoActions = new SelfcareContactInfoActions(driver);
	}

	/**
	 * Click the Public Storage icon
	 */
	public void clickPublicStorageIcon() {
		
		webAppDriver.clickElementByXpath(imgPSLogoXpath);
		webAppDriver.relax(300);

	}

	public void clickMyAccount() {

		webAppDriver.clickElementById(btnMyAccountNewId);

		webAppDriver.verifyPresenceOfTextInSpanTag("My Public Storage Account");
	}

	/**
	 * Verify the user account section after perform ECI
	 * 
	 * @param storageDetails
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param currentDate
	 * @param address1
	 * @param address2
	 * @param city
	 * @param zip
	 * @param phoneNumber
	 */
	public void verifyReservationCareAfterECI(String[] storageDetails, String firstName, String lastName, String email,
			String currentDate, String address1, String address2, String city, String zip, String phoneNumber) {
		/*
		 * Self-Care > Summary -
		 * https://psstaging.phelpsagency.com/selfcare/summary.aspx Verify a.
		 * Name b. Email
		 */
		webAppDriver.verifyElementTextContainsByCss(lbNameCss, firstName + " " + lastName);
		webAppDriver.verifyElementTextContainsByCss(lbEmailCss, email);

		/*
		 * Confirm that the ‘Reservations’ module has the same data you chose /
		 * entered on the on the SRP: a. Property b. Size c. Move-In Date
		 */
		webAppDriver.verifyElementTextContainsByXpath(SelfcareSummaryLocators.lbMoveInDateXpath, currentDate);
		verifyPropertyAddress(SelfcareSummaryLocators.lbStorageAddressXpath, storageDetails[0]);
		/*
		 * for (String add : storageDetails[0].replaceAll("\n", " ").split(" "))
		 * {
		 * webAppDriver.verifyElementTextContainsByXpath(SelfcareSummaryLocators
		 * .lbStorageAddressXpath, add); }
		 */
		webAppDriver.verifyElementTextContainsByCss(SelfcareSummaryLocators.lbStorageAddressAndSizeCss,
				storageDetails[1]);

		// 3. Click the ‘Details’ link in the ‘Reservations’ module.
		// WC2 removed
		// selfcareSummaryActions.clickReservationDetailsLink();

		/*
		 * Self-Care > Reservation Details -
		 * https://psstaging.phelpsagency.com/selfcare/myreservationdetails.aspx
		 * 1. Confirm that the Reservation Details page contains the same data
		 * as you chose / entered on the on the SRP: a. Property b. Size c.
		 * Move-In Date d. Promotion e. Monthly Price
		 */
		verifyPropertyAddress(ReservationCarePageLocators.lbStorageAddressReservationCareXpath, storageDetails[0]);
		/*
		 * for (String add : storageDetails[0].replaceAll("\n", " ").split(" "))
		 * { webAppDriver.verifyElementTextContainsByXpath(
		 * SelfcareMyReservationsDetailsLocators.lbStorageAddressXpath, add); }
		 */
		webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbStorageSizeXpath,
				storageDetails[1]);
		String[] moveInMonth = currentDate.split("/");
		String currentDateOnreservations = moveInMonth[0] + "/" + moveInMonth[1] + "/16";
		webAppDriver.verifyElementTextContainsByXpath(
				".//table[@class='tblMyReservations fLeft mTop']//td[1]/p[1]/span", currentDateOnreservations);
		// webAppDriver.verifyElementTextContainsByXpath(SelfcareMyReservationsDetailsLocators.lbMoveInDateXpath,
		// currentDate); date format to be discussed. Bug 2273
		webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbMonthlyRentXpath,
				storageDetails[2]);
		/*
		 * 3. Confirm that the yellow call-out in the top right corner of the
		 * page reads, “Save Time on Move-in Day…” It should not promote Express
		 * Check-In (ECI) since you already completed ECI.
		 */
		webAppDriver.verifyElementTextContainsByXpath(ReservationCarePageLocators.lbPromotionXpath,
				"Save Time on Move-in Day");

		/*
		 * 4. Click the ‘Contact Info’ link. Self-Care > Contact Information -
		 * https
		 * ://psstaging.phelpsagency.com/selfcare/addressandpersonalinfo.aspx 1.
		 * Confirm that the Contact Information page contains the same data you
		 * entered on the RDP: a. Your full name (shown in Address & Personal
		 * Info) b. Address (shown in Address & Personal Info) c. Phone number
		 * (shown in Address & Personal Info) d. E-mail address (shown in
		 * Username, Email & Password)
		 */
		selfcareSummaryActions.clickContactInfoLink();
		webAppDriver.verifyElementTextContainsByXpath(lbCustomerNameXpath, firstName + " " + lastName);
		webAppDriver.verifyElementTextById(tbAddressId, address1);
		webAppDriver.verifyElementTextById(tbAptId, address2);
		webAppDriver.verifyElementTextById(SelfCareContactInfoLocators.tbCityId, city);
		webAppDriver.verifyElementTextContainsById(tbZipId, zip);
		webAppDriver.verifyElementTextById(tbPrimaryPhoneId, phoneNumber.replaceAll("-", ""));

		/*
		 * Username, Email & Password
		 */
		selfcareContactInfoActions.clickUsernameEmailPasswordLink();
		// webAppDriver.verifyElementTextContainsByXpath(lbSelfcareUsernameXpath,"");
		// to be implemented
		webAppDriver.verifyElementTextContainsByXpath(lbSelfcareEmailXpath, email);
	}

	public void verifyPropertyAddress(String xpathOfActualAddress, String expectedAddress) {
		expectedAddress = expectedAddress.replaceAll("\n", "").replaceAll(" ", "").replaceAll(",", "");
		String actualAddress = webAppDriver.findElementByXpath(xpathOfActualAddress).getText().replaceAll("\n", "")
				.replaceAll(" ", "").replaceAll(",", "");
		if (!actualAddress.contains(expectedAddress)) {
			webAppDriver.captureScreenshot();
			throw new AssertionError(expectedAddress + " not found in " + actualAddress);
		}
	}

	public String convertAddressIntoImagePrefix(String address) {
		String prefixVerify = "public-storage";
		address = address.toLowerCase().replaceAll("\n", " ");
		address = address.replaceAll(",  ", " ");
		address = address.replaceAll("  ", " ");
		address = address.replaceAll(",", "");
		String tempAdd[] = address.split(" ");
		for (String iter : tempAdd) {
			prefixVerify = prefixVerify + "-" + iter;
		}
		return prefixVerify;
	}

	public void clickSpanishLabel() {
		webAppDriver.clickElementById(lbSpanishId);
		webAppDriver.verifyPresenceOfTextInATag("sitio web en Inglés");
	}

	// Added on 16-Dec-2015 to make ECI buttons visible on page
	// Author Muzfera naaz
	public void getURLWithOptimizelyTrue() {
		newURL = webAppDriver.getCurrentUrl();
		if (newURL.contains("?"))
			newURL = newURL + "&optimizely_disable=true";
		else
			newURL = newURL + "?optimizely_disable=true";
		webAppDriver.get(newURL);
	}

	public List<CateredWebElement> getAllSearchResultShowAllUnits() {
		if (webAppDriver.getCurrentUrl().contains("landing4.aspx"))
			return webAppDriver.findAllElementsByXpath(hlinkShowAllUnitsXpath1);
		else if (webAppDriver.getCurrentUrl().contains("vehicle2.aspx"))
			return webAppDriver.findAllElementsByXpath(hlinkShowAllUnitsXpath1);
		/*else if (webAppDriver.getCurrentUrl().contains("landing.aspx"))
			return webAppDriver.findAllElementsByXpath(hlinkShowAllUnitsXpath1);*/
		else if (webAppDriver.getCurrentUrl().contains("landing3.aspx"))
			return webAppDriver.findAllElementsByXpath(hlinkShowAllViewLocationXpath);
		else
			return webAppDriver.findAllElementsByXpath(hlinkShowAllUnitsXpath1);
	}

	public List<CateredWebElement> getAllPLPUnits() {
		return webAppDriver.findAllElementsByXpath(btnAllContinueXpath);
	}

	public String[] generateMoveInDate(int numberOfDays) {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, numberOfDays);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String[] dates = { moveInDate, dayOfMoveInDate };
		return dates;
	}

	public void clearChache() {
		webAppDriver.manage().deleteAllCookies();
	}

	public String getStateFullName(String stateAbbrevation) {
		if (stateAbbrevation == null)
			System.out.println("abbrevation is null");

		switch (stateAbbrevation) {
		case "AL":
			state = "Alabama";
			break;
		case "AK":
			state = "Alaska";
			break;
		case "AZ":
			state = "Arizona";
			break;
		case "AR":
			state = "Arkansas";
			break;
		case "CA":
			state = "California";
			break;
		case "CO":
			state = "Colorado";
			break;
		case "CT":
			state = "Connecticut";
			break;
		case "DE":
			state = "Delaware";
			break;
		case "FL":
			state = "Florida";
			break;
		case "GE":
			state = "Georgia";
			break;
		case "HI":
			state = "Hawaii";
			break;
		case "ID":
			state = ",Idaho";
			break;
		case "IL":
			state = "Illinois";
			break;
		case "IN":
			state = "Indiana";
			break;
		case "IA":
			state = "Iowa";
			break;
		case "KS":
			state = "Kansas";
			break;
		case "KY":
			state = "Kentucky";
			break;
		case "LA":
			state = "Louisiana";
			break;
		case "ME":
			state = "Maine";
			break;
		case "MD":
			state = "Maryland";
			break;
		case "MA":
			state = "Massachusetts";
			break;
		case "MI":
			state = "Michigan";
			break;
		case "MN":
			state = "Minnesota";
			break;
		case "MS":
			state = "Mississippi";
			break;
		case "MO":
			state = "Missouri";
			break;
		case "MT":
			state = "Montana";
			break;
		case "NE":
			state = "Nebraska";
			break;
		case "NV":
			state = "Nevada";
			break;
		case "NH":
			state = "New-Hampshire";
			break;
		case "NJ":
			state = "New-Jersey";
			break;
		case "NM":
			state = "New-Mexico";
			break;
		case "NY":
			state = "New-York";
			break;
		case "NC":
			state = "North Carolina";
			break;
		case "ND":
			state = "North-	Dakota";
			break;
		case "OH":
			state = "Ohio";
			break;
		case "OK":
			state = "Oklahoma";
			break;
		case "OR":
			state = "Oregon";
			break;
		case "PA":
			state = "Pennsylvania";
			break;
		case "RI":
			state = ",Rhode-Island";
			break;
		case "SC":
			state = "South-Carolina";
			break;
		case "SD":
			state = "South-Dakota";
			break;
		case "TN":
			state = "Tennessee";
			break;
		case "TX":
			state = "Texas";
			break;
		case "UT":
			state = "Utah";
			break;
		case "VT":
			state = "Vermont";
			break;
		case "VA":
			state = "Virginia";
			break;
		case "WA":
			state = "Washington";
			break;
		case "WV":
			state = "West-Virginia";
			break;
		case "WI":
			state = "Wisconsin";
			break;
		case "WY":
			state = "Wyoming";
			break;
		default:
			state = "no city";
			break;
		}
		return state;

	}

	public void switchOnGeoLocation() {

		webAppDriver.navigate().to("chrome://settings/content");
		webAppDriver.switchTo().frame("settings");
		CateredWebElement location = webAppDriver.findElement(By.xpath("//*[@name='location' and @value='allow']"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webAppDriver.findElement(By.xpath("//*[@id='content-settings-overlay-confirm']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
