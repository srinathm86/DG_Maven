package com.ibsplc.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlRead {
	static String b;
	

	/**
	 * 
	 * @param 
	 * @return
	 * @author a-8457 Souvik
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static String Read(String value){
		try {
			
			File stocks = new File("TestSuite/test.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName(value);

			Node nNode = nodes.item(0);
			Element element = (Element) nNode;
		     b = element.getAttribute("value");
			} catch (Exception ex) {
			ex.printStackTrace();
			}
		return b;
			}

			private static String getValue(String tag, Element element) {
			NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node node = (Node) nodes.item(0);
			return node.getNodeValue();
	       
}
}