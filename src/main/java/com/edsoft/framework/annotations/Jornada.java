package com.edsoft.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.edsoft.framework.constantes.Jornadas;

/**
 * Jornada para aponta o que cada jornada deve seguir
 * @Interface
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Jornada {
	Jornadas value();
}
