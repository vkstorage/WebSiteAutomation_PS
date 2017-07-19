package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.autotest.web.CateredWebElement;

public class NovDecReleaseBugsTestSuite extends BasicTestSuite {

	Calendar c;
	String moveInDate;
	int tomorrowDate;
	String dayOfMoveInDate;
	List<CateredWebElement> allCoupons = new ArrayList<CateredWebElement>();
	String newURL;
	String firstName = "Mary";
	String phoneNumber = "705-869-3784";
	String ext = "111";
	String password = "test123";

	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 5);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		navigator.gotoDefaultSearchResults(searchByZip);

	}

	// PS-403:Desktop : Bug 2745 - [NEW] User with username containing
	// ampersand(&) is unable to login
	// as per the new funtionality the any special characters <
	/*
	 * > !
	 * 
	 * @ # $ % ^ &
	 * 
	 * , should not be alowed in first or last name
	 */
	@Test
	public void verifySpecialCharactersNotallowedInUsernameTest() {
		generateMoveInDate();
		String[] specialCharacters = { "<", ">", "!", "@", "#", "$", "%", "^", "&", "*", "," };
		String lastName = new UniqueId(new UniqueId().id).charId;
		String email = envTestData.get("email");
		webAppDriver.get(baseUrl);
		navigator.gotoDefaultSearchResults("90707");
		searchResultsPageActions.clickContinue();
		for (int i = 0; i < specialCharacters.length; i++) {
			firstName = "Mary";
			firstName = firstName + specialCharacters[i];
			lastName = new UniqueId(new UniqueId().id).charId;
			lastName = lastName + specialCharacters[i];
			reservationDetailsActions.enterAllReservationDetails(firstName, lastName, phoneNumber, ext, email, email, 
					dayOfMoveInDate);
			reservationDetailsActions.clickHoldNowOrComplete();
			webAppDriver
					.verifyElementPresentByXpath("//span[@class='rdp_val_msg FirstNameErrorMessage'][text()='No special characters allowed']");
			webAppDriver
					.verifyElementPresentByXpath("//span[@class='rdp_val_msg LastNameErrorMessage'][text()='No special characters allowed']");
			webAppDriver.verifyElementPresentByXpath(".//*[@id='lets_get_started']");
			webAppDriver.verifyElementNotPresntByXpath(".//*[@id='logout']");

		}
	}

	// PS-402:Bug 2741 - Destination in Directions tab is incorrect
	//@Test
	public void verifyDestinationAddressTest() {

		webAppDriver
				.get("https://psstaging.phelpsagency.com/california/self-storage-los-angeles-ca/90064-self-storage/121?lat=34.03572&lng=-118.44154&location=90066&ms=1,2#/?zl=16&vd=0.39312466534245405&lat=34.037467469632055&lng=-118.44115418280032&sort=dasc&ssort=dasc&vsort=dasc&v20=0&v35=0&v50=0&vc=0&vu=0&ve=0&cc=0&da=0&ms=1,2,3,4,5,6,7,8");

	}

	// PS-425:Bug 2763 - Decommission the Spanish site - part 1
	// PS-424:Bug 2764 - Decommission the Spanish site - part 2
	@Test
	public void verifySpanishSiteNotActiveTest() {
		List<String> spanishURLs = new ArrayList<String>();

		spanishURLs.add(baseUrl + "/es/");
		spanishURLs.add(baseUrl + "/es/buscar-storage-inicio.aspx?location=90401");
		spanishURLs.add(baseUrl + "/es/detalles-reservacion.aspx?st=120&sz=18974&key=376512&location=90401");
		spanishURLs
				.add(baseUrl
						+ "/es/buscar-storage-inicio.aspx?location=90401#srp;ll=33.99732,-118.47504000000004;st=storage;vd=1;zl=16;iz=11;ild=0.2197265625;idi=;idn=;mp=-1;f=;pi=26811;ms=;si");
		spanishURLs.add(baseUrl + "/es/detalles-reservacion.aspx?st=120&sz=18975&key=358456&location=90401");

		for (String spanishSingleURL : spanishURLs) {
			webAppDriver.get(spanishSingleURL);
			webAppDriver.verifyDivTagTextContains("Due to site improvements this page is no longer available.");
			webAppDriver.verifyElementPresentByXpath("//img[@src='/images/site_maintenance_3.jpg']");
			webAppDriver.verifyElementPresentByXpath("//input[@class='search-input']");
		}

	}

	// PS-428:Bug 2755 - ZLP is not showing the range of sizes on the coupon
	@Test
	public void verifyZLPSizeRangeTest() {
		webAppDriver.get(baseUrl + "/oregon/self-storage-beaverton-or/97005-self-storage/");
		int couponCount = webAppDriver.getTotalNumberOfElementsByXpath("//div[@class='result property']");
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(".//*[@id='results']//div[@class='col col2']//span[@class='size-to']",
				couponCount);

	}

	// PS-408:Bug 2762 - Update Phone number order on all PLP pages
	//@Test
	public void verifyPhoneNumberOrderPLPTest() {
		webAppDriver.get(baseUrl);
		navigator.gotoDefaultSearchResults("90404");
		searchResultsPageActions.clickPropertyImage();
		webAppDriver.verifyElementPresentByXpath("//div[text()='PHONE']/../div[6][@id='localNumber']");
		webAppDriver.verifyElementPresentByXpath("//div[text()='PHONE']/../div[7][@id='800number']");

	}

	// PS-407:Bug 2761 - Update Blog on Homepage
	@Test
	public void verifyBlogHomepageTest() {
		String expectedgetOrganizedURL = "http://www.getorganizedalready.com/";
		String expectedVisitblogURL = "https://blog.publicstorage.com/";
		String expectedImageBlogTargetURL = "http://blog.publicstorage.com/post/2015/11/16/three-steps-to-organize-your-kitchen-like-a-pro";
		String expectedReadMoreTargetURL = "http://blog.publicstorage.com/post/2015/11/16/three-steps-to-organize-your-kitchen-like-a-pro";
		webAppDriver.get(baseUrl);
		webAppDriver.verifyDivTagTextContains("In Your Space");
		webAppDriver.verifyDivTagTextContains("Ideas, Inspiration and Organization from the Storage Experts");
		webAppDriver.verifyDivTagTextContains("Three Steps to Organize Your Kitchen Like a Pro");
		webAppDriver
				.verifyPTagTextContains("Stop. Breathe. We have a plan for you! We put together some kitchen organization ideas, with expert tips from professional organizer Nonnahs Driskill of");
		//webAppDriver
		//		.verifyPTagTextContains(", to help you get your kitchen organized so it’s neat and efficient, and stays that way.");
		String actualOrganizedURL = webAppDriver.findElementByLinkText("Get Organized Already").getAttribute("href");
		if (!(actualOrganizedURL.equalsIgnoreCase(expectedgetOrganizedURL))) {
			Reporter.log("get organized url expected " + expectedgetOrganizedURL + " but found " + actualOrganizedURL);
			webAppDriver.captureScreenshot();
			throw new AssertionError("get organized url expected " + expectedgetOrganizedURL + " but found " + actualOrganizedURL);
		}

		String actualVisitBlogURL = webAppDriver.findElementByLinkText("Visit our Storage Blog").getAttribute("href");
		if (!(actualVisitBlogURL.equalsIgnoreCase(expectedVisitblogURL))) {
			Reporter.log("Visit our blog  url expected " + expectedVisitblogURL + " but found " + actualVisitBlogURL);
			webAppDriver.captureScreenshot();
			throw new AssertionError("Visit our blog  url expected " + expectedVisitblogURL + " but found " + actualVisitBlogURL);
		}
		String actualReadMoreURL = webAppDriver.findElementByLinkText("Read More").getAttribute("href");
		if (!(actualReadMoreURL.equalsIgnoreCase(expectedReadMoreTargetURL))) {
			Reporter.log("Read More  url expected " + expectedReadMoreTargetURL + " but found " + actualReadMoreURL);
			webAppDriver.captureScreenshot();
			throw new AssertionError("Read More  url expected " + expectedReadMoreTargetURL + " but found " + actualReadMoreURL);
		}
		String actualImageBlogURL= webAppDriver.findElementByXpath("//img[@src='images/hp/blog/organize-kitchen.jpg']/..").getAttribute("href");
		 
		if (!(actualImageBlogURL.equalsIgnoreCase(expectedImageBlogTargetURL))) {
			Reporter.log("Blog Image  url expected " + expectedImageBlogTargetURL + " but found " + actualImageBlogURL);
			webAppDriver.captureScreenshot();
			throw new AssertionError("Blog Image  url expected " + expectedImageBlogTargetURL + " but found " + actualImageBlogURL);
		}

	}

}
