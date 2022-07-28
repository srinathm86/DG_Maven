package com.ibsplc.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.ibsplc.common.BasePage;
import com.ibsplc.common.enums.StockType;
import com.ibsplc.generic.InitialSetup;
import com.ibsplc.utils.BizUtility;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Screen OPR026 - CAPTURE AWB Created on 21/12/2017
 * 
 * @author a-7868
 */
public class OPR026 extends BasePage {

	public static String FRAME = "iCargoContentFrameOPR026";
	public static String screenFrame = "iCargoContentFrameOPR026";
	private static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"OPR_FLT.properties");
	private static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"Generic.properties");
	WebDriver driver;
	ExtentTest test;
	String dataFileName;
	private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");
	private By txt_prefix, txt_awb, btn_list, chcbx_Autorate, div_sc, txt_shipper, txt_consignee, btn_asIs,
			txt_Currencyv, btn_chargeTab, info_saveawb, txt_chargePcs, btn_btnDuplicate, txt_chargeWt,
			dropdown_rateClass, txt_iataRate, txt_netCharge, dropdown_sci, btn_close, yesBtn, txt_ActualAmount,
			tbl_RecoPopup, noBtn, okBtn, msg_executed, info_executed, txt_remarks, txt_AWBOnDate, txt_AWBdropOffdate,
			btn_pymnt_ok, btn_finalize, txt_pymntAdvice, btn_pymnt_close, btn_save, chkbx_HAWBFinalized, chkbx_consol,
			msg_popup, btn_clear, info_footerNotification, txt_origin, txt_dest, txt_routingDest1, txt_routingCarrCode1,
			txt_scc, txt_agentCode, btn_Delete, txt_commCode, txt_commPcs, btn_RecoEmbargoContinue, info_RecoRefId,
			btn_RecoEmbargoClose, txt_commWt, txt_commVol, btn_execute, txt_shipmentDetailsPcs,
			txt_shipmentDetailsWeight, txt_shipmentDetailsVolume, txt_shipmentDetailsCmdtyCode, btn_genericOk,
			info_genericMsg, txt_pymnt_txt_custCode, btn_HAWB, info_HouseNo1, info_HouseNo2, btn_CloseCaptureHouse,
			pymnt_btn_Save, btn_AdditionalInfoTab, info_source, txt_DropoffTime, div_creditbtn, div_Cash, txt_ubrNo,
			chk_verifyExecute, txt_spotRate, txt_markRateCard, txt_totalPP, txt_OCDCpp, txt_OCDApp, dropDown_houses,
			info_errormsg, btn_reOpen, txt_product, btn_btnAutoRate, btn_btnCalculateCharge, btn_btnComputeTotal,
			Generic_info_error, dropdown_chargesCode, dropdown_paymentType, chkbox_paymnetAdviceCredit,
			dropdown_chargePaymentType, chkbox_dueCarrier, txt_routingCarrCode2, txt_routingDest2, info_errorMsg,
			msg_blk_errors, txt_agentName, txt_chargeAdjWt, txt_chargeChargableWt, tbl_chargeTblHeader,
			tbl_chargeTblBody, info_dgrDtls, txt_ratedCust, btn_chargeCurrencyLOV, txt_currLovCurrCode, btn_currLOVlist,
			btn_currLOVOk, chkBx_currLOVSelectChkBx;

	// created byA-8254
	private By btn_Attach_Detach, txt_awbNo_AttachWindow, btn_Attach_Detach_AttachWindow, btn_Select_SaveTemplate,
			txt_Save_template_As, txt_Brief_Description, btn_Template_Save, list_Template;

	private By txt_chargeCode, txt_chargeValue, chkbox_duecarrier, txt_iataratelineid, txt_marketratelineid,
			btn_CloseCaptureHouseNavigate, btn_sendFWB, btn_send, btn_add, dropdwn_interfacesys,
			dropdwn_transmissionmode, btn_address, txt_filelistener, btn_okButton, btn_okButton1, info_footer, btn_DGR,
			txt_DGRUnid, txt_DGRaddedUNID, txt_DGRPcs, dropDown_DGRName, btn_DGRadd, btn_DGRdelete, btn_DGROk,
			txt_DGRwtPerPcs, dropDown_DGRWtUnit, txt_DGRpi, txt_DGRSCC, txt_servicecargo, txt_iatacharge, btn_print,
			btn_Lprint, radio_publishedrates, chkbox_termscond, btn_okbuton, btn_closebuton, txt_statedwt,
			txt_statedpcs, dropDown_houses1, btn_proceed, txt_servicedetails, tbl_DGRunid, btn_checkSheet,
			btn_chkSheetSave, btn_chkSheetClose, txt_chkSheetResponse, tab_additionalInfo, info_sccLbl,
			tbl_otherCustomInfo, tab_bookingDetails, txt_flightNo, txt_flightDate,
			// new A-8254
			txt_shipper_address, txt_consignee_address, img_dimension, txt_dimPcs, txt_dimWt, txt_dimLength,
			txt_dimWidth, txt_dimHeight, txt_dimVol, btn_dimOk, Irregularity_Inbx_irregularityCode,
			Irregularity_Inbx_irregularityRemarks, Irregularity_Btn_OKbutton, valueStatus, chkBx_DGRcheckAll,
			btn_DGRTypeOfPkg, chkBx_pkgDtlsAllPkOne, txt_pkgDtlsNoOfPkg, btn_pkgDtlsOk, txt_Charges_grossVol,

			tab_chargesAndAccounting, txt_uniqueReferance,
			// A-7290
			btn_Add_payment, btn_PaymentFinalize;

	private By tbl_Additional_info;

	public OPR026(WebDriver driver, String dataFileName, ExtentTest test) {
		super(driver,test);
		this.driver = driver;
		this.test=test;
		initElements();
		this.dataFilePath = this.dataFilePath + dataFileName;
		this.dataFileName = dataFileName;
	}

	/**
	 * Initializes the page objects required for the class
	 */
	private void initElements() {

		txt_prefix = MiscUtility.getWebElement(objFilepath, "OPR026_txt_shipmentPrefix");
		txt_awb = MiscUtility.getWebElement(objFilepath, "OPR026_txt_AWBNo");

		chcbx_Autorate = MiscUtility.getWebElement(objFilepath, "OPR026_chcbx_Autorate");
		btn_list = MiscUtility.getWebElement(objFilepath, "OPR026_btn_list");
		div_sc = MiscUtility.getWebElement(objFilepath, "OPR026_shipperConsigneeDiv");
		txt_shipper = MiscUtility.getWebElement(objFilepath, "OPR026_shipperCode");
		txt_consignee = MiscUtility.getWebElement(objFilepath, "OPR026_consigneeCode");
		btn_chargeTab = MiscUtility.getWebElement(objFilepath, "OPR026_btn_chargesTab");
		dropdown_sci = MiscUtility.getWebElement(objFilepath, "OPR026_dropdown_sci");
		txt_chargePcs = MiscUtility.getWebElement(objFilepath, "OPR026_chargeTab_txt_pcs");
		txt_chargeWt = MiscUtility.getWebElement(objFilepath, "OPR026_chargeTab_txt_wt");
		dropdown_rateClass = MiscUtility.getWebElement(objFilepath, "OPR026_chargeTab_dropdown_rateClass");
		txt_iataRate = MiscUtility.getWebElement(objFilepath, "OPR026_chargeTab_txt_iataRate");
		txt_netCharge = MiscUtility.getWebElement(objFilepath, "OPR026_chargeTab_txt_netCharge");
		btn_asIs = MiscUtility.getWebElement(objFilepath, "OPR026_btn_asIsExecute");
		btn_close = MiscUtility.getWebElement(objFilepath, "OPR026_btn_close");
		msg_executed = MiscUtility.getWebElement(objFilepath, "OPR026_msg_executed");
		info_executed = MiscUtility.getWebElement(objFilepath, "OPR026_info_executed");
		btn_save = MiscUtility.getWebElement(objFilepath, "OPR026_btn_save");
		chkbx_HAWBFinalized = MiscUtility.getWebElement(objFilepath, "OPR026_chkbx_HAWBFinalized");
		chkbx_consol = MiscUtility.getWebElement(objFilepath, "OPR026_chkbx_consol");
		txt_origin = MiscUtility.getWebElement(objFilepath, "OPR026_txt_origin");
		txt_dest = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dest");
		txt_routingDest1 = MiscUtility.getWebElement(objFilepath, "OPR026_txt_routingDest1");
		txt_routingCarrCode1 = MiscUtility.getWebElement(objFilepath, "OPR026_txt_routingCarrCode1");
		txt_scc = MiscUtility.getWebElement(objFilepath, "OPR026_txt_scc");
		txt_agentCode = MiscUtility.getWebElement(objFilepath, "OPR026_txt_agentCode");
		txt_agentName = MiscUtility.getWebElement(objFilepath, "OPR026_txt_agentName");
		txt_commCode = MiscUtility.getWebElement(objFilepath, "OPR026_txt_commCode");
		txt_commPcs = MiscUtility.getWebElement(objFilepath, "OPR026_txt_commPcs");
		txt_commWt = MiscUtility.getWebElement(objFilepath, "OPR026_txt_commWt");
		txt_commVol = MiscUtility.getWebElement(objFilepath, "OPR026_txt_commVol");
		btn_clear = MiscUtility.getWebElement(objFilepath, "OPR026_btn_clear");
		btn_execute = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Execute");
		btn_Delete = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Delete");
		info_RecoRefId = MiscUtility.getWebElement(objFilepath, "OPR026_info_RecoRefId");
		btn_RecoEmbargoContinue = MiscUtility.getWebElement(objFilepath, "OPR026_btn_RecoEmbargoContinue");
		info_saveawb = MiscUtility.getWebElement(objFilepath, "OPR026_info_saveawb");
		btn_RecoEmbargoClose = MiscUtility.getWebElement(objFilepath, "OPR026_btn_RecoEmbargoClose");
		dropDown_houses = MiscUtility.getWebElement(objFilepath, "OPR026_dropDown_houses");
		btn_HAWB = MiscUtility.getWebElement(objFilepath, "OPR026_btn_HAWB");
		info_HouseNo1 = MiscUtility.getWebElement(objFilepath, "OPR026_info_HouseNo1");
		info_HouseNo2 = MiscUtility.getWebElement(objFilepath, "OPR026_info_HouseNo2");
		btn_CloseCaptureHouse = MiscUtility.getWebElement(objFilepath, "OPR026_btn_CloseCaptureHouse");
		btn_AdditionalInfoTab = MiscUtility.getWebElement(objFilepath, "OPR026_btn_AdditionalInfoTab");
		info_source = MiscUtility.getWebElement(objFilepath, "OPR026_info_source");
		txt_DropoffTime = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DropoffTime");
		txt_ubrNo = MiscUtility.getWebElement(objFilepath, "OPR026_txt_ubrNo");
		txt_spotRate = MiscUtility.getWebElement(objFilepath, "OPR026_txt_spotRate");
		txt_markRateCard = MiscUtility.getWebElement(objFilepath, "OPR026_txt_markRateCard");
		txt_OCDApp = MiscUtility.getWebElement(objFilepath, "OPR026_txt_OCDApp");
		txt_OCDCpp = MiscUtility.getWebElement(objFilepath, "OPR026_txt_OCDCpp");
		txt_totalPP = MiscUtility.getWebElement(objFilepath, "OPR026_txt_totalPP");
		info_errormsg = MiscUtility.getWebElement(objFilepath, "OPR026_info_errormsg");
		txt_chargeAdjWt = MiscUtility.getWebElement(objFilepath, "OPR026_txt_chargeAdjWt");
		txt_chargeChargableWt = MiscUtility.getWebElement(objFilepath, "OPR026_txt_chargeChargableWt");
		info_footerNotification = MiscUtility.getWebElement(genObjPath, "Generic_foot_layer");
		tbl_chargeTblHeader = MiscUtility.getWebElement(objFilepath, "OPR026_tbl_chargeTblHeader");
		tbl_chargeTblBody = MiscUtility.getWebElement(objFilepath, "OPR026_tbl_chargeTblBody");
		// payment screen
		txt_remarks = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_txt_remarks");
		btn_pymnt_ok = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_btn_ok");
		btn_finalize = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_btn_finalize");
		txt_pymntAdvice = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_txt_adviceNo");
		btn_pymnt_close = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_btn_close");
		pymnt_btn_Save = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_btn_Save");
		txt_product = MiscUtility.getWebElement(objFilepath, "OPR026_txt_product");
		txt_Currencyv = MiscUtility.getWebElement(objFilepath, "OPR026_txt_Currencyv");
		txt_shipmentDetailsPcs = MiscUtility.getWebElement(objFilepath, "OPR026_txt_shipmentDetailsPcs");
		txt_shipmentDetailsWeight = MiscUtility.getWebElement(objFilepath, "OPR026_txt_shipmentDetailsWeight");
		txt_shipmentDetailsVolume = MiscUtility.getWebElement(objFilepath, "OPR026_txt_shipmentDetailsVolume");
		txt_shipmentDetailsCmdtyCode = MiscUtility.getWebElement(objFilepath, "OPR026_txt_shipmentDetailsCmdtyCode");
		txt_pymnt_txt_custCode = MiscUtility.getWebElement(objFilepath, "OPR026_pymnt_txt_custCode");
		btn_reOpen = MiscUtility.getWebElement(objFilepath, "OPR026_btn_reOpen");
		chk_verifyExecute = MiscUtility.getWebElement(objFilepath, "OPR026_chk_verifyExecute");
		div_creditbtn = MiscUtility.getWebElement(objFilepath, "OPR026_div_creditbtn");
		div_Cash = MiscUtility.getWebElement(objFilepath, "OPR026_div_Cash");

		btn_btnAutoRate = MiscUtility.getWebElement(objFilepath, "OPR026_btn_btnAutoRate");
		btn_btnCalculateCharge = MiscUtility.getWebElement(objFilepath, "OPR026_btn_btnCalculateCharge");
		btn_btnComputeTotal = MiscUtility.getWebElement(objFilepath, "OPR026_btn_btnComputeTotal");
		txt_ActualAmount = MiscUtility.getWebElement(objFilepath, "OPR026_txt_ActualAmount");
		dropdown_chargesCode = MiscUtility.getWebElement(objFilepath, "OPR026_dropdown_chargesCode");
		dropdown_paymentType = MiscUtility.getWebElement(objFilepath, "OPR026_dropdown_paymentType");
		dropdown_chargePaymentType = MiscUtility.getWebElement(objFilepath, "OPR026_dropdown_chargePaymentType");
		chkbox_dueCarrier = MiscUtility.getWebElement(objFilepath, "OPR026_chkbox_dueCarrier");
		txt_routingCarrCode2 = MiscUtility.getWebElement(objFilepath, "OPR026_txt_routingCarrCode2");
		txt_routingDest2 = MiscUtility.getWebElement(objFilepath, "OPR026_txt_routingDest2");
		chkbox_paymnetAdviceCredit = MiscUtility.getWebElement(objFilepath, "OPR026_chkbox_paymnetAdviceCredit");
		/*
		 * btn_chargeCurrencyLOV = MiscUtility.getWebElement(objFilepath,
		 * "OPR026_btn_chargeCurrencyLOV"); txt_currLovCurrCode =
		 * MiscUtility.getWebElement(objFilepath, "OPR026_txt_currLovCurrCode");
		 * btn_currLOVlist = MiscUtility.getWebElement(objFilepath,
		 * "OPR026_btn_currLOVlist"); btn_currLOVOk =
		 * MiscUtility.getWebElement(objFilepath, "OPR026_btn_currLOVOk");
		 * chkBx_currLOVSelectChkBx = MiscUtility.getWebElement(objFilepath,
		 * "OPR026_chkBx_currLOVSelectChkBx");
		 */
		yesBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
		Generic_info_error = MiscUtility.getWebElement(genObjPath, "Generic_info_error");
		noBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_noBtn");
		msg_popup = MiscUtility.getWebElement(genObjPath, "Generic_info_msg");
		btn_genericOk = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		info_genericMsg = MiscUtility.getWebElement(genObjPath, "Generic_info_msg");
		info_errorMsg = MiscUtility.getWebElement(genObjPath, "Generic_info_error");
		okBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		txt_AWBOnDate = MiscUtility.getWebElement(objFilepath, "OPR026_txt_AWBOnDate");
		txt_AWBdropOffdate = MiscUtility.getWebElement(objFilepath, "OPR026_txt_AWBdropOffdate");
		btn_btnDuplicate = MiscUtility.getWebElement(objFilepath, "OPR026_btn_btnDuplicate");
		// created byA-8254
		btn_Attach_Detach = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Attach_Detach");
		txt_awbNo_AttachWindow = MiscUtility.getWebElement(objFilepath, "OPR026_txt_awbNo_AttachWindow");
		btn_Attach_Detach_AttachWindow = MiscUtility.getWebElement(objFilepath,
				"OPR026_btn_Attach_Detach_AttachWindow");
		btn_Select_SaveTemplate = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Select_SaveTemplate");
		txt_Save_template_As = MiscUtility.getWebElement(objFilepath, "OPR026_txt_Save_template_As");
		txt_Brief_Description = MiscUtility.getWebElement(objFilepath, "OPR026_txt_Brief_Description");
		btn_Template_Save = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Template_Save");
		list_Template = MiscUtility.getWebElement(objFilepath, "OPR026_list_Template");
		tbl_RecoPopup = MiscUtility.getWebElement(objFilepath, "OPR026_tbl_RecoPopup");
		txt_servicecargo = MiscUtility.getWebElement(objFilepath, "OPR026_txt_servicecargo");
		// abhilash
		msg_blk_errors = MiscUtility.getWebElement(genObjPath, "Generic_blk_error");

		// pavani
		txt_chargeCode = MiscUtility.getWebElement(objFilepath, "OPR026_txt_chargeCode");
		txt_chargeValue = MiscUtility.getWebElement(objFilepath, "OPR026_txt_chargeValue");
		chkbox_duecarrier = MiscUtility.getWebElement(objFilepath, "OPR026_chkbox_dueCarrier");
		txt_iataratelineid = MiscUtility.getWebElement(objFilepath, "OPR026_txt_iataratelineid");
		txt_marketratelineid = MiscUtility.getWebElement(objFilepath, "OPR026_txt_marketratelineid");
		btn_CloseCaptureHouseNavigate = MiscUtility.getWebElement(objFilepath, "OPR026_btn_CloseCaptureHouseNavigate");
		btn_sendFWB = MiscUtility.getWebElement(objFilepath, "OPR026_btn_sendFWB");
		btn_send = MiscUtility.getWebElement(objFilepath, "OPR026_btn_send");
		btn_add = MiscUtility.getWebElement(objFilepath, "OPR026_btn_add");
		dropdwn_interfacesys = MiscUtility.getWebElement(objFilepath, "OPR026_dropdwn_interfacesys");
		dropdwn_transmissionmode = MiscUtility.getWebElement(objFilepath, "OPR026_dropdwn_transmissionmode");
		btn_address = MiscUtility.getWebElement(objFilepath, "OPR026_btn_address");
		txt_filelistener = MiscUtility.getWebElement(objFilepath, "OPR026_txt_filelistener");
		btn_okButton = MiscUtility.getWebElement(objFilepath, "OPR026_btn_okButton");
		btn_okButton1 = MiscUtility.getWebElement(objFilepath, "OPR026_btn_okButton1");
		info_footer = MiscUtility.getWebElement(genObjPath, "Generic_foot_layer");
		btn_DGR = MiscUtility.getWebElement(objFilepath, "OPR026_btn_DGR");
		txt_DGRUnid = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DGRUnid");
		txt_DGRaddedUNID = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DGRaddedUNID");
		txt_DGRPcs = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DGRPcs");
		dropDown_DGRName = MiscUtility.getWebElement(objFilepath, "OPR026_dropDown_DGRName");
		btn_DGRadd = MiscUtility.getWebElement(objFilepath, "OPR026_btn_DGRadd");
		btn_DGRdelete = MiscUtility.getWebElement(objFilepath, "OPR026_btn_DGRdelete");
		btn_DGROk = MiscUtility.getWebElement(objFilepath, "OPR026_btn_DGROk");
		txt_DGRwtPerPcs = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DGRwtPerPcs");
		dropDown_DGRWtUnit = MiscUtility.getWebElement(objFilepath, "OPR026_dropDown_DGRWtUnit");
		txt_DGRpi = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DGRpi");
		txt_DGRSCC = MiscUtility.getWebElement(objFilepath, "OPR026_txt_DGRSCC");
		txt_servicecargo = MiscUtility.getWebElement(objFilepath, "OPR026_txt_servicecargo");
		txt_iatacharge = MiscUtility.getWebElement(objFilepath, "OPR026_txt_iatacharge");
		btn_print = MiscUtility.getWebElement(objFilepath, "OPR026_btn_print");
		btn_Lprint = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Lprint");
		radio_publishedrates = MiscUtility.getWebElement(objFilepath, "OPR026_radio_publishedrates");
		chkbox_termscond = MiscUtility.getWebElement(objFilepath, "OPR026_chkbox_termscond");
		btn_okbuton = MiscUtility.getWebElement(objFilepath, "OPR026_btn_okbuton");
		btn_closebuton = MiscUtility.getWebElement(objFilepath, "OPR026_btn_closebuton");
		txt_statedpcs = MiscUtility.getWebElement(objFilepath, "OPR026_txt_statedpcs");
		txt_statedwt = MiscUtility.getWebElement(objFilepath, "OPR026_txt_statedwt");
		dropDown_houses1 = MiscUtility.getWebElement(objFilepath, "OPR026_dropDown_houses1");
		btn_proceed = MiscUtility.getWebElement(objFilepath, "OPR026_btn_proceed");
		txt_servicedetails = MiscUtility.getWebElement(objFilepath, "OPR026_txt_servicedetails");
		tbl_DGRunid = MiscUtility.getWebElement(objFilepath, "OPR026_tbl_DGRunid");
		info_dgrDtls = MiscUtility.getWebElement(objFilepath, "OPR026_info_dgrDtls");
		// checksheet
		btn_checkSheet = MiscUtility.getWebElement(objFilepath, "OPR026_btn_checkSheet");
		btn_chkSheetSave = MiscUtility.getWebElement(objFilepath, "OPR026_btn_chkSheetSave");
		btn_chkSheetClose = MiscUtility.getWebElement(objFilepath, "OPR026_btn_chkSheetClose");
		txt_chkSheetResponse = MiscUtility.getWebElement(objFilepath, "OPR026_txt_chkSheetResponse");

		tab_additionalInfo = MiscUtility.getWebElement(objFilepath, "OPR026_tab_additionalInfo");
		info_sccLbl = MiscUtility.getWebElement(objFilepath, "OPR026_info_sccLbl");
		tbl_otherCustomInfo = MiscUtility.getWebElement(objFilepath, "OPR026_tbl_otherCustomInfo");

		// new A-8254
		txt_shipper_address = MiscUtility.getWebElement(objFilepath, "OPR026_txt_shipper_address");
		txt_consignee_address = MiscUtility.getWebElement(objFilepath, "OPR026_txt_consignee_address");
		tab_bookingDetails = MiscUtility.getWebElement(objFilepath, "OPR026_tab_bookingDetails");
		txt_flightNo = MiscUtility.getWebElement(objFilepath, "OPR026_txt_flightNo");
		txt_flightDate = MiscUtility.getWebElement(objFilepath, "OPR026_txt_flightDate");
		Irregularity_Inbx_irregularityCode = MiscUtility.getWebElement(objFilepath,
				"OPR026_Irregularity_Inbx_irregularityCode");
		Irregularity_Inbx_irregularityRemarks = MiscUtility.getWebElement(objFilepath,
				"OPR026_Irregularity_Inbx_irregularityRemarks");
		Irregularity_Btn_OKbutton = MiscUtility.getWebElement(objFilepath, "OPR026_Irregularity_Btn_OKbutton");
		Irregularity_Inbx_irregularityCode = MiscUtility.getWebElement(objFilepath,
				"OPR026_Irregularity_Inbx_irregularityCode");
		Irregularity_Inbx_irregularityRemarks = MiscUtility.getWebElement(objFilepath,
				"OPR026_Irregularity_Inbx_irregularityRemarks");
		Irregularity_Btn_OKbutton = MiscUtility.getWebElement(objFilepath, "OPR026_Irregularity_Btn_OKbutton");
		tab_bookingDetails = MiscUtility.getWebElement(objFilepath, "OPR026_tab_bookingDetails");
		txt_flightNo = MiscUtility.getWebElement(objFilepath, "OPR026_txt_flightNo");
		txt_flightDate = MiscUtility.getWebElement(objFilepath, "OPR026_txt_flightDate");
		Irregularity_Inbx_irregularityCode = MiscUtility.getWebElement(objFilepath,
				"OPR026_Irregularity_Inbx_irregularityCode");
		Irregularity_Inbx_irregularityRemarks = MiscUtility.getWebElement(objFilepath,
				"OPR026_Irregularity_Inbx_irregularityRemarks");
		Irregularity_Btn_OKbutton = MiscUtility.getWebElement(objFilepath, "OPR026_Irregularity_Btn_OKbutton");

		img_dimension = MiscUtility.getWebElement(objFilepath, "OPR026_img_dimension");
		txt_dimPcs = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dimPcs");
		txt_dimWt = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dimWt");
		txt_dimLength = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dimLength");
		txt_dimWidth = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dimWidth");
		txt_dimHeight = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dimHeight");
		txt_dimVol = MiscUtility.getWebElement(objFilepath, "OPR026_txt_dimVol");
		btn_dimOk = MiscUtility.getWebElement(objFilepath, "OPR026_btn_dimOk");

		chkBx_DGRcheckAll = MiscUtility.getWebElement(objFilepath, "OPR026_chkBx_DGRcheckAll");
		btn_DGRTypeOfPkg = MiscUtility.getWebElement(objFilepath, "OPR026_btn_DGRTypeOfPkg");
		chkBx_pkgDtlsAllPkOne = MiscUtility.getWebElement(objFilepath, "OPR026_chkBx_pkgDtlsAllPkOne");
		txt_pkgDtlsNoOfPkg = MiscUtility.getWebElement(objFilepath, "OPR026_txt_pkgDtlsNoOfPkg");
		btn_pkgDtlsOk = MiscUtility.getWebElement(objFilepath, "OPR026_btn_pkgDtlsOk");
		valueStatus = MiscUtility.getWebElement(objFilepath, "OPR026_valueStatus");

		tab_bookingDetails = MiscUtility.getWebElement(objFilepath, "OPR026_tab_bookingDetails");
		txt_flightNo = MiscUtility.getWebElement(objFilepath, "OPR026_txt_flightNo");
		txt_flightDate = MiscUtility.getWebElement(objFilepath, "OPR026_txt_flightDate");
		txt_Charges_grossVol = MiscUtility.getWebElement(objFilepath, "OPR026_txt_Charges_grossVol");

		tab_chargesAndAccounting = MiscUtility.getWebElement(objFilepath, "OPR026_tab_chargesAndAccounting");
		txt_uniqueReferance = MiscUtility.getWebElement(objFilepath, "OPR026_txt_uniqueReferance");
		btn_PaymentFinalize = MiscUtility.getWebElement(objFilepath, "OPR026_btn_PaymentFinalize");
		btn_Add_payment = MiscUtility.getWebElement(objFilepath, "OPR026_btn_Add_payment");

		// tbl_Additional_info=MiscUtility.getWebElement(objFilepath,"OPR026_tbl_Additional_info");
	}

	/*****
	 * 
	 * @param prefix
	 * @param awbno
	 * @param stockType
	 * @param origin
	 * @param carrCode
	 * @param dest
	 * @param shipper
	 * @param consignee
	 * @param sci
	 * @param pcs
	 * @param wt
	 * @param commCode
	 * @param chargetype
	 * @return
	 */

	public OPR026 ExecuteMultirouteAWB(String prefix, String awbno, String stockType, String origin, String carrCode,
			String carrCode2, String dest, String intermediate, String shipper, String consignee, String sci,
			String pcs, String wt, String commCode, String chargetype, String... currency) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		// chargetype=PropertyHandler.getPropValue(dataFilePath, chargetype);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		carrCode2 = PropertyHandler.getPropValue(dataFilePath, carrCode2);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, prefix, awbno);
		maxWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, intermediate);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_routingDest2, dest);
		enterKeys(txt_routingCarrCode2, carrCode2);

		selectByText(dropdown_sci, sci);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_agentCode, shipper + Keys.TAB);

		// click(div_sc);
		minWait();
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		minWait();

		enterKeys(txt_product, "");
		click(btn_chargeTab);
		minWait();
		if (currency.length != 0) {
			click(btn_chargeCurrencyLOV);
			switchToSecondWindow();
			enterKeys(txt_currLovCurrCode, currency[0]);
			click(btn_currLOVlist);
			check(chkBx_currLOVSelectChkBx);
			click(btn_currLOVOk);
			switchToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
		}
		if (chargetype == "PP") {

			// click(btn_btnAutoRate);
			// minWait();
			selectByValue(dropdown_chargePaymentType, "PP");
			selectByValue(dropdown_paymentType, "PP");
			selectByValue(dropdown_chargePaymentType, "PP");
		} else if (chargetype == "CC") {
			selectByValue(dropdown_chargePaymentType, "CC");
			selectByValue(dropdown_paymentType, "CC");
			selectByValue(dropdown_chargePaymentType, "CC");
		}

		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();

		click(btn_execute);
		minWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		// driver.switchTo().defaultContent();
		//
		// if
		// (waitForElement(msg_popup).getText().contains("different from the
		// booked"))
		// {
		//
		// while (verifyElementPresent(yesBtn)) {
		// status = waitForElement(info_genericMsg).getText();
		//
		// click(yesBtn);
		// }
		driver.switchTo().defaultContent();
		// String status = waitForElement(msg_executed).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		// Assert.assertTrue(status.contains("executed"),
		// "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/*****
	 * 
	 * @param prefix
	 * @param awbno
	 * @param stockType
	 * @param origin
	 * @param carrCode
	 * @param dest
	 * @param shipper
	 * @param consignee
	 * @param sci
	 * @param pcs
	 * @param wt
	 * @param commCode
	 * @param chargetype
	 * @return
	 */

	public OPR026 SaveMultirouteAWB(String prefix, String awbno, String stockType, String origin, String carrCode,
			String carrCode2, String dest, String intermediate, String shipper, String consignee, String sci,
			String pcs, String wt, String commCode, String chargetype) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		// chargetype=PropertyHandler.getPropValue(dataFilePath, chargetype);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		carrCode2 = PropertyHandler.getPropValue(dataFilePath, carrCode2);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, prefix, awbno);
		maxWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, intermediate);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_routingDest2, dest);
		enterKeys(txt_routingCarrCode2, carrCode2);

		selectByText(dropdown_sci, sci);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_agentCode, shipper + Keys.TAB);

		// click(div_sc);
		minWait();
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		minWait();

		enterKeys(txt_product, "");
		click(btn_chargeTab);
		minWait();
		if (chargetype == "PP") {

			// click(btn_btnAutoRate);
			// minWait();
			selectByValue(dropdown_chargePaymentType, "PP");
			selectByValue(dropdown_paymentType, "PP");
			selectByValue(dropdown_chargePaymentType, "PP");
		} else if (chargetype == "CC") {
			selectByValue(dropdown_chargePaymentType, "CC");
			selectByValue(dropdown_paymentType, "CC");
			selectByValue(dropdown_chargePaymentType, "CC");
		}

		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();

		click(btn_save);

		driver.switchTo().defaultContent();

		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 ExecuteBookedAWB_withChargeCode(String prefix, String awbno, String stockType, String shipper,
			String consignee, String sci, String chargetype) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		click(btn_clear);
		minWait();
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();

		enterKeys(txt_agentCode, shipper + Keys.TAB);

		click(div_sc);
		minWait();
		click(div_sc);
		minWait();
		// click(div_sc);
		if (isElementPresent(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		minWait();
		if (chargetype == "PP") {

			// click(btn_btnAutoRate);
			// minWait();
			selectByValue(dropdown_chargePaymentType, "PP");
			selectByValue(dropdown_paymentType, "PP");
			selectByValue(dropdown_chargePaymentType, "PP");
		} else if (chargetype == "CC") {
			selectByValue(dropdown_chargePaymentType, "CC");
			selectByValue(dropdown_paymentType, "CC");
			selectByValue(dropdown_chargePaymentType, "CC");
		}

		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();

		click(btn_execute);
		minWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		String status = "";
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			// status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		if (verifyElementPresent(yesBtn)) {
			// status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),
		// "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("Executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 ExecuteBookedAWB_CP(String prefix, String awbno, String shipper, String consignee, String sci) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		click(btn_clear);
		minWait();
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();

		enterKeys(txt_agentCode, shipper + Keys.TAB);

		expandShipperConsigneeWrapper();
		minWait();
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		minWait();
		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		minWait();

		// click(btn_btnAutoRate);
		// minWait();
		selectByValue(dropdown_chargePaymentType, "CC");
		selectByValue(dropdown_paymentType, "CC");
		selectByValue(dropdown_chargePaymentType, "PP");

		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();

		click(btn_execute);

		String status = "";
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);

		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 ExecuteBookedAWB_PC(String prefix, String awbno, String shipper, String consignee, String sci) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		click(btn_clear);
		minWait();
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();

		enterKeys(txt_agentCode, shipper + Keys.TAB);

		expandShipperConsigneeWrapper();
		minWait();
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		minWait();
		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		minWait();

		// click(btn_btnAutoRate);
		// minWait();
		selectByValue(dropdown_chargePaymentType, "PP");
		selectByValue(dropdown_paymentType, "PP");
		selectByValue(dropdown_chargePaymentType, "CC");

		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();

		click(btn_execute);

		String status = "";
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);

		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 FopCreditNoncreditexecuteAWB(String prefix, String awbno, String stockType, String origin,
			String carrCode, String dest, String shipper, String consignee, String sci, String pcs, String wt,
			String commCode, String chargetype) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		chargetype = PropertyHandler.getPropValue(dataFilePath, chargetype);

		StockType stock = StockType.valueOf(stockType);
		minWait();

		getAWB(stock, prefix, awbno);
		maxWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_product, "");
		selectByText(dropdown_sci, sci);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_agentCode, shipper + Keys.TAB);
		/*
		 * click(div_sc); waitForElement(btn_chargeTab);
		 */
		expandShipperConsigneeWrapper();
		minWait();
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		minWait();

		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		minWait();
		if (chargetype == "PP") {

			// click(btn_btnAutoRate);
			// minWait();
			selectByValue(dropdown_chargePaymentType, "PP");
			selectByValue(dropdown_paymentType, "PP");
			selectByValue(dropdown_chargePaymentType, "PP");
		} else if (chargetype == "CC") {
			selectByValue(dropdown_chargePaymentType, "CC");
			selectByValue(dropdown_paymentType, "CC");
			selectByValue(dropdown_chargePaymentType, "CC");
		}

		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();

		click(btn_execute);
		minWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		String status = "";
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			// status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		if (verifyElementPresent(yesBtn)) {
			// status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		minWait();
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),
		// "ERROR : AWB not Executed.");
		// Assert.assertTrue(status.contains("Executed"),
		// "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 createAndSaveDuplicateAwb(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		maxWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_product, "");
		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		minWait();
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		enterKeys(txt_prefix, awbPre);
		enterKeys(txt_awb, awbNo);
		click(btn_list);
		minWait();
		click(btn_btnDuplicate);
		maxWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_product, "");
		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		minWait();
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();

		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	/***
	 * Capturing paymnent advice by checking verify and pay checkbox
	 * 
	 * @param prefix
	 * @param awbno
	 * @param stockType
	 * @param shipper
	 * @param consignee
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @param sci
	 * @param paymentType
	 * @return
	 */
	public OPR026 executeWithVerifyandPay(String prefix, String awbno, String stockType, String shipper,
			String consignee, String rateClass, String iataRate, String netCharge, String sci, String paymentType) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		click(div_sc);
		minWait();
		// click(div_sc);
		if (isElementPresent(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			minWait();
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			minWait();
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(chk_verifyExecute);
		check(chk_verifyExecute);
		if (paymentType.equals("CREDIT")) {
			click(div_creditbtn);
		} else {

			List<WebElement> listElement1;
			listElement1 = getWebElements(chkbox_paymnetAdviceCredit);
			int count1 = listElement1.size();
			for (int i = 1; i <= count1; i++) {
				unCheck(getWebElements(chkbox_paymnetAdviceCredit).get(i));
			}

			click(div_Cash);
		}

		enterKeys(txt_remarks, "Payment Remarks");
		click(btn_pymnt_ok);
		click(btn_finalize);
		minWait();
		Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
				"ERROR : Payment not successful");
		click(btn_pymnt_close);
		minWait();

		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;

	}

	/****
	 * 
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @param sci
	 * @param paymentType
	 * @return
	 */
	public OPR026 verifyandPayExceution(String prefix, String awbno, String rateClass, String iataRate,
			String netCharge, String sci, String paymentType) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(chk_verifyExecute);
		check(chk_verifyExecute);
		if (paymentType.equals("CREDIT")) {
			click(div_creditbtn);
		} else {
			click(div_Cash);
		}

		enterKeys(txt_remarks, "Payment Remarks");
		click(btn_pymnt_ok);
		click(btn_finalize);
		minWait();
		Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
				"ERROR : Payment not successful");
		click(btn_pymnt_close);
		minWait();

		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;

	}

	public OPR026 storeTotalAmt(String prefix, String awbno, String toatalAmt) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		click(div_sc);

		return this;

	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields, rate fields and
	 * As-Is Execute Handles both cash and credit customers
	 * 
	 * @author a-7868 21/12/2017
	 */
	public OPR026 asIsExecute(String prefix, String awbno, String stockType, String shipper, String consignee,
			String rateClass, String iataRate, String netCharge, String sci) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		minWait();
		// click(div_sc);
		if (verifyElementVisible(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		minWait();
		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_asIs);
		maxWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}
		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * as is execute by changing chargeble wt
	 * 
	 * @param prefix
	 * @param awbno
	 * @param stockType
	 * @param shipper
	 * @param consignee
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @param sci
	 * @return
	 */
	public OPR026 asIsExecute_updateChargebleWt(String prefix, String awbno, String stockType, String shipper,
			String consignee, String rateClass, String iataRate, String netCharge, String sci, String newChargebleWt) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		newChargebleWt = PropertyHandler.getPropValue(dataFilePath, newChargebleWt);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		minWait();
		// click(div_sc);
		if (verifyElementVisible(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		enterKeys(txt_chargeChargableWt, newChargebleWt);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_asIs);
		maxWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}
		minWait();
		// driver.switchTo().defaultContent();
		// if(waitForElement(msg_popup).getText().contains("different from the
		// booked"))
		// {
		// click(yesBtn);
		// }
		driver.switchTo().defaultContent();
		String status = null;
		while (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 asIsExecute_UpdateDate(String prefix, String awbno, String stockType, String shipper,
			String consignee, String rateClass, String iataRate, String netCharge, String sci, String flightDate) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		minWait();
		expandShipperConsigneeWrapper();
		minWait();
		// click(div_sc);
		// if (isElementPresent(txt_shipper)) {
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		// } else {
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		// }

		selectByText(dropdown_sci, sci);

		enterKeys(txt_AWBOnDate, flightDate);
		enterKeys(txt_AWBdropOffdate, flightDate);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_asIs);
		maxWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}
		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * Method to list Blacklisted AWB and verify error message
	 * 
	 * @author a-6836 21/12/2017
	 */
	public OPR026 ListBlacklistedAWB(String prefix, String awbno) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		list(prefix, awbno);
		minWait();

		driver.switchTo().defaultContent();
		waitForElement(info_genericMsg).getText().contains("AWB does not exist.Do you want to capture?");
		click(yesBtn);
		waitForFrameAndSwitch(screenFrame);
		String status = waitForElement(info_errormsg).getText();
		Assert.assertTrue(waitForElement(info_errormsg).getText().contains("is blacklisted"),
				"ERROR :Able to proceed booking for blacklisted AWB.");
		Assert.assertTrue(status.contains("is blacklisted"), "ERROR :Able to proceed booking for blacklisted AWB.");
		minWait();

		return this;
	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields, rate fields and
	 * Execute Handles both cash and credit customers
	 * 
	 * @author a-7868 18/01/2018
	 */
	public OPR026 executeAWB(String prefix, String awbno, String stockType, String shipper, String consignee,
			String rateClass, String iataRate, String netCharge, String sci) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		expandShipperConsigneeWrapper();

		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		minWait();
		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_execute);

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		String status = "";
		driver.switchTo().defaultContent();
		while (verifyElementPresent(yesBtn)) {
			click(yesBtn);
			minWait();
		}
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		// Assert.assertTrue(status.contains("executed"),
		// "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 verifySpot(String prefix, String awbno, String SpotRateId, boolean isSpot) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		SpotRateId = PropertyHandler.getPropValue(dataFilePath, SpotRateId);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		click(btn_chargeTab);
		if (isSpot) {
			String tempId = waitForElement(txt_spotRate).getAttribute("value");

			Assert.assertEquals(tempId, SpotRateId);
			click(waitForElement(btn_clear));

		} else {

			waitForElement(txt_spotRate).getAttribute("value").isEmpty();

			click(waitForElement(btn_clear));
		}

		minWait();

		return this;

	}

	public OPR026 verifyRate(String prefix, String awbno, String Rate, boolean IsRateExistInFWB) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		Rate = PropertyHandler.getPropValue(dataFilePath, Rate);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		click(btn_chargeTab);

		if (IsRateExistInFWB == true) {
			System.out.println(waitForElement(txt_iataRate).getAttribute("value"));
			if (waitForElement(txt_iataRate).getAttribute("value").split(".", 0).equals(Rate)) {

				System.out.println("AWB taken the Rate given in FWB successfully");

			}

		} else {

			String tmpRate = waitForElement(txt_iataRate).getAttribute("value");
			Assert.assertFalse(tmpRate.equals(Rate), "ERROR : AWB not taken System rate.");
		}
		return this;

	}

	public OPR026 listAndExecute(String stockType, String prefix, String awbno, String rateClass, String iataRate,
			String netCharge) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		// //click(div_sc);
		// enterKeys(txt_shipper, shipper);
		// enterKeys(txt_consignee, consignee);
		// selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_execute);

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		driver.switchTo().defaultContent();
		String status = null;
		while (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),
		// "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 listAndExecuteWithRatedCust(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String ratedCust, String... shipmentType) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		ratedCust = PropertyHandler.getPropValue(dataFilePath, ratedCust);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		enterKeys(txt_ratedCust, ratedCust);

		if (shipmentType.length != 0) {
			switch (shipmentType[0].toUpperCase()) {
			case "PP":
				selectByText(dropdown_paymentType, "PP");
				selectByText(dropdown_chargePaymentType, "PP");
				break;
			case "CC":
				selectByText(dropdown_paymentType, "CC");
				selectByText(dropdown_chargePaymentType, "CC");
				break;
			case "PC":
				selectByText(dropdown_paymentType, "PP");
				selectByText(dropdown_chargePaymentType, "CC");
				break;
			}
		}

		click(btn_execute);

		driver.switchTo().defaultContent();
		while (verifyElementPresent(yesBtn)) {
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			Assert.assertEquals(
					waitForElementVisible(txt_pymnt_txt_custCode).getAttribute("value").trim().toUpperCase(),
					ratedCust.toUpperCase(), "The Bill to party doesn't match for the AWB No: " + awbno);
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		driver.switchTo().defaultContent();
		String status = null;
		while (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),
		// "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * execute AWB with shipper and consignee
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 */
	public OPR026 executeAWB(String stockType, String prefix, String awbno, String rateClass, String iataRate,
			String netCharge, String shippr, String consignee) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		shippr = PropertyHandler.getPropValue(dataFilePath, shippr);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}

		enterKeys(txt_shipper, shippr + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);

		selectByText(dropdown_sci, "T1");
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_execute);

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}
		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 executeAWBwithAdjustedWt(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String shippr, String consignee) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		shippr = PropertyHandler.getPropValue(dataFilePath, shippr);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}

		enterKeys(txt_shipper, shippr + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);

		selectByText(dropdown_sci, "T1");
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_execute);

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}
		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * execute AWB with shipper and consignee with diff wt and vol
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 */
	public OPR026 saveAWBByChangingWtAndVol(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String shippr, String consignee, String wt, String vol) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		shippr = PropertyHandler.getPropValue(dataFilePath, shippr);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		String status = null;

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		minWait();
		// click(div_sc);
		if (verifyElementVisible(txt_shipper)) {
			enterKeys(txt_shipper, shippr + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shippr + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, "T1");

		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_save);
		driver.switchTo().defaultContent();

		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);
		/*
		 * if (stockType.equals("CASH")) {
		 * 
		 * enterKeys(txt_remarks, "Payment Remarks"); click(btn_pymnt_ok);
		 * click(btn_finalize); minWait();
		 * Assert.assertFalse(waitForElement(txt_pymntAdvice
		 * ).getAttribute("value").isEmpty(), "ERROR : Payment not successful");
		 * click(btn_pymnt_close); minWait(); } maxWait();
		 * driver.switchTo().defaultContent(); if
		 * (waitForElement(msg_popup).getText
		 * ().contains("different from the booked")) { click(yesBtn); }
		 * driver.switchTo().defaultContent(); while
		 * (verifyElementPresent(yesBtn)) { status =
		 * waitForElement(msg_executed).getText(); click(yesBtn); }
		 * 
		 * waitForFrameAndSwitch(FRAME);
		 * Assert.assertTrue(status.contains("executed"),
		 * "ERROR : AWB not Executed."); minWait();
		 */
		return this;
	}

	/**
	 * lists a AWB and As IS executes without entering shipper and consignee
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author Arun A-7681
	 */
	public OPR026 listAndAsIsExecute(String stockType, String prefix, String awbno, String rateClass, String iataRate,
			String netCharge) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		// //click(div_sc);
		// enterKeys(txt_shipper, shipper);
		// enterKeys(txt_consignee, consignee);
		// selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		minWait();
		click(btn_asIs);
		minWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * Method to list an existing AWB, rpovide rate details and click AS-IS
	 * Execute Method expects for an error message and compares the error
	 * message with parameter passed
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @param errorMsg
	 * @return
	 * @author a-7868 Krishna <15/03/2018>
	 */
	public OPR026 listAndAsIsExecuteWithError(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String errorMsg) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		// //click(div_sc);
		// enterKeys(txt_shipper, shipper);
		// enterKeys(txt_consignee, consignee);
		// selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		minWait();
		click(btn_asIs);
		minWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(yesBtn))
				click(yesBtn);
			else
				break;
		}
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_errorMsg).getText().toLowerCase().contains(errorMsg),
				"ERROR : Incorrect error message.");
		minWait();
		return this;
	}

	/**
	 * Method to list an existing AWB, rpovide rate details and click Execute
	 * Method expects for an error message and compares the error message with
	 * parameter passed
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @param errorMsg
	 * @return
	 * @author a-7868 Krishna <15/03/2018>
	 */
	public OPR026 listAndExecuteWithError(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String errorMsg) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		// //click(div_sc);
		// enterKeys(txt_shipper, shipper);
		// enterKeys(txt_consignee, consignee);
		// selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		minWait();
		click(btn_execute);
		minWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(yesBtn))
				click(yesBtn);
			else
				break;
		}
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_errorMsg).getText().contains(errorMsg),
				"ERROR : Incorrect error message.");
		minWait();
		return this;
	}

	/**
	 * lists a AWB and As IS executes and navigate to payment screen even credit
	 * agent
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author A-6545 17/2/2018
	 */
	public OPR026 AsIsExecute_paymentdet(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String ActualAmount) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(chk_verifyExecute);
		check(chk_verifyExecute);
		minWait();

		click(btn_asIs);

		minWait();
		String value = waitForElementVisible(txt_ActualAmount).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ActualAmount, value);

		enterKeys(txt_remarks, "Payment Remarks");
		click(btn_pymnt_ok);
		click(btn_finalize);
		minWait();
		Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
				"ERROR : Payment not successful");
		click(btn_pymnt_close);
		minWait();

		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * lists a AWB and executes and navigate to payment screen even credit agent
	 * save as draft
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author A-6545 23/2/2018
	 */
	public OPR026 Execute_paymentdet_savasdraft(String stockType, String prefix, String awbno, String rateClass,
			String iataRate, String netCharge, String ActualAmount) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(chk_verifyExecute);
		check(chk_verifyExecute);
		minWait();
		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();
		click(btn_execute);

		minWait();
		String value = waitForElementVisible(txt_ActualAmount).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ActualAmount, value);

		enterKeys(txt_remarks, "Payment Remarks");
		// enterKeys(txt_Currencyv, "USD");

		click(btn_pymnt_ok);
		click(pymnt_btn_Save);
		minWait();
		click(btn_pymnt_close);
		minWait();

		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * lists a AWB and As IS executes and navigate to payment screen even credit
	 * agent
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author A-6545 19/2/2018
	 */
	public OPR026 Execute_paymentdet(String stockType, String prefix, String awbno, String rateClass, String iataRate,
			String netCharge, String ActualAmount) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(chk_verifyExecute);
		check(chk_verifyExecute);
		minWait();
		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();
		click(btn_execute);

		minWait();
		String value = waitForElementVisible(txt_ActualAmount).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ActualAmount, value);

		enterKeys(txt_remarks, "Payment Remarks");
		// enterKeys(txt_Currencyv, "USD");

		click(btn_pymnt_ok);
		click(btn_finalize);
		minWait();
		Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
				"ERROR : Payment not successful");
		click(btn_pymnt_close);
		minWait();

		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * lists a AWB and As IS executes and navigate to payment screen even credit
	 * agent
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author A-6545 19/2/2018
	 */
	public OPR026 Execute_Error(String stockType, String prefix, String awbno, String rateClass, String iataRate,
			String netCharge, String ActualAmount) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(chk_verifyExecute);
		check(chk_verifyExecute);
		minWait();
		click(btn_btnCalculateCharge);
		minWait();
		click(btn_btnComputeTotal);
		minWait();
		click(btn_execute);
		minWait();

		Assert.assertTrue(waitForElement(Generic_info_error).getText().contains("No active cash draw exist"));

		minWait();
		return this;
	}

	public OPR026 checkIfAWBExecuted(String key_awbNoPrefix, String key_awb) {
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awb);
		list(awbNoPrefix, awbNo);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		return this;
	}

	public OPR026 checkIfAWBNotExecuted(String key_awbNoPrefix, String key_awb) {
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awb);
		list(awbNoPrefix, awbNo);
		minWait();
		Assert.assertFalse(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB is Executed.");
		return this;
	}

	// modified by A-8254
	public OPR026 DeleteAWBAfterExecution(String awbPre, String awbNo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);
		minWait();
		// driver.switchTo().defaultContent();

		click(btn_Delete);
		driver.switchTo().defaultContent();
		Assert.assertTrue(waitForElement(msg_popup).getText().contains("Do you want to delete the AWB?"));
		click(yesBtn);
		Assert.assertTrue(waitForElement(msg_popup).getText().contains("Do you want to cancel booking?"));
		click(yesBtn);
		minWait();
		waitForNewWindow(2);

		switchToSecondWindow();
		enterKeys(waitForElement(Irregularity_Inbx_irregularityCode), "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");

		click(Irregularity_Btn_OKbutton);

		switchToFirstWindow();
		Assert.assertTrue(waitForElement(msg_popup).getText().contains("deleted successfully"));

		click(btn_genericOk);
		minWait();

		waitForFrameAndSwitch(FRAME);
		return this;
	}

	private void list(String prefix, String awbno) {

		click(btn_clear);
		minWait();
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
	}

	/**
	 * Created by A-7605 on 15-3-18 This method expand the shipper consignee
	 * wrapper
	 */
	private void expandShipperConsigneeWrapper() {

		while (true) {
			minWait();
			if (driver.findElement(txt_shipper).isDisplayed())
				break;
			click(div_sc);
			waitForElement(txt_shipper);
		}
	}

	/**
	 * Saves the listed AWB as consol shipment
	 * 
	 * @param awbPre
	 * @return an instance of OPR029 screen
	 * @author Arun A-7681 on 26/12
	 */

	public OPR026 saveAsConsol(String awbPre, String awbNo, String shipper, String consignee, String sci) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		list(awbPre, awbNo);
		check(chkbx_consol);
		check(chkbx_HAWBFinalized);

		expandShipperConsigneeWrapper();
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, sci);

		click(btn_save);

		driver.switchTo().defaultContent();

		while (true) {
			if (verifyElementPresent(yesBtn)) {
				minWait();
				if (waitForElement(msg_popup).getText().contains("Do you want to capture houses?")) {
					maxWait();
					click(noBtn);
				} else
					click(yesBtn);
				minWait();

			} else
				break;
		}
		if (verifyElementPresent(yesBtn)) {
			if (waitForElement(msg_popup).getText().contains("Do you want to capture houses?")) {
				maxWait();
				click(noBtn);
			}
		}
		Assert.assertTrue(waitForElement(msg_popup).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		return this;
	}

	/**
	 * Method to list already saved AWB, provide charges@accounting details and
	 * execute
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awbNo
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author A-7868 Krishna <22/02/2018>
	 */
	public OPR026 listAndExecuteWithCharges(String stockType, String awbPre, String awbNo, String rateClass,
			String iataRate, String netCharge) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(awbPre, awbNo);

		click(btn_chargeTab);
		selectByText(dropdown_rateClass, "Q");
		enterKeys(txt_iataRate, "100");
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, "1200");

		click(btn_execute);

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		String status = "";
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();

		return this;
	}

	public OPR026 createAwbAndSave_withProduct(String stockType, String awbPre, String awbNo, String origin,
			String dest, String carrCode, String sci, String scc, String agentCode, String shipper, String consignee,
			String pcs, String wt, String commCode, String product) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		product = PropertyHandler.getPropValue(dataFilePath, product);
		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);
		maxWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_product, product);

		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		minWait();
		click(btn_save);
		// click(btn_save);

		driver.switchTo().defaultContent();

		if (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);

		Assert.assertTrue(waitForElement(txt_agentCode).getAttribute("value").trim().equalsIgnoreCase(agentCode),
				"The AWB is not saved correctly");

		click(btn_clear);

		return this;
	}

	public OPR026 changeProduct_errorChck(String awbPre, String awbNo, String product) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		product = PropertyHandler.getPropValue(dataFilePath, product);

		enterKeys(txt_awb, awbPre);

		enterKeys(txt_awb, awbNo);

		click(btn_list);

		enterKeys(txt_product, product);

		click(btn_save);

		Assert.assertTrue(waitForElement(info_errorMsg).getText()
				.contains("Invalid Product Code-Priority-Transportation Mode-Primary SCC combination"));
		return this;

	}

	/**
	 * Creates a new AWB from the stock and saves it
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @param carrCode
	 * @param sci
	 * @param scc
	 * @param shipper
	 * @param consignee
	 * @param pcs
	 * @param wt
	 * @param commCode
	 * @return
	 */
	public OPR026 createAwbAndSave(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode, boolean isConsol, String... volmetricWt) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);

		maxWait();
		if (isConsol) {
			check(chkbx_consol);
		}
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_product, "");

		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		if (volmetricWt.length != 0) {
			setDimensions(pcs, wt, volmetricWt[0]);
		}

		minWait();
		click(btn_save);
		// click(btn_save);

		driver.switchTo().defaultContent();

		if (isConsol) {

			while (true) {
				if (waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?")) {

					Assert.assertTrue(
							waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
					click(noBtn);
					break;
				} else
					click(yesBtn);
			}

		}

		if (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);

		Assert.assertTrue(waitForElement(txt_agentCode).getAttribute("value").trim().equalsIgnoreCase(agentCode),
				"The AWB is not saved correctly");

		click(btn_clear);

		return this;
	}

	/**
	 * Sets the dimensions for the commodity
	 * 
	 * @param pcs
	 * @param wt
	 * @author A-7681
	 */

	private void setDimensions(String pcs, String wt, String volmetricWt) {

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, pcs);
		enterKeys(txt_dimWt, wt);
		enterKeys(txt_dimLength, "100");
		enterKeys(txt_dimWidth, "100");
		enterKeys(txt_dimHeight, "100" + Keys.TAB);
		int vol = Integer.parseInt(waitForElementVisible(txt_dimVol).getAttribute("value"));

		String volWt = String.valueOf(Math.round((double) (vol * 1000 / 6)));
		PropertyHandler.setPropValue(dataFilePath, volmetricWt, volWt);

		click(btn_dimOk);
		if (getNumberOfWindows() == 2 && verifyElementPresent(yesBtn)) {
			click(yesBtn);
			minWait();

		}
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

	}

	/**
	 * Creates a new AWB from the stock and executes it
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @param carrCode
	 * @param sci
	 * @param scc
	 * @param shipper
	 * @param consignee
	 * @param pcs
	 * @param wt
	 * @param commCode
	 * @return
	 * @author a-7681
	 */
	public OPR026 createAwbAndExecute(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode, String rateClass, String netCharge, String iataRate, boolean verifyAndExecute) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		getAWB(stock, awbPre, awbNo);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_agentName, agentCode);
		enterKeys(txt_product, "");

		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		minWait();

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		if (verifyAndExecute) {
			scrollToView(chk_verifyExecute);
			check(chk_verifyExecute);
		}

		click(btn_execute);

		if (stockType.equals("CASH") || verifyAndExecute) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;

	}

	public OPR026 createAwbAndSaveDGShipment(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode, boolean isConsol, boolean isDGCapture) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);

		if (isConsol) {
			check(chkbx_consol);
		}
		String tempDate = waitForElement(txt_DropoffTime).getAttribute("value");

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_product, "");
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		click(btn_save);

		driver.switchTo().defaultContent();

		if (isConsol) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
			click(noBtn);
		}
		if (isDGCapture) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains(
					"Dangerous goods details not captured for the SCC DGR. Do you want to proceed saving the AWB without capturing the DGR details?"));
			click(yesBtn);
			minWait();
		} else {
			click(yesBtn);
		}
		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		return this;
	}

	public OPR026 createAwbAndSaveWithError(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode, String errorMsg) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);
		maxWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_product, "");

		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		minWait();
		click(btn_save);
		// click(btn_save);

		driver.switchTo().defaultContent();
		while (verifyElementPresent(yesBtn)) {
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_errorMsg).getText().contains(errorMsg), "Error Message mismatch");

		return this;
	}

	/**
	 * Created by a-7605 on 03/01/2017
	 */

	public OPR026 captureDetails(String awbPrefix, String awbNo, String shipper, String consignee, String pcs,
			String weight, String commodityCode) {

		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);

		list(awbPrefix, awbNo);
		minWait();
		waitForElement(btn_chargeTab);
		enterKeys(txt_agentCode, shipper + Keys.TAB);
		expandShipperConsigneeWrapper();
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		selectByText(dropdown_sci, "T1");
		// enterKeys(txt_product, "GENERAL CARGO");
		click(btn_save);
		driver.switchTo().defaultContent();
		click(waitForElement(okBtn));
		if (verifyElementPresent(okBtn)) {

			click(okBtn);
		}

		waitForFrameAndSwitch(FRAME);
		return this;
	}

	/**
	 * @param awbPre
	 * @param awbNo
	 * @return Author A-6784
	 **/
	public OPR026 verifyAdditionalInfo(String awbPre, String awbNo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		list(awbPre, awbNo);
		minWait();
		click(btn_AdditionalInfoTab);
		minWait();
		// waitForElement(info_source).getText().contains("FWB");
		System.out.println(waitForElement(info_source).getText());
		// Assert.assertTrue(waitForElement(info_source).getText().contains("RA"),
		// "ERROR : Data not matching");
		click(btn_clear);
		return this;

	}

	/**
	 * Gets the new AWB from the stock and stores it in the data file
	 * corresponding to key awb
	 * 
	 * @param stock
	 * @param awbPre
	 * @param awb
	 * @return
	 * @author A-7681
	 */
	private String getAWB(StockType stock, String awbPre, String awb) {

		String awbNo;

		/*
		 * while (true) { awbNo = BizUtility.createAWBNum(stock); minWait();
		 * enterKeys((txt_prefix), awbPre); enterKeys(txt_awb, awbNo);
		 * click(btn_list); maxWait(); driver.switchTo().defaultContent(); if
		 * (verifyElementPresent(yesBtn)) {
		 * 
		 * if (waitForElement(info_genericMsg).getText().contains("executed")) {
		 * click(noBtn); waitForFrameAndSwitch(FRAME); continue; }
		 * click(yesBtn); waitForFrameAndSwitch(FRAME); break; }
		 * waitForFrameAndSwitch(FRAME); click(btn_clear); }
		 * PropertyHandler.setPropValue(dataFilePath, awb, awbNo);
		 * 
		 * /* while (true) {
		 * 
		 * awbNo = BizUtility.createAWBNum(stock); enterKeys((txt_prefix),
		 * awbPre); enterKeys(txt_awb, awbNo); click(btn_list); minWait();
		 * driver.switchTo().defaultContent(); if (verifyElementPresent(yesBtn))
		 * {
		 * 
		 * if (waitForElement(info_genericMsg).getText().contains("executed")) {
		 * click(noBtn); waitForFrameAndSwitch(FRAME); continue; }
		 * click(yesBtn); waitForFrameAndSwitch(FRAME); break; }
		 * waitForFrameAndSwitch(FRAME); click(btn_clear); }
		 * PropertyHandler.setPropValue(dataFilePath, awb, awbNo); >>>>>>>
		 * .r2473
		 * 
		 * <<<<<<< .mine // return awbNo; =======
		 * 
		 * return awbNo; >>>>>>> .r2473
		 */

		// Above method commented and added below method, to accomodate new
		// change in OPR026 screen (Do ypu want to create new WAB dialogue has
		// been removed)

		while (true) {

			awbNo = BizUtility.createAWBNum(stock);
			enterKeys(txt_prefix, awbPre);
			enterKeys(txt_awb, awbNo);
			click(btn_list);
			minWait();
			driver.switchTo().defaultContent();

			if (verifyElementPresent(info_genericMsg) && driver.findElement(info_genericMsg).getText().trim()
					.equals("AWB does not exist.Do you want to capture?")) {
				click(yesBtn);
				waitForFrameAndSwitch(FRAME);
				break;
			}

			if (verifyElementPresent(noBtn)) {
				click(noBtn);

			} else if (verifyElementPresent(okBtn)) {
				click(okBtn);
			}
			minWait();
			waitForFrameAndSwitch(FRAME);
			click(btn_clear);
			minWait();
		}
		PropertyHandler.setPropValue(dataFilePath, awb, awbNo);
		return awbNo;
	}

	public OPR026 verifyMarktRateCardAllInRate(String awbPre, String awb, String RateCardId,
			String AttributeAllInRate) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		RateCardId = PropertyHandler.getPropValue(dataFilePath, RateCardId);

		enterKeys((txt_prefix), awbPre);
		enterKeys(txt_awb, awb);
		click(btn_list);
		minWait();
		click(btn_chargeTab);
		minWait();
		scrollToView(txt_markRateCard);
		System.out.println("Expected Rate card :" + RateCardId);
		System.out.println("Actual Ratecard Id : " + waitForElementVisible(txt_markRateCard).getAttribute("value"));
		Assert.assertTrue(waitForElementVisible(txt_markRateCard).getAttribute("value").contains(RateCardId));

		scrollToView(txt_OCDCpp);
		minWait();
		if (AttributeAllInRate.equalsIgnoreCase("OCDC")) {

			Assert.assertTrue(waitForElementVisible(txt_OCDCpp).getAttribute("value").equals("0"));

		} else if (AttributeAllInRate.equalsIgnoreCase("OCDA")) {

			Assert.assertTrue(waitForElementVisible(txt_OCDApp).getAttribute("value").equals("0"));
		} else if (AttributeAllInRate.equalsIgnoreCase("OCDA and OCDA")) {

			Assert.assertTrue(waitForElementVisible(txt_OCDApp).getAttribute("value").equals("0"));
			Assert.assertTrue(waitForElementVisible(txt_OCDCpp).getAttribute("value").equals("0"));
		} else if (AttributeAllInRate.equalsIgnoreCase("Null")) {

			Assert.assertTrue(waitForElementVisible(txt_OCDApp).getAttribute("value").equals("0"));
			Assert.assertTrue(waitForElementVisible(txt_OCDCpp).getAttribute("value").equals("0"));
			Assert.assertTrue(waitForElementVisible(txt_totalPP).getAttribute("value").equals("0"));

		}

		click(btn_clear);

		return this;

	}

	public OPR026 storeUBR(String UBRNo, String awbPre, String awb) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		list(awbPre, awb);
		minWait();

		String ubr = waitForElement(txt_ubrNo).getAttribute("value");
		System.out.println(ubr);
		PropertyHandler.setPropValue(dataFilePath, UBRNo, ubr);
		return this;

	}

	/**
	 * Relist the AWB already saved and verify the origin and destination and
	 * pieces values
	 * 
	 * @param stockType
	 * @param prefix
	 * @param awbno
	 * @param key_orgin
	 * @param key_destination
	 * @param key_pcs
	 * @return
	 * @author A-6836
	 */

	public OPR026 RelistandVerify(String stockType, String prefix, String awbno, String key_orgin,
			String key_destination, String key_pcs) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String orgin = PropertyHandler.getPropValue(dataFilePath, key_orgin);

		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pcs);
		list(prefix, awbno);

		minWait();

		Assert.assertTrue(waitForElement(txt_origin).getAttribute("value").equals(orgin), "ERROR : Data not matching");
		Assert.assertTrue(waitForElement(txt_dest).getAttribute("value").equals(destination),
				"ERROR : Data not matching");
		Assert.assertTrue(waitForElement(txt_commPcs).getAttribute("value").equals(pieces),
				"ERROR : Data not matching");
		return this;
	}

	/**
	 * Relist the AWB already saved and verify the origin and destination and
	 * pieces values
	 * 
	 * @param prefix
	 * @param awbno
	 * @return
	 * @author A-6784
	 */

	public OPR026 VerifyModifiedDataAfterFWB(String ShipperCode, String ConsigneeCode, String prefix, String awbno) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		ShipperCode = PropertyHandler.getPropValue(dataFilePath, ShipperCode);
		ConsigneeCode = PropertyHandler.getPropValue(dataFilePath, ConsigneeCode);

		list(prefix, awbno);

		minWait();
		expandShipperConsigneeWrapper();

		Assert.assertTrue(waitForElement(txt_shipper).getAttribute("value").equals(ShipperCode),
				"ERROR : Data not matching");
		Assert.assertTrue(waitForElement(txt_consignee).getAttribute("value").equals(ConsigneeCode),
				"ERROR : Data not matching");

		return this;
	}

	/**
	 * Method to list an awb and verify if details are captured (pcs and wt)
	 * 
	 * @param prefix
	 * @param awbno
	 * @param pcs
	 * @param wt
	 * @return
	 * @author a-7868 Krishna <06/03/2018>
	 */
	public OPR026 verifyAWBCaptured(String prefix, String awbno, String pcs, String wt) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		list(prefix, awbno);
		minWait();
		Assert.assertTrue(waitForElement(txt_shipmentDetailsPcs).getAttribute("value").equals(pcs),
				"ERROR : Data not matching");
		Assert.assertTrue(waitForElement(txt_shipmentDetailsWeight).getAttribute("value").equals(wt),
				"ERROR : Data not matching");
		return this;
	}

	public OPR026 modifyAWB(String prefix, String awbno, String key_modifiedpcs) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		key_modifiedpcs = PropertyHandler.getPropValue(dataFilePath, key_modifiedpcs);

		list(prefix, awbno);

		minWait();
		enterKeys(txt_commPcs, key_modifiedpcs);

		click(btn_save);

		driver.switchTo().defaultContent();
		minWait();

		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);
		waitForFrameAndSwitch(FRAME);
		click(btn_clear);
		return this;
	}

	/**
	 * 
	 * @param awbPrefix
	 * @param awbNo
	 * @param embargoId
	 * @return
	 */
	public OPR026 execution_Embargo(String awbPrefix, String awbNo, String agent, String embargoId) {

		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		embargoId = PropertyHandler.getPropValue(dataFilePath, embargoId);
		agent = PropertyHandler.getPropValue(dataFilePath, agent);
		enterKeys((txt_prefix), awbPrefix);
		enterKeys(txt_awb, awbNo);
		click(btn_list);
		maxWait();

		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, agent + Keys.TAB);
		enterKeys(txt_consignee, agent + Keys.TAB);

		minWait();
		selectByText(dropdown_sci, "T1");
		selectByText(txt_servicecargo, "Free of Charge");
		click(btn_execute);
		minWait();
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		waitForElement(btn_RecoEmbargoClose).isEnabled();
		String tmpTblData = waitForElement(tbl_RecoPopup).getText();
		Assert.assertTrue(tmpTblData.contains(embargoId), "Error:Embargo restriction failed");
		click(btn_RecoEmbargoClose);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(btn_clear);
		return this;

	}

	/**
	 * 
	 * @param awbPrefix
	 * @param awbNo
	 * @param embargoId
	 * @return
	 */
	public OPR026 saveShipmentHavingEmbargo(String awbPrefix, String awbNo, String agentCode, String embargoId) {

		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		embargoId = PropertyHandler.getPropValue(dataFilePath, embargoId);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);

		enterKeys((txt_prefix), awbPrefix);
		enterKeys(txt_awb, awbNo);
		click(btn_list);
		maxWait();

		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, agentCode + Keys.TAB);
		enterKeys(txt_consignee, agentCode + Keys.TAB);

		minWait();
		selectByText(dropdown_sci, "T1");
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		waitForElement(btn_RecoEmbargoClose).isEnabled();
		String tmpTblData = waitForElement(tbl_RecoPopup).getText();
		Assert.assertTrue(tmpTblData.contains(embargoId), "Error:Embargo restriction failed");
		click(btn_RecoEmbargoClose);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		click(btn_clear);
		return this;
	}

	public OPR026 captureShipmentHavingEmbargo(String stockType, String awbPre, String awbNo, String origin,
			String dest, String carrCode, String sci, String scc, String agentCode, String shipper, String consignee,
			String pcs, String wt, String key_Parametervalue, String key_RECO_Refnum, String key_EmbargoLevel,
			boolean isConsol) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		key_Parametervalue = PropertyHandler.getPropValue(dataFilePath, key_Parametervalue);
		key_RECO_Refnum = PropertyHandler.getPropValue(dataFilePath, key_RECO_Refnum);
		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);

		if (isConsol) {
			check(chkbx_consol);
		}
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, key_Parametervalue);

		click(btn_save);

		driver.switchTo().defaultContent();

		if (isConsol) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
			click(noBtn);
		}
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		waitForElement(btn_RecoEmbargoClose);
		Assert.assertTrue(waitForElement(info_RecoRefId).getText().contains(key_RECO_Refnum));

		if (key_EmbargoLevel.equals("Error")) {

			click(btn_RecoEmbargoClose);
			switchToFirstWindow();
			waitForFrameAndSwitch(FRAME);
			click(btn_clear);
			return this;

		} else if (key_EmbargoLevel.equals("Info")) {

			waitForElement(btn_RecoEmbargoContinue).isEnabled();
			click(btn_RecoEmbargoContinue);
			maxWait();
		} else if (key_EmbargoLevel.equals("Warning")) {

			waitForElement(btn_RecoEmbargoContinue).isEnabled();
			click(btn_RecoEmbargoContinue);
			maxWait();
		}
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		// waitForFrameAndSwitch(FRAME);

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);

		Assert.assertTrue(waitForElement(txt_agentCode).getAttribute("value").trim().equalsIgnoreCase(agentCode),
				"The Concsol checkbox is not selected");

		click(btn_clear);

		return this;
	}

	public OPR026 Exceute_EmbargoAWB(String prefix, String awbno, String stockType, String shipper, String consignee,
			String rateClass, String iataRate, String netCharge, String sci, String key_RECO_Refnum,
			String key_EmbargoLevel) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		key_RECO_Refnum = PropertyHandler.getPropValue(dataFilePath, key_RECO_Refnum);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_consignee, consignee);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		click(btn_execute);
		maxWait();
		// driver.switchTo().defaultContent();
		waitForNewWindow(2);// krishna
		switchToSecondWindow();// krishna
		waitForElement(btn_RecoEmbargoContinue);
		// Assert.assertTrue(waitForElement(info_RecoRefId).getText().contains(key_RECO_Refnum));

		if (key_EmbargoLevel.equals("Error")) {
			click(btn_RecoEmbargoClose);
			switchToFirstWindow();// krishna
			waitForFrameAndSwitch(FRAME);
			click(btn_clear);
			return this;

		} else if (key_EmbargoLevel.equals("Info")) {
			click(btn_RecoEmbargoContinue);
			switchToFirstWindow();// krishna
			waitForFrameAndSwitch(FRAME);
			maxWait();
		} else if (key_EmbargoLevel.equals("Warning")) {
			click(btn_RecoEmbargoContinue);
			switchToFirstWindow();// krishna
			waitForFrameAndSwitch(FRAME);
			maxWait();
		}

		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	public OPR026 CaptureShipmentWithWALKIN(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode, boolean isConsol) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);

		if (isConsol) {
			check(chkbx_consol);
		}
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		expandShipperConsigneeWrapper();
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		click(btn_save);

		driver.switchTo().defaultContent();

		if (isConsol) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
			click(noBtn);
		}
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);

		Assert.assertTrue(waitForElement(txt_agentCode).getAttribute("value").trim().equalsIgnoreCase(agentCode),
				"The Concsol checkbox is not selected");

		click(btn_clear);

		return this;
	}

	public OPR026 checkHouses(String awbPre, String awbNo, String house1, String house2) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		list(awbPre, awbNo);
		List<String> values = getAllValues(dropDown_houses);

		Assert.assertTrue(values.contains((String) house1), "The house details have not been updated");
		Assert.assertTrue(values.contains((String) house2), "The house details have not been updated");
		return this;
	}

	public OPR026 executeAWB_PaymentNavigation(String awbPrefix, String awbNo, String agentCode) {
		String agent;
		String notificationMessage;
		list(awbPrefix, awbNo);
		click(btn_execute);
		minWait();
		agent = waitForElement(txt_pymnt_txt_custCode).getAttribute("value");
		Assert.assertTrue(agent.equals(agentCode), "Actual agent code is'" + agent + "' and expected is '" + agentCode);
		waitForElement(txt_remarks);
		enterKeys(txt_remarks, "Payment Remarks");
		click(btn_pymnt_ok);
		click(btn_finalize);
		minWait();
		Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
				"ERROR : Payment not successful");
		click(btn_pymnt_close);
		minWait();
		driver.switchTo().defaultContent();
		notificationMessage = waitForElement(msg_popup).getText();
		Assert.assertTrue(notificationMessage.contains("is executed. Do you want to relist?"),
				"Actual notification message is '" + notificationMessage
						+ "' and expected message is 'is executed. Do you want to relist?'");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 executeAWB_NonPaymentNavigation(String awbPrefix, String awbNo) {
		String notificationMessage;
		list(awbPrefix, awbNo);
		click(btn_execute);
		driver.switchTo().defaultContent();
		notificationMessage = waitForElement(msg_popup).getText();
		Assert.assertTrue(notificationMessage.contains("is executed. Do you want to relist?"),
				"Actual notification message is '" + notificationMessage
						+ "' and expected message is 'is executed. Do you want to relist?'");
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 VerifyHouses(String awbPrefix, String awbNo) {

		list(awbPrefix, awbNo);
		minWait();
		click(btn_HAWB);
		waitForElement(info_HouseNo1).getText().contains("H1");
		waitForElement(info_HouseNo2).getText().contains("H2");
		click(btn_CloseCaptureHouse);
		minWait();
		click(btn_clear);
		return this;

	}

	public OPR026 captureAWB(String stockType, String awbPrefix, String awbNo, String carrierCode, String origin,
			String destination, String scc, String agentCode, String shipperCode, String consigneeCode, String pcs,
			String weight, String volume, String commodityCode) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);

		StockType stock = StockType.valueOf(stockType);
		String notificationMessage;
		String currentStockAWBNo;
		getAWB(stock, awbPrefix, awbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_scc, scc);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		expandShipperConsigneeWrapper();
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);
		selectByText(dropdown_sci, "T1");
		waitForElement(txt_product).clear();
		click(btn_save);
		driver.switchTo().defaultContent();
		notificationMessage = waitForElement(msg_popup).getText();
		Assert.assertTrue(notificationMessage.contains("saved successfully"), "Actual notification message is '"
				+ notificationMessage + "' and expected message is 'saved successfully'");
		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	/**
	 * changes the pcs and wt of a reopened AWB and Executes it
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param pcs
	 * @param wt
	 * @return
	 * @author Arun A-7681
	 */
	public OPR026 changePcsWtandAsIsExecute(String awbPre, String awbNo, String pcs, String wt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		list(awbPre, awbNo);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);

		click(btn_asIs);
		String status = "";
		driver.switchTo().defaultContent();

		while (true) {
			if (verifyElementPresent(yesBtn)) {
				status = waitForElement(msg_executed).getText();
				click(yesBtn);
			} else
				break;
		}
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * reopens a Executed awb
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @return
	 * @author Arun A-7681
	 */
	public OPR026 reOpen(String awbPre, String awbNo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);
		minWait();
		click(btn_reOpen);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Reopened"), "The AWB is not reopened");

		return this;
	}

	/**
	 * reopens a Executed awb and verifies save button is enabled
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @return
	 * @author Arun A-7681
	 */
	public OPR026 reOpenAndVerifySaveBtn(String awbPre, String awbNo, boolean saveBtnEnabled) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);
		minWait();
		click(btn_reOpen);
		minWait();
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Reopened"), "The AWB is not reopened");
		if (saveBtnEnabled) {
			Assert.assertTrue(waitForElementVisible(btn_save).isEnabled(), "The save button is not anabled in OPR026");
		} else {
			Assert.assertFalse(waitForElementVisible(btn_save).isEnabled(), "The save button is not anabled in OPR026");
		}

		return this;
	}

	/**
	 * Clears the form
	 */
	public OPR026 clear() {
		minWait();
		click(btn_clear);
		return this;
	}

	/**
	 * Method to close current page and return to Homepae returns the instance
	 * of Homepage
	 * 
	 * @return
	 * @author a-7868
	 */
	public HomePage close() {

		click(btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}
		return new HomePage(driver, dataFileName, test);
	}

	/**
	 * Created by A-7605 on 9-2-18 This method will execute AWB regardless if
	 * the agent is credit or cash
	 * 
	 * @param prefix
	 * @param awbno
	 * @param shipper
	 * @param consignee
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @param sci
	 * @return
	 */
	public OPR026 executeAWB_CashOrCredit(String prefix, String awbno, String shipper, String consignee,
			String rateClass, String iataRate, String netCharge, String sci) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		expandShipperConsigneeWrapper();
		enterKeys(txt_shipper, shipper);
		enterKeys(txt_shipper, Keys.TAB);
		minWait();
		enterKeys(txt_consignee, consignee);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		List<WebElement> dueCarrierCheckboxes = getWebElements(chkbox_dueCarrier);
		try {
			for (int i = 0; i < dueCarrierCheckboxes.size(); i++) {
				scrollToView(dueCarrierCheckboxes.get(i));
				check(dueCarrierCheckboxes.get(i));
			}
		} catch (Exception e) {

		}
		click(btn_execute);
		minWait();
		if (driver.getTitle().contains("Generate Payment Advice")) {
			minWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(yesBtn)) {
				click(yesBtn);
				minWait();
			}
			waitForFrameAndSwitch(FRAME);
			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		String status = "";
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * Created by A-7605 on 14-2-18 This method will as is execute an already
	 * captured awb regradless if its cash or credit
	 * 
	 * @param prefix
	 * @param awbno
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 */
	public OPR026 listAndAsIsExecute_CashOrCredit(String prefix, String awbno, String rateClass, String iataRate,
			String netCharge) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);
		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		minWait();
		click(btn_asIs);
		minWait();
		if (driver.getTitle().contains("Generate Payment Advice")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
			minWait();
		}
		String status = waitForElement(msg_popup).getText();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		minWait();
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),
		// "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();
		return this;
	}

	/**
	 * This method will capture AWB with Consol and HAWB finalized checkboxes
	 * ticked
	 * 
	 * @param stockType
	 * @param awbPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param scc
	 * @param agentCode
	 * @param shipperCode
	 * @param consigneeCode
	 * @param pcs
	 * @param weight
	 * @param volume
	 * @param commodityCode
	 * @return
	 */
	public OPR026 captureConsolAWB(String stockType, String awbPrefix, String awbNo, String carrierCode, String origin,
			String destination, String scc, String agentCode, String shipperCode, String consigneeCode, String pcs,
			String weight, String volume, String commodityCode) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);

		StockType stock = StockType.valueOf(stockType);
		String notificationMessage;
		getAWB(stock, awbPrefix, awbNo);
		minWait();
		check(chkbx_consol);
		check(chkbx_HAWBFinalized);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		expandShipperConsigneeWrapper();
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);
		click(btn_save);
		driver.switchTo().defaultContent();
		notificationMessage = waitForElement(msg_popup).getText();
		Assert.assertTrue(notificationMessage.contains("saved successfully"), "Actual notification message is '"
				+ notificationMessage + "' and expected message is 'saved successfully'");
		while (true) {
			if (verifyElementPresent(noBtn)) {

				if (waitForElement(msg_popup).getText().contains("Do you want to capture houses?")) {
					click(noBtn);
					break;
				} else
					click(yesBtn);
				minWait();

			}
		}
		minWait();
		click(waitForElement(okBtn));
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	// created by A-8254

	public OPR026 SaveSimpleTemplate(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String templateValue) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);

		String awbNo;
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_prefix), awbPre);
		enterKeys(txt_awb, awbNo);
		click(btn_list);
		minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_genericMsg).getText().contains("AWB does not
		// exist.Do you want to capture?");
		// click(yesBtn);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		minWait();
		click(btn_Select_SaveTemplate);
		minWait();
		String templateName = waitForElement(txt_Save_template_As).getAttribute("value");
		Assert.assertNotEquals("", templateName);
		String templateDescription = waitForElement(txt_Brief_Description).getAttribute("value");
		Assert.assertNotEquals("", templateDescription);
		String templateName2 = "templateinOPR026";
		enterKeys(txt_Save_template_As, templateName2);
		click(btn_Template_Save);
		verifyTemplatePresentInList(templateName2, templateValue);

		return this;
	}

	private OPR026 verifyTemplatePresentInList(String templateName, String templateValue) {

		int count = 0;
		boolean flag = false;

		List<WebElement> listElement;

		listElement = driver.findElements(By.xpath("//*[text()='Saved Templates']/following::a"));

		count = listElement.size();

		for (int i = 1; i < count + 1; i++) {
			String listValue2 = driver.findElement(By.xpath("//*[text()='Saved Templates']/following::a[" + i + "]"))
					.getText();
			// //*[text()='Saved Templates']/following::a[3]

			// String
			// listValue2=waitForElement(list_Template).findElement(By.xpath("["+i+"]")).getText();
			if (listValue2.equalsIgnoreCase(templateName)) {
				System.out.println("the tempate found");
				flag = true;
				break;
			}

		}
		if (flag == false) {
			System.out.println("not found");
			Assert.assertEquals(flag, true);

		}

		PropertyHandler.setPropValue(dataFilePath, templateValue, templateName);
		return this;
	}

	/**
	 * @param prefix
	 * @param awbno
	 * @return
	 * @author A-8260
	 */
	public OPR026 executeAWB_ErrorVerification(String prefix, String awbno) {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		System.out.println(prefix);
		System.out.println(awbno);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		// list(prefix, awbno);
		minWait();
		minWait();
		click(btn_execute);
		minWait();
		Assert.assertTrue(waitForElement(msg_blk_errors).getText().contains("Blocked for security reasons"),
				"Blocked for security reasons message validation successful");
		// Assert.assertTrue(waitForElement(msg_blk_errors).getText().contains("blocked
		// for security reasons"),
		// "Blocked for security reasons message validation successful");
		return this;
	}

	/**
	 * @param AwbPref
	 * @param AwbNo
	 * @param modScc
	 * @return
	 * @author A-8260
	 */
	public OPR026 modifySCC(String AwbPref, String AwbNo, String modScc) {
		AwbPref = PropertyHandler.getPropValue(dataFilePath, AwbPref);
		AwbNo = PropertyHandler.getPropValue(dataFilePath, AwbNo);
		modScc = PropertyHandler.getPropValue(dataFilePath, modScc);

		// reOpen(AwbPref, AwbNo);
		list(AwbPref, AwbNo);
		minWait();
		// click(btn_reOpen);
		// list(AwbPref, AwbNo);
		enterKeys(txt_scc, modScc);

		minWait();
		click(btn_chargeTab);

		click(btn_save);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(okBtn)) {

			click(okBtn);
		}
		return this;
	}


	/*
	 * A-8255
	 */
	public OPR026 verifySpotAndNetCharge(String prefix, String awbno, String SpotRateId, boolean isSpot,
			String offeredSpotvalue) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		SpotRateId = PropertyHandler.getPropValue(dataFilePath, SpotRateId);
		offeredSpotvalue = PropertyHandler.getPropValue(dataFilePath, offeredSpotvalue);

		list(prefix, awbno);
		minWait();
		waitForElement(btn_chargeTab);

		click(btn_chargeTab);

		String netcharge = waitForElement(txt_netCharge).getAttribute("value");
		if (isSpot) {
			String tempId = waitForElement(txt_spotRate).getAttribute("value");

			Assert.assertEquals(tempId, SpotRateId);
			Assert.assertEquals(netcharge, offeredSpotvalue, "Offered charge not getting effected:::");

			click(waitForElement(btn_clear));

		} else {

			waitForElement(txt_spotRate).getAttribute("value").isEmpty();

			click(waitForElement(btn_clear));
		}

		minWait();

		return this;

	}

	/*
	 * A-8255
	 */
	public OPR026 captureDetailsWithAWB(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String agentCode, String shipper, String consignee, String pcs, String wt, String commCode,
			boolean isConsol) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);
		maxWait();
		if (isConsol) {
			check(chkbx_consol);
		}
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);

		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		click(btn_save);

		driver.switchTo().defaultContent();

		if (isConsol) {

			while (true) {
				if (waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?")) {

					Assert.assertTrue(
							waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
					click(noBtn);
					break;
				} else
					minWait();
				click(yesBtn);
			}

		}

		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);

		minWait();
		click(btn_clear);
		return this;
	}

	/*
	 * A-8255
	 */
	public OPR026 relistAWBDetails(String awbPre, String awbNo, String origin, String dest, String carrCode,
			String agentCode, String shipper, String consignee, boolean isConsol) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		click(btn_clear);
		list(awbPre, awbNo);

		String cOrigin = waitForElementVisible(txt_origin).getAttribute("value");
		String cdest = waitForElementVisible(txt_dest).getAttribute("value");
		String croutingDest = waitForElementVisible(txt_routingDest1).getAttribute("value");
		String croutingcarrcode = waitForElementVisible(txt_routingCarrCode1).getAttribute("value");
		String cagentCode = waitForElementVisible(txt_agentCode).getAttribute("value");
		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}
		String cshipper = waitForElementVisible(txt_shipper).getAttribute("value");
		String cconsignee = waitForElementVisible(txt_consignee).getAttribute("value");
		boolean isConsolval = waitForElementVisible(chkbx_consol).isSelected();

		Assert.assertTrue((isConsolval == isConsol & origin.equals(cOrigin) & dest.equals(cdest)
				& croutingDest.equals(dest) & croutingcarrcode.equals(carrCode) & cagentCode.equals(agentCode)
				& cshipper.equals(shipper) & cconsignee.equals(consignee)), "All the details got captured properly.");

		click(btn_clear);
		return this;

	}


	public OPR026 relistAWBandCaptureDGR(String awbPre, String awbNo, String unid, String dgrName, String pi,
			String noOfPkg, String wtPerPkg) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		unid = PropertyHandler.getPropValue(dataFilePath, unid);
		dgrName = PropertyHandler.getPropValue(dataFilePath, dgrName);
		pi = PropertyHandler.getPropValue(dataFilePath, pi);
		noOfPkg = PropertyHandler.getPropValue(dataFilePath, noOfPkg);
		wtPerPkg = PropertyHandler.getPropValue(dataFilePath, wtPerPkg);

		list(awbPre, awbNo);
		minWait();

		click(btn_save);

		driver.switchTo().defaultContent();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains(
				"Dangerous goods details not captured for the SCC DGR. Do you want to proceed saving the AWB without capturing the DGR details?"));
		click(noBtn);

		waitForFrameAndSwitch(FRAME);

		waitForNewWindow(2);
		switchToSecondWindow();

		enterKeys(txt_DGRUnid, unid + Keys.TAB);
		minWait();
		selectByText(dropDown_DGRName, dgrName);
		scrollToView(txt_DGRwtPerPcs);
		enterKeys(txt_DGRpi, pi);
		enterKeys(txt_DGRPcs, noOfPkg);
		enterKeys(txt_DGRwtPerPcs, wtPerPkg);
		click(btn_DGRadd);
		waitForElement(txt_DGRaddedUNID);
		click(btn_DGROk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		click(btn_save);

		driver.switchTo().defaultContent();
		click(btn_genericOk);
		waitForFrameAndSwitch(FRAME);

		return this;

	}

	public OPR026 addUNIDToDGRAwb(String awbPre, String awbNo, String unid, String dgrName, String pi, String noOfPkg,
			String wtPerPkg) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		unid = PropertyHandler.getPropValue(dataFilePath, unid);
		dgrName = PropertyHandler.getPropValue(dataFilePath, dgrName);
		pi = PropertyHandler.getPropValue(dataFilePath, pi);
		noOfPkg = PropertyHandler.getPropValue(dataFilePath, noOfPkg);
		wtPerPkg = PropertyHandler.getPropValue(dataFilePath, wtPerPkg);

		list(awbPre, awbNo);
		minWait();

		click(btn_DGR);

		waitForNewWindow(2);
		switchToSecondWindow();

		enterKeys(txt_DGRUnid, unid + Keys.TAB);
		minWait();
		selectByText(dropDown_DGRName, dgrName);
		scrollToView(txt_DGRwtPerPcs);
		enterKeys(txt_DGRpi, pi);
		enterKeys(txt_DGRPcs, noOfPkg);
		enterKeys(txt_DGRwtPerPcs, wtPerPkg);
		click(btn_DGRadd);
		waitForElement(txt_DGRaddedUNID);
		click(btn_DGROk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		click(btn_save);

		driver.switchTo().defaultContent();
		click(btn_genericOk);
		waitForFrameAndSwitch(FRAME);

		return this;

	}

	public OPR026 verifyUNIDCaptured(String awbPre, String awbNo, String... unids) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		list(awbPre, awbNo);
		minWait();

		click(btn_DGR);

		switchToSecondWindow();

		for (String unid : unids) {
			unid = PropertyHandler.getPropValue(dataFilePath, unid);
			Assert.assertTrue(verifyValuePresentInTblColumn(tbl_DGRunid, 3, unid),
					"The UNID : " + unid + " is not captured for AWB No: " + awbNo);
		}
		minWait();

		click(btn_DGROk);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);

		return this;

	}

	public OPR026 verifyUNIDCapturedWithPcsAndWt(String awbPre, String awbNo, String noPkg, String totPcs, String totWt,
			String... unids) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		noPkg = PropertyHandler.getPropValue(dataFilePath, noPkg);
		totPcs = PropertyHandler.getPropValue(dataFilePath, totPcs);
		totWt = PropertyHandler.getPropValue(dataFilePath, totWt);

		list(awbPre, awbNo);
		minWait();

		click(btn_DGR);

		switchToSecondWindow();

		Assert.assertTrue(
				waitForElement(info_dgrDtls).getText().toUpperCase()
						.contains(("TOTAL DG PACKAGES : " + totPcs).toUpperCase()),
				"The total DG packages DGR details not present");
		Assert.assertTrue(
				waitForElement(info_dgrDtls).getText().toUpperCase()
						.contains(("STATED PCS/WGT : " + totPcs + "/" + totWt + ".0 KG").toUpperCase()),
				"The total stated Wt and Pcs DGR details not present");

		for (String unid : unids) {
			unid = PropertyHandler.getPropValue(dataFilePath, unid);
			Assert.assertTrue(verifyValuePresentInTblColumn(tbl_DGRunid, 3, unid),
					"The UNID : " + unid + " is not captured for AWB No: " + awbNo);
		}
		minWait();

		click(btn_DGROk);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);

		return this;

	}

	/**
	 * verifies DGR button color and SCC listed
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param scc
	 * @param dgrCaptured
	 * @return
	 */
	public OPR026 verifyDGRBtnAndSCC(String awbPre, String awbNo, String scc, boolean dgrCaptured) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);

		list(awbPre, awbNo);
		minWait();

		if (dgrCaptured) {
			Assert.assertEquals(waitForElementVisible(btn_DGR).getAttribute("style").trim().toLowerCase(),
					"background-color: rgb(0, 77, 0);".toLowerCase(),
					"The DGR button is not in green color for the AWB No: " + awbNo);
			Assert.assertTrue(
					waitForElementVisible(txt_scc).getAttribute("value").toUpperCase().contains(scc.toUpperCase()),
					"The UNID scc is not captured for the AWb No: " + awbNo);
		} else {
			Assert.assertNull(waitForElementVisible(btn_DGR).getAttribute("style"),
					"The DGR button is in green color for the AWB No: " + awbNo);
			Assert.assertFalse(
					waitForElementVisible(txt_scc).getAttribute("value").toUpperCase().contains(scc.toUpperCase()),
					"The UNID scc is still captured for the AWb No: " + awbNo);
		}

		return this;

	}

	/**
	 * verifies SCC
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param scc
	 * @return
	 */
	public OPR026 verifySCC(String awbPre, String awbNo, String scc) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);

		list(awbPre, awbNo);
		minWait();

		Assert.assertTrue(
				waitForElementVisible(txt_scc).getAttribute("value").toUpperCase().contains(scc.toUpperCase()),
				"The scc is not captured for the AWb No: " + awbNo);

		return this;

	}

	/**
	 * Captures DGR detaials and saves the awb for one or more UNIDs
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @param carrCode
	 * @param sci
	 * @param scc
	 * @param agentCode
	 * @param shipper
	 * @param consignee
	 * @param pcs
	 * @param wt
	 * @param commCode
	 * @param isConsol
	 * @param isDGCapture
	 * @param otherDGRDtls
	 *            : entered in the order UNID, DGRName, pi, noOfPkg, wtPerPkg
	 * @return
	 */

	public OPR026 captureDGRGoods(String stockType, String awbPre, String awbNo, String origin, String dest,
			String carrCode, String sci, String scc, String agentCode, String shipper, String consignee, String pcs,
			String wt, String commCode, boolean isConsol, boolean isDGCapture, boolean singlePkg,
			String... otherDGRDtls) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		String unid, dgrName, noOfPkg, pi, wtPerPkg;

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo);

		if (isConsol) {
			check(chkbx_consol);
		}

		String tempDate = waitForElement(txt_DropoffTime).getAttribute("value");
		// PropertyHandler.setPropValue(dataFilePath, key_startDate, tempDate);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		selectByText(dropdown_sci, sci);
		enterKeys(txt_scc, scc);
		enterKeys(txt_product, "");
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		if (!verifyElementVisible(txt_shipper)) {
			click(div_sc);
		}

		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		if (isDGCapture) {
			click(btn_DGR);
			waitForNewWindow(2);
			switchToSecondWindow();

			int i = 0;

			do {

				unid = PropertyHandler.getPropValue(dataFilePath, otherDGRDtls[i++]);
				dgrName = PropertyHandler.getPropValue(dataFilePath, otherDGRDtls[i++]);
				pi = PropertyHandler.getPropValue(dataFilePath, otherDGRDtls[i++]);
				noOfPkg = PropertyHandler.getPropValue(dataFilePath, otherDGRDtls[i++]);
				wtPerPkg = PropertyHandler.getPropValue(dataFilePath, otherDGRDtls[i++]);

				enterKeys(txt_DGRUnid, unid + Keys.TAB);
				minWait();
				selectByText(dropDown_DGRName, dgrName);
				scrollToView(txt_DGRwtPerPcs);
				enterKeys(txt_DGRpi, pi);
				enterKeys(txt_DGRPcs, noOfPkg);
				enterKeys(txt_DGRwtPerPcs, wtPerPkg);
				selectByText(dropDown_DGRWtUnit, "KG");
				click(btn_DGRadd);
				waitForElement(txt_DGRaddedUNID);
			} while (i < otherDGRDtls.length);

			if (singlePkg) {

				check(chkBx_DGRcheckAll);

				click(btn_DGRTypeOfPkg);
				switchToNthWindow(3);
				check(chkBx_pkgDtlsAllPkOne);
				enterKeys(txt_pkgDtlsNoOfPkg, "1");
				click(btn_pkgDtlsOk);
				switchToSecondWindow();
			}

			click(btn_DGROk);
			switchToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
		}

		click(btn_save);

		driver.switchTo().defaultContent();

		if (isConsol) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
			click(noBtn);
		}

		if (!isDGCapture) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains(
					"Dangerous goods details not captured for the SCC DGR. Do you want to proceed saving the AWB without capturing the DGR details?"));
			click(noBtn);
		}
		while (verifyElementPresent(yesBtn)) {
			click(yesBtn);
		}
		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		// Assert.assertTrue(waitForElement(txt_agentCode).getAttribute("value").trim().equalsIgnoreCase(agentCode),
		// "The Concsol checkbox is not selected");

		click(btn_clear);

		return this;
	}

	/**
	 * deletes a captured UNID
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param unid
	 * @return
	 */
	public OPR026 deleteUNID(String awbPre, String awbNo, String unid) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		unid = PropertyHandler.getPropValue(dataFilePath, unid);

		list(awbPre, awbNo);

		click(btn_DGR);
		waitForNewWindow(2);
		switchToSecondWindow();

		minWait();
		tblSelectRowByColValue(tbl_DGRunid, 1, 3, unid);
		click(btn_DGRdelete);
		minWait();
		waitForElementNotPresent(txt_DGRaddedUNID);

		click(btn_DGROk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		click(btn_save);
		driver.switchTo().defaultContent();
		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		return this;
	}

	/*
	 * A-8255
	 */

	public OPR026 sendFWB() {

		hoverAndClick(waitForElement(btn_send), waitForElement(btn_sendFWB));
		switchToSecondWindow();
		click(btn_add);
		selectByValue(dropdwn_interfacesys, "ICS");
		selectByValue(dropdwn_transmissionmode, "FILE");
		click(btn_address);
		switchToNthWindow(3);
		enterKeys(txt_filelistener, "abcd");
		click(btn_okButton);
		switchToSecondWindow();
		click(btn_okButton1);
		driver.switchTo().defaultContent();
		Assert.assertTrue(waitForElement(info_footer).getText().contains("FWB sent sucessfully"),
				"ERROR : FWB not sent sucessfully.");
		waitForFrameAndSwitch(FRAME);

		return this;
	}

	/*
	 * A-8255
	 */
	public OPR026 VerifyAWBError() {
		click(btn_list);
		minWait();
		driver.switchTo().defaultContent();
		click(yesBtn);
		waitForFrameAndSwitch(screenFrame);
		Assert.assertTrue(waitForElementVisible(info_errormsg).getText().contains("enter AWB No"),
				"Please enter AWB No Message getting displayed.");
		;
		return this;

	}

	/*
	 * A-8255
	 */

	public OPR026 printAWB(String awbNo) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		hoverAndClick(waitForElement(btn_print), waitForElement(btn_Lprint));

		switchToSecondWindow();

		Assert.assertTrue(waitForElement(radio_publishedrates).isSelected(),
				"Published Rates radio button not checked.");
		check(chkbox_termscond);
		minWait();
		click(btn_okbuton);

		System.out.println("Print action completed:::");

		click(btn_closebuton);

		waitForFrameAndSwitch(FRAME);

		return this;
	}

	/*
	 * A-8255 Change IATA Rate and Net charge and chargecode under charge
	 * details
	 */
	public OPR026 changeIATANetChargeChargeCodeandAsIsExecute(String awbPre, String awbNo, String iataRate,
			String netCharge, String chargeCode, String chargeValue, boolean chkbox_duecarrierb, String stockType) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);

		click(btn_reOpen);

		click(btn_chargeTab);

		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);

		// enterKeys(txt_chargeCode, chargeCode + Keys.TAB);
		enterKeys(txt_chargeValue, chargeValue);
		if (chkbox_duecarrierb) {
			check(chkbox_duecarrier);
		}

		click(btn_asIs);
		maxWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}
		maxWait();

		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();

		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();

		click(btn_clear);

		relistChargesAndRating(iataRate, netCharge, chargeCode, chargeValue, chkbox_duecarrierb);

		return this;
	}

	/*
	 * A-8255
	 */

	public OPR026 executeAWBwithFreeofCharge(String awbPre, String awbNo, String servicecargo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		servicecargo = PropertyHandler.getPropValue(dataFilePath, servicecargo);

		list(awbPre, awbNo);
		minWait();

		selectByText(txt_servicecargo, servicecargo);

		click(btn_save);

		driver.switchTo().defaultContent();

		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		list(awbPre, awbNo);
		minWait();

		click(btn_execute);

		String status = "";
		driver.switchTo().defaultContent();

		if (verifyElementPresent(yesBtn)) {
			status = waitForElement(msg_executed).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		minWait();

		Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"), "ERROR : AWB not Executed.");
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();

		click(btn_chargeTab);
		String iatacharge = waitForElementVisible(txt_iatacharge).getAttribute("value");
		scrollToView(txt_netCharge);
		String netcharge = waitForElementVisible(txt_netCharge).getAttribute("value");

		Assert.assertTrue((iatacharge.equals("0") & netcharge.equals("0")),
				"Charges should not be present for free of charge service");

		return this;

	}

	/**
	 * Created by a-8255 on 06/02/2018
	 */
	public OPR026 captureDetailsWOAWB(String awbPre, String awbNo, String origin, String dest, String carrCode,
			String agentCode, String shipper, String consignee, String pcs, String wt, String commCode,
			boolean isConsol) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		click(btn_list);
		minWait();
		driver.switchTo().defaultContent();
		click(yesBtn);
		waitForFrameAndSwitch(FRAME);
		if (isConsol) {
			check(chkbx_consol);
		}
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		click(div_sc);

		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		minWait();

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		click(btn_save);

		driver.switchTo().defaultContent();
		if (verifyElementPresent(okBtn)) {
			awbNo = waitForElement(msg_popup).getText().substring(8, 16);
			click(okBtn);
		}

		waitForFrameAndSwitch(FRAME);

		list(awbPre, awbNo);

		// Verifying all the details captured correctly.

		return this;
	}

	/*
	 * A-8255
	 */

	public OPR026 executeAWBAfterAutoRate(String rateClass, String RateCardId, String sci, String awbPre, String origin,
			String dest, String carrCode, String agentCode, String shipper, String consignee, String pcs, String wt,
			String commCode, String awbNo, boolean isConsol) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		RateCardId = PropertyHandler.getPropValue(dataFilePath, RateCardId);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);

		enterKeys((txt_prefix), awbPre);
		enterKeys(txt_awb, awbNo);
		click(btn_list);

		maxWait();

		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn))
			click(yesBtn);
		waitForFrameAndSwitch(FRAME);

		maxWait();
		if (isConsol) {
			check(chkbx_consol);
		}
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		click(div_sc);
		// click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);
		selectByText(dropdown_sci, sci);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		scrollToView(btn_btnAutoRate);
		click(btn_btnAutoRate);

		click(btn_btnCalculateCharge);

		click(btn_save);

		driver.switchTo().defaultContent();
		if (isConsol) {

			while (true) {
				if (waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?")) {

					Assert.assertTrue(
							waitForElement(info_genericMsg).getText().contains("Do you want to capture houses?"));
					click(noBtn);
					break;
				} else
					minWait();
				click(yesBtn);
			}

		}

		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);
		click(btn_clear);
		// awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPre, awbNo);
		click(btn_chargeTab);

		String[] iataratelineid = waitForElement(txt_iataratelineid).getAttribute("value").split("/");
		String[] marketratelineid = waitForElement(txt_marketratelineid).getAttribute("value").split("/");

		if (RateCardId.equals(iataratelineid[0]) | RateCardId.equals(marketratelineid[0]))
			System.out.println("RatelineID matching");
		else
			System.out.println("RatelineID not matching");
		// relistRatingDetails();

		click(btn_clear);

		return this;
	}

	/*
	 * A-8255
	 */
	public OPR026 checkHousesAndModifyValues(String awbPre, String awbNo, String house1, String house2, String shipper,
			String consignee, String pcs, String wt, String commCode) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		list(awbPre, awbNo);
		List<String> values = getAllValues(dropDown_houses);

		Assert.assertTrue(values.contains((String) house1), "The house details have not been updated");
		Assert.assertTrue(values.contains((String) house2), "The house details have not been updated");

		click(div_sc);
		enterKeys(txt_shipper, shipper + Keys.TAB);
		enterKeys(txt_consignee, consignee + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);

		click(btn_save);

		driver.switchTo().defaultContent();

		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		return this;
	}

	/*
	 * A-8255
	 */

	public OPR026 verifyHouseDetails(String h1, String h2, String awbPre, String awbNo, String origin, String h1pcs,
			String h1wt, String h2pcs, String h2wt, String pcs) {

		h2wt = PropertyHandler.getPropValue(dataFilePath, h2wt);
		h2pcs = PropertyHandler.getPropValue(dataFilePath, h2pcs);
		h1wt = PropertyHandler.getPropValue(dataFilePath, h1wt);
		h1pcs = PropertyHandler.getPropValue(dataFilePath, h1pcs);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		h1 = PropertyHandler.getPropValue(dataFilePath, h1);
		h2 = PropertyHandler.getPropValue(dataFilePath, h2);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);

		String cOrigin, spcs, swt;

		list(awbPre, awbNo);

		enterKeys(dropDown_houses1, h1 + Keys.TAB);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_genericMsg)) {
			Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("Specified HAWB H1 not found"),
					"HAWB details are getting updated for executed AWB");
			click(btn_proceed);
			waitForFrameAndSwitch(FRAME);
		} else {
			waitForFrameAndSwitch(FRAME);

			enterKeys(dropDown_houses1, "");

			int i = Integer.parseInt(h1pcs) + Integer.parseInt(h2pcs);
			boolean isconsol = waitForElement(chkbx_consol).isSelected();
			spcs = waitForElementVisible(txt_statedpcs).getAttribute("value");

			Assert.assertTrue(((Integer.parseInt(spcs) >= i) & (isconsol == true)),
					"Stated Pieces Showing Wrong Value");

			enterKeys(dropDown_houses1, h1 + Keys.TAB);
			cOrigin = waitForElement(txt_origin).getAttribute("value");
			spcs = waitForElementVisible(txt_statedpcs).getAttribute("value");
			swt = waitForElementVisible(txt_statedwt).getAttribute("value");
			Assert.assertTrue(cOrigin.equals(origin) & spcs.equals(h1pcs) & swt.equals(h1wt),
					"House1 Details are not fetched properly");

			enterKeys(dropDown_houses1, h2 + Keys.TAB);
			cOrigin = waitForElement(txt_origin).getAttribute("value");
			spcs = waitForElementVisible(txt_statedpcs).getAttribute("value");
			swt = waitForElementVisible(txt_statedwt).getAttribute("value");
			Assert.assertTrue(cOrigin.equals(origin) & spcs.equals(h2pcs) & swt.equals(h2wt),
					"House2 Details are not fetched properly");
		}

		return this;
	}

	/*
	 * A-8255
	 */

	public OPR026 executeAWBWithDGR(String awbPrefix, String awbNo)

	{
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		list(awbPrefix, awbNo);
		maxWait();

		click(btn_execute);
		minWait();

		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains(
				"Dangerous goods details not captured for the SCC DGR. Do you want to proceed saving the AWB without capturing the DGR details?"));
		click(yesBtn);
		minWait();

		return this;
	}

	/*
	 * A-8255
	 */

	public OPR026 executeAWB_VerifyCustCode(String awbPrefix, String awbNo, String agentCode) {

		String agent;

		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);

		list(awbPrefix, awbNo);
		click(btn_execute);
		minWait();
		agent = waitForElement(txt_pymnt_txt_custCode).getAttribute("value");

		Assert.assertTrue(agent.equals(agentCode), "Actual agent code is'" + agent + "' and expected is '" + agentCode);

		System.out.println("Agent is" + agentCode + "CustCode is" + agent);

		return this;
	}

	/*
	 * A-8255
	 */
	public OPR026 asIsExecuteWithChargeCode(String prefix, String awbno, String stockType, String shipper,
			String consignee, String rateClass, String iataRate, String netCharge, String sci, String chargeCode,
			String chargeValue, String origin, String dest, String carrCode, String agentCode, String pcs, String wt,
			String commCode, boolean chkbox_duecarrierb) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		chargeCode = PropertyHandler.getPropValue(dataFilePath, chargeCode);
		chargeValue = PropertyHandler.getPropValue(dataFilePath, chargeValue);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);

		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, prefix, awbno);

		maxWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_routingDest1, dest);
		enterKeys(txt_routingCarrCode1, carrCode);
		enterKeys(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commCode, commCode);
		click(div_sc);
		minWait();
		// click(div_sc);
		if (isElementPresent(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, sci);

		click(btn_chargeTab);

		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		enterKeys(txt_chargeCode, chargeCode + Keys.TAB);
		enterKeys(txt_chargeValue, chargeValue);
		if (chkbox_duecarrierb) {
			check(chkbox_duecarrier);
		}

		click(btn_save);

		driver.switchTo().defaultContent();

		minWait();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		waitForFrameAndSwitch(FRAME);

		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		list(prefix, awbno);

		click(btn_asIs);
		maxWait();
		if (stockType.equals("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();

		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();

		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();

		click(btn_clear);
		// relistChargesAndRating(iataRate,
		// netCharge,chargeCode,chargeValue,chkbox_duecarrierb);
		return this;
	}

	public OPR026 relistChargesAndRating(String iataRate, String netCharge, String chargeCode, String chargeValue,
			boolean chkbox_duecarrierb) {

		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		chargeCode = PropertyHandler.getPropValue(dataFilePath, chargeCode);
		chargeValue = PropertyHandler.getPropValue(dataFilePath, chargeValue);

		click(btn_chargeTab);

		scrollToView(txt_chargeValue);
		String cchargeValue = waitForElementVisible(txt_chargeValue).getAttribute("value");
		boolean chkbox_duecarrierbVal = waitForElementVisible(chkbox_duecarrier).isSelected();
		String ciataRate = waitForElementVisible(txt_iataRate).getAttribute("value");
		scrollToView(txt_netCharge);
		String cnetCharge = waitForElementVisible(txt_netCharge).getAttribute("value");

		String[] iataRate1 = iataRate.split(".");
		String[] netCharge1 = iataRate.split(".");
		String[] chargeValue1 = iataRate.split(".");

		Assert.assertTrue(
				(chkbox_duecarrierbVal == chkbox_duecarrierb & iataRate1[0].equals(ciataRate)
						& netCharge1[0].equals(cnetCharge) & chargeValue1[0].equals(cchargeValue)),
				"All the details got captured properly.");

		return this;

	}

	public OPR026 verifyServiceDetails(String awb, String serviceDetails) {

		String serviceDetails1 = null;

		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		serviceDetails = PropertyHandler.getPropValue(dataFilePath, serviceDetails);

		if (!awb.equals("")) {

			enterKeys(txt_awb, awb);

			click(btn_list);

			click(btn_asIs);

			maxWait();

			serviceDetails1 = waitForElementVisible(txt_servicedetails).getText();
			Assert.assertTrue(serviceDetails1.contains("AWBA"));
		} else {

			String[] awbNo = serviceDetails.split("134-");

			// enterKeys(txt_prefix,"134");

			enterKeys(txt_awb, awbNo[1]);

			click(btn_list);

			click(btn_asIs);

			maxWait();

			serviceDetails1 = waitForElementVisible(txt_servicedetails).getText();
			System.out.println(serviceDetails1);
			Assert.assertTrue(serviceDetails.equals(serviceDetails1));
		}

		enterKeys(txt_remarks, "Payment Remarks");
		click(btn_pymnt_ok);
		click(btn_finalize);
		minWait();
		click(btn_pymnt_close);
		minWait();

		driver.switchTo().defaultContent();
		String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();

		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");

		return this;

	}

	/**
	 * Created by A-7605 on 12-4-18 This method update details in an already
	 * captured AWB
	 * 
	 * @param awbPrefix
	 * @param awbNo
	 * @param agentCode
	 * @param pieces
	 * @param weight
	 * @param commodityCode
	 * @param scc
	 * @return
	 */
	public OPR026 updateAWBDetails(String awbPrefix, String awbNo, String agentCode, String pieces, String weight,
			String commodityCode, String scc) {
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);

		enterKeys(txt_prefix, awbPrefix);
		enterKeys(txt_awb, awbNo);
		click(btn_list);
		minWait();
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		expandShipperConsigneeWrapper();
		enterKeys(txt_shipper, agentCode);
		enterKeys(txt_shipper, Keys.TAB);
		minWait();
		enterKeys(txt_consignee, agentCode);
		enterKeys(txt_consignee, Keys.TAB);
		selectByText(dropdown_sci, "T1");
		enterKeys(txt_scc, scc);
		enterKeys(txt_product, "");
		enterKeys(txt_shipmentDetailsPcs, pieces);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		click(btn_save);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {
			click(yesBtn);
		}
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 listAndExecuteWithChecksheet(String stockType, String awbPre, String awbNo, String response,
			String rateClass, String iataRate, String netCharge) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		response = PropertyHandler.getPropValue(dataFilePath, response);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);

		String status = null;
		list(awbPre, awbNo);

		click(btn_checkSheet);
		switchToSecondWindow();

		enterKeys(txt_chkSheetResponse, response);
		click(btn_chkSheetSave);
		click(btn_genericOk);
		click(btn_chkSheetClose);
		switchToFirstWindow();
		waitForFrameAndSwitch(FRAME);

		waitForElement(btn_chargeTab);
		// click(div_sc);
		// //click(div_sc);
		// enterKeys(txt_shipper, shipper);
		// enterKeys(txt_consignee, consignee);
		// selectByText(dropdown_sci, sci);
		/*
		 * click(btn_chargeTab);
		 * 
		 * selectByText(dropdown_rateClass, rateClass); enterKeys(txt_iataRate,
		 * iataRate); scrollToView(txt_netCharge); enterKeys(txt_netCharge,
		 * netCharge); minWait();
		 */
		click(btn_execute);
		minWait();
		if (stockType.equalsIgnoreCase("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(yesBtn)) {
			status = waitForElement(info_genericMsg).getText();
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);
		Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
		minWait();

		return this;
	}

	/**
	 * @param awbPre
	 * @param awbNo
	 * @param source
	 *            : not read from data sheet
	 * @param agentCode
	 *            : can be left blank if not checked
	 * @param checkSCC
	 * @param checkAgentcode
	 * @return
	 */
	public OPR026 checkAdditionalInfo(String awbPre, String awbNo, String source, String agentCode, boolean checkSCC,
			boolean checkAgentcode) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		String status = null;
		list(awbPre, awbNo);

		click(tab_additionalInfo);

		Assert.assertEquals(verifyValuePresentInTblColumn_input(tbl_otherCustomInfo, 3, source),
				"The country code doesn't match.");
		Assert.assertEquals(verifyValuePresentInTblColumn_input(tbl_otherCustomInfo, 4, "IN"),
				"The country code doesn't match.");

		if (checkSCC) {
			Assert.assertTrue(waitForElement(info_sccLbl).getText().contains("SPX"),
					"The SCC is not present. The displayed SCC is : " + waitForElement(info_sccLbl).getText());
		}

		if (checkAgentcode) {
			agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
			Assert.assertEquals(verifyValuePresentInTblColumn_input(tbl_otherCustomInfo, 7, agentCode),
					"The agent code doesn't match.");
		}

		return this;
	}

	/**
	 * Check the weights in the Charg tab
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param grossWt
	 * @param adjustWt
	 * @param chrWt
	 *            : can be "" if not needed to check
	 * @return
	 */
	public OPR026 verifyWts(String awbPre, String awbNo, String grossWt, String adjustWt, String chrWt,
			boolean... chargbleWtEditable) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		grossWt = PropertyHandler.getPropValue(dataFilePath, grossWt);
		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		if (!adjustWt.equals("")) {
			adjustWt = PropertyHandler.getPropValue(dataFilePath, adjustWt);
		}
		if (!chrWt.equals("")) {
			chrWt = PropertyHandler.getPropValue(dataFilePath, chrWt);
		}

		list(awbPre, awbNo);

		click(btn_chargeTab);

		Assert.assertEquals(waitForElementVisible(txt_chargeWt).getAttribute("value").trim(), grossWt,
				"The Gross weight doesn't match for AWB No:" + awbNo);
		if (!adjustWt.equals("")) {
			Assert.assertEquals(waitForElementVisible(txt_chargeAdjWt).getAttribute("value").trim(), adjustWt,
					"The Adjusted weight doesn't match for AWB No:" + awbNo);
		}
		if (!chrWt.equals("")) {
			Assert.assertEquals(waitForElementVisible(txt_chargeChargableWt).getAttribute("value").trim(), chrWt,
					"The Chargeable weight doesn't match for AWB No:" + awbNo);
		}
		if (chargbleWtEditable.length != 0) {
			if (chargbleWtEditable[0]) {
				Assert.assertTrue(waitForElementVisible(txt_chargeChargableWt).isEnabled());
			} else {
				Assert.assertFalse(waitForElementVisible(txt_chargeChargableWt).isEnabled());
			}
		}

		return this;
	}

	public OPR026 confirmVol(String awbPre, String awbNo, String vol) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		list(awbPre, awbNo);

		click(btn_chargeTab);

		Assert.assertEquals(getTableCellTextValue_header(tbl_chargeTblHeader, 13, 2).trim().toUpperCase(), "VOLUME",
				"The header for volume is not correct");
		Assert.assertEquals(getTableCellValue_input(tbl_chargeTblBody, 13, 1).trim(), vol,
				"The value for volume is not correct for AWB No: " + awbNo);

		return this;
	}

	public OPR026 captureAWBWithBookingDetails(String stockType, String awbPrefix, String awbNo, String carrierCode,
			String origin, String destination, String scc, String agentCode, String shipperCode, String consigneeCode,
			String pcs, String weight, String volume, String commodityCode, String flightNo) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);

		StockType stock = StockType.valueOf(stockType);
		String notificationMessage;
		String currentStockAWBNo;
		getAWB(stock, awbPrefix, awbNo);
		/*
		 * while (true) { currentStockAWBNo = getAWB(stock, awbPrefix, awbNo);
		 * enterKeys(txt_prefix, awbPrefix); enterKeys(txt_awb,
		 * currentStockAWBNo); click(btn_list); if
		 * (verifyElementPresent(yesBtn)) { click(yesBtn); break; }
		 * click(btn_clear); } PropertyHandler.setPropValue(dataFilePath, awbNo,
		 * currentStockAWBNo);
		 */
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		// click(div_sc);
		// waitForElementVisible(txt_shipper);
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);
		waitForElement(txt_product).clear();

		click(tab_bookingDetails);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_flightDate, ".");

		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		notificationMessage = waitForElement(msg_popup).getText();
		Assert.assertTrue(notificationMessage.contains("saved successfully"), "Actual notification message is '"
				+ notificationMessage + "' and expected message is 'saved successfully'");
		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 verifyAWBNotExistMSG(String prefix, String awbno) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		list(prefix, awbno);
		minWait();

		driver.switchTo().defaultContent();
		waitForElement(info_genericMsg).getText().contains("AWB does not exist.Do you want to capture?");
		click(yesBtn);
		waitForFrameAndSwitch(screenFrame);

		minWait();

		return this;
	}

	// public OPR026 findNonUtilisedAWB(String stockType,String awbPrefix,String
	// awbNo) {
	//
	// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
	// StockType stock = StockType.valueOf(stockType);
	//
	// getAWB(stock, awbPrefix, awbNo);
	//
	//
	// minWait();
	//
	// return this;
	// }
	//

	public OPR026 captureAWBWithBookingDetailsAndExecute(String stockType, String awbPrefix, String awbNo,
			String carrierCode, String origin, String destination, String scc, String agentCode, String shipperCode,
			String consigneeCode, String pcs, String weight, String volume, String commodityCode, String flightNo) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);

		StockType stock = StockType.valueOf(stockType);
		String notificationMessage;
		String currentStockAWBNo;
		String status = null;
		getAWB(stock, awbPrefix, awbNo);
		/*
		 * while (true) { currentStockAWBNo = getAWB(stock, awbPrefix, awbNo);
		 * enterKeys(txt_prefix, awbPrefix); enterKeys(txt_awb,
		 * currentStockAWBNo); click(btn_list); if
		 * (verifyElementPresent(yesBtn)) { click(yesBtn); break; }
		 * click(btn_clear); } PropertyHandler.setPropValue(dataFilePath, awbNo,
		 * currentStockAWBNo);
		 */
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		// click(div_sc);
		// waitForElementVisible(txt_shipper);
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);
		waitForElement(txt_product).clear();

		click(tab_bookingDetails);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_flightDate, ".");
		click(btn_execute);
		minWait();
		if (stockType.equalsIgnoreCase("CASH")) {

			enterKeys(txt_remarks, "Payment Remarks");
			click(btn_pymnt_ok);
			click(btn_finalize);
			minWait();
			Assert.assertFalse(waitForElement(txt_pymntAdvice).getAttribute("value").isEmpty(),
					"ERROR : Payment not successful");
			click(btn_pymnt_close);
			minWait();
		}

		maxWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(yesBtn)) {
			status = waitForElement(info_genericMsg).getText();
			Assert.assertTrue(status.contains("executed"), "ERROR : AWB not Executed.");
			click(yesBtn);
		}
		waitForFrameAndSwitch(FRAME);

		minWait();
		// click(btn_save);
		// driver.switchTo().defaultContent();
		// notificationMessage = waitForElement(msg_popup).getText();
		// Assert.assertTrue(notificationMessage.contains("saved successfully"),
		// "Actual notification message is '" + notificationMessage +
		// "' and expected message is 'saved successfully'");
		// click(okBtn);
		// waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 verifyAWBIsVoidedErrorMSG(String prefix, String awbno) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		list(prefix, awbno);
		minWait();

		String status = waitForElement(Generic_info_error).getText();
		System.out.println(status);
		// Assert.assertTrue(waitForElement(info_errormsg).getText().contains("The
		// specified AWB number is blacklisted. Cannot proceed."),
		// "ERROR :Able to proceed booking for blacklisted AWB.");
		Assert.assertTrue(status.contains("is voided"), "ERROR :AWB is voided msg not coming");

		minWait();

		return this;
	}

	public OPR026 verifyStockNotAvailableErrorMSG(String stockType, String awbPrefix, String awbNo, String carrierCode,
			String origin, String destination, String scc, String agentCode, String shipperCode, String consigneeCode,
			String pcs, String weight, String volume, String commodityCode, String flightNo) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);

		// StockType stock = StockType.valueOf(stockType);
		// String notificationMessage;
		// String currentStockAWBNo;
		// getAWB(stock, awbPrefix, awbNo);
		/*
		 * while (true) { currentStockAWBNo = getAWB(stock, awbPrefix, awbNo);
		 * enterKeys(txt_prefix, awbPrefix); enterKeys(txt_awb,
		 * currentStockAWBNo); click(btn_list); if
		 * (verifyElementPresent(yesBtn)) { click(yesBtn); break; }
		 * click(btn_clear); } PropertyHandler.setPropValue(dataFilePath, awbNo,
		 * currentStockAWBNo);
		 */
		click(btn_list);
		driver.switchTo().defaultContent();
		waitForElement(info_genericMsg).getText().contains("AWB does not exist.Do you want to capture?");
		click(yesBtn);
		waitForFrameAndSwitch(screenFrame);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		// click(div_sc);
		// waitForElementVisible(txt_shipper);
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);
		waitForElement(txt_product).clear();

		click(tab_bookingDetails);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_flightDate, ".");

		click(btn_save);
		String status = waitForElement(Generic_info_error).getText();
		System.out.println(status);
		// Assert.assertTrue(waitForElement(info_errormsg).getText().contains("The
		// specified AWB number is blacklisted. Cannot proceed."),
		// "ERROR :Able to proceed booking for blacklisted AWB.");
		Assert.assertTrue(status.contains("Stock not available for the specified agent"),
				"ERROR :Stock not available for the agent msg not coming");
		return this;
	}

	public OPR026 listAWB_verifyStatus(String awbPrefix, String awbNo, String status) {

		list(awbPrefix, awbNo);
		status = PropertyHandler.getPropValue(dataFilePath, status);
		String status2 = waitForElement(valueStatus).getAttribute("value");
		Assert.assertTrue(status.equalsIgnoreCase(status2), "status displayed is wrong");

		return this;
	}

	public OPR026 captureAWBWithBookingDetailsVerifyDimensionMandatoryErrorMSG(String stockType, String awbPrefix,
			String awbNo, String carrierCode, String origin, String destination, String scc, String agentCode,
			String shipperCode, String consigneeCode, String pcs, String weight, String volume, String commodityCode,
			String flightNo) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);

		StockType stock = StockType.valueOf(stockType);
		String notificationMessage;
		String currentStockAWBNo;
		getAWB(stock, awbPrefix, awbNo);
		/*
		 * while (true) { currentStockAWBNo = getAWB(stock, awbPrefix, awbNo);
		 * enterKeys(txt_prefix, awbPrefix); enterKeys(txt_awb,
		 * currentStockAWBNo); click(btn_list); if
		 * (verifyElementPresent(yesBtn)) { click(yesBtn); break; }
		 * click(btn_clear); } PropertyHandler.setPropValue(dataFilePath, awbNo,
		 * currentStockAWBNo);
		 */
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		// click(div_sc);
		// waitForElementVisible(txt_shipper);
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);
		waitForElement(txt_product).clear();

		click(tab_bookingDetails);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_flightDate, ".");

		click(btn_save);
		Assert.assertTrue(
				waitForElement(info_errormsg).getText().contains(
						"Dimension information is mandatory. Please provide dimension details for the shipment to proceed"),
				"ERROR :dimension mandatory error message not coming");

		// driver.switchTo().defaultContent();
		// notificationMessage = waitForElement(msg_popup).getText();
		// Assert.assertTrue(notificationMessage.contains("saved successfully"),
		// "Actual notification message is '" + notificationMessage +
		// "' and expected message is 'saved successfully'");
		// click(okBtn);
		// waitForFrameAndSwitch(FRAME);
		return this;
	}

	public OPR026 captureAWBWithBookingDetailsWithDim(String stockType, String awbPrefix, String awbNo,
			String carrierCode, String origin, String destination, String scc, String agentCode, String shipperCode,
			String consigneeCode, String pcs, String weight, String volume, String commodityCode, String flightNo) {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		shipperCode = PropertyHandler.getPropValue(dataFilePath, shipperCode);
		consigneeCode = PropertyHandler.getPropValue(dataFilePath, consigneeCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);

		StockType stock = StockType.valueOf(stockType);
		String notificationMessage;
		String currentStockAWBNo;
		getAWB(stock, awbPrefix, awbNo);
		/*
		 * while (true) { currentStockAWBNo = getAWB(stock, awbPrefix, awbNo);
		 * enterKeys(txt_prefix, awbPrefix); enterKeys(txt_awb,
		 * currentStockAWBNo); click(btn_list); if
		 * (verifyElementPresent(yesBtn)) { click(yesBtn); break; }
		 * click(btn_clear); } PropertyHandler.setPropValue(dataFilePath, awbNo,
		 * currentStockAWBNo);
		 */

		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, destination);
		enterKeys(txt_routingDest1, destination);
		enterKeys(txt_routingCarrCode1, carrierCode);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		// click(div_sc);
		// waitForElementVisible(txt_shipper);
		enterKeys(txt_shipper, shipperCode);
		enterKeys(txt_shipper, Keys.TAB);
		enterKeys(txt_consignee, consigneeCode);
		enterKeys(txt_consignee, Keys.TAB);
		enterKeys(txt_shipmentDetailsPcs, pcs);
		enterKeys(txt_shipmentDetailsWeight, weight);
		enterKeys(txt_shipmentDetailsVolume, volume);
		enterKeys(txt_shipmentDetailsCmdtyCode, commodityCode);
		enterKeys(txt_shipmentDetailsCmdtyCode, Keys.TAB);

		setDimensions(pcs, weight);
		waitForElement(txt_product).clear();
		minWait();

		click(tab_bookingDetails);
		enterKeys(txt_flightNo, flightNo);
		enterKeys(txt_flightDate, ".");

		click(btn_save);
		driver.switchTo().defaultContent();
		notificationMessage = waitForElement(msg_popup).getText();

		Assert.assertTrue(notificationMessage.contains("saved successfully"), "Actual notification message is '"
				+ notificationMessage + "' and expected message is 'saved successfully'");
		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	private void setDimensions(String pcs, String wt) {

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, pcs);
		enterKeys(txt_dimWt, wt);
		enterKeys(txt_dimLength, "10");
		enterKeys(txt_dimWidth, "10");
		enterKeys(txt_dimHeight, "10" + Keys.TAB);

		click(btn_dimOk);
		driver.switchTo().defaultContent();
		// while(verifyElementPresent(yesBtn)) {
		//
		// click(yesBtn);
		// minWait();
		// }
		click(yesBtn);
		minWait();
		click(yesBtn);
		minWait();
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

	}

	// new

	public OPR026 listAWBAndVerifyUniqueReferance(String AWBNo, String awbPre, String uniqueReference1) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		uniqueReference1 = PropertyHandler.getPropValue(dataFilePath, uniqueReference1);
		enterKeys(txt_awb, AWBNo);
		click(btn_list);
		minWait();
		click(tab_chargesAndAccounting);
		maxWait();
		String uniqueReference2 = waitForElement(txt_uniqueReferance).getAttribute("value");

		Assert.assertEquals(uniqueReference1, uniqueReference2, "ERROR:unique referance is wrong");

		return this;
	}

	public OPR026 listAWBAndVerifyNoUniqueReferance(String AWBNo, String awbPre) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		enterKeys(txt_awb, AWBNo);
		click(btn_list);
		minWait();
		click(tab_chargesAndAccounting);
		maxWait();
		String uniqueReference = waitForElement(txt_uniqueReferance).getAttribute("value");

		Assert.assertEquals("", uniqueReference, "ERROR:unique referance is there");

		return this;
	}

	/**
	 * @author A-8260
	 * @param AwbPref
	 * @param AwbNo
	 * @return
	 */
	public OPR026 validateScreeningMethodFWB(String AwbPref, String AwbNo) {
		AwbPref = PropertyHandler.getPropValue(dataFilePath, AwbPref);
		AwbNo = PropertyHandler.getPropValue(dataFilePath, AwbNo);
		list(AwbPref, AwbNo);
		minWait();
		click(btn_AdditionalInfoTab);
		String scrMethods = "";
		// Assert.assertTrue(condition);
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM",
				"AOM-TRACE DETECTION CONFIRMED AWBS");
		Assert.assertTrue(scrMethods.equals("AOM-TRACE DETECTION CONFIRMED AWBS"),
				"Screening method should be available in the table");
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM", "RES");
		Assert.assertTrue(scrMethods.equals("RES"), "Screening method should be available in the table");
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM", "XRY");
		Assert.assertTrue(scrMethods.equals("XRY"), "Screening method should be available in the table");
		return this;
	}

	/**
	 * @author A-8260
	 * @param tblLocator
	 * @param ref
	 * @param val
	 * @return
	 */

	private String validateTableTextFieldRowWithReference(By tblLocator, String ref, String val) {
		String xpath;
		WebElement table = waitForElement(tblLocator);
		xpath = ".//tbody/tr/td/*[contains(@value,'" + ref + "')]/../following-sibling::td/*[contains(@value,'" + val
				+ "')]";
		System.out.println("value: " + table.findElement(By.xpath(xpath)).getText());
		System.out.println("Value::: " + table.findElement(By.xpath(xpath)).getAttribute("value"));
		return table.findElement(By.xpath(xpath)).getAttribute("value");
	}

	public HomePage closeScreeen() {
		if (verifyElementPresent(btn_close) && verifyElementVisible(btn_close))
			click(btn_close);
		else
			minWait();
		Actions action = new Actions(driver);
		action.keyDown(Keys.ALT).sendKeys("o").keyUp(Keys.ALT).perform();

		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn))
			click(yesBtn);
		return new HomePage(driver, dataFileName, test);
	}

	/**
	 * @author A-8260
	 * @param AwbPref
	 * @param AwbNo
	 * @return
	 */

	public OPR026 validateScreeningMethod(String AwbPref, String AwbNo, String screeningMethod1,
			String screeningMethod2) {
		AwbPref = PropertyHandler.getPropValue(dataFilePath, AwbPref);
		AwbNo = PropertyHandler.getPropValue(dataFilePath, AwbNo);
		screeningMethod1 = PropertyHandler.getPropValue(dataFilePath, screeningMethod1);
		screeningMethod2 = PropertyHandler.getPropValue(dataFilePath, screeningMethod2);
		list(AwbPref, AwbNo);
		minWait();
		click(btn_AdditionalInfoTab);
		String scrMethods = "";
		// Assert.assertTrue(condition);
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM", screeningMethod1);
		Assert.assertTrue(scrMethods.equals(screeningMethod1), "Screening method should be available in the table");
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM", screeningMethod2);
		Assert.assertTrue(scrMethods.equals(screeningMethod2), "Screening method should be available in the table");

		return this;
	}

	/**
	 * @author A-8260
	 * @param AwbPref
	 * @param AwbNo
	 * @param scc
	 * @return
	 */
	public OPR026 changeSCCAndSave(String AwbPref, String AwbNo, String scc) {
		AwbPref = PropertyHandler.getPropValue(dataFilePath, AwbPref);
		AwbNo = PropertyHandler.getPropValue(dataFilePath, AwbNo);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		list(AwbPref, AwbNo);
		minWait();
		enterKeys(txt_scc, scc);

		minWait();
		click(btn_save);
		if (verifyElementPresent(yesBtn)) {
			click(yesBtn);
		}
		minWait();
		driver.switchTo().defaultContent();
		Assert.assertTrue(waitForElement(info_genericMsg).getText().contains("saved successfully"));
		click(btn_genericOk);

		return this;
	}

	/**
	 * @author A-8260
	 * @param AwbPref
	 * @param AwbNo
	 * @return
	 */
	public OPR026 validateScreeningMethodxFWB(String AwbPref, String AwbNo) {
		AwbPref = PropertyHandler.getPropValue(dataFilePath, AwbPref);
		AwbNo = PropertyHandler.getPropValue(dataFilePath, AwbNo);
		list(AwbPref, AwbNo);
		minWait();
		click(btn_AdditionalInfoTab);
		String scrMethods = "";
		// Assert.assertTrue(condition);
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM",
				"AOM-confirmed awb-success screen");
		Assert.assertTrue(scrMethods.equals("AOM-confirmed awb-success screen"),
				"Screening method should be available in the table");
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM", "RES");
		Assert.assertTrue(scrMethods.equals("RES"), "Screening method should be available in the table");
		scrMethods = validateTableTextFieldRowWithReference(tbl_Additional_info, "SM", "XRY");
		Assert.assertTrue(scrMethods.equals("XRY"), "Screening method should be available in the table");
		return this;
	}

	/**
	 * Method to autorate with value in Adjusted Weight field
	 * 
	 * @param awbPrefix
	 * @param awbNo
	 * @param adjWt
	 * @param rateClass
	 * @param iataRate
	 * @param netCharge
	 * @return
	 * @author A-7868 Krishna <03/07/2018>
	 */
	public OPR026 autoRateWithAdjustedWt(String awbPrefix, String awbNo, String adjWt, String rateClass,
			String iataRate, String netCharge) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		adjWt = PropertyHandler.getPropValue(dataFilePath, adjWt);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);

		list(awbPrefix, awbNo);
		minWait();
		click(btn_chargeTab);
		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		enterKeys(txt_netCharge, netCharge);
		scrollToView(txt_chargeAdjWt);
		enterKeys(txt_chargeAdjWt, adjWt);
		String tempVol = waitForElement(txt_Charges_grossVol).getAttribute("value");

		scrollToView(By.xpath("//*[@name='checkAll']"));
		click(By.xpath("//*[@name='checkAll']"));

		click(btn_btnAutoRate);
		minWait();
		String newVol = waitForElement(txt_Charges_grossVol).getAttribute("value");
		Assert.assertFalse(tempVol.equals(newVol), "Volume changed as per Adjusted Wt");

		click(btn_save);
		driver.switchTo().defaultContent();
		String notificationMessage = waitForElement(msg_popup).getText();
		Assert.assertTrue(notificationMessage.contains("saved successfully"), "Actual notification message is '"
				+ notificationMessage + "' and expected message is 'saved successfully'");
		click(okBtn);
		waitForFrameAndSwitch(FRAME);
		return this;
	}

	/**
	 * Method to list an AWB, navigate to Charges&Accounting tab and verify the
	 * Gross volume
	 * 
	 * @param awbPrefix
	 * @param awbNo
	 * @param grossVol
	 * @return
	 * @author A-7868 Krishna <03/07/2018>
	 */
	public OPR026 checkGrossVolume(String awbPrefix, String awbNo, String grossVol) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		grossVol = PropertyHandler.getPropValue(dataFilePath, grossVol);

		list(awbPrefix, awbNo);
		click(btn_chargeTab);
		Assert.assertEquals(waitForElement(txt_Charges_grossVol).getAttribute("value"), grossVol,
				"Gross Volume mismatch");

		return this;
	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields, rate fields and
	 * As-Is Execute Handles both cash and credit customers
	 * 
	 * @author shalini 21/12/2018
	 */
	public OPR026 ProcessasIsExecute(String prefix, String awbno, String shipper, String consignee, String sci,
			String Product) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		minWait();
		if (verifyElementVisible(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		enterKeys(txt_product, Product);
		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		maxWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		maxWait();
		click(btn_asIs);
		maxWait();
		handleAlert("Accept", "OPR026");
		driver.switchTo().frame("iCargoContentFrameOPR026");
		/*
		 * click(btn_asIs); handleAlert("Accept", "OPR026");
		 * driver.switchTo().frame("iCargoContentFrame");
		 */
		minWait();
		String actaulValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		Assert.assertTrue(isFound, "Executed" + actaulValue);
		test.log(LogStatus.PASS, "Status is Executed successfully" + actaulValue);
		return this;
	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields,and do As is
	 * Excute
	 * 
	 * @author shalini 21/12/2018
	 */
	public OPR026 ProcessasIsExecuteafterBooking(String prefix, String awbno, String shipper, String consignee) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Successfully entered Prefix and AWB no in OPR026 screen");
		// if (verifyElementVisible(txt_shipper))
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		click(btn_chargeTab);
		minWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		click(btn_asIs);
		maxWait();
		handleAlert("Accept", "OPR026");
		driver.switchTo().frame("iCargoContentFrameOPR026");
		minWait();
		maxWait();
		minWait();
		String actaulValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		Assert.assertTrue(isFound, "Executed" + actaulValue);
		test.log(LogStatus.PASS, "AWB is Executed successfully: " + actaulValue);
		return this;
	}

	/**
	 * Description... Handles an alert with options getText/Accept/Dismiss/Close
	 * 
	 * @param alertOps
	 * @param ScreenName
	 */
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
			test.log(LogStatus.INFO, "Not able to handle Alert " + ScreenName);

		}
	}

	// Sharath
	public OPR026 ProcessExecuteafterBooking(String prefix, String awbno, String shipper, String consignee) {
		boolean isFound = true;
	//	prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
	//	awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
	//	shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
	//	consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		test.log(LogStatus.INFO, "Entered the shipper and consignee details");
		minWait();
		try {
			if (PropertyHandler.getPropValue(dataFilePath, "commCode").equals("CATDOG")) {
				captureChecksheetForAvi();
			}
			
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		click(btn_execute);
		test.log(LogStatus.INFO, "Clicked on button : execute");
//		continueEmbargo();
		maxWait();
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
//		maxWait();
		maxWait();
		try {
			waitForFrameAndSwitch("popupContainerFrame");
			if (verifyElementPresent(
					By.xpath("//span[contains(text(),'Suspicious')]/../..//td[contains(@class,'error')]"))) {
				test.log(LogStatus.WARNING, getText_JS(
						By.xpath("//span[contains(text(),'Suspicious')]/../..//td[contains(@class,'error')]")));
				captureAndAddScreenshot();
				click(By.xpath("//span[contains(text(),'Suspicious')]/../..//input[@type='checkbox']"));
				minWait();
				driver.findElement(By.xpath("//span[contains(text(),'Suspicious')]/../..//button[@name='btContinue']")).click();
				maxWait();
			}
		} catch (Exception e) {
		}
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		maxWait();
//		minWait();
//		maxWait();
		maxWait();
//		maxWait();
		String actualValue= waitForElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]")).getText();
//		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
//				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.FAIL, "AWB isn't Executed successfully and has status : " + actualValue);
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}
		return this;
	}

	// Sharath
	public OPR026 ProcessExecuteafterBookingwithDataChange(String prefix, String awbno, String shipper,
			String consignee, String newVol, String newWt) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		newWt = PropertyHandler.getPropValue(dataFilePath, newWt);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		// if (verifyElementVisible(txt_shipper))
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		test.log(LogStatus.INFO, "Entered the shipper and consignee details");

		enterKeys(txt_commWt, newWt);
		click(btn_chargeTab);
		minWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		click(btn_execute);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on button : execute");

		// handle weight change alert
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			System.out.println("No weight related alert");
			waitForFrameAndSwitch(screenFrame);
		}

		maxWait();
		// handle overall dimension change alert
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			System.out.println("No weight related alert");
			waitForFrameAndSwitch(screenFrame);
		}
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		minWait();
		maxWait();
		minWait();
		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + actualValue);
			captureAndAddScreenshot();
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}

		return this;
	}

	// Sharath
	public OPR026 ProcessExecuteafterBookingforCC(String prefix, String awbno, String shipper, String consignee) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		// if (verifyElementVisible(txt_shipper))
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		test.log(LogStatus.INFO, "Entered the shipper and consignee details");
		click(btn_chargeTab);
		minWait();
		selectByText(By.xpath("//select[@name='paymentType']"), "CC");
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		WebElement chrge1 = driver
				.findElement(By.xpath("//table[@id='otherChargeDtlsTable']/tbody/tr[1]/td[4]//input[@name='charges']"));
		WebElement chrge2 = driver
				.findElement(By.xpath("//table[@id='otherChargeDtlsTable']/tbody/tr[2]/td[4]//input[@name='charges']"));
		String value1 = getAttributebyValue(chrge1);
		String value2 = getAttributebyValue(chrge2);
		if (value1.equals("0.00")) {
			enterKeys(chrge1, "5");
		}
		if (value2.equals("0.00")) {
			enterKeys(chrge2, "5");
		}
		minWait();
		click(btn_execute);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on button : execute");

		// handle weight change alert
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			System.out.println("No weight related alert");
			waitForFrameAndSwitch(screenFrame);
		}

		maxWait();
		// handle overall dimension change alert
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			System.out.println("No weight related alert");
			waitForFrameAndSwitch(screenFrame);
		}
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		minWait();
		maxWait();
		minWait();
		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + actualValue);
			captureAndAddScreenshot();
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}

		return this;
	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields, rate fields and
	 * As-Is Execute Handles both cash and credit customers
	 * 
	 * @author a-8457 Souvik 03/04/2019
	 */
	public OPR026 asIsExecuteInOPR026(String prefix, String awbno, String stockType, String shipper, String consignee,
			String rateClass, String iataRate, String netCharge, String sci, boolean checksheetFlag) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		minWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		minWait();
		// click(div_sc);
		if (verifyElementVisible(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, sci);
		click(btn_chargeTab);
		minWait();
		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		// enterKeys(txt_netCharge, netCharge);
		if (checksheetFlag == true) {
			captureChecksheetForAvi();
		}
		minWait();
		click(btn_asIs);
		maxWait();
		/*
		 * if (stockType.equals("CASH")) {
		 * 
		 * enterKeys(txt_remarks, "Payment Remarks"); click(btn_pymnt_ok);
		 * click(btn_finalize); minWait(); Assert.assertFalse(
		 * waitForElement(txt_pymntAdvice).getAttribute("value") .isEmpty(),
		 * "ERROR : Payment not successful"); click(btn_pymnt_close); minWait();
		 * }
		 */
		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		// String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),"ERROR
		// : AWB not Executed.");
		// Assert.assertTrue(status.contains("executed"),"ERROR : AWB not
		// Executed.");
		test.log(LogStatus.PASS, "AWB No :" + awbno + " is executed successfully");
		captureAndAddScreenshot();
		minWait();
		return this;
	}

	// Souvik
	public void captureChecksheetForAvi() {
		minWait();
		click(By.xpath("//button[@name='btnCheckSheet']"));
		maxWait();
		maxWait();
		// driver.switchTo().frame(screenFrame);
		driver.switchTo().frame("popupContainerFrame");
		// driver.switchTo().defaultContent();
		selectByText(
				By.xpath(
						"//input[contains(@value,'Was a manual paper Shipper Security Endorsement (SSE) completed')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		/*
		enterKeys(
				By.xpath(
						"//input[contains(@value,'SHIPPERS/ AUTHORIZED REP NAME:')]/parent::label/../following-sibling::div[1]/textarea"),
				"TEST"+Keys.TAB);
		
		enterKeys(By.xpath("//input[@tooltip_info='Date' and @name=\"questionwithAnswer[5].answerDate\"]"),"."+Keys.TAB);
		enterKeys(By.xpath("//input[@value='ADDRESS:']/parent::label/../following-sibling::div[1]/textarea"),"TEST" + Keys.TAB);
		enterKeys(By.xpath("//input[@value='CITY:']/parent::label/../following-sibling::div[1]/textarea"),"TEST" + Keys.TAB);
		enterKeys(By.xpath("//input[@value='STATE:']/parent::label/../following-sibling::div[1]/textarea"),"TEST" + Keys.TAB);
		enterKeys(By.xpath("//input[@value='ZIP CODE:']/parent::label/../following-sibling::div[1]/textarea"),"TEST" + Keys.TAB);
		enterKeys(By.xpath("//input[@value='TELEPHONE:']/parent::label/../following-sibling::div[1]/textarea"),"555345" + Keys.TAB);
		*/
		/*
		 * click(By.xpath("(//span[@class='ui-icon ui-icon-triangle-1-s'])[1]"))
		 * ; check(By.xpath(
		 * "//input[@name='multiselect_CMP_Checksheet_Defaults_CaptureCheckSheet_MultiSelect2'][@value='25ц/-3.9у']"
		 * ));
		 * click(By.xpath("(//span[@class='ui-icon ui-icon-circle-close'])[2]"))
		 * ; minWait();
		 */
		selectByText(
				By.xpath(
						"//input[contains(@value,'statement of acclimation')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		click(By.xpath(
				"//input[contains(@value,'acclimation temperature')]/parent::label/../following-sibling::div[1]/button"));
		minWait();
		click(By.xpath("//input[@value='20�F/-6.7�C']"));
		minWait();
		enterKeys(By.xpath("//input[@value='20�F/-6.7�C']"), Keys.TAB);

		// click(By.xpath("//input[contains(@value,'acclimation
		// temperature')]/.."));
		minWait();
		selectByText(By.xpath("//input[contains(@value,'ARC')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");

		selectByText(By.xpath("//input[contains(@value,'LHR OPS')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Elected Broker')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'iCargo AWB Enquiry')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath("//input[contains(@value,'Pet Passport')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Has a Health Certificate')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'at least 15 weeks old')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'breed of the cat or dog been verified')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'more than 5 pets traveling unaccompanied')]/parent::label/../following-sibling::div[1]/select"),
				"No");
		selectByText(
				By.xpath(
						"//input[contains(@value,'pet is traveling no more than 5 days')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'USDA Certified Veterinarian')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'pet received an implanted microchip')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		enterKeys(
				By.xpath(
						"//input[contains(@value,'when the microchip implantation')]/parent::label/../following-sibling::div[1]//input[@btype='BT_CALENDAR']"),
				"-1" + Keys.TAB);

		selectByText(
				By.xpath(
						"//input[contains(@value,'current rabies vaccination')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		enterKeys(
				By.xpath(
						"//input[contains(@value,'when the rabies vaccination was performed')]/parent::label/../following-sibling::div[1]//input[@btype]"),
				"." + Keys.TAB);

		selectByText(
				By.xpath(
						"//input[contains(@value,'vaccination waiting period of 21 days')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Has the anti-parasite treatment')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'pet have a PET PASSPORT')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Details of Ownership')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Description of Animal')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Marking of Animal')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Vaccination Against Rabies')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Echinococcus Treatment')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,' Clinical Examination')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'completed and signed by veterinarian')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'Customer Acknowledgement Form signed')]/parent::label/../following-sibling::div[1]/select"),
				"Yes");
		selectByText(
				By.xpath(
						"//input[contains(@value,'cat or dog pregnant')]/parent::label/../following-sibling::div[1]/select"),
				"No");
		selectByText(
				By.xpath("//input[contains(@value,'been sedated')]/parent::label/../following-sibling::div[1]/select"),
				"No");
		/*
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[42].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[43].templateAnswer']"),"Yes");
		 */
		click(By.xpath("//button[@name='btnSave']"));
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		driver.switchTo().frame("popupContainerFrame");
		driver.findElement(By.xpath("//button[@name='btnClose']")).click();
		// click(By.xpath("//button[@name='btnClose']"));
		waitForFrameAndSwitch(screenFrame);
		test.log(LogStatus.INFO, "Successfully entered values in Check Sheet for commodity: ");
	}

	// Sharath
	public OPR026 ProcessExecuteafterBookingAVI(String prefix, String awbno, String shipper, String consignee) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		test.log(LogStatus.INFO, "Entered the shipper and consignee details");
		click(btn_chargeTab);
		minWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		captureChecksheetForAviWarm();
		click(btn_execute);
		maxWait();
		test.log(LogStatus.INFO, "Clicked on button : execute");
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			System.out.println("No volume related alert");
			waitForFrameAndSwitch(screenFrame);
		}
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		minWait();
		maxWait();
		minWait();
		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
		} else {
			test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + actualValue);
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}
		return this;
	}

	// Sharath
	public void captureChecksheetForAviWarm() {
		minWait();
		click(By.xpath("//button[@name='btnCheckSheet']"));
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		/*
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[1].templateAnswer']"),"Yes");
		 * click(By.xpath("(//span[@class='ui-icon ui-icon-triangle-1-s'])[1]"))
		 * ; click(By.xpath(
		 * "(//input[@name='multiselect_CMP_Checksheet_Defaults_CaptureCheckSheet_MultiSelect2'])[1]"
		 * ));
		 * click(By.xpath("(//span[@class='ui-icon ui-icon-circle-close'])[2]"))
		 * ; minWait(); selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[4].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[6].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[7].templateAnswer']"),"No");
		 * enterKeys(By.xpath(
		 * "//textarea[@name='questionwithAnswer[8].templateAnswer']"),"IBSAT");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[10].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[11].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[12].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[13].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[15].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[16].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[17].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[20].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[21].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[23].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[24].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[25].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[27].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[28].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[29].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[31].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[32].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[33].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[34].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[35].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[36].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[37].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[39].templateAnswer']"),"Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[41].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[42].templateAnswer']"),"No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[43].templateAnswer']"),"Yes");
		 */ click(By.xpath("//button[@name='btnSave']"));
		try {
			maxWait();
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
			// waitForFrameAndSwitch("popupContainerFrame");
			maxWait();
			// click(By.name("btnClose"));
			click(By.xpath("(//button[@title='Close'])[1]"));
		} catch (Exception e) {
			System.out.println("No popup available");
		}
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		test.log(LogStatus.INFO, "Successfully entered values in Check Sheet for commodity: ");
	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields,and do As is
	 * Excute
	 * 
	 * @author shalini 21/12/2018
	 */
	public OPR026 ProcessasExcuteWithFinalStatus(String prefix, String awbno, String shipper, String consignee) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Successfully entered Prefix and AWB no in OPR026 screen");
		// if (verifyElementVisible(txt_shipper))
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		click(btn_chargeTab);
		minWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		click(btn_execute);
		maxWait();
		if (PropertyHandler.getPropValue(dataFilePath, "commodityCode").equals("VALCARGO")) {
			continueEmbargo();
		}
		// FinalizePayment();
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch("iCargoContentFrameOPR026");
		maxWait();
		maxWait();
		String actaulValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		Assert.assertTrue(isFound, "Executed" + actaulValue);
		test.log(LogStatus.PASS, "AWB is Executed successfully: " + actaulValue);
		return this;
	}

	// Shalini
	public OPR026 FinalizePayment() {

		String actualAmt = driver.findElement(By.name("formAmount")).getAttribute("value");
		driver.findElement(By.name("parameterValue")).sendKeys(actualAmt + " paid");
		click(btn_Add_payment);
		minWait();
		click(btn_PaymentFinalize);
		maxWait();
		String status = driver.findElement(By.xpath("//div[@title='Payment Status']")).getText();
		if (status.equalsIgnoreCase("Final")) {
			test.log(LogStatus.PASS, "AWB is Finalized with Cash : " + status);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.FAIL, "AWB isn't Executed successfully and has status : " + status);
			Assert.fail("AWB is Finalized with Cash: " + status);
		}
		// driver.findElement(btn_CloseCaptureHouseNavigate).click();
		click(By.name("btClose"));
		return this;
	}

	/**
	 * Description: capture AWB with payment done by CCard in CSH007 Screen
	 * 
	 * @author A-8680 Sharath
	 * @param prefix
	 * @param awbno
	 * @param shipper
	 * @param consignee
	 * @return
	 */
	public OPR026 ProcessExecuteAfterCreditCardPayment(String prefix, String awbno, String shipper, String consignee) {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		// if (verifyElementVisible(txt_shipper))
		if (driver.findElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		test.log(LogStatus.INFO, "Entered the shipper and consignee details");
		click(btn_chargeTab);
		minWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		click(btn_execute);
		test.log(LogStatus.INFO, "Clicked on button : execute");
		maxWait();
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			waitForFrameAndSwitch(screenFrame);
		}
		minWait();
		/*
		 * try{ handleAlert("Accept", "OPR026");
		 * waitForFrameAndSwitch(screenFrame); } catch(Exception e){
		 * waitForFrameAndSwitch(screenFrame); } try{ handleAlert("Accept",
		 * "OPR026"); waitForFrameAndSwitch(screenFrame); } catch(Exception e){
		 * waitForFrameAndSwitch(screenFrame); } list(prefix, awbno); minWait();
		 * click(btn_execute); test.log(LogStatus.INFO,
		 * "Clicked on button : execute after relisting"); maxWait();
		 * 
		 * PaymentByCCard(prefix, awbno); driver.switchTo().defaultContent();
		 * waitForFrameAndSwitch(screenFrame); //click(By.xpath(
		 * "//*[@id='CMP_Operations_Shipment_CaptureAWB_Execute']")); maxWait();
		 * handleAlert("Accept", "OPR026"); waitForFrameAndSwitch(screenFrame);
		 */
		maxWait();
		maxWait();
		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + actualValue);
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}
		return this;
	}


	public OPR026 continueEmbargo() {
		try {
			if(getNumberOfWindows()>1){
			switchToSecondWindow();
			captureAndAddScreenshot();
			if (verifyElementPresent(By.name("btContinue"))) {
				click(By.name("btContinue"));
			} else {
				throw new Exception();
			}
			// driver.findElement(By.name("btContinue")).click();
			switchToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
			test.log(LogStatus.INFO, "Restriction has been handled");
			logger.info("Restriction has been handled");
			}else{
				throw new Exception();
			}
		} catch (ElementNotVisibleException e) {
			test.log(LogStatus.INFO, "No Restriction found");
			logger.info("No Restriction found");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "No Restriction found");
			logger.info("No Restriction found");
		}
		return this;
	}

	// Sharath
	public OPR026 ProcessExecuteafterFWBTrigger(String prefix, String awbno) {
		boolean isFound = true;
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		click(btn_chargeTab);
		minWait();
		click(chcbx_Autorate);
		click(btn_btnAutoRate);
		minWait();
		try {
			if (PropertyHandler.getPropValue(dataFilePath, "commCode").equals("CATDOG")) {
				captureChecksheetForAvi();
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		click(btn_execute);
		test.log(LogStatus.INFO, "Clicked on button : execute");
		maxWait();
		try {
			if (driver.findElement(By.name("parameterValue")).isDisplayed()) {
				FinalizePayment();
			}
		} catch (Exception e) {
		}
		maxWait();
		/*
		 * try{ continueEmbargo(); }catch(Exception e){
		 * test.log(LogStatus.INFO, "No Restriction found");
		 * logger.info("No Restriction found"); }
		 */
		continueEmbargo();
		try {
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			System.out.println("No volume related alert");
		}
		maxWait();
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		minWait();
		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + actualValue);
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}
		return this;
	}

	/**
	 * Method to list an AWB, fill the shipper consignee fields, rate fields and
	 * As-Is Execute Handles both cash and credit customers It also adds HAWB in
	 * the awb
	 * 
	 * @author a-8457 Souvik 03/04/2019
	 */
	public OPR026 asIsExecuteInOPR026WithHAWBAddition(String prefix, String awbno, String stockType, String shipper,
			String consignee, String rateClass, String iataRate, String netCharge, String sci, boolean checksheetFlag,
			String origin, String destination, String pcs, String wt) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
		rateClass = PropertyHandler.getPropValue(dataFilePath, rateClass);
		iataRate = PropertyHandler.getPropValue(dataFilePath, iataRate);
		netCharge = PropertyHandler.getPropValue(dataFilePath, netCharge);
		sci = PropertyHandler.getPropValue(dataFilePath, sci);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();

		click(By.xpath("//button[@name='btnHawb']"));
		driver.switchTo().defaultContent();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		maxWait();
		maxWait();
		click(By.xpath("//a[@name='btAddUpdate']"));
		maxWait();
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(By.xpath("//input[@name='hawbNumber']"), "H1");
		enterKeys(By.xpath("//input[@name='consigneeCode']"), consignee);
		enterKeys(By.xpath("//input[@name='shipperCode']"), shipper);
		enterKeys(By.xpath("//input[@name='statedPieces']"), pcs);
		enterKeys(By.xpath("//input[@name='statedWeight']"), wt);
		minWait();
		// enterKeys(By.xpath("//textarea[@name='shipmentDescription']"),"BOOKS");
		driver.findElement(By.xpath("//textarea[@name='shipmentDescription']")).sendKeys("BOOKS");
		enterKeys(By.xpath("//input[@name='origin']"), origin);
		enterKeys(By.xpath("//input[@name='destination']"), destination);
		click(By.xpath("//button[@name='btOK']"));
		minWait();
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		click(By.xpath("//button[@name='btSave']"));
		maxWait();
		click(By.xpath("//button[@name='btClose']"));
		maxWait();
		waitForElement(btn_chargeTab);
		// click(div_sc);
		minWait();
		// click(div_sc);
		if (verifyElementVisible(txt_shipper)) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}

		selectByText(dropdown_sci, sci);
		check(By.xpath("//input[@name='HAWBCaptureCompleted']"));
		click(btn_chargeTab);
		minWait();
		selectByText(dropdown_rateClass, rateClass);
		enterKeys(txt_iataRate, iataRate);
		scrollToView(txt_netCharge);
		// enterKeys(txt_netCharge, netCharge);
		if (checksheetFlag == true) {
			captureChecksheetForAvi();
		}
		minWait();
		click(By.xpath("//button[@name='btnExecute']"));
		maxWait();
		if (stockType.equals("CASH")) {

			minWait();
			test.log(LogStatus.INFO, "Successfully listed at CSH007 Screen");
			maxWait();
			click(By.xpath("//*[@id='paymentSection']//a//div//h2[contains(text(),'CASH')]"));
			enterKeys(By.xpath("//*[@name='parameterValue']"), "CASH");
			click(By.xpath("//*[@name='btOK']"));
			minWait();
			click(By.xpath("//*[@name='btFinalizePayment']"));
			String paymentAdviceNo = driver.findElement(By.xpath("//*[@name='paymentAdviceNo']")).getAttribute("value");
			if ((paymentAdviceNo != "")) {
				PropertyHandler.setPropValue(dataFilePath, "PaymentAdviceNofor" + awbno + "", paymentAdviceNo);
				test.log(LogStatus.INFO, "Payment made by CASH and Payment Advice Number is: ");
				click(By.xpath("//button[@name='btClose']"));
				maxWait();
			} else {
				test.log(LogStatus.FAIL, "Could not do payment");
				Assert.fail();
			}

		}
		maxWait();
		driver.switchTo().defaultContent();
		if (waitForElement(msg_popup).getText().contains("different from the booked")) {
			click(yesBtn);
		}
		driver.switchTo().defaultContent();
		// String status = waitForElement(msg_executed).getText();
		while (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}

		waitForFrameAndSwitch(FRAME);
		minWait();
		// Assert.assertTrue(waitForElement(info_executed).getText().contains("Executed"),"ERROR
		// : AWB not Executed.");
		// Assert.assertTrue(status.contains("executed"),"ERROR : AWB not
		// Executed.");
		test.log(LogStatus.PASS, "AWB No :" + awbno + " is executed successfully");
		minWait();
		return this;
	}
	
	
	// Sharath
		public OPR026 TestDataCreationProcessExecuteafterBooking(String prefix, String awbno, String shipper, String consignee) {
			awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
			enterKeys(txt_prefix, prefix);
			enterKeys(txt_awb, awbno);
			click(btn_list);
			maxWait();
			maxWait();
			test.log(LogStatus.INFO, "Starting with the capture AWB process");
			if (waitForElement(txt_shipper).isDisplayed()) {
				enterKeys(txt_shipper, shipper + Keys.TAB);
				enterKeys(txt_consignee, consignee + Keys.TAB);
				minWait();
			} else {
				click(div_sc);
				enterKeys(txt_shipper, shipper + Keys.TAB);
				enterKeys(txt_consignee, consignee + Keys.TAB);
			}
			test.log(LogStatus.INFO, "Entered the shipper and consignee details");
			click(btn_execute);
			driver.switchTo().defaultContent();
			if (verifyElementPresent(By.xpath("//p[contains(text(),'No Active Cash draw exists')]"))) {
				click(By.xpath("//button[text()='Yes']"));
				minWait();
			}
			waitForFrameAndSwitch(screenFrame);
			test.log(LogStatus.INFO, "Clicked on button : execute");
			maxWait();
			/*try {
				if (driver.findElement(By.name("parameterValue")).isDisplayed()) {
					FinalizePayment();
				}
			} catch (Exception e) {
			}
			maxWait();
*/			/*
			 * try{ continueEmbargo(); }catch(Exception e){
			 * test.log(LogStatus.INFO, "No Restriction found");
			 * logger.info("No Restriction found"); }
			 */
			continueEmbargo();
			/*try {
				handleAlert("Accept", "OPR026");
				waitForFrameAndSwitch(screenFrame);
			} catch (Exception e) {
				System.out.println("No volume related alert");
			}*/
			maxWait();
			handleAlert("Dismiss", "OPR026");
			waitForFrameAndSwitch(screenFrame);
			return this;
		}

		// Sharath
				public OPR026 ProcessExecuteafterBookingWithCheckSheet(String prefix, String awbno, String shipper, String consignee, String commCode) {
					boolean isFound = true;
//					prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//					awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//					shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
//					consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
//					commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
					enterKeys(txt_prefix, prefix);
					enterKeys(txt_awb, awbno);
					click(btn_list);
					maxWait();
					maxWait();
					test.log(LogStatus.INFO, "Starting with the capture AWB process");
				if (waitForElement(txt_shipper).isDisplayed()) {
						enterKeys(txt_shipper, shipper + Keys.TAB);
						enterKeys(txt_consignee, consignee + Keys.TAB);
						minWait();
					} else {
						
					
						click(div_sc);
						enterKeys(txt_shipper, shipper + Keys.TAB);
						enterKeys(txt_consignee, consignee + Keys.TAB);
				}
					test.log(LogStatus.INFO, "Entered the shipper and consignee details");
					maxWait();
					click(btn_execute);
					maxWait();
					driver.switchTo().defaultContent();
					if (verifyElementPresent(By.xpath("//p[contains(text(),'No Active Cash draw exists')]"))) {
						click(By.xpath("//button[text()='Yes']"));
						minWait();
					}
					if(getNumberOfWindows()>1){
					continueEmbargo();
					}
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					test.log(LogStatus.INFO, "Clicked on button : execute");
					maxWait();
					if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
						click(By.xpath("//img[contains(@src,'close')]"));
						if (commCode.contains("TC")) {
							captureChecksheetForTC();
						} else if (commCode.contains("CATDOG")){
							captureChecksheetForAvi();
						} else if (commCode.contains("AVIWARM")){
							captureChecksheetForAviWarm();
						} else if(commCode.contains("2915") || commCode.contains("1845")) {
							captureChecksheetForDG2915And1845();
						}
//					waitForFrameAndSwitch(screenFrame);
					maxWait();
					click(btn_execute);
					maxWait();
					if(getNumberOfWindows()>1){
						continueEmbargo();
						}
					}
					minWait();
					if(getNumberOfWindows()>1){
						continueEmbargo();
						}
					maxWait();
					driver.switchTo().defaultContent();
					String alertText = getText_JS(By.xpath("//div[@id='ic-sd-msgc']/p")).trim();
					click(By.xpath("//div/button[text()='No']"));
					minWait();
					if(alertText.contains("AWB 001-"+awbno+" is executed. Do you want to relist?")){
						test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + alertText);
						captureAndAddScreenshot();
					}
					else{
						test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + alertText);
						Assert.fail("AWB isn't Executed successfully and has status : " + alertText);
					}
					waitForFrameAndSwitch(screenFrame);
					return this;
				}
				
				
				public void captureChecksheetForTC() {
					minWait();
					click(By.xpath("//button[@name='btnCheckSheet']"));
//					maxWait();
					maxWait();
//					driver.switchTo().frame("popupContainerFrame");
					waitForFrameAndSwitch("popupContainerFrame");
					selectByText(
						By.xpath(
								"//input[contains(@value,'Capture TC Details and confirm')]/parent::label/../following-sibling::div[1]/select"),
						"Yes");
					click(By.xpath("//button[@name='btnSave']"));
					maxWait();
					handleAlert("Accept", "OPR026");
					waitForFrameAndSwitch(screenFrame);
					waitForFrameAndSwitch("popupContainerFrame");
//					driver.switchTo().frame("popupContainerFrame");
					driver.findElement(By.xpath("//button[@name='btnClose']")).click();
//					click(By.xpath("//button[@name='btnClose']"));
					waitForFrameAndSwitch(screenFrame);
					test.log(LogStatus.INFO, "Successfully entered values in Check Sheet for commodity: ");
				}
				
