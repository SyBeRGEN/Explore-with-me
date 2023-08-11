package ru.practicum.adminApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.category.NewCategoryDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.repository.CategoryRepository;
import ru.practicum.base.repository.EventRepository;

@ContextConfiguration(classes = {AdminCategoriesServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminCategoriesServiceImplTest {
    @Autowired
    private AdminCategoriesServiceImpl adminCategoriesServiceImpl;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private EventRepository eventRepository;

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#createCategory(NewCategoryDto)}
     */
    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category);
        CategoryDto actualCreateCategoryResult = adminCategoriesServiceImpl.createCategory(new NewCategoryDto("Name"));
        assertEquals(1L, actualCreateCategoryResult.getId().longValue());
        assertEquals("Name", actualCreateCategoryResult.getName());
        verify(categoryRepository).save(Mockito.<Category>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#createCategory(NewCategoryDto)}
     */
    @Test
    void testCreateCategory2() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(category.getName()).thenReturn("Name");
        doNothing().when(category).setId(Mockito.<Long>any());
        doNothing().when(category).setName(Mockito.<String>any());
        category.setId(1L);
        category.setName("Name");
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category);
        CategoryDto actualCreateCategoryResult = adminCategoriesServiceImpl.createCategory(new NewCategoryDto("Name"));
        assertEquals(1L, actualCreateCategoryResult.getId().longValue());
        assertEquals("Name", actualCreateCategoryResult.getName());
        verify(categoryRepository).save(Mockito.<Category>any());
        verify(category).getId();
        verify(category, atLeast(1)).getName();
        verify(category).setId(Mockito.<Long>any());
        verify(category).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#updateCategory(NewCategoryDto, Long)}
     */
    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepository).flush();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CategoryDto actualUpdateCategoryResult = adminCategoriesServiceImpl.updateCategory(new NewCategoryDto("Name"),
                1L);
        assertEquals(1L, actualUpdateCategoryResult.getId().longValue());
        assertEquals("Name", actualUpdateCategoryResult.getName());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).flush();
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#updateCategory(NewCategoryDto, Long)}
     */
    @Test
    void testUpdateCategory2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new DataIntegrityViolationException("Получена категория: {}")).when(categoryRepository).flush();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminCategoriesServiceImpl.updateCategory(new NewCategoryDto("Name"), 1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).flush();
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#updateCategory(NewCategoryDto, Long)}
     */
    @Test
    void testUpdateCategory3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new ConflictException("An error occurred")).when(categoryRepository).flush();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminCategoriesServiceImpl.updateCategory(new NewCategoryDto("Name"), 1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).flush();
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#updateCategory(NewCategoryDto, Long)}
     */
    @Test
    void testUpdateCategory4() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);
        when(category.getName()).thenReturn("Name");
        doNothing().when(category).setId(Mockito.<Long>any());
        doNothing().when(category).setName(Mockito.<String>any());
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepository).flush();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CategoryDto actualUpdateCategoryResult = adminCategoriesServiceImpl.updateCategory(new NewCategoryDto("Name"),
                1L);
        assertEquals(1L, actualUpdateCategoryResult.getId().longValue());
        assertEquals("Name", actualUpdateCategoryResult.getName());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).flush();
        verify(category).getId();
        verify(category, atLeast(1)).getName();
        verify(category).setId(Mockito.<Long>any());
        verify(category, atLeast(1)).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#updateCategory(NewCategoryDto, Long)}
     */
    @Test
    void testUpdateCategory5() {
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                () -> adminCategoriesServiceImpl.updateCategory(new NewCategoryDto("Name"), 1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() {
        when(eventRepository.existsByCategory(Mockito.<Category>any())).thenReturn(true);

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCategoriesServiceImpl.deleteCategory(1L));
        verify(eventRepository).existsByCategory(Mockito.<Category>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory2() {
        when(eventRepository.existsByCategory(Mockito.<Category>any()))
                .thenThrow(new DataIntegrityViolationException("Получена категория: {}"));

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(DataIntegrityViolationException.class, () -> adminCategoriesServiceImpl.deleteCategory(1L));
        verify(eventRepository).existsByCategory(Mockito.<Category>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory3() {
        when(eventRepository.existsByCategory(Mockito.<Category>any())).thenReturn(false);

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepository).deleteById(Mockito.<Long>any());
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        adminCategoriesServiceImpl.deleteCategory(1L);
        verify(eventRepository).existsByCategory(Mockito.<Category>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory4() {
        when(eventRepository.existsByCategory(Mockito.<Category>any())).thenReturn(true);
        Category category = mock(Category.class);
        when(category.getName()).thenReturn("Name");
        doNothing().when(category).setId(Mockito.<Long>any());
        doNothing().when(category).setName(Mockito.<String>any());
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCategoriesServiceImpl.deleteCategory(1L));
        verify(eventRepository).existsByCategory(Mockito.<Category>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(category).getName();
        verify(category).setId(Mockito.<Long>any());
        verify(category).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCategoriesServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory5() {
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> adminCategoriesServiceImpl.deleteCategory(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }
}

