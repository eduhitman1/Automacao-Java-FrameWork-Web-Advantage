package com.edsoft.framework.utilities;

import java.io.File;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.edsoft.framework.configs.Settings;

public class ExtentManager {

	private static ExtentReports extent = new ExtentReports();
	private static Platform platform;
	private static String reportFileName = "EdsoftExtentReport.html";
	private static String macPath = System.getProperty("user.dir") + "/extent-reporting";
	
	
	private static String windowsPath = System.getProperty("user.dir") + "\\extent-reporting";
//	private static String windowsPath = Settings.path_Report.toString();
//	private static String windowsPath = "C:\\Users\\Public\\report\\web";
		

	private static String macReportFileLoc = macPath + "/" + reportFileName;
	private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

	public static ExtentReports createInstance() {
		platform = getCurrentPlatForm();
        String fileName = getReportFileLocation(platform);
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(fileName);
		htmlReport.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReport.config().setChartVisibilityOnOpen(true);
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setDocumentTitle(fileName);
		htmlReport.config().setEncoding("utf-8");
		htmlReport.config().setReportName(fileName);

		htmlReport.config().setChartVisibilityOnOpen(true);
		htmlReport.config().setCSS(fileName);
		htmlReport.getCategoryContextInfo();
		extent.setReportUsesManualConfiguration(true);
		
		extent.attachReporter(htmlReport);
		
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Tester Name", "Eduardo Henrique");
		extent.setSystemInfo("Browser","Chrome");
		extent.setTestRunnerOutput(fileName);
		
		return extent;
	}

	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case MAC:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			System.out.println("ExtentReport path for Mac: " + macPath + "\n");
			break;

		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			System.out.println("ExtentReport path for Windows: " + windowsPath + "\n");
			break;

		default:
			System.out.println("ExtentReport path has not been set ! There is a problem \n");
			break;
		}
		return reportFileLocation;
	}

	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}
	
	private static Platform getCurrentPlatForm() {
		if(platform ==null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if(operSys.contains("win")) {
				platform= Platform.WINDOWS;
			} else if(operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
        return platform;		
	}
}
