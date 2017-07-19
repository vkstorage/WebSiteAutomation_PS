package com.phelps.ps.com.testsuite;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.utils.FileGenerator;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PLPLocators;
import com.phelps.ps.com.locators.ReservationDetailsLocators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;

public class SRP_PLPTestSuite extends BasicTestSuite implements SearchResultsPageLocators, ReservationDetailsLocators,
		PLPLocators, HomeLocators, CommonSearchTextLocators {

	/**
	 * PS-116:TC-SRP-2.1.1-Validate page elements and default view of SRP
	 */
	@Test
	public void verifySRPPageTest() {
		String searchCriteria = searchText28;
		navigator.gotoDefaultSearchResults(searchCriteria);

		/*
		 * Verify presence of Home/Search Results Bread Crumbs
		 */
		String breadCrumb = "Home / Search Results";
		webAppDriver.verifyPresenceOfTextLocatedById(lbBrdCrmbsId, breadCrumb);

		/*
		 * Verify text in search box of SRP is same as text entered on Home Page
		 */
		webAppDriver.verifyPresenceOfTextInTextBoxLocatedById(tbSearchBoxId, searchCriteria);

		/*
		 * Verify Presence Of Global Header
		 */
		//webAppDriver.verifyPresenceOfTextLocatedById(lbNeedHelpclass, "NEED HELP?");
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbNeedHelpclass, "Need Help?");
		webAppDriver.verifyPresenceOfTextLocatedByXpath(hlnkStorageXpath, "Storage");
	}

	@Test
	public void verifySRPPageStorageTabTest() {
		navigator.gotoDefaultSearchResults(searchText29);
		/*
		 * Verify Storage Units tab
		 */
		webAppDriver.checkTextInListByCss(tabStorageTypesCss, "Storage Units");
		/*webAppDriver.verifyPresenceOfTextInDivTagText("Size");
		webAppDriver.verifyPresenceOfTextInDivTagText("Promotion");
		webAppDriver.verifyPresenceOfTextInDivTagText("Monthly Price");
*/
		/*
		 * Verify storage Distance sorting order
		 */
		/*ArrayList<Double> distanceInMiles = new ArrayList<Double>();
		for (WebElement dist : webAppDriver.findAllElementsByXpath(lbDistanceXpath)) {
			distanceInMiles.add(Double.parseDouble(dist.getText().split(" ")[0]));
		}
		webAppDriver.verifyNumberListIsAscendingOrderSorted(distanceInMiles);

		// Click on distance to reverse the sorting order
		searchResultsPageActions.clickDistance();

		
		 * Verify storage Distance sorting order in reverse
		 
		distanceInMiles = new ArrayList<Double>();
		for (WebElement dist : webAppDriver.findAllElementsByXpath(lbDistanceXpath)) {
			distanceInMiles.add(Double.parseDouble(dist.getText().split(" ")[0]));
		}
		webAppDriver.verifyNumberListIsDescendingOrderSorted(distanceInMiles);
*/
		/*
		 * Verify all properties have continue button and size details
		 */
		int expectedNumber = webAppDriver.getTotalNumberOfElementsByXpath(lbSizeCategoryXpath);
		webAppDriver.verifyTotalNumberOfElementPresentByCssSelector(btnAllContinueCss, expectedNumber);
		//webAppDriver.verifyTotalNumberOfElementPresentByCssSelector(lbSpaceSizeCss, expectedNumber);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbSpacesSizeXpath, expectedNumber);
		//webAppDriver.verifyTotalNumberOfElementPresentByCssSelector(lbPriceCss, expectedNumber);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbPriceXpath, expectedNumber);

		/*
		 * Verify Storage Vehicle tab
		 */
		searchResultsPageActions.clickVehicleUnitTab();
		webAppDriver.checkTextInListByCss(tabStorageTypesCss, "Vehicle Units");

	}

	/**
	 * 
	 * PS-115:TC-SRP-1.1.3-Verify Search Functionality On SRP Page
	 */
	@Test
	public void verifySearchFunctionalityOnSRPPageTest() {
		String[] noPropertyText={"No properties were found","in this region.",	"For a broader search,","drag or zoom out on the map.","Alternatively","change your search","to an adjacent zip code or area."};
				String searchCriteria = searchText30;
		navigator.gotoDefaultSearchResults(searchCriteria);
		webAppDriver.checkAllElementsHavingTextByCss(lbSpaceAddressCss, "CA");

		searchCriteria = "Chicago";
		searchResultsPageActions.clearAndReEnterSearch(searchCriteria);
		webAppDriver.checkAllElementsHavingTextByCss(lbSpaceAddressCss, searchCriteria);

		searchCriteria = "60605";
		searchResultsPageActions.clearAndReEnterSearch(searchCriteria);
		webAppDriver.verifyTotalNumberOfElementPresentIsGreaterThanGivenByCss(lbSpaceAddressCss, 0);

		/*searchCriteria = " ";
		searchResultsPageActions.clearAndReEnterSearch(searchCriteria);
*/
		searchCriteria = ".";
		searchResultsPageActions.clearAndReEnterSearch(searchCriteria);
for(int i = 0;i<=noPropertyText.length;i++){
			
			if(i==4)
				webAppDriver.verifyPresenceOfTextInSpanTag(noPropertyText[i]);	
			else if(i==5)
				webAppDriver.verifyElementPresentByXpath("//div[@class='r3'][contains(.,'"+noPropertyText[i]+"')]");
			else
			webAppDriver.verifyPresenceOfTextInDivTagText(noPropertyText[i]);
					
			}
	}

	/**
	 * Verify show all available units and info, show all small ,large, medium
	 * units, and different test objects. Also verify various
	 * tabs(direction,storage unit, review,video)
	 */
	@Test(dataProvider = "PropertySizeNameProvider", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyTypesOfSizeLinksTest(String linkName) {

		String searchCriteria = searchText31;
		/*
		 * Search for Storage units by Area Code
		 */
		navigator.gotoDefaultSearchResults(searchCriteria);

		/*
		 * Storing data for PLP page for further verifications
		 */
		String firstPageAddress = webAppDriver.readTextByCss(lbSpaceAddressCss);
		String firstPageRating = webAppDriver.readTextByCss(lbSpaceRatingCss);
		String firstPageReviewCount = webAppDriver.readTextByCss(lbSpaceReviewCountCss);

		/*
		 * Hashmap containing cost for each dimension on SRP page
		 */
		HashMap<String, String> costAndDimensionsFirstPage = searchResultsPageActions.costAndDimensionsOnFirstPage();
		int panelIndex = searchResultsPageActions.getSrpItemIndex();

		/*
		 * Click on All Storage Units and Info link
		 */
		searchResultsPageActions.clickLinkOfProperty(panelIndex, linkName);

		/*
		 * Verifying that the cost from SRP page is minimum when compared to all
		 * the costs on PLP page(for a particular dimension)
		 */

		if (linkName.equals("Show all available units and info")) {
			for (String dimensionOnFirstPage : costAndDimensionsFirstPage.keySet()) {
				int costOnFirstPage = Integer.parseInt(
						costAndDimensionsFirstPage.get(dimensionOnFirstPage).substring(1).replaceFirst("/mo.", ""));
				int minCostSecondPage = plpActions.minimumPriceOnSecondPage(dimensionOnFirstPage);
				webAppDriver.verifyEquals(costOnFirstPage, minCostSecondPage);
			}
		}

		/*
		 * Verify Multiple details on PLP Page
		 */
		plpActions.verifyMultipleDetails(firstPageAddress, firstPageRating, firstPageReviewCount, linkName,
				searchCriteria);
	}

	/**
	 * Test objects displayed on Storage unit tab and all the functionalities on
	 * storage units tab
	 */
	@Test(dataProvider = "SearchCriteriaData", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyStorageUnitsTabTest(String searchCriteria) {
		navigator.intoPLPByPropertyPhoto(searchCriteria);

		/*
		 * Verify Hide filters button
		 */
		webAppDriver.verifyElementPresentByLinkText("Hide Filters >");
		plpActions.clickLinkForHideFilters();
		webAppDriver.verifyElementIsInvisibleById(btnSmallUnitsId);
		webAppDriver.verifyElementIsInvisibleById(btnMediumUnitsId);
		plpActions.clickLinkForShowFilters();
		webAppDriver.verifyAttributeContainsValueById(btnAllUnitsId, "class", "srp_filter_btn active");
		/*
		 * Verify All units, small units, medium units and large units tab
		 * buttons
		 */
		webAppDriver.verifyElementPresentById(btnSmallUnitsId);
		webAppDriver.verifyElementPresentById(btnLargeUnitsId);
		webAppDriver.verifyElementPresentById(btnMediumUnitsId);
		webAppDriver.verifyElementPresentById(btnAllUnitsId);

		/*
		 * Verify all units list with details and continue button
		 */
		int allSizes = webAppDriver.getTotalNumberOfElementsByCss(lbAllDimensionsCss);
		int allDimensionsForSizes = webAppDriver.getTotalNumberOfElementsByCss(lbAllDImensionsStorageTabCss);
		int allDetailsForSizes = webAppDriver.getTotalNumberOfElementsByCss(lbAllDetailsStorageTabCss);
		int allPricesForSizes = webAppDriver.getTotalNumberOfElementsByCss(lbAllPricesStorageTabCss);
		int allContinues = webAppDriver.getTotalNumberOfElementsByCss(btnContinueButtonStorageUnitCss);
		webAppDriver.verifyEquals(allSizes, allDimensionsForSizes, allDetailsForSizes, allPricesForSizes, allContinues);

		/*
		 * Click on continue button and verify that reservations page is
		 * displayed
		 */
		plpActions.clickContinueButtonForUnit();
		commonActions.getURLWithOptimizelyTrue();
		webAppDriver.verifySpanTagTextEquals("Hold Unit");
		webAppDriver.verifySpanTagTextEquals("Express Check-in");
		
		//plpActions.clickBackLinkForGoingToPLP();
		webAppDriver.navigate().back();

		plpActions.clickSmallUnit();
		webAppDriver.checkAllElementsHavingTextByCss(lbAllDimensionsCss, "Small");

		plpActions.clickMediumUnit();
		webAppDriver.checkAllElementsHavingTextByCss(lbAllDimensionsCss, "Medium");

		plpActions.clickLargeUnit();
		webAppDriver.checkAllElementsHavingTextByCss(lbAllDimensionsCss, "Large");

		/*
		 * Verify List of nearby self storage locations
		 */
		webAppDriver.verifyDivTagTextContains("Nearby Self Storage Locations");
		webAppDriver.checkAllElementsHavingTextByCss(lbNearbyDistanceCss, "mi");

		/*
		 * Verify different search storage facility with search box and search
		 * buttons
		 */
		webAppDriver.verifyPresenceOfTextInH2Tag("Find A Different Self Storage Facility");
		webAppDriver.verifyElementPresentByCss(btnSearchAgainCss);
		webAppDriver.verifyElementPresentByCss(tbSearchAgainCss);

		/*
		 * Verify Map with street view and virtual tour buttons
		 */
		webAppDriver.verifyElementPresentById(mapId);
		webAppDriver.verifyElementTextById(hlnkStreetViewId, "Street View");
		webAppDriver.verifyElementTextByXpath(hlnkVirtualTourStorageTabXpath, "Virtual Tour");
		/*
		 * Verify Unit features such as Climate controlled and Drive up access
		 * buttons
		 */
		webAppDriver.verifyElementPresentById(btnClimateControlledId);
		plpActions.clickClimateControlled();
		webAppDriver.verifyElementPresentById(btnDriveUpAccessId);

		/*
		 * Verify Size Guide Storage
		 */
		webAppDriver.verifyElementPresentByXpath(hlnkSizeGuideStorageTabXpath);

		/*
		 * Click on Search Again and verify that SRP page is displayed with
		 * search results
		 */
		String zipCode = "0703";
		plpActions.clickSearchAgain(zipCode);
		String breadCrumb = "Home / Search Results";
		webAppDriver.verifyVisibilityOfElementLocatedByClassname("srp_first_promo");
		webAppDriver.verifyPresenceOfTextLocatedById(lbBrdCrmbsId, breadCrumb);
		webAppDriver.verifyPresenceOfTextLocatedById(tbSearchBoxId, zipCode);
	}

	/**
	 * PS-128:TC-PLP-07.1-Test objects displayed on Vehicle unit tab
	 */
	@Test(dataProvider = "SearchCriteriaData", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyVehiclesUnitTabTest(String searchCriteria) {

		navigator.intoPLPByPropertyPhoto(searchCriteria);

		plpActions.clickVehicleTab();
		webAppDriver.verifyAttributeContainsValueById(MessageFormat.format(tabAllTabsId, "vehicle"), "class",
				"ps-ui-plptab on");

		webAppDriver.verifyElementPresentByLinkText("Hide Filters >");
		plpActions.clickLinkForHideFilters();
		webAppDriver.verifyElementIsInvisibleById(btnSizeUpto20InchId);
		plpActions.clickLinkForShowFilters();

		webAppDriver.verifyElementPresentById(btnSizeUpto20InchId);
		webAppDriver.verifyElementPresentById(btnSizeUpto35InchId);
		webAppDriver.verifyElementPresentById(btnSizeUpto50InchId);

		webAppDriver.verifyElementPresentById(btnUnitFeatureCoveredId);
		webAppDriver.verifyElementPresentById(btnUncoveredId);
		webAppDriver.verifyElementPresentById(btnUnitFeatureEnclosedId);
	}

	/**
	 * PS-120:TC-SRP-6.1.1-Verify that user can reserve the unit displayed on
	 * SRP
	 * 
	 */
	@Test
	public void verifyLinksToSRPOnRDPTest() {
		String searchCriteria = searchText32;
		navigator.intoGetStartedPageWithStorage(searchCriteria, 2, 1);
		reservationDetailsActions.clickChangeLocationLink();
		webAppDriver.verifyPresenceOfTextLocatedById(tbSearchBoxId, searchCriteria);
		searchResultsPageActions.clickContinue();
		reservationDetailsActions.clickChangeUnitLink();
		webAppDriver.verifyPresenceOfTextLocatedById(tbSearchBoxId, searchCriteria);
	}

	/**
	 * Verify Home Page Search Result Functionality
	 */

	@Test
	public void verifySearchFunctionalityOnHomePageTest() {
		String SearchCriteria = "Illinois";
		navigator.gotoDefaultSearchResults(SearchCriteria);
		webAppDriver.checkAllElementsHavingTextByCss(lbSpaceAddressCss, "IL");

		SearchCriteria = "Chicago";
		navigator.gotoDefaultSearchResults(SearchCriteria);
		webAppDriver.checkAllElementsHavingTextByCss(lbSpaceAddressCss, SearchCriteria);

		SearchCriteria = "60605";
		navigator.gotoDefaultSearchResults(SearchCriteria);
		webAppDriver.verifyTotalNumberOfElementPresentIsGreaterThanGivenByCss(lbSpaceAddressCss, 0);
	}

	/**
	 * PS-118:TC-SRP-4.1.1-Verify page view for 'Vehicle Unit' tab.
	 */
	@Test
	public void verifyVehicleStorageDetailsTest() {
		String searchText = "las vegas";
		navigator.gotoVehicleSearchResults(searchText);
		webAppDriver.verifyTotalNumberOfElementPresentIsGreaterThanGivenByXpath(lbSearchResultsXpath, 0);

		/*
		 * Verify filter options by 1. Upto 20', 2. Upto 35', 3.Upto 50' and
		 * Unit feature filter by 1.Covered, 2.Uncovered, 3.Enclosed
		 */

		plpActions.verifySizeFiltersAndUnitFiltersPresent();

		/*
		 * Verify Size filters and Unit Features filter is visible after click
		 * show filters
		 */
		searchResultsPageActions.clickHideFilters();
		webAppDriver.verifyElementIsInvisibleByClassname(btnSelectSizeSelectUnitButtonsClass);
		searchResultsPageActions.clickShowFilters();
		plpActions.verifySizeFiltersAndUnitFiltersPresent();

		/*
		 * Verify all properties have continue button and size details
		 */
		int expectedVehicleNumber = webAppDriver.getTotalNumberOfElementsByXpath(lbSizeCategoryVehicleXpath);
		webAppDriver.verifyTotalNumberOfElementPresentByCssSelector(btnAllContinueCss, expectedVehicleNumber);
		//webAppDriver.verifyTotalNumberOfElementPresentByCssSelector(lbSpaceSizeCss, expectedVehicleNumber);
		
		//webAppDriver.verifyTotalNumberOfElementPresentByCssSelector(lbPriceCss, expectedVehicleNumber);
		webAppDriver.verifyTotalNumberOfElementPresentByXpath(lbPriceXpath, expectedVehicleNumber);

		/*
		 * Verify vehicle storage Distance sorting order
		 */

		ArrayList<Double> distanceInMiles = new ArrayList<Double>();
		for (WebElement dist : webAppDriver.findAllElementsByXpath(lbDistanceXpath)) {
			distanceInMiles.add(Double.parseDouble(dist.getText().split(" ")[0]));
		}
		webAppDriver.verifyNumberListIsAscendingOrderSorted(distanceInMiles);

		// Click on distance to reverse the sorting order
		searchResultsPageActions.clickDistance();

		// Verify vehicle Distance sorting order in reverse
		distanceInMiles = new ArrayList<Double>();
		for (WebElement dist : webAppDriver.findAllElementsByXpath(lbDistanceXpath)) {
			distanceInMiles.add(Double.parseDouble(dist.getText().split(" ")[0]));
		}
		webAppDriver.verifyNumberListIsDescendingOrderSorted(distanceInMiles);

		/*
		 * Verify vehicle storage monthly prize sorting order
		 */
		searchResultsPageActions.clickMonthlyPrize();
		ArrayList<Double> monthlyPrize = new ArrayList<Double>();
		for (WebElement prize : webAppDriver.findAllElementsByXpath(lbPriceXpath)) {
			String completePrize = prize.getText();
			int positionOfSlash = completePrize.indexOf('/');
			monthlyPrize.add(Double.parseDouble(completePrize.substring(1, positionOfSlash)));
		}
		webAppDriver.verifyNumberListIsAscendingOrderSorted(monthlyPrize);

		// Click on monthly prize to reverse the sorting order
		searchResultsPageActions.clickMonthlyPrize();

		// Verify vehicle monthly prize sorting order in reverse

		monthlyPrize = new ArrayList<Double>();
		for (WebElement prize : webAppDriver.findAllElementsByXpath(lbPriceXpath)) {
			String completePrize = prize.getText();
			int positionOfSlash = completePrize.indexOf('/');
			monthlyPrize.add(Double.parseDouble(completePrize.substring(1, positionOfSlash)));
		}
		webAppDriver.verifyNumberListIsDescendingOrderSorted(monthlyPrize);

		/*
		 * Verify Unit Filters
		 */
		String coveredParking = "Covered parking";
		plpActions.clickButtonCovered();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbSizeCss, coveredParking);
		plpActions.clickButtonCovered();

		String uncoveredParking = "Uncovered parking";
		plpActions.clickButtonUnCovered();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbSizeCss, uncoveredParking);
		plpActions.clickButtonUnCovered();

		String enclosedParking = "Fully enclosed parking";
		plpActions.clickButtonEnclosed();
		webAppDriver.verifyPresenceOfTextLocatedByCss(lbSizeCss, enclosedParking);
		plpActions.clickButtonEnclosed();

		/*
		 * Verify Size Filters
		 */
		searchText = "gilbert";
		searchResultsPageActions.clearAndReEnterSearch(searchText);
		webAppDriver.verifyElementPresentById(btnSizeUpto20InchId);
		plpActions.clickButtonUpTo20();
		webAppDriver.verifyElementIsInvisibleByXpath(SearchResultsPageLocators.lbSearchPleaseWaitXpath);
		String sizeParts[] = plpActions.getSizePartsByCss(lbSpaceSizeCss);
		//String sizeParts[] = plpActions.getSizePartsByXpath(lbSpacesSizeXpath);
		
		plpActions.verifyAllSizesAreLessThanGivenNumber(sizeParts, 20);
		plpActions.clickButtonUpTo20();

		plpActions.clickButtonUpTo35();
		webAppDriver.verifyElementIsInvisibleByXpath(SearchResultsPageLocators.lbSearchPleaseWaitXpath);
		//sizeParts = plpActions.getSizePartsByCss(lbSpaceSizeCss);
		sizeParts=plpActions.getSizePartsByXpath(lbSpacesSizeXpath);
		plpActions.verifyAllSizesAreLessThanGivenNumber(sizeParts, 35);
		plpActions.clickButtonUpTo35();

		plpActions.clickButtonUpTo50();
		webAppDriver.verifyElementIsInvisibleByXpath(SearchResultsPageLocators.lbSearchPleaseWaitXpath);
		//sizeParts = plpActions.getSizePartsByCss(lbSpaceSizeCss);
		sizeParts=plpActions.getSizePartsByXpath(lbSpacesSizeXpath);
		plpActions.verifyAllSizesAreLessThanGivenNumber(sizeParts, 50);
		plpActions.clickButtonUpTo50();

		/*
		 * Verify that View Size Guide Link is present
		 */
		webAppDriver.verifyElementPresentByXpath(hlnkViewSizeGuideXpath);

		/*
		 * Verify that user is navigated to Reservation page after clicking on
		 * Continue button
		 */
		searchResultsPageActions.clickContinue();

	}

	@Test
	public void verifyInvaidSearchOnHomeTest() {
		
		String[] noPropertyText={"No properties were found","in this region.",	"For a broader search,","drag or zoom out on the map.","Alternatively","change your search","to an adjacent zip code or area."};
		navigator.redirectToHomePageNotLoggedIn();
		homeActions.enterSearchText("");
		homeActions.clickSearch();
		webAppDriver.verifyPresenceOfTextInDivTagText("Find Storage in Your Area");
		homeActions.enterSearchCriteria(".");
		// homeActions
		/*webAppDriver.verifyPresenceOfTextInDivTagText(
				"No properties were found in this region. For a broader search, drag or zoom out on the map. Alternatively, change your search to an adjacent zip code or area.");
*/
		for(int i = 0;i<=noPropertyText.length;i++){
			
			if(i==4)
				webAppDriver.verifyPresenceOfTextInSpanTag(noPropertyText[i]);	
			else if(i==5)
				webAppDriver.verifyElementPresentByXpath("//div[@class='r3'][contains(.,'"+noPropertyText[i]+"')]");
			else
			webAppDriver.verifyPresenceOfTextInDivTagText(noPropertyText[i]);
					
			}
	}

	@Test
	public void verifyButtonTextOfStorageTabTest() {
		navigator.intoPLPByPropertyPhoto(searchByZip);
		webAppDriver.verifyElementTextContainsById(btnAllUnitsId, "ALL UNITS\nAll available units");

		webAppDriver.verifyElementTextContainsById(btnSmallUnitsId,
				"SMALL UNITS\nBoxes, small furniture or studio apartment");

		webAppDriver.verifyElementTextContainsById(btnMediumUnitsId, "MEDIUM UNITS\n1 or 2 bedroom apartment");

		webAppDriver.verifyElementTextContainsById(btnLargeUnitsId, "LARGE UNITS\n2 bedroom house\nor larger");

		webAppDriver.verifyElementTextContainsById(btnClimateControlledId, "CLIMATE-CONTROLLED");

		webAppDriver.verifyElementTextContainsById(btnDriveUpAccessId, "DRIVE-UP ACCESS");
	}

	@Test
	public void verifyButtonTextOfVehicleTabTest() {
		navigator.intoPLPByPropertyPhoto(searchByZip);
		plpActions.clickVehicleTab();

		webAppDriver.verifyElementTextContainsById(btnSizeUpto20InchId, "UP TO 20'\nSuitable for a car");

		webAppDriver.verifyElementTextContainsById(btnSizeUpto35InchId, "UP TO 35'\nFits most RV's & Boats");

		webAppDriver.verifyElementTextContainsById(btnSizeUpto50InchId, "UP TO 50'\nOversized Vehicles");

		webAppDriver.verifyElementTextContainsById(btnUnitFeatureCoveredId, "COVERED");

		webAppDriver.verifyElementTextContainsById(btnUnitFeatureUncoveredId, "UNCOVERED");

		webAppDriver.verifyElementTextContainsById(btnUnitFeatureEnclosedId, "ENCLOSED");
	}

	/**
	 * Verify get direction
	 */
	@Test
	public void verifyGetDirectionsFunctionalityTest() {
		String searchCriteria = "Illinois";
		navigator.gotoDefaultSearchResults(searchCriteria);
		String firstAddress = webAppDriver.findElementByXpath(lbFirstAddressXpath).getText();
		String secondAddress = webAppDriver.findElementByXpath(lbSecondAddressXpath).getText();
		System.out.println("   1:" + firstAddress + "   2:" + secondAddress);
		searchResultsPageActions.clickShowAllSmallUnitsForDirections();
		plpActions.clickDirectionsTab();
		webAppDriver.verifyPresenceOfTextLocatedById(lbAddressStorageTabId, firstAddress.replace("\n", ", "));
		plpActions.addFromAddress(secondAddress);
		plpActions.clickGetDirectionsButton();
		webAppDriver.verifyElementPresentByXpath(imgAXpath);
		webAppDriver.verifyElementPresentByXpath(imgBXpath);
		int count = webAppDriver.getTotalNumberOfElementsByXpath(lbNumberOfRowsOfDirectionsXpath);
		webAppDriver.verifyDirectionsGreaterThanZero(count);
	}

	/**
	 * PS-139:TC-PLP-18.1-Test functionality of objects of Vehicle unit tab
	 */

	public void verifyVehicleUnitFunctionalityOnPlpPageTest() {
		String searchCriteria = "las vegas";
		navigator.intoPLPByPropertyPhoto(searchCriteria);
	}

	/**
	 * Bug 2490 - Long-term fix for /locations/ URL issue
	 */
	@Test(dataProvider = "LocationURLDetails", dataProviderClass = com.phelps.ps.com.dataprovider.TestDataProvider.class)
	public void verifyLocationsUrlTest(String url) {
		System.out.println(url);
		webAppDriver.get(baseUrl + url + "?optimizely_disable=true");
		String storeToFile = url;
		String checkEndingUrl = "self-storage-";
		url = url.substring(url.lastIndexOf("/") + 1, url.length());
		String parts[] = url.split("-");
		parts[0] = parts[0].replace("+", " ");
		parts[0] = parts[0].replace("%20", " ");
		String cityToCheck = parts[0];
		String stateCodeToheck = parts[1];
		String verifyLocation = "Self-Storage Units in " + cityToCheck + ", " + stateCodeToheck;
		String srpLocationText = webAppDriver.findElementByXpath(lbSelfStorageUnitsXpath).getText();
		// webAppDriver.verifyPresenceOfTextInH1Tag(verifyLocation);
		webAppDriver.verifyEqualsString(srpLocationText, verifyLocation);
		String currentUrl = webAppDriver.getCurrentUrl();
		checkEndingUrl = checkEndingUrl + cityToCheck + "-" + stateCodeToheck;
		checkEndingUrl = checkEndingUrl.replace(" ", "-");
		checkEndingUrl = checkEndingUrl + "?optimizely_disable=true";
		searchResultsPageActions.verifyUrl(currentUrl, checkEndingUrl);
		System.out.println("Last Part Of URL:" + checkEndingUrl);
		System.out.println("Current URL:" + currentUrl);

		FileGenerator.appendTextToFile("D:\\", "outputFile.csv", storeToFile + "," + currentUrl);

	}

	@Test
	public void verifylocationFinderPopUpRedirectingWrongPLPTest() {
		String url = "https://www.publicstorage.com/storage-locations.aspx";
		String prpUrl = baseUrl + url;
		System.out.println(prpUrl);
		webAppDriver.get(prpUrl);
		webAppDriver.clickElementByXpath(btnOHXpath);

	}

	// Bug 3256 - Hardcode "Uncovered parking" and "Covered parking" on SRP
	// coupons (DESKTOP Only) (PS-0475)
	@Test
	public void VerifyHardCodeParkingTest() {

		navigator.gotoDefaultSearchResults(uncoveredParkSearchText);
		int totalElements = webAppDriver.getTotalNumberOfElementsByXpath("//div[text()='Uncovered parking']");
		for (int j = 1; j <= totalElements; j++) {
			String alluncoveredXpath = "(//div[text()='Uncovered parking'])[" + j
					+ "]/parent::div/parent::li//a[@class='srp_continue']";
			webAppDriver.clickElementByXpath(alluncoveredXpath);
			webAppDriver.verifyElementPresentByXpath(lbUncoveredParkingXpath);
			webAppDriver.navigate().back();
		}
		// Covered

		/*
		 * navigator.gotoDefaultSearchResults(coveredParkSearchText);
		 * totalElements = webAppDriver.getTotalNumberOfElementsByXpath(
		 * "//div[text()='Covered parking']"); for (int j = 1; j <=
		 * totalElements; j++) { String alluncoveredXpath =
		 * "(//div[text()='Covered parking'])[" + j +
		 * "]/parent::div/parent::li//a[@class='srp_continue']";
		 * webAppDriver.clickElementByXpath(alluncoveredXpath);
		 * webAppDriver.verifyElementPresentByXpath(lbCoveredParkingXpath);
		 * webAppDriver.navigate().back(); }
		 */
	}

	// Reservation Deposit
	@Test
	public void verifyReservationDepositSRPTest() {
		String depositTermsText1 = "TBD";
		String depositTermsText2 = "TBD";
		String depositTermsText3 = "TBD";
		String depositTermsText4 = "TBD";
		String depositTermsText5 = "TBD";
		String depositTermsText6 = "TBD";
		String depositTermsText7 = "TBD";

		String searchCriteria = "Illinois";
		navigator.gotoDefaultSearchResults(searchCriteria);

	}

}
