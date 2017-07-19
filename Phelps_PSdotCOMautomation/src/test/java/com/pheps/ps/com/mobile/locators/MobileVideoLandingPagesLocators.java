package com.pheps.ps.com.mobile.locators;

public interface MobileVideoLandingPagesLocators {
	
	//Video landing page
	
	String tbMobileSearchBoxVideoPageXpath =".//*[@id='vids']//input[@class='search-input']";
	String btnMobileFindId="searchButton";
	String ddVideoFileterId="filterer";
	String hlnkMobileRecentSearchlink1Xpath=".//*[@id='rcntSearches']/span[1]/a[2]";
	String hlnkMobileRecentSearchlink2Xpath=".//*[@id='rcntSearches']/span[2]/a[2]";
	String hlnkMobileRecentSearchlink3Xpath=".//*[@id='rcntSearches']/span[3]/a[2]";
	String imgMobilePromoXpath="//div[@class='promo']//img[@src='/Assets/images/videos/promo-1.gif']";
	
//Video individual landing pages
	
	String btnMobileDownloadTipsPDFXpath="//a[@class='btn'][text()='PDF TIP SHEET']";
	String iframeMobileVideoIndividualXpath="//iframe[@id='ytplayer']";
	String btnMobileVideoPauseXpath="//button[@class='ytp-play-button ytp-button'][@aria-label='Pause']";
	String btnMobileLargePlayCss=".ytp-large-play-button.ytp-button";
	
}
