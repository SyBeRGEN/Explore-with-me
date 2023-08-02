package ru.practicum.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import ru.practicum.EndpointHitDto;
import ru.practicum.model.EndpointHit;

class EndpointHitMapperTest {

    @Test
    void testToEntity() {
        EndpointHit actualToEntityResult = EndpointHitMapper.toEntity(new EndpointHitDto());
        assertNull(actualToEntityResult.getApp());
        assertNull(actualToEntityResult.getUri());
        assertNull(actualToEntityResult.getTimestamp());
        assertNull(actualToEntityResult.getIp());
        assertNull(actualToEntityResult.getId());
    }

    @Test
    void testToDto() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setApp("App");
        endpointHit.setId(1L);
        endpointHit.setIp("127.0.0.1");
        endpointHit.setTimestamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        endpointHit.setUri("Uri");
        EndpointHitDto actualToDtoResult = EndpointHitMapper.toDto(endpointHit);
        assertEquals("App", actualToDtoResult.getApp());
        assertEquals("Uri", actualToDtoResult.getUri());
        assertEquals("00:00", actualToDtoResult.getTimestamp().toLocalTime().toString());
        assertEquals(1L, actualToDtoResult.getId().longValue());
    }
}