package com.ibsplc.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import com.ibsplc.generic.Assertion;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * Created by a-7681 on 12/11/2017.
 */
public class BaseSetup implements ITestListener {
	
	protected final static Logger logger = Logger.getLogger(BaseSetup.class); 	
    public static String browserName;       	
    private static String ScreenShotPath = "reports\\ScreenShots\\";
	private static String TOLRUNREQ = null;
	private static long startMillis;
	private DesiredCapabilities capabilities;
	public WebDriver driver;
	protected String portalURL = "";
    public static ExtentReports extent;
    public ExtentTest test;
    public String run;
    public ITestResult iTestResult;
    String SAUCE_USERNAME = "sso-aa-Sharath.Madananth";
	String SAUCE_ACCESS_KEY = "ffa62482-99ff-45c3-8044-557e05d6e503";
	String SAUCE_URL = "https://sso-aa-Sharath.Madananth:ffa62482-99ff-45c3-8044-557e05d6e503@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	String tunnel = "sso-aa-Carlo.Almeida";
    
    @BeforeMethod
    public void beforeMethod() {
    	System.out.println("Before Method");
    }
    
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("on test start");
		
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		int i = iTestResult.getStatus();
		System.out.println("on test fail");
	//		this.test.log(LogStatus.FAIL, "The test failed, please refer below screenshot" );
	//		captureAndAddScreenshot();
//			extent.endTest(test);
	//		this.driver.quit();
		
}


	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("on test skip");
//		extent.endTest(this.test);
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("on test success");
		
