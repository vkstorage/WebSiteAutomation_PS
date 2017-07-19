package com.phelps.ps.com.testsuite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;

public class HomeTestSuiteNotLoggedIn extends BasicTestSuite implements HomeLocators, CommonLocators,CommonSearchTextLocators {

	Map<String, Boolean> isTextPresent;
	Iterator<Map.Entry<String, Boolean>> iterator;
	static boolean allTextPresent;
	StringBuilder failedText;
	String email;
	
	@BeforeMethod
	public void clearHistory(){
		commonActions.clearChache();
	}
	/*
	@Test
	public void verifyHomePageHeaderTest() {
		
		// Scenario 1
		// Public Storage icon
	//	webAppDriver.verifyAttributeValueByXpath(imgPSLogoXpath, "src", "/images/nav_logo.png");
		// Menu items: Storage, Moving Suppliers, Company Info
		navigator.redirectToHomePageNotLoggedIn();
		homeActions.hoverStorage();
		webAppDriver.verifyPresenceOfTextInSpanTag("Self Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Business Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Vehicle Storage");
		webAppDriver.verifyPresenceOfTextInSpanTag("Size Guide");
		webAppDriver.verifyPresenceOfTextInSpanTag("Storage Blog");
		// Clicking on each Storage menu options
		homeActions.clickOnHowSelfStorageWorksMenu();
		homeActions.hoverStorage();
		homeActions.clickOnSelfStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnBusinessStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnVehicleStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnStorageBlogMenu();
		webAppDriver.navigate().back();
		homeActions.hoverMovingSupplies();
		webAppDriver.verifyPresenceOfTextInSpanTag("Packing Supplies");
		webAppDriver.verifyPresenceOfTextInSpanTag("Tape");
		// Clicking on each Moving Supplies menu options
		homeActions.clickOnStorageBoxesMenu();
		navigator.gotoHomePageFromMovingSupplies();
		//homeActions.hoverMovingSupplies();
		//homeActions.clickOnStorageLocksMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnPackingSuppliesMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnTapeMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverCompanyInfo();
		webAppDriver.verifyPresenceOfTextInSpanTag("Investor Relations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Global Locations");
		webAppDriver.verifyPresenceOfTextInSpanTag("Careers");
		webAppDriver.verifyPresenceOfTextInSpanTag("Contact Us");
		// Clicking on each Company Info menu options
		homeActions.clickOnAboutUsMenu();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnGlobalLocationMenu();
		webAppDriver.navigate().back();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnContactUsMenu();
		// Need help text and phone number
		webAppDriver.verifyDivTagTextEquals("NEED HELP?");
		webAppDriver.navigate().back();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnInvestorRelationMenu();
	}

	
	//Author -mnaaz
	//Added on 6th -Oct
	@Test
	public void verifyContactUsFunctionalityTest(){
		email=envTestData.get("email");
		String name="Test User";
		String topic="Reservations";
		String inquiry="Reservations Inquiry";
		String phone="7656765465";
		String ext="674";
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.get(baseUrl+"/storage-contact.aspx");
		homeActions.clickContactUsSubmitbutton();
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Name");
		webAppDriver.verifyDivTagTextEquals("Please select a Topic");
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Email Address");
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Phone Number");
		webAppDriver.verifyDivTagTextEquals("Please enter a valid Nature of Inquiry");
		
		//successful submition
		homeActions.enterContactusName(name);
		homeActions.enterContactUsEmail(email);
		homeActions.enterContactusInquiry(inquiry);
		homeActions.selectContactUsTopic(topic);
		homeActions.enterContactUsPhone(phone);
		homeActions.enterContactUsExtension(ext);
		homeActions.clickContactUsSubmitbutton();
		
		webAppDriver.verifyDivTagTextEquals("Your request has been registered from the email "+email+".");
		
	}
	@Test
	public void verifyMyAccountPayMyBillLink() {
		// Scenario 2
		navigator.redirectToHomePageNotLoggedIn();
		// scenario 3
		webAppDriver.verifyElementPresentByLinkText("Pay My Bill");
		navigator.redirectToHomePageNotLoggedIn();
	}

	@Test
	public void verifySearchTest() {
		// Scenario 4
		navigator.gotoDefaultSearchResults(searchText33);
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoDefaultSearchResults(searchText34);
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoDefaultSearchResults(searchText35);
		navigator.redirectToHomePageNotLoggedIn();

	}

	@Test
	public void verifyStorageMadeImagesLinksTest() {
		// Scenario 5,6,7
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoStorageMadeEasyContent();
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoStorageMadeReliableContent();
		navigator.redirectToHomePageNotLoggedIn();
		navigator.gotoStorageMadeLocalContent();
		navigator.redirectToHomePageNotLoggedIn();
	}
*/
	@Test
	public void verifyStorageMadeLinksTest() {
		// Scenario 8,9,10
		isTextPresent = new HashMap<String, Boolean>();
		iterator = isTextPresent.entrySet().iterator();
		failedText = new StringBuilder();
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.clickElementByLinkText("Learn how to store with us");
		isTextPresent.put("Learn how to store with us - How Self Storage Works",
				webAppDriver.verifyTextContainInAnyTag("How Self Storage Works"));
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.clickElementByLinkText("Learn more about us");
		isTextPresent.put("Learn more about us-How Self Storage Works",
				webAppDriver.verifyTextContainInAnyTag("How Self Storage Works"));
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.clickElementByLinkText("Access our Location Finder");
		isTextPresent.put("Access our Location Finder-Find Public Storage Locations",
				webAppDriver.verifyTextContainInAnyTag("Find Public Storage Locations"));

		while (iterator.hasNext()) {
			if (!iterator.next().getValue()) {
				allTextPresent = false;
				failedText.append(iterator.next().getKey());
				failedText.append(System.lineSeparator());
			}
		}

		if (!allTextPresent) {
			webAppDriver.captureScreenshot();
			Reporter.log(failedText.toString() + " not displayed on the Home page");
			throw new AssertionError(failedText.toString() + " not displayed on the on the Home page");
		}
		
		// * webAppDriver.verifyLinkContent("Learn how to store with us",
		// * "How Self Storage Works", true);
		// * webAppDriver.verifyLinkContent("Learn more about us",
		// * "How Self Storage Works", true);
		// * webAppDriver.verifyLinkContent("Access our Location Finder",
		// * "Find Public Storage Locations", true);
		 
	}
/*
	@Test
	public void verifyBenefitSection() {
		// Scenario 11
		navigator.gotoBenefitsContent();
		navigator.redirectToHomePageNotLoggedIn();
	}

	@Test
	public void verifyBlogImageLink() {
		// Scenario 12
		webAppDriver.verifyLinkContent(imgBlogImageXpath, "Experts Uncork Tips for Storing Red Wine", true);
	}

	@Test
	public void verifyInYourSpaceLinksTest() {
		// Scenario 13,14
		navigator.redirectToHomePageNotLoggedIn();
		webAppDriver.verifyLinkContent("Read More", "TExperts Uncork Tips for Storing Red Wine", true);
		webAppDriver.verifyLinkContent("Storage Blog", "The Organized Life", true);

	}
	
	@Test(dataProvider = "homepagelinkproviderNewWindow", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class, priority = -1)
	public void verifyHomePageNewWindowLinksTest(String linkData) {
		// Scenario 13,14
		navigator.redirectToHomePageNotLoggedIn();
		String parentHandle = webAppDriver.getWindowHandle();
		String linkName = linkData.split(",")[0];
		String expectedText = linkData.split(",")[1];
		webAppDriver.clickElementByLinkText(linkName);
		Set<String> windowHandles = webAppDriver.getWindowHandles();
		windowHandles.remove(parentHandle);
		for (String winHandle : windowHandles) {
			webAppDriver.switchTo().window(winHandle);
			if (!webAppDriver.verifyTextContainInAnyTag(expectedText)) {
				Reporter.log(expectedText);
				webAppDriver.captureScreenshot();
				webAppDriver.close();
				webAppDriver.switchTo().window(parentHandle);
				throw new AssertionError(expectedText);
			}
			webAppDriver.close();
			webAppDriver.switchTo().window(parentHandle);
			
		}

	}
	

	@Test
	public void verifyHomePageHeaderTest() {
		
		// Scenario 1
		// Public Storage icon
	//	webAppDriver.verifyAttributeValueByXpath(imgPSLogoXpath, "src", "/images/nav_logo.png");
		// Menu items: Storage, Moving Suppliers, Company Info
		navigator.redirectToHomePageNotLoggedIn();
		homeActions.hoverStorage();
		webAppDriver.verifyPresenceOfTextInATag("How Storage Works");
		webAppDriver.verifyPresenceOfTextInATag("Self Storage");
		webAppDriver.verifyPresenceOfTextInATag("Business Storage");
		webAppDriver.verifyPresenceOfTextInATag("Vehicle Storage");
		webAppDriver.verifyPresenceOfTextInATag("Size Guide");
		webAppDriver.verifyPresenceOfTextInATag("Storage Blog");
		
		// Clicking on each Storage menu options
		homeActions.clickOnHowSelfStorageWorksMenu();
		homeActions.hoverStorage();
		homeActions.clickOnSelfStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnBusinessStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnVehicleStorageMenu();
		homeActions.hoverStorage();
		homeActions.clickOnStorageBlogMenu();
		webAppDriver.navigate().back();
		homeActions.hoverMovingSupplies();
		webAppDriver.verifyPresenceOfTextInATag("Packing Supplies");
		webAppDriver.verifyPresenceOfTextInATag("Tape");
		// Clicking on each Moving Supplies menu options
		homeActions.clickOnStorageBoxesMenu();
		navigator.gotoHomePageFromMovingSupplies();
		//homeActions.hoverMovingSupplies();
		//homeActions.clickOnStorageLocksMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnPackingSuppliesMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverMovingSupplies();
		homeActions.clickOnTapeMenu();
		navigator.gotoHomePageFromMovingSupplies();
		homeActions.hoverCompanyInfo();
		webAppDriver.verifyPresenceOfTextInATag("Investor Relations");
		webAppDriver.verifyPresenceOfTextInATag("Global Locations");
		webAppDriver.verifyPresenceOfTextInATag("Careers");
		webAppDriver.verifyPresenceOfTextInATag("Contact Us");
		// Clicking on each Company Info menu options
		homeActions.clickOnAboutUsMenu();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnGlobalLocationMenu();
		webAppDriver.navigate().back();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnContactUsMenu();
		// Need help text and phone number
		webAppDriver.verifyDivTagTextEquals("Reserve Storage");
		webAppDriver.navigate().back();
		homeActions.hoverCompanyInfo();
		homeActions.clickOnInvestorRelationMenu();
	}
	
	
	
@Test(dataProvider = "homepagelinkprovider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class, priority = -1)
	public void verifyHomePageLinksTest(String linkData) {
		// Scenario 15
		navigator.redirectToHomePageNotLoggedIn();
		String linkName = linkData.split(",")[0];
		String expectedText="";
		if(linkData.contains("\""))
		{
			
			expectedText = linkData.split("\"")[1];
		}
		else
		{
		 expectedText = linkData.split(",")[1];
		}
		if(linkName.equalsIgnoreCase("Company Info"))
		{
			webAppDriver.clickElementByXpath("//a[contains(text(),'Company Info')]");
		}
		webAppDriver.clickElementByLinkText(linkName);
		
		if (!webAppDriver.verifyTextContainInAnyTag(expectedText)) {
			webAppDriver.captureScreenshot();
			Reporter.log(expectedText);
			throw new AssertionError(expectedText + " not found");
		}
		//navigator.redirectToHomePageNotLoggedIn();
		//String linkName = linkData.split(",")[0];
		//String expectedText = linkData.split(",")[1];
		//webAppDriver.verifyLinkContent(linkName.trim(), expectedText.trim(), false);
	}
	*/
	

}
