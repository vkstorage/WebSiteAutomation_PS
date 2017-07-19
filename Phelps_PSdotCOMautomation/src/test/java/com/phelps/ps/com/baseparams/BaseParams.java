package com.phelps.ps.com.baseparams;

import java.util.HashMap;
import java.util.Map;

import com.phelps.ps.com.autotest.utils.PropertyLoader;

public interface BaseParams {
	/**
	 * base Url of application under test
	 */
	public final static String baseUrl = PropertyLoader.loadProperty("web.url").get();

	static String toEmailIds = PropertyLoader.loadProperty("to.email.id").get();
	static String emailNotification = PropertyLoader.loadProperty("email.notification").get();

	static Map<String, String> envTestData = new HashMap<String, String>();

}
