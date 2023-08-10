package ru.practicum.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.EndpointHitDto;
import ru.practicum.service.StatsService;

@ContextConfiguration(classes = {StatsController.class})
@ExtendWith(SpringExtension.class)
class StatsControllerTest {
    @Autowired
    private StatsController statsController;

    @MockBean
    private StatsService statsService;

    /**
     * Method under test: {@link StatsController#getStats(LocalDateTime, LocalDateTime, List, Boolean)}
     */
    @Test
    void testGetStats() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/stats");
        MockHttpServletRequestBuilder paramResult = getResult.param("end", String.valueOf((Object) null));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("start", String.valueOf((Object) null));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("unique", String.valueOf(true));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(statsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link StatsController#saveHit(EndpointHitDto)}
     */
    @Test
    void testSaveHit() throws Exception {
        when(statsService.saveHit(Mockito.<EndpointHitDto>any())).thenReturn(new EndpointHitDto());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/hit")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new EndpointHitDto()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(statsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"app\":null,\"uri\":null,\"ip\":null,\"timestamp\":null}"));
    }
}

