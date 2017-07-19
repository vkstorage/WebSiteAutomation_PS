package com.phelps.ps.com.testsuite;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.phelps.ps.com.actions.AccountCreationActions;
import com.phelps.ps.com.actions.CLPActions;
import com.phelps.ps.com.actions.CommonActions;
import com.phelps.ps.com.actions.ECIDetailsActions;
import com.phelps.ps.com.actions.ECI_RDP_ConfActions;
import com.phelps.ps.com.actions.ECI_RetConfActions;
import com.phelps.ps.com.actions.ECI_ReturnsActions;
import com.phelps.ps.com.actions.HomeActions;
import com.phelps.ps.com.actions.HomePageRefreshActions;
import com.phelps.ps.com.actions.HowSelfStorageWorksActions;
import com.phelps.ps.com.actions.InsuranceActions;
import com.phelps.ps.com.actions.KnowledgeGraphActions;
import com.phelps.ps.com.actions.LoginActions;
import com.phelps.ps.com.actions.PLPActions;
import com.phelps.ps.com.actions.PaymentsActions;
import com.phelps.ps.com.actions.Phase5Actions;
import com.phelps.ps.com.actions.RDP_Conf_HoldActions;
import com.phelps.ps.com.actions.ReservationCareActions;
import com.phelps.ps.com.actions.ReservationCareLoginActions;
import com.phelps.ps.com.actions.ReservationDetailsActions;
import com.phelps.ps.com.actions.ReviewActions;
import com.phelps.ps.com.actions.SearchResultsPageActions;
import com.phelps.ps.com.actions.SelfCareMyStorageUnitsActions;
import com.phelps.ps.com.actions.SelfStorageActions;
import com.phelps.ps.com.actions.SelfcareContactInfoActions;
import com.phelps.ps.com.actions.VideoPageActions;
//WC2
//import com.phelps.ps.com.actions.SelfcareMyReservationsDetailsActions;
import com.phelps.ps.com.actions.SelfcareSummaryActions;
import com.phelps.ps.com.actions.SpanishSRPActions;
import com.phelps.ps.com.autotest.web.CateredWebDriver;
import com.phelps.ps.com.basetest.BaseTest;
import com.phelps.ps.com.nevigation.Navigator;

public class BasicTestSuite extends BaseTest {
	protected static HomeActions homeActions;
	protected static Navigator navigator;
	protected static ReservationDetailsActions reservationDetailsActions;
	protected static ECIDetailsActions eciDetailsActions;
	protected static RDP_Conf_HoldActions rdp_Conf_HoldActions;
	protected static CommonActions commonActions;
	protected static SelfcareSummaryActions selfcareSummaryActions;
	// WC2
	// protected static SelfcareMyReservationsDetailsActions
	// selfcareMyReservationsActions;
	protected static LoginActions loginActions;
	protected static SelfcareContactInfoActions selfcareContactInfoActions;
	protected static HowSelfStorageWorksActions howSelfStorageWorksActions;
	protected static ECI_RDP_ConfActions eci_RDP_ConfActions;
	protected static ECI_ReturnsActions eci_ReturnsActions;
	protected static ECI_RetConfActions eci_RetConfActions;
	protected static SelfStorageActions selfStorageActions;
	protected static PaymentsActions paymentActions;
	protected static Phase5Actions phase5Actions;
	protected static InsuranceActions insuranceActions;
	protected static SearchResultsPageActions searchResultsPageActions;
	protected static PLPActions plpActions;
	protected static SelfCareMyStorageUnitsActions selfCareMyStorageUnitsActions;
	protected static ReviewActions reviewActions;
	protected static SpanishSRPActions spanishSRPActions;
	protected static KnowledgeGraphActions knowledgeGraphActions;
	protected static CLPActions clpActions;
	protected static VideoPageActions videoLandingPageActions;
	// WC2
	protected static ReservationCareActions reservationCareActions;
	protected static ReservationCareLoginActions reservationCareLoginActions;
	protected static AccountCreationActions accountCreationActions;
	protected SearchResultsPageActions searchResultsActions;
	// HomePage Refresh
	protected static HomePageRefreshActions homePageRefreshActions;
	Properties systemProperties;

	/*
	 * Search space
	 */
	String searchByZip = "IL";

