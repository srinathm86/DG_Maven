package com.ibsplc.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ibsplc.utils.PropertyHandler;

/**
 * 
 * @author SHALINI/Sharath on 29-03-2019 Description To create Dynamic suite
 *         based on module
 **/
public class TestInitiator {

	public static void main(String[] args) throws Exception {
		// Fetch Module from Property file and update the Flag
		String pathPrefix = System.getProperty("user.dir");
		String path = pathPrefix + "\\resources\\TestToRun.properties";
		List<String> moduleList = new ArrayList<String>();
		String module = PropertyHandler.getPropValue(path, "module");
		int moduleLength = module.split(",").length;
		String[] modulearr = module.split(",");
		if (moduleLength > 1) {
			for (int i = 0; i < moduleLength; i++) {
				moduleList.add(modulearr[i]);
			}
		} else {
			moduleList.add(module);
		}
		Generic obj = new Generic(null, null, null,null);
		obj.writeNoFlagExcel();
		for (int j = 0; j < moduleList.size(); j++) {
			String pmyKey = moduleList.get(j).toString().trim();
			obj.updateExcelFlags(pmyKey);
		}
		// Fetch Data from Dynamic suite and update the Excel
		List<String> suitefiles = new ArrayList<String>();
		String testCaseClassName = "";
		int count = 0;
		String flag = "";
		String st2 = System.getProperty("user.dir");
		String path2 = st2 + "\\resources\\DynamicSuite.xlsx";
		File f1 = new File(path2);
		FileInputStream ios1 = new FileInputStream(f1);
		XSSFWorkbook workbook1 = new XSSFWorkbook(ios1);
		XSSFSheet sheet1 = workbook1.getSheet("AA");
		for (int i = 2; i <= sheet1.getLastRowNum(); i++) {
			Row row = sheet1.getRow(i);
			Cell RunStatus = row.getCell(3);
			flag = RunStatus.toString();
//			System.out.println(flag);
			if (flag.equalsIgnoreCase("Yes")) {
				testCaseClassName = row.getCell(1).toString();
				suitefiles.add(testCaseClassName);
				count++;
			} else {
				continue;
			}
			System.out.println(suitefiles);
		}

		/*
		 * Author- Shalini/Sharath Purpose: Method to create Dyanamic suite
		 * Date: 22-02-2019
		 */
		String testNGpath = st2 + "\\TestSuite\\test.xml";
		File fXmlFile = new File(testNGpath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(fXmlFile);
		Element element = (Element) doc.getElementsByTagName("classes").item(0);
		Node parent = element.getParentNode();
		parent.removeChild(element);
		parent.normalize();
		toString(doc);
		Node testTag = doc.getElementsByTagName("test").item(0);
		testTag.appendChild(createPersonElement(doc, suitefiles));
		toString(doc);
		System.out.println("Working");
		TestNG runner = new TestNG();
		List<String> suitefiles1 = new ArrayList<String>();
		suitefiles1.add("TestSuite/test.xml");
		runner.setTestSuites(suitefiles1);
		runner.run();
	}

	/*
	 * Author- Souvik/Shalini/Sharath Purpose: Method to Create Nodes
	 * 
	 * @Param: newDoc Date: 22-02-2019
	 */
	private static Node createPersonElement(Document doc, List<String> suitefiles) {
		Element el = doc.createElement("classes");
		for (int i = 0; i < suitefiles.size(); i++) {
			String testName = suitefiles.get(i).toString();
			el.appendChild(createPersonDetailElement(doc, "class", testName));
		}
		return el;
	}

	/*
	 * Author- Souvik/Shalini/Sharath Purpose : Method to write data to XML
	 * 
	 * @Param: newDoc Date: 22-02-2019
	 */
	private static void toString(Document newDoc) throws Exception {
		String st2 = System.getProperty("user.dir");
		String testNGpath = st2 + "\\TestSuite\\test.xml";
		DOMSource domSource = new DOMSource(newDoc);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);
		StreamResult result = new StreamResult(new File(testNGpath));
		transformer.transform(domSource, result);
		System.out.println("Done");

	}

	/*
	 * Author- Souvik/Shalini/Sharath Purpose : Method to Create attribute and
	 * tag name
	 * 
	 * @Param: doc,name and value Date: 22-02-2019
	 */
	private static Element createPersonDetailElement(Document doc, String name, String value) {
		Element el = doc.createElement(name);
		Attr attr = doc.createAttribute("name");
		attr.setValue(value);
		el.setAttributeNode(attr);
		return el;
	}

}
