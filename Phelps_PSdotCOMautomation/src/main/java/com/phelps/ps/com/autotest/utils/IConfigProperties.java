package com.phelps.ps.com.autotest.utils;

import java.util.HashMap;
import java.util.Map;

public interface IConfigProperties {
	Map<String, Integer> waitTimeMap = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 67597869875987598L;

		{
			put("devpsstaging.phelpsagency.com", Integer.parseInt("30"));
			put("psstaging.phelpsagency.com", Integer.parseInt("30"));
			put("test-web.ps.com", Integer.parseInt("15"));
			put("stage-mweb.ps.com", Integer.parseInt("30"));
			put("stage-web.ps.com", Integer.parseInt("30"));
			put("www.publicstorage.com", Integer.parseInt("30"));
			put("wc2psstaging.phelpsagency.com", Integer.parseInt("15"));
			put("wc2psmobilestaging.phelpsagency.com", Integer.parseInt("20"));
			put("qa-web.ps.com", Integer.parseInt("30"));
			put("m.publicstorage.com", Integer.parseInt("20"));
			put("private-web.ps.com", Integer.parseInt("20"));
			put("test-mweb2.ps.com", Integer.parseInt("30"));
			put("deppsstaging.phelpsagency.com", Integer.parseInt("30"));
			
		}
	};
	String envName = PropertyLoader.loadProperty("env.name").get();
	long maxWaitTime = waitTimeMap.get(envName);// Long.parseLong(PropertyLoader.loadProperty("max.wait.time").get());
	String testPackageName = PropertyLoader.loadProperty("test.package.name").get();
	String browserType = PropertyLoader.loadProperty("browser.type").get();

}
