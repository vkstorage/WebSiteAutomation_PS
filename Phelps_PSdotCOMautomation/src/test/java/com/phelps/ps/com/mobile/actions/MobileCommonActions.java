package com.phelps.ps.com.mobile.actions;

import java.util.List;

import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.pheps.ps.com.mobile.locators.MobileSRPPLPLocators;

public class MobileCommonActions implements MobileSRPPLPLocators{
	
	MobileWebDriver mobileWebAppDriver;
	
	public MobileCommonActions(MobileWebDriver mobileWebAppDriver){
		this.mobileWebAppDriver=mobileWebAppDriver;
	}
	

	public List<CateredWebElement> getAllSearchResultArrows()
	{
		/*if(mobileWebAppDriver.getCurrentUrl().contains("landing.aspx"))
			return mobileWebAppDriver.findAllElementsByXpath(hlinkShowAllUnitsXpath);
		else if(mobileWebAppDriver.getCurrentUrl().contains("landing3.aspx"))
		return mobileWebAppDriver.findAllElementsByXpath(hlinkShowAllViewLocationXpath);
		else*/
			return mobileWebAppDriver.findAllElementsByXpath(hlnkAllPLPUnitsXpath);	
		
	}
}
