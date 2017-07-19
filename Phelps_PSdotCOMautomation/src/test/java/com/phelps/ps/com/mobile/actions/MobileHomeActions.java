package com.phelps.ps.com.mobile.actions;

//import com.gargoylesoftware.htmlunit.WebAssert;
import com.phelps.ps.com.autotest.mobileweb.MobileWebDriver;
import com.pheps.ps.com.mobile.locators.MobileHomeLocators;

public class MobileHomeActions implements MobileHomeLocators{

	
MobileWebDriver mobileWebAppDriver;
	
	public MobileHomeActions(MobileWebDriver mobileWebAppDriver){
		this.mobileWebAppDriver=mobileWebAppDriver;
	}
	
	
//Burger Menu
	public void clickContactUs(){
		mobileWebAppDriver.clickElementById(hlnkContactUsId);
	}
	
	//Contact Us
	public void enterFullName(String fullName){
		mobileWebAppDriver.enterTextToElementById(tbFullNameId, fullName);
	}
	public void enterPhoneNumber(String phone){
		mobileWebAppDriver.enterTextToElementById(tbPhoneNumberId, phone);
	}
	public void enterEmai(String email){
		mobileWebAppDriver.enterTextToElementById(tbEmailAddressId, email);
	}
	
	public void enterMessage(String message){
		mobileWebAppDriver.enterTextToElementById(taMessageId, message);		
	}
	
	public void selectTopic(String topic){
		mobileWebAppDriver.selectByVisibleTextByLocatorId(ddTopicId, topic);
	}
	
	public void clickContactSubmit(){
		mobileWebAppDriver.clickElementById(hlnkContactSubmitId);
	}
	
	public void enterContactUsInfo(String fullName, String phone,String email,String topic,String message ){
		
		enterFullName(fullName);
		enterPhoneNumber(phone);
		enterEmai(email);
		selectTopic(topic);
		enterMessage(message);
		clickContactSubmit();
		
	}
}
