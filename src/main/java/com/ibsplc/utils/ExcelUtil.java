package com.ibsplc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	
	/**
	 * Function used to fetch the excel data in a hashmap
	 * @author A-8680
	 * @param fileName
	 * @param sheetName
	 * @param rowno
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String, String> setMapData(String fileName, String sheetName) throws IOException{
		String dir = System.getProperty("user.dir");
		String path = dir +"//resources//TestData//BASE//" + fileName + ".xlsx";
		FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetName);
		
		HashMap<String, String> dataMap = new HashMap<String,String>();
		
		int lastColumn = sh.getRow(0).getLastCellNum();
	
		for(int a=1; a<= sh.getLastRowNum(); a++) {
//			if(!sh.getRow(a).getCell(1).toString().equalsIgnoreCase(className))
//				continue;
			
			for (int i = 1; i < lastColumn; i++) {
				String key, value;
				
	//			Header
				String keyCell = sh.getRow(0).getCell(i).toString();
				String keyPrefix = sh.getRow(a).getCell(0).toString();
				key = keyPrefix + "." +keyCell;
				
	//			Data
				Cell valueCell = sh.getRow(a).getCell(i);
				try {
					value = valueCell.getStringCellValue().trim();
				} catch (IllegalStateException e) {
					int numericValue = (int) valueCell.getNumericCellValue();
					value = Integer.toString(numericValue);
				}
				dataMap.put(key, value);
			}
		}
		fis.close();
		return dataMap;
	}
	
	
	/**
	 * Function used to fetch the excel data in a hashmap
	 * @author A-8680
	 * @param fileName
	 * @param sheetName
	 * @param rowno
	 * @return
	 * @throws IOException
	 */
	public HashMap<String, String> setMapData(String fileName, String sheetName, int rowNo) throws IOException{
		String dir = System.getProperty("user.dir");
		String path = dir +"//resources//TestData//BASE//" + fileName + ".xlsx";
		FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetName);
		
		HashMap<String, String> dataMap = new HashMap<String,String>();
		
		int lastColumn = sh.getRow(0).getLastCellNum();
	
			for (int i = 1; i < lastColumn; i++) {
				String key, value;
				
	//			Header
				key = sh.getRow(0).getCell(i).toString();
				
	//			Data
				Cell valueCell = sh.getRow(rowNo).getCell(i);
				try {
					value = valueCell.getStringCellValue().trim();
				} catch (IllegalStateException e) {
					int numericValue = (int) valueCell.getNumericCellValue();
					value = Integer.toString(numericValue);
				}
//				System.out.println(key);
				dataMap.put(key, value);
			}
		fis.close();
		return dataMap;
	}
	
	/**
	* A-8680
	* @param tcID
	* @param awbNo
	* @throws Exception
	*/
	public void writeDatatoExcelCell(int tcID,String awbNo, String fileName) throws Exception {
	try {
		
	String path = System.getProperty("user.dir")+"//resources//TestData//BASE//" + fileName + ".xlsx";
	File f1 = new File(path);
	FileInputStream ios1 = new FileInputStream(f1);
	XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
	XSSFSheet sheet1 = workbook1.getSheetAt(0);
	int rows = sheet1.getLastRowNum();
	Row row1 = sheet1.getRow(tcID);
	Cell cell1 = row1.createCell(1);
	cell1.setCellValue(awbNo);
	FileOutputStream out = new FileOutputStream(new File(path));
	workbook1.write(out);
	out.close();
	out.flush();
	} catch (Exception e) {
	System.out.println("Setting flags to No failed");
	e.printStackTrace();
	}
	}

	/**
	* A-8680
	* @param tcID
	* @param awbNo
	* @param cell
	* @throws Exception
	*/
	public void writeDatatoExcelCell(int tcID,String value, int cell, String fileName) throws Exception {
	try {
	String path = System.getProperty("user.dir")+"//resources//TestData//BASE//" + fileName + ".xlsx";
	File f1 = new File(path);
	FileInputStream ios1 = new FileInputStream(f1);
	XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
	XSSFSheet sheet1 = workbook1.getSheetAt(0);
	int rows = sheet1.getLastRowNum();
	Row row1 = sheet1.getRow(tcID);
	Cell cell1 = row1.createCell(cell);
	cell1.setCellValue(value);
	FileOutputStream out = new FileOutputStream(new File(path));
	workbook1.write(out);
	out.close();
	out.flush();
	} catch (Exception e) {
	System.out.println("Setting flags to No failed");
	e.printStackTrace();
	}

	}
}