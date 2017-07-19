package com.phelps.ps.com.actions;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;

public class PLPActions extends CommonActions implements PLPLocators {

	CateredWebDriver webAppDriver;

	public PLPActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	/**
	 * This method finds out minimum cost for storage dimension(passed parameter)
	 * 
	 * @param dimension
	 * @return
	 */
	public int minimumPriceOnSecondPage(String dimension) {
		List<CateredWebElement> firstColumnsOnSecondPage = webAppDriver.findAllElementsByXpath(lbAllFirstColumnsXpath);
		int minCostSecondPage = 99999999;
		for (int i = 1; i <= firstColumnsOnSecondPage.size(); i++) {
			String dimensionOnSecondPage = webAppDriver.readTextByXpath(MessageFormat.format(lbAllDimensionsXpath, i));
			if (dimension.equalsIgnoreCase(dimensionOnSecondPage)) {
				int temp = Integer.parseInt(webAppDriver.readTextByXpath(MessageFormat.format(lbAllCostsXpath, i)).substring(1)
						.replaceFirst("/mo.", ""));
				if (temp < minCostSecondPage)
					minCostSecondPage = temp;
			}
		}

		return minCostSecondPage;

	}

	public void verifyMultipleDetails(String firstPageAddress, String firstPageRating, String firstPageReviewCount, String linkName,
			String searchCriteria) {
		/*
		 * Verify that Address on both the pages is same
		 */
		webAppDriver.verifyPresenceOfTextLocatedById(lbSpaceAddressId, firstPageAddress);

		/*
		 * Verify that the Rating for units is same on both the pages
		 */
		webAppDriver.verifyPresenceOfTextInSpanTag(firstPageRating);

		/*
		 * Verify that Review Count is same on both the pages
		 */
		webAppDriver.verifyElementPresentByLinkText("Read Reviews " + firstPageReviewCount);

		webAppDriver.verifyElementPresentByClassName("srp_search_image");
		webAppDriver.verifyElementPresentByXpath(hlnkSizeGuideXpath);
		webAppDriver.verifyElementPresentById(hlnkVirtualTourId);
		webAppDriver.verifyDivTagTextEquals("OFFICE HOURS");
		webAppDriver.verifyDivTagTextEquals("ACCESS HOURS");
		webAppDriver.verifyDivTagTextEquals("PHONE");
		webAppDriver.verifyPresenceOfTextLocatedById(tbSearchCriteriaId, searchCriteria);

		/*
		 * Verify that for Small,Medium,Large or All Units corresponding button is
		 * active and also verify all elements are either Small,Medium or Large if
		 * one of them is selected
		 */
		if (linkName.contains("Small") || linkName.contains("SMALL")) {
			webAppDriver.checkAllElementsHavingTextByCss(lbAllDimensionsCss, "SMALL");
			webAppDriver.verifyAttributeValueById(btnSmallUnitsId, "class", "srp_filter_btn active");
		} else if (linkName.contains("Medium") || linkName.contains("MEDIUM")) {
			webAppDriver.checkAllElementsHavingTextByCss(lbAllDimensionsCss, "MEDIUM");
			webAppDriver.verifyAttributeValueById(btnMediumUnitsId, "class", "srp_filter_btn active");
		} else if (linkName.contains("Large") || linkName.contains("LARGE")) {
			webAppDriver.checkAllElementsHavingTextByCss(lbAllDimensionsCss, "LARGE");
			webAppDriver.verifyAttributeValueById(btnLargeUnitsId, "class", "srp_filter_btn active");
		} else {
			webAppDriver.verifyAttributeValueById(btnAllUnitsId, "class", "srp_filter_btn active");
		}

		webAppDriver.clickElementByPartialLinkText("Read Reviews");

		/*
		 * Verify that the number of Reviews is same as written in the Read reviews
		 * Link
		 */
		int numberOfReviews = webAppDriver.getTotalNumberOfElementsByXpath(lbAllReviewsXpath);
		webAppDriver.verifyATagTextEquals("Read Reviews (" + numberOfReviews + ")");

		/*
		 * Verify that the address is same as earlier one
		 */
		webAppDriver.verifyElementTextContainsByXpath(lbAddressInReviewsXpath, firstPageAddress);

		/*
		 * Verify that the rating is same as earlier one
		 */
		webAppDriver.verifyElementTextContainsByCss(spnRatingInReviewCss, firstPageRating);

		/*
		 * Verify that the ratings tab is selected
		 */
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "reviews"), "class", "ps-ui-plptab on");
		webAppDriver.clickElementById(MessageFormat.format(tabAllTabsId, "features"));
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "features"), "class", "ps-ui-plptab on");
		String addressLines[] = firstPageAddress.split("\n");
		webAppDriver.verifyPresenceOfTextInH2Tag("Self Storage Facility Features at " + addressLines[0]);
		webAppDriver.clickElementByLinkText("GET DIRECTIONS");
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "directions"), "class", "ps-ui-plptab on");
		webAppDriver.verifyPresenceOfTextLocatedById(lbAddressStorageTabId, firstPageAddress.replace("\n", ", "));
		webAppDriver.clickElementById(MessageFormat.format(tabAllTabsId, "storage"));
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "storage"), "class", "ps-ui-plptab left on");
		webAppDriver.clickElementById(MessageFormat.format(tabAllTabsId, "vehicle"));
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "vehicle"), "class", "ps-ui-plptab on");
		webAppDriver.clickElementById(MessageFormat.format(tabAllTabsId, "video"));
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "video"), "class", "ps-ui-plptab on");
	}

	/**
	 * click Directions tab
	 */
	public void clickDirectionsTab() {
		webAppDriver.clickElementById((MessageFormat.format(tabAllTabsId, "directions")));
	}

	/**
	 * add From Address
	 */
	public void addFromAddress(String fromAddress) {
		webAppDriver.enterTextToElementById(lbAddressFromId, fromAddress);
	}

	/**
	 * click on Get Directions button
	 */
	public void clickGetDirectionsButton() {
		webAppDriver.clickElementByXpath(btnGetDirectionsXpath);
	}

	/*
	 * Click on small unit button
	 */
	public void clickSmallUnit() {
		webAppDriver.clickElementById(btnSmallUnitsId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	/*
	 * Click on Medium unit button
	 */
	public void clickMediumUnit() {
		webAppDriver.clickElementById(btnMediumUnitsId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	/*
	 * Click on large unit button
	 */
	public void clickLargeUnit() {
		webAppDriver.clickElementById(btnLargeUnitsId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	/*
	 * Click on climate controlled button
	 */
	public void clickClimateControlled() {
		webAppDriver.clickElementById(btnClimateControlledId);
		webAppDriver.checkAllElementsHavingTextByXpath(lbClimateControlInUnitFeaturesXpath, "Climate Controlled");
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	/*
	 * Click on search again button after entering search criteria in text box
	 */
	public void clickSearchAgain(String searchText) {
		webAppDriver.enterTextToElementByCss(tbSearchAgainCss, "0703");
		webAppDriver.clickElementByCss(btnSearchAgainCss);
	}

	/*
	 * Click on hide Filter Link
	 */
	public void clickLinkForHideFilters() {
		webAppDriver.clickElementByLinkText("Hide Filters >");
	}

	/*
	 * Click on show Filter Link
	 */
	public void clickLinkForShowFilters() {
		webAppDriver.clickElementByLinkText("Show Filters >");
	}

	/*
	 * Click on vehicles tab
	 */
	public void clickVehicleTab() {
		webAppDriver.clickElementById(MessageFormat.format(tabAllTabsId, "vehicle"));
	}

	/*
	 * Click on continue button for any unit
	 */
	public void clickContinueButtonForUnit() {
		webAppDriver.clickElementByCss(btnContinueButtonStorageUnitCss);
		webAppDriver.verifyPresenceOfTextInDivTagText("Your Reservation");
	}

	/*
	 * Click on back link for going back to PLP
	 */
	public void clickBackLinkForGoingToPLP() {
		//webAppDriver.clickElementByLinkText("Back");
		webAppDriver.clickElementById("rdp_go_back_link");
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "storage"), "class", "ps-ui-plptab left on");
	}

	public void clickButtonUpTo20() {
		webAppDriver.clickElementById(btnSizeUpto20InchId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	public void clickButtonUpTo35() {
		webAppDriver.clickElementById(btnSizeUpto35InchId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	public void clickButtonUpTo50() {
		webAppDriver.clickElementById(btnSizeUpto50InchId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	public void clickButtonCovered() {
		webAppDriver.clickElementById(btnUnitFeatureCoveredId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	public void clickButtonUnCovered() {
		webAppDriver.clickElementById(btnUnitFeatureUncoveredId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);
	}

	public void clickButtonEnclosed() {
		webAppDriver.clickElementById(btnUnitFeatureEnclosedId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbPleaseWaitXpath);

	}

	public void verifySizeFiltersAndUnitFiltersPresent() {
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUpto20Id, "UP TO 20'");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUpto20Id, "Suitable for a car");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUpto35Id, "UP TO 35'");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUpto35Id, "Fits most RV's & Boats");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUpto50Id, "UP TO 50'");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUpto50Id, "Oversized Vehicles");

		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnCoveredId, "COVERED");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnUncoveredId, "UNCOVERED");
		webAppDriver.verifyElementTextContainsById(SearchResultsPageLocators.btnEnclosedId, "ENCLOSED");
	}

	public String[] getSizePartsByCss(String lbSpaceSizeCss) {
		String parts[] = null;
		for (WebElement size : webAppDriver.findAllElementsByCss(lbSpaceSizeCss)) {
			String completeSize = size.getText();
			parts = completeSize.split("x");
			for (int i = 0; i < parts.length; i++) {
				parts[i] = parts[i].substring(0, parts[i].indexOf("'")).trim();
			}
		}
		return parts;
	}

	
	public String[] getSizePartsByXpath(String lbSpaceSizeXpath) {
		String parts[] = null;
		for (WebElement size : webAppDriver.findAllElementsByXpath(lbSpaceSizeXpath)) {
			String completeSize = size.getText();
			parts = completeSize.split("x");
			for (int i = 0; i < parts.length; i++) {
				parts[i] = parts[i].substring(0, parts[i].indexOf("'")).trim();
			}
		}
		return parts;
	}

	/**
	 * This method checks that all the numbers present in the list passed as first
	 * parameter are less than second number
	 */
	public void verifyAllSizesAreLessThanGivenNumber(String numbers[], int number) {
		for (int i = 0; i < numbers.length; i++) {
			if (Integer.parseInt(numbers[i]) > number)
				webAppDriver.captureScreenshot();
		}
	}

	/**
	 * This method returns prefix address from the property address
	 */
	public String getPrefixPropertyAddress(String search) {

		String address = webAppDriver.findElementById(search).getText();
		String imagePrefix = convertAddressIntoImagePrefix(address);
		return imagePrefix;

	}

	/**
	 * This method returns the image name from image source string passed as a
	 * string argument
	 */
	public String getImageName(String srcStringXpath) {

		String srcString = webAppDriver.findElementByXpath(srcStringXpath).getAttribute("src");
		String imageName = srcString.substring(srcString.lastIndexOf("/") + 1, srcString.length());
		return imageName;
	}

	/**
	 * This method returns the image name from image style for thumbnail images *
	 */

	public String getThumbnailImageName(String srcStringXpath) {

		String srcString = webAppDriver.findElementByXpath(srcStringXpath).getAttribute("style");
		String temp[] = srcString.split("/");
		int len = temp.length;
		String imageName = temp[len - 1];
		imageName = imageName.substring(0, imageName.indexOf("\""));
		System.out.println("^^^name from method " + imageName);
		return imageName;
	}

	/**
	 * This method returns the image name from image src for thumbnail images *
	 */

	public String getImageNameSrc(String srcStringXpath) {

		String srcString = webAppDriver.findElementByXpath(srcStringXpath).getAttribute("src");
		String temp[] = srcString.split("/");
		int len = temp.length;
		String imageName = temp[len - 1];
		// System.out.println("^^^name from method "+imageName);
		imageName = imageName.substring(0, imageName.length());
		// System.out.println("Image name from method "+imageName);
		return imageName;
	}

	public void verifyEqualImageSourceStrings(String str1, String str2) {

		if (!str1.equalsIgnoreCase(str2)) {
			webAppDriver.captureScreenshot();
			Reporter.log("First value " + str1 + " is not equal to second value " + str2);
			throw new AssertionError("First value " + str1 + " is not equal to second value " + str2);
		}
	}

	/**
	 * This method is used to click thumbnail image located by Xpath
	 */
	public void clickImageThumbnail(String thumbnailImageXpath) {
		webAppDriver.clickElementByXpath(thumbnailImageXpath);
	}

	public void clickFAQTab() {
		webAppDriver.clickElementByXpath(tabFaqXpath);
	}
	
	public void clickChangeUnitConfirm(){
		webAppDriver.clickElementByXpath(btnChangeUnitXpath);
	}
	
	
	//For HomePageRefresh Recommended Location
	public List<Integer> getSortedPriceOfAllUnit(String unitSize){
		List<Integer> allUnitPrice=new ArrayList<Integer>();
		List<CateredWebElement> allUnitElements=new ArrayList<CateredWebElement>();
		String[] singleSizePrices;
		
		switch(unitSize){
		case "Small":
		allUnitElements=webAppDriver.findAllElementsByXpath(lbAllSmallPricesXpath);
		singleSizePrices=new String[allUnitElements.size()];
		for (int i=0;i<allUnitElements.size();i++){
			singleSizePrices[i]=allUnitElements.get(i).getText().replace("$", "");
			singleSizePrices[i]=singleSizePrices[i].replace("/mo.", "").trim();
			allUnitPrice.add(Integer.parseInt(singleSizePrices[i]));			
		}
		Collections.sort(allUnitPrice);
		return allUnitPrice;
		case "Medium":
			allUnitElements=webAppDriver.findAllElementsByXpath(lbAllMediumPricesXpath);
			singleSizePrices=new String[allUnitElements.size()];
			for (int i=0;i<allUnitElements.size();i++){
				singleSizePrices[i]=allUnitElements.get(i).getText().replace("$", "");
				singleSizePrices[i]=singleSizePrices[i].replace("/mo.", "").trim();
				allUnitPrice.add(Integer.parseInt(singleSizePrices[i]));	
			}
			Collections.sort(allUnitPrice);
			return allUnitPrice;
		case "Large":
			allUnitElements=webAppDriver.findAllElementsByXpath(lbAllLargePricesXpath);
			singleSizePrices=new String[allUnitElements.size()];
			for (int i=0;i<allUnitElements.size();i++){
				singleSizePrices[i]=allUnitElements.get(i).getText().replace("$", "");
				singleSizePrices[i]=singleSizePrices[i].replace("/mo.", "").trim();
				allUnitPrice.add(Integer.parseInt(singleSizePrices[i]));	
			}
			Collections.sort(allUnitPrice);
			return allUnitPrice;
		
		}
		
		Collections.sort(allUnitPrice);
		return allUnitPrice;
	}
}
