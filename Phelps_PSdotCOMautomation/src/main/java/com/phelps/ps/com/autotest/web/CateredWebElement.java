package com.phelps.ps.com.autotest.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.phelps.ps.com.autotest.utils.IConfigProperties;

public class CateredWebElement implements WebElement, IConfigProperties {

	final static Logger logger = Logger.getLogger(CateredWebElement.class);

	public RemoteWebElement getWebElement() {
		return this.webElement;
	}

	public CateredWebDriver getCateredWebDriver() {
		return this.cateredWebDriver;
	}

	private RemoteWebElement webElement;
	private final By byLocator;
	private final CateredWebDriver cateredWebDriver;

	public CateredWebElement(CateredWebDriver cateredWebDriver, By byLocator, WebElement webElement) {
		this.webElement = (RemoteWebElement) webElement;
		this.cateredWebDriver = cateredWebDriver;
		this.byLocator = byLocator;
	}

	public By getByLocator() {
		return byLocator;
	}

	@Override
	public void clear() {
		boolean status = false;
		int count = 0;
		Throwable thr = null;
		while (!status && count <= 5) {
			try {
				count++;
				webElement.clear();
				status = true;
			} catch (InvalidElementStateException e) {
				logger.debug("Attempt: " + count);
				thr = e;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				thr = e;
			}
		}
		if (!status) {
			Reporter.log("Unable to clear text in the element by " + this.getByLocator().toString());
			this.getCateredWebDriver().captureScreenshot();
			throw new WebDriverException(thr);
		}
	}

	@Override
	public void click() {
		boolean status = false;
		int count = 0;
		Exception exp = null;
		while (!status && count <= 10) {
			count++;
			try {
				WebDriverWait wait = new WebDriverWait(this.cateredWebDriver, maxWaitTime, 500);
				wait.until(ExpectedConditions.elementToBeClickable(this.getByLocator()));
				webElement.click();
				status = true;
			} catch (WebDriverException e) {
				logger.debug("Attempt: " + count);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				Reporter.log("Unable to click element by " + this.getByLocator().toString());
				this.getCateredWebDriver().captureScreenshot();
				throw e;
			}
		}
		if (!status) {
			Reporter.log("Unable to click element by " + this.getByLocator().toString());
			this.getCateredWebDriver().captureScreenshot();
			throw new WebDriverException(exp);
		}
	}

	@Override
	public CateredWebElement findElement(By loc) {
		WebElement innerElement = webElement.findElement(loc);
		((JavascriptExecutor) cateredWebDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", innerElement);

		return new CateredWebElement(cateredWebDriver, loc, innerElement);
	}

	@Override
	public List<WebElement> findElements(By loc) {
		return webElement.findElements(loc);
	}

	@Override
	public String getAttribute(String atr) {
		long maxWaitTimeInMilis = maxWaitTime * 1000;

		String innerText = webElement.getAttribute(atr);
		try {
			while (innerText.isEmpty() && maxWaitTimeInMilis > 0) {

				Thread.sleep(500);
				logger.debug("Time remaining " + maxWaitTimeInMilis);
				// System.out.println("Time remaining " + maxWaitTimeInMilis);
				maxWaitTimeInMilis = maxWaitTimeInMilis - 500;
				innerText = webElement.getAttribute(atr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return innerText;
	}

	@Override
	public String getCssValue(String atr) {
		return webElement.getCssValue(atr);
	}

	@Override
	public Point getLocation() {
		return webElement.getLocation();
	}

	@Override
	public Dimension getSize() {
		return webElement.getSize();
	}

	@Override
	public String getTagName() {
		return webElement.getTagName();
	}

	@Override
	public String getText() {
		return webElement.getText();
	}

	public String readInnerText() {
		long maxWaitTimeInMilis = maxWaitTime * 1000;
		boolean textRead = false;
		String innerText = "";
		while (!textRead && maxWaitTimeInMilis > 0 && innerText.isEmpty()) {
			try {
				innerText = webElement.getText();
				textRead = true;
			} catch (Exception e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				maxWaitTimeInMilis = maxWaitTimeInMilis - 500;
				logger.info("Time remaining..." + maxWaitTimeInMilis);
				// System.out.println("Time remaining..." + maxWaitTimeInMilis);
			}
		}
		return innerText;
	}

	@Override
	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		return webElement.isEnabled();
	}

	@Override
	public boolean isSelected() {
		return webElement.isSelected();
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		int attemp = 1;
		boolean typeSucess = false;
		while (!typeSucess && attemp <= 5) {
			try {
				WebDriverWait wait = new WebDriverWait(this.cateredWebDriver, maxWaitTime, 500);
				wait.until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()));
				attemp++;
				webElement.sendKeys(arg0);
				typeSucess = true;
			} catch (Exception e) {
				if (attemp > 5) {
					Reporter.log("Unable to enter text into element by " + this.getByLocator().toString());
					this.getCateredWebDriver().captureScreenshot();
					throw e;
				}
			}
		}
	}

	@Override
	public void submit() {
		webElement.submit();
	}

	public void selectOption() {
		boolean status = false;
		int count = 0;
		Exception exp = null;
		while (!status && count <= 10) {
			count++;
			try {
				webElement.click();
				status = true;
			} catch (ElementNotVisibleException e) {
				logger.debug("Attempt:" + count);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				exp = e;
				logger.debug("Attempt:" + count);
			}
		}
		if (!status) {
			Reporter.log("Unable to click element by " + this.getByLocator().toString());
			this.getCateredWebDriver().captureScreenshot();
			throw new WebDriverException(exp);
		}

	}

	public void scrollDownwards(int limit) {
		Actions dragger = new Actions(cateredWebDriver.getDriver());
		dragger.moveToElement(webElement).clickAndHold().moveByOffset(0, limit).release().perform();
	}

	/**
	 * Find all elements with the element by the locator specified
	 * 
	 * @param loc
	 * @return A list of WebElements wrapped in CaterdWebElement
	 */
	public List<CateredWebElement> findAllElements(By loc) {
		List<WebElement> innerElements = webElement.findElements(loc);
		List<CateredWebElement> wrappedElements = new ArrayList<CateredWebElement>();
		for (WebElement innerElement : innerElements) {
			((JavascriptExecutor) cateredWebDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", innerElement);
			wrappedElements.add(new CateredWebElement(cateredWebDriver, loc, innerElement));
		}
		return wrappedElements;
	}

	public List<CateredWebElement> findAllElementsByXpath(String xpath) {
		return findAllElements(By.xpath(xpath));
	}

	public List<CateredWebElement> findAllElementsByCss(String css) {
		return findAllElements(By.cssSelector(css));
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {

		return webElement.getScreenshotAs(arg0);
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

}
// >>>>>>> refs/remotes/origin/maste
