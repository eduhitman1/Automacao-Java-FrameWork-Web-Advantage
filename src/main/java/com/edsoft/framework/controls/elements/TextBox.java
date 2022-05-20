package com.edsoft.framework.controls.elements;

import com.edsoft.framework.controls.api.ImplementedBy;
import com.edsoft.framework.controls.internals.Control;

@ImplementedBy(TextBoxBase.class)
public interface TextBox extends Control {

	/**
	 * Interfacece de elemento de ação
	 */
	void EnterText(String text);

	String GetTextValue();

}