//		extent.endTest(this.test);
//		this.driver.quit();
	  }
	
	
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("on test finish");
		extent.flush();
		
	}
	
	
	@Override
	public void onStart(ITestContext iTestContext) {  
		
		System.out.println("on test start context");
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    				   LocalDateTime now = LocalDateTime.now();  
	    				   System.out.println(dtf.format(now)); 
	    				   String dateTime = dtf.format(now).toString().replaceAll(" ","")
	    						   .replaceAll("/", ".").replaceAll(":", ""); 
    
		System.out.println(System.getProperty("user.dir"));
        extent = new ExtentReports(System.getProperty("user.dir")
                                        + "\\reports\\DGReport_"+ dateTime+".html");
//		 extent = new ExtentReports(System.getProperty("user.dir")
//               + "\\reports\\DGAutoNotocReports.html", false);
       intializeLogger();
	}

	/******************************/
	
	private String checkForError() {
		
	    driver.switchTo().defaultContent();
	    String xpath = "//*[@id='errorDisplayDiv']/table/tbody/tr/td[2]";
	    String text = null;
	    try {
	        text = driver.findElement(By.xpath(xpath)).getText();
	    } catch (NoSuchElementException e) {
	        List<WebElement> frameList = driver.findElements(By.xpath("//iframe[contains(@name,'iCargoContentFrame')]"));
	        driver.switchTo().frame(frameList.get(1));
	        try {
	            text = driver.findElement(By.xpath(xpath)).getText();
	        } catch (NoSuchElementException e1) {
	        }
	    }
	
	    return text;
	}
	
	/**
	 * Created by A-7605
	 */
	protected void intializeLogger() {
	    Properties properties = new Properties();
	
	    try {
	        properties.load(new FileInputStream("log4j.properties"));
	        PropertyConfigurator.configure(properties);
	        logger.info("Logger configured");
	    } catch (IOException e) {
	        e.printStackTrace();
	
	    }
	}
	
	public enum Days {
	    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	}
	    
	/**
	 * Created by A-7605 on 6-3-18
	 * This method is used to associate event handler with the driver
	 * @param driver
	 * @return
	 */
	protected WebDriver registerEvent(WebDriver driver){
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		EventHandler eventListener = new EventHandler();
		eventDriver.register(eventListener);
		driver = eventDriver;
		logger.info("Event handler registered with driver "+driver);
		return driver;    	
	}
	    
	/**
	 * Created by A-7605 on 32-5-18
	 * This method kills already running driver files
	 */
	private void killBrowserDriver(){
		logger.info("Killing driver files");
		String[] commands = new String[]{"taskkill /im chromedriver.exe /f","taskkill /im IEDriverServer.exe /f","taskkill /im geckodriver.exe /f"};
		try {
			for(String command:commands)
				Runtime.getRuntime().exec(command);
			logger.info("Driver files killed");
		} catch (IOException e) {
			logger.info("Error on killing driver files",e);
		}
	}
	
	public void captureAndAddScreenshot(){
		try{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now)); 
        String path = dtf.format(now).toString().replaceAll(" ","").replaceAll("/", ".").replaceAll(":", "")+".png";
        File scrFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./Reports/ScreenShots/"+path));     
        System.out.println();
        System.out.println("Screenshot taken");   
        String dir = System.getProperty("user.dir")+"\\Reports\\Screenshots\\";
        test.log(LogStatus.INFO, test.addScreenCapture(dir+path) );
		}
		catch(Exception e){
         e.printStackTrace();
         System.out.println(e);
		}
	}

	/*
	 * Author- Sharath 
	 * Purpose: Method to Set all Flag status to No status
	 * Date: 29-03-2019
	 */
		public void writeFlagtoExcelCell(String path, int index, String dataToWrite) throws Exception {  
			try {
	            File f1 = new File(path);
	            FileInputStream ios1 = new FileInputStream(f1);
	            XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
	            XSSFSheet sheet1 = workbook1.getSheetAt(0);
	            int rows = sheet1.getLastRowNum();
	            Row row1 = sheet1.getRow(index);
	            int cellIndex = row1.getFirstCellNum();
	            Cell cell1 = row1.createCell(cellIndex);
	            cell1.setCellValue(dataToWrite);
	            FileOutputStream out = new FileOutputStream(new File(path));
	            workbook1.write(out);
	            out.close();
	      } catch (Exception e) {
	    	   System.out.println("Setting flags to No failed");
	            e.printStackTrace();
	      }

		}
		
		// Sharath
		public List<Map<String, String>> getRawData(String path) throws IOException {

			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
			Row headerRow = sh.getRow(0);
			Row dataRow;
			String key, value;
			List<Map<String, String>> mylists = new ArrayList<>();
			int noOfRows = sh.getLastRowNum();
			int noOfCols = sh.getRow(0).getLastCellNum();
			HashMap<String, String> myMap = null;
			for (int i = 1; i <= noOfRows; i++) {
				myMap = new HashMap<>();
				dataRow = sh.getRow(i);
				for (int j = 0; j < noOfCols; j++) {
					key = CellUtil.getCell(headerRow, j).getStringCellValue();
					try{
						value = CellUtil.getCell(dataRow, j).getStringCellValue();
						}catch(IllegalStateException e){
							int b = (int) CellUtil.getCell(dataRow, j).getNumericCellValue();
							value = Integer.toString(b);
						}
					myMap.put(key, value);
				}
				mylists.add(myMap);
			}
			fis.close();
			return mylists;
		}
		
		/**
		 * Initializes the driver
		 * @return
		 * @throws MalformedURLException 
		 */
		public WebDriver initializeDriver() throws MalformedURLException {
			System.out.println("Initializing driver");
//			browserName = PropertyHandler.getPropValue("resources\\TestData\\Base\\ICargo_1.1_Regression.properties","browser");
//			 String url = PropertyHandler.getPropValue("resources\\TestData\\Base\\ICargo_1.1_Regression.properties","iCargoURL");
			BaseSetup.browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
			String url = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("portalURL");
			//browserName = PropertyHandler.getPropValue(browser, "browser");
		     //this.browserName = browserName;
		     browserName=String.valueOf(browserName.toUpperCase());
		       //browser = Browser.valueOf(browser.toUpperCase());
		      switch (browserName) {
		          case "FIREFOX":
		              String sFF = System.getProperty("user.dir");
		              String pathFF = sFF + "\\lib\\geckodriver.exe";
		              System.setProperty("webdriver.gecko.driver", pathFF);
		              capabilities = DesiredCapabilities.firefox();
		              capabilities.setBrowserName("firefox");
		              capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
		              capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		              capabilities.setCapability("marionette", true);
		              capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
		              // Below line can be added to avoid full page load.
		              // profile.setPreference("webdriver.load.strategy", "unstable");
		              driver = new FirefoxDriver(capabilities);
		              break;
		          case "IE":
		              capabilities = DesiredCapabilities.internetExplorer();

		              String s2 = System.getProperty("user.dir");
		              String path = s2 + "\\lib\\IEDriverServer.exe";

		              System.setProperty("webdriver.ie.driver", path);
		              
		              //capabilities.setBrowserName("iexplore");

		             // capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		              //capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
		              //capabilities.setCapability("ignoreProtectedModeSettings", true);

		              driver = new InternetExplorerDriver(capabilities);
		              break;
		          case "CHROME":
		              capabilities = DesiredCapabilities.chrome();
		              String sc2 = System.getProperty("user.dir");
		              String pathc = sc2 + "\\lib\\chromedriver.exe";

		              System.setProperty("webdriver.chrome.driver", pathc);
		              ChromeOptions options = new ChromeOptions();
		              options.addArguments("--no-sandbox");
		  		      options.addArguments("--disable-dev-shm-usage");// Added for checking
//		  		      options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//		              Proxy proxy = new Proxy();// Added for checking for proxy settings
//		              proxy.setProxyType(Proxy.ProxyType.SYSTEM);// Added for checking for
		              // proxy settings
		              capabilities.setBrowserName("chrome");
		              capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
//		              capabilities.setCapability("proxy", proxy);// Added for checking for proxy settings
		              capabilities.setCapability(ChromeOptions.CAPABILITY, options);// Added
		              // for
		              // checking
		              capabilities.setCapability("chrome.switches",
		                      Arrays.asList("--start-maximized"));
		              capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
//		              capabilities.setCapability("pageLoadStrategy", "eager");
		              driver = new ChromeDriver(capabilities);
		              break;
		              
		              
		          case "WEBDRIVER_MANAGER":
		        	  WebDriverManager.chromedriver().setup();
		        	  driver = new ChromeDriver();
		        	  break;

		          case "SAUCE_LABS":
		        	  capabilities = new DesiredCapabilities();
	                  capabilities.setCapability("username", SAUCE_USERNAME);
	                  capabilities.setCapability("accessKey", SAUCE_ACCESS_KEY);
	                  capabilities.setCapability("platform", "Windows 10");
	                  capabilities.setCapability("browserName", "Chrome");
	                  capabilities.setCapability("version", "latest");
	                  capabilities.setCapability("name", "iCargo Test");
	                  capabilities.setCapability("screenResolution", "1280x800");
//	                  capabilities.setCapability("parentTunnel", tunnel);
//	                  capabilities.setCapability("tunnelIdentifier", "LAB");
	                  driver = new RemoteWebDriver(new java.net.URL(SAUCE_URL), capabilities);
			        break; 
		      
		      }
		      driver.manage().window().maximize();
		      driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		      driver.get(url);
//		      test.log(LogStatus.PASS, "Started the browser and entered the iCargo url");
		      /*Create and initialize SoftAssert object*/
		      Assertion.setSoftAssert(Assertion.createSoftAssert());
			  return driver;
		  }

		public WebDriver getDriver() {
			return this.driver;
		}

		public ExtentTest getTest() {
//			test = extent.startTest(run.split("@")[0]);
			return  test;
		}
//		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
}
