package ru.practicum.base.exceptions;

public class BadTimeException extends RuntimeException {
    public BadTimeException(String message) {
        super(message);
    }
}
