package com.ibsplc.pageObjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.xml.sax.SAXException;

import com.ibsplc.common.BasePage;
import com.ibsplc.generic.InitialSetup;
import com.ibsplc.generic.Message;
import com.ibsplc.utils.BizUtility;
import com.ibsplc.utils.MiscUtility;
import com.ibsplc.utils.PropertyHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author A-7605
 * @author A-7605
 */

/**
 * @author A-7605
 */
public class MSG005 extends BasePage {
	public static final String messagePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"selenium.sample.message.path");
	private final static Logger logger = Logger.getLogger(MSG005.class);
	public static String objectFileName = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"CAP_MSG.properties");
	public static String genObjPath = PropertyHandler.getPropValue("resources\\EnvSetup.properties",
			"Generic.properties");
	// public static String dataFilePath;
	private final String contentFrame = "iCargoContentFrameMSG005";
	static String refID ="";
	static boolean WriteUNTLine;
	ExtentTest test;
	public String dataFilePath = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "testDataDirectory");
	public String dataFileName;
	By txt_messgeType;
	By btn_reference, txt_reference1, txt_reference2, txt_reference3, txt_reference4, txt_reference5;
	By tbl_message;
	By btn_list;
	By btn_loadFromFile;
	By loadFromFile_list_participantType;
	By loadFromFile_txt_participant;
	By loadFromFile_list_transmissionMode;
	By loadFromFile_txt_station;
	By loadFromFile_btn_address;
	By messageAddressDetails_btn_ok;
	By messageAddress_txt_FileListner;
	By loadFromFile_btn_chooseFile;
	By loadFromFile_btn_load;
	By loadFromFile_btn_close;
	By btn_close;
	By dialogue_btn_ok;
	By dialogue_msg;
	By list_messageStatus;
	By chk_selectMessage;
	By btn_process;
	By txt_awbpfx;
	By txt_awbser;
	By dropdown_messageSubType;
	By btn_view;
	By info_ErrorDesc;
	By txt_list_referencevalue, txt_list_referencevalueFWB, txt_calFromDate, btn_clear, yesBtn, Generic_info_error,
			viewMessage_info_message, txt_calToDate;
	private WebDriver driver;
	private By list_checkBoxSelectMsg, txt_viewMsg, btn_viewClose;

	// A-8680
	private By txt_date, txt_time;

	public MSG005(WebDriver driver, String dataFileName, ExtentTest test) {
		super(driver,test);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
		initElements();
		this.dataFilePath = dataFilePath + dataFileName;
		this.dataFileName = dataFileName;
	}

	private void initElements() {
		txt_messgeType = MiscUtility.getWebElement(objectFileName, "MSG005_txt_messageType");
		btn_reference = MiscUtility.getWebElement(objectFileName, "MSG005_btn_reference");
		txt_awbpfx = MiscUtility.getWebElement(objectFileName, "MSG005_txt_awbpfx");
		txt_awbser = MiscUtility.getWebElement(objectFileName, "MSG005_txt_awbser");
		txt_reference1 = MiscUtility.getWebElement(objectFileName, "MSG005_txt_reference1");
		txt_reference2 = MiscUtility.getWebElement(objectFileName, "MSG005_txt_reference2");
		txt_reference3 = MiscUtility.getWebElement(objectFileName, "MSG005_txt_reference3");
		txt_reference4 = MiscUtility.getWebElement(objectFileName, "MSG005_txt_reference4");
		txt_reference5 = MiscUtility.getWebElement(objectFileName, "MSG005_txt_reference5");

		tbl_message = MiscUtility.getWebElement(objectFileName, "MSG005_tbl_message");
		btn_list = MiscUtility.getWebElement(objectFileName, "MSG005_btn_list");
		btn_loadFromFile = MiscUtility.getWebElement(objectFileName, "MSG005_btn_loadFromFile");
		loadFromFile_list_participantType = MiscUtility.getWebElement(objectFileName,
				"MSG005_loadFromFile_list_participantType");
		loadFromFile_txt_participant = MiscUtility.getWebElement(objectFileName, "MSG005_loadFromFile_txt_participant");
		loadFromFile_list_transmissionMode = MiscUtility.getWebElement(objectFileName,
				"MSG005_loadFromFile_list_transmissionMode");
		loadFromFile_txt_station = MiscUtility.getWebElement(objectFileName, "MSG005_loadFromFile_txt_station");
		loadFromFile_btn_address = MiscUtility.getWebElement(objectFileName, "MSG005_loadFromFile_btn_address");
		messageAddressDetails_btn_ok = MiscUtility.getWebElement(objectFileName, "MSG005_messageAddressDetails_btn_ok");
		messageAddress_txt_FileListner = MiscUtility.getWebElement(objectFileName,
				"MSG005_messageAddress_txt_FileListner");
		loadFromFile_btn_chooseFile = MiscUtility.getWebElement(objectFileName, "MSG005_loadFromFile_btn_chooseFile");
		loadFromFile_btn_load = MiscUtility.getWebElement(objectFileName, "MSG005_loadFromFile_btn_load");
		loadFromFile_btn_close = MiscUtility.getWebElement(objectFileName, "MSG005_loadFromFile_btn_close");
		btn_close = MiscUtility.getWebElement(objectFileName, "MSG005_btn_close");
		dialogue_btn_ok = MiscUtility.getWebElement(genObjPath, "Generic_btn_ok");
		dialogue_msg = MiscUtility.getWebElement(genObjPath, "Generic_info_msg");
		list_messageStatus = MiscUtility.getWebElement(objectFileName, "MSG005_list_messageStatus");
		chk_selectMessage = MiscUtility.getWebElement(objectFileName, "MSG005_chk_selectMessage");
		btn_process = MiscUtility.getWebElement(objectFileName, "MSG005_btn_process");
		dropdown_messageSubType = MiscUtility.getWebElement(objectFileName, "MSG005_dropdown_messageSubType");
		btn_view = MiscUtility.getWebElement(objectFileName, "MSG005_btn_view");
		info_ErrorDesc = MiscUtility.getWebElement(objectFileName, "MSG005_info_ErrorDesc");
		txt_list_referencevalue = MiscUtility.getWebElement(objectFileName, "MSG005_txt_list_referencevalue");
		txt_list_referencevalueFWB = MiscUtility.getWebElement(objectFileName, "MSG005_txt_list_referencevalueFWB");
		list_checkBoxSelectMsg = MiscUtility.getWebElement(objectFileName, "MSG005_list_checkBoxSelectMsg");
		txt_viewMsg = MiscUtility.getWebElement(objectFileName, "MSG005_txt_viewMsg");
		txt_calFromDate = MiscUtility.getWebElement(objectFileName, "MSG005_txt_calFromDate");
		btn_viewClose = MiscUtility.getWebElement(objectFileName, "MSG005_btn_viewClose");
		viewMessage_info_message = MiscUtility.getWebElement(objectFileName, "MSG005_viewMessage_info_message");
		btn_clear = MiscUtility.getWebElement(objectFileName, "MSG005_btn_clear");
		yesBtn = MiscUtility.getWebElement(genObjPath, "Generic_btn_diaYes");
		// A-8255
		Generic_info_error = MiscUtility.getWebElement(genObjPath, "Generic_info_error");

		// A-8680
		txt_date = MiscUtility.getWebElement(objectFileName, "MSG005_txt_date");
		txt_time = MiscUtility.getWebElement(objectFileName, "MSG005_txt_time");
		txt_calToDate = MiscUtility.getWebElement(objectFileName, "MSG005_txt_calToDate");

	}

	/**
	 * Description : Method will check if FFR message is triggered
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @return
	 */

	public MSG005 checkIfFFRTriggered(String key_awbNoPrefix, String key_awbNo) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);

		boolean isFound = false;
		logger.info("Checking if FFR triggered");
		enterKeys(txt_messgeType, "FFR");
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FFR triggered");
			isFound = true;
		} else {
			logger.info("FFR not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FFR message is not not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		return this;
	}

	/**
	 * Description : Method will check if FFR message is triggered
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @return
	 * @author Arun A-7681 05/01
	 */

	public MSG005 checkIfFMATriggered(String key_awbNoPrefix, String key_awbNo) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);

		boolean isFound = false;
		logger.info("Checking if FMA triggered");
		enterKeys(txt_messgeType, "FMA");
		click(btn_reference);
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FMA triggered");
			isFound = true;
		} else {
			logger.info("FMA not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FMA message is not not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		return this;
	}

	/**
	 * Description : Method will upload message in the messagePath
	 * 
	 * @param messagePath
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 */
	public String uploadMessage(String messagePath, String participantType, String participant, String station) {

		File f = new File(messagePath);
		messagePath = f.getAbsolutePath();
		logger.info("Uploaing message");
		String uploadedMessageStatus = null;
		logger.info("Message path is " + messagePath);
		click(btn_loadFromFile);
		switchToSecondWindow();
		selectByText(loadFromFile_list_participantType, participantType);
		if (!participantType.equals("All")) {
			enterKeys(loadFromFile_txt_participant, participant);
		}
		selectByText(loadFromFile_list_transmissionMode, "FILE");
		enterKeys(loadFromFile_txt_station, station);

		String interfaceSystem = PropertyHandler.getPropValue(dataFilePath, "interfaceSystem");
		if (interfaceSystem != null && interfaceSystem.equals("SITA")) {
			selectByText(By.name("interfaceSystem"), "SITA");
		}
		click(loadFromFile_btn_address);
		switchToNthWindow(3);
		enterKeys(messageAddress_txt_FileListner, "IBSAT");
		click(messageAddressDetails_btn_ok);
		switchToSecondWindow();
		minWait();
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		click(loadFromFile_btn_load);
		uploadedMessageStatus = waitForElement(dialogue_msg).getText();
		captureAndAddScreenshot();
		click(dialogue_btn_ok);
		click(loadFromFile_btn_close);
		switchToFirstWindow();
		logger.info("Message uploaded. Status is '" + uploadedMessageStatus + "'");
		waitForFrameAndSwitch(contentFrame);
		// Message loaded successfully, decoded successfully.
		if (uploadedMessageStatus.contains("Message loaded successfully")) {
			test.log(LogStatus.PASS, "Message has been uploaded successfully");
		} else {
			test.log(LogStatus.FAIL,
					"Message wasn't uploaded successfully, having status : " + uploadedMessageStatus);
		}
		return uploadedMessageStatus;
	}

	/**
	 * Description : Method will upload existing SSIM file and verify if
	 * processed successfully
	 * 
	 * @param file_to_upload
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 */
	public MSG005 uploadMessageFileAndVerify(String path, String key_participantType, String key_participant,
			String key_station) {

		// String fileName = PropertyHandler.getPropValue(dataFilePath,
		// file_to_upload);
		String filePath = messagePath + path;
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String processingMessage = uploadMessage(filePath, participantType, participant, station);
		/*
		 * Boolean isFound = true;
		 * if(!processingMessage.contains("Succesfully")){ isFound = false; }
		 * Assert.assertTrue(isFound,
		 * "SSIM file - "+path+" not processed succesfully'" );
		 */
		return this;
	}

	/**
	 * Description : Method will process FFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public void processFFR(String awbNoPrefix, String awbNo) {
		logger.info("Processing FFR message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FFR");
		click(btn_reference);
		minWait();
		// enterKeys(txt_awbpfx, awbNoPrefix);
		// enterKeys(txt_awbser, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
		}
		logger.info("FFR message processed");
		return;
	}

	public void processFDD(String awbNoPrefix, String awbNo) {
		logger.info("Processing FDD message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FDD");
		click(btn_reference);
		minWait();

		enterKeys(getWebElements(txt_list_referencevalue).get(1), awbNoPrefix);
		enterKeys(getWebElements(txt_list_referencevalue).get(4), awbNo);

		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException e) {
			logger.warn("Exception occured " + e);
		}
		logger.info(" message processed");
		return;
	}

	/**
	 * Description : Method will process FFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	private void processFFR_Error(String awbNoPrefix, String awbNo, String reason) {

		awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, awbNoPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		reason = PropertyHandler.getPropValue(dataFilePath, reason);
		logger.info("Processed with FFR message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FFR");
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			selectByText(list_messageStatus, "Processed With Errors");
			click(btn_list);
			minWait();
			check(chk_selectMessage);
			click(btn_view);
			minWait();
			switchToSecondWindow();
			Assert.assertTrue(waitForElement(info_ErrorDesc).getAttribute("text").contains(reason),
					"ERROR : Data not matching");

		} catch (NoSuchElementException e) {
			logger.warn("Exception occured " + e);
		}
		logger.info("FFR message processed with Error");
		return;
	}

	/**
	 * Description : Method will process FFA message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public void processFFA(String awbNoPrefix, String awbNo) {

		logger.info("Processing FFA message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FFA");
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FFA message processed");
		return;
	}

	/**
	 * Description : Method will create new FFR message file, upload and process
	 * the message
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_origin
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_flightStartDate
	 * @param key_fullFlightNo
	 * @param key_FFR_Code
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @return
	 */
	public MSG005 uploadFFRMessage(String key_awbNoPrefix, String key_awbNo, String key_origin, String key_destination,
			String key_pieces, String key_weight, String key_flightStartDate, String key_fullFlightNo,
			String key_FFR_Code, String key_participantType, String key_participant, String key_station) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String fullFlightNo = PropertyHandler.getPropValue(dataFilePath, key_fullFlightNo);
		String FFR_Code = PropertyHandler.getPropValue(dataFilePath, key_FFR_Code);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		String messageSampleFilePath = messagePath + "FFR.txt";
		String messagePath = Message.createFFRMessage(messageSampleFilePath, awbNoPrefix, awbNo, origin, destination,
				pieces, weight, flightStartDate, fullFlightNo, FFR_Code);
		uploadMessage(messagePath, participantType, participant, station);
		processFFR(awbNoPrefix, awbNo);
		return this;
	}

	public MSG005 uploadFDDMessage(String key_awbNoPrefix, String key_awbNo, String key_origin, String key_destination,
			String key_participantType, String key_participant, String key_station) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);

		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		String flightStartDate = format.format(date).toUpperCase();

		// flightStartDate = PropertyHandler.getPropValue(dataFilePath,
		// flightStartDate);

		String messageSampleFilePath = messagePath + "FDD.txt";
		String messagePath = Message.createFDDMessage(messageSampleFilePath, awbNoPrefix, awbNo, origin, destination,
				flightStartDate);
		uploadMessage(messagePath, participantType, participant, station);
		processFDD(awbNoPrefix, awbNo);
		return this;
	}

	/**
	 * Description : Method will create new FFA message file, upload and process
	 * the message
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_origin
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_flightStartDate
	 * @param key_OALFullFlightNo
	 * @param key_OALCarrierCode
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @return
	 */
	public MSG005 uploadFFAMessage(String key_awbNoPrefix, String key_awbNo, String key_origin, String key_destination,
			String key_pieces, String key_weight, String key_flightStartDate, String key_OALFullFlightNo,
			String key_OALCarrierCode, String key_participantType, String key_participant, String key_station) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String OALFullFlightNo = PropertyHandler.getPropValue(dataFilePath, key_OALFullFlightNo);
		String OALCarrierCode = PropertyHandler.getPropValue(dataFilePath, key_OALCarrierCode);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		String fullAWBNo = awbNoPrefix + "-" + awbNo;
		String messageSampleFilePath = messagePath + "FFA.txt";
		String messagePath = Message.createFFAMessage(messageSampleFilePath, fullAWBNo, awbNo, origin, destination,
				pieces, weight, flightStartDate, OALFullFlightNo, OALCarrierCode);
		uploadMessage(messagePath, participantType, participant, station);
		processFFA(awbNoPrefix, awbNo);
		return this;
	}

	/**
	 * Description : Method will process FHL message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public void processFHLMessage(String awbNoPrefix, String awbNo) {
		logger.info("Processing FHL message");
		waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FHL");
		click(btn_reference);
		minWait();

		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FHL message processed");
		return;
	}

	public void processCSNMessage(String awbNo) {
		logger.info("Processing FHL message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "CSN");
		// click(btn_reference);
		// minWait();
		// enterKeys(txt_awbpfx, awbNoPrefix);
		// enterKeys(txt_awbser, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			tblSelectRowByColValue(tbl_message, 1, 2, awbNo);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FHL message processed");
		return;
	}

	/**
	 * Description : Method will create new FHL message file, upload and process
	 * the message
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_flightOrigin
	 * @param key_flightDestination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_hawb1_pieces
	 * @param key_hawb1_weight
	 * @param key_hawb2_pieces
	 * @param key_hawb2_weight
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @return
	 */

	public MSG005 uploadFHLMessage(String key_awbNoPrefix, String key_awbNo, String key_flightOrigin,
			String key_flightDestination, String key_pieces, String key_weight, String key_hawb1_pieces,
			String key_hawb1_weight, String key_hawb2_pieces, String key_hawb2_weight, String key_participantType,
			String key_participant, String key_station) {
		String messageSampleFilePath = messagePath + "FHL.txt";
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String flightOrigin = PropertyHandler.getPropValue(dataFilePath, key_flightOrigin);
		String flightDestination = PropertyHandler.getPropValue(dataFilePath, key_flightDestination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String hawb1_pieces = PropertyHandler.getPropValue(dataFilePath, key_hawb1_pieces);
		String hawb1_weight = PropertyHandler.getPropValue(dataFilePath, key_hawb1_weight);
		String hawb2_pieces = PropertyHandler.getPropValue(dataFilePath, key_hawb2_pieces);
		String hawb2_weight = PropertyHandler.getPropValue(dataFilePath, key_hawb2_weight);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String fullAWBNo = awbNoPrefix + "-" + awbNo;

		String messagePath = Message.createFHLMessage(messageSampleFilePath, fullAWBNo, flightOrigin, flightDestination,
				pieces, weight, hawb1_pieces, hawb1_weight, hawb2_pieces, hawb2_weight);
		uploadMessage(messagePath, participantType, participant, station);
		processFHLMessage(awbNoPrefix, awbNo);
		return this;
	}

	public MSG005 uploadCSNMessage(String awbPre, String awbNo, String fullFltNo, String origin, String dest,
			String date, String pcs, String participantType, String participant, String station) {
		String messageSampleFilePath = messagePath + "CSN.txt";
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		fullFltNo = PropertyHandler.getPropValue(dataFilePath, fullFltNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		date = PropertyHandler.getPropValue(dataFilePath, date);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = Message.createCSNMessage(messageSampleFilePath, awbPre, awbNo, fullFltNo, origin, dest,
				date, pcs);
		uploadMessage(messagePath, participantType, participant, station);
		processCSNMessage(awbNo);
		return this;
	}

	/**
	 * Method will process FWB message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public void processFWBMessage(String awbNoPrefix, String awbNo) {
		logger.info("Processing FWB message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FWB");
		click(btn_reference);
		minWait();

		By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
		By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

		// enterKeys(getWebElements(txt_list_referencevalue).get(0),
		// awbNoPrefix);
		// enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
		enterKeys(txt_awbPre, awbNoPrefix);
		enterKeys(txt_awbNo, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FWB message processed");
		return;
	}

	/**
	 * Method will process FWB message as per message status
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param status
	 * @author A-7868 Krishna <24/05/2018>
	 */
	public void processFWBMessage(String awbNoPrefix, String awbNo, String status) {

		logger.info("Processing FWB message");

		if (status.toLowerCase().contains("error")) {
			Assert.fail("Error in Message");
		} else if (status.toLowerCase().contains("processed")) {
			logger.info("Message already in Processed status.");
		} else {

			By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
			By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

			enterKeys(txt_messgeType, "FWB");
			click(btn_reference);
			minWait();
			enterKeys(txt_awbPre, awbNoPrefix);
			enterKeys(txt_awbNo, awbNo);
			click(btn_list);
			if (verifyElementPresent(chk_selectMessage)) {
				check(chk_selectMessage);
				click(btn_process);
				driver.switchTo().defaultContent();
				if (waitForElement(dialogue_msg).getText().toLowerCase().contains("error"))
					Assert.fail("Error in Message");
				click(dialogue_btn_ok);
				waitForFrameAndSwitch(contentFrame);
			}
			logger.info("FWB message processed");
		}
		return;
	}

	/**
	 * verify FWB error message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */

	private void verifyDateErrorFWB(String awbNoPrefix, String awbNo) {
		logger.info("Processing FWB message");
		enterKeys(txt_messgeType, "FWB");
		click(btn_reference);
		minWait();

		By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
		By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

		// enterKeys(getWebElements(txt_list_referencevalue).get(0),
		// awbNoPrefix);
		// enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
		enterKeys(txt_awbPre, awbNoPrefix);
		enterKeys(txt_awbNo, awbNo);
		selectByText(list_messageStatus, "Processed  With Errors");
		click(btn_list);
		check(chk_selectMessage);
		click(btn_view);
		minWait();
		switchToSecondWindow();
		Assert.assertTrue(
				waitForElement(info_ErrorDesc).getText().toLowerCase()
						.contains("Issuing date is older than given days"),
				"The error message is not as expected for the FWB message for AWB No: " + awbNo);
		switchToFirstWindow();
		waitForFrameAndSwitch(contentFrame);

		return;
	}

	/**
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	private void processFFMMessage(String awbNoPrefix, String awbNo) {
		logger.info("Processing FFM message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FFM");
		click(btn_reference);
		minWait();
		// enterKeys(txt_awbpfx, awbNoPrefix);
		// enterKeys(txt_awbser, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FFM message processed");
		return;
	}

	/**
	 * Description : Method will create new FWB message file, upload and process
	 * the message. key_rate should be 'null' if no rate is required
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_carrierCode
	 * @param key_origin
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_shipperName
	 * @param key_consigneeName
	 * @param key_rate
	 * @param key_flightStartDate
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @return
	 */

	public MSG005 uploadFWBMessage(String key_awbNoPrefix, String key_awbNo, String key_carrierCode, String key_origin,
			String key_destination, String key_pieces, String key_weight, String key_shipperName,
			String key_consigneeName, String key_agentCode, String FullFlightNo, String key_exportIATAAgentCode,
			String key_rate, String key_flightStartDate, String key_participantType, String key_participant,
			String key_station, String paymentType, boolean OCIFlag, String... key_secureScc) {

		String messageSampleFilePath;
		String secureScc = "";

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);
		if (key_secureScc.length != 0)
			secureScc = PropertyHandler.getPropValue(dataFilePath, key_secureScc[0]);
		if (rate.equalsIgnoreCase("null"))
			rate = null;
		if (OCIFlag) {
			messageSampleFilePath = messagePath + "FWB_OCI.txt";
			messageSampleFilePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
					origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
					exportIATAAgentCode, rate, flightStartDate, paymentType, true);
			String status = uploadMessage(messageSampleFilePath, participantType, participant, station);
			processFWBMessage(awbNoPrefix, awbNo, status);

		} else {
			messageSampleFilePath = messagePath + "FWB.txt";
			if (key_secureScc.length != 0)
				messageSampleFilePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
						origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
						exportIATAAgentCode, rate, flightStartDate, paymentType, false, secureScc);
			else
				messageSampleFilePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
						origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
						exportIATAAgentCode, rate, flightStartDate, paymentType, false);
			String status = uploadMessage(messageSampleFilePath, participantType, participant, station);
			processFWBMessage(awbNoPrefix, awbNo, status);

		}
		return this;
	}

	/**
	 * Description : Method will create new FWB message file, upload and process
	 * the message. key_rate should be 'null' if no rate is required
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_carrierCode
	 * @param key_origin
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_shipperName
	 * @param key_consigneeName
	 * @param key_rate
	 * @param key_flightStartDate
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @return
	 */

	public MSG005 uploadFWBMessageWithDiffChargableWt(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_origin, String key_destination, String key_pieces, String key_weight, String chargWt,
			String key_shipperName, String key_consigneeName, String key_agentCode, String FullFlightNo,
			String key_exportIATAAgentCode, String key_rate, String key_flightStartDate, String key_participantType,
			String key_participant, String key_station, String paymentType, boolean OCIFlag, String... key_secureScc) {

		String messageSampleFilePath;
		String secureScc = "";

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		chargWt = PropertyHandler.getPropValue(dataFilePath, chargWt);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);
		if (rate.equalsIgnoreCase("null"))
			rate = null;
		messageSampleFilePath = messagePath + "FWB.txt";
		messageSampleFilePath = Message.createFWBMessageWithDiffChargWt(messageSampleFilePath, awbNoPrefix, awbNo,
				carrierCode, origin, destination, pieces, weight, chargWt, shipperName, consigneeName, agentCode,
				FullFlightNo, exportIATAAgentCode, rate, flightStartDate, paymentType, false);
		String status = uploadMessage(messageSampleFilePath, participantType, participant, station);
		processFWBMessage(awbNoPrefix, awbNo, status);

		return this;
	}

	public MSG005 uploadFWBMessageWithoutFlight(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_origin, String key_destination, String key_pieces, String key_weight, String key_shipperName,
			String key_consigneeName, String key_agentCode, String key_exportIATAAgentCode, String key_rate,
			String key_participantType, String key_participant, String key_station, String paymentType,
			String... key_secureScc) {

		String messageSampleFilePath;

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);
		if (rate.equalsIgnoreCase("null"))
			rate = null;
		messageSampleFilePath = messagePath + "FWB_withoutFlight.txt";
		if (key_secureScc.length != 0) {
			messageSampleFilePath = Message.createFWBMessageWithoutFlight(messageSampleFilePath, awbNoPrefix, awbNo,
					carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
					exportIATAAgentCode, rate, paymentType, key_secureScc[0]);
		} else {
			messageSampleFilePath = Message.createFWBMessageWithoutFlight(messageSampleFilePath, awbNoPrefix, awbNo,
					carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
					exportIATAAgentCode, rate, paymentType);
		}
		String status = uploadMessage(messageSampleFilePath, participantType, participant, station);
		processFWBMessage(awbNoPrefix, awbNo, status);

		return this;
	}

	/**
	 * Process a FWB with the given date difference
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_carrierCode
	 * @param key_origin
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_shipperName
	 * @param key_consigneeName
	 * @param key_agentCode
	 * @param key_exportIATAAgentCode
	 * @param key_rate
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @param paymentType
	 * @param dateDiff
	 * @return
	 */
	public MSG005 uploadFWBMessageWithDiffDate(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_origin, String key_destination, String key_pieces, String key_weight, String key_shipperName,
			String key_consigneeName, String key_agentCode, String key_exportIATAAgentCode, String key_rate,
			String key_participantType, String key_participant, String key_station, String paymentType, int dateDiff,
			boolean processSuccessful) {

		String messageSampleFilePath;

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);
		if (rate.equalsIgnoreCase("null"))
			rate = null;
		messageSampleFilePath = messagePath + "FWB_withoutFlight.txt";
		messageSampleFilePath = Message.createFWBMessageWithDiffDate(messageSampleFilePath, awbNoPrefix, awbNo,
				carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
				exportIATAAgentCode, rate, paymentType, dateDiff);
		String status = uploadMessage(messageSampleFilePath, participantType, participant, station);
		if (status.toLowerCase().contains("decoded successfully")) {
			processFWBMessage(awbNoPrefix, awbNo);
		} else if (status.toLowerCase().contains("processed successfully")) {

		} else if (status.toLowerCase().contains("processed  with errors") && !processSuccessful) {
			verifyDateErrorFWB(awbNoPrefix, awbNo);
		} else {
			Assert.assertTrue(false, "The message was not processed successfully");
		}
		return this;
	}

	/**
	 * FWB for multi flight shipments
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_carrierCode
	 * @param key_pieces
	 * @param key_weight
	 * @param key_shipperName
	 * @param key_consigneeName
	 * @param key_agentCode
	 * @param FullFlightNo
	 * @param FullFlightNo2
	 * @param key_exportIATAAgentCode
	 * @param key_rate
	 * @param key_flightStartDate
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @param paymentType
	 * @param OCIFlag
	 * @return
	 * @author A-7868 Krishna <23/02/2018>
	 */

	public MSG005 uploadFWBMessage(String key_awbNoPrefix, String key_awbNo, String key_carrierCode, String key_origin1,
			String key_dest1, String key_origin2, String key_dest2, String key_pieces, String key_weight,
			String key_shipperName, String key_consigneeName, String key_agentCode, String FullFlightNo,
			String FullFlightNo2, String key_exportIATAAgentCode, String key_rate, String key_flightStartDate,
			String key_participantType, String key_participant, String key_station, String paymentType,
			boolean OCIFlag) {

		String messageSampleFilePath;
		if (OCIFlag) {
			messageSampleFilePath = messagePath + "FWB_OCI_2F.txt";
		} else {
			messageSampleFilePath = messagePath + "FWB_2F.txt";
		}
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin1 = PropertyHandler.getPropValue(dataFilePath, key_origin1);
		String dest1 = PropertyHandler.getPropValue(dataFilePath, key_dest1);
		String origin2 = PropertyHandler.getPropValue(dataFilePath, key_origin2);
		String dest2 = PropertyHandler.getPropValue(dataFilePath, key_dest2);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		FullFlightNo2 = PropertyHandler.getPropValue(dataFilePath, FullFlightNo2);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);

		if (rate.equalsIgnoreCase("null"))
			rate = null;
		String messagePath = Message.createFWBMessage_2Flights(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
				origin1, dest1, origin2, dest2, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
				FullFlightNo2, exportIATAAgentCode, rate, flightStartDate, paymentType, OCIFlag);
		String status = uploadMessage(messagePath, participantType, participant, station);
		processFWBMessage(awbNoPrefix, awbNo, status);
		return this;
	}

	public MSG005 uploadFWBMessage_ErrorCase(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_origin, String key_destination, String key_pieces, String key_weight, String key_shipperName,
			String key_consigneeName, String key_agentCode, String FullFlightNo, String key_exportIATAAgentCode,
			String key_rate, String key_flightStartDate, String key_participantType, String key_participant,
			String key_station, String reason) {
		String messageSampleFilePath = messagePath + "FWB.txt";
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		reason = PropertyHandler.getPropValue(dataFilePath, reason);

		if (rate.equalsIgnoreCase("null"))
			rate = null;
		String messagePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode, origin,
				destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo, exportIATAAgentCode,
				rate, flightStartDate, "PP", false);
		uploadMessage(messagePath, participantType, participant, station);
		processFFR_Error(awbNoPrefix, awbNo, reason);
		return this;
	}

	/**
	 * Description : Method will check if FFM message is triggered
	 * 
	 * @param key_carrierCode
	 * @param key_flightNo
	 * @return current instance of MSG005
	 */
	public MSG005 checkIfFFMTriggered(String key_carrierCode, String key_flightNo) {
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String flightNo = PropertyHandler.getPropValue(dataFilePath, key_flightNo);

		boolean isFound = false;
		logger.info("Checking if FFM triggered");
		enterKeys(txt_messgeType, "FFM");
		click(btn_reference);
		minWait();
		// enterKeys(txt_awbpfx, awbNoPrefix);
		// enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		List<String> tableCellVals = getTableColumnValues(tbl_message, 2);
		for (String tableCellVal : tableCellVals) {
			if (tableCellVal.contains(carrierCode + "-" + flightNo)) {
				logger.info("FFM triggered");
				isFound = true;
				break;
			}
		}

		Assert.assertTrue(isFound,
				"FFM message is not triggered for the flight '" + carrierCode + "-" + flightNo + "'");
		return this;
	}

	/**
	 * Method to check no.of FFM triggered
	 * 
	 * @param key_carrierCode
	 * @param key_flightNo
	 * @param count
	 * @return
	 * @author A-7868 Krishna <20/03/2018>
	 */
	public MSG005 checkIfFFMTriggered(String key_carrierCode, String key_flightNo, String count) {

		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String flightNo = PropertyHandler.getPropValue(dataFilePath, key_flightNo);

		enterKeys(txt_messgeType, "FFM");
		click(btn_reference);
		minWait();
		By txt_fltNum = By.xpath("//td[contains(text(), 'FLTNUM')]/following::td[1]/input");
		By txt_cc = By.xpath("//td[contains(text(), 'CARCOD')]/following::td[1]/input");

		enterKeys(txt_cc, carrierCode);
		enterKeys(txt_fltNum, flightNo);
		click(btn_list);
		minWait();

		Assert.assertTrue(getTableRowCount(tbl_message) == Integer.parseInt(count));
		return this;
	}

	/**
	 * Description : Method will check if FSU message is triggered.
	 * messageSubType should be 'null' if no subtype check is required
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @return A-7681 added msg Super sub type
	 */

	public MSG005 checkIfFSUTriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType, String fromdate,
			String... msgSuperSubType) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		fromdate = PropertyHandler.getPropValue(dataFilePath, fromdate);
		boolean isFound = false;

		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FSU");
		enterKeys(txt_calFromDate, fromdate);
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		// if (messageSubType.equalsIgnoreCase("null"))
		// messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FSU triggered");
			isFound = true;
		} else {
			logger.info("FSU not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FSU message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");

		// Added by A-7681
		if (msgSuperSubType.length > 0) {

			WebElement temp = null;
			boolean flag = false;
			int noOfRows = getWebElements(list_checkBoxSelectMsg).size();

			for (int i = 0; i < noOfRows; i++) {

				temp = getWebElements(list_checkBoxSelectMsg).get(i);
				check(temp);
				click(btn_view);
				waitForNewWindow(2);
				switchToSecondWindow();

				if (waitForElement(txt_viewMsg).getText().contains(msgSuperSubType[0])) {
					flag = true;
				}
				logger.info("Message content is :" + waitForElement(txt_viewMsg).getText());
				click(btn_viewClose);
				switchToFirstWindow();
				waitForFrameAndSwitch(contentFrame);
				if (flag) {
					break;
				}

			}
			Assert.assertTrue(flag, "The FSU with super sub type " + msgSuperSubType[0] + "  not triggered");

		}

		return this;
	}

	/**
	 * Description : Method will check if XFSU message is triggered.
	 * messageSubType should be 'null' if no subtype check is required
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @return A-7868 Krishna <11/05/2018>
	 */

	public MSG005 checkIfxFSUTriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType,
			String fromdate) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		fromdate = PropertyHandler.getPropValue(dataFilePath, fromdate);
		boolean isFound = false;

		logger.info("Checking if XFSU triggered");
		enterKeys(txt_messgeType, "XFSU");
		enterKeys(txt_calFromDate, fromdate);
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);

		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FSU triggered");
			isFound = true;
		} else {
			logger.info("FSU not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "XFSU message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");

		return this;
	}

	/****
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @param msgSuperSubType
	 * @return
	 */
	public MSG005 checkFSUnotTriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType,
			String... msgSuperSubType) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);

		enterKeys(txt_messgeType, "FSU");

		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();

		Assert.assertFalse(getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo),
				"Error:FSU message is triggered for the AWB");

		return this;

	}

	/*****
	 * 
	 * @param messageSubType
	 * @return
	 */
	public MSG005 checkIfFSUTriggered_Dis(String messageSubType) {

		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);

		boolean isFound = false;
		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FSU");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}

		minWait();
		click(btn_list);
		minWait();
		System.out.println(getTableColumnValues(tbl_message, 2));
		if (getTableColumnValues(tbl_message, 9).contains("Sending")) {
			logger.info("FSU triggered");
			isFound = true;
		} else {
			logger.info("FSU not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FSU message is  triggered for the ULD");
		return this;
	}

	/***
	 * A-6784
	 * 
	 * @param messageSubType
	 * @return
	 */
	public MSG005 checkFSUTriggeredwithSubtype(String messageSubType) {

		boolean isFound = false;
		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FSU");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}

		minWait();
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 9).contains("Sending")) {
			logger.info("FSU triggered");
			isFound = true;
		} else {
			logger.info("FSU not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FSU message is  triggered for the ULD");
		return this;
	}

	/**
	 * Description : Method will check if FNA message is triggered.
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @return A-6784
	 */

	public MSG005 checkIfFNATriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType) {
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);

		boolean isFound = false;
		logger.info("Checking if FNA triggered");
		enterKeys(txt_messgeType, "FNA");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		// enterKeys(txt_awbpfx, awbNoPrefix);
		// enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FNA triggered");
			isFound = true;
		} else {
			logger.info("FNA not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FNA message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		return this;
	}

	/***
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @return
	 */

	public MSG005 checkIfFMATriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType) {
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);

		boolean isFound = false;
		logger.info("Checking if FMA triggered");
		enterKeys(txt_messgeType, "FMA");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		// enterKeys(txt_awbpfx, awbNoPrefix);
		// enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FMA triggered");
			isFound = true;
		} else {
			logger.info("FMA not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FMA message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		return this;
	}

	/**
	 * Created by a-7605 on 03/01/2017
	 * 
	 * @param sampleMessageFileName
	 *            TODO
	 */

	public MSG005 uploadFFMMessage(String awbPrefix, String fullFlightNo, String flightStartDate, String origin,
			String destination, String awbNo1, String pieces1, String weight1, String volume1, String uldNo,
			String awbNo2, String pieces2, String weight2, String volume2, String awbNo3, String pieces3,
			String weight3, String volume3, String participantType, String participant, String station,
			String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		pieces2 = PropertyHandler.getPropValue(dataFilePath, pieces2);
		weight2 = PropertyHandler.getPropValue(dataFilePath, weight2);
		volume2 = PropertyHandler.getPropValue(dataFilePath, volume2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		pieces3 = PropertyHandler.getPropValue(dataFilePath, pieces3);
		weight3 = PropertyHandler.getPropValue(dataFilePath, weight3);
		volume3 = PropertyHandler.getPropValue(dataFilePath, volume3);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String messagePath = Message.createFFMMessage(messageSampleFilePath, fullFlightNo, flightStartDate, origin,
				destination, fullAWBNo1, pieces1, weight1, volume1, uldNo, fullAWBNo2, pieces2, weight2, volume2,
				fullAWBNo3, pieces3, weight3, volume3);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Created by A-7605
	 * 
	 * @param awbPrefix
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param awbNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param uldNo1
	 * @param awbNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo2
	 * @param awbNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param participantType
	 * @param participant
	 * @param station
	 * @param sampleMessageFileName
	 * @return
	 */

	public MSG005 uploadFFMMessage_1B_2ULD(String awbPrefix, String fullFlightNo, String flightStartDate, String origin,
			String destination, String awbNo1, String pieces1, String weight1, String volume1, String uldNo1,
			String awbNo2, String pieces2, String weight2, String volume2, String uldNo2, String awbNo3, String pieces3,
			String weight3, String volume3, String participantType, String participant, String station,
			String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo1 = PropertyHandler.getPropValue(dataFilePath, uldNo1);
		uldNo2 = PropertyHandler.getPropValue(dataFilePath, uldNo2);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		pieces2 = PropertyHandler.getPropValue(dataFilePath, pieces2);
		weight2 = PropertyHandler.getPropValue(dataFilePath, weight2);
		volume2 = PropertyHandler.getPropValue(dataFilePath, volume2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		pieces3 = PropertyHandler.getPropValue(dataFilePath, pieces3);
		weight3 = PropertyHandler.getPropValue(dataFilePath, weight3);
		volume3 = PropertyHandler.getPropValue(dataFilePath, volume3);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String messagePath = Message.createFFMMessage_1B_2ULD(messageSampleFilePath, fullFlightNo, flightStartDate,
				origin, destination, fullAWBNo1, pieces1, weight1, volume1, uldNo1, fullAWBNo2, pieces2, weight2,
				volume2, uldNo2, fullAWBNo3, pieces3, weight3, volume3);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Created by A-7605
	 * 
	 * @param awbPrefix
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param awbNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param uldNo1
	 * @param awbNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo2
	 * @param awbNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param participantType
	 * @param participant
	 * @param station
	 * @param sampleMessageFileName
	 * @return
	 */
	public MSG005 uploadFFMMessage_1B_5ULD(String awbPrefix, String fullFlightNo, String flightOrigin,
			String flightDestination, String flightStartDate, String awbNo1, String awb1Origin, String awb1Destination,
			String pieces1, String weight1, String volume1, String uldNo1, String awbNo2, String awb2Origin,
			String awb2Destination, String pieces2, String weight2, String volume2, String uldNo2, String awbNo3,
			String awb3Origin, String awb3Destination, String pieces3, String weight3, String volume3, String uldNo3,
			String awbNo4, String awb4Origin, String awb4Destination, String pieces4, String weight4, String volume4,
			String uldNo4, String awbNo5, String awb5Origin, String awb5Destination, String pieces5, String weight5,
			String volume5, String uldNo5, String awbNo6, String awb6Origin, String awb6Destination, String pieces6,
			String weight6, String volume6, String participantType, String participant, String station,
			String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo1 = PropertyHandler.getPropValue(dataFilePath, uldNo1);
		uldNo2 = PropertyHandler.getPropValue(dataFilePath, uldNo2);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		pieces2 = PropertyHandler.getPropValue(dataFilePath, pieces2);
		weight2 = PropertyHandler.getPropValue(dataFilePath, weight2);
		volume2 = PropertyHandler.getPropValue(dataFilePath, volume2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		pieces3 = PropertyHandler.getPropValue(dataFilePath, pieces3);
		weight3 = PropertyHandler.getPropValue(dataFilePath, weight3);
		volume3 = PropertyHandler.getPropValue(dataFilePath, volume3);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		flightOrigin = PropertyHandler.getPropValue(dataFilePath, flightOrigin);
		flightDestination = PropertyHandler.getPropValue(dataFilePath, flightDestination);
		awb1Origin = PropertyHandler.getPropValue(dataFilePath, awb1Origin);
		awb1Destination = PropertyHandler.getPropValue(dataFilePath, awb1Destination);
		awb2Origin = PropertyHandler.getPropValue(dataFilePath, awb2Origin);
		awb2Destination = PropertyHandler.getPropValue(dataFilePath, awb2Destination);
		awb3Origin = PropertyHandler.getPropValue(dataFilePath, awb3Origin);
		awb3Destination = PropertyHandler.getPropValue(dataFilePath, awb3Destination);
		uldNo3 = PropertyHandler.getPropValue(dataFilePath, uldNo3);
		awb4Origin = PropertyHandler.getPropValue(dataFilePath, awb4Origin);
		awb4Destination = PropertyHandler.getPropValue(dataFilePath, awb4Destination);
		pieces4 = PropertyHandler.getPropValue(dataFilePath, pieces4);
		weight4 = PropertyHandler.getPropValue(dataFilePath, weight4);
		volume4 = PropertyHandler.getPropValue(dataFilePath, volume4);
		uldNo4 = PropertyHandler.getPropValue(dataFilePath, uldNo4);
		awb5Origin = PropertyHandler.getPropValue(dataFilePath, awb5Origin);
		awb5Destination = PropertyHandler.getPropValue(dataFilePath, awb5Destination);
		pieces5 = PropertyHandler.getPropValue(dataFilePath, pieces5);
		weight5 = PropertyHandler.getPropValue(dataFilePath, weight5);
		volume5 = PropertyHandler.getPropValue(dataFilePath, volume5);
		uldNo5 = PropertyHandler.getPropValue(dataFilePath, uldNo5);
		awb6Origin = PropertyHandler.getPropValue(dataFilePath, awb6Origin);
		awb6Destination = PropertyHandler.getPropValue(dataFilePath, awb6Destination);
		pieces6 = PropertyHandler.getPropValue(dataFilePath, pieces6);
		weight6 = PropertyHandler.getPropValue(dataFilePath, weight6);
		volume6 = PropertyHandler.getPropValue(dataFilePath, volume6);
		awbNo4 = PropertyHandler.getPropValue(dataFilePath, awbNo4);
		awbNo5 = PropertyHandler.getPropValue(dataFilePath, awbNo5);
		awbNo6 = PropertyHandler.getPropValue(dataFilePath, awbNo6);

		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String fullAWBNo4 = awbPrefix + "-" + awbNo4;
		String fullAWBNo5 = awbPrefix + "-" + awbNo5;
		String fullAWBNo6 = awbPrefix + "-" + awbNo6;
		String messagePath = Message.createFFMMessage_1B_5ULD(messageSampleFilePath, fullFlightNo, flightStartDate,
				flightOrigin, flightDestination, fullAWBNo1, awb1Origin, awb1Destination, pieces1, weight1, volume1,
				uldNo1, fullAWBNo2, awb2Origin, awb2Destination, pieces2, weight2, volume2, uldNo2, fullAWBNo3,
				awb3Origin, awb3Destination, pieces3, weight3, volume3, uldNo3, fullAWBNo4, awb4Origin, awb4Destination,
				pieces4, weight4, volume4, uldNo4, fullAWBNo5, awb5Origin, awb5Destination, pieces5, weight5, volume5,
				uldNo5, fullAWBNo6, awb6Origin, awb6Destination, pieces6, weight6, volume6);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Overloaded for one awb in Bulk Created by a-7681 on 12/01/2017
	 */

	public MSG005 uploadFFMMessage(String awbPrefix, String fullFlightNo, String flightStartDate, String origin,
			String destination, String awbNo1, String pieces1, String weight1, String volume1, String participantType,
			String participant, String station) {
		String messageSampleFilePath = messagePath + "FFM_1.txt";
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String messagePath = Message.createFFMMessage(messageSampleFilePath, fullFlightNo, flightStartDate, origin,
				destination, fullAWBNo1, pieces1, weight1, volume1);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Description : Method will check the number of FSU message triggered.
	 * messageSubType should be 'null' if no sub type check is required
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @param triggerCount
	 * @return
	 */

	public MSG005 checkIfMultipleFSUTriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType,
			int triggerCount) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);

		boolean isFound = false;
		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FSU");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0)
			selectByText(dropdown_messageSubType, messageSubType);

		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();

		int count = 0;
		String[] values = getTableColumnValues(tbl_message, 2)
				.toArray(new String[getTableColumnValues(tbl_message, 2).size()]);
		for (String val : values)
			if (val.equals(awbNoPrefix + " - " + awbNo))
				count++;

		if (count == triggerCount)
			isFound = true;
		else
			isFound = false;

		Assert.assertTrue(isFound, "FSU message is not triggered for " + triggerCount + " times for the AWB '"
				+ awbNoPrefix + "-" + awbNo + "'");
		return this;
	}

	/**
	 * Checks if FUM triggered for the flight
	 * 
	 * @param carrCode
	 * @param fltNo
	 * @return
	 * @authoe Arun A-7681
	 */
	public MSG005 checkIfFUMTriggered(String carrCode, String fltNo) {

		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		enterKeys(txt_messgeType, "FUM");
		click(btn_list);

		WebElement temp = null;
		boolean flag = false;
		int noOfRows = getWebElements(list_checkBoxSelectMsg).size();

		for (int i = 0; i < noOfRows; i++) {

			temp = getWebElements(list_checkBoxSelectMsg).get(i);
			check(temp);
			click(btn_view);
			waitForNewWindow(2);
			switchToSecondWindow();

			if (waitForElement(txt_viewMsg).getText().contains(carrCode + fltNo)) {
				flag = true;
			}

			click(btn_viewClose);
			switchToFirstWindow();
			waitForFrameAndSwitch(contentFrame);
			if (flag) {
				break;
			}

		}
		Assert.assertTrue(flag, "The FUM not triggered for Flight:" + fltNo);
		return this;
	}

	public HomePage close() {
		logger.info("Closing MSG005 screen");
		click(btn_close);
		logger.info("MSG005 screen closed");
		driver.switchTo().defaultContent();

		return new HomePage(driver, dataFileName, test);
	}

	/**
	 * Created By A-7605 on 31/01/18 This method will create an FFM message that
	 * contain 2 Bulk shipments and 2 shipments in a single ULD. This method
	 * will create, upload and process the message
	 * 
	 * @param awbPrefix
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param awbNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param awbNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param awbNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param participantType
	 * @param participant
	 * @param station
	 * @param sampleMessageFileName
	 * @return
	 */
	public MSG005 uploadFFMMessage_2B_1ULD_2AWBs(String awbPrefix, String fullFlightNo, String flightStartDate,
			String origin, String destination, String awbNo1, String pieces1, String weight1, String volume1,
			String awbNo2, String pieces2, String weight2, String volume2, String uldNo, String awbNo3, String pieces3,
			String weight3, String volume3, String awbNo4, String pieces4, String weight4, String volume4,
			String participantType, String participant, String station, String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		pieces2 = PropertyHandler.getPropValue(dataFilePath, pieces2);
		weight2 = PropertyHandler.getPropValue(dataFilePath, weight2);
		volume2 = PropertyHandler.getPropValue(dataFilePath, volume2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		pieces3 = PropertyHandler.getPropValue(dataFilePath, pieces3);
		weight3 = PropertyHandler.getPropValue(dataFilePath, weight3);
		volume3 = PropertyHandler.getPropValue(dataFilePath, volume3);
		awbNo4 = PropertyHandler.getPropValue(dataFilePath, awbNo4);
		pieces4 = PropertyHandler.getPropValue(dataFilePath, pieces4);
		weight4 = PropertyHandler.getPropValue(dataFilePath, weight4);
		volume4 = PropertyHandler.getPropValue(dataFilePath, volume4);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String fullAWBNo4 = awbPrefix + "-" + awbNo4;
		String messagePath = Message.createFFMMessage_2B_2ShipmentsInOneULD(messageSampleFilePath, fullFlightNo,
				flightStartDate, origin, destination, fullAWBNo1, pieces1, weight1, volume1, fullAWBNo2, pieces2,
				weight2, volume2, uldNo, fullAWBNo3, pieces3, weight3, volume3, fullAWBNo4, pieces4, weight4, volume4);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Created By A-6545 on 02/02/18 This method will create an FFM message that
	 * contain 1 Bulk shipments and 1 shipments in a single ULD. This method
	 * will create, upload and process the message
	 * 
	 * @param awbPrefix
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param awbNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param participantType
	 * @param participant
	 * @param station
	 * @param sampleMessageFileName
	 * @return
	 */
	public MSG005 uploadFFMMessage_1ULD_3AWBs(String awbPrefix, String fullFlightNo, String flightStartDate,
			String origin, String destination, String awbNo1, String pieces1, String weight1, String volume1,
			String awbNo2, String ULDNo, String ULDNo2, String ULDNo3, String participantType, String participant,
			String station, String awbNo3, String awbNo4, String sampleMessageFileName, String uldType,
			String compCode) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		uldType = PropertyHandler.getPropValue(dataFilePath, uldType);
		compCode = PropertyHandler.getPropValue(dataFilePath, compCode);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		String uldNo1 = BizUtility.createULDNum(uldType, compCode);
		String uldNo2 = BizUtility.createULDNum(uldType, compCode);
		String uldNo3 = BizUtility.createULDNum(uldType, compCode);
		PropertyHandler.setPropValue(dataFilePath, ULDNo, uldNo1);
		PropertyHandler.setPropValue(dataFilePath, ULDNo2, uldNo2);
		PropertyHandler.setPropValue(dataFilePath, ULDNo3, uldNo3);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		ULDNo = PropertyHandler.getPropValue(dataFilePath, ULDNo);
		ULDNo2 = PropertyHandler.getPropValue(dataFilePath, ULDNo2);
		ULDNo3 = PropertyHandler.getPropValue(dataFilePath, ULDNo3);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		awbNo4 = PropertyHandler.getPropValue(dataFilePath, awbNo4);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String fullAWBNo4 = awbPrefix + "-" + awbNo4;

		String messagePath = Message.createFFMMessage_1B_3ShipmentsInOneULD(messageSampleFilePath, fullFlightNo,
				flightStartDate, origin, destination, fullAWBNo1, pieces1, weight1, volume1, fullAWBNo2, pieces1,
				weight1, volume1, uldNo1, uldNo2, uldNo3, fullAWBNo3, pieces1, weight1, volume1, fullAWBNo4, pieces1,
				weight1, volume1);
		uploadMessage(messagePath, participantType, participant, station);
		;
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Created By A-6545 on 02/02/18 This method will create an FFM message that
	 * contain 1 Bulk shipments and 3 shipments in a single ULD. This method
	 * will create, upload and process the message
	 * 
	 * @param awbPrefix
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param awbNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param participantType
	 * @param participant
	 * @param station
	 * @param sampleMessageFileName
	 * @return
	 */
	public MSG005 uploadFFMMessage_1ULD_1AWBs(String awbPrefix, String fullFlightNo, String flightStartDate,
			String origin, String destination, String awbNo1, String pieces1, String weight1, String volume1,
			String awbNo2, String uldNo, String participantType, String participant, String station,
			String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;

		String messagePath = Message.createFFMMessage_1B_1ULD(messageSampleFilePath, fullFlightNo, flightStartDate,
				origin, destination, fullAWBNo1, origin, destination, pieces1, weight1, volume1, uldNo, fullAWBNo2);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	public MSG005 uploadFFMMessage_1B_1ULD_4AWBs(String awbPrefix, String fullFlightNo, String flightStartDate,
			String origin, String destination, String awbNo1, String pieces1, String weight1, String volume1,
			String awbNo2, String pieces2, String weight2, String volume2, String uldNo, String awbNo3, String pieces3,
			String weight3, String volume3, String awbNo4, String pieces4, String weight4, String volume4,
			String awbNo5, String pieces5, String weight5, String volume5, String participantType, String participant,
			String station, String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		pieces2 = PropertyHandler.getPropValue(dataFilePath, pieces2);
		weight2 = PropertyHandler.getPropValue(dataFilePath, weight2);
		volume2 = PropertyHandler.getPropValue(dataFilePath, volume2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		pieces3 = PropertyHandler.getPropValue(dataFilePath, pieces3);
		weight3 = PropertyHandler.getPropValue(dataFilePath, weight3);
		volume3 = PropertyHandler.getPropValue(dataFilePath, volume3);
		awbNo4 = PropertyHandler.getPropValue(dataFilePath, awbNo4);
		pieces4 = PropertyHandler.getPropValue(dataFilePath, pieces4);
		weight4 = PropertyHandler.getPropValue(dataFilePath, weight4);
		volume4 = PropertyHandler.getPropValue(dataFilePath, volume4);
		awbNo5 = PropertyHandler.getPropValue(dataFilePath, awbNo5);
		pieces5 = PropertyHandler.getPropValue(dataFilePath, pieces5);
		weight5 = PropertyHandler.getPropValue(dataFilePath, weight5);
		volume5 = PropertyHandler.getPropValue(dataFilePath, volume5);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String fullAWBNo4 = awbPrefix + "-" + awbNo4;
		String fullAWBNo5 = awbPrefix + "-" + awbNo5;
		String messagePath = Message.createFFMMessage_1B_4ShipmentsInOneULD(messageSampleFilePath, fullFlightNo,
				flightStartDate, origin, destination, fullAWBNo1, pieces1, weight1, volume1, fullAWBNo2, pieces2,
				weight2, volume2, uldNo, fullAWBNo3, pieces3, weight3, volume3, fullAWBNo4, pieces4, weight4, volume4,
				fullAWBNo5, pieces5, weight5, volume5);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	public MSG005 clear() {
		minWait();
		if (!verifyElementVisible(btn_clear))
			click(By.xpath("//*[@id='main_filter_Info_Id']/div/div/div/div/div[2]/a")); // edit
																						// button
																						// to
																						// display
																						// the
																						// filter
		click(btn_clear);
		driver.switchTo().defaultContent();
		if (verifyElementPresent(yesBtn)) {

			click(yesBtn);
		}
		waitForFrameAndSwitch(contentFrame);
		return this;
	}

	public MSG005 verifyFieldsAreCleared() {
		Assert.assertEquals(waitForElement(txt_messgeType).getText().trim().length(), 0);
		return this;
	}

	/**
	 * Created by A-7605 This method is used to check if FSU is triggered and
	 * particular FSU subtype is not triggered
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @param fromdate
	 * @param msgSuperSubType
	 * @return
	 */

	public MSG005 verifyParticularFSUSubtypeNotTriggered(String key_awbNoPrefix, String key_awbNo,
			String messageSubType, String fromdate, String... msgSuperSubType) {
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		fromdate = PropertyHandler.getPropValue(dataFilePath, fromdate);
		boolean isFound = false;
		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FSU");
		enterKeys(txt_calFromDate, fromdate);
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		// if (messageSubType.equalsIgnoreCase("null"))
		// messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FSU triggered");
			isFound = true;
		} else {
			logger.info("FSU not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FSU message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		WebElement temp = null;
		boolean flag = false;
		int noOfRows = getWebElements(list_checkBoxSelectMsg).size();

		for (int i = 0; i < noOfRows; i++) {

			temp = getWebElements(list_checkBoxSelectMsg).get(i);
			check(temp);
			click(btn_view);
			waitForNewWindow(2);
			switchToSecondWindow();

			if (waitForElement(txt_viewMsg).getText().contains(msgSuperSubType[0])) {
				flag = true;
			}

			click(btn_viewClose);
			switchToFirstWindow();
			waitForFrameAndSwitch(contentFrame);
			if (flag) {
				break;
			}

		}
		Assert.assertFalse(flag, "The FSU with super sub type " + msgSuperSubType[0] + " triggered");
		return this;
	}

	/**
	 * Created by A-7605 on 12-2-18 This method will check if FSU message is
	 * triggered, it also compares the number of message triggered for
	 * particular AWB
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param messageSubType
	 * @param fromdate
	 * @param expectedNumberOfMessagesTriggered
	 * @param msgSuperSubType
	 * @return
	 */
	public MSG005 verifyNumberOfFSUTriggerd(String awbNoPrefix, String awbNo, String messageSubType, String fromdate,
			String expectedNumberOfMessagesTriggered, String... msgSuperSubType) {
		awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, awbNoPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		fromdate = PropertyHandler.getPropValue(dataFilePath, fromdate);
		int expectedMessageTriggerCount = Integer.parseInt(expectedNumberOfMessagesTriggered);
		boolean isFound = false;
		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FSU");
		enterKeys(txt_calFromDate, fromdate);
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		// if (messageSubType.equalsIgnoreCase("null"))
		// messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FSU triggered");
			isFound = true;
		} else {
			logger.info("FSU not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FSU message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		Assert.assertEquals(getWebElements(list_checkBoxSelectMsg).size(), expectedMessageTriggerCount);
		// Added by A-7681
		if (msgSuperSubType.length > 0) {

			WebElement temp = null;
			boolean flag = false;
			int noOfRows = getWebElements(list_checkBoxSelectMsg).size();

			for (int i = 0; i < noOfRows; i++) {

				temp = getWebElements(list_checkBoxSelectMsg).get(i);
				check(temp);
				click(btn_view);
				waitForNewWindow(2);
				switchToSecondWindow();

				if (waitForElement(txt_viewMsg).getText().contains(msgSuperSubType[0])) {
					flag = true;
				}

				click(btn_viewClose);
				switchToFirstWindow();
				waitForFrameAndSwitch(contentFrame);
				if (flag) {
					break;
				}

			}
			Assert.assertTrue(flag, "The FSU with super sub type " + msgSuperSubType[0] + "  not triggered");

		}

		return this;
	}

	/**
	 * Method to check if FBL message is triggered and if so, check whether it
	 * is in SENT status
	 * 
	 * @param carrCode
	 * @param fltNo
	 * @param fltDt
	 * @param origin
	 * @return
	 * @author A-7868 Krishna <16/02/2018>
	 */
	public MSG005 checkIfFBLTriggered(String carrCode, String fltNo, String fltDt, String origin) {

		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);

		enterKeys(txt_messgeType, "FBL");
		minWait();
		click(btn_list);
		minWait();

		String[] date = fltDt.split("-");

		List<String> list = getTableColumnValues(tbl_message, 2);
		for (String str : list) {
			if (!str.equals(
					carrCode + " - " + fltNo + " - " + date[0].trim() + " - " + date[1].trim() + " - " + origin))
				Assert.fail("ERROR : FBL Message NOT Trigerred");
		}
		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				carrCode + " - " + fltNo + " - " + date[0].trim() + " - " + date[1].trim() + " - " + origin);
		Assert.assertTrue(msgStatus.contains("SENT"), "ERROR : FBL Message Not sent");
		return this;

	}

	/**
	 * Method to check if FBR message is triggered and if so, check whether it
	 * is in SENT status
	 * 
	 * @param carrCode
	 * @param fltNo
	 * @param fltDt
	 * @param origin
	 * @return
	 * @author A-7868 Krishna <16/02/2018>
	 */
	public MSG005 checkIfFBRTriggered(String carrCode, String fltNo, String fltDt, String origin) {

		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);

		enterKeys(txt_messgeType, "FBR");
		minWait();
		click(btn_list);
		minWait();

		String[] date = fltDt.split("-");

		List<String> list = getTableColumnValues(tbl_message, 2);
		for (String str : list) {
			if (!str.equals(
					carrCode + " - " + fltNo + " - " + date[0].trim() + " - " + date[1].trim() + " - " + origin))
				Assert.fail("ERROR : FBL Message NOT Trigerred");
		}
		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				carrCode + " - " + fltNo + " - " + date[0].trim() + " - " + date[1].trim() + " - " + origin);
		Assert.assertTrue(msgStatus.contains("SENT"), "ERROR : FBR Message Not sent");
		return this;

	}

	/**
	 * Method to check if FWB message is triggered (Status = Sending)
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @return
	 * @author A-7868 Krishna <22/02/2018>
	 */
	public MSG005 checkIfFWBTriggered(String awbPre, String awbNo, String origin, String dest, String... count) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);

		enterKeys(txt_messgeType, "FWB");
		minWait();
		click(btn_list);
		minWait();

		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				awbPre + " - " + awbNo + " - " + origin + " - " + dest);
		Assert.assertTrue(msgStatus.contains("Sending") || msgStatus.contains("Sent"), "ERROR : FWB Message Not sent");

		return this;

	}

	/**
	 * Method to check no. of FWB message triggered (Status = Sending)
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @return
	 * @author A-7868 Krishna <20/03/2018>
	 */
	public MSG005 checkIfFWBTriggered(String awbPre, String awbNo, String origin, String dest, String count) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);

		By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
		By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

		enterKeys(txt_messgeType, "FWB");
		click(btn_reference);
		enterKeys(txt_awbPre, awbPre);
		enterKeys(txt_awbNo, awbNo);
		minWait();
		click(btn_list);
		minWait();

		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				awbPre + " - " + awbNo + " - " + origin + " - " + dest);
		Assert.assertTrue(msgStatus.contains("Sending"), "ERROR : FWB Message Not sent");

		Assert.assertTrue(getTableRowCount(tbl_message) == Integer.parseInt(count), "ERROR : Count mismatch.");
		return this;

	}

	/**
	 * Method to check if FHL is triggered for two houses (Status : Sending)
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param origin
	 * @param dest
	 * @param house1
	 * @param house2
	 * @return
	 * @author A-7868 Krishna <22/02/2018>
	 */
	public MSG005 checkIfFHLTriggered(String awbPre, String awbNo, String origin, String dest, String house1,
			String house2) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		house1 = PropertyHandler.getPropValue(dataFilePath, house1);
		house2 = PropertyHandler.getPropValue(dataFilePath, house2);

		// to handle delay in FHL trigger
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterKeys(txt_messgeType, "FHL");
		click(btn_reference);
		minWait();
		/*
		 * enterKeys(txt_reference1, awbPre); enterKeys(txt_reference5, awbNo);
		 */
		click(btn_list);
		minWait();

		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				awbPre + " - " + awbNo + " - " + origin + " - " + dest + " - " + house1);
		Assert.assertTrue(msgStatus.contains("Sending"), "ERROR : FHL Message Not sent for House 1");
		msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				awbPre + " - " + awbNo + " - " + origin + " - " + dest + " - " + house2);
		Assert.assertTrue(msgStatus.contains("Sending") || msgStatus.contains("Sent"),
				"ERROR : FHL Message Not sent for House 2");

		return this;

	}

	/**
	 * Method to upload FSU-RCS message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param stdPcs
	 * @param stdWt
	 * @param accPcs
	 *            TODO
	 * @param accWt
	 *            TODO
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public MSG005 uploadFSURCSMessage(String FullAWBNo, String flightStartDate, String origin, String dest,
			String stdPcs, String stdWt, String accPcs, String accWt, String participantType, String participant,
			String station, String... uldNum) {

		String messageSampleFilePath = messagePath + "FSU_RCS.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		stdPcs = PropertyHandler.getPropValue(dataFilePath, stdPcs);
		stdWt = PropertyHandler.getPropValue(dataFilePath, stdWt);
		accPcs = PropertyHandler.getPropValue(dataFilePath, accPcs);
		accWt = PropertyHandler.getPropValue(dataFilePath, accWt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String uldList = "";
		boolean isULD = false;

		if (uldNum.length > 0)
			for (String uld : uldNum) {
				uld = PropertyHandler.getPropValue(dataFilePath, uld);
				uldList += "/" + uld;
				isULD = true;
			}

		String messagePath;
		if (isULD)
			messagePath = Message.createFSU_RCSMessage(messageSampleFilePath, FullAWBNo, flightStartDate, origin, dest,
					stdPcs, stdWt, accPcs, accWt, uldList);
		else
			messagePath = Message.createFSU_RCSMessage(messageSampleFilePath, FullAWBNo, flightStartDate, origin, dest,
					stdPcs, stdWt, accPcs, accWt);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Acceptance", FullAWBNo);
		return this;
	}

	/**
	 * Method to upload FSU_DLV message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 */
	public MSG005 uploadFSUDLVMessage(String FullAWBNo, String flightStartDate, String origin, String dest, String pcs,
			String wt, String participantType, String participant, String station) {

		String messageSampleFilePath = messagePath + "FSU_DLV.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = Message.createFSU_DLVMessage(messageSampleFilePath, FullAWBNo, flightStartDate, origin,
				dest, pcs, wt);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Delivery", FullAWBNo);
		return this;
	}

	/**
	 * Method to upload FSU-MAN message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param fullFlightNo
	 * @param origin
	 * @param dest
	 * @param depStn
	 * @param arrStn
	 * @param pcs
	 * @param wt
	 * @param depTime
	 * @param arrTime
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public MSG005 uploadFSUMANMessage(String FullAWBNo, String flightStartDate, String fullFlightNo, String origin,
			String dest, String depStn, String arrStn, String pcs, String wt, String depTime, String arrTime,
			String participantType, String participant, String station) {

		String messageSampleFilePath = messagePath + "FSU_MAN.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		depStn = PropertyHandler.getPropValue(dataFilePath, depStn);
		arrStn = PropertyHandler.getPropValue(dataFilePath, arrStn);
		depTime = PropertyHandler.getPropValue(dataFilePath, depTime);
		arrTime = PropertyHandler.getPropValue(dataFilePath, arrTime);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = Message.createFSU_MANMessage(messageSampleFilePath, FullAWBNo, fullFlightNo,
				flightStartDate, depStn, arrStn, depTime, arrTime, origin, dest, pcs, wt);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Manifest Details", FullAWBNo);
		return this;
	}

	/**
	 * Method to upload FSU_RCF message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param fullFlightNo
	 * @param origin
	 * @param dest
	 * @param arrStn
	 * @param pcs
	 * @param wt
	 * @param arrTime
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public MSG005 uploadFSURCFMessage(String FullAWBNo, String flightStartDate, String fullFlightNo, String origin,
			String dest, String arrStn, String pcs, String wt, String arrTime, String participantType,
			String participant, String station, String... uldNum) {

		String messageSampleFilePath = messagePath + "FSU_RCF.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		arrStn = PropertyHandler.getPropValue(dataFilePath, arrStn);
		arrTime = PropertyHandler.getPropValue(dataFilePath, arrTime);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String uldList = "";
		boolean isULD = false;

		if (uldNum.length > 0)
			for (String uld : uldNum) {
				uld = PropertyHandler.getPropValue(dataFilePath, uld);
				uldList += "/" + uld;
				isULD = true;
			}

		String messagePath;
		if (isULD)
			messagePath = Message.createFSU_RCFMessage(messageSampleFilePath, FullAWBNo, fullFlightNo, flightStartDate,
					arrStn, arrTime, origin, dest, pcs, wt, uldList);
		else
			messagePath = Message.createFSU_RCFMessage(messageSampleFilePath, FullAWBNo, fullFlightNo, flightStartDate,
					arrStn, arrTime, origin, dest, pcs, wt);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Breakdown", FullAWBNo);
		return this;
	}

	/**
	 * Method to upload FSU_RCT message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param arrStn
	 * @param pcs
	 * @param wt
	 * @param arrTime
	 * @param carrCode
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author a-7868 Krishna <06/02/2018>
	 */
	public MSG005 uploadFSURCTMessage(String FullAWBNo, String flightStartDate, String origin, String dest,
			String arrStn, String pcs, String wt, String arrTime, String carrCode, String participantType,
			String participant, String station, String... uldNum) {

		String messageSampleFilePath = messagePath + "FSU_RCT.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		arrStn = PropertyHandler.getPropValue(dataFilePath, arrStn);
		arrTime = PropertyHandler.getPropValue(dataFilePath, arrTime);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String uldList = "";
		boolean isULD = false;

		if (uldNum.length > 0)
			for (String uld : uldNum) {
				uld = PropertyHandler.getPropValue(dataFilePath, uld);
				uldList += "/" + uld;
				isULD = true;
			}

		String messagePath;

		if (isULD)
			messagePath = Message.createFSU_RCTMessage(messageSampleFilePath, FullAWBNo, flightStartDate, arrStn,
					arrTime, origin, dest, pcs, wt, carrCode, uldList);
		else
			messagePath = Message.createFSU_RCTMessage(messageSampleFilePath, FullAWBNo, flightStartDate, arrStn,
					arrTime, origin, dest, pcs, wt, carrCode);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Inbound CTM", FullAWBNo);
		return this;
	}

	/**
	 * Method to create upload and process FSU-TFD message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param currStn
	 * @param pcs
	 * @param wt
	 * @param carrCode
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna
	 */
	public MSG005 uploadFSUTFDMessage(String FullAWBNo, String flightStartDate, String origin, String dest,
			String currStn, String pcs, String wt, String carrCode, String participantType, String participant,
			String station) {

		String messageSampleFilePath = messagePath + "FSU_RCT.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		currStn = PropertyHandler.getPropValue(dataFilePath, currStn);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = Message.createFSU_TFDMessage(messageSampleFilePath, FullAWBNo, flightStartDate, currStn,
				origin, dest, pcs, wt, carrCode);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Outbound CTM", FullAWBNo);
		return this;
	}

	/**
	 * Method to create FSU-DIS message, upload and process it
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param currStn
	 * @param pcs
	 * @param wt
	 * @param fullFltNo
	 * @param disPcs
	 * @param disWt
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna
	 */
	public MSG005 uploadFSUDISMessage(String FullAWBNo, String flightStartDate, String origin, String dest,
			String currStn, String pcs, String wt, String fullFltNo, String disPcs, String disWt,
			String participantType, String participant, String station, String... subType) {

		String messageSampleFilePath = null;
		if (subType.length == 0) {
			messageSampleFilePath = messagePath + "FSU_DIS.txt";
		} else {
			messageSampleFilePath = messagePath + "FSU_DIS_FDCA.txt";
		}

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		currStn = PropertyHandler.getPropValue(dataFilePath, currStn);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		fullFltNo = PropertyHandler.getPropValue(dataFilePath, fullFltNo);
		disPcs = PropertyHandler.getPropValue(dataFilePath, disPcs);
		disWt = PropertyHandler.getPropValue(dataFilePath, disWt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = null;

		if (subType.length == 0) {
			messagePath = Message.createFSU_DISMessage(messageSampleFilePath, FullAWBNo, flightStartDate, fullFltNo,
					currStn, origin, dest, pcs, wt, disPcs, disWt);
		} else {
			messagePath = Message.createFSU_DIS_FDCAMessage(messageSampleFilePath, FullAWBNo, flightStartDate,
					fullFltNo, currStn, origin, dest, pcs, wt, disPcs);
		}
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Discrepancy", FullAWBNo);
		return this;
	}

	/**
	 * Description : Method will process FSU message
	 * 
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public void processFSUMessage(String msgSubType, String fullAWBNo) {
		logger.info("Processing FSURCS message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FSU");
		selectByText(dropdown_messageSubType, msgSubType);
		click(btn_reference);
		minWait();
		String[] awb = fullAWBNo.split("-");
		enterKeys(txt_awbpfx, awb[0]);
		enterKeys(txt_awbser, awb[1]);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FSU_RCS message processed");
		return;
	}

	/**
	 * Method to check whether the mentioned number of UWS message is triggered
	 * for the given Flight
	 * 
	 * @param fltNo
	 * @param msgCount
	 * @return
	 * @author a-7868 Krishna <16/03/2018>
	 */
	public MSG005 checkIfUWSTriggered(String fltNo, int msgCount) {

		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		By txt_fltNo = By.xpath("//td[contains(text(), 'FLTNUM')]/following::td[1]/input");
		enterKeys(txt_messgeType, "UWS");
		click(btn_reference);
		enterKeys(txt_fltNo, fltNo);
		click(btn_list);
		minWait();
		Assert.assertTrue(getTableColumnValues(tbl_message, 2).size() == msgCount, "ERROR : Message not triggered");
		return this;

	}

	/**
	 * @param messageSubType
	 * @param awbNo
	 * @param awbNoPrefix
	 * @return
	 * @author A-8257 Souvik 04-04-2019
	 */
	public MSG005 checkFSUTriggeredwithSubtypeWithAWB(String messageSubType, String awbNo, String awbNoPrefix) {

		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, awbNoPrefix);

		boolean isFound = false;
		enterKeys(txt_messgeType, "FSU");
		// selectByText(By.xpath("//select[@name='messageSubtype']"),messageSubType);
		minWait();
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		selectByText(By.xpath("//select[@name='messageSubtype']"), messageSubType);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			isFound = true;
			captureAndAddScreenshot();
		} else {
			isFound = false;
			captureAndAddScreenshot();
		}

		Assert.assertTrue(isFound, "FSU message was not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");

		return this;
	}

	public MSG005 checkFFRTriggeredWithAWB(String awbNo, String awbNoPrefix) {

		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, awbNoPrefix);

		boolean isFound = false;
		logger.info("Checking if FSU triggered");
		enterKeys(txt_messgeType, "FFR");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);

		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FFR triggered");
			isFound = true;
		} else {
			logger.info("FFR not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FFR message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");

		minWait();
		if (getTableColumnValues(tbl_message, 9).contains("Sending")) {
			logger.info("FFR triggered");
			isFound = true;
		} else {
			logger.info("FFR not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FFR message is not triggered for the ULD");
		return this;
	}

	/**
	 * This message will upload FWB with transhipment details
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_carrierCode
	 * @param key_carrierCode_2
	 * @param key_origin
	 * @param key_intermediate
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_shipperName
	 * @param key_consigneeName
	 * @param key_agentCode
	 * @param FullFlightNo
	 * @param key_exportIATAAgentCode
	 * @param key_rate
	 * @param key_flightStartDate
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @param paymentType
	 * @param key_secureScc
	 * @return
	 * @author A-8260
	 */
	public MSG005 uploadFWBMessage_Transhipment(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_carrierCode_2, String key_origin, String key_intermediate, String key_destination,
			String key_pieces, String key_weight, String key_shipperName, String key_consigneeName,
			String key_agentCode, String FullFlightNo, String key_exportIATAAgentCode, String key_rate,
			String key_flightStartDate, String key_participantType, String key_participant, String key_station,
			String paymentType, String... key_secureScc) {

		String messageSampleFilePath = messagePath + "FWB_TRNS.txt";

		String secureScc = null;
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String carriercode_2 = PropertyHandler.getPropValue(dataFilePath, key_carrierCode_2);
		String intermediate = PropertyHandler.getPropValue(dataFilePath, key_intermediate);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);

		if (key_secureScc.length != 0)
			secureScc = PropertyHandler.getPropValue(dataFilePath, key_secureScc[0]);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);

		String messagePath;
		if (rate.equalsIgnoreCase("null"))
			rate = null;

		messagePath = Message.createFWBMessage_Transhipment(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
				carriercode_2, origin, intermediate, destination, pieces, weight, shipperName, consigneeName, agentCode,
				FullFlightNo, exportIATAAgentCode, rate, flightStartDate, paymentType);
		String status = uploadMessage(messagePath, participantType, participant, station);
		processFWBMessage(awbNoPrefix, awbNo, status);
		return this;
	}

	/**
	 * this message is used to upload FWB with screening method details
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param key_carrierCode
	 * @param key_origin
	 * @param key_destination
	 * @param key_pieces
	 * @param key_weight
	 * @param key_shipperName
	 * @param key_consigneeName
	 * @param key_agentCode
	 * @param FullFlightNo
	 * @param key_exportIATAAgentCode
	 * @param key_rate
	 * @param key_flightStartDate
	 * @param key_participantType
	 * @param key_participant
	 * @param key_station
	 * @param paymentType
	 * @param OCIFlag
	 * @param key_secureScc
	 * @return
	 * @author A-8260
	 */
	public MSG005 uploadFWBMessage_OCIScreeningMethod(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_origin, String key_destination, String key_pieces, String key_weight, String key_shipperName,
			String key_consigneeName, String key_agentCode, String FullFlightNo, String key_exportIATAAgentCode,
			String key_rate, String key_flightStartDate, String key_participantType, String key_participant,
			String key_station, String paymentType, boolean OCIFlag, String... key_secureScc) {

		String messageSampleFilePath;
		String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());

		messageSampleFilePath = messagePath + "FWB_OCI_SCRMTD.txt";
		System.out.println("path of sample message: " + messageSampleFilePath);
		String secureScc = null;
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		if (key_secureScc.length != 0)
			secureScc = PropertyHandler.getPropValue(dataFilePath, key_secureScc[0]);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);

		String messagePath;
		if (rate.equalsIgnoreCase("null"))
			rate = null;
		if (key_secureScc.length != 0)
			messagePath = Message.createFWBMessage_OCIScreenMethod(messageSampleFilePath, awbNoPrefix, awbNo,
					carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
					FullFlightNo, exportIATAAgentCode, rate, flightStartDate, paymentType, false, secureScc);
		else
			messagePath = Message.createFWBMessage_OCIScreenMethod(messageSampleFilePath, awbNoPrefix, awbNo,
					carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
					FullFlightNo, exportIATAAgentCode, rate, flightStartDate, paymentType, false);
		String status = uploadMessage(messagePath, participantType, participant, station);
		processFWBMessage(awbNoPrefix, awbNo, status);
		return this;
	}

	/*
	 * A-8255
	 */

	public MSG005 verifyFWBAutoProcess(String messageStatus, String key_awbNoPrefix, String key_awbNo,
			String key_carrierCode, String key_origin, String key_destination, String key_pieces, String key_weight,
			String key_shipperName, String key_consigneeName, String key_agentCode, String FullFlightNo,
			String key_exportIATAAgentCode, String key_rate, String key_flightStartDate, String key_participantType,
			String key_participant, String key_station, String paymentType, boolean OCIFlag) {

		String messageSampleFilePath;

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);

		if (rate.equalsIgnoreCase("null"))
			rate = null;
		if (OCIFlag) {
			messageSampleFilePath = messagePath + "FWB_OCI.txt";
			String messagePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
					origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
					exportIATAAgentCode, rate, flightStartDate, paymentType, true);
			uploadMessage(messagePath, participantType, participant, station);

			logger.info("Verifying AutoProcess");
			waitForFrameAndSwitch(contentFrame);

			if (messageStatus.equalsIgnoreCase("Processed Successfully")) {
				enterKeys(txt_messgeType, "FWB");
				click(btn_reference);
				minWait();

				enterKeys(getWebElements(txt_list_referencevalue).get(0), awbNoPrefix);
				enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
				click(btn_list);

				List<String> tableCellVals = getTableColumnValues(tbl_message, 9);

				for (String tableCellVal : tableCellVals) {
					if (tableCellVal.contains("Processed Successfully")) {
						logger.info("FWB Processed Successfully");
						break;
					}
				}
			}

			if (messageStatus.equalsIgnoreCase("Processed With Errors")) {
				enterKeys(txt_messgeType, "FWB");
				click(btn_reference);
				minWait();

				enterKeys(getWebElements(txt_list_referencevalue).get(0), awbNoPrefix);
				enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
				click(btn_list);

				tblSelectRowByColValue(tbl_message, 1, 9, "Processed With Errors");
				click(btn_view);
				switchToSecondWindow();
				String desc = waitForElementVisible(info_ErrorDesc).getText();
				Assert.assertTrue(desc.contains("AWB is executed"), "AWB is executed::message not displayed");
				click(btn_viewClose);

			}

		} else {
			messageSampleFilePath = messagePath + "FWB.txt";
			String messagePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
					origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
					exportIATAAgentCode, rate, flightStartDate, paymentType, false);
			uploadMessage(messagePath, participantType, participant, station);

			logger.info("Verifying AutoProcess");
			waitForFrameAndSwitch(contentFrame);

			if (messageStatus.equalsIgnoreCase("Processed Successfully")) {

				enterKeys(txt_messgeType, "FWB");
				click(btn_reference);
				minWait();

				enterKeys(getWebElements(txt_list_referencevalue).get(0), awbNoPrefix);
				enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
				click(btn_list);

				List<String> tableCellVals = getTableColumnValues(tbl_message, 9);

				for (String tableCellVal : tableCellVals) {
					if (tableCellVal.contains("Processed Successfully")) {
						logger.info("FWB Processed Successfully");
						break;
					}

				}
			}

		}
		return this;
	}

	/*
	 * A-8255
	 */

	public MSG005 withinvalidFWBDetails(String key_awbNoPrefix, String key_awbNo, String key_carrierCode,
			String key_origin, String key_destination, String key_pieces, String key_weight, String key_shipperName,
			String key_consigneeName, String key_agentCode, String FullFlightNo, String key_exportIATAAgentCode,
			String key_rate, String key_flightStartDate, String key_participantType, String key_participant,
			String key_station, String paymentType, boolean OCIFlag, boolean invalidawb, boolean invalidorigin) {

		String messageSampleFilePath;

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);

		if (rate.equalsIgnoreCase("null"))
			rate = null;
		if (OCIFlag) {
			messageSampleFilePath = messagePath + "FWB_OCI.txt";
			String messagePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
					origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
					exportIATAAgentCode, rate, flightStartDate, paymentType, true);
			uploadMessage(messagePath, participantType, participant, station);
			waitForFrameAndSwitch(contentFrame);
			enterKeys(txt_messgeType, "FWB");
			click(btn_list);
			minWait();

			if (invalidawb) {
				tblSelectRowByColValue(tbl_message, 1, 11, "Missing element");
				click(btn_view);
				switchToSecondWindow();
				String desc = waitForElementVisible(info_ErrorDesc).getText();
				Assert.assertTrue(desc.contains("Missing element AWB serial number"),
						"Missing element AWB serial number::message not displayed");
				click(btn_viewClose);

			}
			if (invalidorigin) {
				tblSelectRowByColValue(tbl_message, 1, 11, "Invalid Origin");
				click(btn_view);
				switchToSecondWindow();
				String desc = waitForElementVisible(info_ErrorDesc).getText();
				Assert.assertTrue(desc.contains("Missing element Airport/City code(of Origin)"),
						"Missing element Airport/City code(of Origin)::message not displayed");
				click(btn_viewClose);
			}

			switchToFirstWindow();
			click(btn_process);

			Assert.assertTrue(waitForElement(Generic_info_error).getText()
					.contains("Cannot process a message with the Decoded With Errors status"));

		} else {
			messageSampleFilePath = messagePath + "FWB.txt";
			String messagePath = Message.createFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, carrierCode,
					origin, destination, pieces, weight, shipperName, consigneeName, agentCode, FullFlightNo,
					exportIATAAgentCode, rate, flightStartDate, paymentType, false);
			uploadMessage(messagePath, participantType, participant, station);

			waitForFrameAndSwitch(contentFrame);
			enterKeys(txt_messgeType, "FWB");
			click(btn_list);
			minWait();

			if (invalidawb) {
				tblSelectRowByColValue(tbl_message, 1, 11, "Missing element");
				click(btn_view);
				switchToSecondWindow();
				String desc = waitForElementVisible(info_ErrorDesc).getText();
				Assert.assertTrue(desc.contains("Missing element AWB serial number"),
						"Missing element AWB serial number::message not displayed");
				Assert.assertTrue(desc.contains("Missing element Airport/City code(of Origin)"),
						"Missing element Airport/City code(of Origin)::message not displayed");
				click(btn_viewClose);

			}
			if (invalidorigin) {
				tblSelectRowByColValue(tbl_message, 1, 11, "Invalid Origin");
				click(btn_view);
				switchToSecondWindow();
				String desc = waitForElementVisible(info_ErrorDesc).getText();
				Assert.assertTrue(desc.contains("Missing element Airport/City code(of Origin)"),
						"Missing element Airport/City code(of Origin)::message not displayed");
				click(btn_viewClose);
			}

			switchToFirstWindow();
			click(btn_process);

			Assert.assertTrue(waitForElement(Generic_info_error).getText()
					.contains("Cannot process a message with the Decoded With Errors status"));

		}

		return this;
	}

	// A-8255
	public MSG005 verifyFHLAutoProcess(String messageStatus, String house1, String key_awbNoPrefix, String key_awbNo,
			String key_flightOrigin, String key_flightDestination, String key_pieces, String key_weight,
			String key_hawb1_pieces, String key_hawb1_weight, String key_hawb2_pieces, String key_hawb2_weight,
			String key_participantType, String key_participant, String key_station) {
		String messageSampleFilePath = messagePath + "FHL.txt";
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String flightOrigin = PropertyHandler.getPropValue(dataFilePath, key_flightOrigin);
		String flightDestination = PropertyHandler.getPropValue(dataFilePath, key_flightDestination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String hawb1_pieces = PropertyHandler.getPropValue(dataFilePath, key_hawb1_pieces);
		String hawb1_weight = PropertyHandler.getPropValue(dataFilePath, key_hawb1_weight);
		String hawb2_pieces = PropertyHandler.getPropValue(dataFilePath, key_hawb2_pieces);
		String hawb2_weight = PropertyHandler.getPropValue(dataFilePath, key_hawb2_weight);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		house1 = PropertyHandler.getPropValue(dataFilePath, house1);
		String fullAWBNo = awbNoPrefix + "-" + awbNo;

		String messagePath = Message.createFHLMessage(messageSampleFilePath, fullAWBNo, flightOrigin, flightDestination,
				pieces, weight, hawb1_pieces, hawb1_weight, hawb2_pieces, hawb2_weight);
		uploadMessage(messagePath, participantType, participant, station);
		logger.info("Verifying AutoProcess");
		waitForFrameAndSwitch(contentFrame);

		enterKeys(txt_messgeType, "FHL");
		click(btn_reference);
		minWait();
		enterKeys(txt_reference1, awbNoPrefix);
		enterKeys(txt_reference5, awbNo);
		click(btn_list);
		minWait();

		List<String> tableCellVals = getTableColumnValues(tbl_message, 9);

		for (String tableCellVal : tableCellVals) {
			if (tableCellVal.contains("Processed Successfully")) {
				logger.info("FWB Processed Successfully");
				break;
			}
		}

		if (messageStatus.equalsIgnoreCase("Processed  With Errors")) {
			tblSelectRowByColValue(tbl_message, 1, 9, "Processed  With Errors");
			click(btn_view);
			switchToSecondWindow();
			String desc = waitForElementVisible(info_ErrorDesc).getText();
			Assert.assertTrue(desc.contains("AWB is executed"), "AWB is executed::message not displayed");
			click(btn_viewClose);

		}

		return this;
	}

	/**
	 * Created by A-7605 on 27-3-18 This method check if ALERT is triggered for
	 * the specific AWB
	 * 
	 * @param awbPrefix
	 * @param awbNo
	 * @return
	 */
	public MSG005 verifyAlertTriggered(String awbPrefix, String awbNo) {
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		boolean isAWBNoFound = false;
		enterKeys(txt_messgeType, "ALERT");
		click(btn_list);
		minWait();
		if (getTableRowCount(tbl_message) > 0) {
			List<WebElement> checkBoxes = getWebElements(chk_selectMessage);
			for (int i = 0; i < checkBoxes.size(); i++) {
				try {
					checkBoxes = getWebElements(chk_selectMessage);
					check(checkBoxes.get(i));
				} catch (StaleElementReferenceException e) {
					maxWait();
					check(checkBoxes.get(i));
				}
				click(btn_view);
				driver.switchTo().defaultContent();
				waitForNewWindow(2);
				switchToSecondWindow();
				if (waitForElementVisible(viewMessage_info_message).getText().contains(awbPrefix + "-" + awbNo)) {
					isAWBNoFound = true;
				}
				click(btn_viewClose);
				switchToFirstWindow();
				waitForFrameAndSwitch(contentFrame);
				if (isAWBNoFound)
					break;
			}
			checkBoxes = getWebElements(chk_selectMessage);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(checkBoxes.get(0))));
		} else
			throw new AssertionError("Alert not triggered, total row count in the table is 0");
		Assert.assertTrue(isAWBNoFound, "ALERT not triggered for the AWB " + awbPrefix + "-" + awbNo);
		return this;
	}

	public MSG005 checkWRKFLWMSGTriggered(String awbNo) {
		return this;
	}

	/**
	 * @param messageSubType
	 * @param awbNo
	 * @param awbNoPrefix
	 * @return
	 * @author A-7605
	 */
	public MSG005 checkFZETriggeredwithSubtypeWithAWB(String messageSubType, String awbNo, String awbNoPrefix) {

		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, awbNoPrefix);

		boolean isFound = false;
		logger.info("Checking if FZE triggered");
		enterKeys(txt_messgeType, "FZE");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		if (messageSubType == null || messageSubType.equalsIgnoreCase("null"))
			messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNo);
		// enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		Assert.assertTrue(getTableRowCount(tbl_message) > 0);
		Assert.assertTrue(getTableCellValue(tbl_message, 2, 1).contains(awbNoPrefix + " - " + awbNo));
		return this;
	}

	/**
	 * Method to upload FSU-DEP message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param fullFlightNo
	 * @param origin
	 * @param dest
	 * @param depStn
	 * @param arrStn
	 * @param pcs
	 * @param wt
	 * @param arrTime
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna <19/04/2018>
	 */
	public MSG005 uploadFSUDEPMessage(String FullAWBNo, String flightStartDate, String fullFlightNo, String origin,
			String dest, String depStn, String arrStn, String pcs, String wt, String arrTime, String participantType,
			String participant, String station) {

		String messageSampleFilePath = messagePath + "FSU_DEP.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		arrStn = PropertyHandler.getPropValue(dataFilePath, arrStn);
		depStn = PropertyHandler.getPropValue(dataFilePath, depStn);
		arrTime = PropertyHandler.getPropValue(dataFilePath, arrTime);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = Message.createFSU_DEPMessage(messageSampleFilePath, FullAWBNo, fullFlightNo,
				flightStartDate, depStn, arrStn, arrTime, origin, dest, pcs, wt);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Breakdown", FullAWBNo);
		return this;
	}

	/**
	 * Method to upload FSU-ARR message
	 * 
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param fullFlightNo
	 * @param origin
	 * @param dest
	 * @param depStn
	 * @param arrStn
	 * @param pcs
	 * @param wt
	 * @param arrTime
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 * @author A-7868 Krishna <19/04/2018>
	 */
	public MSG005 uploadFSUARRMessage(String FullAWBNo, String flightStartDate, String fullFlightNo, String origin,
			String dest, String depStn, String arrStn, String pcs, String wt, String arrTime, String participantType,
			String participant, String station) {

		String messageSampleFilePath = messagePath + "FSU_ARR.txt";

		FullAWBNo = PropertyHandler.getPropValue(dataFilePath, FullAWBNo);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		arrStn = PropertyHandler.getPropValue(dataFilePath, arrStn);
		depStn = PropertyHandler.getPropValue(dataFilePath, depStn);
		arrTime = PropertyHandler.getPropValue(dataFilePath, arrTime);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);

		String messagePath = Message.createFSU_ARRMessage(messageSampleFilePath, FullAWBNo, fullFlightNo,
				flightStartDate, depStn, arrStn, arrTime, origin, dest, pcs, wt);
		uploadMessage(messagePath, participantType, participant, station);
		processFSUMessage("Breakdown", FullAWBNo);
		return this;
	}

	/**
	 * Method to list an FSU Message and check the status
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param subType
	 * @param status
	 * @return
	 * @author A-7868 Krishna <30/04/2018>
	 */
	public MSG005 checkFSUMessageStatus(String awbPre, String awbNo, String subType, String status) {

		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		subType = PropertyHandler.getPropValue(dataFilePath, subType);

		logger.info("Checking message status");
		enterKeys(txt_messgeType, "FSU");
		enterKeys(txt_calFromDate, "-1");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);

		if (subType != null && subType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, subType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbPre);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		String msgStatus = tblGetTextByColValue(tbl_message, 10, 2, awbPre + " - " + awbNo);
		Assert.assertTrue(msgStatus.contains(status), "ERROR : Status mismatch.");
		return this;
	}

	public MSG005 uploadFFRNEWMessage(String key_awbNoPrefix, String key_awbNo, String key_origin,
			String key_destination, String key_pieces, String key_weight, String key_flightStartDate,
			String key_fullFlightNo, String key_FFR_Code, String key_participantType, String key_participant,
			String key_station, String key_agentCode, String key_exportIATAAgentCode) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String fullFlightNo = PropertyHandler.getPropValue(dataFilePath, key_fullFlightNo);
		String FFR_Code = PropertyHandler.getPropValue(dataFilePath, key_FFR_Code);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);

		String messageSampleFilePath = messagePath + "FFRNEW.txt";
		String messagePath = Message.createFFRNEWMessage(messageSampleFilePath, awbNoPrefix, awbNo, origin, destination,
				pieces, weight, flightStartDate, fullFlightNo, FFR_Code, agentCode, exportIATAAgentCode);
		uploadMessage(messagePath, participantType, participant, station);
		processFFR(awbNoPrefix, awbNo);
		return this;
	}

	public MSG005 checkIfFFRNotTriggered(String key_awbNoPrefix, String key_awbNo) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);

		boolean isFound = false;
		logger.info("Checking if FFR triggered");
		enterKeys(txt_messgeType, "FFR");
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FFR triggered");
			isFound = true;
		} else {
			logger.info("FFR not triggered");
			isFound = false;
		}

		Assert.assertFalse(isFound, "FFR message is not not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");
		return this;
	}

	/**
	 * Overloaded for one awb in ULD Created by a-7605 on 10-5-18
	 */

	public MSG005 uploadFFMMessage_1ULD(String messageFileName, String awbPrefix, String fullFlightNo,
			String flightStartDate, String origin, String destination, String uldNo, String awbNo, String pieces,
			String weight, String volume, String participantType, String participant, String station) {
		messageFileName = PropertyHandler.getPropValue(dataFilePath, messageFileName);
		String messageSampleFilePath = messagePath + messageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		volume = PropertyHandler.getPropValue(dataFilePath, volume);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);

		String fullAWBNo = awbPrefix + "-" + awbNo;
		String messagePath = Message.createFFMMessage_1ULD(messageSampleFilePath, fullFlightNo, flightStartDate, origin,
				destination, fullAWBNo, pieces, weight, volume, uldNo);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Method to check the status of an FFM message
	 * 
	 * @param carrCode
	 * @param fltNo
	 * @param status
	 * @return
	 * @author A-7868 Krishna <11/05/2018>
	 */
	public MSG005 checkFFMMessageStatus(String carrCode, String fltNo, String status) {

		carrCode = PropertyHandler.getPropValue(dataFilePath, carrCode);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		enterKeys(txt_messgeType, "FFM");
		click(btn_reference);
		minWait();
		By txt_fltNum = By.xpath("//td[contains(text(), 'FLTNUM')]/following::td[1]/input");
		By txt_cc = By.xpath("//td[contains(text(), 'CARCOD')]/following::td[1]/input");

		enterKeys(txt_cc, carrCode);
		enterKeys(txt_fltNum, fltNo);
		click(btn_list);
		minWait();
		Assert.assertTrue(getTableColumnValues(tbl_message, 9).contains(status), "ERROR : Status mismatch.");
		return this;
	}

	/**
	 * @author A-8260
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param pcs
	 * @param wt
	 * @param key_carrierCode
	 * @param key_origin
	 * @param key_destination
	 * @param key_flightStartDate
	 * @param key_station
	 * @param key_participant
	 * @param key_participantType
	 * @param key_secureScc
	 * @return
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public MSG005 uploadxFWBMessage(String key_awbNoPrefix, String key_awbNo, String pcs, String wt,
			String key_carrierCode, String key_origin, String key_destination, String key_flightStartDate,
			String key_station, String key_participant, String key_participantType, String... key_secureScc)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException,
			TransformerFactoryConfigurationError, TransformerException {

		String messageSampleFilePath;
		String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());

		messageSampleFilePath = messagePath + "XFWB.xml";
		System.out.println("path of sample message: " + messageSampleFilePath);
		String secureScc = null;
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);

		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);

		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		if (key_secureScc.length != 0)
			secureScc = PropertyHandler.getPropValue(dataFilePath, key_secureScc[0]);

		String messagePath = "";
		if (key_secureScc.length != 0)
			messagePath = Message.createxFWBMessage(messageSampleFilePath, awbNoPrefix, awbNo, pcs, wt, flightStartDate,
					origin, destination);

		uploadMessage(messagePath, participantType, participant, station);
		processxFWBMessage(awbNoPrefix, awbNo);
		return this;
	}

	public void processxFWBMessage(String awbNoPrefix, String awbNo) {
		logger.info("Processing FWB message");
		waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "xFWB");
		click(btn_reference);
		minWait();

		By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
		By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

		// enterKeys(getWebElements(txt_list_referencevalue).get(0),
		// awbNoPrefix);
		// enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
		enterKeys(txt_awbPre, awbNoPrefix);
		enterKeys(txt_awbNo, awbNo);
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("NoSuchElementException " + e);
		}
		logger.info("FWB message processed");
		return;
	}

	public MSG005 uploadFWBMessage_OCIScreeningMethod_Transhipment(String key_awbNoPrefix, String key_awbNo,
			String key_carrierCode, String key_origin, String key_destination, String key_pieces, String key_weight,
			String key_shipperName, String key_consigneeName, String key_agentCode, String FullFlightNo,
			String key_exportIATAAgentCode, String key_rate, String key_flightStartDate, String key_participantType,
			String key_participant, String key_station, String paymentType, boolean OCIFlag, boolean trans,
			String key_intermediate, String key_carrierCode_2, String... key_secureScc) {

		String messageSampleFilePath;
		String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());

		messageSampleFilePath = messagePath + "FWB_OCI_SCRMTD.txt";
		System.out.println("path of sample message: " + messageSampleFilePath);
		String secureScc = null;
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		String carrierCode = PropertyHandler.getPropValue(dataFilePath, key_carrierCode);
		String carriercode_2 = PropertyHandler.getPropValue(dataFilePath, key_carrierCode_2);
		String intermediate = PropertyHandler.getPropValue(dataFilePath, key_intermediate);
		String origin = PropertyHandler.getPropValue(dataFilePath, key_origin);
		String destination = PropertyHandler.getPropValue(dataFilePath, key_destination);
		String pieces = PropertyHandler.getPropValue(dataFilePath, key_pieces);
		String weight = PropertyHandler.getPropValue(dataFilePath, key_weight);
		String shipperName = PropertyHandler.getPropValue(dataFilePath, key_shipperName);
		String consigneeName = PropertyHandler.getPropValue(dataFilePath, key_consigneeName);
		String rate = PropertyHandler.getPropValue(dataFilePath, key_rate);
		String flightStartDate = PropertyHandler.getPropValue(dataFilePath, key_flightStartDate);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String agentCode = PropertyHandler.getPropValue(dataFilePath, key_agentCode);
		if (key_secureScc.length != 0)
			secureScc = PropertyHandler.getPropValue(dataFilePath, key_secureScc[0]);
		String exportIATAAgentCode = PropertyHandler.getPropValue(dataFilePath, key_exportIATAAgentCode);
		FullFlightNo = PropertyHandler.getPropValue(dataFilePath, FullFlightNo);
		paymentType = PropertyHandler.getPropValue(dataFilePath, paymentType);

		String messagePath;
		if (rate.equalsIgnoreCase("null"))
			rate = null;
		if (key_secureScc.length != 0)
			messagePath = Message.createFWBMessage_OCIScreenMethod_Transhipment(messageSampleFilePath, awbNoPrefix,
					awbNo, carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
					FullFlightNo, exportIATAAgentCode, rate, flightStartDate, paymentType, false, true, intermediate,
					carriercode_2, secureScc);
		else
			messagePath = Message.createFWBMessage_OCIScreenMethod_Transhipment(messageSampleFilePath, awbNoPrefix,
					awbNo, carrierCode, origin, destination, pieces, weight, shipperName, consigneeName, agentCode,
					FullFlightNo, exportIATAAgentCode, rate, flightStartDate, paymentType, false, true, intermediate,
					carriercode_2);
		String status = uploadMessage(messagePath, participantType, participant, station);
		processFWBMessage(awbNoPrefix, awbNo, status);
		return this;
	}

	public MSG005 checkFWBTriggeredSendingStatus(String key_awbNoPrefix, String key_awbNo, String screeningMethod) {
		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		boolean isFound = false;
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FWB");
		click(btn_reference);
		minWait();

		By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
		By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

		// enterKeys(getWebElements(txt_list_referencevalue).get(0),
		// awbNoPrefix);
		// enterKeys(getWebElements(txt_list_referencevalue).get(3), awbNo);
		enterKeys(txt_awbPre, awbNoPrefix);
		enterKeys(txt_awbNo, awbNo);
		selectByText(list_messageStatus, "Sending");
		click(btn_list);
		WebElement temp = null;
		boolean flag = false;
		int noOfRows = getWebElements(list_checkBoxSelectMsg).size();

		for (int i = 0; i < noOfRows; i++) {

			temp = getWebElements(list_checkBoxSelectMsg).get(i);
			check(temp);
			click(btn_view);
			waitForNewWindow(2);
			switchToSecondWindow();

			if (waitForElement(txt_viewMsg).getText().contains(screeningMethod)) {
				click(btn_viewClose);
				switchToFirstWindow();
				waitForFrameAndSwitch(contentFrame);
				minWait();
				flag = true;

			} else
				click(btn_viewClose);
			switchToFirstWindow();
			waitForFrameAndSwitch(contentFrame);
			minWait();
			if (flag) {
				break;
			}

		}

		return this;
	}

	/**
	 * Description : Method will check if FZE message is triggered.
	 * messageSubType should be 'null' if no subtype check is required
	 * 
	 * @param key_awbNoPrefix
	 * @param key_awbNo
	 * @param messageSubType
	 * @return A-7681 added msg Super sub type
	 */

	public MSG005 checkIfFZETriggered(String key_awbNoPrefix, String key_awbNo, String messageSubType, String fromdate,
			String... msgSuperSubType) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		String awbNo = PropertyHandler.getPropValue(dataFilePath, key_awbNo);
		messageSubType = PropertyHandler.getPropValue(dataFilePath, messageSubType);
		fromdate = PropertyHandler.getPropValue(dataFilePath, fromdate);
		boolean isFound = false;

		logger.info("Checking if FZE triggered");
		enterKeys(txt_messgeType, "FZE");
		enterKeys(txt_calFromDate, fromdate);
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		// if (messageSubType.equalsIgnoreCase("null"))
		// messageSubType = null;
		if (messageSubType != null && messageSubType.trim().length() > 0) {
			selectByText(dropdown_messageSubType, messageSubType);
		}
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNoPrefix);
		enterKeys(txt_awbser, awbNo);
		click(btn_list);
		minWait();
		if (getTableColumnValues(tbl_message, 2).contains(awbNoPrefix + " - " + awbNo)) {
			logger.info("FZE triggered");
			isFound = true;
		} else {
			logger.info("FZE not triggered");
			isFound = false;
		}

		Assert.assertTrue(isFound, "FZE message is not triggered for the AWB '" + awbNoPrefix + "-" + awbNo + "'");

		// Added by A-7681
		if (msgSuperSubType.length > 0) {

			WebElement temp = null;
			boolean flag = false;
			int noOfRows = getWebElements(list_checkBoxSelectMsg).size();

			for (int i = 0; i < noOfRows; i++) {

				temp = getWebElements(list_checkBoxSelectMsg).get(i);
				check(temp);
				click(btn_view);
				waitForNewWindow(2);
				switchToSecondWindow();

				if (waitForElement(txt_viewMsg).getText().contains(msgSuperSubType[0])) {
					flag = true;
				}
				logger.info("Message content is :" + waitForElement(txt_viewMsg).getText());
				click(btn_viewClose);
				switchToFirstWindow();
				waitForFrameAndSwitch(contentFrame);
				if (flag) {
					break;
				}

			}
			Assert.assertTrue(flag, "The FSU with super sub type " + msgSuperSubType[0] + "  not triggered");

		}

		return this;
	}

	/**
	 * Created by A-7605 on 21-5-18 This method create, upload and process FFM
	 * message for 3 AWBs containing in 1 ULD
	 * 
	 * @param awbPrefix
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param awbNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param awbNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param awbNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param uldNo
	 * @param participantType
	 * @param participant
	 * @param station
	 * @param sampleMessageFileName
	 * @return
	 */
	public MSG005 uploadFFMMessage_3AWBsIn_3ULD(String awbPrefix, String fullFlightNo, String flightStartDate,
			String origin, String destination, String awbNo1, String pieces1, String weight1, String volume1,
			String uldNo1, String awbNo2, String pieces2, String weight2, String volume2, String uldNo2, String awbNo3,
			String pieces3, String weight3, String volume3, String uldNo3, String participantType, String participant,
			String station, String sampleMessageFileName) {
		sampleMessageFileName = PropertyHandler.getPropValue(dataFilePath, sampleMessageFileName);
		String messageSampleFilePath = messagePath + sampleMessageFileName;
		awbPrefix = PropertyHandler.getPropValue(dataFilePath, awbPrefix);
		fullFlightNo = PropertyHandler.getPropValue(dataFilePath, fullFlightNo);
		flightStartDate = PropertyHandler.getPropValue(dataFilePath, flightStartDate);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		destination = PropertyHandler.getPropValue(dataFilePath, destination);
		awbNo1 = PropertyHandler.getPropValue(dataFilePath, awbNo1);
		pieces1 = PropertyHandler.getPropValue(dataFilePath, pieces1);
		weight1 = PropertyHandler.getPropValue(dataFilePath, weight1);
		volume1 = PropertyHandler.getPropValue(dataFilePath, volume1);
		uldNo1 = PropertyHandler.getPropValue(dataFilePath, uldNo1);
		uldNo2 = PropertyHandler.getPropValue(dataFilePath, uldNo2);
		uldNo3 = PropertyHandler.getPropValue(dataFilePath, uldNo3);
		awbNo2 = PropertyHandler.getPropValue(dataFilePath, awbNo2);
		pieces2 = PropertyHandler.getPropValue(dataFilePath, pieces2);
		weight2 = PropertyHandler.getPropValue(dataFilePath, weight2);
		volume2 = PropertyHandler.getPropValue(dataFilePath, volume2);
		awbNo3 = PropertyHandler.getPropValue(dataFilePath, awbNo3);
		pieces3 = PropertyHandler.getPropValue(dataFilePath, pieces3);
		weight3 = PropertyHandler.getPropValue(dataFilePath, weight3);
		volume3 = PropertyHandler.getPropValue(dataFilePath, volume3);
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		String fullAWBNo1 = awbPrefix + "-" + awbNo1;
		String fullAWBNo2 = awbPrefix + "-" + awbNo2;
		String fullAWBNo3 = awbPrefix + "-" + awbNo3;
		String messagePath = Message.createFFMMessage_3AWBs_3ULD(messageSampleFilePath, fullFlightNo, flightStartDate,
				origin, destination, fullAWBNo1, pieces1, weight1, volume1, uldNo1, fullAWBNo2, pieces2, weight2,
				volume2, uldNo2, fullAWBNo3, pieces3, weight3, volume3, uldNo3);
		uploadMessage(messagePath, participantType, participant, station);
		processFFMMessage("", "");
		return this;
	}

	/**
	 * Method to verify if UWS triggered is Provisional
	 * 
	 * @param fltNo
	 * @return
	 * @author A-7868 Krishna <22/05/2018>
	 */
	public MSG005 checkIfUWSProvisionalTriggered(String fltNo) {

		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		By txt_fltNo = By.xpath("//td[contains(text(), 'FLTNUM')]/following::td[1]/input");
		enterKeys(txt_messgeType, "UWS");
		click(btn_reference);
		enterKeys(txt_fltNo, fltNo);
		click(btn_list);
		minWait();
		waitForElement(tbl_message).findElement(By.xpath(".//tbody/tr[1]/td[1]/input")).click();
		click(btn_view);
		minWait();
		driver.switchTo().defaultContent();
		waitForNewWindow(2);
		switchToSecondWindow();
		Assert.assertFalse(waitForElement(txt_viewMsg).getText().contains("FINAL"),
				"ERROR : Message contains field Final");
		click(btn_viewClose);
		minWait();
		switchBackToFirstWindow();
		waitForFrameAndSwitch(contentFrame);
		return this;

	}

	/**
	 * Method to verify if UWS triggered is Final
	 * 
	 * @param fltNo
	 * @return
	 * @author A-7868 Krishna <22/05/2018>
	 */
	public MSG005 checkIfUWSFinalTriggered(String fltNo) {

		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);

		By txt_fltNo = By.xpath("//td[contains(text(), 'FLTNUM')]/following::td[1]/input");
		enterKeys(txt_messgeType, "UWS");
		click(btn_reference);
		enterKeys(txt_fltNo, fltNo);
		click(btn_list);
		minWait();
		waitForElement(tbl_message).findElement(By.xpath(".//tbody/tr[1]/td[1]/input")).click();
		click(btn_view);
		waitForNewWindow(2);
		switchToSecondWindow();
		Assert.assertTrue(waitForElement(txt_viewMsg).getText().contains("FINAL"),
				"ERROR : Message contains field Final");
		click(btn_viewClose);
		minWait();
		switchBackToFirstWindow();
		waitForFrameAndSwitch(contentFrame);
		return this;

	}

	/**
	 * Description : Method will load and Process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadXFRNEWMessage(String key_awbNoPrefix, String awbno, String key_participantType,
			String key_participant, String key_station) {

		String awbNoPrefix = PropertyHandler.getPropValue(dataFilePath, key_awbNoPrefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		String messageSampleFilePath = messagePath + "XFR.txt";
		uploadMessage(messageSampleFilePath, participantType, participant, station);
		processXFR(awbNoPrefix, awbno);
		return this;
	}

	public MSG005 processXFR(String awbNoPrefix, String awbNo) {
		logger.info("Processing XFR message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "XFR");
		click(btn_reference);
		maxWait();
		enterKeys(txt_awbpfx, awbNo);

		maxWait();
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, "XFR message not processed");
		}
		test.log(LogStatus.PASS, "XFR message processed");
		logger.info("XFR message processed");
		return this;
	}

	/**
	 * Description : Method will load and Process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadXFRNEWMessageFromGivenPath(String messageSampleFilePath, String prefix, String awbno,
			String key_participantType, String key_participant, String key_station) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		key_participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		key_participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		// String messageSampleFilePath = messagePath + "XFR.txt";
		uploadMessage(messageSampleFilePath, key_participantType, key_participant, station);
		processXFR(prefix, awbno);
		return this;
	}

	/**
	 * Author: Shalini Description : Method will load and Process message based
	 * on AWB NO
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadMessageFromGivenPathonAWB(String messageSampleFilePath, String prefix, String awbno,
			String key_participantType, String key_participant, String key_station, String msgForm) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		key_participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		key_participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		uploadMessage(messageSampleFilePath, key_participantType, key_participant, station);
		processMessageonAWB(msgForm, prefix, awbno);
		return this;
	}

	/**
	 * Author: Shalini Description : Method will load and Process message based
	 * on AWB NO
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadMessageFromGivenPathonAWBeFreight(String messageSampleFilePath, String prefix, String awbno,
			String key_participantType, String key_participant, String key_station, String msgForm) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		key_participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		key_participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		uploadMessage(messageSampleFilePath, key_participantType, key_participant, station);
		// processMessageonAWBeFreight(msgForm, prefix, awbno);
		return this;
	}

	// Sharath
	public MSG005 uploadMessageFromGivenPathonAWBPSN(String messageSampleFilePath, String prefix, String awbno,
			String key_participantType, String key_participant, String key_station, String msgForm) {

		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		key_participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		key_participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		uploadMessage(messageSampleFilePath, key_participantType, key_participant, station);
		processMessageonAWBPSN(msgForm, prefix, awbno);
		return this;
	}

	/**
	 * Description : Method will load and Process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadFLHNEWMessage(String FlightNo, String key_participantType, String key_participant,
			String key_station) {

		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		String messageSampleFilePath = messagePath + "FLH_CRTE.txt";
		uploadMessage(messageSampleFilePath, participantType, participant, station);
		processFLH(FlightNo);
		return this;
	}

	/**
	 * Description : Method will load and Process message
	 * 
	 * @param path
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploaNEWMessagewithFlightNo(String path, String FlightNo, String key_participantType,
			String key_participant, String key_station, String Msgform) {

		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);

		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);
		test.log(LogStatus.INFO, "Uploading the message file : " + Msgform);
		uploadMessage(path, participantType, participant, station);
		// processFLH(FlightNo);
		processMessage(Msgform, FlightNo);
		return this;
	}

	/**
	 * Description : Method will load and Process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadMessageandVerify(String msgForm, String key_participantType, String key_participant,
			String key_station) {

		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		String messageSampleFilePath = messagePath + msgForm;
		uploadMessage(messageSampleFilePath, participantType, participant, station);
		processMessage(msgForm, "FlightNo");
		return this;
	}

	/**
	 * Description : Method will load and Process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadFLH_EQSUBMessage(String FlightNo, String key_participantType, String key_participant,
			String key_station) {

		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);

		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		String messageSampleFilePath = messagePath + "FLH_EQSUB.txt";
		uploadMessage(messageSampleFilePath, participantType, participant, station);
		processFLH(FlightNo);
		return this;
	}

	/**
	 * Description : Method will process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public MSG005 processMessageonAWB(String msgForm, String awbNoPrefix, String awbNo) {
		logger.info("Processing the message : " + msgForm);
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, msgForm);
		click(btn_reference);
		maxWait();
		// enterKeys(txt_awbpfx, awbNo);
		if (msgForm.equals("FWB")) {
			enterKeys(txt_list_referencevalueFWB, awbNo);
		} else {
			enterKeys(txt_list_referencevalue, awbNo);
		}

		// maxWait();
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			String actaulValue = driver.findElement(By.xpath("//div[@id='ic-sd-msgc']")).getText();
			if (actaulValue.equalsIgnoreCase("Message Processed Successfully.")) {
				test.log(LogStatus.PASS, "Message Processed Successfully" + " " + actaulValue);
			} else {
				test.log(LogStatus.FAIL, "Message Not Processed Successfully" + " " + actaulValue);
				Assert.fail("Message Not Processed Successfully");
			}
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			logger.info("message processed");
			/*
			 * click(dialogue_btn_ok); waitForFrameAndSwitch(contentFrame);
			 */
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, msgForm + " message not processed");
		}
		test.log(LogStatus.PASS, msgForm + " message processed");
		logger.info(msgForm + " message processed");
		return this;
	}

	/**
	 * Description : Method will process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public MSG005 processMessageonAWBeFreight(String msgForm, String awbNoPrefix, String awbNo) {
		logger.info("Processing the message : " + msgForm);
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, msgForm);
		click(btn_reference);
		maxWait();
		// enterKeys(txt_awbpfx, awbNo);
		if (msgForm.equals("FWB")) {
			enterKeys(txt_list_referencevalueFWB, awbNo);
		} else {
			enterKeys(txt_list_referencevalue, awbNo);
		}

		// maxWait();
		selectByText(list_messageStatus, "Decoded With Warnings");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			String actaulValue = driver.findElement(By.xpath("//div[@id='ic-sd-msgc']")).getText();
			if (actaulValue.equalsIgnoreCase("Message Processed Successfully.")) {
				test.log(LogStatus.PASS, "Message Processed Successfully" + " " + actaulValue);
			} else {
				test.log(LogStatus.FAIL, "Message Not Processed Successfully" + " " + actaulValue);
				Assert.fail("Message Not Processed Successfully");
			}
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			logger.info("message processed");
			/*
			 * click(dialogue_btn_ok); waitForFrameAndSwitch(contentFrame);
			 */
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, msgForm + " message not processed");
		}
		test.log(LogStatus.PASS, msgForm + " message processed");
		logger.info(msgForm + " message processed");
		return this;
	}

	// Sharath
	public MSG005 processMessageonAWBPSN(String msgForm, String awbNoPrefix, String awbNo) {
		logger.info("Processing the message : " + msgForm);
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(By.name("fromDate"), ".");
		enterKeys(By.name("toDate"), "+1" + Keys.TAB);
		enterKeys(txt_messgeType, msgForm);
		click(btn_reference);
		maxWait();
		// enterKeys(txt_awbpfx, awbNo);
		// enterKeys(txt_list_referencevalue, awbNo);
		enterKeys(txt_awbpfx, awbNo);

		// maxWait();
		selectByText(list_messageStatus, "Decoded Successfully");
		click(btn_list);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			String actaulValue = driver.findElement(By.xpath("//div[@id='ic-sd-msgc']")).getText();
			if (actaulValue.equalsIgnoreCase("Message Processed Successfully.")) {
				test.log(LogStatus.PASS, "Message Processed Successfully" + " " + actaulValue);
				captureAndAddScreenshot();
			} else {
				test.log(LogStatus.FAIL, "Message Not Processed Successfully" + " " + actaulValue);
				captureAndAddScreenshot();
				// Assert.fail("Message Not Processed Successfully");
			}
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			logger.info("message processed");
			/*
			 * click(dialogue_btn_ok); waitForFrameAndSwitch(contentFrame);
			 */
		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, msgForm + " message not processed");
			captureAndAddScreenshot();
		}
		test.log(LogStatus.PASS, msgForm + " message processed");
		captureAndAddScreenshot();
		logger.info(msgForm + " message processed");
		return this;
	}

	/**
	 * Description : Method will process FLH message
	 * 
	 * @param FlightNumber
	 */
	public MSG005 processFLH(String FlightNo) {
		boolean isFound = true;
		logger.info("Processed FLH message");
		// waitForFrameAndSwitch(contentFrame);
		enterKeys(txt_messgeType, "FLH");
		click(btn_reference);
		maxWait();
		enterKeys(txt_list_referencevalue, FlightNo);

		maxWait();

		click(btn_list);
		maxWait();
		String actualValue = driver.findElement(By.xpath("(//table[@id='listmessaging']//tr[1]//td[9])[2]")).getText();
		Assert.assertTrue(isFound, "Message Processed Successfully" + actualValue + "Processed Successfully");
		logger.info("FLH message processed");
		return this;
	}

	/**
	 * Description : Method will process message
	 * 
	 * @param Msgform
	 * @param FlightNumber
	 */
	public MSG005 processMessage(String Msgform, String FlightNo) {
		test.log(LogStatus.INFO, "Started processing the message : " + Msgform);
		logger.info("Processed  message");
		enterKeys(txt_messgeType, Msgform);
		enterKeys(txt_calFromDate, "-1" + Keys.TAB);
		enterKeys(txt_calToDate, "." + Keys.TAB);
		// maxWait();
		selectByText(list_messageStatus, "Decoded Successfully");
		maxWait();
		driver.findElement(btn_list).click();
		// enterKeys(btn_list,Keys.F12);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			minWait();
			String actualValue = driver.findElement(By.xpath("//div[@id='ic-sd-msgc']")).getText();
			captureAndAddScreenshot();
			if (actualValue.equalsIgnoreCase("Message Processed Successfully.")) {
				test.log(LogStatus.PASS, "Message Processed Successfully with status : " + actualValue);
			} else {
				test.log(LogStatus.FAIL, "Message Not Processed with status" + actualValue);
				Assert.fail("Message Not Processed");
			}
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			logger.info("message processed");

		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, "Timeout Exception/Nosuch Element Exception" + Msgform);
			Assert.fail("Message Not Processed");
		}
		logger.info(" message processed" + Msgform);
		return this;

	}
	
	
	public MSG005 getAttributeFromText(String path, String pryKey) throws IOException {

		String deatils = "";
		System.out.println();
		String sCurrentLine = "";
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));

		while ((sCurrentLine = br.readLine()) != null) {

			if (sCurrentLine.trim().contains(pryKey))
			// sCurrentLine;
			{
				deatils = sCurrentLine.replace(pryKey, "").replace("<", "").replace(">", "").replace(" ", "")
						.replace("/", "");

				System.out.println(deatils);
				PropertyHandler.setPropValue(dataFilePath, "FlightNo", deatils);
				break;
			}
		}
		return this;

	}

	public MSG005 getAttributeFromMessage(String path, String pryKey) throws IOException {

		String deatils = "";
		System.out.println();
		String sCurrentLine = "";
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));

		while ((sCurrentLine = br.readLine()) != null) {

			if (sCurrentLine.trim().contains(pryKey))
			// sCurrentLine;
			{
				deatils = sCurrentLine.replace(pryKey, "").replace("<", "").replace(">", "").replace(" ", "")
						.replace("/", "");

				System.out.println(deatils);
				PropertyHandler.setPropValue(dataFilePath, "AircraftType", deatils);
				break;
			}
		}
		return this;

	}

	/*
	 * Author- Shalini P Purpose : Method to read the AWB number from the text
	 * File.
	 * 
	 * @Param: key_awbNo return AWB no
	 */
	public MSG005 getXFRAWBNo(String path, String awbno) {

		BufferedReader br = null;
		String AWBno = "";
		String sCurrentLine = "";
		String pryKey = "-";
		// String messageSampleFilePath = messagePath + "XFR.txt";
		try {
			br = new BufferedReader(new FileReader(new File(path)));
			System.out.println();
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine.trim();
				String line = sCurrentLine.substring(0, 4);
				if (line.trim().contains(pryKey)) {
					AWBno = sCurrentLine.substring(4, 12);
					System.out.println();
					break;

				}
			}
		} catch (Exception e) {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Unable to get AWB no:" + AWBno);
		}
		PropertyHandler.setPropValue(dataFilePath, awbno, AWBno);
		System.out.println(AWBno);
		test.log(LogStatus.PASS, "Successfully taken AWB no from the format" + AWBno);
		return this;

	}

	/*
	 * Author- Shalini P Purpose : Method to read the AWB number from the text
	 * File.
	 * 
	 * @Param: key_awbNo
	 */
	public MSG005 getPIDinXFR(String PIDNo) {

		BufferedReader br = null;
		String PIDno = "";
		String sCurrentLine = "";
		String pryKey = "PID";
		String messageSampleFilePath = messagePath + "XFR.txt";
		try {
			br = new BufferedReader(new FileReader(new File(messageSampleFilePath)));
			System.out.println();
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine.trim();
				String line = sCurrentLine.substring(0, 4);
				if (line.trim().contains(pryKey)) {
					PIDno = sCurrentLine.substring(4, 5);
					System.out.println();
					break;

				}
			}
		} catch (Exception e) {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Unable to get PID format:" + PIDNo);
		}
		PropertyHandler.setPropValue(dataFilePath, PIDNo, PIDno);
		System.out.println(PIDNo);
		test.log(LogStatus.PASS, "Successfully taken PID  from the format" + PIDNo);
		return this;

	}

	/**
	 * Description : Method will Update XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public MSG005 UpdateXFR() {
		String messageSampleFilePath = messagePath + "XFR.txt";
		File f = new File(messageSampleFilePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldString = "NN";
		String newString = "XX";
		try {
			reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}

			String newContent = oldContent.replaceAll(oldString, newString);
			writer = new FileWriter(f);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this;

	}

	/**
	 * Description : Method will Update XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public MSG005 UpdateMessage(String kindofMSG, String oldString, String newString) {

		String messageSampleFilePath = messagePath + kindofMSG;
		File f = new File(messageSampleFilePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		/*
		 * String oldString = "NN"; String newString = "XX";
		 */
		try {
			reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}

			String newContent = oldContent.replaceAll(oldString, newString);
			writer = new FileWriter(f);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this;

	}

	/**
	 * Description : Method will Update XFR message based on Flightnumber
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 */
	public MSG005 UpdateXFRwithFlightNumber(String FlightNo) {
		BufferedReader br = null;
		String FlightNumber = "";
		String sCurrentLine = "";
		String pryKey = "-";
		String messageSampleFilePath = messagePath + "XFR.txt";
		FlightNumber = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		try {
			br = new BufferedReader(new FileReader(new File(messageSampleFilePath)));
			System.out.println();
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine.trim();
				String line = sCurrentLine.substring(0, 4);
				if (line.trim().contains(pryKey)) {
					FlightNumber = sCurrentLine.substring(2, 6);
					System.out.println();
					break;

				}
			}
		} catch (Exception e) {
			Assert.fail("Error in Message");
		}

		System.out.println(FlightNumber);
		return this;

	}

	/**
	 * Description : Method will load and Process XFR message
	 * 
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param key_participantType
	 * @param key_participant
	 * @param station
	 */
	public MSG005 uploadNEWMessage(String Path, String FlightNo, String key_participantType, String key_participant,
			String key_station) {

		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);

		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		// String messageSampleFilePath = messagePath + Path;
		uploadMessage(Path, participantType, participant, station);
		processFLH(FlightNo);
		return this;
	}

	/*
	 * Author- Shalini P Purpose : Method to read the ULD Mixed type from the
	 * text File.
	 * 
	 * @Param: key_awbNo
	 */
	public MSG005 getULDType(String ULDType, String SecondULDType) {

		BufferedReader br = null;
		String ULD1 = "";
		String ULD2 = "";
		String sCurrentLine = "";
		String pryKey = "ULD";
		String messageSampleFilePath = messagePath + "XFR.txt";
		try {
			br = new BufferedReader(new FileReader(new File(messageSampleFilePath)));
			System.out.println();
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine.trim();
				String line = sCurrentLine.substring(0, 4);
				if (line.trim().contains(pryKey)) {

					ULD1 = sCurrentLine.substring(6, 9);
					String nxtline = br.readLine();
					ULD2 = nxtline.substring(1, 4);
					System.out.println();

					System.out.println(ULD1);
					System.out.println(ULD2);
				}
			}
		} catch (Exception e) {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Error in Message");
		}
		PropertyHandler.setPropValue(dataFilePath, ULDType, ULD1);
		PropertyHandler.setPropValue(dataFilePath, SecondULDType, ULD2);
		test.log(LogStatus.PASS,
				" Successfully taken First ULD and Sceond ULD from the format" + ULDType + SecondULDType);
		return this;

	}

	/*
	 * Author- Shalini P Purpose : Method to read the AWB number from the text
	 * File.
	 * 
	 * @Param: key_awbNo
	 */
	public MSG005 getSSIMFlightNo(String path) {

		String FlightNo = "";
		BufferedReader br = null;
		// String FlightNumber = "";
		String sCurrentLine = "";
		String pryKey = "3";
		String messageSampleFilePath = messagePath + path;
		try {
			br = new BufferedReader(new FileReader(new File(messageSampleFilePath)));
			System.out.println();
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine.trim();
				String line = sCurrentLine.substring(0, 4);
				if (line.trim().contains(pryKey)) {
					FlightNo = sCurrentLine.substring(5, 9);
					System.out.println();
					break;

				}
			}
		} catch (Exception e) {
			Assert.fail("Error in Message");
			test.log(LogStatus.FAIL, "Error in Message");
		}
		PropertyHandler.setPropValue(dataFilePath, "FlightNo", FlightNo);
		test.log(LogStatus.PASS, "Successfully written Flight number in Property file" + FlightNo);
		return this;

	}

	/*
	 * Author- Shalini P Purpose : Method to read the all Flight number from the
	 * text and written to the property file
	 * 
	 * @Params: keys[]- to which key needs to be set
	 */
	public MSG005 getAllFlightNo(String keys[]) {
		// minWait();

		BufferedReader br = null;
		String[] FlightNumber = new String[keys.length];
		String sCurrentLine = "";
		String arrData[] = { "AA", "BA", "LH" };
		String messageSampleFilePath = messagePath + "XFR.txt";
		int k = 0;
		try {
			br = new BufferedReader(new FileReader(messageSampleFilePath));
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine.trim();
				String line = sCurrentLine.substring(0, 4);

				for (int i = 0; i < arrData.length; i++) {
					String pryKey = arrData[i];

					if (line.trim().contains(pryKey)) {

						FlightNumber[i] = sCurrentLine.substring(0, 6);
						System.out.println(FlightNumber[i] + "\n");
						PropertyHandler.setPropValue(dataFilePath, keys[k], FlightNumber[i]);
						test.log(LogStatus.PASS,
								"Flight No and Carrier Code Successfully written" + FlightNumber[i]);
						if (k <= keys.length)
							k = k + 1;

					}
				}

			}
		} catch (Exception e) {
			System.out.println("Error in catch");
		}

		return this;

	}

	// A-8680
	public MSG005 getDate(String fltDt) {
		fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		click(txt_date);
		enterKeys(txt_date, fltDt + Keys.ENTER);
		minWait();
		String date = getAttributebyValue(txt_date);
		// PropertyHandler.setPropValue(dataFilePath, "fltDt", date);
		date = date.replaceAll("-", "");
		PropertyHandler.setPropValue(dataFilePath, "fltDtwithYY", date);
		date = date.substring(0, 5);
		date = date.toUpperCase();
		PropertyHandler.setPropValue(dataFilePath, "fltDtwithoutYY", date);
		enterKeys(txt_date, "." + Keys.TAB);
		return this;

	}

	// A-8680
	public MSG005 getcurrentDate() {
		// fltDt = PropertyHandler.getPropValue(dataFilePath, fltDt);
		click(txt_date);
		enterKeys(txt_date, "." + Keys.TAB);
		minWait();
		String date = getAttributebyValue(txt_date);
		// PropertyHandler.setPropValue(dataFilePath, "fltDt", date);
		date = date.replaceAll("-", "");
		PropertyHandler.setPropValue(dataFilePath, "currentDtwithYY", date);
		date = date.substring(0, 5);
		date = date.toUpperCase();
		PropertyHandler.setPropValue(dataFilePath, "currentDtwithoutYY", date);
		return this;

	}

	public MSG005 uploadingMessagewithFlightNum(String path, String FlightNo, String key_participantType,
			String key_participant, String key_station) {

		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		String participantType = PropertyHandler.getPropValue(dataFilePath, key_participantType);
		String participant = PropertyHandler.getPropValue(dataFilePath, key_participant);
		String station = PropertyHandler.getPropValue(dataFilePath, key_station);

		uploadMessage(path, participantType, participant, station);
		processFLH(FlightNo);
		return this;
	}

	public MSG005 writeDatatoPropertyfile(String Path, String prefix, String awbno, String origin, String dest,
			String agentCode, String Product, String pcs, String wt, String vol, String FlightNo, String fltDt,
			String ubr, String bookingType, String ULDType, String noOfULD, String CarrierCode, String uldNo) {
		Path = PropertyHandler.getPropValue(dataFilePath, Path);

		Path = "./resources/TestData/BASE/" + Path;
		test.log(LogStatus.INFO, "Starting with writing the data to property file");
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
		PropertyHandler.setPropValue(Path, "prefix", prefix);
		PropertyHandler.setPropValue(Path, "CarrierCode", CarrierCode);
		PropertyHandler.setPropValue(Path, "FlightNo", FlightNo);
		PropertyHandler.setPropValue(Path, "awbno", awbno);
		PropertyHandler.setPropValue(Path, "dest", dest);
		PropertyHandler.setPropValue(Path, "agentCode", agentCode);
		PropertyHandler.setPropValue(Path, "Product", Product);
		PropertyHandler.setPropValue(Path, "pcs", pcs);
		PropertyHandler.setPropValue(Path, "wt", wt);
		PropertyHandler.setPropValue(Path, "vol", vol);
		PropertyHandler.setPropValue(Path, "ULDType", ULDType);
		PropertyHandler.setPropValue(Path, "noOfULD", noOfULD);
		PropertyHandler.setPropValue(Path, "fltDt", fltDt);
		PropertyHandler.setPropValue(Path, "carrierwithFlightno", carrierWithFlightno);

		PropertyHandler.setPropValue(Path, "uldNo", uldNo);
		test.log(LogStatus.INFO, "Data has been written to the property file");
		return this;

	}

	/**
	 * Method to modify FWB file
	 * 
	 * @param prefix
	 *            ,awbNo,org,dest,
	 * @param carrierwithFlightno
	 * @param fltDtwithYY
	 * @return
	 * @author Sharath on 29-01-2019
	 */
	public MSG005 modifytheFWBfile1leg(String path, String prefix, String awbNo, String org, String dest,
			String carrierwithFlightno, String fltDtwithYY) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);

		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		fltDtwithYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithYY);
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// pre
		modifyFile(path, split[1].substring(1, 4), prefix);

		// awb
		modifyFile(path, split[1].substring(5, 13), awbNo);

		// modify date
		String Dt[] = fltDtwithYY.split("20");
		// System.out.println(split[4]);
		// String dtRTG= split[4].replace(split[4], Dt[0].substring(0,
		// 2)+"RTG");
		System.out.println(split[4].substring(2, 5));
		// modifyFileReplaceOnly(path,split[4], fltDtwithYY.substring(0,
		// 2)+split[4].substring(2, 5));
		modifyFile(path, split[4].substring(0, 2) + "\r\n" + split[4].substring(2, 5),
				Dt[0].substring(0, 2) + "\r\nRTG");
		modifyFile(path, split[65], Dt[0].toUpperCase() + Dt[1]);

		return this;
	}

	/**
	 * Method to modify FWB file
	 * 
	 * @param prefix
	 *            ,awbNo,org,dest,
	 * @param carrierwithFlightno
	 * @param fltDtwithYY
	 * @return
	 * @author Sharath on 29-01-2019
	 */
	public MSG005 modifytheFWBfile2leg(String path, String prefix, String awbNo, String org, String dest,
			String carrierwithFlightno, String fltDtwithYY) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);

		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		fltDtwithYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithYY);
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// pre
		modifyFile(path, split[1].substring(1, 4), prefix);
		// awb
		modifyFile(path, split[1].substring(5, 13), awbNo);
		// modify date
		String Dt[] = fltDtwithYY.split("20");
		modifyFile(path, split[62], Dt[0].toUpperCase() + Dt[1]);

		return this;
	}

	/**
	 * Method to create FSU-RCS with the values present in properties file
	 * 
	 * @param: path,prefix,origin,awbNo,origin,dest,fltDtwithoutYY,pieces,wt,
	 *             ArrivalTime
	 * @author Faizan on 29-01-2019
	 * @throws IOException
	 */

	public MSG005 processGoodsAcceptancethroughFSU_RCS(String path, String prefix, String awbno, String origin,
			String dest, String fltDtwithoutYY, String pcs, String wt, String ArrivalTime) throws IOException {
		boolean isFound = true;
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		ArrivalTime = PropertyHandler.getPropValue(dataFilePath, ArrivalTime);
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");
		String splitawbdetails[] = split[1].split("-");

		int size = split.length;

		// modify awbpre
		modifyFile(messagefilepath, splitawbdetails[0].substring((splitawbdetails[0].length() - 3)), prefix);

		// modify awb and org+dest
		modifyFile(messagefilepath, splitawbdetails[1], awbno + origin + dest);

		// modify pieces and wt
		String splitpieces[] = split[5].split("K");
		pcs = splitpieces[0].replace(splitpieces[0].substring(1), pcs);
		wt = "K" + splitpieces[1].replace(splitpieces[1], wt);
		modifyFile(messagefilepath, split[5], pcs + wt);
		// should be receiving time not ArrivalTime
		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY + ArrivalTime);
		// modify origin
		modifyFile(messagefilepath, split[4], origin);

		return this;

	}

	public MSG005 processFlightFinalizalizationthroughFFM(String path, String carrierwithFlightno,
			String fltDtwithoutYY, String origin, String TailNo, String dest, String DepartureDate,
			String DepartureTime, String ArrivalDate, String ArrivalTime) throws IOException {
		boolean isFound = true;
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		TailNo = PropertyHandler.getPropValue(dataFilePath, TailNo);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		DepartureDate = PropertyHandler.getPropValue(dataFilePath, DepartureDate);
		DepartureTime = PropertyHandler.getPropValue(dataFilePath, DepartureTime);
		ArrivalDate = PropertyHandler.getPropValue(dataFilePath, ArrivalDate);
		ArrivalTime = PropertyHandler.getPropValue(dataFilePath, ArrivalTime);

		// String messagefilepath = "./resources/SampleMessages/FFM.txt";
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// modify flight number
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify origin
		modifyFile(messagefilepath, split[4], origin);

		// modify tail
		modifyFile(messagefilepath, split[5].substring(0, (split[5].length() - 3)), TailNo);

		// modify dest
		modifyFile(messagefilepath, split[5].substring(split[5].length() - 3), dest);

		// modify departure date and time
		modifyFile(messagefilepath, split[7], DepartureDate.concat(DepartureTime));

		// modify arrival date and time
		modifyFile(messagefilepath, split[8].substring(0, (split[8].length() - 4)), ArrivalDate.concat(ArrivalTime));

		return this;

	}

	/**
	 * Method to create FSU-DEP with the values present in properties file
	 * 
	 * @param: path,prefix,origin,awbNo,carrierwithFlightno,origin,dest,fltDtwithoutYY
	 *             ,pieces,wt,ArrivalTime,DepartureTime
	 * @author Faizan on 29-01-2019
	 * @throws IOException
	 */

	public MSG005 processDeparturethroughFSU_DEP(String path, String prefix, String awbno, String carrierwithFlightno,
			String origin, String dest, String fltDtwithoutYY, String pcs, String wt, String ArrivalTime,
			String DepartureTime) throws IOException {
		boolean isFound = true;
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		ArrivalTime = PropertyHandler.getPropValue(dataFilePath, ArrivalTime);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		DepartureTime = PropertyHandler.getPropValue(dataFilePath, DepartureTime);

		// String messagefilepath = "./resources/SampleMessages/FFM.txt";
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");
		String splitawbdetails[] = split[1].split("-");

		int size = split.length;

		// modify awbpre
		modifyFile(messagefilepath, splitawbdetails[0].substring((splitawbdetails[0].length() - 3)), prefix);

		// modify awb and org+dest
		modifyFile(messagefilepath, splitawbdetails[1], awbno + origin + dest);

		// modify pieces and wt
		String splitpieces[] = split[2].split("K");
		pcs = splitpieces[0].replace(splitpieces[0].substring(1), pcs);
		wt = "K" + splitpieces[1].replace(splitpieces[1].substring(0, (splitpieces[1].length() - 3)), wt);
		modifyFile(messagefilepath, split[2], pcs + wt);

		// modify flight details
		modifyFile(messagefilepath, split[3], carrierwithFlightno);

		// modify date
		modifyFile(messagefilepath, split[4], fltDtwithoutYY);

		// modify O-D pair
		modifyFile(messagefilepath, split[5], origin + dest);

		// modify pcs
		modifyFile(messagefilepath, split[6], pcs);

		// modify Std. Departure Time
		modifyFile(messagefilepath, split[7], "S".concat(DepartureTime));

		// modify Std. Arrival Time
		modifyFile(messagefilepath, split[8], "S".concat(ArrivalTime));

		return this;

	}

	public MSG005 processDLVthroughFSU_DLV(String path, String prefix, String awbno, String origin, String dest,
			String fltDtwithoutYY) throws IOException {
		boolean isFound = true;

		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbno = PropertyHandler.getPropValue(dataFilePath, awbno);

		// String messagefilepath = "./resources/SampleMessages/FFM.txt";
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		String[] subspl = split[1].split("-");
		// pre
		modifyFile(path, subspl[0].substring(1, 4), prefix);
		// awb
		modifyFile(path, subspl[1].substring(0, 8), awbno);
		// org
		modifyFile(path, subspl[1].substring(8, 11), origin);
		// dest
		modifyFile(path, subspl[1].substring(11, 14), dest);

		modifyFile(path, split[3].substring(0, 5), fltDtwithoutYY);

		return this;

	}

	public MSG005 modifytheFSUMANfile(String prefix, String awbNo, String org, String dest, String carrierwithFlightno,
			String fltDtwithoutYY, String path, String pcs, String wt) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);

		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		// String messagefilepath = "./resources/SampleMessages/FSU_MAN.txt";
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");
		String splitawbdetails[] = split[1].split("-");

		int size = split.length;

		// modify awbpre
		modifyFile(messagefilepath, splitawbdetails[0].substring(2, 5), prefix);

		// modify awb and org+dest
		modifyFile(messagefilepath, splitawbdetails[1], awbNo + org + dest);

		// modify fltno.
		modifyFile(messagefilepath, split[3], carrierwithFlightno);

		// modify date
		modifyFile(messagefilepath, split[4], fltDtwithoutYY);

		// modify orgdest
		modifyFile(messagefilepath, split[5], org + dest);
		// modify pieces
		String[] wtandpieces = split[6].split("T");
		String[] wtandpieces1 = wtandpieces[1].split("K");
		modifyFile(messagefilepath, wtandpieces1[0], pcs);

		// modify weight
		modifyFile(messagefilepath, wtandpieces1[1], wt);

		// modify pieces and weight
		modifyFile(messagefilepath, split[2].substring(0, 7), split[6]);

		return this;

	}

	public MSG005 modifytheFSURCFfile(String prefix, String awbNo, String org, String dest, String carrierwithFlightno,
			String fltDtwithoutYY, String path, String pcs, String wt, String time) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		wt = PropertyHandler.getPropValue(dataFilePath, wt);
		time = PropertyHandler.getPropValue(dataFilePath, time);

		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		// String messagefilepath = "./resources/SampleMessages/FSU_MAN.txt";
		String messagefilepath = path;
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String spl[] = strold.split("/");

		String[] subspl = spl[1].split("-");
		String pre = subspl[0].substring(1, 4);
		// pre
		modifyFile(path, subspl[0].substring(1, 4), prefix);
		// awb
		modifyFile(path, subspl[1].substring(0, 8), awbNo);
		// org
		modifyFile(path, subspl[1].substring(8, 11), "abc");
		// dest
		modifyFile(path, subspl[1].substring(11, 14), "xyz");
		modifyFile(path, "abc", org);
		modifyFile(path, "xyz", dest);
		// pcs and wt
		subspl = spl[2].split("R");
		modifyFile(path, subspl[0], "T" + pcs + "L" + wt);
		// flt
		modifyFile(path, spl[3], carrierwithFlightno);
		// fltdt
		modifyFile(path, spl[4].substring(0, 5), fltDtwithoutYY);

		// flt time
		modifyFile(path, spl[4].substring(5, 9), time);

		return this;

	}

	// A-8680
	public MSG005 modifytheFFMfile(String prefix, String awbNo, String org, String dest, String fltNo,
			String fltDtwithoutYY, String pieces, String weight, String carrierwithFlightno, String path,
			String deptime, String uldNo) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		deptime = PropertyHandler.getPropValue(dataFilePath, deptime);
		uldNo = PropertyHandler.getPropValue(dataFilePath, uldNo);
		test.log(LogStatus.INFO, "Starting with updating the FFM file");
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// reset origin and dest
		// modify org, dest
		modifyFile(messagefilepath, split[4].substring(0, 3), "abc");
		modifyFile(messagefilepath, split[4].substring(3, 6), "xyz");

		// modify flight details
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify org, dest
		modifyFile(messagefilepath, "abc", org);
		modifyFile(messagefilepath, "xyz", dest);

		// modify date
		// modifyFile(messagefilepath, split[5].substring(0, 5),
		// fltDtwithoutYY);

		// modify dep time
		modifyFile(messagefilepath, split[5].substring(5, 9), deptime);

		// modify uldNo
		modifyFile(messagefilepath, split[7], uldNo);

		// modify pre,awb,org+dest
		String[] preawb = split[8].split("-");
		modifyFile(messagefilepath, preawb[1].substring(1, 4), prefix);
		modifyFile(messagefilepath, preawb[2].substring(0, 8), awbNo);
		// modifyFile(messagefilepath, preawb[2].substring(8, 11), org);
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify pcs and wt
		String[] wtandpieces = split[9].split("T");
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify pcs and wt

		modifyFile(messagefilepath, split[9], "T" + pieces + "L" + weight + "MC1");
		// modifyFile(messagefilepath, wtandpieces[1].substring(0, 1),pieces);
		// modifyFile(messagefilepath, wtandpieces[1].substring(2, 5),weight);

		test.log(LogStatus.INFO, "The FFM file has been updated");

		return this;

	}

	// A-8680
	public MSG005 modifytheFFMfileMultipleULDs(String prefix, String awbNo, String org, String dest, String fltNo,
			String fltDtwithoutYY, String pieces, String weight, String carrierwithFlightno, String path,
			String deptime, String... uldNo) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		deptime = PropertyHandler.getPropValue(dataFilePath, deptime);
		test.log(LogStatus.INFO, "Starting with updating the FFM file");
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// reset origin and dest
		// modify org, dest
		modifyFile(messagefilepath, split[4].substring(0, 3), "abc");
		modifyFile(messagefilepath, split[4].substring(3, 6), "xyz");

		// modify flight details
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify org, dest
		modifyFile(messagefilepath, "abc", org);
		modifyFile(messagefilepath, "xyz", dest);

		// modify date
		// modifyFile(messagefilepath, split[5].substring(0, 5),
		// fltDtwithoutYY);

		// modify dep time
		modifyFile(messagefilepath, split[5].substring(5, 9), deptime);

		// modify uldNo
		String uld1 = PropertyHandler.getPropValue(dataFilePath, uldNo[0]);
		modifyFile(messagefilepath, split[7], "aaa");
		modifyFile(messagefilepath, "aaa", uld1);

		// modify pre,awb,org+dest
		String[] preawb = split[8].split("-");
		modifyFile(messagefilepath, preawb[1].substring(1, 4), prefix);
		modifyFile(messagefilepath, preawb[2].substring(0, 8), awbNo);
		// modifyFile(messagefilepath, preawb[2].substring(8, 11), org);
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify pcs and wt
		String[] wtandpieces = split[9].split("T");
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify pcs and wt

		modifyFile(messagefilepath, split[9], "T" + pieces + "L" + weight + "MC1");
		// modifyFile(messagefilepath, wtandpieces[1].substring(0, 1),pieces);
		// modifyFile(messagefilepath, wtandpieces[1].substring(2, 5),weight);

		// modify uldNo
		String uld2 = PropertyHandler.getPropValue(dataFilePath, uldNo[1]);
		modifyFile(messagefilepath, split[11], "bbb");
		modifyFile(messagefilepath, "bbb", uld2);
		// modify uldNo
		String uld3 = PropertyHandler.getPropValue(dataFilePath, uldNo[2]);
		modifyFile(messagefilepath, split[15], "ccc");
		modifyFile(messagefilepath, "ccc", uld3);

		test.log(LogStatus.INFO, "The FFM file has been updated");

		return this;

	}

	// A-8680
	public MSG005 modifytheFFMfile2ULDs(String prefix, String awbNo, String org, String dest, String fltNo,
			String fltDtwithoutYY, String pcs1, String pcs2, String wt1, String wt2, String carrierwithFlightno,
			String path, String deptime, String... uldNo) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		pcs1 = PropertyHandler.getPropValue(dataFilePath, pcs1);
		pcs2 = PropertyHandler.getPropValue(dataFilePath, pcs2);
		wt1 = PropertyHandler.getPropValue(dataFilePath, wt1);
		wt2 = PropertyHandler.getPropValue(dataFilePath, wt2);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		deptime = PropertyHandler.getPropValue(dataFilePath, deptime);
		test.log(LogStatus.INFO, "Starting with updating the FFM file");
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// reset origin and dest
		// modify org, dest
		modifyFile(messagefilepath, split[4].substring(0, 3), "abc");
		modifyFile(messagefilepath, split[4].substring(3, 6), "xyz");

		// modify flight details
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify org, dest
		modifyFile(messagefilepath, "abc", org);
		modifyFile(messagefilepath, "xyz", dest);

		// modify date
		// modifyFile(messagefilepath, split[5].substring(0, 5),
		// fltDtwithoutYY);

		// modify dep time
		modifyFile(messagefilepath, split[5].substring(5, 9), deptime);

		// modify uldNo
		String uld1 = PropertyHandler.getPropValue(dataFilePath, uldNo[0]);
		modifyFile(messagefilepath, split[7], "aaa");
		modifyFile(messagefilepath, "aaa", uld1);

		// modify pre,awb,org+dest
		String[] preawb = split[8].split("-");
		modifyFile(messagefilepath, preawb[1].substring(1, 4), prefix);
		modifyFile(messagefilepath, preawb[2].substring(0, 8), awbNo);
		// modifyFile(messagefilepath, preawb[2].substring(8, 11), org);
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify pcs and wt
		String[] wtandpieces = split[9].split("T");
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// pcs and wt in 1stULD
		modifyFile(messagefilepath, split[9], "T" + pcs1 + "L" + wt1 + "MC1");
		// modifyFile(messagefilepath, wtandpieces[1].substring(0, 1),pieces);
		// modifyFile(messagefilepath, wtandpieces[1].substring(2, 5),weight);

		// modify uldNo
		String uld2 = PropertyHandler.getPropValue(dataFilePath, uldNo[1]);
		modifyFile(messagefilepath, split[11], "bbb");
		modifyFile(messagefilepath, "bbb", uld2);

		// pcs and wt in 2ndULD
		modifyFile(messagefilepath, split[13], "T" + pcs2 + "L" + wt2 + "MC1");
		test.log(LogStatus.INFO, "The FFM file has been updated");

		return this;

	}

	// A-8680
	public MSG005 modifytheFFMfilenonULD(String prefix, String awbNo, String org, String dest, String fltNo,
			String fltDtwithoutYY, String pieces, String weight, String carrierwithFlightno, String path,
			String deptime) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		deptime = PropertyHandler.getPropValue(dataFilePath, deptime);

		test.log(LogStatus.INFO, "Starting with updating the FFM file");
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// modify flight details
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify org, dest
		modifyFile(messagefilepath, split[4].substring(0, 3), org);
		modifyFile(messagefilepath, split[4].substring(3, 6), dest);

		// modify date
		modifyFile(messagefilepath, split[5].substring(0, 5), fltDtwithoutYY);

		// modify dep time
		modifyFile(messagefilepath, split[5].substring(5, 9), deptime);

		/*
		 * //modify uldNo modifyFile(messagefilepath, split[7], uldNo);
		 */
		// modify pre,awb,org+dest
		String[] preawb = split[6].split("-");
		modifyFile(messagefilepath, preawb[0].substring(9, 12), prefix);
		modifyFile(messagefilepath, preawb[1].substring(0, 8), awbNo);
		modifyFile(messagefilepath, preawb[1].substring(8, 11), org);
		modifyFile(messagefilepath, preawb[1].substring(11, 14), dest);

		// modify pcs and wt
		modifyFile(messagefilepath, split[7], "T" + pieces + "L" + weight + "MC1");
		/*
		 * String[] wtandpieces = split[7].split("T"); //
		 * modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);
		 * 
		 * // modify awb with org and dest modifyFile(messagefilepath,
		 * wtandpieces[1].substring(0, 2),pieces); modifyFile(messagefilepath,
		 * wtandpieces[1].substring(3, 6),weight);
		 */
		test.log(LogStatus.INFO, "The FFM file has been updated");
		return this;
	}

	// Sharath
	public MSG005 ListAndVerifyMsg(String awbNo, String origin, String dest, String msgform) {
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		// msgform = PropertyHandler.getPropValue(dataFilePath, msgform);
		enterKeys(txt_calFromDate, "-1" + Keys.TAB);
		enterKeys(txt_messgeType, msgform);
		minWait();
		click(btn_reference);
		minWait();
		enterKeys(txt_awbpfx, awbNo);
		minWait();
		enterKeys(txt_awbpfx, Keys.F12);
		// driver.findElement(btn_list).click();
		click(btn_list);
		check(chk_selectMessage);
		String actualvalue = getText_JS(By.xpath("//table[@id='listmessaging']/tbody/tr[1]/td[2]")).replaceAll("-", "")
				.replaceAll(" ", "");
		minWait();
		String outvalue = getText_JS(By.xpath("//table[@id='listmessaging']/tbody/tr[1]/td[7]")).trim();
		if (actualvalue.contains(awbNo) && outvalue.contains("TULCGAA")) {
			test.log(LogStatus.PASS, "The " + msgform + " has been triggered for this booking");
			captureAndAddScreenshot();
		} else {
			test.log(LogStatus.FAIL, "The " + msgform + " hasn't been triggered for this booking");
			captureAndAddScreenshot();
			Assert.fail("The " + msgform + " hasn't been triggered for this booking");
		}
		return this;
	}

	// A-8452 To check if message is sent from I-Cargo
	public MSG005 checkIfMsgTriggered(String MsgType, String awbPre, String awbNo, String origin, String dest) {

		MsgType = PropertyHandler.getPropValue(dataFilePath, MsgType);
		awbPre = PropertyHandler.getPropValue(dataFilePath, awbPre);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		origin = PropertyHandler.getPropValue(dataFilePath, origin);

		minWait();
		enterKeys(txt_messgeType, MsgType);
		click(btn_reference);
		minWait();

		By txt_awbPre = By.xpath("//td[contains(text(), 'AWBPFX')]/following::td[1]/input");
		By txt_awbNo = By.xpath("//td[contains(text(), 'AWBSER')]/following::td[1]/input");

		enterKeys(txt_awbPre, awbPre);
		enterKeys(txt_awbNo, awbNo);
		minWait();
		click(btn_list);
		minWait();

		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2,
				awbPre + " - " + awbNo + " - " + origin + " - " + dest);
		Assert.assertTrue(msgStatus.contains("Sending") || msgStatus.contains("Sent"),
				"ERROR : " + MsgType + " Message Not sent");
		if (msgStatus.contains("Sending") || msgStatus.contains("Sent")) {
			test.log(LogStatus.PASS, "Message successfully sent from ICargo");
		} else
			test.log(LogStatus.FAIL, "Message was not sent from ICargo");

		return this;

	}

	// A-8680
	public MSG005 modifytheFFMfileWithoutULD(String prefix, String awbNo, String org, String dest, String fltNo,
			String fltDtwithoutYY, String pieces, String weight, String carrierwithFlightno, String path,
			String deptime) throws IOException {
		prefix = PropertyHandler.getPropValue(dataFilePath, prefix);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		org = PropertyHandler.getPropValue(dataFilePath, org);
		dest = PropertyHandler.getPropValue(dataFilePath, dest);
		fltNo = PropertyHandler.getPropValue(dataFilePath, fltNo);
		fltDtwithoutYY = PropertyHandler.getPropValue(dataFilePath, fltDtwithoutYY);

		pieces = PropertyHandler.getPropValue(dataFilePath, pieces);
		weight = PropertyHandler.getPropValue(dataFilePath, weight);
		carrierwithFlightno = PropertyHandler.getPropValue(dataFilePath, carrierwithFlightno);
		deptime = PropertyHandler.getPropValue(dataFilePath, deptime);

		test.log(LogStatus.INFO, "Starting with updating the FFM file");
		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// modify flight details
		modifyFile(messagefilepath, split[2], carrierwithFlightno);

		// modify flight date
		modifyFile(messagefilepath, split[3], fltDtwithoutYY);

		// modify org, dest
		modifyFile(messagefilepath, split[4].substring(0, 3), org);
		modifyFile(messagefilepath, split[4].substring(3, 6), dest);

		// modify date
		modifyFile(messagefilepath, split[5].substring(0, 5), fltDtwithoutYY);

		// modify dep time
		modifyFile(messagefilepath, split[5].substring(5, 9), deptime);

		// modify pre,awb,org+dest
		String[] preawb = split[6].split("-");
		modifyFile(messagefilepath, preawb[0].substring(9, 12), prefix);
		modifyFile(messagefilepath, preawb[1].substring(0, 8), awbNo);
		modifyFile(messagefilepath, preawb[1].substring(8, 11), org);
		modifyFile(messagefilepath, preawb[1].substring(11, 14), dest);

		// modify pcs and wt
		String[] wtandpieces = split[7].split("T");
		// modifyFile(messagefilepath, preawb[2].substring(11, 14), dest);

		// modify awb with org and dest
		modifyFile(messagefilepath, wtandpieces[1].substring(0, 2), pieces);
		modifyFile(messagefilepath, wtandpieces[1].substring(3, 6), weight);
		test.log(LogStatus.INFO, "The FFM file has been updated");
		return this;
	}

	// Faizan
	public MSG005 updatePSNMessage(String PreAWB, String awbNo, String todaysDt, String messagefilepath, String pcs)
			throws IOException {
		PreAWB = PropertyHandler.getPropValue(dataFilePath, PreAWB);
		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);
		todaysDt = PropertyHandler.getPropValue(dataFilePath, todaysDt);
		pcs = PropertyHandler.getPropValue(dataFilePath, pcs);
		todaysDt = todaysDt.toUpperCase().replaceAll("-", "");
		// String NewFullAWB= PreAWB+"-"+awbNo;
		test.log(LogStatus.INFO, "Starting with updating the PSN Message file");
		// String messagefilepath = messagePath+MsgFileName;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		String split[] = strold.split("/");
		// String fullAWB=split[0].substring(3,15);
		// modify flight details
		modifyFile(messagefilepath, split[0].substring(3, 6), PreAWB);
		modifyFile(messagefilepath, split[0].substring(7, 15), awbNo);
		// modify pcs
		modifyFile(messagefilepath, split[1], "SF-" + pcs);
		// modify flight date
		modifyFile(messagefilepath, split[2], todaysDt);
		return this;
	}

	/**
	 * Description : Method will upload FSU_RCF message in the messagePath with
	 * MQ-SERIES
	 * 
	 * @author A-8452 Faizan
	 * @param messagePath
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 */
	public MSG005 uploadFSU_RCFMessage(String messagePath, String participantType, String participant, String station) {

		File f = new File(messagePath);
		messagePath = f.getAbsolutePath();
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		// station = PropertyHandler
		// .getPropValue(dataFilePath, station);
		logger.info("Uploaing message");
		String uploadedMessageStatus = null;
		logger.info("Message path is " + messagePath);
		click(btn_loadFromFile);
		switchToSecondWindow();
		selectByText(loadFromFile_list_participantType, participantType);
		if (!participantType.equals("All")) {
			enterKeys(loadFromFile_txt_participant, participant);
		}
		selectByText(loadFromFile_list_transmissionMode, "MQ-SERIES");
		selectByText(By.xpath("//*[@name='interfaceSystem']"), "SABRE Sync - Incoming");
		enterKeys(loadFromFile_txt_station, station);
		click(loadFromFile_btn_address);
		switchToNthWindow(3);
		// enterKeys(messageAddress_txt_FileListner, "IBSAT");
		enterKeys(By.xpath("//label[contains(text(),'Primary Host')]/../input[@name='textValues']"), "IBSAT");
		click(messageAddressDetails_btn_ok);
		switchToSecondWindow();
		minWait();
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		click(loadFromFile_btn_load);
		uploadedMessageStatus = waitForElement(dialogue_msg).getText();
		captureAndAddScreenshot();
		click(dialogue_btn_ok);
		click(loadFromFile_btn_close);
		switchToFirstWindow();
		logger.info("Message uploaded. Status is '" + uploadedMessageStatus + "'");
		waitForFrameAndSwitch(contentFrame);
		// Message loaded successfully, decoded successfully.
		// Message loaded successfully, processed successfully.
		if (uploadedMessageStatus.contains("Message loaded successfully")) {
			test.log(LogStatus.PASS, "Message has been uploaded successfully");
			logger.info("Message has been uploaded successfully");
		} else {
			test.log(LogStatus.FAIL,
					"Message wasn't uploaded successfully, having status : " + uploadedMessageStatus);
			logger.info("Message wasn't uploaded successfully, having status : " + uploadedMessageStatus);
		}
		return this;
	}

	/**
	 * Description : Method will upload FSU_RCF message in the messagePath with
	 * MQ-SERIES
	 * 
	 * @author A-8452 Faizan
	 * @param messagePath
	 * @param participantType
	 * @param participant
	 * @param station
	 * @return
	 */
	public MSG005 uploadSabreIncomingMessage(String messagePath, String participantType, String participant,
			String station) {

		File f = new File(messagePath);
		messagePath = f.getAbsolutePath();
		participantType = PropertyHandler.getPropValue(dataFilePath, participantType);
		participant = PropertyHandler.getPropValue(dataFilePath, participant);
		station = PropertyHandler.getPropValue(dataFilePath, station);
		logger.info("Uploaing message");
		String uploadedMessageStatus = null;
		logger.info("Message path is " + messagePath);
		click(btn_loadFromFile);
		switchToSecondWindow();
		selectByText(loadFromFile_list_participantType, participantType);
		if (!participantType.equals("All")) {
			enterKeys(loadFromFile_txt_participant, participant);
		}
		selectByText(loadFromFile_list_transmissionMode, "MQ-SERIES");
		selectByText(By.xpath("//*[@name='interfaceSystem']"), "SABRE Sync - Incoming");
		enterKeys(loadFromFile_txt_station, station);
		click(loadFromFile_btn_address);
		switchToNthWindow(3);
		// enterKeys(messageAddress_txt_FileListner, "IBSAT");
		enterKeys(By.xpath("//label[contains(text(),'Primary Host')]/../input[@name='textValues']"), "IBSAT");
		click(messageAddressDetails_btn_ok);
		switchToSecondWindow();
		minWait();
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		click(loadFromFile_btn_load);
		uploadedMessageStatus = waitForElement(dialogue_msg).getText();
		captureAndAddScreenshot();
		click(dialogue_btn_ok);
		click(loadFromFile_btn_close);
		switchToFirstWindow();
		logger.info("Message uploaded. Status is '" + uploadedMessageStatus + "'");
		waitForFrameAndSwitch(contentFrame);
		// Message loaded successfully, decoded successfully.
		// Message loaded successfully, processed successfully.
		if (uploadedMessageStatus.contains("Message loaded successfully")) {
			test.log(LogStatus.PASS, "Message has been uploaded successfully");
			logger.info("Message has been uploaded successfully");
		} else {
			test.log(LogStatus.FAIL,
					"Message wasn't uploaded successfully, having status : " + uploadedMessageStatus);
			logger.info("Message wasn't uploaded successfully, having status : " + uploadedMessageStatus);
		}
		return this;
	}

	/**
	 * @author a-8452 Faizan
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public MSG005 ResetFFM_ULDfile(String path) throws IOException {

		String messagefilepath = path;
		File file = new File(messagefilepath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;

		String split[] = strold.split("/");

		// modify pre,awb,org+dest
		String[] preawb = split[8].split("-");
		modifyFile(messagefilepath, preawb[2].substring(8, 11), "ABC");
		modifyFile(messagefilepath, preawb[2].substring(11, 14), "XYZ");

		logger.info("The FFM file has been reset");
		return this;

	}

	/**
	 * Method to modify FWB file
	 * 
	 * @param prefix
	 *            ,awbNo,org,dest,
	 * @param carrierwithFlightno
	 * @param fltDtwithYY
	 * @return
	 * @author Sharath on 29-01-2019
	 */
	public MSG005 modifytheCarditMessageFilePropFile(String path, String origin, String dest, String intermediate)
			throws IOException {
		/*
		 * origin = PropertyHandler.getPropValue(dataFilePath, origin); dest =
		 * PropertyHandler.getPropValue(dataFilePath, dest);
		 */
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();
		// Cardit ID
		String carditId[] = strold.split("BGM++");
		String carditIDstr = carditId[1].substring(8, 14);
		int carditIDint = Integer.parseInt(carditIDstr);
		carditIDint = ++carditIDint;
		carditIDstr = Integer.toString(carditIDint);
		modifyFile(path, carditId[1].substring(2, 14), carditId[1].substring(2, 8) + carditIDstr);

		// Store footer
		String footer[] = strold.split("UNT");
		String UNTData[] = footer[1].split("UNZ");
		String footerData = "UNZ" + UNTData[0] + "\n" + "UNT" + UNTData[1];

		// remove mailBags and footer
		removeLineFromFile(path, "GID");
		removeLineFromFile(path, "MEA");
		removeLineFromFile(path, "CNI");
		removeLineFromFile(path, "UNT");
		removeLineFromFile(path, "UNZ");

		// Update origin
		String org[] = strold.split("LOC+84+");
		modifyFile(path, org[1].substring(0, 3), "origin");
		modifyFile(path, "origin", origin);

		// Update intermediate
		String inter[] = strold.split("LOC+13+");
		modifyFile(path, inter[1].substring(0, 3), "intermediate");
		modifyFile(path, "intermediate", intermediate);

		// Update dest
		String desti[] = strold.split("LOC+1+");
		modifyFile(path, desti[1].substring(0, 3), "destination");
		modifyFile(path, "destination", dest);

		// add data to append

		// write footer
		appendLine(path, footerData);

		return this;
	}

	public MSG005 modifytheCarditMessageFileExcelFileITNLMultileg(String path, List<Map<String, String>> getRawData)
			throws IOException {
		/*
		 * origin = PropertyHandler.getPropValue(dataFilePath, origin); dest =
		 * PropertyHandler.getPropValue(dataFilePath, dest);
		 */
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

		String originExcel = getRawData.get(1).get("Origin");
		String intermediateExcel = getRawData.get(1).get("Transfer");
		String destinationExcel = getRawData.get(1).get("Dest");
		String fltno1Excel = getRawData.get(1).get("Flight1");
		String fltno2Excel = getRawData.get(1).get("Flight2");

		// Cardit ID
		String carditId[] = strold.split("BGM++");
		String carditIDstr = carditId[1].substring(8, 14);
		int carditIDint = Integer.parseInt(carditIDstr);
		carditIDint = ++carditIDint;
		carditIDstr = Integer.toString(carditIDint);
		modifyFile(path, carditId[1].substring(2, 14), carditId[1].substring(2, 8) + carditIDstr);

		// Store footer
		String footer[] = strold.split("UNT");
		String UNTData[] = footer[1].split("UNZ");
		String footerData = "UNT" + UNTData[0] + "\n" + "UNZ" + UNTData[1];

		// remove mailBags and footer
		removeLineFromFile(path, "GID");
		removeLineFromFile(path, "MEA");
		removeLineFromFile(path, "CNI");
		removeLineFromFile(path, "UNT");
		removeLineFromFile(path, "UNZ");

		// Update origin
		String org[] = strold.split("LOC\\+84\\+");
		modifyFile(path, org[1].substring(0, 3), "origin");
		modifyFile(path, "origin", originExcel);

		// Update intermediate
		String inter[] = strold.split("LOC\\+13\\+");
		modifyFile(path, inter[1].substring(0, 3), "intermediate");
		modifyFile(path, "intermediate", intermediateExcel);

		// Update dest
		String desti[] = strold.split("LOC\\+1\\+");
		modifyFile(path, desti[1].substring(0, 3), "destination");
		modifyFile(path, "destination", destinationExcel);

		// Update Flights
		String flt1[] = strold.split("TDT\\+10\\+");
		modifyFile(path, flt1[1].substring(0, 6), "flt1");
		modifyFile(path, "flt1", fltno1Excel);

		String flt2[] = strold.split("TDT\\+20\\+");
		modifyFile(path, flt2[1].substring(0, 6), "flt2");
		modifyFile(path, "flt2", fltno2Excel);

		// add data to append
		// String originExcel, intermediateExcel, destExcel;
		for (Map<String, String> hm : getRawData) {
			String appendLineData = "CNI++" + hm.get("MaibagID") + "\n" + "GID++:PC'" + "\n" + "MEA+WT+AAB+KGM:"
					+ hm.get("Weight") + "'";
			appendLine(path, appendLineData);
		}

		// write footer
		appendLine(path, footerData);

		// remove empty lines if any
		removeEmptyLineFromFile(path);

		return this;
	}

	public MSG005 modifytheCarditMessageFileExcelFileITNLSinglelegMultiMailBags(String path, List<Map<String, String>> getRawData) throws IOException {
		
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

		String firstLine[] = strold.split("UNH");
		String header = firstLine[0];

		String lastLine[] = strold.split("UNZ");
		String footer = "UNZ" + lastLine[1]; // add no. of rows here: UNZ+ no of
												// mailbags+ further data

		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush();
		pw.close();

		refID = getRawData.get(1).get("UNHid");

		appendLine(path, header);

		boolean isFirst = true;
		int z = 0;
		for (Map<String, String> hm : getRawData) {
			z++;
			if (!hm.get("UNHid").equalsIgnoreCase(refID) || isFirst) {
				if (!isFirst) {
					String untID = getRawData.get(z - 2).get("UNHid");
					String UNTLine = "UNT+60+" + untID + "'";
					appendLine(path, UNTLine);
				}

				System.out.println("header first");
				String mailBag = "UNH+" + hm.get("UNHid") + "+IFCSUM:D:96A:UN:CNS200'\n" + "BGM++"
						+ hm.get("ConsignmentId") + "+47'\n"
						+ "DTM+137:"+hm.get("dateTime0")+":201'\nFTX+ABK++A'\nRFF+AHI:ICAIR151AA'\nTCC+C'\n" + "EQN+"
						+ hm.get("eqnNo") + ":NMB'\n" + "QTY+101:" + hm.get("mailBagWt") + ":KGM'\n" + "TCC+U'\n"
						+ "EQN+" + hm.get("eqnNo2") + ":NMB'\n" + "QTY+101:" + hm.get("mailBagWt2")
						+ ":KGM'\nTDT+Z90'\nLOC+84+" + hm.get("origin") + "::3:" + hm.get("origin") + "'\n" + "DTM+234:"
						+ hm.get("dateTime") + ":201'\nTDT+Z90'\n" + "LOC+1+" + hm.get("dest") + "::3:"
						+ hm.get("dest") + "'\n" + "DTM+63:" + hm.get("dateTime1") + ":201'\n" + "TDT+20+"
						+ hm.get("Flight1") + "+4'\nTSR+" + hm.get("tsrSuffix") + "'\n" + "LOC+5+" + hm.get("origin")
						+ ":163:3'\nLOC+7+" + hm.get("dest") + ":163:3'\n" + "DTM+189:" + hm.get("dateTime3")
						+ ":201'\nDTM+232:" + hm.get("dateTime4") + ":201'\n";
				appendLine(path, mailBag);
				refID = hm.get("UNHid");
			}
			System.out.println("only body");
			String mails = "CNI++" + hm.get("MaibagID") + "'\n" + "FTX+INS++N'\n" + "FTX+AAP++Y+" + hm.get("dateTime4")
					+ "'\n" + "GID++:PU'\n" + "MEA+WT+AAB+KGM:" + hm.get("weight") + "'\n";
			appendLine(path, mails);
			isFirst = false;
		}

		int size = getRawData.size();
		String untID = getRawData.get(size - 1).get("UNHid");
		String UNTLine = "UNT+60+" + untID + "'";
		appendLine(path, UNTLine);
		
		// write footer
		appendLine(path, footer);

		// remove empty lines if any
		removeEmptyLineFromFile(path);

		return this;
	}

	// Sharath
	public void appendLine(String filepath, String textToAppend) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
		// bw.newLine();
		bw.append("\n");
		bw.write(textToAppend);
		bw.close();
	}

	public void useDataToModifyCarditItnl(List<Map<String, String>> getRawData) {
		for (Map<String, String> hm : getRawData) {
			if (!hm.get("type").equalsIgnoreCase("itnl") && !hm.get("type").equalsIgnoreCase("dmst")) {
				continue;
			}
			// write code to modify cardit itnl
			switch (hm.get("type")) {

			case "itnl":

				break;

			case "dmst":

				break;

			}

			// call method to upload cardit
		}
	}

	public void removeLine(String lineContent, String filePath) throws IOException {
		File file = new File(filePath);
		File temp = new File("_temp_");
		PrintWriter out = new PrintWriter(new FileWriter(temp));
		Files.lines(file.toPath()).filter(line -> !line.contains(lineContent)).forEach(out::println);
		out.flush();
		out.close();
		temp.renameTo(file);
	}

	public void removeLineFromFile(String file, String lineToRemove) {

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim()
						.contains(lineToRemove) /*
												 * || !line.trim().contains(
												 * lineToRemove[1]) ||
												 * !line.trim().contains(
												 * lineToRemove[2]) ||
												 * !line.trim().contains(
												 * lineToRemove[3]) ||
												 * !line.trim().contains(
												 * lineToRemove[4])
												 */) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void removeEmptyLineFromFile(String file) {

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.isEmpty() /*
									 * || !line.trim().contains(lineToRemove[1])
									 * || !line.trim().contains(lineToRemove[2])
									 * || !line.trim().contains(lineToRemove[3])
									 * || !line.trim().contains(lineToRemove[4])
									 */) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
  /*  * Description : Method will upload Cardit Message
    * @author A-8457 Souvik
    * @param messagePath
    * @param participantType
    * @param participant
    * @param station
    * @return
    */
	public MSG005 UploadCardit(String messagePath, String participantType, String participant, String station) {

		File f = new File(messagePath);
		messagePath = f.getAbsolutePath();
		logger.info("Uploaing message");
		String uploadedMessageStatus = null;
		logger.info("Message path is " + messagePath);
		click(btn_loadFromFile);
		switchToSecondWindow();
		selectByText(loadFromFile_list_participantType, participantType);
		if (!participantType.equals("All")) {
			enterKeys(loadFromFile_txt_participant, participant);
		}
		selectByText(loadFromFile_list_transmissionMode, "FILE");
		enterKeys(loadFromFile_txt_station, station);
		click(loadFromFile_btn_address);
		switchToNthWindow(3);
		enterKeys(By.xpath("//input[@name='textValues']"), "IBSAT");
		click(messageAddressDetails_btn_ok);
		switchToSecondWindow();
		minWait();
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		waitForElement(loadFromFile_btn_chooseFile).sendKeys(messagePath);
		click(loadFromFile_btn_load);
		uploadedMessageStatus = waitForElement(dialogue_msg).getText();
		click(dialogue_btn_ok);
		click(loadFromFile_btn_close);
		switchToFirstWindow();
		logger.info("Message uploaded. Status is '" + uploadedMessageStatus + "'");
		waitForFrameAndSwitch(contentFrame);
		if (uploadedMessageStatus.contains("Message loaded successfully")) {
			test.log(LogStatus.PASS, "Cardit has been uploaded successfully");
			captureAndAddScreenshot();
			logger.info("Cardit has been uploaded successfully");
		} else {
			test.log(LogStatus.FAIL,
					"Cardit wasn't uploaded successfully, having status : " + uploadedMessageStatus);
			logger.info("Cardit wasn't uploaded successfully, having status : " + uploadedMessageStatus);
		}
		return this;
	}
	
	public MSG005 modifyIFCSUMCarditFile(String path, List<Map<String, String>> getRawData) throws IOException {
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

	/*	String BGMLine[] = strold.split("BGM");
		String BGM = firstLine[0];*/

		/*String lastLine[] = strold.split("UNZ");
		String footer = "UNZ" + lastLine[1]; // add no. of rows here: UNZ+ no of
												// mailbags+ further data
*/
		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush();
		pw.close();

		refID = getRawData.get(1).get("UNHid");
		PropertyHandler.setPropValue(dataFilePath, "refId", refID);
//		appendLine(path, header);

		boolean isFirst = true;
		int z = 0;
		for (Map<String, String> hm : getRawData) {
			z++;
			if (!hm.get("UNHid").equalsIgnoreCase(refID) || isFirst) {
			/*	if (!isFirst) {
					String untID = getRawData.get(z - 2).get("UNHid");
					String UNTLine = "UNT+60+" + untID + "'";
					appendLine(path, UNTLine);
				}
*/
				System.out.println("header first");
				String mailBag = "UNH+"+hm.get("UNHid")+"+IFCSUM:D:96A:UN:CNS200'\n"
				+"BGM++"+hm.get("ConsignmentID")+"+47'\n"
				+"DTM+137:"+hm.get("dateTime0")+":201'\n"
				+"FTX+ABK++B'\n"
				+"RFF+AHI:ICAIR151AA'\n"
				+"TCC+U'\n"
				+"EQN+1:NMB'\n"
				+"QTY+101:"+hm.get("totalWt")+":KGM'\n"
				+"TDT+Z90'\n"
				+"LOC+84+"+hm.get("origin")+"::3:"+hm.get("origin")+"'\n"
				+"DTM+234:"+hm.get("dateTime1")+":201'\n"
				+"TDT+Z90'\n"
				+"LOC+1+"+hm.get("dest")+"::3:"+hm.get("dest")+"'\n"
				+"DTM+63:"+hm.get("dateTime2")+":201'\n"
				+"TDT+20+"+hm.get("flightNo")+"+4'\n"
				+"TSR+DOC'\n"
				+"LOC+5+"+hm.get("origin")+":163:3'\n"
				+"LOC+7+"+hm.get("dest")+":163:3'\n"
				+"DTM+189:"+hm.get("dateTime3")+":201'\n"
				+"DTM+232:"+hm.get("dateTime4")+":201'\n";
						
				appendLine(path, mailBag);
				refID = hm.get("UNHid");
			}
			System.out.println("only body");
			String mails = "CNI++" + hm.get("MaibagID") + "'\n" + "FTX+INS++N'\n" + "FTX+AAP++Y+" + hm.get("dateTime4")
					+ "'\n" + "GID++:BG'\n" + "MEA+WT+AAB+KGM:" + hm.get("weight") + "'\n";
			appendLine(path, mails);
			isFirst = false;
		}

		int size = getRawData.size();
		String untID = getRawData.get(size - 1).get("UNHid");
		String UNTLine = "UNT+24+" + untID + "'";
		appendLine(path, UNTLine);
		
		// write footer
//		appendLine(path, footer);

		// remove empty lines if any
		removeEmptyLineFromFile(path);
	
		return this;
	}
	
	public MSG005 modifyCarditFileItnlSingleLeg(String path, List<Map<String, String>> getRawData) throws IOException {
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

	/*	String BGMLine[] = strold.split("BGM");
		String BGM = firstLine[0];*/

		/*String lastLine[] = strold.split("UNZ");
		String footer = "UNZ" + lastLine[1]; // add no. of rows here: UNZ+ no of
												// mailbags+ further data
*/
		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush();
		pw.close();

		refID = getRawData.get(1).get("UNHid");
		PropertyHandler.setPropValue(dataFilePath, "refId", refID);
//		appendLine(path, header);

		boolean isFirst = true;
		int z = 0;
		for (Map<String, String> hm : getRawData) {
			int a = getRawData.size();
			z++;
			if (!hm.get("UNHid").equalsIgnoreCase(refID) || isFirst) {
			/*	if (!isFirst) {
					String untID = getRawData.get(z - 2).get("UNHid");
					String UNTLine = "UNT+60+" + untID + "'";
					appendLine(path, UNTLine);
				}
*/
				System.out.println("header first");
				String mailBag = "UNH+"+hm.get("UNHid")+"+CARDIT:1.2:912:UP:CNS200'\n" 
				+"BGM++"+hm.get("ConsignmentID")+"+9'\n"
				+"DTM+137:"+hm.get("dateTime0")+":201'\n"
				+"TSR++++A'\n"
				+"RFF+AHI:AA-CDG-13-0021'\n"
				+"RFF+ERN:FRCDGA'\n"
				+"TDT+Z90'\n"
				+"LOC+84:"+hm.get("origin")+"::3:GATE 23'\n"
				+"LOC+85:FR"+hm.get("origin")+"A:108:139'\n"
				+"DTM+234:"+hm.get("dateTime1")+":201'\n"
				+"DTM+63:"+hm.get("dateTime2")+":201'\n"
				+"TDT+20+"+hm.get("flightNo")+"+4'\n"
				+"LOC+5+"+hm.get("origin")+":163:3'\n"
				+"LOC+7+"+hm.get("dest")+":163:3'\n"
				+"DTM+189:"+hm.get("dateTime3")+":201'\n"
				+"DTM+232:"+hm.get("dateTime4")+":201'\n"
				+"GID++1:IL'\n";
				
						
				appendLine(path, mailBag);
				refID = hm.get("UNHid");
			}
			
			System.out.println("only body");
			
			if (a == z){
						
			String mails = "PCI+++CW:"+hm.get("MaibagID")+"'\n"
							+ "MEA+WT+AAB+KGM:"+hm.get("weight")+"'\n";
			appendLine(path, mails);
			} else{
				String mails = "PCI+++CW:"+hm.get("MaibagID")+"'\n"
						+ "MEA+WT+AAB+KGM:"+hm.get("weight")+"'\n"
						+"GID++1:IL'";
				appendLine(path, mails);
			}
			
//			appendLine(path, mails);
			isFirst = false;
		}

		int size = getRawData.size();
		String untID = getRawData.get(size - 1).get("UNHid");
		String last2Lines = "CNT+C:1+8:2:NMB+7:"+getRawData.get(size-1).get("totalWt")+":KGM'\n"
				+"UNT+24+" + untID + "'";
		appendLine(path, last2Lines);
		PropertyHandler.setPropValue(dataFilePath, "consignmentId", getRawData.get(size-1).get("ConsignmentID"));
		PropertyHandler.setPropValue(dataFilePath, "origin", getRawData.get(size-1).get("origin"));
		PropertyHandler.setPropValue(dataFilePath, "dest", getRawData.get(size-1).get("dest"));
		PropertyHandler.setPropValue(dataFilePath, "flightDt", getRawData.get(size-1).get("flightDt"));
		PropertyHandler.setPropValue(dataFilePath, "flightNo", getRawData.get(size-1).get("flightNo"));
		PropertyHandler.setPropValue(dataFilePath, "uldNo", getRawData.get(size-1).get("container"));
		
		// write footer
//		appendLine(path, footer);

		// remove empty lines if any
		removeEmptyLineFromFile(path);
	
		return this;
	}
	
