package com.edsoft.framework.configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.edsoft.framework.base.web.BrowserType;

public class ConfigReader {
	public static void PopulateSettings() throws IOException {
		ConfigReader reader = new ConfigReader();
		reader.ReadProperty();
	}

	/**
	 * Metodo de leitura de property
	 */
	public void ReadProperty() throws IOException {
		Properties p = new Properties();
//	        InputStream inputStream = new FileInputStream("src/main/java/com/edsoft/framework/configs/GlobalConfig.properties");
		InputStream inputStream = new FileInputStream("GlobalConfig.properties");
		p.load(inputStream);

		// Get AUTConnection String
		Settings.AUTConnectionString = p.getProperty("AUTConnectionString");
		// Get LogPath
		Settings.LogPath = p.getProperty("LogPath");
		// Get DriverType
		Settings.DriverType = p.getProperty("DriverType");
		// GEt ExcelSheetPath
		Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
		// Get AUT
		Settings.AUT = p.getProperty("AUT");
		// Browser Type
		Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
		// hub selenium
		Settings.SeleniumGridHub = p.getProperty("SeleniumGrid");

		Settings.ExcelPoi = p.getProperty("ExcelPoi");

		Settings.path_chromedriver = p.getProperty("path_chromedriver");
		Settings.path_firefox = p.getProperty("path_firefox");
		Settings.path_ie = p.getProperty("path_ie");
		Settings.path_safari = p.getProperty("path_safari");
		Settings.path_edge = p.getProperty("path_edge");

		Settings.path_MassaJson = p.getProperty("path_MassaJson");

		Settings.path_Screenshort = p.getProperty("path_Screenshort");

		Settings.path_evidencia = p.getProperty("path_evidencia");

		Settings.path_logo = p.getProperty("path_logo");
		Settings.path_qa = p.getProperty("path_qa");

		Settings.path_modelo_report_web = p.getProperty("path_modelo_report_web");
		Settings.path_modelo_report_mobile = p.getProperty("path_modelo_report_mobile");
		
		Settings.path_modelo_report_tipo = p.getProperty("path_modelo_report_tipo");
		
		Settings.uri_api = p.getProperty("uri_api");
	}
}