//	Sharath
	public OPR026 ProcessExecuteWithCommCodeUpdate(String prefix, String awbno, String shipper, 
			String consignee, String commCode2) {
//		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//		shipper = PropertyHandler.getPropValue(dataFilePath, shipper);
//		consignee = PropertyHandler.getPropValue(dataFilePath, consignee);
//		commCode2 = PropertyHandler.getPropValue(dataFilePath, commCode2);
		enterKeys(txt_prefix, prefix);
		enterKeys(txt_awb, awbno);
		click(btn_list);
		maxWait();
		maxWait();
		test.log(LogStatus.INFO, "Starting with the capture AWB process");
		if (waitForElement(txt_shipper).isDisplayed()) {
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
			minWait();
		} else {
			click(div_sc);
			enterKeys(txt_shipper, shipper + Keys.TAB);
			enterKeys(txt_consignee, consignee + Keys.TAB);
		}
		test.log(LogStatus.INFO, "Entered the shipper and consignee details");
		minWait();
		scrollToView(By.name("ratelineShipmentCommodities"));
		enterKeys(By.name("ratelineShipmentCommodities"),commCode2+ Keys.TAB);
		minWait();
		try {
			if (PropertyHandler.getPropValue(dataFilePath, "commCode").equals("CATDOG")) {
				captureChecksheetForAvi();
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		click(btn_execute);
		test.log(LogStatus.INFO, "Clicked on button : execute");
		continueEmbargo();
		handleAlert("Accept", "OPR026");
//		waitForFrameAndSwitch(screenFrame);
		maxWait();
		if(verifyElementPresent(By.xpath("//*[contains(text(),'Dangerous goods details not captured for the SCC')]"))){
			test.log(LogStatus.INFO, "handling DG popup");
			captureAndAddScreenshot();
			click(By.xpath("//button[text()='Yes']"));
			minWait();
		}
		maxWait();
//		try {
//			waitForFrameAndSwitch("popupContainerFrame");
//			if (verifyElementPresent(
//					By.xpath("//span[contains(text(),'Suspicious')]/../..//td[contains(@class,'error')]"))) {
//				test.log(LogStatus.WARNING, getText_JS(
//						By.xpath("//span[contains(text(),'Suspicious')]/../..//td[contains(@class,'error')]")));
//				captureAndAddScreenshot();
//				click(By.xpath("//span[contains(text(),'Suspicious')]/../..//input[@type='checkbox']"));
//				minWait();
//				driver.findElement(By.xpath("//span[contains(text(),'Suspicious')]/../..//button[@name='btContinue']"))
//						.click();
//				maxWait();
//			}
//		} catch (Exception e) {
//		}
		handleAlert("Accept", "OPR026");
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		minWait();
		String actualValue = driver.findElement(By.xpath("(//label[text()='Status']//following-sibling::span/span)[2]"))
				.getText();
		if (actualValue.equalsIgnoreCase("Executed")) {
			test.log(LogStatus.PASS, "AWB is Executed successfully with status : " + actualValue);
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.PASS, "AWB isn't Executed successfully and has status : " + actualValue);
			Assert.fail("AWB isn't Executed successfully and has status : " + actualValue);
		}
		return this;
	}
		
	public void captureChecksheetForDG2915And1845() {
		minWait();
		click(By.xpath("//button[@name='btnCheckSheet']"));
		maxWait();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		
		 selectByText(By.xpath("//input[contains(@value,'Is it a acidic material present')]/parent::label/../following-sibling::div[1]/select"),"No");
		 click(By.xpath("//button[@name='btnSave']"));
		try {
			maxWait();
			handleAlert("Accept", "OPR026");
			waitForFrameAndSwitch(screenFrame);
			// waitForFrameAndSwitch("popupContainerFrame");
			maxWait();
			// click(By.name("btnClose"));
			click(By.xpath("(//button[@title='Close'])[1]"));
		} catch (Exception e) {
			System.out.println("No popup available");
		}
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		test.log(LogStatus.INFO, "Successfully entered values in Check Sheet for commodity: ");
	}

}
