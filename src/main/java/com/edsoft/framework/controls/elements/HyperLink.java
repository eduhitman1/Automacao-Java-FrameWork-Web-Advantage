package com.edsoft.framework.controls.elements;

import com.edsoft.framework.controls.api.ImplementedBy;
import com.edsoft.framework.controls.internals.Control;
import com.edsoft.framework.controls.internals.ControlBase;

@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control {

	/**
	 * Interfacece de elemento de ação
	 */
	void ClickLink();

	String GetUrlText();

	boolean CheckUrlTextContains(String containsText);

	ControlBase Wait();

	ControlBase WaitForVisible();

	ControlBase Click();
}
