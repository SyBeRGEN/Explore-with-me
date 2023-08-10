package ru.practicum.base.dto.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CategoryDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CategoryDto#CategoryDto()}
     *   <li>{@link CategoryDto#setId(Long)}
     *   <li>{@link CategoryDto#setName(String)}
     *   <li>{@link CategoryDto#getId()}
     *   <li>{@link CategoryDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CategoryDto actualCategoryDto = new CategoryDto();
        actualCategoryDto.setId(1L);
        actualCategoryDto.setName("Name");
        assertEquals(1L, actualCategoryDto.getId().longValue());
        assertEquals("Name", actualCategoryDto.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CategoryDto#CategoryDto(Long, String)}
     *   <li>{@link CategoryDto#setId(Long)}
     *   <li>{@link CategoryDto#setName(String)}
     *   <li>{@link CategoryDto#getId()}
     *   <li>{@link CategoryDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        CategoryDto actualCategoryDto = new CategoryDto(1L, "Name");
        actualCategoryDto.setId(1L);
        actualCategoryDto.setName("Name");
        assertEquals(1L, actualCategoryDto.getId().longValue());
        assertEquals("Name", actualCategoryDto.getName());
    }
}

