package ru.practicum.privateApi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.base.dto.event.UpdateEventUserRequest;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.Request;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CategoryRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.RequestRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionUser;
import ru.practicum.base.utils.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PrivateEventServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PrivateEventServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private PrivateEventServiceImpl privateEventServiceImpl;

    @MockBean
    private RequestRepository requestRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEvents(Long, Long, Long)}
     */
    @Test
    void testGetEvents() {
        when(eventRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(privateEventServiceImpl.getEvents(1L, 1L, 3L).isEmpty());
        verify(eventRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEvents(Long, Long, Long)}
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

        ArrayList<Event> content = new ArrayList<>();
        content.add(event);
        PageImpl<Event> pageImpl = new PageImpl<>(content);
        when(eventRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        assertEquals(1, privateEventServiceImpl.getEvents(1L, 1L, 3L).size());
        verify(eventRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEvents(Long, Long, Long)}
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
        category2.setName("Получен список событий с размером: {}");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("Получен список событий с размером: {}");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("Получен список событий с размером: {}");
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

        ArrayList<Event> content = new ArrayList<>();
        content.add(event2);
        content.add(event);
        PageImpl<Event> pageImpl = new PageImpl<>(content);
        when(eventRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        assertEquals(2, privateEventServiceImpl.getEvents(1L, 1L, 3L).size());
        verify(eventRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEvents(Long, Long, Long)}
     */
    @Test
    void testGetEvents4() {
        assertThrows(ArithmeticException.class, () -> privateEventServiceImpl.getEvents(1L, 1L, 0L));
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEvents(Long, Long, Long)}
     */
    @Test
    void testGetEvents5() {
        when(eventRepository.findAll(Mockito.<Pageable>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> privateEventServiceImpl.getEvents(1L, 1L, 3L));
        verify(eventRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEvents(Long, Long, Long)}
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
        Event event = mock(Event.class);
        when(event.getPaid()).thenReturn(true);
        when(event.getViews()).thenReturn(1L);
        when(event.getTitle()).thenReturn("Dr");
        when(event.getConfirmedRequests()).thenReturn(1L);
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

        ArrayList<Event> content = new ArrayList<>();
        content.add(event);
        PageImpl<Event> pageImpl = new PageImpl<>(content);
        when(eventRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        assertEquals(1, privateEventServiceImpl.getEvents(1L, 1L, 3L).size());
        verify(eventRepository).findAll(Mockito.<Pageable>any());
        verify(event).getPaid();
        verify(event).getConfirmedRequests();
        verify(event).getId();
        verify(event).getViews();
        verify(event).getAnnotation();
        verify(event).getTitle();
        verify(event).getDate();
        verify(event).getCategory();
        verify(event).getInitiator();
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
     * Method under test: {@link PrivateEventServiceImpl#getEventById(Long, Long)}
     */
    @Test
    void testGetEventById() {
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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        EventFullDto actualEventById = privateEventServiceImpl.getEventById(1L, 1L);
        assertEquals("Annotation", actualEventById.getAnnotation());
        assertEquals(1L, actualEventById.getViews().longValue());
        assertEquals("Dr", actualEventById.getTitle());
        assertEquals(State.PENDING, actualEventById.getState());
        assertTrue(actualEventById.getRequestModeration());
        assertEquals(1L, actualEventById.getParticipantLimit().longValue());
        assertEquals("00:00", actualEventById.getPublishedOn().toLocalTime().toString());
        assertTrue(actualEventById.getPaid());
        assertEquals("The characteristics of someone or something", actualEventById.getDescription());
        assertEquals("00:00", actualEventById.getCreatedOn().toLocalTime().toString());
        assertEquals(1L, actualEventById.getConfirmedRequests().longValue());
        assertEquals(1L, actualEventById.getId().longValue());
        assertEquals("1970-01-01", actualEventById.getEventDate().toLocalDate().toString());
        UserShortDto initiator2 = actualEventById.getInitiator();
        assertEquals(1L, initiator2.getId().longValue());
        CategoryDto category2 = actualEventById.getCategory();
        assertEquals(1L, category2.getId().longValue());
        assertEquals("Name", category2.getName());
        assertEquals("Name", initiator2.getName());
        Location location2 = actualEventById.getLocation();
        assertEquals(10.0f, location2.getLat().floatValue());
        assertEquals(10.0f, location2.getLon().floatValue());
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getEventById(Long, Long)}
     */
    @Test
    void testGetEventById2() {
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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        EventFullDto actualEventById = privateEventServiceImpl.getEventById(1L, 1L);
        assertEquals("Annotation", actualEventById.getAnnotation());
        assertEquals(1L, actualEventById.getViews().longValue());
        assertEquals("Dr", actualEventById.getTitle());
        assertEquals(State.PENDING, actualEventById.getState());
        assertTrue(actualEventById.getRequestModeration());
        assertEquals(1L, actualEventById.getParticipantLimit().longValue());
        assertEquals("00:00", actualEventById.getPublishedOn().toLocalTime().toString());
        assertTrue(actualEventById.getPaid());
        assertEquals("The characteristics of someone or something", actualEventById.getDescription());
        assertEquals("00:00", actualEventById.getCreatedOn().toLocalTime().toString());
        assertEquals(1L, actualEventById.getConfirmedRequests().longValue());
        assertEquals(1L, actualEventById.getId().longValue());
        assertEquals("1970-01-01", actualEventById.getEventDate().toLocalDate().toString());
        UserShortDto initiator2 = actualEventById.getInitiator();
        assertEquals(1L, initiator2.getId().longValue());
        CategoryDto category3 = actualEventById.getCategory();
        assertEquals(1L, category3.getId().longValue());
        assertEquals("Name", category3.getName());
        assertEquals("Name", initiator2.getName());
        Location location3 = actualEventById.getLocation();
        assertEquals(10.0f, location3.getLat().floatValue());
        assertEquals(10.0f, location3.getLon().floatValue());
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(event).getPaid();
        verify(event).getRequestModeration();
        verify(event).getConfirmedRequests();
        verify(event, atLeast(1)).getId();
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
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests() {
        when(requestRepository.findByEvent_Id(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);
        assertTrue(privateEventServiceImpl.getRequests(1L, 1L).isEmpty());
        verify(requestRepository).findByEvent_Id(Mockito.<Long>any());
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests2() {
        when(requestRepository.findByEvent_Id(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);
        assertThrows(NotFoundException.class, () -> privateEventServiceImpl.getRequests(1L, 1L));
        verify(requestRepository).findByEvent_Id(Mockito.<Long>any());
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests3() {
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

        User requester = new User();
        requester.setEmail("jane.doe@example.org");
        requester.setId(1L);
        requester.setName("Name");

        Request request = new Request();
        request.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(requester);
        request.setStatus(Status.CONFIRMED);

        ArrayList<Request> requestList = new ArrayList<>();
        requestList.add(request);
        when(requestRepository.findByEvent_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateEventServiceImpl.getRequests(1L, 1L);
        assertEquals(1, actualRequests.size());
        ParticipationRequestDto getResult = actualRequests.get(0);
        assertEquals(Status.CONFIRMED, getResult.getStatus());
        assertEquals("00:00", getResult.getCreated().toLocalTime().toString());
        assertEquals(1L, getResult.getRequester().longValue());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(1L, getResult.getEvent().longValue());
        verify(requestRepository).findByEvent_Id(Mockito.<Long>any());
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests4() {
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

        User requester = new User();
        requester.setEmail("jane.doe@example.org");
        requester.setId(1L);
        requester.setName("Name");

        Request request = new Request();
        request.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(requester);
        request.setStatus(Status.CONFIRMED);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("42");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("42");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("42");
        event2.setCategory(category2);
        event2.setConfirmedRequests(0L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("Description");
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

        User requester2 = new User();
        requester2.setEmail("john.smith@example.org");
        requester2.setId(2L);
        requester2.setName("42");

        Request request2 = new Request();
        request2.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request2.setEvent(event2);
        request2.setId(2L);
        request2.setRequester(requester2);
        request2.setStatus(Status.REJECTED);

        ArrayList<Request> requestList = new ArrayList<>();
        requestList.add(request2);
        requestList.add(request);
        when(requestRepository.findByEvent_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateEventServiceImpl.getRequests(1L, 1L);
        assertEquals(2, actualRequests.size());
        ParticipationRequestDto getResult = actualRequests.get(0);
        assertEquals(Status.REJECTED, getResult.getStatus());
        ParticipationRequestDto getResult2 = actualRequests.get(1);
        assertEquals(Status.CONFIRMED, getResult2.getStatus());
        assertEquals(1L, getResult2.getRequester().longValue());
        assertEquals(1L, getResult2.getId().longValue());
        assertEquals(1L, getResult2.getEvent().longValue());
        assertEquals(2L, getResult.getRequester().longValue());
        assertEquals("00:00", getResult2.getCreated().toLocalTime().toString());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals(2L, getResult.getEvent().longValue());
        assertEquals("1970-01-01", getResult.getCreated().toLocalDate().toString());
        verify(requestRepository).findByEvent_Id(Mockito.<Long>any());
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests5() {
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> privateEventServiceImpl.getRequests(1L, 1L));
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests6() {
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

        User requester = new User();
        requester.setEmail("jane.doe@example.org");
        requester.setId(1L);
        requester.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

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
        Request request = mock(Request.class);
        when(request.getStatus()).thenReturn(Status.CONFIRMED);
        when(request.getCreated()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(request.getEvent()).thenReturn(event2);
        when(request.getId()).thenReturn(1L);
        when(request.getRequester()).thenReturn(user);
        doNothing().when(request).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(request).setEvent(Mockito.<Event>any());
        doNothing().when(request).setId(Mockito.<Long>any());
        doNothing().when(request).setRequester(Mockito.<User>any());
        doNothing().when(request).setStatus(Mockito.<Status>any());
        request.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(requester);
        request.setStatus(Status.CONFIRMED);

        ArrayList<Request> requestList = new ArrayList<>();
        requestList.add(request);
        when(requestRepository.findByEvent_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateEventServiceImpl.getRequests(1L, 1L);
        assertEquals(1, actualRequests.size());
        ParticipationRequestDto getResult = actualRequests.get(0);
        assertEquals(Status.CONFIRMED, getResult.getStatus());
        assertEquals("00:00", getResult.getCreated().toLocalTime().toString());
        assertEquals(1L, getResult.getRequester().longValue());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(1L, getResult.getEvent().longValue());
        verify(requestRepository).findByEvent_Id(Mockito.<Long>any());
        verify(request).getId();
        verify(request).getCreated();
        verify(request).getEvent();
        verify(request).getRequester();
        verify(request).getStatus();
        verify(request).setCreated(Mockito.<LocalDateTime>any());
        verify(request).setEvent(Mockito.<Event>any());
        verify(request).setId(Mockito.<Long>any());
        verify(request).setRequester(Mockito.<User>any());
        verify(request).setStatus(Mockito.<Status>any());
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests7() {
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

        User requester = new User();
        requester.setEmail("jane.doe@example.org");
        requester.setId(1L);
        requester.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

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
        Event event2 = mock(Event.class);
        when(event2.getId()).thenReturn(1L);
        doNothing().when(event2).setAnnotation(Mockito.<String>any());
        doNothing().when(event2).setCategory(Mockito.<Category>any());
        doNothing().when(event2).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event2).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event2).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event2).setDescription(Mockito.<String>any());
        doNothing().when(event2).setId(Mockito.<Long>any());
        doNothing().when(event2).setInitiator(Mockito.<User>any());
        doNothing().when(event2).setLocation(Mockito.<Location>any());
        doNothing().when(event2).setPaid(Mockito.<Boolean>any());
        doNothing().when(event2).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event2).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event2).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event2).setState(Mockito.<State>any());
        doNothing().when(event2).setTitle(Mockito.<String>any());
        doNothing().when(event2).setViews(Mockito.<Long>any());
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
        Request request = mock(Request.class);
        when(request.getStatus()).thenReturn(Status.CONFIRMED);
        when(request.getCreated()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(request.getEvent()).thenReturn(event2);
        when(request.getId()).thenReturn(1L);
        when(request.getRequester()).thenReturn(user);
        doNothing().when(request).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(request).setEvent(Mockito.<Event>any());
        doNothing().when(request).setId(Mockito.<Long>any());
        doNothing().when(request).setRequester(Mockito.<User>any());
        doNothing().when(request).setStatus(Mockito.<Status>any());
        request.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request.setEvent(event);
        request.setId(1L);
        request.setRequester(requester);
        request.setStatus(Status.CONFIRMED);

        ArrayList<Request> requestList = new ArrayList<>();
        requestList.add(request);
        when(requestRepository.findByEvent_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(eventRepository.existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateEventServiceImpl.getRequests(1L, 1L);
        assertEquals(1, actualRequests.size());
        ParticipationRequestDto getResult = actualRequests.get(0);
        assertEquals(Status.CONFIRMED, getResult.getStatus());
        assertEquals("00:00", getResult.getCreated().toLocalTime().toString());
        assertEquals(1L, getResult.getRequester().longValue());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(1L, getResult.getEvent().longValue());
        verify(requestRepository).findByEvent_Id(Mockito.<Long>any());
        verify(request).getId();
        verify(request).getCreated();
        verify(request).getEvent();
        verify(request).getRequester();
        verify(request).getStatus();
        verify(request).setCreated(Mockito.<LocalDateTime>any());
        verify(request).setEvent(Mockito.<Event>any());
        verify(request).setId(Mockito.<Long>any());
        verify(request).setRequester(Mockito.<User>any());
        verify(request).setStatus(Mockito.<Status>any());
        verify(event2).getId();
        verify(event2).setAnnotation(Mockito.<String>any());
        verify(event2).setCategory(Mockito.<Category>any());
        verify(event2).setConfirmedRequests(Mockito.<Long>any());
        verify(event2).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event2).setDate(Mockito.<LocalDateTime>any());
        verify(event2).setDescription(Mockito.<String>any());
        verify(event2).setId(Mockito.<Long>any());
        verify(event2).setInitiator(Mockito.<User>any());
        verify(event2).setLocation(Mockito.<Location>any());
        verify(event2).setPaid(Mockito.<Boolean>any());
        verify(event2).setParticipantLimit(Mockito.<Long>any());
        verify(event2).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event2).setRequestModeration(Mockito.<Boolean>any());
        verify(event2).setState(Mockito.<State>any());
        verify(event2).setTitle(Mockito.<String>any());
        verify(event2).setViews(Mockito.<Long>any());
        verify(eventRepository).existsByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#updateEvent(Long, Long, UpdateEventUserRequest)}
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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        EventFullDto actualUpdateEventResult = privateEventServiceImpl.updateEvent(1L, 1L,
                new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW));
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
        CategoryDto category2 = actualUpdateEventResult.getCategory();
        assertEquals(1L, category2.getId().longValue());
        assertEquals("Name", category2.getName());
        assertEquals("Name", initiator2.getName());
        Location location2 = actualUpdateEventResult.getLocation();
        assertEquals(10.0f, location2.getLat().floatValue());
        assertEquals(10.0f, location2.getLon().floatValue());
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(eventRepository).flush();
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#updateEvent(Long, Long, UpdateEventUserRequest)}
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
        doThrow(new NotFoundException("An error occurred")).when(eventRepository).flush();
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> privateEventServiceImpl.updateEvent(1L, 1L,
                new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW)));
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(eventRepository).flush();
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#updateEvent(Long, Long, UpdateEventUserRequest)}
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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        EventFullDto actualUpdateEventResult = privateEventServiceImpl.updateEvent(1L, 1L,
                new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW));
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
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
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
        verify(event, atLeast(1)).setState(Mockito.<State>any());
        verify(event).setTitle(Mockito.<String>any());
        verify(event).setViews(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#updateEvent(Long, Long, UpdateEventUserRequest)}
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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> privateEventServiceImpl.updateEvent(1L, 1L,
                new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW)));
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
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
     * Method under test: {@link PrivateEventServiceImpl#updateRequestStatus(Long, Long, EventRequestStatusUpdateRequest)}
     */
    @Test
    void testUpdateRequestStatus() {
        when(requestRepository.findByIdIn(Mockito.<Collection<Long>>any()))
                .thenThrow(new NotFoundException("An error occurred"));

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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class,
                () -> privateEventServiceImpl.updateRequestStatus(1L, 1L, new EventRequestStatusUpdateRequest()));
        verify(requestRepository).findByIdIn(Mockito.<Collection<Long>>any());
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#updateRequestStatus(Long, Long, EventRequestStatusUpdateRequest)}
     */
    @Test
    void testUpdateRequestStatus2() {
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                () -> privateEventServiceImpl.updateRequestStatus(1L, 1L, new EventRequestStatusUpdateRequest()));
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateEventServiceImpl#updateRequestStatus(Long, Long, EventRequestStatusUpdateRequest)}
     */
    @Test
    void testUpdateRequestStatus3() {
        when(requestRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

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
        when(event.getConfirmedRequests()).thenReturn(1L);
        when(event.getParticipantLimit()).thenReturn(1L);
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
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);

        EventRequestStatusUpdateRequest request = new EventRequestStatusUpdateRequest();
        request.setRequestIds(new ArrayList<>());
        assertThrows(ConflictException.class, () -> privateEventServiceImpl.updateRequestStatus(1L, 1L, request));
        verify(requestRepository).findByIdIn(Mockito.<Collection<Long>>any());
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(event).getConfirmedRequests();
        verify(event).getParticipantLimit();
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

