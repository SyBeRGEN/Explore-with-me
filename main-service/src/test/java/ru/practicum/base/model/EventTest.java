package ru.practicum.base.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.utils.State;

class EventTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Event#Event()}
     *   <li>{@link Event#setAnnotation(String)}
     *   <li>{@link Event#setCategory(Category)}
     *   <li>{@link Event#setConfirmedRequests(Long)}
     *   <li>{@link Event#setCreatedOn(LocalDateTime)}
     *   <li>{@link Event#setDate(LocalDateTime)}
     *   <li>{@link Event#setDescription(String)}
     *   <li>{@link Event#setId(Long)}
     *   <li>{@link Event#setInitiator(User)}
     *   <li>{@link Event#setLocation(Location)}
     *   <li>{@link Event#setPaid(Boolean)}
     *   <li>{@link Event#setParticipantLimit(Long)}
     *   <li>{@link Event#setPublishedOn(LocalDateTime)}
     *   <li>{@link Event#setRequestModeration(Boolean)}
     *   <li>{@link Event#setState(State)}
     *   <li>{@link Event#setTitle(String)}
     *   <li>{@link Event#setViews(Long)}
     *   <li>{@link Event#getAnnotation()}
     *   <li>{@link Event#getCategory()}
     *   <li>{@link Event#getConfirmedRequests()}
     *   <li>{@link Event#getCreatedOn()}
     *   <li>{@link Event#getDate()}
     *   <li>{@link Event#getDescription()}
     *   <li>{@link Event#getId()}
     *   <li>{@link Event#getInitiator()}
     *   <li>{@link Event#getLocation()}
     *   <li>{@link Event#getPaid()}
     *   <li>{@link Event#getParticipantLimit()}
     *   <li>{@link Event#getPublishedOn()}
     *   <li>{@link Event#getRequestModeration()}
     *   <li>{@link Event#getState()}
     *   <li>{@link Event#getTitle()}
     *   <li>{@link Event#getViews()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Event actualEvent = new Event();
        actualEvent.setAnnotation("Annotation");
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        actualEvent.setCategory(category);
        actualEvent.setConfirmedRequests(1L);
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualEvent.setCreatedOn(ofResult);
        LocalDateTime ofResult1 = LocalDateTime.of(1, 1, 1, 1, 1);
        actualEvent.setDate(ofResult1);
        actualEvent.setDescription("The characteristics of someone or something");
        actualEvent.setId(1L);
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        actualEvent.setInitiator(user);
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        actualEvent.setLocation(location);
        actualEvent.setPaid(true);
        actualEvent.setParticipantLimit(1L);
        LocalDateTime ofResult2 = LocalDateTime.of(1, 1, 1, 1, 1);
        actualEvent.setPublishedOn(ofResult2);
        actualEvent.setRequestModeration(true);
        actualEvent.setState(State.PENDING);
        actualEvent.setTitle("Dr");
        actualEvent.setViews(1L);
        assertEquals("Annotation", actualEvent.getAnnotation());
        assertSame(category, actualEvent.getCategory());
        assertEquals(1L, actualEvent.getConfirmedRequests().longValue());
        assertSame(ofResult, actualEvent.getCreatedOn());
        assertSame(ofResult1, actualEvent.getDate());
        assertEquals("The characteristics of someone or something", actualEvent.getDescription());
        assertEquals(1L, actualEvent.getId().longValue());
        assertSame(user, actualEvent.getInitiator());
        assertSame(location, actualEvent.getLocation());
        assertTrue(actualEvent.getPaid());
        assertEquals(1L, actualEvent.getParticipantLimit().longValue());
        assertSame(ofResult2, actualEvent.getPublishedOn());
        assertTrue(actualEvent.getRequestModeration());
        assertEquals(State.PENDING, actualEvent.getState());
        assertEquals("Dr", actualEvent.getTitle());
        assertEquals(1L, actualEvent.getViews().longValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Event#Event(Long, String, Category, Long, LocalDateTime, String, LocalDateTime, User, Location, Boolean, Long, LocalDateTime, Boolean, State, String, Long)}
     *   <li>{@link Event#setAnnotation(String)}
     *   <li>{@link Event#setCategory(Category)}
     *   <li>{@link Event#setConfirmedRequests(Long)}
     *   <li>{@link Event#setCreatedOn(LocalDateTime)}
     *   <li>{@link Event#setDate(LocalDateTime)}
     *   <li>{@link Event#setDescription(String)}
     *   <li>{@link Event#setId(Long)}
     *   <li>{@link Event#setInitiator(User)}
     *   <li>{@link Event#setLocation(Location)}
     *   <li>{@link Event#setPaid(Boolean)}
     *   <li>{@link Event#setParticipantLimit(Long)}
     *   <li>{@link Event#setPublishedOn(LocalDateTime)}
     *   <li>{@link Event#setRequestModeration(Boolean)}
     *   <li>{@link Event#setState(State)}
     *   <li>{@link Event#setTitle(String)}
     *   <li>{@link Event#setViews(Long)}
     *   <li>{@link Event#getAnnotation()}
     *   <li>{@link Event#getCategory()}
     *   <li>{@link Event#getConfirmedRequests()}
     *   <li>{@link Event#getCreatedOn()}
     *   <li>{@link Event#getDate()}
     *   <li>{@link Event#getDescription()}
     *   <li>{@link Event#getId()}
     *   <li>{@link Event#getInitiator()}
     *   <li>{@link Event#getLocation()}
     *   <li>{@link Event#getPaid()}
     *   <li>{@link Event#getParticipantLimit()}
     *   <li>{@link Event#getPublishedOn()}
     *   <li>{@link Event#getRequestModeration()}
     *   <li>{@link Event#getState()}
     *   <li>{@link Event#getTitle()}
     *   <li>{@link Event#getViews()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        LocalDateTime createdOn = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime date = LocalDateTime.of(1, 1, 1, 1, 1);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        Event actualEvent = new Event(1L, "Annotation", category, 1L, createdOn,
                "The characteristics of someone or something", date, user, location, true, 1L,
                LocalDateTime.of(1, 1, 1, 1, 1), true, State.PENDING, "Dr", 1L);
        actualEvent.setAnnotation("Annotation");
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Name");
        actualEvent.setCategory(category1);
        actualEvent.setConfirmedRequests(1L);
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualEvent.setCreatedOn(ofResult);
        LocalDateTime ofResult1 = LocalDateTime.of(1, 1, 1, 1, 1);
        actualEvent.setDate(ofResult1);
        actualEvent.setDescription("The characteristics of someone or something");
        actualEvent.setId(1L);
        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(1L);
        user1.setName("Name");
        actualEvent.setInitiator(user1);
        Location location1 = new Location();
        location1.setLat(10.0f);
        location1.setLon(10.0f);
        actualEvent.setLocation(location1);
        actualEvent.setPaid(true);
        actualEvent.setParticipantLimit(1L);
        LocalDateTime ofResult2 = LocalDateTime.of(1, 1, 1, 1, 1);
        actualEvent.setPublishedOn(ofResult2);
        actualEvent.setRequestModeration(true);
        actualEvent.setState(State.PENDING);
        actualEvent.setTitle("Dr");
        actualEvent.setViews(1L);
        assertEquals("Annotation", actualEvent.getAnnotation());
        assertSame(category1, actualEvent.getCategory());
        assertEquals(1L, actualEvent.getConfirmedRequests().longValue());
        assertSame(ofResult, actualEvent.getCreatedOn());
        assertSame(ofResult1, actualEvent.getDate());
        assertEquals("The characteristics of someone or something", actualEvent.getDescription());
        assertEquals(1L, actualEvent.getId().longValue());
        assertSame(user1, actualEvent.getInitiator());
        assertSame(location1, actualEvent.getLocation());
        assertTrue(actualEvent.getPaid());
        assertEquals(1L, actualEvent.getParticipantLimit().longValue());
        assertSame(ofResult2, actualEvent.getPublishedOn());
        assertTrue(actualEvent.getRequestModeration());
        assertEquals(State.PENDING, actualEvent.getState());
        assertEquals("Dr", actualEvent.getTitle());
        assertEquals(1L, actualEvent.getViews().longValue());
    }
}

