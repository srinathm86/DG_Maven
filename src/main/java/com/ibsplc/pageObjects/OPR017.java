package com.ibsplc.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.ibsplc.common.BasePage;
import com.ibsplc.generic.InitialSetup;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * OPR017 : Generate NOTOC
 * @author a-7868 Krishna
 * <19-03-2017>
 *
 */
public class OPR017 extends BasePage{

	private static String objFilepath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "OPR_FLT.properties");
    private static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "Generic.properties");
    private static String FRAME = "iCargoContentFrameOPR017";
    private String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");
    private WebDriver driver;
    private String dataFileName;
    ExtentTest test;
    private By 	txt_carrierCode,
    			txt_fltNo,
    			txt_fltDt,
    			btn_list,
    			btn_clear,
    			btn_close,
    			btn_generateNOTOC,
    			btn_sendNTM,
    			chkbox_final,
    			tbl_otherSplLoads,
    			yesBtn,
    			noBtn,
    			info_footLayer,
    			tbl_dgrDtls,
    			link_expandAllRows,
    			btn_autoAssign;
    
    public OPR017(WebDriver driver, String dataFileName, ExtentTest test) {
        super(driver, test);
        this.driver = driver;
        this.test = test;
        this.dataFilePath = dataFilePath + dataFileName;
        this.dataFileName = dataFileName;
        initElements();
    }
    /**
     * MEthod to initialize page elements
     */
    private void initElements() {

    	txt_carrierCode = MiscUtility.getWebElement(objFilepath, "OPR017_txt_carrierCode");
    	txt_fltNo = MiscUtility.getWebElement(objFilepath, "OPR017_txt_fltNo");
    	txt_fltDt = MiscUtility.getWebElement(objFilepath, "OPR017_txt_fltDt");
    	btn_list = MiscUtility.getWebElement(objFilepath, "OPR017_btn_list");
    	btn_clear = MiscUtility.getWebElement(objFilepath, "OPR017_btn_clear");
    	btn_close = MiscUtility.getWebElement(objFilepath, "OPR017_btn_close");
    	btn_generateNOTOC = MiscUtility.getWebElement(objFilepath, "OPR017_btn_generateNOTOC");
    	btn_sendNTM = MiscUtility.getWebElement(objFilepath, "OPR017_btn_sendNTM");
    	chkbox_final = MiscUtility.getWebElement(objFilepath, "OPR017_chkbox_final");
    	tbl_otherSplLoads = MiscUtility.getWebElement(objFilepath, "OPR017_tbl_otherSplLoads");
        tbl_dgrDtls= MiscUtility.getWebElement(objFilepath, "OPR017_tbl_dgrDtls");
        link_expandAllRows=MiscUtility.getWebElement(objFilepath, "OPR017_link_expandAllRows");
        btn_autoAssign = MiscUtility.getWebElement(objFilepath, "OPR017_btn_autoAssign");
        yesBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
        noBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_noBtn");
        info_footLayer = MiscUtility.getWebElement(genObjPath, "Generic_foot_layer");
    }
    
    /**
     * Method to lis a flight, check for the AWBm
     * check Final and click Generate NOTOC button
     * @param carrCode
     * @param fltNo
     * @param fltDt
     * @param awbNo
     * @return
     * @author a-7868 Krishna <19/03/2018>
     */
    public OPR017 generateNOTOC(String carrCode, String fltNo, String fltDt, String awbPre, String awbNo){
    	
//    	carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
//    	fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
//    	fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
//    	awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
//    	awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
    	
    	enterKeys(txt_carrierCode, carrCode);
    	enterKeys(txt_fltNo, fltNo);
    	enterKeys(txt_fltDt, fltDt+Keys.TAB+Keys.F12);
//    	click(btn_list);
    	minWait();
    	
//    	Assert.assertTrue(getTableColumnValues(tbl_otherSplLoads, 1).contains(awbPre+"-"+awbNo),"ERROR : AWB not listed");
    	
    	check(chkbox_final);
    	click(btn_generateNOTOC);
    	driver.switchTo().defaultContent();
    	if(verifyElementPresent(yesBtn))
    		click(yesBtn);
    	//Assert.assertTrue(waitForElement(info_footLayer).getText().contains("successfully"), "ERROR : NOTOC not generated.");
    	waitForFrameAndSwitch(FRAME);
    	waitForNewWindow(2);
    	switchToSecondWindow();
    	closeAndmoveToFirstWindow();
    	waitForFrameAndSwitch(FRAME);
    	return this;
    }
    
