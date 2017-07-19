package com.phelps.ps.com.locators;

public interface PLPLocators {

	String lbSpaceAddressId = "address";
	String lbSpaceStreetAddressXpath="//*[@id='address']/span[@itemprop='streetAddress']";
	String lbSpaceAddressLocalityXpath="//*[@id='address']/span[@itemprop='addressLocality']";
	String lbSpaceAddressRegionXpath="//*[@id='address']/span[@itemprop='addressRegion']";
	String lbSpacePostalCodeXpath="//*[@id='address']/span[@itemprop='postalCode']";
	String lbAllFirstColumnsXpath = ".//ul[@id='srp_results']/li/div[@class='srp_res_clm srp_clm160']";
	String lbAllDimensionsXpath = ".//ul[@id=''srp_results'']/li[{0}]//div[@class=''srp_label srp_font_14'']";
	String lbAllCostsXpath = ".//ul[@id=''srp_results'']/li[{0}]//div[@class=''srp_label alt-price'']";
	String lbAllReviewsXpath = ".//div[@id='plp_reviews']//div[@class='googlereview_name']";
	String lbAddressInReviewsXpath = ".//div[@id='plp_reviews']/h2";
	String spnRatingInReviewCss = "#plp_reviews>span";
	String tabAllTabsId = "plp-tab-{0}";
	String lbAllDimensionsCss = ".srp_label.plp";
	String btnSmallUnitsId = "srp_flt_su";
	String btnLargeUnitsId = "srp_flt_lu";
	String btnMediumUnitsId = "srp_flt_mu";
	String btnAllUnitsId = "srp_flt_bv";
	String hlnkSizeGuideXpath = ".//div[@class='plp_clm plp_clm100']/div[1]/a";
	String hlnkVirtualTourId = "insidetour";
	String tbSearchCriteriaId = "srp_search";
	String lbPleaseWaitXpath = "//div[text()='Please wait...']";
	String imgXpath = ".//*[@id='plp-prop-img']";
	String imgThumbnailOneXpath = ".//*[@id='content']/div[5]/div[2]/ul/li[1]/div";
	String imgThumbnailTwoXpath = ".//*[@id='content']/div[5]/div[2]/ul/li[2]/div";
	String imgThumbnailThreeXpath = ".//*[@id='content']/div[5]/div[2]/ul/li[3]/div";
	String imgThumbnailFourXpath = ".//*[@id='content']/div[5]/div[2]/ul/li[4]/div";
	String imgThumbnailFiveXpath = ".//*[@id='content']/div[5]/div[2]/ul/li[5]/div";
	String imgThumbnailOneECIXpath = ".//*[@id='slideshow']/div/a[1]/img";
	String imgSpanishXpath = ".//*[@id='slideshow']/img";
	String imgThumbnailOneSpanishXpath = ".//*[@id='slideshow']/div/a[1]/img";
	String imgThumbnailTwoSpanishXpath = ".//*[@id='slideshow']/div/a[2]/img";
	String imgThumbnailThreeSpanishXpath = ".//*[@id='slideshow']/div/a[3]/img";
	String imgThumbnailFourSpanishXpath = ".//*[@id='slideshow']/div/a[4]/img";
	String imgThumbnailFiveSpanishXpath = ".//*[@id='slideshow']/div/a[5]/img";
	String hlnkStorageUnitTabId="plp-tab-storage";
  String btnChangeUnitXpath=".//*[@id='confirm_lnk']/img";

