package ru.practicum.base.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import ru.practicum.base.utils.Status;

class ParticipationRequestDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ParticipationRequestDto#ParticipationRequestDto()}
     *   <li>{@link ParticipationRequestDto#setCreated(LocalDateTime)}
     *   <li>{@link ParticipationRequestDto#setEvent(Long)}
     *   <li>{@link ParticipationRequestDto#setId(Long)}
     *   <li>{@link ParticipationRequestDto#setRequester(Long)}
     *   <li>{@link ParticipationRequestDto#setStatus(Status)}
     *   <li>{@link ParticipationRequestDto#getCreated()}
     *   <li>{@link ParticipationRequestDto#getEvent()}
     *   <li>{@link ParticipationRequestDto#getId()}
     *   <li>{@link ParticipationRequestDto#getRequester()}
     *   <li>{@link ParticipationRequestDto#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ParticipationRequestDto actualParticipationRequestDto = new ParticipationRequestDto();
        LocalDateTime created = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualParticipationRequestDto.setCreated(created);
        actualParticipationRequestDto.setEvent(1L);
        actualParticipationRequestDto.setId(1L);
        actualParticipationRequestDto.setRequester(1L);
        actualParticipationRequestDto.setStatus(Status.CONFIRMED);
        assertSame(created, actualParticipationRequestDto.getCreated());
        assertEquals(1L, actualParticipationRequestDto.getEvent().longValue());
        assertEquals(1L, actualParticipationRequestDto.getId().longValue());
        assertEquals(1L, actualParticipationRequestDto.getRequester().longValue());
        assertEquals(Status.CONFIRMED, actualParticipationRequestDto.getStatus());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ParticipationRequestDto#ParticipationRequestDto(LocalDateTime, Long, Long, Long, Status)}
     *   <li>{@link ParticipationRequestDto#setCreated(LocalDateTime)}
     *   <li>{@link ParticipationRequestDto#setEvent(Long)}
     *   <li>{@link ParticipationRequestDto#setId(Long)}
     *   <li>{@link ParticipationRequestDto#setRequester(Long)}
     *   <li>{@link ParticipationRequestDto#setStatus(Status)}
     *   <li>{@link ParticipationRequestDto#getCreated()}
     *   <li>{@link ParticipationRequestDto#getEvent()}
     *   <li>{@link ParticipationRequestDto#getId()}
     *   <li>{@link ParticipationRequestDto#getRequester()}
     *   <li>{@link ParticipationRequestDto#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ParticipationRequestDto actualParticipationRequestDto = new ParticipationRequestDto(
                LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 1L, 1L, Status.CONFIRMED);
        LocalDateTime created = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualParticipationRequestDto.setCreated(created);
        actualParticipationRequestDto.setEvent(1L);
        actualParticipationRequestDto.setId(1L);
        actualParticipationRequestDto.setRequester(1L);
        actualParticipationRequestDto.setStatus(Status.CONFIRMED);
        assertSame(created, actualParticipationRequestDto.getCreated());
        assertEquals(1L, actualParticipationRequestDto.getEvent().longValue());
        assertEquals(1L, actualParticipationRequestDto.getId().longValue());
        assertEquals(1L, actualParticipationRequestDto.getRequester().longValue());
        assertEquals(Status.CONFIRMED, actualParticipationRequestDto.getStatus());
    }
}

