package ru.practicum.base.mapper;

import ru.practicum.base.dto.event.*;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.model.Event;
import ru.practicum.base.utils.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {
    public static Event toEntity(NewEventDto newEventDto) {
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .createdOn(LocalDateTime.now())
                .description(newEventDto.getDescription())
                .date(newEventDto.getEventDate())
                .location(new Location(newEventDto.getLocation().getLat(), newEventDto.getLocation().getLon()))
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration())
                .state(State.PENDING)
                .title(newEventDto.getTitle())
                .build();
    }

    public static Event toEntity(UpdateEventAdminRequest dto) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                .createdOn(LocalDateTime.now())
                .description(dto.getDescription())
                .date(dto.getEventDate())
                .location(dto.getLocation() != null ? new Location(dto.getLocation().getLat(),
                        dto.getLocation().getLon()) : null)
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                .requestModeration(dto.getRequestModeration())
                .title(dto.getTitle())
                .build();
    }

    public static Event toEntity(UpdateEventUserRequest dto) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                .description(dto.getDescription())
                .date(dto.getEventDate())
                .paid(dto.getPaid())
                .location(dto.getLocation() != null ? new Location(dto.getLocation().getLat(),
                        dto.getLocation().getLon()) : null)
                .participantLimit(dto.getParticipantLimit())
                .title(dto.getTitle())
                .build();
    }

    public static EventFullDto toEventFullDto(Event entity) {
        return EventFullDto.builder()
                .id(entity.getId())
                .annotation(entity.getAnnotation())
                .category(CategoryMapper.toDto(entity.getCategory()))
                .confirmedRequests(entity.getConfirmedRequests())
                .createdOn(entity.getCreatedOn())
                .description(entity.getDescription())
                .eventDate(entity.getDate())
                .initiator(UserMapper.toUserShortDto(entity.getInitiator()))
                .location(new Location(entity.getLocation().getLat(), entity.getLocation().getLon()))
                .paid(entity.getPaid())
                .participantLimit(entity.getParticipantLimit())
                .publishedOn(entity.getPublishedOn())
                .requestModeration(entity.getRequestModeration())
                .state(entity.getState())
                .title(entity.getTitle())
                .views(entity.getViews())
                .build();
    }

    public static EventShortDto toEventShortDto(Event entity) {
        return EventShortDto.builder()
                .id(entity.getId())
                .annotation(entity.getAnnotation())
                .category(CategoryMapper.toDto(entity.getCategory()))
                .confirmedRequests(entity.getConfirmedRequests())
                .eventDate(entity.getDate())
                .initiator(UserMapper.toUserShortDto(entity.getInitiator()))
                .paid(entity.getPaid())
                .title(entity.getTitle())
                .views(entity.getViews())
                .build();
    }

    public static List<EventShortDto> toEventShortDtoList(List<Event> events) {
        return events
                .stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }
}