	// Storage units tab
	String hlnkSizeGuideStorageTabXpath = ".//div[@class='srp_filters_content_hideable hf_storage']//img[contains(@src,'view_size_guide2')]";
	String btnSearchAgainCss = ".submit";
	String tbSearchAgainCss = ".text_field.rounded.query";
	String mapId = "map_outer";
	String hlnkStreetViewId = "lbl_street_view";
	String hlnkVirtualTourStorageTabXpath =".//div[@id='srp_right_col_innerWrap']//a[3]";
	String btnClimateControlledId = "srp_flt_ufcc";
	String btnDriveUpAccessId = "srp_flt_ufdu";
	String lbAllDImensionsStorageTabCss = ".srp_label.srp_font_14";
	String lbAllDetailsStorageTabCss = ".srp_list";
	String lbAllPricesStorageTabCss = ".srp_label.alt-price";
	String btnContinueButtonStorageUnitCss = ".srp_continue>img";
	String lbAddressStorageTabId = "address_to";
	String lbClimateControlInUnitFeaturesXpath = ".//ul[@class='srp_list']/li[1]";
	String lbNearbyDistanceCss = ".nearby_distance";
	String lbAddressFromId = "address_from";
	String btnGetDirectionsXpath = ".//*[@id='directions_submitbutton']/img";
	String btnAllContinueXpath=".//*[@id='srp_results']//img[@src='/images/srp-cont-new-80.png']";//"//".//*[@id='srp_results']//img[@src='/images/srp_continue2.jpg']";//
	//For Home Page Refresh Recommended Location
	String lbAllSmallUnitStorageTabXpath="//div[@class='srp_label plp'][text()='Small']";
	String lbAllMediumUnitStorageTabXpath="//div[@class='srp_label plp'][text()='Medium']";
	String lbAllLargeUnitStorageTabXpath="//div[@class='srp_label plp'][text()='Large']";
	String lbAllSmallPricesXpath="//div[@class='srp_label plp'][text()='Small']/../..//div[@class='srp_label alt-price']";
	String lbAllMediumPricesXpath="//div[@class='srp_label plp'][text()='Medium']/../..//div[@class='srp_label alt-price']";
	String lbAllLargePricesXpath="//div[@class='srp_label plp'][text()='Large']/../..//div[@class='srp_label alt-price']";
	

	// Directions units tab
	String imgAXpath = ".//*[@id='adp-placemark']/tbody/tr/td[1]/img";
	String imgBXpath = ".//*[@id='directions_list']/div/div[3]/div[3]/table/tbody/tr/td[1]/img";
	String lbNumberOfRowsOfDirectionsXpath = ".//*[@id='directions_list']/div/div[3]/div[2]/table/tbody/tr/td[3]";

	// Vehicles unit tab
	String btnSizeUpto20InchId = "srp_flt_20";
	String btnSizeUpto35InchId = "srp_flt_35";
	String btnSizeUpto50InchId = "srp_flt_50";
	String btnUnitFeatureCoveredId = "srp_flt_cv";
	String btnUnitFeatureUncoveredId = "srp_flt_uc";
	String btnUnitFeatureEnclosedId = "srp_flt_en";
	
	String btnOHXpath=".//*[@id='map_us_overlay']/div[13]";

	// aug release
	
	
	String tabFaqXpath = ".//*[@id='city-tab-faqs']";
	
	//October release
	String propertyReviewStarXpath=".//div[@class='plp_location_info']//span[@class='ratingstars']";
	String lblPLPReviewTabReviewStartXpath=".//*[@id='plp_reviews']/span[2]/span";
	
	//December Release
	String lbStorageUnitAllUnitSizeXpath=".//*[@id='srp_results']//div[@class='srp_label srp_font_14']";
	String lbStorageUnitAllUnitPriceXpath=".//*[@id='srp_results']//div[@class='srp_label alt-price']";
	
//Reservation Deposit
	String lbDepositTextXpath=".//*[@id='srp_results']//div[@class='deposit-text']";
	String lbDepositXpath=".//*[@id='srp_results']//div[@class='deposit-required']//div[contains(.,'Deposit')]";
	String lbRequiredTextxpath=".//*[@id='srp_results']//div[@class='deposit-required']//div[contains(.,'Required')]";
    String imgDepositToolTipXpath=".//img[@src='/images/icon_question_orange.png']";
}
