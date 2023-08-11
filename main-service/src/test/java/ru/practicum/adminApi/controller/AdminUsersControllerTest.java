package ru.practicum.adminApi.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
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
import ru.practicum.adminApi.service.AdminUsersService;
import ru.practicum.base.dto.user.NewUserRequest;

@ContextConfiguration(classes = {AdminUsersController.class})
@ExtendWith(SpringExtension.class)
class AdminUsersControllerTest {
    @Autowired
    private AdminUsersController adminUsersController;

    @MockBean
    private AdminUsersService adminUsersService;

    /**
     * Method under test: {@link AdminUsersController#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers() throws Exception {
        when(adminUsersService.getUsers(Mockito.<List<Long>>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/users");
        MockHttpServletRequestBuilder paramResult = getResult.param("from", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(adminUsersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminUsersController#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(adminUsersService).deleteUser(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/users/{userId}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminUsersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AdminUsersController#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(adminUsersService).deleteUser(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/users/{userId}", 1L);
        requestBuilder.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminUsersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AdminUsersController#saveUser(NewUserRequest)}
     */
    @Test
    void testSaveUser() throws Exception {
        when(adminUsersService.getUsers(Mockito.<List<Long>>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());

        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setEmail("jane.doe@example.org");
        newUserRequest.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(newUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminUsersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

