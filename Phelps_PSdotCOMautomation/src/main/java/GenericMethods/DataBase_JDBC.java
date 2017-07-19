package GenericMethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class DataBase_JDBC 
{
	public ExtentTest logger;
	public static String executeSQLQuery(String sqlQuery) {


		String resultValue = "";
		ResultSet rs;

		//To connect with QA Database
		//String connectionUrl=Generic_Class.getPropertyValue("DB_Connection_Url_AUT");
		String connectionUrl=Generic_Class.getPropertyValue("DB_Connection_Url_QA");


		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			//Connection conn = DriverManager.getConnection("jdbc:sqlserver://HOSP_SQL1.company.com;user=name;password=abcdefg;database=Test");

			Connection connection = DriverManager.getConnection(connectionUrl);
			if(connection!=null) {
				Reporter.log("Connected to the database...",true);
			}else {
				Reporter.log("Database connection failed ",true);
			}
			Statement stmt = connection.createStatement();
			rs=stmt.executeQuery(sqlQuery);

			try {
				while(rs.next()){
					resultValue = rs.getString(1).toString();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			connection.close();

		}catch(Exception sqlEx) {

		}
		
		return resultValue;
	}


	public static ArrayList<String> executeSQLQuery_List(String sqlQuery) {


		ArrayList<String> resultValue = new ArrayList<String>();
		ResultSet resultSet;

		//To connect with QA Database
		//To connect with QA Database
		//String connectionUrl=Generic_Class.getPropertyValue("DB_Connection_Url_AUT");
		String connectionUrl=Generic_Class.getPropertyValue("DB_Connection_Url_QA");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(connectionUrl);
			if(connection!=null) {
				Reporter.log("Connected to the database...",true);
			}else {
				Reporter.log("Database connection failed ",true);
			}
			Statement statement = connection.createStatement();
			resultSet=statement.executeQuery(sqlQuery);
			String reqValue;
			try {


				while(resultSet.next()){          

					int columnCount = resultSet.getMetaData().getColumnCount();
					for(int k=1; k<=columnCount; k++){
						reqValue = resultSet.getString(k);
						resultValue.add(reqValue); 

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (Exception ex) {

					}
				}
			}

		}catch(Exception sqlEx) {

		}
		return resultValue;
	}
	
	public static ArrayList<String> executeSQLQuery_List_SecondCol(String sqlQuery) {


		ArrayList<String> resultValue = new ArrayList<String>();
		ResultSet resultSet;

		//To connect with QA Database
		//To connect with QA Database
		//String connectionUrl=Generic_Class.getPropertyValue("DB_Connection_Url_AUT");
		String connectionUrl=Generic_Class.getPropertyValue("DB_Connection_Url_QA");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(connectionUrl);
			if(connection!=null) {
				Reporter.log("Connected to the database...",true);
			}else {
				Reporter.log("Database connection failed ",true);
			}
			Statement statement = connection.createStatement();
			resultSet=statement.executeQuery(sqlQuery);
			String reqValue;
			try {


				while(resultSet.next()){          

						reqValue = resultSet.getString(2);
						resultValue.add(reqValue); 

					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (Exception ex) {

					}
				}
			}

		}catch(Exception sqlEx) {
			System.out.println("Exception Occured: "+sqlEx);
		}
		return resultValue;
	}

	public static void main(String[] args) {
		System.out.println(executeSQLQuery("select EmployeeID from reservation where reservationID='112637453'"));
	}
	//Venkat 04/06/2017: This Function set IpAddress to particular SiteId and particular ParamCode. Once set particular siteID, It will check
	// whether it set to correct siteId and returns true or false
	 public static boolean ipAssigned(String IpAddress, String SiteID)
	 {
	  //Setting 'xxx' in SiteParamter for particular IpAddress
	  String Paramcode="";
	  String updateQuery="update siteparameter set paramvalue='xxx' where paramvalue='"+IpAddress+"'";
	        DataBase_JDBC.executeSQLQuery(updateQuery);
	        
	        //set Paramcode value based on IpAddress
	        String IP = IpAddress.substring(9, IpAddress.length());
	        int ipNum = Integer.parseInt(IP);
	        switch (ipNum) {
	  case 54:
	  Paramcode = "IP_Computer_VM2";
	   break;
	  case 53:
	   Paramcode = "IP_Computer_VM1";
	    break;
	  case 110:
	   Paramcode = "IP_Computer_110";
	    break;
	  case 109:
	   Paramcode = "IP_Computer_109";
	    break;
	  case 25:
	   Paramcode = "IP_Computer_VM25";
	    break;
	  case 55:
	   Paramcode = "IP_Computer_VM55";
	   break;
	  default:
	   Paramcode = "IP_Computer_First";
	    break;
	     
	   
	   }
	        //Verify for VM record for siteID in SuteParameter table and then insert if not found
	        String verifyRowinSiteParameter="select * from siteparameter where paramcode='"+Paramcode+"' and siteid='"+SiteID+"'";
	        String result = DataBase_JDBC.executeSQLQuery(verifyRowinSiteParameter);
	        
	        if(result.isEmpty()){
	        	String insertRowinSiteParameter="INSERT INTO SiteParameter (LastUpdate, SiteID, ParamCode, ParamValue, RecordDateTime)"+
	        									"SELECT getUtcdate(), '"+SiteID+"','"+Paramcode+"','', getutcdate()";
		        DataBase_JDBC.executeSQLQuery(insertRowinSiteParameter);
	        	
	        }
	        
	        //Setting IpAddress to particular siteID and Paramcode
	        String updateQueryIpAdress="update siteparameter set paramvalue='"+IpAddress+"' where paramcode='"+Paramcode+"' and siteid='"+SiteID+"'";
	        DataBase_JDBC.executeSQLQuery(updateQueryIpAdress);
	        
	        //Get SiteId after setting to particular siteID
	        updateQuery=" select siteid from siteparameter where paramvalue='"+IpAddress+"'";
	        String siteNum = DataBase_JDBC.executeSQLQuery(updateQuery);
	        if(siteNum.equalsIgnoreCase(SiteID))
	        {
	         return true;
	        }
	        else
	        {
	         return false;
	        }
	        
	        
	        
	        
	 }
	 public static void SiteIdAssignBasedOnState()
	 {
		 String ipAdress=Generic_Class.getIPAddress();
			String siteIDQuery="select top 1 siteid from site s "
	 +"join address ad on ad.addressid=s.physicaladdressid "
	 +"where ad.statecode='"+Generic_Class.getPropertyValue("StateLocation")+"'"
	 + " and s.isactive=1 ";
			
			String siteID=DataBase_JDBC.executeSQLQuery(siteIDQuery);
			DataBase_JDBC.ipAssigned(ipAdress, "90");
			System.out.println("IP assigned to site belongs to particular state");
	 }
}


