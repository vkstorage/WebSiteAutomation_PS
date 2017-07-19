package com.phelps.ps.com.testsuite;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.PLPActions;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.Phase5Locators;


public class AugReleaseBugsTestSuite extends BasicTestSuite implements HomeLocators, PaymentsLocators, PLPLocators, Phase5Locators {

	/*
	 * Issue 2564
	 */

	@Test
	public void testMentionOfAccess365Test() {

		String url1 = baseUrl + "/business-storage.aspx?optimizely_disable=true";
		String url2 = baseUrl + "/self-storage.aspx?optimizely_disable=true";
		String url3 = baseUrl + "/index.aspx?optimizely_disable=true";
		String url4 = baseUrl + "/vehicle-car-storage.aspx?optimizely_disable=true";
		webAppDriver.get(url1);
		webAppDriver.verifyDivTagTextContains("convenient access");
		webAppDriver.get(url2);
		webAppDriver.verifyDivTagTextContains("convenient access");
		webAppDriver.get(url4);
		webAppDriver.verifyDivTagTextContains("convenient access");
		webAppDriver.get(url3);
		webAppDriver.verifyElementTextContainsByXpath("lbAccess365MentionXpath", "Convenient");
		webAppDriver.verifyElementTextContainsByXpath("lbAccess365MentionXpath", "Access Hours");
	}

	/*
	 * Issue 2562
	 * 
	 * @Test public void verifyAddCheckboxAckOkTest(){
	 * 
	 * String username = "Docscully1"; String password = "test123"; String url1=
	 * baseUrl + "/selfcare/makepay.aspx"; String url2 = baseUrl +
	 * "/selfcare/makepaypssi.aspx"; navigator.gotoMyAccountPage(username,
	 * password); webAppDriver.get(url1);
	 * webAppDriver.clickElementByXpath(".//*[@id='unsavedpaymethods']");
	 * webAppDriver
	 * .verifyVisibilityOfElementLocatedByXpath(".//*[@id='termsagree']");
	 * webAppDriver.get(url2);
	 * webAppDriver.clickElementByXpath(".//*[@id='unsavedpaymethods']");
	 * webAppDriver
	 * .verifyVisibilityOfElementLocatedByXpath(".//*[@id='termsagree']"); }
	 */
	/*
	 * Issue 2559 : FAQ tab returning bad data when property has limited sale
	 * coupon.
	 */
	@Test
	public void verifyFAQDataForLimitedSaleCouponTest() {
		List<CateredWebElement> faqAnswers;

		String url1 = baseUrl + "/texas/self-storage-league-city-tx";
		String url2 = baseUrl + "/florida/self-storage-coconut-creek-fl";
		String url3 = baseUrl + "/arizona/self-storage-gilbert-az/";
		String url4 = baseUrl + "/california/self-storage-sunnyvale-ca/";
		String url5 = baseUrl + "/oregon/self-storage-portland-or/";
		String url6 = baseUrl + "/virginia/self-storage-norfolk-va/";
		String url7 = baseUrl + "/texas/self-storage-georgetown-tx";
		String url8 = baseUrl + "/texas/self-storage-richmond-tx/";
		String url9 = baseUrl + "/maryland/self-storage-owings+mills-md/";
		String url10 = baseUrl + "/louisiana/self-storage-bossier+city-la/";
		String url11 = baseUrl + "/south+carolina/self-storage-barnwell-sc/";
		String url12 = baseUrl + "/virginia/self-storage-fredericksburg-va/";
		String url13 = baseUrl + "/california/self-storage-fullerton-ca/";
		String url14 = baseUrl + "/new+york/self-storage-rochester-ny/";
		String url15 = baseUrl + "/washington/self-storage-puyallup-wa/";
		String url16 = baseUrl + "/kansas/self-storage-topeka-ks/";
		String url17 = baseUrl + "/ohio/self-storage-columbus-oh/";
		String url18 = baseUrl + "/utah/self-storage-salt+lake+city-ut/";
		String url19 = baseUrl + "/maryland/self-storage-baltimore-md/";
		String url20 = baseUrl + "/colorado/self-storage-westminster-co/";
		webAppDriver.get(url1);
		plpActions.clickFAQTab();
		// webAppDriver.clickElementByXpath(".//*[@id='city-tab-faqs']");
		// Added by Naaz
		// faqAnswers =
		List<String> answers = webAppDriver.getTextFromAllElemetnsByXPath(lbCLPFAQAnswersXpath);
		for (String eachAnswer : answers) {

			
			String parts[] = eachAnswer.split(" ");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].startsWith("$")) {
					
					String price = parts[i].replace("$", "");
					//System.out.println("the price array is " + price + " ");
					try {
						Float rate = Float.parseFloat("00.00");
						if (!(rate > 0.0)) {
							Reporter.log("The rate  " + rate + " is not correct");
							webAppDriver.captureScreenshot();
							throw new AssertionError();
						}
					} catch (NumberFormatException exe) {
						Reporter.log("The price is not valid  ");
						webAppDriver.captureScreenshot();
					}

				} else if (parts[i].contains("'x")) {
					String size = parts[i].replace("'", "");
					String unit[] = size.split("x");
					try {

						//System.out.println("the unit array is " + unit[0] + " " + unit[1]);
						Float.parseFloat(unit[0]);
						Float.parseFloat(unit[1]);
						if (unit.length == 3)
							Float.parseFloat(unit[2]);
					} catch (NumberFormatException ex) {
						Reporter.log("The unit size is not valid");
						webAppDriver.captureScreenshot();
						throw ex;
					}

				}
			}
		}

		/*
		 * String s1 =
		 * "The cheapest storage unit in League City, TX is $18.00 per month.";
		 * for(int i=0;;i++) { String parts[]=s1.split(" ");
		 * //System.out.println(parts[0]);
		 * 
		 * if(parts[i].startsWith("$")){ String x=parts[i]; System.out.println(x); }
		 * }
		 */

	}

	/**
	 * Bug 2588
	 */
	@Test
	public void verifyAutopayTOSisWorkingTest() {
		String username = "Docscully1";
		String password = "test123";
		String url1 = baseUrl + "/selfcare/makepay.aspx";
		String url2 = baseUrl + "/selfcare/makepaypssi.aspx";
		navigator.gotoMyAccountPage(username, password);
		webAppDriver.get(url1);
		paymentActions.clickTermsAndConditionsLink();
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbHeadingTOSXpath,
				"Terms and Conditions for Use of the Public Storage Online Bill Paying Service");
		webAppDriver.get(url2);
		paymentActions.clickTermsAndConditionsLink();
		webAppDriver.verifyPresenceOfTextLocatedByXpath(lbHeadingTOSXpath,
				"Terms and Conditions for Use of the Public Storage Online Bill Paying Service");
	}

}
