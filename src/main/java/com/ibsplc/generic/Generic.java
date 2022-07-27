package com.ibsplc.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.ibsplc.pageObjects.HomePage;
import com.ibsplc.pageObjects.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;

public class Generic{

	String dataFileName, browserName;
	public WebDriver driver;
	public ExtentTest test;
	
	public Generic(WebDriver driver, String browserName, String dataFileName, ExtentTest test) {
 
		this.driver = driver;
		this.browserName = browserName;
		this.dataFileName = dataFileName;
		this.test=test;
	}
	
	public HomePage login() {
		LoginPage lp = new LoginPage(driver, browserName, dataFileName,test);
        return lp.doLogin();
	}
	
	
	public HomePage login1() {
		LoginPage lp = new LoginPage(driver, browserName, dataFileName,test);
        return lp.doLoginCargo();
	}
	public HomePage login(String username) {
		LoginPage lp = new LoginPage(driver, browserName, dataFileName,test);
        return lp.doLogin(username);
	}
	public HomePage Airlinlogin() {
		LoginPage lp = new LoginPage(driver, browserName, dataFileName,test);
        return lp.doAirlineLogin();
	}
	
	//*********Changes: A-8260 Logoff and Switch User
	public HomePage logoffAndSwitchUser(String username)
	{
		LoginPage lp = new LoginPage(driver, browserName, dataFileName,test);
        return lp.logoffAndSwitchUser( username);
	}
	
	
//	
	
	//verifying data in excel
	public void updateExcel(String pmyKey, String dwnloadPath) throws Exception {
	        try {
	            String flag = "";
	        	String st2 = System.getProperty("user.dir");
	      		String path2 = st2 + "\\resources\\DynamicSuite.xlsx";
	      		File f1 = new File(path2);
	      		FileInputStream ios1 = new FileInputStream(f1);
	      		XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
	      		XSSFSheet sheet1 = workbook1.getSheet("AA");
	      		for (int i = 2; i <= sheet1.getLastRowNum(); i++) {
	      			Row row = sheet1.getRow(i);
	      			Cell RunStatus = row.getCell(1);
	      			flag = RunStatus.toString();
	      			System.out.println(flag);
	      			//String actValue = flag.getRow(1).getCell(flag).getStringCellValue();
	      			if (flag.equalsIgnoreCase(pmyKey)) {
	      				//Write Data in Excel
	      				
	      				
	      			} else {
	      				continue;
	      			}
	      			
	      		}

	          } catch (Exception e) {
	              System.err.println("Failed in Excel Verification");
	              Assert.assertFalse(true, "Failed in Excel Verification");
	          }
	     }
	
	/*
	 * Author- Sharath 
	 * Purpose: Method to Set all Flag status to No and update the Excel based on Module parameter
	 * Date: 29-02=3-2019
	 */
	public void updateExcelFlags(String pmyKey) throws Exception {
        try {
            String test = "";
        	String st2 = System.getProperty("user.dir");
      		String path2 = st2 + "\\resources\\DynamicSuite.xlsx";
      		File f1 = new File(path2);
      		FileInputStream ios1 = new FileInputStream(f1);
      		XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
      		XSSFSheet sheet1 = workbook1.getSheet("AA");
      		for (int i = 2; i <= sheet1.getLastRowNum(); i++) {
      			Row row = sheet1.getRow(i);
      			Cell RunStatus = row.getCell(1);
      			test = RunStatus.toString();
      			String text[] = test.split("_");
      			test = text[text.length-1];
//      			System.out.println(test);
      			if (test.equalsIgnoreCase(pmyKey)) {      				
//      				writeYesFlagExcel(i);
      				Cell cell1 = row.createCell(3);
      	            cell1.setCellValue("Yes");
/*      	            FileOutputStream out = new FileOutputStream(new File(path2));
      	            workbook1.write(out);
      	            out.close();*/
      			} else {	
      				continue;
      			}
      		}
      	   FileOutputStream out = new FileOutputStream(new File(path2));
	       workbook1.write(out);
	       out.close();
          } catch (Exception e) {
              System.err.println("Failed in Excel Verification");
              Assert.assertFalse(true, "Failed in Excel Verification");
          }
     }
	
	/*
	 * Author- Sharath 
	 * Purpose: Method to Modify excel to Yes based on Module parameter
	 * Date: 29-03-2019
	 */
	public void writeYesFlagExcel(int i) throws Exception {  
		try {
            String st2 = System.getProperty("user.dir");
            String path2 = st2
                        + "\\resources\\DynamicSuite.xlsx";
            File f1 = new File(path2);
            FileInputStream ios1 = new FileInputStream(f1);
            XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
            XSSFSheet sheet1 = workbook1.getSheet("AA");        
            Row row1 = sheet1.getRow(i);
            Cell cell1 = row1.createCell(3);
            cell1.setCellValue("Yes");
            FileOutputStream out = new FileOutputStream(new File(path2));
            workbook1.write(out);
            out.close();
            System.out.println("Report written successfully on disk.");
      } catch (Exception e) {
            e.printStackTrace();
      }

	}
	
	/*
	 * Author- Sharath 
	 * Purpose: Method to Set all Flag status to No status
	 * Date: 29-03-2019
	 */
		public void writeNoFlagExcel() throws Exception {  
			try {
	            String st2 = System.getProperty("user.dir");
	            String path2 = st2
	                        + "\\resources\\DynamicSuite.xlsx";
	            File f1 = new File(path2);
	            FileInputStream ios1 = new FileInputStream(f1);
	            XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
	            XSSFSheet sheet1 = workbook1.getSheet("AA");
	            int rows = sheet1.getLastRowNum();
	            for(int a=2;a<=rows;a++){
	            Row row1 = sheet1.getRow(a);
	            Cell cell1 = row1.createCell(3);
	            cell1.setCellValue("No");
	       /*     Cell cell1 = row1.getCell(3);
	            row1.removeCell(cell1);*/
	            }
	            FileOutputStream out = new FileOutputStream(new File(path2));
	            workbook1.write(out);
	            out.close();
	            System.out.println("Successfully set all flags to No as precondition");
	      } catch (Exception e) {
	    	   System.out.println("Setting flags to No failed");
	            e.printStackTrace();
	      }

		}
}