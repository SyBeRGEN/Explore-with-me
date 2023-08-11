package ru.practicum.publicApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.repository.CategoryRepository;

@ContextConfiguration(classes = {PublicCategoriesServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PublicCategoriesServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private PublicCategoriesServiceImpl publicCategoriesServiceImpl;

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories() {
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(publicCategoriesServiceImpl.getCategories(1L, 3L).isEmpty());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("id");

        ArrayList<Category> content = new ArrayList<>();
        content.add(category);
        PageImpl<Category> pageImpl = new PageImpl<>(content);
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        List<CategoryDto> actualCategories = publicCategoriesServiceImpl.getCategories(1L, 3L);
        assertEquals(1, actualCategories.size());
        CategoryDto getResult = actualCategories.get(0);
        assertEquals(1L, getResult.getId().longValue());
        assertEquals("id", getResult.getName());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("id");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Name");

        ArrayList<Category> content = new ArrayList<>();
        content.add(category2);
        content.add(category);
        PageImpl<Category> pageImpl = new PageImpl<>(content);
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        List<CategoryDto> actualCategories = publicCategoriesServiceImpl.getCategories(1L, 3L);
        assertEquals(2, actualCategories.size());
        CategoryDto getResult = actualCategories.get(0);
        assertEquals("Name", getResult.getName());
        CategoryDto getResult2 = actualCategories.get(1);
        assertEquals("id", getResult2.getName());
        assertEquals(1L, getResult2.getId().longValue());
        assertEquals(2L, getResult.getId().longValue());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories4() {
        assertThrows(ArithmeticException.class, () -> publicCategoriesServiceImpl.getCategories(1L, 0L));
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories5() {
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> publicCategoriesServiceImpl.getCategories(1L, 3L));
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategories(Long, Long)}
     */
    @Test
    void testGetCategories6() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(category.getName()).thenReturn("Name");
        doNothing().when(category).setId(Mockito.<Long>any());
        doNothing().when(category).setName(Mockito.<String>any());
        category.setId(1L);
        category.setName("id");

        ArrayList<Category> content = new ArrayList<>();
        content.add(category);
        PageImpl<Category> pageImpl = new PageImpl<>(content);
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        List<CategoryDto> actualCategories = publicCategoriesServiceImpl.getCategories(1L, 3L);
        assertEquals(1, actualCategories.size());
        CategoryDto getResult = actualCategories.get(0);
        assertEquals(1L, getResult.getId().longValue());
        assertEquals("Name", getResult.getName());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
        verify(category).getId();
        verify(category).getName();
        verify(category).setId(Mockito.<Long>any());
        verify(category).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategoryById(Long)}
     */
    @Test
    void testGetCategoryById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CategoryDto actualCategoryById = publicCategoriesServiceImpl.getCategoryById(1L);
        assertEquals(1L, actualCategoryById.getId().longValue());
        assertEquals("Name", actualCategoryById.getName());
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategoryById(Long)}
     */
    @Test
    void testGetCategoryById2() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(category.getName()).thenReturn("Name");
        doNothing().when(category).setId(Mockito.<Long>any());
        doNothing().when(category).setName(Mockito.<String>any());
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CategoryDto actualCategoryById = publicCategoriesServiceImpl.getCategoryById(1L);
        assertEquals(1L, actualCategoryById.getId().longValue());
        assertEquals("Name", actualCategoryById.getName());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(category).getId();
        verify(category).getName();
        verify(category).setId(Mockito.<Long>any());
        verify(category).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PublicCategoriesServiceImpl#getCategoryById(Long)}
     */
    @Test
    void testGetCategoryById3() {
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> publicCategoriesServiceImpl.getCategoryById(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }
}

