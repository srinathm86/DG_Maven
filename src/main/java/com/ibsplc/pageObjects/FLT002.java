package com.ibsplc.pageObjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ibsplc.common.BasePage;
import com.ibsplc.common.BaseSetup.Days;
import com.ibsplc.utils.BizUtility;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FLT002 extends BasePage{

	public FLT002(WebDriver driver,String dataFileName, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
        this.dataFileName = dataFileName;
        this.dataFilePath = dataFilePath+ dataFileName;
        this.test=test;
        initPageElements();
	}
	
	public static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "OPR_FLT.properties");
	public static String genFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties","Generic.properties");
	private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");
	public static String filepath;
	public static String FRAME = "iCargoContentFrameFLT002";
	WebDriver driver;   
	ExtentTest test;
    String dataFileName;
    
    By txt_flightCarrierCode;    
    By txt_flightNo;
    By txt_fromDate;
    By txt_toDate;
    By dropdown_orginLevel;
    By txt_origin;
    By dropdown_destinationLevel;
    By txt_destination;
    By dropdown_flightType;
    By dropdown_scheduleType;
    By txt_aircraftType;
    By btn_list;
    By tbl_oprFlts;
    By btn_close;
    By dropdown_status;
    By chkbox_active;
    By yesBtn;
    By btn_details;
    By btn_cancel;
    By btn_monitorFlts;
    By btn_revalidate;
    By btn_clear;
    By chkbox_dayOfOperation_Mon;
    By chkbox_dayOfOperation_Tue;
    By chkbox_dayOfOperation_Wed;
    By chkbox_dayOfOperation_Thu;
    By chkbox_dayOfOperation_Fri;
    By chkbox_dayOfOperation_Sat;
    By chkbox_dayOfOperation_Sun;
    By chkbox_selectTableItem;
    By maintainOperationalFlight_btn_legDetails;
    By maintainOperationalFlight_LegCapacity_txt_loadFactor;
    By maintainOperationalFlight_LegCapacity_btn_close;
    By maintainOperationalFlight_btn_close;
    By maintainOperationalFlight_dropdown_scheduleType;
    By maintainOperationalFlight_dropdown_flightType;
    By maintainOperationalFlight_table_leg;
    By maintainOperationalFlight_btn_save;
    By info_footer_error;
    By table_operationalFlights;
    By chkbox_canceled;
    By dialogueText;
    By noBtn;
    By info_tableNumberOfRecords;
    By maintainOperationalFlight_txt_route;
    By maintainOperationalFlight_LegCapacity_txt_departureTime;
    By maintainOperationalFlight_LegCapacity_txt_arrivalTime;
    By maintainOperationalFlight_LegCapacity_txt_aircraftType;
    By maintainOperationalFlight_LegCapacity_btn_viewCapacty;
    By maintainOperationalFlight_LegCapacity_btn_ok;
    By btn_create;
    By maintainOperationalFlight_txt_carrierCode;
    By maintainOperationalFlight_txt_flightNo;
    By maintainOperationalFlight_txt_flightDate;
    By maintainOperationalFlight_btn_list;
    By maintainOperationalFlight_btn_clear;
    By info_foot_layer;
    By btn_copy;
    By btn_bookingList;
    By listBookings_table_bookingDetails;
    By listBookings_btn_close;
    By monitorFlight_txt_carrierCode;
    By monitorFlight_txt_flightNo;
    By monitorFlight_btn_close;
    By btn_activate;
    By btn_flightHistory;
    By flightHistory_table_flightHistory;
    By flightHistory_btn_close;
    By maintainOperationalFlight_LegCapacity_txt_weight;
    By maintainOperationalFlight_LegCapacity_txt_volume;
    By maintainOperationalFlight_btn_segment;
    By maintainOperationalFlight_btn_sgementCapacity;
    By maintainOperationalFlight_SgementCapacity_txt_weight;
    By maintainOperationalFlight_SgementCapacity_txt_volume;
    By maintainOperationalFlight_SgementCapacity_btn_ok;
    By maintainOperationalFlight_SgementCapacity_btn_close;
    By okBtn;
    By maintainOperationalFlight_chkbox_selectTableItem;
    By link_editFilters;
    By info_tableRowCount;
    private By lbl_fromDate;
    private By lbl_toDate;
    List<WebElement> chkbox_selectTableItems;

    private void initPageElements(){
    	txt_flightCarrierCode	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_fltCarCode");
    	txt_flightNo	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_fltNum");    	
    	txt_fromDate	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_frmDt");
    	txt_toDate	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_toDt");
    	dropdown_orginLevel	= MiscUtility.getWebElement(objFilepath,"FLT002_dropdown_orginLevel");
    	txt_origin	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_origin");
    	dropdown_destinationLevel	= MiscUtility.getWebElement(objFilepath,"FLT002_dropdown_destinationLevel");
    	txt_destination	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_destination");
    	dropdown_flightType	= MiscUtility.getWebElement(objFilepath,"FLT002_dropdown_flightType");
    	dropdown_scheduleType	= MiscUtility.getWebElement(objFilepath,"FLT002_dropdown_scheduleType");
    	txt_aircraftType	= MiscUtility.getWebElement(objFilepath,"FLT002_txt_aircraftType");
    	btn_list	= MiscUtility.getWebElement(objFilepath,"FLT002_btn_list");
		tbl_oprFlts = MiscUtility.getWebElement(objFilepath,"FLT002_table_operationalFlights");
		table_operationalFlights	= MiscUtility.getWebElement(objFilepath,"FLT002_table_operationalFlights");
		btn_close	= MiscUtility.getWebElement(objFilepath,"FLT002_btn_close");
    	dropdown_status	= MiscUtility.getWebElement(objFilepath,"FLT002_dropdown_status");
    	chkbox_active	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_active");
    	yesBtn = MiscUtility.getWebElement(genFilepath,"Generic_btn_diaYes");
		btn_details = MiscUtility.getWebElement(objFilepath, "FLT002_btn_details");
		btn_cancel = MiscUtility.getWebElement(objFilepath, "FLT002_btn_cancel");
		btn_monitorFlts = MiscUtility.getWebElement(objFilepath, "FLT002_btn_monitorFlts");
		btn_revalidate = MiscUtility.getWebElement(objFilepath, "FLT002_btn_revalidate");
		btn_clear = MiscUtility.getWebElement(objFilepath, "FLT002_btn_clear");
		link_editFilters = MiscUtility.getWebElement(objFilepath, "FLT002_link_editFilters");
		
		chkbox_dayOfOperation_Mon	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Mon");
    	chkbox_dayOfOperation_Tue	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Tue");
    	chkbox_dayOfOperation_Wed	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Wed");
    	chkbox_dayOfOperation_Thu	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Thu");
    	chkbox_dayOfOperation_Fri	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Fri");
    	chkbox_dayOfOperation_Sat	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Sat");
    	chkbox_dayOfOperation_Sun	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_dayOfOperation_Sun");
    	maintainOperationalFlight_btn_close = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_close");
    	yesBtn = MiscUtility.getWebElement(genFilepath,"Generic_btn_diaYes");
    	okBtn = MiscUtility.getWebElement(genFilepath,"Generic_btn_ok");
    	info_footer_error = MiscUtility.getWebElement(genFilepath,"Generic_footer_error");
    	
    	btn_details	= MiscUtility.getWebElement(objFilepath,"FLT002_btn_details");
    	chkbox_selectTableItem	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_selectTableItem");
//    	chkbox_selectTableItems	= MiscUtility.getWebElements(driver, objFilepath, "FLT002_chkbox_selectTableItem");
    	maintainOperationalFlight_chkbox_selectTableItem	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_chkbox_selectTableItem");
    	maintainOperationalFlight_btn_legDetails	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_legDetails");
    	maintainOperationalFlight_LegCapacity_txt_loadFactor	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_txt_loadFactor");
    	maintainOperationalFlight_LegCapacity_btn_close	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_btn_close");
        maintainOperationalFlight_dropdown_scheduleType	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_dropdown_scheduleType");
        maintainOperationalFlight_dropdown_flightType	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_dropdown_flightType");
        maintainOperationalFlight_table_leg	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_table_leg");
        maintainOperationalFlight_btn_save	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_save");
        
        chkbox_canceled	= MiscUtility.getWebElement(objFilepath,"FLT002_chkbox_canceled");       
        dialogueText = MiscUtility.getWebElement(genFilepath,"Generic_info_msg");
        noBtn =  MiscUtility.getWebElement(genFilepath,"Generic_btn_noBtn");
        info_tableNumberOfRecords = MiscUtility.getWebElement(objFilepath,"FLT002_info_tableNumberOfRecords");
        
        maintainOperationalFlight_txt_route	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_txt_route");
        maintainOperationalFlight_LegCapacity_txt_departureTime	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_txt_departureTime");
        maintainOperationalFlight_LegCapacity_txt_arrivalTime	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_txt_arrivalTime");
        maintainOperationalFlight_LegCapacity_txt_aircraftType	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_txt_aircraftType");
        maintainOperationalFlight_LegCapacity_btn_viewCapacty	= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_btn_viewCapacty");
        maintainOperationalFlight_LegCapacity_btn_ok= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_btn_ok");
        btn_create= MiscUtility.getWebElement(objFilepath,"FLT002_btn_create");
        
        maintainOperationalFlight_txt_carrierCode= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_txt_carrierCode");
        maintainOperationalFlight_txt_flightNo= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_txt_flightNo");
        maintainOperationalFlight_txt_flightDate= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_txt_flightDate");
        maintainOperationalFlight_btn_list= MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_list");
        maintainOperationalFlight_btn_clear = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_clear");
        info_foot_layer = MiscUtility.getWebElement(genFilepath,"Generic_foot_layer");
        btn_copy= MiscUtility.getWebElement(objFilepath,"FLT002_btn_copy");
        btn_bookingList= MiscUtility.getWebElement(objFilepath,"FLT002_btn_bookingList");
        listBookings_table_bookingDetails= MiscUtility.getWebElement(objFilepath,"FLT002_ListBookings_table_bookingDetails");
        listBookings_btn_close= MiscUtility.getWebElement(objFilepath,"FLT002_ListBookings_btn_close");
        monitorFlight_txt_carrierCode= MiscUtility.getWebElement(objFilepath,"FLT002_MonitorFlight_txt_carrierCode");
        monitorFlight_txt_flightNo= MiscUtility.getWebElement(objFilepath,"FLT002_MonitorFlight_txt_flightNo");
        monitorFlight_btn_close= MiscUtility.getWebElement(objFilepath,"FLT002_MonitorFlight_btn_close");
        btn_activate = MiscUtility.getWebElement(objFilepath,"FLT002_btn_activate");
        btn_flightHistory = MiscUtility.getWebElement(objFilepath,"FLT002_btn_flightHistory");
        flightHistory_table_flightHistory = MiscUtility.getWebElement(objFilepath,"FLT002_FlightHistory_table_flightHistory");
        flightHistory_btn_close = MiscUtility.getWebElement(objFilepath,"FLT002_FlightHistory_btn_close");
        maintainOperationalFlight_LegCapacity_txt_weight = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_txt_weight");
        maintainOperationalFlight_LegCapacity_txt_volume = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_LegCapacity_txt_volume");
        maintainOperationalFlight_btn_segment = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_segment");
        maintainOperationalFlight_btn_sgementCapacity = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_btn_sgementCapacity");
        maintainOperationalFlight_SgementCapacity_txt_weight = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_SgementCapacity_txt_weight");
        maintainOperationalFlight_SgementCapacity_txt_volume = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_SgementCapacity_txt_volume");
        maintainOperationalFlight_SgementCapacity_btn_ok = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_SgementCapacity_btn_ok");
        maintainOperationalFlight_SgementCapacity_btn_close = MiscUtility.getWebElement(objFilepath,"FLT002_MaintainOperationalFlight_SgementCapacity_btn_close");
        info_tableRowCount = MiscUtility.getWebElement(objFilepath,"FLT002_info_tableRowCount");
        
        lbl_fromDate=MiscUtility.getWebElement(objFilepath,"FLT002_label_fromDate");
        lbl_toDate=MiscUtility.getWebElement(objFilepath,"FLT002_label_toDate");
    }
    
	public void list(String carrCode, String fltNo, String fltDt) {
		enterKeys(txt_flightCarrierCode, carrCode);
		enterKeys(txt_flightNo, fltNo);
		enterKeys(txt_fromDate, fltDt);
		click(btn_list);
	}



	/**
	 * cancel a operational flight with booking
	 * @param carrCode
	 * @param fltNo
	 * @param fltDt
	 * @return
	 * @author Arun A-7681
	 */
	public FLT002 fltCancelWithBooking(String carrCode, String fltNo, String fltDt){

		carrCode = PropertyHandler.getPropValue(dataFilePath,carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath,fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath,fltDt);
		list(carrCode,fltNo,fltDt);
		tblSelectRowByColValue(tbl_oprFlts, 1, 2, fltNo);

		click(btn_cancel);

		driver.switchTo().defaultContent();
		click(yesBtn);
		click(yesBtn);

		Assert.assertTrue(tblGetTextByColValue(tbl_oprFlts,16,2,fltNo).trim().equalsIgnoreCase("TBC"),"The flight is not in To Be Cancelled status.");

		return this;
	}

	public FLT002 revalidateFlt(String carrCode, String fltNo, String fltDt, String status){

		carrCode = PropertyHandler.getPropValue(dataFilePath,carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath,fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath,fltDt);
		list(carrCode,fltNo,fltDt);
		tblSelectRowByColValue(tbl_oprFlts, 1, 2, fltNo);

		click(btn_revalidate);
		minWait();
		Assert.assertEquals(tblGetTextByColValue(tbl_oprFlts,16,2,fltNo).trim().toUpperCase(),status.toUpperCase(),"The flight is not cancelled.");
		return this;
	}

	public FLT002 checkStatus(String carrCode, String fltNo, String fltDt, String status){
		carrCode = PropertyHandler.getPropValue(dataFilePath,carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath,fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath,fltDt);
		status = PropertyHandler.getPropValue(dataFilePath,status);
		list(carrCode,fltNo,fltDt);
		minWait();
		Assert.assertEquals(tblGetTextByColValue(tbl_oprFlts,17,2,fltNo).trim().toUpperCase(),status.toUpperCase(),"The flight is in in " + tblGetTextByColValue(tbl_oprFlts,17,2,fltNo) + " status.");
		return this;
	}

	public FLT002 clear(){

		minWait();
		waitForElement(btn_close);
		minWait();
		Actions action = new Actions(driver);
		action.keyDown(Keys.ALT).sendKeys("c").keyUp(Keys.ALT).perform();
		minWait();
		//click(btn_clear);
		/*driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);*/
		return this;
	}


    public HomePage close() {    	
        
        click(btn_close);
        driver.switchTo().defaultContent();
        if (verifyElementPresent(yesBtn)) {

            click(yesBtn);
        }
        return new HomePage(driver, dataFileName,test);
    }
    /**
     * Created by A-7605
     * @param carrierCode
     * @param flightNo
     * @param fromDate
     * @param toDate
     * @param originLevel
     * @param origin
     * @param destinationLevel
     * @param destination
     * @param flightType
     * @param scheduleType
     * @param aircraftType
     * @return
     * @throws ParseException
     */
    public FLT002 checkFlightActiveSchedule(String carrierCode, String flightNo, String fromDate, String toDate, String originLevel,
    		String origin, String destinationLevel, String destination, String flightType, String scheduleType, String aircraftType
    		) throws ParseException{
    	carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
    	flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
    	fromDate = PropertyHandler.getPropValue(dataFilePath, fromDate);
    	toDate = PropertyHandler.getPropValue(dataFilePath, toDate);
    	originLevel = PropertyHandler.getPropValue(dataFilePath, originLevel);
    	origin = PropertyHandler.getPropValue(dataFilePath, origin);
    	destinationLevel = PropertyHandler.getPropValue(dataFilePath, destinationLevel);
    	destination = PropertyHandler.getPropValue(dataFilePath, destination);
    	flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
    	scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
    	aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    	String from;
    	String to;

    	enterKeys(txt_flightCarrierCode, carrierCode);
    	enterKeys(txt_flightNo, flightNo);
    	enterKeys(txt_fromDate, fromDate);
    	enterKeys(txt_toDate, toDate);
    	selectByText(dropdown_orginLevel, originLevel);
    	enterKeys(txt_origin, origin);
    	selectByText(dropdown_destinationLevel, destinationLevel);
    	enterKeys(txt_destination, destination);
    	selectByText(dropdown_flightType, flightType);
    	selectByText(dropdown_scheduleType, scheduleType);
    	enterKeys(txt_aircraftType, aircraftType);
    	click(dropdown_status);
    	check(waitForElement(chkbox_active));
    	enterKeys(chkbox_active, Keys.ESCAPE);
    	click(btn_list);
    	minWait();
//    	from = waitForElement(txt_fromDate).getAttribute("value");
    	from = waitForElement(lbl_fromDate).getText();
    	
//    	to = waitForElement(txt_toDate).getAttribute("value");
    	to = waitForElement(lbl_toDate).getText();
    	System.out.println(from);
    	System.out.println(to);
    	Date startDate = sdf.parse(from);
    	Date endDate = sdf.parse(to);
    	int difference = (int) TimeUnit.DAYS.convert((endDate.getTime()- startDate.getTime()),TimeUnit.MILLISECONDS);
//    	System.out.println(difference);
//    	System.out.println(getTableRowCount(table_operationalFlights));
    	Assert.assertTrue(getTableRowCount() == difference +1," Expected table records is"+difference+" and actual is "+getTableRowCount(table_operationalFlights));
    	return this;
    }
    
    private int getTableRowCount(){
    	String rowCountInfo = waitForElementVisible(info_tableRowCount).getText();
    	int rowCount = Integer.parseInt(rowCountInfo.substring(rowCountInfo.lastIndexOf(" ")+1, rowCountInfo.length()));
    	return rowCount;
    }
    
    /**
     * Created by A-7605
     * @param carrierCode
     * @param flightNo
     * @param fromDate
     * @param toDate
     * @return
     */
    public FLT002 verifyFlightSchedulesCancelled(String carrierCode, String flightNo, String fromDate, String toDate){
    	int numberOfSchedules;
    	carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
    	flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
    	fromDate = PropertyHandler.getPropValue(dataFilePath, fromDate);
    	toDate = PropertyHandler.getPropValue(dataFilePath, toDate);
    	
    	enterKeys(txt_flightCarrierCode, carrierCode);
    	enterKeys(txt_flightNo, flightNo);
    	enterKeys(txt_fromDate, fromDate);
    	enterKeys(txt_toDate, toDate);
    	click(btn_list);
    	minWait();
    	numberOfSchedules = getTableRowCount(table_operationalFlights);
    	for(int i=1;i<=numberOfSchedules;i++){
    		Assert.assertTrue(getTableCellValue(table_operationalFlights, 16, i).equals("CAN"));
    	}
    	return this;
    }
    
 

	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return
	 */
	
	public FLT002 verifyFlightStatus(String carrierCode, String flightNo, String startDate, String endDate, String status){
		int numberOfSchedules;
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		startDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		endDate = PropertyHandler.getPropValue(dataFilePath, endDate);
		status = PropertyHandler.getPropValue(dataFilePath, status);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, startDate);
		enterKeys(txt_toDate, endDate);
		click(btn_list);
		minWait();
		numberOfSchedules = getTableRowCount(table_operationalFlights);
		for(int i=1;i<=numberOfSchedules;i++){
			String currentStatus = getTableCellValue(table_operationalFlights, 17, i);
			Assert.assertTrue(currentStatus.equals(status),"Actual status is '"+currentStatus+"' and expected is '"+status+"'");
		}
		return this;
	}

	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param startDate
	 * @param endDate
	 * @param updatedAircraftType
	 * @param updatedScheduleType
	 * @param updatedFlightType
	 * @return
	 */
	
	public FLT002 verifyUpdatedFlightDetails(String carrierCode, String flightNo, String startDate, String endDate, String updatedAircraftType, String updatedScheduleType, String updatedFlightType){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		startDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		endDate = PropertyHandler.getPropValue(dataFilePath, endDate);
		updatedAircraftType = PropertyHandler.getPropValue(dataFilePath, updatedAircraftType);
		updatedScheduleType = PropertyHandler.getPropValue(dataFilePath, updatedScheduleType);
		updatedFlightType = PropertyHandler.getPropValue(dataFilePath, updatedFlightType);
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, startDate);
		enterKeys(txt_toDate, endDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_details);
		minWait();
		Assert.assertTrue(getSelectedValue(maintainOperationalFlight_dropdown_scheduleType).equals(updatedScheduleType));
		Assert.assertTrue(getSelectedValue(maintainOperationalFlight_dropdown_flightType).equals(updatedFlightType));
		Assert.assertTrue(getTableCellValue(maintainOperationalFlight_table_leg, 8, 1).equals(updatedAircraftType));
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	           click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		maxWait();
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param startDate
	 * @param endDate
	 * @param newScheduleType
	 * @return
	 */
	public FLT002 checkValidationOnUpdatingFlight(String carrierCode, String flightNo, String startDate, String endDate, String newScheduleType){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		startDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		endDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		newScheduleType = PropertyHandler.getPropValue(dataFilePath, newScheduleType);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, startDate);
		enterKeys(txt_toDate, endDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_details);
		selectByText(maintainOperationalFlight_dropdown_scheduleType, newScheduleType);
		click(maintainOperationalFlight_btn_save);
		Assert.assertEquals(waitForElement(info_footer_error).getText(), "Flight is Finalized.Cannot modify");
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		return this;
	}
	
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param numberOfCancelledSchedules
	 * @return
	 */
	public FLT002 verifySchedulesAreCancelled(String carrierCode, String flightNo, String splitStartDate, String splitEndDate, String numberOfCancelledSchedules){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		splitStartDate = PropertyHandler.getPropValue(dataFilePath, splitStartDate);
		splitEndDate = PropertyHandler.getPropValue(dataFilePath, splitEndDate);
		numberOfCancelledSchedules = PropertyHandler.getPropValue(dataFilePath, numberOfCancelledSchedules);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, splitStartDate);
		enterKeys(txt_toDate, splitEndDate);
		waitForElement(txt_origin).clear();
		waitForElement(txt_destination).clear();
		click(dropdown_status);
		check(chkbox_canceled);
		click(btn_list);
		minWait();
		int tableRowCount = getTableRowCount(table_operationalFlights);
		Assert.assertEquals(tableRowCount, Integer.parseInt(numberOfCancelledSchedules));
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public FLT002 verifyAllSchedulesAreCancelled(String carrierCode, String flightNo, String startDate, String endDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		startDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		endDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, startDate);
		enterKeys(txt_toDate, endDate);
		waitForElement(txt_origin).clear();
		waitForElement(txt_destination).clear();
		click(btn_list);
		minWait();
		Assert.assertTrue(getTableRowCount(table_operationalFlights)>=1);
		Assert.assertTrue(waitForElement(table_operationalFlights).getText().contains("Canceled"));
		Assert.assertTrue(!verifyValuePresentInTblColumn(table_operationalFlights, 17, "Live"));
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param bookingDate
	 * @return
	 */
	public FLT002 checkValidationOnCancellingFlightHavingBooking(String carrierCode, String flightNo, String bookingDate){
		String dialogueContent;
		String errorMessage;
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		bookingDate = PropertyHandler.getPropValue(dataFilePath, bookingDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, bookingDate);
		enterKeys(txt_toDate, bookingDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_cancel);
		driver.switchTo().defaultContent();
		dialogueContent = waitForElement(dialogueText).getText();
		Assert.assertEquals(dialogueContent, "Do you want to cancel the flight?");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		errorMessage = waitForElement(info_footer_error).getText();
		Assert.assertEquals(errorMessage, "Flight is Finalized.Cannot cancel");
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param bookingDate
	 * @return
	 */
	public FLT002 checkFunctionalityOfNoButtonInFlightCancelDialogue(String carrierCode, String flightNo, String bookingDate){
		String dialogueContent;
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		bookingDate = PropertyHandler.getPropValue(dataFilePath, bookingDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, bookingDate);
		enterKeys(txt_toDate, bookingDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_cancel);
		driver.switchTo().defaultContent();
		dialogueContent = waitForElement(dialogueText).getText();
		Assert.assertEquals(dialogueContent, "Do you want to cancel the flight?");
		click(noBtn);
		waitForFrameAndSwitch(FRAME);
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 17, 1), "Active");
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @return
	 */
	private int getTotalNumberOfTableItems(){
		String tableRecordInfo = waitForElement(info_tableNumberOfRecords).getText();
		String recordCount = tableRecordInfo.split(" ")[6];
		return (Integer.parseInt(recordCount));
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param startDate
	 * @param endDate
	 * @param expectedNoOfRecords
	 * @return
	 */
	public FLT002 checkNumberOfSchedules(String carrierCode, String flightNo, String startDate, String endDate, String expectedNoOfRecords){
		int tableRecordActualCount;
		int tableRecordExpectedCount;
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		startDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		endDate = PropertyHandler.getPropValue(dataFilePath, endDate);
		expectedNoOfRecords = PropertyHandler.getPropValue(dataFilePath, expectedNoOfRecords);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, startDate);

		click(btn_list);
		minWait();
		try{
			tableRecordActualCount = getTotalNumberOfTableItems();
		}catch(Exception e){
			tableRecordActualCount = 0;
		}
		tableRecordExpectedCount = Integer.parseInt(expectedNoOfRecords);
		Assert.assertEquals(tableRecordActualCount, tableRecordExpectedCount);
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param startDate
	 * @param endDate
	 * @param newScheduleType
	 * @param newFlightType
	 * @param newRoute
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @return
	 */
	public FLT002 updateFlight(String carrierCode, String flightNo, String startDate, String endDate, String newScheduleType, String newFlightType, String newRoute
			,String departureTime, String arrivalTime, String aircraftType){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		startDate = PropertyHandler.getPropValue(dataFilePath, startDate);
		endDate = PropertyHandler.getPropValue(dataFilePath, endDate);
		newScheduleType = PropertyHandler.getPropValue(dataFilePath, newScheduleType);
		newFlightType = PropertyHandler.getPropValue(dataFilePath, newFlightType);
		newRoute = PropertyHandler.getPropValue(dataFilePath, newRoute);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, startDate);
		enterKeys(txt_toDate, endDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_details);
		enterKeys(waitForElement(maintainOperationalFlight_txt_route),newRoute);
		enterKeys(maintainOperationalFlight_txt_route, Keys.TAB);
		driver.switchTo().defaultContent();
		click(waitForElement(yesBtn));
		waitForFrameAndSwitch(FRAME);
		selectByText(maintainOperationalFlight_dropdown_flightType, newFlightType);
		selectByText(maintainOperationalFlight_dropdown_scheduleType, newScheduleType);
		click(maintainOperationalFlight_btn_legDetails);
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(maintainOperationalFlight_LegCapacity_txt_departureTime, departureTime);
		enterKeys(maintainOperationalFlight_LegCapacity_txt_arrivalTime, arrivalTime);
		enterKeys(maintainOperationalFlight_LegCapacity_txt_aircraftType, aircraftType);
		click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
		minWait();
		click(maintainOperationalFlight_LegCapacity_btn_ok);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_save);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 7, 1), newRoute);
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 3, 1), newScheduleType);
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 4, 1), newFlightType);
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param fullFlightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @param loadFactor
	 * @return
	 */
	public FLT002 createAdhocFlight(String carrierCode, String flightNo, String fullFlightNo, String flightDate, String route, String scheduleType,
			String flightType, String departureTime, String arrivalTime, String aircraftType, String loadFactor){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		loadFactor = PropertyHandler.getPropValue(dataFilePath, loadFactor);
		
		click(btn_create);
		enterKeys(waitForElement(maintainOperationalFlight_txt_carrierCode), carrierCode);
		String randomNumber;
        while (true) {
        	randomNumber = BizUtility.createFlightNum();
            enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            enterKeys(maintainOperationalFlight_txt_flightDate, flightDate);
            click(maintainOperationalFlight_btn_list);           
            driver.switchTo().defaultContent();
            minWait();
            
	  		if (verifyElementPresent(yesBtn)) {
                click(yesBtn);
                waitForFrameAndSwitch(FRAME);
                break;
            }
            waitForFrameAndSwitch(FRAME);
            click(waitForElement(maintainOperationalFlight_btn_clear));
        }
        PropertyHandler.setPropValue(dataFilePath, flightNo, randomNumber);
        PropertyHandler.setPropValue(dataFilePath, fullFlightNo, carrierCode+"-"+randomNumber);
        enterKeys(maintainOperationalFlight_txt_route,route);
        selectByText(maintainOperationalFlight_dropdown_scheduleType, scheduleType);
        selectByText(maintainOperationalFlight_dropdown_flightType, flightType);
        click(maintainOperationalFlight_btn_legDetails);
        driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_departureTime), departureTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_arrivalTime), arrivalTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_aircraftType), aircraftType);
        click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
        minWait();
        click(maintainOperationalFlight_LegCapacity_btn_ok);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_save);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(info_foot_layer).getText().trim(), "Flight Saved Successfully.");
		waitForFrameAndSwitch(FRAME);
		minWait();
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param newFlightNo
	 * @param flightStartDate
	 * @param flightEndDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param aircraftType
	 * @param newScheduleType
	 * @return
	 */
	public FLT002 copyFlight(String carrierCode, String flightNo, String newFlightNo, String flightStartDate,String flightEndDate, String route, String scheduleType,
			String flightType, String aircraftType, String newScheduleType){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		flightEndDate = PropertyHandler.getPropValue(dataFilePath, flightEndDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		newScheduleType = PropertyHandler.getPropValue(dataFilePath, newScheduleType);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightStartDate);
		enterKeys(txt_toDate, flightEndDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_copy);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_route).getAttribute("value"), route);
		Assert.assertEquals(getSelectedValue(maintainOperationalFlight_dropdown_scheduleType), scheduleType);
		Assert.assertEquals(getSelectedValue(maintainOperationalFlight_dropdown_flightType), flightType);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_flightNo).getAttribute("value"), flightNo);
		String randomNumber = BizUtility.createFlightNum();
        
        while(true){
        	enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            click(maintainOperationalFlight_btn_list);
            driver.switchTo().defaultContent();
        	if (verifyElementPresent(yesBtn)) {       		
    	        click(yesBtn);
    	        waitForFrameAndSwitch(FRAME);
    	        break;
    	    }
        }
        minWait();
        PropertyHandler.setPropValue(dataFilePath, newFlightNo, randomNumber);
		selectByText(maintainOperationalFlight_dropdown_scheduleType, newScheduleType);
		click(maintainOperationalFlight_btn_save);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		click(btn_clear);
		minWait();
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, randomNumber);
		enterKeys(txt_fromDate, flightStartDate);
		enterKeys(txt_toDate, flightEndDate);
		click(btn_list);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 11, 1), aircraftType);
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 3, 1), newScheduleType);
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	public FLT002 cancelOperationalFlight(String carrierCode, String flightNo, String flightDate, String expectedStatus){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		expectedStatus = PropertyHandler.getPropValue(dataFilePath, expectedStatus);
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		waitForElement(txt_origin).clear();
		waitForElement(txt_destination).clear();
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_cancel);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Do you want to cancel the flight?");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 17, 1), expectedStatus);
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param bookingDate
	 * @param ubrNo
	 * @return
	 */
	public FLT002 checkFunctionalityOfBookingListButton(String carrierCode, String flightNo, String bookingDate, String ubrNo){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		bookingDate = PropertyHandler.getPropValue(dataFilePath, bookingDate);
		ubrNo = PropertyHandler.getPropValue(dataFilePath, ubrNo);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, bookingDate);
		enterKeys(txt_toDate, bookingDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_bookingList);
		waitForElement(listBookings_btn_close);
		Assert.assertEquals(getTableCellValue(listBookings_table_bookingDetails, 4, 1), ubrNo);
		click(listBookings_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param bookingDate
	 * @return
	 */
	public FLT002 checkFunctionalityOfMonitorFlightButton(String carrierCode, String flightNo, String bookingDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		bookingDate = PropertyHandler.getPropValue(dataFilePath, bookingDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, bookingDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_monitorFlts);
		minWait();
		waitForElement(monitorFlight_btn_close);
		Assert.assertEquals(driver.getTitle(), "Monitor Flights");
		Assert.assertEquals(waitForElementVisible(monitorFlight_txt_carrierCode).getAttribute("value"), carrierCode);
		Assert.assertEquals(waitForElementVisible(monitorFlight_txt_flightNo).getAttribute("value"), flightNo);
		click(monitorFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	public FLT002 activateOperationalFlight(String carrierCode, String flightNo, String flightDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_activate);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 17, 1), "Active");
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	public FLT002 cancelFlightWithBooking(String carrierCode, String flightNo, String flightDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		/*enterKeys(txt_fromDate, ".");
		enterKeys(txt_toDate, ".");*/
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_cancel);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Do you want to cancel the flight?");
		click(yesBtn);
		minWait();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Bookings exist against the flight. Do you want to cancel the flight?");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 16, 1), "TBC");
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 17, 1), "To be cancelled");
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	public FLT002 checkFunctionalityOfRevalidateButton(String carrierCode, String flightNo, String flightDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_revalidate);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 17, 1), "Active");
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	public FLT002 checkFunctionalityOfFlightHistoryButton(String carrierCode, String flightNo, String flightDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_flightHistory);
		minWait();
		waitForElement(flightHistory_btn_close);
		Assert.assertTrue(getTableColumnValues(flightHistory_table_flightHistory, 1).contains("FLIGHT CREATED"));
		Assert.assertEquals(tblGetTextByColValue(flightHistory_table_flightHistory, 4, 1, "FLIGHT CANCELLED"),"Flight Status : To be cancelled");
		click(flightHistory_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param aircraftType
	 * @return
	 */
	public FLT002 checkFunctionalityOfDetailsButton(String carrierCode, String flightNo, String flightDate, String route, String scheduleType, String flightType, String aircraftType){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		String flightStartDate;
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
//		enterKeys(txt_fromDate, ".");
		enterKeys(txt_toDate, flightDate);
//		enterKeys(txt_toDate, ".");
		click(btn_list);
		minWait();
		flightStartDate = waitForElement(txt_toDate).getAttribute("value");
		check(chkbox_selectTableItem);
		click(btn_details);
		minWait();
		waitForElement(maintainOperationalFlight_btn_close);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_route).getAttribute("value"), route);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_carrierCode).getAttribute("value"), carrierCode);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_flightNo).getAttribute("value"), flightNo);
		Assert.assertEquals(getSelectedValue(maintainOperationalFlight_dropdown_flightType), flightType);
		Assert.assertEquals(getSelectedValue(maintainOperationalFlight_dropdown_scheduleType), scheduleType);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_flightDate).getAttribute("value"), flightStartDate);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		waitForElement(btn_activate);
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 11, 1), aircraftType);
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @param weight
	 * @param volume
	 * @return
	 */
	public FLT002 updateLegCapacity(String carrierCode, String flightNo, String flightDate, String weight, String volume){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_details);
		minWait();
		waitForElement(maintainOperationalFlight_btn_close);
		click(maintainOperationalFlight_btn_legDetails);
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_weight), weight);
		enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_volume), volume);
		click(maintainOperationalFlight_LegCapacity_btn_ok);
		minWait();
		if (getNumberOfWindows() == 2) {
			Assert.assertEquals(waitForElement(dialogueText).getText(), "Specified leg capacity greater than aircraft capacity. Do you want to continue?");
			click(yesBtn);
		}
		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_save);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}
		Assert.assertEquals(waitForElement(info_foot_layer).getText().trim(), "Flight Saved Successfully.");
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @param newRoute
	 * @param newAircraftType
	 * @return
	 */
	// need to confirm the error messge since the error messge is not showing in current build
	public FLT002 checkValidationOnChangingLegForManifestedFlight(String carrierCode, String flightNo, String flightDate, String newRoute, String newAircraftType){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		newAircraftType = PropertyHandler.getPropValue(dataFilePath, newAircraftType);
		newRoute = PropertyHandler.getPropValue(dataFilePath, newRoute);
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_details);
		minWait();
		waitForElement(maintainOperationalFlight_btn_close);
		enterKeys(maintainOperationalFlight_txt_route, newRoute);
		enterKeys(maintainOperationalFlight_txt_route, Keys.TAB);
		driver.switchTo().defaultContent();
		click(waitForElement(yesBtn));
		waitForFrameAndSwitch(FRAME);
		minWait();
		click(maintainOperationalFlight_btn_legDetails);
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(maintainOperationalFlight_LegCapacity_txt_departureTime, "00");
		enterKeys(maintainOperationalFlight_LegCapacity_txt_arrivalTime, "12");
		enterKeys(maintainOperationalFlight_LegCapacity_txt_aircraftType, newAircraftType);
		click(maintainOperationalFlight_LegCapacity_btn_ok);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		minWait();
		click(maintainOperationalFlight_btn_save);
		Assert.assertEquals(waitForElement(info_footer_error).getText(), "Cannot modify details");
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	public FLT002 checkErrorOnUpdatingCancelledFlight(String carrierCode, String flightNo, String flightDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_details);
		minWait();
		waitForElement(maintainOperationalFlight_btn_close);
		Assert.assertEquals(waitForElement(info_footer_error).getText(), "Flight is cancelled.");
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param fullFlightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @param loadFactor
	 * @return
	 */
	public FLT002 createOALFlight(String carrierCode, String flightNo, String fullFlightNo, String flightDate, String route, String scheduleType,
			String flightType, String departureTime, String arrivalTime, String aircraftType, String loadFactor){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		loadFactor = PropertyHandler.getPropValue(dataFilePath, loadFactor);
		
		click(btn_create);
		enterKeys(waitForElement(maintainOperationalFlight_txt_carrierCode), carrierCode);
		String randomNumber;
        while (true) {
        	randomNumber = BizUtility.createFlightNum();
            enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            enterKeys(maintainOperationalFlight_txt_flightDate, flightDate);
            click(maintainOperationalFlight_btn_list);           
            driver.switchTo().defaultContent();
            minWait();
            
	  		if (verifyElementPresent(yesBtn)) {
                click(yesBtn);
                waitForFrameAndSwitch(FRAME);
                break;
            }
            waitForFrameAndSwitch(FRAME);
            click(waitForElement(maintainOperationalFlight_btn_clear));
        }
        PropertyHandler.setPropValue(dataFilePath, flightNo, randomNumber);
        PropertyHandler.setPropValue(dataFilePath, fullFlightNo, carrierCode+"-"+randomNumber);
        enterKeys(maintainOperationalFlight_txt_route,route);
        selectByText(maintainOperationalFlight_dropdown_scheduleType, scheduleType);
        selectByText(maintainOperationalFlight_dropdown_flightType, flightType);
        click(maintainOperationalFlight_btn_legDetails);
        driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_departureTime), departureTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_arrivalTime), arrivalTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_aircraftType), aircraftType);
        click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
        minWait();
        click(maintainOperationalFlight_LegCapacity_btn_ok);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_save);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Aircraft Owner is different from the carrier. Do you want to continue?");
		click(yesBtn);	
		Assert.assertEquals(waitForElement(info_foot_layer).getText().trim(), "Flight Saved Successfully.");
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param fullFlightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @param loadFactor
	 * @param weight
	 * @param volume
	 * @return
	 */
	public FLT002 createAdhocFlightWithLegCapacityGreaterThanAircraftCapacity(String carrierCode, String flightNo, String fullFlightNo, String flightDate, String route, String scheduleType,
			String flightType, String departureTime, String arrivalTime, String aircraftType, String loadFactor, String weight, String volume){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		loadFactor = PropertyHandler.getPropValue(dataFilePath, loadFactor);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		
		click(btn_create);
		enterKeys(waitForElement(maintainOperationalFlight_txt_carrierCode), carrierCode);
		String randomNumber;
        while (true) {
        	randomNumber = BizUtility.createFlightNum();
            enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            enterKeys(maintainOperationalFlight_txt_flightDate, flightDate);
            click(maintainOperationalFlight_btn_list);           
            driver.switchTo().defaultContent();
            minWait();
            
	  		if (verifyElementPresent(yesBtn)) {
                click(yesBtn);
                waitForFrameAndSwitch(FRAME);
                break;
            }
            waitForFrameAndSwitch(FRAME);
            click(waitForElement(maintainOperationalFlight_btn_clear));
        }
        PropertyHandler.setPropValue(dataFilePath, flightNo, randomNumber);
        PropertyHandler.setPropValue(dataFilePath, fullFlightNo, carrierCode+"-"+randomNumber);
        enterKeys(maintainOperationalFlight_txt_route,route);
        selectByText(maintainOperationalFlight_dropdown_scheduleType, scheduleType);
        selectByText(maintainOperationalFlight_dropdown_flightType, flightType);
        click(maintainOperationalFlight_btn_legDetails);
        driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_departureTime), departureTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_arrivalTime), arrivalTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_aircraftType), aircraftType);
        click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
        minWait();
        enterKeys(maintainOperationalFlight_LegCapacity_txt_weight, weight);
        enterKeys(maintainOperationalFlight_LegCapacity_txt_volume, volume);
        click(maintainOperationalFlight_LegCapacity_btn_ok);
        Assert.assertEquals(waitForElement(dialogueText).getText(), "Specified leg capacity greater than aircraft capacity. Do you want to continue?");
        click(yesBtn);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_save);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(info_foot_layer).getText().trim(), "Flight Saved Successfully.");
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param fullFlightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @param loadFactor
	 * @param weight
	 * @param volume
	 * @return
	 */
	public FLT002 checkValidationIfSegmentCapacityGreaterThanLegCapacity(String carrierCode, String flightNo, String fullFlightNo, String flightDate, String route, String scheduleType,
			String flightType, String departureTime, String arrivalTime, String aircraftType, String loadFactor, String weight, String volume){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		loadFactor = PropertyHandler.getPropValue(dataFilePath, loadFactor);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		
		click(btn_create);
		enterKeys(waitForElement(maintainOperationalFlight_txt_carrierCode), carrierCode);
		String randomNumber;
        while (true) {
        	randomNumber = BizUtility.createFlightNum();
            enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            enterKeys(maintainOperationalFlight_txt_flightDate, flightDate);
            click(maintainOperationalFlight_btn_list);           
            driver.switchTo().defaultContent();
            minWait();
            
	  		if (verifyElementPresent(yesBtn)) {
                click(yesBtn);
                waitForFrameAndSwitch(FRAME);
                break;
            }
            waitForFrameAndSwitch(FRAME);
            click(waitForElement(maintainOperationalFlight_btn_clear));
        }
        PropertyHandler.setPropValue(dataFilePath, flightNo, randomNumber);
        PropertyHandler.setPropValue(dataFilePath, fullFlightNo, carrierCode+"-"+randomNumber);
        enterKeys(maintainOperationalFlight_txt_route,route);
        selectByText(maintainOperationalFlight_dropdown_scheduleType, scheduleType);
        selectByText(maintainOperationalFlight_dropdown_flightType, flightType);
        click(maintainOperationalFlight_btn_legDetails);
        driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_departureTime), departureTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_arrivalTime), arrivalTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_aircraftType), aircraftType);
        click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
        minWait();
        click(maintainOperationalFlight_LegCapacity_btn_ok);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_segment);
		click(waitForElement(maintainOperationalFlight_btn_sgementCapacity));
		driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
		enterKeys(waitForElement(maintainOperationalFlight_SgementCapacity_txt_weight), weight);
		enterKeys(waitForElement(maintainOperationalFlight_SgementCapacity_txt_volume), volume);
		click(maintainOperationalFlight_SgementCapacity_btn_ok);
		Assert.assertEquals(waitForElement(info_footer_error).getText(), "Specified segment capacity greater than leg capacity.");
		click(maintainOperationalFlight_SgementCapacity_btn_close);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		minWait();
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param newCarrierCode
	 * @param newFlightNo
	 * @param flightStartDate
	 * @param flightEndDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param aircraftType
	 * @param newScheduleType
	 * @return
	 */
	public FLT002 copyFlightAndSaveAsOAL(String carrierCode, String flightNo, String newCarrierCode, String newFlightNo, String flightStartDate,String flightEndDate, String route, String scheduleType,
			String flightType, String aircraftType, String newScheduleType){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		flightEndDate = PropertyHandler.getPropValue(dataFilePath, flightEndDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		newScheduleType = PropertyHandler.getPropValue(dataFilePath, newScheduleType);
		newCarrierCode = PropertyHandler.getPropValue(dataFilePath, newCarrierCode);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightStartDate);
		enterKeys(txt_toDate, flightEndDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_copy);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_route).getAttribute("value"), route);
		Assert.assertEquals(getSelectedValue(maintainOperationalFlight_dropdown_scheduleType), scheduleType);
		Assert.assertEquals(getSelectedValue(maintainOperationalFlight_dropdown_flightType), flightType);
		Assert.assertEquals(waitForElement(maintainOperationalFlight_txt_flightNo).getAttribute("value"), flightNo);
		String randomNumber;
		while(true){
			randomNumber = BizUtility.createFlightNum();
			enterKeys(maintainOperationalFlight_txt_carrierCode, newCarrierCode);
			enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
			click(maintainOperationalFlight_btn_list);
			driver.switchTo().defaultContent();
			if(verifyElementPresent(yesBtn)){
				click(yesBtn);
				break;
			}
			waitForFrameAndSwitch(FRAME);
			click(btn_clear);
			minWait();
		}
		waitForFrameAndSwitch(FRAME);
        minWait();
        PropertyHandler.setPropValue(dataFilePath, newFlightNo, randomNumber);
		selectByText(maintainOperationalFlight_dropdown_scheduleType, newScheduleType);
		click(maintainOperationalFlight_btn_save);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Aircraft Owner is different from the carrier. Do you want to continue?");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		click(btn_clear);
		minWait();
		enterKeys(txt_flightCarrierCode, newCarrierCode);
		enterKeys(txt_flightNo, randomNumber);
		enterKeys(txt_fromDate, flightStartDate);
		enterKeys(txt_toDate, flightEndDate);
		click(btn_list);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 11, 1), aircraftType);
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 3, 1), newScheduleType);
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param newFlightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @param loadFactor
	 * @param weight
	 * @param volume
	 * @return
	 */
	public FLT002 copyAdhocFlightWithLegCapacityGreaterThanAircraftCapacity(String carrierCode, String flightNo, String newFlightNo, String flightDate, String route, String scheduleType,
			String flightType, String departureTime, String arrivalTime, String aircraftType, String loadFactor, String weight, String volume){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		loadFactor = PropertyHandler.getPropValue(dataFilePath, loadFactor);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_copy);
		enterKeys(waitForElement(maintainOperationalFlight_txt_carrierCode), carrierCode);
		String randomNumber;
        while (true) {
        	randomNumber = BizUtility.createFlightNum();
            enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            enterKeys(maintainOperationalFlight_txt_flightDate, flightDate);
            click(maintainOperationalFlight_btn_list);           
            driver.switchTo().defaultContent();
            minWait();
            
	  		if (verifyElementPresent(yesBtn)) {
                click(yesBtn);
                waitForFrameAndSwitch(FRAME);
                break;
            }
            waitForFrameAndSwitch(FRAME);
            click(waitForElement(maintainOperationalFlight_btn_clear));
        }

        PropertyHandler.setPropValue(dataFilePath, newFlightNo, randomNumber);
        enterKeys(maintainOperationalFlight_txt_route,route);
        selectByText(maintainOperationalFlight_dropdown_scheduleType, scheduleType);
        selectByText(maintainOperationalFlight_dropdown_flightType, flightType);
        click(maintainOperationalFlight_btn_legDetails);
        driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_departureTime), departureTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_arrivalTime), arrivalTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_aircraftType), aircraftType);
        click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
        minWait();
        enterKeys(maintainOperationalFlight_LegCapacity_txt_weight, weight);
        enterKeys(maintainOperationalFlight_LegCapacity_txt_volume, volume);
        click(maintainOperationalFlight_LegCapacity_btn_ok);
        Assert.assertEquals(waitForElement(dialogueText).getText(), "Specified leg capacity greater than aircraft capacity. Do you want to continue?");
        click(yesBtn);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_save);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(info_foot_layer).getText().trim(), "Flight Saved Successfully.");
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param fullFlightNo
	 * @param flightDate
	 * @param route
	 * @param scheduleType
	 * @param flightType
	 * @param departureTime
	 * @param arrivalTime
	 * @param aircraftType
	 * @param loadFactor
	 * @param weight
	 * @param volume
	 * @return
	 */
	public FLT002 checkValidationIfSegmentCapacityGreaterThanLegCapacityOnCopyingFlight(String carrierCode, String flightNo, String fullFlightNo, String flightDate, String route, String scheduleType,
			String flightType, String departureTime, String arrivalTime, String aircraftType, String loadFactor, String weight, String volume){
		
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		route = PropertyHandler.getPropValue(dataFilePath, route);
		scheduleType = PropertyHandler.getPropValue(dataFilePath, scheduleType);
		flightType = PropertyHandler.getPropValue(dataFilePath, flightType);
		departureTime = PropertyHandler.getPropValue(dataFilePath, departureTime);
		arrivalTime = PropertyHandler.getPropValue(dataFilePath, arrivalTime);
		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);
		loadFactor = PropertyHandler.getPropValue(dataFilePath, loadFactor);		
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_copy);
		enterKeys(waitForElement(maintainOperationalFlight_txt_carrierCode), carrierCode);
		String randomNumber;
        while (true) {
        	randomNumber = BizUtility.createFlightNum();
            enterKeys(maintainOperationalFlight_txt_flightNo, randomNumber);
            enterKeys(maintainOperationalFlight_txt_flightDate, flightDate);
            click(maintainOperationalFlight_btn_list);           
            driver.switchTo().defaultContent();
            minWait();
            
	  		if (verifyElementPresent(yesBtn)) {
                click(yesBtn);
                waitForFrameAndSwitch(FRAME);
                break;
            }
            waitForFrameAndSwitch(FRAME);
            click(waitForElement(maintainOperationalFlight_btn_clear));
        }
        PropertyHandler.setPropValue(dataFilePath, flightNo, randomNumber);
        PropertyHandler.setPropValue(dataFilePath, fullFlightNo, carrierCode+"-"+randomNumber);
        enterKeys(maintainOperationalFlight_txt_route,route);
        selectByText(maintainOperationalFlight_dropdown_scheduleType, scheduleType);
        selectByText(maintainOperationalFlight_dropdown_flightType, flightType);
        click(maintainOperationalFlight_btn_legDetails);
        driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_departureTime), departureTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_arrivalTime), arrivalTime);
        enterKeys(waitForElement(maintainOperationalFlight_LegCapacity_txt_aircraftType), aircraftType);
        click(maintainOperationalFlight_LegCapacity_btn_viewCapacty);
        minWait();
        click(maintainOperationalFlight_LegCapacity_btn_ok);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_segment);
		click(waitForElement(maintainOperationalFlight_btn_sgementCapacity));
		driver.switchTo().defaultContent();
        waitForNewWindow(2);
        switchToSecondWindow();
		enterKeys(waitForElement(maintainOperationalFlight_SgementCapacity_txt_weight), weight);
		enterKeys(waitForElement(maintainOperationalFlight_SgementCapacity_txt_volume), volume);
		click(maintainOperationalFlight_SgementCapacity_btn_ok);
		Assert.assertEquals(waitForElement(info_footer_error).getText(), "Specified segment capacity greater than leg capacity.");
		click(maintainOperationalFlight_SgementCapacity_btn_close);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(maintainOperationalFlight_btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
	
	        click(yesBtn);
	    }
		waitForFrameAndSwitch(FRAME);
		minWait();
		return this;
	}
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @return
	 */
	
	public FLT002 checkErrorOnCancellingClosedFlight(String carrierCode, String flightNo, String flightDate){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_cancel);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Do you want to cancel the flight?");
		click(yesBtn);
		minWait();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Bookings exist against the flight. Do you want to cancel the flight?");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertEquals(waitForElement(info_footer_error).getText(),"Flight is closed");
		return this;
	}
	
	/**
	 * Created by A-7605
	 * @param carrierCode
	 * @param flightNo
	 * @param flightDate
	 * @param expectedStatus
	 * @return
	 */
	public FLT002 cancelOperationalFlightWithAllotment(String carrierCode, String flightNo, String flightDate, String expectedStatus){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
		expectedStatus = PropertyHandler.getPropValue(dataFilePath, expectedStatus);
		
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightDate);
		enterKeys(txt_toDate, flightDate);
		waitForElement(txt_origin).clear();
		waitForElement(txt_destination).clear();
		click(btn_list);
		minWait();
		check(chkbox_selectTableItem);
		click(btn_cancel);
		driver.switchTo().defaultContent();
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Do you want to cancel the flight?");
		click(yesBtn);
		minWait();		
		Assert.assertEquals(waitForElement(dialogueText).getText(), "Allotments exist against the flight. Do you want to cancel the flight?");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertEquals(getTableCellValue(table_operationalFlights, 17, 1), expectedStatus);
		return this;
	}
	
	public FLT002 verifyLoadFactorOfSecondLeg(String carrierCode, String flightNo, String flightFromDate,String flightToDate, String expectedLoadFactor, Days... days){
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
		flightFromDate = PropertyHandler.getPropValue(dataFilePath, flightFromDate);
		flightToDate = PropertyHandler.getPropValue(dataFilePath, flightToDate);
		expectedLoadFactor = PropertyHandler.getPropValue(dataFilePath, expectedLoadFactor);
		int numberOfDays = 0;
		enterKeys(txt_flightCarrierCode, carrierCode);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_fromDate, flightFromDate);
		enterKeys(txt_toDate, flightToDate);
		waitForElement(txt_origin).clear();
		waitForElement(txt_destination).clear();
		for(Days day:days){
			numberOfDays++;
			if(day == Days.MONDAY){
				check(chkbox_dayOfOperation_Mon);
			}
			else if(day == Days.TUESDAY){
				check(chkbox_dayOfOperation_Tue);
			}
			else if(day == Days.WEDNESDAY){
				check(chkbox_dayOfOperation_Wed);
			}
			else if(day == Days.THURSDAY){
				check(chkbox_dayOfOperation_Thu);
			}
			else if(day == Days.FRIDAY){
				check(chkbox_dayOfOperation_Fri);
			}
			else if(day == Days.SATURDAY){
				check(chkbox_dayOfOperation_Sat);
			}
			else if(day == Days.SUNDAY){
				check(chkbox_dayOfOperation_Sun);
			}
		}
		click(btn_list);
		minWait();
		for(int i=0;i<numberOfDays;i++){
			List<WebElement> chkboxes = getWebElements(chkbox_selectTableItem);
			scrollToView(chkboxes.get(i));
			check(chkboxes.get(i));
			click(btn_details);
			waitForElement(maintainOperationalFlight_btn_save);
			check(getWebElements(maintainOperationalFlight_chkbox_selectTableItem).get(1));
			click(maintainOperationalFlight_btn_legDetails);
			driver.switchTo().defaultContent();
			waitForNewWindow(2);
			switchToSecondWindow();
			String actualLoadFactor = waitForElement(maintainOperationalFlight_LegCapacity_txt_loadFactor).getAttribute("value");
			click(maintainOperationalFlight_LegCapacity_btn_close);
			switchToFirstWindow();
			minWait();
			waitForFrameAndSwitch(FRAME);
			Assert.assertEquals(actualLoadFactor, expectedLoadFactor+".0");
			click(maintainOperationalFlight_btn_close);
			driver.switchTo().defaultContent();
			if(verifyElementPresent(yesBtn)){
				click(yesBtn);
			}
			waitForFrameAndSwitch(FRAME);
			minWait();
		}
		return this;
	}
	 public String getAttributeFromText(String path,String pryKey) throws IOException{
			
    	 String deatils="";
			System.out.println();
    	 String sCurrentLine = "";
 		BufferedReader br = new BufferedReader(new FileReader(new File(path)));

 		while ((sCurrentLine = br.readLine()) != null) {
 			
 			
 			if(sCurrentLine.trim().contains(pryKey))
 			//sCurrentLine;
 			{	 deatils=sCurrentLine.replace(pryKey,"").replace("<", "").replace(">", "").replace(" ", "").replace("/", "");
 			
 			System.out.println(deatils);
 			
 			break;
 			}
 	}
		return deatils;
 			
 			}
	/**
	 * Created by Shalini	
	 * @return
	 * @throws IOException 
	 */
	public FLT002 VerifyAircraftType(String FlightNo,String path,String pryKey) throws IOException{
		boolean isFound = true;
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
	enterKeys(txt_flightNo, FlightNo);
	click(btn_list);
	minWait();
	String aircarfyType = getAttributeFromText(path, pryKey);
	String actualValue = driver.findElement(By.xpath("//table[@id='_fltListTable']//tr//td[11]")).getText();
	Assert.assertTrue(isFound, "Message Processed Successfully" + aircarfyType + actualValue);
	logger.info("Aircraft type is Matching");	
	test.log(LogStatus.PASS, "Aircraft type is Matching");
	return this;
	}
	/**
	 * Created by Shalini	
	 * @return
	 * @throws IOException 
	 */
	public FLT002 VerifyArrivalTimeStatus(String path) throws IOException{
		boolean isFound = true;
		/*FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
	enterKeys(txt_flightNo, FlightNo);
	click(btn_list);
	minWait();
	//String aircarfyType = getAttributeFromText(path, pryKey);*/
	String actualArrivalStatus = driver.findElement(By.xpath("//table[@id='_fltListTable']//tr//td[10]")).getText();
	 String[] s1 = actualArrivalStatus.split("\\(");
	 for(int i=0;i<s1.length;i++)
	System.out.println(s1);
	 String ActaulStatus = s1[1];
	 	Assert.assertTrue(isFound, "S" + "S" + ActaulStatus);
	logger.info("Aircraft type is Matching");	
	test.log(LogStatus.PASS, "Arrival Statuus is matching");
	return this;
	}
	/**
	 * Created by Shalini	
	 * @return
	 * @throws IOException 
	 */
	public FLT002 VerifyFlightStatus(String FlightNo,String msg) throws IOException{
		boolean isFound = true;
		String carrCode=PropertyHandler.getPropValue(dataFilePath, "CarrierCode");
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		enterKeys(txt_flightCarrierCode, carrCode);
	enterKeys(txt_flightNo, FlightNo);
	click(btn_list);
	minWait();	
	String actualValue = driver.findElement(By.xpath("//table[@id='_fltListTable']//tr[14]//td[17]")).getText();
	Assert.assertTrue(isFound, "Message Processed Successfully" + msg + actualValue);
	logger.info("Status is Matching");	
	test.log(LogStatus.PASS, "Status is Matching");
	return this;
	}
	
	public FLT002 fetchFlightDetails() throws InterruptedException {
		String origin, dest, fltDt, aircraftType;
		origin = dest = fltDt = aircraftType = null;

		try {
	//	origin = PropertyHandler.getPropValue(dataFilePath, "origin");
	//	dest = PropertyHandler.getPropValue(dataFilePath, "dest");
	//	fltDt = PropertyHandler.getPropValue(dataFilePath, "fltDt");
	//	aircraftType = PropertyHandler.getPropValue(dataFilePath, "aircraftType");

		} catch (Exception e) {
		e.printStackTrace();
		}

		String flight = getFlights(origin, dest, null, fltDt, fltDt, aircraftType);

		try {
		PropertyHandler.setPropValue(dataFilePath, "fltNo", flight.substring(3));
		} catch (Exception e) {
		e.printStackTrace();
		}

		System.out.print("Flight number : " + flight);
		return this;
		}

	public FLT002 fetchFlightDetails(String origin, String dest, String fltDt, String aircraftType, String fltNo, HashMap<String, String> data) throws InterruptedException {

		try {
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		dest = PropertyHandler.getPropValue(dataFilePath, dest);
//		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
//		aircraftType = PropertyHandler.getPropValue(dataFilePath, aircraftType);

		} catch (Exception e) {
		e.printStackTrace();
		}

		String flight = getFlights(origin, dest, null, fltDt, fltDt, aircraftType);

		try {
//		PropertyHandler.setPropValue(dataFilePath, fltNo, flight.substring(3));
			data.put(fltNo, flight.substring(3));
		} catch (Exception e) {
		e.printStackTrace();
		}

		System.out.print("Flight number : " + flight);
		return this;
		}



		public String getFlights(String origin, String dest, String via, String fromDate, String toDate,String aircraftType) {
		waitForElement(By.id("CMP_Flight_Operation_ListFlight_Origin")).click();

		// Active Flight check
		click(By.xpath("//button[@id='CMP_Flight_Operation_ListFlight_Status_ms']"));
		minWait();
		click(By.xpath("//input[@name='multiselect_CMP_Flight_Operation_ListFlight_Status'][@id='ui-multiselect-1-CMP_Flight_Operation_ListFlight_Status-option-0']"));
		// Aircraft type selection
		if (aircraftType != null) {
		WebElement drp_AircraftClassification=driver.findElement(By.xpath("//select[@id='CMP_Flight_Operation_ListFlight_AircraftClassification']"));
		drp_AircraftClassification.click();
		Select drp_Aircraft = new Select(drp_AircraftClassification);
		drp_Aircraft.selectByVisibleText(aircraftType);
		}
		// Fill From Date
		driver.findElement(By.xpath("//input[@id='fromDate']")).clear();
		driver.findElement(By.xpath("//input[@id='fromDate']")).sendKeys(fromDate + Keys.TAB);

		// Fill To Date
		if (toDate != null) {
		driver.findElement(By.xpath("//input[@id='toDate']")).clear();
		driver.findElement(By.xpath("//input[@id='toDate']")).sendKeys(toDate + Keys.TAB);
		}
		// OD and via Pair
		driver.findElement(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_Origin']")).sendKeys(origin + Keys.TAB);

		if (via != null) {
		driver.findElement(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_ViaPoint']")).sendKeys(via);
		}
		driver.findElement(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_Destination']")).sendKeys(dest + Keys.TAB);
		   click(By.xpath("//button[@id='CMP_Flight_Operation_ListFlight_BtnList']"));

		// Ascending order of flights w.r.t flight departure
		click(By.xpath("//*[@id=\"_fltListTable-header\"]/thead/tr[2]/td[8]"));
		// picking the Flight no.
		String flight = "N/A";
		try {
		flight = waitForElement(By.xpath("//*[@id=\"_fltListTable\"]//td[2]")).getText();
		} catch (Exception e)
		{
		System.out.println("No flights Found");
		}

		minWait();
		//added by Zimmy
		//Expand for next search
		click(By.xpath("//*[@id=\"main_filter_Info_Id\"]//div[1]/div/a"));
		return flight;
		}
		
		public FLT002 fetchFlightDetails(String origin, String dest, String fltDt,
				String aircraftType, String fltNo, String fltDtArr, HashMap<String, String> data) throws InterruptedException {
				String flight = getFlights(origin, dest, null, fltDt, fltDt, aircraftType, fltDtArr);
				try {
					data.put(fltNo, flight.substring(3,7));
					data.put(fltDtArr, flight.substring(8));
				} catch (Exception e) {
				e.printStackTrace();
					Assert.fail();
				}
				test.log(LogStatus.INFO, "Flight number: "+ flight);
				logger.info("Flight number : " + flight);
				return this;
				}
		
		public String getFlights(String origin, String dest, String via,
				String fromDate, String toDate, String aircraftType, String fltDtArr) {
				if(!verifyElementVisible(By.id("CMP_Flight_Operation_ListFlight_Origin"))){
				click(By.xpath("//a[@class='hfilter-edit']"));
				}
				waitForElement(By.id("CMP_Flight_Operation_ListFlight_Origin")).click();
				// Active Flight check
				click(By.xpath("//button[@id='CMP_Flight_Operation_ListFlight_Status_ms']"));
				minWait();
				click(By.xpath("//input[@name='multiselect_CMP_Flight_Operation_ListFlight_Status'][@id='ui-multiselect-1-CMP_Flight_Operation_ListFlight_Status-option-0']"));
				// Aircraft type selection
				if (aircraftType != null) {
				WebElement drp_AircraftClassification = waitForElement(By.xpath("//select[@id='CMP_Flight_Operation_ListFlight_AircraftClassification']"));
				drp_AircraftClassification.click();
				Select drp_Aircraft = new Select(drp_AircraftClassification);
				drp_Aircraft.selectByVisibleText(aircraftType);
				}
				// Fill From Date
				enterKeys(By.xpath("//input[@id='fromDate']"), Keys.TAB);
//				driver.findElement(By.xpath("//input[@id='fromDate']")).clear();
//				driver.findElement(By.xpath("//input[@id='fromDate']")).sendKeys(fromDate + Keys.TAB);



				// Fill To Date
				if (toDate != null) {
					enterKeys(By.xpath("//input[@id='toDate']"), Keys.TAB);
//				driver.findElement(By.xpath("//input[@id='toDate']")).clear();
//				driver.findElement(By.xpath("//input[@id='toDate']")).sendKeys(
//				toDate + Keys.TAB);
				}
				// OD and via Pair
				// driver.findElement(
				// By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_Origin']"))
				// .sendKeys(origin + Keys.TAB);
				enterKeys(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_Origin']"), origin+Keys.TAB);
				if (via != null) {
					enterKeys(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_ViaPoint']"), via);
//				driver.findElement(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_ViaPoint']"))
//				.sendKeys(via);
				}
				// driver.findElement(
				// By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_Destination']"))
				// .sendKeys(dest + Keys.TAB);
				enterKeys(By.xpath("//input[@id='CMP_Flight_Operation_ListFlight_Destination']"), dest+Keys.TAB);
				click(By.xpath("//button[@id='CMP_Flight_Operation_ListFlight_BtnList']"));
				// Ascending order of flights w.r.t flight departure
				click(By.xpath("//*[@id=\"_fltListTable-header\"]/thead/tr[2]/td[8]"));
				// picking the Flight no.
				String flight = "N/A";
				try {
				flight = waitForElement(By.xpath("//*[@id=\"_fltListTable\"]//td[2]")).getText() + " " +waitForElement(By.xpath("//*[@id=\"_fltListTable\"]//td[10]")).getText().substring(0, 11);
				test.log(LogStatus.INFO, "Flight: "+flight+ " is being used for this booking");
				} catch (Exception e) {
				logger.info("No flights Found");
				test.log(LogStatus.FAIL, "No flights found for requested OD pair: "+origin+"-"+dest);
				}
				return flight;
				}
}
