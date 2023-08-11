package ru.practicum.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionStatsHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidTimeException(Exception exception) {
        log.error("Ошибка статуса: ", exception);
        return Map.of(
                "error", "Ошибка статуса: 400",
                "errorMessage", exception.getMessage()
        );
    }
}