//    Sharath
	public OPR017 generateNOTOCTestData(String carrCode, String fltNo, String fltDt, String awbPre, String awbNo,
			String container, String wt, String flag) {
  	
    	enterKeys(txt_carrierCode, carrCode);
    	enterKeys(txt_fltNo, fltNo);
    	enterKeys(txt_fltDt, fltDt+Keys.TAB);
    	click(btn_list);
    	maxWait();
    	String prefix = "//td[text()='";
    	String suffix = "']/../td[1]/input";
    	//td[text()='DFW0050']/../td[1]/input
    	click(By.xpath(prefix+container+suffix));
    	String value = getAttributebyValue(By.xpath("(//input[@name='netQuantity'])[last()-1]"));
    	if(!value.equals("0")){
    		click(By.xpath("//a[text()='Add']"));
    	}
    	selectByText(By.xpath("(//select[@name='dryiceuld'])[last()-1]"), container);
    	enterKeys(By.xpath("(//input[@name='netQuantity'])[last()-1]"), wt);
//    	check(chkbox_final);
    	click(By.name("btSave"));
    	test.log(LogStatus.PASS, "Successfully saved the details");
		captureAndAddScreenshot();
    	maxWait();
    	test.log(LogStatus.PASS, "Successfully clicked on save button");
    	if(flag.equalsIgnoreCase("moreWt"))
			if (verifyElementPresent(By.xpath("//td[@class='ic-error-msg']"))) {
				String status = getText_JS(By.xpath("//td[@class='ic-error-msg']")).trim();
				test.log(LogStatus.PASS, "Validtion passed with message: " + status);
				captureAndAddScreenshot();
			}else{
				test.log(LogStatus.FAIL, "No error message popped up for weight greater than threshold");
			}
    	else{
			click(btn_generateNOTOC);
			test.log(LogStatus.PASS, "Successfully clicked on Generate Notoc button");
			captureAndAddScreenshot();
			driver.switchTo().defaultContent();
			if (verifyElementPresent(yesBtn))
				click(yesBtn);
			// Assert.assertTrue(waitForElement(info_footLayer).getText().contains("successfully"),
			// "ERROR : NOTOC not generated.");
			waitForFrameAndSwitch(FRAME);
			waitForNewWindow(2);
			switchToSecondWindow();
			test.log(LogStatus.PASS, "Generate notoc was successful");
			captureAndAddScreenshot();
			closeAndmoveToFirstWindow();
			waitForFrameAndSwitch(FRAME);
    	}
    	return this;
    }
  
    /**
     * Method to check for the AWBm
     * check Final and click Generate NOTOC button
     * @param carrCode
     * @param fltNo
     * @param fltDt
     * @param awbNo
     * @return
     * @author a-7868 Krishna <19/03/2018>
     */
    public OPR017 assignUNIDandGenerateNOTOCFromOPR016(String carrCode, String fltNo, String fltDt, String awbPre, String awbNo){
    	
    	
    	click(link_expandAllRows);
    	tblSelectRowByColValue(tbl_dgrDtls, 1, 6, awbPre+" "+awbNo);
    	click(btn_autoAssign);
    	minWait();
    	check(chkbox_final);
    	click(btn_generateNOTOC);
    	driver.switchTo().defaultContent();
    	if(verifyElementPresent(yesBtn))
    		click(yesBtn);
    	//Assert.assertTrue(waitForElement(info_footLayer).getText().contains("successfully"), "ERROR : NOTOC not generated.");
    	waitForFrameAndSwitch("iCargoContentFrameOPR016");
    	waitForNewWindow(2);
    	switchToSecondWindow();
    	closeAndmoveToFirstWindow();
    	waitForFrameAndSwitch("iCargoContentFrameOPR016");
    	return this;
    }
    
    /**
     * closes the screen and goes back to home page
     *
     * @return
     * @author A-7868 Krishna <19/03/2018>
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
     *To reOpen a Notoc
     * @param carrCode
     * @param fltNo
     * @param fltDt
     * @param awbNo
     * @return
     *
     * @author by Souvik A-8457 on 16.04.2020
     */
    public OPR017 ReopenNOTOC(String carrCode, String fltNo, String fltDt){
    	
//    	carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
//    	fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
//    	fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
    	
    	
    	enterKeys(txt_carrierCode, carrCode);
    	enterKeys(txt_fltNo, fltNo);
    	enterKeys(txt_fltDt, fltDt+Keys.TAB);
    	click(btn_list);
    	maxWait();
    	click(By.xpath("//button[@name='btReopen']"));
    	maxWait();
//    	click(By.xpath("//button[@name='btSave']"));
//    	maxWait();
    	return this;
    }
    
    /**
     * Method to list a flight, check for the AWB
     * check Final and click Generate NOTOC button
     * @param carrCode
     * @param fltNo
     * @param fltDt
     * @param awbNo
     * @return
     * @author a-8021 Karthik <07/12/2020>
     */
    public OPR017 generateNOTOCAfterVerification(String carrCode, String fltNo, String fltDt, String awbPre, String awbNo){
    	
    	carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
    	fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
    	fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
    	awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
    	awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
    	
    	enterKeys(txt_carrierCode, carrCode);
    	enterKeys(txt_fltNo, fltNo);
    	enterKeys(txt_fltDt, fltDt+Keys.TAB+Keys.F12);
//    	click(btn_list);
    	minWait();
    	test.log(LogStatus.INFO, "Generating Notoc");
    	click(link_expandAllRows);
    	
    	System.out.println(getTableColumnValues(tbl_dgrDtls, 6).toString());
    	
    	Assert.assertTrue(getTableColumnValues(tbl_dgrDtls, 6).toString().contains(awbNo),"ERROR : AWB not listed");
    	
//    	check(chkbox_final);
    	click(btn_generateNOTOC);
    	driver.switchTo().defaultContent();
    	if(verifyElementPresent(noBtn))
    		click(noBtn);
    	test.log(LogStatus.INFO, "Notoc has been generated");
    	captureAndAddScreenshot();
    	//Assert.assertTrue(waitForElement(info_footLayer).getText().contains("successfully"), "ERROR : NOTOC not generated.");
    	return this;
    }
    
    
}
