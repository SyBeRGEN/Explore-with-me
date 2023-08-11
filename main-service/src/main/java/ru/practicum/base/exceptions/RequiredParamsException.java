package ru.practicum.base.exceptions;

public class RequiredParamsException extends RuntimeException {
    public RequiredParamsException(String message) {
        super(message);
    }
}
