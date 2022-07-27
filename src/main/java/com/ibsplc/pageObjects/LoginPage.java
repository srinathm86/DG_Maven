package com.ibsplc.pageObjects;


import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.ibsplc.common.BasePage;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Created by a-7681 on 9/13/2017.
 */
public class LoginPage extends BasePage {
	private ExtentTest test;
    private WebDriver driver;
    private String browser;
    private String dataFileName;
    private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties","testDataDirectory");
   
    public static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "OPR_FLT.properties");
    public static String genFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "Generic.properties");
    private By txt_usrname;
    private By txt_password;
    private By txt_cmpnyCod;
    private By btn_login;
    private By txt_cargo_usrname;
    private By txt_cargo_password;
    private By txt_cargo_login;
    private By logoff_link;
    private By btn_logoff;
    private By Yes_btn;
    
    public LoginPage(WebDriver driver, String browser, String dataFileName, ExtentTest test) {
        super(driver,test);
        this.driver = driver;
        this.browser = browser;
        this.dataFilePath = this.dataFilePath + dataFileName;
        this.dataFileName = dataFileName;
        this.test = test;
        initPageElements();
    }

    private void initPageElements(){
    
    	txt_usrname 	= MiscUtility.getWebElement(objFilepath,"login_txt_userName");
    	txt_password 	= MiscUtility.getWebElement(objFilepath,"login_txt_password");
    	txt_cmpnyCod 	= MiscUtility.getWebElement(objFilepath,"login_txt_compCode");
    	btn_login 		= MiscUtility.getWebElement(objFilepath,"login_btn_login");
    	txt_cargo_usrname= MiscUtility.getWebElement(objFilepath,"login_txt_cargo_usrname");
    	txt_cargo_password 	= MiscUtility.getWebElement(objFilepath,"login_txt_cargo_password");
    	txt_cargo_login=MiscUtility.getWebElement(objFilepath,"login_txt_cargo_login");
    	
    	//**A-8260
    	logoff_link		= MiscUtility.getWebElement(objFilepath,"logoff_link");
    	btn_logoff		= MiscUtility.getWebElement(objFilepath,"logoff_btn");
    	Yes_btn			= MiscUtility.getWebElement(genFilepath,"Generic_btn_diaYes");
    }
    
    public HomePage doLogin(){

//    	String username = PropertyHandler.getPropValue(dataFilePath, "username");
//      String password = PropertyHandler.getPropValue(dataFilePath, "password");
//      String Companycode = PropertyHandler.getPropValue(dataFilePath, "Companycode");
//    	String username=XmlRead.Read("LoginID");
//    	String password ="";
    	String username =  Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("UserName");
    	String password =  Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Password");
    	click(driver.findElement(By.xpath("//label[@for='userID']")));
    	enterKeys(txt_usrname,username);
    	click(driver.findElement(By.xpath("//label[@for='password']")));
        enterKeys(txt_password,password);
//        enterKeys(txt_cmpnyCod,Companycode);
       click(btn_login);
        switch (browser.toUpperCase()){
            case "CHROME":
                waitForNewWindow(2);
                moveToSecondWindow();
                break;

            case "IE":
                WebDriverWait wait = new WebDriverWait(driver,60);

                wait.until(ExpectedConditions.alertIsPresent());

                Alert alert = driver.switchTo().alert();

                alert.accept();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Set<String> handels = driver.getWindowHandles();

                System.out.println(handels.toString());
                driver.switchTo().window((String)handels.toArray()[0]);
        }
        minWait();
        test.log(LogStatus.PASS, "Logged in Successfully with user : "+username);
        return new HomePage(driver,dataFileName,test);
    }
    
    /**
     * Overloaded method with usertype as parameter
     * @return
     * @author a-7868 Krishna <12/03/2018>
     */
    public HomePage doLogin(String username){

//    	username = PropertyHandler.getPropValue(dataFilePath, username);
        String password = PropertyHandler.getPropValue(dataFilePath, "password");
        String Companycode = PropertyHandler.getPropValue(dataFilePath, "Companycode");

        enterKeys(By.name("username"),username);
        enterKeys(By.name("j_password"),"weblogic");
        enterKeys(By.name("companyCode"),"AA");
        minWait();
        click(By.name("btOk"));
        switch (browser.toUpperCase()){
        	case "SAUCE_LABS": 
        	case "WEBDRIVER_MANAGER": 
            case "CHROME":
                waitForNewWindow(2);
                moveToSecondWindow();
                break;

            case "IE":
                WebDriverWait wait = new WebDriverWait(driver,6);

                wait.until(ExpectedConditions.alertIsPresent());

                Alert alert = driver.switchTo().alert();

                alert.accept();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Set<String> handels = driver.getWindowHandles();

                System.out.println(handels.toString());
                driver.switchTo().window((String)handels.toArray()[0]);
        }
        minWait();
        test.log(LogStatus.INFO, "Logged into iCargo");
        return new HomePage(driver,dataFileName,test);

    }
    
    public HomePage doAirlineLogin(){

//      String username = xls_read.getTestData("IAG", usrname, testCaseName);
//      String password = xls_read.getTestData("IAG", pwd, testCaseName);
//      String companyCod = xls_read.getTestData("IAG", cmpnyCod, testCaseName);
      String Airlineusername = PropertyHandler.getPropValue(dataFilePath, "Airlineusername");
      String password = PropertyHandler.getPropValue(dataFilePath, "password");
      String Companycode = PropertyHandler.getPropValue(dataFilePath, "Companycode");

      enterKeys(txt_usrname,Airlineusername);
      enterKeys(txt_password,password);
      enterKeys(txt_cmpnyCod,Companycode);
      click(btn_login);
      switch (browser.toUpperCase()){
          case "CHROME":
              waitForNewWindow(2);
              moveToSecondWindow();
              break;

          case "IEXPLORE":
              WebDriverWait wait = new WebDriverWait(driver,6);

              wait.until(ExpectedConditions.alertIsPresent());

              Alert alert = driver.switchTo().alert();

              alert.accept();

              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              Set<String> handels = driver.getWindowHandles();

              System.out.println(handels.toString());
              driver.switchTo().window((String)handels.toArray()[0]);
      }
      minWait();
      return new HomePage(driver,dataFileName,test);
 
  }
    
    /**
     * @author A-8260
     *  
     *  The method Logs off and Switches User
     */
    public HomePage logoffAndSwitchUser(String userName)
    {
    	userName = PropertyHandler.getPropValue(dataFilePath, userName);
    	String password = PropertyHandler.getPropValue(dataFilePath, "password");
        String Companycode = PropertyHandler.getPropValue(dataFilePath, "Companycode");
    	driver.switchTo().defaultContent();
    	click(logoff_link);
    	click(btn_logoff);
    	if(verifyElementEnabled(Yes_btn))
    		click(Yes_btn);
    	 enterKeys(txt_cargo_usrname,userName);
         enterKeys(txt_cargo_password,password);
         enterKeys(txt_cmpnyCod,Companycode);
         click(btn_login);
         switch (browser.toUpperCase()){
         case "CHROME":
        	
        	 waitForNewWindow(1);
        	 minWait();
        	 moveToWindow();
             break;

         case "IEXPLORE":
             WebDriverWait wait = new WebDriverWait(driver,6);

             wait.until(ExpectedConditions.alertIsPresent());

             Alert alert = driver.switchTo().alert();

             alert.accept();

             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             Set<String> handels = driver.getWindowHandles();

             System.out.println(handels.toString());
             driver.switchTo().window((String)handels.toArray()[0]);
     }
     minWait();
    	return new HomePage(driver,dataFileName,test);
    }
    public HomePage doLoginCargo(){

    	String username = PropertyHandler.getPropValue(dataFilePath, "username");
        String password = PropertyHandler.getPropValue(dataFilePath, "password");
        String Companycode = PropertyHandler.getPropValue(dataFilePath, "Companycode");

        enterKeys(txt_cargo_usrname,username);
        enterKeys(txt_cargo_password,password);
       enterKeys(txt_cmpnyCod,Companycode);
        click(txt_cargo_login);
        switch (browser.toUpperCase()){
            case "CHROME":
                waitForNewWindow(2);
                moveToSecondWindow();
                break;

            case "IE":
                WebDriverWait wait = new WebDriverWait(driver,6);

                wait.until(ExpectedConditions.alertIsPresent());

                Alert alert = driver.switchTo().alert();

                alert.accept();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Set<String> handels = driver.getWindowHandles();

                System.out.println(handels.toString());
                driver.switchTo().window((String)handels.toArray()[0]);
        }
        minWait();
        return new HomePage(driver,dataFileName,test);

    }
}
