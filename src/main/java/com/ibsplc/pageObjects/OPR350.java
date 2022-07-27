package com.ibsplc.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.ibsplc.common.BasePage;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Screen OPR350 - Capture DG Details Screen
 * @author Souvik
 * A-8457
 * on 24th March-2020
 */
public class OPR350 extends BasePage {

	public static String FRAME = "iCargoContentFrameOPR350";
	public static String screenFrame = "iCargoContentFrameOPR350";
	private static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"OPR_FLT.properties");
	private static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"Generic.properties");
	WebDriver driver;
	String dataFileName;
	ExtentTest test;
	private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");

	private By txtbx_carriercode, txtbx_ATDdate, txtbx_flightNumber, txtbx_date, btn_List;
	private By sel_blockedFor, txt_remarks, btn_blk_details, chcbx_Bulk, btn_manifst, btn_finalize, btn_close, yesBtn,
			btn_editIcon, btn_switchRole, btn_station, btn_ok, btn_flightfinalize, txt_statusfinalized, btn_assignhere,
			btn_Manifest, btn_PopOk, btn_Popup_Save,
			// A-7290
			Select_Rolegroup, btn_Bulk, Icon_bulk,
			// Zimmy
			txt_DGRUNIDnumber;


	public OPR350(WebDriver driver, String dataFileName, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
		initElements();
		this.dataFilePath = this.dataFilePath + dataFileName;
		this.dataFileName = dataFileName;
	}

	/**
	 * Method to initialize page elements
	 */
	private void initElements() {
		yesBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
		
	}

	/**
	 
	 * @author Souvik
	 * A-8457
	 * Capture DG details
	 */
	public OPR350 CaptureDGDetails(String awbno, String Unid, String pcs, String wt, String PIValue, String TIValue) {
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		maxWait();
		enterKeys(By.name("masterDocumentNumber"), awbno);
		click(By.name("btList"));
		maxWait();
		click(By.xpath("//i[@class='icon edit']"));
		minWait();
		enterKeys(By.name("firstEmergencyContactName"),"Sans");
		enterKeys(By.name("firstEmergencyContactNumber"),"74835649");
		enterKeys(By.name("shipperpobox"), "245245");
		enterKeys(By.name("consigneepobox"), "245245");
		enterKeys(By.name("shipmentlimitation"), "DG");
		enterKeys(By.name("shipmenttype"), "DG");
		enterKeys(By.name("additionalhandlingInformation"), "TEST");
		enterKeys(By.name("warning"), "TEST");
		enterKeys(By.name("declaration"), "TEST");
		enterKeys(By.name("nameortitleofsignatory"), "TEST");
		enterKeys(By.name("place"), "Dallas");
		enterKeys(By.name("signature"),"TEST");
		enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
		enterKeys(By.name("signatoryTime"), "0000");
		minWait();
		scrollToView(By.name("btnShipperReferenceOk"));
		click(By.xpath("//button[@name='btnShipperReferenceOk']"));
		minWait();
		CaptureUnidDetails(Unid, pcs, wt, PIValue, TIValue);
		minWait();
		minWait();
		if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
		click(By.name("dgVerifiedFlag"));
		minWait();
		click(By.xpath("//button[@name='btnSave']"));
		maxWait();
		if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
			String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
			if(status.equalsIgnoreCase("No package more than 3.0 TI is Allowed")){
				test.log(LogStatus.PASS, "DG acceptance has been stopped successfully :"+status);
			}
		}
		test.log(LogStatus.PASS, "DG Details has been succesfully captured");
		return this;		
	}

	/**
	 *
	 * @author Souvik
	 * A-8457
	 * Capture DG details
	 */
	public OPR350 CaptureDGDetailsPropertyFile(String awbno, String emergencyName, String emergencyContact) {
	/*	awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		emergencyName = PropertyHandler.getPropValue(dataFilePath, emergencyName);
		emergencyContact = PropertyHandler.getPropValue(dataFilePath, emergencyContact);*/
//		maxWait();
		enterKeys(By.name("masterDocumentNumber"), awbno);
		click(By.name("btList"));
		maxWait();
//		minWait();
		click(By.xpath("//i[@class='icon edit']"));
//		maxWait();
		enterKeys(By.name("firstEmergencyContactName"),emergencyName);
		enterKeys(By.name("firstEmergencyContactNumber"),emergencyContact);
		enterKeys(By.name("shipperpobox"), "245245");
		enterKeys(By.name("consigneepobox"), "245245");
		enterKeys(By.name("shipmentlimitation"), "DG");
		enterKeys(By.name("shipmenttype"), "DG");
		enterKeys(By.name("additionalhandlingInformation"), "TEST");
		enterKeys(By.name("warning"), "TEST");
		enterKeys(By.name("declaration"), "TEST");
		enterKeys(By.name("nameortitleofsignatory"), "TEST");
		enterKeys(By.name("place"), "Dallas");
		enterKeys(By.name("signature"),"TEST");
		enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
		enterKeys(By.name("signatoryTime"), "0000");
		minWait();
		scrollToView(By.name("btnShipperReferenceOk"));
		click(By.xpath("//button[@name='btnShipperReferenceOk']"));
		minWait();
//		CaptureUnidDetails(Unid, pcs, wt, PIValue, TIValue);
		minWait();
//		click(By.xpath("//button[@name='btnSave']"));
//		maxWait();
//		if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
//			String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
//			if(status.equalsIgnoreCase("No package more than 3.0 TI is Allowed")){
//				test.log(LogStatus.PASS, "DG acceptance has been stopped successfully :"+status);
//			}
//		}
		test.log(LogStatus.PASS, "DG Details has been succesfully captured");
		captureAndAddScreenshot();
		return this;		
	}
	
	
	//overrided for accepting UNID details also 
	
	public OPR350 CaptureDGDetailsPropertyFile(String awbno, String emergencyName, String emergencyContact, String Unid, String pcs, String wt, String PIValue, String TIValue) {
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		emergencyName = PropertyHandler.getPropValue(dataFilePath, emergencyName);
		emergencyContact = PropertyHandler.getPropValue(dataFilePath, emergencyContact);
		maxWait();
		enterKeys(By.name("masterDocumentNumber"), awbno);
		click(By.name("btList"));
		maxWait();
		minWait();
		click(By.xpath("//i[@class='icon edit']"));
		maxWait();
		enterKeys(By.name("firstEmergencyContactName"),emergencyName);
		enterKeys(By.name("firstEmergencyContactNumber"),emergencyContact);
		enterKeys(By.name("shipperpobox"), "245245");
		enterKeys(By.name("consigneepobox"), "245245");
		enterKeys(By.name("shipmentlimitation"), "DG");
		enterKeys(By.name("shipmenttype"), "DG");
		enterKeys(By.name("additionalhandlingInformation"), "TEST");
		enterKeys(By.name("warning"), "TEST");
		enterKeys(By.name("declaration"), "TEST");
		enterKeys(By.name("nameortitleofsignatory"), "TEST");
		enterKeys(By.name("place"), "Dallas");
		enterKeys(By.name("signature"),"TEST");
		enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
		enterKeys(By.name("signatoryTime"), "0000");
		minWait();
		scrollToView(By.name("btnShipperReferenceOk"));
		click(By.xpath("//button[@name='btnShipperReferenceOk']"));
		minWait();
		CaptureUnidDetailsForPropertyFile(awbno, Unid, pcs, wt, PIValue, TIValue, true);
		minWait();
		click(By.xpath("//button[@name='btnSave']"));
		maxWait();
		if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
			String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
			if(status.equalsIgnoreCase("No package more than 3.0 TI is Allowed")){
				test.log(LogStatus.PASS, "DG acceptance has been stopped successfully :"+status);
			}
		}
		test.log(LogStatus.PASS, "DG Details has been succesfully captured");
		captureAndAddScreenshot();
		return this;		
	}
	
	/**
	 
	 * @author Sharath
	 * A-8457
	 * Capture DG details
	 */
	public OPR350 CaptureDGDetailsOverPack(String awbno, String unidNo[], String DGpcs, String DGwt, String piValue[]) {
//		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		maxWait();
		enterKeys(By.name("masterDocumentNumber"), awbno);
		click(By.name("btList"));
		maxWait();
		minWait();
		click(By.xpath("//i[@class='icon edit']"));
		minWait();
		enterKeys(By.name("firstEmergencyContactName"),"testuser");
		enterKeys(By.name("firstEmergencyContactNumber"),"123456");
		enterKeys(By.name("shipperpobox"), "245245");
		enterKeys(By.name("consigneepobox"), "245245");
		enterKeys(By.name("shipmentlimitation"), "DG");
		enterKeys(By.name("shipmenttype"), "DG");
		enterKeys(By.name("additionalhandlingInformation"), "TEST");
		enterKeys(By.name("warning"), "TEST");
		enterKeys(By.name("declaration"), "TEST");
		enterKeys(By.name("nameortitleofsignatory"), "TEST");
		enterKeys(By.name("place"), "Dallas");
		enterKeys(By.name("signature"),"TEST");
		enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
		enterKeys(By.name("signatoryTime"), "0000");
		minWait();
		scrollToView(By.name("btnShipperReferenceOk"));
		click(By.xpath("//button[@name='btnShipperReferenceOk']"));
		minWait();
		
		for(int i =0;i<4;i++){
			
			CaptureUnidDetailsForPropertyFile("",unidNo[i], DGpcs, DGwt, piValue[i], "",false);
		}
//		CaptureUnidDetailsForPropertyFile("",unidNo[0], "DGpcs", "DGwt", "PIValue1", "",false);
//		CaptureUnidDetailsForPropertyFile("","UNIDNo2", "DGpcs", "DGwt", "PIValue2", "",false);
//		CaptureUnidDetailsForPropertyFile("","UNIDNo3", "DGpcs", "DGwt", "PIValue3", "",false);
//		CaptureUnidDetailsForPropertyFile("","UNIDNo4", "DGpcs", "DGwt", "PIValue4", "",false);
		minWait();
		performAllPack(unidNo[0], unidNo[3], DGpcs);
		performAllPack(unidNo[1], unidNo[2], DGpcs);
		minWait();
		
		performOverPack(unidNo[0], unidNo[1], unidNo[2], unidNo[3], DGpcs);
	
		minWait();
		if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
			click(By.name("dgVerifiedFlag"));
			minWait();
			test.log(LogStatus.INFO, "DG Verified flag checked");
			captureAndAddScreenshot();
		click(By.xpath("//button[@name='btnSave']"));
		maxWait();
		if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
			String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
			if(status.equalsIgnoreCase("No package more than 3.0 TI is Allowed")){
				test.log(LogStatus.PASS, "DG acceptance has been stopped successfully :"+status);
			}
		}
		test.log(LogStatus.PASS, "DG Details has been succesfully captured");
		return this;		
	}
	