	@BeforeTest(alwaysRun = true)
	public void loadEnvTestData() {
		try {
			HSSFSheet getSheet = null;
			HSSFWorkbook hssfWorkBook = null;
			FileInputStream file = new FileInputStream(new File("src/test/resources/UserData.xls"));
			hssfWorkBook = new HSSFWorkbook(file);
			getSheet = hssfWorkBook.getSheetAt(0);
			int getSheetLastRow = hssfWorkBook.getSheetAt(0).getLastRowNum();
			System.out.println("lastrow" + getSheetLastRow);
			int colIndex = 0;
			if (baseUrl.contains("devpsstaging.phelpsagency.com")) {
				colIndex = 1;
			} else if (baseUrl.contains("wc2psstaging.phelpsagency.com")) {
				colIndex = 6;
			} else if (baseUrl.contains("test-web2.ps.com")) {
				colIndex = 7;
			} else if (baseUrl.contains("qa-web.ps.com")) {
				colIndex = 3;
			} else if (baseUrl.contains("stage-web.ps.com")) {
				colIndex = 4;
			} else if (baseUrl.contains("m.publicstorage.com")) {
				colIndex = 9;
			}

			else if (baseUrl.contains("publicstorage.com")) {
				colIndex = 5;
			} else if (baseUrl.contains("psstaging.phelpsagency.com")) {
				colIndex = 2;
			} else if (baseUrl.contains("private-web.ps.com")) {
				colIndex = 8;
			}
			 else if (baseUrl.contains("deppsstaging.phelpsagency.com")) {
					colIndex =9 ;
				}
			for (int rowNum = 1; rowNum <= getSheetLastRow + 1; rowNum++) {
				if (getSheet.getRow(rowNum).getCell(0) != null && getSheet.getRow(rowNum).getCell(colIndex) != null) {
					System.out.println(rowNum + ":" + getSheet.getRow(rowNum).getCell(0).toString() + "----"
							+ getSheet.getRow(rowNum).getCell(colIndex).toString());
					envTestData.put(getSheet.getRow(rowNum).getCell(0).toString(),
							getSheet.getRow(rowNum).getCell(colIndex).toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void openBrowserBeforeClassTest(ITestContext testContext) {
		webAppDriver = new CateredWebDriver();
		webAppDriver.get(baseUrl);
		testLogger.info("Executing Test Suite Name: " + this.getClass().getSimpleName());
		// Initiate navigation and actions
		homeActions = new HomeActions(webAppDriver);
		navigator = new Navigator(webAppDriver);
		reservationDetailsActions = new ReservationDetailsActions(webAppDriver);
		eciDetailsActions = new ECIDetailsActions(webAppDriver);
		rdp_Conf_HoldActions = new RDP_Conf_HoldActions(webAppDriver);
		commonActions = new CommonActions(webAppDriver);
		selfcareSummaryActions = new SelfcareSummaryActions(webAppDriver);
		// WC2
		// selfcareMyReservationsActions = new
		// SelfcareMyReservationsDetailsActions(webAppDriver);
		loginActions = new LoginActions(webAppDriver);
		selfcareContactInfoActions = new SelfcareContactInfoActions(webAppDriver);
		howSelfStorageWorksActions = new HowSelfStorageWorksActions(webAppDriver);
		eci_RDP_ConfActions = new ECI_RDP_ConfActions(webAppDriver);
		eci_ReturnsActions = new ECI_ReturnsActions(webAppDriver);
		eci_RetConfActions = new ECI_RetConfActions(webAppDriver);
		selfStorageActions = new SelfStorageActions(webAppDriver);
		paymentActions = new PaymentsActions(webAppDriver);
		phase5Actions = new Phase5Actions(webAppDriver);
		insuranceActions = new InsuranceActions(webAppDriver);
		searchResultsPageActions = new SearchResultsPageActions(webAppDriver);
		plpActions = new PLPActions(webAppDriver);
		selfCareMyStorageUnitsActions = new SelfCareMyStorageUnitsActions(webAppDriver);
		reviewActions = new ReviewActions(webAppDriver);
		spanishSRPActions = new SpanishSRPActions(webAppDriver);
		knowledgeGraphActions = new KnowledgeGraphActions(webAppDriver);
		clpActions = new CLPActions(webAppDriver);
		videoLandingPageActions = new VideoPageActions(webAppDriver);
		searchResultsActions = new SearchResultsPageActions(webAppDriver);

		// WC2
		reservationCareActions = new ReservationCareActions(webAppDriver);
		reservationCareLoginActions = new ReservationCareLoginActions(webAppDriver);
		accountCreationActions = new AccountCreationActions(webAppDriver);
		homePageRefreshActions = new HomePageRefreshActions(webAppDriver);
		systemProperties = System.getProperties();
		systemProperties.setProperty("https.proxyHost", "proxy.corp.globant.com");
		systemProperties.setProperty("https.proxyPort", "3128");

	}

	@BeforeMethod(alwaysRun = true)
	public void printTestcaseNameBeforeMethod(Method test) {
		testLogger.info("Executing Testcase Name: " + test.getName());
	}
}
