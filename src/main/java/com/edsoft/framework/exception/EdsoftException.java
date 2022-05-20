package com.edsoft.framework.exception;

import org.testng.Assert;

public class EdsoftException extends Exception {
	/**
	 * Exception Erro Junit fail
	 * @String @exception
	 */
	public EdsoftException(String message, Exception ex) {
		Assert.fail(message, ex.getCause());
	}
}