//Sharath			
	public OPR350 CaptureUnidDetails(String Unid, String pcs, String wt, String PIValue, String TIValue) {
		scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
		String prefix = "(//td[contains(text(),'";
		String suffix = "')]/following-sibling::td)[last()]";
		click(By.xpath(prefix + Unid + suffix));
		suffix = "')]/following-sibling::td)[last()]//i";
		click(By.xpath(prefix + Unid + suffix));
		click(By.name("btEdit"));
		minWait();
		waitForFrameAndSwitch("popupContainerFrame");
		enterKeys(By.name("packingInstruction"), PIValue);
		if(!TIValue.equals("")){
			enterKeys(By.name("transportIndex"), TIValue);
			selectByText(By.name("rmc"),"I");
			enterKeys(By.name("packingDimensionHeight"), "10");
		}
		enterKeys(By.name("numberOfPackages"), pcs);
		int p = Integer.parseInt(pcs);
		int w = Integer.parseInt(wt);
		if (!TIValue.equals("")) {
		} else {
			enterKeys(By.name("netQuantityPerPackage"), Integer.toString(w / p));
		}
		selectByText(By.name("reportableQuantity"), "Yes");
		click(By.name("BtnUpdate"));
		
		driver.switchTo().defaultContent();
		waitForFrameAndSwitch(screenFrame);
//		click(By.xpath("//button[@name='btnSave']"));
		maxWait();
		test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
		return this;
	}
	
	//Sharath			
		public OPR350 CaptureUnidDetailsForPropertyFile(String awbno, String Unid[], String DGpcs, String DGwt, String piValue[] , String TIValue, boolean listingReqd) {
		//	Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
		//	pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		//	wt = PropertyHandler.getPropValue(dataFilePath, wt);
		//	PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
		//	TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
			if(listingReqd){
			//	awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
				maxWait();
				enterKeys(By.name("masterDocumentNumber"), awbno);
				click(By.name("btList"));
				maxWait();
			}
			for(int i=0; i<2; i++){
			maxWait();
//			scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
			
			scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
			String prefix = "(//td[contains(text(),'";
			String suffix = "')]/following-sibling::td)[last()]";
		    click(By.xpath(prefix + Unid[i] + suffix));
			suffix = "')]/following-sibling::td)[last()]//i";
			click(By.xpath(prefix + Unid[i] + suffix));
			click(By.xpath("(//button[@name='btEdit'])[last()]"));
			minWait();
			waitForFrameAndSwitch("popupContainerFrame");
			enterKeys(By.name("packingInstruction"), piValue[i]);
		    enterKeys(By.name("numberOfPackages"), DGpcs);
			int p = Integer.parseInt(DGpcs);
			int w = Integer.parseInt(DGwt);
        	enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
			selectByText(By.name("netQuantityPerPackageUnit"), "KG");
			selectByText(By.name("reportableQuantity"), "Yes");
			minWait();
			click(By.name("BtnUpdate"));
			driver.switchTo().defaultContent();
			waitForFrameAndSwitch(screenFrame);
			minWait();
			}
			minWait();
			test.log(LogStatus.INFO, "Details have been filled");
//			captureAndAddScreenshot();
			minWait();
			if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
			click(By.name("dgVerifiedFlag"));
			minWait();
			test.log(LogStatus.INFO, "DG Verified flag checked");
			captureAndAddScreenshot();
			click(By.xpath("//button[@name='btnSave']"));
			maxWait();
			if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
				click(By.xpath("//img[contains(@src,'close')]"));
			}
