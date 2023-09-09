package ru.practicum.publicApi.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.publicApi.service.PublicCommentsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PublicCommentsControllerTest {
    /**
     * Method under test: {@link PublicCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments() {
        PublicCommentsService service = mock(PublicCommentsService.class);
        when(service.getComments(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(new ArrayList<>());
        PublicCommentsController publicCommentsController = new PublicCommentsController(service);
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = publicCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L);
        assertEquals(events, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(service).getComments(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PublicCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments2() {
        PublicCommentsService service = mock(PublicCommentsService.class);
        when(service.getComments(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(new ArrayList<>());
        PublicCommentsController publicCommentsController = new PublicCommentsController(service);

        ArrayList<Long> events = new ArrayList<>();
        events.add(6L);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = publicCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L);
        assertTrue(actualComments.hasBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(service).getComments(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PublicCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments3() {
        PublicCommentsService service = mock(PublicCommentsService.class);
        when(service.getComments(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(new ArrayList<>());
        PublicCommentsController publicCommentsController = new PublicCommentsController(service);

        ArrayList<Long> events = new ArrayList<>();
        events.add(1L);
        events.add(6L);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = publicCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L);
        assertTrue(actualComments.hasBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(service).getComments(Mockito.<String>any(), Mockito.<List<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Long>any(), Mockito.<Long>any());
    }
}

