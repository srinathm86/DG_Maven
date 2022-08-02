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
import cucumber.api.java.en.When;

/**
 * Module : Regression
 * 
 * @author Sharath on 03-01-2019 Description capture and Acceptance
 **/
public class DGFlow02_2_UpdateTCBookingToDG extends BaseSetup {
private BaseSetup base;
	
	public DGFlow02_2_UpdateTCBookingToDG(BaseSetup base) {
		this.base=base;
	}
	HomePage home = null;
	
	String [] shippingName= new String [2];
	String [] unidNo= new String [2];
	String[] piValue= new String[2];
	
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();
	
	
	public static final String messagePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"selenium.sample.message.path");

	@Given("^Login to Application and switch role for validation of DG3$")
	public void Flow2UpdateTCToDGBooking() throws Throwable {
		
		data=testData.setMapData(filename, "TestData", 2);
		
		for(int i =0 ;i<2;i++){
			shippingName[i] = data.get("shippingName"+(i+1));
			unidNo[i] = data.get("unidNo"+(i+1));
			piValue[i]=data.get("piValue"+(i+1));
		}
		String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DG_NOTOC.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting test for Update a TC booking to DG booking------------------------------");
		logger.info("------------------------------------Starting test for Update a TC booking to DG booking------------------------------");
		home = gen.login();
	}
	
	@When("^perform booking for TC commodity for DG3$")
	public void performTCBooking() throws Exception {
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL") 
		.goToFLT002()
		.fetchFlightDetails(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("aircraftType"),"fltNo1","arrivalDt1",data)
        .close();
			
		testData.writeDatatoExcelCell(2, data.get("fltNo1"), cell_flightno, filename);
		
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("product"), data.get("pcs"), data.get("wt"), data.get("vol"),
				data.get("fltDt"), data.get("ULDType"), data.get("ULDwt"), data.get("commCode3"))
		.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo1"), 1)
		.captureTCDetailsPcs(data.get("wt"), data.get("origin"), data.get("fltDt"), data.get("pcs"))
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo2", data.get("commCode3"), data)
    	.close();
	
		testData.writeDatatoExcelCell(2, data.get("awbNo2"), cell_awbNo2, filename);
	}
	
	@Then("^verify booking can be updated to DG and acceptance works fine for DG3$")
	public void updateToDgBooking() throws Exception {
	
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.updateBookingWithNewCommCodePropertyfile(data.get("prefix"), data.get("awbNo2"), data.get("pcs"), data.get("wt"), data.get("vol"),data.get("fltDt"), data.get("commCode4"))
		.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"), data.get("DGpcs"),  data.get("DGwt"),
				data.get("commCode3"), unidNo, shippingName, 2)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo2", "", data)
		.close();
		testData.writeDatatoExcelCell(2, data.get("awbNo2"), 5, filename);
		
		home.SwitchRoleGroupParameter(data.get("origin"), "OCTRAGT").goToOPR026()
		.ProcessExecuteafterBooking(data.get("prefix"), data.get("awbNo2"), data.get("shipper"), data.get("consignee")).close();

		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR350()
		.CaptureDGDetailsPropertyFile(data.get("awbNo2"), data.get("emergencyName"), data.get("emergencyContact"))
		.CaptureUnidDetailsForPropertyFile(data.get("awbNo2"), unidNo, data.get("DGpcs"), data.get("DGwt"), piValue, "", false)
		.close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR335()
		.processGoodsAcceptanceWithCheckSheetwithDGPopUp(data.get("prefix"), data.get("awbNo2"), data.get("Location"), data.get("pcs"), data.get("wtLbs"), data.get("screening_method"), data.get("unidNo4"))
		.close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
		.CheckXsDGMessageStatus(data.get("awbNo2"), data.get("status"))
		.close();
	
		test.log(LogStatus.INFO, "-----------------------------------End test for Update a TC shipment to DG shipment----------------------");
		logger.info("-----------------------------------End test for Update a TC shipment to DG shipment----------------------");
		
	
	}

}