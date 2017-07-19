package com.phelps.ps.com.actions;

import javax.jws.WebParam;

import org.openqa.selenium.By;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.baseparams.BaseParams;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.LoginLocators;

public class LoginActions implements LoginLocators, HomeLocators, CommonLocators, BaseParams {

	CateredWebDriver webAppDriver;

	public LoginActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	/**
	 * Enter User Name
	 */
	public void enterUserName(String userName) {
		webAppDriver.enterTextToElementById(tbUserNameId, userName);
	}

	/**
	 * Enter password
	 */
	public void enterPassword(String password) {
		webAppDriver.enterTextToElementById(tbPasswordId, password);
	}

	/**
	 * Click on Login Button
	 */
	public void clickOnLogin() {
		webAppDriver.clickElementById(btnLoginId);
	/*	if(webAppDriver.verifyElementPresentBy(By.xpath(iframeSelfcareSummaryIframeXpath))){
			webAppDriver.switchtoiFrameByXpath(iframeSelfcareSummaryIframeXpath);
			webAppDriver.clickElementByLinkText(hlinkRemindMeLaterlink);
			webAppDriver.switchTo().defaultContent();
			
		}*/
		
		webAppDriver.verifySpanTagTextEquals("My Public Storage Account");
	}

	/**
	 * Click on Log Out Button
	 */
	public void clickOnLogOut() {
		webAppDriver.clickElementById(linkLogoutId);
		webAppDriver.verifyElementPresentByCss(".hpSearchInput");
	}

	/**
	 * Click on MyAccount
	 */
	public void clickOnMyAccount() {
		if(webAppDriver.verifyElementIsPresentByXpath(btnMyAccountXpath)){
webAppDriver.clickElementById(btnMyAccountNewId);

		//webAppDriver.clickElementByXpath(btnMyAccountLoginXpath);
webAppDriver.clickElementById(btnMyAccountLoginId);
		}
		/*webAppDriver.clickElementById(btnMyAccountLoggedInId);
		webAppDriver.clickElementById(btnMyAccountLoginId);*/
	}
	
	public void clickOnLogo() {
		String stringToCheck = "Your Storage Info";
		webAppDriver.clickElementByXpath(imgLogoImageXpath);
	}
	/**
	 * This method will redirect to Home page from Moving and Supplies Page
	 */
	public void clickOnMovingSuppliesLogo() {
		webAppDriver.get(baseUrl);
		webAppDriver.verifyElementPresentByXpath(menuItmStorageXpath);
	}

}
