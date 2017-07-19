package com.phelps.ps.com.locators;

public interface SearchResultsPageLocators {

	String lbBrdCrmbsId = "srp_brd_crmbs";
	String btnFirstContinueResultXpath = "(.//*[contains(@id,'srp_item')]/ul/li/div[4]/a[1]/img)[1]";
	String lbSearchResultsXpath = "//li[contains(@id,'srp_item')]";
	String lbAllLocationsXpath = ".//*[contains(@id,'srp_item')]";
	String lbAllTypesCss = ".srp_res_row_unit";
	//Wc2 change
	String tabStorageTypesCss = ".fg.top";
	String tbSearchBoxId = "srp_search";
	//String hlnkNeedHelpId = "tm_nh";
	String lbNeedHelpclass = ".halp";
	String hlnkStorageXpath = ".//*[@id='nav_starting_t']/span";
	String btnSearchXpath = ".//*[@id='srp_search_wrap']/a/img";
	String lbSelfStorageUnitsXpath = "//h1[@class='page_title']";//changed from .//h1[@class='page_title']
	

	// Storage Units
	String btnDistanceId = "srp_results_nav_distance-action";
	String lbDistanceXpath = ".//li[contains(@id,'srp_item')]/div/div[2]";
	String lbSearchPleaseWaitXpath = "//div[text()='Please wait...']";
	//String lbSizeCategoryXpath = ".//*[contains(@id,'srp_item')]/ul/li//div[contains(text(),'SMALL') or contains(text(),'MEDIUM') or contains(text(),'LARGE')]";
	String lbSizeCategoryXpath = ".//*[contains(@id,'srp_item')]/ul/li//div[contains(text(),'Small') or contains(text(),'Medium') or contains(text(),'Large')]";
	String btnAllContinueCss = ".srp_continue>img";
	String lbSpaceSizeCss = ".srp_label.srp_font_14";
	String lbSpacesSizeXpath = ".//div[contains(@class,'srp_res_clm srp_')]//div[contains(@class,'srp_v-space_3')]";
	String lbPriceCss = ".srp_label.alt-price";
	String lbPriceXpath = ".//div[contains(@class,'srp_res_clm srp_')]//div[contains(@class,'srp_label alt-price')]";
	String LbMessageXpath = ".//*[@id='srp_results_container']";
	String lbSpaceAddressCss = ".srp-address";
	String hlnkShowUnitsXpath = ".//li[@id=''srp_item_{0}'']//a[contains(text(),''{1}'')]";
	String lbSpaceRatingCss = ".rating";
	String lbSpaceReviewCountCss = ".smallreviewscount";
	String lbAllRowsOfPanelXpath = ".//li[@id=''srp_item_{0}'']/ul/li";
	String lbDimensionsXpath = ".//li[@id=''srp_item_0'']/ul/li[{0}]//div[@class=''srp_label srp_font_14'']";
	String lbCostXpath = ".//li[@id=''srp_item_0'']/ul/li[{0}]//div[@class=''srp_label alt-price'']";
	String imgUnitImageXpath = ".//li[@id=''srp_item_{0}'']//a[@class=''srp_results_item_hero_image'']/img";
	//String lbFirstAddressXpath = ".//*[@id='srp_item_0']/div/div[4]";
	String lbFirstAddressXpath =".//*[@id='srp_item_0']/ul/li[1]/div[2]/div[2]";
	//String lbSecondAddressXpath = ".//*[@id='srp_item_1']/div/div[4]";
	String lbSecondAddressXpath = ".//*[@id='srp_item_1']/ul/li[1]/div[2]/div[2]";
	String hLnkShowAllSmallUnitsXpath = ".//*[@id='srp_item_0']/ul/li[1]/div[4]/a[2]";

	// Vehicle Units
	String btnVehicleUnitsCss = ".ps-ui-tab.right";
	String btnUpto20Id = "srp_flt_20";
	String btnUpto35Id = "srp_flt_35";
	String btnUpto50Id = "srp_flt_50";
	String btnCoveredId = "srp_flt_cv";
	String btnUncoveredId = "srp_flt_uc";
	String btnEnclosedId = "srp_flt_en";
	String hlnkHideFiltersXpath = ".//*[@id='content']/div[5]/div[3]/div[1]/a";
	String hLnkShowFiltersXpath = ".//*[@id='content']/div[5]/div[3]/div[1]/a";
	String btnSelectSizeSelectUnitButtonsClass = "srp_filter_btn";
	String lbSizeCategoryVehicleXpath = ".//*[contains(@id,'srp_item')]/ul/li//div[contains(text(),'Small') or contains(text(),'Medium') or contains(text(),'Large')]";
	String lbMonthlyPrizeXpath = ".//*[contains(@id,'srp_item_1')]/ul/li/div/div[2]";
	String btnMonthlyPrizeId = "srp_results_nav_price-action";
	String hlnkViewSizeGuideXpath = ".//*[@id='content']//img[@src='/images/view_size_guide2.png']";
	String lbSizeCss = ".srp_list>li";
	
	//common
	
	String imgCommonPhotoCss = ".srp_results_item_hero_image>img";
	String imgCommonPhtXpath = "//a[@class='srp_results_item_hero_image']/img";
	String lbCommonAddrXpath = "//div[@class='srp-address']";
	String hlinkShowAllUnitsXpath=".//*[contains(@id, 'srp_item_')]//a[text()='Show all available units and info >']";
	String hlinkShowAllUnitsXpath1=".//*[contains(@id, 'srp_item_')]//a[text()='Show All Available Units and Info']";
	String hlinkShowAllViewLocationXpath=".//*[contains(@id, 'srp_item_')]//a[text()='View Location']";
	
	//Reservation Deposit
	String lbSRPDepositXpath="TBD";
	String ttSRPDepositXpath="TBD";
	String lbSRPDepositRequiredXpath="TBD";
	String imgDepositPopUpDisplayNoneXpath=".//*[@id='reservationdeposits_popup'][contains(@style,'none')]/img[@src='/images/reservationdeposits-popup.png']";
	String imgDepoPopXpath=".//*[@id='reservationdeposits_popup']/img[@src='/images/reservationdeposits-popup.png']";
	String imgCloseDepoPopUpId="textmessage_popup_x";
	
}
