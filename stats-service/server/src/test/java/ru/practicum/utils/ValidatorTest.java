package ru.practicum.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class ValidatorTest {
    @Test
    void testValidateTime() {
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        Validator.validateTime(start, LocalDate.of(1970, 1, 1).atStartOfDay());
    }
}

