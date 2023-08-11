package ru.practicum.adminApi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.adminApi.service.AdminEventsService;
import ru.practicum.adminApi.service.AdminEventsServiceImpl;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.UpdateEventAdminRequest;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionAdmin;

@ContextConfiguration(classes = {AdminEventsController.class})
@ExtendWith(SpringExtension.class)
class AdminEventsControllerTest {
    @Autowired
    private AdminEventsController adminEventsController;

    @MockBean
    private AdminEventsService adminEventsService;

    /**
     * Method under test: {@link AdminEventsController#getEvents(List, List, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetEvents() throws Exception {
        when(adminEventsService.getEvents(Mockito.<List<Long>>any(), Mockito.<List<State>>any(), Mockito.<List<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/events");
        MockHttpServletRequestBuilder paramResult = getResult.param("from", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(adminEventsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminEventsController#updateEvent(Long, UpdateEventAdminRequest)}
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
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        AdminEventsController adminEventsController = new AdminEventsController(
                new AdminEventsServiceImpl(eventRepository));
        ResponseEntity<EventFullDto> actualUpdateEventResult = adminEventsController.updateEvent(1L,
                new UpdateEventAdminRequest(StateActionAdmin.PUBLISH_EVENT));
        assertTrue(actualUpdateEventResult.hasBody());
        assertTrue(actualUpdateEventResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateEventResult.getStatusCode());
        EventFullDto body = actualUpdateEventResult.getBody();
        assert body != null;
        assertTrue(body.getRequestModeration());
        assertEquals(1L, body.getParticipantLimit().longValue());
        assertEquals("00:00", body.getPublishedOn().toLocalTime().toString());
        assertTrue(body.getPaid());
        assertEquals("Dr", body.getTitle());
        assertEquals(1L, body.getViews().longValue());
        assertEquals("The characteristics of someone or something", body.getDescription());
        assertEquals(1L, body.getId().longValue());
        assertEquals("Annotation", body.getAnnotation());
        assertEquals(State.PUBLISHED, body.getState());
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
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(eventRepository).flush();
    }
}

