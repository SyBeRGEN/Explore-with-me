package ru.practicum.adminApi.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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
import ru.practicum.adminApi.service.AdminCompilationsService;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.compilation.UpdateCompilationRequest;

@ContextConfiguration(classes = {AdminCompilationsController.class})
@ExtendWith(SpringExtension.class)
class AdminCompilationsControllerTest {
    @Autowired
    private AdminCompilationsController adminCompilationsController;

    @MockBean
    private AdminCompilationsService adminCompilationsService;

    /**
     * Method under test: {@link AdminCompilationsController#deleteCompilation(Long)}
     */
    @Test
    void testDeleteCompilation() throws Exception {
        doNothing().when(adminCompilationsService).deleteCompilation(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/compilations/{compId}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminCompilationsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AdminCompilationsController#deleteCompilation(Long)}
     */
    @Test
    void testDeleteCompilation2() throws Exception {
        doNothing().when(adminCompilationsService).deleteCompilation(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/compilations/{compId}", 1L);
        requestBuilder.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminCompilationsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AdminCompilationsController#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation() throws Exception {
        when(adminCompilationsService.updateCompilation(Mockito.<Long>any(), Mockito.<UpdateCompilationRequest>any()))
                .thenReturn(new CompilationDto());

        UpdateCompilationRequest updateCompilationRequest = new UpdateCompilationRequest();
        updateCompilationRequest.setEvents(new ArrayList<>());
        updateCompilationRequest.setPinned(true);
        updateCompilationRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(updateCompilationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/admin/compilations/{compId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminCompilationsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"events\":null,\"id\":null,\"pinned\":null,\"title\":null}"));
    }

    /**
     * Method under test: {@link AdminCompilationsController#saveCompilation(NewCompilationDto)}
     */
    @Test
    void testSaveCompilation() throws Exception {
        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setEvents(new ArrayList<>());
        newCompilationDto.setPinned(true);
        newCompilationDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(newCompilationDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/compilations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminCompilationsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

