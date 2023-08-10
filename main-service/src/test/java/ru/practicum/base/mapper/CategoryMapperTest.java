package ru.practicum.base.mapper;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.category.NewCategoryDto;
import ru.practicum.base.model.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {
    /**
     * Method under test: {@link CategoryMapper#toEntity(NewCategoryDto)}
     */
    @Test
    void testToEntity() {
        Category actualToEntityResult = CategoryMapper.toEntity(new NewCategoryDto("Name"));
        assertNull(actualToEntityResult.getId());
        assertEquals("Name", actualToEntityResult.getName());
    }

    /**
     * Method under test: {@link CategoryMapper#toDto(Category)}
     */
    @Test
    void testToDto() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        CategoryDto actualToDtoResult = CategoryMapper.toDto(category);
        assertEquals(1L, actualToDtoResult.getId().longValue());
        assertEquals("Name", actualToDtoResult.getName());
    }

    /**
     * Method under test: {@link CategoryMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList() {
        assertTrue(CategoryMapper.toDtoList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link CategoryMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        List<CategoryDto> actualToDtoListResult = CategoryMapper.toDtoList(categoryList);
        assertEquals(1, actualToDtoListResult.size());
        CategoryDto getResult = actualToDtoListResult.get(0);
        assertEquals(1L, getResult.getId().longValue());
        assertEquals("Name", getResult.getName());
    }

    /**
     * Method under test: {@link CategoryMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("42");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);
        List<CategoryDto> actualToDtoListResult = CategoryMapper.toDtoList(categoryList);
        assertEquals(2, actualToDtoListResult.size());
        CategoryDto getResult = actualToDtoListResult.get(0);
        assertEquals("42", getResult.getName());
        CategoryDto getResult1 = actualToDtoListResult.get(1);
        assertEquals("Name", getResult1.getName());
        assertEquals(1L, getResult1.getId().longValue());
        assertEquals(2L, getResult.getId().longValue());
    }
}

