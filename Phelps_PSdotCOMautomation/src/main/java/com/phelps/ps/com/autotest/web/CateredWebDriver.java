package com.phelps.ps.com.autotest.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.utils.VerificationStatus;
//import com.gargoylesoftware.htmlunit.WebAssert;
import com.phelps.ps.com.autotest.utils.BrowserType;
import com.phelps.ps.com.autotest.utils.IConfigProperties;
import com.phelps.ps.com.autotest.utils.PropertyLoader;
import com.phelps.ps.com.autotest.utils.UniqueId;

public class CateredWebDriver implements WebDriver, IConfigProperties {

	final static Logger logger = Logger.getLogger(CateredWebDriver.class);

	protected WebDriver driver;

	public CateredWebDriver(BrowserType type) {
		driver = initiateDriver(type.toString());

		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("devpsstaging", "30");
		m1.put("psstaging", "30");
		m1.put("test-web", "15");
		m1.put("stage-web", "30");
		m1.put("production", "30");
	}

	public CateredWebDriver() {
		driver = initiateDriver(browserType);
	}

	public CateredWebDriver(String arg) {

	}

	private WebDriver initiateDriver(String browserType) {
		switch (browserType) {
		case "firefox":
			//System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			/*
			 * DesiredCapabilities capabilitiesChrome = DesiredCapabilities.chrome();
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("test-type");
			 * capabilitiesChrome.setCapability(ChromeOptions.CAPABILITY, options);
			 */
			
			/*ChromeOptions options = new ChromeOptions();
			options.addArguments("-incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
			File chromeDriver = new File("src/main/resources/chromedriver.exe");
			logger.info(chromeDriver.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			driver = new ChromeDriver();
			//driver = new ChromeDriver(capabilities);


			break;
		case "IE":
			File ieDriver = new File("src/main/resources/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
			DesiredCapabilities capabilitiesIE = new DesiredCapabilities();
			capabilitiesIE.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(capabilitiesIE);
			break;
		default:
			System.err.println("Please enter proper browser name");
		}
		driver.manage().window().maximize();
		return driver;
	}

	public boolean verifyElementText(By by, String text) {
		boolean result = false;
		String actualText = "";
		int attempt = 0;
		CateredWebElement webElment = findElement(by);
		while (!result && attempt <= 5) {
			try {
				attempt++;
				WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime / 5, 500);
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				// Adding support for textarea
				if (webElment.getTagName().equalsIgnoreCase("input") || webElment.getTagName().equalsIgnoreCase("textarea")) {
					actualText = webElment.getAttribute("value");
				} else {
					actualText = webElment.readInnerText();
				}
			} catch (Exception e) {
				System.err.println("attempt " + attempt + "...");
				if (attempt >= 5) {
					Reporter.log("Unable to get the text by locator " + by.toString());
					captureScreenshot();
					throw new WebDriverException(e);
				}
			}
			result = actualText.trim().equals(text);
		}
		if (result) {
			return true;
		} else {
			Reporter.log("Expected: \"" + text + "\" but found: \"" + actualText + "\"");
			/*
			 * ((JavascriptExecutor) driver).executeScript(
			 * "arguments[0].setAttribute('style', arguments[1]);" , webElment,
			 * "color: yellow; border: 2px solid yellow;");
			 */
			captureScreenshot();
			throw new AssertionError("Expected: \"" + text + "\" but found: \"" + actualText + "\"" + "\n"
					+ Thread.currentThread().getStackTrace().toString());
		}
	}

	/**
	 * To check the given text contains by the element located by the locator
	 * 
	 * @param by
	 * @param text
	 * @return
	 */
	public boolean verifyElementTextContains(By by, String text) {
		return verifyElementTextContains(by, text, false);
	}

	/**
	 * To check the given text contains by the element located by the locator
	 * 
	 * @param by
	 * @param text
	 * @return
	 */
	public boolean verifyElementTextContains(By by, String text, boolean noScreenshot) {
		boolean result = false;
		String actualText = "";
		int attempt = 0;
		CateredWebElement webElment = findElement(by, noScreenshot);
		while (!result && attempt <= 5) {
			try {
				webElment = findElement(by, noScreenshot);
				attempt++;
				WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				if (webElment.getTagName().equalsIgnoreCase("input") || webElment.getTagName().equalsIgnoreCase("textarea")) {
					actualText = webElment.getAttribute("value");
				} else {
					actualText = webElment.readInnerText();
				}
			} catch (StaleElementReferenceException statEleRefExp) {

				if (attempt > 5) {
					webElment = findElement(by, noScreenshot);
					Reporter.log("Unable to get the text by locator " + by.toString());
					captureScreenshot();
					throw statEleRefExp;
				}
			} catch (Exception e) {
				System.err.println("attempt " + attempt + "...");
				if (attempt > 5) {
					Reporter.log("Unable to get the text by locator " + by.toString());
					captureScreenshot();
					throw new WebDriverException(e);
				}
			}
			result = actualText.contains(text);
		}
		if (result) {
			return true;
		} else {
			if (!noScreenshot) {
				Reporter.log("\"" + text + "\" not found in \"" + actualText + "\"");
				captureScreenshot();
			}
			throw new AssertionError("\"" + text + "\" not found in \"" + actualText + "\"" + "\n"
					+ Thread.currentThread().getStackTrace().toString());
		}
	}

