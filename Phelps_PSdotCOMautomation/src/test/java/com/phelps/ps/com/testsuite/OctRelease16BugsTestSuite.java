package com.phelps.ps.com.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OctRelease16BugsTestSuite extends BasicTestSuite {
	
	@Test
	public void verifyBug3236(){
		WebDriverWait wait = new WebDriverWait(webAppDriver, 30);
		
		navigator.gotoDefaultSearchResults("90230");
		webAppDriver.clickElementByXpath(".//*[@id='map_canvas']//div[@title='Zoom in']/div[1]/img");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='map_overlay'][@style='display: none;']/div[text()='Please wait...']")));
		webAppDriver.clickElementByXpath(".//*[@id='map_canvas']//div[@title='Zoom in']/div[1]/img");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='map_overlay'][@style='display: none;']/div[text()='Please wait...']")));
		webAppDriver.clickElementByXpath(".//*[@id='map_canvas']//div[@title='Zoom in']/div[1]/img");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='map_overlay'][@style='display: none;']/div[text()='Please wait...']")));
		webAppDriver.clickElementByXpath(".//*[@id='map_canvas']//div[@title='Zoom in']/div[1]/img");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='map_overlay'][@style='display: none;']/div[text()='Please wait...']")));
	}

}
