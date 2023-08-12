package ru.practicum.adminApi.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.adminApi.service.AdminCommentsService;
import ru.practicum.adminApi.service.AdminCommentsServiceImpl;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.model.Comment;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AdminCommentsControllerTest {
    /**
     * Method under test: {@link AdminCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments() {
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(), Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any()))
                .thenReturn(new ArrayList<>());
        AdminCommentsController adminCommentsController = new AdminCommentsController(new AdminCommentsServiceImpl(
                new CommentMapper(mock(EventRepository.class), mock(UserRepository.class)), commentsRepository));
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = adminCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), Status.CONFIRMED, 1L, 3L);
        assertEquals(events, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments2() {
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        ArrayList<Comment> commentList = new ArrayList<>();
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(commentList);
        AdminCommentsController adminCommentsController = new AdminCommentsController(new AdminCommentsServiceImpl(
                new CommentMapper(mock(EventRepository.class), mock(UserRepository.class)), commentsRepository));

        ArrayList<Long> events = new ArrayList<>();
        events.add(1L);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = adminCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), Status.CONFIRMED, 1L, 3L);
        assertEquals(commentList, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments3() {
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        ArrayList<Comment> commentList = new ArrayList<>();
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(commentList);
        AdminCommentsController adminCommentsController = new AdminCommentsController(new AdminCommentsServiceImpl(
                new CommentMapper(mock(EventRepository.class), mock(UserRepository.class)), commentsRepository));

        ArrayList<Long> events = new ArrayList<>();
        events.add(6L);
        events.add(1L);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = adminCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), Status.CONFIRMED, 1L, 3L);
        assertEquals(commentList, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments4() {
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        AdminCommentsController adminCommentsController = new AdminCommentsController(new AdminCommentsServiceImpl(
                new CommentMapper(mock(EventRepository.class), mock(UserRepository.class)), commentsRepository));
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = adminCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), Status.REJECTED, 1L, 3L);
        assertEquals(events, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments5() {
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        AdminCommentsController adminCommentsController = new AdminCommentsController(new AdminCommentsServiceImpl(
                new CommentMapper(mock(EventRepository.class), mock(UserRepository.class)), commentsRepository));
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = adminCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), Status.PENDING, 1L, 3L);
        assertEquals(events, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments6() {
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        AdminCommentsController adminCommentsController = new AdminCommentsController(new AdminCommentsServiceImpl(
                new CommentMapper(mock(EventRepository.class), mock(UserRepository.class)), commentsRepository));
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<List<CommentDto>> actualComments = adminCommentsController.getComments("Text", events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), Status.CANCELED, 1L, 3L);
        assertEquals(events, actualComments.getBody());
        assertEquals(HttpStatus.OK, actualComments.getStatusCode());
        assertTrue(actualComments.getHeaders().isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#patchComment(Long, CommentDto)}
     */
    @Test
    void testPatchComment() {
        AdminCommentsService service = mock(AdminCommentsService.class);
        when(service.patchComment(Mockito.<Long>any(), Mockito.<Status>any())).thenReturn(new CommentDto());
        AdminCommentsController adminCommentsController = new AdminCommentsController(service);
        ResponseEntity<CommentDto> actualPatchCommentResult = adminCommentsController.patchComment(1L, new CommentDto());
        assertTrue(actualPatchCommentResult.hasBody());
        assertTrue(actualPatchCommentResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualPatchCommentResult.getStatusCode());
        verify(service).patchComment(Mockito.<Long>any(), Mockito.<Status>any());
    }

    /**
     * Method under test: {@link AdminCommentsController#updateComment(CommentDto)}
     */
    @Test
    void testUpdateComment() {
        AdminCommentsService service = mock(AdminCommentsService.class);
        when(service.updateComment(Mockito.<CommentDto>any())).thenReturn(new CommentDto());
        AdminCommentsController adminCommentsController = new AdminCommentsController(service);
        ResponseEntity<CommentDto> actualUpdateCommentResult = adminCommentsController.updateComment(new CommentDto());
        assertTrue(actualUpdateCommentResult.hasBody());
        assertTrue(actualUpdateCommentResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateCommentResult.getStatusCode());
        verify(service).updateComment(Mockito.<CommentDto>any());
    }
}

