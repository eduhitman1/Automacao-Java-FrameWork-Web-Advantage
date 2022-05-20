package com.edsoft.framework.controls.internals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import com.edsoft.framework.controls.api.ImplementedBy;

@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {

	/**
	 * ações de elemento Base Control
	 */
	ControlBase Wait();

	ControlBase WaitForVisible();

	ControlBase Click();

	ControlBase ScrollToElement();

}
