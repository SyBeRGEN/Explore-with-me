package ru.practicum.adminApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.UpdateEventAdminRequest;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionAdmin;

@ContextConfiguration(classes = {AdminEventsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminEventsServiceImplTest {
    @Autowired
    private AdminEventsServiceImpl adminEventsServiceImpl;

    @MockBean
    private EventRepository eventRepository;

    /**
     * Method under test: {@link AdminEventsServiceImpl#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents() {
        when(eventRepository.findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Pageable>any()))
                .thenReturn(new ArrayList<>());
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<State> statesEnum = new ArrayList<>();
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(adminEventsServiceImpl
                .getEvents(users, statesEnum, categories, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                .isEmpty());
        verify(eventRepository).findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("id");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("id");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("id");
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

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        when(eventRepository.findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any())).thenReturn(eventList);
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<State> statesEnum = new ArrayList<>();
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals(1,
                adminEventsServiceImpl
                        .getEvents(users, statesEnum, categories, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                        .size());
        verify(eventRepository).findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("id");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("id");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("id");
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

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Name");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("Name");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("Annotation");
        event2.setCategory(category2);
        event2.setConfirmedRequests(0L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("id");
        event2.setId(2L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(false);
        event2.setParticipantLimit(0L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(false);
        event2.setState(State.PUBLISHED);
        event2.setTitle("Mr");
        event2.setViews(0L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event2);
        eventList.add(event);
        when(eventRepository.findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any())).thenReturn(eventList);
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<State> statesEnum = new ArrayList<>();
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals(2,
                adminEventsServiceImpl
                        .getEvents(users, statesEnum, categories, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                        .size());
        verify(eventRepository).findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents4() {
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<State> statesEnum = new ArrayList<>();
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(ArithmeticException.class, () -> adminEventsServiceImpl.getEvents(users, statesEnum, categories,
                rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 0L));
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents5() {
        when(eventRepository.findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any())).thenThrow(new ConflictException("An error occurred"));
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<State> statesEnum = new ArrayList<>();
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(ConflictException.class, () -> adminEventsServiceImpl.getEvents(users, statesEnum, categories,
                rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L));
        verify(eventRepository).findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents6() {
        Category category = new Category();
        category.setId(1L);
        category.setName("id");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("id");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);
        Event event = mock(Event.class);
        when(event.getPaid()).thenReturn(true);
        when(event.getRequestModeration()).thenReturn(true);
        when(event.getParticipantLimit()).thenReturn(1L);
        when(event.getViews()).thenReturn(1L);
        when(event.getTitle()).thenReturn("Dr");
        when(event.getPublishedOn()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getState()).thenReturn(State.PENDING);
        when(event.getLocation()).thenReturn(location2);
        when(event.getConfirmedRequests()).thenReturn(1L);
        when(event.getDescription()).thenReturn("The characteristics of someone or something");
        when(event.getCreatedOn()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getInitiator()).thenReturn(user);
        when(event.getId()).thenReturn(1L);
        when(event.getAnnotation()).thenReturn("Annotation");
        when(event.getCategory()).thenReturn(category2);
        doNothing().when(event).setAnnotation(Mockito.<String>any());
        doNothing().when(event).setCategory(Mockito.<Category>any());
        doNothing().when(event).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDescription(Mockito.<String>any());
        doNothing().when(event).setId(Mockito.<Long>any());
        doNothing().when(event).setInitiator(Mockito.<User>any());
        doNothing().when(event).setLocation(Mockito.<Location>any());
        doNothing().when(event).setPaid(Mockito.<Boolean>any());
        doNothing().when(event).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event).setState(Mockito.<State>any());
        doNothing().when(event).setTitle(Mockito.<String>any());
        doNothing().when(event).setViews(Mockito.<Long>any());
        event.setAnnotation("id");
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

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        when(eventRepository.findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any())).thenReturn(eventList);
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<State> statesEnum = new ArrayList<>();
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals(1,
                adminEventsServiceImpl
                        .getEvents(users, statesEnum, categories, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                        .size());
        verify(eventRepository).findEventsByParams(Mockito.<List<Long>>any(), Mockito.<List<State>>any(),
                Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Pageable>any());
        verify(event).getPaid();
        verify(event).getRequestModeration();
        verify(event).getConfirmedRequests();
        verify(event).getId();
        verify(event).getParticipantLimit();
        verify(event).getViews();
        verify(event).getAnnotation();
        verify(event).getDescription();
        verify(event).getTitle();
        verify(event).getCreatedOn();
        verify(event).getDate();
        verify(event).getPublishedOn();
        verify(event, atLeast(1)).getLocation();
        verify(event).getCategory();
        verify(event).getInitiator();
        verify(event).getState();
        verify(event).setAnnotation(Mockito.<String>any());
        verify(event).setCategory(Mockito.<Category>any());
        verify(event).setConfirmedRequests(Mockito.<Long>any());
        verify(event).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event).setDate(Mockito.<LocalDateTime>any());
        verify(event).setDescription(Mockito.<String>any());
        verify(event).setId(Mockito.<Long>any());
        verify(event).setInitiator(Mockito.<User>any());
        verify(event).setLocation(Mockito.<Location>any());
        verify(event).setPaid(Mockito.<Boolean>any());
        verify(event).setParticipantLimit(Mockito.<Long>any());
        verify(event).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event).setRequestModeration(Mockito.<Boolean>any());
        verify(event).setState(Mockito.<State>any());
        verify(event).setTitle(Mockito.<String>any());
        verify(event).setViews(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#updateEvent(Long, UpdateEventAdminRequest)}
     */
    @Test
    void testUpdateEvent() {
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
        Optional<Event> ofResult = Optional.of(event);
        doNothing().when(eventRepository).flush();
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EventFullDto actualUpdateEventResult = adminEventsServiceImpl.updateEvent(1L,
                new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT));
        assertEquals("Annotation", actualUpdateEventResult.getAnnotation());
        assertEquals(1L, actualUpdateEventResult.getViews().longValue());
        assertEquals("Dr", actualUpdateEventResult.getTitle());
        assertEquals(State.PUBLISHED, actualUpdateEventResult.getState());
        assertTrue(actualUpdateEventResult.getRequestModeration());
        assertEquals(1L, actualUpdateEventResult.getParticipantLimit().longValue());
        assertEquals("00:00", actualUpdateEventResult.getPublishedOn().toLocalTime().toString());
        assertTrue(actualUpdateEventResult.getPaid());
        assertEquals("The characteristics of someone or something", actualUpdateEventResult.getDescription());
        assertEquals(1L, actualUpdateEventResult.getConfirmedRequests().longValue());
        assertEquals(1L, actualUpdateEventResult.getId().longValue());
        assertEquals("1970-01-01", actualUpdateEventResult.getEventDate().toLocalDate().toString());
        UserShortDto initiator2 = actualUpdateEventResult.getInitiator();
        assertEquals(1L, initiator2.getId().longValue());
        CategoryDto category2 = actualUpdateEventResult.getCategory();
        assertEquals(1L, category2.getId().longValue());
        assertEquals("Name", category2.getName());
        assertEquals("Name", initiator2.getName());
        Location location2 = actualUpdateEventResult.getLocation();
        assertEquals(10.0f, location2.getLat().floatValue());
        assertEquals(10.0f, location2.getLon().floatValue());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(eventRepository).flush();
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#updateEvent(Long, UpdateEventAdminRequest)}
     */
    @Test
    void testUpdateEvent2() {
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
        Optional<Event> ofResult = Optional.of(event);
        doThrow(new ConflictException("An error occurred")).when(eventRepository).flush();
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminEventsServiceImpl.updateEvent(1L, new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT)));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(eventRepository).flush();
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#updateEvent(Long, UpdateEventAdminRequest)}
     */
    @Test
    void testUpdateEvent3() {
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
        Optional<Event> ofResult = Optional.of(event);
        doThrow(new DataIntegrityViolationException("PUBLISH_EVENT")).when(eventRepository).flush();
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminEventsServiceImpl.updateEvent(1L, new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT)));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(eventRepository).flush();
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#updateEvent(Long, UpdateEventAdminRequest)}
     */
    @Test
    void testUpdateEvent4() {
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

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);
        Event event = mock(Event.class);
        when(event.getPaid()).thenReturn(true);
        when(event.getRequestModeration()).thenReturn(true);
        when(event.getParticipantLimit()).thenReturn(1L);
        when(event.getViews()).thenReturn(1L);
        when(event.getPublishedOn()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getLocation()).thenReturn(location2);
        when(event.getConfirmedRequests()).thenReturn(1L);
        when(event.getDescription()).thenReturn("The characteristics of someone or something");
        when(event.getCreatedOn()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getInitiator()).thenReturn(user);
        when(event.getId()).thenReturn(1L);
        when(event.getAnnotation()).thenReturn("Annotation");
        when(event.getTitle()).thenReturn("Dr");
        when(event.getCategory()).thenReturn(category2);
        when(event.getState()).thenReturn(State.PENDING);
        doNothing().when(event).setAnnotation(Mockito.<String>any());
        doNothing().when(event).setCategory(Mockito.<Category>any());
        doNothing().when(event).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDescription(Mockito.<String>any());
        doNothing().when(event).setId(Mockito.<Long>any());
        doNothing().when(event).setInitiator(Mockito.<User>any());
        doNothing().when(event).setLocation(Mockito.<Location>any());
        doNothing().when(event).setPaid(Mockito.<Boolean>any());
        doNothing().when(event).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event).setState(Mockito.<State>any());
        doNothing().when(event).setTitle(Mockito.<String>any());
        doNothing().when(event).setViews(Mockito.<Long>any());
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
        Optional<Event> ofResult = Optional.of(event);
        doNothing().when(eventRepository).flush();
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EventFullDto actualUpdateEventResult = adminEventsServiceImpl.updateEvent(1L,
                new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT));
        assertEquals("Annotation", actualUpdateEventResult.getAnnotation());
        assertEquals(1L, actualUpdateEventResult.getViews().longValue());
        assertEquals("Dr", actualUpdateEventResult.getTitle());
        assertEquals(State.PENDING, actualUpdateEventResult.getState());
        assertTrue(actualUpdateEventResult.getRequestModeration());
        assertEquals(1L, actualUpdateEventResult.getParticipantLimit().longValue());
        assertEquals("00:00", actualUpdateEventResult.getPublishedOn().toLocalTime().toString());
        assertTrue(actualUpdateEventResult.getPaid());
        assertEquals("The characteristics of someone or something", actualUpdateEventResult.getDescription());
        assertEquals("00:00", actualUpdateEventResult.getCreatedOn().toLocalTime().toString());
        assertEquals(1L, actualUpdateEventResult.getConfirmedRequests().longValue());
        assertEquals(1L, actualUpdateEventResult.getId().longValue());
        assertEquals("1970-01-01", actualUpdateEventResult.getEventDate().toLocalDate().toString());
        UserShortDto initiator2 = actualUpdateEventResult.getInitiator();
        assertEquals(1L, initiator2.getId().longValue());
        CategoryDto category3 = actualUpdateEventResult.getCategory();
        assertEquals(1L, category3.getId().longValue());
        assertEquals("Name", category3.getName());
        assertEquals("Name", initiator2.getName());
        Location location3 = actualUpdateEventResult.getLocation();
        assertEquals(10.0f, location3.getLat().floatValue());
        assertEquals(10.0f, location3.getLon().floatValue());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(eventRepository).flush();
        verify(event).getPaid();
        verify(event).getRequestModeration();
        verify(event).getConfirmedRequests();
        verify(event).getId();
        verify(event).getParticipantLimit();
        verify(event).getViews();
        verify(event).getAnnotation();
        verify(event).getDescription();
        verify(event, atLeast(1)).getTitle();
        verify(event).getCreatedOn();
        verify(event).getDate();
        verify(event).getPublishedOn();
        verify(event, atLeast(1)).getLocation();
        verify(event).getCategory();
        verify(event).getInitiator();
        verify(event, atLeast(1)).getState();
        verify(event).setAnnotation(Mockito.<String>any());
        verify(event).setCategory(Mockito.<Category>any());
        verify(event).setConfirmedRequests(Mockito.<Long>any());
        verify(event, atLeast(1)).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event).setDate(Mockito.<LocalDateTime>any());
        verify(event).setDescription(Mockito.<String>any());
        verify(event).setId(Mockito.<Long>any());
        verify(event).setInitiator(Mockito.<User>any());
        verify(event).setLocation(Mockito.<Location>any());
        verify(event).setPaid(Mockito.<Boolean>any());
        verify(event).setParticipantLimit(Mockito.<Long>any());
        verify(event).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event).setRequestModeration(Mockito.<Boolean>any());
        verify(event, atLeast(1)).setState(Mockito.<State>any());
        verify(event).setTitle(Mockito.<String>any());
        verify(event).setViews(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#updateEvent(Long, UpdateEventAdminRequest)}
     */
    @Test
    void testUpdateEvent5() {
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
        Event event = mock(Event.class);
        when(event.getState()).thenReturn(State.PUBLISHED);
        doNothing().when(event).setAnnotation(Mockito.<String>any());
        doNothing().when(event).setCategory(Mockito.<Category>any());
        doNothing().when(event).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDescription(Mockito.<String>any());
        doNothing().when(event).setId(Mockito.<Long>any());
        doNothing().when(event).setInitiator(Mockito.<User>any());
        doNothing().when(event).setLocation(Mockito.<Location>any());
        doNothing().when(event).setPaid(Mockito.<Boolean>any());
        doNothing().when(event).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event).setState(Mockito.<State>any());
        doNothing().when(event).setTitle(Mockito.<String>any());
        doNothing().when(event).setViews(Mockito.<Long>any());
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminEventsServiceImpl.updateEvent(1L, new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT)));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(event).getState();
        verify(event).setAnnotation(Mockito.<String>any());
        verify(event).setCategory(Mockito.<Category>any());
        verify(event).setConfirmedRequests(Mockito.<Long>any());
        verify(event).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event).setDate(Mockito.<LocalDateTime>any());
        verify(event).setDescription(Mockito.<String>any());
        verify(event).setId(Mockito.<Long>any());
        verify(event).setInitiator(Mockito.<User>any());
        verify(event).setLocation(Mockito.<Location>any());
        verify(event).setPaid(Mockito.<Boolean>any());
        verify(event).setParticipantLimit(Mockito.<Long>any());
        verify(event).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event).setRequestModeration(Mockito.<Boolean>any());
        verify(event).setState(Mockito.<State>any());
        verify(event).setTitle(Mockito.<String>any());
        verify(event).setViews(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminEventsServiceImpl#updateEvent(Long, UpdateEventAdminRequest)}
     */
    @Test
    void testUpdateEvent6() {
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
        Event event = mock(Event.class);
        when(event.getState()).thenReturn(State.CANCELED);
        doNothing().when(event).setAnnotation(Mockito.<String>any());
        doNothing().when(event).setCategory(Mockito.<Category>any());
        doNothing().when(event).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event).setDescription(Mockito.<String>any());
        doNothing().when(event).setId(Mockito.<Long>any());
        doNothing().when(event).setInitiator(Mockito.<User>any());
        doNothing().when(event).setLocation(Mockito.<Location>any());
        doNothing().when(event).setPaid(Mockito.<Boolean>any());
        doNothing().when(event).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event).setState(Mockito.<State>any());
        doNothing().when(event).setTitle(Mockito.<String>any());
        doNothing().when(event).setViews(Mockito.<Long>any());
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminEventsServiceImpl.updateEvent(1L, new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT)));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(event, atLeast(1)).getState();
        verify(event).setAnnotation(Mockito.<String>any());
        verify(event).setCategory(Mockito.<Category>any());
        verify(event).setConfirmedRequests(Mockito.<Long>any());
        verify(event).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event).setDate(Mockito.<LocalDateTime>any());
        verify(event).setDescription(Mockito.<String>any());
        verify(event).setId(Mockito.<Long>any());
        verify(event).setInitiator(Mockito.<User>any());
        verify(event).setLocation(Mockito.<Location>any());
        verify(event).setPaid(Mockito.<Boolean>any());
        verify(event).setParticipantLimit(Mockito.<Long>any());
        verify(event).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event).setRequestModeration(Mockito.<Boolean>any());
        verify(event).setState(Mockito.<State>any());
        verify(event).setTitle(Mockito.<String>any());
        verify(event).setViews(Mockito.<Long>any());
    }
}

