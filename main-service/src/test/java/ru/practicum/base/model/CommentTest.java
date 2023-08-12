package ru.practicum.base.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

class CommentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Comment#Comment()}
     *   <li>{@link Comment#setCommentator(User)}
     *   <li>{@link Comment#setCreated(LocalDateTime)}
     *   <li>{@link Comment#setEvent(Event)}
     *   <li>{@link Comment#setId(Long)}
     *   <li>{@link Comment#setStatus(Status)}
     *   <li>{@link Comment#setText(String)}
     *   <li>{@link Comment#getCommentator()}
     *   <li>{@link Comment#getCreated()}
     *   <li>{@link Comment#getEvent()}
     *   <li>{@link Comment#getId()}
     *   <li>{@link Comment#getStatus()}
     *   <li>{@link Comment#getText()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Comment actualComment = new Comment();
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");
        actualComment.setCommentator(commentator);
        LocalDateTime created = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualComment.setCreated(created);
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        actualComment.setEvent(event);
        actualComment.setId(1L);
        actualComment.setStatus(Status.CONFIRMED);
        actualComment.setText("Text");
        assertSame(commentator, actualComment.getCommentator());
        assertSame(created, actualComment.getCreated());
        assertSame(event, actualComment.getEvent());
        assertEquals(1L, actualComment.getId().longValue());
        assertEquals(Status.CONFIRMED, actualComment.getStatus());
        assertEquals("Text", actualComment.getText());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Comment#Comment(Long, String, LocalDateTime, Event, User, Status)}
     *   <li>{@link Comment#setCommentator(User)}
     *   <li>{@link Comment#setCreated(LocalDateTime)}
     *   <li>{@link Comment#setEvent(Event)}
     *   <li>{@link Comment#setId(Long)}
     *   <li>{@link Comment#setStatus(Status)}
     *   <li>{@link Comment#setText(String)}
     *   <li>{@link Comment#getCommentator()}
     *   <li>{@link Comment#getCreated()}
     *   <li>{@link Comment#getEvent()}
     *   <li>{@link Comment#getId()}
     *   <li>{@link Comment#getStatus()}
     *   <li>{@link Comment#getText()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        LocalDateTime created = LocalDate.of(1970, 1, 1).atStartOfDay();

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");
        Comment actualComment = new Comment(1L, "Text", created, event, commentator, Status.CONFIRMED);
        User commentator2 = new User();
        commentator2.setEmail("jane.doe@example.org");
        commentator2.setId(1L);
        commentator2.setName("Name");
        actualComment.setCommentator(commentator2);
        LocalDateTime created2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualComment.setCreated(created2);
        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        User initiator2 = new User();
        initiator2.setEmail("jane.doe@example.org");
        initiator2.setId(1L);
        initiator2.setName("Name");
        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);
        Event event2 = new Event();
        event2.setAnnotation("Annotation");
        event2.setCategory(category2);
        event2.setConfirmedRequests(1L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("The characteristics of someone or something");
        event2.setId(1L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(true);
        event2.setParticipantLimit(1L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(true);
        event2.setState(State.PENDING);
        event2.setTitle("Dr");
        event2.setViews(1L);
        actualComment.setEvent(event2);
        actualComment.setId(1L);
        actualComment.setStatus(Status.CONFIRMED);
        actualComment.setText("Text");
        assertSame(commentator2, actualComment.getCommentator());
        assertSame(created2, actualComment.getCreated());
        assertSame(event2, actualComment.getEvent());
        assertEquals(1L, actualComment.getId().longValue());
        assertEquals(Status.CONFIRMED, actualComment.getStatus());
        assertEquals("Text", actualComment.getText());
    }
}

