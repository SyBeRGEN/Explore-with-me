package ru.practicum.base.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ConflictExceptionTest {
    /**
     * Method under test: {@link ConflictException#ConflictException(String)}
     */
    @Test
    void testConstructor() {
        ConflictException actualConflictException = new ConflictException("An error occurred");
        assertNull(actualConflictException.getCause());
        assertEquals(0, actualConflictException.getSuppressed().length);
        assertEquals("An error occurred", actualConflictException.getMessage());
        assertEquals("An error occurred", actualConflictException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link ConflictException#ConflictException(String, Throwable)}
     */
    @Test
    void testConstructor2() {
        Throwable throwable = new Throwable();
        ConflictException actualConflictException = new ConflictException("An error occurred", throwable);

        Throwable cause = actualConflictException.getCause();
        assertSame(throwable, cause);
        Throwable[] suppressed = actualConflictException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertEquals("An error occurred", actualConflictException.getLocalizedMessage());
        assertEquals("An error occurred", actualConflictException.getMessage());
        assertNull(cause.getLocalizedMessage());
        assertNull(cause.getCause());
        assertNull(cause.getMessage());
        assertSame(suppressed, cause.getSuppressed());
    }
}

