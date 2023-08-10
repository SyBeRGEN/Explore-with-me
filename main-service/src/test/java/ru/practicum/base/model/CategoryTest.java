package ru.practicum.base.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CategoryTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Category#Category()}
     *   <li>{@link Category#setId(Long)}
     *   <li>{@link Category#setName(String)}
     *   <li>{@link Category#getId()}
     *   <li>{@link Category#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Category actualCategory = new Category();
        actualCategory.setId(1L);
        actualCategory.setName("Name");
        assertEquals(1L, actualCategory.getId().longValue());
        assertEquals("Name", actualCategory.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Category#Category(Long, String)}
     *   <li>{@link Category#setId(Long)}
     *   <li>{@link Category#setName(String)}
     *   <li>{@link Category#getId()}
     *   <li>{@link Category#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Category actualCategory = new Category(1L, "Name");
        actualCategory.setId(1L);
        actualCategory.setName("Name");
        assertEquals(1L, actualCategory.getId().longValue());
        assertEquals("Name", actualCategory.getName());
    }
}

