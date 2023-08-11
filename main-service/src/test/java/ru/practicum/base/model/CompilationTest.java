package ru.practicum.base.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CompilationTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Compilation#Compilation()}
     *   <li>{@link Compilation#setEvents(List)}
     *   <li>{@link Compilation#setId(Long)}
     *   <li>{@link Compilation#setPinned(Boolean)}
     *   <li>{@link Compilation#setTitle(String)}
     *   <li>{@link Compilation#getEvents()}
     *   <li>{@link Compilation#getId()}
     *   <li>{@link Compilation#getPinned()}
     *   <li>{@link Compilation#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Compilation actualCompilation = new Compilation();
        ArrayList<Event> eventList = new ArrayList<>();
        actualCompilation.setEvents(eventList);
        actualCompilation.setId(1L);
        actualCompilation.setPinned(true);
        actualCompilation.setTitle("Dr");
        assertSame(eventList, actualCompilation.getEvents());
        assertEquals(1L, actualCompilation.getId().longValue());
        assertTrue(actualCompilation.getPinned());
        assertEquals("Dr", actualCompilation.getTitle());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Compilation#Compilation(Long, String, Boolean, List)}
     *   <li>{@link Compilation#setEvents(List)}
     *   <li>{@link Compilation#setId(Long)}
     *   <li>{@link Compilation#setPinned(Boolean)}
     *   <li>{@link Compilation#setTitle(String)}
     *   <li>{@link Compilation#getEvents()}
     *   <li>{@link Compilation#getId()}
     *   <li>{@link Compilation#getPinned()}
     *   <li>{@link Compilation#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<Event> eventList = new ArrayList<>();
        Compilation actualCompilation = new Compilation(1L, "Dr", true, eventList);
        ArrayList<Event> eventList1 = new ArrayList<>();
        actualCompilation.setEvents(eventList1);
        actualCompilation.setId(1L);
        actualCompilation.setPinned(true);
        actualCompilation.setTitle("Dr");
        List<Event> events = actualCompilation.getEvents();
        assertSame(eventList1, events);
        assertEquals(eventList, events);
        assertEquals(1L, actualCompilation.getId().longValue());
        assertTrue(actualCompilation.getPinned());
        assertEquals("Dr", actualCompilation.getTitle());
    }
}