//			waitForFrameAndSwitch("popupContainerFrame");
			test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
			captureAndAddScreenshot();
			return this;
		}
			
		//Sharath			
				public OPR350 CaptureUnidDetailsForPropertyFile(String awbno, String Unid[], String DGpcs, String DGwt, String piValue , String TIValue, boolean listingReqd) {
				//	Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
				//	pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
				//	wt = PropertyHandler.getPropValue(dataFilePath, wt);
				//	PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
				//	TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
					if(listingReqd){
					//	awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
						maxWait();
						enterKeys(By.name("masterDocumentNumber"), awbno);
						click(By.name("btList"));
						maxWait();
					}
					for(int i=0; i<2; i++){
					maxWait();
//					scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
					
					scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
					String prefix = "(//td[contains(text(),'";
					String suffix = "')]/following-sibling::td)[last()]";
				    click(By.xpath(prefix + Unid[i] + suffix));
					suffix = "')]/following-sibling::td)[last()]//i";
					click(By.xpath(prefix + Unid[i] + suffix));
					click(By.xpath("(//button[@name='btEdit'])[last()]"));
					minWait();
					waitForFrameAndSwitch("popupContainerFrame");
					enterKeys(By.name("packingInstruction"), piValue);
				    enterKeys(By.name("numberOfPackages"), DGpcs);
					int p = Integer.parseInt(DGpcs);
					int w = Integer.parseInt(DGwt);
		        	enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
					selectByText(By.name("netQuantityPerPackageUnit"), "KG");
					selectByText(By.name("reportableQuantity"), "Yes");
					minWait();
					click(By.name("BtnUpdate"));
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					minWait();
					}
					minWait();
					test.log(LogStatus.INFO, "Details have been filled");
//					captureAndAddScreenshot();
					minWait();
					if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
					click(By.name("dgVerifiedFlag"));
					minWait();
					test.log(LogStatus.INFO, "DG Verified flag checked");
					captureAndAddScreenshot();
					click(By.xpath("//button[@name='btnSave']"));
					maxWait();
					if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
						click(By.xpath("//img[contains(@src,'close')]"));
					}
//					waitForFrameAndSwitch("popupContainerFrame");
					test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
					captureAndAddScreenshot();
					return this;
				}
		public OPR350 CaptureUnidDetailsForPropertyFile(String awbno, String Unid, String DGpcs, String DGwt, String piValue , String TIValue, boolean listingReqd) {
			//	Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
			//	pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
			//	wt = PropertyHandler.getPropValue(dataFilePath, wt);
			//	PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
			//	TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
				if(listingReqd){
			//		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
//					maxWait();
					enterKeys(By.name("masterDocumentNumber"), awbno);
					click(By.name("btList"));
//					maxWait();
				}
				maxWait();
//				scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
				scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
				String prefix = "(//td[contains(text(),'";
				String suffix = "')]/following-sibling::td)[last()]";
				click(By.xpath(prefix + Unid + suffix));
				suffix = "')]/following-sibling::td)[last()]//i";
				click(By.xpath(prefix + Unid + suffix));
				click(By.xpath("(//button[@name='btEdit'])[last()]"));
//				minWait();
				waitForFrameAndSwitch("popupContainerFrame");
				for(int i=0;i<=1;i++){
				enterKeys(By.name("packingInstruction"), piValue);
				}
				enterKeys(By.name("numberOfPackages"), DGpcs);
				int p = Integer.parseInt(DGpcs);
				int w = Integer.parseInt(DGwt);
	        	enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
				selectByText(By.name("netQuantityPerPackageUnit"), "KG");
				selectByText(By.name("reportableQuantity"), "Yes");
				minWait();
				click(By.name("BtnUpdate"));
				driver.switchTo().defaultContent();
				waitForFrameAndSwitch(screenFrame);
				minWait();
				test.log(LogStatus.INFO, "Details have been filled");
//				captureAndAddScreenshot();
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
				click(By.name("dgVerifiedFlag"));
				minWait();
				test.log(LogStatus.INFO, "DG Verified flag checked");
				captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
					click(By.xpath("//img[contains(@src,'close')]"));
				}
