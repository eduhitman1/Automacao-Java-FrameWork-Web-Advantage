package com.edsoft.framework.configs;

import java.sql.Connection;

import com.edsoft.framework.base.web.BrowserType;
import com.edsoft.framework.utilities.LogUtil;

public class Settings {
	
	/**
	 * Criação de property
	 */
	// For Application Backend
	public static Connection AUTConnection;
	// For EARS Reporting
	public static Connection ReportingConnection;
	// Connection batabase string
	public static String AUTConnectionString;
	// For report
	public static String ReportingConnectionString;
	// Log Path for framework
	public static String LogPath;
	// Driver Type for SQL Server connectivity
	public static String DriverType;
	// Passed excelsheetPath
	public static String ExcelSheetPath;
	// Url site
	public static String AUT;
	// Typer Browser
	public static BrowserType BrowserType;
	// Logs system
	public static LogUtil Logs;

	public static String FeatureContext;

	public static String ScenarioContext;

	public static String SeleniumGridHub;

	public static String ExcelPoi;

	public static String path_chromedriver;
	public static String path_firefox;
	public static String path_ie;
	public static String path_safari;
	public static String path_edge;

	public static String path_MassaJson;

	public static String path_Screenshort;

	public static String path_evidencia;

	public static String path_logo;
	public static String path_qa;

	public static String path_modelo_report_web;
	public static String path_modelo_report_mobile;

	public static String path_modelo_report_tipo;
	
	public static String uri_api;
}
