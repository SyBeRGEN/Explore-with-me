package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.user.UserShortDto;

class EventShortDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventShortDto#EventShortDto()}
     *   <li>{@link EventShortDto#setAnnotation(String)}
     *   <li>{@link EventShortDto#setCategory(CategoryDto)}
     *   <li>{@link EventShortDto#setConfirmedRequests(Long)}
     *   <li>{@link EventShortDto#setEventDate(LocalDateTime)}
     *   <li>{@link EventShortDto#setId(Long)}
     *   <li>{@link EventShortDto#setInitiator(UserShortDto)}
     *   <li>{@link EventShortDto#setPaid(Boolean)}
     *   <li>{@link EventShortDto#setTitle(String)}
     *   <li>{@link EventShortDto#setViews(Long)}
     *   <li>{@link EventShortDto#getAnnotation()}
     *   <li>{@link EventShortDto#getCategory()}
     *   <li>{@link EventShortDto#getConfirmedRequests()}
     *   <li>{@link EventShortDto#getEventDate()}
     *   <li>{@link EventShortDto#getId()}
     *   <li>{@link EventShortDto#getInitiator()}
     *   <li>{@link EventShortDto#getPaid()}
     *   <li>{@link EventShortDto#getTitle()}
     *   <li>{@link EventShortDto#getViews()}
     * </ul>
     */
    @Test
    void testConstructor() {
        EventShortDto actualEventShortDto = new EventShortDto();
        actualEventShortDto.setAnnotation("Annotation");
        CategoryDto category = new CategoryDto();
        actualEventShortDto.setCategory(category);
        actualEventShortDto.setConfirmedRequests(1L);
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventShortDto.setEventDate(eventDate);
        actualEventShortDto.setId(1L);
        UserShortDto initiator = new UserShortDto();
        actualEventShortDto.setInitiator(initiator);
        actualEventShortDto.setPaid(true);
        actualEventShortDto.setTitle("Dr");
        actualEventShortDto.setViews(1L);
        assertEquals("Annotation", actualEventShortDto.getAnnotation());
        assertSame(category, actualEventShortDto.getCategory());
        assertEquals(1L, actualEventShortDto.getConfirmedRequests().longValue());
        assertSame(eventDate, actualEventShortDto.getEventDate());
        assertEquals(1L, actualEventShortDto.getId().longValue());
        assertSame(initiator, actualEventShortDto.getInitiator());
        assertTrue(actualEventShortDto.getPaid());
        assertEquals("Dr", actualEventShortDto.getTitle());
        assertEquals(1L, actualEventShortDto.getViews().longValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventShortDto#EventShortDto(String, CategoryDto, Long, LocalDateTime, Long, UserShortDto, Boolean, String, Long)}
     *   <li>{@link EventShortDto#setAnnotation(String)}
     *   <li>{@link EventShortDto#setCategory(CategoryDto)}
     *   <li>{@link EventShortDto#setConfirmedRequests(Long)}
     *   <li>{@link EventShortDto#setEventDate(LocalDateTime)}
     *   <li>{@link EventShortDto#setId(Long)}
     *   <li>{@link EventShortDto#setInitiator(UserShortDto)}
     *   <li>{@link EventShortDto#setPaid(Boolean)}
     *   <li>{@link EventShortDto#setTitle(String)}
     *   <li>{@link EventShortDto#setViews(Long)}
     *   <li>{@link EventShortDto#getAnnotation()}
     *   <li>{@link EventShortDto#getCategory()}
     *   <li>{@link EventShortDto#getConfirmedRequests()}
     *   <li>{@link EventShortDto#getEventDate()}
     *   <li>{@link EventShortDto#getId()}
     *   <li>{@link EventShortDto#getInitiator()}
     *   <li>{@link EventShortDto#getPaid()}
     *   <li>{@link EventShortDto#getTitle()}
     *   <li>{@link EventShortDto#getViews()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        CategoryDto category = new CategoryDto();
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        EventShortDto actualEventShortDto = new EventShortDto("Annotation", category, 1L, eventDate, 1L,
                new UserShortDto(), true, "Dr", 1L);
        actualEventShortDto.setAnnotation("Annotation");
        CategoryDto category2 = new CategoryDto();
        actualEventShortDto.setCategory(category2);
        actualEventShortDto.setConfirmedRequests(1L);
        LocalDateTime eventDate2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventShortDto.setEventDate(eventDate2);
        actualEventShortDto.setId(1L);
        UserShortDto initiator = new UserShortDto();
        actualEventShortDto.setInitiator(initiator);
        actualEventShortDto.setPaid(true);
        actualEventShortDto.setTitle("Dr");
        actualEventShortDto.setViews(1L);
        assertEquals("Annotation", actualEventShortDto.getAnnotation());
        assertSame(category2, actualEventShortDto.getCategory());
        assertEquals(1L, actualEventShortDto.getConfirmedRequests().longValue());
        assertSame(eventDate2, actualEventShortDto.getEventDate());
        assertEquals(1L, actualEventShortDto.getId().longValue());
        assertSame(initiator, actualEventShortDto.getInitiator());
        assertTrue(actualEventShortDto.getPaid());
        assertEquals("Dr", actualEventShortDto.getTitle());
        assertEquals(1L, actualEventShortDto.getViews().longValue());
    }
}

