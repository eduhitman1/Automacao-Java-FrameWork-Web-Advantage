package com.edsoft.framework.controls.internals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;

import com.edsoft.framework.base.web.DriverContextWeb;

public class ControlBase implements Control {

	private final WebElement element;

	/**
	 * instancia do webelement 
	 */
	
	public ControlBase(final WebElement element) {
		this.element = element;
	}

	@Override
	public void click() {
		element.click();
	}

	@Override
	public void submit() {
		element.submit();
	}

	@Override
	public void sendKeys(CharSequence... charSequences) {
		element.sendKeys(charSequences);
	}

	@Override
	public void clear() {
		element.clear();
	}

	@Override
	public String getTagName() {
		return element.getTagName();
	}

	@Override
	public String getAttribute(String s) {
		return element.getAttribute(s);
	}

	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public String getText() {
		return element.getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return element.findElement(by);
	}

	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return null;
	}

	@Override
	public Dimension getSize() {
		return null;
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	@Override
	public String getCssValue(String s) {
		return null;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
		return null;
	}

	@Override
	public Coordinates getCoordinates() {
		return null;
	}

	@Override
	public WebElement getWrappedElement() {
		return element;
	}

	@Override
	public ControlBase Wait() {
		DriverContextWeb.WaitForPageToLoad();
		return this;
	}

	@Override
	public ControlBase WaitForVisible() {
		DriverContextWeb.WaitForElementVisible(getWrappedElement());
		return this;
	}

	@Override
	public ControlBase Click() {
		getWrappedElement().click();
		return this;
	}

	@Override
	public ControlBase ScrollToElement() {
		return this;
	}

}
