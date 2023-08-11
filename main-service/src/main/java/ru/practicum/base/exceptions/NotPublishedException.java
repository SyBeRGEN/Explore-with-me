package ru.practicum.base.exceptions;

public class NotPublishedException extends RuntimeException {
    public NotPublishedException(String message) {
        super(message);
    }
}
