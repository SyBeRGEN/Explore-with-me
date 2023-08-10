package ru.practicum.base.dto.compilation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.event.EventShortDto;

class CompilationDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompilationDto#CompilationDto()}
     *   <li>{@link CompilationDto#setEvents(List)}
     *   <li>{@link CompilationDto#setId(Long)}
     *   <li>{@link CompilationDto#setPinned(Boolean)}
     *   <li>{@link CompilationDto#setTitle(String)}
     *   <li>{@link CompilationDto#getEvents()}
     *   <li>{@link CompilationDto#getId()}
     *   <li>{@link CompilationDto#getPinned()}
     *   <li>{@link CompilationDto#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CompilationDto actualCompilationDto = new CompilationDto();
        ArrayList<EventShortDto> events = new ArrayList<>();
        actualCompilationDto.setEvents(events);
        actualCompilationDto.setId(1L);
        actualCompilationDto.setPinned(true);
        actualCompilationDto.setTitle("Dr");
        assertSame(events, actualCompilationDto.getEvents());
        assertEquals(1L, actualCompilationDto.getId().longValue());
        assertTrue(actualCompilationDto.getPinned());
        assertEquals("Dr", actualCompilationDto.getTitle());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompilationDto#CompilationDto(List, Long, Boolean, String)}
     *   <li>{@link CompilationDto#setEvents(List)}
     *   <li>{@link CompilationDto#setId(Long)}
     *   <li>{@link CompilationDto#setPinned(Boolean)}
     *   <li>{@link CompilationDto#setTitle(String)}
     *   <li>{@link CompilationDto#getEvents()}
     *   <li>{@link CompilationDto#getId()}
     *   <li>{@link CompilationDto#getPinned()}
     *   <li>{@link CompilationDto#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<EventShortDto> events = new ArrayList<>();
        CompilationDto actualCompilationDto = new CompilationDto(events, 1L, true, "Dr");
        ArrayList<EventShortDto> events2 = new ArrayList<>();
        actualCompilationDto.setEvents(events2);
        actualCompilationDto.setId(1L);
        actualCompilationDto.setPinned(true);
        actualCompilationDto.setTitle("Dr");
        List<EventShortDto> events3 = actualCompilationDto.getEvents();
        assertSame(events2, events3);
        assertEquals(events, events3);
        assertEquals(1L, actualCompilationDto.getId().longValue());
        assertTrue(actualCompilationDto.getPinned());
        assertEquals("Dr", actualCompilationDto.getTitle());
    }
}

