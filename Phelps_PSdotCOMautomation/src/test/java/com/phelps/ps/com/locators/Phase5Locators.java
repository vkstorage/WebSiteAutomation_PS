package com.phelps.ps.com.locators;

/**
 * Basic home page
 * 
 * @author amodak
 * 
 */
public interface Phase5Locators {
	// Header
	String lbStorageUnitSizeXpath = ".//*[@id='results']/div/div[2]/div/div[1]/div[1]/a/img";
	String lbStorageUnitSizeCityXpath = "//div[@id='results']/table/tbody/tr[1]/td[2]/b";
	String lbStorageMonthlyPriceXpath = "//div[@id='results']/table[4]/tbody/tr[1]/td[4]/span[2]";
	String lbStorageMonthlyPriceCityXpath = "//div[@id='results']/table/tbody/tr[1]/td[4]/span[2]";
	String btnSearchXpath = "//img[@class='srp_search_image']/..";
	String tbSearchId = "srp_input";
	String btnSortingPriceXpath = "//div[@id='results']/table[4]/thead/tr[2]/td[4]/div/a/img";
	String btnSortingPriceCityXpath = "//div[@id='results']/table/thead/tr[2]/td[4]/div/a/img";
	String drpdwnCityId = "city_dropdown";
	String btnContinueXpath = "//div[@id='results']/table[4]/tbody/tr[1]/td[5]/div/a/img";
	String btnContinueCityXpath = "//div[@id='results']/table/tbody/tr[1]/td[5]/div/a/img";
	String tbSRPSearchId = "srp_search";
	String imgPropertyXpath = ".//*[@id='results']/div[4]/div[3]/div[1]/div[1]/div[1]/a/img";
	String lbSRPId = "srp_speical";

	String imgAllPropertyPhotosXpath = ".//*[@id='results']//img[contains(@alt,'Exterior Image of Public Storage')]";
	String lbCLPFAQAnswersXpath=".//*[@id='city-tab-faqs-content']//div//p[contains(text(),'$') or contains(text(),'x') or contains(text(),'size')]";
}
