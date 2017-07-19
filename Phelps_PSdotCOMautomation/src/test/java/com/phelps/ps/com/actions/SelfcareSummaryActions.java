package com.phelps.ps.com.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.ReservationCarePageLocators;

import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class SelfcareSummaryActions extends CommonActions implements SelfcareSummaryLocators, ReservationCarePageLocators, PaymentsLocators, CommonLocators {

	CateredWebDriver webAppDriver;

	public SelfcareSummaryActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	/**
	 * Click reservation from the menu
	 */
	//WC2 removed
	/*public void clickReservations() {
		webAppDriver.clickElementByLinkText(hlinkReservationsLink);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbReservationDetailsXpath, "Reservation Details");
	}

	public void clickReservationDetailsLink() {
		webAppDriver.clickElementByXpath(hlinkReservationDetailsXpath);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbReservationDetailsXpath, "Reservation Details");
	}*/

	public void clickContactInfoLink() {
		webAppDriver.clickElementByLinkText("Contact Info");
		webAppDriver.verifyPresenceOfTextInPTagText("Contact Information");
	}

	public void clickMyStorageUnits() {
		webAppDriver.clickElementByLinkText("My Storage Units");
		webAppDriver.verifyPresenceOfTextInSpanTag("My Public Storage Account");
	}

	public void clickPublicStorageOnlineAccountFeatures() {
		webAppDriver.clickElementById(hlinlPublicStoraheOnlineAccountFeaturesId);
	}

	public void clickFrequentlyAskedQuestions() {
		webAppDriver.clickElementById(hlnkFrequentlyAskedQuestionsId);
	}

	public void clickPayment() {
		webAppDriver.clickElementByXpath(hlnkPaymentXpath);
		webAppDriver.verifyPresenceOfTextInDivTagText("Payments");
	}

	public void clickPersonalInfoLink() {
		webAppDriver.clickElementByXpath(hlnkPersonalInfoXpath);

	}

	public void clickViewFullHistory() {
		webAppDriver.clickElementByXpath(hlnkViewFullHistoryXpath);
	}

	public void clickMakeThisPayment() {
		webAppDriver.clickElementByXpath(hlnkMakeThisPaymentXpath);
	}

	public void clickAddToMyCalender() {
		webAppDriver.clickElementByXpath(hlnkAddToMyCalenderXpath);
	}

	public void clickEditNextToPayment() {
		webAppDriver.clickElementByXpath(hlnkEditNextToPaymentXpath);
	}

	public void clickManagePaymentMethod() {
		webAppDriver.clickElementByXpath(hlnkManagePaymentMethodXpath);
	}

	public void clickLoginDetailsTab() {
		webAppDriver.clickElementByLinkText("Login Details");
	}

	public void clickEdit() {
		webAppDriver.clickElementByXpath(hlnkEmailEditXpath);
	}

	public void clickCancel() {
		webAppDriver.clickElementByXpath(hlnkCancelXpath);

	}

	/**
	 * This method clicks on Notification Subtabs
	 */
	public void clickOnNotificationSubTab() {
		webAppDriver.clickElementByLinkText("Notifications");
		webAppDriver.verifyElementPresentByXpath(btnSaveChanges1Xpath);
	}

	public void clickOnEmergencyContact() {
		webAppDriver.clickElementByLinkText("Emergency Contact & Authorized User");
		webAppDriver.verifyElementPresentByXpath(hlnkUnitXpath);
	}

	/**
	 * this method selects the radio button from Notification Subtab
	 * 
	 * @param name
	 * @param flag
	 */
	public void selectRadioButton(String name, boolean flag) {
		String xpath = "//input[@name='" + name + "' and @value='" + flag + "']";
		webAppDriver.clickElementByXpath(xpath);
	}

	/**
	 * If Radio button is checked then method will not throw any Exception If
	 * radio button is not checked then method will throw Expcetion
	 * 
	 * @param name
	 * @param flag
	 */
	public void verifyRadioButtonChecked(String name, boolean flag) {
		String xpath = "//input[@name='" + name + "' and @value='" + flag + "']";
		String attrValue = webAppDriver.findElementByXpath(xpath).getAttribute("value");
		if (attrValue == null) {
			throw new AssertionError();
		}
	}

	/**
	 * This method gets Address Info from all text fields.
	 * 
	 * @return
	 */
	public HashMap<String, String> getEmergencyContact() {
		HashMap<String, String> emergencyContact = new HashMap<String, String>();
		emergencyContact.put("fName", webAppDriver.findElementByName(tbFirstNameName).getAttribute("value"));
		emergencyContact.put("lName", webAppDriver.findElementByName(tbLastNameName).getAttribute("value"));
		emergencyContact.put("address", webAppDriver.findElementByName(tbAddressName).getAttribute("value"));
		emergencyContact.put("city", webAppDriver.findElementByName(tbCityName).getAttribute("value"));
		emergencyContact.put("state", webAppDriver.findElementByName(drpdwnStateName).getAttribute("value"));
		emergencyContact.put("zipCode", webAppDriver.findElementByName(tbZipCodeName).getAttribute("value"));
		emergencyContact.put("phone", webAppDriver.findElementByName(tbPhoneName).getAttribute("value"));
		System.err.println();
		return emergencyContact;
	}

	/**
	 * This method compares the entries in Web
	 * 
	 * @param emergencyContact
	 */
	public void verifyEmergencyContact(HashMap<String, String> emergencyContact) {
		String fNameLastName = webAppDriver.findElementByXpath(lbAddressInfo + "/div[1]").getText();
		String address = webAppDriver.findElementByXpath(lbAddressInfo + "/div[2]").getText();
		String cityStateZip = webAppDriver.findElementByXpath(lbAddressInfo + "/div[3]").getText();
		String contactNumber = webAppDriver.findElementByXpath(lbAddressInfo + "/div[4]").getText();

		String firstNameLastNameFromMap = emergencyContact.get("fName") + " " + emergencyContact.get("lName");
		String cityStateZipFromMap = emergencyContact.get("city") + ", " + emergencyContact.get("state") + " "
				+ emergencyContact.get("zipCode");
		String contactNumberFromMap = emergencyContact.get("phone").replaceAll("-", "");

		if (!(firstNameLastNameFromMap.equals(fNameLastName) && emergencyContact.get("address").equals(address)
				&& cityStateZipFromMap.equals(cityStateZip) && contactNumberFromMap.equals(contactNumber))) {
			throw new AssertionError();
		}
	}

	/**
	 * This methods modifies the emergency contact randomly
	 * 
	 * @return
	 */
	public HashMap<String, String> modifyEmergencyContact() {
		HashMap<String, String> emergencyContact = new HashMap<String, String>();
		String fName = genrateRandonString(10);
		String LName = genrateRandonString(10);
		String address = genrateRandonString(10);
		String city = genrateRandonString(10);
		String state = webAppDriver.findElementByName(drpdwnStateName).getAttribute("value");
		String zipCode = "" + System.nanoTime() / 1000000000;
		String contactNo = "8149890269";
		emergencyContact.put("fName", fName);
		emergencyContact.put("lName", LName);
		emergencyContact.put("address", address);
		emergencyContact.put("city", city);
		emergencyContact.put("zipCode", zipCode);
		emergencyContact.put("phone", contactNo);
		emergencyContact.put("state", state);

		webAppDriver.enterTextToElementByName(tbFirstNameName, fName);
		webAppDriver.enterTextToElementByName(tbLastNameName, LName);
		webAppDriver.enterTextToElementByName(tbAddressName, address);
		webAppDriver.enterTextToElementByName(tbCityName, city);
		webAppDriver.enterTextToElementByName(tbZipCodeName, zipCode);
		webAppDriver.enterTextToElementByName(tbPhoneName, contactNo);

		webAppDriver.clickElementByXpath(btnSaveXpath);
		webAppDriver.verifyElementPresentByXpath(hlnkUnitXpath);
		return emergencyContact;
	}

	/**
	 * This method generates the random number
	 * 
	 * @param lenghtOfString
	 * @return
	 */
	public String genrateRandonString(int lenghtOfString) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	/**
	 * This method return all Authorised access are present
	 * 
	 * @return
	 */
	public LinkedHashMap<String, String> getAuthorisedAccess() {
		LinkedHashMap<String, String> authorisedAccess = new LinkedHashMap<String, String>();
		for (int i = 0; i < 5; i++) {
			authorisedAccess.put(webAppDriver.getDriver().findElement(By.name(tbAuthorisedAccessFName + i)).getAttribute("value"),
					webAppDriver.getDriver().findElement(By.name(tbAuthorisedAccesLName + i)).getAttribute("value"));
		}
		return authorisedAccess;
	}

	/**
	 * This methods compairs the Authorised User entries
	 * 
	 * @param authorisedAccess
	 */
	public void verifyAuthorisedUsers(LinkedHashMap<String, String> authorisedAccess) {
		authorisedAccess = removeEmptyValue(authorisedAccess);
		LinkedHashMap<String, String> runtimeMap = new LinkedHashMap<String, String>();

		// check for No entries are present on clicking On cancel Button
		String authorisedText = "";
		boolean flag = false;
		try {
			authorisedText = webAppDriver.findElementByXpath(lbAuthorisedNameXpath).getText();
			if (!(authorisedAccess.size() == 1 && authorisedText.contains("No authorized users assigned."))) {
				throw new AssertionError();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = true;
		}

		if (flag) {
			for (int i = 0; i < 4; i++) {
				String name = webAppDriver.findElementByXpath(lbAuthorisedUser + "/div[" + i + "]").getText();
				runtimeMap.put(name.split(" ")[0], name.split(" ")[1]);
			}
			runtimeMap = removeEmptyValue(runtimeMap);
			Set<String> keySet = authorisedAccess.keySet();
			Set<String> keySetRuntime = runtimeMap.keySet();
			if (keySet.containsAll(keySetRuntime)) {
				Iterator<String> mapItr = keySet.iterator();
				while (mapItr.hasNext()) {
					String keyElement = mapItr.next();
					if (!(authorisedAccess.get(keyElement).equals(runtimeMap.get(keyElement)))) {
						throw new AssertionError();
					}
				}
			} else {
				throw new AssertionError();
			}
		}
	}

	public LinkedHashMap<String, String> removeEmptyValue(LinkedHashMap<String, String> tempMap) {
		for (Map.Entry<String, String> entr : tempMap.entrySet()) {
			String key = entr.getKey();
			if (tempMap.get(key).isEmpty()) {
				tempMap.remove(key);
				continue;
			}
		}
		return tempMap;
	}

	public LinkedHashMap<String, String> enterValuesForAuthorisedUser() {
		LinkedHashMap<String, String> authorisedUserMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < 2; i++) {
			String fName = genrateRandonString(10);
			String lName = genrateRandonString(10);
			webAppDriver.enterTextToElementByName(tbAuthorisedAccessFName + i, fName);
			webAppDriver.enterTextToElementByName(tbAuthorisedAccesLName + i, lName);
			authorisedUserMap.put(fName, lName);
		}
		webAppDriver.clickElementByXpath(btnSaveXpath);
		webAppDriver.verifyElementPresentByXpath(hlnkUnitXpath);
		return authorisedUserMap;
	}

	public void clickOnChangeCalender() {
		webAppDriver.clickElementByLinkText("Change");
		webAppDriver.verifyElementPresentByXpath(lbScheduleMoveOutXpath);
	}

	public void changeCalenderDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 2); // Adding 5 days
		String output = sdf.format(c.getTime());
		String moveInDate = output.split("/")[1];
		webAppDriver.clickElementByXpath("//a[text()='" + moveInDate + "']");
		webAppDriver.verifyElementPresentByXpath(btnDateSaveXpath);
		webAppDriver.clickElementByXpath(btnDateSaveXpath);
		webAppDriver.verifyElementPresentByXpath(btnContinueXpath);
		webAppDriver.clickElementByXpath(btnContinueXpath);
		webAppDriver.verifyElementPresentByXpath(lbConfirmDateXpath);
		String runtimeDate = webAppDriver.findElementByXpath(lbConfirmDateXpath).getText();
		/*String dateVerify = "Move-out scheduled for: " + output;
		if (!runtimeDate.equals(dateVerify)) {
			throw new AssertionError();
		}*/
	}

	public void verifyViewFullDetails() {
		webAppDriver.clickElementByLinkText("View full details");
		webAppDriver.verifyElementPresentByXpath(lbNexPaymentXpath);
		webAppDriver.verifyElementPresentByXpath(lbMontlySummeryXpath);
		webAppDriver.verifyElementPresentByXpath(lnkReviewXpath);
	}

	public void verifyPaymentDetails() {
		webAppDriver.clickElementByXpath(btnMakePaymentXpath);
		webAppDriver.verifyElementPresentByXpath(lbPageVerificationMyPublicStorageAccountXpath);
		webAppDriver.verifyElementPresentByXpath(lbUnitDetailsXpath);
		webAppDriver.verifyElementPresentByXpath(lbDueNowXpath);
		webAppDriver.verifyElementPresentByXpath(lbNextPaymentXpath);
		webAppDriver.verifyElementPresentByXpath(SelfcareSummaryLocators.lbPaymentAmountXpath);
	}
	public void verifyMoveOutScheduledDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 2); // Adding 5 days
		String output = sdf.format(c.getTime());
		String moveInDate = output.split("/")[1];
		webAppDriver.clickElementByXpath("//a[text()='" + moveInDate + "']");
		webAppDriver.verifyElementPresentByXpath(btnDateSaveXpath);
		webAppDriver.clickElementByXpath(btnDateSaveXpath);
		webAppDriver.verifyElementPresentByXpath(btnContinueXpath);
		webAppDriver.clickElementByXpath(btnContinueXpath);
		webAppDriver.verifyElementPresentByXpath(lbConfirmDateXpath);
		String runtimeDate = webAppDriver.findElementByXpath(lbConfirmDateXpath).getText();
		String dateVerify = "Move-out scheduled for: " + output;
		if (!runtimeDate.equals(dateVerify)) {
			throw new AssertionError();
		}
	}	
		public void selectMoveOutDate(){
			SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 2); // Adding 5 days
			String output = sdf.format(c.getTime());
			String moveInDate = output.split("/")[1];
			webAppDriver.clickElementByXpath("//a[text()='" + moveInDate + "']");
			webAppDriver.verifyElementPresentByXpath(btnDateSaveXpath);
			webAppDriver.clickElementByXpath(btnDateSaveXpath);
			webAppDriver.verifyElementPresentByXpath(btnContinueXpath);
			webAppDriver.clickElementByXpath(btnContinueXpath);
			//webAppDriver.verifyElementPresentByXpath(lbConfirmDateXpath);
			String runtimeDate = "Move out date successfully scheduled.";
			//webAppDriver.verifyPresenceOfTextLocatedByXpath(lbConfirmDateXpath, runtimeDate);
		
		}
		
		public void selectMoveOutDateAndClickCancleButton(){
			SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 2); // Adding 5 days
			String output = sdf.format(c.getTime());
			String moveInDate = output.split("/")[1];
			webAppDriver.clickElementByXpath("//a[text()='" + moveInDate + "']");
			webAppDriver.clickElementByXpath(btnDateCancelXpath);
		}
		public void verifyPhotoSrcWithAddress(String imgSrc, String address) {

			if (!imgSrc.equals("nolocation_photo_93.gif")) {
				String expectedImgSrc = convertAddressIntoImagePrefix(address) + "-exterior_1_map.jpg";
				if (!imgSrc.equals(expectedImgSrc)) {
					Reporter.log("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
					webAppDriver.captureScreenshot();
					throw new AssertionError("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
			}
		 }
		}

			public void verifyPhotoSrcWithAddressForReservation(String imgSrc, String address) {

				if (!imgSrc.equals("nolocation_photo_340.gif")) {
					String expectedImgSrc = convertAddressIntoImagePrefix(address) + "-exterior_1_slideshow_full.jpg";
					if (!imgSrc.equals(expectedImgSrc)) {
						Reporter.log("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
						webAppDriver.captureScreenshot();
						throw new AssertionError("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);

					}
				}
		}
			
			public void verifyPhotoSrcWithAddressForThumb(String imgSrc, String address,String Suffix) {

				if (!imgSrc.equals("nolocation_photo_340.gif")) {
					String expectedImgSrc = convertAddressIntoImagePrefix(address) + Suffix;
					if (!imgSrc.equals(expectedImgSrc)) {
						Reporter.log("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
						webAppDriver.captureScreenshot();
						throw new AssertionError("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);

					}
				}
		}
			public void verifyPhotoSrcWithAddressForSelfCareMyStorageUnit(String imgSrc, String address) {

				if (!imgSrc.equals("nolocation_photo_93.gif")) {
					String expectedImgSrc = convertAddressIntoImagePrefix(address) + "-exterior_1_map.jpg";
					if (!imgSrc.equals(expectedImgSrc)) {
						Reporter.log("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
						webAppDriver.captureScreenshot();
						throw new AssertionError("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);

					}
				}
		}	
			
			
}
