package com.ibsplc.pageObjects;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ibsplc.common.BasePage;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Screen OPR344 - Export Manifest Created on 07/01/2019
 * 
 * @author Shalini
 */
public class OPR344 extends BasePage {

	public static String FRAME = "iCargoContentFrameOPR344";
	public static String screenFrame = "iCargoContentFrameOPR344";
	private static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"OPR_FLT.properties");
	private static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"Generic.properties");
	WebDriver driver;
	ExtentTest test;
	String dataFileName;
	private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");

	private By txtbx_carriercode, txtbx_ATDdate, txtbx_flightNumber, txtbx_date, btn_List;
	private By sel_blockedFor, txt_remarks, btn_blk_details, chcbx_Bulk, btn_manifst, btn_finalize, btn_close, yesBtn,
			btn_editIcon, btn_switchRole, btn_station, btn_ok, btn_flightfinalize, txt_statusfinalized, btn_assignhere,
			btn_Manifest, btn_PopOk, btn_Popup_Save,
			// A-7290
			Select_Rolegroup, btn_Bulk, Icon_bulk;

	public OPR344(WebDriver driver, String dataFileName, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		initElements();
		this.dataFilePath = this.dataFilePath + dataFileName;
		this.dataFileName = dataFileName;
	}

	/**
	 * Method to initialize page elements
	 */
	private void initElements() {
		yesBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
		txtbx_ATDdate = MiscUtility.getWebElement(objFilepath, "OPR344_txtbx_ATDdate");
		txtbx_carriercode = MiscUtility.getWebElement(objFilepath, "OPR344_txtbx_carriercode");
		txtbx_flightNumber = MiscUtility.getWebElement(objFilepath, "OPR344_txtbx_flightNumber");
		txtbx_date = MiscUtility.getWebElement(objFilepath, "OPR344_txtbx_date");
		btn_List = MiscUtility.getWebElement(objFilepath, "OPR344_btn_List");
		chcbx_Bulk = MiscUtility.getWebElement(objFilepath, "OPR344_chcbx_Bulk");
		btn_manifst = MiscUtility.getWebElement(objFilepath, "OPR344_btn_manifst");
		btn_finalize = MiscUtility.getWebElement(objFilepath, "OPR344_btn_finalize");
		btn_close = MiscUtility.getWebElement(objFilepath, "OPR344_btn_close");
		btn_editIcon = MiscUtility.getWebElement(objFilepath, "OPR344_btn_editIcon");

		btn_switchRole = MiscUtility.getWebElement(objFilepath, "OPR344_btn_switchRole");
		btn_station = MiscUtility.getWebElement(objFilepath, "OPR344_btn_station");
		btn_ok = MiscUtility.getWebElement(objFilepath, "OPR344_btn_ok");
		btn_flightfinalize = MiscUtility.getWebElement(objFilepath, "OPR344_btn_flightfinalize");
		txt_statusfinalized = MiscUtility.getWebElement(objFilepath, "OPR344_txt_statusfinalized");
		btn_assignhere = MiscUtility.getWebElement(objFilepath, "OPR344_btn_assignhere");
		btn_Manifest = MiscUtility.getWebElement(objFilepath, "OPR344_btn_Manifest");
		btn_PopOk = MiscUtility.getWebElement(objFilepath, "OPR344_btn_PopOk");
		btn_Popup_Save = MiscUtility.getWebElement(objFilepath, "OPR344_btn_Popup_Save");
		btn_Bulk = MiscUtility.getWebElement(objFilepath, "OPR344_btn_Bulk");
		Icon_bulk = MiscUtility.getWebElement(objFilepath, "OPR344_Icon_bulk");
	}

	/**
	 
	 * @author Sharath
	 */
	public OPR344 processExportManifest(String CarrierCode, String FlightNo, String fltDt, String ULD) {
		boolean isFound = true;
		minWait();
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		ULD = PropertyHandler.getPropValue(dataFilePath, ULD);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		maxWait();
		//h5[text()='AKE70426AA']/../preceding-sibling::div[2]/input
		String pre = "//h5[text()='";
		String suff = "']/../preceding-sibling::div[2]/input";
		click(By.xpath(pre + ULD + suff));
		
//		click(By.xpath("//*[@data-uldnumber='BULK'][text()='BULK']"));
		minWait();
		try {
//			maxWait();
			test.log(LogStatus.INFO, "Assigning the "+ULD+" for buildup");
			click(By.xpath("//span[text()='Assign Here']"));
			maxWait();
			/*try {
				continueEmbargo();
			} catch (Exception e) {
			}*/
			// click(By.xpath("//span[text()='Assign Here']"));
		} catch (Exception e) {
			logger.info("No need to assign here separately as this is bulk shipment");
		}
		if(verifyElementPresent(By.xpath("//span[contains(@class,'error')]"))){
			click(By.xpath("//span[contains(@class,'error')]"));
			String status = getText_JS(By.xpath("//div[contains(@class,'errorrow')]//p")).trim();
			if(status.contains("Invalid Aircraft")){
				test.log(LogStatus.FAIL, status);
				Assert.fail();
			}
		}
		maxWait();
		maxWait();
		click(btn_Manifest);
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actualValue + "");
		actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actualValue + "");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(btn_flightfinalize)).click();
		if (driver
				.findElements(By
						.xpath("//span[text()='Flight not departed from previous airport. Do you want to continue?']"))
				.size() > 0) {
			click(By.xpath("//button[text()='Ok']"));
		}
		// click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click(); maxWait();
			 */
			maxWait();
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			// enterKeys(By.xpath("//input[@name='actualDate']"), "-1" +
			// Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}
		return this;		
	}

	public OPR344 processExportManifestTestData(String CarrierCode, String FlightNo, String fltDt, String ULD) {
		boolean isFound = true;
		minWait();
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		maxWait();
		//h5[text()='AKE70426AA']/../preceding-sibling::div[2]/input
		String pre = "//h5[text()='";
		String suff = "']/../preceding-sibling::div[2]/input";
		click(By.xpath(pre + ULD + suff));
		
//		click(By.xpath("//*[@data-uldnumber='BULK'][text()='BULK']"));
		minWait();
		try {
//			maxWait();
			click(By.xpath("//span[text()='Assign Here']"));
			maxWait();
			/*try {
				continueEmbargo();
			} catch (Exception e) {
			}*/
			// click(By.xpath("//span[text()='Assign Here']"));
		} catch (Exception e) {
			logger.info("No need to assign here separately as this is bulk shipment");
		}
		maxWait();
		maxWait();
		click(btn_Manifest);
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actualValue + "");

		actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actualValue + "");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(btn_flightfinalize)).click();
		if (driver
				.findElements(By
						.xpath("//span[text()='Flight not departed from previous airport. Do you want to continue?']"))
				.size() > 0) {
			click(By.xpath("//button[text()='Ok']"));
		}
		// click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click(); maxWait();
			 */
			maxWait();
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			// enterKeys(By.xpath("//input[@name='actualDate']"), "-1" +
			// Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}
		return this;		
	}
	
	/**
	 * Method to close current page and return to Homepae returns the instance
	 * of Homepage
	 * 
	 * @return
	 * @author a-7868 Krishna <19-01-2018>
	 */
	public HomePage close() {

		if (verifyElementPresent(btn_close))
			click(btn_close);
		else
			driver.findElements(By.className("remove")).get(1).click();

		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn))
			click(yesBtn);
		return new HomePage(driver, dataFileName, test);
	}

	/**
	 * Method to switch role to the given Destination
	 * 
	 * @param:
	 * @author Shalini on 07/12/2019
	 */
	public OPR344 SwitchRole(String stationCode, String Office, String RoleGroup) {
		stationCode = PropertyHandler.getPropValue(dataFilePath, stationCode);
		driver.switchTo().defaultContent();
		click(btn_editIcon);
		click(btn_switchRole);
		driver.switchTo().frame("swichRoleiframe");

		click(btn_station);
		selectByText(btn_station, stationCode);
		minWait();
		click(btn_ok);
		maxWait();
		driver.switchTo().defaultContent();
		return this;
	}

	// A-8680

	public OPR344 modifytheFSUMANfile(String prefix, String awbNo, String org, String dest, String carrierwithFlightno,
			String fltDtwithoutYY, String path, String pcs, String wt) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");
		String splitawbdetails[] = split[1].split("-");

		int size = split.length;

		// modify awbpre
		modifyFile(messagefilepath, splitawbdetails[0].substring(2, 5), prefix);

		// modify awb and org+dest
		modifyFile(messagefilepath, splitawbdetails[1], awbNo + org + dest);

		// modify fltno.
		modifyFile(messagefilepath, split[3], carrierwithFlightno);

		// modify date
		modifyFile(messagefilepath, split[4], fltDtwithoutYY);

		// modify orgdest
		modifyFile(messagefilepath, split[5], org + dest);
		// modify pieces
		String[] wtandpieces = split[6].split("T");
		String[] wtandpieces1 = wtandpieces[1].split("K");
		modifyFile(messagefilepath, wtandpieces1[0], pcs);

		// modify weight
		modifyFile(messagefilepath, wtandpieces1[1], wt);

		// modify pieces and weight
		modifyFile(messagefilepath, split[2].substring(0, 7), split[6]);

		return this;
	}

	/**
	 * Method to list an AWB, fill the Manditory fields and click on Save button
	 * 
	 * @param: Location,pcs
	 * @author Shalini on 31/12/2018
	 */

	public OPR344 checkStatusAndFinalizeFlight(String CarrierCode, String FlightNo, String fltDt, String ULDNo,
			String prefix, String awb) {
		boolean isFound = true;
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		ULDNo = PropertyHandler.getPropValue(dataFilePath, ULDNo);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		test.log(LogStatus.INFO, "Staring with the Export Manifest");
		maxWait();
		/*
		 * waitForElement(By.xpath("//input[@id='flightnumber_carrier']"));
		 * WebElement
		 * ele=driver.findElement(By.xpath("//input[@id='flightnumber_carrier']"
		 * ));
		 */
		waitForElementUntilAttributePresent(waitForElement(By.xpath("//input[@id='flightnumber_carrier']")), "value",
				CarrierCode);
		// enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		test.log(LogStatus.INFO, "The awb : " + awb + " has been listed successfully");
		maxWait();
		maxWait();
		try {
			if (!ULDNo.equals(null)) {
				String ULDno = driver.findElement(By.xpath("(//h5[contains(text(),'AKE')])[last()]")).getText();
				click(By.xpath("(//h5[contains(text(),'AKE')]/parent::div/preceding-sibling::div/button)[last()]"));
				String awbno = driver.findElement(By.xpath("//a[@data-shipmentkey]")).getText().trim()
						.replaceAll("[^0-9]", "");
				if (ULDno.equals(ULDNo) && awbno.equals(prefix + awb)) {
					test.log(LogStatus.PASS, "The ULD No. : " + ULDno + " and the AWB No. : " + prefix
							+ "-" + awbno + " has been correctly picked");
					captureAndAddScreenshot();
				} else {
					test.log(LogStatus.FAIL, "The ULD No. : " + ULDno + " and the AWB No. : " + prefix
							+ "-" + awbno + " aren't been correctly picked");
				}
			} else {
				System.out.println("null value");
			}
		} catch (Exception e) {
			click(By.xpath("(//div[@aria-label='ULD No.']/../../div[2]//button)[1]"));
			String awbno = driver.findElement(By.xpath("//a[@data-shipmentkey]")).getText().trim().replaceAll("[^0-9]",
					"");
			if (awbno.equals(prefix + awb)) {
				test.log(LogStatus.PASS,
						"The AWB No. : " + prefix + "-" + awbno + " has been correctly picked");
				captureAndAddScreenshot();
			} else {
				test.log(LogStatus.FAIL,
						"The AWB No. : " + prefix + "-" + awbno + " aren't been correctly picked");
			}
		}
		click(btn_flightfinalize);
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			// click(By.xpath("//button[contains(text(),'Cancel')]"));
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click();
			 */
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			maxWait();
			minWait();
			enterKeys(txtbx_ATDdate, "-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been closed");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully with status : " + status);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.FAIL, "Flight is not finalized succeessfully with status :" + status);
			Assert.fail("Flight is not finalized succeessfully with status :" + status);
		}
		return this;
	}

	// A-8680
	public OPR344 modifytheFFMfile(String prefix, String awbNo, String org, String dest, String fltNo,
			String fltDtwithoutYY, String pieces, String weight, String carrierwithFlightno, String path,
			String deptime, String uldNo) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);
		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		deptime = PropertyHandler.getPropValue(dataFilePath, deptime);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		// String messagefilepath = "./resources/SampleMessages/FFMAA.txt";
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// modify flight details
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify org, dest
		modifyFile(messagefilepath, split[4].substring(0, 3), org);
		modifyFile(messagefilepath, split[4].substring(3, 6), dest);

		// modify date
		modifyFile(messagefilepath, split[5].substring(0, 5), fltDtwithoutYY);

		// modify dep time
		modifyFile(messagefilepath, split[5].substring(5, 9), deptime);

		// modify uldNo
		modifyFile(messagefilepath, split[7], uldNo);

		// modify pre,awb,org+dest
		String[] preawb = split[8].split("-");
		modifyFile(messagefilepath, preawb[1].substring(1, 4), prefix);
		modifyFile(messagefilepath, preawb[2].substring(0, 8), awbNo);
		modifyFile(messagefilepath, preawb[2].substring(8, 11), org);
		modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify pcs and wt
		String[] wtandpieces = split[9].split("T");
		modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify awb with org and dest
		modifyFile(messagefilepath, wtandpieces[1].substring(0, 2), pieces);
		modifyFile(messagefilepath, wtandpieces[1].substring(3, 6), weight);
		return this;
	}

	/**
	 * Method to Process Export manifest screen and Finalizeing the Flight
	 * 
	 * @param: CarrierCode,FlightNo,Location,pcs,fltDt,awbno,uldNo
	 * @author Shalini/Sharath on 21-01-2019
	 */

	public OPR344 processExportManifestWithULD(String CarrierCode, String FlightNo, String fltDt, String awbno,
			String uldNo) {
		boolean isFound = true;
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, ".");
		click(btn_List);
		maxWait();
		String AWBcheckbx = "//*[contains(text()," + awbno + ")]/../../..//input";
		driver.findElement(By.xpath(AWBcheckbx)).click();

		String ULDnoChckbx = "//*[contains(text()," + uldNo + ")]/../../..//input";
		driver.findElement(By.xpath(ULDnoChckbx)).click();
		minWait();
		click(btn_assignhere);
		click(chcbx_Bulk);
		click(btn_Manifest);
		switchToSecondWindow();
		click(btn_close);
		switchBackToFirstWindow();
		click(btn_flightfinalize);
		maxWait();
		if (driver.getWindowHandles().size() > 1) {
			switchToSecondWindow();
			click(btn_PopOk);
			switchBackToFirstWindow();
			switchToSecondWindow();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			switchBackToFirstWindow();
			String actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
			if (actaualValue.equalsIgnoreCase("100%")) {
				test.log(LogStatus.PASS, "BuildUp is 100%");
			} else {
				Assert.fail("Error in Message");
				test.log(LogStatus.FAIL, "BuildUp is failed");
			}
			actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
			if (actaualValue.equalsIgnoreCase("100%")) {
				test.log(LogStatus.PASS, "Manifest is 100%");
			} else {
				Assert.fail("Error in Message");
				test.log(LogStatus.FAIL, "Manifest is failed");
			}
		} else {
			String actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
			if (actaualValue.equalsIgnoreCase("100%")) {
				test.log(LogStatus.PASS, "BuildUp is 100%");
			} else {
				Assert.fail("Error in Message");
				test.log(LogStatus.FAIL, "BuildUp is failed");
			}
			actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
			if (actaualValue.equalsIgnoreCase("100%")) {
				test.log(LogStatus.PASS, "Manifest is 100%");
			} else {
				Assert.fail("Error in Message");
				test.log(LogStatus.FAIL, "Manifest is failed");
			}
		}

		minWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}
		return this;

	}

	public OPR344 checkStatusAndFinalizeFlightNonULD(String CarrierCode, String FlightNo, String fltDt, String prefix,
			String awb) {
		boolean isFound = true;
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		test.log(LogStatus.INFO, "Staring with the Export Manifest");
		maxWait();
		waitForElementUntilAttributePresent(waitForElement(By.xpath("//input[@id='flightnumber_carrier']")), "value",
				CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		test.log(LogStatus.INFO, "The awb : " + awb + " has been listed successfully");
		maxWait();
		maxWait();
		click(By.xpath("//h5[contains(text(),'BULK')]//preceding::button[1]"));
		String awbno = driver.findElement(By.xpath("//a[@data-shipmentkey]")).getText().trim().replaceAll("[^0-9]", "");
		if (awbno.contains(prefix + awb)) {
			test.log(LogStatus.PASS,
					"The AWB No. : " + prefix + "-" + awbno + " has been correctly picked");
		} else {
			test.log(LogStatus.FAIL,
					"The AWB No. : " + prefix + "-" + awbno + " isn't been correctly picked");
		}
		click(By.xpath("//button[@id='CMP_Operations_FltHandling_ExportManifest_Close']"));
		/*
		 * click(By.id("CMP_Operations_FltHandling_ExportManifest_Notoc"));
		 * click(By.xpath("//table[@id='dgrtable']/tbody/tr/td[1]/input"));
		 * selectByText(By.xpath(
		 * "//select[@id='CMP_operations_flthandling_Notoc_uld0']"),
		 * "BULK-DFW"); click(By.name("btGenerate")); handleAlert("Dismiss",
		 * "OPR017"); click(By.name("btClose"));
		 */
		maxWait();
		click(btn_flightfinalize);
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			click(By.xpath("//button[contains(text(),'Cancel')]"));
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Ok')]"))
			 * .click(); minWait(); minWait(); enterKeys(txtbx_ATDdate, "-1" +
			 * Keys.TAB);
			 * driver.findElement(By.name("actualTime")).sendKeys("00" +
			 * Keys.TAB); click(btn_Popup_Save);
			 */
			test.log(LogStatus.INFO, "The ATD pop-up has been closed");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully with status : " + status);
		} else {
			test.log(LogStatus.FAIL, "Flight is not finalized succeessfully with status :" + status);
			Assert.fail("Flight is not finalized succeessfully with status :" + status);
		}
		return this;
	}

	public void handleAlert(String alertOps, String ScreenName) {
		driver.switchTo().defaultContent();
		String AlertText = "";
		try {
			AlertText = driver.findElement(By.xpath("//div[@role='dialog']//p")).getText();
			switch (alertOps.valueOf(alertOps)) {
			case "getText":
				test.log(LogStatus.PASS, "Accepted Alert text " + AlertText + ScreenName);
				break;

			case "Accept":
				driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[1]")).click();
				test.log(LogStatus.PASS, "Accepted Alert with text " + ScreenName);

				break;
			case "Dismiss":
				driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[2]")).click();
				test.log(LogStatus.PASS, "Clicked on Dismiss button" + ScreenName);
				break;
			case "Close":
				driver.findElement(By.xpath("(//button[@title='Close'])[2]|//button[@name='btClose']")).click();
				test.log(LogStatus.PASS, "Clicked on Close button " + ScreenName);
				break;
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not able to handle Alert " + ScreenName);

		}
	}

	/**
	 * Method to list an AWB, fill the Manditory fields and process Manifest and
	 * Finalize
	 * 
	 * @param: Location,pcs
	 * @author Shalini on 14/03/2019
	 */
	public OPR344 processExportManifestWithBuildupManifest(String CarrierCode, String FlightNo, String fltDt,
			String awbno) {
		boolean isFound = true;
		minWait();
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		maxWait();
		maxWait();
		maxWait();
		driver.findElement(
				By.xpath("//button[contains(text(),awbno)]/../../..//input[@name='plannedShipmentTable-select']"))
				.click();
		click(btn_Bulk);
		minWait();
		// click(Icon_bulk);
		driver.switchTo().defaultContent();
		// driver.switchTo().frame("iCargoContentFrameOPR344");
		waitForFrameAndSwitch("iCargoContentFrameOPR344");
		WebElement ele = driver
				.findElement(By.xpath("//button[@class='ico-down-with-border btn btn-link no-border no-pad']"));
		javaScriptExecute("arguments[0].click()", ele);
		minWait();
		minWait();
		/*
		 * driver.switchTo().defaultContent();
		 * driver.switchTo().frame("iCargoContentFrameOPR344"); String
		 * AWBcheckbx = "//*[contains(text(),'" + awbno + "')]/../../..//input";
		 * driver.findElement(By.xpath(AWBcheckbx)).click();
		 */
		driver.findElement(By.xpath("//a[@class='awb-number awb-number-link']/../../..//input")).click();
		minWait();
		click(btn_manifst);
		maxWait();
		maxWait();
		try {
			// driver.switchTo().frame("popupContainerFrame");
			waitForFrameAndSwitch("popupContainerFrame");
			driver.findElement(By.name("btClose")).click();
		} catch (Exception e) {
		}
		// click(btn_close);
		minWait();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("iCargoContentFrameOPR344");
		click(btn_finalize);
		minWait();
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			minWait();
			minWait();
			enterKeys(txtbx_ATDdate, "-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
		} catch (Exception e) {
		}
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight :" + FlightNo + " is finalized succeessfully");
		} else {
			test.log(LogStatus.FAIL, "Flight is not finalized");
			Assert.fail("Error in Message");
		}
		return this;
	}

	/**
	 * Method to list an AWB, fill the Manditory fields and process Manifest and
	 * Finalize
	 * 
	 * @param: Location,pcs
	 * @author Shalini on 14/03/2019
	 */
	public OPR344 continueEmbargo() {
		try {
			driver.switchTo().frame("popupContainerFrame");
			maxWait();
			captureAndAddScreenshot();
			driver.findElement(By.name("btContinue")).click();
			driver.switchTo().defaultContent();
			waitForFrameAndSwitch(screenFrame);
			test.log(LogStatus.INFO, "Embargo has been handled");
		} catch (NoSuchElementException e) {
			driver.switchTo().defaultContent();
			waitForFrameAndSwitch(screenFrame);
			test.log(LogStatus.INFO, "No embargo found");
			System.err.println(e);
		}
		return this;
	}

	// Sharath
	public OPR344 PPSprocessExportManifestandFinalize(String CarrierCode, String FlightNo, String fltDt, String awbno,
			String origin) {
//		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
//		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);

		String pre = "//*[text()='";
		String suff = "']/../preceding-sibling::div[1]/button";

		click(By.xpath(pre + origin + "P" + FlightNo + suff));
		pre = "//a[contains(@data-shipmentkey,'";
		suff = "')]";
		if (driver.findElement(By.xpath(pre + awbno + suff)).isDisplayed()) {
			test.log(LogStatus.PASS, "The shipment : " + awbno + "has been auto-builtup");
		} else {
			test.log(LogStatus.FAIL, "The PPS shipment : " + awbno + "hasn't been auto-builtup");
			Assert.fail("The PPS shipment : " + awbno + "hasn't been auto-builtup");
		}
		maxWait();
		waitForElement(btn_manifst);
		click(btn_manifst);
		maxWait();
		maxWait();
		try {
			waitForFrameAndSwitch("popupContainerFrame");
			driver.findElement(By.name("btClose")).click();
			test.log(LogStatus.PASS, "The Manifest has been successfully performed");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "The Manifest hasn't been successfully performed");
		}
		// click(btn_close);
		minWait();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch("iCargoContentFrameOPR344");
		click(btn_finalize);
		minWait();
		try {
			// driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
			// click(By.xpath("//button[contains(text(),'Cancel')]"));
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			minWait();
			minWait();
			enterKeys(txtbx_ATDdate, "-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD popup has been closed");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD popup didn't appear");
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.FAIL, "Flight is not finalized");
			captureAndAddScreenshot();
			Assert.fail("Error in Message");
		}
		return this;
	}

	/**
	 * @author A-8452 Faizan
	 * altered by Sharath
	 * @param CartULDNumber
	 */
	private void assignAsCart(String CartULDNumber, String dest) throws AWTException {
		String pre = "//div[@aria-label='";
		String suff = "']/div";
		click(By.xpath("//span[contains(text(),'Add ULD')]"));
		minWait();
		click(By.xpath("//input[@name='uld.barrowFlag']"));
		enterKeys(By.xpath("//input[@name='uld.uldNumber']"), CartULDNumber + Keys.TAB);
		minWait();
		// Robot r = new Robot();
		maxWait();
		driver.findElement(By.xpath("(//div[text()='Select...'])[1]")).click();
//		driver.findElement(By.xpath(pre + dest + suff)).click();
		minWait();
		// r.keyPress(KeyEvent.VK_ENTER);
		// r.keyRelease(KeyEvent.VK_ENTER);
		// minWait();
		click(By.xpath("//button[text()='Save ']"));
		maxWait();
		maxWait();
		// driver.findElement(By.xpath("//button[@formname='uldManifest'][text()='Close']")).click();
		click(By.xpath("//button[@formname='uldManifest'][text()='Close']"));
		minWait();
	}
	
	/**
	 * @author A-8452 Faizan
	 * altered by Sharath
	 * @param CartULDNumber
	 */
	private void assignAsULD(String ULDNumber, String dest) throws AWTException {
		String pre = "//div[@aria-label='";
		String suff = "']/div";
		click(By.xpath("//span[contains(text(),'Add ULD')]"));
		minWait();
		enterKeys(By.xpath("//input[@name='uld.uldNumber']"), ULDNumber + Keys.TAB);
		minWait();
		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		maxWait();
		driver.findElement(By.xpath("(//div[text()='Select...'])[1]")).click();
  //	driver.findElement(By.xpath(pre + dest + suff)).click();
		minWait();
		// r.keyPress(KeyEvent.VK_ENTER);
		// r.keyRelease(KeyEvent.VK_ENTER);
		// minWait();
		click(By.xpath("//button[text()='Save ']"));
		maxWait();
	
		// driver.findElement(By.xpath("//button[@formname='uldManifest'][text()='Close']")).click();
		click(By.xpath("//button[@formname='uldManifest'][text()='Close']"));
		minWait();
	}

	/**
	 * @author A-8452 Faizan
	 * @param awbNo
	 * @param carrierCode
	 * @param FlightNo
	 * @param flightDt
	 * @param CartULDNumber
	 * @return
	 */
	public OPR344 performExportManifestWithAWBasCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String origin, String dest) {
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
		String CartULDNumber = origin + FlightNo + carrierCode;
		
		try {
			assignAsCart(CartULDNumber, dest);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		// manifestation and finalization
		maxWait();
		click(btn_Manifest);
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actualValue + "");

		actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actualValue + "");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(btn_flightfinalize)).click();
		if (driver
				.findElements(By
						.xpath("//span[text()='Flight not departed from previous airport. Do you want to continue?']"))
				.size() > 0) {
			click(By.xpath("//button[text()='Ok']"));
		}
		// click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click(); maxWait();
			 */
			maxWait();
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			// enterKeys(By.xpath("//input[@name='actualDate']"), "-1" +
			// Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}

		return this;
	}

	
	/**
	 * @author A-8452 Faizan
	 * @param awbNo
	 * @param carrierCode
	 * @param FlightNo
	 * @param flightDt
	 * @param CartULDNumber
	 * @return
	 */
	public OPR344 performExportManifestWithAWBasULD(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String origin, String dest, String ULDNumber) {
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		ULDNumber = PropertyHandler.getPropValue(dataFilePath, ULDNumber);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
		try {
			assignAsULD(ULDNumber, "");
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + ULDNumber.trim() + "']"));

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		// manifestation and finalization
		maxWait();
		click(btn_Manifest);
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actualValue + "");

		actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actualValue + "");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(btn_flightfinalize)).click();
		if (driver
				.findElements(By
						.xpath("//span[text()='Flight not departed from previous airport. Do you want to continue?']"))
				.size() > 0) {
			click(By.xpath("//button[text()='Ok']"));
		}
		// click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click(); maxWait();
			 */
			maxWait();
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			// enterKeys(By.xpath("//input[@name='actualDate']"), "-1" +
			// Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}

		return this;
	}

	
	
	public void listWithFlightDetails(String CarrierCode, String FlightNo, String fltDt) {
		// CarrierCode = PropertyHandler.getPropValue(dataFilePath,
		// CarrierCode);
		// FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
	//	fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		waitForElementUntilAttributePresent(waitForElement(By.xpath("//input[@id='flightnumber_carrier']")), "value",
				CarrierCode);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		maxWait();
	}

	public void listWithFlightDetailsTestData(String CarrierCode, String FlightNo, String fltDt) {
		waitForElementUntilAttributePresent(waitForElement(By.xpath("//input[@id='flightnumber_carrier']")), "value",
				CarrierCode);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		maxWait();
	}
	
	/**
	 * Method to Process Export manifest screen and Finalizeing the Flight for
	 * pps shipment
	 * 
	 * @param: CarrierCode,FlightNo,Location,pcs,fltDt,awbno
	 * @author Souvik (A-8457) on 5-04-2019
	 */

	public OPR344 ManifestFinalizeforPPS(String CarrierCode, String FlightNo, String fltDt) {
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		enterKeys(txtbx_carriercode, CarrierCode);
		enterKeys(txtbx_flightNumber, FlightNo);
		enterKeys(txtbx_date, fltDt + Keys.TAB);
		click(btn_List);
		maxWait();
		minWait();
		click(btn_Manifest);
		maxWait();
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		maxWait();
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actaualValue + "");

		actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actaualValue + "");
		click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			maxWait();

			enterKeys(By.xpath("//input[@name='actualDate']"), "-1" + Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}
		return this;

	}

	/*
	 * Souvik A-8457 Manifest only as a cart
	 * 
	 */
	   public OPR344 generateCartNumber(String origin, String cartNo){
           
           int max = 9999;
           int min = 1000;
           int cartno=(int)(Math.random()*(max-min+1)+min);
           String cart=origin+cartno;
           
         //  cart = PropertyHandler.setPropValue(dataFilePath, cartNo, cart);
           return this;
       }

	public OPR344 performOnlyManifestWithAWBasCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String CartULDNumber, String dest) {
		carrierCode = PropertyHandler.getPropValue(dataFilePath,carrierCode);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();

		minWait();		
		
		try {
			
			assignAsCart(CartULDNumber,dest);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/../../..//input";
		check(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		
		// manifestation and finalization
		maxWait();
		
		
		click(btn_Manifest);
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		maxWait();
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		
		minWait();
		String actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actaualValue + "");

		actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actaualValue + "");
		maxWait();

		return this;
	}
	
	//overridden method to auto generate CartNo (added origin to parameters)
	
	public OPR344 performOnlyManifestWithAWBasCart(String awbNo, String carrierCode, String FlightNo, 
 String flightDt,String CartULDNumber, String origin, String dest) {
		
//		carrierCode = PropertyHandler.getPropValue(dataFilePath,carrierCode);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();

		minWait();		
		String CartULDNumber1= origin + awbNo.substring(4) + "AA";
////		try {
////			generateCartNumber(origin,CartULDNumber);
////			maxWait();
//	
////			CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);
//					
////			assignAsCart(CartULDNumber1,dest);
//			
//		} catch (AWTException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/../../..//input";
		check(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber1.trim() + "']"));
		
		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		
		// manifestation and finalization
		maxWait();
		
		//buildup verification
		
		
		String editCart = "//h5[text()='" + CartULDNumber1.trim() + "']/../following-sibling::div[4]//button[3]";
		click(By.xpath(editCart));
		maxWait();
		if (verifyElementPresent(By.xpath("//div[@class='pad-xs']//div[@class='ReactVirtualized__Table table-list']//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@aria-label='row']//div[@aria-colindex='1']"))) {
			String awbPresent = driver.findElement(By.xpath("//div[@class='pad-xs']//div[@class='ReactVirtualized__Table table-list']//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@aria-label='row']//div[@aria-colindex='1']")).getText();
			
			if (awbPresent.contains(awbNo)) {
				
				test.log(LogStatus.PASS, "BuildUp of AWB : "+ awbNo + " is Successful ");
			}else
				test.log(LogStatus.FAIL, "BuildUp of AWB : "+ awbNo + " Failed");	
		}
	
		click(By.xpath("//button[@formname='uldManifest'][text()='Close']"));
		maxWait();
		
		
		click(btn_Manifest);
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		maxWait();
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		
		minWait();
		String actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actaualValue + "");

		actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actaualValue + "");
		maxWait();

		return this;
	}
	
	//overriden method to handle UNID popup for split booking manifestation(added pcs  to parameters)
	
	public OPR344 performOnlyManifestWithAWBasCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String CartULDNumber, String origin, String dest, String pcs) {
		
//		carrierCode = PropertyHandler.getPropValue(dataFilePath,carrierCode);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();

		minWait();		
		
		try {
			generateCartNumber(origin,CartULDNumber);
			maxWait();
			
			CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);
					
			assignAsCart(CartULDNumber,dest);
			
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/../../..//input";
		check(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));
		
		handleUNIDpopUp(pcs);
		maxWait();

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		
		// manifestation and finalization
		maxWait();
		
		//buildup verification
		
		
		String editCart = "//h5[text()='" + CartULDNumber.trim() + "']/../following-sibling::div[4]//button[3]";
		click(By.xpath(editCart));
		maxWait();
		if (verifyElementPresent(By.xpath("//div[@class='pad-xs']//div[@class='ReactVirtualized__Table table-list']//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@aria-label='row']//div[@aria-colindex='1']"))) {
			String awbPresent = driver.findElement(By.xpath("//div[@class='pad-xs']//div[@class='ReactVirtualized__Table table-list']//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@aria-label='row']//div[@aria-colindex='1']")).getText();
			
			if (awbPresent.contains(awbNo)) {
				
				test.log(LogStatus.PASS, "BuildUp of AWB : "+ awbNo + " is Successful ");
			}else
				test.log(LogStatus.FAIL, "BuildUp of AWB : "+ awbNo + " Failed");	
		}
	
		click(By.xpath("//button[@formname='uldManifest'][text()='Close']"));
		maxWait();
		
		click(btn_Manifest);
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		maxWait();
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		
		minWait();
		String actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actaualValue + "");

		actaualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actaualValue + "");
		maxWait();

		return this;
	}
	/*
	 * Souvik A-8457 Offload and Reassign to different Flight
	 * 
	 */

	public OPR344 OffloadAndreAssignToFlight(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String CartULDNumber, Boolean Listing, String DestStation, String CarrierCode, String ToFlightNo,
			String ToFlightDt) {

//		carrierCode = PropertyHandler.getPropValue(dataFilePath,carrierCode);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);
//		DestStation = PropertyHandler.getPropValue(dataFilePath, DestStation);
//		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
//		ToFlightNo = PropertyHandler.getPropValue(dataFilePath, ToFlightNo);
//		ToFlightDt = PropertyHandler.getPropValue(dataFilePath, ToFlightDt);

		if (Listing == true) {
			listWithFlightDetails(carrierCode, FlightNo, flightDt);
		}

		maxWait();

		click(By.xpath("//div[@class='ReactVirtualized__Table__row table-row']//button[@data-type='row-toggler']"));
		minWait();
		click(By.xpath("(//i[@class='icon ico-offload'])[2]"));
		minWait();

		driver.findElement(By.xpath("//span[@class='Select-arrow-zone']")).click();
		driver.findElement(By.xpath("//*[text()='Damaged']")).click();

		enterKeys(By.name("offloadRemarks"), "TEST");
		check(By.xpath("(//input[@class='form-check-input'])[last()]"));
		minWait();
		check(By.xpath("//input[@name='barrowFlag']"));
		enterKeys(By.name("uldNumber"), CartULDNumber);
		enterKeys(By.name("pou"), DestStation);
		enterKeys(By.name("flightnumber.carrierCode"), CarrierCode);
		enterKeys(By.name("flightnumber.flightNumber"), ToFlightNo);
		enterKeys(By.name("flightnumber.flightDate"), ToFlightDt);
		enterKeys(By.name("flightnumber.flightDate"),Keys.TAB);
		minWait();
		click(By.xpath("(//button[@formname='offloadPopupForm'])[contains(text(),'Save')]"));
		maxWait();
		test.log(LogStatus.PASS, "Offload is done and reassigned to different flight");
		maxWait();

		return this;
	}
	
	
	/**
	 * @author A-8452 Faizan
	 * @param awbNo
	 * @param carrierCode
	 * @param FlightNo
	 * @param flightDt
	 * @param CartULDNumber
	 * @return
	 */
	public OPR344 performExportManifestWithAWBasCartTestData(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String origin, String dest, String CartULDNumber ,boolean manifestFinalize) {
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		listWithFlightDetailsTestData(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
//		CartULDNumber = getCartNo(origin, FlightNo);
		try {
			assignAsCart(CartULDNumber, dest);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));
		maxWait();
		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		if(verifyElementPresent(By.xpath("//div[contains(@class,'icdanger alert')]//i"))){
			click(By.xpath("//div[contains(@class,'icdanger alert')]//i"));
			String status = getText_JS(By.xpath("//p[contains(text(),'The total amount of ICE exceeds the limit')]")).trim();
			if(status.contains("Cannot proceed")){
				test.log(LogStatus.PASS, "Booking is not allowed with status :"+status);
				captureAndAddScreenshot();
			}else{
				test.log(LogStatus.FAIL, "Booking is not allowed with status :"+status);
			}
		}
		maxWait();
		// manifestation and finalization
		
		maxWait();
		click(btn_Manifest);
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actualValue + "");

		actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actualValue + "");
		if(manifestFinalize){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(btn_flightfinalize)).click();
		if (driver
				.findElements(By
						.xpath("//span[text()='Flight not departed from previous airport. Do you want to continue?']"))
				.size() > 0) {
			click(By.xpath("//button[text()='Ok']"));
		}
		// click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click(); maxWait();
			 */
			maxWait();
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			// enterKeys(By.xpath("//input[@name='actualDate']"), "-1" +
			// Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}
		}
		return this;
	}

	public OPR344 performDGbuidUpAWBasULD(String awbNo1, String carrierCode, String FlightNo, String flightDt,
			String origin, String awbNo2, String ULDNumber) {
//		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
//		ULDNumber = PropertyHandler.getPropValue(dataFilePath, ULDNumber);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
		try {
			assignAsULD(ULDNumber, "");
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo1 + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo1 + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + ULDNumber.trim() + "']"));
		test.log(LogStatus.PASS, "Assigned awbNo1: "+awbNo1+" to uld: "+ULDNumber);
		captureAndAddScreenshot();

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		maxWait();
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo2 + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		AWBcheckbx = "//*[contains(text(),'" + awbNo2 + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + ULDNumber.trim() + "']"));
		test.log(LogStatus.PASS, "Assigned awbNo1: "+awbNo1+" to uld: "+ULDNumber);
		captureAndAddScreenshot();
		
		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		// manifestation and finalization
		maxWait();
		if(verifyElementEnabled(By.xpath("//span[@class='errorcount badge badge-danger']"))){
			click(By.xpath("//span[@class='errorcount badge badge-danger']"));
			String status = getText_JS(By.xpath("//p[contains(text(),'Cannot Proceed')]")).trim();
			if(status.contains("Incompatible")){
				test.log(LogStatus.PASS, "Verified incompatibilty :"+status);
				captureAndAddScreenshot();
				}
				else{
					test.log(LogStatus.FAIL, "Failed incompatibilty test:"+status);
				}
			}
		return this;
	}
	
	/**
	 * @author A-8457 Souvik
	 * 
	 * Method to Split and assign
	 * @param awbNo
	 * @param carrierCode
	 * @param FlightNo
	 * @param flightDt
	 * @param CartULDNumber
	 * @return
	 */
	public OPR344 SplitAndAssignToCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String origin, String dest, String pcs1, String wt1, String pcs2, String wt2, String i, String cartUldNumber, String cartUldNumber1, HashMap<String, String> data) {
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
//		pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
//		wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);
//		pcs2 = PropertyHandler.getPropValue(dataFilePath, pcs2);
//		wt2 = PropertyHandler.getPropValue(dataFilePath, wt2);
//		i = PropertyHandler.getPropValue(dataFilePath, i);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
		int a = Integer.parseInt(i);
		a=a+1;
	//	flightDt = PropertyHandler.getPropValue(dataFilePath, flightDt);
		String CartULDNumber = origin + FlightNo+ Integer.toString(a);
		a=a+1;
		String CartULDNumber1 = origin + FlightNo+ Integer.toString(a);
//		PropertyHandler.setPropValue(dataFilePath, "i", Integer.toString(a));
		try {
			assignAsCart(CartULDNumber, dest);
			data.put(cartUldNumber,CartULDNumber);
	//		PropertyHandler.setPropValue(dataFilePath,"CartULDNumber" , CartULDNumber);
			assignAsCart(CartULDNumber1, dest);
			data.put(cartUldNumber1,CartULDNumber1);
	//		PropertyHandler.setPropValue(dataFilePath,"CartULDNumber1" , CartULDNumber1);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		minWait();
		// To Split and assign
		click(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//a/i"));
		minWait();
		click(By.xpath("//*[contains(text(),'"+awbNo+"')]/../../..//a/i/following::button[contains(text(),' Split & Assign')][1]"));
		maxWait();
		enterKeys(By.name("pieces"), pcs1);
		enterKeys(By.name("weight.roundedDisplayValue"), wt1);
		click(By.xpath("//button[@type='submit']"));
		maxWait();
		click(By.xpath("(//*[contains(text(),'" + awbNo + "')]/../../..//input)[1]"));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));
		maxWait();
		handleShipmentLocationPopUp(pcs1);
		handleUNIDpopUp(pcs1);
//		driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
		maxWait();
		click(By.xpath("(//*[contains(text(),'" + awbNo + "')]/../../..//input)[1]"));
		click(By.xpath("//h5[text()='" + CartULDNumber1.trim() + "']"));
		maxWait();
		handleShipmentLocationPopUp(pcs2);
		handleUNIDpopUp(pcs2);
//		driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
		maxWait();
		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		// manifestation and finalization
		maxWait();
		test.log(LogStatus.PASS, "Carts have been builtup with split AWBs");
		return this;
	}
	
	/**
	 * @author A-8457 Souvik
	 * @param awbNo
	 * @param carrierCode
	 * @param FlightNo
	 * @param flightDt
	 * @param CartULDNumber
	 * @return
	 */
	public OPR344 BuildupWithAWBasCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String origin, String dest, String i) {
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
//		i = PropertyHandler.getPropValue(dataFilePath, i);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
    	maxWait();
		minWait();
		int a = Integer.parseInt(i);
		a=a+1;
//		PropertyHandler.setPropValue(dataFilePath, "i", Integer.toString(a));
		String CartULDNumber = origin + FlightNo+Integer.toString(a) + "A";
//		PropertyHandler.setPropValue(dataFilePath, "cart", CartULDNumber);
		try {
			assignAsCart(CartULDNumber, dest);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		// manifestation and finalization
		maxWait();
		test.log(LogStatus.PASS, "Cart has been build with an AWB");
		captureAndAddScreenshot();
		return this;
	}
	
	/**
	 * @author A-8457 Souvik
	 * @param awbNo
	 * @param carrierCode
	 * @param FlightNo
	 * @param flightDt
	 * @param CartULDNumber
	 * @return
	 */
	public OPR344 AddAWBtoExistingCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String CartULDNumber, String origin, String i) {
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);
////		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		//listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
		int a = Integer.parseInt(i);
		a=a+1;
