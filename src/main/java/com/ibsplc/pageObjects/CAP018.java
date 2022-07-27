package com.ibsplc.pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ibsplc.common.BasePage;
import com.ibsplc.common.enums.StockType;
import com.ibsplc.utils.BizUtility;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.ibsplc.common.Exceptions.AWBRangeNotCorrect;

/**
 * Created by a-7681 on 12/14/2017.
 */
public class CAP018 extends BasePage {

	private static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"CAP_MSG.properties");
	private static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"Generic.properties");
	private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");
	private final static Logger logger = Logger.getLogger(CAP018.class);
	ExtentTest test;
	private By btn_genericYes, info_msg, btn_noBtn;
	// Summary page
	private By info_summaryUbrNo, btn_summaryOk;
	// Dimension Screen
	private By txt_dimPcs, txt_dimWt, txt_dimLength, txt_dimWidth, txt_dimHeight, txt_dimVol, btn_dimOk;
	// ULD info screen
	private By txt_uldInfoUldType, txt_uldInfoNoOfUlds, dropDown_uldInfoContour, Generic_btn_ok, btn_ratingTab,
			btn_uldInfoOk;
	private String screenFrame, screenFrame2, screenFrame3, screenFrame4;
	private String dataFileName;
	private WebDriver driver;
	ExtentTest extentTest;
	// Main screen
	private By txt_awbNoPrefix, txt_awbNo, btn_List, txt_productname, btn_clear, txt_ULDfrst, btn_popUp_Ok,
			txt_shipmentDescrptn, txt_ULDsecnd, txt_origin, txt_dest, txt_agentCode, txt_shippingDt, txt_scc,
			txt_queueRemarksRemark, btn_queueRemarksOk, btn_queueRemarksOk1, txt_commCode, txt_commPcs,
			list_Chk_Waitlisted, txt_commWt, txt_commVol, txt_commAdjWt, btn_chooseAllotId, btn_save, btn_close,
			list_txt_fltOrigin, list_txt_fltDest, list_txt_fltNo, list_txt_fltDt, list_txt_fltPcs, list_txt_fltWt,
			list_txt_fltVol, list_chkBx_fltFSstatus, list_txt_fltAllotId, list_dropDown_fltForce, list_chkBx_sendFFR,
			btn_cancel, info_bookingStatus, dropDown_capacityType, img_dimension, list_txt_L3bup, info_OCDA, info_OCDC,
			info_TotalOthercharges, img_fltSegULDInfo, info_errormsg, Irregularity_Inbx_irregularityCode,
			Irregularity_Inbx_irregularityRemarks, Irregularity_Btn_OKbutton, Irregularity_Inbx_pcs,
			Irregularity_Inbx_weight, txt_product, txt_uldType, txt_noOfULD, txt_uldWt, chkBox_preventReplan,
			btn_shipperConsigneeDet, txt_dimension;
	// DGR screen
	private By btn_DGR, txt_DGRUnid, dropDown_DGRName, txt_DGRpi, txt_DGRPcs, txt_DGRwtPerPcs, btn_DGRadd,
			txt_DGRaddedUNID, txt_SpotrateId, info_rateCardId, btn_DGROk, footer_error, txt_commGrossVol,
			info_bkgReasonCode;

	// A-8254 <22/03/2018>
	private By btn_Attach_Detach, txt_awbNo_AttachWindow, btn_Attach_Detach_AttachWindow, btn_Select_SaveTemplate,
			txt_Save_template_As, txt_Brief_Description, btn_Template_Save, list_Template, info_SummaryStatus,
			info_FlightDetailsStatus, btn_ShowTransportationPlan, info_TransportationPlanResultRow,
			btn_ShipperConsignee, btn_ShipperCreateButton, btn_ConsigneeCreateButton, txt_ShipperCode,
			txt_ConsigneeCode, btn_ShipperConsigneeOK, txt_flightDetailsStatus, txt_UBRNo, info_error,
			table_RatingAndCharge, btn_bookingHistory, Tbl_flightDetailsSumarry, checkbox_flightSelectAll,
			btn_flightDetailsDelete, btn_flightDetailsAdd, dropDown_serviceClass, btn_OK_RemarksPopUp,
			btn_registerEnquiry, txtArea_remarks_newWindow, btn_autoRate, btn_OK_remarks_newWindow, btn_generic_ok,
			table_DGdetails, btn_requestSpotRate, txt_rateApplied_summaryPopUP, btn_RecoEmbargoClose,
			tbl_chooseAllotmnt_stnAllotment,

			btn_RecoEmbargoContinue, info_RecoRefId, btn_genericOk, btn_okBtn, txt_totalcharge, info_footerNotification,
			btn_spotrate, irregularityframe;

	private By btn_SelectFlight, txt_Origin_selflt, txt_Dest_selflt, btn_selectFlightList, btn_MoveToList,
			btn_SelectFlight_Ok, tbl_flightDetails,

			// new a-8254
			tab_shipmentULD, checkbox_dim, btn_dim_delete, btn_dim_close, btn_dim_CalcVolumetricWeight,
			txt_volumetricWeight, txt_dim_volume, txt_volume, txt_LAT_Time, txt_LAT_Date, btn_UniqueReferance,
			txt_UniqueReferance, btn_save_UniqueReferance, btn_chooseAllotmnt_ok, btn_chooseAllotmnt_close,

			checkbox_radioActive, txt_DGR_T1, dropDown_RMC, checkbox_table_DGdetails, btn_DGRdelete,
			lnk_rejectedSegments, tbl_rejSegments, btn_rejSegClose, chkRstrctn_btn_close, info_successMessageToast,
			txt_awbNoGenerated;

	// Faizan
	private By checkSheet_0, checkSheet_1, checkSheet_2, checkSheet_3, checkSheet_4, checkSheet_5, checkSheet_6,
			checkSheet_7, checkSheet_8, checkSheet_9, checkSheet_10, checkSheet_12, checkSheet_13, checkSheet_14,
			checkSheet_15, checkSheet_16, checkSheet_18, checkSheet_19, checkSheet_20, checkSheet_21, checkSheet_23,
			checkSheet_24, checkSheet_25, checkSheet_26, checkSheet_27, checkSheet_28, checkSheet_29, checkSheetSaveBtn,
			sel_WeightUnit, sel_VolumeUnit, checkSheetAVIothers, SelectBtnInAVICS, checkSheet_3_avi, txt_Insurance,
			txt_DVcarraiage, txt_customs, checkSheetAVIDog;

	By btn_chkRstrctn_continue;

	private By chk_boxID1, icon_CCShipment, Btn_Add, btn_PopUpicon, txt_EmergencyContactName,
			txt_EmergencyContactNumber, btn_iconOK, txt_DGRUNIDnumber, Select_ShippingName, txt_P1Value, txt_NetValue,
			txt_NoofPackges, Select_Reportable, btn_Popupadd, btn_PopupOK;

	private By logo_Embargo_err, txt_uldCommodityCode;

	private By txt_UNID;
	private By sel_shpName;
	private By sel_RMC;
	private By txt_PI;
	private By txt_TI;
	private By txt_NoofPKGs;
	private By txt_qtyPerKg;
	private By btn_addDGR;
	private By btn_saveDGR;

	private String reportFrame;

	public CAP018(WebDriver driver, String dataFileName, ExtentTest test) {
		super(driver,test);
		this.driver = driver;
		this.extentTest = test;
		initElements();
		this.dataFilePath = this.dataFilePath + dataFileName;
		this.dataFileName = dataFileName;
	}

	/**
	 * Initializes all the webelements
	 * 
	 * @author A-7681
	 */
	private void initElements() {

		screenFrame = "iCargoContentFrameCAP018";
		screenFrame2 = "iCargoContentFrameCAP016";
		screenFrame3 = "iCargoContentFrameCAP015";
		screenFrame4 = "popupContainerFrame";
		reportFrame = "reportFrame";
		txt_awbNoPrefix = MiscUtility.getWebElement(objFilepath, "CAP018_txt_awbNoPrefix");
		txt_awbNo = MiscUtility.getWebElement(objFilepath, "CAP018_txt_awbNo");
		txt_dimension = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimension");
		btn_List = MiscUtility.getWebElement(objFilepath, "CAP018_btn_list");
		txt_ULDfrst = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ULDfrst");
		txt_ULDsecnd = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ULDsecnd");
		btn_popUp_Ok = MiscUtility.getWebElement(objFilepath, "CAP018_btn_popUp_Ok");
		txt_productname = MiscUtility.getWebElement(objFilepath, "CAP018_txt_productname");
		txt_shipmentDescrptn = MiscUtility.getWebElement(objFilepath, "CAP018_txt_shipmentDescrptn");
		btn_clear = MiscUtility.getWebElement(objFilepath, "CAP018_btn_clear");
		txt_origin = MiscUtility.getWebElement(objFilepath, "CAP018_txt_origin");
		txt_dest = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dest");
		txt_agentCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_agentCode");
		txt_shippingDt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_shippingDt");
		txt_scc = MiscUtility.getWebElement(objFilepath, "CAP018_txt_scc");
		txt_commCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_commCode");
		txt_commPcs = MiscUtility.getWebElement(objFilepath, "CAP018_txt_commPcs");
		txt_commWt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_commWt");
		txt_commVol = MiscUtility.getWebElement(objFilepath, "CAP018_txt_commVol");
		txt_commAdjWt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_commAdjWt");
		btn_chooseAllotId = MiscUtility.getWebElement(objFilepath, "CAP018_btn_chooseAllotId");
		btn_save = MiscUtility.getWebElement(objFilepath, "CAP018_btn_save");
		btn_close = MiscUtility.getWebElement(objFilepath, "CAP018_btn_close");
		btn_cancel = MiscUtility.getWebElement(objFilepath, "CAP018_btn_cancel");
		info_bookingStatus = MiscUtility.getWebElement(objFilepath, "CAP018_info_bookingStatus");
		dropDown_capacityType = MiscUtility.getWebElement(objFilepath, "CAP018_dropDown_capacityType");
		img_dimension = MiscUtility.getWebElement(objFilepath, "CAP018_img_dimension");
		img_fltSegULDInfo = MiscUtility.getWebElement(objFilepath, "CAP018_img_fltSegULDInfo");
		list_Chk_Waitlisted = MiscUtility.getWebElement(objFilepath, "CAP018_list_Chk_Waitlisted");
		txt_dimPcs = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimPcs");
		txt_dimWt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimWt");
		txt_dimLength = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimLength");
		txt_dimWidth = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimWidth");
		txt_dimHeight = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimHeight");
		txt_dimVol = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dimVol");
		btn_dimOk = MiscUtility.getWebElement(objFilepath, "CAP018_btn_dimOk");
		txt_product = MiscUtility.getWebElement(objFilepath, "CAP018_txt_product");
		txt_uldType = MiscUtility.getWebElement(objFilepath, "CAP018_txt_uldType");
		txt_noOfULD = MiscUtility.getWebElement(objFilepath, "CAP018_txt_noOfULD");
		txt_uldWt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_uldWt");
		Generic_btn_ok = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		txt_uldInfoUldType = MiscUtility.getWebElement(objFilepath, "CAP018_txt_uldInfoUldType");
		txt_uldInfoNoOfUlds = MiscUtility.getWebElement(objFilepath, "CAP018_txt_uldInfoNoOfUlds");
		dropDown_uldInfoContour = MiscUtility.getWebElement(objFilepath, "CAP018_dropDown_uldInfoContour");
		btn_uldInfoOk = MiscUtility.getWebElement(objFilepath, "CAP018_btn_uldInfoOk");
		btn_ratingTab = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ratingTab");
		info_OCDA = MiscUtility.getWebElement(objFilepath, "CAP018_info_OCDA");
		info_OCDC = MiscUtility.getWebElement(objFilepath, "CAP018_info_OCDC");
		info_TotalOthercharges = MiscUtility.getWebElement(objFilepath, "CAP018_info_TotalOthercharges");
		info_bkgReasonCode = MiscUtility.getWebElement(objFilepath, "CAP018_info_bkgReasonCode");

		list_txt_fltOrigin = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltOrigin");
		list_txt_fltDest = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltDest");
		list_txt_fltNo = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltNo");
		list_txt_fltDt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ftlDt");
		list_txt_fltPcs = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltPcs");
		list_txt_fltWt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltWt");
		list_txt_fltVol = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltVol");
		list_dropDown_fltForce = MiscUtility.getWebElement(objFilepath, "CAP018_list_fltForce");
		list_chkBx_fltFSstatus = MiscUtility.getWebElement(objFilepath, "CAP018_chkBx_fltFSstatus");
		list_txt_fltAllotId = MiscUtility.getWebElement(objFilepath, "CAP018_txt_fltAllotId");
		list_chkBx_sendFFR = MiscUtility.getWebElement(objFilepath, "CAP018_chkBx_sendFFR");

		info_summaryUbrNo = MiscUtility.getWebElement(objFilepath, "CAP018_info_summaryUBRNo");
		btn_summaryOk = MiscUtility.getWebElement(objFilepath, "CAP018_btn_SummaryOk");

		btn_DGR = MiscUtility.getWebElement(objFilepath, "CAP018_btn_DGR");
		txt_DGRUnid = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DGRUnid");
		dropDown_DGRName = MiscUtility.getWebElement(objFilepath, "CAP018_dropDown_DGRName");
		txt_DGRpi = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DGRpi");
		txt_DGRPcs = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DGRPcs");
		txt_DGRwtPerPcs = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DGRwtPerPcs");
		btn_DGRadd = MiscUtility.getWebElement(objFilepath, "CAP018_btn_DGRadd");
		txt_DGRaddedUNID = MiscUtility.getWebElement(objFilepath, "CAP018_link_DGRaddedUNID");
		btn_DGROk = MiscUtility.getWebElement(objFilepath, "CAP018_btn_DGROk");
		list_txt_L3bup = MiscUtility.getWebElement(objFilepath, "CAP018_list_txt_L3bup");
		txt_queueRemarksRemark = MiscUtility.getWebElement(objFilepath, "CAP018_txt_queueRemarksRemark");
		btn_queueRemarksOk1 = MiscUtility.getWebElement(objFilepath, "CAP018_btn_queueRemarksOk");

		btn_genericYes = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
		btn_noBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_noBtn");
		info_msg = MiscUtility.getWebElement(genObjPath, "Generic_info_msg");
		txt_SpotrateId = MiscUtility.getWebElement(objFilepath, "CAP018_txt_SpotrateId");
		info_rateCardId = MiscUtility.getWebElement(objFilepath, "CAP018_info_rateCardId");
		info_error = MiscUtility.getWebElement(genObjPath, "Generic_info_error");

		info_errormsg = MiscUtility.getWebElement(objFilepath, "CAP018_info_errormsg");
		footer_error = MiscUtility.getWebElement(genObjPath, "Generic_footer_error");
		Irregularity_Inbx_irregularityCode = MiscUtility.getWebElement(objFilepath,
				"CAP018_Irregularity_Inbx_irregularityCode");
		Irregularity_Inbx_irregularityRemarks = MiscUtility.getWebElement(objFilepath,
				"CAP018_Irregularity_Inbx_irregularityRemarks");
		Irregularity_Btn_OKbutton = MiscUtility.getWebElement(objFilepath, "CAP018_Irregularity_Btn_OKbutton");
		Irregularity_Inbx_pcs = MiscUtility.getWebElement(objFilepath, "CAP018_Irregularity_Inbx_pcs");
		Irregularity_Inbx_weight = MiscUtility.getWebElement(objFilepath, "CAP018_Irregularity_Inbx_weight");

		// created by A-8254
		btn_Attach_Detach = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Attach_Detach");
		txt_awbNo_AttachWindow = MiscUtility.getWebElement(objFilepath, "CAP018_txt_awbNo_AttachWindow");
		btn_Attach_Detach_AttachWindow = MiscUtility.getWebElement(objFilepath,
				"CAP018_btn_Attach_Detach_AttachWindow");
		btn_Select_SaveTemplate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Select_SaveTemplate");
		txt_Save_template_As = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Save_template_As");
		txt_Brief_Description = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Brief_Description");
		btn_Template_Save = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Template_Save");
		list_Template = MiscUtility.getWebElement(objFilepath, "CAP018_list_Template");
		info_SummaryStatus = MiscUtility.getWebElement(objFilepath, "CAP018_info_SummaryStatus");
		info_FlightDetailsStatus = MiscUtility.getWebElement(objFilepath, "CAP018_info_FlightDetailsStatus");
		btn_ShowTransportationPlan = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShowTransportationPlan");
		info_TransportationPlanResultRow = MiscUtility.getWebElement(objFilepath,
				"CAP018_info_TransportationPlanResultRow");
		btn_ShipperConsignee = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShipperConsignee");
		btn_ShipperCreateButton = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShipperCreateButton");
		btn_ConsigneeCreateButton = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ConsigneeCreateButton");
		txt_ShipperCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ShipperCode");
		txt_ConsigneeCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ConsigneeCode");
		btn_ShipperConsigneeOK = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShipperConsigneeOK");
		txt_flightDetailsStatus = MiscUtility.getWebElement(objFilepath, "CAP018_txt_flightDetailsStatus");
		txt_UBRNo = MiscUtility.getWebElement(objFilepath, "CAP018_txt_UBRNo");
		table_RatingAndCharge = MiscUtility.getWebElement(objFilepath, "CAP018_table_RatingAndCharge");
		btn_bookingHistory = MiscUtility.getWebElement(objFilepath, "CAP018_btn_bookingHistory");
		checkbox_flightSelectAll = MiscUtility.getWebElement(objFilepath, "CAP018_checkbox_flightSelectAll");
		btn_flightDetailsDelete = MiscUtility.getWebElement(objFilepath, "CAP018_btn_flightDetailsDelete");
		btn_flightDetailsAdd = MiscUtility.getWebElement(objFilepath, "CAP018_btn_flightDetailsAdd");
		Tbl_flightDetailsSumarry = MiscUtility.getWebElement(objFilepath, "CAP018_Tbl_flightDetailsSumarry");
		dropDown_serviceClass = MiscUtility.getWebElement(objFilepath, "CAP018_dropDown_serviceClass");
		btn_OK_RemarksPopUp = MiscUtility.getWebElement(objFilepath, "CAP018_btn_OK_RemarksPopUp");
		btn_registerEnquiry = MiscUtility.getWebElement(objFilepath, "CAP018_btn_registerEnquiry");
		btn_autoRate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_autoRate");
		txtArea_remarks_newWindow = MiscUtility.getWebElement(objFilepath, "CAP018_txtArea_remarks_newWindow");
		btn_OK_remarks_newWindow = MiscUtility.getWebElement(objFilepath, "CAP018_btn_OK_remarks_newWindow");
		btn_generic_ok = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		table_DGdetails = MiscUtility.getWebElement(objFilepath, "CAP018_table_DGdetails");
		btn_requestSpotRate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_requestSpotRate");
		txt_rateApplied_summaryPopUP = MiscUtility.getWebElement(objFilepath, "CAP018_txt_rateApplied_summaryPopUP");

		// A-8255
		txt_totalcharge = MiscUtility.getWebElement(objFilepath, "CAP018_txt_totalcharge");
		btn_spotrate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_spotrate");
		btn_RecoEmbargoClose = MiscUtility.getWebElement(objFilepath, "CAP018_btn_RecoEmbargoClose");
		btn_RecoEmbargoContinue = MiscUtility.getWebElement(objFilepath, "CAP018_btn_RecoEmbargoContinue");
		info_RecoRefId = MiscUtility.getWebElement(objFilepath, "CAP018_info_RecoRefId");
		btn_genericOk = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");

		btn_genericYes = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
		btn_noBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_noBtn");
		info_msg = MiscUtility.getWebElement(genObjPath, "Generic_info_msg");
		txt_SpotrateId = MiscUtility.getWebElement(objFilepath, "CAP018_txt_SpotrateId");
		info_rateCardId = MiscUtility.getWebElement(objFilepath, "CAP018_info_rateCardId");
		info_errormsg = MiscUtility.getWebElement(objFilepath, "CAP018_info_errormsg");
		footer_error = MiscUtility.getWebElement(genObjPath, "Generic_footer_error");
		Irregularity_Inbx_irregularityCode = MiscUtility.getWebElement(objFilepath,
				"CAP018_Irregularity_Inbx_irregularityCode");
		Irregularity_Inbx_irregularityRemarks = MiscUtility.getWebElement(objFilepath,
				"CAP018_Irregularity_Inbx_irregularityRemarks");
		Irregularity_Btn_OKbutton = MiscUtility.getWebElement(objFilepath, "CAP018_Irregularity_Btn_OKbutton");
		Irregularity_Inbx_pcs = MiscUtility.getWebElement(objFilepath, "CAP018_Irregularity_Inbx_pcs");
		Irregularity_Inbx_weight = MiscUtility.getWebElement(objFilepath, "CAP018_Irregularity_Inbx_weight");

		// created by A-8254
		btn_Attach_Detach = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Attach_Detach");
		txt_awbNo_AttachWindow = MiscUtility.getWebElement(objFilepath, "CAP018_txt_awbNo_AttachWindow");
		btn_Attach_Detach_AttachWindow = MiscUtility.getWebElement(objFilepath,
				"CAP018_btn_Attach_Detach_AttachWindow");
		btn_Select_SaveTemplate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Select_SaveTemplate");
		txt_Save_template_As = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Save_template_As");
		txt_Brief_Description = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Brief_Description");
		btn_Template_Save = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Template_Save");
		list_Template = MiscUtility.getWebElement(objFilepath, "CAP018_list_Template");
		info_SummaryStatus = MiscUtility.getWebElement(objFilepath, "CAP018_info_SummaryStatus");
		info_FlightDetailsStatus = MiscUtility.getWebElement(objFilepath, "CAP018_info_FlightDetailsStatus");
		btn_ShowTransportationPlan = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShowTransportationPlan");
		info_TransportationPlanResultRow = MiscUtility.getWebElement(objFilepath,
				"CAP018_info_TransportationPlanResultRow");
		btn_ShipperConsignee = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShipperConsignee");
		btn_ShipperCreateButton = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShipperCreateButton");
		btn_ConsigneeCreateButton = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ConsigneeCreateButton");
		txt_ShipperCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ShipperCode");
		txt_ConsigneeCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_ConsigneeCode");
		btn_ShipperConsigneeOK = MiscUtility.getWebElement(objFilepath, "CAP018_btn_ShipperConsigneeOK");
		txt_flightDetailsStatus = MiscUtility.getWebElement(objFilepath, "CAP018_txt_flightDetailsStatus");
		txt_UBRNo = MiscUtility.getWebElement(objFilepath, "CAP018_txt_UBRNo");
		table_RatingAndCharge = MiscUtility.getWebElement(objFilepath, "CAP018_table_RatingAndCharge");
		btn_bookingHistory = MiscUtility.getWebElement(objFilepath, "CAP018_btn_bookingHistory");
		checkbox_flightSelectAll = MiscUtility.getWebElement(objFilepath, "CAP018_checkbox_flightSelectAll");
		btn_flightDetailsDelete = MiscUtility.getWebElement(objFilepath, "CAP018_btn_flightDetailsDelete");
		btn_flightDetailsAdd = MiscUtility.getWebElement(objFilepath, "CAP018_btn_flightDetailsAdd");
		Tbl_flightDetailsSumarry = MiscUtility.getWebElement(objFilepath, "CAP018_Tbl_flightDetailsSumarry");
		dropDown_serviceClass = MiscUtility.getWebElement(objFilepath, "CAP018_dropDown_serviceClass");
		btn_OK_RemarksPopUp = MiscUtility.getWebElement(objFilepath, "CAP018_btn_OK_RemarksPopUp");
		btn_registerEnquiry = MiscUtility.getWebElement(objFilepath, "CAP018_btn_registerEnquiry");
		btn_autoRate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_autoRate");
		txtArea_remarks_newWindow = MiscUtility.getWebElement(objFilepath, "CAP018_txtArea_remarks_newWindow");
		btn_OK_remarks_newWindow = MiscUtility.getWebElement(objFilepath, "CAP018_btn_OK_remarks_newWindow");
		btn_generic_ok = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		table_DGdetails = MiscUtility.getWebElement(objFilepath, "CAP018_table_DGdetails");
		btn_requestSpotRate = MiscUtility.getWebElement(objFilepath, "CAP018_btn_requestSpotRate");
		txt_rateApplied_summaryPopUP = MiscUtility.getWebElement(objFilepath, "CAP018_txt_rateApplied_summaryPopUP");

		/*
		 * //A-8255 txt_totalcharge = MiscUtility.getWebElement(objFilepath,
		 * "CAP018_txt_totalcharge"); btn_spotrate =
		 * MiscUtility.getWebElement(objFilepath, "CAP018_btn_spotrate");
		 * btn_RecoEmbargoClose = MiscUtility.getWebElement(objFilepath,
		 * "CAP018_btn_RecoEmbargoClose"); btn_RecoEmbargoContinue =
		 * MiscUtility.getWebElement(objFilepath,
		 * "CAP018_btn_RecoEmbargoContinue"); info_RecoRefId =
		 * MiscUtility.getWebElement(objFilepath, "CAP018_info_RecoRefId");
		 * btn_genericOk = MiscUtility.getWebElement(genObjPath,
		 * "Generic_btn_ok");
		 */

		// queue remarks
		btn_queueRemarksOk = MiscUtility.getWebElement(objFilepath, "CAP018_info_RecoRefId");
		// info_RecoRefId = MiscUtility.getWebElement(objFilepath,
		// "CAP018_info_RecoRefId");

		btn_SelectFlight = MiscUtility.getWebElement(objFilepath, "CAP018_btn_SelectFlight");
		txt_Origin_selflt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Origin_selFlight");
		txt_Dest_selflt = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Dest_selFlight");
		btn_selectFlightList = MiscUtility.getWebElement(objFilepath, "CAP018_txt_List_selFlight");
		btn_MoveToList = MiscUtility.getWebElement(objFilepath, "CAP018_btn_MoveToList");
		btn_SelectFlight_Ok = MiscUtility.getWebElement(objFilepath, "CAP018_btn_SelectFlight_ok");
		tbl_flightDetails = MiscUtility.getWebElement(objFilepath, "CAP018_tbl_flt_det");
		info_footerNotification = MiscUtility.getWebElement(genObjPath, "Generic_foot_layer");
		btn_okBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		irregularityframe = MiscUtility.getWebElement(objFilepath, "CAP018_irregularityframe");

		// nea a-8254
		tab_shipmentULD = MiscUtility.getWebElement(objFilepath, "CAP018_tab_shipmentULD");
		checkbox_dim = MiscUtility.getWebElement(objFilepath, "CAP018_checkbox_dim");
		btn_dim_delete = MiscUtility.getWebElement(objFilepath, "CAP018_btn_dim_delete");
		btn_dim_close = MiscUtility.getWebElement(objFilepath, "CAP018_btn_dim_close");
		btn_dim_CalcVolumetricWeight = MiscUtility.getWebElement(objFilepath, "CAP018_btn_dim_CalcVolumetricWeight");
		txt_volumetricWeight = MiscUtility.getWebElement(objFilepath, "CAP018_txt_volumetricWeight");
		txt_dim_volume = MiscUtility.getWebElement(objFilepath, "CAP018_txt_dim_volume");
		txt_volume = MiscUtility.getWebElement(objFilepath, "CAP018_txt_volume");
		txt_LAT_Time = MiscUtility.getWebElement(objFilepath, "CAP018_txt_LAT_Time");
		txt_LAT_Date = MiscUtility.getWebElement(objFilepath, "CAP018_txt_LAT_Date");

		btn_UniqueReferance = MiscUtility.getWebElement(objFilepath, "CAP018_btn_UniqueReferance");
		txt_UniqueReferance = MiscUtility.getWebElement(objFilepath, "CAP018_txt_UniqueReferance");
		btn_save_UniqueReferance = MiscUtility.getWebElement(objFilepath, "CAP018_btn_save_UniqueReferance");

		checkbox_radioActive = MiscUtility.getWebElement(objFilepath, "CAP018_checkbox_radioActive");
		txt_DGR_T1 = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DGR_T1");
		dropDown_RMC = MiscUtility.getWebElement(objFilepath, "CAP018_dropDown_RMC");
		checkbox_table_DGdetails = MiscUtility.getWebElement(objFilepath, "CAP018_checkbox_table_DGdetails");
		btn_DGRdelete = MiscUtility.getWebElement(objFilepath, "CAP018_btn_DGRdelete");
		/*
		 * lnk_rejectedSegments = MiscUtility.getWebElement(objFilepath,
		 * "CAP018_lnk_rejectedSegments"); tbl_rejSegments =
		 * MiscUtility.getWebElement(objFilepath, "CAP018_tbl_rejSegments");
		 * btn_rejSegClose = MiscUtility.getWebElement(objFilepath,
		 * "CAP018_btn_rejSegClose"); chkBox_preventReplan =
		 * MiscUtility.getWebElement(objFilepath,
		 * "CAP018_chkBox_preventReplan");
		 */
		info_successMessageToast = MiscUtility.getWebElement(genObjPath, "Info_successMessageToast");

		// chk_boxID1=MiscUtility.getWebElement(objFilepath,
		// "CAP018_chkSelectFirstRow_flight");
		txt_commGrossVol = MiscUtility.getWebElement(objFilepath, "CAP018_txt_commGrossVol");
		btn_chkRstrctn_continue = MiscUtility.getWebElement(objFilepath, "CAP018_chkRstrctn_btn_continue");
		chkRstrctn_btn_close = MiscUtility.getWebElement(objFilepath, "CAP018_chkRstrctn_btn_close");
		tbl_chooseAllotmnt_stnAllotment = MiscUtility.getWebElement(objFilepath,
				"CAP018_tbl_chooseAllotmnt_stnAllotment");
		MiscUtility.getWebElement(objFilepath, "CAP018_tbl_chooseAllotmnt_prdAllotment");
		btn_chooseAllotmnt_ok = MiscUtility.getWebElement(objFilepath, "CAP018_btn_chooseAllotmnt_ok");
		btn_chooseAllotmnt_close = MiscUtility.getWebElement(objFilepath, "CAP018_btn_chooseAllotmnt_close");
		// logo_Embargo_err=MiscUtility.getWebElement(objFilepath,"CAP018_err_embargo");

		// Dangerous goods new pop up A-8260
		/*
		 * txt_UNID=MiscUtility.getWebElement(objFilepath,"CAP018_txt_UNID");
		 * sel_shpName
		 * =MiscUtility.getWebElement(objFilepath,"CAP018_sel_shippingName");
		 * sel_RMC =MiscUtility.getWebElement(objFilepath,"CAP018_sel_RMC");
		 * txt_PI=MiscUtility.getWebElement(objFilepath,"CAP018_txt_PI");
		 * txt_TI=MiscUtility.getWebElement(objFilepath,"CAP018_txt_TI");
		 * txt_NoofPKGs
		 * =MiscUtility.getWebElement(objFilepath,"CAP018_txt_NoofPkgs");
		 * txt_qtyPerKg
		 * =MiscUtility.getWebElement(objFilepath,"CAP018_txt_qtyPerKG");
		 * btn_addDGR
		 * =MiscUtility.getWebElement(objFilepath,"CAP018_btn_AddCapDG");
		 * btn_saveDGR
		 * =MiscUtility.getWebElement(objFilepath,"CAP018_btn_saveDG");
		 */

		// Shipper Consignee Details
		// Created A-8452
		checkSheet_0 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet0");
		checkSheet_1 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet1");
		checkSheet_2 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet2");
		checkSheet_3 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet3");
		checkSheet_4 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet4");
		checkSheet_5 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet5");
		checkSheet_6 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet6");
		checkSheet_7 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet7");
		checkSheet_8 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet8");
		checkSheet_9 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet9");
		checkSheet_10 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet10");
		checkSheet_12 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet12");
		checkSheet_13 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet13");
		checkSheet_14 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet14");
		checkSheet_15 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet15");
		checkSheet_16 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet16");
		checkSheet_18 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet18");
		checkSheet_19 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet19");
		checkSheet_20 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet20");
		checkSheet_21 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet21");
		checkSheet_23 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet23");
		checkSheet_24 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet24");
		checkSheet_25 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet25");
		checkSheet_26 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet26");
		checkSheet_27 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet27");
		checkSheet_28 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet28");
		checkSheet_29 = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet29");
		checkSheetAVIDog = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheetAVIDog");
		checkSheetSaveBtn = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheetSaveBtn");
		checkSheet_3_avi = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheet_3_avi");

		sel_WeightUnit = MiscUtility.getWebElement(objFilepath, "CAP018_sel_WeightUnit");
		sel_VolumeUnit = MiscUtility.getWebElement(objFilepath, "CAP018_sel_VolumeUnit");

		checkSheetAVIothers = MiscUtility.getWebElement(objFilepath, "CAP018_checkSheetAVIothers");
		SelectBtnInAVICS = MiscUtility.getWebElement(objFilepath, "CAP018_SelectBtnInAVICS");

		txt_DVcarraiage = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DVcarraiage");
		txt_Insurance = MiscUtility.getWebElement(objFilepath, "CAP018_txt_Insurance");
		txt_customs = MiscUtility.getWebElement(objFilepath, "CAP018_txt_customs");
		icon_CCShipment = MiscUtility.getWebElement(objFilepath, "CAP018_icon_CCShipment");
		Btn_Add = MiscUtility.getWebElement(objFilepath, "CAP018_Btn_Add");

		// DGR Shalini
		btn_PopUpicon = MiscUtility.getWebElement(objFilepath, "CAP018_btn_PopUpicon");
		txt_EmergencyContactName = MiscUtility.getWebElement(objFilepath, "CAP018_txt_EmergencyContactName");
		txt_EmergencyContactNumber = MiscUtility.getWebElement(objFilepath, "CAP018_txt_EmergencyContactNumber");
		btn_iconOK = MiscUtility.getWebElement(objFilepath, "CAP018_btn_iconOK");
		txt_DGRUNIDnumber = MiscUtility.getWebElement(objFilepath, "CAP018_txt_DGRUNIDnumber");
		Select_ShippingName = MiscUtility.getWebElement(objFilepath, "CAP018_Select_ShippingName");
		txt_P1Value = MiscUtility.getWebElement(objFilepath, "CAP018_txt_P1Value");
		txt_NetValue = MiscUtility.getWebElement(objFilepath, "CAP018_txt_NetValue");
		txt_NoofPackges = MiscUtility.getWebElement(objFilepath, "CAP018_txt_NoofPackges");
		Select_Reportable = MiscUtility.getWebElement(objFilepath, "CAP018_Select_Reportable");
		btn_Popupadd = MiscUtility.getWebElement(objFilepath, "CAP018_btn_Popupadd");
		btn_PopupOK = MiscUtility.getWebElement(objFilepath, "CAP018_btn_PopupOK");
		// sharath
		txt_uldCommodityCode = MiscUtility.getWebElement(objFilepath, "CAP018_txt_uldCommodityCode");
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
	private String getAWB(StockType stock, String awbPre, String awb, String... FullawbNo) {

		String awbNo = "";

		// while(true){
		awbNo = BizUtility.createAWBNum(stock);
		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);

		minWait();

		// minWait();
		// if(verifyElementPresent(info_msg) &&
		// waitForElement(info_msg).getText().trim().equals("AWB does not
		// exist.Do you want to capture?")){
		// click(btn_genericYes);
		// break;
		// }

		driver.switchTo().defaultContent();
		// if(waitForElement(info_footerNotification).getText().trim().equals("AWB
		// does not exist. Please continue to create new AWB data.")){
		// waitForFrameAndSwitch(screenFrame);
		// break;
		// }
		if (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);

		} else if (verifyElementPresent(btn_okBtn)) {
			click(btn_okBtn);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		// click(btn_clear);
		// minWait();
		// }
		driver.switchTo().defaultContent();
		PropertyHandler.setPropValue(dataFilePath, awb, awbNo);
		try {
			// String fullAWBNo = awbPre + awbNo;
			PropertyHandler.setPropValue(dataFilePath, FullawbNo[0], awbPre + "-" + awbNo);
		} catch (Exception e) {
			;
		}
		return awbNo;

	}

	//

	/**
	 * check whether AWB is already used or not and saves the new AWB to the
	 * property file corresponding to the key awb
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @return
	 * @author A-7681
	 */

	public CAP018 checkAWB(String stockType, String awbPre, String awb, String... fullAWB) {

		String awbNo;
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		// int count=0;
		/*
		 * while (true) { awbNo = BizUtility.createAWBNum(stock);
		 * enterKeys((txt_awbNoPrefix), awbPre); enterKeys(txt_awbNo, awbNo);
		 * click(btn_List); driver.switchTo().defaultContent(); if
		 * (verifyElementPresent(btn_genericYes)) {
		 * 
		 * if (waitForElement(info_msg).getText().contains("executed")) {
		 * click(btn_noBtn); waitForFrameAndSwitch(screenFrame); continue; }
		 * click(btn_genericYes); waitForFrameAndSwitch(screenFrame); break; }
		 * waitForFrameAndSwitch(screenFrame); click(btn_clear); // count++; //
		 * if (count == 20){ // throw new AWBRangeNotCorrect(); // } }
		 * PropertyHandler.setPropValue(dataFilePath, awb, awbNo); try{
		 * PropertyHandler.setPropValue(dataFilePath, fullAWB[0],
		 * awbPre+"-"+awbNo); }catch(Exception e){} minWait(); return this;
		 */

		// un comment above code and comment below if running in 4.6.3
		while (true) {
			awbNo = BizUtility.createAWBNum(stock);
			enterKeys(txt_awbNoPrefix, awbPre);
			enterKeys(txt_awbNo, awbNo);
			click(btn_List);

			if (verifyElementPresent(info_successMessageToast) && waitForElement(info_successMessageToast).getText()
					.trim().equals("AWB does not exist. Please continue to create new AWB data.")) {
				break;
			}
			driver.switchTo().defaultContent();

			if (verifyElementPresent(btn_noBtn)) {
				click(btn_noBtn);

			} else if (verifyElementPresent(btn_okBtn)) {
				click(btn_okBtn);
			}
			minWait();
			waitForFrameAndSwitch(screenFrame);
			click(btn_clear);
			minWait();
		}
		PropertyHandler.setPropValue(dataFilePath, awb, awbNo);
		try {
			// String fullAWBNo = awbPre + awbNo;
			PropertyHandler.setPropValue(dataFilePath, fullAWB[0], awbPre + "-" + awbNo);
		} catch (Exception e) {

		}
		return this;
	}

	/**
	 * lists blacklisted awb
	 * 
	 * @param awbPre
	 * @author A-6836
	 */
	public CAP018 CheckBlacklistedAWB(String AWBNo, String awbPre) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, AWBNo);
		click(btn_List);
		minWait();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("AWB does not exist.Do you want to capture?");
		click(btn_genericYes);
		minWait();
		waitForFrameAndSwitch(screenFrame);
		String status = waitForElement(info_error).getText();
		System.out.println(status);
		// Assert.assertTrue(waitForElement(info_errormsg).getText().contains("The
		// specified AWB number is blacklisted. Cannot proceed."),
		// "ERROR :Able to proceed booking for blacklisted AWB.");
		Assert.assertTrue(status.contains("The specified AWB  number is blacklisted. Cannot proceed."),
				"ERROR :Able to proceed booking for blacklisted AWB.");
		minWait();

		return this;
	}

	/**
	 * Performs a simple booking with revoked AWB with the given parameters.
	 * 
	 * @param awbPre
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @return
	 * @author A-6836
	 */
	public CAP018 BookingwithRevokedAWb(String AWBNo, String awbPre, String origin, String dest, String agentCode,
			String scc, String commCode, String pcs, String wt, String vol, String fltNo, String fltDt, String ubr) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, AWBNo);
		click(btn_List);
		minWait();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("AWB does not exist.Do you want to capture?");
		click(btn_genericYes);
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_product, "");
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);

		save(ubr);
		return this;
	}

	/**
	 * lists a given awb
	 * 
	 * @param awbPre
	 * @param awb
	 * @author A-7681
	 */
	private void listAwb(String awbPre, String awb) {

		click(btn_clear);
		minWait();
		enterKeys(waitForElement(txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awb);
		click(btn_List);
		minWait();
	}

	public CAP018 list(String awbPre, String awb) {

		click(btn_clear);
		minWait();
		enterKeys(waitForElement(txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awb);
		click(btn_List);
		minWait();
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author Shalini
	 */
	public CAP018 simpleBooking(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String ubr,
			String bookingType, String ULDType, String noOfULD, String CarrierCode, String commCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		noOfULD = PropertyHandler.getPropValue(dataFilePath, noOfULD);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_agentCode, agentCode);
		enterKeys(txt_agentCode, Keys.TAB);
		minWait();
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_shipmentDescrptn, "GEN");
		minWait();
		// setDimensions(pcs, wt);
		enterKeys(txt_productname, Product);

		enterKeys(txt_uldType, ULDType);
		enterKeys(txt_noOfULD, noOfULD);
		enterKeys(By.name("uldWeight"), wt);
		enterKeys(By.name("uldCommodityCode"), "GENERAL");

		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		enterKeys(list_txt_fltVol, vol);
		
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		driver.findElement(btn_save).click();
		try {
			handleAlert("Accept", "CAP018");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			waitForFrameAndSwitch(screenFrame);
		}
		continueEmbargo();
		extentTest.log(LogStatus.INFO, "Saved all the booking details");
		// maxWait();
		// maxWait();
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);
		if (!ubrNo.equals(null) || !awb.equals(null)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with AWB No: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
		} else {
			extentTest.log(LogStatus.FAIL,
					"Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			Assert.fail("Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
		}
		return this;
	}

	public CAP018 writeDatatoPropertyfile(String Path, String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String ubr, String bookingType, String ULDType, String noOfULD, String CarrierCode, String uldNo) {
		Path = PropertyHandler.getPropValue(dataFilePath, Path);

		Path = "./resources/TestData/BASE/" + Path;
		extentTest.log(LogStatus.INFO, "Started writing the data to property file");
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		noOfULD = PropertyHandler.getPropValue(dataFilePath, noOfULD);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		// Writing to Another Property File
		// PropertyHandler.setPropValue(Path, "prefix", prefix);
		// PropertyHandler.setPropValue(Path, "CarrierCode", CarrierCode);
		// PropertyHandler.setPropValue(Path, "FlightNo", FlightNo);
		PropertyHandler.setPropValue(Path, "awbno", awbno);
		// PropertyHandler.setPropValue(Path, "dest", dest);
		// PropertyHandler.setPropValue(Path, "agentCode", agentCode);
		// PropertyHandler.setPropValue(Path, "Product", Product);
		// PropertyHandler.setPropValue(Path, "pcs", pcs);
		// PropertyHandler.setPropValue(Path, "wt", wt);
		// PropertyHandler.setPropValue(Path, "vol", vol);
		// PropertyHandler.setPropValue(Path, "ULDType", ULDType);
		// PropertyHandler.setPropValue(Path, "noOfULD", noOfULD);
		// PropertyHandler.setPropValue(Path, "fltDt", fltDt);
		// PropertyHandler.setPropValue(Path, "carrierwithFlightno",
		// carrierWithFlightno);
		//
		// PropertyHandler.setPropValue(Path, "uldNo",
		// uldNo);
		extentTest.log(LogStatus.INFO, "Data successfully written to the property file");
		return this;

	}

	/**
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param FullawbNo
	 * @param product
	 * @return
	 */
	public CAP018 Errorcheck_invalidProductHandlingCode(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String product) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		product = PropertyHandler.getPropValue(dataFilePath, product);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);

		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_product, product);
		// setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);

		click(btn_save);
		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		waitForFrameAndSwitch(screenFrame);

		driver.switchTo().defaultContent();

		Assert.assertTrue(waitForElement(info_msg).getText()
				.contains("Invalid Product Code-Priority-Transportation Mode-Handling code combination."));

		click(Generic_btn_ok);

		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 BookingAfterCapture(String awbPrefix, String awb, String origin, String dest, String carrier,
			String fltNum, String fltDt, String ubr) {

		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		carrier = PropertyHandler.getPropValue(dataFilePath, carrier);
		fltNum = PropertyHandler.getPropValue(dataFilePath, fltNum);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		enterKeys(txt_awbNoPrefix, awbPrefix);
		enterKeys(txt_awbNo, awb);
		click(btn_List);

		minWait();

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNum);
		enterKeys(list_txt_fltDt, fltDt);

		save(ubr);
		return this;
	}



	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param uldDtls
	 *            : given in order type and wt
	 * @return
	 * @author A-7681
	 */
	public CAP018 simpleBookingWithAdjWtAndDim(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String adjWt,
			String chargWt, String fltNo, String fltDt, String ubr, String bookingType, String FullawbNo,
			String... uldDtls) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		adjWt = PropertyHandler.getPropValue(dataFilePath, adjWt);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_commAdjWt, adjWt);
		setDimensions(pcs, wt, chargWt);

		if (uldDtls.length != 0) {
			enterKeys(txt_uldType, uldDtls[0]);
			enterKeys(txt_noOfULD, "1");
			enterKeys(txt_uldWt, uldDtls[1]);
		}

		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}
		selectByText(list_dropDown_fltForce, "Confirm");

		save(ubr);
		return this;
	}

	public CAP018 simpleBookingEY(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, String awbNumber, boolean... forceQueue) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		PropertyHandler.setPropValue(dataFilePath, awbNumber, awbNo);

		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}
		if (forceQueue.length != 0 && forceQueue[0]) {
			selectByText(list_dropDown_fltForce, "Queue");
		} else {
			selectByText(list_dropDown_fltForce, "Confirm");
		}

		saveEY(ubr);
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-7681
	 */
	public CAP018 simpleBookingWithOutForceConfirm(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, boolean... forceQueue) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}
		if (forceQueue.length != 0 && forceQueue[0]) {
			selectByText(list_dropDown_fltForce, "Queue");
		}

		save(ubr);
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-6545
	 */
	public CAP018 simpleBooking_diffdim(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions("10", "200");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		if (bookingType.equals("OAL")) {

			check(list_chkBx_sendFFR);
		}
		// selectByText(list_dropDown_fltForce, "Confirm");

		save(ubr);
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-7681
	 */
	public CAP018 simpleBooking_BUP(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_L3bup, "1");

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}
		// selectByText(list_dropDown_fltForce, "Confirm");
		enterKeys(txt_product, "");
		save(ubr);
		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param pcs12
	 * @param wt12
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param volDtls
	 *            : in order vol,vol11,vol12
	 * @return
	 * @author A-6545
	 */

	public CAP018 multiFlightBooking(String stockType, String awbPre, String awb, String origin, String dest,
			String origin2, String dest2, String agentCode, String scc, String commCode, String pcs, String wt,
			String pcs11, String wt11, String pcs12, String wt12, String fltNo, String fltNo1, String fltDt, String ubr,
			String bookingType, String FullawbNo, String... volDtls) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);
		pcs12 = PropertyHandler.getPropValue(dataFilePath, pcs12);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);
		wt12 = PropertyHandler.getPropValue(dataFilePath, wt12);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_product, "");
		if (volDtls.length != 0) {
			enterKeys(txt_commVol, PropertyHandler.getPropValue(dataFilePath, volDtls[0]));
		}

		setDimensions(pcs, wt);
		minWait();
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		if (volDtls.length != 0) {
			enterKeys(list_txt_fltVol, PropertyHandler.getPropValue(dataFilePath, volDtls[1]));
		}
		// setULDInfo();
		scrollToView(list_dropDown_fltForce);
		// selectByIndex(list_dropDown_fltForce, 2);

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs12);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt12);
		if (volDtls.length != 0) {
			enterKeys(getWebElements(list_txt_fltVol).get(1), PropertyHandler.getPropValue(dataFilePath, volDtls[2]));
		}
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		// selectByIndex(getWebElements(list_dropDown_fltForce).get(1), 2);
		selectByText(getWebElements(list_dropDown_fltForce).get(1), "Confirm");

		save(ubr);
		return this;
	}

	public CAP018 multiFlightBooking_withoutforceQueue(String stockType, String awbPre, String awb, String origin,
			String dest, String origin2, String dest2, String agentCode, String scc, String commCode, String pcs,
			String wt, String pcs11, String wt11, String pcs12, String wt12, String fltNo, String fltNo1, String fltDt,
			String ubr, String bookingType, String FullawbNo, String... volDtls) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);
		pcs12 = PropertyHandler.getPropValue(dataFilePath, pcs12);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);
		wt12 = PropertyHandler.getPropValue(dataFilePath, wt12);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_product, "");
		if (volDtls.length != 0) {
			enterKeys(txt_commVol, PropertyHandler.getPropValue(dataFilePath, volDtls[0]));
		}

		setDimensions(pcs, wt);
		minWait();
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		if (volDtls.length != 0) {
			enterKeys(list_txt_fltVol, PropertyHandler.getPropValue(dataFilePath, volDtls[1]));
		}

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs12);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt12);
		if (volDtls.length != 0) {
			enterKeys(getWebElements(list_txt_fltVol).get(1), PropertyHandler.getPropValue(dataFilePath, volDtls[2]));
		}
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));

		save(ubr);
		return this;
	}

	public CAP018 multiFlightBookingWithServiceClass(String stockType, String awbPre, String awb, String origin,
			String dest, String origin2, String dest2, String agentCode, String scc, String commCode,
			String serviceClass, String pcs, String wt, String pcs11, String wt11, String pcs12, String wt12,
			String fltNo, String fltNo1, String fltDt, String ubr, String bookingType, String FullawbNo,
			String... volDtls) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		serviceClass = PropertyHandler.getPropValue(dataFilePath, serviceClass);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);
		pcs12 = PropertyHandler.getPropValue(dataFilePath, pcs12);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);
		wt12 = PropertyHandler.getPropValue(dataFilePath, wt12);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_product, "");
		selectByText(dropDown_serviceClass, serviceClass);
		if (volDtls.length != 0) {
			enterKeys(txt_commVol, PropertyHandler.getPropValue(dataFilePath, volDtls[0]));
		}

		setDimensions(pcs, wt);
		minWait();
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		if (volDtls.length != 0) {
			enterKeys(list_txt_fltVol, PropertyHandler.getPropValue(dataFilePath, volDtls[1]));
		}
		// setULDInfo();
		scrollToView(list_dropDown_fltForce);
		// selectByIndex(list_dropDown_fltForce, 2);

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs12);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt12);
		if (volDtls.length != 0) {
			enterKeys(getWebElements(list_txt_fltVol).get(1), PropertyHandler.getPropValue(dataFilePath, volDtls[2]));
		}
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		// selectByIndex(getWebElements(list_dropDown_fltForce).get(1), 2);
		selectByText(getWebElements(list_dropDown_fltForce).get(1), "Confirm");

		save(ubr);
		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-6545
	 */

	public CAP018 multiFlightBooking_F2(String stockType, String awbPre, String awb, String origin, String dest,
			String origin2, String dest2, String agentCode, String scc, String commCode, String pcs, String wt,
			String pcs11, String wt11, String fltNo, String fltNo1, String fltDt, String ubr, String bookingType,
			String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		// setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, origin2);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);

		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, "Confirm");

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt11);
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		selectByText(getWebElements(list_dropDown_fltForce).get(1), "Confirm");

		save(ubr);
		return this;
	}

	/**
	 * method for Select flight booking multileg for EY sanity
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param origin2
	 * @param dest2
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @return
	 * @author A-8260
	 */

	public CAP018 multilegBookingSelectFlightButton(String stockType, String awbPre, String awb, String origin,
			String dest, String origin2, String dest2, String agentCode, String scc, String commCode, String pcs,
			String wt, String pcs11, String wt11, String fltNo, String fltNo1, String fltDt, String ubr,
			String bookingType, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		// select flight button

		click(btn_SelectFlight);
		minWait();
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		click(btn_selectFlightList);
		enterKeys(txt_Origin_selflt, origin);
		enterKeys(txt_Dest_selflt, dest);
		click(btn_selectFlightList);
		tblSelectRowByColValue(tbl_flightDetails, 1, 2, fltNo);
		click(btn_MoveToList);
		enterKeys(txt_Origin_selflt, origin2);
		enterKeys(txt_Dest_selflt, dest2);
		click(btn_selectFlightList);
		tblSelectRowByColValue(tbl_flightDetails, 1, 2, fltNo);
		click(btn_MoveToList);
		minWait();
		click(btn_SelectFlight_Ok);

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		selectByText(getWebElements(list_dropDown_fltForce).get(1), "Confirm");

		click(btn_save);
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);

			else
				break;

		}

		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-6545
	 */

	public CAP018 multiFlightBooking_F2_Waitlisted(String stockType, String awbPre, String awb, String origin,
			String dest, String origin2, String dest2, String agentCode, String scc, String commCode, String pcs,
			String wt, String pcs11, String wt11, String fltNo, String fltNo1, String fltDt, String ubr,
			String bookingType, String FullawbNo, String ForceStatus) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		check(list_Chk_Waitlisted);

		scrollToView(list_dropDown_fltForce);
		// selectByText(list_dropDown_fltForce,ForceStatus);

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt11);

		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		// selectByText(getWebElements(list_dropDown_fltForce).get(1),
		// ForceStatus);

		save(ubr);
		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param pcs12
	 * @param wt12
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-6545
	 */
	public CAP018 ConnectionFlightBooking(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String pcs11, String wt11,
			String fltNo, String fltDt, String ubr, String bookingType, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		setULDInfo();
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, "Confirm");

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(1), pcs11);
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		selectByText(getWebElements(list_dropDown_fltForce).get(1), "Confirm");

		save(ubr);
		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param pcs12
	 * @param wt12
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-6545
	 */
	public CAP018 ConnectionFlightBooking_closedstation(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String pcs11,
			String wt11, String fltNo, String fltDt, String ubr, String bookingType, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		setULDInfo();
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, "Confirm");

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(1), pcs11);
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		selectByText(getWebElements(list_dropDown_fltForce).get(1), "Confirm");

		click(btn_save);
		while (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);
		}
		Assert.assertTrue(waitForElement(info_msg).getText().contains("The station  FRA is closed for booking"));

		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param pcs12
	 * @param wt12
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-6545
	 */
	public CAP018 ConnectionFlightBooking_withoutAWB(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String pcs11,
			String wt11, String fltNo, String fltDt, String ubr, String bookingType) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		click(btn_List);
		// driver.switchTo().defaultContent();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		setULDInfo();
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, "Confirm");

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(1), pcs11);
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		// selectByText(getWebElements(list_dropDown_fltForce).get(1),
		// "Confirm");

		save(ubr);
		return this;
	}

	/**
	 * Performs a multiflight booking
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param pcs11
	 * @param wt11
	 * @param pcs12
	 * @param wt12
	 * @param fltNo
	 * @param fltNo1
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author A-7681
	 */
	public CAP018 multiFlightBookingDGR(String stockType, String awbPre, String awb, String origin, String dest,
			String origin2, String dest2, String agentCode, String scc, String commCode, String pcs, String wt,
			String pcs11, String wt11, String pcs12, String wt12, String fltNo, String fltNo1, String fltDt, String ubr,
			String bookingType, String unid, String dgrName, String pi, String noOfPkg, String wtPerPkg,
			String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);
		pcs12 = PropertyHandler.getPropValue(dataFilePath, pcs12);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);
		wt12 = PropertyHandler.getPropValue(dataFilePath, wt12);
		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		unid = PropertyHandler.getPropValue(dataFilePath, unid);
		dgrName = PropertyHandler.getPropValue(dataFilePath, dgrName);
		pi = PropertyHandler.getPropValue(dataFilePath, pi);
		noOfPkg = PropertyHandler.getPropValue(dataFilePath, noOfPkg);
		wtPerPkg = PropertyHandler.getPropValue(dataFilePath, wtPerPkg);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest2);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		// setULDInfo();
		// scrollToView(list_dropDown_fltForce);
		// selectByText(list_dropDown_fltForce, "Confirm");

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs12);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt12);
		if (bookingType.equals("OAL")) {
			check(getWebElements(list_chkBx_sendFFR).get(1));
		}
		// scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		// selectByText(getWebElements(list_dropDown_fltForce).get(1),
		// "Confirm");

		// captureDGRGoods(unid, dgrName, pi, noOfPkg, wtPerPkg);
		captureDGRGoodsPopUp(unid, dgrName, pi, noOfPkg, wtPerPkg);

		save(ubr);
		// krishna
		clear();
		return this;
	}

	/**
	 * Capture the DGR goods for an AWB
	 * 
	 * @param unid
	 * @param pi
	 * @param noOfPkg
	 * @param wtPerPkg
	 */
	private void captureDGRGoods(String unid, String dgrName, String pi, String noOfPkg, String wtPerPkg) {

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
		return;
	}

	private void captureDGRGoodsPopUp(String unid, String dgrName, String pi, String noOfPkg, String wtPerPkg) {
		click(btn_DGR);
		// waitForFrameAndSwitch(reportFrame);
		maxWait();
		waitForFrameAndSwitch(screenFrame4);

		waitForElement(txt_UNID);
		enterKeys(txt_UNID, unid);
		selectByText(sel_shpName, dgrName);
		selectByIndex(sel_RMC, 1);
		enterKeys(txt_TI, "1");
		enterKeys(txt_PI, pi);
		enterKeys(txt_NoofPKGs, noOfPkg);
		enterKeys(txt_qtyPerKg, wtPerPkg);

		click(btn_addDGR);
		click(btn_saveDGR);
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();
	}

	/**
	 * Sets the ULD info in the filght details session
	 */
	private void setULDInfo() {
		click(img_fltSegULDInfo);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_uldInfoUldType, "AKE");
		enterKeys(txt_uldInfoNoOfUlds, "1");
		selectByText(dropDown_uldInfoContour, "L7");
		click(btn_uldInfoOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
	}

	/**
	 * Sets the dimensions for the commodity
	 * 
	 * @param pcs
	 * @param wt
	 * @author A-7681
	 */
	private void setDimensions(String pcs, String wt, String... volmetricWt) {

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, pcs);
		enterKeys(txt_dimWt, wt);
		enterKeys(txt_dimLength, "10");
		enterKeys(txt_dimWidth, "10");
		enterKeys(txt_dimHeight, "10" + Keys.TAB);
		float vol = Float.parseFloat(waitForElementVisible(txt_dimVol).getAttribute("value"));

		try {
			String volWt = String.valueOf(Math.round((double) (vol * 1000 / 6)));
			PropertyHandler.setPropValue(dataFilePath, volmetricWt[0], volWt);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		click(btn_dimOk);
		
		if (getNumberOfWindows() == 2 && verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();

		}
		switchToFirstWindow();
		
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);

	}

	private void setDimensions2(String pcs, String wt, String... volmetricWt) {

		click(By.xpath("(//img[contains(@id,'dimensionImg')])[2]"));
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, pcs);
		enterKeys(txt_dimWt, wt);
		enterKeys(txt_dimLength, "10");
		enterKeys(txt_dimWidth, "10");
		enterKeys(txt_dimHeight, "10" + Keys.TAB);
		float vol = Float.parseFloat(waitForElementVisible(txt_dimVol).getAttribute("value"));

		try {
			String volWt = String.valueOf(Math.round((double) (vol * 1000 / 6)));
			PropertyHandler.setPropValue(dataFilePath, volmetricWt[0], volWt);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		click(btn_dimOk);
		if (getNumberOfWindows() == 2 && verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();

		}
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

	}

	/**
	 * Performs booking with a allotment id
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param allotId
	 * @param ubr
	 * @return
	 * @author A-7681
	 */
	public CAP018 bookingWithAllotId(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String allotId, String ubr, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);

		getAllotID(allotId);
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, "Confirm");

		save(ubr);
		return this;
	}

	/**
	 * Performs booking with a allotment id
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param allotId
	 * @param ubr
	 * @return
	 * @author A-8260
	 */
	public CAP018 bookingWithGlobalCustomerAllotId(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String allotId, String ubr, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(chk_boxID1);
		getAllotID_GlobalCustomer(allotId);
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, "Confirm");

		save(ubr);
		return this;
	}

	public CAP018 verify_Spotrate(String awbPre, String awbNo, String SpotRateID, boolean isSpot) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		SpotRateID = PropertyHandler.getPropValue(dataFilePath, SpotRateID);

		listAwb(awbPre, awbNo);
		minWait();
		click(btn_ratingTab);
		minWait();
		scrollToView(txt_SpotrateId);
		if (isSpot) {
			String tempId = waitForElementVisible(txt_SpotrateId).getAttribute("value");
			System.out.println(tempId);
			Assert.assertEquals(tempId, SpotRateID);
		} else {

			waitForElement(txt_SpotrateId).getAttribute("value").isEmpty();

		}
		click(waitForElement(btn_clear));
		minWait();

		return this;

	}

	/**
	 * Selects an allotment id for a flight
	 * 
	 * @param allotID
	 * @author A-7681
	 */
	private void getAllotID(String allotID) {

		click(waitForElement(btn_chooseAllotId));
		waitForNewWindow(2);
		switchToSecondWindow();
		click(waitForElement(By.xpath("//*[@id='stationAllotment']/tbody/tr/td[3][contains(text()," + allotID
				+ ")]/preceding-sibling::td[2]/input")));
		click(waitForElement(By.id("CMP_Capacity_Booking_ChooseAllotment_Ok_Button")));

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

	}

	private void getAllotID_GlobalCustomer(String allotID) {

		click(waitForElement(btn_chooseAllotId));
		waitForNewWindow(2);
		switchToSecondWindow();
		click(waitForElement(By
				.xpath("(//*[text()='Customer Allotment']/../following-sibling::div//table)[2]/tbody//td[3][contains(text(),"
						+ allotID + ")]/preceding-sibling::td[2]/input")));
		click(waitForElement(By.id("CMP_Capacity_Booking_ChooseAllotment_Ok_Button")));

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

	}

	/**
	 * Clears the form
	 * 
	 * @return
	 * @author A-7681
	 */
	public CAP018 clear() {

		click(btn_clear);
		minWait();
		return this;
	}

	/**
	 * Saves a booking and writes the ubr number to the data file
	 * 
	 * @param ubr
	 * @param screenName
	 */
	private void save(String ubr) {

		String screenFrameName = screenFrame;

		/*
		 * if (screenName.length > 0) { screenFrameName = screenName[0]; }
		 */
		try {
			enterKeys(txt_product, "");
		} catch (InvalidElementStateException e) {
			;
		}

		click(btn_save);
		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		if (verifyNumberOfWindows(2)) {
			switchToSecondWindow();
			if (driver.getTitle().toUpperCase().contains("queuing".toUpperCase())) {
				enterKeys(txt_queueRemarksRemark, "Queuing remarks by automation");
				minWait();
				scrollToView(btn_queueRemarksOk1);
				click(btn_queueRemarksOk1);
				switchToFirstWindow();
			} else {
				switchBackToFirstWindow();
			}
			waitForFrameAndSwitch(screenFrameName);
		}

		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);

			else
				break;

		}

		waitForFrameAndSwitch(screenFrameName);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();

		minWait();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrameName);

	}

	/**
	 * Saves a booking and writes the ubr number to the data file
	 * 
	 * @param ubr
	 * @param screenName
	 */
	private void saveEY(String ubr, String... screenName) {

		String screenFrameName = screenFrame;

		if (screenName.length > 0) {
			screenFrameName = screenName[0];
		}
		try {
			enterKeys(txt_product, "");
		} catch (InvalidElementStateException e) {
			;
		}

		click(btn_save);
		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		// if (verifyElementPresent(btn_genericYes))
		// click(btn_genericYes);

		if (verifyNumberOfWindows(2)) {
			switchToSecondWindow();
			if (driver.getTitle().toUpperCase().contains("queuing".toUpperCase())) {
				enterKeys(txt_queueRemarksRemark, "Queuing remarks by automation");
				minWait();
				scrollToView(btn_queueRemarksOk1);
				click(btn_queueRemarksOk1);
			}
			switchToFirstWindow();
			waitForFrameAndSwitch(screenFrameName);
		}

		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);

			else
				break;

		}

		waitForFrameAndSwitch(screenFrameName);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();

		minWait();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrameName);

	}

	/**
	 * Cancel a booking for awb
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @return
	 * @author A-7681
	 */
	public CAP018 cancelBooking(String awbPre, String awbNo) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		listAwb(awbPre, awbNo);

		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(btn_genericYes))
			click(btn_genericYes);
		waitForFrameAndSwitch(screenFrame);
		minWait();

		click(btn_cancel);
		driver.switchTo().defaultContent();
		click(btn_genericYes);
		minWait();
		waitForFrameAndSwitch(screenFrame);
		waitForFrameAndSwitch("popupContainerFrame");

		maxWait();

		enterKeys(waitForElement(Irregularity_Inbx_irregularityCode), "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		click(Irregularity_Btn_OKbutton);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		Assert.assertTrue(waitForElementVisible(info_bookingStatus).getAttribute("value").equalsIgnoreCase("CANCELLED"),
				"The AWB is not in the cancel state");
		return this;
	}

	/**
	 * verifies FS split for a booking
	 * 
	 * @param awbPre
	 * @param awb
	 * @param allotId
	 * @return
	 * @author A-7681
	 */
	public CAP018 verifyFSsplit(String awbPre, String awb, String allotId) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		allotId = PropertyHandler.getPropValue(dataFilePath, allotId);
		//
		listAwb(awbPre, awb);
		minWait();

		Assert.assertTrue(
				waitForElement(getWebElements(list_txt_fltAllotId).get(0)).getAttribute("value").equals(allotId),
				"The allotment ID is not matching");
		Assert.assertTrue(waitForElement(getWebElements(list_txt_fltAllotId).get(1)).getAttribute("value").equals("FS"),
				"The allotment ID is not matching");
		Assert.assertTrue(waitForElement(getWebElements(list_chkBx_fltFSstatus).get(1)).isEnabled());

		return this;

	}

	/**
	 * Verify the allotment ID present for flight booking
	 * 
	 * @param allotId
	 * @return
	 * @author A-7681
	 */
	public CAP018 verifyAllotID(String allotId) {

		// awb = PropertyHandler.getPropValue(dataFilePath, awb);
		allotId = PropertyHandler.getPropValue(dataFilePath, allotId);
		//
		// listAwb(awbPre, awb);
		minWait();
		Assert.assertTrue(
				waitForElement(getWebElements(list_txt_fltAllotId).get(0)).getAttribute("value").equals(allotId),
				"The allotment ID is not matching");

		return this;

	}

	/**
	 * update the flight to a flight with allotment after navigation from CAP016
	 * screen
	 * 
	 * @param fullFltNo
	 * @param ubr
	 * @return
	 * @author A-7681
	 */
	public CAP018 updateFlightWithAllotmentCAP016(String fullFltNo, String ubr) {

		fullFltNo = PropertyHandler.getPropValue(dataFilePath, fullFltNo);
		selectByText(dropDown_capacityType, "Allotment");
		enterKeys(getWebElements(list_txt_fltNo).get(0), fullFltNo);
		unCheck(getWebElements(list_chkBx_fltFSstatus).get(0));

		String screenName = "iCargoContentFrameCAP016";

		saveIrregularity(ubr, screenName);
		return this;
	}

	/**
	 * update the flight to a flight with allotment after navigation from CAP142
	 * screen
	 * 
	 * @param fullFltNo
	 * @param ubr
	 * @return
	 * @author A-7868
	 */
	public CAP018 updateFlightWithAllotmentCAP142(String fullFltNo, String ubr) {

		fullFltNo = PropertyHandler.getPropValue(dataFilePath, fullFltNo);
		selectByText(dropDown_capacityType, "Allotment");
		enterKeys(getWebElements(list_txt_fltNo).get(0), fullFltNo);
		unCheck(getWebElements(list_chkBx_fltFSstatus).get(0));

		String screenName = "iCargoContentFrameCAP142";

		saveIrregularity(ubr, screenName);
		return this;
	}

	private void saveIrregularity(String ubr, String... screenName) {

		String screenFrameName = screenFrame;

		if (screenName.length > 0) {
			screenFrameName = screenName[0];
		}
		try {
			enterKeys(txt_product, "");
		} catch (InvalidElementStateException e) {
			;
		}

		click(btn_save);
		// click(btn_save);

		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);

		}

		waitForFrameAndSwitch(screenFrameName);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();

		enterKeys(waitForElement(Irregularity_Inbx_irregularityCode), "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		click(Irregularity_Btn_OKbutton);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrameName);
		minWait();

		waitForNewWindow(2);
		switchToSecondWindow();
		minWait();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrameName);
	}

	public CAP018 verifyRateCard(String awbPre, String awb, String RateCardId, String rateAttribute) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		RateCardId = PropertyHandler.getPropValue(dataFilePath, RateCardId);

		listAwb(awbPre, awb);
		click(btn_ratingTab);
		minWait();
		scrollToView(info_rateCardId);
		waitForElement(info_rateCardId).getText().contains(RateCardId);

		if (rateAttribute.equals("OCDA")) {
			waitForElement(info_OCDA).getText().equals("0");
		} else if (rateAttribute.equals("OCDA and OCDC")) {

			waitForElement(info_OCDA).getText().equals("0");
			waitForElement(info_OCDC).getText().equals("0");
		} else if (rateAttribute.equals("OCDC")) {

			waitForElement(info_OCDC).getText().equals("0");
		} else if (rateAttribute.equals("Null")) {

			waitForElement(info_OCDA).getText().equals("0");
			waitForElement(info_OCDC).getText().equals("0");
			waitForElement(info_TotalOthercharges).getText().equals("0");

		}
		click(btn_clear);

		return this;

	}

	/**
	 * @param awbPre
	 * @param awb
	 * @param Wt
	 * @return
	 */
	public CAP018 modifyShipmentWt(String awbPre, String awb, String Wt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awb);
		click(btn_List);

		enterKeys(txt_commWt, Wt);
		enterKeys(list_txt_fltWt, Wt);

		minWait();

		click(btn_save);

		driver.switchTo().defaultContent();
		if (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);
			waitForFrameAndSwitch(screenFrame);

		}

		driver.switchTo().defaultContent();

		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);

			else
				break;
		}

		waitForFrameAndSwitch(screenFrame);
		minWait();
		driver.switchTo().defaultContent();
		waitForNewWindow(2);

		switchToSecondWindow();

		click(btn_summaryOk);
		minWait();
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;

	}

	/**
	 * Method to update pcs and wt of existing booking
	 * 
	 * @param awbPre
	 * @param awb
	 * @param pcs
	 * @param wt
	 * @return
	 * @author a-7868 Krishna <09/03/2018>
	 */
	public CAP018 modifyShipmentPcsWt(String awbPre, String awb, String pcs, String wt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awb);
		click(btn_List);
		minWait();

		// enterKeys(txt_commPcs, pcs);
		enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(txt_commWt, wt);
		enterKeys(list_txt_fltWt, wt);
		save("");
		return this;
	}

	/**
	 * update the wt and vol of the commodity after navigating from CAP027
	 * screen
	 * 
	 * @param newWt
	 * @param newVol
	 * @param ubr
	 * @return
	 * @author A-7681
	 */
	public CAP018 updateWtAndVolCAP027(String newWt, String newVol, String ubr) {

		newWt = PropertyHandler.getPropValue(dataFilePath, newWt);
		newVol = PropertyHandler.getPropValue(dataFilePath, newVol);
		enterKeys(txt_commWt, newWt);
		enterKeys(txt_commVol, newVol);

		enterKeys(list_txt_fltPcs, "");
		enterKeys(list_txt_fltWt, "");
		enterKeys(list_txt_fltVol, "");

		String screenName = "iCargoContentFrameCAP027";
		// save(ubr, screenName);

		return this;
	}

	/**
	 * update the wt and vol of the commodity
	 * 
	 * @param newWt
	 * @param newVol
	 * @param ubr
	 * @return
	 * @author A-7681
	 */
	public CAP018 updateWtAndVol(String awbPre, String awbNo, String newWt, String newVol, String ubr) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		newWt = PropertyHandler.getPropValue(dataFilePath, newWt);
		newVol = PropertyHandler.getPropValue(dataFilePath, newVol);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();

		enterKeys(txt_commWt, newWt);
		enterKeys(txt_commVol, newVol);

		enterKeys(list_txt_fltPcs, "");
		enterKeys(list_txt_fltWt, "");
		enterKeys(list_txt_fltVol, "");

		save(ubr);

		return this;
	}



	/**
	 * Check if a booking is in confirmed status
	 * 
	 * @param awbPre
	 * @param awb
	 * @return
	 */
	public CAP018 checkIfBookingConfirmed(String key_awbNoPrefix, String key_awbNo) {
		key_awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		key_awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		listAwb(key_awbNoPrefix, key_awbNo);
		minWait();
		Assert.assertTrue(waitForElement(info_bookingStatus).getAttribute("value").equals("Confirmed"));
		return this;
	}

	/**
	 * @param awbPre
	 * @param awb
	 * @param status
	 * @return
	 */
	public CAP018 checkBookingStatus(String awbPre, String awb, String status, String... reasonCode) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		listAwb(awbPre, awb);
		minWait();
		System.out.println(waitForElement(info_bookingStatus).getAttribute("value"));
		Assert.assertTrue(waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase(status));
		try {
			Assert.assertTrue(waitForElement(info_bkgReasonCode).getAttribute("value").equalsIgnoreCase(reasonCode[0]));
		} catch (Exception e) {
			;
		}
		return this;
	}

	/**
	 * CLose and screen and go to home page
	 * 
	 * @return
	 * @author A-7681
	 */
	public HomePage close() {

		click(btn_close);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);
		}
		return new HomePage(driver, dataFileName, extentTest);
	}

	/**
	 * Created by A-7605
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param ubr
	 * @param bookingType
	 * @param bookingDate
	 * @return
	 */
	public CAP018 futureBooking(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo, String ubr,
			String bookingType, String bookingDate, String fullAWBNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		bookingDate = PropertyHandler.getPropValue(dataFilePath, bookingDate);
		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, fullAWBNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, bookingDate);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, bookingDate);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}

		save(ubr);
		return this;
	}

	/**
	 * Created by A-7605
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param bookingDate
	 * @param fullAWBNo
	 * @param expectedErrorMessage
	 * @return
	 */
	public CAP018 checkErrorMessageOnBookingCreation(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String bookingDate, String fullAWBNo, String expectedErrorMessage) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		bookingDate = PropertyHandler.getPropValue(dataFilePath, bookingDate);
		expectedErrorMessage = PropertyHandler.getPropValue(dataFilePath, expectedErrorMessage);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, fullAWBNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, bookingDate);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, bookingDate);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);
		}

		waitForFrameAndSwitch(screenFrame);
		Assert.assertTrue(waitForElement(footer_error).getText().contains(expectedErrorMessage));
		return this;
	}

	/**
	 * Created by A-7605 on 15-2-18 This method will reassign flight in a
	 * booking
	 * 
	 * @param awbPre
	 * @param awb
	 * @param flightNo
	 * @param flightDate
	 * @param pcs
	 * @param weight
	 * @return
	 */
	public CAP018 reassignFlight(String awbPre, String awb, String flightNo, String flightDate, String pcs,
			String weight) {
//		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
//		awb = PropertyHandler.getPropValue(dataFilePath, awb);
//		flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
//		flightDate = PropertyHandler.getPropValue(dataFilePath, flightDate);
//		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		
		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awb);
		click(btn_List);
		minWait();
		handleAlert("Accept", "CAP018");
		waitForFrameAndSwitch(screenFrame);
		enterKeys(list_txt_fltNo, "AA"+flightNo);
		enterKeys(list_txt_fltDt, flightDate);
		enterKeys(list_txt_fltDt, Keys.TAB);
		minWait();
		click(btn_save);
		
//		while (verifyElementPresent(btn_genericYes)) {
//			click(btn_genericYes);
//			minWait();
//		}
		
		handleAlert("Accept", "CAP018");
		waitForFrameAndSwitch(screenFrame);
		
		
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		

		enterKeys(Irregularity_Inbx_irregularityCode, "Flight - Aircraft change, mechanical, cancellation or diversion");
		enterKeys(Irregularity_Inbx_pcs, pcs);
		enterKeys(Irregularity_Inbx_pcs, Keys.TAB);
		// enterKeys(Irregularity_Inbx_weight, weight);
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		click(Irregularity_Btn_OKbutton);
		switchToFirstWindow();
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	/******** A-8254 ************/

	/**
	 * method to navigate to booking screen in case of no need to capture
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @return
	 */

	public CAP018 verifyNavigateToBookingScreen(String stockType, String awbPre, String awb) {

		String awbNo;
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		awbNo = BizUtility.createAWBNum(stock);
		enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);

		// 4.6.3
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_noBtn);
		// waitForFrameAndSwitch(screenFrame);
		// 4.6.3 over

		return this;

	}

	public CAP018 BookingwithAWb(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals(pcs, pcs2);
		String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
		// Assert.assertEquals(wt, wt2);
		String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// added later
		// PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	public CAP018 VerifyDefaultPiecesWhenBookingwithAWb(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals("1", pcs2, "wrong default piece value");
		// String wt2=waitForElement(list_txt_fltWt).getAttribute("value");
		// Assert.assertEquals(wt,wt2);
		// String vol2=waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	/***
	 * 
	 * @param stockType
	 * @param awbPrefix
	 * @param awbNo
	 * @param commodity
	 * @param scc
	 * @param pcs
	 * @param wgt
	 * @param agentCode
	 * @param origin
	 * @param Destination
	 * @param Intermediate
	 * @param Fullflight1
	 * @param date1
	 * @param pcs1
	 * @param wgt1
	 * @param Fullflight2
	 * @param date2
	 * @param pcs2
	 * @param wgt2
	 * @param Fullflight3
	 * @param date3
	 * @param pcs3
	 * @param wgt3
	 * @param ubr
	 * @return
	 */
	public CAP018 SplitBookingwith_3Flights(String stockType, String awbPrefix, String awbNo, String commodity,
			String scc, String pcs, String wgt, String agentCode, String origin, String Destination,
			String Intermediate, String Fullflight1, String date1, String pcs1, String wgt1, String Fullflight2,
			String date2, String pcs2, String wgt2, String Fullflight3, String date3, String pcs3, String wgt3,
			String ubr, String FullawbNo) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);

		commodity = PropertyHandler.getPropValue(dataFilePath, commodity);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		Destination = PropertyHandler.getPropValue(dataFilePath, Destination);
		Intermediate = PropertyHandler.getPropValue(dataFilePath, Intermediate);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wgt = PropertyHandler.getPropValue(dataFilePath, wgt);
		Fullflight1 = PropertyHandler.getPropValue(dataFilePath, Fullflight1);
		date1 = PropertyHandler.getPropValue(dataFilePath, date1);
		pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
		wgt1 = PropertyHandler.getPropValue(dataFilePath, wgt1);
		Fullflight2 = PropertyHandler.getPropValue(dataFilePath, Fullflight2);
		date2 = PropertyHandler.getPropValue(dataFilePath, date2);
		pcs2 = PropertyHandler.getPropValue(dataFilePath, pcs2);
		wgt2 = PropertyHandler.getPropValue(dataFilePath, wgt2);

		Fullflight3 = PropertyHandler.getPropValue(dataFilePath, Fullflight3);
		date3 = PropertyHandler.getPropValue(dataFilePath, date3);
		pcs3 = PropertyHandler.getPropValue(dataFilePath, pcs3);
		wgt3 = PropertyHandler.getPropValue(dataFilePath, wgt3);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String AWBNo = getAWB(stock, awbPrefix, awbNo, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, Destination);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commodity + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wgt);

		setDimensions(pcs, wgt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), Intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), Fullflight1);
		enterKeys(getWebElements(list_txt_fltPcs).get(0), pcs1);
		enterKeys(getWebElements(list_txt_fltWt).get(0), wgt1);

		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), Intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(1), Fullflight2);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs2);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wgt2);

		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");

		enterKeys(getWebElements(list_txt_fltOrigin).get(2), Intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(2), Destination);
		enterKeys(getWebElements(list_txt_fltPcs).get(2), pcs3);
		enterKeys(getWebElements(list_txt_fltWt).get(2), wgt3);
		enterKeys(getWebElements(list_txt_fltNo).get(2), Fullflight3);
		enterKeys(getWebElements(list_txt_fltDt).get(2), ".");

		save(ubr);

		return this;

	}

	public CAP018 changeWeightToMultiFlight(String awbPre, String awbNo, String key_fullfltNum, String key_fullfltNum1,
			String origin, String dest, String newWeight, String fltDt) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_fullfltNum1 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum1);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		newWeight = PropertyHandler.getPropValue(dataFilePath, newWeight);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		// intermediate =
		// PropertyHandler.getPropValue(dataFilePath,intermediate);
		// enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		// click(checkbox_flightSelectAll);
		// click(btn_flightDetailsDelete);
		click(btn_flightDetailsAdd);
		// click(btn_flightDetailsAdd);

		// enterKeys(getWebElements(list_txt_fltOrigin).get(0),origin);
		// enterKeys(getWebElements(list_txt_fltDest).get(0), dest);
		// enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum);
		// enterKeys(getWebElements(list_txt_fltDt).get(0),".");
		enterKeys(getWebElements(list_txt_fltWt).get(0), newWeight);
		// //
		// scrollToView(list_dropDown_fltForce);
		// scrollToView(getWebElements(list_txt_fltOrigin).get(2));

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum1);
		enterKeys(getWebElements(list_txt_fltWt).get(1), newWeight);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		selectByIndex(getWebElements(list_dropDown_fltForce).get(0), 2);
		selectByIndex(getWebElements(list_dropDown_fltForce).get(1), 2);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForFrameAndSwitch(screenFrame);
		/*
		 * waitForNewWindow(2); switchToSecondWindow();
		 */
		waitForFrameAndSwitch("popupContainerFrame");
		enterKeys(waitForElement(Irregularity_Inbx_irregularityCode), "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		click(Irregularity_Btn_OKbutton);
		// switchToFirstWindow();
		minWait();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);

		String parentHandle = driver.getWindowHandle();

		// waitForNewWindow(2);
		// the 2nd window closed,another second window came
		switchToSecondWindow();

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals("1", pcs2, "wrong default piece value");
		// String wt2=waitForElement(list_txt_fltWt).getAttribute("value");
		// Assert.assertEquals(wt,wt2);
		// String vol2=waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	public CAP018 VerifyErrorMsgWhenBookingAWBWithWrongAgent(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode2, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode2 = PropertyHandler.getPropValue(dataFilePath, agentCode2);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		// pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, AWBNo);
		// 4.7

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_agentCode, agentCode2 + Keys.TAB);
		waitForElement(footer_error).getText().contains("Agent code  is invalid");
		return this;

		// String awbNo = getAWB(stock, awbPre, AWBNo);

		// 4.7 over

		// 4.6.3

		// enterKeys(txt_origin, origin);
		// enterKeys(txt_dest, dest);
		// enterKeys(txt_agentCode, agentCode2 + Keys.TAB);
		// enterKeys(txt_shippingDt, ".");
		//
		// enterKeys(txt_commCode, commCode + Keys.TAB);
		//
		// enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		//
		//
		//
		// enterKeys(list_txt_fltOrigin, origin);
		// enterKeys(list_txt_fltDest, dest);
		// enterKeys(list_txt_fltNo, key_fullfltNum);
		// enterKeys(list_txt_fltDt, key_sDate);
		//
		// click(btn_save);
		// minWait();
		// driver.switchTo().defaultContent();
		// while (verifyElementPresent(btn_genericYes)) {
		// click(btn_genericYes);
		// minWait();
		// }
		// waitForFrameAndSwitch(screenFrame);
		// waitForElement(footer_error).getText().contains("AWB not found in the
		// agent stock");
		//
		//
		// return this;
		//
		// 4.6.3
	}

	public CAP018 BookingwithoutAWb(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		click(btn_List);
		minWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();

		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String AWBNo2 = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, AWBNo, AWBNo2);

		click(btn_Attach_Detach);
		waitForNewWindow(2);
		switchToSecondWindow();

		String AWBNo3;
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		AWBNo3 = BizUtility.createAWBNum(stock);
		enterKeys(txt_awbNo_AttachWindow, AWBNo3);
		minWait();
		click(btn_Attach_Detach_AttachWindow);
		minWait();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("AWB does not exist.Do you want to capture?");
		click(btn_genericYes);
		minWait();
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		String AWBNo4 = waitForElement(txt_awbNo).getAttribute("value");
		Assert.assertEquals(AWBNo4, AWBNo3, "Wrong:AWB number is different");

		// for testcase 11

		enterKeys(txt_product, "");
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		// click(btn_summaryOk);
		// switchToFirstWindow();
		// waitForFrameAndSwitch(screenFrame);

		// save(ubr);
		return this;
	}

	// in BookingwithoutAWb function AWBNo1 changed to AWBNo2.The AWBNo1 stored
	// in property file as AWBNo,that using in this function

	public CAP018 verifyOldAWBValuesNotPopulated(String AWBNo) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		enterKeys(txt_awbNo, AWBNo);
		click(btn_List);
		minWait();

		// need to execute the following for 4.6.3

		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		// 4.6.3 over

		String dest2 = waitForElement(txt_dest).getAttribute("value");
		Assert.assertEquals("", dest2);
		String shippingDt2 = waitForElement(txt_shippingDt).getAttribute("value");
		Assert.assertEquals("", shippingDt2);
		String commCode2 = waitForElement(txt_commCode).getAttribute("value");
		Assert.assertEquals("GEN", commCode2);

		String pcs2 = waitForElement(txt_commPcs).getAttribute("value");
		Assert.assertEquals("", pcs2);
		String wt2 = waitForElement(txt_commWt).getAttribute("value");
		Assert.assertEquals("0", wt2);
		String vol2 = waitForElement(txt_commVol).getAttribute("value");
		Assert.assertEquals("0", vol2);
		String origin2 = waitForElement(list_txt_fltOrigin).getAttribute("value");
		Assert.assertEquals("", origin2);
		String dest3 = waitForElement(list_txt_fltDest).getAttribute("value");
		Assert.assertEquals("", dest3);
		String key_fullfltNum2 = waitForElement(list_txt_fltNo).getAttribute("value");
		Assert.assertEquals("", key_fullfltNum2);
		String key_sDate2 = waitForElement(list_txt_fltDt).getAttribute("value");
		Assert.assertEquals("", key_sDate2);

		return null;

	}

	public CAP018 verifySystemNotAllowAWBNumberHavingLengthGreaterThan8(String AWBNo) {

		enterKeys(txt_awbNo, AWBNo);
		String AWBNo2 = AWBNo.substring(0, 8);
		minWait();
		String AWBno3 = waitForElement(txt_awbNo).getAttribute("value");
		Assert.assertEquals(AWBNo2, AWBno3);

		return null;

	}

	public CAP018 BookingwithAWbAndSaveTemplate(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr, String templateValue) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_Select_SaveTemplate);
		minWait();
		String templateName = waitForElement(txt_Save_template_As).getAttribute("value");
		Assert.assertNotEquals("", templateName);
		String templateDescription = waitForElement(txt_Brief_Description).getAttribute("value");
		Assert.assertNotEquals("", templateDescription);
		String templateName2 = templateName + "abcd";
		enterKeys(txt_Save_template_As, templateName2);
		click(btn_Template_Save);
		verifyTemplatePresentInList(templateName2, templateValue);

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// while(verifyElementPresent(btn_genericYes)){
		// click(btn_genericYes);
		// minWait();
		// }
		// waitForNewWindow(2);
		// switchToSecondWindow();
		// click(btn_summaryOk);
		// switchToFirstWindow();
		// waitForFrameAndSwitch(screenFrame);
		//
		// String pcs2=waitForElement(list_txt_fltPcs).getAttribute("value");
		// Assert.assertEquals(pcs,pcs2);
		// String wt2=waitForElement(list_txt_fltWt).getAttribute("value");
		// Assert.assertEquals(wt,wt2);
		// String vol2=waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);
		//
		// // enterKeys(list_txt_fltPcs, pcs);
		// // enterKeys(list_txt_fltWt, wt);
		// // enterKeys(list_txt_fltVol,vol);
		//
		// // save(ubr);
		return this;
	}

	private CAP018 verifyTemplatePresentInList(String templateName, String templateValue) {

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

	// try {
	// String columnText = table.findElement(By.xpath("./tbody/tr[" + i +
	// "]/td[" + columnNo + "]")).getText();
	// tableColsVals.add(columnText);
	// } catch (NoSuchElementException e) {
	// continue;
	// }

	// tableRowCount = table.findElements(By.xpath("./tbody/tr")).size();

	// WebElement table = waitForElement(by);

	public CAP018 verifyTemplateNotPresentInList(String templateValue) {

		click(btn_List);
		minWait();
		click(btn_Select_SaveTemplate);
		minWait();

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
			if (listValue2.equalsIgnoreCase(templateValue)) {
				System.out.println("the tempate found");
				flag = true;
				break;
			}

		}
		Assert.assertEquals(flag, false);
		return this;

	}

	public CAP018 BookingFromSavedTemplate(String templateValue, String newTemplateName, String fltNewPcs,
			String fltNewWt, String fltNewVol) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		templateValue = PropertyHandler.getPropValue(dataFilePath, templateValue);
		newTemplateName = PropertyHandler.getPropValue(dataFilePath, newTemplateName);
		fltNewPcs = PropertyHandler.getPropValue(dataFilePath, fltNewPcs);
		fltNewWt = PropertyHandler.getPropValue(dataFilePath, fltNewWt);
		fltNewVol = PropertyHandler.getPropValue(dataFilePath, fltNewVol);

		click(btn_List);
		click(btn_Select_SaveTemplate);
		minWait();
		openTemplate(templateValue);
		enterKeys(list_txt_fltDt, ".");
		enterKeys(list_txt_fltPcs, fltNewPcs);
		enterKeys(list_txt_fltWt, fltNewWt);
		enterKeys(list_txt_fltVol, fltNewVol);
		click(btn_Select_SaveTemplate);
		minWait();
		String templateName = waitForElement(txt_Save_template_As).getAttribute("value");
		Assert.assertNotEquals("", templateName);
		String templateDescription = waitForElement(txt_Brief_Description).getAttribute("value");
		Assert.assertNotEquals("", templateDescription);
		// String templateName2=templateName +"abcd";
		enterKeys(txt_Save_template_As, newTemplateName);
		click(btn_Template_Save);
		minWait();
		enterKeys(txt_product, "");
		setDimensions(fltNewPcs, fltNewWt);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;
	}

	public CAP018 openTemplate(String templateValue) {

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
			if (listValue2.equalsIgnoreCase(templateValue)) {
				System.out.println("the tempate found");
				flag = true;
				doubleClick(driver.findElement(By.xpath("//*[text()='Saved Templates']/following::a[" + i + "]")));
				break;
			}

		}
		if (flag == false) {
			System.out.println("not found");
			Assert.assertEquals(flag, true);

		}

		return this;

	}

	public CAP018 BookingwithAWbAndVerifyBookingSummary(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_product, "");
		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		Assert.assertNotEquals(ubrNo, "");
		String bookingStatus = waitForElement(info_SummaryStatus).getText();
		Assert.assertNotEquals(bookingStatus, "");
		String flightDetailsStatus = waitForElement(info_FlightDetailsStatus).getText();
		Assert.assertNotEquals(flightDetailsStatus, "");
		click(btn_ShowTransportationPlan);
		minWait();
		verifyElementPresent(info_TransportationPlanResultRow);
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 BookingwithShipperAndConsignee(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_product, "");
		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_ShipperConsignee);
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_ShipperCreateButton);
		minWait();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("Do you want to create a new customer?");
		click(btn_genericYes);
		switchToSecondWindow();
		// waitForFrameAndSwitch(screenFrame);
		click(btn_ConsigneeCreateButton);
		minWait();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("Do you want to create a new customer?");
		click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);
		switchToSecondWindow();
		String shipperCode = waitForElement(txt_ShipperCode).getAttribute("value");
		Assert.assertNotEquals(shipperCode, "");
		String consigneeCode = waitForElement(txt_ConsigneeCode).getAttribute("value");
		Assert.assertNotEquals(consigneeCode, "");
		click(btn_ShipperConsigneeOK);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 flightDetailsStatus(String status, String UBRNoOfQueuedConstruct_Rate) {

		String status2 = waitForElement(txt_flightDetailsStatus).getAttribute("value");
		Assert.assertTrue(status2.contains(status));
		String UBRNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, UBRNoOfQueuedConstruct_Rate, UBRNo);

		return this;

	}

	public CAP018 BookingwithAWbOAL(String stockType, String AWBNoOAL, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNoOAL);

		minWait();

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		PropertyHandler.setPropValue(dataFilePath, AWBNoOAL, awbNo);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);
		// check(list_chkBx_sendFFR);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);
		check(list_chkBx_sendFFR);

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText().contains("Interline Agreement does not exist")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag, "Interline Agreement does not exist not displayed");

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// boolean flag = false;
		// int i = 1;
		// while (verifyElementPresent(btn_genericYes)) {
		// if (i == 1) {
		// if
		// (waitForElement(info_msg).getText().contains("Interline Agreement
		// does not exist"))
		// {
		// flag = true;
		// i = 2;
		// }
		// }
		// click(btn_genericYes);
		// minWait();
		// }
		// Assert.assertTrue(flag,
		// "Interline Agreement does not exist not displayed");

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("Interline Agreement does
		// not exist");
		// click(btn_genericYes);
		// while(verifyElementPresent(btn_genericYes)){
		// click(btn_genericYes);
		// minWait();
		// }
		// boolean flag=true;
		// while(verifyElementPresent(btn_genericYes)){
		// if(waitForElement(info_msg).getText().contains("Interline Agreement
		// does not exist with TK. Do you want to Continue?"))
		// flag=false;
		// click(btn_genericYes);
		// minWait();
		// }
		// if(flag==true)
		// Assert.assertEquals(flag,false);

		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 verifyInterlineAgremntDoesNotExistErrorMSG(String stockType, String AWBNoOAL, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNoOAL);

		minWait();

		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");

		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		PropertyHandler.setPropValue(dataFilePath, AWBNoOAL, awbNo);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);
		check(list_chkBx_sendFFR);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);
		waitForElement(footer_error).getText().contains("Interline Agreement does not exist");

		return this;
	}

	public CAP018 BookingwithProduct(String Product, String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, Product);
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals(pcs, pcs2);
		String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
		Assert.assertEquals(wt, wt2);
		String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
		Assert.assertEquals(vol, vol2);

		// added later
		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	public CAP018 verifyRateIdIsDisplayed(String Product) {

		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		click(btn_ratingTab);
		minWait();
		int rowNo = getTableRowNumber(table_RatingAndCharge, 2, Product);
		String cellValue = getTableCellValue(table_RatingAndCharge, 8, rowNo);
		Assert.assertNotEquals(cellValue, "");

		return this;

	}

	public CAP018 changeShippingDateOfWorkingAWBAndSave(String awbPre, String awbNo, String shippingDate) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		enterKeys(txt_shippingDt, "+2" + Keys.TAB);
		String sDate = waitForElement(txt_shippingDt).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, shippingDate, sDate);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;

	}


	public CAP018 singltFlightToMultiFlight(String awbPre, String awbNo, String key_fullfltNum2, String key_fullfltNum3,
			String origin, String dest, String intermediate) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		scrollToView(checkbox_flightSelectAll);
		click(checkbox_flightSelectAll);
		click(btn_flightDetailsDelete);
		click(btn_flightDetailsAdd);
		click(btn_flightDetailsAdd);

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// while(verifyElementPresent(btn_genericYes)){
		// click(btn_genericYes);
		// minWait();
		// }
		//
		// waitForFrameAndSwitch(screenFrame);
		// /* waitForNewWindow(2);
		// switchToSecondWindow();*/
		// waitForFrameAndSwitch("popupContainerFrame");
		// enterKeys(waitForElement(Irregularity_Inbx_irregularityCode),"Flight
		// Change");
		// enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		// click(Irregularity_Btn_OKbutton);
		// //switchToFirstWindow();
		// // waitForFrameAndSwitch(screenFrame);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
		// //
		// scrollToView(list_dropDown_fltForce);
		// // //selectByIndex(list_dropDown_fltForce, 2);
		// scrollToView(getWebElements(list_txt_fltOrigin).get(2));

		enterKeys(getWebElements(list_txt_fltOrigin).get(2), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(2), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(2), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(2), ".");

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForFrameAndSwitch(screenFrame);

		// iregularity popup
		waitForFrameAndSwitch(screenFrame4);
		enterKeys(Irregularity_Inbx_irregularityCode, "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)), "Flight Change");
		enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)), "Flight Change");
		click(Irregularity_Btn_OKbutton);

		waitForFrameAndSwitch(screenFrame);
		minWait();

		// waitForNewWindow(2);
		// the 2nd window closed,another second window came
		// switchToSecondWindow();

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// while (verifyElementPresent(btn_genericYes)) {
		// click(btn_genericYes);
		// minWait();
		// }
		// waitForNewWindow(2);
		// switchToSecondWindow();
		// enterKeys(waitForElement(Irregularity_Inbx_irregularityCode),
		// "Flight Change");
		// enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),
		// "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),
		// "Flight Change");
		// click(Irregularity_Btn_OKbutton);
		// //switchToFirstWindow();
		// // waitForFrameAndSwitch(screenFrame);
		// minWait();
		//
		//
		// String parentHandle = driver.getWindowHandle();
		//
		// // waitForNewWindow(2);
		// //the 2nd window closed,another second window came
		// switchToSecondWindow();

		List<String> listElement;
		int flag = 0;
		switchToSecondWindow();
		listElement = getTableColumnValues(Tbl_flightDetailsSumarry, 1);
		if ((listElement.contains(key_fullfltNum2)) && (listElement.contains(key_fullfltNum3))) {

			flag = 1;
		}
		if (flag == 0) {

			Assert.assertEquals(false, true);
		}
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;
	}

	public CAP018 BookingwithAWbMultiFlight(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr, String intermediate, String key_fullfltNum2, String key_fullfltNum3) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");

		// enterKeys(list_txt_fltOrigin, origin);
		// enterKeys(list_txt_fltDest, dest);
		// enterKeys(list_txt_fltNo,key_fullfltNum);
		// enterKeys(list_txt_fltDt,key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals(pcs, pcs2);
		String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
		Assert.assertEquals(wt, wt2);
		String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// added later
		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	// public CAP018 multiFlightToSingleFlight(String awbPre,String awbNo,String
	// key_fullfltNum2,String key_fullfltNum3,String origin,String dest,String
	// intermediate,String key_fullfltNum){
	//
	// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
	// awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
	// key_fullfltNum2 =
	// PropertyHandler.getPropValue(dataFilePath,key_fullfltNum2);
	// key_fullfltNum3 =
	// PropertyHandler.getPropValue(dataFilePath,key_fullfltNum3);
	// origin = PropertyHandler.getPropValue(dataFilePath, origin);
	// dest = PropertyHandler.getPropValue(dataFilePath, dest);
	// intermediate = PropertyHandler.getPropValue(dataFilePath,intermediate);
	// key_fullfltNum =
	// PropertyHandler.getPropValue(dataFilePath,key_fullfltNum);
	// enterKeys((txt_awbNoPrefix), awbPre);
	// enterKeys(txt_awbNo, awbNo);
	// click(btn_List);
	// minWait();
	// click(checkbox_flightSelectAll);
	// click(btn_flightDetailsDelete);
	// click(btn_flightDetailsAdd);
	// // click(btn_flightDetailsAdd);
	//
	//
	// // enterKeys(getWebElements(list_txt_fltOrigin).get(1),origin);
	// // enterKeys(getWebElements(list_txt_fltDest).get(1), intermediate);
	// // enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum2);
	// // enterKeys(getWebElements(list_txt_fltDt).get(1),".");
	// ////
	// // scrollToView(list_dropDown_fltForce);
	// //// //selectByIndex(list_dropDown_fltForce, 2);
	// // scrollToView(getWebElements(list_txt_fltOrigin).get(2));
	//
	// enterKeys(getWebElements(list_txt_fltOrigin).get(2),origin);
	// enterKeys(getWebElements(list_txt_fltDest).get(2), dest);
	// enterKeys(getWebElements(list_txt_fltNo).get(2),key_fullfltNum);
	// enterKeys(getWebElements(list_txt_fltDt).get(2),".");
	//
	//
	//
	// click(btn_save);
	// driver.switchTo().defaultContent();
	// while(verifyElementPresent(btn_genericYes)){
	// click(btn_genericYes);
	// minWait();
	// }
	// waitForFrameAndSwitch(screenFrame);
	// /* waitForNewWindow(2);
	// switchToSecondWindow();*/
	// waitForFrameAndSwitch("popupContainerFrame");
	// enterKeys(waitForElement(Irregularity_Inbx_irregularityCode),"Flight
	// Change");
	// enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
	// //
	// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
	// Change");
	// //
	// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
	// Change");
	// click(Irregularity_Btn_OKbutton);
	// //switchToFirstWindow();
	// // waitForFrameAndSwitch(screenFrame);
	// minWait();
	// driver.switchTo().defaultContent();
	// waitForFrameAndSwitch(screenFrame);
	//
	// enterKeys(txt_origin, origin);
	// enterKeys(txt_dest, dest);
	// enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
	// enterKeys(txt_shippingDt, ".");
	// enterKeys(txt_product, "");
	// //enterKeys(txt_scc, scc);
	// enterKeys(txt_commCode, commCode + Keys.TAB);
	// enterKeys(txt_commPcs, pcs);
	// enterKeys(txt_commWt, wt);
	// enterKeys(txt_commVol, vol);
	//
	// //setDimensions(pcs, wt);
	// enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
	// enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
	// enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
	// enterKeys(getWebElements(list_txt_fltDt).get(0), ".");
	//
	//
	// switchToSecondWindow();
	//
	// enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
	// enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
	// enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
	// enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
	//
	//
	//
	// // enterKeys(list_txt_fltOrigin, origin);
	// // enterKeys(list_txt_fltDest, dest);
	// // enterKeys(list_txt_fltNo,key_fullfltNum);
	// // enterKeys(list_txt_fltDt,key_sDate);
	//
	// click(btn_save);
	// driver.switchTo().defaultContent();
	// while (verifyElementPresent(btn_genericYes)) {
	// click(btn_genericYes);
	// minWait();
	// }
	// waitForNewWindow(2);
	// switchToSecondWindow();
	// click(btn_summaryOk);
	// switchToFirstWindow();
	// waitForFrameAndSwitch(screenFrame);
	//
	// String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
	// Assert.assertEquals(pcs, pcs2);
	// String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
	// Assert.assertEquals(wt, wt2);
	// String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
	// //Assert.assertEquals(vol,vol2);
	//
	// //added later
	// PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);
	//
	//
	// // enterKeys(list_txt_fltPcs, pcs);
	// // enterKeys(list_txt_fltWt, wt);
	// // enterKeys(list_txt_fltVol,vol);
	//
	// // save(ubr);
	// return this;
	// }

	public CAP018 multiFlightToSingleFlight(String awbPre, String awbNo, String key_fullfltNum2, String key_fullfltNum3,
			String origin, String dest, String intermediate, String key_fullfltNum) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		scrollToView(checkbox_flightSelectAll);
		click(checkbox_flightSelectAll);
		click(btn_flightDetailsDelete);
		click(btn_flightDetailsAdd);
		// click(btn_flightDetailsAdd);

		// enterKeys(getWebElements(list_txt_fltOrigin).get(1),origin);
		// enterKeys(getWebElements(list_txt_fltDest).get(1), intermediate);
		// enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum2);
		// enterKeys(getWebElements(list_txt_fltDt).get(1),".");
		// //
		// scrollToView(list_dropDown_fltForce);
		// // //selectByIndex(list_dropDown_fltForce, 2);
		// scrollToView(getWebElements(list_txt_fltOrigin).get(2));

		enterKeys(getWebElements(list_txt_fltOrigin).get(2), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(2), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(2), key_fullfltNum);
		enterKeys(getWebElements(list_txt_fltDt).get(2), ".");
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForFrameAndSwitch(screenFrame);

		// iregularity popup
		waitForFrameAndSwitch(screenFrame4);
		enterKeys(Irregularity_Inbx_irregularityCode, "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		click(Irregularity_Btn_OKbutton);

		waitForFrameAndSwitch(screenFrame);
		minWait();

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// while (verifyElementPresent(btn_genericYes)) {
		// click(btn_genericYes);
		// minWait();
		// }
		//
		// waitForFrameAndSwitch(screenFrame);
		// /* waitForNewWindow(2);
		// switchToSecondWindow();*/
		// waitForFrameAndSwitch("popupContainerFrame");
		//
		// waitForNewWindow(2);
		// switchToSecondWindow();
		//
		// enterKeys(waitForElement(Irregularity_Inbx_irregularityCode),
		// "Flight Change");
		// enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// //
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// //
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		// click(Irregularity_Btn_OKbutton);
		// //switchToFirstWindow();
		//
		// //
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForFrameAndSwitch(screenFrame);
		//
		// // waitForFrameAndSwitch(screenFrame);
		// minWait();
		//
		//
		// String parentHandle = driver.getWindowHandle();
		//
		//
		//
		//
		//
		// switchToSecondWindow();

		List<String> listElement;
		int flag = 0;
		switchToSecondWindow();
		listElement = getTableColumnValues(Tbl_flightDetailsSumarry, 1);
		if ((listElement.contains(key_fullfltNum))) {

			flag = 1;
		}
		if (flag == 0) {

			Assert.assertEquals(false, true);
		}
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;
	}

	public CAP018 changeWeightToMultiFlight(String awbPre, String awbNo, String key_fullfltNum, String key_fullfltNum1,
			String origin, String dest, String newWeight) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_fullfltNum1 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum1);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		newWeight = PropertyHandler.getPropValue(dataFilePath, newWeight);

		// intermediate =
		// PropertyHandler.getPropValue(dataFilePath,intermediate);
		enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		// click(checkbox_flightSelectAll);
		// click(btn_flightDetailsDelete);
		click(btn_flightDetailsAdd);
		// click(btn_flightDetailsAdd);

		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");
		enterKeys(getWebElements(list_txt_fltWt).get(0), newWeight);
		// //
		// scrollToView(list_dropDown_fltForce);
		// // //selectByIndex(list_dropDown_fltForce, 2);
		// scrollToView(getWebElements(list_txt_fltOrigin).get(2));

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum1);
		enterKeys(getWebElements(list_txt_fltWt).get(1), newWeight);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForFrameAndSwitch(screenFrame);

		// iregularity popup
		waitForFrameAndSwitch(screenFrame4);
		enterKeys(Irregularity_Inbx_irregularityCode, "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		click(Irregularity_Btn_OKbutton);

		waitForFrameAndSwitch(screenFrame);
		minWait();

		// Booking summary
		List<String> listElement;
		int flag = 0;
		switchToSecondWindow();
		listElement = getTableColumnValues(Tbl_flightDetailsSumarry, 1);
		if ((listElement.contains(key_fullfltNum)) && (listElement.contains(key_fullfltNum1))) {
			flag = 1;
		}
		if (flag == 0) {
			Assert.assertEquals(false, true);
		}
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;
	}

	public CAP018 changeVolumeToMultiFlight(String awbPre, String awbNo, String key_fullfltNum, String key_fullfltNum1,
			String origin, String dest, String newVolume) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_fullfltNum1 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum1);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		newVolume = PropertyHandler.getPropValue(dataFilePath, newVolume);

		// intermediate =
		// PropertyHandler.getPropValue(dataFilePath,intermediate);
		// enterKeys((txt_awbNoPrefix), awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		// click(checkbox_flightSelectAll);
		// click(btn_flightDetailsDelete);
		click(btn_flightDetailsAdd);
		// click(btn_flightDetailsAdd);

		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");
		enterKeys(getWebElements(list_txt_fltVol).get(0), newVolume);
		enterKeys(getWebElements(list_txt_fltWt).get(0), "50");
		// //
		// scrollToView(list_dropDown_fltForce);
		// // //selectByIndex(list_dropDown_fltForce, 2);
		// scrollToView(getWebElements(list_txt_fltOrigin).get(2));

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// while(verifyElementPresent(btn_genericYes)){
		// click(btn_genericYes);
		// minWait();
		// }
		// waitForFrameAndSwitch(screenFrame);
		// /* waitForNewWindow(2);
		// switchToSecondWindow();*/
		// waitForFrameAndSwitch("popupContainerFrame");
		// enterKeys(waitForElement(Irregularity_Inbx_irregularityCode),"Flight
		// Change");
		// enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// //
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// //
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		// click(Irregularity_Btn_OKbutton);
		// //switchToFirstWindow();
		// // waitForFrameAndSwitch(screenFrame);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForFrameAndSwitch(screenFrame);
		//
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
		enterKeys(getWebElements(list_txt_fltVol).get(1), newVolume);
		enterKeys(getWebElements(list_txt_fltWt).get(1), "50");

		// click(btn_save);
		// driver.switchTo().defaultContent();
		// while (verifyElementPresent(btn_genericYes)) {
		// click(btn_genericYes);
		// minWait();
		// }
		// waitForNewWindow(2);
		// switchToSecondWindow();
		// enterKeys(waitForElement(Irregularity_Inbx_irregularityCode),
		// "Flight Change");
		// enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// //
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// //
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		// click(Irregularity_Btn_OKbutton);
		// //switchToFirstWindow();
		// // waitForFrameAndSwitch(screenFrame);
		// minWait();
		//
		//
		//
		// switchToSecondWindow();
		//
		// String parentHandle = driver.getWindowHandle();
		//
		//
		// // waitForNewWindow(2);
		// //the 2nd window closed,another second window came
		// switchToSecondWindow();
		//
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForFrameAndSwitch(screenFrame);

		// iregularity popup
		waitForFrameAndSwitch(screenFrame4);
		enterKeys(Irregularity_Inbx_irregularityCode, "Flight Change");
		enterKeys(Irregularity_Inbx_irregularityRemarks, "Flight Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityCode).get(1)),"Flight
		// Change");
		// enterKeys(waitForElement(getWebElements(Irregularity_Inbx_irregularityRemarks).get(1)),"Flight
		// Change");
		click(Irregularity_Btn_OKbutton);

		waitForFrameAndSwitch(screenFrame);
		minWait();

		List<String> listElement;
		int flag = 0;
		switchToSecondWindow();
		listElement = getTableColumnValues(Tbl_flightDetailsSumarry, 1);
		if ((listElement.contains(key_fullfltNum)) && (listElement.contains(key_fullfltNum1))) {

			flag = 1;
		}
		if (flag == 0) {

			Assert.assertEquals(false, true);
		}
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;
	}

	public CAP018 save() {

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame2);

		return this;
	}

	public CAP018 BookingwithAWbWithServiceClassDropDown(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr, String serviceClass) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		selectByText(dropDown_serviceClass, serviceClass);
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals(pcs, pcs2);
		String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
		Assert.assertEquals(wt, wt2);
		String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// added later
		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	public CAP018 verifyRate(String Rate) {

		click(btn_ratingTab);
		minWait();
		// int rowNo=getTableRowNumber(table_RatingAndCharge,2,Product);
		String cellValue = getTableCellValue(table_RatingAndCharge, 10, 1);
		Assert.assertEquals(cellValue, Rate);

		return this;

	}

	public CAP018 BookingwithAWbWithForceDropDownQueue(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr, String force) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");

		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);
		selectByText(list_dropDown_fltForce, force);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_OK_RemarksPopUp);
		switchToFirstWindow();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForNewWindow(2);
		switchToSecondWindow();

		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals(pcs, pcs2);
		String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
		Assert.assertEquals(wt, wt2);
		String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// added later
		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	public CAP018 listAWBandChangeForceToConfirm(String awbPre, String awbNo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		listAwb(awbPre, awbNo);
		minWait();

		selectByText(list_dropDown_fltForce, "Confirm");
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		Assert.assertTrue(waitForElement(info_bookingStatus).getAttribute("value").equals("Confirmed"));

		return this;
	}

	public CAP018 listAWBandChangeForceToQueued(String awbPre, String awbNo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		listAwb(awbPre, awbNo);
		minWait();
		selectByText(list_dropDown_fltForce, "Queue");
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_OK_RemarksPopUp);
		switchToFirstWindow();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForNewWindow(2);
		switchToSecondWindow();

		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		Assert.assertTrue(waitForElement(info_bookingStatus).getAttribute("value").equals("Queued"));

		return this;
	}

	public CAP018 registerEnquiry(String origin, String dest, String ubr) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);

		click(btn_List);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);

		click(btn_registerEnquiry);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txtArea_remarks_newWindow, "what is??");
		click(btn_OK_remarks_newWindow);

		switchToFirstWindow();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("Enquiry has been registered under UBR Number :");
		String str = waitForElement(info_msg).getText();
		String[] tokens = str.split(":");
		String ubrNo = tokens[1];
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		click(btn_generic_ok);
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 updateAndSaveOnly(String origin, String dest, String agentCode, String scc, String commCode,
			String pcs, String wt, String vol, String key_fullfltNum, String key_sDate) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		click(btn_flightDetailsAdd);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		minWait();
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame3);

		return this;
	}

	public CAP018 verifyRateLineId(String rateId, String origin, String dest, String agentCode, String scc,
			String commCode, String pcs, String wt, String product) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		rateId = PropertyHandler.getPropValue(dataFilePath, rateId);
		product = PropertyHandler.getPropValue(dataFilePath, product);
		click(btn_List);
		minWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, product);
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		click(btn_ratingTab);
		minWait();
		click(btn_autoRate);
		boolean flag = false;
		String cellValue = getTableCellValue(table_RatingAndCharge, 8, 1);
		if (cellValue.contains(rateId)) {
			flag = true;
		}

		Assert.assertTrue(flag, "ratelineid is not matching");

		return this;

	}

	public CAP018 BookingwithAWbDGR(String stockType, String awbNo, String awbPre, String origin, String dest,
			String agentCode, String scc2, String commCode2, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr, String unid, String dgrName, String pi, String noOfPkg, String wtPerPkg) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc2 = PropertyHandler.getPropValue(dataFilePath, scc2);
		commCode2 = PropertyHandler.getPropValue(dataFilePath, commCode2);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		unid = PropertyHandler.getPropValue(dataFilePath, unid);
		dgrName = PropertyHandler.getPropValue(dataFilePath, dgrName);
		pi = PropertyHandler.getPropValue(dataFilePath, pi);
		noOfPkg = PropertyHandler.getPropValue(dataFilePath, noOfPkg);
		wtPerPkg = PropertyHandler.getPropValue(dataFilePath, wtPerPkg);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		getAWB(stock, awbPre, awbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		enterKeys(txt_scc, scc2);
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode2 + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		captureDGRGoods(unid, dgrName, pi, noOfPkg, wtPerPkg);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, awbNo, awbNo);

		click(btn_DGR);
		waitForNewWindow(2);
		switchToSecondWindow();
		boolean flag = false;
		String slNo = getTableCellValue(table_DGdetails, 3, 1);
		if (slNo.equalsIgnoreCase(unid)) {
			flag = true;
		}

		Assert.assertTrue(flag, "the DG information not listed");
		click(btn_DGROk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 verifyOfferedSpotValueIsPresent(String awbPre, String awbNo, String offeredSpotvalue) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		offeredSpotvalue = PropertyHandler.getPropValue(dataFilePath, offeredSpotvalue);

		listAwb(awbPre, awbNo);
		minWait();
		click(btn_ratingTab);
		minWait();
		scrollToView(txt_SpotrateId);
		String Rate = getTableCellValue(table_RatingAndCharge, 10, 1);
		boolean flag = false;
		if (offeredSpotvalue.equalsIgnoreCase(Rate)) {
			flag = true;
		}
		Assert.assertTrue(flag, "rate is not correct");

		return this;

	}

	public CAP018 listAWBandSaveAndConfirmRateAppliedIsCorrect(String awbPre, String awbNo, String offeredSpotvalue) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		offeredSpotvalue = PropertyHandler.getPropValue(dataFilePath, offeredSpotvalue);
		listAwb(awbPre, awbNo);
		minWait();

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();

		String rate = waitForElement(txt_rateApplied_summaryPopUP).getAttribute("value");
		String ratesplit[] = rate.split(".");
		boolean flag = false;

		if (offeredSpotvalue.equalsIgnoreCase(ratesplit[0])) {
			flag = true;
		}

		Assert.assertTrue(flag, "rate is not correct");

		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		return this;
	}

	/*
	 * A-8255
	 */
	public CAP018 verify_SpotrateAndTotalCharge(String awbPre, String awbNo, String SpotRateID, boolean isSpot,
			String offeredSpotvalue) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		SpotRateID = PropertyHandler.getPropValue(dataFilePath, SpotRateID);
		offeredSpotvalue = PropertyHandler.getPropValue(dataFilePath, offeredSpotvalue);

		listAwb(awbPre, awbNo);
		minWait();
		click(btn_ratingTab);
		minWait();

		String ctotalCharge = waitForElementVisible(txt_totalcharge).getAttribute("value");
		scrollToView(txt_SpotrateId);
		if (isSpot) {
			String tempId = waitForElement(txt_SpotrateId).getAttribute("value");

			Assert.assertEquals(tempId, SpotRateID);
		} else {

			waitForElement(txt_SpotrateId).getAttribute("value").isEmpty();

		}
		Assert.assertEquals(ctotalCharge, offeredSpotvalue, "Offered charge not getting effected:::");

		click(waitForElement(btn_clear));
		minWait();

		return this;

	}

	/**
	 * @param stockType
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @param scc
	 * @param agentCode
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @param key_EmbargoLevel
	 * @param key_RECO_Refnum
	 * @return
	 * @author A-8255
	 */
	public CAP018 bookingWithWmbargo(String stockType, String awbPre, String awbNo, String origin, String dest,
			String scc, String agentCode, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, String key_EmbargoLevel,
			String key_RECO_Refnum) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		key_RECO_Refnum = PropertyHandler.getPropValue(dataFilePath, key_RECO_Refnum);
		key_EmbargoLevel = PropertyHandler.getPropValue(dataFilePath, key_EmbargoLevel);
		StockType stock = StockType.valueOf(stockType);

		getAWB(stock, awbPre, awbNo, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeys(txt_shippingDt, fltDt);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);

		// click(div_sc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);

		enterKeys(txt_product, "");
		click(btn_save);
		// click(btn_save);

		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);

		}

		// waitForFrameAndSwitch(screenFrameName);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();

		waitForElement(btn_RecoEmbargoClose);
		Assert.assertTrue(waitForElement(info_RecoRefId).getText().contains(key_RECO_Refnum));

		if (key_EmbargoLevel.equals("Error")) {

			click(btn_RecoEmbargoClose);
			switchToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
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
		waitForFrameAndSwitch(screenFrame);
		Assert.assertTrue(waitForElement(info_msg).getText().contains("saved successfully"));
		click(btn_genericOk);

		// waitForFrameAndSwitch(FRAME);

		return this;
	}

	/**
	 * Method to list an awb and verify the pcs/wt in shipment table and flight
	 * table*
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param shpPcs
	 * @param shpWt
	 * @param fltPcs
	 * @param fltWt
	 * @return
	 * @author A-7868 Krishna <26/03/2018>
	 */
	public CAP018 verifyShipmentPcsWtAndFltPcsWt(String awbPre, String awbNo, String shpPcs, String shpWt,
			String fltPcs, String fltWt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		shpPcs = PropertyHandler.getPropValue(dataFilePath, shpPcs);
		shpWt = PropertyHandler.getPropValue(dataFilePath, shpWt);
		fltPcs = PropertyHandler.getPropValue(dataFilePath, fltPcs);
		fltWt = PropertyHandler.getPropValue(dataFilePath, fltWt);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();

		Assert.assertTrue(waitForElementVisible(txt_commPcs).getAttribute("value").equals(shpPcs),
				"ERROR : Shipment pcs mismatch.");
		Assert.assertTrue(waitForElementVisible(txt_commWt).getAttribute("value").equals(shpWt),
				"ERROR : Shipment wt mismatch.");
		scrollToView(list_txt_fltPcs);
		System.out.println("--" + getWebElements(list_txt_fltPcs).get(0).getAttribute("value"));
		System.out.println("--" + getWebElements(list_txt_fltWt).get(0).getAttribute("value"));

		Assert.assertTrue(getWebElements(list_txt_fltPcs).get(0).getAttribute("value").equals(fltPcs),
				"ERROR : Shipment pcs mismatch.");
		Assert.assertTrue(getWebElements(list_txt_fltWt).get(0).getAttribute("value").equals(fltWt),
				"ERROR : Shipment wt mismatch.");

		return this;
	}

	/**
	 * Method to list an awb and verify the pcs/wt in shipment table
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param shpPcs
	 * @param shpWt
	 * @return
	 * @author A-7868 Krishna <04/04/2018>
	 */
	public CAP018 verifyShipmentPcsWt(String awbPre, String awbNo, String shpPcs, String shpWt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		shpPcs = PropertyHandler.getPropValue(dataFilePath, shpPcs);
		shpWt = PropertyHandler.getPropValue(dataFilePath, shpWt);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();

		Assert.assertTrue(waitForElementVisible(txt_commPcs).getAttribute("value").equals(shpPcs),
				"ERROR : Shipment pcs mismatch.");
		Assert.assertTrue(waitForElementVisible(txt_commWt).getAttribute("value").equals(shpWt),
				"ERROR : Shipment wt mismatch.");

		return this;
	}

	/**
	 * Method to list an awb and verify the pcs/wt in flight table
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param shpPcs
	 * @param shpWt
	 * @param fltPcs
	 * @param fltWt
	 * @return
	 * @author A-7868 Krishna <26/03/2018>
	 */
	public CAP018 verifyFltPcsWt(String awbPre, String awbNo, String fltPcs, String fltWt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		fltPcs = PropertyHandler.getPropValue(dataFilePath, fltPcs);
		fltWt = PropertyHandler.getPropValue(dataFilePath, fltWt);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();

		scrollToView(list_txt_fltPcs);

		int pcs = 0, wt = 0;
		int i = 0;
		while (true) {

			String str1 = getWebElements(list_txt_fltPcs).get(i).getAttribute("value");
			String str2 = getWebElements(list_txt_fltWt).get(i).getAttribute("value");
			if (str1.isEmpty() || str2.isEmpty())
				break;
			pcs += Integer.parseInt(str1);
			wt += Integer.parseInt(str2);
			i++;
		}

		Assert.assertTrue(String.valueOf(pcs).equals(fltPcs), "ERROR : Shipment pcs mismatch.");
		Assert.assertTrue(String.valueOf(wt).equals(fltWt), "ERROR : Shipment wt mismatch.");

		return this;
	}

	public CAP018 verifyAWBNotExistMSG(String awbPre, String awbNo) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();
		driver.switchTo().defaultContent();
		waitForElement(info_msg).getText().contains("AWB does not exist.Do you want to capture?");
		click(btn_genericYes);
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 verifyAWBIsVoidedErrorMSG(String prefix, String awbno) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		listAwb(prefix, awbno);
		minWait();

		String status = waitForElement(footer_error).getText();
		System.out.println(status);
		// Assert.assertTrue(waitForElement(info_errormsg).getText().contains("The
		// specified AWB number is blacklisted. Cannot proceed."),
		// "ERROR :Able to proceed booking for blacklisted AWB.");
		Assert.assertTrue(status.contains("is voided"), "ERROR :AWB is voided msg not coming");

		minWait();

		return this;
	}

	public CAP018 BookingwithoutAWbWithNoStockAgent(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		click(btn_List);
		minWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText()
						.contains("Stock does not exist for the agent.Do you want to continue?")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag, "Stock does not exist for the agent.Do you want to continue?");
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		// PropertyHandler.setPropValue(dataFilePath,AWBNo,awbNo);

		return this;
	}

	/**
	 * verifies wt and vol for shipment and flt
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param shipWt
	 * @param shipVol
	 * @param chkForFlt
	 * @param fltDtls
	 *            : give if chkForFlt is true. given in order wt then vol
	 * @return
	 */
	public CAP018 checkWtandVol(String awbPre, String awbNo, String shipWt, String shipVol, boolean chkForFlt,
			String... fltDtls) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		shipWt = PropertyHandler.getPropValue(dataFilePath, shipWt);
		if (!shipVol.equals("")) {
			shipVol = PropertyHandler.getPropValue(dataFilePath, shipVol);
		}

		enterKeys(txt_awbNoPrefix, awbPre);
		enterKeys(txt_awbNo, awbNo);
		click(btn_List);
		minWait();

		driver.switchTo().defaultContent();
		if (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}
		waitForFrameAndSwitch(screenFrame);
		Assert.assertEquals(waitForElementVisible(txt_commWt).getAttribute("value").trim(), shipWt,
				"The shipment weight doesn't match for the AWB No: " + awbNo);
		if (!shipVol.equals("")) {
			Assert.assertEquals(waitForElementVisible(txt_commVol).getAttribute("value").trim(), shipVol,
					"The shipment volume doesn't match for the AWB No: " + awbNo);
		}
		if (chkForFlt) {
			String fltWt = PropertyHandler.getPropValue(dataFilePath, fltDtls[0]);
			if (!fltDtls[1].equals("")) {
				String fltVol = PropertyHandler.getPropValue(dataFilePath, fltDtls[1]);
				Assert.assertEquals(waitForElementVisible(list_txt_fltVol).getAttribute("value").trim(), fltVol,
						"The flight volume doesn't match for the AWB No: " + awbNo);
			}
			Assert.assertEquals(waitForElementVisible(list_txt_fltWt).getAttribute("value").trim(), fltWt,
					"The flight weight doesn't match for the AWB No: " + awbNo);
		}

		return this;
	}

	public CAP018 verifySpotRateDetachedWhenUpdatesDetails(String awbPre, String awbNo, String offeredSpotvalue,
			String dest) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		offeredSpotvalue = PropertyHandler.getPropValue(dataFilePath, offeredSpotvalue);

		listAwb(awbPre, awbNo);
		minWait();
		minWait();
		click(btn_ratingTab);
		minWait();
		scrollToView(txt_SpotrateId);
		String Rate = getTableCellValue(table_RatingAndCharge, 10, 1);
		boolean flag = false;
		if (offeredSpotvalue.equalsIgnoreCase(Rate)) {
			flag = true;
		}
		Assert.assertTrue(flag, "rate is not correct");

		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		enterKeys(txt_dest, dest);
		click(tab_shipmentULD);
		enterKeys(list_txt_fltDest, dest);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		click(btn_ratingTab);
		minWait();
		scrollToView(txt_SpotrateId);
		String Rate2 = getTableCellValue(table_RatingAndCharge, 10, 1);
		boolean flag2 = false;
		if (Rate2.equalsIgnoreCase("0")) {
			flag2 = true;
		}
		Assert.assertTrue(flag2, "rate is not correct");

		return this;

	}

	public CAP018 verifyDimensionMandatoryErrorMSG(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);

		Assert.assertTrue(
				waitForElement(info_errormsg).getText().contains(
						"Dimension information is mandatory. Please provide dimension details for the shipment to proceed"),
				"ERROR :dimension mandatory error message not coming");

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 BookingwithAWbWithDimension(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 deleteDimensionAndVerifyDimensionMandatoryErrorMSG(String AWBNo, String awbPre) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		listAwb(awbPre, AWBNo);
		deleteDimensions();
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);

		Assert.assertTrue(
				waitForElement(info_errormsg).getText().contains(
						"Dimension information is mandatory. Please provide dimension details for the shipment to proceed"),
				"ERROR :dimension mandatory error message not coming");

		return this;
	}

	private void deleteDimensions() {

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();

		click(checkbox_dim);
		click(btn_dim_delete);
		click(btn_dimOk);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {

			click(btn_genericYes);
			minWait();
		}
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

	}

	public CAP018 listAWBAndVerifyDimensionMandatoryErrorMSG(String AWBNo, String awbPre) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		listAwb(awbPre, AWBNo);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);

		Assert.assertTrue(
				waitForElement(info_errormsg).getText().contains(
						"Dimension information is mandatory. Please provide dimension details for the shipment to proceed"),
				"ERROR :dimension mandatory error message not coming");

		return this;
	}

	public CAP018 verifyDimensionMandatoryErrorMSGNotPresent(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);

		boolean errorPresent;
		try {
			waitForElement(info_errormsg);
			// errorPresent = true;
			Assert.fail("No error expected");
		} catch (Exception e) {
			errorPresent = false;
			logger.info("Error message not shown");
			logger.info("Checking got booking summary");
			waitForNewWindow(2);
			switchToSecondWindow();
			click(btn_summaryOk);
			switchToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
		}

		// if(errorPresent)
		// Assert.fail("No error expected");

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 listAWBAndUpdateDimAndVerifyDimensionMandatoryErrorMSGNotComing(String AWBNo, String awbPre) {

		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		listAwb(awbPre, AWBNo);
		// setDimensions("3","120");
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		// Assert.assertFalse(waitForElement(info_errormsg).getText().contains("Dimension
		// information is mandatory. Please provide dimension details for the
		// shipment to proceed"),
		// "ERROR :dimension mandatory error coming,its not mandatory for
		// domestic root");
		return this;
	}

	public CAP018 changeDimentionDetailsAndVerify(String stockType, String AWBNo, String awbPre, String dest, String wt,
			String pcs, String commCode) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// AWBNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, AWBNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_dest, dest);

		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, "1");
		enterKeys(txt_dimWt, "50");
		enterKeys(txt_dimLength, "10");
		enterKeys(txt_dimWidth, "10");
		enterKeys(txt_dimHeight, "10" + Keys.TAB);

		click(btn_dimOk);
		driver.switchTo().defaultContent();
		Assert.assertTrue(
				waitForElement(info_msg).getText().contains(
						"Dimensional information is not matching with the shipment information. Do you want to replace shipment information?"),
				"not matching error message not coming");
		click(btn_noBtn);
		try {
			click(btn_dim_close);
		} catch (NoSuchWindowException nswe) {
			logger.info("Dimension window already closed..!");
		}
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		String pcs2 = waitForElement(txt_commPcs).getAttribute("value");

		Assert.assertEquals(pcs, pcs2);

		String wt2 = waitForElement(txt_commWt).getAttribute("value");
		boolean flag = false;
		if (wt.equalsIgnoreCase(wt2.split("\\.")[0])) {
			flag = true;
		}

		Assert.assertTrue(flag, "wt is not correct");

		return this;
	}

	public CAP018 verifyVolumetricWeight(String stockType, String AWBNo, String awbPre, String dest, String wt,
			String pcs, String commCode) {

		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		//
		//
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// AWBNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, AWBNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_dest, dest);

		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, pcs);
		enterKeys(txt_dimWt, wt);
		enterKeys(txt_dimLength, "100");
		enterKeys(txt_dimWidth, "100");
		enterKeys(txt_dimHeight, "100" + Keys.TAB);
		click(btn_dim_CalcVolumetricWeight);
		int pcs2 = Integer.parseInt(pcs);
		String value = Integer.toString((100 * 100 * 100 * pcs2) / 6000);

		String volumetricWeight = waitForElement(txt_volumetricWeight).getAttribute("value");
		String value2 = volumetricWeight.split("\\.")[0];
		Assert.assertEquals(value, value2, "volumetric weight not correct");

		click(btn_dimOk);
		driver.switchTo().defaultContent();
		Assert.assertTrue(
				waitForElement(info_msg).getText().contains(
						"Dimensional information is not matching with the shipment information. Do you want to replace shipment information?"),
				"not matching error message not coming");
		click(btn_genericYes);

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 changeDimentionDetailsAndVerifyVolumeChange(String stockType, String AWBNo, String awbPre,
			String dest, String wt, String pcs, String commCode) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		//
		//
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// AWBNo = BizUtility.createAWBNum(stock);
		//
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, AWBNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);
		//

		enterKeys(txt_dest, dest);

		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);

		click(img_dimension);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_dimPcs, pcs);
		enterKeys(txt_dimWt, wt);
		enterKeys(txt_dimLength, "100");
		enterKeys(txt_dimWidth, "100");
		enterKeys(txt_dimHeight, "100" + Keys.TAB);
		minWait();
		String volume = waitForElement(txt_dim_volume).getAttribute("value");
		volume = volume.split("\\.")[0];

		click(btn_dimOk);
		driver.switchTo().defaultContent();
		Assert.assertTrue(
				waitForElement(info_msg).getText().contains(
						"Dimensional information is not matching with the shipment information. Do you want to replace shipment information?"),
				"not matching error message not coming");
		click(btn_genericYes);

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		String volume2 = waitForElement(txt_volume).getAttribute("value");

		Assert.assertEquals(volume, volume2, "volume change in dimension updated");

		return this;
	}

	public CAP018 BookingwithoutAWbAndSaveTemplateAndVerifyDescription(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr, String templateValue) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		click(btn_List);
		minWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");

		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_Select_SaveTemplate);
		minWait();
		String templateName = waitForElement(txt_Save_template_As).getAttribute("value");
		// Assert.assertNotEquals("",templateName);
		String templateDescription = waitForElement(txt_Brief_Description).getAttribute("value");
		Assert.assertNotEquals("", templateDescription);
		String templateName2 = templateName + "abcd";
		enterKeys(txt_Save_template_As, templateName2);
		click(btn_Template_Save);
		verifyTemplatePresentInList(templateName2, templateValue);
		clickTemplate(templateName2);
		minWait();
		templateDescription = waitForElement(txt_Brief_Description).getAttribute("value");
		Assert.assertTrue((templateDescription.contains(origin)), "decription doesnt contains origin details");
		Assert.assertTrue((templateDescription.contains(dest)), "decription doesnt contains destination details");
		Assert.assertTrue((templateDescription.contains(agentCode)), "decription doesnt contains agentcode details");

		return this;
	}

	public CAP018 clickTemplate(String templateValue) {

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
			if (listValue2.equalsIgnoreCase(templateValue)) {
				System.out.println("the tempate found");
				flag = true;
				click(driver.findElement(By.xpath("//*[text()='Saved Templates']/following::a[" + i + "]")));
				break;
			}

		}
		if (flag == false) {
			System.out.println("not found");
			Assert.assertEquals(flag, true);

		}

		return this;

	}

	public CAP018 updateTemplateDecscriptionAndVerifyBooking(String dest, String origin, String templateValue,
			String newTemplateName, String fltNewPcs, String fltNewWt, String fltNewVol) {

		templateValue = PropertyHandler.getPropValue(dataFilePath, templateValue);
		newTemplateName = PropertyHandler.getPropValue(dataFilePath, newTemplateName);
		fltNewPcs = PropertyHandler.getPropValue(dataFilePath, fltNewPcs);
		fltNewWt = PropertyHandler.getPropValue(dataFilePath, fltNewWt);
		fltNewVol = PropertyHandler.getPropValue(dataFilePath, fltNewVol);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		fltNewVol = PropertyHandler.getPropValue(dataFilePath, fltNewVol);

		click(btn_List);
		click(btn_Select_SaveTemplate);
		minWait();
		clickTemplate(templateValue);
		enterKeys(txt_Save_template_As, newTemplateName);
		String templateDescription = waitForElement(txt_Brief_Description).getAttribute("value");
		templateDescription.replaceFirst(dest, origin);
		templateDescription.replaceFirst(origin, dest);

		click(btn_Template_Save);
		minWait();
		clickTemplate(newTemplateName);

		return this;
	}

	public CAP018 verifyUncheckFFRMSG(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");

		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);
		check(list_chkBx_sendFFR);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);
		click(btn_save);

		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);

		Assert.assertTrue(
				waitForElement(info_errormsg).getText().contains(
						"FFR is not meant for own flights. Please uncheck the send FFR checkbox for the flight"),
				"ERROR :MESSAGE -FFR is not meant for own flights. Please uncheck the send FFR checkbox for the flight not showing");

		return this;
	}

	public CAP018 uncheckFFRAndSave() {

		unCheck(list_chkBx_sendFFR);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 checkFlightSegmentBookingStatus(String awbPre, String awb, String status, String status2) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		listAwb(awbPre, awb);
		minWait();
		Assert.assertTrue(waitForElement(txt_flightDetailsStatus).getAttribute("value").equals(status));
		Assert.assertTrue(
				waitForElement(getWebElements(txt_flightDetailsStatus).get(1)).getAttribute("value").equals(status2));
		return this;
	}

	public CAP018 checkFlightSegmentBookingStatus(String awbPre, String awb, String status, String status2,
			String status3) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		listAwb(awbPre, awb);
		minWait();
		Assert.assertTrue(waitForElement(txt_flightDetailsStatus).getAttribute("value").equals(status));
		Assert.assertTrue(
				waitForElement(getWebElements(txt_flightDetailsStatus).get(1)).getAttribute("value").equals(status2));
		Assert.assertTrue(
				waitForElement(getWebElements(txt_flightDetailsStatus).get(2)).getAttribute("value").equals(status3));
		return this;
	}

	public CAP018 checkFlightAllotmentFieldValue(String awbPre, String awb, String allotId) {
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		allotId = PropertyHandler.getPropValue(dataFilePath, allotId);

		listAwb(awbPre, awb);
		minWait();
		Assert.assertTrue(waitForElement(list_txt_fltAllotId).getAttribute("value").equals(allotId));
		return this;
	}

	public CAP018 multiFlightBooking_F1_F3_Waitlisted(String stockType, String awbPre, String awb, String origin,
			String dest, String origin2, String dest2, String origin3, String dest3, String agentCode, String scc,
			String commCode, String pcs, String wt, String pcs11, String wt11, String fltNo, String fltNo1,
			String fltNo2, String fltDt, String ubr, String bookingType, String FullawbNo, String ForceStatus) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin2 = PropertyHandler.getPropValue(dataFilePath, origin2);
		dest2 = PropertyHandler.getPropValue(dataFilePath, dest2);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		pcs11 = PropertyHandler.getPropValue(dataFilePath, pcs11);

		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		wt11 = PropertyHandler.getPropValue(dataFilePath, wt11);
		dest3 = PropertyHandler.getPropValue(dataFilePath, dest3);
		origin3 = PropertyHandler.getPropValue(dataFilePath, origin3);
		fltNo2 = PropertyHandler.getPropValue(dataFilePath, fltNo2);

		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltNo1 = PropertyHandler.getPropValue(dataFilePath, fltNo1);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest3);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs11);
		enterKeys(list_txt_fltWt, wt11);
		check(list_Chk_Waitlisted);

		scrollToView(list_dropDown_fltForce);
		// selectByText(list_dropDown_fltForce,ForceStatus);

		scrollToView(getWebElements(list_txt_fltOrigin).get(1));
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), origin2);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest2);
		enterKeys(getWebElements(list_txt_fltNo).get(1), fltNo1);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt11);
		scrollToView(getWebElements(list_dropDown_fltForce).get(1));

		enterKeys(getWebElements(list_txt_fltOrigin).get(2), origin3);
		enterKeys(getWebElements(list_txt_fltDest).get(2), dest3);
		enterKeys(getWebElements(list_txt_fltNo).get(2), fltNo2);
		enterKeys(getWebElements(list_txt_fltDt).get(2), fltDt);
		enterKeys(getWebElements(list_txt_fltPcs).get(2), pcs11);
		enterKeys(getWebElements(list_txt_fltWt).get(2), wt11);
		check(getWebElements(list_Chk_Waitlisted).get(2));

		save(ubr);
		return this;
	}

	// value =true to check rateid is present,value=false to check rateid not
	// present
	public CAP018 listAWBAndverifyRateLineId(String rateId, Boolean value, String awb, String awbPre) {

		rateId = PropertyHandler.getPropValue(dataFilePath, rateId);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		listAwb(awbPre, awb);
		minWait();

		click(btn_ratingTab);
		minWait();
		// click(btn_autoRate);
		boolean flag = false;
		String cellValue = getTableCellValue(table_RatingAndCharge, 8, 1);
		if (cellValue.contains(rateId)) {
			flag = true;
		}

		if (value == true) {
			Assert.assertTrue(flag, "ratelineid is wrong");
		} else if (value == false) {
			Assert.assertFalse(flag, "ratelineid is wrong");
		}

		return this;

	}

	public CAP018 listAWBAndverifyRate(String rate, String awb, String awbPre) {

		rate = PropertyHandler.getPropValue(dataFilePath, rate);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		listAwb(awbPre, awb);
		minWait();

		click(btn_ratingTab);
		minWait();
		// click(btn_autoRate);
		boolean flag = false;
		String cellValue = getTableCellValue(table_RatingAndCharge, 10, 1);
		if (cellValue.equalsIgnoreCase(rate)) {
			flag = true;
		}

		Assert.assertTrue(flag, "rate is wrong");

		return this;

	}

	public CAP018 listAWBAndverifyLATOffsetValue(String awb, String awbPre, String LATDate, String LATTime) {

		boolean flag = true;
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		listAwb(awbPre, awb);
		minWait();

		LATTime = PropertyHandler.getPropValue(dataFilePath, LATTime);
		String LATTime2 = waitForElement(txt_LAT_Time).getAttribute("value");
		Assert.assertEquals(LATTime, LATTime2);

		enterKeys(txt_shippingDt, "-1"); // to get the 1 day before date,bcoz
											// LAT offset is 1 Day.
		String LATDatevalue = waitForElement(txt_shippingDt).getAttribute("value");
		// LATDate = PropertyHandler.getPropValue(dataFilePath, LATDate);
		String LATDate2 = waitForElement(txt_LAT_Date).getAttribute("value");
		if (LATDatevalue.equalsIgnoreCase(LATDate2)) {
			flag = false;
		}
		Assert.assertFalse(flag, "wrong value");

		// Calendar cal = Calendar.getInstance();
		// System.out.println("Updated = " + cal.get(cal.YEAR));
		// System.out.println("Updated = " + (cal.get(cal.MONTH)+1));
		// cal.set(Calendar.HOUR,depTimeSmall);
		// cal.add(Calendar.MINUTE,-offsetValueInMinute);
		//

		return this;

	}

	public CAP018 verifyLATOffsetEnabled() {
		verifyElementEnabled(txt_LAT_Date);
		verifyElementEnabled(txt_LAT_Time);

		return this;
	}

	public CAP018 verifyMinimumConnectionTimeNotSatisfiedErrorMSG(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr, String intermediate,
			String key_fullfltNum2, String key_fullfltNum3) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText().contains("Minimum connection time not satisfied for Flight")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag, "message Minimum connection time not satisfied for Flight not displayed");
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 verifyMaximumConnectionTimeNotSatisfiedErrorMSG(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr, String intermediate,
			String key_fullfltNum2, String key_fullfltNum3) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText().contains("Maximum connection time not satisfied for Flight")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag, "message Maximum connection time not satisfied for Flight not displayed");
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 verifyConnectionTimeNotSatisfiedErrorMSGNotComing(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr, String intermediate,
			String key_fullfltNum2, String key_fullfltNum3) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText().contains("connection time not satisfied for Flight")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertFalse(flag, "ERROR:message connection time not satisfied for Fligh displayed");
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 BookingwithAWbWithUniqueReference(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr, String uniqueReference) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		uniqueReference = PropertyHandler.getPropValue(dataFilePath, uniqueReference);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		click(btn_UniqueReferance);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_UniqueReferance, uniqueReference);
		click(btn_save_UniqueReferance);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 listAWBAndRemoveUniqueReferance(String AWBNo, String awbPre) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		enterKeys(txt_awbNo, AWBNo);
		click(btn_List);
		minWait();

		click(btn_UniqueReferance);
		waitForNewWindow(2);
		switchToSecondWindow();
		enterKeys(txt_UniqueReferance, "");
		click(btn_save_UniqueReferance);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, AWBNo);

		return this;
	}

	public CAP018 verifyBookingSplitInToTwo(String awbPre, String awb, String key_fullfltNum) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awb = PropertyHandler.getPropValue(dataFilePath, awb);
		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);

		listAwb(awbPre, awb);
		minWait();

		scrollToView(list_txt_fltNo);
		Assert.assertEquals(waitForElement(getWebElements(list_txt_fltNo).get(0)).getAttribute("value"),
				key_fullfltNum);
		Assert.assertEquals(waitForElement(getWebElements(list_txt_fltNo).get(1)).getAttribute("value"),
				key_fullfltNum);

		return this;

	}

	public CAP018 verifyCustomerStopCreditStatusErrorMSG(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText()
						.contains("The customer is in Stop Credit status. Do you want to continue")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag,
				"message The customer is in Stop Credit status. Do you want to continue is not displayed");
		maxWait();
		waitForFrameAndSwitch(screenFrame);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 verifyCustomerStopCreditStatusErrorMSGNotPresent(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText()
						.contains("The customer is in Stop Credit status. Do you want to continue")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertFalse(flag,
				"ERROR:message The customer is in Stop Credit status. Do you want to continue is  displayed");
		maxWait();
		waitForFrameAndSwitch(screenFrame);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		return this;
	}

	public CAP018 withoutAWb(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		click(btn_List);
		minWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();

		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String AWBNo2 = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, AWBNo, AWBNo2);

		return this;
	}

	public CAP018 verifyAWBNumberNotDisplayed() {

		String AWBNo = waitForElement(txt_awbNo).getAttribute("value");
		Assert.assertTrue(AWBNo.equalsIgnoreCase(""), "ERROR:AWB number is displayed");

		return this;
	}

	public CAP018 verifyUNIDInformationForAllDGSCCarenotcapturedMsg(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		click(btn_List);
		minWait();

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				String msg = waitForElement(info_msg).getText();
				if (waitForElement(info_msg).getText()
						.contains("UNID information for all DG SCCs are not captured. Do you want to continue?")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag, "UNID information for all DG SCCs are not captured. Do you want to continue?");
		waitForNewWindow(2);
		switchToSecondWindow();

		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String AWBNo2 = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, AWBNo, AWBNo2);

		return this;
	}

	public CAP018 verifyAWBMandatoryForCaptureDGInfoMsgPresent() {

		click(btn_DGR);
		String status = waitForElement(footer_error).getText();
		System.out.println(status);

		Assert.assertTrue(status.contains("AWB mandatory for capturing DG information"),
				"ERROR: AWB mandatory for capturing DG information not displayed");

		minWait();
		return this;
	}

	public CAP018 modifyUNIDForDGBooking(String stockType, String AWBNo, String awbPre, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String key_fullfltNum,
			String key_sDate, String ubr, String unid, String dgrName, String T1, String unid2, String dgrName2) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		modifyUNID(unid, dgrName, T1, unid2, dgrName2);
		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String pcs2 = waitForElement(list_txt_fltPcs).getAttribute("value");
		Assert.assertEquals(pcs, pcs2);
		String wt2 = waitForElement(list_txt_fltWt).getAttribute("value");
		Assert.assertEquals(wt, wt2);
		String vol2 = waitForElement(list_txt_fltVol).getAttribute("value");
		// Assert.assertEquals(vol,vol2);

		// added later
		PropertyHandler.setPropValue(dataFilePath, AWBNo, awbNo);

		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		// enterKeys(list_txt_fltVol,vol);

		// save(ubr);
		return this;
	}

	private void modifyUNID(String unid, String dgrName, String T1, String unid2, String dgrName2) {

		click(btn_DGR);
		waitForNewWindow(2);
		switchToSecondWindow();// checkbox_radioActive

		enterKeys(txt_DGRUnid, unid + Keys.TAB);
		minWait();
		selectByText(dropDown_DGRName, dgrName);
		waitForElement(checkbox_radioActive).isSelected();
		Assert.assertFalse(waitForElement(txt_DGRwtPerPcs).isEnabled(), "Error:that text box is enabled");
		enterKeys(txt_DGR_T1, T1);
		selectByIndex(dropDown_RMC, 2);
		click(btn_DGRadd);
		waitForElement(txt_DGRaddedUNID);
		check(checkbox_table_DGdetails);
		click(btn_DGRdelete);

		enterKeys(txt_DGRUnid, unid2 + Keys.TAB);
		minWait();
		selectByText(dropDown_DGRName, dgrName2);
		Assert.assertTrue(waitForElement(txt_DGRwtPerPcs).isEnabled(), "Error:that text box is disabled");
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		return;
	}

	public CAP018 verifyUNIDCannotCarryInPassengerFlightErrorMSG(String stockType, String awbNo, String awbPre,
			String origin, String dest, String agentCode, String scc2, String commCode2, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr, String unid, String dgrName, String pi,
			String noOfPkg, String wtPerPkg) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc2 = PropertyHandler.getPropValue(dataFilePath, scc2);
		commCode2 = PropertyHandler.getPropValue(dataFilePath, commCode2);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		unid = PropertyHandler.getPropValue(dataFilePath, unid);
		dgrName = PropertyHandler.getPropValue(dataFilePath, dgrName);
		pi = PropertyHandler.getPropValue(dataFilePath, pi);
		noOfPkg = PropertyHandler.getPropValue(dataFilePath, noOfPkg);
		wtPerPkg = PropertyHandler.getPropValue(dataFilePath, wtPerPkg);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		getAWB(stock, awbPre, awbNo);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		enterKeys(txt_scc, scc2);
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode2 + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, key_fullfltNum);
		enterKeys(list_txt_fltDt, key_sDate);

		captureDGRGoods(unid, dgrName, pi, noOfPkg, wtPerPkg);

		click(btn_save);
		driver.switchTo().defaultContent();
		boolean flag = false;
		int i = 1;
		while (verifyElementPresent(btn_genericYes)) {
			if (i == 1) {
				if (waitForElement(info_msg).getText().contains("cannot be carried in Passenger flight")) {
					flag = true;
					i = 2;
				}
			}
			click(btn_genericYes);
			minWait();
		}
		Assert.assertTrue(flag, "ERROR:cannot be carried in Passenger flight msg not displayed");

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	/**
	 * Method to list an AWB and verify the corr. Agent code listed in agent
	 * field
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awbNo
	 * @param agentCode
	 * @return
	 * @author A-7868 Krishna <04/05/2018>
	 */
	public CAP018 listAndVerfiyAgentCode(String stockType, String awbPre, String awbNo, String agentCode) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		getAWB(stock, awbPre, awbNo);

		Assert.assertTrue(waitForElement(txt_agentCode).getAttribute("value").equalsIgnoreCase(agentCode),
				"ERROR : Agent code mismatch.");
		return this;
	}

	public CAP018 VerifyFFRisNotMeanForOwnFlightsMSGinMultiFlightBooking(String stockType, String AWBNo, String awbPre,
			String origin, String dest, String agentCode, String scc, String commCode, String pcs, String wt,
			String vol, String key_fullfltNum, String key_sDate, String ubr, String intermediate,
			String key_fullfltNum2, String key_fullfltNum3) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");
		check(list_chkBx_sendFFR);

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
		check(getWebElements(list_chkBx_sendFFR).get(1));

		// enterKeys(list_txt_fltOrigin, origin);
		// enterKeys(list_txt_fltDest, dest);
		// enterKeys(list_txt_fltNo,key_fullfltNum);
		// enterKeys(list_txt_fltDt,key_sDate);

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}
		// waitForNewWindow(2);
		// switchToSecondWindow();
		// click(btn_summaryOk);
		// switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		Assert.assertTrue(
				waitForElement(info_errormsg).getText().contains(
						"FFR is not meant for own flights. Please uncheck the send FFR checkbox for the flight"),
				"ERROR :MESSAGE -FFR is not meant for own flights. Please uncheck the send FFR checkbox for the flight not showing");

		return this;
	}

	public CAP018 VerifyFFRCheckMultiFlightBooking(String stockType, String AWBNo, String awbPre, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol,
			String key_fullfltNum, String key_sDate, String ubr, String intermediate, String key_fullfltNum2,
			String key_fullfltNum3) {

		// AWBNo = PropertyHandler.getPropValue(dataFilePath, AWBNo);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);

		key_fullfltNum = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum);
		key_sDate = PropertyHandler.getPropValue(dataFilePath, key_sDate);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		key_fullfltNum2 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum2);
		key_fullfltNum3 = PropertyHandler.getPropValue(dataFilePath, key_fullfltNum3);

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		String awbNo = getAWB(stock, awbPre, AWBNo);

		// String awbNo;
		// stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		// awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		// awbNo = BizUtility.createAWBNum(stock);
		// enterKeys((txt_awbNoPrefix), awbPre);
		// enterKeys(txt_awbNo, awbNo);
		// click(btn_List);
		// minWait();
		// driver.switchTo().defaultContent();
		// waitForElement(info_msg).getText().contains("AWB does not exist.Do
		// you want to capture?");
		// click(btn_genericYes);
		// waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_product, "");
		// enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);

		setDimensions(pcs, wt);
		enterKeys(getWebElements(list_txt_fltOrigin).get(0), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(0), intermediate);
		enterKeys(getWebElements(list_txt_fltNo).get(0), key_fullfltNum2);
		enterKeys(getWebElements(list_txt_fltDt).get(0), ".");
		check(list_chkBx_sendFFR);

		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), key_fullfltNum3);
		enterKeys(getWebElements(list_txt_fltDt).get(1), ".");
		check(getWebElements(list_chkBx_sendFFR).get(1));

		click(btn_save);
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			minWait();
		}

		waitForNewWindow(2);
		switchToSecondWindow();
		click(btn_summaryOk);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	/**
	 * Method to list an AWB and verify the flight number of a Rejected segment
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param rejectedFltNum
	 *            (Full Flight NUmber)
	 * @return
	 * @author A-7868 Krishna <09/05/2018>
	 */
	public CAP018 verifyFlightInRejectSegments(String awbPre, String awbNo, String rejectedFltNum) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		rejectedFltNum = PropertyHandler.getPropValue(dataFilePath, rejectedFltNum);

		list(awbPre, awbNo);
		minWait();

		scrollToView(lnk_rejectedSegments);
		click(lnk_rejectedSegments);
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		Assert.assertTrue(getTableColumnValues(tbl_rejSegments, 3).contains(rejectedFltNum),
				"ERROR : Rejected segment not listed.");
		click(btn_rejSegClose);
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	/**
	 * Method to create a booking with Prevent Replan checkbox checked
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @param forceQueue
	 * @return
	 * @author A-7868 Krishna <10/05/2018>
	 */
	public CAP018 simpleBookingPreventReplan(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, boolean... forceQueue) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		check(chkBox_preventReplan);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}
		if (forceQueue.length != 0 && forceQueue[0]) {
			selectByText(list_dropDown_fltForce, "Queue");
		} else {
			selectByText(list_dropDown_fltForce, "Confirm");
		}

		save(ubr);
		return this;
	}

	public CAP018 simpleBookingOAL(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String FullawbNo, boolean agrmntCheckError) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());

		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		scrollToView(list_dropDown_fltForce);
		check(list_chkBx_sendFFR);

		// save(ubr);
		String screenFrameName = screenFrame;

		try {
			enterKeys(txt_product, "");
		} catch (InvalidElementStateException e) {
			;
		}

		click(btn_save);
		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		if (agrmntCheckError) {
			waitForFrameAndSwitch(screenFrameName);
			Assert.assertTrue(waitForElement(info_error).getText().contains("Interline Agreement does not exist"),
					"Invalid Error message");
		} else {
			if (verifyNumberOfWindows(2)) {
				switchToSecondWindow();
				if (driver.getTitle().toUpperCase().contains("queuing".toUpperCase())) {
					enterKeys(txt_queueRemarksRemark, "Queuing remarks by automation");
					minWait();
					scrollToView(btn_queueRemarksOk1);
					click(btn_queueRemarksOk1);
					switchToFirstWindow();
				} else {
					switchBackToFirstWindow();
				}
				waitForFrameAndSwitch(screenFrameName);
			}

			minWait();
			driver.switchTo().defaultContent();
			while (true) {
				if (verifyElementPresent(btn_genericYes))
					click(btn_genericYes);

				else
					break;

			}

			waitForFrameAndSwitch(screenFrameName);
			minWait();
			waitForNewWindow(2);
			switchToSecondWindow();

			minWait();
			String ubrNo = waitForElement(info_summaryUbrNo).getText();
			PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);

			click(btn_summaryOk);

			switchToFirstWindow();
			minWait();
			waitForFrameAndSwitch(screenFrameName);
		}

		return this;
	}

	/**
	 * Method to create a booking with Restriction popup handle
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @param restrictionId
	 * @param forceQueue
	 * @return
	 * @author A-7868 Krishna <30/05/2018>
	 */
	public CAP018 simpleBookingWithRestriction(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, String restrictionId,
			boolean... forceQueue) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		restrictionId = PropertyHandler.getPropValue(dataFilePath, restrictionId);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}
		if (forceQueue.length != 0 && forceQueue[0]) {
			selectByText(list_dropDown_fltForce, "Queue");
		} else {
			selectByText(list_dropDown_fltForce, "Confirm");
		}

		try {
			enterKeys(txt_product, "");
		} catch (InvalidElementStateException e) {
			;
		}

		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		if (verifyNumberOfWindows(2)) {
			switchToSecondWindow();
			if (driver.getTitle().toUpperCase().contains("queuing".toUpperCase())) {
				enterKeys(txt_queueRemarksRemark, "Queuing remarks by automation");
				minWait();
				scrollToView(btn_queueRemarksOk1);
				click(btn_queueRemarksOk1);
				switchToFirstWindow();
			} else if (driver.getTitle().toUpperCase().contains("restriction".toUpperCase())) {
				By table = By.id("fixed_header_table_0");
				System.out.println(getTableColumnValues(table, 1));
				Assert.assertTrue(getTableColumnValues(table, 1).contains(restrictionId),
						"ERROR : Restriction ID mismatch.");
				click(btn_chkRstrctn_continue);
				switchToFirstWindow();
			} else {
				switchBackToFirstWindow();
			}
			waitForFrameAndSwitch(screenFrame);
		}

		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}
		waitForFrameAndSwitch(screenFrame);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();
		minWait();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		click(btn_summaryOk);
		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	/**
	 * Method to list an AWB and verify the Allotment ID
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param allotId
	 * @return
	 * @author A-7868 Krishna <04/06/2018>
	 */
	public CAP018 listAndVerifyAllotID(String awbPre, String awbNo, String allotId) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		allotId = PropertyHandler.getPropValue(dataFilePath, allotId);

		listAwb(awbPre, awbNo);
		minWait();
		scrollToView(list_txt_fltAllotId);
		Assert.assertTrue(
				waitForElement(getWebElements(list_txt_fltAllotId).get(0)).getAttribute("value").equals(allotId),
				"The allotment ID is not matching");

		return this;

	}

	public CAP018 Booking_withRestrictionError(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String FullawbNo, String restrictionId) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		restrictionId = PropertyHandler.getPropValue(dataFilePath, restrictionId);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(list_txt_fltWt);

		click(btn_save);

		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		By table = By.id("fixed_header_table_0");
		System.out.println(getTableColumnValues(table, 1));
		Assert.assertTrue(getTableColumnValues(table, 1).contains(restrictionId), "ERROR : Restriction ID mismatch.");

		click(chkRstrctn_btn_close);
		switchToFirstWindow();

		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 Booking_withRestrictionWarning(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String FullawbNo, String restrictionId) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		restrictionId = PropertyHandler.getPropValue(dataFilePath, restrictionId);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(list_txt_fltWt);

		click(btn_save);

		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		By table = By.id("fixed_header_table_0");
		System.out.println(getTableColumnValues(table, 1));
		Assert.assertTrue(getTableColumnValues(table, 1).contains(restrictionId), "ERROR : Restriction ID mismatch.");
		By infoImg = By.id("//*[@id='fixed_header_table_0']/tbody/tr/td[3]/img");

		Assert.assertTrue(waitForElement(infoImg).getAttribute("src").equals("warning.gif"),
				"ERROR : Restriction Type mismatch.");
		Assert.assertTrue(waitForElement(btn_chkRstrctn_continue).isEnabled());
		click(btn_chkRstrctn_continue);

		switchToFirstWindow();

		waitForFrameAndSwitch(screenFrame);
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);

			else
				break;

		}

		waitForFrameAndSwitch(screenFrame);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();

		minWait();

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();

		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 Booking_withRestrictioninfo(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String FullawbNo, String restrictionId) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		restrictionId = PropertyHandler.getPropValue(dataFilePath, restrictionId);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(list_txt_fltWt);

		click(btn_save);

		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		By table = By.id("fixed_header_table_0");
		System.out.println(getTableColumnValues(table, 1));
		Assert.assertTrue(getTableColumnValues(table, 1).contains(restrictionId), "ERROR : Restriction ID mismatch.");
		By infoImg = By.id("//*[@id='fixed_header_table_0']/tbody/tr/td[3]/img");

		Assert.assertTrue(waitForElement(infoImg).getAttribute("src").equals("info.gif"),
				"ERROR : Restriction Type mismatch.");
		Assert.assertTrue(waitForElement(btn_chkRstrctn_continue).isEnabled());
		click(btn_chkRstrctn_continue);

		switchToFirstWindow();

		waitForFrameAndSwitch(screenFrame);
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);

			else
				break;

		}

		waitForFrameAndSwitch(screenFrame);
		minWait();
		waitForNewWindow(2);
		switchToSecondWindow();

		minWait();

		click(btn_summaryOk);

		switchToFirstWindow();
		minWait();

		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	public CAP018 Booking_withRestrictionErrorTextValidation(String origin, String dest, String agentCode, String scc,
			String commCode, String pcs, String wt, String vol, String fltNo, String fltDt, String FullawbNo,
			String restrictionTxt) {

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		restrictionTxt = PropertyHandler.getPropValue(dataFilePath, restrictionTxt);

		// String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		click(btn_List);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(list_txt_fltWt);

		click(btn_save);

		// click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
		}

		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		By table = By.id("fixed_header_table_0");
		System.out.println(getTableColumnValues(table, 1));
		Assert.assertTrue(getTableColumnValues(table, 1).contains(restrictionTxt), "ERROR : Restriction ID mismatch.");
		By infoImg = By.id("//*[@id='fixed_header_table_0']/tbody/tr/td[3]/img");

		// Assert.assertTrue(waitForElement(infoImg).getAttribute("src").equals("info.gif"),
		// "ERROR : Restriction Type mismatch.");
		Assert.assertTrue(waitForElement(btn_chkRstrctn_continue).isEnabled());
		click(btn_chkRstrctn_continue);

		switchToFirstWindow();

		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	/**
	 * Method to create a booking by selecting an allotment Also verfies whether
	 * the allotmentIDs passed are listed in station allotment table in Choose
	 * Allotment window
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @param allotmentIDList
	 * @return
	 * @author A-7868 Krishna <04/06/2018>
	 */
	public CAP018 simpleBookingWithAllotmentFromList(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, String... allotmentIDList) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(list_txt_fltWt);
		click(btn_chooseAllotId);
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		List<String> allotList = getTableColumnValues(tbl_chooseAllotmnt_stnAllotment, 3);
		for (String allotId : allotmentIDList)
			Assert.assertTrue(allotList.contains(allotId), "ERROR : Allotment ID not in List");
		tblSelectRowByColValue(tbl_chooseAllotmnt_stnAllotment, 1, 3, allotmentIDList[0]);
		click(btn_chooseAllotmnt_ok);

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}

		save(ubr);
		return this;
	}

	/**
	 * Method verfies whether the allotmentIDs passed are listed in station
	 * allotment table in Choose Allotment window But creates the booking
	 * without selecting any Allotment ID
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @param allotmentIDList
	 * @return
	 * @author A-7868 Krishna <04/06/2018>
	 */
	public CAP018 simpleBookingWithoutSelectingAllotment(String stockType, String awbPre, String awb, String origin,
			String dest, String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, String... allotmentIDList) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		click(list_txt_fltWt);
		click(btn_chooseAllotId);
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();

		List<String> allotList = getTableColumnValues(tbl_chooseAllotmnt_stnAllotment, 3);
		for (String allotId : allotmentIDList)
			Assert.assertTrue(allotList.contains(allotId), "ERROR : Allotment ID not in List");

		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}

		save(ubr);
		return this;
	}

	/**
	 * Method to create booking by providing value in AllotId field
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @param FullawbNo
	 * @param allotId
	 * @return
	 * @author A-7868 Krishna <29/06/2018>
	 */
	public CAP018 simpleBookingWithAllotmentId(String stockType, String awbPre, String awb, String origin, String dest,
			String agentCode, String scc, String commCode, String pcs, String wt, String vol, String fltNo,
			String fltDt, String ubr, String bookingType, String FullawbNo, String allotId) {

		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		allotId = PropertyHandler.getPropValue(dataFilePath, allotId);

		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, fltDt);
		enterKeys(txt_shipmentDescrptn, "GEN");

		enterKeys(txt_commCode, commCode + Keys.TAB);

		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		// setDimensions(pcs, wt);
		enterKeys(txt_product, "");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		// enterKeys(list_txt_fltPcs, pcs);
		// enterKeys(list_txt_fltWt, wt);
		enterKeys(list_txt_fltAllotId, allotId);
		scrollToView(list_dropDown_fltForce);
		if (bookingType.equals("OAL")) {
			check(list_chkBx_sendFFR);
		}

		save(ubr);
		return this;
	}

	public CAP018 productOrientedBookingErrorVerification(String origin, String dest, String agentCode, String scc,
			String commCode, String pcs, String wt, String vol, String fltNo, String fltDt, String ubr,
			String product) {
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		product = PropertyHandler.getPropValue(dataFilePath, product);
		click(btn_clear);
		minWait();
		click(btn_List);
		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_product, product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, "10");
		enterKeys(list_txt_fltWt, "100");
		enterKeys(list_txt_fltVol, vol);
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}
		minWait();
		Assert.assertTrue(waitForElement(info_msg).getText()
				.equals("Invalid Product Code-Priority-Transportation Mode-Handling code combination."));
		click(btn_generic_ok);
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	public CAP018 awbProductOrientedBookingErrorVerification(String stockType, String awbPre, String awb,
			String FullawbNo, String origin, String dest, String agentCode, String scc, String commCode, String pcs,
			String wt, String vol, String fltNo, String fltDt, String ubr, String product) {
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		product = PropertyHandler.getPropValue(dataFilePath, product);
		StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		String awbNo = getAWB(stock, awbPre, awb, FullawbNo);
		minWait();
		minWait();
		click(btn_List);
		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_product, product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, "10");
		enterKeys(list_txt_fltWt, "100");
		enterKeys(list_txt_fltVol, vol);
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}
		minWait();
		Assert.assertTrue(waitForElement(info_msg).getText()
				.equals("Invalid Product Code-Priority-Transportation Mode-Handling code combination."));
		click(btn_generic_ok);
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	public CAP018 productOrientedBooking(String origin, String dest, String agentCode, String scc, String commCode,
			String pcs, String wt, String vol, String fltNo, String fltDt, String ubr, String product, String scc2) {
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		product = PropertyHandler.getPropValue(dataFilePath, product);
		scc2 = PropertyHandler.getPropValue(dataFilePath, scc2);
		click(btn_clear);
		minWait();
		click(btn_List);
		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_product, product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, "10");
		enterKeys(list_txt_fltWt, "100");
		enterKeys(list_txt_fltVol, vol);
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}
		minWait();
		Assert.assertTrue(waitForElement(info_msg).getText()
				.equals("Invalid Product Code-Priority-Transportation Mode-Handling code combination."));
		click(btn_generic_ok);
		waitForFrameAndSwitch(screenFrame);

		enterKeys(txt_scc, scc2);
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}
		waitForNewWindow(2);
		switchToSecondWindow();
		minWait();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		click(btn_summaryOk);
		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	public CAP018 splitBookingWeigthVolume(String stockType, String origin, String dest, String agentCode, String scc,
			String commCode, String pcs, String wt, String vol, String fltNo, String fltDt, String ubr, String allotId)
			throws Exception {
		stockType = PropertyHandler.getPropValue(dataFilePath, stockType);

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);

		// StockType stock = StockType.valueOf(stockType.toUpperCase());
		click(btn_clear);
		minWait();
		click(btn_List);
		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, "10");
		enterKeys(list_txt_fltWt, "100");
		enterKeys(list_txt_fltVol, vol);
		// click(chk_boxID1);
		getAllotID_GlobalCustomer(allotId);

		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}

		waitForFrameAndSwitch(screenFrame);
		waitForNewWindow(2);
		switchToSecondWindow();
		minWait();
		String ubrNo = waitForElement(info_summaryUbrNo).getText();
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		click(btn_summaryOk);
		switchToFirstWindow();
		minWait();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	public CAP018 bookingEmbargoLevelVerification(String origin, String dest, String agentCode, String scc,
			String commCode, String pcs, String wt, String vol, String fltNo, String fltDt, String ubr, String level) {
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		level = PropertyHandler.getPropValue(dataFilePath, level);
		click(btn_clear);
		minWait();
		click(btn_List);
		initElements();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_shippingDt, ".");
		enterKeys(txt_scc, scc);

		enterKeys(txt_commCode, commCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, fltNo);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, "10");
		enterKeys(list_txt_fltWt, "100");
		enterKeys(list_txt_fltVol, vol);
		click(btn_save);
		minWait();
		driver.switchTo().defaultContent();
		while (true) {
			minWait();
			if (verifyElementPresent(btn_genericYes))
				click(btn_genericYes);
			else
				break;
		}
		minWait();
		// waitForFrameAndSwitch(screenFrame);
		// driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		if (level.equals("Error"))
			Assert.assertTrue(verifyElementPresent(logo_Embargo_err), "Embargo error should be displayed");
		// incomplete
		if (level.equals("Warning"))
			Assert.assertTrue(verifyElementPresent(logo_Embargo_err), "Embargo error should be displayed");
		if (level.equals("Info")) {
		}
		click(btn_RecoEmbargoClose);
		minWait();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	/**
	 * Method to list an AWB and verify the gross volume
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param grossVol
	 * @return
	 * @author A-7868 Krishna <03/07/2018>
	 */
	public CAP018 listAndVerifyGrossVolume(String awbPre, String awbNo, String grossVol) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		grossVol = PropertyHandler.getPropValue(dataFilePath, grossVol);

		listAwb(awbPre, awbNo);
		minWait();
		scrollToView(txt_commGrossVol);
		Assert.assertTrue(waitForElement(txt_commGrossVol).getAttribute("value").equals(grossVol),
				"Gross Volume mismatch");

		return this;

	}

	/**
	 * Check if a booking is in confirmed status
	 * 
	 * @param awbPre
	 * @param awb
	 * @return
	 */
	public CAP018 checkIfBookingCancelled(String key_awbNoPrefix, String key_awbNo) {
		key_awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		key_awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		listAwb(key_awbNoPrefix, key_awbNo);
		minWait();
		Assert.assertTrue(waitForElement(info_bookingStatus).getAttribute("value").equals("Cancelled"));
		return this;
	}

	/**
	 * Check Status of Dimension
	 * 
	 * @param awbPre
	 * @return
	 */
	public CAP018 checkDimensionStatus() {
		minWait();

		Assert.assertTrue(waitForElement(txt_dimension).getAttribute("value").equals("YES"));
		return this;

	}

	/**
	 * Check Status of Based on UBN number
	 * 
	 * @param awbPre
	 * @return
	 */
	public CAP018 checkStatusCancelonUBNNumber(String UBRNo) {
		minWait();
		UBRNo = PropertyHandler.getPropValue(dataFilePath, UBRNo);
		enterKeys(txt_UBRNo, UBRNo);
		Assert.assertTrue(waitForElement(info_bookingStatus).getAttribute("value").equals("Cancelled"));
		return this;
	}

	/**
	 * Method to craete UBN number and store in property file
	 * 
	 * @param awbPre
	 * @return
	 */
	public CAP018 flightUBRNumber(String UBRNo) {
		String ubnNumber = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, UBRNo, ubnNumber);
		return this;
	}

	/**
	 * Method to Verify Flight Number in Screen
	 * 
	 * @param FlightNumber
	 * @return
	 */
	public CAP018 VerifyFlightNumber(String FlightNo) {
		minWait();
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		String expectedFlightNo = waitForElement(list_txt_fltNo).getAttribute("value");
		expectedFlightNo.replaceAll("[^0-9]", "");
		Assert.assertEquals(FlightNo, expectedFlightNo, "Flight No is same");
		return this;
	}

	/**
	 * Method to Verify Flight Number in Screen
	 * 
	 * @param FlightNumber
	 *            and Carrier Code
	 * @return
	 */
	public CAP018 VerifyFlightNos(String CarrierCode, String FlightNo) {
		minWait();
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		String FlightwithCarrierCode = CarrierCode.concat(FlightNo);
		String expectedFlightNo = waitForElement(list_txt_fltNo).getAttribute("value");
		Assert.assertEquals(FlightwithCarrierCode, expectedFlightNo, "Flight No is same");
		extentTest.log(LogStatus.PASS, "Carrier code and Flight no are matching");
		return this;
	}

	/**
	 * Method to Verify ULD
	 * 
	 * @param FlightNumber
	 * @return
	 */
	public CAP018 VerifyULD() {
		minWait();
		String Uld = PropertyHandler.getPropValue(dataFilePath, "ULDType");
		String expectedULD = waitForElement(txt_ULDfrst).getAttribute("value");
		Assert.assertEquals(Uld, expectedULD, "ULD Are matching");
		String UldSecond = PropertyHandler.getPropValue(dataFilePath, "SecondULDType");
		String expectedULDSecond = waitForElement(txt_ULDsecnd).getAttribute("value");
		return this;
	}

	/**
	 * Method to Verify ULD
	 * 
	 * @param FlightNumber
	 * @return
	 */
	public CAP018 getPIDValue() {
		String pid = PropertyHandler.getPropValue(dataFilePath, "PIDNo");
		WebElement ele = driver.findElement(By.xpath("//select[@name='serviceCode']"));
		Select select = new Select(ele);
		WebElement expValue = select.getFirstSelectedOption();
		String exppidno = expValue.getText();
		Assert.assertEquals(pid, exppidno, "PID Are matching");
		extentTest.log(LogStatus.PASS, "Successfully taken PID from the format" + exppidno);
		return this;
	}

	/**
	 * Method to do a Booking
	 * 
	 * @param FlightNumber
	 * @return
	 */
	public CAP018 BookingWithExistingFlightNo() {
		String pid = PropertyHandler.getPropValue(dataFilePath, "PIDNo");
		WebElement ele = driver.findElement(By.xpath("//select[@name='serviceCode']"));
		Select select = new Select(ele);
		WebElement expValue = select.getFirstSelectedOption();
		String exppidno = expValue.getText();
		Assert.assertEquals(pid, exppidno, "PID Are matching");
		extentTest.log(LogStatus.PASS, "Successfully taken PID from the format" + exppidno);
		return this;
	}

	// Faizan
	public CAP018 simpleHVBookingwithDVandInsurance(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String CarrierCode, String forceStatus, String commodityCode, String bookingStatus, String Insurance,
			String DVAmt, String customs) {
		// get Values from properties file
		Insurance = PropertyHandler.getPropValue(dataFilePath, Insurance);
		DVAmt = PropertyHandler.getPropValue(dataFilePath, DVAmt);
		customs = PropertyHandler.getPropValue(dataFilePath, customs);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);

		// enter values
		simpleBookingwithoutULD(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo, fltDt,
				CarrierCode, forceStatus);
		// addValuesInCAP018();
		// switch to rating tab and fill values
		click(btn_ratingTab);
		minWait();
		enterKeys(txt_Insurance, Insurance);
		enterKeys(txt_DVcarraiage, DVAmt);
		enterKeys(txt_customs, customs);
		extentTest.log(LogStatus.INFO, "Successfully Entered DV Amount:" + DVAmt + "\n\t" + "Insurance: "
				+ Insurance + "\n\t" + "Customs: " + customs);

		// CheckSheet
		fillCheckSheetafterSaveClick(commodityCode);// if needed, make changes
													// in values passed in
													// CheckSheet

		// Save and Capture UBR
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	// Sharath
	public CAP018 HVBookingwithDVandInsurance(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String ULDvol, String FlightNo, String fltDt, String CarrierCode,
			String forceStatus, String commodityCode, String bookingStatus, String Insurance, String DVAmt,
			String customs, String ULDType, String noOfULD, String ULDpcs, String ULDwt, String LoosePcs,
			String LooseWt, String commodityCode2) {
		// get Values from properties file
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		ULDpcs = PropertyHandler.getPropValue(dataFilePath, ULDpcs);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
		LoosePcs = PropertyHandler.getPropValue(dataFilePath, LoosePcs);
		LooseWt = PropertyHandler.getPropValue(dataFilePath, LooseWt);

		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);// Confirm
																				// or
																				// Queue
																				// (case-sensitive)
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);
		Insurance = PropertyHandler.getPropValue(dataFilePath, Insurance);
		DVAmt = PropertyHandler.getPropValue(dataFilePath, DVAmt);
		customs = PropertyHandler.getPropValue(dataFilePath, customs);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		commodityCode2 = PropertyHandler.getPropValue(dataFilePath, commodityCode2);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		noOfULD = PropertyHandler.getPropValue(dataFilePath, noOfULD);
		ULDvol = PropertyHandler.getPropValue(dataFilePath, ULDvol);

		// enter values
		click(btn_List);
		minWait();
		extentTest.log(LogStatus.INFO, "Clicked List Button in CAP018 screen");
		logger.info("Clicked List Button in CAP018 screen");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		// workaround
		// selectByText(By.xpath("//select[@name='serviceCargoClass']"),
		// "COMAT");
		//
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_productname, Product);

		enterKeys(txt_commCode, commodityCode + Keys.TAB);
		enterKeys(txt_commPcs, LoosePcs);
		enterKeys(txt_commWt, LooseWt);
		// enterKeys(txt_shipmentDescrptn, "GEN");

		minWait();
		setDimensions(LoosePcs, LooseWt);

		//
		click(By.name("addBookingDetailLink"));
		enterKeys(getWebElements(By.xpath("//input[@name='commodityCode']")).get(1), commodityCode2 + Keys.TAB);
		enterKeys(getWebElements(txt_commPcs).get(1), ULDpcs);
		enterKeys(getWebElements(txt_commWt).get(1), ULDwt);
		// enterKeys(getWebElements(By.xpath("//input[@name='commodityCode']")).get(1),commodityCode);
		minWait();
		// enterKeys(getWebElements(txt_shipmentDescrptn).get(1), "GEN");
		enterKeys(txt_uldType, ULDType);
		enterKeys(txt_noOfULD, noOfULD);

		/*
		 * int uWt = Integer.parseInt(ULDwt); int uPcs =
		 * Integer.parseInt(ULDpcs); int newUWt = uWt/uPcs; String nUWt =
		 * Integer.toString(newUWt);
		 */

		enterKeys(By.name("uldWeight"), getuldWt(ULDwt, ULDpcs));
		maxWait();
		enterKeys(getWebElements(By.xpath("//input[@name='uldCommodityCode']")).get(0), commodityCode2);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		//
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered the shipment details");
		logger.info("Successfully entered the shipment details");

		// switch to rating tab and fill values
		click(btn_ratingTab);
		minWait();
		enterKeys(txt_Insurance, Insurance);
		enterKeys(txt_DVcarraiage, DVAmt);
		enterKeys(txt_customs, customs);
		extentTest.log(LogStatus.INFO, "Successfully Entered DV Amount:" + DVAmt + "\n\t" + "Insurance: "
				+ Insurance + "\n\t" + "Customs: " + customs);
		logger.info("Successfully Entered DV Amount:" + DVAmt + "\n\t" + "Insurance: " + Insurance + "\n\t"
				+ "Customs: " + customs);

		// CheckSheet
		// fillCheckSheetafterSaveClick(commodityCode);//if needed, make changes
		// in values passed in CheckSheet

		// Save and Capture UBR
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Faizan
	 * @date 30-01-2019
	 */
	public CAP018 simpleBookingwithoutULD(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String CarrierCode,
			String commCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		minWait();
		extentTest.log(LogStatus.INFO, "Clicked List Button in CAP018 screen: ");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		/*
		 * try { vol = PropertyHandler.getPropValue(dataFilePath, vol);
		 * enterKeys(txt_commVol, vol); } catch(NullPointerException e) {
		 * System.out.println("Volume not given as TestData"); }
		 */
		// enterKeys(txt_shipmentDescrptn, "GEN");
		setDimensions(pcs, wt);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: " + carrierWithFlightno);
		return this;
	}

	// Faizan A-8452

	public CAP018 addValuesInCAP018() {

		try {
			String scc = PropertyHandler.getPropValue(dataFilePath, "scc");
			enterKeys(txt_scc, scc);
		} catch (NullPointerException ex) {
			System.out.println();
		}

		try {
			String commodityCode = PropertyHandler.getPropValue(dataFilePath, "commodityCode");
			enterKeys(txt_commCode, commodityCode + Keys.TAB);
		} catch (NullPointerException ex) {
			System.out.println();
		}

		try {
			String allotID = PropertyHandler.getPropValue(dataFilePath, "allotID");
			enterKeys(list_txt_fltAllotId, allotID + Keys.TAB);
		} catch (NullPointerException ex) {
			System.out.println();
		}

		return this;
	}

	// Faizan A-8452
	public CAP018 fillCheckSheetafterSaveClick(String commodityCode) {
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode).trim();
		click(btn_save);
		maxWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)) {
			click(btn_genericYes);
		}
		minWait();
		fillCheckSheet(commodityCode);
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	public void fillCheckSheet(String commodityCode) {
		maxWait();
		waitForFrameAndSwitch(screenFrame);
		waitForFrameAndSwitch("popupContainerFrame");

		switch (commodityCode.toUpperCase()) {
		case "HUMUNCREM":
			enterKeys(checkSheet_0, "TESTING");
			enterKeys(checkSheet_1, "Casket");
			selectByText(checkSheet_2, "Yes");
			selectByText(checkSheet_3, "No");
			enterKeys(checkSheet_4, "Prepaid");
			enterKeys(checkSheet_5, "Embalmed");
			// 7 not given related data
			selectByText(checkSheet_7, "No");
			selectByText(checkSheet_8, "No");

			enterKeys(checkSheet_12, "IBSAT");
			enterKeys(checkSheet_13, "1234567890");

			// Add more values if needed
			click(checkSheetSaveBtn);
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);

			break;
		case "AVIWARM":
			click(SelectBtnInAVICS);
			click(checkSheetAVIothers);
			enterKeys(checkSheet_1, "FROGS");
			enterKeys(checkSheet_3_avi, "FROGS");
			enterKeys(checkSheet_19, "IBSAT");
			enterKeys(checkSheet_20, "0123456789");
			// Add more values if needed
			click(checkSheetSaveBtn);
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		case "VALCARGO":
			selectByText(By.xpath("(//*[@name='questionwithAnswer[0].templateAnswer'])[2]"), "No");
			selectByText(By.xpath("//select[@name='questionwithAnswer[1].templateAnswer']"), "Yes");
			selectByText(By.xpath("//select[@name='questionwithAnswer[8].templateAnswer']"), "Yes");
			selectByText(By.xpath("//select[@name='questionwithAnswer[10].templateAnswer']"), "No");
			// Add more values if needed
			click(checkSheetSaveBtn);
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		case "MILHUM":
			// selectByText(By.xpath("//select[@name='questionwithAnswer[0].templateAnswer']"),
			// "Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'empty casket and cadavers')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Checked PAL is Verified Known')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Asked the customer weight')]/../../following-sibling::div[1]//select"),
					"Yes");
			enterKeys(By.xpath("//input[contains(@value,'Weight Given')]/../../following-sibling::div[1]/textarea"),
					"100");
			selectByText(By.xpath("//input[contains(@value,'Any Escorts?')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Sent approvals for inbound to')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Advised customer that for')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'All station approvals in scribble pad')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'WB to NB Flight Detail')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Advised the customer of cut off time')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Added the consignee information and checked')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Scribble Pad documented with callers name')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'booked based on the weight and dimensions provided')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Is this an international HUM')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath("//input[contains(@value,'Booked in Kilos')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Documents discussed and documented')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Advised International must travel')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(By.xpath("//input[contains(@value,'Add /NO EEI 30')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Advise shipper to send paperwork to TLC')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Shipments out of Canada booked by a freight forwarder')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Customs available upon arrival')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Is this a Military HUM')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Scribble Pad includes MIL and rank')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Scribble Pad includes MIL branch served')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Honor guard location documented')]/../../following-sibling::div[1]//select"),
					"Yes");
			selectByText(
					By.xpath(
							"//input[contains(@value,'Escort name/rank (if applicable) and PNR')]/../../following-sibling::div[1]//select"),
					"Yes");
			/*
			 * selectByText(By.
			 * xpath("//input[contains(@value,'Is this an infant HUM')]/../../following-sibling::div[1]//select"
			 * ), "Yes"); selectByText(By.
			 * xpath("//input[contains(@value,'Allowed in the casket w/parent')]/../../following-sibling::div[1]//select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//input[contains(@value,'')]/../../following-sibling::div[1]//select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//input[contains(@value,'')]/../../following-sibling::div[1]//select"
			 * ), "Yes");
			 */
			click(checkSheetSaveBtn);
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		case "MEDEVAC":
			enterKeys(
					By.xpath(
							"//input[contains(@value,'individual requesting the booking')]/../../following-sibling::div[1]/textarea"),
					"Test Person");
			enterKeys(By.xpath("//input[contains(@value,'Company name')]/../../following-sibling::div[1]/textarea"),
					"Test Company");
			/*
			 * selectByText(By.xpath(
			 * "//select[@name='questionwithAnswer[7].templateAnswer']"),
			 * "Yes"); selectByText(By.xpath(
			 * "//select[@name='questionwithAnswer[8].templateAnswer']"), "No");
			 */
			click(checkSheetSaveBtn);
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		case "ACTTCPIL":
			selectByText(By.xpath("//span[text()='1.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='2.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='3.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='4.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='5.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='6.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='7.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='8.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='9.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='10.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			click(By.xpath("//button[@name='btnSave']"));
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		case "CATDOG":
			selectByText(By.xpath("//span[text()='1.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='2.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='3.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='4.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='5.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='6.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='7.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='8.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='9.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='11.']/parent::label/../following-sibling::div[1]/select"), "No");
			selectByText(By.xpath("//span[text()='12.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='13.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			selectByText(By.xpath("//span[text()='14.']/parent::label/../following-sibling::div[1]/select"), "Yes");
			click(By.xpath("//button[@name='btnSave']"));
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		case "TCSACT":
			selectByText(By.xpath("//input[contains(@value,'Customs hours checked for Origin/Connection/Destination')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'Email added to scribble pad')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'EPXTC ACTIVE added to scribble pad')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'Dry Ice wording (if applicable) added to scribble pad')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'Demurrage start date added in scribble pad')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'Container Lease fees added to Rating & Charges field')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'Express Order fees added to Rating & Charges field')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'Repositioning fees added to Rating & Charges field')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'DG fee added to Rating & Charges field')]/../../following-sibling::div[1]//select"),"Yes");
			selectByText(By.xpath("//input[contains(@value,'After hours approvals obtained and documented in scribble pad')]/../../following-sibling::div[1]//select"),"Yes");
			click(checkSheetSaveBtn);
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);

		break;
			

			
		case "AVICOLD":
			selectByText(
					By.xpath(
							"//input[contains(@value,'Customs hours were checked')]/../../following-sibling::div[1]//select"),
					"Yes");
			/*
			 * enterKeys(By.
			 * xpath("//input[contains(@value,'acclimation temperature')]/../../following-sibling::div[1]/textarea"
			 * ),"-30"); selectByText(By.xpath(
			 * "//span[text()='1.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='2.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='3.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='4.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='5.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='6.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='7.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='8.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='9.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes"); selectByText(By.xpath(
			 * "//span[text()='10.']/parent::label/../following-sibling::div[1]/select"
			 * ), "Yes");
			 */
			click(By.xpath("//button[@name='btnSave']"));
			extentTest.log(LogStatus.INFO,
					"Successfully entered values in Check Sheet for commodity: " + commodityCode);
			break;

		default:
			Assert.assertTrue(false, "Wrong commodity Code given");

		}

	}

	/**
	 * Saves details of CAP018 and verifies UBR and makes a copy of UBR in
	 * properties file and check Booking Status
	 * 
	 * @param awb
	 * @return
	 * @author Faizan
	 * @date 30-01-2019
	 */
	public CAP018 saveInCAP018AndVerifyUBRnStatusDirect(String awbno, String commCode, HashMap<String, String> data) {
//		awbno = PropertyHandler.getPropValue(dataFilePath, awbno); 
	//	commCode = PropertyHandler.getPropValue(dataFilePath, commCode); 
		click(btn_save);
		// try{
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(By.xpath("//p[contains(text(),'UNID information for all DG SCC')]"))) {
			click(btn_genericYes);
		}
		waitForFrameAndSwitch(screenFrame);
//		maxWait();
	/*		driver.switchTo().defaultContent();
			if (verifyElementPresent(By.xpath("//p[contains(text(),'Do you want to capture Check Sheet')]"))) {
				click(By.xpath("//button[text()='Yes']"));
				fillCheckSheet(commCode);
				// to be checked
				driver.switchTo().defaultContent();
				waitForFrameAndSwitch(screenFrame);
				click(btn_save);
			} else {
				waitForFrameAndSwitch(screenFrame);
			}*/
//		continueEmbargo();
		/*
		 * }catch(Exception e){ driver.switchTo().defaultContent(); //
		 * waitForFrameAndSwitch(screenFrame);
		 * extentTest.log(LogStatus.INFO,
		 * "No Restriction applied for the commodity");
		 * logger.info("No Restriction applied for the commodity"); }
		 */ /*
			 * try{ handleAlert("Accept", "CAP018");
			 * waitForFrameAndSwitch(screenFrame); } catch(Exception e){
			 * waitForFrameAndSwitch(screenFrame); }
			 */
	/*	if (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			String msg = waitForElement(info_msg).getText();
			extentTest.log(LogStatus.INFO, "Clicked Yes on Popup : " + msg);
			logger.info("Clicked Yes on Popup : " + msg);
//			minWait();
		}*/
//		maxWait();
//		maxWait();
	/*	if (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			String msg = waitForElement(info_msg).getText();
			extentTest.log(LogStatus.INFO, "Clicked Yes on Popup : " + msg);
			logger.info("Clicked Yes on Popup : " + msg);
//			minWait();
		}*/
//		minWait();
/*		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();*/
		// waitForFrameAndSwitch(screenFrame);
//		maxWait();
//		maxWait();
//		if (driver.getWindowHandles().size() > 1) {
		if(getNumberOfWindows()>1) {
			switchToSecondWindow();
			click(btn_popUp_Ok);
			switchBackToFirstWindow();
			minWait();
		}
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
//		minWait();

    	String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		data.put(awbno, awb);

		if (waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase("CONFIRMED")
				|| waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase("QUEUED")) {
			extentTest.log(LogStatus.PASS, "Successfully booked with: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
			logger.info("Successfully booked with: " + awb + " and UBR No: " + ubrNo);

		} else {
			extentTest.log(LogStatus.FAIL, "VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo
					+ "\n" + "Booking Status doesn't match");
			logger.info("VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo + "\n"
					+ "Booking Status doesn't match");
			Assert.fail("VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo + "\n"
					+ "Booking Status doesn't match");
		}
		extentTest.log(LogStatus.INFO,
				"Booking Status: " + waitForElement(info_bookingStatus).getAttribute("value"));
		return this;
	}

	/**
	 * Saves details of CAP018 and verifies UBR and makes a copy of UBR in
	 * properties file and check Booking Status
	 * 
	 * @param awb
	 * @return
	 * @author Faizan
	 * updated by Sharath
	 * @date 30-01-2019
	 */

	public CAP018 saveInCAP018AndVerifyUBRStatus(String awbNo, String bookingStatus) {
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno); depricated
		bookingStatus = PropertyHandler.getPropValue(dataFilePath, bookingStatus);// added
		click(btn_save);
		maxWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(By.xpath("//p[contains(text(),'Do you want to capture Check Sheet')]"))) {
			click(By.xpath("//button[text()='Yes']"));
			fillCheckSheet(PropertyHandler.getPropValue(dataFilePath, "commCode"));
			waitForFrameAndSwitch(screenFrame);
			click(btn_save);
		} else {
			waitForFrameAndSwitch(screenFrame);
		}
		continueEmbargo();
		/*
		 * try{ handleAlert("Accept", "CAP018");
		 * waitForFrameAndSwitch("iCargoContentFrameCAP018"); } catch(Exception
		 * e){ waitForFrameAndSwitch("iCargoContentFrameCAP018"); }
		 */
		maxWait();
		maxWait();
		if (verifyElementPresent(btn_genericYes)) {
			click(btn_genericYes);
			String msg = waitForElement(info_msg).getText();
			extentTest.log(LogStatus.INFO, "Clicked Yes on Popup : " + msg);
			logger.info("Clicked Yes on Popup : " + msg);
			minWait();
		}
		minWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		// waitForFrameAndSwitch(screenFrame);
		maxWait();
		maxWait();

		if (driver.getWindowHandles().size() > 1) {
			switchToSecondWindow();
			click(btn_popUp_Ok);
			switchBackToFirstWindow();
		}

		waitForFrameAndSwitch(screenFrame);
		minWait();

		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, "ubr", ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbNo, awb);
		
		if (waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase("CONFIRMED")
				|| waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase("QUEUED")) {
			extentTest.log(LogStatus.PASS, "Successfully booked with: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
			logger.info("Successfully booked with: " + awb + " and UBR No: " + ubrNo);
		} else {
			extentTest.log(LogStatus.FAIL, "VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo
					+ "\n" + "Booking Status doesn't match");
			logger.info("VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo + "\n"
					+ "Booking Status doesn't match");
			// Assert.fail("VAILIDATION FAILURE: booked with: " + awb
			// + "and UBR No: " + ubrNo+"\n"+"Booking Status doesn't match");
		}
		extentTest.log(LogStatus.INFO,
				"Booking Status: " + waitForElement(info_bookingStatus).getAttribute("value"));
		return this;
	}

	// Sharath
	public CAP018 DGBookingwithPaymentCC(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String ubr,
			String CarrierCode, String ProductDesptn, String PaymentType, String ULDpcs, String ULDwt, String LoosePcs,
			String LooseWt, String ULDType, String noOfULD, String PIValue, String UNIDNo, String DG) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		ProductDesptn = PropertyHandler.getPropValue(dataFilePath, ProductDesptn);
		PaymentType = PropertyHandler.getPropValue(dataFilePath, PaymentType);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		ULDpcs = PropertyHandler.getPropValue(dataFilePath, ULDpcs);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
		LoosePcs = PropertyHandler.getPropValue(dataFilePath, LoosePcs);
		LooseWt = PropertyHandler.getPropValue(dataFilePath, LooseWt);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		noOfULD = PropertyHandler.getPropValue(dataFilePath, noOfULD);
		PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
		UNIDNo = PropertyHandler.getPropValue(dataFilePath, UNIDNo);
		DG = PropertyHandler.getPropValue(dataFilePath, DG);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Booking has been started for the shipment");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		click(icon_CCShipment);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);

		enterKeys(getWebElements(By.xpath("//input[@name='commodityCode']")).get(0), "UN3257");
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		minWait();
		setDimensions(pcs, wt);

		enterKeys(txt_productname, Product);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");

		// DGR UNID
		click(btn_DGR);
		maxWait();
		// maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		minWait();
		click(btn_PopUpicon);
		minWait();
		enterKeys(txt_EmergencyContactName, "Test");
		enterKeys(txt_EmergencyContactNumber, "123456");
		click(btn_iconOK);
		enterKeys(txt_DGRUNIDnumber, UNIDNo + Keys.TAB);
		maxWait();
		selectByText(Select_ShippingName, DG);
		enterKeys(txt_P1Value, PIValue);
		enterKeys(txt_NoofPackges, "1");
		enterKeys(txt_NetValue, pcs);
		selectByText(Select_Reportable, "Yes");

		click(btn_Popupadd);
		click(btn_PopupOK);
		extentTest.log(LogStatus.PASS, "Successfully filled details with UNID: " + UNIDNo);
		maxWait();
		try {
			handleAlert("Accept", "CAP018");
		} catch (Exception e) {
		}
		maxWait();
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		click(btn_save);
		extentTest.log(LogStatus.INFO, "Saved all the booking details");
		maxWait();
		continueEmbargo();
		maxWait();
		// DGR pop-up
		maxWait();
		try {
			handleAlert("Accept", "CAP018");
		} catch (Exception e) {

		}
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		// LAT pop-up
		maxWait();
		try {
			handleAlert("Accept", "CAP018");
		} catch (Exception e) {

		}
		waitForFrameAndSwitch(screenFrame);

		// booking successful popup
		maxWait();
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);
		if (!ubrNo.equals(null) || !awb.equals(null)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with AWB No: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
		} else {
			extentTest.log(LogStatus.FAIL,
					"Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
			Assert.fail("Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
		}
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
				extentTest.log(LogStatus.PASS, "Accepted Alert text " + AlertText + ScreenName);
				break;

			case "Accept":
				driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[1]")).click();
				extentTest.log(LogStatus.PASS, "Accepted Alert with text " + ScreenName);

				break;
			case "Dismiss":
				driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[2]")).click();
				extentTest.log(LogStatus.PASS, "Clicked on Dismiss button" + ScreenName);
				break;
			case "Close":
				driver.findElement(By.xpath("(//button[@title='Close'])[2]|//button[@name='btClose']")).click();
				extentTest.log(LogStatus.PASS, "Clicked on Close button " + ScreenName);
				break;
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to handle Alert " + ScreenName);

		}
	}

	// Sharath
	public CAP018 verifyUpdatedWeight(String awbPre, String awbNo, String newWt) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		newWt = PropertyHandler.getPropValue(dataFilePath, newWt);

		listAwb(awbPre, awbNo);
		minWait();
		String actualValue = getAttributebyValue(By.xpath("(//input[@name='weight'])[1]"));
		if (actualValue.equals(newWt)) {
			extentTest.log(LogStatus.PASS, "Weight has been successfully updated in booking screen");
		} else {
			extentTest.log(LogStatus.FAIL, "Weight hasn't been updated in booking screen");
		}

		return this;

	}

	// A-8452 Faizan
	public CAP018 temperatureCheckAndSave(String PopUpYesOrNo, String bookingStatus, String awbno) {
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		bookingStatus = PropertyHandler.getPropValue(dataFilePath, bookingStatus);
		minWait();
		click(btn_save);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)) {
			String popUp_message = waitForElement(By.xpath("//div[@id='ic-sd-msgc']/p")).getText();
			if (PopUpYesOrNo.toLowerCase().equals("yes")) {
				click(btn_genericYes);
				extentTest.log(LogStatus.INFO, "Successfully clicked Yes on pop-up: " + popUp_message);
			} else {
				click(btn_noBtn);
				extentTest.log(LogStatus.INFO, "Successfully clicked No on pop-up: " + popUp_message);

			}
		}
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();

		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, "ubr", ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);

		if (waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase(bookingStatus)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with: " + awb + "and UBR No: " + ubrNo);
			captureAndAddScreenshot();

		} else {
			extentTest.log(LogStatus.FAIL, "VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo
					+ "\n" + "Booking Status doesn't match");
			captureAndAddScreenshot();
		}
		extentTest.log(LogStatus.INFO,
				"Booking Status: " + waitForElement(info_bookingStatus).getAttribute("value"));
		return this;
	}

	/*
	 * Souvik A-8457 Date: 01-04-2019 MultiLeg(2 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor2Legs(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String scc, String pcs, String wt, String vol, String FlightNo1, String fltDt,
			String intermediate, String ubr, String commCode, String CarrierCode, String P1Value, String UDIDNo,
			String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt, String FlightNo2) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		P1Value = PropertyHandler.getPropValue(dataFilePath, P1Value);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		FlightNo2 = PropertyHandler.getPropValue(dataFilePath, FlightNo2);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + FlightNo2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);

		int legNo = Integer.parseInt(PropertyHandler.getPropValue(dataFilePath, NoOfLegs));// 2
																							// for
																							// 2
																							// leg
																							// flight

		simpleBookingwithoutULDandMultilegFLT1(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo1,
				fltDt, CarrierCode, intermediate, commCode);
		enterAnotherFlightforMultilegFlt1(intermediate, dest, pcs, wt, flt2Dt, carrierWithFlightno2, forceStatus,
				legNo);
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);
		return this;
	}

	/*
	 * Sharath A-8457 Date: 01-04-2019 MultiLeg(2 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor2LegsOAtoAA(String prefix, String awbno, String origin, String intermediate,
			String dest, String agentCode, String Product, String scc, String pcs, String wt, String vol,
			String FlightNo1, String FlightNo2, String fltDt1, String fltDt2, String commodityCode, String CarrierCode1,
			String CarrierCode2, String forceStatus, String bookingStatus, String ubr) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		FlightNo2 = PropertyHandler.getPropValue(dataFilePath, FlightNo2);
		fltDt1 = PropertyHandler.getPropValue(dataFilePath, fltDt1);
		fltDt2 = PropertyHandler.getPropValue(dataFilePath, fltDt2);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		CarrierCode1 = PropertyHandler.getPropValue(dataFilePath, CarrierCode1);
		CarrierCode2 = PropertyHandler.getPropValue(dataFilePath, CarrierCode2);
		bookingStatus = PropertyHandler.getPropValue(dataFilePath, bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);

		String carrierWithFlightno1 = CarrierCode1 + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode2 + FlightNo2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);

		simpleBookingWithoutULDFirstLegOA(prefix, awbno, origin, intermediate, dest, agentCode, Product, pcs, wt, vol,
				FlightNo1, fltDt1, ubr, carrierWithFlightno1, commodityCode);
		enterAnotherFlightforMultilegFlt1(intermediate, dest, pcs, wt, fltDt2, carrierWithFlightno2, forceStatus, 2);
		/*
		 * click(btn_save); handleAlert("Accept", "CAP018"); //
		 * waitForFrameAndSwitch(screenFrame); //
		 * driver.switchTo().defaultContent(); fillCheckSheet(commodityCode);
		 * click(By.name("btnSave")); driver.switchTo().defaultContent();
		 * waitForFrameAndSwitch(screenFrame);
		 */
		fillCheckSheetafterSaveClick("commCode");
		saveInCAP018AndVerifyUBRStatus(awbno, "bookingStatus");

		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Faizan A-8452
	 * @date 25-02-2019
	 */
	public CAP018 simpleBookingwithoutULDandMultilegFLT1(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String CarrierCode, String intermediate, String commCode) {

		String carrierWithFlightno = CarrierCode.concat(FlightNo);

		// enterKeys(txt_awbNoPrefix, prefix);
		// enterKeys(txt_awbNo, awbno);
		click(btn_List);
		// extentTest.log(LogStatus.INFO, "Successfully entered Prefix
		// and AWB no in CAP018 screen: "+awbno);
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(By.name("shipmentDate"), fltDt + Keys.TAB);
		if (Product.equals("COMAT")) {
			selectByText(By.name("serviceCargoClass"), "COMAT");
		}
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive

		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);

		// driver.findElement(By.name("shipmentDate")).sendKeys(".");
		// driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);

		// enterKeys(txt_shipmentDescrptn, "GEN");
		setDimensions(pcs, wt);

		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: " + carrierWithFlightno);
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Faizan A-8452
	 * @date 25-02-2019
	 */
	public CAP018 simpleBookingwithoutULDandMultilegFLT(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String CarrierCode, String intermediate, String forceStatus) {

		String carrierWithFlightno = CarrierCode.concat(FlightNo);

		// enterKeys(txt_awbNoPrefix, prefix);
		// enterKeys(txt_awbNo, awbno);
		click(btn_List);
		// extentTest.log(LogStatus.INFO, "Successfully entered Prefix
		// and AWB no in CAP018 screen: "+awbno);
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(By.name("shipmentDate"), fltDt + Keys.TAB);
		minWait();
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		// driver.findElement(By.name("shipmentDate")).sendKeys(".");
		// driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_shipmentDescrptn, "GEN");
		setDimensions(pcs, wt);
		enterKeys(txt_productname, Product);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		enterKeys(list_txt_fltVol, vol);
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, forceStatus);
		extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: " + carrierWithFlightno);
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Faizan A-8452
	 * @date 25-02-2019
	 */
	public CAP018 simpleBookingwithULDandMultilegFLT(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String CarrierCode, String intermediate, String forceStatus, String ULDType, String ULDwt,
			String commCode) {

		String carrierWithFlightno = CarrierCode.concat(FlightNo);

		// enterKeys(txt_awbNoPrefix, prefix);
		// enterKeys(txt_awbNo, awbno);
		click(btn_List);
		// extentTest.log(LogStatus.INFO, "Successfully entered Prefix
		// and AWB no in CAP018 screen: "+awbno);
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(By.name("shipmentDate"), fltDt + Keys.TAB);
		minWait();
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		// driver.findElement(By.name("shipmentDate")).sendKeys(".");
		// driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		if (ULDType == null || ULDType.equals("")) {
			setDimensions(pcs, wt);
		} else {
			enterKeys(txt_commVol, vol);
			enterKeys(txt_uldType, ULDType);
			enterKeys(txt_noOfULD, "1");
			enterKeys(txt_uldWt, wt);
			enterKeys(txt_uldCommodityCode, commCode);
		}
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		enterKeys(list_txt_fltVol, vol);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered leg 1 flight Number: " + carrierWithFlightno);
		return this;
	}

	/**
	 * In case of Multi-Leg Flight, enters another flight data
	 * 
	 * 
	 * @param awb
	 * @return
	 * @author Faizan A-8452
	 * @date 25-02-2019
	 */
	public CAP018 enterAnotherFlightforMultilegFlt1(String OriginOfNewleg, String destOfNewleg, String pcs, String wt,
			String fltDt, String carrierWithFlightno, String forceStatus, int legNo) {
		legNo -= 1;

		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), OriginOfNewleg);
		enterKeys(getWebElements(list_txt_fltDest).get(legNo), destOfNewleg);
		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
		enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
		scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
		// selectByText(getWebElements(list_dropDown_fltForce).get(legNo),
		// forceStatus);
		extentTest.log(LogStatus.INFO,
				"Successfully entered leg" + (legNo + 1) + " flight as: " + carrierWithFlightno);
		return this;
	}

	public CAP018 enterFlightforMultilegFlt1(String OriginOfNewleg, String destOfNewleg, String pcs, String wt,
			String vol, String fltDt, String carrierWithFlightno, String forceStatus, int legNo) {
		legNo -= 1;
		
		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), OriginOfNewleg);
		enterKeys(getWebElements(list_txt_fltDest).get(legNo), destOfNewleg);
		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
//		enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
//		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
		// enterKeys(getWebElements(list_txt_fltVol).get(legNo),vol);
		scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
		// selectByText(getWebElements(list_dropDown_fltForce).get(legNo),
		// forceStatus);
		extentTest.log(LogStatus.INFO,
				"Successfully entered leg" + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);

		return this;
	}
	
//	Sharath
	public CAP018 enterFlightforMultilegFlt1(String OriginOfNewleg, String destOfNewleg, String pcs, String wt,
			String vol, String fltDt, String carrierCode, String FlightNo, String forceStatus, int legNo) {
        
		legNo -= 1;
		
		String carrierWithFlightno = carrierCode+FlightNo;
		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), OriginOfNewleg);
		enterKeys(getWebElements(list_txt_fltDest).get(legNo), destOfNewleg);
		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
//		enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
//		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
		// enterKeys(getWebElements(list_txt_fltVol).get(legNo),vol);
		scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
		 selectByText(getWebElements(list_dropDown_fltForce).get(legNo), "Confirm");
		extentTest.log(LogStatus.INFO,
				"Successfully entered leg" + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);
	

		return this;
	}

	/*
	 * Souvik A-8457 Date: 01-04-2019 MultiLeg(4 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor4Legs(String prefix, String awbno, String nonHuborigin,
			String LiteInternationaldestination, String agentCode, String Product, String scc, String pcs, String wt,
			String vol, String FlightNo, String fltDt, String hubintermediate1, String hubintermediate2,
			String liteintermediate3, String ubr, String commodityCode, String CarrierCode, String P1Value,
			String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt,
			String carrierWithFlightno2, String carrierWithFlightno3, String carrierWithFlightno4, String flt3Dt,
			String flt4Dt) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		nonHuborigin = PropertyHandler.getPropValue(dataFilePath, nonHuborigin);
		LiteInternationaldestination = PropertyHandler.getPropValue(dataFilePath, LiteInternationaldestination);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		P1Value = PropertyHandler.getPropValue(dataFilePath, P1Value);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		hubintermediate1 = PropertyHandler.getPropValue(dataFilePath, hubintermediate1);
		hubintermediate2 = PropertyHandler.getPropValue(dataFilePath, hubintermediate2);
		liteintermediate3 = PropertyHandler.getPropValue(dataFilePath, liteintermediate3);
		carrierWithFlightno2 = PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno2);
		carrierWithFlightno3 = PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno3);
		carrierWithFlightno4 = PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno4);
		flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
		flt4Dt = PropertyHandler.getPropValue(dataFilePath, flt4Dt);

		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno", carrierWithFlightno);

		int legNo = Integer.parseInt(PropertyHandler.getPropValue(dataFilePath, NoOfLegs));// 2
																							// for
																							// 2
																							// leg
																							// flight

		simpleBookingwithoutULDandMultilegFLT1(prefix, awbno, nonHuborigin, LiteInternationaldestination, agentCode,
				Product, pcs, wt, vol, FlightNo, fltDt, CarrierCode, hubintermediate1, forceStatus);
		enterAnotherFlightforMultilegFlt1(hubintermediate1, hubintermediate2, pcs, wt, flt2Dt, carrierWithFlightno2,
				forceStatus, 2);
		enterAnotherFlightforMultilegFlt1(hubintermediate2, liteintermediate3, pcs, wt, flt3Dt, carrierWithFlightno3,
				forceStatus, 3);
		enterAnotherFlightforMultilegFlt1(liteintermediate3, LiteInternationaldestination, pcs, wt, flt4Dt,
				carrierWithFlightno4, forceStatus, 4);
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	/*
	 * Sharath A-8680 Date: 20-05-2019 MultiLeg(3 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor3Legs(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String scc, String pcs, String wt, String vol, String FlightNo1, String fltDt,
			String intermediate1, String intermediate2, String ubr, String commodityCode, String CarrierCode,
			String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt, String Flightno2,
			String Flightno3, String flt3Dt, String ULDType, String ULDwt) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		// scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
		intermediate2 = PropertyHandler.getPropValue(dataFilePath, intermediate2);
		Flightno2 = PropertyHandler.getPropValue(dataFilePath, Flightno2);
		Flightno3 = PropertyHandler.getPropValue(dataFilePath, Flightno3);
		flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + Flightno2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);
		String carrierWithFlightno3 = CarrierCode + Flightno3;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno3", carrierWithFlightno3);

		simpleBookingwithULDandMultilegFLT(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo1,
				fltDt, CarrierCode, intermediate1, forceStatus, ULDType, ULDwt, commodityCode);

		// simpleBookingwithoutULDandMultilegFLT(prefix, awbno, origin, dest,
		// agentCode, Product, pcs, wt, vol, FlightNo1, fltDt, CarrierCode,
		// intermediate1,forceStatus);
		enterFlightforMultilegFlt1(intermediate1, intermediate2, pcs, wt, vol, flt2Dt, carrierWithFlightno2,
				forceStatus, 2);
		enterFlightforMultilegFlt1(intermediate2, dest, pcs, wt, vol, flt3Dt, carrierWithFlightno3, forceStatus, 3);
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	/*
	 * Sharath A-8680 Date: 20-05-2019 MultiLeg(3 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor3LegsSplitShipments(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String scc, String pcs, String wt, String vol, String FlightNo1,
			String fltDt, String intermediate1, String intermediate2, String ubr, String commodityCode,
			String CarrierCode, String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt,
			String Flightno2, String Flightno3, String flt3Dt, String ULDType, String ULDwt, String pcs1, String wt1) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		// scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
		intermediate2 = PropertyHandler.getPropValue(dataFilePath, intermediate2);
		Flightno2 = PropertyHandler.getPropValue(dataFilePath, Flightno2);
		Flightno3 = PropertyHandler.getPropValue(dataFilePath, Flightno3);
		flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
		pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
		wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + Flightno2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);
		String carrierWithFlightno3 = CarrierCode + Flightno3;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno3", carrierWithFlightno3);

		simpleBookingwithULDandMultilegFLT(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo1,
				fltDt, CarrierCode, intermediate1, forceStatus, ULDType, ULDwt, commodityCode);

		// simpleBookingwithoutULDandMultilegFLT(prefix, awbno, origin, dest,
		// agentCode, Product, pcs, wt, vol, FlightNo1, fltDt, CarrierCode,
		// intermediate1,forceStatus);
		enterFlightforMultilegFlt1(intermediate1, intermediate2, pcs, wt, vol, flt2Dt, carrierWithFlightno2,
				forceStatus, 2);
		enterFlightforMultilegFlt1(intermediate2, dest, pcs, wt, vol, flt3Dt, carrierWithFlightno3, forceStatus, 3);
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	/*
	 * Sharath A-8680 Date: 20-05-2019 MultiLeg(2 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor2LegwithValueAddedServices(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String scc, String pcs, String wt, String vol, String FlightNo1,
			String fltDt, String intermediate, String ubr, String commodityCode, String CarrierCode, String UDIDNo,
			String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt, String Flightno2) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		// scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		Flightno2 = PropertyHandler.getPropValue(dataFilePath, Flightno2);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + Flightno2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);

		simpleBookingwithCommCodeAndULD("prefix", "awbno", "origin", "dest", "intermediate", "agentCode", "Product",
				"pcs", "wt", "vol", "FlightNo1", "fltDt1", "ubr", "commCode", "ULDType", "noOfULD", "CarrierCode");

		enterFlightforMultilegFlt1(intermediate, dest, pcs, wt, vol, flt2Dt, carrierWithFlightno2, forceStatus, 2);
		// captureTCDetails(wt, origin, fltDt, "uldNo");
		// Value added services code
		/*
		 * click(By.xpath("//button[text()='Value-Added Service']"));
		 * switchToSecondWindow(); maxWait();
		 * click(By.xpath("//tbody//td/input[@type='checkbox']"));
		 * click(By.name("btOk")); switchToFirstWindow();
		 * waitForFrameAndSwitch(screenFrame);
		 */
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);
		return this;
	}

	/*
	 * Sharath A-8680 Date: 20-05-2019 MultiLeg(3 legs) Simple booking
	 */

	public CAP018 MultilegBookingfor2LegsULD(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String scc, String pcs, String wt, String vol, String FlightNo1, String fltDt,
			String intermediate, String ubr, String commCode, String CarrierCode, String ULDwt, String forceStatus,
			String NoOfLegs, String bookingStatus, String flt2Dt, String Flightno2, String ULDType) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		// scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		Flightno2 = PropertyHandler.getPropValue(dataFilePath, Flightno2);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + Flightno2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);

		simpleBookingwithULDandMultilegFLT(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo1,
				fltDt, CarrierCode, intermediate, forceStatus, ULDType, ULDwt, commCode);
		enterFlightforMultilegFlt1(intermediate, dest, pcs, wt, vol, flt2Dt, carrierWithFlightno2, forceStatus, 2);
		// TC code
		maxWait();

		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	public CAP018 handleTCframe() {
		/*
		 * waitForFrameAndSwitch("popupContainerFrame"); CarrierCode =
		 * PropertyHandler.getPropValue(dataFilePath, CarrierCode); prefix =
		 * PropertyHandler.getPropValue(dataFilePath, prefix);
		 */

		return this;
	}

	// Faizan A-8452
	public CAP018 TLCBookingWithRestrictionWarningInCAP018(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String commodityCode, String wt, String vol, String FlightNo,
			String fltDt, String CarrierCode, String forceStatus, String bookingStatus) {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		// commodityCode = PropertyHandler.getPropValue(dataFilePath,
		// commodityCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		bookingStatus = PropertyHandler.getPropValue(dataFilePath, bookingStatus);

		simpleBooking1withoutULD(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo, fltDt,
				CarrierCode, forceStatus);
		addValuesInCAP018();
		fillCheckSheetafterSaveClick(commodityCode);
		saveInCAP018AndVerifyEmbargoRestriction();
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Faizan
	 * @date 30-01-2019
	 */
	public CAP018 simpleBooking1withoutULD(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String CarrierCode,
			String forceStatus) {

		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		list(prefix, awbno);
		extentTest.log(LogStatus.INFO, "Successfully entered Prefix and AWB no in CAP018 screen: " + awbno);
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);

		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		enterKeys(txt_shipmentDescrptn, "GEN");
		setDimensions(pcs, wt);
		enterKeys(txt_productname, Product);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		scrollToView(list_dropDown_fltForce);
		selectByText(list_dropDown_fltForce, forceStatus);
		extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: " + carrierWithFlightno);
		return this;
	}

	/**
	 * Saves details of CAP018 and verifies UBR and makes a copy of UBR in
	 * properties file and check Booking Status
	 * 
	 * @param awb
	 * @return
	 * @author Faizan
	 * @date 30-01-2019
	 */
	public CAP018 saveInCAP018AndVerifyEmbargoRestriction() {
		click(btn_save);
		maxWait();
		maxWait();

		// driver.switchTo().defaultContent();
		switchToSecondWindow();
		String message = driver.findElement(By.xpath("(//td[@class='iCargoTableDataTd'])[3]")).getText().trim();
		if (message.contains("Human Remains are not permitted to arrive on Saturday or Sunday")) {
			extentTest.log(LogStatus.PASS, "Cannot Do booking due to reason :  " + message);
		} else {
			extentTest.log(LogStatus.FAIL, "Booking Done");
			Assert.fail("Should Not have done the booking with Embargo and restriction");
		}

		click(By.xpath("//input[@name='btClose']"));
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);

		return this;
	}

	/**
	 * @author A-8452 Faizan
	 * @param awbPre
	 * @param awbNo
	 * @param UBRNo
	 * @param BookingStatus
	 * @param verifyBooking
	 * @return
	 */
	public CAP018 ListAndVerifyBookingStatus(String awbPre, String awbNo, String UBRNo, String BookingStatus,
			boolean verifyBooking) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		listAwb(awbPre, awbNo);
		minWait();

		driver.switchTo().defaultContent();

		if (verifyElementPresent(info_msg)) {
			if (!(driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?"))) {
				extentTest.log(LogStatus.INFO,
						"Pop-up message after listing: " + driver.findElement(info_msg).getText());
				click(btn_genericYes);
			} else {
				extentTest.log(LogStatus.FAIL, "Unable to list in CAP018");
				Assert.fail("Unable to list AWB in CAP018");
			}
		}

		minWait();
		waitForFrameAndSwitch(screenFrame);

		String ubrNumber = driver.findElement(txt_UBRNo).getAttribute("value");
		if (ubrNumber == null) {
			extentTest.log(LogStatus.FAIL, "Booking has not been done with AWB: " + awbNo);
			Assert.fail("Booking has not been done");
		}
		PropertyHandler.setPropValue(dataFilePath, UBRNo, ubrNumber);
		String bookStatus = driver.findElement(info_bookingStatus).getAttribute("value");
		if (verifyBooking == true) {
			BookingStatus = PropertyHandler.getPropValue(dataFilePath, BookingStatus);
			if (bookStatus.equalsIgnoreCase(BookingStatus)) {
				extentTest.log(LogStatus.PASS, "Booking Status matches");
			} else {
				extentTest.log(LogStatus.FAIL, "Booking Status doesn't match");
				Assert.fail("Booking Status doesn't match");
			}
		}
		extentTest.log(LogStatus.INFO, "Booking Status: " + bookStatus + " and UBR No.: " + UBRNo);
		PropertyHandler.setPropValue(dataFilePath, "bookingStatus", bookStatus);

		return this;
	}

	/**
	 * @author A-8452 Faizan
	 * @return
	 */
	public CAP018 verifyAllotment() {
		String Allot = getAttributebyValue(By.xpath("(//input[@name='allotmentId'])[1]"));
		PropertyHandler.setPropValue(dataFilePath, "AllotmentID", Allot);
		if (!((Allot = driver.findElement(By.xpath("(//*[@name='allotmentId'])[1]")).getAttribute("value"))
				.equals("FS"))) {
			extentTest.log(LogStatus.PASS,
					"Booking has been done with in an allotment with Allotment id: " + Allot);
			logger.info("Booking has been done with in an allotment with Allotment id: " + Allot);
		} else {
			extentTest.log(LogStatus.FAIL, "Booking has been done in FS");
			logger.warn("Booking has been done in FS");
		}
		return this;
	}

	public CAP018 VerifyRateDetailsinCAP018() {
		minWait();
		click(btn_ratingTab);
		minWait();

		String NetCharge = driver
				.findElement(By
						.xpath("//*[@id='fixed_header_table_0']//tbody/tr/td[@class='iCargoTableDataTd']//span[@id='netCharge_LBL']"))
				.getText();
		PropertyHandler.setPropValue(dataFilePath, "NetCharge", NetCharge);

		String RateLineID = driver
				.findElement(By.xpath("(//*[@id='fixed_header_table_0']//tbody/tr/td[@class='iCargoTableDataTd'])[8]"))
				.getText();
		PropertyHandler.setPropValue(dataFilePath, "RateLineID", RateLineID);

		String otherChargesDC = driver.findElement(By.xpath("//*[@id='otherChargesDc_LBL']")).getText();
		PropertyHandler.setPropValue(dataFilePath, "otherChargesDC", otherChargesDC);

		String otherChargesDA[] = driver
				.findElement(By.xpath("(//*[@class='ic-input ic-split-50 ic-label ic-center'])[2]")).getText()
				.split("\n");
		PropertyHandler.setPropValue(dataFilePath, "otherChargesDA", otherChargesDA[1]);

		Double netCharge = Double.parseDouble(NetCharge);
		Double OCDC = Double.parseDouble(otherChargesDC);
		Double OCDA = Double.parseDouble(otherChargesDA[1]);
		Double Total = netCharge + OCDA + OCDC;
		String totalcalc = Total.toString();
		if (totalcalc.equals(driver.findElement(By.xpath("//*[@id='grandTotal_LBL']")).getText())) {
			extentTest.log(LogStatus.PASS, "Total amount calculated is correct. Amount is: " + totalcalc);

		} else {
			extentTest.log(LogStatus.FAIL,
					"Total amount calculated is incorrect. Amount should be: " + totalcalc);
		}

		return this;
	}

	// Sharath
	public CAP018 simpleBookingWithoutULD(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String ubr,
			String bookingType, String CarrierCode, String commCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		minWait();
		if (Product.equals("COMAT")) {
			selectByText(By.xpath("//select[@name='serviceCargoClass']"), "COMAT");
		}
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		try {
			if (!PropertyHandler.getPropValue(dataFilePath, "valueAdd").equals(null)
					&& PropertyHandler.getPropValue(dataFilePath, "valueAdd").equals("Yes")) {
				click(By.xpath("//button[text()='Value-Added Service']"));
				switchToSecondWindow();
				click(By.xpath("//tbody//td/input"));
				click(By.name("btOk"));
				maxWait();
				switchToFirstWindow();
				waitForFrameAndSwitch(screenFrame);
				maxWait();
				extentTest.log(LogStatus.INFO, "Updated booking with Value Added Service");
				captureAndAddScreenshot();
				logger.info("Updated booking with Value Added Service");
			}
		} catch (Exception e) {
			logger.info("No value add parameter");
		}
		click(btn_save);
		minWait();
		try {
			if (PropertyHandler.getPropValue(dataFilePath, "checkSheet").equals("Yes")) {
				handleAlert("Accept", "CAP018");
				fillCheckSheet(commCode);
				driver.switchTo().defaultContent();
				waitForFrameAndSwitch(screenFrame);
				click(btn_save);
			}
		} catch (Exception e) {
			logger.info("No checksheet configured");
		}
		/*
		 * try{ handleAlert("Accept", "CAP018");
		 * driver.switchTo().frame(screenFrame); } catch(Exception e){
		 * driver.switchTo().defaultContent();
		 * waitForFrameAndSwitch(screenFrame); }
		 */
		extentTest.log(LogStatus.INFO, "Saved all the booking details");
		maxWait();
		continueEmbargo();
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);
		if (!ubrNo.equals(null) || !awb.equals(null)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with AWB No: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
		} else {
			extentTest.log(LogStatus.FAIL,
					"Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			Assert.fail("Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
		}
		try {
			String validateScc = PropertyHandler.getPropValue(dataFilePath, "validateSCC");

			if (validateScc != null && PropertyHandler.getPropValue(dataFilePath, "validateSCC").equals("Yes")) {
				String SCC = getAttributebyValue(By.name("sccCode"));
				if (SCC.contains("BSX")) {
					extentTest.log(LogStatus.PASS, "BSX stamping has been done");
				} else {
					extentTest.log(LogStatus.FAIL, "BSX stamping hasn't been done");
				}
			}
		} catch (Exception e) {
		}
		return this;
	}

	// Sharath
	public CAP018 simpleBookingWithoutULDFirstLeg(String prefix, String awbno, String origin, String intermediate,
			String dest, String agentCode, String Product, String pcs, String wt, String vol, String FlightNo,
			String fltDt, String ubr, String bookingType, String CarrierCode, String commCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		if (commCode.contains("REMAINS")) {
			continueEmbargo();
		}
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered first leg details");
		return this;
	}

	// Sharath
	public CAP018 simpleBookingWithoutULDFirstLegOA(String prefix, String awbno, String origin, String intermediate,
			String dest, String agentCode, String Product, String pcs, String wt, String vol, String FlightNo,
			String fltDt1, String ubr, String carrierWithFlightno1, String commCode) {

		/*
		 * CarrierCode = PropertyHandler.getPropValue(dataFilePath,
		 * CarrierCode); prefix = PropertyHandler.getPropValue(dataFilePath,
		 * prefix); FlightNo = PropertyHandler.getPropValue(dataFilePath,
		 * FlightNo); origin = PropertyHandler.getPropValue(dataFilePath,
		 * origin); dest = PropertyHandler.getPropValue(dataFilePath, dest);
		 * agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		 * Product = PropertyHandler.getPropValue(dataFilePath, Product); pcs =
		 * PropertyHandler.getPropValue(dataFilePath, pcs); wt =
		 * PropertyHandler.getPropValue(dataFilePath, wt); vol =
		 * PropertyHandler.getPropValue(dataFilePath, vol); fltDt =
		 * PropertyHandler.getPropValue(dataFilePath, fltDt); commCode=
		 * PropertyHandler.getPropValue(dataFilePath, commCode); String
		 * carrierWithFlightno = CarrierCode + FlightNo;
		 * PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno",
		 * carrierWithFlightno);
		 */

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt1);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno1);
		enterKeys(list_txt_fltDt, fltDt1);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered leg1 flight as: " + carrierWithFlightno1);
		return this;
	}

	// Sharath
	public CAP018 simpleHVBookingWithoutULDCC(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String ubr,
			String bookingType, String CarrierCode, String commCode, String insurance, String Dvamount, String customs,
			boolean allotmenttest) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		insurance = PropertyHandler.getPropValue(dataFilePath, insurance);
		Dvamount = PropertyHandler.getPropValue(dataFilePath, Dvamount);
		customs = PropertyHandler.getPropValue(dataFilePath, customs);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		// selectByText(By.xpath("//select[@name='capacityType']"),
		// "Allotment");
		click(icon_CCShipment);
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * // scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		click(btn_ratingTab);
		minWait();
		enterKeys(txt_Insurance, insurance);
		enterKeys(txt_customs, customs);
		enterKeys(txt_DVcarraiage, Dvamount);
		extentTest.log(LogStatus.INFO, "Successfully Entered DV Amount:" + Dvamount + "\n\t" + "Insurance: "
				+ insurance + "\n\t" + "Customs: " + customs);
		logger.info("Successfully Entered DV Amount:" + Dvamount + "\n\t" + "Insurance: " + insurance + "\n\t"
				+ "Customs: " + customs);
		click(btn_save);
		/*
		 * try{ handleAlert("Accept", "CAP018");
		 * waitForFrameAndSwitch(screenFrame); } catch(Exception e){
		 * waitForFrameAndSwitch(screenFrame); }
		 * extentTest.log(LogStatus.PASS, "Checksheet for HVL Shipment");
		 * maxWait(); waitForFrameAndSwitch("popupContainerFrame");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[0].templateAnswer']"), "Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[1].templateAnswer']"), "Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[8].templateAnswer']"), "No");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[10].templateAnswer']"), "No");
		 * click(By.xpath("//button[@name='btnSave']")); maxWait(); maxWait();
		 * try{ continueEmbargo(); }catch(Exception e){
		 * extentTest.log(LogStatus.INFO,
		 * "No embargo/restriction window after checksheet saving");
		 * logger.info("No embargo/restriction window after checksheet saving");
		 * } driver.switchTo().defaultContent();
		 * waitForFrameAndSwitch(screenFrame);
		 * extentTest.log(LogStatus.PASS,
		 * "Successfully entered the details for the Checksheet");
		 * click(btn_save);
		 */ extentTest.log(LogStatus.INFO, "Saved all the booking details");
		maxWait();
		continueEmbargo();
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);
		if (allotmenttest == true) {
			String AllotId = getAttributebyValue(By.xpath("(//input[@name='allotmentId'])[1]"));
			PropertyHandler.setPropValue(dataFilePath, "AllotmentID", AllotId);
			String CapacityType = driver.findElement(By.xpath("//select[@name='capacityType']/option[@selected]"))
					.getText();
			if (CapacityType.equals("Allotment")) {
				extentTest.log(LogStatus.PASS, "Capacity type is : " + CapacityType);
				extentTest.log(LogStatus.PASS, "Successfully booked with Allotment ID : " + AllotId);
			} else {
				extentTest.log(LogStatus.FAIL,
						"Allotment hasn't been applied for this booking, Capacity Type here is : " + CapacityType);
			}
		}
		if (!ubrNo.equals(null) || !awb.equals(null)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with AWB No: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
		} else {
			extentTest.log(LogStatus.FAIL,
					"Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			Assert.fail("Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
		}
		return this;
	}

	// Sharath
	public CAP018 simpleBookingWithoutULDAVI(String prefix, String awbno, String origin, String dest, String agentCode,
			String Product, String pcs, String wt, String vol, String FlightNo, String fltDt, String ubr,
			String bookingType, String CarrierCode, String commCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		enterKeys(txt_productname, Product);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		minWait();
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		click(btn_save);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)) {
			click(btn_genericYes);
		}
		maxWait();
		try {
			fillCheckSheet(commCode);
			driver.switchTo().defaultContent();
			waitForFrameAndSwitch(screenFrame);
			extentTest.log(LogStatus.INFO, "Checksheet has been updated");
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "No checksheet configured");
			logger.info("No checksheet configured");
		}
		/*
		 * try{ waitForFrameAndSwitch(screenFrame);
		 * waitForFrameAndSwitch("popupContainerFrame");
		 * click(SelectBtnInAVICS); click(checkSheetAVIDog);
		 * enterKeys(By.id("CMP_Checksheet_Defaults_CaptureCheckSheet_Remarks3")
		 * ,"GreatDane"); click(By.id(
		 * "CMP_Checksheet_Defaults_CaptureCheckSheet_MultiSelect6_ms"));
		 * click(By.xpath("//input[@value='8 weeks - 6 months']"));
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[9].templateAnswer']"), "Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[16].templateAnswer']"), "Yes");
		 * click(By.name("btnSave")); driver.switchTo().defaultContent();
		 * waitForFrameAndSwitch(screenFrame);
		 * extentTest.log(LogStatus.INFO, "Checksheet has been updated");
		 * }catch(Exception e){ extentTest.log(LogStatus.INFO,
		 * "No checksheet configured"); }
		 */
		click(btn_save);
		extentTest.log(LogStatus.INFO, "Successfully entered values in Check Sheet for commodity: " + commCode);
		try {
			handleAlert("Accept", "CAP018");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			waitForFrameAndSwitch(screenFrame);
		}
		extentTest.log(LogStatus.INFO, "Saved all the booking details");
		// maxWait();
		// maxWait();
		maxWait();
		switchToSecondWindow();
		minWait();
		if (verifyElementPresent(By.xpath("//button[text()='Continue']"))) {
			click(By.xpath("//button[text()='Continue']"));
		}
		maxWait();
		maxWait();
		// switchToFirstWindow();
		// driver.switchTo().defaultContent();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);
		if (!ubrNo.equals(null) || !awb.equals(null)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with AWB No: " + awb + " and UBR No: " + ubrNo);
		} else {
			extentTest.log(LogStatus.FAIL,
					"Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			Assert.fail("Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
		}
		return this;
	}

	// Sharath
	public CAP018 simpleBookingWithoutULDAVImultileg(String prefix, String awbno, String origin, String dest,
			String intermediate, String agentCode, String Product, String pcs, String wt, String vol, String FlightNo1,
			String FlightNo2, String fltDt1, String fltDt2, String ubr, String bookingType, String CarrierCode,
			String commCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		FlightNo2 = PropertyHandler.getPropValue(dataFilePath, FlightNo2);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		// vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt1 = PropertyHandler.getPropValue(dataFilePath, fltDt1);
		fltDt2 = PropertyHandler.getPropValue(dataFilePath, fltDt2);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno1", carrierWithFlightno1);

		String carrierWithFlightno2 = CarrierCode + FlightNo2;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno2", carrierWithFlightno2);
		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt1);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		enterKeys(txt_productname, Product);
		minWait();
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno1);
		enterKeys(list_txt_fltDt, fltDt1);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		enterKeys(getWebElements(list_txt_fltOrigin).get(1), intermediate);
		enterKeys(getWebElements(list_txt_fltDest).get(1), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(1), carrierWithFlightno2);
		enterKeys(getWebElements(list_txt_fltDt).get(1), fltDt2);
		enterKeys(getWebElements(list_txt_fltPcs).get(1), pcs);
		enterKeys(getWebElements(list_txt_fltWt).get(1), wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(getWebElements(list_dropDown_fltForce).get(1));
		 * selectByText(getWebElements(list_dropDown_fltForce).get(1),
		 * "Confirm");
		 */
		click(btn_save);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)) {
			click(btn_genericYes);
		}
		/*
		 * maxWait(); waitForFrameAndSwitch(screenFrame);
		 * waitForFrameAndSwitch("popupContainerFrame");
		 */
		fillCheckSheet(commCode);
		/*
		 * click(SelectBtnInAVICS); click(checkSheetAVIDog); enterKeys(By.xpath(
		 * "//textarea[@name='questionwithAnswer[1].templateAnswer']")
		 * ,"non-mandate field for cat and dog"); enterKeys(By.xpath(
		 * "//textarea[@name='questionwithAnswer[3].templateAnswer']"),
		 * "GreatDane"); click(By.id(
		 * "CMP_Checksheet_Defaults_CaptureCheckSheet_MultiSelect6_ms"));
		 * click(By.xpath("//input[@value='8 weeks - 6 months']"));
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[9].templateAnswer']"), "Yes");
		 * selectByText(By.xpath(
		 * "//select[@name='questionwithAnswer[16].templateAnswer']"), "Yes");
		 */
		// click(By.name("btnSave"));
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		click(btn_save);
		extentTest.log(LogStatus.INFO, "Successfully entered values in Check Sheet for commodity: " + commCode);
		try {
			handleAlert("Accept", "CAP018");
			waitForFrameAndSwitch(screenFrame);
		} catch (Exception e) {
			waitForFrameAndSwitch(screenFrame);
		}
		extentTest.log(LogStatus.INFO, "Saved all the booking details");
		maxWait();
		maxWait();
		continueEmbargo();
		/*
		 * try{
		 * 
		 * }catch(Exception e){ }
		 */
		/*
		 * maxWait(); switchToSecondWindow(); minWait();
		 * if(verifyElementPresent(By.xpath("//button[text()='Continue']"))){
		 * click(By.xpath("//button[text()='Continue']")); } maxWait();
		 * maxWait(); // switchToFirstWindow(); //
		 * driver.switchTo().defaultContent();
		 */switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, ubr, ubrNo);
		String awb = waitForElement(txt_awbNo).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, awbno, awb);
		if (!ubrNo.equals(null) || !awb.equals(null)) {
			extentTest.log(LogStatus.PASS, "Successfully booked with AWB No: " + awb + " and UBR No: " + ubrNo);
			captureAndAddScreenshot();
		} else {
			extentTest.log(LogStatus.FAIL,
					"Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
			Assert.fail("Booking wasn't successfull with awb : " + awb + " and UBR No: " + ubrNo);
		}
		return this;
	}

	// Sharath
	public CAP018 verifyReassignStatus(String prefix, String awbno, String flt2, String flt) {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		flt2 = PropertyHandler.getPropValue(dataFilePath, flt2);
		flt = PropertyHandler.getPropValue(dataFilePath, flt);
		enterKeys(txt_awbNoPrefix, prefix);
		enterKeys(txt_awbNo, awbno);
		click(btn_List);
		maxWait();
		maxWait();
		String actualvalue = getAttributebyValue(By.xpath("(//input[@name='flightNumber'])[1]"));
		if (actualvalue.equals(flt2)) {
			extentTest.log(LogStatus.PASS,
					"Successfully reassigned the awb no : " + awbno + "to flight : " + flt2 + " from filght : " + flt);
		} else {
			extentTest.log(LogStatus.FAIL, "Reassigning the flight failed");
			Assert.fail("Reassigning the flight failed");
		}
		return this;
	}

	// Sharath
	public CAP018 HVBookingwithDVandInsuranceWithoutULD(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String CarrierCode, String forceStatus, String commodityCode, String bookingStatus, String Insurance,
			String DVAmt, String customs, String ULDType, String noOfULD, String ULDpcs, String ULDwt, String LoosePcs,
			String LooseWt) {
		// get Values from properties file
		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		/*
		 * ULDpcs = PropertyHandler.getPropValue(dataFilePath, ULDpcs); ULDwt =
		 * PropertyHandler.getPropValue(dataFilePath, ULDwt);
		 */
		LoosePcs = PropertyHandler.getPropValue(dataFilePath, LoosePcs);
		LooseWt = PropertyHandler.getPropValue(dataFilePath, LooseWt);

		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);// Confirm
																				// or
																				// Queue
																				// (case-sensitive)
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);
		Insurance = PropertyHandler.getPropValue(dataFilePath, Insurance);
		DVAmt = PropertyHandler.getPropValue(dataFilePath, DVAmt);
		customs = PropertyHandler.getPropValue(dataFilePath, customs);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		boolean chksht = false;
		if (commodityCode.equals("VALCARGO")) {
			chksht = true;
		}
		// enter values
		click(btn_List);
		minWait();
		extentTest.log(LogStatus.INFO, "Clicked List Button in CAP018 screen ");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_productname, Product);
		/*
		 * driver.findElement(By.name("commodityCode")).click();
		 * driver.findElement(By.name("commodityCode")).sendKeys(Keys.CLEAR);
		 * driver.findElement(By.name("commodityCode")).sendKeys(commodityCode+
		 * Keys.TAB);
		 */
		enterKeys(txt_commCode, commodityCode + Keys.TAB);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		minWait();
		// enterKeys(txt_shipmentDescrptn, "GEN");
		setDimensions(pcs, wt);
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered the shipment details ");
		// switch to rating tab and fill values
		click(btn_ratingTab);
		minWait();
		enterKeys(txt_Insurance, Insurance);
		enterKeys(txt_DVcarraiage, DVAmt);
		enterKeys(txt_customs, customs);
		extentTest.log(LogStatus.INFO, "Successfully Entered DV Amount:" + DVAmt + "\n\t" + "Insurance: "
				+ Insurance + "\n\t" + "Customs: " + customs);
		// Save and Capture UBR
		if (chksht = true) {
			fillCheckSheetafterSaveClick("commodityCode");
			driver.switchTo().defaultContent();
			waitForFrameAndSwitch(screenFrame);
		}
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author Sharath
	 */
	public CAP018 simpleBookingwithCommCodeAndULD(String prefix, String awbno, String origin, String dest,
			String intermediate, String agentCode, String Product, String pcs, String wt, String vol, String FlightNo,
			String fltDt, String ubr, String commCode, String ULDType, String noOfULD, String CarrierCode) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		noOfULD = PropertyHandler.getPropValue(dataFilePath, noOfULD);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
//		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
//		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		// enterKeys(txt_commVol, vol);
		// setDimensions(pcs, wt);
		enterKeys(txt_uldType, ULDType);
		enterKeys(txt_noOfULD, noOfULD);
		int uldwt = Integer.parseInt(wt);
		int pcsint = Integer.parseInt(pcs);
		int wtToEnter = uldwt / pcsint;
		enterKeys(By.name("uldWeight"), Integer.toString(wtToEnter));
		enterKeys(txt_uldCommodityCode, commCode);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, intermediate);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		return this;
	}

	// Sharath
	public String getuldWt(String uldWt, String uldpcs) {

		int uWt = Integer.parseInt(uldWt);
		int uPcs = Integer.parseInt(uldpcs);
		int newUWt = uWt / uPcs;
		String nUWt = Integer.toString(newUWt);
		return nUWt;
	}

	// Sharath
	public CAP018 continueEmbargo() {
		try {
			if (verifyNumberOfWindows(2)) {
				switchToSecondWindow();
				maxWait();
				if(verifyElementPresent(By.xpath("//input[1][@disabled]"))){
					extentTest.log(LogStatus.FAIL, "Failed due to embargo");
					Assert.fail();
				}
				// captureAndAddScreenshot();
				// click(By.name("btContinue"));
				// driver.findElement(By.name("btContinue")).click();
				else if (verifyElementPresent(By.name("btContinue"))) {
					click(By.name("btContinue"));
				} else {
					throw new Exception();
				}
				maxWait();
				/*
				 * switchToFirstWindow(); waitForFrameAndSwitch(screenFrame); //
				 * driver.switchTo().frame(screenFrame);
				 */ extentTest.log(LogStatus.INFO, "Restriction has been handled");
				logger.info("Restriction has been handled");
				switchToFirstWindow();
			}
		} catch (ElementNotVisibleException e) {
			switchToFirstWindow();
			System.err.println("An interupted Exception occured" + e);
			extentTest.log(LogStatus.INFO, "Element not found" + e);
		} catch (Exception e) {
//			switchToFirstWindow();
			extentTest.log(LogStatus.INFO, "No Restriction applied for the commodity");
			logger.info("No Restriction applied for the commodity");
		}
		return this;
	}

	// Sharath
	public CAP018 verifySCCstamping(String prefix, String awbno, String expectedSCC, boolean listingreqd) {
		if (listingreqd == true) {
//			prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//			awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//			expectedSCC = PropertyHandler.getPropValue(dataFilePath, expectedSCC);
			list(prefix, awbno);

			maxWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(info_msg)) {
				click(btn_genericYes);
			}
			maxWait();
			waitForFrameAndSwitch(screenFrame);
			maxWait();
		}

		String SCC = getAttributebyValue(By.name("sccCode"));
		if (SCC.contains(expectedSCC)) {
			extentTest.log(LogStatus.PASS, SCC + " stamping is successful");
			captureAndAddScreenshot();
		} else {
			extentTest.log(LogStatus.FAIL, SCC + " stamping failed");
			captureAndAddScreenshot();
		}
		return this;
	}

	// Sharath
	public CAP018 captureTCDetailsPcs(String wt, String origin, String fltDt, String pcs) {
//		wt = PropertyHandler.getPropValue(dataFilePath, wt);
//		origin = PropertyHandler.getPropValue(dataFilePath, origin);
//		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
//		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		click(By.name("btCaptureTC"));
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		/*
		 * scrollToView(By.xpath("//*[text()='Orders & ULD Details']/.."));
		 * click(By.xpath("//*[text()='Orders & ULD Details']/.."));
		 * click(By.name("btnActualUld")); maxWait();
		 * enterKeys(By.name("actualUldNumber"), uldNo);
		 * click(By.name("button_actual_ok")); maxWait();
		 */
		click(By.xpath("//h4[text()='Additional Details']/../preceding::span[1]"));
		selectByText(By.xpath("//select[@name='temRange']"), "15 to 25");
		int w = Integer.parseInt(wt);
		enterKeys(By.name("dryIceWeight"), Integer.toString(w / 10));
		enterKeys(By.name("emergencyContactName"), "testuser");
		enterKeys(By.name("emergencyContactNumber"), "12345");
		enterKeys(By.name("emergencyContactEmail"), "abc@gmail.com");
		enterKeys(By.name("airportCode"), origin);
		enterKeys(By.name("reIceWeight"), wt);
		enterKeys(By.name("plannedDate"), fltDt);
		enterKeys(By.name("plannedTime"), "10" + Keys.TAB);
		maxWait();
		click(By.name("btnReIceAdd"));
		/*scrollToView(By.xpath("//*[text()='Monitoring Details']/.."));
		click(By.xpath("//*[text()='Monitoring Details']/.."));
		enterKeys(By.xpath("//input[@name='airport']"), origin);
		// selectByText(By.xpath("//select[@name='uldNumber']"),uldNo);
		click(By.xpath("//input[@value='pcs']"));
		minWait();
		enterKeys(By.xpath("//div[contains(@id,'monitoringFilter')]//input[@name='pieces']"), pcs);
		selectByText(By.xpath("//select[@name='chkType']"), "Is Set Temperature Correct");
		maxWait();
		selectByText(By.xpath("//select[@name='value']"), "Yes");
		enterKeys(By.name("remarks"), "test");
		click(By.name("BtnMonitoringAdd"));
		minWait();*/
		minWait();
		click(By.name("btnOK"));
		
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		return this;
	}

	// Sharath
	public CAP018 captureTCDetailsULD(String wt, String origin, String fltDt, String uldNo) {
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		click(By.name("btCaptureTC"));
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		// maxWait();
		driver.findElement(By.xpath("//h4[text()='Additional Details']/../preceding::span[1]")).click();
		// click(By.xpath("//h4[text()='Additional
		// Details']/../preceding::span[1]"));
		selectByText(By.xpath("//select[@name='temRange']"), "15 to 25");
		int w = Integer.parseInt(wt);
		enterKeys(By.name("dryIceWeight"), Integer.toString(w / 10));
		enterKeys(By.name("emergencyContactName"), "testuser");
		enterKeys(By.name("emergencyContactNumber"), "12345");
		enterKeys(By.name("emergencyContactEmail"), "abc@gmail.com");
		enterKeys(By.name("airportCode"), origin);
		enterKeys(By.name("reIceWeight"), wt);
		enterKeys(By.name("plannedDate"), fltDt);
		enterKeys(By.name("plannedTime"), "10" + Keys.TAB);
		maxWait();
		click(By.name("btnReIceAdd"));
		minWait();

		scrollToView(By.xpath("//*[text()='Orders & ULD Details']/.."));
		click(By.xpath("//*[text()='Orders & ULD Details']/.."));
		click(By.name("btnActualUld"));
		maxWait();
		enterKeys(By.name("actualUldNumber"), uldNo);
		click(By.name("button_actual_ok"));
		maxWait();
		scrollToView(By.xpath("//*[text()='Monitoring Details']/.."));
		click(By.xpath("//*[text()='Monitoring Details']/.."));
		enterKeys(By.xpath("//input[@name='airport']"), origin);
		selectByText(By.xpath("//select[@name='uldNumber']"), uldNo);
		// click(By.xpath("//input[@value='pcs']"));
		minWait();
		// enterKeys(By.xpath("//div[contains(@id,'monitoringFilter')]//input[@name='pieces']"),pcs);
		selectByText(By.xpath("//select[@name='chkType']"), "Is Set Temperature Correct");
		maxWait();
		selectByText(By.xpath("//select[@name='value']"), "Yes");
		enterKeys(By.name("remarks"), "test");
		click(By.name("BtnMonitoringAdd"));
		minWait();
		click(By.name("btnOK"));
		waitForFrameAndSwitch(screenFrame);
		fillCheckSheetafterSaveClick("commCode");
		minWait();
		return this;
	}

	// Sharath
	public CAP018 verifyAllotment(String prefix, String awbno) {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		enterKeys(txt_awbNoPrefix, prefix);
		enterKeys(txt_awbNo, awbno);
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Verifying Allotment");
		maxWait();
		maxWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)) {
			click(btn_genericYes);
		}
		maxWait();
		waitForFrameAndSwitch(screenFrame);
		/*
		 * Select sel = new
		 * Select(driver.findElement(By.xpath("//select[@name='capacityType']"))
		 * ); WebElement ele = sel.getFirstSelectedOption();
		 * 
		 * //
		 */ String allt = getSelectedValue(By.xpath("//select[@name='capacityType']"));
		if (allt.equalsIgnoreCase("Allotment")) {
			extentTest.log(LogStatus.PASS, "Allotment has been applied");
		} else {
			extentTest.log(LogStatus.FAIL, "Allotment hasn't been applied, applied capacity type is : " + allt);
			Assert.fail("Allotment hasn't been applied, applied capacity type is : " + allt);
		}
		String altid = getAttributebyValue(By.xpath("(//input[@name='allotmentId'])[1]"));
		captureAndAddScreenshot();
		extentTest.log(LogStatus.INFO, "Allotment applied here is : " + altid);
		return this;
	}

	// Sharath
	public CAP018 splitBookingWeightDivision(String pcs, String wt) {
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		int pcs1 = Integer.parseInt(pcs);
		pcs1 = (pcs1) / 2;
		int wt1 = Integer.parseInt(wt);
		wt1 = (wt1) / 2;
		PropertyHandler.setPropValue(dataFilePath, "pcs1", Integer.toString(pcs1));
		PropertyHandler.setPropValue(dataFilePath, "wt1", Integer.toString(wt1));
		return this;
	}

	// Sharath
	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Sharath A-8680
	 * @date 25-02-2019
	 */
	public CAP018 enterInitialBookingDetails(String origin, String dest, String agentCode, String Product, String pcs,
			String wt, String vol, String fltDt, String ULDType, String ULDwt, String commCode) {

		click(btn_List);
		// extentTest.log(LogStatus.INFO, "Successfully entered Prefix
		// and AWB no in CAP018 screen: "+awbno);
		minWait();
		driver.switchTo().defaultContent();
		/*if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}*/
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(By.name("shipmentDate"), fltDt + Keys.TAB);
		minWait();
	
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		
		// driver.findElement(By.name("shipmentDate")).sendKeys(".");
		// driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		if (ULDType == null || ULDType.equals("")) {
			setDimensions(pcs, wt);
		} else {
			enterKeys(txt_commVol, vol);
			enterKeys(txt_uldType, ULDType);
			enterKeys(txt_noOfULD, "1");
			enterKeys(txt_uldWt, wt);
			enterKeys(txt_uldCommodityCode, commCode);
		}
		/*
		 * enterKeys(list_txt_fltOrigin, origin); enterKeys(list_txt_fltDest,
		 * intermediate); enterKeys(list_txt_fltNo, carrierWithFlightno);
		 * enterKeys(list_txt_fltDt, fltDt+Keys.TAB); enterKeys(list_txt_fltPcs,
		 * pcs); enterKeys(list_txt_fltWt, wt); enterKeys(list_txt_fltVol,vol);
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered booking details");
		return this;
	}
	
	public CAP018 enterInitialBookingDetailsDGData(String origin, String dest, String agentCode, String Product, String pcs,
			String wt, String vol, String fltDt, String ULDType, String ULDwt, String commCode, String wtUnit) {

		click(btn_List);
		// extentTest.log(LogStatus.INFO, "Successfully entered Prefix
		// and AWB no in CAP018 screen: "+awbno);
		minWait();
		driver.switchTo().defaultContent();
		/*if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}*/
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(By.name("shipmentDate"), fltDt + Keys.TAB);
		minWait();
		if(wtUnit.equalsIgnoreCase("lbs")){
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		}else{
			selectByText(sel_WeightUnit, "Kilogram"); // case-sensitive
			selectByText(sel_VolumeUnit, "Cubic Meters");// case-sensitive
		}
		// driver.findElement(By.name("shipmentDate")).sendKeys(".");
		// driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		if (ULDType == null || ULDType.equals("")) {
			setDimensions(pcs, wt);
		} else {
			enterKeys(txt_commVol, vol);
			enterKeys(txt_uldType, ULDType);
			enterKeys(txt_noOfULD, "1");
			enterKeys(txt_uldWt, wt);
			enterKeys(txt_uldCommodityCode, commCode);
		}
		/*
		 * enterKeys(list_txt_fltOrigin, origin); enterKeys(list_txt_fltDest,
		 * intermediate); enterKeys(list_txt_fltNo, carrierWithFlightno);
		 * enterKeys(list_txt_fltDt, fltDt+Keys.TAB); enterKeys(list_txt_fltPcs,
		 * pcs); enterKeys(list_txt_fltWt, wt); enterKeys(list_txt_fltVol,vol);
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, forceStatus);
		 */
		extentTest.log(LogStatus.INFO, "Successfully entered booking details");
		return this;
	}

	/*
	 * Sharath A-8680 Date: 08-08-2019 MultiLeg(3 legs) Split booking
	 */

	public CAP018 MultilegSplitBookingfor3Legs(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String scc, String pcs, String wt, String vol, String FlightNo1,
			String fltDt1, String intermediate1, String intermediate2, String ubr, String commCode, String CarrierCode,
			String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt, String Flightno2,
			String Flightno3, String flt3Dt, String ULDType, String ULDwt, String pcs1, String wt1) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		// scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		fltDt1 = PropertyHandler.getPropValue(dataFilePath, fltDt1);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
		intermediate2 = PropertyHandler.getPropValue(dataFilePath, intermediate2);
		Flightno2 = PropertyHandler.getPropValue(dataFilePath, Flightno2);
		Flightno3 = PropertyHandler.getPropValue(dataFilePath, Flightno3);
		flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
		pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
		wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + Flightno2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);
		String carrierWithFlightno3 = CarrierCode + Flightno3;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno3", carrierWithFlightno3);

		enterInitialBookingDetails(origin, dest, agentCode, Product, pcs, wt, vol, fltDt1, ULDType, ULDwt, commCode);
		enterFlightforMultilegFlt1(origin, intermediate1, pcs1, wt1, vol, fltDt1, carrierWithFlightno1, forceStatus, 1);
		enterFlightforMultilegFlt1(origin, intermediate2, pcs1, wt1, vol, flt2Dt, carrierWithFlightno2, forceStatus, 2);
		enterFlightforMultilegFlt1(intermediate1, dest, pcs, wt, vol, flt3Dt, carrierWithFlightno3, forceStatus, 3);
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	/*
	 * Sharath A-8680 Date: 08-08-2019 MultiLeg(3 legs) Simple booking_2
	 */

	public CAP018 MultilegSplitBookingfor3Legs_2(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String scc, String pcs, String wt, String vol, String FlightNo1,
			String fltDt1, String intermediate1, String intermediate2, String ubr, String commCode, String CarrierCode,
			String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt, String Flightno2,
			String Flightno3, String flt3Dt, String ULDType, String ULDwt, String pcs1, String wt1, String pcs2,
			String wt2) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo1 = PropertyHandler.getPropValue(dataFilePath, FlightNo1);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		// scc = PropertyHandler.getPropValue(dataFilePath, scc);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		fltDt1 = PropertyHandler.getPropValue(dataFilePath, fltDt1);
		UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
		// bookingStatus);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
		intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
		intermediate2 = PropertyHandler.getPropValue(dataFilePath, intermediate2);
		Flightno2 = PropertyHandler.getPropValue(dataFilePath, Flightno2);
		Flightno3 = PropertyHandler.getPropValue(dataFilePath, Flightno3);
		flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
		ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
		ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
		pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
		wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);
		pcs2 = PropertyHandler.getPropValue(dataFilePath, pcs2);
		wt2 = PropertyHandler.getPropValue(dataFilePath, wt2);

		String carrierWithFlightno1 = CarrierCode + FlightNo1;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
		String carrierWithFlightno2 = CarrierCode + Flightno2;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);
		String carrierWithFlightno3 = CarrierCode + Flightno3;
		PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno3", carrierWithFlightno3);

		enterInitialBookingDetails(origin, dest, agentCode, Product, pcs, wt, vol, fltDt1, ULDType, ULDwt, commCode);
		enterFlightforMultilegFlt1(origin, dest, pcs1, wt1, vol, fltDt1, carrierWithFlightno1, forceStatus, 1);
		enterFlightforMultilegFlt1(origin, intermediate1, pcs2, wt2, vol, flt2Dt, carrierWithFlightno2, forceStatus, 2);
		enterFlightforMultilegFlt1(intermediate1, dest, pcs2, wt2, vol, flt3Dt, carrierWithFlightno3, forceStatus, 3);
		saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

		return this;
	}

	/**
	 * 
	 * @param prefix
	 * @param awbno
	 * @author Sharath
	 * @return
	 */
	public CAP018 spotRateUpdate(String prefix, String awbno) {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		enterKeys(txt_awbNoPrefix, prefix);
		enterKeys(txt_awbNo, awbno);
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		click(btn_ratingTab);
		scrollToView(By.name("spotRatePerKg"));
		enterKeys(By.name("spotRatePerKg"), "10" + Keys.TAB);
		captureAndAddScreenshot();
		click(btn_save);
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		click(btn_ratingTab);
		scrollToView(By.name("spotRatePerKg"));
		captureAndAddScreenshot();
		String val = getAttributebyValue(By.name("spotRateId"));
		if (val != null) {
			extentTest.log(LogStatus.PASS, "Spot rate " + val + " has been attached");
		} else {
			extentTest.log(LogStatus.FAIL, "Spot Rate attachment failed");
		}

		return this;
	}

	/**
	 * 
	 * @param prefix
	 * @param awbno
	 * @author Sharath
	 * @return
	 */
	public CAP018 detachSpotRate(String prefix, String awbno) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		enterKeys(txt_awbNoPrefix, prefix);
		enterKeys(txt_awbNo, awbno);
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		click(btn_ratingTab);
		scrollToView(By.name("spotRatePerKg"));
		click(By.name("btAttachDettachSpotRate"));
		maxWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(By.id("ic-sd-msgco"))) {
			click(btn_genericYes);
		}
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		switchToSecondWindow();
		driver.close();
		switchToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		maxWait();
		click(btn_save);
		maxWait();
		switchToSecondWindow();
		click(btn_popUp_Ok);
		switchBackToFirstWindow();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		click(btn_ratingTab);
		scrollToView(By.name("spotRatePerKg"));
		captureAndAddScreenshot();
		String val = getAttributebyValue(By.name("spotRateId"));
		if (val.equals("") || val == null) {
			extentTest.log(LogStatus.PASS, "Spot rate " + val + " has been detached");
		} else {
			extentTest.log(LogStatus.FAIL, "Spot Rate detachment failed");
		}
		return this;
	}

	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param prefix
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param product
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param carrierCode
	 * @return
	 * @author Faizan A-8452
	 * @date 30-01-2019
	 */
	public CAP018 enterValuesWithOneFltInCAP018(String prefix, String awbNo, String origin, String dest,
			String agentCode, String product, String pcs, String wt, String vol, String fltNo, String fltDt,
			String carrierCode, boolean isMultiLeg, String intermediateStation, String shipmentDescription,
			String commodityCode, String scc, String forceStatus) {

		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		product = PropertyHandler.getPropValue(dataFilePath, product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		String carrierWithFlightno = carrierCode + fltNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		shipmentDescription = PropertyHandler.getPropValue(dataFilePath, shipmentDescription);
		commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		if (!(prefix.equalsIgnoreCase("001")))// not an AA AWB
		{
			enterKeys(txt_awbNoPrefix, prefix);
			try {
				awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
				enterKeys(txt_awbNo, awbNo);
			} catch (Exception e) {
				extentTest.log(LogStatus.FAIL, "AWB for the OAL is not specified in the properties File");
				Assert.fail();
			}
		}
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Clicked on list button");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		minWait();
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(txt_productname, product);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		enterKeys(By.xpath("//*[@id='commodityCode0']"), commodityCode);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_commVol, vol);
		// Weight-Volume unit
		
		setDimensions(pcs, wt);
		String volume = driver.findElement(txt_commVol).getAttribute("value");
		PropertyHandler.setPropValue(dataFilePath, "volume", volume);
		extentTest.log(LogStatus.INFO, "Origin is: " + origin + " Destination is: " + dest);

		extentTest.log(LogStatus.INFO, "Successfully entered shipment details. Commodity is: " + commodityCode);
		if (isMultiLeg == true) {
			dest = PropertyHandler.getPropValue(dataFilePath, intermediateStation);
		}
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt + Keys.TAB);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		// enterKeys(By.xpath("//input[@name='flightVolume'])[1]"), volume);
		scrollToView(list_dropDown_fltForce);
		// selectByText(list_dropDown_fltForce, forceStatus);
		extentTest.log(LogStatus.INFO, "Successfully entered flight details. Flight Number is: "
				+ carrierWithFlightno + " O-D: " + origin + "-" + dest);
		return this;
	}

	/**
	 * @author A-8452 Faizan
	 * @param Service
	 * @return
	 */
	public CAP018 AddServiceCargo(String Service) {
		Service = PropertyHandler.getPropValue(dataFilePath, Service);
		selectByText((By.xpath("//*[@id='serviceCargo']")), Service);
		minWait();
		return this;
	}

	/*
	 * Faizan
	 */
	public CAP018 enterAnotherFlightforMultilegFlt(String origin, String dest, String pcs, String wt, String fltDt,
			String carrierCode, String fltNo, String forceStatus, int legNo) {
		legNo -= 1;

		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		String carrierWithFlightno = carrierCode + fltNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno" + legNo, carrierWithFlightno);

		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(legNo), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
		enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
		scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
		// selectByText(getWebElements(list_dropDown_fltForce).get(legNo),
		// forceStatus);
		extentTest.log(LogStatus.INFO,
				"Successfully entered leg: " + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);

		return this;
	}
	
//	Sharath
	public CAP018 bookingWithMultipleULDs(String awbno, String bookingStatus) {
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//		bookingStatus = PropertyHandler.getPropValue(dataFilePath, bookingStatus);
		simpleBookingwithCommCodeAndULD("prefix", "awbno", "origin", "dest", "intermediate", "agentCode", "Product",
				"pcs", "wt", "vol", "FlightNo1", "fltDt1", "ubr", "commCode", "ULDType", "noOfULD", "CarrierCode");
		enterAnotherFlightforMultilegFlt("intermediate", "dest", "pcs", "wt", "fltDt2", "CarrierCode", "FlightNo2", "forceStatus", 2);
		saveInCAP018AndVerifyUBRStatus("awbno", "bookingStatus");
		return this;
	}
	
	/**
	 * Performs a simple booking with the given parameters.
	 * 
	 * @param stockType
	 * @param awbPre
	 * @param awb
	 * @param origin
	 * @param dest
	 * @param agentCode
	 * @param scc
	 * @param commCode
	 * @param pcs
	 * @param wt
	 * @param vol
	 * @param fltNo
	 * @param fltDt
	 * @param ubr
	 * @param bookingType
	 * @return
	 * @author Sharath
	 */
	public CAP018 simpleBookingwith2ULDs(String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo,
			String fltDt, String ubr, String commCode, String ULDType1, String ULDType2, String noOfULD, String CarrierCode, String wt1, String wt2) {

		CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
		Product = PropertyHandler.getPropValue(dataFilePath, Product);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		ULDType1 = PropertyHandler.getPropValue(dataFilePath, ULDType1);
		ULDType2 = PropertyHandler.getPropValue(dataFilePath, ULDType2);
		noOfULD = PropertyHandler.getPropValue(dataFilePath, noOfULD);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);
		wt2 = PropertyHandler.getPropValue(dataFilePath, wt2);
		String carrierWithFlightno = CarrierCode + FlightNo;
		PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno", carrierWithFlightno);

		/*
		 * enterKeys(txt_awbNoPrefix, prefix); enterKeys(txt_awbNo, awbno);
		 */
		click(btn_List);
		extentTest.log(LogStatus.INFO, "Started with the booking process");
		minWait();
		driver.switchTo().defaultContent();
		if (verifyElementPresent(info_msg)
				&& driver.findElement(info_msg).getText().trim().equals("AWB does not exist.Do you want to capture?")) {
			click(btn_genericYes);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
		driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode + Keys.TAB);
		minWait();
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		enterKeys(txt_uldType, ULDType1);
		enterKeys(txt_noOfULD, noOfULD);
		enterKeys(By.name("uldWeight"), wt1);
		enterKeys(txt_uldCommodityCode, commCode);
		click(By.name("addULDDetailLink"));
		enterKeys(getWebElements(txt_uldType).get(1), ULDType2);
		enterKeys(getWebElements(txt_noOfULD).get(1), noOfULD);
		enterKeys(getWebElements(By.name("uldWeight")).get(1), wt2);
		enterKeys(getWebElements(txt_uldCommodityCode).get(1), commCode);
		extentTest.log(LogStatus.INFO, "Entered all the shipment details");
		enterKeys(list_txt_fltOrigin, origin);
		enterKeys(list_txt_fltDest, dest);
		enterKeys(list_txt_fltNo, carrierWithFlightno);
		enterKeys(list_txt_fltDt, fltDt);
		enterKeys(list_txt_fltPcs, pcs);
		enterKeys(list_txt_fltWt, wt);
		extentTest.log(LogStatus.INFO, "Entered the flight details");
		/*
		 * scrollToView(list_dropDown_fltForce);
		 * selectByText(list_dropDown_fltForce, "Confirm");
		 */
		return this;
	}	
	
	/**
     * @author A-8452 Faizan
     * @return
     */
     public CAP018 AddCCshipment()
     {
            minWait();
            driver.findElement(By.name("ccShipment")).click();
            return this;
     }
     /*
      * Souvik A-8457
      * Date: 23-05-2019
      * MultiLeg(3-legs) Simple booking
      */

      public CAP018 MultilegBookingfor3Legs(String prefix, String awbno,
                   String origin, String dest, String agentCode, String Product,
                   String scc, String pcs, String wt, String vol, String FlightNo,
                   String fltDt,String intermediate, String ubr, String commodityCode, String CarrierCode,
                   String P1Value, String UDIDNo,String forceStatus,String NoOfLegs, String bookingStatus,String flt2Dt,String carrierWithFlightno2,
                   String flt3Dt,String carrierWithFlightno3,String intermediate1)
      {
             
             CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
             prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
             FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
             //awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
             origin = PropertyHandler.getPropValue(dataFilePath, origin);
             dest = PropertyHandler.getPropValue(dataFilePath, dest);
             agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
             Product = PropertyHandler.getPropValue(dataFilePath, Product);
             pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
             wt = PropertyHandler.getPropValue(dataFilePath, wt);
             vol = PropertyHandler.getPropValue(dataFilePath, vol);
             scc = PropertyHandler.getPropValue(dataFilePath, scc);
             commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
             fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
             P1Value = PropertyHandler.getPropValue(dataFilePath, P1Value);
             UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
             forceStatus= PropertyHandler.getPropValue(dataFilePath, forceStatus);
             flt2Dt=PropertyHandler.getPropValue(dataFilePath, flt2Dt);
             intermediate=PropertyHandler.getPropValue(dataFilePath, intermediate);
             carrierWithFlightno2=PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno2);
             flt3Dt=PropertyHandler.getPropValue(dataFilePath, flt3Dt);
             carrierWithFlightno3=PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno3);
             intermediate1=PropertyHandler.getPropValue(dataFilePath, intermediate1);
             
             String carrierWithFlightno = CarrierCode + FlightNo;
             PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno", carrierWithFlightno);
             int legNo=Integer.parseInt(PropertyHandler.getPropValue(dataFilePath, NoOfLegs));
             
             simpleBookingwithoutULDandMultilegFLT1(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo, fltDt, CarrierCode, intermediate,forceStatus);
             addValuesInCAP018();
             enterAnotherFlightforMultilegFlt1(intermediate, intermediate1, pcs, wt, flt2Dt, carrierWithFlightno2, forceStatus, legNo-1);
             enterAnotherFlightforMultilegFlt1(intermediate1, dest, pcs, wt, flt3Dt, carrierWithFlightno3, forceStatus, legNo);
             saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus); 
             return this;
      }





/* Souvik A-8457
      * Human Remains booking with Spot Rate
      * 3 Leg booking
      */
      public CAP018 TLCBookingWith3Leg(String prefix, String awbno, String origin,
                   String dest, String agentCode, String Product, String pcs,String commodityCode,
                   String wt, String vol, String FlightNo, String fltDt,
                   String CarrierCode,String forceStatus,String bookingStatus, String NoOfLegs, String intermediate , String intermediate1,
                   String carrierWithFlightno2 , String flt2Dt , String carrierWithFlightno3 , String flt3Dt)
      {
             prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
             origin = PropertyHandler.getPropValue(dataFilePath, origin);
             dest = PropertyHandler.getPropValue(dataFilePath, dest);
             agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
             commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
             Product = PropertyHandler.getPropValue(dataFilePath, Product);
             pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
             wt = PropertyHandler.getPropValue(dataFilePath, wt);
             vol = PropertyHandler.getPropValue(dataFilePath, vol);
             FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
             fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
             CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
             forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
             bookingStatus = PropertyHandler.getPropValue(dataFilePath, bookingStatus);
             int Legs=Integer.parseInt(PropertyHandler.getPropValue(dataFilePath, NoOfLegs));
             intermediate = PropertyHandler.getPropValue(dataFilePath, intermediate);
             intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
             carrierWithFlightno2 = PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno2);
             flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
             carrierWithFlightno3 = PropertyHandler.getPropValue(dataFilePath, carrierWithFlightno3);
             flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
             
             simpleBookingwithoutULDandMultilegFLT1(prefix, awbno, origin, dest, agentCode, Product, pcs, wt, vol, FlightNo, fltDt, CarrierCode, intermediate,forceStatus);
             addValuesInCAP018();
             if(Legs>1){
                   enterAnotherFlightforMultilegFlt1(intermediate, intermediate1, pcs, wt, flt2Dt, carrierWithFlightno2, forceStatus, Legs);
                   }
             if(Legs>2){
                   enterAnotherFlightforMultilegFlt1(intermediate1, dest, pcs, wt, flt3Dt, carrierWithFlightno3, forceStatus, Legs);
                   }
                
             fillCheckSheetafterSaveClick(commodityCode);
             saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);
             return this;
      }

      

  	public CAP018 TestDataCreationSave(String index, String excelPath) throws Exception {
  		
  			click(btn_save);
  			maxWait();
  			driver.switchTo().defaultContent();
  			if (verifyElementPresent(By.xpath("//p[contains(text(),'Do you want to capture Check Sheet')]"))) {
  				click(By.xpath("//button[text()='Yes']"));
  				fillCheckSheet(PropertyHandler.getPropValue(dataFilePath, "commCode"));
  				waitForFrameAndSwitch(screenFrame);
  				click(btn_save);
  			} else {
  				waitForFrameAndSwitch(screenFrame);
  			}
  			continueEmbargo();
  			/*
  			 * try{ handleAlert("Accept", "CAP018");
  			 * waitForFrameAndSwitch("iCargoContentFrameCAP018"); } catch(Exception
  			 * e){ waitForFrameAndSwitch("iCargoContentFrameCAP018"); }
  			 */
  			maxWait();
  			if (verifyElementPresent(btn_genericYes)) {
  				click(btn_genericYes);
  				String msg = waitForElement(info_msg).getText();
  				extentTest.log(LogStatus.INFO, "Clicked Yes on Popup : " + msg);
  				logger.info("Clicked Yes on Popup : " + msg);
  				minWait();
  			}
  			minWait();
  			switchToSecondWindow();
  			click(btn_popUp_Ok);
  			switchBackToFirstWindow();
  			// waitForFrameAndSwitch(screenFrame);
  			maxWait();
  			maxWait();

  			if (driver.getWindowHandles().size() > 1) {
  				switchToSecondWindow();
  				click(btn_popUp_Ok);
  				switchBackToFirstWindow();
  			}

  			waitForFrameAndSwitch(screenFrame);
  			minWait();

  			String awb = waitForElement(txt_awbNo).getAttribute("value");
  			PropertyHandler.setPropValue(dataFilePath, "awbNo", awb);
  			int rowNoint = Integer.parseInt(index);
  			writeDatatoExcelCell(excelPath, rowNoint, "001-"+awb);
  		
  		return this;
  	}
  	
  	
  	public CAP018 handleDGpopUp(String UDIDNo, String ShippingName, String emergencyName, String emergencyContact, String pcs, String wt, String commCode) throws Exception {
  		if(!UDIDNo.equalsIgnoreCase("")){
  		// DGR UNID
			click(btn_DGR);
			maxWait();
			// maxWait();
			waitForFrameAndSwitch("popupContainerFrame");
//			driver.switchTo().frame("popupContainerFrame");
			minWait();
//			click(btn_PopUpicon);
//			minWait();
//			scrollToView(txt_EmergencyContactName);
//			enterKeys(txt_EmergencyContactName,emergencyName);
//			enterKeys(txt_EmergencyContactNumber, emergencyContact);
//			click(btn_iconOK);
//			minWait();
			scrollToView(txt_DGRUNIDnumber);
			enterKeys(txt_DGRUNIDnumber, UDIDNo + Keys.TAB);
			minWait();
			selectByText(Select_ShippingName, ShippingName);
//			enterKeys(txt_P1Value, P1Value);
			/*enterKeys(txt_NoofPackges, pcs);
			enterKeys(txt_NetValue, wt);
			selectByText(Select_Reportable, "Yes");*/
//			minWait();
//			click(By.name("dgVerifiedFlag"));
//			minWait();
			click(btn_Popupadd);
			click(btn_PopupOK);
			extentTest.log(LogStatus.PASS, "Successfully filled details with UNID: " + UDIDNo);
//			maxWait();
//			maxWait();
			switchBackToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
			minWait();             
//			if (!UDIDNo.equals("3322")) {
			click(btn_save);
			minWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(btn_genericYes)) {
				click(btn_genericYes);
			}
			waitForFrameAndSwitch(screenFrame);
			}
//  		}
			return this;
		}

	// Sharath
	public CAP018 captureTCDetailsULDTestData(String wt, String origin, String fltDt) {
		click(By.name("btCaptureTC"));
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		minWait();
		driver.findElement(By.xpath("//h4[text()='Additional Details']/../preceding::span[1]")).click();
		// click(By.xpath("//h4[text()='Additional
		// Details']/../preceding::span[1]"));
		selectByText(By.xpath("//select[@name='temRange']"), "2 to 8");
		//int w = Integer.parseInt(wt);
		enterKeys(By.name("dryIceWeight"), "100");
		enterKeys(By.name("emergencyContactName"), "testuser");
		enterKeys(By.name("emergencyContactNumber"), "12345");
		enterKeys(By.name("emergencyContactEmail"), "abc@gmail.com");
		enterKeys(By.name("airportCode"), origin);
		enterKeys(By.name("reIceWeight"), wt);
		enterKeys(By.name("plannedDate"), fltDt);
		enterKeys(By.name("plannedTime"), "10" + Keys.TAB);
		maxWait();
		click(By.name("btnReIceAdd"));
		minWait();

	/*	scrollToView(By.xpath("//*[text()='Orders & ULD Details']/.."));
		click(By.xpath("//*[text()='Orders & ULD Details']/.."));
		click(By.name("btnActualUld"));
		maxWait();
		enterKeys(By.name("actualUldNumber"), uldNo);
		click(By.name("button_actual_ok"));
		maxWait();
		scrollToView(By.xpath("//*[text()='Monitoring Details']/.."));
		click(By.xpath("//*[text()='Monitoring Details']/.."));
		enterKeys(By.xpath("//input[@name='airport']"), origin);
		selectByText(By.xpath("//select[@name='uldNumber']"), uldNo);
		// click(By.xpath("//input[@value='pcs']"));
		minWait();
		// enterKeys(By.xpath("//div[contains(@id,'monitoringFilter')]//input[@name='pieces']"),pcs);
		selectByText(By.xpath("//select[@name='chkType']"), "Is Set Temperature Correct");
		maxWait();
		selectByText(By.xpath("//select[@name='value']"), "Yes");
		enterKeys(By.name("remarks"), "test");
		click(By.name("BtnMonitoringAdd"));*/
		minWait();
		click(By.name("btnOK"));
		waitForFrameAndSwitch(screenFrame);
	/*	if(commCode.equals("ACTTCPIL")){
		fillCheckSheetafterSaveClickTestData(commCode);
		}*/
		minWait();
		return this;
	}

  	public CAP018 TestDataCreationSave(String index, String excelPath, String commCode) throws Exception {
			click(btn_save);
			maxWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(By.xpath("//p[contains(text(),'Do you want to capture Check Sheet')]"))) {
				click(By.xpath("//button[text()='Yes']"));
				fillCheckSheet(commCode);
				waitForFrameAndSwitch(screenFrame);
				click(btn_save);
			} else {
				waitForFrameAndSwitch(screenFrame);
			}
			continueEmbargo();
			/*
			 * try{ handleAlert("Accept", "CAP018");
			 * waitForFrameAndSwitch("iCargoContentFrameCAP018"); } catch(Exception
			 * e){ waitForFrameAndSwitch("iCargoContentFrameCAP018"); }
			 */
			maxWait();
			if (verifyElementPresent(btn_genericYes)) {
				click(btn_genericYes);
				String msg = waitForElement(info_msg).getText();
				extentTest.log(LogStatus.INFO, "Clicked Yes on Popup : " + msg);
				logger.info("Clicked Yes on Popup : " + msg);
				minWait();
			}
			minWait();
			switchToSecondWindow();
			click(btn_popUp_Ok);
			switchBackToFirstWindow();
			// waitForFrameAndSwitch(screenFrame);
			maxWait();
			maxWait();

			if (driver.getWindowHandles().size() > 1) {
				switchToSecondWindow();
				click(btn_popUp_Ok);
				switchBackToFirstWindow();
			}

			waitForFrameAndSwitch(screenFrame);
			minWait();

			String awb = waitForElement(txt_awbNo).getAttribute("value");
			PropertyHandler.setPropValue(dataFilePath, "awbNo", awb);
			int rowNoint = Integer.parseInt(index);
			writeDatatoExcelCell(excelPath, rowNoint, "001-"+awb);
		
		return this;
	}
  	
//	Sharath
	public CAP018 enterFlightforMultilegFlt1TestData(String OriginOfNewleg, String destOfNewleg, String pcs, String wt,
			String vol, String fltDt, String carrierCode, String FlightNo, String forceStatus, int legNo) {
        
		legNo -= 1;
		
		String carrierWithFlightno = carrierCode+FlightNo;
		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), OriginOfNewleg);
		enterKeys(getWebElements(list_txt_fltDest).get(legNo), destOfNewleg);
		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
//		enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
//		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
		// enterKeys(getWebElements(list_txt_fltVol).get(legNo),vol);
		scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
		// selectByText(getWebElements(list_dropDown_fltForce).get(legNo),
		// forceStatus);
		extentTest.log(LogStatus.INFO,
				"Successfully entered leg" + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);
	

		return this;
	}
  	
	public CAP018 TestDataCreationSaveDG(String index, String excelPath, String test) throws Exception {
			click(btn_save);
			maxWait();
			if(test.equalsIgnoreCase("Class6 booking")){
			if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
				String status = getText_JS(By.xpath("//td[@class='ic-error-msg']")).trim();
				if(status.contains("UNID 1098 cannot be carried by Air")){
					extentTest.log(LogStatus.PASS, "Booking isnt created with status : "+status);
					captureAndAddScreenshot();
				}else{
					extentTest.log(LogStatus.FAIL, "Booking isnt created due to invalid reason");
				}
			}}
			else{
			driver.switchTo().defaultContent();
			if (verifyElementPresent(By.xpath("//p[contains(text(),'Do you want to capture Check Sheet')]"))) {
				click(By.xpath("//button[text()='Yes']"));
				fillCheckSheet(PropertyHandler.getPropValue(dataFilePath, "commCode"));
				waitForFrameAndSwitch(screenFrame);
				click(btn_save);
			} else {
				waitForFrameAndSwitch(screenFrame);
			}
			continueEmbargo();
			/*
			 * try{ handleAlert("Accept", "CAP018");
			 * waitForFrameAndSwitch("iCargoContentFrameCAP018"); } catch(Exception
			 * e){ waitForFrameAndSwitch("iCargoContentFrameCAP018"); }
			 */
			maxWait();
			if (verifyElementPresent(btn_genericYes)) {
				click(btn_genericYes);
//				String msg = waitForElement(info_msg).getText();
				extentTest.log(LogStatus.INFO, "Clicked Yes on Popup : " /*+ msg*/);
				logger.info("Clicked Yes on Popup : "/* + msg*/);
				minWait();
			}
			minWait();
			
			driver.switchTo().defaultContent();
			switchToSecondWindow();
			click(btn_popUp_Ok);
			switchBackToFirstWindow();
			// waitForFrameAndSwitch(screenFrame);
			maxWait();
			maxWait();

			if (driver.getWindowHandles().size() > 1) {
				switchToSecondWindow();
				click(btn_popUp_Ok);
				switchBackToFirstWindow();
			}

			waitForFrameAndSwitch(screenFrame);
			minWait();

			String awb = waitForElement(txt_awbNo).getAttribute("value");
			PropertyHandler.setPropValue(dataFilePath, "awbNo", awb);
			int rowNoint = Integer.parseInt(index);
			writeDatatoExcelCell(excelPath, rowNoint, "001-"+awb);
			extentTest.log(LogStatus.PASS, "Booking successfully done with and awb no is : "+ awb);
			captureAndAddScreenshot();
			}
			
		return this;
	}
	
	public CAP018 enterInitialBookingDetailsforPropertyfile(String origin, String dest, String agentCode, String Product, String pcs,
			String wt, String vol, String fltDt, String ULDType, String ULDwt, String commCode) {
		click(btn_List);
//		minWait();
		driver.switchTo().defaultContent();
//		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_origin, origin);
		enterKeys(txt_dest, dest);
		enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
		enterKeys(By.name("shipmentDate"), fltDt + Keys.TAB);
//		minWait();
		if (Product.equals("COMAT")) {
			selectByText(By.name("serviceCargoClass"), "COMAT");
		}
		selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
		try{
		if(PropertyHandler.getPropValue(dataFilePath, "unit").equalsIgnoreCase("kg")){
			selectByText(sel_WeightUnit, "Kilogram"); // case-sensitive
			selectByText(sel_VolumeUnit, "Cubic Meters");// case-sensitive
		}}catch(Exception e){
		}
		enterKeys(txt_productname, Product);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		if (ULDType == null || ULDType.equals("")) {
			setDimensions(pcs, wt);
		} else {
			enterKeys(txt_commVol, vol);
			enterKeys(txt_uldType, ULDType);
			enterKeys(txt_noOfULD, "1");
			enterKeys(txt_uldWt, wt);
			enterKeys(txt_uldCommodityCode, commCode);
		}
		extentTest.log(LogStatus.INFO, "Successfully entered booking details");
		return this;
	}
	
	public CAP018 enteradditionalCommodityforPropertyfile(String pcs,
			String wt, String vol, String ULDType, String ULDwt, String commCode, int additionalCommNo) {

//		additionalCommNo =-1;
		
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
//		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		ULDType = PropertyHandler.getPropValue(dataFilePath, wt);
		ULDwt = PropertyHandler.getPropValue(dataFilePath,ULDwt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		
		
		/*selectByText(sel_WeightUnit, "Pound"); // case-sensitive
		selectByText(sel_VolumeUnit, "Cubic Feet");// case-sensitive
*/		click(By.xpath("//a[@id='addBookingDetailLink']"));
		enterKeys(By.xpath("(//input[@name='commodityCode'])[2]"), commCode);
		enterKeys(By.xpath("(//input[@name='pieces'])[2]"), pcs);
		enterKeys(By.xpath("(//input[@name='weight'])[2]"), wt);
		if (ULDType == null || ULDType.equals("")) {
			setDimensions2(pcs, wt);
		} else {
			enterKeys(txt_commVol, vol);
			enterKeys(txt_uldType, ULDType);
			enterKeys(txt_noOfULD, "1");
			enterKeys(txt_uldWt, wt);
			enterKeys(txt_uldCommodityCode, commCode);
		}
		extentTest.log(LogStatus.INFO, "Successfully entered booking details");
		return this;
	}
	
//	Sharath
	public CAP018 enterFlightDetailsforPropertyFile(String origin, String dest,String fltDt, String carrierCode, String FlightNo, int legNo) {
		legNo -= 1;
		String carrierWithFlightno = carrierCode+FlightNo;
		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), origin);
		enterKeys(getWebElements(list_txt_fltDest).get(legNo), dest);
		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
//		enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
//		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
		// enterKeys(getWebElements(list_txt_fltVol).get(legNo),vol);
//		scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
//		 selectByText(getWebElements(list_dropDown_fltForce).get(legNo),"Confirm");
		extentTest.log(LogStatus.INFO,
				"Successfully entered leg" + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);
		return this;
	}
	
//	Sharath
	public CAP018 handleDGpopUpForPropertyFile(String emergencyName, String emergencyContact, 
			String DGpcs, String DGwt ,String commCode,String UDIDNo[], String ShippingName[], int i) throws Exception {
//		 UDIDNo = new String [i];
//		 ShippingName = new String [i];
//		 UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
		
//		 ShippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
//		 emergencyName = PropertyHandler.getPropValue(dataFilePath, emergencyName);
//		 emergencyContact = PropertyHandler.getPropValue(dataFilePath, emergencyContact);
//		 pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//		 DGpcs = PropertyHandler.getPropValue(dataFilePath, DGpcs);
//		 wt = PropertyHandler.getPropValue(dataFilePath, wt);
//		 DGwt = PropertyHandler.getPropValue(dataFilePath, DGwt);
//		 commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
		// DGR UNID
			click(btn_DGR);
			maxWait();
			// maxWait();
			waitForFrameAndSwitch("popupContainerFrame");
			minWait();
//			click(btn_PopUpicon);
//			minWait();
//			scrollToView(txt_EmergencyContactName);
//			enterKeys(txt_EmergencyContactName,emergencyName);
//			enterKeys(txt_EmergencyContactNumber, emergencyContact);
//			click(btn_iconOK);
			if(i==1){
//				String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
				minWait();
				scrollToView(txt_DGRUNIDnumber);
				enterKeys(txt_DGRUNIDnumber, UDIDNo[0] + Keys.TAB);
				minWait();
				selectByText(Select_ShippingName, ShippingName[0]);
				selectByText(Select_Reportable, "Yes");
				click(btn_Popupadd);
			}else{
			for(int a=0;a<i;a++){
//				String b = Integer.toString(a);
//				String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo+b);
//				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName+b);
				minWait();
				scrollToView(txt_DGRUNIDnumber);
			enterKeys(txt_DGRUNIDnumber, UDIDNo [a]+ Keys.TAB);
			minWait();
			selectByText(Select_ShippingName, ShippingName[a]);
//			enterKeys(txt_P1Value, P1Value);
//			enterKeys(txt_NoofPackges, DGpcs);
//			enterKeys(txt_NetValue, DGwt);
			selectByText(Select_Reportable, "Yes");
			click(btn_Popupadd);
			}
			}
		//	click(By.name("dgVerifiedFlag"));
			minWait();
			extentTest.log(LogStatus.INFO, "Successfully added DG as all pack");
			captureAndAddScreenshot();
			maxWait();
			/*if (packType.equalsIgnoreCase("AllPack")) {
				DGPackAllPropertyFile(i, pcs);
			} else if (packType.equalsIgnoreCase("overpack")) {
				DGPackOverPropertyFile(i, pcs);
			}*/
			click(btn_PopupOK);
			extentTest.log(LogStatus.PASS, "Successfully filled details with UNID");
//			maxWait();
//			maxWait();
			switchBackToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
			minWait();             
			/*click(btn_save);
			minWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(btn_genericYes)) {
				click(btn_genericYes);
			}
			waitForFrameAndSwitch(screenFrame);*/
			return this;
		}

	//	Sharath
	public CAP018 handleDGpopUpForPropertyFile(String emergencyName, String emergencyContact, 
			String DGpcs, String DGwt ,String commCode,String UDIDNo, String ShippingName, int i) throws Exception {
		// DGR UNID
			click(btn_DGR);
//			maxWait();
			maxWait();
			waitForFrameAndSwitch("popupContainerFrame");
//			minWait();
//			click(btn_PopUpicon);
//			minWait();
//			scrollToView(txt_EmergencyContactName);
//			enterKeys(txt_EmergencyContactName,emergencyName);
//			enterKeys(txt_EmergencyContactNumber, emergencyContact);
//			click(btn_iconOK);
			if(i==1){
//				String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
				minWait();
				scrollToView(txt_DGRUNIDnumber);
				enterKeys(txt_DGRUNIDnumber, UDIDNo + Keys.TAB);
				minWait();
				selectByText(Select_ShippingName, ShippingName);
				selectByText(Select_Reportable, "Yes");
				click(btn_Popupadd);
			}else{
			for(int a=0;a<i;a++){
//				String b = Integer.toString(a);
//				String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo+b);
//				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName+b);
				minWait();
				scrollToView(txt_DGRUNIDnumber);
			enterKeys(txt_DGRUNIDnumber, UDIDNo+ Keys.TAB);
			minWait();
			selectByText(Select_ShippingName, ShippingName);
//			enterKeys(txt_P1Value, P1Value);
//			enterKeys(txt_NoofPackges, DGpcs);
//			enterKeys(txt_NetValue, DGwt);
			selectByText(Select_Reportable, "Yes");
			click(btn_Popupadd);
			}
			}
		//	click(By.name("dgVerifiedFlag"));
//			minWait();
			extentTest.log(LogStatus.INFO, "Successfully added DG as all pack");
			captureAndAddScreenshot();
//			maxWait();
			/*if (packType.equalsIgnoreCase("AllPack")) {
				DGPackAllPropertyFile(i, pcs);
			} else if (packType.equalsIgnoreCase("overpack")) {
				DGPackOverPropertyFile(i, pcs);
			}*/
			click(btn_PopupOK);
			extentTest.log(LogStatus.PASS, "Successfully filled details with UNID");
//			maxWait();
//			maxWait();
			switchBackToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
			minWait();             
			/*click(btn_save);
			minWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(btn_genericYes)) {
				click(btn_genericYes);
			}
			waitForFrameAndSwitch(screenFrame);*/
			return this;
		}

//	Sharath
	public CAP018 DGPackAllPropertyFile(int i, String pcs) throws Exception {
		scrollToView(By.xpath("(//table[@class='awbDetailtable'])"));
		maxWait();
		for (int a = 1; a <= i; a++) {
			click(By.xpath("(//table[@class='awbDetailtable']//tbody//td/input)[" + a + "]"));
		}
		maxWait();
		click(By.xpath("//button[@name='btAllPacked']"));
		extentTest.log(LogStatus.PASS, "Successfully clicked on all pack button");
		captureAndAddScreenshot();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		enterKeys(By.name("numberOfOAPackages"), pcs);
		enterKeys(By.name("pkgGrpDimensionLength"), "10");
		enterKeys(By.name("pkgGrpDimensionWidth"), "10");
		enterKeys(By.name("pkgGrpDimensionHeight"), "10");
		click(By.name("btOk"));
		maxWait();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		waitForFrameAndSwitch("popupContainerFrame");
		return this;
	}
	
	public CAP018 DGPackOverPropertyFile(int i, String pcs) throws Exception {
		scrollToView(By.xpath("(//table[@class='awbDetailtable'])"));
		maxWait();
		for (int a = 1; a <= i; a++) {
			click(By.xpath("(//table[@class='awbDetailtable']//tbody//td/input)[" + a + "]"));
		}
		maxWait();
		click(By.xpath("//button[@name='btAllPacked']"));
		extentTest.log(LogStatus.PASS, "Successfully clicked on all pack button");
		captureAndAddScreenshot();
		maxWait();
		waitForFrameAndSwitch("popupContainerFrame");
		enterKeys(By.name("numberOfOAPackages"), pcs);
		enterKeys(By.name("pkgGrpDimensionLength"), "10");
		enterKeys(By.name("pkgGrpDimensionWidth"), "10");
		enterKeys(By.name("pkgGrpDimensionHeight"), "10");
		click(By.name("btOk"));
		maxWait();
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
		waitForFrameAndSwitch("popupContainerFrame");
		return this;
	}
	
	public CAP018 updateBookingWithNewCommCodePropertyfile(String prefix, String awbno, String pcs, String wt, String vol, 
			String fltDt, String commCode) {

	/*	prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		vol = PropertyHandler.getPropValue(dataFilePath, vol);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		commCode = PropertyHandler.getPropValue(dataFilePath, commCode);*/
		enterKeys(txt_awbNoPrefix, prefix); 
		enterKeys(txt_awbNo, awbno);
		click(btn_List);
		driver.switchTo().defaultContent();

		if (verifyElementPresent(btn_noBtn)) {
			click(btn_noBtn);

		} else if (verifyElementPresent(btn_okBtn)) {
			click(btn_okBtn);
		}
		minWait();
		waitForFrameAndSwitch(screenFrame);
		enterKeys(txt_commCode, commCode);
		enterKeys(txt_commPcs, pcs);
		enterKeys(txt_commWt, wt);
		setDimensions(pcs, wt);
		extentTest.log(LogStatus.INFO, "Successfully entered booking details");
		return this;
	}
	
	/**
     * Performs a simple booking with the given parameters.
     * 
      * @param stockType
     * @param awbPre
     * @param awb
     * @param origin
     * @param dest
     * @param agentCode
     * @param scc
     * @param commCode
     * @param pcs
     * @param wt
     * @param vol
     * @param fltNo
     * @param fltDt
     * @param ubr
     * @param bookingType
     * @return
     * @author A-8457   Souvik
     */
     public CAP018 simpleBookingwithDGR(String prefix, String awbno,
                  String origin, String dest, String agentCode, String Product,
                  String SCC, String pcs, String wt, String vol, String FlightNo,
                  String fltDt, String ubr, String commCode, String CarrierCode,
                  String P1Value, String UDIDNo , String ShippingName) {

            CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
            prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
            FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
            //awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
            origin = PropertyHandler.getPropValue(dataFilePath, origin);
            dest = PropertyHandler.getPropValue(dataFilePath, dest);
            agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
            Product = PropertyHandler.getPropValue(dataFilePath, Product);
            pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
            wt = PropertyHandler.getPropValue(dataFilePath, wt);
            vol = PropertyHandler.getPropValue(dataFilePath, vol);
            SCC = PropertyHandler.getPropValue(dataFilePath, SCC);
            commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
            fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
            P1Value = PropertyHandler.getPropValue(dataFilePath, P1Value);
            String carrierWithFlightno = CarrierCode + FlightNo;
            UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
            ShippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
            PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno",
                         carrierWithFlightno);

            /*enterKeys(txt_awbNoPrefix, prefix);
            enterKeys(txt_awbNo, awbno);*/
            click(btn_List);
            extentTest.log(LogStatus.INFO,
                         "Successfully entered Prefix and AWB no in CAP018 screen: "
                                       + awbno);
            minWait();
            /*driver.switchTo().defaultContent();
            if (verifyElementPresent(info_msg)
                         && driver.findElement(info_msg).getText().trim()
                                       .equals("AWB does not exist.Do you want to capture?")) {
                  click(btn_genericYes);
            }*/
            minWait();
            //waitForFrameAndSwitch(screenFrame);
            enterKeys(txt_origin, origin);
            enterKeys(txt_dest, dest);
            enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
            driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
            driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
            enterKeys(txt_productname, Product);
            selectByText(sel_WeightUnit, "Pound"); //case-sensitive
            selectByText(sel_VolumeUnit, "Cubic Feet");//case-sensitive
            try{
            	if(PropertyHandler.getPropValue(dataFilePath, "unit").equalsIgnoreCase("kg")){
            		selectByText(sel_WeightUnit, "Kilogram"); // case-sensitive
        			selectByText(sel_VolumeUnit, "Cubic Meters");// case-sensitive
            	}
            }catch(Exception e){
            	
            }
            enterKeys(txt_commPcs, pcs);
            enterKeys(txt_commWt, wt);
            enterKeys(txt_commVol, vol);
            enterKeys(txt_commCode, commCode);
            setDimensions(pcs, wt);
            enterKeys(list_txt_fltOrigin, origin);
            enterKeys(list_txt_fltDest, dest);
            enterKeys(list_txt_fltNo, carrierWithFlightno);
            enterKeys(list_txt_fltDt, fltDt);
//            enterKeys(list_txt_fltPcs, pcs);
//            enterKeys(list_txt_fltWt, wt);
            scrollToView(list_dropDown_fltForce);
          selectByText(list_dropDown_fltForce, "Confirm");
            extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: "
                         + carrierWithFlightno);
            // DGR UNID
            click(btn_DGR);
            maxWait();
            // maxWait();
            waitForFrameAndSwitch("popupContainerFrame");
            minWait();
//            click(btn_PopUpicon);
//            minWait();
//            maxWait();
//            scrollToView(txt_EmergencyContactName);
//            enterKeys(txt_EmergencyContactName, "Test");
//            enterKeys(txt_EmergencyContactNumber, "123456789");
//            click(btn_iconOK);
            maxWait();
            scrollToView(txt_DGRUNIDnumber);
            enterKeys(txt_DGRUNIDnumber, UDIDNo+Keys.TAB);
            maxWait();
            selectByText(Select_ShippingName, ShippingName);
            minWait();
           // click(By.name("dgVerifiedFlag"));
			minWait();
//            enterKeys(txt_P1Value, P1Value);
//            enterKeys(txt_NoofPackges, pcs);
//            enterKeys(txt_NetValue, wt);
//            selectByText(By.xpath("//select[@name='netQuantityPerPackageUnit']"), "mL");
//            try{
//            	selectByText(By.xpath("//select[@name='netQuantityPerPackageUnit']"), PropertyHandler.getPropValue(dataFilePath, "unit"));
//            }
//            catch(Exception e)
//            {}
//            selectByText(Select_Reportable, "Yes");
            click(btn_Popupadd);
            maxWait();
            click(btn_PopupOK);
            extentTest.log(LogStatus.PASS, "Successfully filled details with UNID: " + UDIDNo);
//            maxWait();
//            maxWait();
            switchBackToFirstWindow();
            waitForFrameAndSwitch(screenFrame);
            minWait();
            click(btn_save);
            minWait();
            driver.switchTo().defaultContent();
            click(btn_genericYes);
            waitForFrameAndSwitch(screenFrame);
            maxWait();
            continueEmbargo();
            maxWait();
            switchToSecondWindow();
            click(btn_popUp_Ok);
            switchBackToFirstWindow();
            //to be tested
            driver.switchTo().defaultContent();
            waitForFrameAndSwitch(screenFrame);
            minWait();
            String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
            PropertyHandler.setPropValue(dataFilePath, "ubr", ubrNo);
            String awb = waitForElement(txt_awbNo).getAttribute("value");
            PropertyHandler.setPropValue(dataFilePath, awbno, awb);
            String bookingStatus=PropertyHandler.getPropValue(dataFilePath, "bookingStatus");
        	if (waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase("CONFIRMED")
    				|| waitForElement(info_bookingStatus).getAttribute("value").equalsIgnoreCase("QUEUED")) {
    			extentTest.log(LogStatus.PASS, "Successfully booked with: " + awb + " and UBR No: " + ubrNo);
    			captureAndAddScreenshot();
    			logger.info("Successfully booked with: " + awb + " and UBR No: " + ubrNo);

    		} else {
    			extentTest.log(LogStatus.FAIL, "VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo
    					+ "\n" + "Booking Status doesn't match");
    			logger.info("VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo + "\n"
    					+ "Booking Status doesn't match");
    			Assert.fail("VAILIDATION FAILURE: booked with: " + awb + "and UBR No: " + ubrNo + "\n"
    					+ "Booking Status doesn't match");
    		}
            extentTest.log(LogStatus.INFO, "Booking Status: " + waitForElement(info_bookingStatus).getAttribute(
                         "value"));

            return this;
     }
     
     /**
		 * Performs a simple booking with the given parameters for a single leg with 2 shipments
		 * 
		 * @param prefix
		 * @param awbPre
		 * @param awb
		 * @param origin
		 * @param dest
		 * @param agentCode
		 * @param product
		 * @param commCode
		 * @param pcs
		 * @param wt
		 * @param vol
		 * @param fltNo
		 * @param fltDt
		 * @param ubr
		 * @param carrierCode
		 * @return
		 * @author Souvik-8457
		 * @date 14-04-2020
		 */
		public CAP018 DGBookingwithTwoShipments(String prefix, String awbno, String origin, String dest,String agentCode,
				String product, String scc, String pieces, String weight, String volume, String flightNo, String fltDt, String intermediate,
				String ubr, String commodityCode, String CarrierCode, String P1Value, String UDIDNo,String forceStatus,
				 String bookingStatus,String pieces1,String weight1,String totalpieces,String totalweight , String ShippingName, String specialComcode, HashMap<String, String> data) {
			
			
//			ShippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
//			CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
//			prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//			flightNo = PropertyHandler.getPropValue(dataFilePath, flightNo);
//			origin = PropertyHandler.getPropValue(dataFilePath, origin);
//			dest = PropertyHandler.getPropValue(dataFilePath, dest);
//			agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
//			product = PropertyHandler.getPropValue(dataFilePath, product);
//			pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
//			weight = PropertyHandler.getPropValue(dataFilePath, weight);
//			volume = PropertyHandler.getPropValue(dataFilePath, volume);
//			scc = PropertyHandler.getPropValue(dataFilePath, scc);
//			commodityCode = PropertyHandler.getPropValue(dataFilePath, commodityCode);
//			fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
//			P1Value = PropertyHandler.getPropValue(dataFilePath, P1Value);
//			UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//			bookingStatus= PropertyHandler.getPropValue(dataFilePath, bookingStatus);
//			forceStatus= PropertyHandler.getPropValue(dataFilePath, forceStatus);
//			intermediate=PropertyHandler.getPropValue(dataFilePath, intermediate);
//			pieces1=PropertyHandler.getPropValue(dataFilePath, pieces1);
//			weight1=PropertyHandler.getPropValue(dataFilePath, weight1);
//			totalpieces=PropertyHandler.getPropValue(dataFilePath, totalpieces);
//			totalweight=PropertyHandler.getPropValue(dataFilePath, totalweight);
			
			String carrierWithFlightno = CarrierCode + flightNo;
		//	PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno", carrierWithFlightno);
			
			minWait();
			click(btn_List);
			minWait();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(info_msg)
					&& driver.findElement(info_msg).getText().trim()
							.equals("AWB does not exist.Do you want to capture?")) {
				click(btn_genericYes);
			}
			minWait();
			waitForFrameAndSwitch(screenFrame);
			enterKeys(txt_origin, origin);
			enterKeys(txt_dest, dest);
			enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);
			enterKeys(By.name("shipmentDate"), fltDt+Keys.TAB);
			enterKeys(txt_productname, product);
			selectByText(sel_WeightUnit, "Pound"); //case-sensitive
			selectByText(sel_VolumeUnit, "Cubic Feet");//case-sensitive
			//driver.findElement(By.name("shipmentDate")).sendKeys(".");
			//driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
			
			 enterKeys(txt_commCode, commodityCode+Keys.TAB);
			 minWait();
			enterKeys(txt_commPcs, pieces);
			enterKeys(txt_commWt, weight);
//			enterKeys(txt_commVol, volume);
		//	enterKeys(txt_shipmentDescrptn, "DAN");
			setDimensions(pieces, weight);
			
			minWait();
			//addValuesInCAP018();
			minWait();
			click(By.xpath("//a[@id='addBookingDetailLink']"));
			minWait();
			enterKeys(By.xpath("(//input[@name='commodityCode'])[2]"), "GENERAL");
			try{
				enterKeys(By.xpath("(//input[@name='commodityCode'])[2]"),specialComcode);
			}
			catch(Exception e)
			{}
			enterKeys(By.xpath("(//input[@name='pieces'])[2]"), pieces1);
			enterKeys(By.xpath("(//input[@name='weight'])[2]"), weight1);
			enterKeys(By.xpath("(//input[@name='volume'])[2]"),"5.789");
			setDimensions2(pieces1,weight1);
			minWait();
			enterKeys(list_txt_fltOrigin, origin);
			enterKeys(list_txt_fltDest, intermediate);
			enterKeys(list_txt_fltNo, carrierWithFlightno);
			enterKeys(list_txt_fltDt, fltDt+Keys.TAB);
//			enterKeys(list_txt_fltPcs, totalpieces);
//			enterKeys(list_txt_fltWt, totalweight);
			scrollToView(list_dropDown_fltForce);
			//selectByText(list_dropDown_fltForce, forceStatus);
			extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: "+carrierWithFlightno);
			
			
			
			  // DGR UNID
         click(btn_DGR);
         maxWait();
         // maxWait();
         driver.switchTo().frame("popupContainerFrame");
         minWait();
//         click(btn_PopUpicon);
//         minWait();
//         maxWait();
//         scrollToView(txt_EmergencyContactName);
//         enterKeys(txt_EmergencyContactName, "Test");
//         enterKeys(txt_EmergencyContactNumber, "123456789");
//         click(btn_iconOK);
         maxWait();
         scrollToView(txt_DGRUNIDnumber);
         enterKeys(txt_DGRUNIDnumber, UDIDNo+Keys.TAB);
         maxWait();
         selectByText(Select_ShippingName, ShippingName);
//         enterKeys(txt_P1Value, P1Value);
//         enterKeys(txt_NoofPackges, pieces);
//         enterKeys(txt_NetValue, weight);
//         selectByText(By.xpath("//select[@name='netQuantityPerPackageUnit']"), "mL");
//         selectByText(Select_Reportable, "Yes");
         minWait();
         click(btn_Popupadd);
         maxWait();
         click(btn_PopupOK);
         extentTest.log(LogStatus.PASS, "Successfully filled details with UNID: " + UDIDNo);
         maxWait();
         maxWait();
         switchBackToFirstWindow();
         waitForFrameAndSwitch(screenFrame);
         maxWait();
			click(btn_save);
			 //click(btn_save);
	            minWait();
	            driver.switchTo().defaultContent();
	            click(btn_genericYes);
	            waitForFrameAndSwitch(screenFrame);
	            maxWait();
	            continueEmbargo();
	            maxWait();
	            switchToSecondWindow();
	            click(btn_popUp_Ok);
	            switchBackToFirstWindow();
	            waitForFrameAndSwitch(screenFrame);
	            minWait();
	            String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
	        //    PropertyHandler.setPropValue(dataFilePath, "ubr", ubrNo);
	            String awb = waitForElement(txt_awbNo).getAttribute("value");
	            data.put(awbno,awb);
	       //     PropertyHandler.setPropValue(dataFilePath, awbno, awb);
	          //  String bookingStatus=PropertyHandler.getPropValue(dataFilePath, "bookingStatus");
	            if(waitForElement(info_bookingStatus).getAttribute(
	                         "value").equalsIgnoreCase(bookingStatus))
	            {
	            extentTest.log(LogStatus.PASS, "Successfully booked with: " + awb
	                         + "and UBR No: " + ubrNo);
	            }
	            else
	            {
	                  extentTest.log(LogStatus.FAIL, "VAILIDATION FAILURE: booked with: " + awb
	                                + "and UBR No: " + ubrNo+"\n"+"Booking Status doesn't match");
	            }
	            extentTest.log(LogStatus.INFO, "Booking Status: " + waitForElement(info_bookingStatus).getAttribute(
	                         "value"));

	            return this;
		}

		/**
	     * Performs a DGR booking with the given parameters fir Radioactive element B(M)
	     * 
	      * @param stockType
	     * @param awbPre
	     * @param awb
	     * @param origin
	     * @param dest
	     * @param agentCode
	     * @param scc
	     * @param commCode
	     * @param pcs
	     * @param wt
	     * @param vol
	     * @param fltNo
	     * @param fltDt
	     * @param ubr
	     * @param bookingType
	     * @return
	     * @author A-8457   Souvik
	     */
	     public CAP018 BookingwithDGRforBMRadioactive(String prefix, String awbno,
	                  String origin, String dest, String agentCode, String Product,
	                  String SCC, String pcs, String wt, String vol, String FlightNo,
	                  String fltDt, String ubr, String commCode, String CarrierCode,
	                  String P1Value, String UDIDNo , String ShippingName, String bookingStatus, HashMap<String, String> data) {

//	            CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
//	            prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//	            FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
//	            //awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//	            origin = PropertyHandler.getPropValue(dataFilePath, origin);
//	            dest = PropertyHandler.getPropValue(dataFilePath, dest);
//	            agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
//	            Product = PropertyHandler.getPropValue(dataFilePath, Product);
//	            pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//	            wt = PropertyHandler.getPropValue(dataFilePath, wt);
//	            vol = PropertyHandler.getPropValue(dataFilePath, vol);
//	            SCC = PropertyHandler.getPropValue(dataFilePath, SCC);
//	            commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
//	            fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
//	            P1Value = PropertyHandler.getPropValue(dataFilePath, P1Value);
//	            UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//	            ShippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
//	            PropertyHandler.setPropValue(dataFilePath, "carrierwithFlightno",carrierWithFlightno);
	            String carrierWithFlightno = CarrierCode + FlightNo;
	            /*enterKeys(txt_awbNoPrefix, prefix);
	            enterKeys(txt_awbNo, awbno);*/
	            click(btn_List);
	            extentTest.log(LogStatus.INFO,
	                         "Successfully entered Prefix and AWB no in CAP018 screen: "
	                                       + awbno);
	            minWait();
	            /*driver.switchTo().defaultContent();
	            if (verifyElementPresent(info_msg)
	                         && driver.findElement(info_msg).getText().trim()
	                                       .equals("AWB does not exist.Do you want to capture?")) {
	                  click(btn_genericYes);
	            }*/
	            minWait();
	            //waitForFrameAndSwitch(screenFrame);
	            enterKeys(txt_origin, origin);
	            enterKeys(txt_dest, dest);
	            enterKeysWithWait(txt_agentCode, agentCode + Keys.TAB);

	            driver.findElement(By.name("shipmentDate")).sendKeys(fltDt);
	            driver.findElement(By.name("shipmentDate")).sendKeys(Keys.TAB);
	            
	            try{
	                  String scc=PropertyHandler.getPropValue(dataFilePath, "CCShipment");
	                  if(scc.equalsIgnoreCase("yes"))
	                         check(By.xpath("//input[@name='ccShipment']"));
	                  }
	                  catch(NullPointerException ex){
	                         System.out.println();
	                  }
	            enterKeys(txt_scc, SCC);
	            enterKeys(txt_productname, Product);
	            selectByText(sel_WeightUnit, "Pound"); //case-sensitive
	            selectByText(sel_VolumeUnit, "Cubic Feet");//case-sensitive
	            enterKeys(txt_commPcs, pcs);
	            enterKeys(txt_commWt, wt);
	            enterKeys(txt_commVol, vol);
	            enterKeys(txt_commCode, commCode);
	            setDimensions(pcs, wt);
	            enterKeys(list_txt_fltOrigin, origin);
	            enterKeys(list_txt_fltDest, dest);
	            enterKeys(list_txt_fltNo, carrierWithFlightno);
	            enterKeys(list_txt_fltDt, fltDt);
	            enterKeys(list_txt_fltPcs, pcs);
	            enterKeys(list_txt_fltWt, wt);
	            scrollToView(list_dropDown_fltForce);
	     //     selectByText(list_dropDown_fltForce, "Confirm");
	            extentTest.log(LogStatus.INFO, "Successfully entered Flight Number: "
	                         + carrierWithFlightno);
	            // DGR UNID
	            click(btn_DGR);
	            maxWait();
	            // maxWait();
	            driver.switchTo().frame("popupContainerFrame");
	            minWait();
	            click(btn_PopUpicon);
	            minWait();
	            maxWait();
	            scrollToView(txt_EmergencyContactName);
	            enterKeys(txt_EmergencyContactName, "Test");
	            enterKeys(txt_EmergencyContactNumber, "123456789");
	            click(btn_iconOK);
	            maxWait();
	            scrollToView(txt_DGRUNIDnumber);
	            enterKeys(txt_DGRUNIDnumber, UDIDNo+Keys.TAB);
	            maxWait();
	            selectByText(Select_ShippingName, ShippingName);
	            minWait();
	            selectByText(By.xpath("//select[@name='rmc']"), "I");
	           // enterKeys(txt_P1Value, P1Value);
	            enterKeys(txt_NoofPackges, pcs);
	           // enterKeys(txt_NetValue, wt);
	           /* selectByText(By.xpath("//select[@name='netQuantityPerPackageUnit']"), "mL");
	            try{
	            	selectByText(By.xpath("//select[@name='netQuantityPerPackageUnit']"), PropertyHandler.getPropValue(dataFilePath, "unit"));
	            }
	            catch(Exception e)
	            {}*/
	            selectByText(Select_Reportable, "Yes");
	            minWait();
	            click(By.xpath("//a[@name='moreDetails']"));
	            minWait();
	            enterKeys(By.xpath("//input[@name='packingDimensionHeight']"), "10");
	            minWait();

	            click(btn_Popupadd);
	            click(btn_PopupOK);
	            extentTest.log(LogStatus.PASS, "Successfully filled details with UNID: " + UDIDNo);
	            maxWait();
	            maxWait();
	            switchBackToFirstWindow();
	            waitForFrameAndSwitch(screenFrame);
	            maxWait();
	            click(btn_save);
	            minWait();
	            driver.switchTo().defaultContent();
	            click(btn_genericYes);
	            waitForFrameAndSwitch(screenFrame);
	            maxWait();
	            continueEmbargo();
	            maxWait();
	            switchToSecondWindow();
	            click(btn_popUp_Ok);
	            switchBackToFirstWindow();
	            waitForFrameAndSwitch(screenFrame);
	            minWait();
	            String ubrNo = waitForElement(txt_UBRNo).getAttribute("value");
	        //    PropertyHandler.setPropValue(dataFilePath, "ubr", ubrNo);
	            String awb = waitForElement(txt_awbNo).getAttribute("value");
	            data.put(awbno,awb);
	        //   PropertyHandler.setPropValue(dataFilePath, awbno, awb);
	           // String bookingStatus=PropertyHandler.getPropValue(dataFilePath, "bookingStatus");
	            if(waitForElement(info_bookingStatus).getAttribute(
	                         "value").equalsIgnoreCase(bookingStatus))
	            {
	            extentTest.log(LogStatus.PASS, "Successfully booked with: " + awb
	                         + "and UBR No: " + ubrNo);
	            }
	            else
	            {
	                  extentTest.log(LogStatus.FAIL, "VAILIDATION FAILURE: booked with: " + awb
	                                + "and UBR No: " + ubrNo+"\n"+"Booking Status doesn't match");
	            }
	            extentTest.log(LogStatus.INFO, "Booking Status: " + waitForElement(info_bookingStatus).getAttribute(
	                         "value"));

	            return this;
	     }
	     
	     
	     public CAP018 validateEmbargo(String commCode) {
//	 		awbno = PropertyHandler.getPropValue(dataFilePath, awbno); 
//	        commCode = PropertyHandler.getPropValue(dataFilePath, commCode); 
	 		click(btn_save);
	 		// try{
	 		minWait();
	 		driver.switchTo().defaultContent();
	 		if (verifyElementPresent(By.xpath("//p[contains(text(),'UNID information for all DG SCC')]"))) {
	 			click(btn_genericYes);
	 		}
	 		waitForFrameAndSwitch(screenFrame);
//	 		maxWait();
	 			driver.switchTo().defaultContent();
	 			if (verifyElementPresent(By.xpath("//p[contains(text(),'Do you want to capture Check Sheet')]"))) {
	 				click(By.xpath("//button[text()='Yes']"));
	 				fillCheckSheet(commCode);
	 				waitForFrameAndSwitch(screenFrame);
	 				click(btn_save);
	 			} else {
	 				waitForFrameAndSwitch(screenFrame);
	 			}
	 			try {
	 				if (verifyNumberOfWindows(2)) {
	 					switchToSecondWindow();
	 					maxWait();
	 					if(verifyElementPresent(By.xpath("//input[1][@disabled]"))){
	 						extentTest.log(LogStatus.INFO, "Restriction found");
	 						if(verifyElementPresent(By.xpath("//td[contains(text(),'Restricted')]"))){
	 							extentTest.log(LogStatus.PASS, "Restriction verified");
	 							captureAndAddScreenshot();
	 						}else{
	 							extentTest.log(LogStatus.FAIL, "Restriction not found");
	 							Assert.fail();
	 						}
	 					}
	 					else if(verifyElementPresent(By.name("btContinue"))) {
	 						extentTest.log(LogStatus.FAIL, "Restriction can be overriden");
	 						click(By.name("btContinue"));
	 					} 
	 				}
	 			} catch (Exception e) {
	 			}
	 			return this;
	     }
	     
	     //Added for DG  Offload Test Case 
	  // Sharath
	 	/**
	 	 * Method to enter the flight details in flight details section in CAP018 screen
	 	 * 
	 	 * @author Sharath
	 	 * @param origin
	 	 * @param dest
	 	 * @param fltDt
	 	 * @param carrierCode
	 	 * @param FlightNo
	 	 * @param legNo
	 	 * @return
	 	 */
	 	public CAP018 enterFlightDetails(String origin, String dest, String fltDt, String carrierCode,
	 			String FlightNo, int legNo) {

	 		legNo -= 1;
	 		origin = PropertyHandler.getPropValue(dataFilePath, origin);
	 		dest = PropertyHandler.getPropValue(dataFilePath, dest);
	 		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
	 		carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
	 		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);

	 		String carrierWithFlightno = carrierCode + FlightNo;
	 		enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), origin);
	 		enterKeys(getWebElements(list_txt_fltDest).get(legNo), dest);
	 		enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
	 		enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
	 		// enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
	 		// enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
	 		// enterKeys(getWebElements(list_txt_fltVol).get(legNo),vol);
	 		// scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
	 		// selectByText(getWebElements(list_dropDown_fltForce).get(legNo),"Confirm");
	 		extentTest.log(LogStatus.INFO,
	 				"Successfully entered leg" + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);
	 		return this;
	 	}
	 	
	 	// Hamdling DG Popup - added for DG offload test case
	 	/**
		 * Method to handle the DG popup to complete the booking
		 * 
		 * @author Sharath
		 * @param emergencyName
		 * @param emergencyContact
		 * @param pcs
		 * @param DGpcs
		 * @param wt
		 * @param DGwt
		 * @param commCode
		 * @param UDIDNo
		 * @param ShippingName
		 * @param i : number of UNIDs
		 * @return
		 * @throws Exception
		 */
		public CAP018 handleDGpopUp(String emergencyName, String emergencyContact, String pcs, String DGpcs,
				String wt, String DGwt, String commCode, String UDIDNo, String ShippingName, int i) throws Exception {
			// UDIDNo = new String [i];
			// ShippingName = new String [i];
			// UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
			// ShippingName = PropertyHandler.getPropValue(dataFilePath,
			// ShippingName);
			emergencyName = PropertyHandler.getPropValue(dataFilePath, emergencyName);
			emergencyContact = PropertyHandler.getPropValue(dataFilePath, emergencyContact);
			// pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
			DGpcs = PropertyHandler.getPropValue(dataFilePath, DGpcs);
			// wt = PropertyHandler.getPropValue(dataFilePath, wt);
			DGwt = PropertyHandler.getPropValue(dataFilePath, DGwt);
			commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
			// DGR UNID
			minWait();
			click(btn_DGR);
			maxWait();
			// maxWait();
			waitForFrameAndSwitch("popupContainerFrame");
			minWait();
			// click(btn_PopUpicon);
			// minWait();
			// scrollToView(txt_EmergencyContactName);
			// enterKeys(txt_EmergencyContactName,emergencyName);
			// enterKeys(txt_EmergencyContactNumber, emergencyContact);
			// click(btn_iconOK);
			if (i == 1) {
				String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
				minWait();
				scrollToView(txt_DGRUNIDnumber);
				enterKeys(txt_DGRUNIDnumber, UdidNo + Keys.TAB);
				minWait();
				selectByText(Select_ShippingName, shippingName);
				selectByText(Select_Reportable, "Yes");
				click(btn_Popupadd);
			} else {
				for (int a = 1; a <= i; a++) {
					String b = Integer.toString(a);
					String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo + b);
					String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName + b);
					minWait();
					scrollToView(txt_DGRUNIDnumber);
					enterKeys(txt_DGRUNIDnumber, UdidNo + Keys.TAB);
					minWait();
					selectByText(Select_ShippingName, shippingName);
					// enterKeys(txt_P1Value, P1Value);
					// enterKeys(txt_NoofPackges, DGpcs);
					// enterKeys(txt_NetValue, DGwt);
					selectByText(Select_Reportable, "Yes");
					click(btn_Popupadd);
					minWait();
					
				}
			}
			extentTest.log(LogStatus.INFO, "Successfully added DG as all pack");
			captureAndAddScreenshot();
			maxWait();
			/*
			 * if (packType.equalsIgnoreCase("AllPack")) { DGPackAllPropertyFile(i,
			 * pcs); } else if (packType.equalsIgnoreCase("overpack")) {
			 * DGPackOverPropertyFile(i, pcs); }
			 */
			click(By.xpath("//input[@name='dgVerifiedFlag']"));
			click(btn_PopupOK);
			extentTest.log(LogStatus.PASS, "Successfully filled details with UNID");
			// maxWait();
			// maxWait();
			switchBackToFirstWindow();
			waitForFrameAndSwitch(screenFrame);
			minWait();
			/*
			 * click(btn_save); minWait(); driver.switchTo().defaultContent(); if
			 * (verifyElementPresent(btn_genericYes)) { click(btn_genericYes); }
			 * waitForFrameAndSwitch(screenFrame);
			 */
			return this;
		}
//Akshai
		public CAP018 handleDGpopUpNotVerified(String emergencyName, String emergencyContact, 
				String DGpcs, String DGwt ,String commCode,String UDIDNo, String ShippingName, int i) throws Exception {
//			 UDIDNo = new String [i];
//			 ShippingName = new String [i];
//			 UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//			 ShippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
//			 emergencyName = PropertyHandler.getPropValue(dataFilePath, emergencyName);
//			 emergencyContact = PropertyHandler.getPropValue(dataFilePath, emergencyContact);
//			 pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//			 DGpcs = PropertyHandler.getPropValue(dataFilePath, DGpcs);
//			 wt = PropertyHandler.getPropValue(dataFilePath, wt);
//			 DGwt = PropertyHandler.getPropValue(dataFilePath, DGwt);
//			 commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
			// DGR UNID
				click(btn_DGR);
				maxWait();
				// maxWait();
				waitForFrameAndSwitch("popupContainerFrame");
				minWait();
//				click(btn_PopUpicon);
//				minWait();
//				scrollToView(txt_EmergencyContactName);
//				enterKeys(txt_EmergencyContactName,emergencyName);
//				enterKeys(txt_EmergencyContactNumber, emergencyContact);
//				click(btn_iconOK);
				if(i==1){
//					String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//					String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
					minWait();
					scrollToView(txt_DGRUNIDnumber);
					enterKeys(txt_DGRUNIDnumber, UDIDNo + Keys.TAB);
					minWait();
					selectByText(Select_ShippingName, ShippingName);
					selectByText(Select_Reportable, "Yes");
					click(btn_Popupadd);
				}else{
				for(int a=1;a<=i;a++){
					String b = Integer.toString(a);
//					String UdidNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo+b);
//					String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName+b);
					minWait();
					scrollToView(txt_DGRUNIDnumber);
				enterKeys(txt_DGRUNIDnumber, UDIDNo + Keys.TAB);
				minWait();
				selectByText(Select_ShippingName, ShippingName);
//				enterKeys(txt_P1Value, P1Value);
//				enterKeys(txt_NoofPackges, DGpcs);
//				enterKeys(txt_NetValue, DGwt);
				selectByText(Select_Reportable, "Yes");
				click(btn_Popupadd);
				}
				}
				//click(By.name("dgVerifiedFlag"));
				minWait();
				extentTest.log(LogStatus.INFO, "Successfully added DG as all pack");
				captureAndAddScreenshot();
				maxWait();
				/*if (packType.equalsIgnoreCase("AllPack")) {
					DGPackAllPropertyFile(i, pcs);
				} else if (packType.equalsIgnoreCase("overpack")) {
					DGPackOverPropertyFile(i, pcs);
				}*/
				click(btn_PopupOK);
				extentTest.log(LogStatus.PASS, "Successfully filled details with UNID");
//				maxWait();
//				maxWait();
				switchBackToFirstWindow();
				waitForFrameAndSwitch(screenFrame);
				minWait();             
				/*click(btn_save);
				minWait();
				driver.switchTo().defaultContent();
				if (verifyElementPresent(btn_genericYes)) {
					click(btn_genericYes);
				}
				waitForFrameAndSwitch(screenFrame);*/
				return this;
			}	
		/**
		 * Saves details of CAP018 and verifies UBR and makes a copy of UBR in
		 * properties file and check Booking Status
		 * 
		 * @param awb
		 * @return
		 * @author Akshai
		 * @date 03-12-2019
		 */
		public CAP018 saveInCAP018AndVerifyEmbargoRestrictionRelatedtoDG() {
			click(btn_save);
			maxWait();
			maxWait();

			// driver.switchTo().defaultContent();
			switchToSecondWindow();
			String message = driver.findElement(By.xpath("(/td[@class='iCargoTableDataTd'][3]")).getText().trim();
			if (message.contains("Division 2.2 non-flammable gases with a subsidiary hazard of 5.1 will not be accepted")) {
				extentTest.log(LogStatus.PASS, "Cannot Do booking due to reason :  " + message);
			} else {
				extentTest.log(LogStatus.FAIL, "Booking Done");
				Assert.fail("Should Not have done the booking with Embargo and restriction related to DG Subsidary Level");
			}

			click(By.xpath("//input[@name='btClose']"));
			switchBackToFirstWindow();
			waitForFrameAndSwitch(screenFrame);

			return this;
		}

	//zimmy trial
		
		public CAP018 enterFlightforMultilegFlt2(String OriginOfNewleg, String destOfNewleg, String pcs, String wt,
				String vol, String fltDt, String carrierWithFlightno, String forceStatus, int legNo) {
			legNo -= 1;
			
			enterKeys(getWebElements(list_txt_fltOrigin).get(legNo), OriginOfNewleg);
			enterKeys(getWebElements(list_txt_fltDest).get(legNo), destOfNewleg);
			enterKeys(getWebElements(list_txt_fltNo).get(legNo), carrierWithFlightno);
			enterKeys(getWebElements(list_txt_fltDt).get(legNo), fltDt + Keys.TAB);
			enterKeys(getWebElements(list_txt_fltPcs).get(legNo), pcs);
      		enterKeys(getWebElements(list_txt_fltWt).get(legNo), wt);
			 enterKeys(getWebElements(list_txt_fltVol).get(legNo),vol);
			//scrollToView(getWebElements(list_dropDown_fltForce).get(legNo));
			// selectByText(getWebElements(list_dropDown_fltForce).get(legNo),
			// forceStatus);
			extentTest.log(LogStatus.INFO,
					"Successfully entered leg" + (legNo + 1) + " details with Flight Number: " + carrierWithFlightno);

			return this;
		}
		//overloaded for split booking
		public CAP018 MultilegDGSplitBookingfor4Legs(String prefix, String awbno, String origin, String dest,
				String agentCode, String Product, String scc, String pcs, String wt, String vol, String intermediate1, String intermediate2, 
				String ubr, String commCode, String CarrierCode,
				String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flightNo1, String fltDt1, String flightNo2, String fltDt2, 
				String flightNo3, String fltDt3, String flightNo4, String fltDt4, String pcs1, String wt1, String pcs2, String wt2, String ULDType, String ULDwt) {

//			CarrierCode = PropertyHandler.getPropValue(dataFilePath, CarrierCode);
//			prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//			flightNo1 = PropertyHandler.getPropValue(dataFilePath, flightNo1);
//			// awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//			origin = PropertyHandler.getPropValue(dataFilePath, origin);
//			dest = PropertyHandler.getPropValue(dataFilePath, dest);
//			agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
//			Product = PropertyHandler.getPropValue(dataFilePath, Product);
//			pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//			wt = PropertyHandler.getPropValue(dataFilePath, wt);
//			vol = PropertyHandler.getPropValue(dataFilePath, vol);
//			// scc = PropertyHandler.getPropValue(dataFilePath, scc);
//			commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
//			fltDt1 = PropertyHandler.getPropValue(dataFilePath, fltDt1);
//			UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//			// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
//			// bookingStatus);
//			forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
//			fltDt2 = PropertyHandler.getPropValue(dataFilePath, fltDt2);
//			intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
//			intermediate2 = PropertyHandler.getPropValue(dataFilePath, intermediate2);
//			flightNo2 = PropertyHandler.getPropValue(dataFilePath, flightNo2);
//			flightNo3 = PropertyHandler.getPropValue(dataFilePath, flightNo3);
//			fltDt3 = PropertyHandler.getPropValue(dataFilePath, fltDt3);
//			
//			flightNo4 = PropertyHandler.getPropValue(dataFilePath, flightNo4);
//			fltDt4 = PropertyHandler.getPropValue(dataFilePath, fltDt4);
//
//			pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
//			wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);
//			
//			pcs2 = PropertyHandler.getPropValue(dataFilePath, pcs2);
//			wt2 = PropertyHandler.getPropValue(dataFilePath, wt2);

			String carrierWithFlightno1 = CarrierCode + flightNo1;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
			String carrierWithFlightno2 = CarrierCode + flightNo2;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);
			String carrierWithFlightno3 = CarrierCode + flightNo3;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno3", carrierWithFlightno3);
			
			String carrierWithFlightno4 = CarrierCode + flightNo4;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno4", carrierWithFlightno4);

			enterInitialBookingDetails(origin, dest, agentCode, Product, pcs, wt, "", fltDt1, ULDType, ULDwt, commCode);
			enterFlightforMultilegFlt2(origin, intermediate1, pcs1, wt1, "", fltDt1, carrierWithFlightno1, forceStatus, 1);
			enterFlightforMultilegFlt2(intermediate1, dest, pcs1, wt1, "", fltDt2, carrierWithFlightno2, forceStatus, 2);
			enterFlightforMultilegFlt2(origin, intermediate2, pcs2, wt2, "", fltDt3, carrierWithFlightno3, forceStatus, 3);
			enterFlightforMultilegFlt2(intermediate2, dest, pcs2, wt2, "", fltDt4, carrierWithFlightno4, forceStatus, 4);
			//saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

			return this;
		}
		
		//overloaded to comply with property file and save later
		

		public CAP018 MultilegSplitBookingfor3Legs1(String prefix, String awbNo, String origin, String dest,
				String agentCode, String Product, String scc, String pcs, String wt, String vol, String flightNo1,
				String fltDt1, String intermediate1, String intermediate2, String ubr, String commCode, String carrierCode,
				String UDIDNo, String forceStatus, String NoOfLegs, String bookingStatus, String flt2Dt, String flightNo2,
				String flightNo3, String flt3Dt, String ULDType, String ULDwt, String pcs1, String wt1) {

//			carrierCode = PropertyHandler.getPropValue(dataFilePath, carrierCode);
//			prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
//			flightNo1 = PropertyHandler.getPropValue(dataFilePath, flightNo1);
//			// awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
//			origin = PropertyHandler.getPropValue(dataFilePath, origin);
//			dest = PropertyHandler.getPropValue(dataFilePath, dest);
//			agentCode = PropertyHandler.getPropValue(dataFilePath, agentCode);
//			Product = PropertyHandler.getPropValue(dataFilePath, Product);
//			pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//			wt = PropertyHandler.getPropValue(dataFilePath, wt);
//			vol = PropertyHandler.getPropValue(dataFilePath, vol);
//			// scc = PropertyHandler.getPropValue(dataFilePath, scc);
//			commCode = PropertyHandler.getPropValue(dataFilePath, commCode);
//			fltDt1 = PropertyHandler.getPropValue(dataFilePath, fltDt1);
//			UDIDNo = PropertyHandler.getPropValue(dataFilePath, UDIDNo);
//			// bookingStatus= PropertyHandler.getPropValue(dataFilePath,
//			// bookingStatus);
//			forceStatus = PropertyHandler.getPropValue(dataFilePath, forceStatus);
//			flt2Dt = PropertyHandler.getPropValue(dataFilePath, flt2Dt);
//			intermediate1 = PropertyHandler.getPropValue(dataFilePath, intermediate1);
//			intermediate2 = PropertyHandler.getPropValue(dataFilePath, intermediate2);
//			flightNo2 = PropertyHandler.getPropValue(dataFilePath, flightNo2);
//			flightNo3 = PropertyHandler.getPropValue(dataFilePath, flightNo3);
//			flt3Dt = PropertyHandler.getPropValue(dataFilePath, flt3Dt);
//			ULDType = PropertyHandler.getPropValue(dataFilePath, ULDType);
//			ULDwt = PropertyHandler.getPropValue(dataFilePath, ULDwt);
//			pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
//			wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);

			String carrierWithFlightno1 = carrierCode + flightNo1;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno1", carrierWithFlightno1);
//			String carrierWithFlightno2 = carrierCode + flightNo2;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno2", carrierWithFlightno2);
			String carrierWithFlightno3 = carrierCode + flightNo3;
//			PropertyHandler.setPropValue(dataFilePath, "carrierWithFlightno3", carrierWithFlightno3);

			enterInitialBookingDetails(origin, dest, agentCode, Product, pcs, wt, "", fltDt1, ULDType, ULDwt, commCode);
			enterFlightforMultilegFlt2(origin, intermediate1, pcs1, wt1, "", fltDt1, carrierWithFlightno1, forceStatus, 1);
//			enterFlightforMultilegFlt2(origin, intermediate2, pcs1, wt1, "", flt2Dt, carrierWithFlightno2, forceStatus, 2);
			enterFlightforMultilegFlt2(intermediate1, dest, pcs, wt, "", flt3Dt, carrierWithFlightno3, forceStatus, 3);
			//saveInCAP018AndVerifyUBRStatus(awbno, bookingStatus);

			return this;
		}
		
		
}