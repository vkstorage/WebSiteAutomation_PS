package com.phelps.ps.com.locators;

/**
 * Basic home page
 * 
 * @author amodak
 * 
 */


public interface MelissaDataLocators {
	String lbSuggestedStandardizedAddressXpath =".//*[@class='melissa-validation-data-address-type'][text()='Suggested Standardized Address']";
	//String lbSuggestedStandardizedAddressXpath =".//div[@id='ValidateEmailPopup']//div[text()='Suggested Standardized Address']";
	String opSuggestedStandardizedAddressXpath ="(//input[@name='melAddr'])[1]";
	//String lbAddressEnteredXpath =".//div[@id='ValidateEmailPopup']//div[text()='Address Entered']";
	String lbAddressEnteredXpath =".//*[@class='melissa-validation-data-address-type'][text()='Address Entered']";
	String opAddressEnteredXpath ="(//input[@name='melAddr'])[2]";
	String btnSaveCompleteXpath = ".//div[@id='ValidateEmailPopup']//input[contains(@class,'btnContinue')]";
	String btnSaveCompleteCss=".melissa-validation-submit-button>a";
	String iframeMelissaDataPopupXpath = "//iframe[@class='cboxIframe']";
	String commonAddressXpath = ".//*[@class='melissa-validation-data-address-type'][text()='Suggested Standardized Address']/../../div[@class='melissa-validation-data-address']";
	//String commonAddressXpath = ".//div[@id='ValidateEmailPopup']//div[text()='Suggested Standardized Address']/../../div[@class='melAddress']";
	
	}
