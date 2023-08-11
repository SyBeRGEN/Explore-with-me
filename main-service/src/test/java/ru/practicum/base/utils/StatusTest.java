package ru.practicum.base.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class StatusTest {
    /**
     * Method under test: {@link Status#containsCheck(String)}
     */
    @Test
    void testContainsCheck() {
        assertNull(Status.containsCheck("Status"));
        assertEquals(Status.CANCELED, Status.containsCheck("CANCELED"));
        assertEquals(Status.CONFIRMED, Status.containsCheck("CONFIRMED"));
        assertEquals(Status.PENDING, Status.containsCheck("PENDING"));
        assertEquals(Status.REJECTED, Status.containsCheck("REJECTED"));
    }
}