//				waitForFrameAndSwitch("popupContainerFrame");
				test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
				captureAndAddScreenshot();
				return this;
			}
		//Sharath			
				public OPR350 performAllPack(String Unid1, String Unid2, String pcs) {
//					Unid1 = PropertyHandler.getPropValue(dataFilePath, Unid1);
//					Unid2 = PropertyHandler.getPropValue(dataFilePath, Unid2);
//					pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
					maxWait();
					scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
					click(By.xpath("//td[contains(text(),'"+Unid1+"')]/parent::tr//input"));
					click(By.xpath("//td[contains(text(),'"+Unid2+"')]/parent::tr//input"));
					click(By.name("btAllPacked"));
					minWait();
					waitForFrameAndSwitch("popupContainerFrame");
					maxWait();
					enterKeys(By.name("numberOfOAPackages"), pcs);
					click(By.name("btOk"));
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					minWait();
					return this;
				}
			
				//Sharath			
				public OPR350 performOverPack(String Unid1, String Unid2, String Unid3, String Unid4, String pcs) {
				//	Unid1 = PropertyHandler.getPropValue(dataFilePath, Unid1);
				//	Unid2 = PropertyHandler.getPropValue(dataFilePath, Unid2);
				//	Unid3 = PropertyHandler.getPropValue(dataFilePath, Unid3);
				//	Unid4 = PropertyHandler.getPropValue(dataFilePath, Unid4);
				//	pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
					maxWait();
					scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
					click(By.xpath("//td[contains(text(),'"+Unid1+"')]/parent::tr//input"));
					click(By.xpath("//td[contains(text(),'"+Unid2+"')]/parent::tr//input"));
					click(By.xpath("//td[contains(text(),'"+Unid3+"')]/parent::tr//input"));
					click(By.xpath("//td[contains(text(),'"+Unid4+"')]/parent::tr//input"));
					click(By.name("btOverPacked"));
					minWait();
					waitForFrameAndSwitch("popupContainerFrame");
					maxWait();
					enterKeys(By.name("numberOfOAPackages"), pcs);
					click(By.name("btOk"));
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					minWait();
					return this;
				}
				//Perform Overpack for 2
				public OPR350 performOverPackfor2Unid(String Unid1, String Unid2, String pcs) {
//					Unid1 = PropertyHandler.getPropValue(dataFilePath, Unid1);
//					Unid2 = PropertyHandler.getPropValue(dataFilePath, Unid2);
					/*Unid3 = PropertyHandler.getPropValue(dataFilePath, Unid3);
					Unid4 = PropertyHandler.getPropValue(dataFilePath, Unid4);*/
//					pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
					maxWait();
					scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
					click(By.xpath("//td[contains(text(),'"+Unid1+"')]/parent::tr//input"));
					click(By.xpath("//td[contains(text(),'"+Unid2+"')]/parent::tr//input"));
					/*click(By.xpath("//td[contains(text(),'"+Unid3+"')]/parent::tr//input"));
					click(By.xpath("//td[contains(text(),'"+Unid4+"')]/parent::tr//input"));*/
					click(By.name("btOverPacked"));
					minWait();
					waitForFrameAndSwitch("popupContainerFrame");
					maxWait();
					enterKeys(By.name("numberOfOAPackages"), pcs);
					click(By.name("btOk"));
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					minWait();
					return this;
				}
	public HomePage close() {

		click(By.xpath("//button[@name='btnClose']"));
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}
		return new HomePage(driver, dataFileName, test);
	}
	
	public OPR350 save(){
		click(By.xpath("//button[@name='btnSave']"));
		maxWait();
		return this;
	}
	
	
	//Sharath			
			public OPR350 CaptureUnidDetailsWithSpecialAuth(String awbno, String Unid, String pcs, String wt, String PIValue, String TIValue,String authCode, boolean listingReqd) {
//				Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
//				pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//				wt = PropertyHandler.getPropValue(dataFilePath, wt);
//				PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
//				TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
//				authCode = PropertyHandler.getPropValue(dataFilePath, authCode);
				if(listingReqd){
					awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
					maxWait();
					enterKeys(By.name("masterDocumentNumber"), awbno);
					click(By.name("btList"));
					maxWait();
				}
				maxWait();
//				scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
				scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
				String prefix = "(//td[contains(text(),'";
				String suffix = "')]/following-sibling::td)[last()]";
				click(By.xpath(prefix + Unid + suffix));
				suffix = "')]/following-sibling::td)[last()]//i";
				click(By.xpath(prefix + Unid + suffix));
				click(By.xpath("(//button[@name='btEdit'])[last()]"));
				minWait();
				waitForFrameAndSwitch("popupContainerFrame");
				enterKeys(By.name("packingInstruction"), PIValue);
				enterKeys(By.name("numberOfPackages"), pcs);
				int p = Integer.parseInt(pcs);
				int w = Integer.parseInt(wt);
				enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
				selectByText(By.name("netQuantityPerPackageUnit"), "KG");
				selectByText(By.name("reportableQuantity"), "Yes");
				minWait();
				click(By.name("BtnUpdate"));
				minWait();
				if(verifyElementPresent(By.xpath("//td[contains(@class,'error-msg')]"))){
					String errormsg = getText_JS(By.xpath("//td[contains(@class,'error-msg')]"));
					test.log(LogStatus.INFO, errormsg);
					captureAndAddScreenshot();
					test.log(LogStatus.INFO, "Providing special autorization");
					click(By.xpath("//img[contains(@alt,'Close')]"));
					minWait();
					click(By.name("specialAuthorizationFlag"));
					enterKeys(By.name("authorization"),authCode);
					captureAndAddScreenshot();
					click(By.name("BtnUpdate"));
					minWait();
				}
				driver.switchTo().defaultContent();
				waitForFrameAndSwitch(screenFrame);
				minWait();
				test.log(LogStatus.INFO, "Details have been filled");
//				captureAndAddScreenshot();
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
				click(By.name("dgVerifiedFlag"));
				minWait();
				test.log(LogStatus.INFO, "DG Verified flag checked");
				captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
					click(By.xpath("//img[contains(@src,'close')]"));
				}
