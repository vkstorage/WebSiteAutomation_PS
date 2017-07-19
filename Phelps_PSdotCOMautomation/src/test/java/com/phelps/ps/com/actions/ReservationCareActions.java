package com.phelps.ps.com.actions;

import java.text.MessageFormat;

import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.ReservationCareLoginLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;

public class ReservationCareActions
		implements ReservationCarePageLocators, PLPLocators, ReservationDetailsLocators, ReservationCareLoginLocators {

	private CateredWebDriver webAppDriver;
	private CommonActions commonActions;
	

	public ReservationCareActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions(webAppDriver);
	}

	public void clickLeftExpressCheckIn() {
		webAppDriver.clickElementByXpath(btnlLeftExpressCheckInXpath);
	}

	public void clickRightExpressCheckIn() {
		webAppDriver.clickElementByXpath(btnRightExpressCheckInXpath);
	}

	public void clickChangeSizeLink() {
		webAppDriver.clickElementByLinkText(hLinkReservationCareChangeSizeLink);
		webAppDriver.verifyElementPresentById(hlnkStorageUnitTabId);
	}

	public void clickChangeLocationLink() {
		webAppDriver.clickElementByLinkText(hLinkReservationCareChangeLocationLink);
		webAppDriver.relax(500);
		webAppDriver.verifyElementPresentByXpath(hlnkRDpStorageUnitsTabXpath);
	}

	public void clickAddCalendarLink() {
		// webAppDriver.clickElementByLinkText("Add to calendar");
		webAppDriver.clickElementById(hlnkAddToCalendarId);
		webAppDriver.switchtoiFrameByXpath(iFrameReservationCareXpath);
		webAppDriver.verifySpanTagTextEquals(lbAddMoveInDateText);
	}

	public void clickReserveAnotherUnit() {
		webAppDriver.clickElementByXpath(hlnkReserveAnotherUnitXpath);
		webAppDriver.relax(500);
		webAppDriver.verifyElementPresentByXpath(hlnkRDpStorageUnitsTabXpath);
	}

	public void closeAddCalendarIframe() {
		// webAppDriver.clickElementById(btnIframeCloseId);
		webAppDriver.switchTo().defaultContent();
	}

	public void clickChooseAnotherreservationLinkOnReservationCare() {
		webAppDriver.clickElementByLinkText(hLinkReservationCareChooseAnotherReservationLink);
		webAppDriver.relax(500);
		webAppDriver.verifySpanTagTextEquals(lbManageYourReservationSpanText);
	}

	public void clickChooseAnotherreservationLinkOnECIPage() {
		// webAppDriver.clickElementByLinkText(hLinkReservationCareChooseAnotherReservationLink);
		webAppDriver.clickElementByLinkText("Choose another reservation");
		webAppDriver.relax(500);
		webAppDriver.verifySpanTagTextEquals(lbExpressCheckInLiText);
	}

	public String clickChangeDateLink(String newdate, int isDeposit) {
		webAppDriver.clickElementByLinkText(hLinkReservationCareChangeDateLink);
		webAppDriver.switchtoiFrameByXpath(iFrameReservationCareXpath);
		webAppDriver.verifyDivTagTextContains("Change Your Move-In Date");
		String changedDate = selectChangeDate(newdate, isDeposit);
		closeAddCalendarIframe();
		webAppDriver.navigate().refresh();
		return changedDate;
	}

	public String selectChangeDate(String date, int isDepo) {
		int dt = Integer.parseInt(date);
		int newDateInt = dt;
		String newDate = "" + newDateInt;
		if (isDepo==0) {
			webAppDriver.verifyElementPresentByXpath(lbCalenderDepositTextXpath);
			String finalXpath = MessageFormat.format(linkDepositDateXpath, date);
			webAppDriver.clickElementByXpath(finalXpath);
		} else if(isDepo==1) {
			webAppDriver.verifyElementNotPresntByXpath(lbCalenderDepositTextXpath);
			webAppDriver.clickElementByLinkText(newDate);
		}
		else
		{
			webAppDriver.verifyElementPresentByXpath(lbCalenderDepositTextXpath);
			webAppDriver.clickElementByLinkText(newDate);
		}
		clickSaveDate();
		return newDate;

	}

	public void clickSaveDate() {
		webAppDriver.clickElementByXpath(".//*[@id='form1']/div[3]/input[1]");
		//webAppDriver.verifySpanTagTextEquals("Your reservation date has been changed successfully.");
	}

	public void verifyReservationCareDetails(String unitSize, String streetAdd, String City, String state, String zip) {
		webAppDriver.verifyTextContainInAnyTag(unitSize);
		webAppDriver.verifyTextContainInAnyTag(streetAdd);
		webAppDriver.verifyTextContainInAnyTag(City);
		webAppDriver.verifyTextContainInAnyTag(state);
		webAppDriver.verifyTextContainInAnyTag(zip);

	}

	public void verifyUserLoggedInToReservationCare(String reservationNumber, String fullName) {
		boolean isNamePresent = true;
		String[] name = fullName.split(" ");
		if (webAppDriver.verifyTextContainInAnyTag(name[0]))
			isNamePresent = false;
		if (webAppDriver.verifyTextContainInAnyTag(name[1]))
			isNamePresent = false;
		if (isNamePresent) {
			Reporter.log(fullName + " not present ");
			throw new AssertionError(fullName + " is not displayed");
		}
		// webAppdDriver.verifySpanTagTextEquals(fullName);
		webAppDriver.verifyTextContainInAnyTag(lbReservationNoText + reservationNumber);
	}

}
