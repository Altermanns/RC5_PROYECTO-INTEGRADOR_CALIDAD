package com.calidad.tareas.exception;

/**
 * Excepción lanzada cuando se intenta registrar una tarea con un título duplicado.
 */
public class DuplicateTaskException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción con el mensaje especificado.
     *
     * @param message el mensaje explicativo de la duplicidad.
     */
    public DuplicateTaskException(final String message) {
        super(message);
    }
}
