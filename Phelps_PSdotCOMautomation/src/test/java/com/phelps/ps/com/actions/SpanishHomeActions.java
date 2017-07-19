package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.SpanishHomeLocators;

public class SpanishHomeActions implements SpanishHomeLocators {

	private CateredWebDriver webAppDriver;

	public SpanishHomeActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	public void enterSearchCriteria(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbSearchBoxId, searchCriteria);
	}

	public void clickSearchButton() {
		webAppDriver.clickElementById(btnSearchId);
	}

}
