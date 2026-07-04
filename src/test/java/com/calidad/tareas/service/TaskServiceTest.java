package com.calidad.tareas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.calidad.tareas.exception.DuplicateTaskException;
import com.calidad.tareas.exception.TaskNotFoundException;
import com.calidad.tareas.exception.TaskValidationException;
import com.calidad.tareas.model.TaskItem;
import com.calidad.tareas.repository.InMemoryTaskRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para el servicio TaskService.
 */
class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(new InMemoryTaskRepository());
    }

    @Test
    void testConstructorWithNullRepository() {
        assertThrows(IllegalArgumentException.class, () -> new TaskService(null));
    }

    @Test
    void testCreateTaskSuccess() {
        final TaskItem task = taskService.createTask("Hacer la tarea");
        assertNotNull(task);
        assertEquals(1, task.getTaskId());
        assertEquals("Hacer la tarea", task.getTitle());
        assertFalse(task.isCompleted());
    }

    @Test
    void testCreateTaskTrimsTitle() {
        final TaskItem task = taskService.createTask("   Hacer la tarea con espacios   ");
        assertEquals("Hacer la tarea con espacios", task.getTitle());
    }

    @Test
    void testCreateTaskThrowsValidationExceptionForNull() {
        final TaskValidationException exception = assertThrows(
                TaskValidationException.class,
                () -> taskService.createTask(null)
        );
        assertEquals("Task title cannot be empty", exception.getMessage());
    }

    @Test
    void testCreateTaskThrowsValidationExceptionForEmpty() {
        assertThrows(TaskValidationException.class, () -> taskService.createTask(""));
        assertThrows(TaskValidationException.class, () -> taskService.createTask("   "));
    }

    @Test
    void testCreateTaskThrowsDuplicateException() {
        taskService.createTask("Estudiar");
        final DuplicateTaskException exception = assertThrows(
                DuplicateTaskException.class,
                () -> taskService.createTask("estudiar")
        );
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testGetAllTasksInitiallyEmpty() {
        final List<TaskItem> tasks = taskService.getAllTasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void testGetAllTasksReturnsAddedTasks() {
        taskService.createTask("Task 1");
        taskService.createTask("Task 2");
        final List<TaskItem> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    void testCompleteTaskSuccess() {
        final TaskItem task = taskService.createTask("Completar informe");
        assertFalse(task.isCompleted());
        
        taskService.completeTask(task.getTaskId());
        assertTrue(task.isCompleted());
    }

    @Test
    void testCompleteTaskThrowsNotFoundException() {
        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.completeTask(999)
        );
    }

    @Test
    void testDeleteTaskSuccess() {
        final TaskItem task = taskService.createTask("Eliminar esta tarea");
        assertEquals(1, taskService.getAllTasks().size());

        taskService.deleteTask(task.getTaskId());
        assertEquals(0, taskService.getAllTasks().size());
    }

    @Test
    void testDeleteTaskThrowsNotFoundException() {
        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.deleteTask(999)
        );
    }
}
