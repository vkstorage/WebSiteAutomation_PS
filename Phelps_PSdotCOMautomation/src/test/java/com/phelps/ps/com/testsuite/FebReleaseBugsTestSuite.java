package com.phelps.ps.com.testsuite;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.phelps.ps.com.locators.PaymentsLocators;

public class FebReleaseBugsTestSuite extends BasicTestSuite implements PaymentsLocators{
	
	
	
	//Bug 2806
	//@Test
	public void verifyProRateTextRemovedTest(){
		String proRateText="We recommend you schedule your move-out in advance (before the 1st of your intended move-out month) because in some cases, we can pro-rate your rent for the last month. Please contact your Property Manager to see if you’re eligible.";
		webAppDriver.get(baseUrl+"/faq.aspx");
		if((webAppDriver.verifyTextContainInAnyTag(proRateText))){
			Reporter.log("the text " + proRateText + " is present in FAQs");
			webAppDriver.captureScreenshot();
			throw new AssertionError("the text " + proRateText + " is present in FAQs");
		}
		navigator.login("AlfredCasazza", "test123");
		webAppDriver.get(baseUrl+"/SelfCare/StorageUnitDetails.aspx");
		if((webAppDriver.verifyTextContainInAnyTag(proRateText))){
			Reporter.log("the text " + proRateText + " is present in FAQs");
			webAppDriver.captureScreenshot();
			throw new AssertionError("the text " + proRateText + " is present in FAQs");
		}
		webAppDriver.get(baseUrl+"/SelfCare/MyStorageUnits.aspx");
		if((webAppDriver.verifyTextContainInAnyTag(proRateText))){
			Reporter.log("the text " + proRateText + " is present in FAQs");
			webAppDriver.captureScreenshot();
			throw new AssertionError("the text " + proRateText + " is present in FAQs");
		}
		webAppDriver.get(baseUrl+"/SelfCare/ChangeMoveOutDateList.aspx");
		if((webAppDriver.verifyTextContainInAnyTag(proRateText))){
			Reporter.log("the text " + proRateText + " is present in FAQs");
			webAppDriver.captureScreenshot();
			throw new AssertionError("the text " + proRateText + " is present in FAQs");
		}
		webAppDriver.get(baseUrl+"/SelfCare/YourMovedOutUnits.aspx");
		if((webAppDriver.verifyTextContainInAnyTag(proRateText))){
			Reporter.log("the text " + proRateText + " is present in FAQs");
			webAppDriver.captureScreenshot();
			throw new AssertionError("the text " + proRateText + " is present in FAQs");
		}
	}
	
	//bug 2801 -for march release
	//@Test
	 public void verifySavingCardNewPaymentRadioTest() throws InterruptedException{
		 String creditCardNumber1 = "375484151689299";
		 webAppDriver.get(baseUrl);
		 navigator.login("AlfredCasazza", "test123");
		 navigator.gotoMyAccountLoggedIn();
		 selfcareSummaryActions.clickPayment();
		 paymentActions.deletePaymentMethods();
		 paymentActions.clickOnPayments();
		 paymentActions.clickOnMakePaymentButton();
		 webAppDriver.findElementById(tbPayAmountId).clear();
			webAppDriver.enterTextToElementById(tbPayAmountId, "1.35");
			paymentActions.clickNewPaymentRadioBtn();
			Thread.sleep(5 * 1000);
			webAppDriver.clickElementById(lnkCardPaymentId);
			Thread.sleep(10 * 1000);
			webAppDriver.switchtoiFrameByXpath(iframePaymentXpath);
			webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();
			webAppDriver.verifyElementPresentByXpath(lbiframeErrorXpath);

			webAppDriver.findElementByXpath(lbCardNumberXpath).sendKeys(creditCardNumber1);
			webAppDriver.selectByIndexByXpath(drpDwnNewCardExpMonthXpath, 4);
			webAppDriver.selectByIndexByXpath(drpDwnNewCardExpYearXpath, 5);
			webAppDriver.findElementById(tbNewCardCVVId).sendKeys("222");
			webAppDriver.switchTo().defaultContent();
			paymentActions.clickSavePaymentMethodCheckBox();
			paymentActions.clickNewPaymentRadioBtn();
			webAppDriver.switchtoiFrameByXpath(iframePaymentXpath);
			webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();
			webAppDriver.switchTo().defaultContent();
			webAppDriver.relax(500);
			paymentActions.clickOnPayments();
			creditCardNumber1 = creditCardNumber1.substring(creditCardNumber1.length() - 4);
			String runtimeCreditCardNumber = webAppDriver.findElementByXpath("//label[contains(text(),'******')]").getText();
			if (!runtimeCreditCardNumber.contains(creditCardNumber1)) {
				throw new AssertionError();
			}
		//div[@class="srp_res_clm srp_clm100"]//div[contains(text(),'Online only*')]

	 }
	 
	 //Issue 2819
	 
	 @Test
	 public void verifySRPCopyTextTest()
	 {
		 webAppDriver.get(baseUrl);
		 navigator.gotoDefaultSearchResults("il");
		 webAppDriver.verifyElementPresentByXpath("//div[@class='srp_res_clm srp_clm100']//div[contains(text(),'Online only*')]");
		 webAppDriver.verifyElementPresentByXpath("div[@class='reg-price'][contains(., 'Phone/At Location')]");
		 if(webAppDriver.verifyTextContainInAnyTag("Online Special*")){
			 throw new AssertionError(" online special* text present on srp page");
		 }
	 }
	

}
