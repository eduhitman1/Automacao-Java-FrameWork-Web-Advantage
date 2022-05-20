package com.edsoft.framework.controls.elements;

import org.openqa.selenium.WebElement;

import com.edsoft.framework.controls.internals.ControlBase;

public class HyperLinkBase extends ControlBase implements HyperLink {

	/**
	 * Interface de elemento de ação Declarados 
	 * extends o controle de base e as açoes do elemento
	 */
	public HyperLinkBase(WebElement element) {
		super(element);
	}

	@Override
	public void ClickLink() {
		getWrappedElement().getText();
	}

	@Override
	public String GetUrlText() {
		return getWrappedElement().getText();
	}

	@Override
	public boolean CheckUrlTextContains(String containsText) {
		if (getWrappedElement().getText().contains(containsText))
			return true;
		else
			return false;
	}
}
