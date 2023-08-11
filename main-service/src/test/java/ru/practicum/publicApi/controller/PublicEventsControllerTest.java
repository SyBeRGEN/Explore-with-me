package ru.practicum.publicApi.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.publicApi.service.PublicEventsService;

@ContextConfiguration(classes = {PublicEventsController.class})
@ExtendWith(SpringExtension.class)
class PublicEventsControllerTest {
    @Autowired
    private PublicEventsController publicEventsController;

    @MockBean
    private PublicEventsService publicEventsService;

    /**
     * Method under test: {@link PublicEventsController#getEvents(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean, String, Long, Long, HttpServletRequest)}
     */
    @Test
    void testGetEvents() throws Exception {
        when(publicEventsService.getEvents(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<Boolean>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Boolean>any(), Mockito.<String>any(),
                Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<HttpServletRequest>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/events");
        MockHttpServletRequestBuilder paramResult = getResult.param("from", String.valueOf(1L));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("onlyAvailable", String.valueOf(true));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("size", String.valueOf(1L))
                .param("sort", "foo");
        MockMvcBuilders.standaloneSetup(publicEventsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PublicEventsController#getEventById(Long, HttpServletRequest)}
     */
    @Test
    void testGetEventById() throws Exception {
        when(publicEventsService.getEventById(Mockito.<Long>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(new EventFullDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/events/{id}", 1L);
        MockMvcBuilders.standaloneSetup(publicEventsController)
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
     * Method under test: {@link PublicEventsController#getEventById(Long, HttpServletRequest)}
     */
    @Test
    void testGetEventById2() throws Exception {
        when(publicEventsService.getEvents(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<Boolean>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Boolean>any(), Mockito.<String>any(),
                Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<HttpServletRequest>any())).thenReturn(new ArrayList<>());
        when(publicEventsService.getEventById(Mockito.<Long>any(), Mockito.<HttpServletRequest>any()))
                .thenReturn(new EventFullDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/events/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(publicEventsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

