package ru.practicum.base.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilMergePropertyTest {
    /**
     * Method under test: {@link UtilMergeProperty#getNullPropertyNames(Object)}
     */
    @Test
    void testGetNullPropertyNames() {
        assertEquals(0, UtilMergeProperty.getNullPropertyNames("Source").length);
    }
}

