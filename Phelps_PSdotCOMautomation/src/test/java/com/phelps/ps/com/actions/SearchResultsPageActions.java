package com.phelps.ps.com.actions;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.SearchResultsPageLocators;

public class SearchResultsPageActions extends CommonActions implements SearchResultsPageLocators {

	CateredWebDriver webAppDriver;

	public SearchResultsPageActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	/**
	 * Clicks on the first available continue button
	 */
	public void clickContinue() {
		webAppDriver.clickElementByXpath(btnFirstContinueResultXpath);
		webAppDriver.verifyDivTagTextEquals("Your Reservation");
	}

	/**
	 * Clicks Vehicle units tab
	 */
	public void clickVehicleUnitTab() {
		webAppDriver.clickElementByCss(btnVehicleUnitsCss);
		webAppDriver.verifyElementPresentById(btnUpto20Id);
	}

	/**
	 * Clicks the Distance column
	 */
	public void clickDistance() {
		webAppDriver.clickElementById(btnDistanceId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbSearchPleaseWaitXpath);
	}

	public int getSrpItemIndex() {

		List<CateredWebElement> allLocations = webAppDriver.findAllElementsByCss(lbSpaceAddressCss);
		for (int i = 0; i < allLocations.size(); i++) {
			List<CateredWebElement> rowsInPanel = webAppDriver.findAllElementsByXpath(MessageFormat
					.format(lbAllRowsOfPanelXpath, i));

			if (rowsInPanel.size() == 3) {
				return i;
			}
		}

		return 0;
	}

	private CateredWebElement getPropertyImageHavingAllSizes() {
		List<CateredWebElement> allLocations = webAppDriver.findAllElementsByXpath(lbAllLocationsXpath);
		CateredWebElement propertyImage = null;
		for (CateredWebElement location : allLocations) {
			if (location.findAllElements(By.cssSelector(lbAllTypesCss)).size() == 4) {
				propertyImage = location.findElement(By.cssSelector(imgCommonPhotoCss));
				break;
			}
		}

		return propertyImage;
	}

	public void clickLinkOfProperty(int propertyIndex, String linkText) {
		webAppDriver.clickElementByXpath(MessageFormat.format(hlnkShowUnitsXpath, propertyIndex, linkText));
		webAppDriver.verifyDivTagTextEquals("LOCATION");
	}

	/**
	 * This method stores the dimensions of units available along with their
	 * corresponding costs and returns it
	 * 
	 * @return
	 */

	public HashMap<String, String> costAndDimensionsOnFirstPage() {

		List<CateredWebElement> elementsOnFirstPage = webAppDriver.findAllElementsByXpath(MessageFormat.format(
				lbAllRowsOfPanelXpath, 0));
		HashMap<String, String> costAndDimensions = new HashMap<String, String>();
		for (int i = 1; i <= elementsOnFirstPage.size(); i++) {
			costAndDimensions.put(webAppDriver.readTextByXpath(MessageFormat.format(lbDimensionsXpath, i)),
					webAppDriver.readTextByXpath(MessageFormat.format(lbCostXpath, i)));
		}
		return costAndDimensions;
	}

	/**
	 * Clicks the Monthly prize column
	 */
	public void clickMonthlyPrize() {
		webAppDriver.clickElementById(btnMonthlyPrizeId);
		webAppDriver.verifyElementIsInvisibleByXpath(lbSearchPleaseWaitXpath);
	}

	/**
	 * Clicks on Hide Filters Link
	 */
	public void clickHideFilters() {
		webAppDriver.clickElementByXpath(hlnkHideFiltersXpath);
	}

	public void clickShowFilters() {
		webAppDriver.clickElementByXpath(hLnkShowFiltersXpath);

	}

	/**
	 * Clear text box, re-enter search text and click on search button
	 */

	public void clearAndReEnterSearch(String searchText) {
		webAppDriver.clearInputById(tbSearchBoxId);
		webAppDriver.enterTextToElementById(tbSearchBoxId, searchText);
		webAppDriver.clickElementByXpath(btnSearchXpath);
	}

	public void clickImageWithIndex(int index) {
		webAppDriver.clickElementByXpath(MessageFormat.format(imgUnitImageXpath, index));
		webAppDriver.verifyDivTagTextEquals("LOCATION");
	}

	public void clickPropertyImage() {
		getPropertyImageHavingAllSizes().click();
		webAppDriver.verifyDivTagTextEquals("LOCATION");
	}

	/**
	 * ClickOnFirstShowAllSmallUnitsLinksForDirections
	 */
	public void clickShowAllSmallUnitsForDirections() {
		webAppDriver.clickElementByXpath(hLnkShowAllSmallUnitsXpath);
	}

	public void verifyImageSrcWithAddress(String imgSrc, String address) {

		if (!imgSrc.equals("nolocation_photo_214.gif")) {
			String expectedImgSrc = convertAddressIntoImagePrefix(address) + "-exterior_1_slideshow_full.jpg";
			if (!imgSrc.equals(expectedImgSrc)) {
				Reporter.log("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);
				webAppDriver.captureScreenshot();
				throw new AssertionError("Error!! Expected Source is :" + expectedImgSrc + " But actual is :" + imgSrc);

			}
		}

	}
	public void verifyUrl(String URL,String end){
		if(!URL.endsWith(end)){
			Reporter.log("Error! Url "+URL+" is supposed to end with "+end+" but it doesn't actually!!!");
			webAppDriver.captureScreenshot();
			throw new AssertionError();
		}
	}
	
	public void verifyStringContainsText(String xpath,String city,String state){
		webAppDriver.verifyPresenceOfTextLocatedByXpath(xpath, city);
		webAppDriver.verifyPresenceOfTextLocatedByXpath(xpath, state);
	}
	
	public void clickContinueWithPosition(int propertyPosition, int buttonPosition ){
		String element="";
		if(webAppDriver.getCurrentUrl().contains("landing4.aspx"))
			//element="//img[@src='/images/srp-cont-new.png']/..[@class='srp_continue']";
		element="//img[@src='/images/srp-cont-new.png']";
			else if(webAppDriver.getCurrentUrl().contains("landing.aspx"))
				element="//li[contains(@id,'srp_item_')]["+propertyPosition+"]/ul/li["+buttonPosition+"]//img[@src='/images/srp-cont-new.png']";
		
			
		webAppDriver.clickElementByXpath(element);
	}
}
