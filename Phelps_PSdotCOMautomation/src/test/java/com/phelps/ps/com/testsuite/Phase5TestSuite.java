package com.phelps.ps.com.testsuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.phelps.ps.com.locators.CommonLocators;
import com.phelps.ps.com.locators.CommonSearchTextLocators;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.PaymentsLocators;
import com.phelps.ps.com.locators.Phase5Locators;

public class Phase5TestSuite extends BasicTestSuite implements HomeLocators, CommonLocators, PaymentsLocators, Phase5Locators , CommonSearchTextLocators {

	@BeforeClass
	public void setUp() {
		 navigator.login(phase5LoginUsername, "test123");
	}

	/**
	 * Refers to test Link PS-106:TC-Phase 5 -1.1.1- Verify ways to navigate phase
	 * 5 state page
	 */
	 //@Test
	public void verifyNavigateToPhase5PageByZipTest() {
		navigator.gotoPhase5("New Jersey");
		phase5Actions.clickOnPropertyImage();
		navigator.gotoPhase5("New Jersey", "Self-Storage Facilities in New Jersey");
	}

	/**
	 * Refers to test Link TC - PS-107:TC-Phase 5 -2.1.1- Verify functionalities
	 * available on phase 5 state page
	 */
	 @Test
	public void verifyFunctionalityAvailableAtPhase5PageTest() {

		navigator.gotoPhase5("New Jersey");
		phase5Actions.checkStorageUnitSize();
		phase5Actions.checkMonthlyPrice();
		phase5Actions.searchFunctionality("New Jersey");
		navigator.gotoPhase5("New Jersey");
		phase5Actions.searchFunctionality("Trenton");
		navigator.gotoPhase5("New Jersey");
		phase5Actions.searchFunctionality("08638");
		navigator.gotoPhase5("New Jersey");
		phase5Actions.checkAllCitiesDropDown();
		phase5Actions.checkContinueButton("state");
	}

	/**
	 * Refers to test Link PS-108:TC-Phase 5 -3.1.1- Verify ways to navigate phase
	 * 5 city page
	 */
	@Test
	public void verifyWaysToNavigateToPhase5PageTest() {
		navigator.gotoPhase5("Las Vegas");
		navigator.gotoPhase5("Austin", "XPATH_Self-Storage Units in Austin, TX");
		navigator.gotoPhase5("Bee Cave", "XPATH_Self-Storage Units in Bee Cave, TX");
		navigator.gotoPhase5("Self Storage Bee Cave TX", "XPATH_Self-Storage Units in Bee Cave, TX");
	}

	/**
	 * Refers to test Link PS-109:TC-Phase 5 -4.1.1- Verify functionalities
	 * available on phase 5 city page
	 */
	 @Test
	public void verifyFunctionalityOnPhase5CityPageTest() {
		navigator.gotoPhase5("Los Angeles");
		phase5Actions.checkStorageUnitSizeForCity();
		phase5Actions.checkMonthlyPriceCity();
		phase5Actions.searchFunctionality("CA");
		navigator.gotoPhase5("Los Angeles");
		phase5Actions.searchFunctionality("Los Angeles");
		navigator.gotoPhase5("Los Angeles");
		phase5Actions.searchFunctionality("90013");
		navigator.gotoPhase5("Los Angeles");
		phase5Actions.checkAllCitiesDropDown();
		phase5Actions.checkContinueButton("city");
	}

	/**
	 * Refers to tes	t Link PS-110:TC-Phase 5 -5.1.1- Verify ways to navigate phase
	 * 5 zip code page
	 */
	 @Test
	public void verifyWaysTONavigateToStatePhase5PageTest() {
		navigator.gotoPhase5("New Jersey");
		webAppDriver.clickElementByLinkText("Brick");
		phase5Actions.clickOnZipLink("08724");
	}

	/**
	 * Refers to test Link PS-111:TC-Phase 5 -6.1.1- Verify functionalities
	 * available on phase 5 zip code page
	 */
	@Test
	public void verifyfunctionalityAvailableOnPhase5ZipPageTest() {
		navigator.gotoPhase5("Los Angeles");
		phase5Actions.clickOnZipLink("90013");
		phase5Actions.checkAllCitiesDropDown();
		phase5Actions.searchFunctionality("Los Angeles");
		phase5Actions.searchFunctionality("New Jersey");
		navigator.gotoPhase5("Los Angeles");
		phase5Actions.clickOnZipLink("90013");
		phase5Actions.searchFunctionality("90013");
	}

}
