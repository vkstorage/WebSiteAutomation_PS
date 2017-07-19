package com.phelps.ps.com.actions;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.SpanishSRPLocators;

public class SpanishSRPActions extends CommonActions implements SpanishSRPLocators {

	private CateredWebDriver webAppDriver;

	public SpanishSRPActions(CateredWebDriver webAppDriver) {
		this.webAppDriver = webAppDriver;
	}

	public void verifyPropertyImageSrc() {
		List<String> srcList = webAppDriver.getAttributeValueFromAllElementsByClassName(imgPropertyPhotoCommonClass, "src");
		List<String> addressList = webAppDriver.getTextFromAllElemetnsByClass(lbAddressClass);
		List<String> prefixList = new ArrayList<String>();
		List<String> imagesNameList = new ArrayList<String>();
		for (int i = 0; i < srcList.size(); i++) {
			if (addressList.get(i) != null) {
				if (!addressList.get(i).isEmpty()) {
					prefixList.add(convertAddressIntoImagePrefix(addressList.get(i)));
					int beginIndex = srcList.get(i).lastIndexOf("/") + 1;
					imagesNameList.add(srcList.get(i).substring(beginIndex));
				}
			}
		}
		for (int i = 0; i < imagesNameList.size(); i++) {
			if (!imagesNameList.get(i).equals("nolocation_photo_78.gif")) {
				String expectedImageName = prefixList.get(i) + "-exterior_1_map.jpg";
				if (!expectedImageName.endsWith(imagesNameList.get(i))) {
					String errorMessage = "Expected image name:" + expectedImageName + " but found:" + imagesNameList.get(i);
					Reporter.log(errorMessage);
					webAppDriver.captureScreenshot();
					throw new AssertionError(errorMessage);
				}
			}
		}
	}

	public void clickViewLocationDetailsLink() {
		//System.out.println(webAppDriver.findElementByClassName(hlinkViewLocationDetailsClass).getAttribute("href"));
		webAppDriver.clickElementByClassname(hlinkViewLocationDetailsClass);
		webAppDriver.switchtoiFrameByXpath("//iframe[@class='cboxIframe']");
		webAppDriver.verifyPresenceOfTextLocatedByXpath(".//*[@id='address_info']/h3", "Public Storage");
		webAppDriver.verifyPresenceOfTextInH3Tag("Public Storage");
	}
}
