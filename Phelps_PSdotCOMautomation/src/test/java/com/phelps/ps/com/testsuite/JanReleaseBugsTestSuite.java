package com.phelps.ps.com.testsuite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phelps.ps.com.autotest.web.CateredWebElement;

public class JanReleaseBugsTestSuite extends BasicTestSuite {
	
	Calendar c;
	String moveInDate;
	String dayOfMoveInDate;
	List<CateredWebElement> allCoupons = new ArrayList<CateredWebElement>();
	@BeforeMethod
	public void generateMoveInDate() {
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 5);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		navigator.gotoDefaultSearchResults(searchByZip);
		webAppDriver.clickElementByXpath(".//*[@id='srp_item_0']//a[@class='show-all']");
		allCoupons = webAppDriver.findAllElementsByXpath(".//*[@id='srp_results']//img[@src='/images/srp_continue2.jpg']");

	}
	
	/*
	 * PS-465:Bug 2783 - Create Dynamic text based on Calendar reservation window
	 * 
	 */
	
	@Test
	public void dynamicMovinDateMessageTest(){
		
		
		
	}
	

}