//				waitForFrameAndSwitch("popupContainerFrame");
				test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
				captureAndAddScreenshot();
				return this;
			}
			
			//Sharath			
			public OPR350 CaptureUnidDetailsWithHeightCapture(String awbno, String Unid, String pcs, String wt, String PIValue, String TIValue, String height1,String height2, boolean listingReqd) {
//				Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
//				pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//				wt = PropertyHandler.getPropValue(dataFilePath, wt);
//				PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
//				TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
//				height1 = PropertyHandler.getPropValue(dataFilePath, height1);
//				height2 = PropertyHandler.getPropValue(dataFilePath, height2);
				if(listingReqd){
					awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
					maxWait();
					enterKeys(By.name("masterDocumentNumber"), awbno);
					click(By.name("btList"));
					maxWait();
				}
				maxWait();
//				scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
				scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
				String prefix = "(//td[contains(text(),'";
				String suffix = "')]/following-sibling::td)[last()]";
				click(By.xpath(prefix + Unid + suffix));
				suffix = "')]/following-sibling::td)[last()]//i";
				click(By.xpath(prefix + Unid + suffix));
				click(By.xpath("(//button[@name='btEdit'])[last()]"));
				minWait();
				waitForFrameAndSwitch("popupContainerFrame");
				enterKeys(By.name("packingInstruction"), PIValue);
				enterKeys(By.name("transportIndex"),TIValue);
				selectByText(By.name("rmc"), "I");
				enterKeys(By.name("numberOfPackages"), pcs);
				int p = Integer.parseInt(pcs);
				int w = Integer.parseInt(wt);
				try{
					driver.findElement(By.name("netQuantityPerPackage")).click();
					driver.findElement(By.name("netQuantityPerPackage")).sendKeys(Integer.toString(w / p));
				}catch(Exception e){
					test.log(LogStatus.INFO, "Quantity field is not clicked for this UNID");
				}
				selectByText(By.name("reportableQuantity"), "Yes");
				enterKeys(By.name("packingDimensionHeight"), height1);
				selectByText(By.name("packingDimensionUnit"), "cm");
				minWait();
				click(By.name("BtnUpdate"));
				minWait();
				if(verifyElementPresent(By.xpath("//td[contains(@class,'error-msg')]"))){
					String errormsg = getText_JS(By.xpath("//td[contains(@class,'error-msg')]"));
					test.log(LogStatus.INFO, errormsg);
					captureAndAddScreenshot();
					test.log(LogStatus.INFO, "Providing corrected height");
					click(By.xpath("//img[contains(@alt,'Close')]"));
					minWait();
					enterKeys(By.name("packingDimensionHeight"), height2);
					selectByText(By.name("packingDimensionUnit"), "cm");
					captureAndAddScreenshot();
					click(By.name("BtnUpdate"));
					minWait();
				}
				driver.switchTo().defaultContent();
				waitForFrameAndSwitch(screenFrame);
				minWait();
				test.log(LogStatus.INFO, "Details have been filled");
//				captureAndAddScreenshot();
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
				click(By.name("dgVerifiedFlag"));
				minWait();
				test.log(LogStatus.INFO, "DG Verified flag checked");
				captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
					String errormsg = getText_JS(By.xpath("//td[contains(@class,'error-msg')]")).trim();
					test.log(LogStatus.INFO, errormsg);
					captureAndAddScreenshot();
					test.log(LogStatus.INFO, "Providing corrected height");
					click(By.xpath("//img[contains(@alt,'Close')]"));
					minWait();
					scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
					click(By.xpath(prefix + Unid + suffix));
					click(By.xpath("(//button[@name='btEdit'])[last()]"));
					minWait();
					waitForFrameAndSwitch("popupContainerFrame");
					enterKeys(By.name("packingDimensionHeight"), height2);
					selectByText(By.name("packingDimensionUnit"), "cm");
					captureAndAddScreenshot();
					click(By.name("BtnUpdate"));
					minWait();
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					minWait();
					test.log(LogStatus.INFO, "Details have been filled");
//					captureAndAddScreenshot();
					minWait();
					if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
					click(By.name("dgVerifiedFlag"));
					minWait();
					test.log(LogStatus.INFO, "DG Verified flag checked");
					captureAndAddScreenshot();
					click(By.xpath("//button[@name='btnSave']"));
					maxWait();
				}
//				waitForFrameAndSwitch("popupContainerFrame");
				test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
				captureAndAddScreenshot();
				return this;
			}
				
			
