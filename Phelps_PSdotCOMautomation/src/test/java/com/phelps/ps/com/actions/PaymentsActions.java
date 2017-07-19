package com.phelps.ps.com.actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.RDP_Conf_HoldLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;

public class PaymentsActions implements LoginLocators, HomeLocators, CommonLocators, PaymentsLocators {

	CateredWebDriver webAppDriver;
	private LoginActions loginAction;

	public PaymentsActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
		this.loginAction = new LoginActions(webappDriver);
	}

	public void clickOnPayments() {
		webAppDriver.clickElementByXpath(lnkPaymentsXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationXpath);
	}

	public void clickOnManageMyPayments() {
		webAppDriver.clickElementByXpath(lnkManagePaymentMethodXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
	}

	public void clickOnAddPayMethod() {
		webAppDriver.clickElementById(hlnkAddPayMethodId);
		webAppDriver.verifyElementPresentById(lnkCardPaymentId);
	}

	public void clickOnCardPayment() {
		webAppDriver.clickElementById(lnkCardPaymentId);
	}

	public void clickOnDeletePaymentMethodButton() {
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dialogString = webAppDriver.findElementByXpath(txtdeleteDialogXpath).getText();
		if (dialogString.contains("AutoPay")) {
			System.out.println("inside the autopay dialogue loop");
			//webAppDriver.clickElementById(btnDeleteButtonAutoPayId);
			webAppDriver.clickAndVerifyElementInvisible(By.id(btnDeleteButtonAutoPayId), By.xpath(txtdeleteDialogXpath));
			
			//webAppDriver.findElementByXpath(btnDeleteModalXpath).sendKeys(Keys.ENTER);
		} else {
			System.out.println("outside the autopay dialogue loop");
			webAppDriver.clickElementById(btnDeleteButtonId);
		}
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
	}

	public void clickOnDeletePayamentLink() {
		webAppDriver.clickElementByXpath(lnkDeleteXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
	}

	public void deletePaymentMethods() {
		try {
			if (webAppDriver.verifyElementTextByLinkText("Manage payment methods", "Manage payment methods")) {
				clickOnManageMyPayments();
			}
			while ((webAppDriver.verifyElementTextByLinkText("Delete", "Delete"))) {
				clickOnDeletePayamentLink();
				clickOnDeletePaymentMethodButton();
			}
			webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickOnMakePaymentButton() {
		webAppDriver.clickElementByXpath(btnMakePaymentXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationMyPublicStorageAccountXpath);
		boolean found = false;
		int count = 0;
		while (!found && count < 5) {
			try {
				webAppDriver.verifyElementPresentById(tbPayAmountId);
				found = true;
			} catch (Exception e) {
				clickOnPayments();
				webAppDriver.clickElementByXpath(btnMakePaymentXpath);
				webAppDriver.verifyElementPresentByXpath(lbPageVerificationMyPublicStorageAccountXpath);
				count++;
			}
		}
	}

	public void makePaymentWithCreditCard(String cardNumber, String ccv,String cardNo, boolean savePayment,boolean isCreditCard) {
		makePaymentWithCreditCard(cardNumber, ccv, cardNo,false, savePayment,isCreditCard);
	}

	public void makePaymentWithCreditCard(String cardNumber, String ccv,String cardNo, boolean insuranceFlag, boolean savePayment,boolean isCreditCard) {
		try {
			String amount = "";
			String unitDetails = "";
			String location = "";
			String[] unitDetailsArray;
			String unit;
			if (!insuranceFlag) {
				double random = Math.random() * 2 + 1;
				amount = "" + random;
				amount = amount.substring(0, 4);
				webAppDriver.findElementById(tbPayAmountId).clear();
				webAppDriver.enterTextToElementById(tbPayAmountId, amount);
			} else {
				amount = webAppDriver.findElementByXpath("//p[@class='fRight totalHighlight']").getText();
				amount = amount.substring(1);
			}
			unitDetails = webAppDriver.findElementByXpath(lbUnitDetailsPaymentXpath).getText();
			unitDetailsArray = unitDetails.split(":");
			unit = unitDetailsArray[1];
			location = webAppDriver.findElementByXpath(lbLocationDetailsXpath).getText();
			Thread.sleep(10 * 1000);
			//webAppDriver.clickElementById(rbtnNewPaymentMethodId);
			clickNewPaymentRadioBtn();
			if(savePayment)
				clickSavePaymentMethodCheckBox();
			Thread.sleep(5 * 1000);
			webAppDriver.clickElementById(lnkCardPaymentId);
			Thread.sleep(5 * 1000);

			webAppDriver.switchtoiFrameByXpath(iframePaymentXpath);
		//	webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();
			//webAppDriver.verifyElementPresentByXpath(lbiframeErrorXpath);

			webAppDriver.findElementByXpath(lbCardNumberXpath).sendKeys(cardNumber);
			webAppDriver.selectByIndexByXpath(drpDwnNewCardExpMonthXpath, 4);
			webAppDriver.selectByIndexByXpath(drpDwnNewCardExpYearXpath, 5);
			webAppDriver.findElementById(tbNewCardCVVId).sendKeys(ccv);
			webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();

			/*
			 * Thread.sleep(2 * 1000);
			 * 
			 * 
			 * pressMultipleKeys(KeyEvent.VK_TAB, 1); Thread.sleep(2 * 1000);
			 * typeStringUsingRobot(cardNumber); Thread.sleep(2 * 1000);
			 * pressMultipleKeys(KeyEvent.VK_TAB, 1); Thread.sleep(2 * 1000);
			 * pressMultipleKeys(KeyEvent.VK_DOWN, 3); Thread.sleep(2 * 1000);
			 * pressMultipleKeys(KeyEvent.VK_TAB, 1); Thread.sleep(2 * 1000);
			 * pressMultipleKeys(KeyEvent.VK_DOWN, 3); Thread.sleep(2 * 1000);
			 * pressMultipleKeys(KeyEvent.VK_TAB, 1); Thread.sleep(2 * 1000);
			 * typeStringUsingRobot(ccv); pressMultipleKeys(KeyEvent.VK_TAB, 1);
			 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_ENTER, 1);
			 * Thread.sleep(2 * 1000);
			 */

			webAppDriver.switchTo().defaultContent();
			System.out.println("the current window is " + webAppDriver.getTitle());
			// webAppDriver.clickElementByXpath(btnAllMakePaymentXpath);
			webAppDriver.verifyElementPresentById(btnPrintCopyId);
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + unit + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + location + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + cardNo + "')]");
			if(isCreditCard)
				webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'Credit card')]");
			else
				webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'Debit card')]");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//For Reservation Deposit
	
	public void  makePaymentWithCreditCardReservationDeposit(String cardNumber, String ccv,String cardNo, String amount,boolean insuranceFlag, boolean savePayment,boolean isCreditCard,boolean isFromReservationCare) {
		try {
			
			String unitDetails = "";
			String location = "";
			String[] unitDetailsArray;
			String unit;
			
			/*
			if (!insuranceFlag) {
				
				webAppDriver.findElementById(tbPayAmountId).clear();
				webAppDriver.enterTextToElementById(tbPayAmountId, amount);
			} else {
				amount = webAppDriver.findElementByXpath("//p[@class='fRight totalHighlight']").getText();
				amount = amount.substring(1);
			}*/
			unitDetails = webAppDriver.findElementByXpath(ReservationDetailsLocators.lbStorageAddressXpath).getText();
			unitDetailsArray = unitDetails.split("\n");
			unit = unitDetailsArray[1];
			location = unitDetailsArray[2];//webAppDriver.findElementByXpath(lbLocationDetailsXpath).getText();
			Thread.sleep(10 * 1000);
			
			
			//webAppDriver.clickElementById(rbtnNewPaymentMethodId);
			/*clickNewPaymentRadioBtn();
			if(savePayment)
				clickSavePaymentMethodCheckBox();
			Thread.sleep(5 * 1000);
			webAppDriver.clickElementById(lnkCardPaymentId);
			Thread.sleep(5 * 1000);*/
			
			while(!webAppDriver.verifyElementIsPresentByXpath(iframePaymentXpath)){
			webAppDriver.clickElementById(ReservationDetailsLocators.btnEditId);
			webAppDriver.relax(30000);
			webAppDriver.clickElementByCss(ReservationDetailsLocators.btnContinueCss);
			webAppDriver.relax(30000);
			}

			webAppDriver.switchtoiFrameByXpath(iframePaymentXpath);
		//	webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();
			//webAppDriver.verifyElementPresentByXpath(lbiframeErrorXpath);

			webAppDriver.findElementByXpath(lbCardNumberXpath).sendKeys(cardNumber);
			webAppDriver.selectByIndexByXpath(drpDwnNewCardExpMonthXpath, 4);
			webAppDriver.selectByIndexByXpath(drpDwnNewCardExpYearXpath, 5);
			webAppDriver.findElementById(tbNewCardCVVId).sendKeys(ccv);
			webAppDriver.findElementByXpath(ReservationDetailsActions.btnPaymentHoldNowXpath).click();

		

			webAppDriver.switchTo().defaultContent();
			System.out.println("the current window is " + webAppDriver.getTitle());
			// webAppDriver.clickElementByXpath(btnAllMakePaymentXpath);
			
			if(isFromReservationCare){
			
			}
			else{
			webAppDriver.verifyElementPresentByLinkText(RDP_Conf_HoldLocators.hlnkPrintConftext);
			webAppDriver.verifyElementPresentByXpath("//div[contains(text(),'"+RDP_Conf_HoldLocators.lbReservationDeposit+"$" + amount + "')]");
			webAppDriver.verifyElementPresentByXpath("//div[@class='sc_inner']//div[contains(.,'" + unit + "')]");
			//webAppDriver.verifyElementPresentByXpath("//div[@class='sc_inner']//div[contains(.,'" + location + "')]");
			webAppDriver.verifyElementPresentByXpath("//div[contains(text(),'" + cardNo + "')]");
			
			if(isCreditCard)
				webAppDriver.verifyElementPresentByXpath("//div[contains(text(),'Credit Card')]");
			else
				webAppDriver.verifyElementPresentByXpath("//div[contains(text(),'Debit Card')]");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void makePaymentWithCheque(String routingNumber, String accountNumber,String chequeNo) {
		try {
			double random = Math.random() * 2 + 1;
			String amount = "" + random;
			amount = amount.substring(0, 4);
			String unitDetails = "";
			String location = "";
			String[] unitDetailsArray;
			String unit;
			webAppDriver.findElementById(tbPayAmountId).clear();
			webAppDriver.enterTextToElementById(tbPayAmountId, amount);
			unitDetails = webAppDriver.findElementByXpath(lbUnitDetailsPaymentXpath).getText();
			unitDetailsArray = unitDetails.split(":");
			unit = unitDetailsArray[1];
			location = webAppDriver.findElementByXpath(lbLocationDetailsXpath).getText();
			Thread.sleep(5 * 1000);
			webAppDriver.clickElementById(rbtnNewPaymentMethodId);
			webAppDriver.findElementByXpath(btnAllMakePaymentXpath).click();
			webAppDriver.verifyElementPresentByXpath("//span[contains(text(),'Please provide your routing number.')]");
			webAppDriver.clickElementById(rbtnNewPaymentMethodId);
			webAppDriver.findElementById(lbNewCheckRoutingNumberId).sendKeys(routingNumber);
			webAppDriver.findElementById(lbNewCheckConfRoutingNumberId).sendKeys(routingNumber);
			webAppDriver.findElementById(lbNewCheckAccntNumberId).sendKeys(accountNumber);
			webAppDriver.findElementById(lbNewCheckConfAccntNumberId).sendKeys(accountNumber);
			// Thread.sleep(10 * 1000);
			// pressMultipleKeys(KeyEvent.VK_TAB, 3);
			// Thread.sleep(2 * 1000);
			// typeStringUsingRobot(routingNumber);
			// Thread.sleep(2 * 1000);
			// pressMultipleKeys(KeyEvent.VK_TAB, 1);
			// Thread.sleep(2 * 1000);
			// typeStringUsingRobot(routingNumber);
			// Thread.sleep(2 * 1000);
			// pressMultipleKeys(KeyEvent.VK_TAB, 1);
			// Thread.sleep(2 * 1000);
			// typeStringUsingRobot(accountNumber);
			// Thread.sleep(2 * 1000);
			// pressMultipleKeys(KeyEvent.VK_TAB, 1);
			// Thread.sleep(2 * 1000);
			// typeStringUsingRobot(accountNumber);
			// Thread.sleep(2 * 1000);
			// pressMultipleKeys(KeyEvent.VK_TAB, 1);
			// Thread.sleep(2 * 1000);
			/*
			 * pressMultipleKeys(KeyEvent.VK_ENTER, 1); Thread.sleep(2 * 1000);
			 */
			webAppDriver.findElementByXpath(btnAllMakePaymentXpath).click();
			webAppDriver.verifySpanTagTextEquals("Please agree to the Terms and Conditions");
			webAppDriver.findElementById(chboxCheckAgreeTermid).click();
			webAppDriver.findElementByXpath(btnAllMakePaymentXpath).click();
			webAppDriver.verifyElementPresentById(btnPrintCopyId);
			// webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$"+amount+"')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + unit + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + location + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + chequeNo + "')]");
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'Checking')]");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void typeStringUsingRobot(String string) {
		Robot robot;
		try {
			robot = new Robot();
			byte[] bytes = string.getBytes();
			for (byte b : bytes) {
				int code = b;
				// keycode only handles [A-Z] (which is ASCII decimal [65-90])
				if (code > 96 && code < 123)
					code = code - 32;
				robot.delay(40);
				robot.keyPress(code);
				robot.keyRelease(code);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pressMultipleKeys(int eventId, int occurances) {
		try {
			Robot robot = new Robot();
			for (int i = 0; i < occurances; i++) {
				robot.delay(40);
				robot.keyPress(eventId);
				robot.keyRelease(eventId);
				Thread.sleep(1 * 1000);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSavePaymentMethodCheckBox() {
		//webAppDriver.clickElementById(rbtnNewPaymentMethodId);
		//clickNewPaymentRadioBtn();
		webAppDriver.clickElementById(chboxSavePaymentMethodId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationMyPublicStorageAccountXpath);
	}

	public void clickAutoPayCheckBox() {
		webAppDriver.clickElementById(chboxAutoPayId);
	}

	public void clickAgreeTermsCheckBox(){
		webAppDriver.clickElementById(chboxCheckAgreeTermid);
	}
	public void verifyManageAutoPayButtonIsPresent() {
		webAppDriver.verifyElementPresentByXpath(btnManageAutoPayXpath);
	}

	public void clickManageAutoPayButton() {
		webAppDriver.clickElementByXpath(btnManageAutoPayXpath);
	}

	public void verifyTurnOffAutoPayButtonIsPresent() {
		webAppDriver.verifyElementPresentByXpath(btnTurnOffAutoPayXpath);
	}

	public void verifyEditLinkForPaymentIsPresent() {
		webAppDriver.verifyElementPresentByXpath(hlnkEditForPaymentMethod);
	}

	public void clickEditLink() {
		webAppDriver.clickElementByXpath(hlnkEditForPaymentMethod);
	}

	public void clickCloseEditAutoPayModal() {
		webAppDriver.clickElementByXpath(".//*[@id='cboxClose']");
	}

	public void clickTurnOffAutoPayButton() {
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webAppDriver.clickElementByXpath(btnTurnOffAutoPayXpath);
		}

	public void clickYesForAutoPay() {
		webAppDriver.clickElementByXpath(btnYesForAutoPayXpath);
	}

	public void clickTurnOnAutoPay() {
		webAppDriver.clickElementByXpath(btnTurnOnAutoPayXpath);
	}

	public void verifyAutoPayPaymentMethodIsNotVisible() {
		webAppDriver.verifyElementIsInvisibleByXpath(lbAutoPayPaymentMethodXpath);
	}

	public void enterCardDetails(String creditCardNumber) {
		/* try { */
		webAppDriver.clickElementByXpath(hlnkCardPaymentAutoPayXpath);
		System.out.println(" window title "+ webAppDriver.getTitle());

		webAppDriver.switchtoiFrameByXpath(iframePaymentXpath);
		webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();
		System.out.println(" window title "+ webAppDriver.getTitle());
	//	webAppDriver.verifyElementPresentByXpath(lbiframeErrorXpath);
		//WebDriverWait wait=new WebDriverWait(webAppDriver, 2000);
		//wait.until(ExpectedConditions.visibilityOf(webAppDriver.findElementById("cardNumber")));
		webAppDriver.relax(1000);
		webAppDriver.findElementByXpath(lbCardNumberXpath).click();
		webAppDriver.findElementByXpath(lbCardNumberXpath).click();
		webAppDriver.findElementByXpath(lbCardNumberXpath).sendKeys(creditCardNumber);
		webAppDriver.selectByIndexByXpath(drpDwnNewCardExpMonthXpath, 4);
		webAppDriver.selectByIndexByXpath(drpDwnNewCardExpYearXpath, 5);
		webAppDriver.findElementByXpath(btniframeNewMakePaymentXpath).click();
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		webAppDriver.switchToWindowWithTitle("Payment Methods");
		System.out.println(" window title "+ webAppDriver.getTitle());

		/*
		 * Thread.sleep(30 * 1000); pressMultipleKeys(KeyEvent.VK_TAB, 3);
		 * Thread.sleep(2 * 1000); typeStringUsingRobot(creditCardNumber);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_TAB, 1);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_DOWN, 3);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_TAB, 1);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_DOWN, 3);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_TAB, 1);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_ENTER, 1);
		 * Thread.sleep(2 * 1000);
		 */
		/*
		 * } catch (InterruptedException e) { e.printStackTrace(); }
		 */
	}

	public void enterCheckingAccountDetails(String routingNumber, String accountNumber) {
		try {
			webAppDriver.clickElementByXpath(hlnkCheckingAccountAutoPayXpath);
			webAppDriver.findElementById(lbNewCheckRoutingNumberId).sendKeys(routingNumber);
			webAppDriver.findElementById(lbNewCheckConfRoutingNumberId).sendKeys(routingNumber);
			webAppDriver.findElementById(lbNewCheckAccntNumberId).sendKeys(accountNumber);
			webAppDriver.findElementById(lbNewCheckConfAccntNumberId).sendKeys(accountNumber);
			Thread.sleep(30 * 1000);
			/*pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			typeStringUsingRobot(routingNumber);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			typeStringUsingRobot(routingNumber);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			typeStringUsingRobot(accountNumber);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			typeStringUsingRobot(accountNumber);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_ENTER, 1);
			Thread.sleep(2 * 1000);*/
			webAppDriver.clickElementByXpath(btnModalTurnOnAutopayXpath);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickSavedPaymentMethod() {
		webAppDriver.clickElementById(rbtnSavedPaymentMethod);
		/*
		 * try { Thread.sleep(30 * 1000); pressMultipleKeys(KeyEvent.VK_TAB, 8);
		 * Thread.sleep(2 * 1000); pressMultipleKeys(KeyEvent.VK_ENTER, 1);
		 * Thread.sleep(2 * 1000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		webAppDriver.clickElementByXpath(btnMakePaymentSavedCreditCardXpath);
	}
	
	public void clickNewPaymentRadioBtn(){
		webAppDriver.findElementById(rbtnNewPaymentMethodId).sendKeys(Keys.ENTER);
	}

	public String getEditLinkUrl() {
		WebElement editLink = webAppDriver.findElementByXpath(hlnkEditForPaymentMethod);
		String newWindowURL = editLink.getAttribute("href");
		return newWindowURL;
	}

	public void verifyCreditCardAdded(String creditCardNumber) {
		creditCardNumber = creditCardNumber.substring(creditCardNumber.length() - 4);
		String runtimeCreditCardNumber = webAppDriver.findElementByXpath(lbCreditCardEntryXpath).getText();
		if (!runtimeCreditCardNumber.contains(creditCardNumber)) {
			throw new AssertionError();
		}
	}

	public void verifyCheckAdded(String checkNumber) {
		checkNumber = checkNumber.substring(checkNumber.length() - 4);
		String runtimeCreditCardNumber = webAppDriver.findElementByXpath(lbCheckEntryXpath).getText();
		if (!runtimeCreditCardNumber.contains(checkNumber)) {
			throw new AssertionError();
		}
	}

	public void clickEditPaymentLink() {
		webAppDriver.clickElementByLinkText(lnkEditName);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
	}

	public void clickOnSaveButtonFromEditCreditCard() {
		webAppDriver.clickElementByName(btnSaveName);
		String creditUpdatedSuccess = webAppDriver.findElementById(lbCreditCardUpdatedId).getText();
		if (!creditUpdatedSuccess.contains("Payment method was successfully updated.")) {
			throw new AssertionError();
		}

	}

	public void verifyCreditCardExpDate() {
		String runtimeCreditCardExpDate = webAppDriver.findElementByXpath(lbCreditCardExpDateEditPageXpath).getText();
		if (!runtimeCreditCardExpDate.contains("Expires: 07/20")) {
			throw new AssertionError();
		}
	}

	public void verifyCreditCardAddedInPaymentMethods(String creditCardNumber) {
		creditCardNumber = creditCardNumber.substring(creditCardNumber.length() - 4);
		
	clickOnPayments();
	webAppDriver.verifyElementPresentByXpath("//span[contains(text(),'Last 4 digits: "+creditCardNumber+"')]");
		
		

	}
public void verifyCreditCardNotAddedTwice(String creditCardNumber){
	List<CateredWebElement> count=new ArrayList<CateredWebElement>();
	creditCardNumber = creditCardNumber.substring(creditCardNumber.length() - 4);
	clickOnPayments();
	count=webAppDriver.findAllElementsByXpath("//span[contains(text(),'Last 4 digits: "+creditCardNumber+"')]");
	if(!(count.size()==1)){
		webAppDriver.captureScreenshot();
		throw new AssertionFailedError(" card payment added twice");
	}
}
	public void makePaymentWithSavedCreditCard(String CardNo,boolean isCreditCard) {
		double random = Math.random() * 2 + 1;
		String amount = "" + random;
		String unitDetails = "";
		String location = "";
		String[] unitDetailsArray;
		String unit;
		amount = amount.substring(0, 4);
		unitDetails = webAppDriver.findElementByXpath(lbUnitDetailsPaymentXpath).getText();
		unitDetailsArray = unitDetails.split(":");
		unit = unitDetailsArray[1];
		location = webAppDriver.findElementByXpath(lbLocationDetailsXpath).getText();
		webAppDriver.findElementById(tbPayAmountId).clear();
		webAppDriver.enterTextToElementById(tbPayAmountId, amount);
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(webAppDriver, 20, 500);
		// deleted this "//iframe[@class='cboxIframe']" from below line and added
		// xpath
		
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		  btnAllMakePaymentXpath)));
		 /* webAppDriver.clickElementByXpath(btnAllMakePaymentXpath);*/
		  try {
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  webAppDriver.clickAndVerifyElementInvisible(By.xpath(btnAllMakePaymentXpath), By.id("unsavedpaymethods"));
		//  webAppDriver.findElementByXpath(btnAllMakePaymentXpath).sendKeys(Keys.ENTER);
		 

		/*try {

			if (webAppDriver.verifyElementNotPresntByXpath(chboxCheckAgreeTermid)) {
				pressMultipleKeys(KeyEvent.VK_TAB, 4);

				Thread.sleep(2 * 1000);
			} else {
				pressMultipleKeys(KeyEvent.VK_TAB, 5);

				Thread.sleep(2 * 1000);
			}
			pressMultipleKeys(KeyEvent.VK_ENTER, 1);
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
		webAppDriver.verifyElementPresentById(btnPrintCopyId);
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + unit + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + location + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + CardNo + "')]");
		if(isCreditCard)
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'Credit card')]");
		else
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'Debit card')]");
		
	}

	public void makePaymentWithSavedCheckingAccount(String checqueNo) throws InterruptedException {
		double random = Math.random() * 2 + 1;
		String amount = "" + random;
		String unitDetails = "";
		String location = "";
		String[] unitDetailsArray;
		String unit="";
		amount = amount.substring(0, 4);
		webAppDriver.findElementById(tbPayAmountId).clear();
		webAppDriver.enterTextToElementById(tbPayAmountId, amount);
		webAppDriver.verifyVisibilityOfElementLocatedByXpath(btnAllMakePaymentXpath);
		webAppDriver.findElementByXpath(btnAllMakePaymentXpath).sendKeys(Keys.ENTER);
		Thread.sleep(10 * 1000);

		webAppDriver.verifyElementPresentById(btnPrintCopyId);
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + unit + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + location + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'" + checqueNo + "')]");
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'Checking')]");
		
	}

	public void clickEditInsuranceLink() {
		webAppDriver.clickElementByLinkText(lnkEditName);
	}

	public void clickTermsAndConditionsLink() {
		webAppDriver.clickElementByXpath(hlnkTOSXpath);
	}

}
