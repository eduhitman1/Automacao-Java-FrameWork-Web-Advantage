package com.edsoft.framework.controls.elements;

import org.openqa.selenium.WebElement;

import com.edsoft.framework.controls.internals.ControlBase;

public class TextBoxBase extends ControlBase implements TextBox {

	/**
	 * Interface de elemento de ação Declarados 
	 * extends o controle de base e as açoes do elemento
	 */
	public TextBoxBase(WebElement element) {
		super(element);
	}

	@Override
	public void EnterText(String text) {
		getWrappedElement().sendKeys(text);
	}

	@Override
	public String GetTextValue() {
		return getWrappedElement().getText();
	}
}