//	Sharath
	public MSG005 modifyCarditFileDmstSingleLeg(String path, List<Map<String, String>> getRawData) throws IOException {
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush();
		pw.close();

		refID = getRawData.get(1).get("UNHid");
		PropertyHandler.setPropValue(dataFilePath, "refId", refID);

		boolean isFirst = true;
		int z = 0;
		for (Map<String, String> hm : getRawData) {
			int a = getRawData.size();
			z++;
			if (!hm.get("UNHid").equalsIgnoreCase(refID) || isFirst) {
				System.out.println("header first");
				String mailBag = "UNH+"+hm.get("UNHid")+"+CARDIT:91:2:UN:USPS01'\n"
						+"BGM++"+hm.get("ConsignmentID")+"++9'\n"
						+"DTM+137:"+hm.get("dateTime0")+":203'\n"
						+"DTM+76:"+hm.get("dateTime1")+":203'\n"
						+"TDT+20+CF45++1384++AA:101:3'\n"
						+"LOC+5:"+hm.get("origin")+"'\n"
						+"LOC+7:"+hm.get("dest")+"'\n"
						+"DTM+189:"+hm.get("dateTime2")+":203'\n"
						+"DTM+232:"+hm.get("dateTime3")+":203'\n"
						+"GID++1::::LS'\n";
						
				appendLine(path, mailBag);
				refID = hm.get("UNHid");
			}
			System.out.println("only body");
			if (a == z) {
				String mails = "PCI++" + hm.get("mailBagId") + "'\n" + "FTX+ZZZ++245'\n" + "MEA+WT++LBR:" + hm.get("wt")
						+ "'\n";
				appendLine(path, mails);
			} else {
				String mails = "PCI++" + hm.get("mailBagId") + "'\n" + "FTX+ZZZ++245'\n" + "MEA+WT++LBR:" + hm.get("wt")
						+ "'\n" + "GID++1::::LS'\n";
				appendLine(path, mails);
			}
			// appendLine(path, mails);
			isFirst = false;
	    	}
		String last2Lines = "CNT+7:7'\n"
				+"UNT+19+00000000000002'\n";
		appendLine(path, last2Lines);
		int size = getRawData.size();
		PropertyHandler.setPropValue(dataFilePath, "consignmentId", getRawData.get(size-1).get("ConsignmentID"));
		PropertyHandler.setPropValue(dataFilePath, "origin", getRawData.get(size-1).get("origin"));
		PropertyHandler.setPropValue(dataFilePath, "dest", getRawData.get(size-1).get("dest"));
		
	// remove empty lines if any
		removeEmptyLineFromFile(path);
		return this;
	}
	
	
