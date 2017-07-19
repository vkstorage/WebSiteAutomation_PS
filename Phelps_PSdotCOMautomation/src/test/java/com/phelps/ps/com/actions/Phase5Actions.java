package com.phelps.ps.com.actions;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.LoginLocators;
import com.phelps.ps.com.locators.Phase5Locators;
import com.phelps.ps.com.locators.SearchResultsPageLocators;

public class Phase5Actions implements LoginLocators, HomeLocators, CommonLocators, Phase5Locators, SearchResultsPageLocators {

	CateredWebDriver webAppDriver;

	public Phase5Actions(CateredWebDriver webappDriver) {
		this.webAppDriver = webappDriver;
	}

	public void checkStorageUnitSize() {
		Pattern digitPattern = Pattern.compile("\\d{1,2}'x\\d{1,2}' - \\d{1,2}'x\\d{1,2}'");
		String storageUnitSize = webAppDriver.findElementByXpath(lbStorageUnitSizeXpath).getText().trim();
		if (!digitPattern.matcher(storageUnitSize).matches()) {
			throw new AssertionError();
		}
	}

	public void checkStorageUnitSizeForCity() {
		Pattern digitPattern = Pattern.compile("\\d{1,2}'x\\d{1,2}' - \\d{1,2}'x\\d{1,2}'");
		String storageUnitSize = webAppDriver.findElementByXpath(lbStorageUnitSizeCityXpath).getText().trim();
		if (!digitPattern.matcher(storageUnitSize).matches()) {
			throw new AssertionError();
		}
	}

	public void checkMonthlyPrice() {

		String unitPrice = webAppDriver.findElementByXpath(lbStorageMonthlyPriceXpath).getText().trim();
		int unitPriceInt = new Integer(unitPrice.substring(1, unitPrice.indexOf("/")));
		webAppDriver.clickElementByXpath(btnSortingPriceXpath);
		webAppDriver.clickElementByXpath(btnSortingPriceXpath);
		String unitPriceAfterSorting = webAppDriver.findElementByXpath(lbStorageMonthlyPriceXpath).getText().trim();
		int unitPriceAfterSortingInt = new Integer(unitPriceAfterSorting.substring(1, unitPriceAfterSorting.indexOf("/")));
		if (!(unitPriceAfterSortingInt > unitPriceInt)) {
			throw new AssertionError();
		}

	}

	public void checkMonthlyPriceCity() {

		String unitPrice = webAppDriver.findElementByXpath(lbStorageMonthlyPriceCityXpath).getText().trim();
		int unitPriceInt = new Integer(unitPrice.substring(1, unitPrice.indexOf("/")));
		webAppDriver.clickElementByXpath(btnSortingPriceCityXpath);
		String unitPriceAfterSorting = webAppDriver.findElementByXpath(lbStorageMonthlyPriceCityXpath).getText().trim();
		int unitPriceAfterSortingInt = new Integer(unitPriceAfterSorting.substring(1, unitPriceAfterSorting.indexOf("/")));
		if (!(unitPriceAfterSortingInt < unitPriceInt)) {
			throw new AssertionError();
		}

	}

	public void searchFunctionality(String searchCriteria) {
		webAppDriver.enterTextToElementById(tbSearchId, searchCriteria);
		webAppDriver.clickElementByXpath(Phase5Locators.btnSearchXpath);
		webAppDriver.verifyElementPresentByXpath(btnFirstContinueResultXpath);
		webAppDriver.navigate().back();
		try {
			webAppDriver.verifyElementPresentById(Phase5Locators.btnSearchXpath);
		} catch (Exception e) {
			Reporter.log("element " +Phase5Locators.btnSearchXpath+" not present");
		}
	}

	public void checkAllCitiesDropDown() {
		WebElement sel = webAppDriver.findElement(By.id("city_dropdown"));
		List<WebElement> lists = sel.findElements(By.tagName("option"));
		if (lists.size() <= 0) {
			throw new AssertionError();
		}

	}

	public void checkContinueButton(String verification) {
		if (verification.contains("state")) {
			webAppDriver.clickElementByXpath(btnContinueXpath);
		} else {
			webAppDriver.clickElementByXpath(btnContinueCityXpath);
		}
		webAppDriver.verifyElementPresentById(tbSRPSearchId);
	}

	public void clickOnZipLink(String linkName) {
		webAppDriver.clickElementByXpath("//a[@data-city-name='" + linkName + "']");
		String xpath = "//h1[contains(text(),'Self-Storage for Rent in " + linkName + "')]";
		webAppDriver.verifyElementPresentByXpath(xpath);
	}

	public void clickOnPropertyImage() {
		webAppDriver.clickElementByXpath(imgPropertyXpath);
		webAppDriver.verifyElementTextById(lbSRPId, "Reserve Today: Month-To-Month Rent, No Obligations");
	}

	public void verifyStateLevelPageImage(String srcURL) {
		if ((!srcURL.endsWith("exterior_1_slideshow_full.jpg")) && (!srcURL.endsWith("/images/nolocation_photo_214.gif"))) {
			
			if((!srcURL.endsWith("exterior_1_map.jpg"))){
			webAppDriver.captureScreenshot();
			Reporter.log(srcURL + " does not contains exterior_1_slideshow_full.jpg or /images/nolocation_photo_214.gif");
			throw new AssertionError("Image source in not maching");
			}
		}
	}
}
