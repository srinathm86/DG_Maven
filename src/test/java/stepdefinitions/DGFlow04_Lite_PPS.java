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
public class DGFlow04_Lite_PPS extends BaseSetup {
private BaseSetup base;
	
	public DGFlow04_Lite_PPS(BaseSetup base) {
		this.base=base;
	}
	HomePage home = null;
	
	
	
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();
	
	public static final String messagePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"selenium.sample.message.path");

	@Given("^Login to Application and switch role for validation of DG7_17$")
	public void loginToUrl() throws Throwable {
		
        data=testData.setMapData(filename, "TestData", 4);
        String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DG_NOTOC.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting test for PPS DG shipment in Lite screen------------------------------");
		logger.info("------------------------------------Starting test for PPS DG shipment in Lite screen------------------------------");
		home = gen.login();
	}

	@When("^perform boking for DG in lite screen for DG7_17$")
	public void performLiteDGBooking() throws Exception {
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL") 
		.goToFLT002()
	    .fetchFlightDetails(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("aircraftType"),"fltNo1","arrivalDt1",data)
       .close();
		
		testData.writeDatatoExcelCell(4, data.get("fltNo1"), cell_flightno, filename);


		        home.SwitchRoleGroupParameter(data.get("origin"), "CCOCDOM").goToLTE001()
				.doPPSBookingOnlyDG(data.get("carrierCode"), "", data.get("fltNo1"), data.get("fltDt"), data.get("product"), data.get("agentCode"), data.get("shipper"),
						data.get("consignee"), data.get("origin"), data.get("dest"), data.get("pcs"), data.get("wt"), data.get("vol"), data.get("commCode"), data.get("prefix"), data.get("customerType"))
				.handleDGpopup(data.get("emergencyName"), data.get("emergencyContact"), data.get("pcs"), data.get("wt"), data.get("piValue"), data.get("unidNo"), data.get("ShippingName"), 1, true)
				.CaptureChecksheetLite("", "", data.get("unidNo"), false)
				.captureBookingDetails("awbNo1", data)
				.handleShipmentBlock()
				.doCaptureAndAcceptanceDG(data.get("pcs"),data.get("screening_method"), data.get("ScreenResult"), false, data.get("prefix"), data.get("awbNo1"), true, false)
				.close();
		         testData.writeDatatoExcelCell(4, data.get("awbNo1"), cell_awbNo, filename);
	}

	@Then("^acceptance should work fine DG7_17$")
	public void performLiteAcceptance() throws Exception {

		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCDOM").goToOPR350()
				.CaptureDGDetailsPropertyFile(data.get("awbNo1"), data.get("emergencyName"), data.get("emergencyContact"))
				.CaptureUnidDetailsForPropertyFile(data.get("awbNo1"), data.get("unidNo"), data.get("pcs"), data.get("wt"), data.get("piValue"), "", false).save().close();

		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCDOM").goToLTE001().doCaptureAndAcceptanceDG(data.get("pcs"),
				data.get("screening_method"), data.get("ScreenResult"), true, data.get("prefix"), data.get("awbNo1"), false, true).close();

		home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL")
		.goToMSG005().CheckXsDGMessageStatus(data.get("awbNo1"), data.get("status"))
				.close();

		test.log(LogStatus.INFO,
				"-----------------------------------End test for PPS DG shipment in Lite screen----------------------");
		logger.info(
				"-----------------------------------End test for PPS DG shipment in Lite screen----------------------");
	}

}