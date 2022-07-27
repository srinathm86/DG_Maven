package com.ibsplc.generic;


import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;


public class TestInitiator_Smoke {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestNG runner=new TestNG();

		List<String> suitefiles=new ArrayList<String>();

		suitefiles.add("TestSuite/test_Smoke.xml");

		runner.setTestSuites(suitefiles);

		runner.run();
	
	}

}
