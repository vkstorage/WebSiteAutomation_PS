package com.phelps.ps.com.locators;

public interface VideoLandingPageLocators {
	
	String tbSearchBoxVideoPageXpath=".//*[@id='container']//input[@class='search-input']";
	String btnFindId="searchButton";
	String iFrameVideoXpath="//iframe[@id='video']";
	String imgPromoXpath="//div[@class='rcol']//img[@src='/images/videos/first-month-promo.png']";
	String imgSizeGuideiconXpath="//div[@class='rhcItem']//img[@src='/images/videos/rhc-size-guide.png']";
	String imgPackageAndStorageTipsIconsXpath="//div[@class='rhcItem']//img[@src='/images/videos/rhc-tips.png']";
	String imgHowStorageWorksIconXpath="//div[@class='rhcItem']//img[@src='/images/videos/rhc-how-storage-works.png']";
	String hlinkLearnMoreSizeGuideXpath="//span[text()='View our Size Guide']";//to be changed later
	String hlinkLearnMorePackageAndStorageTipsXpath="//span[text()='Find our tips here']";//to be changed later
	String hlinkLearnMoreHowStorageWorksXpath="//span[text()='Learn more']";
	String btnSizeGuideLookInsideId="look";
	String hlnkHowSelfStorageStartingOutId="starting-out-tab";
	String hlnkRecentSearchlink1Xpath=".//*[@id='rcntSearches']/span[1]/a[2]";
	String hlnkRecentSearchlink2Xpath=".//*[@id='rcntSearches']/span[2]/a[2]";
	String hlnkRecentSearchlink3Xpath=".//*[@id='rcntSearches']/span[3]/a[2]";
	//Video individual landing pages
	
	String btnDownloadTipsPDFXpath="//a[@class='btn'][text()='PDF TIP SHEET']";
	String iframeVideoIndividualXpath="//iframe[@id='ytplayer']";
	String btnVideoPauseXpath="//button[@class='ytp-play-button ytp-button'][@aria-label='Pause']";
	

}