//	Sharath
	public MSG005 modifyCarditFileItnlTransfer(String path, List<Map<String, String>> getRawData) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush();
		pw.close();

		refID = getRawData.get(1).get("UNHid");
		PropertyHandler.setPropValue(dataFilePath, "refId", refID);

		boolean isFirst = true;
		int z = 0;
		for (Map<String, String> hm : getRawData) {
			int a = getRawData.size();
			z++;
			if (!hm.get("UNHid").equalsIgnoreCase(refID) || isFirst) {
				System.out.println("header first");
				String mailBag = "UNH+"+hm.get("UNHid")+"+IFCSUM:D:96A:UN:CNS200'\n"
						+ "BGM++"+hm.get("ConsignmentId")+"+47'\n"
						+ "DTM+137:+"+hm.get("dateTime0")+"+:201'\n"
						+ "FTX+ABK++A'\n"
						+ "RFF+AHI:I7AIR152N8'\n"
						+ "GOR+1'\n"
						+ "FTX+REG+++EXP-CUS:EXP-PT-19930702-EU2454/93/30A:X'\n"
						+ "TCC+7'\nEQN+11:NMB'\n"
						+ "QTY+101:8.1:KGM'\n"
						+ "TDT+Z90'\n"
						+ "LOC+84+"+hm.get("origin")+"::3:"+hm.get("origin")+"'\n"
						+ "DTM+234:"+hm.get("dateTime1")+":201'\n"
						+ "TDT+Z90'\n"
						+ "LOC+1+"+hm.get("dest")+"::3:"+hm.get("dest")+"'\n"
						+ "DTM+63:"+hm.get("dateTime2")+":201'\n"
						+ "TDT+20+"+hm.get("flightNo1")+"+4'\n"
						+ "TSR+PEU'\n"
						+ "LOC+5+"+hm.get("origin")+":163:3'\n"
						+ "LOC+13+"+hm.get("transfer")+":163:3'\n"
						+ "DTM+189:"+hm.get("dateTime3")+":201'\n"
						+ "DTM+232:"+hm.get("dateTime4")+":201'\n"
						+ "TDT+20+"+hm.get("flightNo2")+"+4'\n"
						+ "TSR+PEU'\n"
						+ "LOC+5+"+hm.get("transfer")+":163:3'\n"
						+ "LOC+7+"+hm.get("dest")+":163:3'\n"
						+ "DTM+189:"+hm.get("dateTime5")+":201'\n"
						+ "DTM+232:"+hm.get("dateTime6")+":201'\n";
						
				appendLine(path, mailBag);
				refID = hm.get("UNHid");
			}
			System.out.println("only body");
			/*if (a == z) {
				String mails = "PCI++" + hm.get("mailBagId") + "'\n" + "FTX+ZZZ++245'\n" + "MEA+WT++LBR:" + hm.get("wt")
						+ "'\n";
				appendLine(path, mails);
			} else {*/
				String mails =  "CNI++"+hm.get("mailBagId")+"'\n"
						+ "GID++:PU'\n"
						+ "MEA+WT+AAB+KGM:"+hm.get("wt")+"'\n";
				appendLine(path, mails);
//			}
			// appendLine(path, mails);
			isFirst = false;
	    	}
		int size = getRawData.size();
		String abc = getRawData.get(size-1).get("ConsignmentId");
		
		String lastLine = "UNT+84+"+getRawData.get(size-1).get("UNHid")+"'\n";
		appendLine(path, lastLine);
		PropertyHandler.setPropValue(dataFilePath, "consignmentId", getRawData.get(size-1).get("ConsignmentId"));
		PropertyHandler.setPropValue(dataFilePath, "origin", getRawData.get(size-1).get("origin"));
		PropertyHandler.setPropValue(dataFilePath, "transfer", getRawData.get(size-1).get("transfer"));
		PropertyHandler.setPropValue(dataFilePath, "dest", getRawData.get(size-1).get("dest"));
		
	// remove empty lines if any
		removeEmptyLineFromFile(path);
		return this;
	}
	
	
