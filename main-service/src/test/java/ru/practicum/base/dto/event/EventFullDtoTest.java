package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.utils.State;

class EventFullDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventFullDto#EventFullDto()}
     *   <li>{@link EventFullDto#setAnnotation(String)}
     *   <li>{@link EventFullDto#setCategory(CategoryDto)}
     *   <li>{@link EventFullDto#setConfirmedRequests(Long)}
     *   <li>{@link EventFullDto#setCreatedOn(LocalDateTime)}
     *   <li>{@link EventFullDto#setDescription(String)}
     *   <li>{@link EventFullDto#setEventDate(LocalDateTime)}
     *   <li>{@link EventFullDto#setId(Long)}
     *   <li>{@link EventFullDto#setInitiator(UserShortDto)}
     *   <li>{@link EventFullDto#setLocation(Location)}
     *   <li>{@link EventFullDto#setPaid(Boolean)}
     *   <li>{@link EventFullDto#setParticipantLimit(Long)}
     *   <li>{@link EventFullDto#setPublishedOn(LocalDateTime)}
     *   <li>{@link EventFullDto#setRequestModeration(Boolean)}
     *   <li>{@link EventFullDto#setState(State)}
     *   <li>{@link EventFullDto#setTitle(String)}
     *   <li>{@link EventFullDto#setViews(Long)}
     *   <li>{@link EventFullDto#getAnnotation()}
     *   <li>{@link EventFullDto#getCategory()}
     *   <li>{@link EventFullDto#getConfirmedRequests()}
     *   <li>{@link EventFullDto#getCreatedOn()}
     *   <li>{@link EventFullDto#getDescription()}
     *   <li>{@link EventFullDto#getEventDate()}
     *   <li>{@link EventFullDto#getId()}
     *   <li>{@link EventFullDto#getInitiator()}
     *   <li>{@link EventFullDto#getLocation()}
     *   <li>{@link EventFullDto#getPaid()}
     *   <li>{@link EventFullDto#getParticipantLimit()}
     *   <li>{@link EventFullDto#getPublishedOn()}
     *   <li>{@link EventFullDto#getRequestModeration()}
     *   <li>{@link EventFullDto#getState()}
     *   <li>{@link EventFullDto#getTitle()}
     *   <li>{@link EventFullDto#getViews()}
     * </ul>
     */
    @Test
    void testConstructor() {
        EventFullDto actualEventFullDto = new EventFullDto();
        actualEventFullDto.setAnnotation("Annotation");
        CategoryDto category = new CategoryDto();
        actualEventFullDto.setCategory(category);
        actualEventFullDto.setConfirmedRequests(1L);
        LocalDateTime createdOn = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventFullDto.setCreatedOn(createdOn);
        actualEventFullDto.setDescription("The characteristics of someone or something");
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventFullDto.setEventDate(eventDate);
        actualEventFullDto.setId(1L);
        UserShortDto initiator = new UserShortDto();
        actualEventFullDto.setInitiator(initiator);
        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        actualEventFullDto.setLocation(location);
        actualEventFullDto.setPaid(true);
        actualEventFullDto.setParticipantLimit(1L);
        LocalDateTime publishedOn = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventFullDto.setPublishedOn(publishedOn);
        actualEventFullDto.setRequestModeration(true);
        actualEventFullDto.setState(State.PENDING);
        actualEventFullDto.setTitle("Dr");
        actualEventFullDto.setViews(1L);
        assertEquals("Annotation", actualEventFullDto.getAnnotation());
        assertSame(category, actualEventFullDto.getCategory());
        assertEquals(1L, actualEventFullDto.getConfirmedRequests().longValue());
        assertSame(createdOn, actualEventFullDto.getCreatedOn());
        assertEquals("The characteristics of someone or something", actualEventFullDto.getDescription());
        assertSame(eventDate, actualEventFullDto.getEventDate());
        assertEquals(1L, actualEventFullDto.getId().longValue());
        assertSame(initiator, actualEventFullDto.getInitiator());
        assertSame(location, actualEventFullDto.getLocation());
        assertTrue(actualEventFullDto.getPaid());
        assertEquals(1L, actualEventFullDto.getParticipantLimit().longValue());
        assertSame(publishedOn, actualEventFullDto.getPublishedOn());
        assertTrue(actualEventFullDto.getRequestModeration());
        assertEquals(State.PENDING, actualEventFullDto.getState());
        assertEquals("Dr", actualEventFullDto.getTitle());
        assertEquals(1L, actualEventFullDto.getViews().longValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventFullDto#EventFullDto(String, CategoryDto, Long, LocalDateTime, String, LocalDateTime, Long, UserShortDto, Location, Boolean, Long, LocalDateTime, Boolean, State, String, Long)}
     *   <li>{@link EventFullDto#setAnnotation(String)}
     *   <li>{@link EventFullDto#setCategory(CategoryDto)}
     *   <li>{@link EventFullDto#setConfirmedRequests(Long)}
     *   <li>{@link EventFullDto#setCreatedOn(LocalDateTime)}
     *   <li>{@link EventFullDto#setDescription(String)}
     *   <li>{@link EventFullDto#setEventDate(LocalDateTime)}
     *   <li>{@link EventFullDto#setId(Long)}
     *   <li>{@link EventFullDto#setInitiator(UserShortDto)}
     *   <li>{@link EventFullDto#setLocation(Location)}
     *   <li>{@link EventFullDto#setPaid(Boolean)}
     *   <li>{@link EventFullDto#setParticipantLimit(Long)}
     *   <li>{@link EventFullDto#setPublishedOn(LocalDateTime)}
     *   <li>{@link EventFullDto#setRequestModeration(Boolean)}
     *   <li>{@link EventFullDto#setState(State)}
     *   <li>{@link EventFullDto#setTitle(String)}
     *   <li>{@link EventFullDto#setViews(Long)}
     *   <li>{@link EventFullDto#getAnnotation()}
     *   <li>{@link EventFullDto#getCategory()}
     *   <li>{@link EventFullDto#getConfirmedRequests()}
     *   <li>{@link EventFullDto#getCreatedOn()}
     *   <li>{@link EventFullDto#getDescription()}
     *   <li>{@link EventFullDto#getEventDate()}
     *   <li>{@link EventFullDto#getId()}
     *   <li>{@link EventFullDto#getInitiator()}
     *   <li>{@link EventFullDto#getLocation()}
     *   <li>{@link EventFullDto#getPaid()}
     *   <li>{@link EventFullDto#getParticipantLimit()}
     *   <li>{@link EventFullDto#getPublishedOn()}
     *   <li>{@link EventFullDto#getRequestModeration()}
     *   <li>{@link EventFullDto#getState()}
     *   <li>{@link EventFullDto#getTitle()}
     *   <li>{@link EventFullDto#getViews()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        CategoryDto category = new CategoryDto();
        LocalDateTime createdOn = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime eventDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        UserShortDto initiator = new UserShortDto();

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);
        EventFullDto actualEventFullDto = new EventFullDto("Annotation", category, 1L, createdOn,
                "The characteristics of someone or something", eventDate, 1L, initiator, location, true, 1L,
                LocalDate.of(1970, 1, 1).atStartOfDay(), true, State.PENDING, "Dr", 1L);
        actualEventFullDto.setAnnotation("Annotation");
        CategoryDto category2 = new CategoryDto();
        actualEventFullDto.setCategory(category2);
        actualEventFullDto.setConfirmedRequests(1L);
        LocalDateTime createdOn2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventFullDto.setCreatedOn(createdOn2);
        actualEventFullDto.setDescription("The characteristics of someone or something");
        LocalDateTime eventDate2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventFullDto.setEventDate(eventDate2);
        actualEventFullDto.setId(1L);
        UserShortDto initiator2 = new UserShortDto();
        actualEventFullDto.setInitiator(initiator2);
        Location location2 = new Location();
        location2.setLat(10.0f);
        location2.setLon(10.0f);
        actualEventFullDto.setLocation(location2);
        actualEventFullDto.setPaid(true);
        actualEventFullDto.setParticipantLimit(1L);
        LocalDateTime publishedOn = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventFullDto.setPublishedOn(publishedOn);
        actualEventFullDto.setRequestModeration(true);
        actualEventFullDto.setState(State.PENDING);
        actualEventFullDto.setTitle("Dr");
        actualEventFullDto.setViews(1L);
        assertEquals("Annotation", actualEventFullDto.getAnnotation());
        assertSame(category2, actualEventFullDto.getCategory());
        assertEquals(1L, actualEventFullDto.getConfirmedRequests().longValue());
        assertSame(createdOn2, actualEventFullDto.getCreatedOn());
        assertEquals("The characteristics of someone or something", actualEventFullDto.getDescription());
        assertSame(eventDate2, actualEventFullDto.getEventDate());
        assertEquals(1L, actualEventFullDto.getId().longValue());
        assertSame(initiator2, actualEventFullDto.getInitiator());
        assertSame(location2, actualEventFullDto.getLocation());
        assertTrue(actualEventFullDto.getPaid());
        assertEquals(1L, actualEventFullDto.getParticipantLimit().longValue());
        assertSame(publishedOn, actualEventFullDto.getPublishedOn());
        assertTrue(actualEventFullDto.getRequestModeration());
        assertEquals(State.PENDING, actualEventFullDto.getState());
        assertEquals("Dr", actualEventFullDto.getTitle());
        assertEquals(1L, actualEventFullDto.getViews().longValue());
    }
}

