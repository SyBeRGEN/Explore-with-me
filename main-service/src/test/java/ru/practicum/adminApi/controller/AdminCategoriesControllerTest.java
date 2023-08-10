package ru.practicum.adminApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import ru.practicum.adminApi.service.AdminCategoriesService;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.category.NewCategoryDto;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AdminCategoriesController.class})
@ExtendWith(SpringExtension.class)
class AdminCategoriesControllerTest {
    @Autowired
    private AdminCategoriesController adminCategoriesController;

    @MockBean
    private AdminCategoriesService adminCategoriesService;

    /**
     * Method under test: {@link AdminCategoriesController#createCategory(NewCategoryDto)}
     */
    @Test
    void testCreateCategory() throws Exception {
        NewCategoryDto newCategoryDto = new NewCategoryDto();
        String name = "Name";
        newCategoryDto.setName(name);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(newCategoryDto);
        String urlTemplate = "/admin/categories";
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get(urlTemplate, uriVariables);
        MediaType contentType = MediaType.APPLICATION_JSON;
        MockHttpServletRequestBuilder contentTypeResult = getResult.contentType(contentType);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(content);
        Object[] controllers = new Object[]{adminCategoriesController};
        StandaloneMockMvcBuilder standaloneSetupResult = MockMvcBuilders.standaloneSetup(controllers);
        MockMvc buildResult = standaloneSetupResult.build();
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
        StatusResultMatchers statusResult = MockMvcResultMatchers.status();
        int status = 405;
        ResultMatcher isResult = statusResult.is(status);
        actualPerformResult.andExpect(isResult);
    }

    /**
     * Method under test: {@link AdminCategoriesController#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(adminCategoriesService).deleteCategory(Mockito.<Long>any());
        String urlTemplate = "/admin/categories/{catId}";
        long resultLong = 1L;
        Object[] uriVariables = new Object[]{resultLong};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(urlTemplate, uriVariables);
        Object[] controllers = new Object[]{adminCategoriesController};
        StandaloneMockMvcBuilder standaloneSetupResult = MockMvcBuilders.standaloneSetup(controllers);
        MockMvc buildResult = standaloneSetupResult.build();
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
        StatusResultMatchers statusResult = MockMvcResultMatchers.status();
        ResultMatcher isNoContentResult = statusResult.isNoContent();
        actualPerformResult.andExpect(isNoContentResult);
    }

    /**
     * Method under test: {@link AdminCategoriesController#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory2() throws Exception {
        doNothing().when(adminCategoriesService).deleteCategory(Mockito.<Long>any());
        String urlTemplate = "/admin/categories/{catId}";
        long resultLong = 1L;
        Object[] uriVariables = new Object[]{resultLong};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(urlTemplate, uriVariables);
        String encoding = "Encoding";
        requestBuilder.characterEncoding(encoding);
        Object[] controllers = new Object[]{adminCategoriesController};
        StandaloneMockMvcBuilder standaloneSetupResult = MockMvcBuilders.standaloneSetup(controllers);
        MockMvc buildResult = standaloneSetupResult.build();
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
        StatusResultMatchers statusResult = MockMvcResultMatchers.status();
        ResultMatcher isNoContentResult = statusResult.isNoContent();
        actualPerformResult.andExpect(isNoContentResult);
    }

    /**
     * Method under test: {@link AdminCategoriesController#updateCategory(NewCategoryDto, Long)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        when(adminCategoriesService.updateCategory(Mockito.<NewCategoryDto>any(), Mockito.<Long>any()))
                .thenReturn(categoryDto);

        NewCategoryDto newCategoryDto = new NewCategoryDto();
        String name = "Name";
        newCategoryDto.setName(name);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(newCategoryDto);
        String urlTemplate = "/admin/categories/{catId}";
        long resultLong = 1L;
        Object[] uriVariables = new Object[]{resultLong};
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch(urlTemplate, uriVariables);
        MediaType contentType = MediaType.APPLICATION_JSON;
        MockHttpServletRequestBuilder contentTypeResult = patchResult.contentType(contentType);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(content);
        Object[] controllers = new Object[]{adminCategoriesController};
        StandaloneMockMvcBuilder standaloneSetupResult = MockMvcBuilders.standaloneSetup(controllers);
        MockMvc buildResult = standaloneSetupResult.build();
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
        StatusResultMatchers statusResult = MockMvcResultMatchers.status();
        ResultMatcher isOkResult = statusResult.isOk();
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        String contentType2 = "application/json";
        ResultMatcher contentTypeResult2 = contentResult.contentType(contentType2);
        ContentResultMatchers contentResult2 = MockMvcResultMatchers.content();
        String expectedContent = "{\"id\":null,\"name\":null}";
        ResultMatcher stringResult = contentResult2.string(expectedContent);
        actualPerformResult.andExpect(isOkResult).andExpect(contentTypeResult2).andExpect(stringResult);
    }
}

