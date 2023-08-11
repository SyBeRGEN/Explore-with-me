package ru.practicum.base.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class StateTest {
    /**
     * Method under test: {@link State#convertFromString(String)}
     */
    @Test
    void testConvertFromString() {
        assertNull(State.convertFromString("MD"));
        assertEquals(State.CANCELED, State.convertFromString("CANCELED"));
        assertEquals(State.PENDING, State.convertFromString("PENDING"));
        assertEquals(State.PUBLISHED, State.convertFromString("PUBLISHED"));
    }
}

