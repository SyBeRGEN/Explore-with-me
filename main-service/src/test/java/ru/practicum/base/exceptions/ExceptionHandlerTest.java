package ru.practicum.base.exceptions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionHandlerTest {
    /**
     * Method under test: {@link ExceptionHandler#handleMissingServletRequestParameterException(Exception)}
     */
    @Test
    void testHandleMissingServletRequestParameterException() {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Map<String, String> actualHandleMissingServletRequestParameterExceptionResult = exceptionHandler
                .handleMissingServletRequestParameterException(new IOException("An error occurred"));
        assertEquals(2, actualHandleMissingServletRequestParameterExceptionResult.size());
        assertEquals("An error occurred", actualHandleMissingServletRequestParameterExceptionResult.get("errorMessage"));
        assertEquals("Ошибка статуса: 404", actualHandleMissingServletRequestParameterExceptionResult.get("error"));
    }

    /**
     * Method under test: {@link ExceptionHandler#handleRequiredParamsException(Exception)}
     */
    @Test
    void testHandleRequiredParamsException() {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Map<String, String> actualHandleRequiredParamsExceptionResult = exceptionHandler
                .handleRequiredParamsException(new IOException("An error occurred"));
        assertEquals(2, actualHandleRequiredParamsExceptionResult.size());
        assertEquals("An error occurred", actualHandleRequiredParamsExceptionResult.get("errorMessage"));
        assertEquals("Ошибка статуса: 400", actualHandleRequiredParamsExceptionResult.get("error"));
    }

    /**
     * Method under test: {@link ExceptionHandler#handleNotPublishedException(Exception)}
     */
    @Test
    void testHandleNotPublishedException() {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Map<String, String> actualHandleNotPublishedExceptionResult = exceptionHandler
                .handleNotPublishedException(new IOException("An error occurred"));
        assertEquals(2, actualHandleNotPublishedExceptionResult.size());
        assertEquals("An error occurred", actualHandleNotPublishedExceptionResult.get("errorMessage"));
        assertEquals("Ошибка статуса: 404", actualHandleNotPublishedExceptionResult.get("error"));
    }

    /**
     * Method under test: {@link ExceptionHandler#handleConflictException(Exception)}
     */
    @Test
    void testHandleConflictException() {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Map<String, String> actualHandleConflictExceptionResult = exceptionHandler
                .handleConflictException(new IOException("An error occurred"));
        assertEquals(2, actualHandleConflictExceptionResult.size());
        assertEquals("An error occurred", actualHandleConflictExceptionResult.get("errorMessage"));
        assertEquals("Ошибка статуса: 409", actualHandleConflictExceptionResult.get("error"));
    }

    /**
     * Method under test: {@link ExceptionHandler#handleBadTimeException(Exception)}
     */
    @Test
    void testHandleBadTimeException() {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Map<String, String> actualHandleBadTimeExceptionResult = exceptionHandler
                .handleBadTimeException(new IOException("An error occurred"));
        assertEquals(2, actualHandleBadTimeExceptionResult.size());
        assertEquals("An error occurred", actualHandleBadTimeExceptionResult.get("errorMessage"));
        assertEquals("Ошибка статуса: 400", actualHandleBadTimeExceptionResult.get("error"));
    }
}

