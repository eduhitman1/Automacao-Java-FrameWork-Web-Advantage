package com.edsoft.framework.base.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseFuncionalidadeMobile {
	/**
	 * BaseFuncionalidadeMobile Inicialização de fábrica da página de controle
	 * personalizada
	 * 
	 * @return Tpage
	 */
	public <TPage extends BasePaginaMobile> TPage getInstance(Class<TPage> pageClass) {
		try {
			return (TPage) PageFactory.initElements((WebDriver) DriverFactory.getDriver(), pageClass);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	
	}
	

}
