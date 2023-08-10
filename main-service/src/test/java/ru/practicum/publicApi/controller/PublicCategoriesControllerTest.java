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
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.publicApi.service.PublicCategoriesService;

@ContextConfiguration(classes = {PublicCategoriesController.class})
@ExtendWith(SpringExtension.class)
class PublicCategoriesControllerTest {
    @Autowired
    private PublicCategoriesController publicCategoriesController;

    @MockBean
    private PublicCategoriesService publicCategoriesService;

    /**
     * Method under test: {@link PublicCategoriesController#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories() throws Exception {
        when(publicCategoriesService.getCategories(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/categories");
        MockHttpServletRequestBuilder paramResult = getResult.param("from", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(publicCategoriesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PublicCategoriesController#getCategoryById(Long)}
     */
    @Test
    void testGetCategoryById() throws Exception {
        when(publicCategoriesService.getCategoryById(Mockito.<Long>any())).thenReturn(new CategoryDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categories/{catId}", 1L);
        MockMvcBuilders.standaloneSetup(publicCategoriesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"name\":null}"));
    }

    /**
     * Method under test: {@link PublicCategoriesController#getCategoryById(Long)}
     */
    @Test
    void testGetCategoryById2() throws Exception {
        when(publicCategoriesService.getCategories(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        when(publicCategoriesService.getCategoryById(Mockito.<Long>any())).thenReturn(new CategoryDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categories/{catId}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(publicCategoriesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

