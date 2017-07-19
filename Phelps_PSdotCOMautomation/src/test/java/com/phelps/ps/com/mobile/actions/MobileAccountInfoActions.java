package com.phelps.ps.com.mobile.actions;

import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.pheps.ps.com.mobile.locators.MobileAccountInfoLocators;

public class MobileAccountInfoActions implements MobileAccountInfoLocators{

	MobileWebDriver webAppDriver;

	public MobileAccountInfoActions(MobileWebDriver webAppDriver){
		this.webAppDriver=webAppDriver;
		
	}

	public void clickOnLogout(){
		webAppDriver.clickElementByXpath(hlnkLogoutXpath);
	}

	public void clickOnPayThisUnit(){
		webAppDriver.clickElementByXpath(hlnkPayThisUnitXpath);
		
	}
}