//			Sharath
			public OPR350 validateError(String awbno, String Unid, String pcs, String wt, String PIValue, String TIValue, boolean listingReqd) {
//				Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
//				pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
//				wt = PropertyHandler.getPropValue(dataFilePath, wt);
//				PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
//				TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
				if(listingReqd){
				//	awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
					maxWait();
					enterKeys(By.name("masterDocumentNumber"), awbno);
					click(By.name("btList"));
					maxWait();
				}
				maxWait();
//				scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
				scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
				String prefix = "(//td[contains(text(),'";
				String suffix = "')]/following-sibling::td)[last()]";
				click(By.xpath(prefix + Unid + suffix));
				suffix = "')]/following-sibling::td)[last()]//i";
				click(By.xpath(prefix + Unid + suffix));
				click(By.xpath("(//button[@name='btEdit'])[last()]"));
				minWait();
				waitForFrameAndSwitch("popupContainerFrame");
				enterKeys(By.name("packingInstruction"), PIValue);
				enterKeys(By.name("numberOfPackages"), pcs);
				int p = Integer.parseInt(pcs);
				int w = Integer.parseInt(wt);
				enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
				selectByText(By.name("netQuantityPerPackageUnit"), "KG");
				selectByText(By.name("reportableQuantity"), "Yes");
				minWait();
				click(By.name("BtnUpdate"));
				minWait();
				if(verifyElementPresent(By.xpath("//td[contains(@class,'error-msg')]"))){
					String errormsg = getText_JS(By.xpath("//td[contains(@class,'error-msg')]"));
					test.log(LogStatus.PASS, errormsg);
					captureAndAddScreenshot();
					click(By.xpath("//img[contains(@alt,'Close')]"));
					minWait();
					driver.findElement(By.name("btCancel")).click();
					driver.switchTo().defaultContent();
					waitForFrameAndSwitch(screenFrame);
					minWait();
					driver.findElement(By.name("btnCancelPackage")).click();
					maxWait();
					minWait();
				}else{
					test.log(LogStatus.FAIL, "No error pops up");
					org.testng.Assert.fail();
				}
				return this;
			}
			/**
			 
			 * @author Akshai
			 * A-8457
			 * Capture DG details
			 */
			//Checking for incompatability validations
			public OPR350 CaptureDGDetailsOverPackverifyincompatability(String awbno, String UDIDNo[],String PIValue[], String DGpcs, String DGwt ) {
//          	awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
				maxWait();
				enterKeys(By.name("masterDocumentNumber"), awbno);
				click(By.name("btList"));
				maxWait();
				minWait();
				click(By.xpath("//i[@class='icon edit']"));
				minWait();
				enterKeys(By.name("firstEmergencyContactName"),"testuser");
				enterKeys(By.name("firstEmergencyContactNumber"),"123456");
				enterKeys(By.name("shipperpobox"), "245245");
				enterKeys(By.name("consigneepobox"), "245245");
				enterKeys(By.name("shipmentlimitation"), "DG");
				enterKeys(By.name("shipmenttype"), "DG");
				enterKeys(By.name("additionalhandlingInformation"), "TEST");
				enterKeys(By.name("warning"), "TEST");
				enterKeys(By.name("declaration"), "TEST");
				enterKeys(By.name("nameortitleofsignatory"), "TEST");
				enterKeys(By.name("place"), "Dallas");
				enterKeys(By.name("signature"),"TEST");
				enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
				enterKeys(By.name("signatoryTime"), "0000");
				minWait();
				scrollToView(By.name("btnShipperReferenceOk"));
				click(By.xpath("//button[@name='btnShipperReferenceOk']"));
				minWait();
				for(int i=0; i<4;i++){
				CaptureUnidDetailsForPropertyFile("",UDIDNo[i], DGpcs, DGwt, PIValue[i], "",false);
				}
//				CaptureUnidDetailsForPropertyFile("","UDIDNo2", "DGpcs", "DGwt", "PIValue2", "",false);
//				CaptureUnidDetailsForPropertyFile("","UDIDNo3", "DGpcs", "DGwt", "PIValue3", "",false);
//				CaptureUnidDetailsForPropertyFile("","UDIDNo4", "DGpcs", "DGwt", "PIValue4", "",false);
				minWait();
			/*	performAllPack("UDIDNo1", "UDIDNo4", "DGpcs");
				performAllPack("UDIDNo2", "UDIDNo3", "DGpcs");*/
				minWait();
				performOverPack(UDIDNo[0], UDIDNo[1], UDIDNo[2], UDIDNo[3], DGpcs);
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
					click(By.name("dgVerifiedFlag"));
					minWait();
					test.log(LogStatus.INFO, "DG Verified flag checked");
					captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
					String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
					if(status.contains("Incompatible UNIDs")||status.contains("Over pack")){
						test.log(LogStatus.PASS, "DG acceptance has been stopped successfully due to incompatability issues in UNID Over pack :"+status);
					}
				}
				test.log(LogStatus.PASS, "Incompatability Verification completed");
				return this;		
			}
			/**
			 
			 * @author Akshai
			 * A-8457
			 * Capture DG details
			 */
			//Checking for USG13
			public OPR350 CaptureDGDetailsOverPackUSG13(String awbno, String DGpcs, String UDIDNo1, String DGpcs1, String DGwt1, String PIValue1,String UDIDNo2, String DGpcs2, String DGwt2, String PIValue2 ) {
//				awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
				maxWait();
				enterKeys(By.name("masterDocumentNumber"), awbno);
				click(By.name("btList"));
				maxWait();
				minWait();
				click(By.xpath("//i[@class='icon edit']"));
				minWait();
				enterKeys(By.name("firstEmergencyContactName"),"testuser");
				enterKeys(By.name("firstEmergencyContactNumber"),"123456");
				enterKeys(By.name("shipperpobox"), "245245");
				enterKeys(By.name("consigneepobox"), "245245");
				enterKeys(By.name("shipmentlimitation"), "DG");
				enterKeys(By.name("shipmenttype"), "DG");
				enterKeys(By.name("additionalhandlingInformation"), "TEST");
				enterKeys(By.name("warning"), "TEST");
				enterKeys(By.name("declaration"), "TEST");
				enterKeys(By.name("nameortitleofsignatory"), "TEST");
				enterKeys(By.name("place"), "Dallas");
				enterKeys(By.name("signature"),"TEST");
				enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
				enterKeys(By.name("signatoryTime"), "0000");
				minWait();
				scrollToView(By.name("btnShipperReferenceOk"));
				click(By.xpath("//button[@name='btnShipperReferenceOk']"));
				minWait();
				
				CaptureUnidDetailsForPropertyFile("",UDIDNo1, DGpcs1, DGwt1, PIValue1, "",false);
				CaptureUnidDetailsForPropertyFile("",UDIDNo2, DGpcs2, DGwt2, PIValue2, "",false);
				//CaptureUnidDetailsForPropertyFile("","UDIDNo3", "DGpcs", "DGwt3", "PIValue3", "",false);
				//CaptureUnidDetailsForPropertyFile("","UDIDNo4", "DGpcs", "DGwt4", "PIValue4", "",false);
				minWait();
//				performAllPack("UDIDNo1", "UDIDNo2", "DGpcs");
				//performAllPack("UDIDNo3", "UDIDNo4", "DGpcs3_4");
				minWait();
				performOverPackfor2Unid(UDIDNo1, UDIDNo2,DGpcs);
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
					click(By.name("dgVerifiedFlag"));
					minWait();
					test.log(LogStatus.INFO, "DG Verified flag checked");
					captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
					String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
					if(status.contains("DGR Limit exeeded for the Shipment")||status.contains("Limit exeeded for the Shipment")){
						test.log(LogStatus.PASS, "As per the USG 13 Regulation Notoc Save restricted for the particular shipment"+status);
					}
				}
				test.log(LogStatus.PASS, "USG 13 Regulation Verification completed");
				return this;		
			}
			/**
			 
			 * @author Akshai
			 * A-8457
			 * Capture DG details
			 */
			//Checking for compatability at Sub risk level
			public OPR350 CaptureDGDetailsIncompatiblesubriskvalidation(String awbno, String DGpcs, String UDIDNo1, String DGpcs1, String DGwt1, String PIValue1, String UDIDNo2, String DGpcs2, String DGwt2, String PIValue2  ) {
		//		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
				maxWait();
				enterKeys(By.name("masterDocumentNumber"), awbno);
				click(By.name("btList"));
				maxWait();
				minWait();
				click(By.xpath("//i[@class='icon edit']"));
				minWait();
				enterKeys(By.name("firstEmergencyContactName"),"testuser");
				enterKeys(By.name("firstEmergencyContactNumber"),"123456");
				enterKeys(By.name("shipperpobox"), "245245");
				enterKeys(By.name("consigneepobox"), "245245");
				enterKeys(By.name("shipmentlimitation"), "DG");
				enterKeys(By.name("shipmenttype"), "DG");
				enterKeys(By.name("additionalhandlingInformation"), "TEST");
				enterKeys(By.name("warning"), "TEST");
				enterKeys(By.name("declaration"), "TEST");
				enterKeys(By.name("nameortitleofsignatory"), "TEST");
				enterKeys(By.name("place"), "Dallas");
				enterKeys(By.name("signature"),"TEST");
				enterKeys(By.name("signatoryDate"), "."+Keys.TAB);
				enterKeys(By.name("signatoryTime"), "0000");
				minWait();
				scrollToView(By.name("btnShipperReferenceOk"));
				click(By.xpath("//button[@name='btnShipperReferenceOk']"));
				minWait();
				
				CaptureUnidDetailsForPropertyFile("",UDIDNo1, DGpcs1, DGwt1, PIValue1, "",false);
				CaptureUnidDetailsForPropertyFile("",UDIDNo2, DGpcs2, DGwt2, PIValue2, "",false);
				//CaptureUnidDetailsForPropertyFile("","UDIDNo3", "DGpcs", "DGwt3", "PIValue3", "",false);
				//CaptureUnidDetailsForPropertyFile("","UDIDNo4", "DGpcs", "DGwt4", "PIValue4", "",false);
				minWait();
				performAllPack(UDIDNo1,UDIDNo2 , DGpcs);
				//performAllPack("UDIDNo3", "UDIDNo4", "DGpcs3_4");
				minWait();
//				performOverPackfor2Unid("UDIDNo1", "UDIDNo2","DGpcs");
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
					click(By.name("dgVerifiedFlag"));
					minWait();
					test.log(LogStatus.INFO, "DG Verified flag checked");
					captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//div[@id='errorDisplayDiv']//td[2]"))){
					String status =getText_JS(By.xpath("//div[@id='errorDisplayDiv']//td[2]")).trim();
					if(status.contains("Incompatible UNIDs")||status.contains("Cannot Proceed")){
						test.log(LogStatus.PASS, "Incompatability validation at subrisk level result:"+status);
					}
				}
				test.log(LogStatus.PASS, "Validated incompatibility checks at sub risk level in OPR350 ");
				return this;		
			}
			
			// overloaded to capture UNID details above threshold value (Zimmy)
			public OPR350 CaptureUnidDetailsWithSpecialAuthAboveThreshold(String awbno, String Unid, String pcs, String wt, String PIValue, String TIValue,String authCode, boolean listingReqd) {
				Unid = PropertyHandler.getPropValue(dataFilePath, Unid);
				pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
				wt = PropertyHandler.getPropValue(dataFilePath, wt);
				PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
				TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
				authCode = PropertyHandler.getPropValue(dataFilePath, authCode);
				if(listingReqd){
					awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
					maxWait();
					enterKeys(By.name("masterDocumentNumber"), awbno);
					click(By.name("btList"));
					maxWait();
				}
				maxWait();
//				scrollToView(By.xpath("(//div[@id='dangerousgoodsdetailtable']//td)[last()-1]"));
				scrollToView(By.xpath("//h2[contains(text(),'Dangerous Goods')]"));
				String prefix = "(//td[contains(text(),'";
				String suffix = "')]/following-sibling::td)[last()]";
				click(By.xpath(prefix + Unid + suffix));
				suffix = "')]/following-sibling::td)[last()]//i";
				click(By.xpath(prefix + Unid + suffix));
				click(By.xpath("(//button[@name='btEdit'])[last()]"));
				minWait();
				waitForFrameAndSwitch("popupContainerFrame");
				enterKeys(By.name("packingInstruction"), PIValue);
				enterKeys(By.name("numberOfPackages"), pcs);
				int p = Integer.parseInt(pcs);
				int w = Integer.parseInt(wt);
				enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
				selectByText(By.name("netQuantityPerPackageUnit"), "kg");
				selectByText(By.name("reportableQuantity"), "Yes");
				minWait();
				click(By.name("BtnUpdate"));
				minWait();
				if(verifyElementPresent(By.xpath("//td[contains(@class,'error-msg')]"))){
					String errormsg = getText_JS(By.xpath("//td[contains(@class,'error-msg')]"));
					test.log(LogStatus.INFO, errormsg);
					captureAndAddScreenshot();
					test.log(LogStatus.INFO, "Providing special autorization");
					click(By.xpath("//img[contains(@alt,'Close')]"));
					minWait();
					click(By.name("specialAuthorizationFlag"));
					enterKeys(By.name("authorization"),authCode);
					captureAndAddScreenshot();
					click(By.name("BtnUpdate"));
					minWait();
				}
				driver.switchTo().defaultContent();
				waitForFrameAndSwitch(screenFrame);
				minWait();
				test.log(LogStatus.INFO, "Details have been filled");
//				captureAndAddScreenshot();
				minWait();
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
				click(By.name("dgVerifiedFlag"));
				minWait();
				test.log(LogStatus.INFO, "DG Verified flag checked");
				captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
					click(By.xpath("//img[contains(@src,'close')]"));
				}
				
				// handling Dialog for Weight above Threshold Value for dryice
				
				driver.switchTo().defaultContent();
				if (verifyElementPresent(By.xpath("//p[contains(text(),'seems to be exceeded')]"))) {
					
					test.log(LogStatus.WARNING, getText_JS(
							By.xpath("//p[contains(text(),'seems to be exceeded')]")));
					captureAndAddScreenshot();
					
					click(By.xpath("//button[text()='Yes']"));
					
					waitForFrameAndSwitch(screenFrame);
					//click(By.xpath("//button[@name='btnSave']"));
				}else {
					waitForFrameAndSwitch(screenFrame);
				}
				// end of Dialog
				
