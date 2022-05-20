package com.edsoft.framework.controls.elements;

import com.edsoft.framework.controls.api.ImplementedBy;
import com.edsoft.framework.controls.internals.Control;
import com.edsoft.framework.controls.internals.ControlBase;

@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

	/**
	 * Interfacece de elemento de ação
	 */
	void PerformClick();

	String GetButtonText();

	void PerformSubmit();

	ControlBase Wait();

	ControlBase WaitForVisible();

	ControlBase Click();

	ControlBase ScrollToElement();

}
