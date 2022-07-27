package com.ibsplc.generic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.ibsplc.common.BasePage;
import com.ibsplc.utils.PropertyHandler;


public class Message {
	private final static Logger logger = Logger.getLogger(Message.class);

	private static BufferedReader getReader(String fileName) {
		logger.info("Reading sample message file");
		logger.info("Sample message path is '" + fileName + "'");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (FileNotFoundException e) {
			logger.error("Error occured while reading sample message file" + e);
		}
		logger.info("Sample message reading completed");
		return reader;
	}

	private static void writeToFile(BufferedReader reader, String text, String newFilePath) {
		logger.info("Creating message file");
		logger.info("Message file path is '" + newFilePath + "'");
		BufferedWriter writer = null;
		try {
			reader.close();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath)));
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			logger.error("Error occured while creating message file" + e);
		}
		logger.info("Message creation completed");

	}

	/**
	 * Description : This method will create a FFA message and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param fullAWBNo
	 * @param awbNo
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param flightStartDate
	 * @param OALFullFlightNo
	 * @param OALCarrierCode
	 * @return
	 */
	public static String createFFAMessage(String messageSampleFilePath, String fullAWBNo, String awbNo, String origin, String destination, String pieces,
			String weight, String flightStartDate, String OALFullFlightNo, String OALCarrierCode) {
		logger.info("Creating FFA message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFA";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;


		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = fullAWBNo + origin + destination + "/T" + pieces + "K" + weight + "/LIONRATE";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {

					String date = flightStartDate;
					int indexOfDateSeperator = date.lastIndexOf("-");
					date = date.substring(0, indexOfDateSeperator);
					date = date.replaceAll("-", "");
					String newd1 = date.toUpperCase();

					line = OALFullFlightNo + "/" + newd1 + "/" + origin + destination + "/KK";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "REF" + "/" + origin + "FC" + OALCarrierCode;
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (Exception e) {
			logger.error("Error while creating FFA message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		writeToFile(reader, oldtext, createdMessagePath);
		logger.info("FFA message will be created in '" + createdMessagePath + "'");
		return createdMessagePath;
	}

	/**
	 * Description : This method will create a FFR message and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param AWBNoPrefix
	 * @param AWBNo
	 * @param Origin
	 * @param Destination
	 * @param Pieces
	 * @param Weight
	 * @param FlightStartDate
	 * @param FullFlightNo
	 * @param FFR_Code
	 * @return
	 */
	public static String createFFRMessage(String messageSampleFilePath, String AWBNoPrefix, String AWBNo, String Origin, String Destination,
			String Pieces, String Weight, String FlightStartDate, String FullFlightNo, String FFR_Code) {

		logger.info("Creating FFR message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFR";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = AWBNoPrefix + "-" + AWBNo + Origin + Destination + "/T" + Pieces + "K" + Weight + "MC0/GEN";
					oldtext += line + System.getProperty("line.separator");

				} else if (counter == 4) {

					String date = FlightStartDate;
					int indexOfDateSeperator = date.lastIndexOf("-");
					date = date.substring(0, indexOfDateSeperator);
					date = date.replaceAll("-", "");
					String newd1 = date.toUpperCase();
					line = FullFlightNo + "/" + newd1 + "/" + Origin + Destination + "/" + FFR_Code;
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
				}
				counter++;
				line = "";
			}
		} catch (Exception e) {
			logger.error("Error while creating FFR message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FFR message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Description : This method will create a FHL message and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param fullAWBNo
	 * @param flightOrigin
	 * @param flightDestination
	 * @param pieces
	 * @param weight
	 * @param hawb1_pieces
	 * @param hawb1_weight
	 * @param hawb2_pieces
	 * @param hawb2_weight
	 * @return
	 */
	public static String createFHLMessage(String messageSampleFilePath, String fullAWBNo, String flightOrigin, String flightDestination,
			String pieces, String weight, String hawb1_pieces, String hawb1_weight, String hawb2_pieces, String hawb2_weight) {
		logger.info("Creating FHL message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FHL";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;


		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					if (line.startsWith("MBI")) {
						line = "MBI/" + fullAWBNo + flightOrigin + flightDestination + "/T" + pieces + "K" + weight;
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 3) {
					if (line.startsWith("HBS")) {
						line = "HBS/H1/" + flightOrigin + flightDestination + "/" + hawb1_pieces + "/K" + hawb1_weight + "//HOUSE 1";
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 4) {
					if (line.startsWith("HBS")) {
						line = "HBS/H2/" + flightOrigin + flightDestination + "/" + hawb2_pieces + "/K" + hawb2_weight + "//HOUSE 2";
						oldtext += line + System.getProperty("line.separator");
					}
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FHL message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FHL message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	public static String createCSNMessage(String messageSampleFilePath, String awbPre, String awbNo, String fullFltNo, String origin, String dest, String date, String pcs) {
		logger.info("Creating CSN message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_CSN";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;


		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					if (line.startsWith("WBI")) {
						line = "WBI/" + awbPre + "-" + awbNo + "/M";
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 3) {
					if (line.startsWith("FLT")) {
						line = "FLT/" + fullFltNo + "/" + origin + dest + "/" + date.replace("-", "").substring(0, 5);
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 5) {
					if (line.startsWith("DTN")) {
						line = "DTN/" + date.replace("-", "").substring(0, 5) + "/0200";
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 6) {
					if (line.startsWith("CND")) {
						line = "CND/HKG123456789/" + pcs;
						oldtext += line + System.getProperty("line.separator");
					}
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FHL message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FHL message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Description : This method will create a FWB message and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param rate
	 * @param flightStartDate
	 * @return
	 */
	public static String createFWBMessage(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode, String origin, String destination, String pieces, String weight
			, String shipperName, String consigneeName, String agentCode, String FullFlightNo, String exportIATAAgentCode, String rate, String flightStartDate, String paymentType, boolean OCIFlag, String... secureScc) {

		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		try {
			if (OCIFlag) {
				while ((line = reader.readLine()) != null) {
					if (counter == 2) {
						line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight + "MC0.03";
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 3) {
						if (line.startsWith("FLT")) {
							String tempdate = flightStartDate.substring(0, 2);
							line = "FLT/" + FullFlightNo + "/" + tempdate;
							oldtext += line + System.getProperty("line.separator");
						}
					} else if (counter == 4) {
						if (line.startsWith("RTG")) {
							line = "RTG/" + destination + carrierCode;
							oldtext += line + System.getProperty("line.separator");
						}
					} else if (counter == 6) {
						line = "/" + shipperName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 11) {
						line = "/" + consigneeName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 15) {
						if (exportIATAAgentCode.length() > 7) {
							String agentIATACode = exportIATAAgentCode.substring(0, 7);
							String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
							line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
						} else {
							line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
						}
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 31) {
						String Startdate = flightStartDate;
						Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
						line = "ISU/" + Startdate + "/" + origin + "/MERCK KGAA";
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 32) {


						line = "REF///AGT/KUEHNENAGEL/" + origin;
						oldtext += line + System.getProperty("line.separator");
					} else {
						oldtext += line + System.getProperty("line.separator");
						;
					}
					counter++; System.out.println("counter = "+counter+"--------------");
					line = "";
				}
			} else {
				while ((line = reader.readLine()) != null) {
					if (counter == 2) {
						line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 3) {
						if (line.startsWith("RTG")) {
							line = "RTG/" + destination + carrierCode;
							oldtext += line + System.getProperty("line.separator");
						}

					} else if (counter == 4) {
						line = "SHP/" + shipperName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 9) {
						line = "CNE/" + consigneeName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 14) {
						if (exportIATAAgentCode.length() > 7) {
							String agentIATACode = exportIATAAgentCode.substring(0, 7);
							String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
							line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
						} else {
							line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
						}
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 17) {
						if (line.startsWith("CVD/")) {
							if (paymentType.equals("NC")) {

								line = "CVD/INR/NC/PP/NVD/NCV/XXX";
								oldtext += line + System.getProperty("line.separator");
							} else if (paymentType.equals("blank")) {
								line = "CVD/INR//PP/NVD/NCV/XXX";
								oldtext += line + System.getProperty("line.separator");
							} else {

								line = "CVD/INR/" + paymentType + "/PP/NVD/NCV/XXX";
								oldtext += line + System.getProperty("line.separator");
							}
						}
					} else if (counter == 18) {
						if (line.startsWith("RTD")) {
							if (rate != null && rate.trim().length() > 0) {
								line = "RTD/1/P" + pieces + "/K" + weight + "/W" + weight + "/R" + rate + "/T100000";
								oldtext += line + System.getProperty("line.separator");
							} else {
								line = "RTD/1/P" + pieces + "/K" + weight + "/W" + weight;
								oldtext += line + System.getProperty("line.separator");
							}
						}
					} else if (counter == 21) {
						String Startdate = flightStartDate;
						Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
						line = "ISU/" + Startdate + "/" + destination;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 24 && secureScc.length != 0) {

						line = "SPH/" + secureScc[0];
						oldtext += line + System.getProperty("line.separator");
					} else {
						oldtext += line + System.getProperty("line.separator");
						;
					}
					counter++;System.out.println("counter = "+counter+"--------------");
					line = "";
				}
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Description : This method will create a FWB message and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param rate
	 * @param flightStartDate
	 * @return
	 */
	public static String createFWBMessageWithDiffChargWt(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode, String origin, String destination, String pieces, String weight, String chrgWt
			, String shipperName, String consigneeName, String agentCode, String FullFlightNo, String exportIATAAgentCode, String rate, String flightStartDate, String paymentType, boolean OCIFlag, String... secureScc) {

		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					if (line.startsWith("RTG")) {
						line = "RTG/" + destination + carrierCode;
						oldtext += line + System.getProperty("line.separator");
					}

				} else if (counter == 4) {
					line = "SHP/" + shipperName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "CNE/" + consigneeName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 14) {
					if (exportIATAAgentCode.length() > 7) {
						String agentIATACode = exportIATAAgentCode.substring(0, 7);
						String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
						line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
					} else {
						line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
					}
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 17) {
					if (line.startsWith("CVD/")) {
						if (paymentType.equals("NC")) {

							line = "CVD/INR/NC/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else if (paymentType.equals("blank")) {
							line = "CVD/INR//PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else {

							line = "CVD/INR/" + paymentType + "/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						}
					}
				} else if (counter == 18) {
					if (line.startsWith("RTD")) {
						if (rate != null && rate.trim().length() > 0) {
							line = "RTD/1/P" + pieces + "/K" + weight + "/W" + chrgWt + "/R" + rate + "/T100000";
							oldtext += line + System.getProperty("line.separator");
						} else {
							line = "RTD/1/P" + pieces + "/K" + weight + "/W" + chrgWt;
							oldtext += line + System.getProperty("line.separator");
						}
					}
				} else if (counter == 21) {
					String Startdate = flightStartDate;
					Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
					line = "ISU/" + Startdate + "/" + destination;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 24 && secureScc.length != 0) {

					line = "SPH/" + secureScc[0];
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Description : This method will create a FWB message and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param rate
	 * @return
	 */
	public static String createFWBMessageWithoutFlight(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode, String origin, String destination, String pieces, String weight
			, String shipperName, String consigneeName, String agentCode, String exportIATAAgentCode, String rate, String paymentType, String... secureScc) {

		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		String flightStartDate = sdf.format(date).toUpperCase();

		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					if (line.startsWith("RTG")) {
						line = "RTG/" + destination + carrierCode;
						oldtext += line + System.getProperty("line.separator");
					}

				} else if (counter == 4) {
					line = "SHP/" + shipperName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "CNE/" + consigneeName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 14) {
					if (exportIATAAgentCode.length() > 7) {
						String agentIATACode = exportIATAAgentCode.substring(0, 7);
						String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
						line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
					} else {
						line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
					}
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 17) {
					if (line.startsWith("CVD/")) {
						if (paymentType.equals("NC")) {

							line = "CVD/INR/NC/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else if (paymentType.equals("blank")) {
							line = "CVD/INR//PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else {

							line = "CVD/INR/" + paymentType + "/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						}
					}
				} else if (counter == 21) {
					String Startdate = flightStartDate;
					Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
					line = "ISU/" + Startdate + "/" + destination;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 24 && secureScc.length != 0) {

					line = "SPH/" + secureScc[0];
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}


	/**
	 * Description : This method will create a FWB message with specified date diff
	 *
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param rate
	 * @return
	 */
	public static String createFWBMessageWithDiffDate(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode, String origin, String destination, String pieces, String weight
			, String shipperName, String consigneeName, String agentCode, String exportIATAAgentCode, String rate, String paymentType, int dateDiff, String... secureScc) {

		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDateTime dt = LocalDateTime.now().minusDays(dateDiff);
		String formatedDt = dt.format(dateFormat);

		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					if (line.startsWith("RTG")) {
						line = "RTG/" + destination + carrierCode;
						oldtext += line + System.getProperty("line.separator");
					}

				} else if (counter == 4) {
					line = "SHP/" + shipperName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "CNE/" + consigneeName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 14) {
					if (exportIATAAgentCode.length() > 7) {
						String agentIATACode = exportIATAAgentCode.substring(0, 7);
						String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
						line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
					} else {
						line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
					}
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 17) {
					if (line.startsWith("CVD/")) {
						if (paymentType.equals("NC")) {

							line = "CVD/INR/NC/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else if (paymentType.equals("blank")) {
							line = "CVD/INR//PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else {

							line = "CVD/INR/" + paymentType + "/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						}
					}
				} else if (counter == 21) {
					String issueDt = formatedDt;
					issueDt = (issueDt.substring(0, 6) + issueDt.substring(9, 11)).replaceAll("-", "");
					line = "ISU/" + issueDt + "/" + destination;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 24 && secureScc.length != 0) {

					line = "SPH/" + secureScc[0];
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Description : This method will create a FWB message for multiflight booking
	 * and return the created file path
	 *
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param rate
	 * @param flightStartDate
	 * @return
	 * @author A-7868 Krishna <23/02/2018>
	 */
	public static String createFWBMessage_2Flights(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode,
			String origin1, String dest1, String origin2, String dest2, String pieces, String weight,
			String shipperName, String consigneeName, String agentCode, String FullFlightNo, String FullFlightNo2,
			String exportIATAAgentCode, String rate, String flightStartDate, String paymentType, boolean OCIFlag) {

		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB_2F";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		try {
			if (OCIFlag) { //issue exists. have to debug
				while ((line = reader.readLine()) != null) {
					if (counter == 2) {
						line = awbNoPrefix + "-" + awbNo + origin1 + dest2 + "/T" + pieces + "K" + weight + "MC0.03";
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 3) {
						if (line.startsWith("FLT")) {
							String tempdate = flightStartDate.substring(0, 2);
							line = "FLT/" + FullFlightNo + "/" + tempdate;
							oldtext += line + System.getProperty("line.separator");
						}
					} else if (counter == 4) {
						if (line.startsWith("FLT")) {
							String tempdate = flightStartDate.substring(0, 2);
							line = "FLT/" + FullFlightNo2 + "/" + tempdate;
							oldtext += line + System.getProperty("line.separator");
						}
					} else if (counter == 5) {
						if (line.startsWith("RTG")) {
							line = "RTG/" + dest1 + carrierCode + "/" + dest2 + carrierCode;
							oldtext += line + System.getProperty("line.separator");
						}
					} else if (counter == 7) {
						line = "/" + shipperName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 12) {
						line = "/" + consigneeName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 16) {
						if (exportIATAAgentCode.length() > 7) {
							String agentIATACode = exportIATAAgentCode.substring(0, 7);
							String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
							line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
						} else {
							line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "/CAG";
						}
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 32) {
						String Startdate = flightStartDate;
						Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
						line = "ISU/" + Startdate + "/" + origin1 + "/MERCK KGAA";
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 33) {


						line = "REF///AGT/KUEHNENAGEL/" + origin1;
						oldtext += line + System.getProperty("line.separator");
					} else {
						oldtext += line + System.getProperty("line.separator");
						;
					}
					counter++;
					line = "";
				}
			} else {
				while ((line = reader.readLine()) != null) {
					if (counter == 2) {
						line = awbNoPrefix + "-" + awbNo + origin1 + dest2 + "/T" + pieces + "K" + weight;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 3) {
						if (line.startsWith("RTG")) {
							line = "RTG/" + dest1 + carrierCode + "/" + dest2 + carrierCode;
							oldtext += line + System.getProperty("line.separator");
						}

					} else if (counter == 4) {
						line = "SHP/" + shipperName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 9) {
						line = "CNE/" + consigneeName;
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 14) {
						if (exportIATAAgentCode.length() > 7) {
							String agentIATACode = exportIATAAgentCode.substring(0, 7);
							String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
							line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
						} else {
							line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "/CAG";
						}
						oldtext += line + System.getProperty("line.separator");
					} else if (counter == 17) {
						if (line.startsWith("CVD/")) {
							if (paymentType.equals("NC")) {

								line = "CVD/INR/NC/PP/NVD/NCV/XXX";
								oldtext += line + System.getProperty("line.separator");
							} else if (paymentType.equals("blank")) {
								line = "CVD/INR//PP/NVD/NCV/XXX";
								oldtext += line + System.getProperty("line.separator");
							} else {

								line = "CVD/INR/" + paymentType + "/PP/NVD/NCV/XXX";
								oldtext += line + System.getProperty("line.separator");
							}
						}
					} else if (counter == 18) {
						if (line.startsWith("RTD")) {
							if (rate != null && rate.trim().length() > 0) {
								line = "RTD/1/P" + pieces + "/K" + weight + "/W" + weight + "/R" + rate + "/T100000";
								oldtext += line + System.getProperty("line.separator");
							} else {
								line = "RTD/1/P" + pieces + "/K" + weight + "/W" + weight;
								oldtext += line + System.getProperty("line.separator");
							}
						}
					} else if (counter == 21) {
						String Startdate = flightStartDate;
						Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
						line = "ISU/" + Startdate + "/" + dest2;
						oldtext += line + System.getProperty("line.separator");
					} else {
						oldtext += line + System.getProperty("line.separator");
						;
					}
					counter++;
					line = "";
				}
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	public static String createFDDMessage(String messageSampleFilePath, String awbNoPrefix, String awbNo, String origin, String destination, String flightStartDate) {
		logger.info("Creating FDD message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FDD";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					if (line.startsWith("DHD")) {
						line = "DHD/1/" + awbNoPrefix + "-" + awbNo + "/" + origin + "/" + destination + "/PAC/RAD";
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 21) {

					if (line.startsWith("DSU")) {
						logger.info(flightStartDate);
						String Startdate = flightStartDate;
						Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
						line = "DSU/" + Startdate + "/" + origin;
						oldtext += line + System.getProperty("line.separator");
					}
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FDD message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("GDD message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	public static String createFFMMessage(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1, String uldNo, String fullAWBNo2, String pieces2, String weight2, String volume2,
			String fullAWBNo3, String pieces3, String weight3, String volume3) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "ULD/" + uldNo + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = fullAWBNo2 + origin + destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = fullAWBNo3 + origin + destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 8) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Created by A-7605
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param uldNo1
	 * @param fullAWBNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo2
	 * @param fullAWBNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @return
	 */
	public static String createFFMMessage_1B_2ULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1, String uldNo1, String fullAWBNo2, String pieces2, String weight2, String volume2,
			String uldNo2, String fullAWBNo3, String pieces3, String weight3, String volume3) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "ULD/" + uldNo1 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = fullAWBNo2 + origin + destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = "ULD/" + uldNo2 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 8) {
					line = fullAWBNo3 + origin + destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Created by A-7605
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param flightOrigin
	 * @param flightDestination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param uldNo1
	 * @param fullAWBNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo2
	 * @param fullAWBNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @return
	 */
	public static String createFFMMessage_1B_5ULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String flightOrigin, String flightDestination, String fullAWBNo1,
			String awb1Origin, String awb1Destination, String pieces1, String weight1, String volume1, String uldNo1, String fullAWBNo2, String awb2Origin, String awb2Destination, String pieces2, String weight2, String volume2,
			String uldNo2, String fullAWBNo3, String awb3Origin, String awb3Destination, String pieces3, String weight3, String volume3, String uldNo3, String fullAWBNo4, String awb4Origin, String awb4Destination, String pieces4, String weight4, String volume4
			, String uldNo4, String fullAWBNo5, String awb5Origin, String awb5Destination, String pieces5, String weight5, String volume5, String uldNo5, String fullAWBNo6, String awb6Origin, String awb6Destination, String pieces6, String weight6, String volume6) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + flightOrigin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = flightDestination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + awb1Origin + awb1Destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "ULD/" + uldNo1 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = fullAWBNo2 + awb2Origin + awb2Destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = "ULD/" + uldNo2 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 8) {
					line = fullAWBNo3 + awb3Origin + awb3Destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "ULD/" + uldNo3 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 10) {
					line = fullAWBNo4 + awb4Origin + awb4Destination + "/T" + pieces4 + "K" + weight4 + "MC" + volume4 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 11) {
					line = "ULD/" + uldNo4 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 12) {
					line = fullAWBNo5 + awb5Origin + awb5Destination + "/T" + pieces5 + "K" + weight5 + "MC" + volume5 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 13) {
					line = "ULD/" + uldNo5 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 14) {
					line = fullAWBNo6 + awb6Origin + awb6Destination + "/T" + pieces6 + "K" + weight6 + "MC" + volume6 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 15) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * overloaded for one loose acceptance awb
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @return
	 */
	public static String createFFMMessage(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Created by A-7605
	 * This method will finalize flight that will contain two bulk shipments and one ULD that will contain two AWBs
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param fullAWBNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo
	 * @param fullAWBNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param fullAWBNo4
	 * @param pieces4
	 * @param weight4
	 * @param volume4
	 * @return
	 */
	public static String createFFMMessage_2B_2ShipmentsInOneULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1, String fullAWBNo2, String pieces2, String weight2, String volume2,
			String uldNo, String fullAWBNo3, String pieces3, String weight3, String volume3, String fullAWBNo4, String pieces4, String weight4, String volume4) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = fullAWBNo2 + origin + destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = "ULD/" + uldNo + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = fullAWBNo3 + origin + destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 8) {
					line = fullAWBNo4 + origin + destination + "/T" + pieces4 + "K" + weight4 + "MC" + volume4 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Created by A-7605
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param flightOrigin
	 * @param flightDestination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param uldNo1
	 * @param fullAWBNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo2
	 * @param fullAWBNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @return
	 */
	public static String createFFMMessage_1B_1ULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String flightOrigin, String flightDestination, String fullAWBNo1,
			String awb1Origin, String awb1Destination, String pieces1, String weight1, String volume1, String uldNo1, String fullAWBNo2
			) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + flightOrigin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = flightDestination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + awb1Origin + awb1Destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "ULD/" + uldNo1 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = fullAWBNo2 + awb1Origin + awb1Destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if(counter == 7){
					line = "/MDC/GEN/SPX";
					oldtext += line + System.getProperty("line.separator");
				}else if(counter == 8){
					line = "COR/T1";
					oldtext += line + System.getProperty("line.separator");
				}else if (counter == 9) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Created by A-7605 on 5-2-18
	 * This message will create FFM message that contains 1 Bulk shipment and 4 other shipments in single ULD
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param fullAWBNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo
	 * @param fullAWBNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param fullAWBNo4
	 * @param pieces4
	 * @param weight4
	 * @param volume4
	 * @param fullAWBNo5
	 * @param pieces5
	 * @param weight5
	 * @param volume5
	 * @return
	 */
	public static String createFFMMessage_1B_3ShipmentsInOneULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1, String fullAWBNo2, String pieces2, String weight2, String volume2,
			String uldNo1, String uldNo2, String uldNo3, String fullAWBNo3, String pieces3, String weight3, String volume3, String fullAWBNo4, String pieces4, String weight4, String volume4
			) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {

				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "ULD/" + uldNo1 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = fullAWBNo2 + origin + destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = "ULD/" + uldNo2 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 8) {
					line = fullAWBNo3 + origin + destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "ULD/" + uldNo3 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 10) {
					line = fullAWBNo4 + origin + destination + "/T" + pieces4 + "K" + weight4 + "MC" + volume4 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 10) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Created by A-7605 on 5-2-18
	 * This message will create FFM message that contains 1 Bulk shipment and 4 other shipments in single ULD
	 *
	 * @param messageSampleFilePath
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param origin
	 * @param destination
	 * @param fullAWBNo1
	 * @param pieces1
	 * @param weight1
	 * @param volume1
	 * @param fullAWBNo2
	 * @param pieces2
	 * @param weight2
	 * @param volume2
	 * @param uldNo
	 * @param fullAWBNo3
	 * @param pieces3
	 * @param weight3
	 * @param volume3
	 * @param fullAWBNo4
	 * @param pieces4
	 * @param weight4
	 * @param volume4
	 * @param fullAWBNo5
	 * @param pieces5
	 * @param weight5
	 * @param volume5
	 * @return
	 */
	public static String createFFMMessage_1B_4ShipmentsInOneULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1, String fullAWBNo2, String pieces2, String weight2, String volume2,
			String uldNo, String fullAWBNo3, String pieces3, String weight3, String volume3, String fullAWBNo4, String pieces4, String weight4, String volume4
			, String fullAWBNo5, String pieces5, String weight5, String volume5) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/GENERAL CARGO";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = "ULD/" + uldNo + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6) {
					line = fullAWBNo2 + origin + destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = fullAWBNo3 + origin + destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 8) {
					line = fullAWBNo4 + origin + destination + "/T" + pieces4 + "K" + weight4 + "MC" + volume4 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = fullAWBNo5 + origin + destination + "/T" + pieces5 + "K" + weight5 + "MC" + volume5 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 10) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FFM message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create FSU_RCS message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param stdPcs
	 * @param stdWt
	 * @param accPcs                TODO
	 * @param accWt                 TODO
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public static String createFSU_RCSMessage(String messageSampleFilePath, String FullAWBNo, String flightStartDate, String origin, String dest, String stdPcs, String stdWt, String accPcs, String accWt, String...uldList) {

		logger.info("Creating FSU_RCS message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_RCS";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);        

		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + stdPcs + "K" + stdWt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "RCS/" + flightDateWithoutYear + "0702/" + origin + "/P" + accPcs + "K" + accWt; //change back to T
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4 && uldList.length > 0) {
					line = "ULD" + uldList[0];
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4 ) {
					line = "";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++; System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_RCS message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_RCS message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}



	/**
	 * Method to create FSU DLV message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public static String createFSU_DLVMessage(String messageSampleFilePath, String FullAWBNo, String flightStartDate, String origin, String dest, String pcs, String wt) {

		logger.info("Creating FSU_DLV message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_DLV";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "DLV/" + flightDateWithoutYear + "0702/" + dest + "/T" + pcs + "K" + wt + "/TEST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_DLV message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_DLV message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create a FSU_MAN message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param depTime
	 * @param arrTime
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public static String createFSU_MANMessage(String messageSampleFilePath, String FullAWBNo, String fullFlightNo, String flightStartDate, String depStn, String arrStn, String depTime, String arrTime, String origin, String dest, String pcs, String wt) {

		logger.info("Creating FSU_MAN message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_MAN";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "MAN/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + depStn + arrStn + "/T" + pcs + "K" + wt + "/S" + depTime + "00/S" + arrTime + "00";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_MAN message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_MAN message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create FSU RCF message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param arrStn
	 * @param arrTime
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @return
	 * @author A-7868 Krishna <02/03/2018>
	 */
	public static String createFSU_RCFMessage(String messageSampleFilePath, String FullAWBNo, String fullFlightNo, String flightStartDate, String arrStn, String arrTime, String origin, String dest, String pcs, String wt, String...uldList) {

		logger.info("Creating FSU_RCF message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_RCF";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "RCF/" + fullFlightNo + "/" + flightDateWithoutYear + "0358/" + arrStn + "/P" + pcs + "K" + wt + "//A0900";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4 && uldList.length > 0) {
					line = "ULD" + uldList[0];
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4 ) {
					line = "";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_RCF message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_RCF message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create a FSU-RCT message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param arrStn
	 * @param arrTime
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @param carrCode
	 * @return
	 * @author a-7868 Krishna
	 */
	public static String createFSU_RCTMessage(String messageSampleFilePath, String FullAWBNo, String flightStartDate, String arrStn, String arrTime, String origin, String dest, String pcs, String wt, String carrCode, String...uldList) {

		logger.info("Creating FSU_RCF message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_RCT";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "RCT/" + carrCode + "/" + flightDateWithoutYear + arrTime + "00/" + arrStn + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4 && uldList.length > 0) {
					line = "ULD" + uldList[0];
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4 ) {
					line = "";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_RCT message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_RCT message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create a FSU-TFD message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param currStn
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @param carrCode
	 * @return
	 * @author A-7868 Krishna
	 */
	public static String createFSU_TFDMessage(String messageSampleFilePath, String FullAWBNo, String flightStartDate, String currStn, String origin, String dest, String pcs, String wt, String carrCode) {

		logger.info("Creating FSU_TFD message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_TFD";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "TFD/" + carrCode + "/" + flightDateWithoutYear + "0100/" + currStn + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_TFD message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_TFD message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Mehtod to create a FSU-DIS message
	 *
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param flightStartDate
	 * @param fullFltNum
	 * @param currStn
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @param pcs1
	 * @param wt1
	 * @return
	 * @author A-7868 Krishna <20/03/2018>
	 */
	public static String createFSU_DISMessage(String messageSampleFilePath, String FullAWBNo, String flightStartDate, String fullFltNum, String currStn, String origin, String dest, String pcs, String wt, String pcs1, String wt1) {

		logger.info("Creating FSU_DIS message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_DIS";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "DIS/" + fullFltNum + "/" + flightDateWithoutYear + "0100/" + currStn + "/MSCA/P" + pcs1 + "K" + wt1;
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_TFD message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_TFD message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	public static String createFSU_DIS_FDCAMessage(String messageSampleFilePath, String FullAWBNo, String flightStartDate, String fullFltNum, String currStn, String origin, String dest, String pcs, String wt, String pcs1) {

		logger.info("Creating FSU_DIS message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_DIS_FDCA";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "DIS/" + fullFltNum + "/" + flightDateWithoutYear + "0100/" + currStn + "/FDCA/P" + pcs1;
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_TFD message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_TFD message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param carriercode_2
	 * @param origin
	 * @param intermediate
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param agentCode
	 * @param FullFlightNo
	 * @param exportIATAAgentCode
	 * @param rate
	 * @param flightStartDate
	 * @param paymentType
	 * @param secureScc
	 * @return
	 * @author A-8260
	 */
	public static String createFWBMessage_Transhipment(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode, String carriercode_2, String origin, String intermediate, String destination, String pieces, String weight
			, String shipperName, String consigneeName, String agentCode, String FullFlightNo, String exportIATAAgentCode, String rate, String flightStartDate, String paymentType, String... secureScc) {

		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					if (line.startsWith("RTG")) {
						line = "RTG/" + intermediate + carriercode_2 + "/" + destination + carrierCode;
						oldtext += line + System.getProperty("line.separator");
					}

				} else if (counter == 4) {
					line = "SHP/" + shipperName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 9) {
					line = "CNE/" + consigneeName;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 14) {
					if (exportIATAAgentCode.length() > 7) {
						String agentIATACode = exportIATAAgentCode.substring(0, 7);
						String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
						line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
					} else {
						line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
					}
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 17) {
					if (line.startsWith("CVD/")) {
						if (paymentType.equals("NC")) {

							line = "CVD/INR/NC/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else if (paymentType.equals("blank")) {
							line = "CVD/INR//PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						} else {

							line = "CVD/INR/" + paymentType + "/PP/NVD/NCV/XXX";
							oldtext += line + System.getProperty("line.separator");
						}
					}
				} else if (counter == 18) {
					if (line.startsWith("RTD")) {
						if (rate != null && rate.trim().length() > 0) {
							line = "RTD/1/P" + pieces + "/K" + weight + "/W" + weight + "/R" + rate + "/T100000";
							oldtext += line + System.getProperty("line.separator");
						} else {
							line = "RTD/1/P" + pieces + "/K" + weight + "/W" + weight;
							oldtext += line + System.getProperty("line.separator");
						}
					}
				} else if (counter == 21) {
					String Startdate = flightStartDate;
					Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
					line = "ISU/" + Startdate + "/" + destination;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 24 && secureScc.length != 0) {

					line = "SPH/" + secureScc[0];
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * @param messageSampleFilePath
	 * @param awbNoPrefix
	 * @param awbNo
	 * @param carrierCode
	 * @param origin
	 * @param destination
	 * @param pieces
	 * @param weight
	 * @param shipperName
	 * @param consigneeName
	 * @param agentCode
	 * @param FullFlightNo
	 * @param exportIATAAgentCode
	 * @param rate
	 * @param flightStartDate
	 * @param paymentType
	 * @param OCIFlag
	 * @param secureScc
	 * @return
	 * @author A-8260
	 */
	public static String createFWBMessage_OCIScreenMethod(String messageSampleFilePath, String awbNoPrefix, String awbNo, String carrierCode, String origin, String destination, String pieces, String weight
			, String shipperName, String consigneeName, String agentCode, String FullFlightNo, String exportIATAAgentCode, String rate, String flightStartDate, String paymentType, boolean OCIFlag, String... secureScc) {
		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;

		String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = awbNoPrefix + "-" + awbNo + origin + destination + "/T" + pieces + "K" + weight + "MC1";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					if (line.startsWith("RTG")) {
						line = "RTG/" + destination + carrierCode;
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 4) {
					if (line.startsWith("SHP")) {
						line = "SHP/" + shipperName;
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 9) {
					if (line.startsWith("CNE")) {
						line = "CNE/" + consigneeName;
						oldtext += line + System.getProperty("line.separator");
					}
				} else if (counter == 14) {
					if (exportIATAAgentCode.length() > 7) {
						String agentIATACode = exportIATAAgentCode.substring(0, 7);
						String remainingDigits = exportIATAAgentCode.substring(7, exportIATAAgentCode.length());
						line = "AGT/" + agentCode + "/" + agentIATACode + "/" + remainingDigits + "/CAG";
					} else {
						line = "AGT/" + agentCode + "/" + exportIATAAgentCode + "//CAG";
					}
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 25) {
					String Startdate = flightStartDate;
					if (date.equals(flightStartDate))
						Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
					else
						Startdate = date;
					Startdate = (Startdate.substring(0, 6) + Startdate.substring(9, 11)).replaceAll("-", "");
					line = "ISU/" + Startdate + "/" + origin + "/SYSADMIN";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 35) {
					String scrDate = date;
					scrDate = BasePage.dateFormatCoverter("dd-MM-yyyy", "ddMMyy", scrDate);
					line = "///SD/" + scrDate;
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FWB message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create FSU DEP message
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param depStn
	 * @param arrStn
	 * @param arrTime
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @return
	 * @author A-7868 Krishna <19/04/2018>
	 */
	public static String createFSU_DEPMessage(String messageSampleFilePath, String FullAWBNo, String fullFlightNo, String flightStartDate, String depStn, String arrStn, String arrTime, String origin, String dest, String pcs, String wt) {

		logger.info("Creating FSU_DEP message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_DEP";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "DEP/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + depStn+arrStn + "/T" + pcs + "K" + wt + "/A" + arrTime + "00/S" + arrTime + "00";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_DEP message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_DEP message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	/**
	 * Method to create FSU-ARR message
	 * @param messageSampleFilePath
	 * @param FullAWBNo
	 * @param fullFlightNo
	 * @param flightStartDate
	 * @param depStn
	 * @param arrStn
	 * @param arrTime
	 * @param origin
	 * @param dest
	 * @param pcs
	 * @param wt
	 * @return
	 * @author A-7868 Krishna <19/04/2018>
	 */
	public static String createFSU_ARRMessage(String messageSampleFilePath, String FullAWBNo, String fullFlightNo, String flightStartDate, String depStn, String arrStn, String arrTime, String origin, String dest, String pcs, String wt) {

		logger.info("Creating FSU_ARR message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FSU_ARR";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = FullAWBNo + origin + dest + "/T" + pcs + "K" + wt;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = "ARR/" + fullFlightNo + "/" + flightDateWithoutYear + "1328/" + arrStn + "/T" + pcs + "K" + wt + "//A" + arrTime + "00";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;System.out.println("counter = "+counter+"--------------");
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FSU_ARR message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FSU_ARR message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}



	

    
    public static String   createxFWBMessage(String xmlDocPath,String awbPref, String awbNo,String pcs, String wt, String date, String origin, String dest)
            throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException, TransformerFactoryConfigurationError,
            TransformerException {
		String msg="MSG005_xFWB";
		String startDate=new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
        String fileName = xmlDocPath.split("\\.")[0];
        
        try {

            String xml;
           
            Node node = null;
//            xml = new String(Files.readAllBytes(Paths.get(xmlDocPath)));
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            Document doc = builder.parse(xmlDocPath);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            

            node = (Node) xpath.evaluate("//MessageHeaderDocument/ID",doc,XPathConstants.NODE);
            startDate=BasePage.dateFormatCoverter("dd-MM-yyyy", "ddMMyy", startDate);
            date=startDate;
            System.out.println(startDate);
            node.setTextContent(awbPref+"-"+awbNo+"_"+startDate+"154714308");
            node = (Node) xpath.evaluate("//MessageHeaderDocument/IssueDateTime",doc,XPathConstants.NODE);
            startDate=BasePage.dateFormatCoverter("ddMMyy", "yyyy-MM-dd", startDate);
            Date d = new Date();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String current_time_str = sdf.format(cal.getTime());
            System.out.println(current_time_str);          
            System.out.println(startDate);
            System.out.println(startDate+"T"+current_time_str);
            node.setTextContent(startDate+"T"+current_time_str);
            node = (Node) xpath.evaluate("//BusinessHeaderDocument/ID",doc,XPathConstants.NODE);
            node.setTextContent(awbPref+"-"+awbNo);
            node = (Node) xpath.evaluate("//BusinessHeaderDocument/SignatoryCarrierAuthentication/ActualDateTime",doc,XPathConstants.NODE);
            node.setTextContent(startDate+"T00:00:00");
            node = (Node) xpath.evaluate("//BusinessHeaderDocument/SignatoryCarrierAuthentication/IssueAuthenticationLocation/Name",doc,XPathConstants.NODE);
            node.setTextContent(origin);
            node = (Node) xpath.evaluate("//BusinessHeaderDocument/SignatoryCarrierAuthentication/IssueAuthenticationLocation/Name",doc,XPathConstants.NODE);
            node.setTextContent(origin);
            node = (Node) xpath.evaluate("//MasterConsignment/OriginLocation/ID",doc,XPathConstants.NODE);
            node.setTextContent(origin);
            node = (Node) xpath.evaluate("//MasterConsignment/FinalDestinationLocation/ID",doc,XPathConstants.NODE);
            node.setTextContent(dest);
            node = (Node) xpath.evaluate("//MasterConsignment/SpecifiedLogisticsTransportMovement/ArrivalEvent/OccurrenceArrivalLocation/ID",doc,XPathConstants.NODE);
            node.setTextContent(dest);
            node = (Node) xpath.evaluate("//MasterConsignment/SpecifiedLogisticsTransportMovement/DepartureEvent/OccurrenceDepartureLocation/ID",doc,XPathConstants.NODE);
            node.setTextContent(origin);
            
          
            node = (Node) xpath.evaluate("//MasterConsignment/TotalPieceQuantity",doc,XPathConstants.NODE);
            node.setTextContent(pcs);
            node = (Node) xpath.evaluate("//MasterConsignment/ApplicableRating/IncludedMasterConsignmentItem/PieceQuantity",doc,XPathConstants.NODE);
            node.setTextContent(pcs);
            node = (Node) xpath.evaluate("//MasterConsignment/IncludedTareGrossWeightMeasure",doc,XPathConstants.NODE);
            node.setTextContent(wt);
            node = (Node) xpath.evaluate("//MasterConsignment/ApplicableRating/IncludedMasterConsignmentItem/GrossWeightMeasure",doc,XPathConstants.NODE);
            node.setTextContent(wt);
            node = (Node) xpath.evaluate("//MasterConsignment/ApplicableRating/IncludedMasterConsignmentItem/ApplicableFreightRateServiceCharge/ChargeableWeightMeasure",doc,XPathConstants.NODE);
            node.setTextContent(wt);
            
            node = (Node) xpath.evaluate("//MasterConsignment/IncludedCustomsNote/Content",doc,XPathConstants.NODE);
            node.setTextContent(date+"1049");
            DOMSource source = new DOMSource(doc);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            FileWriter fw = new FileWriter(msg+ ".xml");

            StreamResult result = new StreamResult(fw);

            transformer.transform(source,result);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg+ ".xml";
    }
    

    
    
    
    public static String createFFRNEWMessage(String messageSampleFilePath,String AWBNoPrefix, String AWBNo, String Origin, String Destination, 
			String Pieces, String Weight, String FlightStartDate, String FullFlightNo, String FFR_Code,String agentCode,String exportIATAAgentCode){

		logger.info("Creating FFR message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFRNEW";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties","selenium.message.path");
		String line = "", oldtext = "";
		int counter=1;

		try {
			while((line = reader.readLine()) != null){                 
				if(counter==2)
				{
					line=AWBNoPrefix+"-"+AWBNo+Origin+Destination+"/T"+Pieces+"K"+Weight+"MC1/CONSOL NR";
					oldtext += line+System.getProperty("line.separator");

				}                                           
				else if(counter==4)
				{

					String date = FlightStartDate;
					int indexOfDateSeperator = date.lastIndexOf("-");
					date = date.substring(0,indexOfDateSeperator);
					date = date.replaceAll("-", "");
					String newd1 = date.toUpperCase();
					line=FullFlightNo+"/"+newd1+"/"+Origin+Destination+"/"+FFR_Code;
					oldtext += line+System.getProperty("line.separator");
				}


				else if(counter==8)
				{

					line = "CUS/"+agentCode+"/"+exportIATAAgentCode+"//AGT";
					oldtext += line+System.getProperty("line.separator");
				}


				else
				{
					oldtext += line+System.getProperty("line.separator");
				}
				counter++;
				line="";
			}
		} catch (Exception e) {
			logger.error("Error while creating FFR message"+e);
		}
		createdMessagePath = path+msg_Type+".txt";
		logger.info("FFR message will be created in '"+createdMessagePath+"'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}

	public static String createFFMMessage_1ULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo,
			String pieces, String weight, String volume, String uldNo) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				}  else if (counter == 4) {
					line = "ULD/" + uldNo + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = fullAWBNo + origin + destination + "/T" + pieces + "K" + weight + "MC" + volume + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				}  else if (counter == 6) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}    
    
    public static String createFWBMessage_OCIScreenMethod_Transhipment(String messageSampleFilePath,String awbNoPrefix,String awbNo,String carrierCode,String origin,String destination,String pieces, String weight
			,String shipperName,String consigneeName,String agentCode,String FullFlightNo,String exportIATAAgentCode,String rate,String flightStartDate, String paymentType,boolean OCIFlag, boolean trans, String intermediate, String carriercode_2, String... secureScc)
	{
		logger.info("Creating FWB message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FWB";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties","selenium.message.path");
		String line = "", oldtext = "";
		int counter=1;
		
		 String date=new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
		try{
			while((line = reader.readLine()) != null){
				if(counter==2){
					line=awbNoPrefix+"-"+awbNo+origin+destination+"/T"+pieces+"K"+weight+"MC1";
					oldtext += line+System.getProperty("line.separator");
				}else if(counter==3){			           
					if(line.startsWith("RTG")){
						if(trans)
							line="RTG/"+intermediate+carriercode_2+"/"+destination+carrierCode;
						else
						line="RTG/"+destination+carrierCode;
						oldtext += line+System.getProperty("line.separator");
					}
				}
				else if(counter==4){			           
					if(line.startsWith("SHP")){
						line="SHP/"+shipperName;
						oldtext += line+System.getProperty("line.separator");
					}}
				else if(counter==9){ 
					if(line.startsWith("CNE")){
					line="CNE/"+consigneeName;
					oldtext += line+System.getProperty("line.separator");
					}}else if(counter==14){
					if(exportIATAAgentCode.length()>7){
						String agentIATACode = exportIATAAgentCode.substring(0,7);
						String remainingDigits = exportIATAAgentCode.substring(7,exportIATAAgentCode.length());
						line = "AGT/"+agentCode+"/"+agentIATACode+"/"+remainingDigits+"/CAG";
					}
					else{
						line = "AGT/"+agentCode+"/"+exportIATAAgentCode+"//CAG";
					}							
					oldtext += line+System.getProperty("line.separator");
				}else if(counter==25){
					String Startdate=flightStartDate;
					if(date.equals(flightStartDate))
					Startdate=(Startdate.substring(0,6)+Startdate.substring(9,11)).replaceAll("-","");
					else
					Startdate=date;	
					Startdate=(Startdate.substring(0,6)+Startdate.substring(9,11)).replaceAll("-","");
					line="ISU/"+Startdate+"/"+origin+"/SYSADMIN";
					oldtext += line+System.getProperty("line.separator");              
				}else if(counter==35){
					String scrDate=date;
					scrDate=BasePage.dateFormatCoverter("dd-MM-yyyy", "ddMMyy", scrDate);						
					line="///SD/"+scrDate;
					oldtext += line+System.getProperty("line.separator");
				}
				else{
					oldtext += line+System.getProperty("line.separator");;
				}
				counter++;
				line="";
			}
		}
		catch(IOException e) {
			logger.error("Error while creating FWB message"+e);
		}
		createdMessagePath = path+msg_Type+".txt";
		logger.info("FWB message will be created in '"+createdMessagePath+"'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}
    
    
    /**
     * Created by A-7605 on 21-5-18
     * This method create FFM message for 3 AWBs containing in 1 ULD
     * @param messageSampleFilePath
     * @param fullFlightNo
     * @param flightStartDate
     * @param origin
     * @param destination
     * @param fullAWBNo1
     * @param pieces1
     * @param weight1
     * @param volume1
     * @param fullAWBNo2
     * @param pieces2
     * @param weight2
     * @param volume2
     * @param fullAWBNo3
     * @param pieces3
     * @param weight3
     * @param volume3
     * @param uldNo
     * @return
     */
    public static String createFFMMessage_3AWBs_3ULD(String messageSampleFilePath, String fullFlightNo, String flightStartDate, String origin, String destination, String fullAWBNo1,
			String pieces1, String weight1, String volume1, String uldNo1,String fullAWBNo2, String pieces2, String weight2, String volume2,String uldNo2,
			String fullAWBNo3, String pieces3, String weight3, String volume3,String uldNo3) {
		logger.info("Creating FFM message");
		BufferedReader reader = getReader(messageSampleFilePath);
		String msg_Type = "MSG005_FFM";
		String createdMessagePath;
		String path = PropertyHandler.getPropValue("resources\\EnvSetup.properties", "selenium.message.path");
		String line = "", oldtext = "";
		int counter = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(flightStartDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdf = new SimpleDateFormat("ddMMMyy");
		String flightDate = sdf.format(date).toUpperCase();
		String flightDateWithoutYear = flightDate.substring(0, 5);
		try {
			while ((line = reader.readLine()) != null) {
				if (counter == 2) {
					line = "1/" + fullFlightNo + "/" + flightDateWithoutYear + "/" + origin;
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 3) {
					line = destination + "/" + flightDate + "25/" + flightDate + "25";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 4){
					line = "ULD/" + uldNo1 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 5) {
					line = fullAWBNo1 + origin + destination + "/T" + pieces1 + "K" + weight1 + "MC" + volume1 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 6){
					line = "ULD/" + uldNo2 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 7) {
					line = fullAWBNo2 + origin + destination + "/T" + pieces2 + "K" + weight2 + "MC" + volume2 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				}  else if (counter == 8){
					line = "ULD/" + uldNo3 + "/C-C";
					oldtext += line + System.getProperty("line.separator");
				}else if (counter == 9) {
					line = fullAWBNo3 + origin + destination + "/T" + pieces3 + "K" + weight3 + "MC" + volume3 + "/BOOKS";
					oldtext += line + System.getProperty("line.separator");
				} else if (counter == 10) {
					line = "LAST";
					oldtext += line + System.getProperty("line.separator");
				} else {
					oldtext += line + System.getProperty("line.separator");
					;
				}
				counter++;
				line = "";
			}
		} catch (IOException e) {
			logger.error("Error while creating FFM message" + e);
		}
		createdMessagePath = path + msg_Type + ".txt";
		logger.info("FWB message will be created in '" + createdMessagePath + "'");
		writeToFile(reader, oldtext, createdMessagePath);
		return createdMessagePath;
	}
    
    
    
    
    
    
   

}
