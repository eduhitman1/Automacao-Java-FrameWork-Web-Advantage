package com.edsoft.framework.controls.api;

import com.edsoft.framework.controls.internals.Control;

public final class ImplementedByProcessor {

	public ImplementedByProcessor() {
	}

	public static <T> Class<?> getWrapperClass(Class<T> iface) {
		if (iface.isAnnotationPresent(ImplementedBy.class)) {
			ImplementedBy annotation = iface.getAnnotation(ImplementedBy.class);
			Class<?> clazz = annotation.value();
			if (Control.class.isAssignableFrom(clazz)) {
				return annotation.value();
			}
		}
		throw new UnsupportedOperationException("Apply @ImplementedBy interface to your Interface "
				+ iface.getCanonicalName() + " se você quiser estender ");
	}
}