//		PropertyHandler.setPropValue(dataFilePath, "i", Integer.toString(a));
		String CartULDNumber1 = origin + FlightNo+Integer.toString(a) + "A";
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber1.trim() + "']"));

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		maxWait();
		test.log(LogStatus.PASS, "AWB is added to existing cart");
		captureAndAddScreenshot();
		return this;
	}
	
	public OPR344 handleUNIDpopUp(String pcs){
		try{
			if(verifyElementPresent(By.xpath("//div[contains(text(),'UNID assignment is not done')]"))){
				click(By.xpath("//div[contains(text(),'UNID assignment is not done')]/../../div[contains(@class,'footer')]/button[text()='Ok']"));
				enterKeys(By.xpath("//input[contains(@name,'pcs')]"), pcs);
				click(By.xpath("//button[text()='Save']"));
				maxWait();
				maxWait();
				maxWait();
			}
			test.log(LogStatus.PASS, "UNID popup has been handled successfully");	
			captureAndAddScreenshot();
		}catch(Exception e){
			test.log(LogStatus.FAIL, "UNID popup has not been handled");	
		}
		return this;
	}
	
	public OPR344 handleShipmentLocationPopUp(String pcs){
		try{
			if(verifyElementPresent(By.xpath("//span[text()='Shipment Locations']"))){
			waitForFrameAndSwitch("popupContainerFrame");
			String pcsText1 = driver.findElement(By.xpath("//tbody[@id='warehousedetailsTable']/tr[1]/td[5]")).getText().trim();
			String pcsText2 = driver.findElement(By.xpath("//tbody[@id='warehousedetailsTable']/tr[2]/td[5]")).getText().trim();
			if(pcsText1.equals(pcs)){
				enterKeys(By.xpath("//tbody[@id='warehousedetailsTable']/tr[1]/td[6]/input"), pcsText1);
			}else{
				enterKeys(By.xpath("//tbody[@id='warehousedetailsTable']/tr[2]/td[6]/input"), pcsText2);	
			}
			click(By.xpath("//button[@name='btnOk']"));
			waitForFrameAndSwitch(screenFrame);
				maxWait();
				maxWait();
				maxWait();
			}
		test.log(LogStatus.PASS, "Shipment Location popup has been handled successfully");	
		captureAndAddScreenshot();
		}catch(Exception e){
		test.log(LogStatus.FAIL, "Shipment Location popup has not been handled");	
		}
		return this;
	}
	
	public String getCartNo(String origin, String FlightNo) {
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		String CartULDNumber = origin + FlightNo + Integer.toString(randomNumber);
		return CartULDNumber;

	}
	/**
     * Method to Finalize a flight
     * 
      * @param: Flight number, date, carrier code
     * @author Souvik on 19/04/2019
     */
     public OPR344 FinalizeFlight(String CarrierCode, String FlightNo,
                   String fltDt , boolean listing) {
            minWait();
            CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
            FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
            fltDt = PropertyHandler.getPropValue(dataFilePath,fltDt);
            if(listing==true)
            {
            enterKeys(txtbx_carriercode, CarrierCode);
            enterKeys(txtbx_flightNumber, FlightNo);
            enterKeys(txtbx_date, fltDt+Keys.TAB);
            click(btn_List);
            maxWait();
            }
     
            click(btn_manifst);
            maxWait();
            maxWait();
            try {
                   waitForFrameAndSwitch("popupContainerFrame");
                   driver.findElement(By.name("btClose")).click();
            } catch (Exception e) {
            }
            
            minWait();
            driver.switchTo().defaultContent();
            driver.switchTo().frame("iCargoContentFrameOPR344");
            click(btn_finalize);
            test.log(LogStatus.INFO, "Clicked on finalized flight button");
            maxWait();
            maxWait();
            maxWait();
            maxWait();
            try {
                   driver.findElement(By.xpath("//button[contains(text(),'Ok')]"))
                                .click();
                   maxWait();
                   maxWait();
                   enterKeys(By.xpath("//input[@name='actualDate']"), "-1" + Keys.TAB);
                   driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
                   driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
                   driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
                   click(btn_Popup_Save);
                   test.log(LogStatus.INFO, "The ATD pop-up has been handled");
            } catch (Exception e) {
                   test.log(LogStatus.INFO, "The ATD pop-up didn't appear"+e);
            }
            maxWait();
            maxWait();
            maxWait();
            /*String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
            if (status.equalsIgnoreCase("Finalized")) {
                   test.log(LogStatus.PASS,
                                "Flight is finalized succeessfully");
            } else {
                   Assert.fail("Error in Message");
                   test.log(LogStatus.FAIL, "Flight is not finalized");
            }*/
            return this;
     }
     
     /*
  	 *  A-8021 Offload From Flight
  	 * 
  	 */

  	public OPR344 performOffloadFromFlight(String awbNo, String carrierCode, String FlightNo, String flightDt,
  			String CartULDNumber, Boolean Listing) {

//  		carrierCode = PropertyHandler.getPropValue(dataFilePath,carrierCode);
//  		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//  		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//  		CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);

  		if (Listing == true) {
  			listWithFlightDetails(carrierCode, FlightNo, flightDt);
  		}

  		maxWait();

  		click(By.xpath("//div[@class='ReactVirtualized__Table__row table-row']//button[@data-type='row-toggler']"));
  		minWait();
  		click(By.xpath("(//i[@class='icon ico-offload'])[2]"));
  		minWait();

  		driver.findElement(By.xpath("//span[@class='Select-arrow-zone']")).click();
  		driver.findElement(By.xpath("//*[text()='Damaged']")).click();

  		enterKeys(By.name("offloadRemarks"), "TEST");
//  		check(By.xpath("(//input[@class='form-check-input'])[last()]"));
  		minWait();
//  		check(By.xpath("//input[@name='barrowFlag']"));
//  		enterKeys(By.name("uldNumber"), CartULDNumber);
//  		enterKeys(By.name("pou"), DestStation);
//  		enterKeys(By.name("flightnumber.carrierCode"), CarrierCode);
//  		enterKeys(By.name("flightnumber.flightNumber"), ToFlightNo);
//  		enterKeys(By.name("flightnumber.flightDate"), ToFlightDt);
//  		enterKeys(By.name("flightnumber.flightDate"),Keys.TAB);
  		click(By.xpath("(//button[@formname='offloadPopupForm'])[contains(text(),'Save')]"));
  		maxWait();
  		test.log(LogStatus.PASS, "Shipment offloaded from flight");
  		maxWait();

  		return this;
  	}
     
	//overloaded to handle UNID popup for Split booked items.(zimmy)
	public OPR344 performExportManifestWithAWBasCart(String awbNo, String carrierCode, String FlightNo, String flightDt,
			String origin, String dest, String pcs2, String CartULDNumber) {
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		pcs2 = PropertyHandler.getPropValue(dataFilePath, pcs2);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		listWithFlightDetails(carrierCode, FlightNo, flightDt);
		maxWait();
		minWait();
		String CartULDNumber1 = origin + FlightNo + "AA";
		
		try {
			
//			generateCartNumber(origin,CartULDNumber);
			maxWait();
			
//			CartULDNumber = PropertyHandler.getPropValue(dataFilePath, CartULDNumber);
			assignAsCart(CartULDNumber1, dest);
			maxWait();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		if (driver.findElements(By.xpath("//*[contains(text(),'" + awbNo + "')]/../../..//input")).size() <= 0) {
			click(By.xpath("//div[text()='Lying List']/i"));
		}
		String AWBcheckbx = "//*[contains(text(),'" + awbNo + "')]/preceding::div[2]/input";
		click(By.xpath(AWBcheckbx));
		click(By.xpath("//h5[text()='" + CartULDNumber.trim() + "']"));
		
		handleUNIDpopUp(pcs2);
		maxWait();

		if (verifyElementPresent(By.xpath("//button[contains(text(),'Ok')]"))) {
			click(By.xpath("//button[contains(text(),'Ok')]"));
		}
		// manifestation and finalization
		maxWait();
		
		//buildup verification
		
		
		String editCart = "//h5[text()='" + CartULDNumber.trim() + "']/../following-sibling::div[4]//button[3]";
		click(By.xpath(editCart));
		maxWait();
		if (verifyElementPresent(By.xpath("//div[@class='pad-xs']//div[@class='ReactVirtualized__Table table-list']//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@aria-label='row']//div[@aria-colindex='1']"))) {
			String awbPresent = driver.findElement(By.xpath("//div[@class='pad-xs']//div[@class='ReactVirtualized__Table table-list']//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@aria-label='row']//div[@aria-colindex='1']")).getText();
			
			if (awbPresent.contains(awbNo)) {
				
				test.log(LogStatus.PASS, "BuildUp of AWB : "+ awbNo + " is Successful ");
			}else
				test.log(LogStatus.FAIL, "BuildUp of AWB : "+ awbNo + " Failed");	
		}
		
		click(By.xpath("//button[@formname='uldManifest'][text()='Close']"));
		maxWait();
		
		click(btn_Manifest);
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		verifyElementPresent(By.xpath("//button[@name='btClose']"));
		driver.findElement(By.xpath("//button[@name='btClose']")).click();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[2]")).getText();
		test.log(LogStatus.PASS, "BuildUp is " + actualValue + "");

		actualValue = driver.findElement(By.xpath("(//span[@class='iprogress-text'])[1]")).getText();
		test.log(LogStatus.PASS, "Manifest is " + actualValue + "");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(btn_flightfinalize)).click();
		if (driver
				.findElements(By
						.xpath("//span[text()='Flight not departed from previous airport. Do you want to continue?']"))
				.size() > 0) {
			click(By.xpath("//button[text()='Ok']"));
		}
		// click(btn_flightfinalize);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on finalized flight button");
		maxWait();
		maxWait();
		try {
			/*
			 * driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"
			 * )) .click(); maxWait();
			 */
			maxWait();
			driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
			// enterKeys(By.xpath("//input[@name='actualDate']"), "-1" +
			// Keys.TAB);
			driver.findElement(By.xpath("//input[@name='actualDate']")).clear();
			driver.findElement(By.xpath("//input[@name='actualDate']")).sendKeys("-1" + Keys.TAB);
			driver.findElement(By.name("actualTime")).sendKeys("00" + Keys.TAB);
			click(btn_Popup_Save);
			test.log(LogStatus.INFO, "The ATD pop-up has been handled");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "The ATD pop-up didn't appear" + e);
		}
		maxWait();
		maxWait();
		String status = driver.findElement(By.xpath("//em[contains(text(),'Status')]/strong")).getText();
		if (status.equalsIgnoreCase("Finalized")) {
			test.log(LogStatus.PASS, "Flight is finalized succeessfully");
			captureAndAddScreenshot();
		} else {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Flight is not finalized");
		}

		return this;
	}
}