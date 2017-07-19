package com.phelps.ps.com.dataprovider;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.phelps.ps.com.autotest.utils.ExcelFileReader;

public class TestDataProvider {

	@DataProvider(name = "homepagelinkprovider")
	public static Iterator<Object[]> workLoadFileNames() {
		String filePath = "src/test/resources/HomePageRefreshLinks.csv";
		return getData(filePath);
	}

	@DataProvider(name = "homepagelinkproviderNewWindow")
	public static Iterator<Object[]> workLoadFileNames2() {
		String filePath = "src/test/resources/HomePageRefreshNewWindowLinks.csv";
		return getData(filePath);
	}
	
	@DataProvider(name = "InvestorRelationlinkprovider")
	public static Iterator<Object[]> workLoadFileNames1() {
		String filePath = "src/test/resources/InvestorRelationsPageLinks_Copy.csv";
		return getData(filePath);
	}

	@DataProvider(name = "PropertySizeNameProvider")
	public static Iterator<Object[]> propertySizeNameProvider() {
		String filePath = "src/test/resources/UnitSizeLinkNames.txt";
		return getData(filePath);
	}

	@DataProvider(name = "SearchCriteriaData")
	public static Iterator<Object[]> SearchCriteriaData() {
		String filePath = "src/test/resources/SearchCriteria.txt";
		return getData(filePath);
	}

	@DataProvider(name = "PLP_URLDetails")
	public static Iterator<Object[]> getPLP_URLDetails() {
		 String filePath = "src/test/resources/PropertyPhotosPLP.xls";
		//String filePath = "src/test/resources/PropertyPhotosPLP_copy_09_Dec.xls";
		return FileReader.getDataFromExcel(filePath);
	}

	@DataProvider(name = "LocationURLDetails")
	public static Iterator<Object[]> getLocationURLs() {
		//String filePath = "src/test/resources/LocationsUrl.csv";
		String filePath = "src/test/resources/LocationsUrl.csv";
		return getData(filePath);
	}

	@DataProvider(name = "StateNamesProvider")
	public static Iterator<Object[]> getStateNames() {
		String filePath = "src/test/resources/StateNames.csv";
		return getData(filePath);
	}

	@DataProvider(name = "CityNamesProvider")
	public static Iterator<Object[]> getCityNames() {
		String filePath = "src/test/resources/CityNames.csv";
		File stateCityFile = new File(filePath);
		List<String> testData = FileReader.getFileContentAsList(stateCityFile.getAbsolutePath());
		List<Object[]> actualTestData = new ArrayList<Object[]>();
		for (String stateCitys : testData) {
			Object[] stateCitiesArray = stateCitys.split(",");
			for (int i = 2; i < stateCitiesArray.length; i++) {
				actualTestData.add(new Object[] { stateCitiesArray[0] + "," + stateCitiesArray[1] + "," + stateCitiesArray[i] });
			}
		}
		return actualTestData.iterator();
	}

	@DataProvider(name = "SRPProvider")
	public static Iterator<Object[]> getSRPData() {
		String filePath = "src/test/resources/SRPData.csv";
		return getData(filePath);
	}

	/**
	 * Reads data from file
	 * 
	 * @param filePath
	 * @return
	 */
	public static Iterator<Object[]> getData(String filePath) {
		File dataFile = new File(filePath);
		List<String> testData = FileReader.getFileContentAsList(dataFile.getAbsolutePath());
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (String userData : testData) {
			dataToBeReturned.add(new Object[] { userData });
		}
		return dataToBeReturned.iterator();
	}

	/**
	 * 
	 * @return
	 */
	@DataProvider(name = "CLPDataProvider")
	public static Iterator<Object[]> contentGatherDataProvider() {

		// String filePath = "src/test/resources/ContentGatherMap.xlsx";
		Object dataIn2DArray[][] = ExcelFileReader.readExcel("src/test/resources", "CLPPageURLs_Sample_1.xlsx", "Sheet1", "2");
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (int i = 0; i < dataIn2DArray.length; i++) {
			dataToBeReturned.add(dataIn2DArray[i]);
		}
		return dataToBeReturned.iterator();
	}

