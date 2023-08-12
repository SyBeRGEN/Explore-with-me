package ru.practicum.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Comment;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

@ContextConfiguration(classes = {CommentMapper.class})
@ExtendWith(SpringExtension.class)
class CommentMapperTest {
    @Autowired
    private CommentMapper commentMapper;

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link CommentMapper#toDto(Comment)}
     */
    @Test
    void testToDto() {
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
        CommentDto actualToDtoResult = commentMapper.toDto(comment);
        assertEquals(1L, actualToDtoResult.getCommentatorId().longValue());
        assertEquals("Text", actualToDtoResult.getText());
        assertEquals(Status.CONFIRMED, actualToDtoResult.getStatus());
        assertEquals(1L, actualToDtoResult.getId().longValue());
        assertEquals(1L, actualToDtoResult.getEventId().longValue());
        assertEquals("1970-01-01", actualToDtoResult.getCreated().toLocalDate().toString());
    }

    /**
     * Method under test: {@link CommentMapper#toDto(Comment)}
     */
    @Test
    void testToDto2() {
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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Comment comment = mock(Comment.class);
        when(comment.getStatus()).thenReturn(Status.CONFIRMED);
        when(comment.getCommentator()).thenReturn(user);
        when(comment.getId()).thenReturn(1L);
        when(comment.getText()).thenReturn("Text");
        when(comment.getCreated()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
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
        CommentDto actualToDtoResult = commentMapper.toDto(comment);
        assertEquals(1L, actualToDtoResult.getCommentatorId().longValue());
        assertEquals("Text", actualToDtoResult.getText());
        assertEquals(Status.CONFIRMED, actualToDtoResult.getStatus());
        assertEquals(1L, actualToDtoResult.getId().longValue());
        assertEquals(1L, actualToDtoResult.getEventId().longValue());
        assertEquals("1970-01-01", actualToDtoResult.getCreated().toLocalDate().toString());
        verify(comment).getId();
        verify(comment).getText();
        verify(comment).getCreated();
        verify(comment).getEvent();
        verify(comment).getCommentator();
        verify(comment).getStatus();
        verify(comment).setCommentator(Mockito.<User>any());
        verify(comment).setCreated(Mockito.<LocalDateTime>any());
        verify(comment).setEvent(Mockito.<Event>any());
        verify(comment).setId(Mockito.<Long>any());
        verify(comment).setStatus(Mockito.<Status>any());
        verify(comment).setText(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CommentMapper#toEntity(CommentDto)}
     */
    @Test
    void testToEntity() {
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        Comment actualToEntityResult = commentMapper.toEntity(new CommentDto());
        assertSame(user, actualToEntityResult.getCommentator());
        assertNull(actualToEntityResult.getText());
        assertNull(actualToEntityResult.getCreated());
        assertNull(actualToEntityResult.getId());
        assertNull(actualToEntityResult.getStatus());
        assertSame(event, actualToEntityResult.getEvent());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CommentMapper#toEntity(CommentDto)}
     */
    @Test
    void testToEntity2() {
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userRepository.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> commentMapper.toEntity(new CommentDto()));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CommentMapper#toEntity(CommentDto)}
     */
    @Test
    void testToEntity3() {
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> commentMapper.toEntity(new CommentDto()));
        verify(eventRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CommentMapper#toEntity(CommentDto)}
     */
    @Test
    void testToEntity4() {
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> commentMapper.toEntity(new CommentDto()));
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CommentMapper#toEntity(CommentDto)}
     */
    @Test
    void testToEntity5() {
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
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        CommentDto dto = mock(CommentDto.class);
        when(dto.getCommentatorId()).thenReturn(1L);
        when(dto.getEventId()).thenReturn(1L);
        when(dto.getId()).thenReturn(1L);
        when(dto.getText()).thenReturn("Text");
        when(dto.getCreated()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(dto.getStatus()).thenReturn(Status.CONFIRMED);
        Comment actualToEntityResult = commentMapper.toEntity(dto);
        assertSame(user, actualToEntityResult.getCommentator());
        assertEquals("Text", actualToEntityResult.getText());
        assertEquals(1L, actualToEntityResult.getId().longValue());
        assertEquals(Status.CONFIRMED, actualToEntityResult.getStatus());
        assertEquals("1970-01-01", actualToEntityResult.getCreated().toLocalDate().toString());
        assertSame(event, actualToEntityResult.getEvent());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
        verify(dto).getCommentatorId();
        verify(dto).getEventId();
        verify(dto).getId();
        verify(dto).getText();
        verify(dto).getCreated();
        verify(dto).getStatus();
    }
}

