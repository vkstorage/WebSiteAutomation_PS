package GenericMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.testng.Reporter;

import com.opencsv.CSVReader;


public class Excel 
{


	public static int getRowNum(String path, int sheetNum)
	{
		int rowcount=0;
		try{
			FileInputStream fis= new FileInputStream(path);
			Workbook wb= WorkbookFactory.create(fis);
			rowcount= wb.getSheetAt(sheetNum).getLastRowNum();

		}
		catch(Exception e)
		{

		}
		return rowcount;



	}

	public static int getColNum(String path, int sheetNum,int rowNum)
	{
		int colcount=0;
		try{
			FileInputStream fis= new FileInputStream(path);
			Workbook wb= WorkbookFactory.create(fis);
			colcount= wb.getSheetAt(sheetNum).getRow(rowNum).getLastCellNum();
			//wb.getSheetAt(sheetNum).getRow(rowNum).

		}
		catch(Exception e)
		{

		}
		return colcount;



	}





	public static String getCellValue_ByIndex(String path, int sheetNum, int rowNum, int colNum)
	{
		String rowval=null;
		try{
			FileInputStream fis= new FileInputStream(path);
			Workbook wb= WorkbookFactory.create(fis);
			rowval= wb.getSheetAt(sheetNum).getRow(rowNum).getCell(colNum).toString();

		}
		catch(Exception e)
		{

		}


		return rowval;


	}

	//-----------------------------------------------------------------------------------------------
	//Method : getCellValue_ByColName
	//Description : This method is used to Retrieve the value from the excel sheet on the basis of test case name and header.
	//Author : Testing Team


	public static String getCellValue_ByColName(String path, int sheetNum, String TestCase_Name, String colName)
	{

		FileInputStream fis;
		Workbook wb=null;
		int rowcnt=0;
		int colcnt=0;
		try{
			fis= new FileInputStream(path);
			wb= WorkbookFactory.create(fis);
			int rowNum= wb.getSheetAt(sheetNum).getLastRowNum();
			for(int i=1; i<=rowNum;i++)
			{
				if(TestCase_Name.equalsIgnoreCase(wb.getSheetAt(sheetNum).getRow(i).getCell(1).toString()))
				{
					rowcnt=i;
					break;
				}

			}
			int colNum=wb.getSheetAt(sheetNum).getRow(1).getLastCellNum();
			for(int j=0; j<colNum; j++)
			{
				if(colName.equalsIgnoreCase(wb.getSheetAt(sheetNum).getRow(0).getCell(j).toString()));
				{
					colcnt=j;
					break;
				}
			}



		}
		catch(Exception e)
		{

		}

		return wb.getSheetAt(sheetNum).getRow(rowcnt).getCell(colcnt).toString();

	}



	//-----------------------------------------------------------------------------------------------
	//Method : setCellValue
	//Description : This method is used to set the value in the excel sheet
	//Author : Testing Team


	public static void setCellValue(String path, String sheet, int rowNum, int colNum, String Result)
	{
		try{
			FileInputStream fis= new FileInputStream(path);
			Workbook wb= WorkbookFactory.create(fis);
			Cell cell=wb.getSheet(sheet).getRow(rowNum).createCell(colNum);
			cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(Result);

			FileOutputStream fos= new FileOutputStream(path);
			wb.write(fos);
			fos.flush();
			fos.close();	

		}

		catch(Exception e)
		{

		}
	}    


	//-----------------------------------------------------------------------------------------------
	//Method : getCellValue_inlist
	//Description : This method is used to Retrieve the value from the excel sheet on the basis of test case name
	//Author : Testing Team


