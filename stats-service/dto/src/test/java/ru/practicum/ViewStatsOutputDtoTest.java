package ru.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ViewStatsOutputDtoTest {
    @Test
    void testConstructor() {
        ViewStatsOutputDto actualViewStatsOutputDto = new ViewStatsOutputDto();
        assertNull(actualViewStatsOutputDto.getApp());
        assertNull(actualViewStatsOutputDto.getHits());
        assertNull(actualViewStatsOutputDto.getUri());
    }

    @Test
    void testConstructor2() {
        ViewStatsOutputDto actualViewStatsOutputDto = new ViewStatsOutputDto("App", "Uri", 1L);

        assertEquals("App", actualViewStatsOutputDto.getApp());
        assertEquals(1L, actualViewStatsOutputDto.getHits().longValue());
        assertEquals("Uri", actualViewStatsOutputDto.getUri());
    }
}

