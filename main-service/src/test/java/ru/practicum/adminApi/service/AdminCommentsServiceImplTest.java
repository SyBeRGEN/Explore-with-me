package ru.practicum.adminApi.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Comment;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

@ContextConfiguration(classes = {AdminCommentsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminCommentsServiceImplTest {
    @Autowired
    private AdminCommentsServiceImpl adminCommentsServiceImpl;

    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private CommentsRepository commentsRepository;

    /**
     * Method under test: {@link AdminCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments() {
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(), Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any()))
                .thenReturn(new ArrayList<>());
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(adminCommentsServiceImpl
                .getComments("Text", events, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), Status.CONFIRMED, 1L, 3L)
                .isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Status, Long, Long)}
     */
    @Test
    void testGetComments2() {
        when(commentsRepository.findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(adminCommentsServiceImpl
                .getComments(null, events, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), Status.CONFIRMED, 1L, 3L)
                .isEmpty());
        verify(commentsRepository).findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Comment comment = new Comment();
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.patchComment(1L, Status.CONFIRMED));
        verify(commentsRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment2() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.CONFIRMED);
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.patchComment(1L, Status.CONFIRMED));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(comment).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment3() {
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);

        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.PENDING);
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doNothing().when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(commentDto, adminCommentsServiceImpl.patchComment(1L, Status.CONFIRMED));
        verify(commentMapper).toDto(Mockito.<Comment>any());
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(comment, atLeast(1)).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment, atLeast(1)).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment4() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.PENDING);
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doThrow(new ConflictException("An error occurred")).when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.patchComment(1L, Status.CONFIRMED));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(comment).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment, atLeast(1)).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment5() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.PENDING);
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doThrow(new DataIntegrityViolationException("Обновлен статус комментария: {}")).when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.patchComment(1L, Status.CONFIRMED));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(comment).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment, atLeast(1)).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment6() {
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> adminCommentsServiceImpl.patchComment(1L, Status.CONFIRMED));
        verify(commentsRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment7() {
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);

        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.PENDING);
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doNothing().when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(commentDto, adminCommentsServiceImpl.patchComment(1L, Status.REJECTED));
        verify(commentMapper).toDto(Mockito.<Comment>any());
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(comment, atLeast(1)).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment, atLeast(1)).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#patchComment(Long, Status)}
     */
    @Test
    void testPatchComment8() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.PENDING);
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.patchComment(1L, Status.PENDING));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(comment).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#updateComment(CommentDto)}
     */
    @Test
    void testUpdateComment() {
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> adminCommentsServiceImpl.updateComment(new CommentDto()));
        verify(commentsRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#updateComment(CommentDto)}
     */
    @Test
    void testUpdateComment2() {
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);

        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Comment comment = new Comment();
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doNothing().when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CommentDto commentDto2 = new CommentDto();
        commentDto2.setText("Text");
        assertSame(commentDto, adminCommentsServiceImpl.updateComment(commentDto2));
        verify(commentMapper).toDto(Mockito.<Comment>any());
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#updateComment(CommentDto)}
     */
    @Test
    void testUpdateComment3() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Comment comment = new Comment();
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doThrow(new ConflictException("An error occurred")).when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CommentDto commentDto = new CommentDto();
        commentDto.setText("Text");
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.updateComment(commentDto));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#updateComment(CommentDto)}
     */
    @Test
    void testUpdateComment4() {
        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Comment comment = new Comment();
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doThrow(new DataIntegrityViolationException("Обновлен комментарий: {}")).when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CommentDto commentDto = new CommentDto();
        commentDto.setText("Text");
        assertThrows(ConflictException.class, () -> adminCommentsServiceImpl.updateComment(commentDto));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
    }

    /**
     * Method under test: {@link AdminCommentsServiceImpl#updateComment(CommentDto)}
     */
    @Test
    void testUpdateComment5() {
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);

        User commentator = new User();
        commentator.setEmail("jane.doe@example.org");
        commentator.setId(1L);
        commentator.setName("Name");

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getText()).thenReturn("Text");
        doNothing().when(comment).setCommentator(Mockito.<User>any());
        doNothing().when(comment).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment).setEvent(Mockito.<Event>any());
        doNothing().when(comment).setId(Mockito.<Long>any());
        doNothing().when(comment).setStatus(Mockito.<Status>any());
        doNothing().when(comment).setText(Mockito.<String>any());
        comment.setCommentator(commentator);
        comment.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEvent(event);
        comment.setId(1L);
        comment.setStatus(Status.CONFIRMED);
        comment.setText("Text");
        Optional<Comment> ofResult = Optional.of(comment);
        doNothing().when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CommentDto commentDto2 = new CommentDto();
        commentDto2.setText("Text");
        assertSame(commentDto, adminCommentsServiceImpl.updateComment(commentDto2));
        verify(commentMapper).toDto(Mockito.<Comment>any());
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(comment).getText();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment, atLeast(1)).setStatus(Mockito.<Status>any());
        verify(comment, atLeast(1)).setText(Mockito.<String>any());
    }
}

