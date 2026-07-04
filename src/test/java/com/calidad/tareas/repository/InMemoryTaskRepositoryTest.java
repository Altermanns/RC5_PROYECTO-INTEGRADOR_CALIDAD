package com.calidad.tareas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.calidad.tareas.model.TaskItem;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para InMemoryTaskRepository.
 */
class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void testAddNullTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.add(null));
    }

    @Test
    void testAddAndFindAll() {
        final TaskItem task = new TaskItem(1, "Task 1");
        repository.add(task);
        assertEquals(1, repository.findAll().size());
        assertEquals(task, repository.findAll().get(0));
    }

    @Test
    void testFindById() {
        final TaskItem task1 = new TaskItem(1, "Task 1");
        final TaskItem task2 = new TaskItem(2, "Task 2");
        repository.add(task1);
        repository.add(task2);

        final Optional<TaskItem> found = repository.findById(1);
        assertTrue(found.isPresent());
        assertEquals("Task 1", found.get().getTitle());

        final Optional<TaskItem> notFound = repository.findById(999);
        assertFalse(notFound.isPresent());
    }

    @Test
    void testExistsByTitle() {
        final TaskItem task = new TaskItem(1, "Estudiar Calidad");
        repository.add(task);

        assertTrue(repository.existsByTitle("Estudiar Calidad"));
        assertTrue(repository.existsByTitle("  estudiar calidad  "));
        assertFalse(repository.existsByTitle("Estudiar Programación"));
        assertFalse(repository.existsByTitle(null));
    }

    @Test
    void testRemove() {
        final TaskItem task1 = new TaskItem(1, "Task 1");
        repository.add(task1);
        assertEquals(1, repository.findAll().size());

        repository.remove(1);
        assertTrue(repository.findAll().isEmpty());
    }
}
