package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.location.Location;

class UpdateEventSuperRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEventSuperRequest#UpdateEventSuperRequest()}
     *   <li>{@link UpdateEventSuperRequest#setAnnotation(String)}
     *   <li>{@link UpdateEventSuperRequest#setCategory(Long)}
     *   <li>{@link UpdateEventSuperRequest#setDescription(String)}
     *   <li>{@link UpdateEventSuperRequest#setEventDate(LocalDateTime)}
     *   <li>{@link UpdateEventSuperRequest#setLocation(Location)}
     *   <li>{@link UpdateEventSuperRequest#setPaid(Boolean)}
     *   <li>{@link UpdateEventSuperRequest#setParticipantLimit(Long)}
     *   <li>{@link UpdateEventSuperRequest#setRequestModeration(Boolean)}
     *   <li>{@link UpdateEventSuperRequest#setTitle(String)}
     *   <li>{@link UpdateEventSuperRequest#getAnnotation()}
     *   <li>{@link UpdateEventSuperRequest#getCategory()}
     *   <li>{@link UpdateEventSuperRequest#getDescription()}
     *   <li>{@link UpdateEventSuperRequest#getEventDate()}
     *   <li>{@link UpdateEventSuperRequest#getLocation()}
     *   <li>{@link UpdateEventSuperRequest#getPaid()}
     *   <li>{@link UpdateEventSuperRequest#getParticipantLimit()}
     *   <li>{@link UpdateEventSuperRequest#getRequestModeration()}
     *   <li>{@link UpdateEventSuperRequest#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UpdateEventSuperRequest actualUpdateEventSuperRequest = new UpdateEventSuperRequest();
        actualUpdateEventSuperRequest.setAnnotation("Annotation");
        actualUpdateEventSuperRequest.setCategory(1L);
        actualUpdateEventSuperRequest.setDescription("The characteristics of someone or something");
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualUpdateEventSuperRequest.setEventDate(eventDate);
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        actualUpdateEventSuperRequest.setLocation(location);
        actualUpdateEventSuperRequest.setPaid(true);
        actualUpdateEventSuperRequest.setParticipantLimit(1L);
        actualUpdateEventSuperRequest.setRequestModeration(true);
        actualUpdateEventSuperRequest.setTitle("Dr");
        assertEquals("Annotation", actualUpdateEventSuperRequest.getAnnotation());
        assertEquals(1L, actualUpdateEventSuperRequest.getCategory().longValue());
        assertEquals("The characteristics of someone or something", actualUpdateEventSuperRequest.getDescription());
        assertSame(eventDate, actualUpdateEventSuperRequest.getEventDate());
        assertSame(location, actualUpdateEventSuperRequest.getLocation());
        assertTrue(actualUpdateEventSuperRequest.getPaid());
        assertEquals(1L, actualUpdateEventSuperRequest.getParticipantLimit().longValue());
        assertTrue(actualUpdateEventSuperRequest.getRequestModeration());
        assertEquals("Dr", actualUpdateEventSuperRequest.getTitle());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEventSuperRequest#UpdateEventSuperRequest(String, Long, String, LocalDateTime, Location, Boolean, Long, Boolean, String)}
     *   <li>{@link UpdateEventSuperRequest#setAnnotation(String)}
     *   <li>{@link UpdateEventSuperRequest#setCategory(Long)}
     *   <li>{@link UpdateEventSuperRequest#setDescription(String)}
     *   <li>{@link UpdateEventSuperRequest#setEventDate(LocalDateTime)}
     *   <li>{@link UpdateEventSuperRequest#setLocation(Location)}
     *   <li>{@link UpdateEventSuperRequest#setPaid(Boolean)}
     *   <li>{@link UpdateEventSuperRequest#setParticipantLimit(Long)}
     *   <li>{@link UpdateEventSuperRequest#setRequestModeration(Boolean)}
     *   <li>{@link UpdateEventSuperRequest#setTitle(String)}
     *   <li>{@link UpdateEventSuperRequest#getAnnotation()}
     *   <li>{@link UpdateEventSuperRequest#getCategory()}
     *   <li>{@link UpdateEventSuperRequest#getDescription()}
     *   <li>{@link UpdateEventSuperRequest#getEventDate()}
     *   <li>{@link UpdateEventSuperRequest#getLocation()}
     *   <li>{@link UpdateEventSuperRequest#getPaid()}
     *   <li>{@link UpdateEventSuperRequest#getParticipantLimit()}
     *   <li>{@link UpdateEventSuperRequest#getRequestModeration()}
     *   <li>{@link UpdateEventSuperRequest#getTitle()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        UpdateEventSuperRequest actualUpdateEventSuperRequest = new UpdateEventSuperRequest("Annotation", 1L,
                "The characteristics of someone or something", eventDate, location, true, 1L, true, "Dr");
        actualUpdateEventSuperRequest.setAnnotation("Annotation");
        actualUpdateEventSuperRequest.setCategory(1L);
        actualUpdateEventSuperRequest.setDescription("The characteristics of someone or something");
        LocalDateTime eventDate2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualUpdateEventSuperRequest.setEventDate(eventDate2);
        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);
        actualUpdateEventSuperRequest.setLocation(location2);
        actualUpdateEventSuperRequest.setPaid(true);
        actualUpdateEventSuperRequest.setParticipantLimit(1L);
        actualUpdateEventSuperRequest.setRequestModeration(true);
        actualUpdateEventSuperRequest.setTitle("Dr");
        assertEquals("Annotation", actualUpdateEventSuperRequest.getAnnotation());
        assertEquals(1L, actualUpdateEventSuperRequest.getCategory().longValue());
        assertEquals("The characteristics of someone or something", actualUpdateEventSuperRequest.getDescription());
        assertSame(eventDate2, actualUpdateEventSuperRequest.getEventDate());
        assertSame(location2, actualUpdateEventSuperRequest.getLocation());
        assertTrue(actualUpdateEventSuperRequest.getPaid());
        assertEquals(1L, actualUpdateEventSuperRequest.getParticipantLimit().longValue());
        assertTrue(actualUpdateEventSuperRequest.getRequestModeration());
        assertEquals("Dr", actualUpdateEventSuperRequest.getTitle());
    }
}

