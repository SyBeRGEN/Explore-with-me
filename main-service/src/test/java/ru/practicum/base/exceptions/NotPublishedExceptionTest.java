package ru.practicum.base.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NotPublishedExceptionTest {
    /**
     * Method under test: {@link NotPublishedException#NotPublishedException(String)}
     */
    @Test
    void testConstructor() {
        NotPublishedException actualNotPublishedException = new NotPublishedException("An error occurred");
        assertNull(actualNotPublishedException.getCause());
        assertEquals(0, actualNotPublishedException.getSuppressed().length);
        assertEquals("An error occurred", actualNotPublishedException.getMessage());
        assertEquals("An error occurred", actualNotPublishedException.getLocalizedMessage());
    }
}

