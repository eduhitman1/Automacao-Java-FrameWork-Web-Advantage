package com.edsoft.framework.base.web;

import org.openqa.selenium.WebDriver;

public class Browser {
	private WebDriver _driver;
	public BrowserType Type;

	public Browser(WebDriver _driver) {
		this._driver = _driver;
	}

	/**
	 * retorna url 
	 */
	public void GoToUrl(String url) {
		_driver.get(url);
	}

	/**
	 * maximizador de tela
	 */
	public Browser Maximize() {
		_driver.manage().window().maximize();
		return new Browser(_driver);
	}

}
