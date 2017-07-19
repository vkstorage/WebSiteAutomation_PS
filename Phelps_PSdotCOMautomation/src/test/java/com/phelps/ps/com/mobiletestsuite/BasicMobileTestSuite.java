package com.phelps.ps.com.mobiletestsuite;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;

import com.phelps.ps.com.actions.CLPActions;
import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.utils.BrowserType;
import com.phelps.ps.com.autotest.utils.DeviceType;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.basetest.BaseTest;
import com.phelps.ps.com.mobile.actions.MobileAccountInfoActions;
import com.phelps.ps.com.mobile.actions.MobileCommonActions;
import com.phelps.ps.com.mobile.actions.MobileHomeActions;
import com.phelps.ps.com.mobile.actions.MobileLoginActions;
import com.phelps.ps.com.mobile.actions.MobilePaymentActions;
import com.phelps.ps.com.mobile.actions.MobileVideoLandingPagesActions;
import com.phelps.ps.com.nevigation.Navigator;

public class BasicMobileTestSuite extends BaseTest {
	
	protected MobileWebDriver webAppDriver;
	protected static MobileLoginActions mobileLoginActions;
	protected static MobileAccountInfoActions mobileAccountInfoActions;
	protected static MobilePaymentActions mobilePaymentActions;
	protected static Navigator navigator;
	protected static MobileVideoLandingPagesActions mobileVideoLandingPagesActions;
	protected static MobileCommonActions mobileCommonActions;
	protected static MobileHomeActions mobileHomeActions;
	protected static CLPActions clpActions;
	@BeforeClass
	public void launchMobileBroserTest() throws MalformedURLException {
		webAppDriver = new MobileWebDriver(DeviceType.ANDROID, BrowserType.chrome);
		webAppDriver.get(baseUrl);
		mobileLoginActions=new MobileLoginActions(webAppDriver);
		mobileAccountInfoActions=new MobileAccountInfoActions(webAppDriver);
		mobilePaymentActions=new MobilePaymentActions(webAppDriver);
		mobileVideoLandingPagesActions=new MobileVideoLandingPagesActions(webAppDriver);
		mobileCommonActions=new MobileCommonActions(webAppDriver);
		mobileHomeActions=new MobileHomeActions(webAppDriver);
		clpActions=new CLPActions(webAppDriver);
		navigator=new Navigator(webAppDriver);
		//
	}

	@AfterClass
	public void closeMobileBrowserTest(){
		webAppDriver.closeAllWindows();
	}
}
