package ru.practicum.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsInputDto;
import ru.practicum.ViewStatsOutputDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;
import ru.practicum.repository.EndpointHitRepository;
import ru.practicum.repository.ViewStatsRepository;

@ContextConfiguration(classes = {StatsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StatsServiceImplTest {
    @MockBean
    private EndpointHitRepository endpointHitRepository;

    @Autowired
    private StatsServiceImpl statsServiceImpl;

    @MockBean
    private ViewStatsRepository viewStatsRepository;

    @Test
    void testSaveHit() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setApp("App");
        endpointHit.setId(1L);
        endpointHit.setIp("127.0.0.1");
        endpointHit.setTimestamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        endpointHit.setUri("Uri");
        when(endpointHitRepository.save(Mockito.<EndpointHit>any())).thenReturn(endpointHit);
        EndpointHitDto actualSaveHitResult = statsServiceImpl.saveHit(new EndpointHitDto());
        assertEquals("App", actualSaveHitResult.getApp());
        assertEquals("Uri", actualSaveHitResult.getUri());
        assertEquals("00:00", actualSaveHitResult.getTimestamp().toLocalTime().toString());
        assertEquals(1L, actualSaveHitResult.getId().longValue());
        verify(endpointHitRepository).save(Mockito.<EndpointHit>any());
    }

    @Test
    void testGetStats() {
        when(viewStatsRepository.getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any())).thenReturn(new ArrayList<>());
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(statsServiceImpl.getStats(new ViewStatsInputDto(start, end, new ArrayList<>(), true), null).isEmpty());
        verify(viewStatsRepository).getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any());
    }

    @Test
    void testGetStats2() {
        ArrayList<ViewStats> viewStatsList = new ArrayList<>();
        viewStatsList.add(new ViewStats());
        when(viewStatsRepository.getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any())).thenReturn(viewStatsList);
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(1970, 1, 1).atStartOfDay();
        List<ViewStatsOutputDto> actualStats = statsServiceImpl
                .getStats(new ViewStatsInputDto(start, end, new ArrayList<>(), true), null);
        assertEquals(1, actualStats.size());
        ViewStatsOutputDto getResult = actualStats.get(0);
        assertNull(getResult.getApp());
        assertNull(getResult.getUri());
        assertNull(getResult.getHits());
        verify(viewStatsRepository).getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any());
    }

    @Test
    void testGetStats3() {
        ArrayList<ViewStats> viewStatsList = new ArrayList<>();
        viewStatsList.add(new ViewStats());
        viewStatsList.add(new ViewStats());
        when(viewStatsRepository.getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any())).thenReturn(viewStatsList);
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(1970, 1, 1).atStartOfDay();
        List<ViewStatsOutputDto> actualStats = statsServiceImpl
                .getStats(new ViewStatsInputDto(start, end, new ArrayList<>(), true), null);
        assertEquals(2, actualStats.size());
        ViewStatsOutputDto getResult = actualStats.get(0);
        assertNull(getResult.getUri());
        ViewStatsOutputDto getResult2 = actualStats.get(1);
        assertNull(getResult2.getUri());
        assertNull(getResult2.getHits());
        assertNull(getResult2.getApp());
        assertNull(getResult.getHits());
        assertNull(getResult.getApp());
        verify(viewStatsRepository).getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any());
    }

    @Test
    void testGetStats4() {
        when(viewStatsRepository.getNotUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any())).thenReturn(new ArrayList<>());
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(
                statsServiceImpl.getStats(new ViewStatsInputDto(start, end, new ArrayList<>(), false), null).isEmpty());
        verify(viewStatsRepository).getNotUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any());
    }
}

