package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsInputDto;
import ru.practicum.ViewStatsOutputDto;
import ru.practicum.mapper.EndpointHitMapper;
import ru.practicum.mapper.ViewStatsMapper;
import ru.practicum.repository.EndpointHitRepository;
import ru.practicum.repository.ViewStatsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final EndpointHitRepository endpointHitRepository;
    private final ViewStatsRepository viewStatsRepository;

    @Transactional
    @Override
    public EndpointHitDto saveHit(EndpointHitDto endpointHitDto) {
        return EndpointHitMapper.toDto(endpointHitRepository.save(EndpointHitMapper.toEntity(endpointHitDto)));
    }

    @Override
    public List<ViewStatsOutputDto> getStats(ViewStatsInputDto viewStatsInputDto, PageRequest pageable) {
        if (viewStatsInputDto.getUnique()) {
            return ViewStatsMapper.toDtoList(viewStatsRepository.getUniqueViewStats(viewStatsInputDto.getStart(),
                    viewStatsInputDto.getEnd(), viewStatsInputDto.getUris(), pageable));
        } else {
            return ViewStatsMapper.toDtoList(viewStatsRepository.getNotUniqueViewStats(viewStatsInputDto.getStart(),
                    viewStatsInputDto.getEnd(), viewStatsInputDto.getUris(), pageable));
        }
    }
}