	/**
	 * Captures the screenshot
	 */
	public void captureScreenshot() {
		String outputPath = null;
		// if tests are running by maven then
		if (!PropertyLoader.loadProperty("output.path").get().contains("timestamp")) {
			outputPath = PropertyLoader.loadProperty("output.path").get();
		} else {
			// It is running by main function
			// outputPath = RunSizerTests.outputPath;
		}
		String screenShotPath = outputPath + "\\ScreenShots\\";
		String fileName = generateFileName() + ".jpg";
		// Take the screenshot

		File scrFile = ((TakesScreenshot) (this.driver)).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(screenShotPath + fileName));
			Reporter.log("<br> Module name: " + getCurrentTestClassName() + "<br>");
			Reporter.log(" Refer to <a href=\"ScreenShots/" + fileName + "\" target = \"_blank\"><img src=\"ScreenShots/" + fileName
					+ "\" width=\"50\" height=\"50\"></a><br>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates the filename for screenshot
	 * 
	 * @return
	 */
	private String generateFileName() {
		return new UniqueId().id + "_" + getCurrentTestClassName() + "_" + getCurrentTestMethodName() + "_"
				+ getCurrentTestMethodLineNumber();
	}

	/**
	 * This will return the current test method name it is executing
	 * 
	 * @return String Test case name
	 */
	public String getCurrentTestMethodName() {
		int stackIndex = 0;
		while (stackIndex < Thread.currentThread().getStackTrace().length) {
			if (Thread.currentThread().getStackTrace()[stackIndex].getMethodName().endsWith("Test")
					&& Thread.currentThread().getStackTrace()[stackIndex].getClassName().startsWith(testPackageName)) {
				break;
			}
			stackIndex++;
		}
		return Thread.currentThread().getStackTrace()[stackIndex].getMethodName();
	}

	/**
	 * This will return the current test class name it is executing
	 * 
	 * @return String test class name
	 */
	public String getCurrentTestClassName() {
		int stackIndex = 0;
		while (stackIndex < Thread.currentThread().getStackTrace().length) {
			if (Thread.currentThread().getStackTrace()[stackIndex].getMethodName().endsWith("Test")
					&& Thread.currentThread().getStackTrace()[stackIndex].getClassName().startsWith(testPackageName)) {
				break;
			}
			stackIndex++;
		}
		//return Thread.currentThread().getStackTrace()[stackIndex].getClassName();
		return Thread.currentThread().getStackTrace()[stackIndex].getClassName().substring(Thread.currentThread().getStackTrace()[stackIndex].getClassName().lastIndexOf('.') + 1);
	}

	/**
	 * This will return the current test method line number it is executing
	 * 
	 * @return int: Test method line number
	 */
	public int getCurrentTestMethodLineNumber() {
		int stackIndex = 0;
		while (stackIndex < Thread.currentThread().getStackTrace().length) {
			if (Thread.currentThread().getStackTrace()[stackIndex].getMethodName().endsWith("Test")
					&& Thread.currentThread().getStackTrace()[stackIndex].getClassName().startsWith(testPackageName)) {
				break;
			}
			stackIndex++;
		}
		return Thread.currentThread().getStackTrace()[stackIndex].getLineNumber();
	}

	/**
	 * Webdriver close
	 */
	@Override
	public void close() {
		driver.close();
	}

	/**
	 * Find element by the given locator after proper wait
	 */
	@Override
	public CateredWebElement findElement(final By locator) {
		return findElement(locator, false);
	}

	public CateredWebElement findElement(final By locator, boolean noLogsAndScreenshot) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			throw error;
		}
		WebElement appElement = null;
		try {
			appElement = (new WebDriverWait(driver, maxWaitTime)).until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Throwable te) {
			if (!noLogsAndScreenshot) {
				Reporter.log("Unable to find the element by locator: " + locator.toString() + " within " + maxWaitTime + " secs");
				captureScreenshot();
			}
			throw new TimeoutException(te);
		}
		scrollIntoViewElement(appElement);
		return new CateredWebElement(this, locator, appElement);
	}

	/**
	 * Find element by the given locator after proper wait
	 */
	public CateredWebElement findElement(final By locator, long waitTime) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			throw error;
		}
		WebElement appElement = null;
		try {
			appElement = (new WebDriverWait(driver, waitTime)).until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Throwable te) {
			Reporter.log("Unable to find the element by locator: " + locator.toString() + " within " + maxWaitTime + " secs");
			captureScreenshot();
			throw new TimeoutException(te);
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", appElement);
		return new CateredWebElement(this, locator, appElement);
	}

	/**
	 * Find element by ID
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementById(String id) {
		return findElement(By.id(id));
	}

	/**
	 * Find element by Name
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByName(String name) {
		return findElement(By.name(name));
	}

	/**
	 * Find element by css selector
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByCSS(String css) {
		return findElement(By.cssSelector(css));
	}

	/**
	 * Find element by Xpath
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByXpath(String xpath) {
		return findElement(By.xpath(xpath));
	}

	/**
	 * Find element by Xpath
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByXpath(String xpath, long waitTime) {
		return findElement(By.xpath(xpath), waitTime);
	}

	/**
	 * Find element by Class name
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByClassName(String className) {
		return findElement(By.className(className));
	}

	/**
	 * Find element by Partial LinkText
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByPartialLinkText(String linkText) {
		return findElement(By.partialLinkText(linkText));
	}

	/**
	 * Find element by LinkText
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByLinkText(String linkText) {
		return findElement(By.linkText(linkText));
	}

	/**
	 * Find element by Tag name
	 * 
	 * @param id
	 * @return
	 */
	public CateredWebElement findElementByTagName(String tagName) {
		return findElement(By.tagName(tagName));
	}

	/**
	 * It verifies the element with given locator is a button and it is visible
	 * 
	 * @param id
	 *          : Locator
	 */
	public void verifyElementIsButtonById(String id) {
		CateredWebElement element = findElementById(id);
		String tagName = element.getTagName();
		if (!tagName.equals("button")) {
			Reporter.log("Element with ID " + "\"" + id + "\" " + "is not a button. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with ID " + "\"" + id + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies the element with given locator is a button and it is visible
	 * 
	 * @param name
	 *          : Locator
	 */
	public void verifyElementIsButtonByName(String name) {
		CateredWebElement element = findElementByName(name);
		String tagName = element.getTagName();
		if (!tagName.equals("button")) {
			Reporter.log("Element with name " + "\"" + name + "\" " + "is not a button. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with name " + "\"" + name + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}

	}

	/**
	 * It verifies the element with given locator is a button and it is visible
	 * 
	 * @param xpath
	 *          :Locator
	 */

	public void verifyElementIsButtonByXpath(String xpath) {
		CateredWebElement element = findElementByXpath(xpath);
		String tagName = element.getTagName();
		if (!tagName.equals("button")) {
			Reporter.log("Element with xpath " + "\"" + xpath + "\" " + "is not a button. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with xpath " + "\"" + xpath + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies the element with given locator is a button and it is visible
	 * 
	 * @param css
	 *          : Locator
	 */
	public void verifyElementIsButtonByCss(String css) {
		CateredWebElement element = findElementByCSS(css);
		String tagName = element.getTagName();
		if (!tagName.equals("button")) {
			Reporter.log("Element with css " + "\"" + css + "\" " + "is not a button. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with css " + "\"" + css + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies if the check box with the given Id is checked
	 * 
	 * @param Id
	 *          : Locator
	 */
	public void verifyCheckBoxIsCheckedById(String Id) {
		CateredWebElement element = findElementById(Id);
		Boolean status = element.isSelected();
		if (status.equals(false)) {
			Reporter.log("Checkbox with id " + Id + " is not checked.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies if the check box with the given Id is Unchecked
	 * 
	 * @param Id
	 *          : Locator
	 */
	public void verifyCheckBoxIsUncheckedById(String Id) {
		CateredWebElement element = findElementById(Id);
		Boolean status = element.isSelected();
		if (status.equals(true)) {
			Reporter.log("Checkbox with id " + Id + " is checked.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies if the check box with the given Id is checked
	 * 
	 * @param Id
	 *          : Locator
	 */
	public void verifyCheckBoxIsCheckedByXpath(String xpath) {
		CateredWebElement element = findElementByXpath(xpath);
		Boolean status = element.isSelected();
		if (status.equals(false)) {
			Reporter.log("Checkbox with xpath " + xpath + " is not checked.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies if the check box with the given xpath is Unchecked
	 * 
	 * @param xpath
	 *          : Locator
	 */
	public void verifyCheckBoxIsUncheckedByXpath(String xpath) {
		CateredWebElement element = findElementByXpath(xpath);
		Boolean status = element.isSelected();
		if (status.equals(true)) {
			Reporter.log("Checkbox with xpath " + xpath + " is checked.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies the element with given locator is a input textbox and it is
	 * visible
	 * 
	 * @param css
	 *          : Locator
	 */
	public void verifyElementIsTextboxByCss(String css) {
		CateredWebElement element = findElementByCSS(css);
		String tagName = element.getTagName();
		if ((!tagName.equals("input")) && (!tagName.equals("textarea"))) {
			Reporter.log("Element with css " + "\"" + css + "\" " + "is not a Tesxtbox. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with css " + "\"" + css + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies the element with given locator is a input textbox and it is
	 * visible
	 * 
	 * @param id
	 *          : Locator
	 */

	public void verifyElementIsTextboxById(String id) {
		CateredWebElement element = findElementById(id);
		String tagName = element.getTagName();
		if ((!tagName.equals("input")) && (!tagName.equals("textarea"))) {
			Reporter.log("Element with ID " + "\"" + id + "\" " + "is not a Textbox. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with id " + "\"" + id + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies the element with given locator is a input textbox and it is
	 * visible
	 * 
	 * @param name
	 *          : Locator
	 */
	public void verifyElementIsTextboxByName(String name) {
		CateredWebElement element = findElementByName(name);
		String tagName = element.getTagName();
		if ((!tagName.equals("input")) && (!tagName.equals("textarea"))) {
			Reporter.log("Element with name " + "\"" + name + "\" " + "is not a Textbox. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with name " + "\"" + name + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * It verifies the element with given locator is a input textbox and it is
	 * visible.
	 * 
	 * @param xpath
	 *          : Locator
	 */
	public void verifyElementIsTextboxByXpath(String xpath) {
		CateredWebElement element = findElementByXpath(xpath);
		String tagName = element.getTagName();
		if ((!tagName.equals("input")) || (!tagName.equals("textarea"))) {
			Reporter.log("Element with xpath " + "\"" + xpath + "\" " + "is not a Textbox. It has tag name " + "\"" + tagName + "\"");
			captureScreenshot();
			throw new AssertionError();
		}
		if (!element.isDisplayed()) {
			Reporter.log("Element with xpath " + "\"" + xpath + "\" " + "is not displayed.");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	public boolean verifyElementTextByName(String name, String text) {
		return this.verifyElementText(By.name(name), text);
	}

	public boolean verifyElementTextById(String id, String text) {
		return this.verifyElementText(By.id(id), text);
	}

	public boolean verifyElementTextByCss(String css, String text) {
		return this.verifyElementText(By.cssSelector(css), text);
	}

	public boolean verifyElementTextByXpath(String xpath, String text) {
		return this.verifyElementText(By.xpath(xpath), text);
	}

	public boolean verifyElementTextByTagname(String Tagname, String text) {
		return this.verifyElementText(By.tagName(Tagname), text);
	}

	public boolean verifyElementTextByClassName(String className, String text) {
		return this.verifyElementText(By.className(className), text);
	}

	public boolean verifyElementTextByLinkText(String linkText, String text) {
		return this.verifyElementText(By.linkText(linkText), text);
	}

	public boolean verifyElementTextByPartialLinkText(String linkText, String text) {
		return this.verifyElementText(By.partialLinkText(linkText), text);
	}

	public boolean verifyElementNotPresntById(String id) {
		boolean found = false;
		try {
			this.findElementById(id);
			found = true;
			Reporter.log("Element is present with id " + id);
			throw new AssertionError("Element is present with id " + id);
		} catch (Exception e) {
		}
		return found;
	}

	/*
	 * To verify element is not present
	 */
	public boolean verifyElementNotPresntByName(String name) {
		boolean found = false;
		try {
			this.findElementByName(name);
			found = true;
			Reporter.log("Element is present with name " + name);
			throw new AssertionError("Element is present with name " + name);
		} catch (Exception e) {
		}
		return found;
	}

	public boolean verifyElementNotPresntByXpath(String xpath) {
		boolean notFound = true;
		try {
			this.findElementByXpath(xpath);
			notFound = false;
			Reporter.log("Element is present with xpath " + xpath);
			throw new AssertionError("Element is present with xpath " + xpath);
		} catch (Exception e) {
		}
		return notFound;
	}

	public boolean verifyElementNotPresntByLinkText(String linkText) {
		boolean notFound = true;
		try {
			this.findElementByLinkText(linkText);
			notFound = false;
			Reporter.log("Element is present with linktext " + linkText);
			throw new AssertionError("Element is present with linktext " + linkText);
		} catch (Exception e) {
		}
		return notFound;
	}

	public boolean verifyElementNotPresntByCss(String css) {
		boolean found = false;
		try {
			this.findElementByCSS(css);
			found = true;
			Reporter.log("Element is present with css " + css);
			throw new AssertionError("Element is present with css " + css);
		} catch (Exception e) {
		}
		return found;
	}

	public boolean checkTextInListByXpath(String xpath, String text) {
		boolean found = false;
		List<CateredWebElement> elements = findAllElements(By.xpath(xpath));
		for (CateredWebElement element : elements) {
			logger.info(element.readInnerText());
			if (element.readInnerText().equals(text)) {
				found = true;
				break;
			}
		}
		if (!found) {
			Reporter.log("Element not found with text " + text + " by xpath " + xpath);
			captureScreenshot();
			throw new NoSuchElementException("Element not found with text " + text + " by xpath " + xpath);
		}
		return found;
	}

	public boolean checkTextInListByCss(String css, String text) {
		boolean found = false;
		List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
		for (CateredWebElement element : elements) {
			logger.info(element.readInnerText());
			if (element.readInnerText().equals(text)) {
				found = true;
				break;
			}
		}
		if (!found) {
			Reporter.log("Element not found with text " + text + " by css " + css);
			captureScreenshot();
			throw new NoSuchElementException("Element not found with text " + text + " by css " + css);
		}
		return found;
	}

	/**
	 * Verify all the elements find by the locator have the given text.
	 * 
	 * @param css
	 *          - cssSelector Value
	 * @param text
	 *          - Visible element text
	 * @return - true on success
	 */
	public boolean checkAllElementsHavingTextByCss(String css, String text) {
		boolean found = true;
		List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
		for (CateredWebElement element : elements) {
			logger.info(element.readInnerText());
			if (!element.readInnerText().contains(text)) {
				Reporter.log(text + " not found in " + element.readInnerText() + " By Css" + css);
				captureScreenshot();
				throw new NoSuchElementException(text + " not found in " + element.readInnerText() + " By Css " + css + " .Actual "
						+ element.readInnerText());
			}
		}
		return found;
	}

	/**
	 * Verify all the elements find by the locator have the given text.
	 * 
	 * @param xpath
	 *          - xpath Expression
	 * @param text
	 *          - Visible element text
	 * @return - true on success
	 */
	public boolean checkAllElementsHavingTextByXpath(String xpath, String text) {
		boolean found = true;
		List<CateredWebElement> elements = findAllElements(By.xpath(xpath));
		for (CateredWebElement element : elements) {
			if (!element.readInnerText().contains(text)) {
				Reporter.log(text + " not found in " + element.readInnerText() + " By Xpath " + xpath + " . Actual "
						+ element.readInnerText());
				captureScreenshot();
				throw new NoSuchElementException(text + " not found in " + element.readInnerText() + " By xpath" + xpath);
			}
		}
		return found;
	}

	/**
	 * Overridden webDriver find Elements with proper wait.
	 */
	@Override
	public List<WebElement> findElements(By locator) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(this.driver, maxWaitTime);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			throw error;
		}
		List<WebElement> appElements = null;
		try {
			appElements = (new WebDriverWait(driver, maxWaitTime)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (TimeoutException te) {
			Reporter.log("Unable to find the element by locator: " + locator.toString());
			captureScreenshot();
			throw new TimeoutException(te);
		}
		List<WebElement> webElements = new ArrayList<WebElement>();
		for (WebElement element : appElements) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			webElements.add(new CateredWebElement(this, locator, element));
		}
		return webElements;
	}

	/**
	 * Returns a list of CateredWebElement by the locator
	 * 
	 * @param locator
	 * @return
	 */
	public List<CateredWebElement> findAllElements(By locator) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(this.driver, maxWaitTime);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			throw error;
		}
		List<WebElement> appElements = null;
		try {
			appElements = (new WebDriverWait(driver, maxWaitTime)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (StaleElementReferenceException sRExp) {
			this.navigate().refresh();
			findAllElements(locator);
		} catch (TimeoutException te) {
			Reporter.log("Unable to find the element by locator: " + locator.toString());
			captureScreenshot();
			throw new TimeoutException(te);
		}
		List<CateredWebElement> cateredWebElements = new ArrayList<CateredWebElement>();
		for (WebElement element : appElements) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			cateredWebElements.add(new CateredWebElement(this, locator, element));
		}
		return cateredWebElements;
	}

	/**
	 * Click the element having given name with given CSS value.
	 * 
	 * @param css
	 *          - cssSelector Value
	 * @param name
	 *          - Visible element text
	 */
	public void clickElementWithNameByCss(String css, String name) {
		int attempt = 0;
		boolean success = false;
		while (attempt < 5 && !success) {
			attempt++;
			try {
				WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(css)));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(css)));
				List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
				for (CateredWebElement element : elements) {
					if (element.readInnerText().equalsIgnoreCase(name)) {
						element.click();
						success = true;
						break;
					}
				}
			} catch (Throwable t) {
				Reporter.log(t.getStackTrace().toString());
				captureScreenshot();
				throw t;
			}
		}

		if (!success) {
			Reporter.log("Unable to find element by Css: " + css + " having name: " + name);
			captureScreenshot();
			throw new NoSuchElementException("Unable to find element by Css: " + css + " having name: " + name);
		}
	}

	/**
	 * Click the element having given name , CSS value and position.
	 * 
	 * @param css
	 *          - cssSelector Value
	 * @param name
	 *          - Visible element text
	 * @param position
	 *          - Position of the element in the list
	 */
	public void clickElementWithNameByCss(String css, String name, int position) {
		int attempt = 0;
		boolean success = false;
		while (attempt < 5 && !success) {
			attempt++;
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(css)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(css)));
			List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
			List<CateredWebElement> elementsWithGivenName = new ArrayList<CateredWebElement>();
			for (CateredWebElement element : elements) {
				if (element.readInnerText().equalsIgnoreCase(name)) {
					elementsWithGivenName.add(element);
				}
			}
			elementsWithGivenName.get(position).click();
			success = true;
		}

		if (!success) {
			captureScreenshot();
			throw new NoSuchElementException("Unable to find element by Css: " + css + " having name: " + name + "in the position"
					+ position);
		}
	}

	public void clickElementWithIndexByCss(String css, int index) {
		List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
		try {
			elements.get(index - 1).click();
		} catch (Exception exp) {
			captureScreenshot();
			Reporter.log("Unable to click element index: " + index + " with css: " + css);
			throw exp;
		}
	}

	public void clickLastElementInListByCss(String css) {
		List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
		elements.get(elements.size() - 1).click();
	}

	public void enterTextToElementByCss(String css, String text, int position) {
		List<CateredWebElement> elements = findAllElements(By.cssSelector(css));
		(elements.get(1)).sendKeys(text);
	}

	public void clickElement(WebElement element) {
		element.click();
	}

	public void clickElement(CateredWebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element.getWebElement());
		element.getWebElement().click();
	}

	public void clickElementById(String id) {
		findElementById(id).click();
	}

	public void clickElementByName(String name) {
		findElementByName(name).click();
	}

	public void clickElementByCss(String css) {
		findElementByCSS(css).click();
	}

	public void clickElementByXpath(String xpath) {
		findElementByXpath(xpath).click();
	}

	public void clickElementByClassname(String className) {
		findElementByClassName(className).click();
	}

	public void clickElementByTagName(String tag) {
		findElementByTagName(tag).click();
	}

	public void clickElementByLinkText(String link) {
		findElementByLinkText(link).click();
	}

	public void clickElementByPartialLinkText(String link) {
		findElementByPartialLinkText(link).click();
	}

	public void enterTextToElementById(String id, String text) {
		
			findElementById(id).clear();
			findElementById(id).sendKeys(text);
		
	}

	public void enterTextToElementByName(String name, String text) {
		findElementByName(name).clear();
		findElementByName(name).sendKeys(text);
	}

	public void enterTextToElementByCss(String css, String text) {
		findElementByCSS(css).clear();
		findElementByCSS(css).sendKeys(text);
	}

	public void enterTextToElementByXpath(String xpath, String text) {
		findElementByXpath(xpath).clear();
		findElementByXpath(xpath).sendKeys(text);
	}

	public void enterTextToElementByClassname(String className, String text) {
		// findElementByClassName(className).clear();
		findElementByClassName(className).sendKeys(text);
	}

	public void enterTextToElementByTagName(String tag, String text) {
		findElementByTagName(tag).clear();
		findElementByTagName(tag).sendKeys(text);
	}

	public void enterTextToElementByLinkText(String link, String text) {
		findElementByLinkText(link).clear();
		findElementByLinkText(link).sendKeys(text);
	}

	public void enterTextToElementByPartialLinkText(String link, String text) {
		findElementByPartialLinkText(link).clear();
		findElementByPartialLinkText(link).sendKeys(text);
	}

	/**
	 * Enter text in the input in the specified index. Index starts from 1.
	 * 
	 * @param xpath
	 * @param text
	 * @param index
	 */
	public void enterTextToElementInIndexByXpath(String xpath, String text, int index) {
		findElements(By.xpath(xpath)).get(index - 1).clear();
		findElements(By.xpath(xpath)).get(index - 1).sendKeys(text);
	}

	/**
	 * To check an option button is enabled. This is SnapAV project specific
	 * method may not work for others.
	 */
	public void checkOptionIsEnabledbyXpath(String xpath) {

		if (!findElementByXpath(xpath).getAttribute("btn-radio").equals("true")) {
			captureScreenshot();
			Reporter.log("Option is not enabled. Locator: " + xpath);
			throw new AssertionError("Option is not enabled. Locator: " + xpath);
		}
	}

	/**
	 * Click the button with given text.
	 * 
	 * @param text
	 *          - Visible button text
	 */
	public void clickButtonWithText(String text) {
		boolean success = false;
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button")));
			List<CateredWebElement> buttons = findAllElements(By.tagName("button"));
			for (CateredWebElement button : buttons) {
				if (button.readInnerText().equalsIgnoreCase(text)) {
					button.click();
					success = true;
					break;
				}
			}
		} catch (Exception e) {
			Reporter.log("Exception occoured while click button with text \"" + text + "\"");
			captureScreenshot();
			throw e;
		}
		if (!success) {
			Reporter.log("Unable to click button with text \"" + text + "\"");
			captureScreenshot();
			throw new NotFoundException("Unable to click button with text \"" + text + "\"");
		}

	}

	public void verifyElementPresentByName(String name) {
		findElementByName(name);
	}

	public void verifyElementPresentByLinkText(String linkText) {
		findElementByLinkText(linkText);
	}

	public void verifyElementPresentByCss(String css) {
		findElementByCSS(css);
	}

	public void verifyElementPresentById(String id) {
		findElementById(id);
	}

	public void verifyElementPresentByTagName(String TagName) {
		findElementByTagName(TagName);
	}

	public void verifyElementPresentByClassName(String className) {
		findElementByClassName(className);
	}

	


	/**
	 * This is to verify any project specific attributes of an element.
	 * 
	 * @param id
	 *          - HTML ID
	 * @param attributeName
	 *          - Attribute name
	 * @param attributeValue
	 *          - Attribute value
	 */
	public void verifyAttributeValueById(String id, String attributeName, String attributeValue) {
		String actualAttributeValue = findElementById(id).getAttribute(attributeName);
		if (!actualAttributeValue.equals(attributeValue)) {
			Reporter.log("Value of the " + attributeName + " was exppected : " + attributeValue + " but found : " + actualAttributeValue);
			captureScreenshot();
			throw new AssertionError("Value of the " + attributeName + " was expected : " + attributeValue + " but found : "
					+ actualAttributeValue);
		}
	}

	/**
	 * This is to verify any project specific attributes of an element.
	 * 
	 * @param name
	 *          - name
	 * @param attributeName
	 *          - Attribute name
	 * @param attributeValue
	 *          - Attribute value
	 */
	public void verifyAttributeValueByName(String name, String attributeName, String attributeValue) {
		String actualAttributeValue = findElementByName(name).getAttribute(attributeName).trim();
		if (!actualAttributeValue.equals(attributeValue)) {
			Reporter.log("Value of the " + attributeName + " was expected : " + attributeValue + " but found : " + actualAttributeValue);
			captureScreenshot();
			throw new AssertionError("Value of the " + attributeName + " was expected : " + attributeValue + " but found : "
					+ actualAttributeValue);
		}
	}

	/**
	 * This is to verify any project specific attributes of an element.
	 * 
	 * @param name
	 *          - xpath
	 * @param attributeName
	 *          - Attribute name
	 * @param attributeValue
	 *          - Attribute value
	 */
	public void verifyAttributeValueByXpath(String Xpath, String attributeName, String attributeValue) {
		String actualAttributeValue = findElementByXpath(Xpath).getAttribute(attributeName).trim();
		if (!actualAttributeValue.trim().equals(attributeValue.trim())) {
			Reporter.log("Value of the " + attributeName + " was expected :<br>" + attributeValue + "<br>but found :<br>"
					+ actualAttributeValue);
			captureScreenshot();
			throw new AssertionError("Value of the " + attributeName + " was expected :<br>" + attributeValue + "<br>but found :<br>"
					+ actualAttributeValue);
		}
	}

	/**
	 * This is to verify any project specific attributes of an element.
	 * 
	 * @param name
	 *          - xpath
	 * @param attributeName
	 *          - Attribute name
	 * @param attributeValue
	 *          - Attribute value
	 */
	public void verifyAttributeContainsValueByXpath(String Xpath, String attributeName, String attributeValue) {
		String actualAttributeValue = findElementByXpath(Xpath).getAttribute(attributeName).trim();
		if (!actualAttributeValue.trim().contains(attributeValue.trim())) {
			Reporter.log("Value of the " + attributeName + " was expected :<br>" + attributeValue + "<br>but found :<br>"
					+ actualAttributeValue);
			captureScreenshot();
			throw new AssertionError("Value of the " + attributeName + " was expected :<br>" + attributeValue + "<br>but found :<br>"
					+ actualAttributeValue);
		}
	}

	public void verifyAttributeContainsValueById(String id, String attributeName, String attributeValue) {
		String actualAttributeValue = findElementById(id).getAttribute(attributeName).trim();
		if (!actualAttributeValue.trim().contains(attributeValue.trim())) {
			Reporter.log("Value of the " + attributeName + " was expected :<br>" + attributeValue + "<br>but found :<br>"
					+ actualAttributeValue);
			captureScreenshot();
			throw new AssertionError("Value of the " + attributeName + " was expected :<br>" + attributeValue + "<br>but found :<br>"
					+ actualAttributeValue);
		}
	}

	/**
	 * This is to verify any project specific attributes of an element.
	 * 
	 * @param name
	 *          - Css selector
	 * @param attributeName
	 *          - Attribute name
	 * @param attributeValue
	 *          - Attribute value
	 */
	public void verifyAttributeValueByCss(String css, String attributeName, String attributeValue) {
		String actualAttributeValue = findElementByCSS(css).getAttribute(attributeName).trim();
		if (!actualAttributeValue.equals(attributeValue)) {
			Reporter.log("Value of the " + attributeName + " was expected : " + attributeValue + " but found : " + actualAttributeValue);
			captureScreenshot();
			throw new AssertionError("Value of the " + attributeName + " was expected : " + attributeValue + " but found : "
					+ actualAttributeValue);
		}
	}

	public void verifyPresenceOfTextInTextBoxLocatedById(String id, String text) {
		// findElementById(id);
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			if (findElementById(id).getTagName().equals("input") || findElementById(id).getTagName().equals("textarea")) {
				wait.until(ExpectedConditions.textToBePresentInElementValue(By.id(id), text));
			}
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in id " + id);
			captureScreenshot();
			throw t;
		}
	}

	public void verifyPresenceOfTextLocatedById(String id, String text) {
		// findElementById(id);
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			if (findElementById(id).getTagName().equals("input") || findElementById(id).getTagName().equals("textarea")) {
				wait.until(ExpectedConditions.textToBePresentInElementValue(By.id(id), text));
			} else {
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(id), text));
			}
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in id " + id);
			captureScreenshot();
			throw t;
		}
	}

	public void verifyPresenceOfTextLocatedByName(String name, String text) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name(name), text));
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in name " + name);
			captureScreenshot();
			throw t;
		}
	}

	public void verifyPresenceOfTextLocatedByXpath(String xpath, String text, long waitTime) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, waitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), text));
			/*
			 * ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);",
			 */
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (StaleElementReferenceException sRExp) {
			this.navigate().refresh();
			verifyPresenceOfTextLocatedByXpath(xpath, text, waitTime);
		} catch (WebDriverException wbExp) {
			Reporter.log("text " + text + " not present in xpath " + xpath + "after " + waitTime + "secs");
			captureScreenshot();
			throw wbExp;
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in xpath " + xpath + "after " + waitTime + "secs");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verify text present with user defined wait time
	 * 
	 * @param xpath
	 * @param text
	 */
	public void verifyPresenceOfTextLocatedByXpath(String xpath, String text) {
		verifyPresenceOfTextLocatedByXpath(xpath, text, maxWaitTime);
	}

	public void verifyPresenceOfTextLocatedByCss(String css, String text) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(css), text));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in css selector " + css + ". Found: " + findElementByCSS(css).readInnerText());
			captureScreenshot();
			throw t;
		}
	}

	public void verifyPresenceOfTextLocatedByClassName(String className, String text) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className(className), text));
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in Class Name " + className);
			captureScreenshot();
			throw t;
		}
	}

	public void verifyPresenceOfTextLocatedByTagName(String tagName, String text) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName(tagName), text));
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in tag name " + tagName);
			captureScreenshot();
			throw t;
		}
	}

	public void verifyPresenceOfTextLocatedByLink(String link, String text) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.linkText(link), text));
		} catch (Throwable t) {
			Reporter.log("text " + text + " not present in tag name " + link);
			captureScreenshot();
			throw t;
		}
	}

	public void acceptAlert() {
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alrt = driver.switchTo().alert();
		alrt.accept();
	}

	public void enterTextInPrompt(String text) {
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert prmpt = driver.switchTo().alert();
		prmpt.sendKeys(text);
		prmpt.accept();
	}

	/**
	 * This is to check total count of elements having same name attribute
	 * 
	 * @param name
	 *          - HTML attribute name
	 * @param number
	 *          - expected total count
	 */
	public void verifyTotalNumberOfElementPresentByName(String name, int number) {

		int actualNumber = findAllElements(By.name(name)).size();

		if (actualNumber != number) {
			Reporter.log("Expected number of elements to be present: " + number + " But found : " + actualNumber);
			captureScreenshot();
			throw new AssertionError("Expected number of elements to be present: " + number + " But found : " + actualNumber);
		}

	}

	/**
	 * This is to check total count of elements having same name attribute
	 * 
	 * @param name
	 *          - HTML attribute name
	 * @param number
	 *          - expected total count
	 */
	public void verifyTotalNumberOfElementPresentByXpath(String xpath, int number) {

		int actualNumber = findAllElements(By.xpath(xpath)).size();

		if (actualNumber != number) {
			Reporter.log("Expected number of elements to be present: " + number + " But found : " + actualNumber);
			captureScreenshot();
			throw new AssertionError("Expected number of elements to be present: " + number + " But found : " + actualNumber);
		}

	}

	/**
	 * This is to check total count of elements having same class name
	 * 
	 * @param className
	 *          - HTML class name
	 * @param number
	 *          - expected total count
	 */
	public void verifyTotalNumberOfElementPresentByClass(String className, int number) {

		int actualNumber = findAllElements(By.className(className)).size();

		if (actualNumber != number) {
			Reporter.log("Expected number of elements to be present: " + number + " But found : " + actualNumber);
			captureScreenshot();
			throw new AssertionError("Expected number of elements to be present: " + number + " But found : " + actualNumber);
		}

	}

	/**
	 * This is to check total count of elements having same css selector value
	 * 
	 * @param css
	 *          - HTML CSS selector value
	 * @param number
	 *          - expected total count
	 */
	public void verifyTotalNumberOfElementPresentByCssSelector(String css, int number) {

		int actualNumber = findAllElements(By.cssSelector(css)).size();

		if (actualNumber != number) {
			Reporter.log("Expected number of elements to be present: " + number + " But found : " + actualNumber);
			captureScreenshot();
			throw new AssertionError("Expected number of elements to be present: " + number + " But found : " + actualNumber);
		}

	}

	/**
	 * This is to check total count of elements are more than given number
	 * 
	 * @param css
	 *          - HTML CSS selector value
	 * @param number
	 *          - expected total count
	 */
	public void verifyTotalNumberOfElementPresentIsGreaterThanGivenByXpath(String xpath, int number) {

		int actualNumber = findAllElements(By.xpath(xpath)).size();

		if (actualNumber <= number) {
			Reporter.log("Actual number of elements present " + actualNumber + " is not greater than expected " + number);
			captureScreenshot();
			throw new AssertionError("Actual number of elements present " + actualNumber + " is not greater than expected " + number);
		}

	}

	/**
	 * verify directions are greater than zero
	 */
	public void verifyDirectionsGreaterThanZero(int count) {
		if (count == 0) {
			Reporter.log("Number of directions is zero!");
			captureScreenshot();
			throw new AssertionError();
		}
	}

	/**
	 * This is to check total count of elements are more than given number
	 * 
	 * @param css
	 *          - HTML CSS selector value
	 * @param number
	 *          - expected total count
	 */
	public void verifyTotalNumberOfElementPresentIsGreaterThanGivenByCss(String css, int number) {

		int actualNumber = findAllElements(By.cssSelector(css)).size();

		if (actualNumber <= number) {
			Reporter.log("Actual number of elements present " + actualNumber + " is not greater than expected " + number);
			captureScreenshot();
			throw new AssertionError("Actual number of elements present " + actualNumber + " is not greater than expected " + number);
		}

	}

	/**
	 * This is to check if the specified name exists in the list
	 * 
	 * @param allNames
	 *          - list of names
	 * @param name
	 *          - value to compare
	 */
	public void verifyNameNotExistsInList(String name, List<String> allNames) {
		if (allNames.contains(name)) {
			Reporter.log(name + " is present in the list" + allNames);
			captureScreenshot();
			throw new AssertionError(name + " is present in the list" + allNames);
		}
	}

	/**
	 * Verify the given text in present in the list
	 * 
	 * @param xpath
	 * @param name
	 */
	public void verifyNameNotExistInListByXpath(String xpath, String name) {
		List<CateredWebElement> elements = findAllElementsByXpath(xpath);
		List<String> elementTexts = new ArrayList<>();
		for (CateredWebElement element : elements) {
			elementTexts.add(element.readInnerText());
		}
		verifyNameNotExistsInList(name, elementTexts);
	}

	/**
	 * Verify the given text in present in the list
	 * 
	 * @param css
	 * @param name
	 */
	public void verifyNameNotExistInListByCss(String css, String name) {
		List<CateredWebElement> elements = findAllElementsByCss(css);
		List<String> elementTexts = new ArrayList<>();
		for (CateredWebElement element : elements) {
			elementTexts.add(element.readInnerText());
		}
		verifyNameNotExistsInList(name, elementTexts);
	}

	/**
	 * Verify the element is visible
	 * 
	 * @param element
	 */
	public void verifyVisibilityOfElement(WebElement element) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Throwable t) {
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param xpath
	 */
	public void verifyVisibilityOfElementLocatedByXpath(String xpath) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Throwable t) {
			Reporter.log("Element by xpath " + xpath + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param xpath
	 */
	public void verifyVisibilityOfElementLocatedById(String id) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		} catch (Throwable t) {
			Reporter.log("Element by Id " + id + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param css
	 */
	public void verifyVisibilityOfElementLocatedByCss(String selector) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
		} catch (Throwable t) {
			Reporter.log("Element by Css " + selector + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param Classname
	 */
	public void verifyVisibilityOfElementLocatedByClassname(String className) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
		} catch (Throwable t) {
			Reporter.log("Element by Classname " + className + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param xpath
	 */
	public void verifyVisibilityOfElementLocatedByLinkText(String linkText) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
		} catch (Throwable t) {
			Reporter.log("Element by linkText " + linkText + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param xpath
	 */
	public void verifyVisibilityOfAllElementsLocatedByXpath(String xpath) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
		} catch (Throwable t) {
			Reporter.log("Element with xpath " + xpath + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verifies element is visible
	 * 
	 * @param xpath
	 */
	public void verifyVisibilityOfAllElementsLocatedByTagname(String tagName) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(tagName)));
		} catch (Throwable t) {
			Reporter.log("Element with Tagname " + tagName + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * This checks if the element is not visible
	 * 
	 * @param xpath
	 */
	public void verifyElementIsInvisibleByXpath(String xpath) {
		verifyElementIsInvisibleByXpath(xpath, maxWaitTime);
	}

	/**
	 * This checks if the element is not visible
	 * 
	 * @param xpath
	 */
	public void verifyElementIsInvisibleByXpath(String xpath, long waitTime) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, waitTime, 500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		} catch (Throwable t) {
			Reporter.log("Elment with xpath " + xpath + " visible after " + waitTime + " secs.");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * This checks if the element is not visible
	 * 
	 * @param xpath
	 */
	public void verifyElementIsInvisibleById(String id) {
		verifyElementIsInvisibleById(id, maxWaitTime);
	}

	/**
	 * This checks if the element is not visible
	 * 
	 * @param id
	 */
	public void verifyElementIsInvisibleById(String id, long waitTime) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, waitTime, 500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
		} catch (Throwable t) {
			Reporter.log("Elment with id " + id + " visible after " + waitTime + " secs.");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * This checks if the element is not visible
	 * 
	 * @param classname
	 */
	public void verifyElementIsInvisibleByClassname(String classname) {
		verifyElementIsInvisibleByClassname(classname, maxWaitTime);
	}

	/**
	 * This checks if the element is not visible
	 * 
	 * @param xpath
	 */
	public void verifyElementIsInvisibleByClassname(String classname, long waitTime) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, waitTime, 500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(classname)));
		} catch (Throwable t) {
			Reporter.log("Elment with classname " + classname + " visible after " + waitTime + " secs.");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * This method checks the given text is present in the given element
	 * 
	 * @param element
	 * @param text
	 */
	public void verifyElementHavingText(CateredWebElement element, String text) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (Throwable t) {
			Reporter.log(text + " not found. Actual Text: " + element.readInnerText());
			captureScreenshot();
			throw t;
		}
	}

	public void clearInputById(String id) {
		findElementById(id).clear();
	}

	public void clearInputByName(String name) {
		findElementByName(name).clear();
	}

	public void clearInputByXpath(String xpath) {
		findElementByXpath(xpath).clear();
	}

	public void clearInputByCss(String css) {
		findElementByCSS(css).clear();
	}

	public void clearInputByClassname(String className) {
		findElementByClassName(className).clear();
	}

	@Override
	public void get(String url) {
		try {
			driver.get(url);
		} catch (NullPointerException exp) {
			System.err.println("URL is NULL");
			Reporter.log("URL is NULL");
			throw exp;
		}

	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {

		return driver.getPageSource();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	/**
	 * Verify given text is present in the link
	 * 
	 * @param linkText
	 */
	public void verifyElementPresentByPartialLinkText(String linkText) {
		findElementByPartialLinkText(linkText);
	}

	/**
	 * Verify element present by xpath
	 * 
	 * @param Xpath
	 */
	public void verifyElementPresentByXpath(String xpath) {
		findElementByXpath(xpath);
	}

	/**
	 * Verify element present by xpath
	 * 
	 * @param Xpath
	 */
	public void verifyElementPresentByXpath(String xpath, long waitTime) {
		findElementByXpath(xpath, waitTime);
	}

	public void switchToWindowWithTitle(String windowTitle) {
		Set<String> windows = this.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			this.switchTo().window(it.next());
			if (windowTitle.equals(this.getTitle())) {
				break;
			}
		}
	}

	public boolean verifyElementPresentBy(By by){
		boolean found=true;
		try{
		WebDriverWait wait=new WebDriverWait(driver, maxWaitTime, 500);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return found;
		}
		catch(Exception e){
			captureScreenshot();
			Reporter.log("element not found by "+ by);
			found=false;
			return found;
		}
		
	}
	public void closeAllWindows() {
		Set<String> windows = this.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			this.switchTo().window(it.next());
			this.close();
		}
		this.quit();
	}

	/**
	 * Checks all elements inner text found by the css in the list are sorted in
	 * ascending order.
	 * 
	 * @param css
	 *          - Css Selector value
	 */
	public void verifyListIsAscendingOrderSortedByCss(String css) {
		List<CateredWebElement> actaulElements = findAllElements(By.cssSelector(css));
		ArrayList<String> list = new ArrayList<String>();
		for (CateredWebElement element : actaulElements) {
			list.add(element.readInnerText().toLowerCase());
		}
		ArrayList<String> actualList = new ArrayList<String>(list);
		Collections.sort(list);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted.<br> Expected: " + list + "<br> Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements inner text found by the css in the list are sorted in
	 * descending order.
	 * 
	 * @param css
	 *          - Css Selector value
	 */
	public void verifyListIsDescendingOrderSortedByCss(String css) {
		List<CateredWebElement> actaulElements = findAllElements(By.cssSelector(css));
		ArrayList<String> list = new ArrayList<String>();
		for (CateredWebElement element : actaulElements) {
			list.add(element.readInnerText().toLowerCase());
		}
		ArrayList<String> actualList = new ArrayList<String>(list);
		Collections.sort(list);
		Collections.reverse(list);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted.<br> Expected: " + list + "<br> Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements inner text found by the Xpath in the list are sorted in
	 * ascending order.
	 * 
	 * @param xpath
	 *          - Xpath Selector value
	 */
	public void verifyListIsAscendingOrderSortedByXpath(String xPath) {
		List<CateredWebElement> actaulElements = findAllElements(By.xpath(xPath));
		ArrayList<String> list = new ArrayList<String>();
		for (CateredWebElement element : actaulElements) {
			String name = element.readInnerText();
			name = name.toLowerCase();
			list.add(name);
		}
		ArrayList<String> actualList = new ArrayList<String>(list);
		Collections.sort(list);
		logger.info("Expected list" + list);
		// System.out.println(list);
		logger.info("Actual list" + actualList);
		// System.out.println(actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br>Expected: " + list + " <br>Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements in the list are sorted in ascending order.
	 * 
	 * @param list
	 */
	public void verifyListIsAscendingOrderSorted(ArrayList<String> list) {
		ArrayList<String> actualList = new ArrayList<String>(list);
		Collections.sort(list);
		logger.info("Expected list" + list);
		logger.info("Actual list" + actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br>Expected: " + list + " <br>Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements in the list are sorted in ascending order.
	 * 
	 * @param list
	 */
	public void verifyNumberListIsAscendingOrderSorted(ArrayList<Double> list) {
		ArrayList<Double> actualList = new ArrayList<Double>(list);
		Collections.sort(list);
		logger.info("Expected list" + list);
		logger.info("Actual list" + actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br>Expected: " + list + " <br>Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements in the list are sorted in Descending order.
	 * 
	 * @param list
	 */
	public void verifyNumberListIsDescendingOrderSorted(ArrayList<Double> list) {
		ArrayList<Double> actualList = new ArrayList<Double>(list);
		Collections.sort(list);
		Collections.reverse(list);
		logger.info("Expected list" + list);
		logger.info("Actual list" + actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br>Expected: " + list + " <br>Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements inner text found by the Xpath in the list are sorted in
	 * descending order.
	 * 
	 * @param xpath
	 *          - Xpath Selector value
	 */
	public void verifyListIsDescendingOrderSortedByXpath(String xPath) {
		List<CateredWebElement> actaulElements = findAllElements(By.xpath(xPath));
		ArrayList<String> list = new ArrayList<String>();
		for (CateredWebElement element : actaulElements) {
			String name = element.readInnerText();
			name = name.toLowerCase();
			list.add(name);
		}
		ArrayList<String> actualList = new ArrayList<String>(list);
		Collections.sort(list);
		Collections.reverse(list);
		logger.info("Expected list" + list);
		// System.out.println(list);
		logger.info("Actual list" + actualList);
		// System.out.println(actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br> Expected: " + list + "<br> Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all Numerical elements inner text found by the Xpath in the list are
	 * sorted in ascending order.
	 * 
	 * @param xpath
	 *          - Xpath Selector value
	 */
	public void verifyListIsAscendingNumericalOrderSortedByXpath(String xPath) {
		List<CateredWebElement> actaulElements = findAllElements(By.xpath(xPath));
		ArrayList<Integer> list = new ArrayList<Integer>();
		int number;
		for (CateredWebElement element : actaulElements) {
			String name = element.readInnerText().replaceAll("[^0-9]", "");
			number = Integer.parseInt(name);
			list.add(number);
		}
		ArrayList<Integer> actualList = new ArrayList<Integer>(list);
		Collections.sort(list);
		logger.info("Expected list" + list);
		logger.info("Actual list" + actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br> Expected: " + list + "<br> Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all Numerical elements inner text found by the Xpath in the list are
	 * sorted in descending order.
	 * 
	 * @param xpath
	 *          - Xpath Selector value
	 */
	public void verifyListIsDescendingNumericalOrderSortedByXpath(String xPath) {
		List<CateredWebElement> actaulElements = findAllElements(By.xpath(xPath));
		ArrayList<Integer> list = new ArrayList<Integer>();
		int number;
		for (CateredWebElement element : actaulElements) {
			String name = element.readInnerText().replaceAll("[^0-9]", "");
			number = Integer.parseInt(name);
			list.add(number);
		}
		ArrayList<Integer> actualList = new ArrayList<Integer>(list);
		Collections.sort(list);
		Collections.reverse(list);
		logger.info("Expected list" + list);
		logger.info("Actual list" + actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br> Expected: " + list + "<br> Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements inner text found by the Xpath in the list are sorted in
	 * ascending order based on timestamp.
	 * 
	 * @param xpath
	 *          - Xpath Selector value
	 */
	public void verifyListIsAscendingTimestampOrderSortedByXpath(String xPath, String format) {
		List<CateredWebElement> actaulElements = findAllElements(By.xpath(xPath));
		ArrayList<Date> list = new ArrayList<Date>();
		Date dateTime = null;
		for (CateredWebElement element : actaulElements) {
			String name = element.readInnerText();
			name = name.toLowerCase();
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			try {
				dateTime = formatter.parse(name);
			} catch (Throwable e) {
				Reporter.log("Invalid date: " + name);
				try {
					throw e;
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
			list.add(dateTime);
		}
		ArrayList<Date> actualList = new ArrayList<Date>(list);
		Collections.sort(list);
		logger.info("Expected list" + list);
		// System.out.println(list);
		logger.info("Actual list" + actualList);
		// System.out.println(actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br>Expected: " + list + " <br>Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks all elements inner text found by the Xpath in the list are sorted in
	 * descending order based on timestamp.
	 * 
	 * @param xpath
	 *          - Xpath Selector value
	 */
	public void verifyListIsDescendingTimestampOrderSortedByXpath(String xPath, String format) {
		List<CateredWebElement> actaulElements = findAllElements(By.xpath(xPath));
		ArrayList<Date> list = new ArrayList<Date>();
		Date dateTime = null;
		for (CateredWebElement element : actaulElements) {
			String name = element.readInnerText();
			name = name.toLowerCase();
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			try {
				dateTime = formatter.parse(name);
			} catch (ParseException e) {
				Reporter.log("Parse exception occured!");
				e.printStackTrace();
			}
			list.add(dateTime);
		}
		ArrayList<Date> actualList = new ArrayList<Date>(list);
		Collections.sort(list);
		Collections.reverse(list);
		logger.info("Expected list" + list);
		// System.out.println(list);
		logger.info("Actual list" + actualList);
		// System.out.println(actualList);
		try {
			Assert.assertTrue(actualList.equals(list));
		} catch (Throwable t) {
			Reporter.log("List is not sorted. <br>Expected: " + list + " <br>Found: " + actualList);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Find all elements by CssSelector value
	 * 
	 * @param css
	 *          -CssSelector value
	 * @return
	 */
	public List<CateredWebElement> findAllElementsByCss(String css) {
		return findAllElements(By.cssSelector(css));
	}

	/**
	 * Find all elements by xpath expression
	 * 
	 * @param css
	 *          -xpath expression
	 * @return
	 */
	public List<CateredWebElement> findAllElementsByXpath(String xpath) {
		return findAllElements(By.xpath(xpath));
	}

	/**
	 * Select from a drop down by visible text with locator ID
	 * 
	 * @param id
	 * @param visibleText
	 */
	public void selectByVisibleTextByLocatorId(String id, String visibleText) {
		try {
			Select ddElement = new Select(driver.findElement(By.id(id)));
			ddElement.selectByVisibleText(visibleText);
		} catch (Throwable t) {
			Reporter.log("Unable to select by visible text:" + visibleText);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verify if the text in the selected option in drop down is same as the
	 * second parameter you pass
	 * 
	 * @param xpath
	 * @param visibleText
	 */
	public void verifySelectedOptionEqualsTextByXpath(String xpath, String visibleText) {

		Select webel = new Select(driver.findElement(By.xpath(xpath)));
		if (!webel.getFirstSelectedOption().getText().equals(visibleText)) {
			captureScreenshot();
			throw new AssertionError("Selected option does not match text provided");
		}
	}

	/**
	 * Verify if the text in the selected option in drop down is same as the
	 * second parameter you pass
	 * 
	 * @param Id
	 * @param visibleText
	 */
	public void verifySelectedOptionEqualsTextById(String id, String visibleText) {

		Select webel = new Select(driver.findElement(By.id(id)));
		if (!webel.getFirstSelectedOption().getText().equals(visibleText)) {
			captureScreenshot();
			throw new AssertionError("Selected option does not match text provided");
		}
	}

	/**
	 * Select from a drop down by visible text with Xpath
	 * 
	 * @param Xpath
	 * @param visibleText
	 */
	public void selectByVisibleTextByXpath(String Xpath, String visibleText) {
		try {
			Select ddElement = new Select(driver.findElement(By.xpath(Xpath)));
			ddElement.selectByVisibleText(visibleText);
		} catch (Throwable t) {
			Reporter.log("Unable to select by visible text:" + visibleText);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Select from a drop down by Index with locator ID
	 * 
	 * @param id
	 * @param visibleText
	 */
	public void selectByIndexByName(String name, int index) {

		Select ddElement = new Select(driver.findElement(By.name(name)));
		try {
			ddElement.selectByIndex(index);
		} catch (Throwable t) {
			Reporter.log("Unable to select by index:" + index);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Select from a drop down by Index with locator ID
	 * 
	 * @param id
	 * @param xpath
	 *          of drop down
	 */
	public void selectByIndexByXpath(String xpath, int index) {

		Select ddElement = new Select(driver.findElement(By.xpath(xpath)));
		try {
			ddElement.selectByIndex(index);
		} catch (Throwable t) {
			Reporter.log("Unable to select by index:" + index);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Select from a drop down by value with locator Xpath
	 * 
	 * @param xpath
	 * @param xpath
	 *          of drop down
	 */
	public void selectByValueByXpath(String xpath, String value) {

		Select ddElement = new Select(driver.findElement(By.xpath(xpath)));
		try {
			ddElement.selectByValue(value);
		} catch (Throwable t) {
			Reporter.log("Unable to select by value:" + value);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Verify given circle parameters (dx, dy, r) are present in graph.
	 * 
	 * @param circleParams
	 */
	public void verifyCirclesPresentInGraph(String[] circleParams, String... otherAttributes) {
		int i;
		try {
			for (i = 0; i < circleParams.length; i += 3) {
				findElementByXpath(".//*[@id='highcharts-6']//circle[@cx=" + circleParams[i] + "] [@cy=" + circleParams[i + 1] + "] [@r="
						+ circleParams[i + 2] + "]");
			}
		} catch (Throwable t) {
			Reporter.log("Avilable circles are:<br>");
			List<CateredWebElement> circles = findAllElements(By.tagName("circle"));
			for (CateredWebElement circle : circles) {
				String params = "cx:" + circle.getAttribute("cx") + " ,cy:" + circle.getAttribute("cy") + " ,r=" + circle.getAttribute("r");
				Reporter.log(params + "<br>");
			}
			throw t;
		}
	}

	public void selectOptionById(String id) {
		try {

			findElementById(id).selectOption();
		} catch (Throwable t) {
			Reporter.log("Option with id:" + id + "not selected");
			captureScreenshot();
			throw t;
		}
	}

	public void selectOptionByXpath(String xpath) {
		try {
			findElementByXpath(xpath).selectOption();
		} catch (Throwable t) {
			Reporter.log("Option with xpath:" + xpath + "not selected");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Mouse over on the given element
	 * 
	 * @param cateredWebElement
	 */
	public void mouseOverOn(CateredWebElement cateredWebElement) {
		Actions action = new Actions(driver);
		action.moveToElement(cateredWebElement.getWebElement()).build().perform();
		// Chrome browser required this sleep because of fast hover in chrome
		this.relax(2000);
	}

	/**
	 * Mouse over on the given element
	 * 
	 * @param WebElement
	 */
	public void mouseOverOn(WebElement webElement) {
		Actions action = new Actions(driver);
		action.moveToElement(webElement).build().perform();
	}

	/**
	 * Verifies given Element has another element having locator.
	 * 
	 * @param containerElement
	 * @param locator
	 */
	public void verifyElementPresentWithinElement(CateredWebElement containerElement, By locator) {
		try {
			containerElement.findElement(locator);
		} catch (Throwable t) {
			Reporter.log("Elment by " + locator + " not visible");
			captureScreenshot();
			throw t;
		}

	}

	/**
	 * Verifies that element is located by the locator provided inside the given
	 * element
	 * 
	 * @param containerElement
	 * @param locator
	 */
	public void verifyElementPresentWithinElement(WebElement containerElement, By locator) {
		try {
			containerElement.findElement(locator);
		} catch (Throwable t) {
			Reporter.log("Elment by " + locator + " not visible");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Returns the orginal driver
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Checking Presence of Frame on next tab
	 * 
	 * @param CssSelector
	 */
	public void verifyPresenceOfFrameByCssSelector(String CssSelector) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(CssSelector)));
		} catch (Throwable t) {
			Reporter.log("Css selector " + CssSelector + " is faulty");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Checks if text extracted through xpath matches pattern provided through
	 * regular expression
	 * 
	 * @param pattern
	 * @param xpath
	 */
	public void verifyTextMatchesElementByXpath(String pattern, String xpath) {
		String textToCompare = findElement(By.xpath(xpath)).getText();
		if (!textToCompare.matches(pattern)) {
			captureScreenshot();
			throw new AssertionError(textToCompare + " obtained does not match the pattern provided." + pattern);
		}
	}

	/**
	 * Checking Title of next tab
	 * 
	 * @param title
	 */
	public void verifyTitleOfNewTab(String title) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			wait.until(ExpectedConditions.titleIs(title));
		} catch (Throwable t) {
			Reporter.log("Title given " + title + " is faulty");
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * To hit the down key of the keyboard
	 * 
	 * @param id
	 */
	public void pressDownArrowById(String id) {
		clickElementById(id);
		findElementById(id).sendKeys(Keys.DOWN);
	}

	/**
	 * To hit the down key of the keyboard
	 * 
	 * @param Name
	 */
	public void pressDownArrowByName(String name) {
		clickElementByName(name);
		findElementByName(name).sendKeys(Keys.DOWN);
	}

	/**
	 * To hit the Enter key of the keyboard
	 * 
	 * @param id
	 */
	public void pressEnterByName(String name) {
		clickElementByName(name);
		findElementByName(name).sendKeys(Keys.ENTER);
	}

	/**
	 * To hit the Enter key of the keyboard
	 * 
	 * @param name
	 */
	public void pressEnterById(String id) {
		clickElementById(id);
		findElementById(id).sendKeys(Keys.ENTER);
	}

	/**
	 * To enable scrolling down a section on the page
	 * 
	 * @param by
	 *          - locator
	 * @param limit
	 *          - pixels to scroll
	 */
	public void scrollDownToPixelBy(By by, int limit) {
		Actions dragger = new Actions(driver);
		int count = 0;
		boolean scrollBar = false;
		WebElement webel = null;
		while (!scrollBar && count <= 5) {
			try {
				count++;
				webel = driver.findElement(by);
				scrollBar = true;
			} catch (NoSuchElementException nsep) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.debug("Attempt " + count);
			}
		}
		try {
			dragger.moveToElement(webel).clickAndHold().moveByOffset(0, limit).release().perform();
		} catch (Throwable t) {
			Reporter.log("Unable to scroll by " + by);
			captureScreenshot();
			throw t;
		}
	}

	/**
	 * Scroll down vertical bar located by Xpath expression by given pixel value
	 * 
	 * @param xpath
	 * @param limit
	 */
	public void scrollDownToPixelByXpath(String xpath, int limit) {
		scrollDownToPixelBy(By.xpath(xpath), limit);
	}

	/**
	 * Verify presence of text lated in "label" tag. It waits until the specified
	 * text is not present
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInLabelTag(String text) {
		String xpath = "//label[text()='" + text + "']";
		verifyPresenceOfTextLocatedByXpath(xpath, text);

	}

	/**
	 * Verify presence of text lated in "span" tag. It waits until the specified
	 * text is not present
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInSpanTag(String text) {
		String xpath = "//span[contains(text(),'" + text + "')]";
		verifyPresenceOfTextLocatedByXpath(xpath, text);
	}

	public void verifyPresenceOfTextInLiTag(String text) {
		String xpath = "//li[contains(text(),'" + text + "')]";
		verifyPresenceOfTextLocatedByXpath(xpath, text);
	}
	
	/**
	 * Verify presence of text lated in "a" tag. It waits until the specified text
	 * is not present
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInATag(String text) {
		String xpath = "//a[contains(text(),'" + text + "')]";
		verifyPresenceOfTextLocatedByXpath(xpath, text);
	}

	/**
	 * Verify presence of text lated in "div" tag. It waits until the specified
	 * text is not present
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInDivTagText(String text) {
		String xpath = "//div[contains(text(),'" + text + "')]";
		// verifyVisibilityOfElementLocatedByXpath(xpath);
		verifyPresenceOfTextLocatedByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in label and also scrolls the page if the
	 * element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyLabelTagTextEquals(String text) {
		String xpath = "//label[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in span and also scrolls the page if the
	 * element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifySpanTagTextEquals(String text) {
		String xpath = "//span[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'a' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyATagTextEquals(String text) {
		String xpath = "//a[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}
	
	/**
	 * Verify the given text is visible in 'a' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyLiTagTextEquals(String text) {
		String xpath = "//li[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'H4' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInH4Tag(String text) {
		String xpath = "//h4[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'H3' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInH3Tag(String text) {
		String xpath = "//h3[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'H2' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInH2Tag(String text) {
		
		String xpath = "//h2[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'H2' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyH2TagTextContains(String text) {
		String xpath = "//h2[contains (text(),'" + text + "')]";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'H1' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInH1Tag(String text) {
		String xpath = "//h1[contains(text(),'" + text + "')]";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'div' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyDivTagTextEquals(String text) {
		String xpath = "//div[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'div' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyDivTagTextContains(String text) {
		String xpath = "//div[contains(text(),'" + text + "')]";
		verifyElementTextContainsByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'p' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyPTagTextContains(String text) {
		String xpath = "//p[contains(text(),'" + text + "')]";
		verifyElementTextContainsByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'li' tag and also scrolls the page if
	 * the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyLiTagTextContains(String text) {
		String xpath = "//li[contains(text(),'" + text + "')]";
		verifyElementTextContainsByXpath(xpath, text);
	}

	/**
	 * Verify the given text is visible in 'string' tag and also scrolls the page
	 * if the element is beyond the current view.
	 * 
	 * @param text
	 */
	public void verifyStrongTagTextEquals(String text) {
		String xpath = "//strong[text()='" + text + "']";
		verifyElementTextByXpath(xpath, text);
	}

	public void scrollElementIntoView(WebElement appElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", appElement);
	}

	public void verifyLinkContent(String linkName, String verificationText) {
		verifyLinkContent(linkName, verificationText, false);
	}

	public void verifyLinkContent(String linkName, String verificationText, boolean continueOnFailure) {
		boolean found = false;
		String linkUrl = "";

		if (linkName.contains("//")) {
			linkUrl = driver.findElement(By.xpath(linkName)).getAttribute("href");
		} else {
			linkUrl = findElement(By.linkText(linkName)).getAttribute("href");
		}

		try {
			BufferedReader in = null;
			
			URL url = new URL(linkUrl);
			if (linkUrl.contains("https://")) {
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}
				} };
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					@Override
					public boolean verify(String arg0, SSLSession arg1) {
						// TODO Auto-generated method stub
						return true;
					}
				};
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
				HttpsURLConnection httpCon = (HttpsURLConnection) url.openConnection();
				// set http request headers
				httpCon.addRequestProperty("Connection", "keep-alive");
				httpCon.addRequestProperty("Cache-Control", "max-age=0");
				httpCon.addRequestProperty("Accept", "text/html");
				httpCon.addRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
				httpCon.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				// httpCon.addRequestProperty("Cookie",
				// "JSESSIONID=EC0F373FCC023CD3B8B9C1E2E2F7606C; lang=tr; __utma=169322547.1217782332.1386173665.1386173665.1386173665.1; __utmb=169322547.1.10.1386173665; __utmc=169322547; __utmz=169322547.1386173665.1.1.utmcsr=stackoverflow.com|utmccn=(referral)|utmcmd=referral|utmcct=/questions/8616781/how-to-get-a-web-pages-source-code-from-java; __gads=ID=3ab4e50d8713e391:T=1386173664:S=ALNI_Mb8N_wW0xS_wRa68vhR0gTRl8MwFA; scrElm=body");
				HttpsURLConnection.setFollowRedirects(false);
				httpCon.setInstanceFollowRedirects(false);
				httpCon.setDoOutput(true);
				httpCon.setUseCaches(true);
				httpCon.setRequestMethod("GET");
				in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8"));
				String inputLine;
				found = false;
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.contains(verificationText)) {
						found = true;
						break;
					}
				}
				in.close();
			} else {
				HttpClient client = new DefaultHttpClient();
				HttpParams httpParameters = client.getParams();
				HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
				HttpConnectionParams.setSoTimeout(httpParameters, 5000);
				HttpConnectionParams.setTcpNoDelay(httpParameters, true);
				HttpGet request = new HttpGet();
				request.setURI(new URI(linkUrl));
				HttpResponse response = client.execute(request);
				InputStream ips = response.getEntity().getContent();
				BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String inputLine = "";
				while (true) {
					inputLine = buf.readLine();
					if (inputLine.contains(verificationText)) {
						found = true;
						break;
					}
				}
				buf.close();
				ips.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!found) {
			Reporter.log("Verification String " + verificationText + " is not found in Link: " + linkName);
			if (!continueOnFailure) {
				throw new AssertionError();
			}
		}
	}

	public String readTextByXpath(String xpath) {
		return findElementByXpath(xpath).getText();
	}

	public String readTextByCss(String css) {
		return findElementByCSS(css).getText();
	}

	public String readTextById(String Id) {
		return findElementById(Id).getText();
	}

	public void verifyElementTextContainsByXpath(String xpath, String text) {
		verifyElementTextContains(By.xpath(xpath), text);
	}

	public void verifyElementTextContainsById(String id, String text) {
		verifyElementTextContains(By.id(id), text);
	}

	public void verifyElementTextContainsByCss(String css, String text) {
		verifyElementTextContains(By.cssSelector(css), text);

	}

	/**
	 * Verify presence of text lated in "div" tag. It waits until the specified
	 * text is not present
	 * 
	 * @param text
	 */
	public void verifyPresenceOfTextInPTagText(String text) {
		String xpath = "//p[contains(text(),'" + text + "')]";
		verifyVisibilityOfElementLocatedByXpath(xpath);
	}

	public void verifyPageSourceContains(String text) {
		if (!driver.getPageSource().contains(text)) {
			captureScreenshot();
			throw new AssertionError(text + " not present.");
		}
	}

	/**
	 * This is sleep method. Not recommended for frequent use.
	 */
	public void relax(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns total number of elements present
	 * 
	 * @param xpath
	 * @return
	 */
	public int getTotalNumberOfElementsByXpath(String xpath) {
		return findAllElements(By.xpath(xpath)).size();
	}

	public int getTotalNumberOfElementsByCss(String css) {
		return findAllElements(By.cssSelector(css)).size();
	}

	/**
	 * This method checks for the equality of passed integer values
	 */

	public void verifyEquals(int... numbers) {
		int num1 = numbers[0];
		for (int num2 : numbers) {
			if (num1 != num2) {
				Reporter.log("First value " + num1 + " is not equal to second value " + num2);
				captureScreenshot();
				throw new AssertionError();
			}
			num2 = num1;
		}

	}

	/**
	 * This method checks for the equality of passed double values
	 */

	public void verifyEqualsDouble(double... numbers) {
		double num1 = numbers[0];
		for (double num2 : numbers) {
			if (num1 != num2) {
				Reporter.log("First value " + num1 + " is not equal to second value " + num2);
				captureScreenshot();
				throw new AssertionError();
			}
			num2 = num1;
		}
	}

	/**
	 * This method checks for the equality of passed String values
	 */
	public void verifyEqualsString(String... strings) {
		String str1 = strings[0];
		for (String str2 : strings) {
			if (!str1.equalsIgnoreCase(str2)) {
				Reporter.log("Actual: " + str1 + " Expected: " + str2);
				captureScreenshot();
				throw new AssertionError();
			}
			str2 = str1;
		}
	}

	public void scrollIntoViewElement(WebElement element) {
		Coordinates coordinate = ((Locatable) element).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
	}

	public void switchtoiFrameByXpath(String xpath) {
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWaitTime, 500);
			// deleted this "//iframe[@class='cboxIframe']" from below line and added
			// xpath
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Throwable t) {
			Reporter.log("iFrame is not avaialble to switch with xpath: " + xpath);
			captureScreenshot();
			throw t;
		}
		switchTo().frame(getDriver().findElement(By.xpath(xpath)));
	}

	/**
	 * Returns a list of all elements' text found by the locator
	 * 
	 * @param by
	 * @return list of texts found in the element
	 * @author amodak
	 * @date 9th June, 2015
	 */
	public List<String> getTextFromAllElemetns(By by) {
		List<CateredWebElement> elementList = findAllElements(by);
		List<String> elementsText = new ArrayList<String>();
		for (CateredWebElement element : elementList) {
			elementsText.add(element.readInnerText());
		}
		return elementsText;
	}

	/**
	 * Returns a list of all elements' text found by the class locator
	 * 
	 * @param by
	 * @return list of texts found in the element
	 * @author amodak
	 * @date 9th June, 2015
	 */
	public List<String> getTextFromAllElemetnsByClass(String className) {
		return getTextFromAllElemetns(By.className(className));
	}

	/**
	 * Returns a list of all elements' text found by the class locator
	 * 
	 * @param by
	 * @return list of texts found in the element
	 * @author Muzfera
	 * @date 09th Sep, 2015
	 */
	public List<String> getTextFromAllElemetnsByXPath(String xPath) {
		return getTextFromAllElemetns(By.xpath(xPath));
	}

	/**
	 * Returns a list of all attribute values' found by the locator
	 * 
	 * @param by
	 * @return list of attribute values found in the element
	 * @author amodak
	 * @date 9th June, 2015
	 */
	public List<String> getAttributeValueFromAllElements(By by, String attributeName) {
		List<CateredWebElement> elementList = findAllElements(by);
		List<String> attributeValueList = new ArrayList<String>();
		for (CateredWebElement element : elementList) {
			attributeValueList.add(element.getAttribute(attributeName));
		}
		return attributeValueList;
	}

	/**
	 * Returns a list of all attribute values' found by the class name
	 * 
	 * @param by
	 * @return list of attribute values found in the element
	 * @author amodak
	 * @date 9th June, 2015
	 */
	public List<String> getAttributeValueFromAllElementsByClassName(String className, String attributeName) {
		return getAttributeValueFromAllElements(By.className(className), attributeName);
	}

	/**
	 * Returns a boolean after checking if the text is present in the Li Tag
	 * 
	 * @param locator
	 * @return boolean
	 * @author mnaaz
	 * @date 2nd Dec, 2015
	 */
	public boolean verifyTextPresentLiTagByXpath(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		} else
			return false;

	}

	/**
	 * Returns a list of all attribute values' found by the class name
	 * 
	 * @param by
	 * @return list of attribute values found in the element
	 * @author amodak
	 * @date 9th June, 2015
	 */

	public String verifyTextPresentInHTML(String expectedText) {

		String bodyText = findElementByTagName("body").getText();

		if (!bodyText.contains(expectedText)) {
			if(!verifyTextWithSpacePresentInAnyTag(expectedText)){
			VerificationStatus.isStatusPass = false;
			VerificationStatus.isTextFound = false;
			captureScreenshot();
			return expectedText;
			}
			else{
				VerificationStatus.isTextFound = true;
			return "Text found";
			}
			// return new VerificationStatus(false, "[NOT FOUND]" + expectedText);
			/*
			 * captureScreenshot(); Reporter.log("Not present in HTML:" +
			 * expectedText); throw new AssertionError("[NOT FOUND]" + expectedText);
			 */

		} else {
			VerificationStatus.isTextFound = true;
			return "Text found";
		}

		// return new VerificationStatus(true, "Link passed");
	}

	/**
	 * @author mnaaz
	 */
	public boolean verifyTextContainInAnyTag(String text) {

		boolean found = false;
		WebElement element;
		try {
			String xPath = "//*[contains(text(),'" + text + "') or contains(@title,'" + text + "') ]";
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(
			        ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
			element = driver.findElement(By.xpath(xPath));
			if (element.isDisplayed())
			{
				found = true;
			}
		} catch (Exception e) {
			captureScreenshot();

			Reporter.log("element  not found");
		}
		return found;

	}

	// To verify presence of text containing br tag
	public boolean verifyTextWithSpacePresentInAnyTag(String text) {
		String xPath;
		if (text.contains("'")) {
			xPath = "//*[contains(.,\"" + text + "\")]";
		}
		xPath = "//*[contains(.,'" + text + "')]";
		boolean found;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1, 500);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			found = true;
		} catch (WebDriverException e) {
			Reporter.log(xPath + " not present");
			captureScreenshot();
			found = false;
		}
		return found;
	}

	
	public boolean verifyTextPresentInAnyTag(String text) {
		String xPath = "//*[text()='" + text + "']";

		WebElement element = driver.findElement(By.xpath(xPath));
		if (element.isDisplayed())
			return true;
		else
			return false;
	}

	public void clickAndVerifyElementInvisible(By locatorOfClick, By locatorOfVerificationElement) {
		boolean status = false;
		int count = 0;
		Exception exp = null;
		while (!status && count <= 5) {
			count++;
			try {

				System.out.println("before click");
				findElement(locatorOfClick).click();
				WebDriverWait wait = new WebDriverWait(driver, 1, 500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(locatorOfVerificationElement));
				System.out.println("element is invisible now ");
				status = true;
			} catch (WebDriverException e) {
				logger.debug("Attempt: " + count);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				Reporter.log("Element is still visible located by  " + locatorOfClick.toString());
				captureScreenshot();
				throw e;
			}
		}
		if (!status) {
			Reporter.log("Element is still visible located by " + locatorOfClick.toString());
			captureScreenshot();
			throw new WebDriverException(exp);
		}

	}

	public boolean verifyElementIsPresentByXpath(String xPath) {
		boolean flag;
		try{
		
		 driver.findElement(By.xpath(xPath));
		 flag=true;
		return flag;
		}
		catch(Exception e){
			flag=false;
					return flag;
		}
	}

}
