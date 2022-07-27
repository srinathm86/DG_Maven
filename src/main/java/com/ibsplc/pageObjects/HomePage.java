package com.ibsplc.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.ibsplc.common.BasePage;
import com.ibsplc.generic.Assertion;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Created by a-7681 on 9/13/2017.
 */
public class HomePage extends BasePage {

    public static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "Generic.properties");

   public WebDriver driver;
    By txt_screenId,
            header_panel, btn_editIcon, btn_switchRole, btn_station, btn_ok;
    private String dataFileName;
    public String dataFilePath = PropertyHandler.getPropValue(
			"resources\\EnvSetup.properties", "testDataDirectory");

    public HomePage(WebDriver driver, String dataFileName, ExtentTest test) {
        super(driver,test);
        this.driver = driver;
        initElements();
        this.dataFileName = dataFileName;
        this.dataFilePath = dataFilePath + dataFileName;
        this.test = test;
    }

    private void initElements() {
        txt_screenId = MiscUtility.getWebElement(genObjPath, "homeScreen_screenID");
        header_panel = MiscUtility.getWebElement(genObjPath, "homeScreen_headerPanel");
        btn_editIcon = MiscUtility.getWebElement(genObjPath,
				"homeScreen_btn_editIcon");

		btn_switchRole = MiscUtility.getWebElement(genObjPath,
				"homeScreen_btn_switchRole");
		btn_station = MiscUtility.getWebElement(genObjPath,
				"homeScreen_btn_station");
		btn_ok = MiscUtility.getWebElement(genObjPath, "homeScreen_btn_ok");
    }
    



    public LTE001 goToLTE001() {
        minWait(); 
        waitForWhiteScreen();
        waitForElement(txt_screenId).clear();
        waitForElement(txt_screenId).sendKeys("LTE001" + Keys.RETURN);
        waitForElement(header_panel).click();
        waitForFrameAndSwitch("iCargoContentFrameLTE001");
        minWait();  
        test.log(LogStatus.PASS, "Clicked on LTE001 Screen");
        return new LTE001(driver, dataFileName,test);
    }


    public CAP018 goToCAP018() {
    	 minWait(); 
//         waitForWhiteScreen();
         waitForElement(txt_screenId).clear();
         waitForElement(txt_screenId).sendKeys("CAP018" + Keys.RETURN);
         waitForElement(header_panel).click();
         waitForFrameAndSwitch("iCargoContentFrameCAP018");
         minWait();  
         test.log(LogStatus.INFO, "Opened the Booking(CAP018) Screen");
         return new CAP018(driver, dataFileName, test);
    }
    
    public OPR026 goToOPR026() {
   	 minWait(); 
//        waitForWhiteScreen();
        waitForElement(txt_screenId).clear();
        waitForElement(txt_screenId).sendKeys("OPR026" + Keys.RETURN);
        waitForElement(header_panel).click();
        waitForFrameAndSwitch("iCargoContentFrameOPR026");
        minWait();  
        test.log(LogStatus.INFO, "Opened the capture AWB(OPR026) Screen");
        return new OPR026(driver, dataFileName, test);
   }
    
    public OPR335 goToOPR335() {
   	 minWait(); 
//        waitForWhiteScreen();
        waitForElement(txt_screenId).clear();
        waitForElement(txt_screenId).sendKeys("OPR335" + Keys.RETURN);
        waitForElement(header_panel).click();
        waitForFrameAndSwitch("iCargoContentFrameOPR335");
        minWait();  
        test.log(LogStatus.INFO, "Opened the Goods Acceptance Screen(OPR335)");
        return new OPR335(driver, dataFileName, test);
      }
   
   public OPR350 goToOPR350() {
  	 minWait(); 
//       waitForWhiteScreen();
       waitForElement(txt_screenId).clear();
       waitForElement(txt_screenId).sendKeys("OPR350" + Keys.RETURN);
       waitForElement(header_panel).click();
       waitForFrameAndSwitch("iCargoContentFrameOPR350");
       minWait();  
       test.log(LogStatus.INFO, "Opened the Goods Acceptance Screen(OPR350)");
       return new OPR350(driver, dataFileName, test);
     }
   
   public OPR344 goToOPR344() {
  	 minWait(); 
       waitForWhiteScreen();
       waitForElement(txt_screenId).clear();
       waitForElement(txt_screenId).sendKeys("OPR344" + Keys.RETURN);
       waitForElement(header_panel).click();
       waitForFrameAndSwitch("iCargoContentFrameOPR344");
       minWait();  
       test.log(LogStatus.PASS, "Opened the Export Manifest(OPR344) Screen");
       return new OPR344(driver, dataFileName, test);
   }
   
   public OPR017 goToOPR017() {

       enterKeys(txt_screenId, "OPR017" + Keys.RETURN);
       click(header_panel);
       waitForFrameAndSwitch("iCargoContentFrameOPR017");
       minWait();
       return new OPR017(driver, dataFileName, test);
   }
   
   public FLT002 goToFLT002() {
       enterKeys(txt_screenId, "FLT002" + Keys.RETURN);
       click(header_panel);
       waitForFrameAndSwitch("iCargoContentFrameFLT002");
       minWait();
       return new FLT002(driver, dataFileName, test);
   }

   public MSG005 goToMSG005() {
       minWait(); 
       waitForWhiteScreen();
       waitForElement(txt_screenId).clear();
       waitForElement(txt_screenId).sendKeys("MSG005" + Keys.RETURN);
       click(header_panel);
       waitForFrameAndSwitch("iCargoContentFrameMSG005");
       minWait();  
       test.log(LogStatus.PASS, "Navigated to Process Messages Screen : MSG005");
       return new MSG005(driver, dataFileName, test);
   }
   
 //A-8680
 	public HomePage SwitchRoleGroupParameter(String stationCode, String RoleGroup) {
 	//	stationCode = PropertyHandler.getPropValue(dataFilePath, stationCode);
 		
 		driver.switchTo().defaultContent();
 		
 		click(btn_editIcon);
 		click(btn_switchRole);
 		driver.switchTo().frame("swichRoleiframe");

 		click(btn_station);
 		selectByText(btn_station, stationCode);
 		click(By.xpath("//select[@name='selectedStationRoleGroup']"));
// 		minWait();
 		selectByText(By.xpath("//select[@name='selectedStationRoleGroup']"), RoleGroup);
// 		minWait();
 		click(btn_ok);
 		maxWait();
 		driver.switchTo().defaultContent();
 		return this;
 	}
 	
 	//A-8680
 	public HomePage SwitchRoleGroupAllParameter(String stationCode, String RoleGroup) {
 		stationCode = PropertyHandler.getPropValue(dataFilePath, stationCode);
 		RoleGroup = PropertyHandler.getPropValue(dataFilePath, RoleGroup);
 		driver.switchTo().defaultContent();
 		click(btn_editIcon);
 		click(btn_switchRole);
 		driver.switchTo().frame("swichRoleiframe");

 		click(btn_station);
 		selectByText(btn_station, stationCode);
 		click(By.xpath("//select[@name='selectedStationRoleGroup']"));
 		selectByText(By.xpath("//select[@name='selectedStationRoleGroup']"), RoleGroup);
 		minWait();
 		click(btn_ok);
 		maxWait();
 		driver.switchTo().defaultContent();
 		return this;
 	}
	    
} 
