package ru.practicum.utils;

import ru.practicum.exceptions.InvalidTimeException;

import java.time.LocalDateTime;

public class Validator {
    public static void validateTime(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new InvalidTimeException("Время начала не может быть позднее времени окончания!");
        }
    }
}
