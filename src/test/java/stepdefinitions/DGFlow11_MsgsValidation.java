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
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Module : Regression
 * 
 *  **/
public class DGFlow11_MsgsValidation extends BaseSetup {
	private BaseSetup base;
	
	public DGFlow11_MsgsValidation(BaseSetup base) {
		this.base=base;
	}
	
	HomePage home = null;
//	public WebDriver driver;
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();
	
	
	public static final String messagePath = PropertyHandler.getPropValue(
			"resources\\EnvSetup.properties", "selenium.sample.message.path");

	@Given("^Login to Application and switch role for REG_DG_016_021_022$")
	public void navigateAndLogin() throws Throwable {
		
		data=testData.setMapData(filename, "TestData", 11);
		
		String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DGFlow11.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting test of NTM message sent validation after NOTOC finalization------------------------------");
		logger.info("------------------------------------Starting test of NTM message sent validation after NOTOC finalization------------------------------");
		home = gen.login("00247319");
	}


	@When("^DG Booking in CAP018 screen for REG_DG_016_021_022$")
	public void performBooking() throws Exception {
		//CCOCFULL
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL") 
		.goToFLT002()
	    .fetchFlightDetails(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("aircraftType"),"fltNo","arrivalDt1",data)
	    .close();
		
		
		
		testData.writeDatatoExcelCell(11, data.get("fltNo"), 9, filename);
	
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
		.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("Product"), data.get("pcs"), data.get("wt"), data.get("vol"),
				data.get("fltDt"), data.get("ULDType"), data.get("ULDwt"), data.get("commCode"))
		.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo"),1)
		.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"), data.get("pcs"), 
				 data.get("wt"), data.get("commCode"), data.get("unidNo1"), data.get("shippingName1"), 1)
		.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "",data)
		.close();
		testData.writeDatatoExcelCell(11, data.get("awbNo1"), 4, filename);
	}
	
	@And("^Do DG capture in OPR026 screen for REG_DG_016_021_022$")
	public void performCapture() throws IOException {
		//OCTRAGT
		home.SwitchRoleGroupParameter(data.get("origin"), "OCTRAGT").goToOPR026()
		.ProcessExecuteafterBooking(data.get("prefix"), data.get("awbNo1"), data.get("shipper"), data.get("consignee"))
		.close();
	}
	
	@And("^Do Capture DG details in OPR350 screen for REG_DG_016_021_022$")
	public void captureDGDetails() throws IOException {
		//CCOCFULL
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR350()
		.CaptureDGDetailsPropertyFile(data.get("awbNo1"), data.get("emergencyName"), data.get("emergencyContact"))
		.CaptureUnidDetailsForPropertyFile(data.get("awbNo1"), data.get("unidNo1"), data.get("pcs"), data.get("wt"), data.get("piValue"), "", false)
		.close();
	}
	
	@And("^Do DG Goods Acceptance in OPR335 screen for REG_DG_016_021_022$")
	public void performAcceptance() throws IOException {
		//OMGR
		home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR335()
		.processGoodsAcceptanceWithCheckSheetwithDGPopUp(data.get("prefix"), data.get("awbNo1"),data.get ("Location"), data.get("pcs"), data.get("wt"), data.get("screening_method"), data.get("unidNo"))
		.close();
	}
	
	
	@And("^Check for XSDG message trigger in MSG005 for REG_DG_016_021_022$")
	public void buildupExport() throws IOException {
		//ASYSANL
		home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
		.CheckXsDGMessageStatus(data.get("awbNo1"), data.get("msgStatus"))
		.close();
				
	}
	
	@And("^do export manifest in OPR344 fro REG_DG_016_021_022$")
	public void export() throws IOException {
		//OPLNR
				/*home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR344()
				.performExportManifestWithAWBasCart(data.get("awbNo1"), data.get("carrierCode"), data.get("fltNo"), data.get("fltDt"), data.get("origin"), data.get("dest"))
				.close();*/			
	}
	
	
	@And("^Generate NOTOC and do finalization for REG_DG_016_021_022$")
	public void notoc() throws IOException {
		//CCOCFULL
				/*home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR017()
				.generateNOTOC(data.get("carrierCode"), data.get("fltNo"), data.get("fltDt"), data.get("prefix"), data.get("awbNo1"))
				.close();	*/		
	}
	
	
	@And("^Check for NTM message trigger for REG_DG_016_021_022$")
	public void msgcgheck() throws IOException {
		//ASYSANL
				/*home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
				.CheckNTMMessageStatus(data.get("fltNo"), data.get("msgStatus"))
				.close();*/
				
	}
	
	
	@And("^Reopen Notoc for REG_DG_016_021_022$")
	public void reopenNTM() throws IOException {
		//ASYSANL
		//CCOCFULL
	/*	home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR017()
		.ReopenNOTOC(data.get("carrierCode"), data.get("fltNo"), data.get("fltDt"))
		.close();	*/
				
	}
	
	
	@Then("^Recheck for NTM message trigger for REG_DG_016_021_022$")
	public void recheckmsg() throws IOException {
		//ASYSANL
			/*	home.SwitchRoleGroupParameter(data.get("origin"), "ASYSANL").goToMSG005()
				.CheckNTMMessageStatus(data.get("fltNo"), data.get("msgStatus"))
				.close();*/
				
		test.log(LogStatus.INFO, "------------------------------------Ending test of NTM message sent validation after NOTOC finalization------------------------------");
		logger.info("------------------------------------Ending test of NTM message sent validation after NOTOC finalization------------------------------");
	}
}