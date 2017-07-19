package com.phelps.ps.com.actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.InsuranceLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.PaymentsLocators;

public class InsuranceActions implements LoginLocators, HomeLocators, CommonLocators, PaymentsLocators, InsuranceLocators {

	CateredWebDriver webAppDriver;
	private LoginActions loginAction;

	public InsuranceActions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
		this.loginAction = new LoginActions(webappDriver);
	}

	public void clickOnMyStorageUnits() {
		webAppDriver.clickElementByLinkText("My Storage Units");
		webAppDriver.verifyElementPresentByXpath(lbMyStorageUnitsXpath);
		webAppDriver.clickElementByLinkText("View full details");
	}

	public String getInsuranceWindowURL() {
		WebElement clickHereBtn = webAppDriver.findElementByXpath(btnClickHereXpath);
		String newWindowURL = clickHereBtn.getAttribute("href");
		return newWindowURL;
	}

	public void clickOnSelectInsurance() {
		clickOnSelectInsurance(3000);
	}

	public void clickOnSelectInsurance(int amount)
	{
		if(amount==3000)
		{
			webAppDriver.clickElementByXpath(rdBtnForInsuranceAmtXpath);
		}
		else if(amount==4000)
		{
			webAppDriver.clickElementByXpath(rBtnForInsuranceAmt4000Xpath);
		}
		else if(amount==5000)
		{
			webAppDriver.clickElementByXpath(rBtnForInsuranceAmt5000Xpath);
		}

		webAppDriver.clickElementByXpath(btnSubmitXpath);
		webAppDriver.verifyElementPresentById(btnContinueId);
		webAppDriver.clickElementById(btnContinueId);
		webAppDriver.verifyElementPresentByXpath(lbMakePaymentXpath);	
	}
	
	public void clickOnDeletePaymentMethodButton() {

		String dialogString = webAppDriver.findElementByXpath(txtdeleteDialogXpath).getText();
		if (dialogString.contains("AutoPay")) {
			webAppDriver.clickElementById(btnDeleteButtonAutoPayId);
		} else {
			webAppDriver.clickElementById(btnDeleteButtonId);
		}
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
	}

	public void clickOnDeletePayamentLink() {
		webAppDriver.clickElementByXpath(lnkDeleteXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationManageMyPayementsXpath);
	}

	public void makePaymentWithCreditCard(String cardNumber, String ccv) {
		try {
			double random = Math.random() * 2 + 1;
			String amount = "" + random;
			amount = amount.substring(0, 4);
			webAppDriver.findElementById(tbPayAmountId).clear();
			webAppDriver.enterTextToElementById(tbPayAmountId, amount);
			webAppDriver.clickElementById(lnkCardPaymentId);
			Thread.sleep(30 * 1000);
			webAppDriver.clickElementById(rbtnNewPaymentMethodId);
			Thread.sleep(10 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 4);
			Thread.sleep(2 * 1000);
			typeStringUsingRobot(cardNumber);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_DOWN, 3);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_DOWN, 3);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			typeStringUsingRobot(ccv);
			pressMultipleKeys(KeyEvent.VK_TAB, 1);
			Thread.sleep(2 * 1000);
			pressMultipleKeys(KeyEvent.VK_ENTER, 1);
			Thread.sleep(2 * 1000);
			webAppDriver.verifyElementPresentById(btnPrintCopyId);
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void makePaymentWithCheque(String routingNumber, String accountNumber) {
		try {
			double random = Math.random() * 2 + 1;
			String amount = "" + random;
			amount = amount.substring(0, 4);
			webAppDriver.findElementById(tbPayAmountId).clear();
			webAppDriver.enterTextToElementById(tbPayAmountId, amount);
			Thread.sleep(5 * 1000);
			webAppDriver.clickElementById(rbtnNewPaymentMethodId);
			Thread.sleep(10 * 1000);
			pressMultipleKeys(KeyEvent.VK_TAB, 3);
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
			webAppDriver.verifyElementPresentById(btnPrintCopyId);
			webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
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
		webAppDriver.clickElementById(rbtnNewPaymentMethodId);
		webAppDriver.clickElementById(chboxSavePaymentMethodId);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationMyPublicStorageAccountXpath);
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

	public void makePaymentWithSavedCreditCard() {
		double random = Math.random() * 2 + 1;
		String amount = "" + random;
		amount = amount.substring(0, 4);
		webAppDriver.findElementById(tbPayAmountId).clear();
		webAppDriver.enterTextToElementById(tbPayAmountId, amount);
		webAppDriver.clickElementByXpath(btnMakePaymentSavedCreditCardXpath);
		webAppDriver.verifyElementPresentById(btnPrintCopyId);
		webAppDriver.verifyElementPresentByXpath("//p[contains(text(),'$" + amount + "')]");
	}

}
