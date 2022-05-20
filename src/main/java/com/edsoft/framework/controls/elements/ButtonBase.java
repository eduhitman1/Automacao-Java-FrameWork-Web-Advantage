package com.edsoft.framework.controls.elements;

import org.openqa.selenium.WebElement;

import com.edsoft.framework.controls.internals.ControlBase;

public class ButtonBase extends ControlBase implements Button {
	
	/**
	 * Interface de elemento de ação Declarados 
	 * extends o controle de base e as açoes do elemento
	 */
	public ButtonBase(WebElement element) {
		super(element);
	}

	@Override
	public void PerformClick() {
		getWrappedElement().click();
	}

	@Override
	public String GetButtonText() {
		return getWrappedElement().getText();
	}

	@Override
	public void PerformSubmit() {
		getWrappedElement().submit();
	}

}
