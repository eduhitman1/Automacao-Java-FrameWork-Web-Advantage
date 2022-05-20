package com.edsoft.framework.controls.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.edsoft.framework.controls.internals.Control;

public class ControlHandler implements InvocationHandler {
	/**
	 * Gera um manipulador para recuperar o WebElement de um localizador para um
	 * determinado Descendente da interface WebElement.
	 */

	private final ElementLocator locator;
	private final Class<?> wrappingType;

	
	
	public <T> ControlHandler(Class<T> interfaceType, ElementLocator locator) {
		this.locator = locator;
		if (!Control.class.isAssignableFrom(interfaceType)) {
			throw new RuntimeException("interface not assignable to Control.");
		}

		this.wrappingType = ImplementedByProcessor.getWrapperClass(interfaceType);
	}

	@Override
	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		WebElement element = locator.findElement();

		if ("getWrappedElement".equals(method.getName())) {
			return element;
		}
		Constructor cons = wrappingType.getConstructor(WebElement.class);
		Object thing = cons.newInstance(element);
		try {
			return method.invoke(wrappingType.cast(thing), objects);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}
}
