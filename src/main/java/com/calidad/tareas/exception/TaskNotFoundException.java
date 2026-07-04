package com.calidad.tareas.exception;

/**
 * Excepción lanzada cuando no se encuentra una tarea con el ID especificado.
 */
public class TaskNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción con el mensaje especificado.
     *
     * @param message el mensaje explicativo del error.
     */
    public TaskNotFoundException(final String message) {
        super(message);
    }
}
