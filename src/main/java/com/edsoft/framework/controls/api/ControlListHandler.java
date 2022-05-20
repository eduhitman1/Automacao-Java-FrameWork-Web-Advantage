package com.edsoft.framework.controls.api;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.edsoft.framework.controls.internals.Control;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ControlListHandler implements InvocationHandler {

	/**
	 * Dada uma interface e um localizador, aplique um wrapper sobre uma lista de
	 * elementos.
	 * @param interfaceType tipo de interface que estamos tentando envolver o elemento.
	 * @param localizador   localizador na p�gina para os elementos. tipo @param <T> da interface.
	 */
	private final ElementLocator locator;
	private final Class<?> wrappingType;

	public <T> ControlListHandler(Class<T> interfaceType, ElementLocator locator) {
		this.locator = locator;
		if (!Control.class.isAssignableFrom(interfaceType)) {
			throw new RuntimeException("interface not assignable to Control.");
		}
		this.wrappingType = ImplementedByProcessor.getWrapperClass(interfaceType);

	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		List<Object> wrappedList = new ArrayList<Object>();
		Constructor<?> cons = wrappingType.getConstructor(WebElement.class);
		for (WebElement element : locator.findElements()) {
			Object thing = cons.newInstance(element);
			wrappedList.add(wrappingType.cast(thing));
		}
		try {
			return method.invoke(wrappedList, objects);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}
}
