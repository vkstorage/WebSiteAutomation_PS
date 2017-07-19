package com.phelps.ps.com.mobiletestsuite;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.pheps.ps.com.mobile.locators.MobileSRPPLPLocators;
import com.pheps.ps.com.mobile.locators.MobileVideoLandingPagesLocators;

public class VideoLandingPageMobileTestSuite extends BasicMobileTestSuite implements MobileVideoLandingPagesLocators,
		CommonSearchTextLocators {
	//@Test
	public void recentSearchTest() {
		int positionRDPProperty = 4;
		String address1, address2, address3;
		String recentAddress1, recentAddress2, recentAddress3;
		navigator.gotoMobileVideoLandingPage();
		mobileVideoLandingPagesActions.searchTest(searchText34);
		mobileCommonActions.getAllSearchResultArrows().get(positionRDPProperty).click();
		webAppDriver.relax(500);
		address1 = webAppDriver.findElementByXpath(MobileSRPPLPLocators.lbSpaceMobileStreetAddressXpath).getText();

		navigator.gotoMobileVideoLandingPage();

		recentAddress1 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink1Xpath).getText();
		webAppDriver.verifyEqualsString(address1, recentAddress1);
		mobileVideoLandingPagesActions.searchTest(searchText37);
		mobileCommonActions.getAllSearchResultArrows().get(positionRDPProperty).click();

		address2 = webAppDriver.findElementByXpath(MobileSRPPLPLocators.lbSpaceMobileStreetAddressXpath).getText();

		navigator.gotoMobileVideoLandingPage();
		recentAddress1 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink1Xpath).getText();
		recentAddress2 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink2Xpath).getText();
		webAppDriver.verifyEqualsString(address2, recentAddress1);
		webAppDriver.verifyEqualsString(address1, recentAddress2);
		mobileVideoLandingPagesActions.searchTest(searchText40);
		mobileCommonActions.getAllSearchResultArrows().get(positionRDPProperty).click();

		address3 = webAppDriver.findElementByXpath(MobileSRPPLPLocators.lbSpaceMobileStreetAddressXpath).getText();

		navigator.gotoMobileVideoLandingPage();
		recentAddress1 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink1Xpath).getText();
		recentAddress2 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink2Xpath).getText();
		recentAddress3 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink3Xpath).getText();
		webAppDriver.verifyEqualsString(address3, recentAddress1);
		webAppDriver.verifyEqualsString(address2, recentAddress2);
		webAppDriver.verifyEqualsString(address1, recentAddress3);
		mobileVideoLandingPagesActions.searchTest(searchText20);
		mobileCommonActions.getAllSearchResultArrows().get(positionRDPProperty).click();

		address1 = webAppDriver.findElementByXpath(MobileSRPPLPLocators.lbSpaceMobileStreetAddressXpath).getText();
		navigator.gotoMobileVideoLandingPage();
		recentAddress1 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink1Xpath).getText();
		recentAddress2 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink2Xpath).getText();
		recentAddress3 = webAppDriver.findElementByXpath(hlnkMobileRecentSearchlink3Xpath).getText();
		webAppDriver.verifyEqualsString(address1, recentAddress1);
		webAppDriver.verifyEqualsString(address3, recentAddress2);
		webAppDriver.verifyEqualsString(address2, recentAddress3);

	}

	@Test(dataProvider = "MobileVideoPageProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyCategoriesTextAndImagesTest(String id, String title,String description, String imagesExtensions) {
		String imagesPrefix = ".//div[@id='"+id+"']//img[@class='vidThumb'][@src='"+imagesExtensions+"']";
		List<String> categoriesText = new ArrayList<String>();
		categoriesText.add("How to Pack & Store");
		categoriesText.add("How to Move In");
		categoriesText.add("What to Expect");
		categoriesText.add("Why Us?");
		categoriesText.add("Commercials");
		navigator.gotoMobileVideoLandingPage();

		for (String categories : categoriesText) {
			webAppDriver.verifyPresenceOfTextInH2Tag(categories);
		}

		// To check for titles,images and copy
		webAppDriver.verifyPresenceOfTextInSpanTag(title.trim());
		webAppDriver.verifyTextWithSpacePresentInAnyTag(description);
		webAppDriver.verifyElementPresentByXpath(imagesPrefix);

		// copy pending

	}
	
@Test(dataProvider="MobileVideoLandingPageProvider",
	 dataProviderClass=com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyIndividualVideoTest(String imagesExtension, String videoLandingPageURL, String videoLandingPagebreadCrumbs,
			String hasDownloadTip, String pageTitle) {

		navigator.gotoMobileVideoLandingPage();

		String elementToClick = "//span[@class='vidText']//span[text()='"+pageTitle+"']/..";
		webAppDriver.clickElementByXpath(elementToClick);
 webAppDriver.relax(500);
		// To verify the redirection to the correct URL
		String videoURLWithBaseURL = baseUrl + videoLandingPageURL;
		String currentVideoURL = webAppDriver.getCurrentUrl();
		webAppDriver.verifyEqualsString(currentVideoURL, videoURLWithBaseURL);

		// To verify the page title
		webAppDriver.verifyPresenceOfTextInH1Tag(pageTitle);

		// To verify video plays on click 
		webAppDriver.switchtoiFrameByXpath(iframeMobileVideoIndividualXpath);
		webAppDriver.clickElementByCss(btnMobileLargePlayCss);
		//webAppDriver.relax(200);
		// webAppDriver.clickElementById("player");
		//webAppDriver.verifyElementPresentByXpath(btnMobileVideoPauseXpath);
		webAppDriver.switchTo().defaultContent();

		// To verify the breadcrumbs
		String[] breadCrumbs = videoLandingPagebreadCrumbs.split(">");
		for (int i = 0; i < breadCrumbs.length; i++) {
			String singleBreadCrumbXpath;
			breadCrumbs[i] = breadCrumbs[i].trim();
			if (i == 0)
				singleBreadCrumbXpath = "//div[@class='breadcrumb']/a[contains(.,'" + breadCrumbs[i] + "')]";
			else if (i == 1)
				singleBreadCrumbXpath = "//div[@class='breadcrumb']/a[contains(.,'" + breadCrumbs[i] + "')]";
			else
				singleBreadCrumbXpath = "//div[@class='breadcrumb'][contains(.,'" + breadCrumbs[i] + "')]";
			webAppDriver.verifyElementPresentByXpath(singleBreadCrumbXpath);
		}

		// To verify the download button is present
		if (hasDownloadTip.equalsIgnoreCase("TRUE")) {
			webAppDriver.verifyElementPresentByXpath(btnMobileDownloadTipsPDFXpath);
		}

		// to verify promo
		webAppDriver.verifyElementPresentByXpath(imgMobilePromoXpath);

		webAppDriver.clickElementByLinkText("See all videos");
		webAppDriver.verifyElementPresentById(ddVideoFileterId);
		webAppDriver.navigate().back();

		mobileVideoLandingPagesActions.searchTest(searchText36);

	}


//@Test(dataProvider="MobileVideoLandingPageProvider",
//dataProviderClass=com.phelps.ps.com.dataprovider.TestDataProvider.class)
public void verifyIndividualVideoPageLinkTest(String imagesExtension, String videoLandingPageURL, String videoLandingPagebreadCrumbs,
		String hasDownloadTip, String pageTitle){
	
	
}

}
