package com.phelps.ps.com.testsuite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.CommonSearchTextLocators;

public class April2017ReleaseBugsTestSuite extends BasicTestSuite implements CommonSearchTextLocators{
	
	//Bug 3535 - [NEW] No features are seen in the Features section on RDP

	//@Test
	public void verifyRDPLeftPanelFeatureTest(){
		List<CateredWebElement> elements=new ArrayList<CateredWebElement>();
		String[] features=new String[2];
		int prop=0;
		int plpUnit=1;
		navigator.gotoDefaultSearchResults(searchText53);
		commonActions.getAllSearchResultShowAllUnits().get(prop).click();
		elements=webAppDriver.findAllElementsByXpath("(//ul[@class='srp_list'])["+plpUnit+"]/li");
		for(int i=0;i<elements.size();i++){
			features[i]=elements.get(i).getText();
			System.out.println(features[i]);
		}
		commonActions.getAllPLPUnits().get(plpUnit-1).click();
		for(int j=0;j<features.length;j++){
			webAppDriver.verifyElementPresentByXpath("//div[@class='rdp_unit_feat']//div[contains(text(),'"+features[j]+"')]");
		}
	}
	
	//Bug 3552 - Desktop self-care "No email on file" functionality not working
	//@Test
	public void verifyNoEmailTest(){
		
		String[] noEmailText={"There is currently no email address on file.","Please specify which email notifications you would like to receive:"};
		
		navigator.login("srousso", "test123");
		selfcareSummaryActions.clickContactInfoLink();
		selfcareSummaryActions.clickOnNotificationSubTab();
		webAppDriver.verifyPTagTextContains(noEmailText[0]);
		webAppDriver.verifyPTagTextContains(noEmailText[1]);
	}
	
	//Bug 3569 - [NEW] Update blog on homepages 
	@Test
	public void verifyNewBlogTest(){
		
		navigator.redirectToHomePageNotLoggedIn();
		homePageRefreshActions.clickBlogRecentNewsImage();

		navigator.redirectToHomePageNotLoggedIn();
		homePageRefreshActions.clickBlogSecondlImage();
		navigator.redirectToHomePageNotLoggedIn();
		homePageRefreshActions.clickBlogThirdImage();
		navigator.redirectToHomePageNotLoggedIn();
		
		String parentHandle = webAppDriver.getWindowHandle();
		homePageRefreshActions.clickBlogLink();
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.verifyElementPresentByXpath("//a[contains(.,'Storage Near Manhattan at New Public Storage')]");
		webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Jersey City')]");
		webAppDriver.verifyElementPresentByXpath("//a[contains(.,'Expert Advice on How to Store a Mattress for')]");
		webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Restful Nights')]");
		webAppDriver.verifyElementPresentByXpath("//a[contains(.,'How to Organize for a Move –')]");
		webAppDriver.verifyElementPresentByXpath("//span[contains(.,'The Best Moving')]");
		webAppDriver.verifyElementPresentByXpath("//span[contains(.,'Checklist Ever')]");
		homePageRefreshActions.clickBlogReadMoreLinks(1);
		webAppDriver.verifyElementPresentByXpath(
				".//*[@id='breadcrumbs']//strong[text()='Storage Near Manhattan at New Public Storage Jersey City']");
		navigator.redirectToHomePageNotLoggedIn();
		homePageRefreshActions.clickBlogReadMoreLinks(2);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyElementPresentByXpath(
					"//*[@id='breadcrumbs']//strong[text()='Expert Advice on How to Store a Mattress for Restful Nights']");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);

		}
		homePageRefreshActions.clickBlogReadMoreLinks(3);
		windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			webAppDriver.verifyElementPresentByXpath(
					"//*[@id='breadcrumbs']//strong[text()='How to Organize for a Move – The Best Moving Checklist Ever']");
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);
		}
		navigator.redirectToHomePageNotLoggedIn();
		
	}
}
