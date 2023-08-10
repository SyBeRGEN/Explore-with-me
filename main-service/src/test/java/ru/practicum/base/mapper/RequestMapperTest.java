package ru.practicum.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.Request;
import ru.practicum.base.model.User;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

class RequestMapperTest {
    /**
     * Method under test: {@link RequestMapper#toRequest(Event, User)}
     */
    @Test
    void testToRequest() {
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
        Request actualToRequestResult = RequestMapper.toRequest(event, user1);
        assertEquals(Status.PENDING, actualToRequestResult.getStatus());
        assertSame(user1, actualToRequestResult.getRequester());
        assertNull(actualToRequestResult.getId());
        assertSame(event, actualToRequestResult.getEvent());
    }

    /**
     * Method under test: {@link RequestMapper#toRequest(Event, User)}
     */
    @Test
    void testToRequest2() {
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
        event.setParticipantLimit(0L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(1L);
        user1.setName("Name");
        Request actualToRequestResult = RequestMapper.toRequest(event, user1);
        assertEquals(Status.CONFIRMED, actualToRequestResult.getStatus());
        assertSame(user1, actualToRequestResult.getRequester());
        assertNull(actualToRequestResult.getId());
        assertSame(event, actualToRequestResult.getEvent());
    }

    /**
     * Method under test: {@link RequestMapper#toRequest(Event, User)}
     */
    @Test
    void testToRequest3() {
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
        event.setRequestModeration(false);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(1L);
        user1.setName("Name");
        Request actualToRequestResult = RequestMapper.toRequest(event, user1);
        assertEquals(Status.CONFIRMED, actualToRequestResult.getStatus());
        assertSame(user1, actualToRequestResult.getRequester());
        assertNull(actualToRequestResult.getId());
        assertSame(event, actualToRequestResult.getEvent());
    }

    /**
     * Method under test: {@link RequestMapper#toParticipationRequestDto(Request)}
     */
    @Test
    void testToParticipationRequestDto() {
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

        Request request = new Request();
        request.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(user1);
        request.setStatus(Status.CONFIRMED);
        ParticipationRequestDto actualToParticipationRequestDtoResult = RequestMapper.toParticipationRequestDto(request);
        assertEquals(Status.CONFIRMED, actualToParticipationRequestDtoResult.getStatus());
        assertEquals("01:01", actualToParticipationRequestDtoResult.getCreated().toLocalTime().toString());
        assertEquals(1L, actualToParticipationRequestDtoResult.getRequester().longValue());
        assertEquals(1L, actualToParticipationRequestDtoResult.getId().longValue());
        assertEquals(1L, actualToParticipationRequestDtoResult.getEvent().longValue());
    }

    /**
     * Method under test: {@link RequestMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList() {
        assertTrue(RequestMapper.toDtoList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link RequestMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList2() {
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

        Request request = new Request();
        request.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(user1);
        request.setStatus(Status.CONFIRMED);

        ArrayList<Request> requestList = new ArrayList<>();
        requestList.add(request);
        List<ParticipationRequestDto> actualToDtoListResult = RequestMapper.toDtoList(requestList);
        assertEquals(1, actualToDtoListResult.size());
        ParticipationRequestDto getResult = actualToDtoListResult.get(0);
        assertEquals(Status.CONFIRMED, getResult.getStatus());
        assertEquals("01:01", getResult.getCreated().toLocalTime().toString());
        assertEquals(1L, getResult.getRequester().longValue());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(1L, getResult.getEvent().longValue());
    }

    /**
     * Method under test: {@link RequestMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList3() {
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

        Request request = new Request();
        request.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(user1);
        request.setStatus(Status.CONFIRMED);

        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("42");

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2L);
        user2.setName("42");

        Location location1 = new Location();
        location1.setLat(0.5f);
        location1.setLon(0.5f);

        Event event1 = new Event();
        event1.setAnnotation("42");
        event1.setCategory(category1);
        event1.setConfirmedRequests(0L);
        event1.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDescription("Description");
        event1.setId(2L);
        event1.setInitiator(user2);
        event1.setLocation(location1);
        event1.setPaid(false);
        event1.setParticipantLimit(0L);
        event1.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setRequestModeration(false);
        event1.setState(State.PUBLISHED);
        event1.setTitle("Mr");
        event1.setViews(0L);

        User user3 = new User();
        user3.setEmail("john.smith@example.org");
        user3.setId(2L);
        user3.setName("42");

        Request request1 = new Request();
        request1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        request1.setEvent(event1);
        request1.setId(2L);
        request1.setRequester(user3);
        request1.setStatus(Status.REJECTED);

        ArrayList<Request> requestList = new ArrayList<>();
        requestList.add(request1);
        requestList.add(request);
        List<ParticipationRequestDto> actualToDtoListResult = RequestMapper.toDtoList(requestList);
        assertEquals(2, actualToDtoListResult.size());
        ParticipationRequestDto getResult = actualToDtoListResult.get(0);
        assertEquals(Status.REJECTED, getResult.getStatus());
        ParticipationRequestDto getResult1 = actualToDtoListResult.get(1);
        assertEquals(Status.CONFIRMED, getResult1.getStatus());
        assertEquals(1L, getResult1.getRequester().longValue());
        assertEquals(1L, getResult1.getId().longValue());
        assertEquals(1L, getResult1.getEvent().longValue());
        assertEquals(2L, getResult.getRequester().longValue());
        assertEquals("01:01", getResult1.getCreated().toLocalTime().toString());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals(2L, getResult.getEvent().longValue());
        assertEquals("0001-01-01", getResult.getCreated().toLocalDate().toString());
    }
}

