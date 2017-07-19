package com.phelps.ps.com.mobile.actions;

import java.util.List;

import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.pheps.ps.com.mobile.locators.MobilePaymentLocators;

public class MobilePaymentActions implements MobilePaymentLocators{
	MobileWebDriver webAppdriver;

	public MobilePaymentActions(MobileWebDriver webAppdriver) {
		this.webAppdriver = webAppdriver;
	}
	
	public void clickOnCheckingPaymentMethod(){
		webAppdriver.clickElementByLinkText("Checking");
		
	}
	
	public void clickOnCardPaymentMethod(){
		webAppdriver.clickElementByLinkText("Card Payment");
	}

	public void clickOnNewCheckingPayment(){
		webAppdriver.clickElementByXpath(rbtnNewCheckingAccountXpath);
	}
	public void enterCheckingRoutingNumber(String rountingNo){
		webAppdriver.findElementById(tbCheckingRoutingNumberId).clear();
		webAppdriver.enterTextToElementById(tbCheckingRoutingNumberId, rountingNo);
	}
	
	public void enterCheckingAccountNumber(String AccountNo){
		webAppdriver.findElementById(tbCheckingAccountNumberId).clear();
		webAppdriver.enterTextToElementById(tbCheckingAccountNumberId, AccountNo);
	}
	
	public void enterCheckingPaymentAmount(String amount){
		webAppdriver.findElementById(tbCheckingPaymentAmountId).clear();
		webAppdriver.enterTextToElementById(tbCheckingPaymentAmountId, amount);
	}
	public void clickOnTermsCondition(){
		webAppdriver.clickElementById(chkboxAgreeTermsId);
	}
	
	public void clickOnSaveCheckingPayment(){
		webAppdriver.clickElementById(chkboxSaveCheckingAccountId);
	}
	
	public void clickOnCheckingTurnOnAutopayCheckbox(){
		webAppdriver.clickElementById(chkboxTurnAutopayOnId);
	}
	
	public void clickOnMakePaymentButton(){
		webAppdriver.clickElementById(hlnkCheckingMakePaymentId);
	}
	
	public void clickOnHomeOnPaymentConfirmationPage(){
		webAppdriver.clickElementById(hlnkHomeId);
		webAppdriver.verifyPresenceOfTextInH1Tag("Account Overview");
	}
	
	public void deleteAllCheckingAccounts(){
		if(webAppdriver.verifyElementIsPresentByXpath(hlnkAllDeleteCheckingAccountsXpath)){
		List<CateredWebElement> allCheckingAccnts=webAppdriver.findAllElementsByXpath(hlnkAllDeleteCheckingAccountsXpath);
		
		for(CateredWebElement deletecheckingaccnt:allCheckingAccnts){
			webAppdriver.clickElement(deletecheckingaccnt);		}
		}
	}
}
