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
public class DGFlow03_multilegDGshipment extends BaseSetup {
private BaseSetup base;
	
	public DGFlow03_multilegDGshipment(BaseSetup base) {
		this.base=base;
	}
	HomePage home = null;
	
	String [] shippingName= new String [1];
	String [] unidNo= new String [1];
	
	
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();
	
	public static final String messagePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"selenium.sample.message.path");

	@Given("^Login to Application and switch role for validation of DGReg_1_4$")
	public void MultiLegDgShipment() throws Throwable {
		 
		data=testData.setMapData(filename, "TestData", 3);
		
		for(int i =0 ;i<1;i++){
			shippingName[i] = data.get("shippingName"+(i+1));
			unidNo[i] = data.get("unidNo"+(i+1));
			
		}
		String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DG_NOTOC.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting test for multileg DG shipment------------------------------");
		logger.info("------------------------------------Starting test for multileg DG shipment------------------------------");
		home = gen.login();
	}
	
	@When("^perform multileg booking for a DG shipment$")
	public void performmultiLegDGBooking() throws Exception {
		
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("product"), data.get("pcs"), data.get("wt"), data.get("vol"),
				data.get("fltDt"), data.get("ULDType"), data.get("ULDwt"), data.get("commCode"))
		.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("intermediate"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo1"), 1)
		.enterFlightDetailsforPropertyFile(data.get("intermediate"), data.get("dest"), data.get("fltDt2"), data.get("carrierCode"), data.get("fltNo2"), 2)
    	.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"), data.get("pcs"), data.get("wt"),
				data.get("commCode"), unidNo, shippingName, 1)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "commCode", data)
		.close();
		testData.writeDatatoExcelCell(3, data.get("awbNo1"), cell_awbNo, filename);

	}
	
	@Then("^acceptance works fine for multileg DG shipment$")
	public void performDGAcceptance() throws Throwable {
		
		home.SwitchRoleGroupParameter(data.get("origin"), "OCTRAGT").goToOPR026()
		.ProcessExecuteafterBooking(data.get("prefix"), data.get("awbNo1"), data.get("shipper"), data.get("consignee")).close();

		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR350()
		.CaptureDGDetailsPropertyFile(data.get("awbNo1"), data.get("emergencyName"), data.get("emergencyContact"))
		.CaptureUnidDetailsForPropertyFile(data.get("awbNo1"), unidNo[0], data.get("pcs"), data.get("wt"), data.get("piValue"), "", false)
		.close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR335()
		.processGoodsAcceptanceWithCheckSheetwithDGPopUp(data.get("prefix"), data.get("awbNo1"), data.get("Location"), data.get("pcs"), data.get("wtLbs"), data.get("screening_method"), data.get("unidNo1")).close();
		
		home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
		.CheckXsDGMessageStatus(data.get("awbNo1"), data.get("status"))
		.close();
		
		
		test.log(LogStatus.INFO, "-----------------------------------End test for multileg DG shipment----------------------");
		logger.info("-----------------------------------End test for multileg DG shipment----------------------");
	}
	
	
}