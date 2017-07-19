package com.phelps.ps.com.testsuite;

import org.testng.annotations.Test;

import com.phelps.ps.com.actions.KnowledgeGraphActions;
import com.phelps.ps.com.locators.KnowledgeGraphLocators;
import com.phelps.ps.com.nevigation.Navigator;

public class KnowledgeGraphTestSuite extends BasicTestSuite implements KnowledgeGraphLocators {

	@Test
	public void verifyDesignFunctionlityKnowledgeGraphTest(){
		
		navigator.gotoPackingAndStorageTips();
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack like a Pro");
		webAppDriver.verifyH2TagTextContains("Learn how to make the most of your storage space in 9 easy steps.");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep1Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep2Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep3Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep4Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep5Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep6Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep7Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep8Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep9Xpath, "class", "accordion-item-header-toggle collapsed");
		knowledgeGraphActions.verifyPackingExpandAll();
		knowledgeGraphActions.verifyPackingCollapseAll();
	}
	
	@Test
	public void verifyDesignFunctionalityStorageTipsTest(){
		navigator.gotoStorageTips();
		webAppDriver.verifyPresenceOfTextInH1Tag("How to Pack a Storage Unit");
		webAppDriver.verifyPresenceOfTextInH2Tag("Learn how to Maximize the Space in Your Storage Unit in 5 Easy Steps.");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep1Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep2Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep3Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep4Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep5Xpath, "class", "accordion-item-header-toggle collapsed");
		knowledgeGraphActions.verifyStorageExpandAll();
		knowledgeGraphActions.verifyStorageCollapseAll();
	}
	
	
}