	public static Object[][] getCellValue_inlist(String path_excel,String moduleName,String sheetname, String TestCase_Name)
	{

		FileInputStream fis;
		Workbook wb=null;
		int rowcnt=1;
		int colcnt=0;

		try{
			fis= new FileInputStream(path_excel);
			wb= WorkbookFactory.create(fis);
		}
		catch(Exception e){
		}
		int rowNum= wb.getSheet(sheetname).getLastRowNum();
		

		int colstartNumber=rowcnt;
		int totalcolNum=wb.getSheet(sheetname).getRow(colstartNumber).getLastCellNum();


		int TCrownum=0;
		for(int i=1; i<=rowNum;i++)
		{
			if(wb.getSheet(sheetname).getRow(i).getCell(1).toString().equals(TestCase_Name))
			{
				TCrownum=i;
				break;

			}

		}

		int TCdatarow=TCrownum;

		int totalcoldataNum=wb.getSheet(sheetname).getRow(TCdatarow).getLastCellNum();


		System.out.println("the TC total data rows-----------"+totalcoldataNum);

		Object[][] data= new Object[1][1];
		int index=0;
		Hashtable<String,String> ht= null;

		for(int rNum=1; rNum<2; rNum++){

			ht=new Hashtable<String,String>();

			for(int colnum=1; colnum<totalcoldataNum; colnum++){

				Cell cell = wb.getSheet(sheetname).getRow(TCdatarow).getCell(colnum, Row.RETURN_BLANK_AS_NULL);
				if(cell != null){
				
					ht.put(wb.getSheet(sheetname).getRow(1).getCell(colnum).toString(),
							wb.getSheet(sheetname).getRow(TCdatarow).getCell(colnum).toString());  
				}else{
					ht.put(wb.getSheet(sheetname).getRow(1).getCell(colnum).toString(),
							"");
				}

			}

			ht.put(moduleName,wb.getSheet(sheetname).getRow(0).getCell(1).toString());

			data[index][0]=ht;

			index++;

		}

		return data; 


	}



	//    public static String getCellData(String path,String sheetName,int colNum,int rowNum) 
	//    {
	//    	FileInputStream fis;
	//    	try{
	//            fis= new FileInputStream(path_excel);
	//          }
	//          catch(Exception e){
	//          }
	//           Workbook wb= WorkbookFactory.create(fis);
	//           
	//           try{
	//                  if(rowNum <=0)
	//                        return "";
	//           
	//           int index = wb.getSheetIndex(sheetName);
	//
	//           if(index==-1)
	//                  return "";
	//           
	//    
	//           sheet = wb.getSheetAt(index);
	//           row = sheet.getRow(rowNum);
	//           if(row==null)
	//                  return "";
	//           cell = row.getCell(colNum);
	//           if(!(cell==null))
	//                  return cell.toString();
	//           
	//
	//      else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	//          return "";
	//      else 
	//             return String.valueOf(cell.getBooleanCellValue());
	//           }
	//           catch(Exception e){
	//                  
	//                  e.printStackTrace();
	//                  return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
	//           }
	//    }


	//-----------------------------------------------------------------------------------------------
	//Method : setCellValBasedOnTcname
	//Description : This method is used to set the result against the test case after execution of test case.
	//Author : Testing Team

	public static void setCellValBasedOnTcname(String path, String sheet, String tcname, String colHeader, String Result)
	{

		
		FileInputStream fis;
		Workbook wb=null;
		int rowcnt=0;
		int colcnt=0;
		try{
			fis= new FileInputStream(path);
			wb= WorkbookFactory.create(fis);
			int rowNum= wb.getSheet(sheet).getLastRowNum();
			for(int i=2; i<=rowNum;i++)
			{
				if(tcname.equalsIgnoreCase(wb.getSheet(sheet).getRow(i).getCell(1).toString()))
				{
					rowcnt=i;
					break;
				}

			}
			int colNum=wb.getSheet(sheet).getRow(0).getLastCellNum();
			for(int j=0; j<colNum; j++)
			{
				if(colHeader.equalsIgnoreCase(wb.getSheet(sheet).getRow(1).getCell(j).toString()));
				{
					colcnt=j;
					break;
				}
			}

			if(rowcnt!=0){

                Cell cell=wb.getSheet(sheet).getRow(rowcnt).createCell(colcnt);
                cell.setCellType(cell.CELL_TYPE_STRING);
                cell.setCellValue(Result);

                }



			FileOutputStream fos= new FileOutputStream(path);
			wb.write(fos);
			fos.flush();
			fos.close();

		}
		catch(Exception e)
		{

		}

	
	}    

