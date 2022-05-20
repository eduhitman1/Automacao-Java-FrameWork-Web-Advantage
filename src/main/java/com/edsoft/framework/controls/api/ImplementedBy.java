package com.edsoft.framework.controls.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.edsoft.framework.controls.internals.ControlBase;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementedBy {
	/**
	 * Interface de ImplementedBy
	 */
	Class<?> value() default ControlBase.class;

}
