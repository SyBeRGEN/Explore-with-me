package ru.practicum.privateApi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.base.dto.event.EventRequestStatusUpdateResult;
import ru.practicum.base.dto.event.NewEventDto;
import ru.practicum.base.dto.event.UpdateEventUserRequest;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CategoryRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.RequestRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionUser;
import ru.practicum.base.utils.Status;
import ru.practicum.privateApi.service.PrivateEventService;
import ru.practicum.privateApi.service.PrivateEventServiceImpl;

@ContextConfiguration(classes = {PrivateEventController.class})
@ExtendWith(SpringExtension.class)
class PrivateEventControllerTest {
    @Autowired
    private PrivateEventController privateEventController;

    @MockBean
    private PrivateEventService privateEventService;

    /**
     * Method under test: {@link PrivateEventController#getEvents(Long, Long, Long)}
     */
    @Test
    void testGetEvents() throws Exception {
        when(privateEventService.getEvents(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/{userId}/events", 1L);
        MockHttpServletRequestBuilder paramResult = getResult.param("from", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(privateEventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrivateEventController#getEventById(Long, Long)}
     */
    @Test
    void testGetEventById() throws Exception {
        when(privateEventService.getEventById(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(new EventFullDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/events/{eventId}", 1L,
                1L);
        MockMvcBuilders.standaloneSetup(privateEventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"annotation\":null,\"category\":null,\"confirmedRequests\":null,\"createdOn\":null,\"description\":null,"
                                        + "\"eventDate\":null,\"id\":null,\"initiator\":null,\"location\":null,\"paid\":null,\"participantLimit\":null,"
                                        + "\"publishedOn\":null,\"requestModeration\":null,\"state\":null,\"title\":null,\"views\":null}"));
    }

    /**
     * Method under test: {@link PrivateEventController#getRequests(Long, Long)}
     */
    @Test
    void testGetRequests() throws Exception {
        when(privateEventService.getRequests(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/events/{eventId}/requests", 1L, 1L);
        MockMvcBuilders.standaloneSetup(privateEventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrivateEventController#createEvent(Long, NewEventDto)}
     */
    @Test
    void testCreateEvent() {
        PrivateEventServiceImpl service = mock(PrivateEventServiceImpl.class);
        when(service.createEvent(Mockito.<Long>any(), Mockito.<NewEventDto>any())).thenReturn(new EventFullDto());
        PrivateEventController privateEventController = new PrivateEventController(service);
        ResponseEntity<EventFullDto> actualCreateEventResult = privateEventController.createEvent(1L, new NewEventDto());
        assertTrue(actualCreateEventResult.hasBody());
        assertTrue(actualCreateEventResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualCreateEventResult.getStatusCode());
        verify(service).createEvent(Mockito.<Long>any(), Mockito.<NewEventDto>any());
    }

    /**
     * Method under test: {@link PrivateEventController#updateEvent(Long, Long, UpdateEventUserRequest)}
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
        EventRepository eventRepository = mock(EventRepository.class);
        doNothing().when(eventRepository).flush();
        when(eventRepository.findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(ofResult);
        PrivateEventController privateEventController = new PrivateEventController(new PrivateEventServiceImpl(
                mock(UserRepository.class), mock(CategoryRepository.class), mock(RequestRepository.class), eventRepository));
        ResponseEntity<EventFullDto> actualUpdateEventResult = privateEventController.updateEvent(1L, 1L,
                new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW));
        assertTrue(actualUpdateEventResult.hasBody());
        assertTrue(actualUpdateEventResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateEventResult.getStatusCode());
        EventFullDto body = actualUpdateEventResult.getBody();
        assertTrue(body.getRequestModeration());
        assertEquals(1L, body.getParticipantLimit().longValue());
        assertEquals("00:00", body.getPublishedOn().toLocalTime().toString());
        assertTrue(body.getPaid());
        assertEquals("Dr", body.getTitle());
        assertEquals(1L, body.getViews().longValue());
        assertEquals("The characteristics of someone or something", body.getDescription());
        assertEquals("00:00", body.getCreatedOn().toLocalTime().toString());
        assertEquals(1L, body.getId().longValue());
        assertEquals("Annotation", body.getAnnotation());
        assertEquals(State.PENDING, body.getState());
        assertEquals("1970-01-01", body.getEventDate().toLocalDate().toString());
        assertEquals(1L, body.getConfirmedRequests().longValue());
        Location location2 = body.getLocation();
        assertEquals(10.0f, location2.getLat().floatValue());
        CategoryDto category2 = body.getCategory();
        assertEquals("Name", category2.getName());
        assertEquals(1L, category2.getId().longValue());
        UserShortDto initiator2 = body.getInitiator();
        assertEquals("Name", initiator2.getName());
        assertEquals(10.0f, location2.getLon().floatValue());
        assertEquals(1L, initiator2.getId().longValue());
        verify(eventRepository).findByIdAndInitiator_Id(Mockito.<Long>any(), Mockito.<Long>any());
        verify(eventRepository).flush();
    }

    /**
     * Method under test: {@link PrivateEventController#updateRequestStatus(Long, Long, EventRequestStatusUpdateRequest)}
     */
    @Test
    void testUpdateRequestStatus() throws Exception {
        when(privateEventService.updateRequestStatus(Mockito.<Long>any(), Mockito.<Long>any(),
                Mockito.<EventRequestStatusUpdateRequest>any())).thenReturn(new EventRequestStatusUpdateResult());

        EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest = new EventRequestStatusUpdateRequest();
        eventRequestStatusUpdateRequest.setRequestIds(new ArrayList<>());
        eventRequestStatusUpdateRequest.setStatus(Status.CONFIRMED);
        String content = (new ObjectMapper()).writeValueAsString(eventRequestStatusUpdateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/users/{userId}/events/{eventId}/requests", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(privateEventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"confirmedRequests\":null,\"rejectedRequests\":null}"));
    }

    /**
     * Method under test: {@link PrivateEventController#updateRequestStatus(Long, Long, EventRequestStatusUpdateRequest)}
     */
    @Test
    void testUpdateRequestStatus2() throws Exception {
        when(privateEventService.updateRequestStatus(Mockito.<Long>any(), Mockito.<Long>any(),
                Mockito.<EventRequestStatusUpdateRequest>any())).thenReturn(new EventRequestStatusUpdateResult());

        EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest = new EventRequestStatusUpdateRequest();
        eventRequestStatusUpdateRequest.setRequestIds(new ArrayList<>());
        eventRequestStatusUpdateRequest.setStatus(Status.REJECTED);
        String content = (new ObjectMapper()).writeValueAsString(eventRequestStatusUpdateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/users/{userId}/events/{eventId}/requests", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(privateEventController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"confirmedRequests\":null,\"rejectedRequests\":null}"));
    }
}

