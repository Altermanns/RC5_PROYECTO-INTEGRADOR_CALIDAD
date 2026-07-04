package com.calidad.tareas.repository;

import com.calidad.tareas.model.TaskItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria del repositorio de tareas.
 */
public class InMemoryTaskRepository implements TaskRepository {
    private final List<TaskItem> tasks;

    /**
     * Constructor explícito que inicializa la lista de tareas.
     * Esto satisface las reglas de PMD AtLeastOneConstructor y evita UnnecessaryConstructor.
     */
    public InMemoryTaskRepository() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void add(final TaskItem taskItem) {
        if (taskItem == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        tasks.add(taskItem);
    }

    @Override
    public List<TaskItem> findAll() {
        return Collections.unmodifiableList(tasks);
    }

    @Override
    public Optional<TaskItem> findById(final int taskId) {
        return tasks.stream()
                .filter(task -> task.getTaskId() == taskId)
                .findFirst();
    }

    @Override
    public boolean existsByTitle(final String title) {
        boolean exists = false;
        if (title != null) {
            exists = tasks.stream()
                    .anyMatch(task -> task.getTitle().equalsIgnoreCase(title.trim()));
        }
        return exists;
    }

    @Override
    public void remove(final int taskId) {
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }
}