	@DataProvider(name = "ReviewMobilePropertiesProvider")
	public static Iterator<Object[]> getReviewProperties() {
		Object dataIn2DArray[][] = com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources",
				"reviewsgmapsYelpsurlsMobile_06Jan17.xlsx", "Sheet1", "2");
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (int i = 0; i < dataIn2DArray.length; i++) {
			dataToBeReturned.add(dataIn2DArray[i]);
		}
		return dataToBeReturned.iterator();
	}

	
	@DataProvider(name="ReviewsLessThanTenPropertiesProvider")
	public static Iterator<Object[]> getLessThanTenReviewsPropertiesDesktop(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "reviewsgmapsurlsdesktop_sample_less_10.xlsx", "Sheet1", "2");
		List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
		for(int i=0;i < dataIn2DArray.length;i++){
			dataToBeReturned.add(dataIn2DArray[i]);
			}
		return dataToBeReturned.iterator();
	}
	
	@DataProvider(name = "StateNameProvider")
	public static Iterator<Object[]> getStateNameData() {
		String filePath = "src/test/resources/Copy of StateNames.csv";
		return getData(filePath);
	}
	
	@DataProvider(name="VideoLandingPageProvider")
	public static Iterator<Object[]> getVideoLandingPageURL(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "VideoPageURL.xlsx", "Sheet1", "2");
		List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
		for(int i=0;i < dataIn2DArray.length;i++){
			dataToBeReturned.add(dataIn2DArray[i]);
			}
		return dataToBeReturned.iterator();
	}
	
	@DataProvider(name="VideoPageProvider")
	public static Iterator<Object[]> getVideoPageTextProvider(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "VideoPageURL.xlsx", "Sheet2", "2");
		List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
		for(int i=0;i < dataIn2DArray.length;i++){
			dataToBeReturned.add(dataIn2DArray[i]);
			}
		return dataToBeReturned.iterator();
	}
	
	@DataProvider(name="VideoPageCopyProvider")
	public static Iterator<Object[]> getVideoPageCopyTextProvider(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "VideoLandingPage_Copy_22_sep.xlsx", "Sheet1", "2");
		List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
		for(int i=0;i < dataIn2DArray.length;i++){
			dataToBeReturned.add(dataIn2DArray[i]);
			}
		return dataToBeReturned.iterator();
	}
	
	
	@DataProvider(name="MobileVideoLandingPageProvider")
	public static Iterator<Object[]> getMobileVideoLandingPageURL(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "VideoPageURL.xlsx", "Sheet3", "2");
		List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
		for(int i=0;i < dataIn2DArray.length;i++){
			dataToBeReturned.add(dataIn2DArray[i]);
			}
		return dataToBeReturned.iterator();
	}
	
	@DataProvider(name="MobileVideoPageProvider")
	public static Iterator<Object[]> getMobileVideoPageTextProvider(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "VideoPageURL.xlsx", "Sheet4", "2");
		List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
		for(int i=0;i < dataIn2DArray.length;i++){
			dataToBeReturned.add(dataIn2DArray[i]);
			}
		return dataToBeReturned.iterator();
	}
	
	@DataProvider(name="SiteMapVideoProvider")
	public static Iterator<Object[]> getSiteMapvideoLinks(){
		Object dataIn2DArray[][]=com.phelps.ps.com.autotest.utils.ExcelFileReader.readExcel("src/test/resources", "VideoPageURL.xlsx", "sitelinks", "2");
	    List<Object[]> dataToBeReturned=new ArrayList<Object[]>();
	    for(int i=0;i < dataIn2DArray.length;i++){
	    	dataToBeReturned.add(dataIn2DArray[i]);
	    }
	    return dataToBeReturned.iterator();
	}
	
	@DataProvider(name="PLPContentProvider")
	public static Iterator<Object[]> PLPContentDataProvider() {

		// String filePath = "src/test/resources/ContentGatherMap.xlsx";
		Object dataIn2DArray[][] = ExcelFileReader.readExcel("src/test/resources", "PLPURLs -Apr.xlsx", "Sheet1", "2");
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (int i = 0; i < dataIn2DArray.length; i++) {
			dataToBeReturned.add(dataIn2DArray[i]);
		}
		return dataToBeReturned.iterator();
	}
	
	
}
