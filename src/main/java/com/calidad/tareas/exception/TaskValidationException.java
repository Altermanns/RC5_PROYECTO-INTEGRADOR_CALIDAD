package com.calidad.tareas.exception;

/**
 * Excepción lanzada cuando los datos de una tarea no cumplen con las validaciones de negocio.
 */
public class TaskValidationException extends RuntimeException {
    /**
     * Crea una nueva excepción con el mensaje especificado.
     *
     * @param message el mensaje explicativo de la validación fallida.
     */
    public TaskValidationException(final String message) {
        super(message);
    }
}
