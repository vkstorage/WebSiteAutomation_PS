package com.phelps.ps.com.actions;

import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.utils.PropertyLoader;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;

public class ReservationDetailsActions implements ReservationDetailsLocators, SearchResultsPageLocators {

	CateredWebDriver webAppDriver;
	//private String lbRDDepositAmountCss;


	public ReservationDetailsActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	/**
	 * Enter Customer's first name
	 * 
	 * @param firstName
	 */
	public void enterFirstName(String firstName) {
		try {
			if (!webAppDriver.findElementById((tbFirstNameId)).getAttribute("readOnly").equals("null")) {

				if (!webAppDriver.findElementById((tbFirstNameId)).getAttribute("readOnly").equals("true")) {
					webAppDriver.enterTextToElementById(tbFirstNameId, firstName);
				}
			}

		} catch (NullPointerException e) {
			webAppDriver.enterTextToElementById(tbFirstNameId, firstName);
		}
	}

	/**
	 * Enter Customer's last name
	 * 
	 * @param lastName
	 */
	public void enterLastName(String lastName) {
		try {
			if (webAppDriver.findElementById((tbLastNameId)).getAttribute("readOnly").equals(null)) {

				if (!webAppDriver.findElementById((tbLastNameId)).getAttribute("readOnly").equals("true")) {
					webAppDriver.enterTextToElementById(tbLastNameId, lastName);
				}
			}

		} catch (Exception e) {
				webAppDriver.enterTextToElementById(tbLastNameId, lastName);
		}
	}

	/**
	 * Enter Customer's phone number
	 * 
	 * @param phoneNumber
	 */
	public void enterPhoneNumber(String phoneNumber) {
		try {
			if (webAppDriver.findElementById((tbPhoneNumberId)).getAttribute("readOnly").equals(null)) {

				if (!webAppDriver.findElementById((tbPhoneNumberId)).getAttribute("readOnly").equals("true")) {
					webAppDriver.enterTextToElementById(tbPhoneNumberId, phoneNumber);
				}
			}

		} catch (Exception e) {
				webAppDriver.enterTextToElementById(tbPhoneNumberId, phoneNumber);
		}
	}

	/**
	 * Enter Phone ext
	 * 
	 * @param ext
	 */
	public void enterExt(String ext) {
		try {
			if (webAppDriver.findElementById((tbExtId)).getAttribute("readOnly").equals(null)) {

				if (!webAppDriver.findElementById((tbExtId)).getAttribute("readOnly").equals("true")) {
					webAppDriver.enterTextToElementById(tbExtId, ext);
				}
			}

		} catch (Exception e) {
				webAppDriver.enterTextToElementById(tbExtId, ext);
		}
	}

	/**
	 * Enter Customer's email address
	 * 
	 * @param email
	 */
	public void enterEmail(String email) {
		try {
			if (webAppDriver.findElementById((tbEmailId)).getAttribute("readOnly").equals("null")) {

				if (!webAppDriver.findElementById((tbEmailId)).getAttribute("readOnly").equals("true")) {
			
					webAppDriver.enterTextToElementById(tbEmailId, email);
				}
			}
		} catch (Exception e) {
				webAppDriver.enterTextToElementById(tbEmailId, email);
		}
	}

	/**
	 * Enter Email confirmation
	 * 
	 * @param emailConf
	 */
	public void enterConfirmEmail(String emailConf) {
		webAppDriver.enterTextToElementById(tbEmailConfirmId, emailConf);
	}

	/**
	 * Enter Account password
	 * 
	 * @param password
	 */
	public void enterPassword(String password) {
		if (PropertyLoader.loadProperty("browser.type").get().equals("chrome")) {
			Actions actions = new Actions((ChromeDriver) webAppDriver.getDriver());
			actions.moveToElement(webAppDriver.getDriver().findElement(By.id(tbPasswordId)));
			actions.click();
			actions.sendKeys(password);
			actions.build().perform();

			// ((ChromeDriver)
			// webAppDriver.getDriver()).findElement(By.id(tbPasswordId)).sendKeys(password);
		} else {
			webAppDriver.getDriver().findElement(By.id(tbPasswordId)).sendKeys(password);
		}
	}

