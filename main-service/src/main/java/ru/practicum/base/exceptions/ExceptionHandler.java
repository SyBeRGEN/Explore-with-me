package ru.practicum.base.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMissingServletRequestParameterException(Exception exception) {
        log.error("Ошибка статуса: ", exception);
        return Map.of(
                "error", "Ошибка статуса: 404",
                "errorMessage", exception.getMessage()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RequiredParamsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequiredParamsException(Exception exception) {
        log.error("Ошибка статуса: ", exception);
        return Map.of(
                "error", "Ошибка статуса: 400",
                "errorMessage", exception.getMessage()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NotPublishedException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotPublishedException(Exception exception) {
        log.error("Ошибка статуса: ", exception);
        return Map.of(
                "error", "Ошибка статуса: 404",
                "errorMessage", exception.getMessage()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleConflictException(Exception exception) {
        log.error("Ошибка статуса: ", exception);
        return Map.of(
                "error", "Ошибка статуса: 409",
                "errorMessage", exception.getMessage()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadTimeException(Exception exception) {
        log.error("Ошибка статуса: ", exception);
        return Map.of(
                "error", "Ошибка статуса: 400",
                "errorMessage", exception.getMessage()
        );
    }
}
