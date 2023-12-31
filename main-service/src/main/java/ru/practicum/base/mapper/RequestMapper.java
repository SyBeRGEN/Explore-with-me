package ru.practicum.base.mapper;

import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.Request;
import ru.practicum.base.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.base.utils.Status.CONFIRMED;
import static ru.practicum.base.utils.Status.PENDING;

public class RequestMapper {
    public static Request toRequest(Event event, User requester) {
        if (event.getParticipantLimit() == 0) {
            return Request.builder()
                    .requester(requester)
                    .event(event)
                    .created(LocalDateTime.now())
                    .status(CONFIRMED)
                    .build();
        }
        return Request.builder()
                .requester(requester)
                .event(event)
                .created(LocalDateTime.now())
                .status(event.getRequestModeration() ? PENDING : CONFIRMED)
                .build();
    }

    public static ParticipationRequestDto toParticipationRequestDto(Request entity) {
        return ParticipationRequestDto.builder()
                .id(entity.getId())
                .requester(entity.getRequester().getId())
                .created(entity.getCreated())
                .event(entity.getEvent().getId())
                .status(entity.getStatus())
                .build();
    }

    public static List<ParticipationRequestDto> toDtoList(List<Request> requests) {
        return requests
                .stream()
                .map(RequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }
}
