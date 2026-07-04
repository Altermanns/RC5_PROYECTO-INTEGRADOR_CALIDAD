package com.calidad.tareas.service;

import com.calidad.tareas.annotation.SuppressFBWarnings;
import com.calidad.tareas.exception.DuplicateTaskException;
import com.calidad.tareas.exception.TaskNotFoundException;
import com.calidad.tareas.exception.TaskValidationException;
import com.calidad.tareas.model.TaskItem;
import com.calidad.tareas.repository.TaskRepository;
import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio asociada a las tareas.
 * Declarada como final para evitar vulnerabilidades de inicialización en el constructor (SpotBugs).
 */
public final class TaskService {
    private final TaskRepository repository;
    private int currentId = 1;

    /**
     * Crea una instancia de TaskService inyectando su repositorio.
     * Silenciamos la advertencia EI_EXPOSE_REP2 de SpotBugs ya que en Dependency Injection
     * la mutabilidad del repositorio inyectado es compartida intencionalmente.
     *
     * @param repository el repositorio donde se almacenarán las tareas.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Dependency Injection compartida de forma intencional")
    public TaskService(final TaskRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    /**
     * Crea e introduce una nueva tarea en el sistema.
     *
     * @param title el título de la tarea.
     * @return la tarea creada.
     * @throws TaskValidationException si el título es nulo o vacío.
     * @throws DuplicateTaskException si ya existe una tarea con el mismo título.
     */
    public TaskItem createTask(final String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new TaskValidationException("Task title cannot be empty");
        }

        final String cleanTitle = title.trim();
        if (repository.existsByTitle(cleanTitle)) {
            throw new DuplicateTaskException("Task with title '" + cleanTitle + "' already exists");
        }

        final TaskItem taskItem = new TaskItem(currentId++, cleanTitle);
        repository.add(taskItem);
        return taskItem;
    }

    /**
     * Retorna todas las tareas registradas en el sistema.
     *
     * @return lista de tareas.
     */
    public List<TaskItem> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Marca una tarea como completada.
     *
     * @param taskId el identificador de la tarea.
     * @throws TaskNotFoundException si la tarea no existe.
     */
    public void completeTask(final int taskId) {
        final TaskItem taskItem = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + taskId + " not found"));
        taskItem.setCompleted(true);
    }

    /**
     * Elimina una tarea del sistema.
     *
     * @param taskId el identificador de la tarea.
     * @throws TaskNotFoundException si la tarea no existe.
     */
    public void deleteTask(final int taskId) {
        if (repository.findById(taskId).isEmpty()) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found");
        }
        repository.remove(taskId);
    }
}
