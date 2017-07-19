package com.phelps.ps.com.locators;

public interface CLPLocators {

	String lbStorageUnitsTabId = "city-tab-units";
	String lbStorageUnitsTabSelectedBorderTopXpath = ".//*[@id='city-tab-units']/div[@class='border top']";
	String lbStorageUnitsTabSelectedBgColorXpath = ".//*[@id='city-tab-units']/div[@class='bg top']";
	String lbStorageUnitsTabSelectedFgColorXpath = ".//*[@id='city-tab-units']/div[@class='fg top']";

	String lbFAQsTabId = "city-tab-faqs";
	String lbFAQsTabSelectedBorderTopXpath = ".//*[@id='city-tab-faqs']/div[@class='border top']";
	String lbFAQsTabSelectedBgColorXpath = ".//*[@id='city-tab-faqs']/div[@class='bg top']";
	String lbFAQsTabSelectedFgColorXpath = ".//*[@id='city-tab-faqs']/div[@class='fg top']";

	String lbCityInformationTabId = "city-tab-information";
	String lbCityInformationTabSelectedBorderTopXpath = ".//*[@id='city-tab-information']/div[@class='border top']";
	String lbCityInformationTabSelectedBgColorXpath = ".//*[@id='city-tab-information']/div[@class='bg top']";
	String lbCityInformationTabSelectedFgColorXpath = ".//*[@id='city-tab-information']/div[@class='fg top']";

	String lbStorageStatisticsTabId = "city-tab-stats";
	String lbStorageStatisticsTabSelectedBorderTopXpath = ".//*[@id='city-tab-stats']/div[@class='border top']";
	String lbStorageStatisticsTabSelectedBgColorXpath = ".//*[@id='city-tab-stats']/div[@class='bg top']";
	String lbStorageStatisticsTabSelectedFgColorXpath = ".//*[@id='city-tab-stats']/div[@class='fg top']";

	String lbStorageStatisticsBodyTextXpath = ".//*[@id='city-tab-stats-content']//div/ul/li";

	String divStorageUnitsAllCouponsXpath = ".//*[@id='results']//div[@class='result property']";
	String lbStorageUnitsAllUnitSizeToXpath = "//div[@class='result property']//div[@class='col col2']//span[@class='size-to']";
	String lbStorageUnitsAllPromotionsXpath = "//div[@class='result property']//div[@class='col col3']";
	String lbStorageUnitsAllFromPriceXpath = "//div[@class='result property']//div[@class='col col4']";
	String imgLinkStorageUnitsAllContinueXpath = "//img[@src='/images/srp_continue2.jpg']";
	String hLinkStorageUnitsAllReviewRatingStarsXpath = "//span[@class='ratingstars']";
	String lbStorageUnitsAllAvailableFeaturesXpath = "//b[text()='Available Unit Features:']";
	String lbStorageUnitsAllUnitsFeaturesXpath="//b[text()='Unit features at this location:']";
	String hLinkStorageUnitsAllHyperlinksXpath=".//*[@id='city-tab-information-content']//a";
	String imageHlinkStorageUnitsAllImagesXpath=".//*[@id='city-tab-information-content']//div//img";
}
