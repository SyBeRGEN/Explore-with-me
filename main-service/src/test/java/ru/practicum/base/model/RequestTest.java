package ru.practicum.base.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

class RequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Request#Request()}
     *   <li>{@link Request#setCreated(LocalDateTime)}
     *   <li>{@link Request#setEvent(Event)}
     *   <li>{@link Request#setId(Long)}
     *   <li>{@link Request#setRequester(User)}
     *   <li>{@link Request#setStatus(Status)}
     *   <li>{@link Request#getCreated()}
     *   <li>{@link Request#getEvent()}
     *   <li>{@link Request#getId()}
     *   <li>{@link Request#getRequester()}
     *   <li>{@link Request#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Request actualRequest = new Request();
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualRequest.setCreated(ofResult);
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(user);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        actualRequest.setEvent(event);
        actualRequest.setId(1L);
        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(1L);
        user1.setName("Name");
        actualRequest.setRequester(user1);
        actualRequest.setStatus(Status.CONFIRMED);
        assertSame(ofResult, actualRequest.getCreated());
        assertSame(event, actualRequest.getEvent());
        assertEquals(1L, actualRequest.getId().longValue());
        assertSame(user1, actualRequest.getRequester());
        assertEquals(Status.CONFIRMED, actualRequest.getStatus());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Request#Request(Long, Event, User, LocalDateTime, Status)}
     *   <li>{@link Request#setCreated(LocalDateTime)}
     *   <li>{@link Request#setEvent(Event)}
     *   <li>{@link Request#setId(Long)}
     *   <li>{@link Request#setRequester(User)}
     *   <li>{@link Request#setStatus(Status)}
     *   <li>{@link Request#getCreated()}
     *   <li>{@link Request#getEvent()}
     *   <li>{@link Request#getId()}
     *   <li>{@link Request#getRequester()}
     *   <li>{@link Request#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(user);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(1L);
        user1.setName("Name");
        Request actualRequest = new Request(1L, event, user1, LocalDateTime.of(1, 1, 1, 1, 1), Status.CONFIRMED);
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualRequest.setCreated(ofResult);
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Name");
        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        Location location1 = new Location();
        location1.setLat(10.0f);
        location1.setLon(10.0f);
        Event event1 = new Event();
        event1.setAnnotation("Annotation");
        event1.setCategory(category1);
        event1.setConfirmedRequests(1L);
        event1.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDescription("The characteristics of someone or something");
        event1.setId(1L);
        event1.setInitiator(user2);
        event1.setLocation(location1);
        event1.setPaid(true);
        event1.setParticipantLimit(1L);
        event1.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setRequestModeration(true);
        event1.setState(State.PENDING);
        event1.setTitle("Dr");
        event1.setViews(1L);
        actualRequest.setEvent(event1);
        actualRequest.setId(1L);
        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setId(1L);
        user3.setName("Name");
        actualRequest.setRequester(user3);
        actualRequest.setStatus(Status.CONFIRMED);
        assertSame(ofResult, actualRequest.getCreated());
        assertSame(event1, actualRequest.getEvent());
        assertEquals(1L, actualRequest.getId().longValue());
        assertSame(user3, actualRequest.getRequester());
        assertEquals(Status.CONFIRMED, actualRequest.getStatus());
    }
}

