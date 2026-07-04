package com.calidad.tareas.repository;

import com.calidad.tareas.model.TaskItem;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso a datos de las tareas en el sistema.
 */
public interface TaskRepository {
    /**
     * Agrega una nueva tarea.
     *
     * @param taskItem la tarea a agregar.
     */
    void add(TaskItem taskItem);

    /**
     * Obtiene todas las tareas registradas.
     *
     * @return una lista inmutable de tareas.
     */
    List<TaskItem> findAll();

    /**
     * Busca una tarea por su identificador.
     *
     * @param taskId el identificador de la tarea.
     * @return un Optional conteniendo la tarea si existe, o vacío en caso contrario.
     */
    Optional<TaskItem> findById(int taskId);

    /**
     * Verifica si existe una tarea con el título especificado.
     *
     * @param title el título de la tarea a buscar.
     * @return true si existe una tarea con el mismo título, false en caso contrario.
     */
    boolean existsByTitle(String title);

    /**
     * Elimina una tarea por su identificador.
     *
     * @param taskId el identificador de la tarea a eliminar.
     */
    void remove(int taskId);
}
