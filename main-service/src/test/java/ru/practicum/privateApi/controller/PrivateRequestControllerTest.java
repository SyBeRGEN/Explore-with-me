package ru.practicum.privateApi.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.privateApi.service.PrivateRequestService;

@ContextConfiguration(classes = {PrivateRequestController.class})
@ExtendWith(SpringExtension.class)
class PrivateRequestControllerTest {
    @Autowired
    private PrivateRequestController privateRequestController;

    @MockBean
    private PrivateRequestService privateRequestService;

    /**
     * Method under test: {@link PrivateRequestController#createRequest(Long, Long)}
     */
    @Test
    void testCreateRequest() throws Exception {
        when(privateRequestService.getRequests(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/{userId}/requests", 1L);
        MockHttpServletRequestBuilder requestBuilder = getResult.param("eventId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(privateRequestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrivateRequestController#getRequests(Long)}
     */
    @Test
    void testGetRequests() throws Exception {
        when(privateRequestService.getRequests(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/requests", 1L);
        MockMvcBuilders.standaloneSetup(privateRequestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrivateRequestController#getRequests(Long)}
     */
    @Test
    void testGetRequests2() throws Exception {
        when(privateRequestService.getRequests(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/requests", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(privateRequestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrivateRequestController#updateRequest(Long, Long)}
     */
    @Test
    void testUpdateRequest() throws Exception {
        when(privateRequestService.updateRequest(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ParticipationRequestDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/users/{userId}/requests/{requestsId}/cancel", 1L, 1L);
        MockMvcBuilders.standaloneSetup(privateRequestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"created\":null,\"event\":null,\"id\":null,\"requester\":null,\"status\":null}"));
    }
}

