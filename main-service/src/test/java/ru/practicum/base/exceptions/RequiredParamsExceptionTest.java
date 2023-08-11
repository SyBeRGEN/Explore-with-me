package ru.practicum.base.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RequiredParamsExceptionTest {
    /**
     * Method under test: {@link RequiredParamsException#RequiredParamsException(String)}
     */
    @Test
    void testConstructor() {
        RequiredParamsException actualRequiredParamsException = new RequiredParamsException("An error occurred");
        assertNull(actualRequiredParamsException.getCause());
        assertEquals(0, actualRequiredParamsException.getSuppressed().length);
        assertEquals("An error occurred", actualRequiredParamsException.getMessage());
        assertEquals("An error occurred", actualRequiredParamsException.getLocalizedMessage());
    }
}

