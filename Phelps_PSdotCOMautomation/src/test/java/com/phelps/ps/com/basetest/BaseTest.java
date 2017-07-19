package com.phelps.ps.com.basetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.phelps.ps.com.autotest.utils.EmailService;
import com.phelps.ps.com.autotest.utils.FileGenerator;
import com.phelps.ps.com.autotest.utils.PropertyLoader;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.baseparams.BaseParams;
import com.phelps.ps.com.dataprovider.FileReader;

public class BaseTest implements BaseParams {
	public final static Logger testLogger = Logger.getLogger(BaseTest.class);
	
	/*
	 * This unique string is used to give unique name in tests
	 */
	protected String uniqueID = (new UniqueTestId()).id;

	/*
	 * A default browser for Web application testing
	 */
	protected static CateredWebDriver webAppDriver;

	/*
	 * Run Status
	 */
	private static int numberOfPassedTests;
	private static int numberOfFailedTests;
	private static int numberOfSkippedTests;

	/**
	 * Test
	 * 
	 * @param testResult
	 */
	@AfterMethod(alwaysRun = true)
	public void printTestcaseNameAfterMethod(ITestResult testResult) {
		String status = null;
		if (testResult.getStatus() == 2) {
			status = "Failed";
		} else if (testResult.getStatus() == 1) {
			status = "Passed";
		} else if (testResult.getStatus() == 3) {
			status = "Skipped";
		}
		testLogger.info("Completed Testcase Name: " + testResult.getName() + " Status: " + status);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		testLogger.info("Completed Test Suite Name: " + this.getClass().getSimpleName());
		if (webAppDriver != null) {
			webAppDriver.quit();
		}
	}

	/**
	 * This will create an index.html file in output folder to list down the test
	 * runs.
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 * @throws AddressException
	 */
	@AfterSuite(alwaysRun = true)
	public void createIndexFile(ITestContext testContext) throws IOException, AddressException, MessagingException {
		String root = PropertyLoader.loadProperty("output.path").get();
		testLogger.info("report path " + root);
		int pathLevel = root.split("/").length;
		String reportFolder = root.split("/")[pathLevel - 1];
		numberOfPassedTests = testContext.getPassedTests().size();
		numberOfFailedTests = testContext.getFailedTests().size();
		numberOfSkippedTests = testContext.getSkippedTests().size();
		String summaryFileName = "runSummary.csv";
		String summaryFilePath = root + "/..";
		String summaryReport = reportFolder + "," + numberOfPassedTests + "," + numberOfFailedTests + "," + numberOfSkippedTests;
		FileGenerator.appendTextToBeginingOfFile(summaryFilePath, summaryFileName, summaryReport);
		testLogger.info(reportFolder);
		testLogger.info("Passed: " + numberOfPassedTests);
		testLogger.info("Failed: " + numberOfFailedTests);
		testLogger.info("Skipped: " + numberOfSkippedTests);
		FileGenerator.generateBarGraphIndexHTML(summaryFilePath);
		String parentFolder = root + "/../..";
		for (int i = 1; i < pathLevel - 1; i++) {
			FileGenerator.generateIndexHTML(parentFolder.toString());
			parentFolder = parentFolder + "/..";
		}
		// Creating message body
		String messageBody = getCompleteEmailMessage(testContext);

		// Write message to file
		String emailSubject = "Automation status of run on " + reportFolder;
		// This is used if automation running on VPN then write the details in file
		// then send the email by other program.
		String emailFile = EmailService.writeEmailIntoFile(toEmailIds, emailSubject, messageBody, root);
		// Store the file path in file
		File emailContextFilePath = new File("emailFilePath.txt");
		FileWriter fw = new FileWriter(emailContextFilePath);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(emailFile);
		bw.close();

		if (emailNotification.equalsIgnoreCase("yes")) {
			System.out.println(messageBody);
			EmailService.generateAndSendEmail(Arrays.asList(toEmailIds.split(",")), emailSubject, messageBody);
		}
	}

