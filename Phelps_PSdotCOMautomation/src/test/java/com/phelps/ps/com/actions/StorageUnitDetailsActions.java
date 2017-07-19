package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.StorageUnitDetailsLocators;

public class StorageUnitDetailsActions implements StorageUnitDetailsLocators {
	private CateredWebDriver webAppDriver;

	public StorageUnitDetailsActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	public void purchaseInsurance() {
		webAppDriver.clickElementByXpath(btnPurchaseInsuranceClickHereXpath);
		webAppDriver.selectOptionByXpath(op3000CoverageXpath);
		webAppDriver.clickElementByXpath(btnSubmitXpath);
		webAppDriver.verifyElementTextContainsByXpath(lbThankYouXpath, "Thank you");
	}

}
