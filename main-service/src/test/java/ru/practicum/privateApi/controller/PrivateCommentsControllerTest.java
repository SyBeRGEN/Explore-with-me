package ru.practicum.privateApi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Comment;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;
import ru.practicum.privateApi.service.PrivateCommentService;
import ru.practicum.privateApi.service.PrivateCommentServiceImpl;

@ContextConfiguration(classes = {PrivateCommentsController.class})
@ExtendWith(SpringExtension.class)
class PrivateCommentsControllerTest {
    @MockBean
    private PrivateCommentService privateCommentService;

    @Autowired
    private PrivateCommentsController privateCommentsController;

    /**
     * Method under test: {@link PrivateCommentsController#createComment(Long, CommentDto)}
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
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        when(commentsRepository.save(Mockito.<Comment>any())).thenReturn(comment);

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
        event2.setState(State.PUBLISHED);
        event2.setTitle("Dr");
        event2.setViews(1L);
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(event2));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(user));
        PrivateCommentsController privateCommentsController = new PrivateCommentsController(
                new PrivateCommentServiceImpl(commentsRepository, new CommentMapper(eventRepository, userRepository)));
        CommentDto commentDto = new CommentDto();
        ResponseEntity<CommentDto> actualCreateCommentResult = privateCommentsController.createComment(1L, commentDto);
        assertTrue(actualCreateCommentResult.hasBody());
        assertTrue(actualCreateCommentResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualCreateCommentResult.getStatusCode());
        CommentDto body = actualCreateCommentResult.getBody();
        assertEquals(1L, body.getEventId().longValue());
        assertEquals(1L, body.getCommentatorId().longValue());
        assertNull(body.getText());
        assertNull(body.getId());
        assertEquals(Status.PENDING, body.getStatus());
        verify(commentsRepository).save(Mockito.<Comment>any());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
        assertEquals(1L, commentDto.getCommentatorId().longValue());
        assertEquals(Status.PENDING, commentDto.getStatus());
    }

    /**
     * Method under test: {@link PrivateCommentsController#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(privateCommentService).deleteComment(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/users/{userId}/comments/{commentId}", 1L, 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(privateCommentsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link PrivateCommentsController#deleteComment(Long, Long)}
     */
    @Test
    void testDeleteComment2() throws Exception {
        doNothing().when(privateCommentService).deleteComment(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/users/{userId}/comments/{commentId}", 1L, 1L);
        requestBuilder.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(privateCommentsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link PrivateCommentsController#updateComment(Long, CommentDto)}
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
        CommentsRepository commentsRepository = mock(CommentsRepository.class);
        doNothing().when(commentsRepository).flush();
        when(commentsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        PrivateCommentsController privateCommentsController = new PrivateCommentsController(new PrivateCommentServiceImpl(
                commentsRepository, new CommentMapper(mock(EventRepository.class), mock(UserRepository.class))));
        ResponseEntity<CommentDto> actualUpdateCommentResult = privateCommentsController.updateComment(1L,
                new CommentDto());
        assertTrue(actualUpdateCommentResult.hasBody());
        assertTrue(actualUpdateCommentResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateCommentResult.getStatusCode());
        CommentDto body = actualUpdateCommentResult.getBody();
        assertEquals(1L, body.getEventId().longValue());
        assertEquals(1L, body.getCommentatorId().longValue());
        assertNull(body.getText());
        assertEquals(1L, body.getId().longValue());
        assertEquals(Status.PENDING, body.getStatus());
        verify(commentsRepository).findById(Mockito.<Long>any());
        verify(commentsRepository).flush();
    }

    /**
     * Method under test: {@link PrivateCommentsController#getCommentsByUserId(Long)}
     */
    @Test
    void testGetCommentsByUserId() throws Exception {
        when(privateCommentService.getCommentsByUserId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/comments", 1L);
        MockMvcBuilders.standaloneSetup(privateCommentsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrivateCommentsController#getCommentsByUserId(Long)}
     */
    @Test
    void testGetCommentsByUserId2() throws Exception {
        when(privateCommentService.getCommentsByUserId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}/comments", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(privateCommentsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