//				waitForFrameAndSwitch("popupContainerFrame");
				test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
				captureAndAddScreenshot();
				return this;
			}
			// for lite screen zimmy
			public OPR350 CaptureUnidDetailsForPropertyFileLite(String awbno, String Unid, String ShippingName, String pcs, String wt, String PIValue, String TIValue) {
				
				String UdidNo = PropertyHandler.getPropValue(dataFilePath, Unid);
				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
				
				pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
				wt = PropertyHandler.getPropValue(dataFilePath, wt);
				PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
				TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);

				scrollToView(By.name("unid"));

				enterKeys(By.name("unid"), UdidNo + Keys.TAB);
				minWait();
				selectByText(By.name("properShippingName"), shippingName);
				minWait();
				
				enterKeys(By.name("packingInstruction"), PIValue);
				enterKeys(By.name("numberOfPackages"), pcs);
				int p = Integer.parseInt(pcs);
				int w = Integer.parseInt(wt);
				enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
				selectByText(By.name("netQuantityPerPackageUnit"), "kg");
				selectByText(By.name("reportableQuantity"), "Yes");
				minWait();

				click(By.name("btnAdd"));
				minWait();				
				
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
				click(By.name("dgVerifiedFlag"));
				minWait();
				test.log(LogStatus.INFO, "DG Verified flag checked");
				captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
					click(By.xpath("//img[contains(@src,'close')]"));
				}