	/**
	 * Enter confirm password
	 * 
	 * @param passwordConf
	 */
	public void enterConfirmPassword(String passwordConf) {
		if (PropertyLoader.loadProperty("browser.type").get().equals("chrome")) {
			Actions actions = new Actions((ChromeDriver) webAppDriver.getDriver());
			actions.moveToElement(webAppDriver.getDriver().findElement(By.id(tbConfirmPassowrdId)));
			actions.click();
			actions.sendKeys(passwordConf);
			actions.build().perform();

			// ((ChromeDriver)
			// webAppDriver.getDriver()).findElement(By.id(tbConfirmPassowrdId)).sendKeys(passwordConf);
		} else {
			webAppDriver.getDriver().findElement(By.id(tbConfirmPassowrdId)).sendKeys(passwordConf);
		}

	}

	/**
	 * Click move-in date
	 * 
	 * @param date
	 */
	public void selectMoveInDate(String date,boolean isDeposit) {
		
		webAppDriver.clickElementById(tbMoveInDateId);
		if(isDeposit){
			webAppDriver.verifyElementPresentByXpath(lbCalenderDepositTextXpath);
			String finalXpath=MessageFormat.format(linkDepositDateXpath, date);
			webAppDriver.clickElementByXpath(finalXpath);
		}
		else{
		
		webAppDriver.clickElementByLinkText(date);
		}
	}

	/**
	 * Enter all reservation details
	 * 
	 * @param fName
	 * @param lName
	 * @param phone
	 * @param ext
	 * @param email
	 * @param emailConf
	 * @param password
	 * @param passwordConf
	 * @param date
	 */
	public void enterAllReservationDetails(String fName, String lName, String phone, String ext, String email, String emailConf,
			String date) {
	
		enterFirstName(fName);
		enterLastName(lName);
		enterPhoneNumber(phone);
		enterExt(ext);
		enterEmail(emailConf);
		enterConfirmEmail(emailConf);
		// deleted enter password and confirm password due to WC2 change Test
		// scenario 1.3.1->1.2
		selectMoveInDate(date,false);
	}

	/**
	 * Select hold now option
	 */
	public void selectHoldNow() {
		webAppDriver.selectOptionById(opHoldUnitId);
	}

	/**
	 * Select Express check-in option
	 */
	public void selectExpressCheckIn() {

		webAppDriver.selectOptionById(opECIId);
	}

	/**
	 * Click hold now or complete button
	 */
	public void clickHoldNowOrComplete() {
		webAppDriver.relax(5000);
		webAppDriver.clickElementByClassname(btnHoldNowCompleteClass);
	}

	/**
	 * 
	 */
	public void clickViewLocationInfoLink() {
		webAppDriver.clickElementByLinkText("View Location Info");
	}

	public void clickChangeLocationLink() {
		webAppDriver.clickElementByLinkText("Change Location");

	}

	/**
	 * clicks on Change Unit Link
	 */

	public void clickChangeUnitLink() {
		webAppDriver.clickElementByLinkText("Change Unit");
	}

	/**
	 * click express checkin
	 */
	public void clickExpressCheckInButton() {

		String textToVerify = "Express Check-In";
		webAppDriver.clickElementById(btnRdpExpressCheckinId);
		webAppDriver.verifyPresenceOfTextInDivTagText(textToVerify);
	}
	
	
	//Reservation Deposit
	
	public void clickOnReservationDepositPaymentHoldNow(){
		webAppDriver.clickElementByXpath(btnPaymentHoldNowXpath);
	}
	
	public String getDepositAmount(){
		 String depositAmmount=webAppDriver.findElementByXpath(lbRDDepositAmountXpath).getText();
		depositAmmount=depositAmmount.replace("-$","");
		return depositAmmount;
	}
	
	public String getTotalAmount(){
		String totalAmmount=webAppDriver.findElementByXpath(lbTotalAmountXpath).getText();
	totalAmmount=totalAmmount.replace("$","");
		return totalAmmount;
	}
	
	
	public double getTotalCostAfterDeposit(String total,String deposit){
		
		double totalAmount=Double.parseDouble(total);
		double depositAmout=Double.parseDouble(deposit);
		double totalDueAmount=totalAmount-depositAmout;
		return totalDueAmount;
		
		
	}
	
	
	//R$P
	public void clickContinueR$P(){
		webAppDriver.clickElementByCss(btnContinueCss);
	}
	
	public void clickEditR$P(){
		webAppDriver.clickElementByCss(btnEditId);
	}
	

}
