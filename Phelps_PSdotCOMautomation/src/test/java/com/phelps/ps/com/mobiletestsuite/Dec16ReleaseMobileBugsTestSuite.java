package com.phelps.ps.com.mobiletestsuite;

import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.pheps.ps.com.mobile.locators.MobileHomeLocators;
import com.pheps.ps.com.mobile.locators.MobileLoginLocators;

public class Dec16ReleaseMobileBugsTestSuite extends BasicMobileTestSuite implements MobileLoginLocators, MobileHomeLocators{
  public enum contactUsDetails{
	  
  }
	//Bug 3295 - [INTERNAL] Contact us validations not done (Mobile) 
	@Test
  public void verifyContactUsMobileTest() {
	  
	  String invalidData="";
	  mobileLoginActions.clickOnMobileBurgerMenu();
	  mobileHomeActions.clickContactUs();
	  mobileHomeActions.clickContactSubmit();
	  webAppDriver.verifyElementPresentByXpath(lbContactUsErrorXpath);
	  
	  mobileHomeActions.enterContactUsInfo("Test Name", invalidData, invalidData, "Reservations", "Reservation Message");
	  mobileHomeActions.clickContactSubmit();
	  mobileHomeActions.clickContactSubmit();
	  mobileHomeActions.clickContactSubmit();
	  mobileHomeActions.clickContactSubmit();
	  mobileHomeActions.clickContactSubmit();
	  
	  webAppDriver.relax(500);
	  webAppDriver.verifyElementPresentByXpath(lbContactUsErrorXpath);
	  webAppDriver.verifyElementNotPresntByXpath(lbSuccessMessageXpath);
	  
	  mobileHomeActions.enterContactUsInfo("Test Name", "7565656765", "muzfera.naaz@phelpsagency.com", "Reservations", "Reservation Message");
	  webAppDriver.verifyElementPresentByXpath(lbSuccessMessageXpath);
	  
	  
	  
  }
}
