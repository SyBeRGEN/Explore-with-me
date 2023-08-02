package ru.practicum.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import ru.practicum.ViewStatsOutputDto;
import ru.practicum.model.ViewStats;

class ViewStatsMapperTest {

    @Test
    void testToDto() {
        ViewStatsOutputDto actualToDtoResult = ViewStatsMapper.toDto(new ViewStats());
        assertNull(actualToDtoResult.getApp());
        assertNull(actualToDtoResult.getUri());
        assertNull(actualToDtoResult.getHits());
    }

    @Test
    void testToDtoList() {
        assertTrue(ViewStatsMapper.toDtoList(new ArrayList<>()).isEmpty());
    }

    @Test
    void testToDtoList2() {
        ArrayList<ViewStats> viewStats = new ArrayList<>();
        viewStats.add(new ViewStats());
        List<ViewStatsOutputDto> actualToDtoListResult = ViewStatsMapper.toDtoList(viewStats);
        assertEquals(1, actualToDtoListResult.size());
        ViewStatsOutputDto getResult = actualToDtoListResult.get(0);
        assertNull(getResult.getApp());
        assertNull(getResult.getUri());
        assertNull(getResult.getHits());
    }

    @Test
    void testToDtoList3() {
        ArrayList<ViewStats> viewStats = new ArrayList<>();
        viewStats.add(new ViewStats());
        viewStats.add(new ViewStats());
        List<ViewStatsOutputDto> actualToDtoListResult = ViewStatsMapper.toDtoList(viewStats);
        assertEquals(2, actualToDtoListResult.size());
        ViewStatsOutputDto getResult = actualToDtoListResult.get(0);
        assertNull(getResult.getUri());
        ViewStatsOutputDto getResult2 = actualToDtoListResult.get(1);
        assertNull(getResult2.getUri());
        assertNull(getResult2.getHits());
        assertNull(getResult2.getApp());
        assertNull(getResult.getHits());
        assertNull(getResult.getApp());
    }
}