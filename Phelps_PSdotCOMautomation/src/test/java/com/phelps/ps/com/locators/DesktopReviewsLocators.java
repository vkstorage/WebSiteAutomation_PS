package com.phelps.ps.com.locators;

public interface DesktopReviewsLocators {
	String tbPropertyID_1Id="propertyid1";
	String tbPropertyID_2Id="propertyid2";
	String tbPropertyID_3Id="propertyid3";
	String tbPropertyID_4Id="propertyid4";
	String tbPropertyID_5Id="propertyid5";
	String iFramexPath="//iframe[@class='cboxIframe']";
	String linkGoogleReviewxPath="//a[@class='googlelink']";
	String linkYelpReviewLessThanTenxPath="//img[@class='square yelplink']/..";
	String linkGoogleReviewLessThanTenXpath="//img[@class='square googlelink']/..";
	
	String linkGetYelpAppXpath=".//*[@id='fullscreen-pitch']/section[@class='pitch-content']/div[@class='text-centered']/a[text()='Open in the Yelp App']";
	String menuButtonXpath="//android.widget.ImageButton[contains(@resource-id,'document_menu_button']";
  String menuButtonId="com.android.chrome:id/url_bar";//com.android.chrome:id/document_menu_button";

}
