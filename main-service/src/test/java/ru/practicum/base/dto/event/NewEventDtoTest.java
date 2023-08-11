package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.location.Location;

class NewEventDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NewEventDto#NewEventDto(String, Long, String, LocalDateTime, Location, Boolean, Long, Boolean, String)}
     *   <li>{@link NewEventDto#setAnnotation(String)}
     *   <li>{@link NewEventDto#setCategory(Long)}
     *   <li>{@link NewEventDto#setDescription(String)}
     *   <li>{@link NewEventDto#setEventDate(LocalDateTime)}
     *   <li>{@link NewEventDto#setLocation(Location)}
     *   <li>{@link NewEventDto#setPaid(Boolean)}
     *   <li>{@link NewEventDto#setParticipantLimit(Long)}
     *   <li>{@link NewEventDto#setRequestModeration(Boolean)}
     *   <li>{@link NewEventDto#setTitle(String)}
     *   <li>{@link NewEventDto#getAnnotation()}
     *   <li>{@link NewEventDto#getCategory()}
     *   <li>{@link NewEventDto#getDescription()}
     *   <li>{@link NewEventDto#getEventDate()}
     *   <li>{@link NewEventDto#getLocation()}
     *   <li>{@link NewEventDto#getPaid()}
     *   <li>{@link NewEventDto#getParticipantLimit()}
     *   <li>{@link NewEventDto#getRequestModeration()}
     *   <li>{@link NewEventDto#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        NewEventDto actualNewEventDto = new NewEventDto("Annotation", 1L, "The characteristics of someone or something",
                eventDate, location, true, 1L, true, "Dr");
        actualNewEventDto.setAnnotation("Annotation");
        actualNewEventDto.setCategory(1L);
        actualNewEventDto.setDescription("The characteristics of someone or something");
        LocalDateTime eventDate2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualNewEventDto.setEventDate(eventDate2);
        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);
        actualNewEventDto.setLocation(location2);
        actualNewEventDto.setPaid(true);
        actualNewEventDto.setParticipantLimit(1L);
        actualNewEventDto.setRequestModeration(true);
        actualNewEventDto.setTitle("Dr");
        assertEquals("Annotation", actualNewEventDto.getAnnotation());
        assertEquals(1L, actualNewEventDto.getCategory().longValue());
        assertEquals("The characteristics of someone or something", actualNewEventDto.getDescription());
        assertSame(eventDate2, actualNewEventDto.getEventDate());
        assertSame(location2, actualNewEventDto.getLocation());
        assertTrue(actualNewEventDto.getPaid());
        assertEquals(1L, actualNewEventDto.getParticipantLimit().longValue());
        assertTrue(actualNewEventDto.getRequestModeration());
        assertEquals("Dr", actualNewEventDto.getTitle());
    }

    /**
     * Method under test: {@link NewEventDto#NewEventDto()}
     */
    @Test
    void testConstructor2() {
        NewEventDto actualNewEventDto = new NewEventDto();
        assertTrue(actualNewEventDto.getRequestModeration());
        assertEquals(0L, actualNewEventDto.getParticipantLimit().longValue());
        assertFalse(actualNewEventDto.getPaid());
    }
}

