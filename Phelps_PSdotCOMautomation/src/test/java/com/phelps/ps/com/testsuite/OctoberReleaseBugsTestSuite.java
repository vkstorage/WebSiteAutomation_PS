package com.phelps.ps.com.testsuite;

import java.util.List;

import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.nevigation.Navigator;

public class OctoberReleaseBugsTestSuite extends BasicTestSuite implements PLPLocators {

	
	//For bug 2727
	@Test
	public void testReviewStarsPLPTest()
	{
		webAppDriver.get("https://psstaging.phelpsagency.com/california/self-storage-west-hollywood-ca/90038-self-storage/2?lat=34.09043&lng=-118.33998&location=90036");
		webAppDriver.verifyElementPresentByXpath(propertyReviewStarXpath);
	webAppDriver.clickElementByXpath("//*[@id='plp-tab-reviews']");
	webAppDriver.findAllElementsByXpath(lblPLPReviewTabReviewStartXpath);
	
	}
}