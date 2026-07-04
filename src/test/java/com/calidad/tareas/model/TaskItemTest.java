package com.calidad.tareas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase de modelo TaskItem.
 */
class TaskItemTest {

    @Test
    void testConstructorSuccess() {
        final TaskItem task = new TaskItem(1, "Task Title");
        assertEquals(1, task.getTaskId());
        assertEquals("Task Title", task.getTitle());
        assertFalse(task.isCompleted());
    }

    @Test
    void testConstructorThrowsExceptionForNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem(1, null));
    }

    @Test
    void testConstructorThrowsExceptionForEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem(1, ""));
        assertThrows(IllegalArgumentException.class, () -> new TaskItem(1, "   "));
    }

    @Test
    void testSetTitleSuccess() {
        final TaskItem task = new TaskItem(1, "Old Title");
        task.setTitle("New Title");
        assertEquals("New Title", task.getTitle());
    }

    @Test
    void testSetTitleThrowsExceptionForNullTitle() {
        final TaskItem task = new TaskItem(1, "Title");
        assertThrows(IllegalArgumentException.class, () -> task.setTitle(null));
    }

    @Test
    void testSetTitleThrowsExceptionForEmptyTitle() {
        final TaskItem task = new TaskItem(1, "Title");
        assertThrows(IllegalArgumentException.class, () -> task.setTitle(""));
        assertThrows(IllegalArgumentException.class, () -> task.setTitle("   "));
    }

    @Test
    void testSetCompleted() {
        final TaskItem task = new TaskItem(1, "Title");
        assertFalse(task.isCompleted());
        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }

    @Test
    void testEqualsAndHashCode() {
        final TaskItem task1 = new TaskItem(1, "Title A");
        final TaskItem task2 = new TaskItem(2, "title a"); // Case insensitive
        final TaskItem task3 = new TaskItem(3, "Title B");

        // Reflexivo
        assertEquals(task1, task1);

        // Simétrico
        assertEquals(task1, task2);
        assertEquals(task2, task1);
        assertEquals(task1.hashCode(), task2.hashCode());

        // No equivalentes
        assertNotEquals(task1, task3);
        assertNotEquals(task1, null);
        assertNotEquals(task1, "string-object");
    }

    @Test
    void testToString() {
        final TaskItem task = new TaskItem(5, "Do laundry");
        assertEquals("Task #5: Do laundry [Pending]", task.toString());

        task.setCompleted(true);
        assertEquals("Task #5: Do laundry [Completed]", task.toString());
    }
}
