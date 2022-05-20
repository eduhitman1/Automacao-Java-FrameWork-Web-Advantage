package com.edsoft.framework.controls.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.edsoft.framework.controls.internals.Control;

public class ControlFieldDecorator implements FieldDecorator {
	/**
	 * ControlFieldDecorator passagem de fatoração de elemento de classPage
	 */
	protected ElementLocatorFactory factory;

	public ControlFieldDecorator(ElementLocatorFactory factory) {
		this.factory = factory;
	}

	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!(WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))) {
			return null;
		}
		ElementLocator locator = factory.createLocator(field);
		if (locator == null) {
			return null;
		}
		Class<?> fieldType = field.getType();
		if (WebElement.class.equals(fieldType)) {
			fieldType = Control.class;
		}
		if (WebElement.class.isAssignableFrom(fieldType)) {
			return proxyForLocator(loader, fieldType, locator);
		} else if (List.class.isAssignableFrom(fieldType)) {
			Class<?> erasureClass = getErasureClass(field);
			return proxyForListLocator(loader, erasureClass, locator);
		} else {
			return null;
		}
	}

	private Class getErasureClass(Field field) {
		Type genericType = field.getGenericType();
		if (!(genericType instanceof ParameterizedType)) {
			return null;
		}
		return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
	}

	protected boolean isDecoratableList(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) {
			return false;
		} else {
			Type genericType = field.getGenericType();
			if (!(genericType instanceof ParameterizedType)) {
				return false;
			} else {
				Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
				return !WebElement.class.equals(listType) ? false
						: field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null
								|| field.getAnnotation(FindAll.class) != null;
			}
		}
	}

	protected <T> T proxyForLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
		InvocationHandler handler = new ControlHandler(interfaceType, locator);
		T proxy;
		proxy = interfaceType.cast(Proxy.newProxyInstance(loader,
				new Class[] { interfaceType, WebElement.class, WrapsElement.class, Locatable.class }, handler));
		return proxy;
	}

	protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
		InvocationHandler handler = new ControlListHandler(interfaceType, locator);
		List<T> proxy;
		proxy = (List<T>) Proxy.newProxyInstance(loader, new Class[] { List.class }, handler);
		return proxy;
	}
}
