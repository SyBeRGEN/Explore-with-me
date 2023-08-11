package ru.practicum.base.dto.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class NewCompilationDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NewCompilationDto#NewCompilationDto(List, Boolean, String)}
     *   <li>{@link NewCompilationDto#setEvents(List)}
     *   <li>{@link NewCompilationDto#setPinned(Boolean)}
     *   <li>{@link NewCompilationDto#setTitle(String)}
     *   <li>{@link NewCompilationDto#getEvents()}
     *   <li>{@link NewCompilationDto#getPinned()}
     *   <li>{@link NewCompilationDto#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ArrayList<Long> events = new ArrayList<>();
        NewCompilationDto actualNewCompilationDto = new NewCompilationDto(events, true, "Dr");
        ArrayList<Long> events2 = new ArrayList<>();
        actualNewCompilationDto.setEvents(events2);
        actualNewCompilationDto.setPinned(true);
        actualNewCompilationDto.setTitle("Dr");
        List<Long> events3 = actualNewCompilationDto.getEvents();
        assertSame(events2, events3);
        assertEquals(events, events3);
        assertTrue(actualNewCompilationDto.getPinned());
        assertEquals("Dr", actualNewCompilationDto.getTitle());
    }

    /**
     * Method under test: {@link NewCompilationDto#NewCompilationDto()}
     */
    @Test
    void testConstructor2() {
        assertFalse((new NewCompilationDto()).getPinned());
    }
}