//	Sharath
	public MSG005 modifyCarditFileDmstTransfer(String path, List<Map<String, String>> getRawData) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		String strold = "";
		while ((str = br.readLine()) != null)
			strold += str;
		br.close();

		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush();
		pw.close();

		refID = getRawData.get(1).get("UNHId");
		PropertyHandler.setPropValue(dataFilePath, "refId", refID);

		boolean isFirst = true;
		int z = 0;
		for (Map<String, String> hm : getRawData) {
			int a = getRawData.size();
			z++;
			if (!hm.get("UNHId").equalsIgnoreCase(refID) || isFirst) {
				System.out.println("header first");
				String mailBag = "UNB+UNOA:2+US001:UP+AAX:ZZ+"+hm.get("dateTime0").substring(0, 6)+":0116+INTREF87962513'\n"
						+ "UNH+"+hm.get("UNHId")+"+CARDIT:91:2:UN:USPS01'\n"
						+ "BGM++"+hm.get("ConsignmentId")+"++9'\n"
						+ "DTM+137:"+hm.get("dateTime0")+":203'\n"
						+ "DTM+76:"+hm.get("dateTime1")+":203'\n"
						+ "TDT+20+F8Q6+"+hm.get("flightNo1").substring(2, 6)+"+AA:101:3'\n"
						+ "LOC+5:"+hm.get("origin")+"'\n"
						+ "LOC+7:"+hm.get("transfer")+"'\n"
						+ "DTM+189:"+hm.get("dateTime2")+":203'\n"
						+ "DTM+232:1303:401'\n"
						+ "TDT+21+F8Q6++"+hm.get("flightNo2").substring(2,6)+"++AA:101:3'\n"
						+ "LOC+5:"+hm.get("transfer")+"'\n"
						+ "LOC+7:"+hm.get("dest")+"'\n"
						+ "DTM+189:"+hm.get("dateTime3")+":203'\n"
						+ "DTM+232:"+hm.get("dateTime4")+":203'\n";
				
				appendLine(path, mailBag);
				refID = hm.get("UNHId");
			}
			System.out.println("only body");
			/*if (a == z) {
				String mails = "PCI++" + hm.get("mailBagId") + "'\n" + "FTX+ZZZ++245'\n" + "MEA+WT++LBR:" + hm.get("wt")
						+ "'\n";
				appendLine(path, mails);
			} else {*/
				String mails =  "GID++1::::LS'\n"
						+ "PCI++"+hm.get("mailBagId")+"'\n"
						+ "FTX+ZZZ++245'\n"
						+ "MEA+WT++LBR:"+hm.get("wt")+"'\n";
						
				appendLine(path, mails);
//			}
			// appendLine(path, mails);
			isFirst = false;
	    	}
		int size = getRawData.size();
		String abc = getRawData.get(size-1).get("ConsignmentId");
		
		String lastLine = "CNT+7:1'\n"
					+ "UNT+19+"+getRawData.get(size-1).get("UNHId")+"'\n"
					+ "UNZ+1+INTREF87962513'\n";
		
					
		appendLine(path, lastLine);
		PropertyHandler.setPropValue(dataFilePath, "consignmentId", getRawData.get(1).get("ConsignmentId"));
		PropertyHandler.setPropValue(dataFilePath, "origin", getRawData.get(1).get("origin"));
		PropertyHandler.setPropValue(dataFilePath, "transfer", getRawData.get(1).get("transfer"));
		PropertyHandler.setPropValue(dataFilePath, "dest", getRawData.get(1).get("dest"));
		PropertyHandler.setPropValue(dataFilePath, "container", getRawData.get(1).get("container"));
		PropertyHandler.setPropValue(dataFilePath, "flightDt1", getRawData.get(1).get("flightDt1"));
		PropertyHandler.setPropValue(dataFilePath, "flightDt2", getRawData.get(1).get("flightDt2"));
		PropertyHandler.setPropValue(dataFilePath, "flightNo1", getRawData.get(1).get("flightNo1"));
		PropertyHandler.setPropValue(dataFilePath, "flightNo2", getRawData.get(1).get("flightNo2"));
		
	// remove empty lines if any
		removeEmptyLineFromFile(path);
		return this;
	}
	
	
	/**
	 * Description : Method will process message
	 * 
	 * @param Msgform
	 * @param FlightNumber
	 */
	public MSG005 processCarditMessage(String Msgform, String consignmentId) {
		consignmentId = PropertyHandler.getPropValue(dataFilePath, consignmentId);
		test.log(LogStatus.INFO, "Started processing the message : " + Msgform);
		logger.info("Processed  message");
		enterKeys(txt_messgeType, Msgform);
		enterKeys(txt_calFromDate, "-1" + Keys.TAB);
		enterKeys(txt_calToDate, "." + Keys.TAB);
		// maxWait();
		click(btn_reference);
		minWait();
		enterKeys(By.xpath("//td[contains(text(),'1004')]/following-sibling::td/input"), consignmentId);
		minWait();
		selectByText(list_messageStatus, "Decoded Successfully");
		maxWait();
		driver.findElement(btn_list).click();
		// enterKeys(btn_list,Keys.F12);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			minWait();
			String actualValue = driver.findElement(By.xpath("//div[@id='ic-sd-msgc']")).getText();
			captureAndAddScreenshot();
			if (actualValue.equalsIgnoreCase("Message Processed Successfully.") || actualValue.equalsIgnoreCase("Message processed with warnings.")) {
				test.log(LogStatus.PASS, "Message Processed Successfully with status : " + actualValue);
			} else {
				test.log(LogStatus.FAIL, "Message Not Processed with status" + actualValue);
				Assert.fail("Message Not Processed");
			}
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			logger.info("message processed");

		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, "Timeout Exception/Nosuch Element Exception" + Msgform);
			Assert.fail("Message Not Processed");
		}
		logger.info(" message processed" + Msgform);
		return this;

	}

	public MSG005 processCarditDOMMessage(String Msgform, String consignmentId) {
		consignmentId = PropertyHandler.getPropValue(dataFilePath, consignmentId);
		test.log(LogStatus.INFO, "Started processing the message : " + Msgform);
		logger.info("Processed  message");
		enterKeys(txt_messgeType, Msgform);
		enterKeys(txt_calFromDate, "-1" + Keys.TAB);
		enterKeys(txt_calToDate, "." + Keys.TAB);
		// maxWait();
		click(btn_reference);
		minWait();
		enterKeys(By.xpath("//input[@name='referenceValue']"), consignmentId);
		minWait();
		selectByText(list_messageStatus, "Decoded Successfully");
		maxWait();
		driver.findElement(btn_list).click();
		// enterKeys(btn_list,Keys.F12);
		try {
			check(chk_selectMessage);
			click(btn_process);
			driver.switchTo().defaultContent();
			minWait();
			String actualValue = driver.findElement(By.xpath("//div[@id='ic-sd-msgc']")).getText();
			captureAndAddScreenshot();
			if (actualValue.equalsIgnoreCase("Message Processed Successfully.") || actualValue.equalsIgnoreCase("Message processed with warnings.")) {
				test.log(LogStatus.PASS, "Message Processed Successfully with status : " + actualValue);
			} else {
				test.log(LogStatus.FAIL, "Message Not Processed with status" + actualValue);
				Assert.fail("Message Not Processed");
			}
			click(dialogue_btn_ok);
			waitForFrameAndSwitch(contentFrame);
			logger.info("message processed");

		} catch (NoSuchElementException | TimeoutException e) {
			logger.warn("Exception occured " + e);
			test.log(LogStatus.FAIL, "Timeout Exception/Nosuch Element Exception" + Msgform);
			Assert.fail("Message Not Processed");
		}
		logger.info(" message processed" + Msgform);
		return this;
	}

	/**
     * Method to list an XSDG Message and check the status
     * 
      * @param awbPre
     * @param awbNo
     * @param subType
     * @param status
     * @return
     * @author A-8457 Souvik <27/03/2020>
     */
	public MSG005 CheckXsDGMessageStatus(String awbNo, String status) {

//		status = PropertyHandler.getPropValue(dataFilePath, status);
//		awbNo = PropertyHandler.getPropValue(dataFilePath, awbNo);

		minWait();
		enterKeys(txt_messgeType, "XSDG");
		enterKeys(txt_calFromDate, "-1");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		click(btn_reference);
//		minWait();
		enterKeys(By.xpath("//td[@class='iCargoTableDataTd']/input[@value='MSTDOCNUM']/../following-sibling::td/input"),
				awbNo);
		click(btn_list);
		minWait();
		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2, "001 - " + awbNo);
		String msgStatus1=getText_JS(By.xpath("(//table[@id='listmessaging']//tr)[last()]/td[9]")).trim();
		if (msgStatus.contains(status)) {
			test.log(LogStatus.PASS, "XsDG Message Processed Successfully");
		} else {
			test.log(LogStatus.FAIL, "XsDG Message was not Processed Successfully");
			Assert.fail();
		}
		return this;
	}

	/**
	 * Method to list an XSDG Message and check the status
	 * 
	 * @param awbPre
	 * @param awbNo
	 * @param subType
	 * @param status
	 * @return
	 * @author A-8457 Souvik <27/03/2020>
	 */
	public MSG005 CheckNTMMessageStatus(String FlightNo, String status) {

//		status = PropertyHandler.getPropValue(dataFilePath, status);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		
		minWait();
		enterKeys(txt_messgeType, "NTM");
		enterKeys(txt_calFromDate, "-1");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		click(btn_reference);
		minWait();
		enterKeys(By.xpath("//td[@class='iCargoTableDataTd']/input[@value='FLTNUM']/../following-sibling::td/input"), FlightNo);
		click(btn_list);
		minWait();
		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2, "AA - " + FlightNo);
		System.out.println(msgStatus);
		if(msgStatus.equalsIgnoreCase(status))
			{
				test.log(LogStatus.PASS, "NTM Message Processed Successfully");
				}
		else{	
				test.log(LogStatus.FAIL, "NTM Message was not Processed Successfully");
				Assert.fail();
				}
		return this;
	}
	
//	Sharath
	public MSG005 CheckNTMMessageStatusSmoke(String FlightNo, String status) {

		status = PropertyHandler.getPropValue(dataFilePath, status);
//		FlightNo = PropertyHandler.getPropValue(dataFilePath, FlightNo);
		
		minWait();
		enterKeys(txt_messgeType, "NTM");
		enterKeys(txt_calFromDate, "-1");
		waitForElement(txt_messgeType).sendKeys(Keys.TAB);
		click(btn_reference);
		minWait();
		enterKeys(By.xpath("//td[@class='iCargoTableDataTd']/input[@value='FLTNUM']/../following-sibling::td/input"), FlightNo);
		click(btn_list);
		minWait();
		String msgStatus = tblGetTextByColValue(tbl_message, 9, 2, "AA - " + FlightNo);
		System.out.println(msgStatus);
		captureAndAddScreenshot();
		if(msgStatus.equalsIgnoreCase(status))
			{
				test.log(LogStatus.PASS, "NTM Message Processed Successfully");
				}
		else{	
				test.log(LogStatus.FAIL, "NTM Message was not Processed Successfully");
				Assert.fail();
				}
		return this;
	}
	
}
