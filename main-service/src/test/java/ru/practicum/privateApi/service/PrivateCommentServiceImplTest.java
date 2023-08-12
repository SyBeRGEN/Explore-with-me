package ru.practicum.privateApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.exceptions.NotPublishedException;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Comment;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

@ContextConfiguration(classes = {PrivateCommentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PrivateCommentServiceImplTest {
    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private CommentsRepository commentsRepository;

    @Autowired
    private PrivateCommentServiceImpl privateCommentServiceImpl;

    /**
     * Method under test: {@link PrivateCommentServiceImpl#createComment(Long, CommentDto)}
     */
    @Test
    void testCreateComment() {
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
        when(commentMapper.toEntity(Mockito.<CommentDto>any())).thenReturn(comment);
        assertThrows(NotPublishedException.class, () -> privateCommentServiceImpl.createComment(1L, new CommentDto()));
        verify(commentMapper).toEntity(Mockito.<CommentDto>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#createComment(Long, CommentDto)}
     */
    @Test
    void testCreateComment2() {
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

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        User initiator2 = new User();
        initiator2.setEmail("jane.doe@example.org");
        initiator2.setId(1L);
        initiator2.setName("Name");

        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);

        Event event2 = new Event();
        event2.setAnnotation("Annotation");
        event2.setCategory(category2);
        event2.setConfirmedRequests(1L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("The characteristics of someone or something");
        event2.setId(1L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(true);
        event2.setParticipantLimit(1L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(true);
        event2.setState(State.PENDING);
        event2.setTitle("Dr");
        event2.setViews(1L);
        Comment comment = mock(Comment.class);
        when(comment.getEvent()).thenReturn(event2);
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
        when(commentMapper.toEntity(Mockito.<CommentDto>any())).thenReturn(comment);
        assertThrows(NotPublishedException.class, () -> privateCommentServiceImpl.createComment(1L, new CommentDto()));
        verify(commentMapper).toEntity(Mockito.<CommentDto>any());
        verify(comment).getEvent();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#createComment(Long, CommentDto)}
     */
    @Test
    void testCreateComment3() {
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
        when(commentsRepository.save(Mockito.<Comment>any())).thenReturn(comment);

        User commentator2 = new User();
        commentator2.setEmail("jane.doe@example.org");
        commentator2.setId(1L);
        commentator2.setName("Name");

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        User initiator2 = new User();
        initiator2.setEmail("jane.doe@example.org");
        initiator2.setId(1L);
        initiator2.setName("Name");

        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);

        Event event2 = new Event();
        event2.setAnnotation("Annotation");
        event2.setCategory(category2);
        event2.setConfirmedRequests(1L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("The characteristics of someone or something");
        event2.setId(1L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(true);
        event2.setParticipantLimit(1L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(true);
        event2.setState(State.PENDING);
        event2.setTitle("Dr");
        event2.setViews(1L);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");

        User initiator3 = new User();
        initiator3.setEmail("jane.doe@example.org");
        initiator3.setId(1L);
        initiator3.setName("Name");

        Location location3 = new Location();
        location3.setLat(10.0f);
        location3.setLon(10.0f);
        Event event3 = mock(Event.class);
        when(event3.getState()).thenReturn(State.PUBLISHED);
        doNothing().when(event3).setAnnotation(Mockito.<String>any());
        doNothing().when(event3).setCategory(Mockito.<Category>any());
        doNothing().when(event3).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event3).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event3).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event3).setDescription(Mockito.<String>any());
        doNothing().when(event3).setId(Mockito.<Long>any());
        doNothing().when(event3).setInitiator(Mockito.<User>any());
        doNothing().when(event3).setLocation(Mockito.<Location>any());
        doNothing().when(event3).setPaid(Mockito.<Boolean>any());
        doNothing().when(event3).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event3).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event3).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event3).setState(Mockito.<State>any());
        doNothing().when(event3).setTitle(Mockito.<String>any());
        doNothing().when(event3).setViews(Mockito.<Long>any());
        event3.setAnnotation("Annotation");
        event3.setCategory(category3);
        event3.setConfirmedRequests(1L);
        event3.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setDescription("The characteristics of someone or something");
        event3.setId(1L);
        event3.setInitiator(initiator3);
        event3.setLocation(location3);
        event3.setPaid(true);
        event3.setParticipantLimit(1L);
        event3.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setRequestModeration(true);
        event3.setState(State.PENDING);
        event3.setTitle("Dr");
        event3.setViews(1L);
        Comment comment2 = mock(Comment.class);
        when(comment2.getEvent()).thenReturn(event3);
        doNothing().when(comment2).setCommentator(Mockito.<User>any());
        doNothing().when(comment2).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment2).setEvent(Mockito.<Event>any());
        doNothing().when(comment2).setId(Mockito.<Long>any());
        doNothing().when(comment2).setStatus(Mockito.<Status>any());
        doNothing().when(comment2).setText(Mockito.<String>any());
        comment2.setCommentator(commentator2);
        comment2.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment2.setEvent(event2);
        comment2.setId(1L);
        comment2.setStatus(Status.CONFIRMED);
        comment2.setText("Text");
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);
        when(commentMapper.toEntity(Mockito.<CommentDto>any())).thenReturn(comment2);
        CommentDto commentDto2 = new CommentDto();
        assertSame(commentDto, privateCommentServiceImpl.createComment(1L, commentDto2));
        verify(commentsRepository).save(Mockito.<Comment>any());
        verify(commentMapper).toDto(Mockito.<Comment>any());
        verify(commentMapper).toEntity(Mockito.<CommentDto>any());
        verify(comment2).getEvent();
        verify(comment2).setCommentator(Mockito.<User>any());
        verify(comment2).setCreated(Mockito.<LocalDateTime>any());
        verify(comment2).setEvent(Mockito.<Event>any());
        verify(comment2).setId(Mockito.<Long>any());
        verify(comment2).setStatus(Mockito.<Status>any());
        verify(comment2).setText(Mockito.<String>any());
        verify(event3).getState();
        verify(event3).setAnnotation(Mockito.<String>any());
        verify(event3).setCategory(Mockito.<Category>any());
        verify(event3).setConfirmedRequests(Mockito.<Long>any());
        verify(event3).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event3).setDate(Mockito.<LocalDateTime>any());
        verify(event3).setDescription(Mockito.<String>any());
        verify(event3).setId(Mockito.<Long>any());
        verify(event3).setInitiator(Mockito.<User>any());
        verify(event3).setLocation(Mockito.<Location>any());
        verify(event3).setPaid(Mockito.<Boolean>any());
        verify(event3).setParticipantLimit(Mockito.<Long>any());
        verify(event3).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event3).setRequestModeration(Mockito.<Boolean>any());
        verify(event3).setState(Mockito.<State>any());
        verify(event3).setTitle(Mockito.<String>any());
        verify(event3).setViews(Mockito.<Long>any());
        assertEquals(1L, commentDto2.getCommentatorId().longValue());
        assertEquals(Status.PENDING, commentDto2.getStatus());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#createComment(Long, CommentDto)}
     */
    @Test
    void testCreateComment4() {
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
        when(commentsRepository.save(Mockito.<Comment>any())).thenReturn(comment);

        User commentator2 = new User();
        commentator2.setEmail("jane.doe@example.org");
        commentator2.setId(1L);
        commentator2.setName("Name");

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        User initiator2 = new User();
        initiator2.setEmail("jane.doe@example.org");
        initiator2.setId(1L);
        initiator2.setName("Name");

        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);

        Event event2 = new Event();
        event2.setAnnotation("Annotation");
        event2.setCategory(category2);
        event2.setConfirmedRequests(1L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("The characteristics of someone or something");
        event2.setId(1L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(true);
        event2.setParticipantLimit(1L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(true);
        event2.setState(State.PENDING);
        event2.setTitle("Dr");
        event2.setViews(1L);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");

        User initiator3 = new User();
        initiator3.setEmail("jane.doe@example.org");
        initiator3.setId(1L);
        initiator3.setName("Name");

        Location location3 = new Location();
        location3.setLat(10.0f);
        location3.setLon(10.0f);
        Event event3 = mock(Event.class);
        when(event3.getState()).thenReturn(State.PUBLISHED);
        doNothing().when(event3).setAnnotation(Mockito.<String>any());
        doNothing().when(event3).setCategory(Mockito.<Category>any());
        doNothing().when(event3).setConfirmedRequests(Mockito.<Long>any());
        doNothing().when(event3).setCreatedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event3).setDate(Mockito.<LocalDateTime>any());
        doNothing().when(event3).setDescription(Mockito.<String>any());
        doNothing().when(event3).setId(Mockito.<Long>any());
        doNothing().when(event3).setInitiator(Mockito.<User>any());
        doNothing().when(event3).setLocation(Mockito.<Location>any());
        doNothing().when(event3).setPaid(Mockito.<Boolean>any());
        doNothing().when(event3).setParticipantLimit(Mockito.<Long>any());
        doNothing().when(event3).setPublishedOn(Mockito.<LocalDateTime>any());
        doNothing().when(event3).setRequestModeration(Mockito.<Boolean>any());
        doNothing().when(event3).setState(Mockito.<State>any());
        doNothing().when(event3).setTitle(Mockito.<String>any());
        doNothing().when(event3).setViews(Mockito.<Long>any());
        event3.setAnnotation("Annotation");
        event3.setCategory(category3);
        event3.setConfirmedRequests(1L);
        event3.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setDescription("The characteristics of someone or something");
        event3.setId(1L);
        event3.setInitiator(initiator3);
        event3.setLocation(location3);
        event3.setPaid(true);
        event3.setParticipantLimit(1L);
        event3.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event3.setRequestModeration(true);
        event3.setState(State.PENDING);
        event3.setTitle("Dr");
        event3.setViews(1L);
        Comment comment2 = mock(Comment.class);
        when(comment2.getEvent()).thenReturn(event3);
        doNothing().when(comment2).setCommentator(Mockito.<User>any());
        doNothing().when(comment2).setCreated(Mockito.<LocalDateTime>any());
        doNothing().when(comment2).setEvent(Mockito.<Event>any());
        doNothing().when(comment2).setId(Mockito.<Long>any());
        doNothing().when(comment2).setStatus(Mockito.<Status>any());
        doNothing().when(comment2).setText(Mockito.<String>any());
        comment2.setCommentator(commentator2);
        comment2.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment2.setEvent(event2);
        comment2.setId(1L);
        comment2.setStatus(Status.CONFIRMED);
        comment2.setText("Text");
        when(commentMapper.toDto(Mockito.<Comment>any())).thenThrow(new NotPublishedException("An error occurred"));
        when(commentMapper.toEntity(Mockito.<CommentDto>any())).thenReturn(comment2);
        assertThrows(NotPublishedException.class, () -> privateCommentServiceImpl.createComment(1L, new CommentDto()));
        verify(commentsRepository).save(Mockito.<Comment>any());
        verify(commentMapper).toDto(Mockito.<Comment>any());
        verify(commentMapper).toEntity(Mockito.<CommentDto>any());
        verify(comment2).getEvent();
        verify(comment2).setCommentator(Mockito.<User>any());
        verify(comment2).setCreated(Mockito.<LocalDateTime>any());
        verify(comment2).setEvent(Mockito.<Event>any());
        verify(comment2).setId(Mockito.<Long>any());
        verify(comment2).setStatus(Mockito.<Status>any());
        verify(comment2).setText(Mockito.<String>any());
        verify(event3).getState();
        verify(event3).setAnnotation(Mockito.<String>any());
        verify(event3).setCategory(Mockito.<Category>any());
        verify(event3).setConfirmedRequests(Mockito.<Long>any());
        verify(event3).setCreatedOn(Mockito.<LocalDateTime>any());
        verify(event3).setDate(Mockito.<LocalDateTime>any());
        verify(event3).setDescription(Mockito.<String>any());
        verify(event3).setId(Mockito.<Long>any());
        verify(event3).setInitiator(Mockito.<User>any());
        verify(event3).setLocation(Mockito.<Location>any());
        verify(event3).setPaid(Mockito.<Boolean>any());
        verify(event3).setParticipantLimit(Mockito.<Long>any());
        verify(event3).setPublishedOn(Mockito.<LocalDateTime>any());
        verify(event3).setRequestModeration(Mockito.<Boolean>any());
        verify(event3).setState(Mockito.<State>any());
        verify(event3).setTitle(Mockito.<String>any());
        verify(event3).setViews(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#deleteComment(Long)}
     */
    @Test
    void testDeleteComment() {
        doNothing().when(commentsRepository).deleteById(Mockito.<Long>any());
        privateCommentServiceImpl.deleteComment(1L);
        verify(commentsRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#deleteComment(Long)}
     */
    @Test
    void testDeleteComment2() {
        doThrow(new NotPublishedException("An error occurred")).when(commentsRepository).deleteById(Mockito.<Long>any());
        assertThrows(NotPublishedException.class, () -> privateCommentServiceImpl.deleteComment(1L));
        verify(commentsRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#updateComment(Long, CommentDto)}
     */
    @Test
    void testUpdateComment() {
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
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);
        assertSame(commentDto, privateCommentServiceImpl.updateComment(1L, new CommentDto()));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(commentMapper).toDto(Mockito.<Comment>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#updateComment(Long, CommentDto)}
     */
    @Test
    void testUpdateComment2() {
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
        when(commentMapper.toDto(Mockito.<Comment>any())).thenThrow(new NotPublishedException("An error occurred"));
        assertThrows(NotPublishedException.class, () -> privateCommentServiceImpl.updateComment(1L, new CommentDto()));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(commentMapper).toDto(Mockito.<Comment>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#updateComment(Long, CommentDto)}
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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Comment comment = mock(Comment.class);
        when(comment.getCommentator()).thenReturn(user);
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
        CommentDto commentDto = new CommentDto();
        when(commentMapper.toDto(Mockito.<Comment>any())).thenReturn(commentDto);
        assertSame(commentDto, privateCommentServiceImpl.updateComment(1L, new CommentDto()));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
        verify(comment).getCommentator();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment, atLeast(1)).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment, atLeast(1)).setStatus(Mockito.<Status>any());
        verify(comment, atLeast(1)).setText(Mockito.<String>any());
        verify(commentMapper).toDto(Mockito.<Comment>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#updateComment(Long, CommentDto)}
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
        User user = mock(User.class);
        when(user.getId()).thenReturn(-1L);
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setName(Mockito.<String>any());
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Comment comment = mock(Comment.class);
        when(comment.getCommentator()).thenReturn(user);
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
        assertThrows(ConflictException.class, () -> privateCommentServiceImpl.updateComment(1L, new CommentDto()));
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(comment).getCommentator();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
        verify(user).getId();
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#updateComment(Long, CommentDto)}
     */
    @Test
    void testUpdateComment5() {
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> privateCommentServiceImpl.updateComment(1L, new CommentDto()));
        verify(commentsRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PrivateCommentServiceImpl#getCommentsByUserId(Long)}
     */
    @Test
    void testGetCommentsByUserId() {
        when(commentsRepository.findByCommentator_IdOrderByCreatedAsc(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertTrue(privateCommentServiceImpl.getCommentsByUserId(1L).isEmpty());
        verify(commentsRepository).findByCommentator_IdOrderByCreatedAsc(Mockito.<Long>any());
    }
}

