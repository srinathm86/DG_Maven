package com.ibsplc.common;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ReadXML {

	public static void main(String argv[]) throws SAXException, IOException{

		try {
		File fXmlFile = new File("./TestSuite/test.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
			dBuilder = dbFactory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(fXmlFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		org.w3c.dom.NodeList  nList = doc.getElementsByTagName("parameter");

		Node nNode = nList.item(1);
		Element eElement = (Element) nNode;
		String a = eElement.getAttribute("value");

		System.out.println(a);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
