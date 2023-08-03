package ru.practicum.mapper;

import ru.practicum.EndpointHitDto;
import ru.practicum.model.EndpointHit;

public class EndpointHitMapper {
    public static EndpointHit toEntity(EndpointHitDto endpointHitDto) {
        return new EndpointHit(endpointHitDto.getId(), endpointHitDto.getApp(),
                endpointHitDto.getUri(), endpointHitDto.getIp(), endpointHitDto.getTimestamp());
    }

    public static EndpointHitDto toDto(EndpointHit endpointHit) {
        return new EndpointHitDto(endpointHit.getId(), endpointHit.getApp(), endpointHit.getUri(),
                endpointHit.getIp(), endpointHit.getTimestamp());
    }
}