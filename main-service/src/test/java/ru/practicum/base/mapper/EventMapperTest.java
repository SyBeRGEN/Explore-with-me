package ru.practicum.base.mapper;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.event.*;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionAdmin;
import ru.practicum.base.utils.StateActionUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

    /**
     * Method under test: {@link EventMapper#toEntity(NewEventDto)}
     */
    @Test
    void testToEntity() {
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        NewEventDto newEventDto = new NewEventDto();
        newEventDto.setLocation(location);
        Event actualToEntityResult = EventMapper.toEntity(newEventDto);
        assertNull(actualToEntityResult.getAnnotation());
        assertNull(actualToEntityResult.getViews());
        assertNull(actualToEntityResult.getTitle());
        assertEquals(State.PENDING, actualToEntityResult.getState());
        assertTrue(actualToEntityResult.getRequestModeration());
        assertNull(actualToEntityResult.getPublishedOn());
        assertEquals(0L, actualToEntityResult.getParticipantLimit().longValue());
        assertFalse(actualToEntityResult.getPaid());
        assertNull(actualToEntityResult.getInitiator());
        assertNull(actualToEntityResult.getCategory());
        assertNull(actualToEntityResult.getId());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getDescription());
        assertNull(actualToEntityResult.getConfirmedRequests());
        Location location1 = actualToEntityResult.getLocation();
        assertEquals(10.0f, location1.getLon().floatValue());
        assertEquals(10.0f, location1.getLat().floatValue());
    }

    /**
     * Method under test: {@link EventMapper#toEntity(UpdateEventAdminRequest)}
     */
    @Test
    void testToEntity2() {
        Event actualToEntityResult = EventMapper.toEntity(new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT));
        assertNull(actualToEntityResult.getAnnotation());
        assertNull(actualToEntityResult.getViews());
        assertNull(actualToEntityResult.getTitle());
        assertNull(actualToEntityResult.getState());
        assertNull(actualToEntityResult.getRequestModeration());
        assertNull(actualToEntityResult.getPublishedOn());
        assertNull(actualToEntityResult.getParticipantLimit());
        assertNull(actualToEntityResult.getPaid());
        assertNull(actualToEntityResult.getLocation());
        assertNull(actualToEntityResult.getInitiator());
        assertNull(actualToEntityResult.getId());
        assertNull(actualToEntityResult.getDescription());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getConfirmedRequests());
        assertNull(actualToEntityResult.getCategory());
    }

    /**
     * Method under test: {@link EventMapper#toEntity(UpdateEventAdminRequest)}
     */
    @Test
    void testToEntity3() {
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        UpdateEventAdminRequest updateEventAdminRequest = new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT);
        updateEventAdminRequest.setLocation(location);
        Event actualToEntityResult = EventMapper.toEntity(updateEventAdminRequest);
        assertNull(actualToEntityResult.getAnnotation());
        assertNull(actualToEntityResult.getViews());
        assertNull(actualToEntityResult.getTitle());
        assertNull(actualToEntityResult.getState());
        assertNull(actualToEntityResult.getRequestModeration());
        assertNull(actualToEntityResult.getPublishedOn());
        assertNull(actualToEntityResult.getParticipantLimit());
        assertNull(actualToEntityResult.getPaid());
        assertNull(actualToEntityResult.getInitiator());
        assertNull(actualToEntityResult.getCategory());
        assertNull(actualToEntityResult.getId());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getDescription());
        assertNull(actualToEntityResult.getConfirmedRequests());
        Location location1 = actualToEntityResult.getLocation();
        assertEquals(10.0f, location1.getLon().floatValue());
        assertEquals(10.0f, location1.getLat().floatValue());
    }

    /**
     * Method under test: {@link EventMapper#toEntity(UpdateEventUserRequest)}
     */
    @Test
    void testToEntity4() {
        Event actualToEntityResult = EventMapper.toEntity(new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW));
        assertNull(actualToEntityResult.getAnnotation());
        assertNull(actualToEntityResult.getViews());
        assertNull(actualToEntityResult.getTitle());
        assertNull(actualToEntityResult.getState());
        assertNull(actualToEntityResult.getRequestModeration());
        assertNull(actualToEntityResult.getPublishedOn());
        assertNull(actualToEntityResult.getParticipantLimit());
        assertNull(actualToEntityResult.getPaid());
        assertNull(actualToEntityResult.getLocation());
        assertNull(actualToEntityResult.getInitiator());
        assertNull(actualToEntityResult.getId());
        assertNull(actualToEntityResult.getDescription());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getCreatedOn());
        assertNull(actualToEntityResult.getConfirmedRequests());
        assertNull(actualToEntityResult.getCategory());
    }

    /**
     * Method under test: {@link EventMapper#toEntity(UpdateEventUserRequest)}
     */
    @Test
    void testToEntity5() {
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        UpdateEventUserRequest updateEventUserRequest = new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW);
        updateEventUserRequest.setLocation(location);
        Event actualToEntityResult = EventMapper.toEntity(updateEventUserRequest);
        assertNull(actualToEntityResult.getAnnotation());
        assertNull(actualToEntityResult.getViews());
        assertNull(actualToEntityResult.getTitle());
        assertNull(actualToEntityResult.getState());
        assertNull(actualToEntityResult.getRequestModeration());
        assertNull(actualToEntityResult.getPublishedOn());
        assertNull(actualToEntityResult.getParticipantLimit());
        assertNull(actualToEntityResult.getPaid());
        assertNull(actualToEntityResult.getInitiator());
        assertNull(actualToEntityResult.getCategory());
        assertNull(actualToEntityResult.getCreatedOn());
        assertNull(actualToEntityResult.getId());
        assertNull(actualToEntityResult.getDescription());
        assertNull(actualToEntityResult.getDate());
        assertNull(actualToEntityResult.getConfirmedRequests());
        Location location1 = actualToEntityResult.getLocation();
        assertEquals(10.0f, location1.getLon().floatValue());
        assertEquals(10.0f, location1.getLat().floatValue());
    }

    /**
     * Method under test: {@link EventMapper#toEventFullDto(Event)}
     */
    @Test
    void testToEventFullDto() {
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
        EventFullDto actualToEventFullDtoResult = EventMapper.toEventFullDto(event);
        assertEquals("Annotation", actualToEventFullDtoResult.getAnnotation());
        assertEquals(1L, actualToEventFullDtoResult.getViews().longValue());
        assertEquals("Dr", actualToEventFullDtoResult.getTitle());
        assertEquals(State.PENDING, actualToEventFullDtoResult.getState());
        assertTrue(actualToEventFullDtoResult.getRequestModeration());
        assertEquals(1L, actualToEventFullDtoResult.getParticipantLimit().longValue());
        assertEquals("01:01", actualToEventFullDtoResult.getPublishedOn().toLocalTime().toString());
        assertTrue(actualToEventFullDtoResult.getPaid());
        assertEquals("The characteristics of someone or something", actualToEventFullDtoResult.getDescription());
        assertEquals("01:01", actualToEventFullDtoResult.getCreatedOn().toLocalTime().toString());
        assertEquals(1L, actualToEventFullDtoResult.getConfirmedRequests().longValue());
        assertEquals(1L, actualToEventFullDtoResult.getId().longValue());
        assertEquals("0001-01-01", actualToEventFullDtoResult.getEventDate().toLocalDate().toString());
        UserShortDto initiator = actualToEventFullDtoResult.getInitiator();
        assertEquals(1L, initiator.getId().longValue());
        CategoryDto category1 = actualToEventFullDtoResult.getCategory();
        assertEquals(1L, category1.getId().longValue());
        assertEquals("Name", category1.getName());
        assertEquals("Name", initiator.getName());
        Location location1 = actualToEventFullDtoResult.getLocation();
        assertEquals(10.0f, location1.getLat().floatValue());
        assertEquals(10.0f, location1.getLon().floatValue());
    }

    /**
     * Method under test: {@link EventMapper#toEventShortDto(Event)}
     */
    @Test
    void testToEventShortDto() {
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
        EventShortDto actualToEventShortDtoResult = EventMapper.toEventShortDto(event);
        assertEquals("Annotation", actualToEventShortDtoResult.getAnnotation());
        assertEquals(1L, actualToEventShortDtoResult.getViews().longValue());
        assertEquals("Dr", actualToEventShortDtoResult.getTitle());
        assertTrue(actualToEventShortDtoResult.getPaid());
        assertEquals(1L, actualToEventShortDtoResult.getId().longValue());
        assertEquals(1L, actualToEventShortDtoResult.getConfirmedRequests().longValue());
        assertEquals("01:01", actualToEventShortDtoResult.getEventDate().toLocalTime().toString());
        CategoryDto category1 = actualToEventShortDtoResult.getCategory();
        assertEquals(1L, category1.getId().longValue());
        UserShortDto initiator = actualToEventShortDtoResult.getInitiator();
        assertEquals("Name", initiator.getName());
        assertEquals("Name", category1.getName());
        assertEquals(1L, initiator.getId().longValue());
    }

    /**
     * Method under test: {@link EventMapper#toEventShortDtoList(List)}
     */
    @Test
    void testToEventShortDtoList() {
        assertTrue(EventMapper.toEventShortDtoList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link EventMapper#toEventShortDtoList(List)}
     */
    @Test
    void testToEventShortDtoList2() {
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

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        assertEquals(1, EventMapper.toEventShortDtoList(eventList).size());
    }

    /**
     * Method under test: {@link EventMapper#toEventShortDtoList(List)}
     */
    @Test
    void testToEventShortDtoList3() {
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

        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("42");

        User user1 = new User();
        user1.setEmail("john.smith@example.org");
        user1.setId(2L);
        user1.setName("42");

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
        event1.setInitiator(user1);
        event1.setLocation(location1);
        event1.setPaid(false);
        event1.setParticipantLimit(0L);
        event1.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setRequestModeration(false);
        event1.setState(State.PUBLISHED);
        event1.setTitle("Mr");
        event1.setViews(0L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event);
        assertEquals(2, EventMapper.toEventShortDtoList(eventList).size());
    }
}

