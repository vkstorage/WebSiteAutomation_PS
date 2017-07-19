package com.phelps.ps.com.locators;

/**
 * Basic home page
 * 
 * @author amodak
 * 
 */
public interface SelfStorageLocators {


	String lbHowSelfStorageWorksCls = "about-title";
	String lbFindPublicStorageLocationsCls = "find_locations";
	String lbStoragemadeEasyTitleXpath=".//*[@id='home_overlay']//h1";
	
	String lbHowSelfStorageWorksPageVerificationXpath = "//h1[text()='Self Storage: Reclaim Your Space']";
	String lbBusinessStorageWorksPageVerificationXpath = "//h1[text()='Business Storage Solutions: Extend Your Office']";
	String lbVehicleStorageWorksPageVerificationXpath = "//h1[text()='Need an Extra Garage? We've Got The Solution.']";
	
	//Storage For moving Tab
	String tbSearchStorageSelfStorageId = "location-moving";
	//Storage For Travelling 
	String lnkTravellingeStorageTabXpath = "//div[contains(text(),'Storage for Traveling/Deployment')]";
	String tbSearchTravellingTabId = "location-trav";
	//Storage for College
	String lnkCollegeStorageTabXpath = "//div[contains(text(),'College Storage')]";
	String tbSearchCollegeStorageTabId ="location-coll";
	//Storage for Extra room
	String lnkExtraRoomTabXpath = "//div[contains(text(),'Extra Room')]";
	String tbSearchExtraRoomTabId = "location-xtra";
	
	//Pharmaceutical Storage
	String tbPharmaStorageId = "location-pharm";
	//Construction Storage
	String tbConstructionStorageId ="location-con";
	String lnkConstructionStorageTabXpath = "//div[contains(text(),'Construction Storage')]";
	//Real estate storage
	String tbRealEstateStorageId ="location-estate";
	String lnkRealEstateStorageTabXpath = "//div[contains(text(),'Real Estate Storage')]";
	//Retail Storage
	String tbRetailStorageId ="location-inv";
	String lnkRetailStorageTabXpath = "//div[contains(text(),'Retail Storage')]";
	//Archive Storage
	String tbArchiveStorageId = "location-arch";
	String lnkArchiveStorageTabXpath = "//div[contains(text(),'Archive Storage')]";
	//Office Storage
	String tbOfficeStorageId = "location-other";
	String lnkOfficeStorageTabXpath = "//div[contains(text(),'Office Storage')]";
	
	//Car Storage
	String tbCarStorageId = "location-car";
	//Trailer Storage
	String tbTrailerStorageId = "location-rv";
	String lnkTrailerStorageTabXpath = "//div[contains(text(),'Trailer or RV Storage')]";
	//Boat Storage
	String tbBoatStorageId = "location-boat";
	String lnkBoatStorageTabXpath = "//div[contains(text(),'Boat Storage')]";
	
	//Motor Cycle Storage
	String tbMotorCycleStorageId = "location-motor";
	String lnkMotorcycleStorageXpath = "//div[contains(text(),'ATV or Motorcycle Storage')]";
	
	

}
