package com.phelps.ps.com.locators;

/**
 * Basic home page
 * 
 * @author amodak
 * 
 */
public interface InsuranceLocators {

	public String lbMyStorageUnitsXpath = "//p[contains(text(),'My Storage Units')]";
	public String btnClickHereXpath = "//div[@id='insButton']/a";
	public String btnSubmitXpath = "//div[@id='submit-links']/a[1]";
	public String rdBtnForInsuranceAmtXpath = "//input[@class='insradio' and @data-amt='3000']";
	public String rBtnForInsuranceAmt4000Xpath = "//input[@class='insradio' and @data-amt='4000']";
	public String rBtnForInsuranceAmt5000Xpath = "//input[@class='insradio' and @data-amt='5000']";
	public String btnContinueId = "finish";
	public String lbMakePaymentXpath = "//p[contains(text(),'Make a Payment')]";
	public String btnClickHereInsuranceXpath = ".//*[@id='insButton']/a/img";
	
}
