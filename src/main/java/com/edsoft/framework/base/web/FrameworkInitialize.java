package com.edsoft.framework.base.web;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.edsoft.framework.configs.Settings;

public class FrameworkInitialize extends BaseFuncionalidadeWeb {
	public void initializeBrowser(BrowserType browserTypes) throws MalformedURLException {
		/**
		 * Caminhos de drivers
		 */
		WebDriver driver = null;
//		RemoteWebDriver driver = null;
		
		
		switch (browserTypes) {
		case Chrome:
			System.setProperty("webdriver.chrome.driver", Settings.path_chromedriver + "chromedriver.exe");
			driver = new ChromeDriver();

//			DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
//			try {
//				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//			} catch (MalformedURLException e) {
//				System.out.println("Falha na conexão com o GRID");
//				e.printStackTrace();
//			}
//			LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
			break;

		case Firefox:
			System.setProperty("webdriver.firefox.marionette", Settings.path_firefox + "geckdriver.exe");
			driver = new FirefoxDriver();
			break;

		case IE:
			System.setProperty("webdriver.ie.marionette", Settings.path_ie + "ie.exe");
			driver = new InternetExplorerDriver();
			break;

		case Edge:
			System.setProperty("webdriver.edge.marionette", Settings.path_edge + "edge.exe");
			driver = new SafariDriver();
			break;

		case Safari:
			System.setProperty("webdriver.safari.marionette", Settings.path_safari + "safari.exe");
			driver = new SafariDriver();
			break;
		}
		DriverContextWeb.setDriver(driver);
		DriverContextWeb.Browser = new Browser(driver);
	}
}
