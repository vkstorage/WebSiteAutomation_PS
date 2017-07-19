package com.phelps.ps.com.testsuite;

import java.text.MessageFormat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.actions.ReviewActions;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.SelfCareMyStorageUnitLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class ReviewsTestSuite extends BasicTestSuite implements SelfcareSummaryLocators, SelfCareMyStorageUnitLocators,
		HomeLocators,CommonSearchTextLocators {

	@BeforeClass
	public void doLoginTest() {
		
		//navigator.login("Docscully1", "test123");
		navigator.login(reviewsDesktopUser, "test123");
	}
/*
 * PS-175:TC-Reviews-01.1-Test availability of review this property button on my storage units section of self care
 *  PS-176:TC-Reviews-02.1-Test availability of Review this property button on yellow call-out present on the public storage my 
 *  PS-177:TC-Review-03.1-Test availability of review this property button on the home screen
 */
	@Test
	public void reviewpropertyTest() {
		navigator.gotoMyStorageUnits();
		webAppDriver.verifyPresenceOfTextInPTagText("My Storage Units");
		webAppDriver.verifyElementPresentByXpath(btnReviewThisPropertyXpath);
		selfCareMyStorageUnitsActions.ClickCalloutNext();
		webAppDriver.verifyElementPresentByXpath(btnReviewThisPropertyCalloutXpath);
		selfCareMyStorageUnitsActions.clickLogo();
		webAppDriver.verifyElementPresentByXpath(btnReviewThisPropertyHomeXpath);

	}

	/*
	 * PS-178:TC-Review-04.1-Test functionality of Review this property button
	 */
	@Test
	public void functionOfReviewPropertyTest(){
		navigator.gotoHomePage();
		selfCareMyStorageUnitsActions.clickLogo();
		webAppDriver.verifyElementPresentByXpath(btnReviewThisPropertyHomeXpath);
		String ReviewWindowURL = reviewActions.getReviewWindowURL();
		webAppDriver.get(ReviewWindowURL);
		reviewActions.checkReviewObjects();
		navigator.gotoMyStorageUnits();
		webAppDriver.verifyElementPresentByXpath(btnReviewThisPropertyXpath);
		webAppDriver.get(ReviewWindowURL);
		reviewActions.checkReviewObjects();
		navigator.gotoMyStorageUnits();
		selfCareMyStorageUnitsActions.ClickCalloutNext();
		webAppDriver.verifyElementPresentByXpath(btnReviewThisPropertyCalloutXpath);
		webAppDriver.get(ReviewWindowURL);
		reviewActions.checkReviewObjects();	
	}
	
	@Test
	public void reviewPropertyForMultiplePropertyUserTest(){
		/*String userName = "nhulsey@marketingwerks.com";
		String password = "test123";*/
		String userName = "AlfredCasazza";
		String password = "test123";
		navigator.gotoMyStorageUnits(userName, password);
		int expectedNum = reviewActions.getTotalNumberOfProperties(tableXpath);
		//webAppDriver.verifyTotalNumberOfElementPresentByXpath(btnReviewThisPropertyXpath, expectedNum);
		
		for (int i=2;i<=expectedNum+1;i++){
			String actualXpath=MessageFormat.format(btnCommonPropertyReviewXpath, i);
			System.out.println(actualXpath);
			webAppDriver.verifyElementPresentByXpath(actualXpath);
		}
		
		
	}
}
