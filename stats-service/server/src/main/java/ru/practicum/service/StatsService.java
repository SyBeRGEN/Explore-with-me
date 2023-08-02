package ru.practicum.service;

import org.springframework.data.domain.PageRequest;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsInputDto;
import ru.practicum.ViewStatsOutputDto;

import java.util.List;

public interface StatsService {
    EndpointHitDto saveHit(EndpointHitDto endpointHitDto);

    List<ViewStatsOutputDto> getStats(ViewStatsInputDto viewStatsInputDto, PageRequest pageable);
}
