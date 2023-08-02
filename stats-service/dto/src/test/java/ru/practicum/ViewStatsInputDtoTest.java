package ru.practicum;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewStatsInputDtoTest {
    @Test
    void testConstructor() {
        ViewStatsInputDto actualViewStatsInputDto = new ViewStatsInputDto();
        assertNull(actualViewStatsInputDto.getEnd());
        assertNull(actualViewStatsInputDto.getStart());
        assertNull(actualViewStatsInputDto.getUnique());
        assertNull(actualViewStatsInputDto.getUris());
    }

    @Test
    void testConstructor2() {
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(1970, 1, 1).atStartOfDay();
        ViewStatsInputDto actualViewStatsInputDto = new ViewStatsInputDto(start, end, new ArrayList<>(), true);

        LocalDateTime expectedEnd = actualViewStatsInputDto.end;
        assertSame(expectedEnd, actualViewStatsInputDto.getEnd());
        LocalDateTime expectedStart = actualViewStatsInputDto.start;
        assertSame(expectedStart, actualViewStatsInputDto.getStart());
        assertTrue(actualViewStatsInputDto.getUnique());
        List<String> expectedUris = actualViewStatsInputDto.uris;
        assertSame(expectedUris, actualViewStatsInputDto.getUris());
    }
}