package com.ibsplc.utils;

import org.openqa.selenium.By;

/**
 * 
 * @author a-7868
 *
 */
public class MiscUtility {
	
	/**
	 * Method to split the locator string and return a By object
	 * @param filepath
	 * @param objName
	 * @return
	 * @author a-7868
	 */
	public static By getWebElement(String filepath, String objName) {
		
		By by;
		String obj = PropertyHandler.getPropValue(filepath, objName);
		String[] arr = obj.split(";");

		switch(arr[0]) {

			case "id"				: 	by = By.id(arr[1]);
										break;
			case "xpath"			: 	by = By.xpath(arr[1]);
										break;
			case "className"		: 	by = By.className(arr[1]);
										break;
			case "css"				: 	by = By.cssSelector(arr[1]);
										break;
			case "name"				: 	by = By.name(arr[1]);
										break;
			case "tagname"			: 	by = By.tagName(arr[1]);
										break;
			case "linkText"			: 	by = By.linkText(arr[1]);
										break;
			case "partialLinkText"	: 	by = By.partialLinkText(arr[1]);
										break;
			default					:	by = null;
		}
		return by;
	}

}
