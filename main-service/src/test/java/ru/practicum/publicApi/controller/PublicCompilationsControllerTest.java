package ru.practicum.publicApi.controller;

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
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.publicApi.service.PublicCompilationsService;

@ContextConfiguration(classes = {PublicCompilationsController.class})
@ExtendWith(SpringExtension.class)
class PublicCompilationsControllerTest {
    @Autowired
    private PublicCompilationsController publicCompilationsController;

    @MockBean
    private PublicCompilationsService publicCompilationsService;

    /**
     * Method under test: {@link PublicCompilationsController#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations() throws Exception {
        when(publicCompilationsService.getCompilations(Mockito.<Boolean>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/compilations");
        MockHttpServletRequestBuilder paramResult = getResult.param("from", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(publicCompilationsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PublicCompilationsController#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById() throws Exception {
        when(publicCompilationsService.getCompilationById(Mockito.<Long>any())).thenReturn(new CompilationDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/compilations/{compId}", 1L);
        MockMvcBuilders.standaloneSetup(publicCompilationsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"events\":null,\"id\":null,\"pinned\":null,\"title\":null}"));
    }

    /**
     * Method under test: {@link PublicCompilationsController#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById2() throws Exception {
        when(publicCompilationsService.getCompilations(Mockito.<Boolean>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        when(publicCompilationsService.getCompilationById(Mockito.<Long>any())).thenReturn(new CompilationDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/compilations/{compId}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(publicCompilationsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

