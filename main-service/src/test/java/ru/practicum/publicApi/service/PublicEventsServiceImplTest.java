package ru.practicum.publicApi.service;

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
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.EndpointHitDto;
import ru.practicum.StatsClient;
import ru.practicum.base.dto.event.EventSearchDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.exceptions.NotPublishedException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.State;

@ContextConfiguration(classes = {PublicEventsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PublicEventsServiceImplTest {
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private PublicEventsServiceImpl publicEventsServiceImpl;

    @MockBean
    private StatsClient statsClient;

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(publicEventsServiceImpl
                .getEvents("Text", categories, true, rangeStart, rangeEnd, true, "Sort", 1L, 3L, new MockHttpServletRequest())
                .isEmpty());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents2() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenThrow(new NotPublishedException("An error occurred"));
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(NotPublishedException.class, () -> publicEventsServiceImpl.getEvents("Text", categories, true,
                rangeStart, rangeEnd, true, "Sort", 1L, 3L, new MockHttpServletRequest()));
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents3() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Category category = new Category();
        category.setId(1L);
        category.setName("EVENT_DATE");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("EVENT_DATE");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("EVENT_DATE");
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
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(pageImpl);
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals(1,
                publicEventsServiceImpl
                        .getEvents("Text", categories, true, rangeStart, rangeEnd, true, "Sort", 1L, 3L,
                                new MockHttpServletRequest())
                        .size());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents4() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Category category = new Category();
        category.setId(1L);
        category.setName("EVENT_DATE");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("EVENT_DATE");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("EVENT_DATE");
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
        category2.setName("VIEWS");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("VIEWS");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("VIEWS");
        event2.setCategory(category2);
        event2.setConfirmedRequests(0L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("EVENT_DATE");
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
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(pageImpl);
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals(2,
                publicEventsServiceImpl
                        .getEvents("Text", categories, true, rangeStart, rangeEnd, true, "Sort", 1L, 3L,
                                new MockHttpServletRequest())
                        .size());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents5() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(
                publicEventsServiceImpl
                        .getEvents("Text", categories, true, rangeStart, rangeEnd, true, "EVENT_DATE", 1L, 3L,
                                new MockHttpServletRequest())
                        .isEmpty());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents6() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(
                publicEventsServiceImpl
                        .getEvents("Text", categories, true, rangeStart, rangeEnd, true, "VIEWS", 1L, 3L,
                                new MockHttpServletRequest())
                        .isEmpty());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents7() {
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(eventRepository.findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(publicEventsServiceImpl
                .getEvents("Text", categories, true, rangeStart, rangeEnd, true, null, 1L, 3L, new MockHttpServletRequest())
                .isEmpty());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findAllWithFilters(Mockito.<Pageable>any(), Mockito.<EventSearchDto>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEventById(Long, HttpServletRequest)}
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotPublishedException.class,
                () -> publicEventsServiceImpl.getEventById(1L, new MockHttpServletRequest()));
        verify(eventRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PublicEventsServiceImpl#getEventById(Long, HttpServletRequest)}
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
        Event event = mock(Event.class);
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotPublishedException.class,
                () -> publicEventsServiceImpl.getEventById(1L, new MockHttpServletRequest()));
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
     * Method under test: {@link PublicEventsServiceImpl#getEventById(Long, HttpServletRequest)}
     */
    @Test
    void testGetEventById3() {
        when(statsClient.getStats(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Boolean>any())).thenThrow(new NotPublishedException("An error occurred"));
        when(statsClient.save(Mockito.<EndpointHitDto>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

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
        when(event.getCreatedOn()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(event.getDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
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
        assertThrows(NotPublishedException.class,
                () -> publicEventsServiceImpl.getEventById(1L, new MockHttpServletRequest()));
        verify(statsClient).getStats(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Boolean>any());
        verify(statsClient).save(Mockito.<EndpointHitDto>any());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(event).getCreatedOn();
        verify(event).getDate();
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
}

