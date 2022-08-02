package stepdefinitions;

import java.util.HashMap;

import com.ibsplc.common.BaseSetup;
import com.ibsplc.generic.Generic;
import com.ibsplc.pageObjects.HomePage;
import com.ibsplc.utils.ExcelUtil;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * Module : Regression
 * 
 * @author Sharath on 03-01-2019 Description capture and Acceptance
 **/
public class DGFlow05_MultiDGMultiLegShipment extends BaseSetup {
private BaseSetup base;
	
	public DGFlow05_MultiDGMultiLegShipment(BaseSetup base) {
		this.base=base;
	}
	HomePage home = null;
	
	String [] shippingName= new String [3];
	String [] unidNo= new String [3];
	
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();
	
	public static final String messagePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"selenium.sample.message.path");
	
	@Given("^Login to Application and switch role for validation of DG18$")
	public void MultiDgShipment() throws Throwable {
		
        data=testData.setMapData(filename, "TestData", 5);
		
		for(int i =0 ;i<3;i++){
			shippingName[i] = data.get("shippingName"+(i+1));
			unidNo[i] = data.get("unidNo"+(i+1));
		}
		String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DG_NOTOC.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting test for Multi DG shipment------------------------------");
		logger.info("------------------------------------Starting test for Multi DG shipment------------------------------");
		home = gen.login();
	}
	
	@Then("^verify booking for multiple DGs works fine for DG18$")
	public void performBooking18() throws Exception {
	
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("product"), data.get("pcs"), data.get("wt"), data.get("vol"),
				data.get("fltDt"), data.get(""), data.get("ULDwt"), data.get("commCode"))
		.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("intermediate"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo1"), 1)
		.enterFlightDetailsforPropertyFile(data.get("intermediate"), data.get("dest"), data.get("fltDt2"), data.get("carrierCode"), data.get("fltNo2"), 2)
		.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"), data.get("DGpcs"), 
				 data.get("DGwt"), data.get("commCode"), unidNo, shippingName, 3)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "", data)
		.close();
		testData.writeDatatoExcelCell(5, data.get("awbNo1"), cell_awbNo, filename);
		
		test.log(LogStatus.INFO, "------------------------------------Ending test for Multi DG shipment------------------------------");
		logger.info("------------------------------------Ending test for Multi DG shipment------------------------------");

	}
}