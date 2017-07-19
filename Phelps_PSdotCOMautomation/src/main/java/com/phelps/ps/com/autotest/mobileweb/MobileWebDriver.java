package com.phelps.ps.com.autotest.mobileweb;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.phelps.ps.com.autotest.utils.BrowserType;
import com.phelps.ps.com.autotest.utils.DeviceType;
import com.phelps.ps.com.autotest.web.CateredWebDriver;

public class MobileWebDriver extends CateredWebDriver {
	final static Logger logger = Logger.getLogger(MobileWebDriver.class);

	public MobileWebDriver(DeviceType deviceType, BrowserType browserType) throws MalformedURLException {
		super("");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (deviceType) {
		case ANDROID:
			capabilities.setCapability("device", "selendroid");
			capabilities.setCapability("deviceName", "Phelps1 Agency Galaxy");
			//capabilities.setCapability("deviceName", "Muzfera Naaz (Galaxy S5)");
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			capabilities.setCapability(CapabilityType.VERSION, "6.0.1");
			capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
			switch (browserType) {
			case chrome:
				capabilities.setCapability("app", "Chrome");
				break;
			case android:
				// Yet to be implemented
				break;
			}
			break;
		case IOS:
			// Yet to be implemented
			 capabilities.setCapability("platformName", "iOS");
			 capabilities.setCapability("deviceName", "iPhone");
			 capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
			 capabilities.setCapability(CapabilityType.VERSION, "8.4");
			// driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),
			 //  capabilities);
			break;
		}

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

}
