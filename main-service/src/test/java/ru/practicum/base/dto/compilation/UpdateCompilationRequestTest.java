package ru.practicum.base.dto.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UpdateCompilationRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateCompilationRequest#UpdateCompilationRequest()}
     *   <li>{@link UpdateCompilationRequest#setEvents(List)}
     *   <li>{@link UpdateCompilationRequest#setPinned(Boolean)}
     *   <li>{@link UpdateCompilationRequest#setTitle(String)}
     *   <li>{@link UpdateCompilationRequest#getEvents()}
     *   <li>{@link UpdateCompilationRequest#getPinned()}
     *   <li>{@link UpdateCompilationRequest#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UpdateCompilationRequest actualUpdateCompilationRequest = new UpdateCompilationRequest();
        ArrayList<Long> events = new ArrayList<>();
        actualUpdateCompilationRequest.setEvents(events);
        actualUpdateCompilationRequest.setPinned(true);
        actualUpdateCompilationRequest.setTitle("Dr");
        assertSame(events, actualUpdateCompilationRequest.getEvents());
        assertTrue(actualUpdateCompilationRequest.getPinned());
        assertEquals("Dr", actualUpdateCompilationRequest.getTitle());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateCompilationRequest#UpdateCompilationRequest(List, Boolean, String)}
     *   <li>{@link UpdateCompilationRequest#setEvents(List)}
     *   <li>{@link UpdateCompilationRequest#setPinned(Boolean)}
     *   <li>{@link UpdateCompilationRequest#setTitle(String)}
     *   <li>{@link UpdateCompilationRequest#getEvents()}
     *   <li>{@link UpdateCompilationRequest#getPinned()}
     *   <li>{@link UpdateCompilationRequest#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<Long> events = new ArrayList<>();
        UpdateCompilationRequest actualUpdateCompilationRequest = new UpdateCompilationRequest(events, true, "Dr");
        ArrayList<Long> events2 = new ArrayList<>();
        actualUpdateCompilationRequest.setEvents(events2);
        actualUpdateCompilationRequest.setPinned(true);
        actualUpdateCompilationRequest.setTitle("Dr");
        List<Long> events3 = actualUpdateCompilationRequest.getEvents();
        assertSame(events2, events3);
        assertEquals(events, events3);
        assertTrue(actualUpdateCompilationRequest.getPinned());
        assertEquals("Dr", actualUpdateCompilationRequest.getTitle());
    }
}

