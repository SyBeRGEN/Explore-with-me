package ru.practicum.privateApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.Request;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.RequestRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

@ContextConfiguration(classes = {PrivateRequestServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PrivateRequestServiceImplTest {
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private PrivateRequestServiceImpl privateRequestServiceImpl;

    @MockBean
    private RequestRepository requestRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link PrivateRequestServiceImpl#getRequests(Long)}
     */
    @Test
    void testGetRequests() {
        when(requestRepository.findByRequester_Id(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        assertTrue(privateRequestServiceImpl.getRequests(1L).isEmpty());
        verify(requestRepository).findByRequester_Id(Mockito.<Long>any());
        verify(userRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#getRequests(Long)}
     */
    @Test
    void testGetRequests2() {
        when(requestRepository.findByRequester_Id(Mockito.<Long>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        assertThrows(NotFoundException.class, () -> privateRequestServiceImpl.getRequests(1L));
        verify(requestRepository).findByRequester_Id(Mockito.<Long>any());
        verify(userRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#getRequests(Long)}
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
        when(requestRepository.findByRequester_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateRequestServiceImpl.getRequests(1L);
        assertEquals(1, actualRequests.size());
        ParticipationRequestDto getResult = actualRequests.get(0);
        assertEquals(Status.CONFIRMED, getResult.getStatus());
        assertEquals("00:00", getResult.getCreated().toLocalTime().toString());
        assertEquals(1L, getResult.getRequester().longValue());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(1L, getResult.getEvent().longValue());
        verify(requestRepository).findByRequester_Id(Mockito.<Long>any());
        verify(userRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#getRequests(Long)}
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
        when(requestRepository.findByRequester_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateRequestServiceImpl.getRequests(1L);
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
        verify(requestRepository).findByRequester_Id(Mockito.<Long>any());
        verify(userRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#getRequests(Long)}
     */
    @Test
    void testGetRequests5() {
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> privateRequestServiceImpl.getRequests(1L));
        verify(userRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#getRequests(Long)}
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
        when(requestRepository.findByRequester_Id(Mockito.<Long>any())).thenReturn(requestList);
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        List<ParticipationRequestDto> actualRequests = privateRequestServiceImpl.getRequests(1L);
        assertEquals(1, actualRequests.size());
        ParticipationRequestDto getResult = actualRequests.get(0);
        assertEquals(Status.CONFIRMED, getResult.getStatus());
        assertEquals("00:00", getResult.getCreated().toLocalTime().toString());
        assertEquals(1L, getResult.getRequester().longValue());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(1L, getResult.getEvent().longValue());
        verify(requestRepository).findByRequester_Id(Mockito.<Long>any());
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
        verify(userRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#createRequest(Long, Long)}
     */
    @Test
    void testCreateRequest() {
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(requestRepository.existsByRequester_IdAndEvent_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(true);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(ConflictException.class, () -> privateRequestServiceImpl.createRequest(1L, 1L));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(requestRepository).existsByRequester_IdAndEvent_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#createRequest(Long, Long)}
     */
    @Test
    void testCreateRequest2() {
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(requestRepository.existsByRequester_IdAndEvent_Id(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(NotFoundException.class, () -> privateRequestServiceImpl.createRequest(1L, 1L));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(requestRepository).existsByRequester_IdAndEvent_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#createRequest(Long, Long)}
     */
    @Test
    void testCreateRequest3() {
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> privateRequestServiceImpl.createRequest(1L, 1L));
        verify(eventRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#createRequest(Long, Long)}
     */
    @Test
    void testCreateRequest4() {
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(requestRepository.existsByRequester_IdAndEvent_Id(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(false);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(ConflictException.class, () -> privateRequestServiceImpl.createRequest(1L, 1L));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(requestRepository).existsByRequester_IdAndEvent_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#createRequest(Long, Long)}
     */
    @Test
    void testCreateRequest5() {
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> privateRequestServiceImpl.createRequest(1L, 1L));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#updateRequest(Long, Long)}
     */
    @Test
    void testUpdateRequest() {
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
        Optional<Request> ofResult = Optional.of(request);

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

        User requester2 = new User();
        requester2.setEmail("jane.doe@example.org");
        requester2.setId(1L);
        requester2.setName("Name");

        Request request2 = new Request();
        request2.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request2.setEvent(event2);
        request2.setId(1L);
        request2.setRequester(requester2);
        request2.setStatus(Status.CONFIRMED);
        when(requestRepository.save(Mockito.<Request>any())).thenReturn(request2);
        when(requestRepository.findByIdAndRequester_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        ParticipationRequestDto actualUpdateRequestResult = privateRequestServiceImpl.updateRequest(1L, 1L);
        assertEquals(Status.CONFIRMED, actualUpdateRequestResult.getStatus());
        assertEquals("00:00", actualUpdateRequestResult.getCreated().toLocalTime().toString());
        assertEquals(1L, actualUpdateRequestResult.getRequester().longValue());
        assertEquals(1L, actualUpdateRequestResult.getId().longValue());
        assertEquals(1L, actualUpdateRequestResult.getEvent().longValue());
        verify(requestRepository).save(Mockito.<Request>any());
        verify(requestRepository).findByIdAndRequester_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#updateRequest(Long, Long)}
     */
    @Test
    void testUpdateRequest2() {
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
        Optional<Request> ofResult = Optional.of(request);
        when(requestRepository.save(Mockito.<Request>any())).thenThrow(new NotFoundException("An error occurred"));
        when(requestRepository.findByIdAndRequester_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> privateRequestServiceImpl.updateRequest(1L, 1L));
        verify(requestRepository).save(Mockito.<Request>any());
        verify(requestRepository).findByIdAndRequester_Id(Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateRequestServiceImpl#updateRequest(Long, Long)}
     */
    @Test
    void testUpdateRequest3() {
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
        Optional<Request> ofResult = Optional.of(request);

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

        User requester2 = new User();
        requester2.setEmail("jane.doe@example.org");
        requester2.setId(1L);
        requester2.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");

        User initiator3 = new User();
        initiator3.setEmail("jane.doe@example.org");
        initiator3.setId(1L);
        initiator3.setName("Name");

        Location location3 = new Location();
        location3.setLat(10.0f);
        location3.setLon(10.0f);

        Event event3 = new Event();
        event3.setAnnotation("Annotation");
        event3.setCategory(category3);
        event3.setConfirmedRequests(1L);
        event3.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setDescription("The characteristics of someone or something");
        event3.setId(1L);
        event3.setInitiator(initiator3);
        event3.setLocation(location3);
        event3.setPaid(true);
        event3.setParticipantLimit(1L);
        event3.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setRequestModeration(true);
        event3.setState(State.PENDING);
        event3.setTitle("Dr");
        event3.setViews(1L);
        Request request2 = mock(Request.class);
        when(request2.getStatus()).thenReturn(Status.CONFIRMED);
        when(request2.getCreated()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(request2.getEvent()).thenReturn(event3);
        when(request2.getId()).thenReturn(1L);
        when(request2.getRequester()).thenReturn(user);
        doNothing().when(request2).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(request2).setEvent(Mockito.<Event>any());
        doNothing().when(request2).setId(Mockito.<Long>any());
        doNothing().when(request2).setRequester(Mockito.<User>any());
        doNothing().when(request2).setStatus(Mockito.<Status>any());
        request2.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        request2.setEvent(event2);
        request2.setId(1L);
        request2.setRequester(requester2);
        request2.setStatus(Status.CONFIRMED);
        when(requestRepository.save(Mockito.<Request>any())).thenReturn(request2);
        when(requestRepository.findByIdAndRequester_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        ParticipationRequestDto actualUpdateRequestResult = privateRequestServiceImpl.updateRequest(1L, 1L);
        assertEquals(Status.CONFIRMED, actualUpdateRequestResult.getStatus());
        assertEquals("00:00", actualUpdateRequestResult.getCreated().toLocalTime().toString());
        assertEquals(1L, actualUpdateRequestResult.getRequester().longValue());
        assertEquals(1L, actualUpdateRequestResult.getId().longValue());
        assertEquals(1L, actualUpdateRequestResult.getEvent().longValue());
        verify(requestRepository).save(Mockito.<Request>any());
        verify(requestRepository).findByIdAndRequester_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(request2).getId();
        verify(request2).getCreated();
        verify(request2).getEvent();
        verify(request2).getRequester();
        verify(request2).getStatus();
        verify(request2).setCreated(Mockito.<LocalDateTime>any());
        verify(request2).setEvent(Mockito.<Event>any());
        verify(request2).setId(Mockito.<Long>any());
        verify(request2).setRequester(Mockito.<User>any());
        verify(request2).setStatus(Mockito.<Status>any());
    }
}