//				waitForFrameAndSwitch("popupContainerFrame");
				test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
				captureAndAddScreenshot();
				
   				
   				return this;

		}
			
			// for handling above threshold weight for Dryice in lite screen
			
			public OPR350 CaptureUnidDetailsForPropertyFileLiteAboveThreshold(String awbno, String Unid, String ShippingName, String pcs, String wt, String PIValue, String TIValue, String authCode, String pcs1, String wt1) {
				
				String UdidNo = PropertyHandler.getPropValue(dataFilePath, Unid);
				String shippingName = PropertyHandler.getPropValue(dataFilePath, ShippingName);
				
				pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
				wt = PropertyHandler.getPropValue(dataFilePath, wt);
				
				pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
				wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);
				
				PIValue = PropertyHandler.getPropValue(dataFilePath, PIValue);
				TIValue = PropertyHandler.getPropValue(dataFilePath, TIValue);
				authCode = PropertyHandler.getPropValue(dataFilePath, authCode);
				

				scrollToView(By.name("unid"));

				enterKeys(By.name("unid"), UdidNo + Keys.TAB);
				minWait();
				selectByText(By.name("properShippingName"), shippingName);
				minWait();
				
				enterKeys(By.name("packingInstruction"), PIValue);
				enterKeys(By.name("numberOfPackages"), pcs);
				enterKeys(By.name("netQuantityPerPackage"), wt1);
				int p = Integer.parseInt(pcs);
				int w = Integer.parseInt(wt);
			//	enterKeys(By.name("netQuantityPerPackage"), /*"1"*/Integer.toString(w / p));
				selectByText(By.name("netQuantityPerPackageUnit"), "kg");
				selectByText(By.name("reportableQuantity"), "Yes");
				minWait();

				click(By.name("btnAdd"));
				maxWait();	
				
				//added for handling weight abovr threshold error
				
				if(verifyElementPresent(By.xpath("//td[contains(@class,'error-msg')]"))){
					String errormsg = getText_JS(By.xpath("//td[contains(@class,'error-msg')]"));
					test.log(LogStatus.INFO, errormsg);
					captureAndAddScreenshot();
					test.log(LogStatus.INFO, "Providing special autorization");
					click(By.xpath("//img[contains(@alt,'Close')]"));
					minWait();
					
					click(By.xpath("//a[@name='moreDetails']"));
					minWait();
					
					click(By.name("specialAuthorizationFlag"));
					enterKeys(By.name("authorization"), authCode);
					captureAndAddScreenshot();
					click(By.name("btnAdd"));
					minWait();
				}
				
				if(!driver.findElement(By.name("dgVerifiedFlag")).isSelected())
				click(By.name("dgVerifiedFlag"));
				minWait();
				test.log(LogStatus.INFO, "DG Verified flag checked");
				captureAndAddScreenshot();
				click(By.xpath("//button[@name='btnSave']"));
				maxWait();
				if(verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))){
					click(By.xpath("//img[contains(@src,'close')]"));
				}
				
				// handling Dialog for Weight above Threshold Value for dryice
				
				driver.switchTo().defaultContent();
				if (verifyElementPresent(By.xpath("//p[contains(text(),'Dry ice seems to be exceeded')]"))) {
					
					test.log(LogStatus.WARNING, getText_JS(
							By.xpath("//p[contains(text(),'Dry ice seems to be exceeded')]")));
					captureAndAddScreenshot();
					
					click(By.xpath("//button[text()='Yes']"));
					
					waitForFrameAndSwitch(screenFrame);
					//click(By.xpath("//button[@name='btnSave']"));
				}else {
					waitForFrameAndSwitch(screenFrame);
				}
				// end of Dialog
				
//				waitForFrameAndSwitch("popupContainerFrame");
				test.log(LogStatus.PASS, "Unid Details has been succesfully captured");
				captureAndAddScreenshot();				
   				
   				return this;

		}
}