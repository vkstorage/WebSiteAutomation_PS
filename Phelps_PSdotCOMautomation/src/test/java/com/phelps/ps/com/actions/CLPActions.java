package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.utils.VerificationStatus;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CLPLocators;

public class CLPActions extends CommonActions implements CLPLocators {

	CateredWebDriver webAppDriver;
	VerificationStatus verifyStatus;

	public CLPActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		
	}

	/*
	 * Click Storage Statistics Tab
	 * Verifies Storage Statistics Highlighted Tab contains purple bar at the top and is lighter in color
	 */
	public void clickStorageStatisticsTab() {

		webAppDriver.clickElementById(lbStorageStatisticsTabId);
		webAppDriver.verifyElementPresentByXpath(lbStorageStatisticsTabSelectedBorderTopXpath);
		webAppDriver.verifyElementPresentByXpath(lbStorageStatisticsTabSelectedBgColorXpath);
		webAppDriver.verifyElementPresentByXpath(lbStorageStatisticsTabSelectedFgColorXpath);

	}

	/*
	 * Click City Information Tab
	 * Verifies City Information Highlighted Tab contains purple bar at the top and is lighter in color
	 */
	public void clickCityInformationTab() {

		webAppDriver.clickElementById(lbCityInformationTabId);
		webAppDriver.verifyElementPresentByXpath(lbCityInformationTabSelectedBorderTopXpath);
		webAppDriver.verifyElementPresentByXpath(lbCityInformationTabSelectedBgColorXpath);
		webAppDriver.verifyElementPresentByXpath(lbCityInformationTabSelectedFgColorXpath);

	}

	/*
	 * Click FAQs Tab
	 * Verifies FAQs Highlighted Tab contains purple bar at the top and is lighter in color
	 */
	public void clickFAQsTab() {

		webAppDriver.clickElementById(lbFAQsTabId);
		webAppDriver.verifyElementPresentByXpath(lbFAQsTabSelectedBorderTopXpath);
		webAppDriver.verifyElementPresentByXpath(lbFAQsTabSelectedBgColorXpath);
		webAppDriver.verifyElementPresentByXpath(lbFAQsTabSelectedFgColorXpath);

	}

	/*
	 * Click Storage Units Tab
	 * Verifies Storage Units Highlighted Tab contains purple bar at the top and is lighter in color
	 */
	public void clickStorageUnitsTab() {

		webAppDriver.clickElementById(lbStorageUnitsTabId);
		webAppDriver.verifyElementPresentByXpath(lbStorageUnitsTabSelectedBorderTopXpath);
		webAppDriver.verifyElementPresentByXpath(lbStorageUnitsTabSelectedBgColorXpath);
		webAppDriver.verifyElementPresentByXpath(lbStorageUnitsTabSelectedFgColorXpath);
	}
	
	public VerificationStatus getStatusMessage(String eachLine, VerificationStatus verifyStatus)
	{
		this.verifyStatus=verifyStatus;
		String statusMessage=webAppDriver.verifyTextPresentInHTML(eachLine);
		if(!VerificationStatus.isTextFound)
		verifyStatus.setStatusMessage(statusMessage);
		return verifyStatus;
	}


}
