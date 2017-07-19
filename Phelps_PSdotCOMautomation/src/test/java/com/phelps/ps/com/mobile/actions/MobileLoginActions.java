package com.phelps.ps.com.mobile.actions;

import org.openqa.selenium.Keys;

import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.pheps.ps.com.mobile.locators.MobileLoginLocators;

public class MobileLoginActions implements MobileLoginLocators{
	
	MobileWebDriver webAppDriver;
	public MobileLoginActions(MobileWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}
	
	public void clickOnLogin(){
		webAppDriver.clickElementByXpath(hlnkLoginXpath);
		
		
	}
	
	public void enterUsername(String username){
		webAppDriver.findElementById(tbUsernameId).clear();
		webAppDriver.enterTextToElementById(tbUsernameId, username);
		webAppDriver.findElementById(tbUsernameId).sendKeys(Keys.TAB);
	}
	
	public void enterPassword(String password){
		webAppDriver.findElementById(tbPasswordId).clear();
	//	webAppDriver.clickElementById(tbPasswordId);
		webAppDriver.enterTextToElementById(tbPasswordId, password);
	}
	
	public void clickOnLoginbutton(){
	
		webAppDriver.clickElementById(btnLoginButtonId);
	}
	
	public void clickOnMobileBurgerMenu(){
		webAppDriver.clickElementByXpath(hlnkhamBurgerLinkXpath);
		webAppDriver.verifyElementPresentById(hlnkhamBrgrLinkSearchNearbyId);
	}
	

}
