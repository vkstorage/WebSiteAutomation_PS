package com.phelps.ps.com.actions;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.KnowledgeGraphLocators;

public class KnowledgeGraphActions implements KnowledgeGraphLocators {

	CateredWebDriver webAppDriver;

	public KnowledgeGraphActions(CateredWebDriver webAppDriver2) {
		this.webAppDriver = webAppDriver2;
	}

	public void clickExpandAll() {
		webAppDriver.clickElementByLinkText("Expand all");
	}

	public void clickCollapseAll() {
		webAppDriver.clickElementByLinkText("Collapse all");
	}

	public void verifyPackingExpandAll() {
		clickExpandAll();
		webAppDriver.relax(500);

		webAppDriver.verifyAttributeValueByXpath(acrPackingStep1Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep1Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep2Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep2text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep3Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep3Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep4Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep4Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep5Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep5Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep6Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep6Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep7Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep7Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep8Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep8Text);
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep9Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrPackingStep9Text);
	}
	
	public void verifyStorageExpandAll() {
		clickExpandAll();
		webAppDriver.relax(500);
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep1Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrStorageStep1Text);
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep2Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrStorageStep2text);
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep3Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrStorageStep3Text);
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep4Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrStorageStep4Text);
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep5Xpath, "class", "accordion-item-header-toggle");
		webAppDriver.verifyPresenceOfTextInDivTagText(acrStorageStep5Text);
	}


	public void verifyPackingCollapseAll() {
		clickCollapseAll();
		webAppDriver.relax(500);

		webAppDriver.verifyAttributeValueByXpath(acrPackingStep1Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep2Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep3Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep4Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep5Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep6Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep7Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep8Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrPackingStep9Xpath, "class", "accordion-item-header-toggle collapsed");
	}

	public void verifyStorageCollapseAll() {
		clickCollapseAll();
		webAppDriver.relax(500);
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep1Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep2Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep3Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep4Xpath, "class", "accordion-item-header-toggle collapsed");
		webAppDriver.verifyAttributeValueByXpath(acrStorageStep5Xpath, "class", "accordion-item-header-toggle collapsed");
	}
}