	public static void msExcel_Copy_File(String sSrc_File , String sDest_File)
	{

		Workbook workbook1=null;
		XSSFWorkbook workbook2=new XSSFWorkbook();
		try{

			FileInputStream fis=new FileInputStream(new File(sSrc_File));
			workbook1=WorkbookFactory.create(fis);
		}
		catch(Exception e)
		{

		}
		int sheetNum=workbook1.getNumberOfSheets();
		Reporter.log(""+sheetNum,true);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(sDest_File));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}
		for(int i=0; i<sheetNum;i++)
		{
			String sheetName=workbook1.getSheetAt(i).getSheetName();
			Reporter.log(sheetName,true);
			//Create the Sheet in Dest Workbook
			XSSFSheet sh=workbook2.createSheet(sheetName);

			int rowcnt=workbook1.getSheetAt(i).getLastRowNum();
			for(int j=0; j<=rowcnt; j++)
			{
				XSSFRow rw=sh.createRow(j);
				if(j==0){
					XSSFCellStyle moduleStyle = workbook2.createCellStyle();
					moduleStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
					moduleStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
					moduleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				
				}else if(j==1){
					XSSFCellStyle colHeaderStyle = workbook2.createCellStyle();
					colHeaderStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
					colHeaderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				}
					
				int colcnt=workbook1.getSheetAt(i).getRow(j).getLastCellNum();
				for(int k=0;k<colcnt;k++)
				{
					if(k==3)
					{
						break;

					}
					else
					{
						String value= workbook1.getSheetAt(i).getRow(j).getCell(k).toString();
						//Cell cell=sh.getRow(rowcnt).createCell(colcnt);
						Reporter.log(value,true);
						
						//XSSFRow rw=sh.createRow(j);
						XSSFCell cell=rw.createCell(k);
						cell.setCellType(cell.CELL_TYPE_STRING);
						cell.setCellValue(value);
						
//						if((j==0&&k==0)||(j==0&&k==1)){
//							XSSFCellStyle moduleStyle = workbook2.createCellStyle();
//							moduleStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
//							moduleStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
//							moduleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//							
//						}else if((j==1&&k==0)||(j==1&&k==1)||(j==1&&k==2)){
//							
//							XSSFCellStyle colHeaderStyle = workbook2.createCellStyle();
//							colHeaderStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
//							colHeaderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//						}
						
						if(k==0&&value=="Pass"){
							XSSFCellStyle PassStyle = workbook2.createCellStyle();
							PassStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
							PassStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							
						}else if(k==0&&value=="Fail"){
							
							XSSFCellStyle PassStyle = workbook2.createCellStyle();
							PassStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
							PassStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						}else if((k==0&&value=="Skip")){
							
							XSSFCellStyle PassStyle = workbook2.createCellStyle();
							PassStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
							PassStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						}
						//cell.setCellValue(value);
						
						// Auto-size the columns.
						sh.autoSizeColumn(0);
						sh.autoSizeColumn(1);
					}
				}

			}
		}

		try {
			workbook2.write(fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	public static void copyHtmlFile(File source, File dest) throws IOException {
	    FileInputStream is = null;
	    FileOutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	
	
	//========================================================================================================
	public static void excel_SetStringValueInputVal(String path, String sheet, String rowHeader, String colHeader, String Result,int colVal) throws Exception
	{	
		Process runtime = Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");

		FileInputStream fis= new FileInputStream(path);
		Workbook wb=null;
		Sheet sh;
		int rowcnt=0;
		int colcnt=0;
		int colNum=0; 
		Cell cell;
		String value = "";

		try{
			//fis= new FileInputStream(path);
			
			wb= WorkbookFactory.create(fis);
			wb.setForceFormulaRecalculation(true);
			int rowNum= wb.getSheet(sheet).getLastRowNum();
			sh = wb.getSheet(sheet);
			for(int i=24; i<=rowNum;i++)
			{
				if(rowHeader.equalsIgnoreCase(wb.getSheet(sheet).getRow(i).getCell(colVal).getStringCellValue()))
				{
					rowcnt=i;
					break;
				}

			}

			colNum=sh.getRow(1).getLastCellNum();
			if(rowcnt>0){

				for(int k=0; k<colNum;k++){

					cell = sh.getRow(1).getCell(k);
					if(cell!=null){

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							value = cell.getRichStringCellValue().getString();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							value = Double.toString(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();
							value=Integer.toString(formulaEval.evaluateFormulaCell(cell));
						}


						if(value.equals(colHeader))
						{
							colcnt=k;
							break;
						}
					}
				}
			}

			cell = sh.getRow(rowcnt).getCell(colcnt);
			cell.setCellValue(Result);	
			
			fis.close();
			FileOutputStream fos= new FileOutputStream(path);
			wb.setForceFormulaRecalculation(true);	
			wb.write(fos);			
			
			
			//fos.flush();
			
			//wb.;
			fos.close();
			
			
			
		
			
			

		}catch(Exception e)
		{
			e.getMessage();
		}
		
		finally{
			
			
			
			
			runtime = Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		}

	}
	public static void excel_SetNumericValueInputVal(String path, String sheet, String rowHeader, String colHeader, double Result,int colVal) throws Exception
	{	
		Process runtime = Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");

		FileInputStream fis=new FileInputStream(path);
		Workbook wb=null;
		Sheet sh;
		int rowcnt=0;
		int colcnt=0;
		int colNum=0; 
		Cell cell;
		String value = "";
		String colName="";
		

		try{
			//fis= new FileInputStream(path);
			wb= WorkbookFactory.create(fis);
			wb.setForceFormulaRecalculation(true);
			int rowNum= wb.getSheet(sheet).getLastRowNum();
			sh = wb.getSheet(sheet);
			for(int i=24; i<=rowNum;i++)
			{
				colName = wb.getSheet(sheet).getRow(i).getCell(colVal).getStringCellValue();
				if(rowHeader.equalsIgnoreCase(wb.getSheet(sheet).getRow(i).getCell(colVal).getStringCellValue()))
				{
					rowcnt=i;
					break;
				}

			}

			colNum=sh.getRow(1).getLastCellNum();
			if(rowcnt>0){

				for(int k=0; k<colNum;k++){

					cell = sh.getRow(1).getCell(k);
					if(cell!=null){

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							value = cell.getRichStringCellValue().getString();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							value = Double.toString(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();
							value=Integer.toString(formulaEval.evaluateFormulaCell(cell));
						}


						if(value.equals(colHeader))
						{
							colcnt=k;
							break;
						}
					}
				}
			}

			cell = sh.getRow(rowcnt).getCell(colcnt);
			double n=(double)Result;
			cell.setCellValue(n);
			fis.close();
			
			FileOutputStream fos= new FileOutputStream(path);
			wb.setForceFormulaRecalculation(true);
			wb.write(fos);	
			//wb.close();
			//wb.setForceFormulaRecalculation(true);
			fos.close();
			
			
			
			
			

		}catch(Exception e)
		{
			e.getMessage();
		}
		
		finally{
			//.close();
			runtime = Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		}

	}






	
	
	
	public static double getDoubleCellValUsingRowAndColName(String path, String sheet, String rowHeader, String colHeader,int colVal) throws Exception
	{

		Process runtime = Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		
		
		 //open file
		String csvPath = "C:\\GitWorkspace\\Automation110\\Nous_PublicStorage\\src\\main\\resources\\Resources\\LeasingCalculation.csv";
		
		FileInputStream fis=new FileInputStream(path);
		Workbook wb=null;
		Sheet sh;
		int rowcnt=0;
		int colcnt=0;
		Cell cell;
		String value = "";
		int colNum=0; 
		double returnValue = 0.0;

		try{
			//fis= new FileInputStream(path);
			
			wb= WorkbookFactory.create(fis);
			wb.setForceFormulaRecalculation(true);
			//wb.setForceFormulaRecalculation(true);
			
			
			int rowNum= wb.getSheet(sheet).getLastRowNum();
			sh = wb.getSheet(sheet);
			
			for(int i=0; i<rowNum;i++)
			{
				String rowValue = wb.getSheet(sheet).getRow(i).getCell(colVal).getStringCellValue();
				rowValue = rowValue.trim();
				rowValue = rowValue.replace("  "," ");
				rowValue = rowValue.replace(" )",")");
				if(rowHeader.equalsIgnoreCase(rowValue))
				{
					rowcnt=i;
					break;
				}

			}
			colNum=sh.getRow(1).getLastCellNum();
			if(rowcnt>0){

				for(int k=0; k<colNum;k++){

					cell = sh.getRow(1).getCell(k);
					if(cell!=null){

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							value = cell.getRichStringCellValue().getString();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							value = Double.toString(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();
							value=Integer.toString(formulaEval.evaluateFormulaCell(cell));
						}


						if(value.equals(colHeader))
						{
							colcnt=k;
							break;
							
							
						}
					}
				}
			}
			
			// Run vbs to convert excel to csv
			 try {
				 Thread.sleep(2000);
		            Runtime.getRuntime().exec("wscript C:\\GitWorkspace\\Automation110\\Nous_PublicStorage\\src\\main\\resources\\Convert.vbs "+ sheet);
		            Thread.sleep(10000);
		            System.out.println("Ran VBS file");
		        } catch (IOException e) {

		            System.exit(0);
		        }
			File inputFile = new File(csvPath);

			// Read existing file 
			CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
			List<String[]> csvBody = reader.readAll();
			String cellstrVal="";
			String s="";
			int i=rowcnt;
			int j=rowcnt;
			while(i<rowNum)
			{
				s = csvBody.get(i)[colVal];
				if(s.equalsIgnoreCase(rowHeader))
				{
					cellstrVal = csvBody.get(i)[colcnt];
					break;
				}
				i++;
			}
			while(j>0)
			{
				s = csvBody.get(j)[colVal];
				if(s.equalsIgnoreCase(rowHeader))
				{
					cellstrVal = csvBody.get(j)[colcnt];
					break;
				}
				j--;
				
			}
			/*
			// get CSV cell data by using row and column
			String str = csvBody.get(rowcnt)[colVal];
			String str1 = csvBody.get(rowcnt-1)[colVal];
			String str2 = csvBody.get(rowcnt+1)[colVal];
			if(csvBody.get(rowcnt)[colVal].equalsIgnoreCase(rowHeader))
			{
			 cellstrVal = csvBody.get(rowcnt)[colcnt];
			}
			else if(csvBody.get(rowcnt-1)[colVal].equalsIgnoreCase(rowHeader))
			{
			  cellstrVal = csvBody.get(rowcnt-1)[colcnt];
			}
			else if(csvBody.get(rowcnt+1)[colVal].equalsIgnoreCase(rowHeader))
			{
			  cellstrVal = csvBody.get(rowcnt+1)[colcnt];
			}
			*/
			double cellVal = Double.parseDouble(cellstrVal);
			reader.close();

			returnValue = cellVal;		
			
	
				
		}
		catch(Exception e)
		{
			e.getMessage();
		}



		finally{
			
			fis.close();
			runtime = Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		}
		return returnValue; 
	}


	
	
	public static  void writetoExcel(String FileName, String sheet,String MoveInDate,double MonthlyRent,double Insurance,double Merchandise,double AdminFee,double Totaldue,double nextMonthDue) throws Exception {


		Process runtime ;
		runtime =  Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		XSSFRow row1;
		XSSFCell Cell1;
		double n=0.0;
		
		try {
			FileInputStream fileInputStream3 = new FileInputStream(FileName);
			File outputsheetfile1 = new File(FileName);

			if (((File) outputsheetfile1).exists()) {

				System.out.println("File existed");
				try {
					@SuppressWarnings("resource")
					XSSFWorkbook ObjWorkBook = new XSSFWorkbook(
							fileInputStream3);

					XSSFSheet DriverTableSheet = ObjWorkBook.getSheet(sheet);
					@SuppressWarnings("unused")
					DataFormatter formatter = new DataFormatter();

					row1 = DriverTableSheet.getRow(24);
					Cell1 = row1.getCell(14);
					Cell1.setCellValue(MoveInDate);

					row1 = DriverTableSheet.getRow(25);
					Cell1 = row1.getCell(14);
					n = (double) MonthlyRent;
					Cell1.setCellValue(n);

					row1 = DriverTableSheet.getRow(26);
					Cell1 = row1.getCell(14);
					n = (double) Insurance;
					Cell1.setCellValue(n);
					
					
					row1 = DriverTableSheet.getRow(27);
					Cell1 = row1.getCell(14);
					n = (double) Merchandise;
					Cell1.setCellValue(n);
					
					
					row1 = DriverTableSheet.getRow(28);
					Cell1 = row1.getCell(14);
					n = (double) AdminFee;
					Cell1.setCellValue(n);

					ObjWorkBook.setForceFormulaRecalculation(true);		
					
					XSSFFormulaEvaluator.evaluateAllFormulaCells(ObjWorkBook);
					FileOutputStream out1 = new FileOutputStream(FileName);
					ObjWorkBook.write(out1);
					out1.close();
					fileInputStream3.close();
					
					try{

					XSSFWorkbook wb = new XSSFWorkbook( new FileInputStream(new File(FileName)));
					XSSFSheet currentSheet = wb.getSheet(sheet);
					for (int i = 2; i <= 14; i++) {
						XSSFRow row2 = currentSheet.getRow(i);
						double Cell2 = row2.getCell(20).getNumericCellValue();
						System.out.println(Cell2);
						if (Cell2 == Totaldue) {
							
							System.out.println("total due amount are matching"+Cell2+"  "+Totaldue);
							//result=true;
						}
					}
					
					}catch (IOException e) {

						e.printStackTrace();
					}
					
					System.out.println("Executed----->");
					
					System.out.println("Executed----->");
					
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			runtime =  Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		}
		

	}
	
}


