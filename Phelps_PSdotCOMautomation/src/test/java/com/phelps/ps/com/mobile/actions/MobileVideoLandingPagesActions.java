package com.phelps.ps.com.mobile.actions;

import com.phelps.ps.com.actions.CommonActions;
import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.pheps.ps.com.mobile.locators.MobileSRPPLPLocators;
import com.pheps.ps.com.mobile.locators.MobileVideoLandingPagesLocators;

public class MobileVideoLandingPagesActions implements MobileVideoLandingPagesLocators , MobileSRPPLPLocators {
	
	MobileWebDriver mobileWebAppDriver;
	MobileCommonActions mobileCommonActions;
	
	public MobileVideoLandingPagesActions(MobileWebDriver mobileWebAppDriver){
		this.mobileWebAppDriver=mobileWebAppDriver;
		mobileCommonActions=new MobileCommonActions(mobileWebAppDriver);
	}
	
	
	
	public void enterSearchText(String searchText){
		mobileWebAppDriver.enterTextToElementByXpath(tbMobileSearchBoxVideoPageXpath, searchText);
		
	}
	
	public void clickOnFind(){
		mobileWebAppDriver.clickElementById(btnMobileFindId);
		mobileWebAppDriver.verifyElementPresentByXpath(hlnkTabListXpath);
	}
	
	public void searchTest(String searchText){
		enterSearchText(searchText);
		clickOnFind();
	}

}
