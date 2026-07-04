package com.calidad.tareas.model;

import java.util.Locale;
import java.util.Objects;

/**
 * Representa una tarea (ítem de tarea) dentro del sistema de gestión de tareas.
 * Declarada como final para evitar ataques de finalizador en constructores (regla SpotBugs).
 */
public final class TaskItem {
    private final int taskId;
    private String title;
    private boolean completed;

    /**
     * Crea una nueva tarea con un identificador y un título.
     *
     * @param taskId el identificador único de la tarea.
     * @param title  el título descriptivo de la tarea.
     * @throws IllegalArgumentException si el título es nulo o está vacío.
     */
    public TaskItem(final int taskId, final String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        this.taskId = taskId;
        this.title = title.trim();
        this.completed = false;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Modifica el título de la tarea.
     *
     * @param title el nuevo título.
     * @throws IllegalArgumentException si el título es nulo o está vacío.
     */
    public void setTitle(final String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        this.title = title.trim();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(final boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(final Object other) {
        boolean isEqual = false;
        if (this == other) {
            isEqual = true;
        } else if (other != null && getClass() == other.getClass()) {
            final TaskItem taskItem = (TaskItem) other;
            isEqual = title.equalsIgnoreCase(taskItem.title);
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        // Se utiliza Locale.ROOT para evitar problemas de conversión no localizada (regla SpotBugs)
        return Objects.hash(title.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString() {
        return "Task #" + taskId + ": " + title + " [" + (completed ? "Completed" : "Pending") + "]";
    }
}
