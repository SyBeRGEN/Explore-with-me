package ru.practicum.base.dto.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NewCategoryDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NewCategoryDto#NewCategoryDto()}
     *   <li>{@link NewCategoryDto#setName(String)}
     *   <li>{@link NewCategoryDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        NewCategoryDto actualNewCategoryDto = new NewCategoryDto();
        actualNewCategoryDto.setName("Name");
        assertEquals("Name", actualNewCategoryDto.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NewCategoryDto#NewCategoryDto(String)}
     *   <li>{@link NewCategoryDto#setName(String)}
     *   <li>{@link NewCategoryDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        NewCategoryDto actualNewCategoryDto = new NewCategoryDto("Name");
        actualNewCategoryDto.setName("Name");
        assertEquals("Name", actualNewCategoryDto.getName());
    }
}

