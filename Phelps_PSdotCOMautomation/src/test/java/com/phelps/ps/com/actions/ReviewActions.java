package com.phelps.ps.com.actions;

import java.util.Arrays;

import org.openqa.selenium.WebElement;

import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.locators.HomeLocators;
import com.phelps.ps.com.locators.SelfCareMyStorageUnitLocators;
import com.phelps.ps.com.locators.SelfcareSummaryLocators;

public class ReviewActions implements SelfcareSummaryLocators, SelfCareMyStorageUnitLocators, HomeLocators {

	CateredWebDriver webAppDriver;

	public ReviewActions(CateredWebDriver driver) {
		webAppDriver = driver;
	}

	public String getReviewWindowURL() {
		String newWindowURL="";
		int start,end;
		WebElement reviewPropertyBtn = webAppDriver.findElementByXpath(btnReviewThisPropertyHomeXpath);
		String page = webAppDriver.getPageSource();
		String functionBody="";
		String lines[] = page.split("\n");
		for(int i=0;i<lines.length;i++)
		{
			if(lines[i].contains("function openReviewsModal")){
				start=i;
			functionBody=functionBody+lines[i];
			for(int j=i;;j++){
				if(lines[j].contains("</script>")){
					end=j;
					break;
				}
				else
				{
					functionBody=functionBody+lines[j];	
				}
			}
			String substring = functionBody.substring(functionBody.indexOf("https:"),functionBody.indexOf("iframe:"));
			substring=substring.trim();
			substring=substring.substring(0, substring.length()-2);
			System.out.println(substring);
			newWindowURL= substring;
			
			}
		}
		return newWindowURL;
	}
	
	public void checkReviewObjects(){
		webAppDriver.verifyPresenceOfTextInDivTagText("Choose where you would like to write your review:");
		webAppDriver.verifyPresenceOfTextInSpanTag("Yelp Reviewing Instructions:");
		webAppDriver.verifyPresenceOfTextInSpanTag("Google+ Reviewing Instructions:");
	}
	
	public int getTotalNumberOfProperties(String xpath){
		
		return webAppDriver.findAllElementsByXpath(tableXpath).size()-1;
		
		}

}