	/**
	 * Creates the complete HTML email body
	 * 
	 * @param testContext
	 * @return
	 */
	public String getCompleteEmailMessage(ITestContext testContext) {
		String newHTMLMessage = "";
		try {
			newHTMLMessage = FileReader.readFile("src/main/resources/emailTemplate.txt");
			System.out.println("Raw file" + newHTMLMessage);
			newHTMLMessage = newHTMLMessage.replace("Passed#", String.valueOf(numberOfPassedTests));
			newHTMLMessage = newHTMLMessage.replace("Failed#", String.valueOf(numberOfFailedTests));
			newHTMLMessage = newHTMLMessage.replace("Skipped#", String.valueOf(numberOfSkippedTests));
			newHTMLMessage = newHTMLMessage.replace("BaseURL", baseUrl);

		} catch (IOException e) {
			System.out.println("exception");
		}

		String message = "";
		message = message + "Passed: " + testContext.getPassedTests().size() + "<br>";
		message = message + "<br>Failed: " + testContext.getFailedTests().size() + "<br>";
		// Appending name of failed test methods into mail body
		List<String> methodNames = new LinkedList<String>();
		for (ITestNGMethod name : testContext.getFailedTests().getAllMethods()) {
			String methodName = name.getConstructorOrMethod().toString()
					.substring(name.getConstructorOrMethod().toString().lastIndexOf(" "));

			methodNames.add(methodName);
		}
		newHTMLMessage = newHTMLMessage.replace("FailedMethodsList", "<font color=\"red\">" + testSuiteMethodExtractor(methodNames)
				+ "</font>" + "<br>");
		message = message + "<font color=\"red\">" + testSuiteMethodExtractor(methodNames) + "</font>" + "<br>";
		message = message + "<br>Skipped: " + testContext.getSkippedTests().size() + "<br>";
		// Appending name of skipped test methods into mail body
		methodNames.clear();
		for (ITestNGMethod name : testContext.getSkippedTests().getAllMethods()) {
			String methodName = name.getConstructorOrMethod().toString()
					.substring(name.getConstructorOrMethod().toString().lastIndexOf(" "));
			methodNames.add(methodName);
		}

		message = message + "<font color=\"GoldenRod\">" + testSuiteMethodExtractor(methodNames) + "</font>" + "<br>";
		newHTMLMessage = newHTMLMessage.replace("SkippedMethodsList", "<font color=\"GoldenRod\">"
				+ testSuiteMethodExtractor(methodNames) + "</font>" + "<br>");
		return message;
	}

	/**
	 * Create the Pie Chart for summary
	 * 
	 * @param testContext
	 * @return
	 */
	private String getPieChartHTML(ITestContext testContext) {
		String chartValues = "['Testcases', 'numbers'],";
		chartValues = chartValues + "['Passed'," + testContext.getPassedTests().size() + "],";
		chartValues = chartValues + "['Failed'," + testContext.getFailedTests().size() + "],";
		chartValues = chartValues + "['Skipped'," + testContext.getSkippedTests().size() + "]";

		String pieChartHTML = "";
		try {
			pieChartHTML = FileReader.readFile("src/test/resources/pieChartScript.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		pieChartHTML = pieChartHTML.replace("ChartData", chartValues);
		System.out.println(pieChartHTML);
		return pieChartHTML;
	}

	private String testSuiteMethodExtractor(List<String> tests) {
		String htmlString = "<ul>";
		Collections.sort(tests);
		Map<String, List<String>> map = new TreeMap<String, List<String>>();
		int count = 1;
		Iterator<String> itr = tests.iterator();
		while (itr.hasNext()) {
			Object element = itr.next();
			String str = (String) element;
			String[] parts = str.split("TestSuite.");
			String testSuiteName = parts[0].concat("TestSuite");
			String methodName = "&nbsp;&nbsp;&nbsp;&nbsp;" + count + ". " + parts[1];
			if (map.get(testSuiteName) == null) {
				map.put(testSuiteName, new ArrayList<String>());
			}
			map.get(testSuiteName).add(methodName);
			count++;
		}

		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			htmlString = htmlString + "<li><b>" + key + "(" + entry.getValue().size() + ")" + "</b></li>";
			List<String> values = entry.getValue();
			for (String method : values) {
				htmlString = htmlString + method + "<br>";
			}
		}
		return htmlString + "<ul>";
	}
}
