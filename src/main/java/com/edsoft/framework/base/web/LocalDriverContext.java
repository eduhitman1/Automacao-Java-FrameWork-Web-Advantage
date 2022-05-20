package com.edsoft.framework.base.web;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalDriverContext {
	private static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();

	/**
	 * Metodos de de recupera driver remoto e set
	 */
	
	public static RemoteWebDriver getRemoteWebDriver() {
		return remoteWebDriverThreadLocal.get();
	}

	static void setRemoteWebDriverThreadLocal(RemoteWebDriver driverThreadLocal) {
		remoteWebDriverThreadLocal.set(driverThreadLocal);
	}
}
