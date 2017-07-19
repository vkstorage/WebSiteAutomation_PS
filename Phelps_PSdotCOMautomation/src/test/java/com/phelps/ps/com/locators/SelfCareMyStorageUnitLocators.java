package com.phelps.ps.com.locators;

public interface SelfCareMyStorageUnitLocators {

	String btnReviewThisPropertyXpath = ".//input[@value=\"Review this property\"]";
	String btnNextCalloutXpath = ".//div[@class='promoNavNext']/img";
	String btnReviewThisPropertyCalloutXpath =".//*[@id='newsticker-selfcare']/ul/li[2]//input";
	String imgLogo = ".//*[@id='logo']/span/a/img";
	String btnContinueMoveOutXpath = ".//*[@id='autopaymsg']/input";
	
	/*
	 * Review Locators
	 */
	String tableXpath = ".//*[@id='aspnetForm']//table[contains(@class,'tblMyReservations')]/tbody/tr";
	String btnCommonPropertyReviewXpath = ".//*[@id=''aspnetForm'']//table[contains(@class,''tblMyReservations'')]/tbody/tr[{0}]//input[@value=''Review this property'']";
	
	
	String btnLocationDetailsXpath =".//*[@class='cboxElement']";
	
	String imgfullXpath = ".//*[@id='slideshow']/img";
	String imgThumb1PhotoXpath = ".//*[@id='slideshow']/div/a[1]/img";
	String imgThumb2PhotoXpath = ".//*[@id='slideshow']/div/a[2]/img";
	String imgThumb3PhotoXpath = ".//*[@id='slideshow']/div/a[3]/img";
	String imgThumb4PhotoXpath = ".//*[@id='slideshow']/div/a[4]/img";
	String imgThumb5PhotoXpath = ".//*[@id='slideshow']/div/a[5]/img";
	
	String btnViewFullDetailsXpath = ".//*[@id='aspnetForm']//table//tr[2]/td[2]/p[3]/a";
	String btnScheduleMoveOutXpath = ".//*[@id='aspnetForm']//table//td/a";
	String btnSaveXpath = ".//*[@id='btnSave']/a[1]/img";
	
	String btnBackXpath = ".//*[@id='container']//span[3]/a";
	
}
