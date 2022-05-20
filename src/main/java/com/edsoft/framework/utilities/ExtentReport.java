package com.edsoft.framework.utilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

public class ExtentReport {
	
	/**
	 *  Report de test
	 */
	public static ExtentReports extentReports = ExtentManager.createInstance();
	private static ExtentTest extentTest = null;
	private static Map extentFeatureMap = new HashMap();
	private static Map extentScenarioMap = new HashMap();

	public static synchronized ExtentTest getFeature() {
		return (ExtentTest) extentFeatureMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest getScenario() {
		return (ExtentTest) extentScenarioMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startFeature(String featureName) throws ClassNotFoundException {
		ExtentTest test = extentReports.createTest(new GherkinKeyword("Feature"), featureName);
		extentFeatureMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	public static synchronized ExtentTest startScenario(String scenarioName) throws ClassNotFoundException {
		extentTest = getFeature();
//		extentTest = extentTest.createNode(new GherkinKeyword("Scenario"), scenarioName);     
		extentScenarioMap.put((int) (long) (Thread.currentThread().getId()), extentTest);
		return extentTest;
	}

}
