package ru.practicum.base.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BadTimeExceptionTest {
    /**
     * Method under test: {@link BadTimeException#BadTimeException(String)}
     */
    @Test
    void testConstructor() {
        BadTimeException actualBadTimeException = new BadTimeException("An error occurred");
        assertNull(actualBadTimeException.getCause());
        assertEquals(0, actualBadTimeException.getSuppressed().length);
        assertEquals("An error occurred", actualBadTimeException.getMessage());
        assertEquals("An error occurred", actualBadTimeException.getLocalizedMessage());
    }
}

