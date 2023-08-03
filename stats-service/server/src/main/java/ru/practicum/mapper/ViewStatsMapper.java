package ru.practicum.mapper;

import ru.practicum.ViewStatsOutputDto;
import ru.practicum.model.ViewStats;

import java.util.List;
import java.util.stream.Collectors;

public class ViewStatsMapper {
    public static ViewStatsOutputDto toDto(ViewStats viewStats) {
        return new ViewStatsOutputDto(viewStats.getApp(), viewStats.getUri(), viewStats.getHits());
    }

    public static List<ViewStatsOutputDto> toDtoList(List<ViewStats> viewStats) {
        return viewStats.stream().map(ViewStatsMapper::toDto).collect(Collectors.toList());
    }
}
