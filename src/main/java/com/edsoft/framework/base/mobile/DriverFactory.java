package com.edsoft.framework.base.mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	/**
	 * DriverFactory 
	 * @Param passagem de parametros mobile
	 */
	private static ThreadLocal<AppiumDriver<MobileElement>> driver = null;

	public static AppiumDriver<MobileElement> getDriver() {
		if (driver == null)
			driver = new ThreadLocal<>();
		return driver.get();
	}

	public static AppiumDriver<MobileElement> constant(String platformName, String deviceName, String appPackage,
			String appActivity) throws MalformedURLException {
		createDriver(platformName, deviceName, appPackage, appActivity);
		return driver.get();
	}

	private static void createDriver(String platformName, String deviceName, String appPackage, String appActivity)
			throws MalformedURLException {
//		if (plataforma.contains("android")) {
		driver.set(createAndroidDriver(platformName, deviceName, appPackage, appActivity));
//		} else {
//			driver.set(createIosDriver(platformName, deviceName, appPackage));
//		}
		getDriver().manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
	}

	private static AndroidDriver<MobileElement> createAndroidDriver(String platformName, String deviceName,
			String appPackage, String appActivity) throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:4723" + "/wd/hub");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//	    desiredCapabilities.setCapability("udid", udid);
		desiredCapabilities.setCapability("platformName", platformName);
		desiredCapabilities.setCapability("deviceName", deviceName);
		desiredCapabilities.setCapability("appPackage", appPackage);
		desiredCapabilities.setCapability("appActivity", appActivity);
//	    desiredCapabilities.setCapability("userName", ReadProperties.getMcUser());
//		desiredCapabilities.setCapability("automationName", "uiautomator2");
		desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:/Users/User/Documents/eclipce-workspace/Automacao-Java-FrameWork-Mobile-Advantage/Advantage+demo+2_2.apk");
		try {
			return new AndroidDriver<MobileElement>(url, (Capabilities) desiredCapabilities);
		} catch (SessionNotCreatedException error) {
			Assert.fail(
					"ANDROID| Falha ao criar conexao com o dispositivo! DICA: Verifique se o dispositivo ja esta reservado ou desconectado.");
			return null;
		}
	}

	private static IOSDriver<MobileElement> createIosDriver(String platformName, String deviceName, String appPackage)
			throws MalformedURLException {
		URL url = new URL("Caminho de configuração" + "/wd/hub");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//		desiredCapabilities.setCapability("udid", udid);
		desiredCapabilities.setCapability("automationName", "XCUITest");
		desiredCapabilities.setCapability("platformName", platformName);
		desiredCapabilities.setCapability("deviceName", deviceName);
//	    desiredCapabilities.setCapability("userName", ReadProperties.getMcUser());
//	    desiredCapabilities.setCapability("password", ReadProperties.getMcPass());
		desiredCapabilities.setCapability("bundleId", appPackage);
		desiredCapabilities.setCapability("launchTimeout", Integer.valueOf(60000));
		desiredCapabilities.setCapability("noReset", false);
		try {
			return new IOSDriver(url, (Capabilities) desiredCapabilities);
		} catch (SessionNotCreatedException error) {
			Assert.fail(
					"iOS| Falha ao criar conexao com o dispositivo! DICA: Verifique se o dispositivo ja esta reservado ou desconectado.");
			return null;
		}
	}

	@AfterClass
	public static void killDriver() throws InterruptedException {
		if (driver != null) {
			driver.wait();
			driver = null;
		}
	
	}

}