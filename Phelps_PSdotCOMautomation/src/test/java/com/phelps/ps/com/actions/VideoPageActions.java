package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.SearchResultsPageLocators;
import com.phelps.ps.com.locators.VideoLandingPageLocators;

public class VideoPageActions implements VideoLandingPageLocators,SearchResultsPageLocators {

	CateredWebDriver webAppDriver;
	CommonActions commonActions;

	public VideoPageActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
		commonActions = new CommonActions();
	}
	
	public void enterSearchText(String searchText){
		webAppDriver.enterTextToElementByXpath(tbSearchBoxVideoPageXpath, searchText);
		
	}
	
	public void clickOnFind(){
		webAppDriver.clickElementById(btnFindId);
		webAppDriver.checkTextInListByCss(tabStorageTypesCss, "Storage Units");
	}
	
	public void searchTest(String searchText){
		enterSearchText(searchText);
		clickOnFind();
	}
}
