package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.MovingSuppliesLocators;

public class MovingSuppliesActions extends CommonActions implements MovingSuppliesLocators{
	CateredWebDriver webAppDriver;

	public MovingSuppliesActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}
	
	public void clickAndVerifyUsefulPackingTipsLink(){
		webAppDriver.clickElementByXpath(hlnkUsefulPackingTipsXpath);
		String currentUrl = webAppDriver.getCurrentUrl();
	}
}
