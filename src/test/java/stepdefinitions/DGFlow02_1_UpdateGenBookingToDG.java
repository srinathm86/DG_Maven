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
public class DGFlow02_1_UpdateGenBookingToDG extends BaseSetup {
private BaseSetup base;
	
	public DGFlow02_1_UpdateGenBookingToDG(BaseSetup base) {
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

	@Given("^Login to Application and switch role for validation of DG2$")
	public void Flow2UpdateGeneralToDGBooking() throws Throwable {
		
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
		test.log(LogStatus.INFO, "------------------------------------Starting test for Update a booking to DG booking------------------------------");
		logger.info("------------------------------------Starting test for Update a booking to DG booking------------------------------");
		home = gen.login();
	}
	
	@When("^perform booking for General commodity for DG2$")
	public void performGenera1Booking() throws Throwable {
	home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL") 
		.goToFLT002()
	    .fetchFlightDetails(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("aircraftType"),"fltNo1","arrivalDt1",data)
       .close();
		
		testData.writeDatatoExcelCell(2, data.get("fltNo1"), cell_flightno, filename);

		
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("product"), data.get("pcs"), data.get("wt"), data.get("vol"),
				data.get("fltDt"), data.get("ULDType"), data.get("ULDwt"), data.get("commCode1"))
		.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo1"), 1)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "",data)
		.close();
		
		testData.writeDatatoExcelCell(2, data.get("awbNo1"), cell_awbNo, filename);
	}

	@Then("^verify booking can be updated to DG and acceptance works fine for DG2$")
	public void updateBookingtoDG() throws Throwable {
		
		
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.updateBookingWithNewCommCodePropertyfile(data.get("prefix"), data.get("awbNo1"), data.get("pcs"), data.get("wt"), data.get("vol"),data.get("fltDt"), data.get("commCode2"))
		.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"), data.get("DGpcs"),  data.get("DGwt"),
				data.get("commCode2"), unidNo, shippingName, 2)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "", data)
		.close();
		testData.writeDatatoExcelCell(2, data.get("awbNo1"), 4, filename);
		
		home.SwitchRoleGroupParameter(data.get("origin"), "OCTRAGT").goToOPR026()
		.ProcessExecuteafterBooking(data.get("prefix"), data.get("awbNo1"), data.get("shipper"), data.get("consignee"))
		.close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR350()
		.CaptureDGDetailsPropertyFile(data.get("awbNo1"), data.get("emergencyName"), data.get("emergencyContact"))
		.CaptureUnidDetailsForPropertyFile(data.get("awbNo1"), unidNo, data.get("DGpcs"), data.get("DGwt"), piValue, "", false)
		.close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR335()
		.processGoodsAcceptanceWithCheckSheetwithDGPopUp(data.get("prefix"), data.get("awbNo1"), data.get("Location"), data.get("pcs"), data.get("wtLbs"), data.get("screening_method"),data.get("unidNo2")).close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
		.CheckXsDGMessageStatus(data.get("awbNo1"), data.get("status"))
		.close();
		
		test.log(LogStatus.INFO, "-----------------------------------End test for Update a booking to DG booking----------------------");
		logger.info("-----------------------------------End test for Update a booking to DG booking----------------------");
//		
	}

}