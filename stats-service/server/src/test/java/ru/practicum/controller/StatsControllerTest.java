package ru.practicum.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsOutputDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.repository.EndpointHitRepository;
import ru.practicum.repository.ViewStatsRepository;
import ru.practicum.service.StatsServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class StatsControllerTest {
    @Test
    void testSaveHit() {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setApp("App");
        endpointHit.setId(1L);
        endpointHit.setIp("127.0.0.1");
        endpointHit.setTimestamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        endpointHit.setUri("Uri");
        EndpointHitRepository endpointHitRepository = mock(EndpointHitRepository.class);
        when(endpointHitRepository.save(Mockito.<EndpointHit>any())).thenReturn(endpointHit);
        StatsController statsController = new StatsController(
                new StatsServiceImpl(endpointHitRepository, mock(ViewStatsRepository.class)));
        ResponseEntity<String> actualSaveHitResult = statsController.saveHit(new EndpointHitDto());
        assertEquals("Успешно сохранено", actualSaveHitResult.getBody());
        assertEquals(HttpStatus.CREATED, actualSaveHitResult.getStatusCode());
        assertTrue(actualSaveHitResult.getHeaders().isEmpty());
        verify(endpointHitRepository).save(Mockito.<EndpointHit>any());
    }

    @Test
    void testGetStats() {
        ViewStatsRepository viewStatsRepository = mock(ViewStatsRepository.class);
        when(viewStatsRepository.getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any())).thenReturn(new ArrayList<>());
        StatsController statsController = new StatsController(
                new StatsServiceImpl(mock(EndpointHitRepository.class), viewStatsRepository));
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(1970, 1, 1).atStartOfDay();
        ArrayList<String> uris = new ArrayList<>();
        ResponseEntity<List<ViewStatsOutputDto>> actualStats = statsController.getStats(start, end, uris, true);
        assertEquals(uris, actualStats.getBody());
        assertEquals(HttpStatus.OK, actualStats.getStatusCode());
        assertTrue(actualStats.getHeaders().isEmpty());
        verify(viewStatsRepository).getUniqueViewStats(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<List<String>>any(), Mockito.<PageRequest>any());
    }
}

