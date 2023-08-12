package ru.practicum.base.dto.comments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.utils.Status;

class CommentDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CommentDto#CommentDto()}
     *   <li>{@link CommentDto#setCommentatorId(Long)}
     *   <li>{@link CommentDto#setCreated(LocalDateTime)}
     *   <li>{@link CommentDto#setEventId(Long)}
     *   <li>{@link CommentDto#setId(Long)}
     *   <li>{@link CommentDto#setStatus(Status)}
     *   <li>{@link CommentDto#setText(String)}
     *   <li>{@link CommentDto#getCommentatorId()}
     *   <li>{@link CommentDto#getCreated()}
     *   <li>{@link CommentDto#getEventId()}
     *   <li>{@link CommentDto#getId()}
     *   <li>{@link CommentDto#getStatus()}
     *   <li>{@link CommentDto#getText()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CommentDto actualCommentDto = new CommentDto();
        actualCommentDto.setCommentatorId(1L);
        LocalDateTime created = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCommentDto.setCreated(created);
        actualCommentDto.setEventId(1L);
        actualCommentDto.setId(1L);
        actualCommentDto.setStatus(Status.CONFIRMED);
        actualCommentDto.setText("Text");
        assertEquals(1L, actualCommentDto.getCommentatorId().longValue());
        assertSame(created, actualCommentDto.getCreated());
        assertEquals(1L, actualCommentDto.getEventId().longValue());
        assertEquals(1L, actualCommentDto.getId().longValue());
        assertEquals(Status.CONFIRMED, actualCommentDto.getStatus());
        assertEquals("Text", actualCommentDto.getText());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CommentDto#CommentDto(Long, String, LocalDateTime, Long, Long, Status)}
     *   <li>{@link CommentDto#setCommentatorId(Long)}
     *   <li>{@link CommentDto#setCreated(LocalDateTime)}
     *   <li>{@link CommentDto#setEventId(Long)}
     *   <li>{@link CommentDto#setId(Long)}
     *   <li>{@link CommentDto#setStatus(Status)}
     *   <li>{@link CommentDto#setText(String)}
     *   <li>{@link CommentDto#getCommentatorId()}
     *   <li>{@link CommentDto#getCreated()}
     *   <li>{@link CommentDto#getEventId()}
     *   <li>{@link CommentDto#getId()}
     *   <li>{@link CommentDto#getStatus()}
     *   <li>{@link CommentDto#getText()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        CommentDto actualCommentDto = new CommentDto(1L, "Text", LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 1L,
                Status.CONFIRMED);
        actualCommentDto.setCommentatorId(1L);
        LocalDateTime created = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCommentDto.setCreated(created);
        actualCommentDto.setEventId(1L);
        actualCommentDto.setId(1L);
        actualCommentDto.setStatus(Status.CONFIRMED);
        actualCommentDto.setText("Text");
        assertEquals(1L, actualCommentDto.getCommentatorId().longValue());
        assertSame(created, actualCommentDto.getCreated());
        assertEquals(1L, actualCommentDto.getEventId().longValue());
        assertEquals(1L, actualCommentDto.getId().longValue());
        assertEquals(Status.CONFIRMED, actualCommentDto.getStatus());
        assertEquals("Text", actualCommentDto.getText());
    }
}

