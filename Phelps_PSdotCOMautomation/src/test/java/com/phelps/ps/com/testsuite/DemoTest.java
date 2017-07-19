package com.phelps.ps.com.testsuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.maven.shared.invoker.SystemOutHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.phelps.ps.com.autotest.utils.MSWordReader;
import com.phelps.ps.com.autotest.utils.UniqueId;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.autotest.web.CateredWebElement;
import com.phelps.ps.com.locators.PLPLocators;


public class DemoTest extends BasicTestSuite {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */

	public static void main(String[] args) throws NumberFormatException, IOException,Exception {
		
		Calendar c;
		 String moveInDate;
		 int tomorrowDate;
		 String dayOfMoveInDate;
		 
		 /*c = Calendar.getInstance();
		  c.add(Calendar.DAY_OF_MONTH, 4);
		  SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		  moveInDate = sdt.format(c.getTime());
		  dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));*/
		 // System.out.println(Calendar.getInstance().get(Calendar.MONTH)+1+"/"+"3"+"/"+Calendar.getInstance().get(Calendar.YEAR));
	
		 int stackIndex = 0;
			while (stackIndex < Thread.currentThread().getStackTrace().length) {
		 
		 stackIndex++;
			}
			System.out.println(Thread.currentThread().getStackTrace()[stackIndex-1].getClassName().substring(Thread.currentThread().getStackTrace()[stackIndex-1].getClassName().lastIndexOf('.') + 1));
		/*try{
			
		
		WebDriver driver=new FirefoxDriver();
		driver.get("https://wc2psstaging.phelpsagency.com");
		driver.findElement(By.cssSelector(".hpSearchInput"));
		System.out.println("test passed  as search box  found");
		}
		catch(Exception e){
			System.out.println("test failed as sear ch box not found");
		}*/
		/*List<Integer> price=new ArrayList<Integer>();
		price.add(74);
		price.add(21);
		price.add(80);
		String locator="((((//div[@class=''suggLocUnits''])[{0}])//span[@class=''suggLocUnitPriceV2''])[{1}])[text()=''${2}/mo'']";
		String finalString=MessageFormat.format(locator,1,2,price.get(2));
		System.out.println(finalString);
		*/
		
		//WebDriver driver=new FirefoxDriver();
		//driver.get("https://wc2psstaging.phelpsagency.com/storage-search-landing.aspx?location=90304");
		//String str=driver.findElement(By.xpath(".//*[@id='srp_item_0']//div[@class='srp-address']")).getText();
		/*String str="10100 La Cienega angeles";
		int index=str.indexOf(" ");
		int[] spacePos=new int[5];
		int i=0;
		while(index >= 0) {
			   System.out.println(index);
			   index = str.indexOf(" ", index+1);
			   spacePos[i]=index;
			   i++;
			}
		System.out.println(spacePos[1]);
		System.out.println(str.substring(0,spacePos[0]));
		System.out.println(str.substring(spacePos[0]));*/
		/*WebDriver driver=new FirefoxDriver();
		driver.get("https://www.publicstorage.com/illinois/self-storage-chicago-il/60605-self-storage/1443?lat=41.86350&lng=-87.62591&location=il");
		
		List<Integer> allUnitPrice=new ArrayList<Integer>();
		List<WebElement> allUnitElements;
		
		
		String[] singleSizePrices;
		
		switch("Small"){
		case "Small":
			allUnitElements=driver.findElements(By.xpath(PLPLocators.lbAllSmallPricesXpath));
			singleSizePrices=new String[allUnitElements.size()];
			//allUnitPrice=new int[allUnitElements.size()];
		for (int i=0;i<allUnitElements.size();i++){
			singleSizePrices[i]=allUnitElements.get(i).getText().replace("$", "");
			singleSizePrices[i]=singleSizePrices[i].replace("/mo.", "").trim();
			allUnitPrice.add(Integer.parseInt(singleSizePrices[i]));			
		}
		for(int price:allUnitPrice){
			System.out.println(price);
		}
		break;
		case "Medium":
			allUnitElements=driver.findElements(By.xpath(PLPLocators.lbAllMediumPricesXpath));
			singleSizePrices=new String[allUnitElements.size()];
			//allUnitPrice=new int[allUnitElements.size()];
			for (int i=0;i<allUnitElements.size();i++){
				singleSizePrices[i]=allUnitElements.get(i).getText().replace("$", "");
				singleSizePrices[i]=singleSizePrices[i].replace("/mo.", "").trim();
				allUnitPrice.add(Integer.parseInt(singleSizePrices[i]));	
			}
			for(int price:allUnitPrice){
				System.out.println(price);
			}
			break;
		case "Large":
			allUnitElements=driver.findElements(By.xpath(PLPLocators.lbAllLargePricesXpath));
			singleSizePrices=new String[allUnitElements.size()];
			//allUnitPrice=new int[allUnitElements.size()];
			for (int i=0;i<allUnitElements.size();i++){
				singleSizePrices[i]=allUnitElements.get(i).getText().replace("$", "");
				singleSizePrices[i]=singleSizePrices[i].replace("/mo.", "").trim();
				allUnitPrice.add(Integer.parseInt(singleSizePrices[i]));		
			}
			for(int price:allUnitPrice){
				System.out.println(price);
			}
		}
		
		Collections.sort(allUnitPrice);
		for(int price:allUnitPrice){
			System.out.println(price);
		}*/
		
		
		
		
		/*StringUtils.ordinalIndexOf(str," ",1);
		
		System.out.println("Address-"+ str);
		if(str.contains("\n"))
			str=str.replace("\n", " ");
		if(str.contains("Blvd"))
			str=str.replace("Blvd", "Bl");
		System.out.println("After replace "+ str);
		String[] splitAddress=str.split(",");*/
		
		/*int x,y,z;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a number");
		x=Integer.parseInt(br.readLine());
		z=x;
		System.out.println("Please enter exponential value");
		y=Integer.parseInt(br.readLine());
		for(int i=1;i<y;i++){
			x=x*z;
		}
		System.out.println("value is "+x);*/
		
		
		/*String phone="7656765465";
		String phone1,phone2,phone3;
		phone1=phone.substring(0, 3);
		System.out.println(" phone1"+ phone1+" phone"+phone);
		phone2=phone.substring(3, 6);
		System.out.println(" phone2"+ phone2+" phone"+phone);
		phone3=phone.substring(6, 10);
		System.out.println(" "+ phone1+" "+phone2+" "+phone3);*/
		/*WebDriver driver;
		
		FirefoxProfile profile = new ProfilesIni().getProfile("MuzferaFirefox"); profile.setPreference("geo.wifi.uri", "src/main/resources/geolocation.json");
		driver = new FirefoxDriver(profile); 
		driver.get("https://wc2psstaging.phelpsagency.com");*/
		/*File chromeDriver = new File("src/main/resources/chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--disable-geolocation");//--allow-geolocation
		options.addArguments("start-maximized");
		options.addArguments("test-type");
		options.addArguments("enable-strict-powerful-feature-restrictions");
		options.addArguments("disable-geolocation");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.get("https://www.publicstorage.com");*/
		
		 /*WebDriver driver=new FirefoxDriver();
		 driver.get("https://wc2psstaging.phelpsagency.com/storage-search-landing4.aspx?location=il&nm=1");
		
	
		List<WebElement> l=driver.findElements(By.xpath(".//*[contains(@id,'srp_item')]//div[@class='srp_label srp_uc'][text()='Small']/../..//div[@class='srp_label alt-price']"));
    List<String> allAmount=new ArrayList<String>();
    List<String> amount=new ArrayList<String>();
    List<WebElement> l1=new ArrayList<WebElement>();
    for(WebElement element: l){
    	String ss=element.getText().split("/")[0];
    	amount.add(ss);
    	allAmount.add(ss);
    }
    
    for(String s:allAmount){
    	l.add(driver.findElement(By.xpath("//*[@id='map_canvas']//span[text()='"+s+"']/..")));
    	
    }*/
   /* for(int i=0;i<l.size();i++){
    	l1.add(driver.findElement(By.xpath("//div[@class='map_bubble_msg'][text()='Units starting from "+amount.get(i)+"']/../div[@class='see_all']/a")));
    	 
    }*/
    
   /* Actions action = new Actions(driver);
		action.moveToElement(l.get(1)).build().perform();
    l.get(1).click();*/
    
   /* for(int i=0;i<l.size();i++){
    	l.get(i).click();
    	if(l1.get(i).isDisplayed()){
    	System.out.println("displayed");
    	}
    	}
   */ 
    
		
		
		
		
		/*if(z==++x && (z==++y) || (z==++z)){}
			System.out.println("x="+x+"  y="+ y+ " z= "+
		*/	
		
	
		
		/*
		 * WebDriver driver=new FirefoxDriver(); driver.get("https://gmail.com");
		 * driver.findElement(By.id("Email")).sendKeys("musfinaaz@gmail.com");
		 * driver.findElement(By.id("next")).click();
		 *//*	String paragraph="The city lies on both sides of the Mississippi river and is home to 20 lakes, including Lake Calhoun, which offers a park where residents can fish, walk, bike, and relax in the summertime. The city also has a host of entertainment options including professional football, baseball and basketball, galleries, museums like the Weisman Art Museum, zoos, and golf. In any one day you can experience a range of activities from boat tours (a local favorite) to gaming on a riverboat casino. Or, you can enjoy a historic tour of the city, sample from a range of craft beers at local breweries, and let loose at an amusement park. ";
		String[] words=paragraph.split("\\.");
		
		
		
		for(int i=0;i<words.length;i++)
		{
			System.out.println(" "+words[i]);
		}*/
		/*String uniString=new UniqueId(new UniqueId().id).charId+"@gmail.com";
		System.out.println(" email is "+uniString);
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,4 );
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		String moveInDate = sdt.format(c.getTime());
		String dayOfMoveInDate = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		System.out.println(moveInDate + " "+ dayOfMoveInDate);
		File chromeDriver = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		WebDriver driver=new ChromeDriver();
		

		driver.get("https://wc2psstaging.phelpsagency.com/");
		driver.findElement(By.id("at_rc")).click();
		driver.findElement(By.id("email_address")).sendKeys("muzfera.naaz@phelpsagency.com");
		driver.findElement(By.id("reservation_number")).sendKeys("126308207");
		//webAppDriver.enterTextToElementById("reservation_number", "126308207");
		driver.findElement(By.className("submit-btn")).click();
		driver.findElement(By.xpath("//img[@alt='Reserve Another Unit']")).click();
		List<WebElement> element=driver.findElements(By.xpath("//img[@src='/images/srp_continue2.jpg']"));
		element.get(1).click();
		if(driver.findElement(By.id("fname")).getAttribute("readOnly").equals("true"))
		System.out.println("the value of readonly is "+ driver.findElement(By.id("fname")).getAttribute("readOnly"));
		else
			System.out.println("not true");*/
		//WebDriver webAppDriver=new FirefoxDriver();
		// TODO Auto-generated method stub
		/*int countHyperlink = 0;
		List<String> cityDocPara = new ArrayList<String>();
		MSWordReader reader=new MSWordReader();
		cityDocPara=reader.readMyDocument("Orlando,_FL-272572");
		for (int i = 0; i < cityDocPara.size(); i++) {
			String eachPara = cityDocPara.get(i);
			
			int index = eachPara.indexOf("INCLUDEPICTURE");
			
			while (index != -1) {
				countHyperlink = countHyperlink + 1;
				eachPara = eachPara.substring(index + 1);
			    index = eachPara.indexOf("INCLUDEPICTURE");
			}
			
		}
		System.out.println("No of *is* in the input is : " + countHyperlink);*/
		/*Calendar c;
		String moveInDate;
		int tomorrowDate;
		String dayOfMoveInDate;
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 12);
		SimpleDateFormat sdt = new SimpleDateFormat("M/d/yyyy");
		moveInDate = sdt.format(c.getTime());
		
		String[] moveInMonth=moveInDate.split("/");
		String pendingHomeMoveInDate="December "+moveInMonth[1]+", 2015";
		System.out.println(""+pendingHomeMoveInDate);
		
		
		
		
		System.out.println(" move in date "+moveInDate);*/
		
	/*	
		final PackageManager pm = getPackageManager();
	//get a list of installed apps.
	List<ApplicationInfo> packages =  pm.getInstalledApplications(PackageManager.GET_META_DATA);

	for (ApplicationInfo packageInfo : packages) {
	    Log.d(TAG, "Installed package :" + packageInfo.packageName);
	    Log.d(TAG, "Apk file path:" + packageInfo.sourceDir);
	}*/
		/*try {
		webAppDriver.get("https://mail.google.com/");
		webAppDriver.findElement(By.id("Email")).sendKeys("musfinaaz@gmail.com");
		webAppDriver.findElement(By.id("next")).click();
		Thread.sleep(10*1000);
		webAppDriver.findElement(By.id("Passwd")).sendKeys("786@khuda");
		webAppDriver.findElement(By.id("signIn")).click();
		
			Thread.sleep(10*1000);
			
			WebElement element=webAppDriver.findElement(By.xpath("//a[@class='gb_b gb_4a gb_R']"));
			element.click();
			webAppDriver.findElement(By.xpath(".//*[@id='gb_71']")).click();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		

	}
}
