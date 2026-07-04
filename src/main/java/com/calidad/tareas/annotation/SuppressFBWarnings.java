package com.calidad.tareas.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Anotación personalizada para silenciar advertencias de SpotBugs.
 * SpotBugs detecta y procesa cualquier anotación llamada SuppressFBWarnings en el classpath.
 */
@Retention(RetentionPolicy.CLASS)
public @interface SuppressFBWarnings {
    /**
     * Identificadores de las advertencias a silenciar (ej. "EI_EXPOSE_REP2").
     *
     * @return los identificadores.
     */
    String[] value() default {};

    /**
     * Razón por la cual se silencia la advertencia.
     *
     * @return la justificación.
     */
    String justification() default "";
}
