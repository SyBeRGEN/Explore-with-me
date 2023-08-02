package ru.practicum;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EndpointHitDtoTest {
    @Test
    void testConstructor() {
        EndpointHitDto actualEndpointHitDto = new EndpointHitDto();
        assertNull(actualEndpointHitDto.getApp());
        assertNull(actualEndpointHitDto.getId());
        assertNull(actualEndpointHitDto.getIp());
        assertNull(actualEndpointHitDto.getTimestamp());
        assertNull(actualEndpointHitDto.getUri());
    }

    @Test
    void testConstructor2() {
        LocalDateTime timestamp = LocalDate.of(1970, 1, 1).atStartOfDay();
        EndpointHitDto actualEndpointHitDto = new EndpointHitDto(1L, "App", "Uri", "127.0.0.1", timestamp);

        assertEquals("App", actualEndpointHitDto.getApp());
        assertEquals(1L, actualEndpointHitDto.getId().longValue());
        assertSame(timestamp, actualEndpointHitDto.getTimestamp());
        assertEquals("Uri", actualEndpointHitDto.getUri());
    }
}