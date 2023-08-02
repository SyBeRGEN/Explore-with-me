package ru.practicum.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class EndpointHitTest {

    @Test
    void testConstructor() {
        EndpointHit actualEndpointHit = new EndpointHit();
        actualEndpointHit.setApp("App");
        actualEndpointHit.setId(1L);
        actualEndpointHit.setIp("127.0.0.1");
        LocalDateTime timestamp = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEndpointHit.setTimestamp(timestamp);
        actualEndpointHit.setUri("Uri");
        assertEquals("App", actualEndpointHit.getApp());
        assertEquals(1L, actualEndpointHit.getId().longValue());
        assertSame(timestamp, actualEndpointHit.getTimestamp());
        assertEquals("Uri", actualEndpointHit.getUri());
    }

    @Test
    void testConstructor2() {
        EndpointHit actualEndpointHit = new EndpointHit(1L, "App", "Uri", "127.0.0.1",
                LocalDate.of(1970, 1, 1).atStartOfDay());
        actualEndpointHit.setApp("App");
        actualEndpointHit.setId(1L);
        actualEndpointHit.setIp("127.0.0.1");
        LocalDateTime timestamp = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEndpointHit.setTimestamp(timestamp);
        actualEndpointHit.setUri("Uri");
        assertEquals("App", actualEndpointHit.getApp());
        assertEquals(1L, actualEndpointHit.getId().longValue());
        assertSame(timestamp, actualEndpointHit.getTimestamp());
        assertEquals("Uri", actualEndpointHit.getUri());
    }
}

