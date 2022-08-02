package stepdefinitions;

import java.io.IOException;
import java.util.HashMap;

import com.ibsplc.common.BaseSetup;
import com.ibsplc.generic.Generic;
import com.ibsplc.pageObjects.HomePage;
import com.ibsplc.utils.ExcelUtil;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

/**
 * Module : Regression
 * 
 *  **/
public class DGFlow10_ExplosiveMaterial extends BaseSetup {
	private BaseSetup base;
	
	public DGFlow10_ExplosiveMaterial(BaseSetup base) {
		this.base=base;
	}
	
	HomePage home = null;
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();

	public static final String messagePath = PropertyHandler.getPropValue(
			"resources\\EnvSetup.properties", "selenium.sample.message.path");

	@Given("^Login to Application and switch role for REG_DG_009$")
	public void navigateAndLogin() throws Throwable {
		
		data=testData.setMapData(filename, "TestData", 10);
		String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DG_NOTOC.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting test of Explosive material shipment------------------------------");
		logger.info("------------------------------------Starting test of Explosive material shipment------------------------------");
		home = gen.login("00247319");
	}


	@And("^DG Booking in CAP018 screen for REG_DG_009$")
	public void performGoodsAcceptance() throws Exception {
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL") 
		.goToFLT002()
	    .fetchFlightDetails(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("aircraftType"),"fltNo","arrivalDt1",data)
        .close();
		
		testData.writeDatatoExcelCell(10, data.get("fltNo"), cell_flightno, filename);
		
		//CCOCFULL
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("product"), data.get("pcs"), data.get("wt"), data.get("vol"),
				data.get("fltDt"), data.get("ULDType"), data.get("ULDwt"), data.get("commCode"))
		.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo"),1)
		.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"), data.get("pcs"), 
				 data.get("wt"), data.get("commCode"), data.get("unidNo1"), data.get("shippingName1"), 1)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "",data)
		.close();
		
		testData.writeDatatoExcelCell(10, data.get("awbNo1"), cell_awbNo, filename);
	}
	
	@And("^Do DG capture in OPR026 screen for REG_DG_009$")
	public void performCapture() throws IOException {
		//OCTRAGT
		home.SwitchRoleGroupParameter(data.get("origin"), "OCTRAGT")
		.goToOPR026()
		.ProcessExecuteafterBooking(data.get("prefix"), data.get("awbNo1"), data.get("shipper"), data.get("consignee"))
		.close();
	}
	
	@And("^Do Capture DG details in OPR350 screen for REG_DG_009$")
	public void captureDGDetails() throws IOException {
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR350()
		.CaptureDGDetailsPropertyFile(data.get("awbNo1"), data.get("emergencyName"), data.get("emergencyContact"))
		.CaptureUnidDetailsForPropertyFile(data.get("awbNo1"), data.get("unidNo1"), data.get("pcs"), data.get("wt"), data.get("piValue"), "", false)
		.close();
	}
	
	@And("^Do DG Goods Acceptance in OPR335 screen for REG_DG_009$")
	public void performAcceptance() throws IOException {
		//OMGR
		home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR335()
		.processGoodsAcceptanceWithCheckSheetwithDGPopUp(data.get("prefix"), data.get("awbNo1"),data.get ("Location"), data.get("pcs"), data.get("wt"), data.get("screening_method"), data.get("unidNo"))
		.close();
	}
	
	
	@And("^Check for XSDG message trigger in MSG005 for REG_DG_009$")
	public void buildupExport() throws IOException {
		//ASYSANL
		home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
		.CheckXsDGMessageStatus(data.get("awbNo1"), data.get("msgStatus"))
		.close();
		
		test.log(LogStatus.INFO, "------------------------------------Ending test of Explosive material shipment------------------------------");
		logger.info("------------------------------------Ending test of Explosive material shipment------------------------------");
	}
	
}