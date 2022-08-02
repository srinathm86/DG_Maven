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
public class DGFlow01_OverPack_AllPack extends BaseSetup {
	private BaseSetup base;
	
	public DGFlow01_OverPack_AllPack(BaseSetup base) {
		this.base=base;
	}
	HomePage home = null;
	
	ExcelUtil testData = new ExcelUtil();
	String filename = "DG_NOTOC_Data";
	public HashMap<String, String> data = new HashMap<String, String>();
	
	String [] shippingName= new String [4];
	String [] unidNo= new String [4];
	String[] piValue= new String[4];
	
	
	public static final String messagePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"selenium.sample.message.path");

	@Given("^Login to Application and switch role for validation of DG Over and All Pack$")
	public void navigateAndLogin() throws Throwable {
		
		data=testData.setMapData(filename, "TestData", 1);
		
		for(int i =0 ;i< 4;i++){
			shippingName[i] = data.get("shippingName"+(i+1));
			unidNo[i] = data.get("unidNo"+(i+1));
			piValue[i]=data.get("piValue"+(i+1));
		}
		String classname = this.getClass().getName();
		String[] ClassName = classname.split("\\.");
		test = extent.startTest(ClassName[1]);
		base.test = test;
		Generic gen = new Generic(base.driver, browserName,"DG_NOTOC.properties",base.test);
		test.log(LogStatus.INFO, "------------------------------------Starting the test for DG Over and All Pack------------------------------");
		logger.info("------------------------------------Starting the test for DG Over and All Pack------------------------------");
		home = gen.login();
	}

	@When("^booking is performed for multiple DG goods$")
	public void performBookingWithMultipleUnidsAllPack() throws Throwable {
	    home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL") 
		.goToFLT002()
		.fetchFlightDetails(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("aircraftType"),"fltNo1","arrivalDt1",data)
        .close();
		
		testData.writeDatatoExcelCell(1, data.get("fltNo1"), cell_flightno, filename);
		
		
		home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToCAP018()
				.enterInitialBookingDetailsforPropertyfile(data.get("origin"), data.get("dest"), data.get("agentCode"), data.get("product"), data.get("pcs"), data.get("wt"), data.get("vol"),
						data.get("fltDt"), data.get("ULDType"), data.get("ULDwt"), data.get("commCode"))
				.enterFlightDetailsforPropertyFile(data.get("origin"), data.get("dest"), data.get("fltDt"), data.get("carrierCode"), data.get("fltNo1"), 1)
				.handleDGpopUpForPropertyFile(data.get("emergencyName"), data.get("emergencyContact"),data.get("DGpcs"), data.get("DGwt"),
						data.get("commCode"), unidNo, shippingName, 4)
				.saveInCAP018AndVerifyUBRnStatusDirect("awbNo1", "",data )
				.close();
		testData.writeDatatoExcelCell(1, data.get("awbNo1"), cell_awbNo, filename);

		     home.SwitchRoleGroupParameter(data.get("origin"), "OCTRAGT").goToOPR026()
			.ProcessExecuteafterBooking(data.get("prefix"), data.get("awbNo1"), data.get("shipper"), data.get("consignee"))
			.close();
	
	}

	@Then("^verify OverPack and AllPack is successfully done$")
	public void performAllPackAndOverPack() throws Exception {
		
    	home.SwitchRoleGroupParameter(data.get("origin"), "CCOCFULL").goToOPR350()
		.CaptureDGDetailsOverPack(data.get("awbNo1"), unidNo, data.get("DGpcs"), data.get("DGwt"), piValue)
		.close();

		home.SwitchRoleGroupParameter(data.get("origin"), "OMGR").goToOPR335()
		.processGoodsAcceptanceWithCheckSheetwithDGPopUp(data.get("prefix"), data.get("awbNo1"), data.get("Location"), data.get("pcs"), data.get("wt"), data.get("screening_method"), data.get("unidNo4"))
		.close();
		
		test.log(LogStatus.INFO, "------------------------------------The test for DG Over and All Pack has been completed------------------------------");
		logger.info("------------------------------------The test for DG Over and All Pack has been completed------------------------------");
	
	}
	
}