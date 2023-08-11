package ru.practicum.privateApi.service;

import ru.practicum.base.dto.event.*;
import ru.practicum.base.dto.request.ParticipationRequestDto;

import java.util.List;

public interface PrivateEventService {
    List<EventShortDto> getEvents(Long userId, Long from, Long size);

    EventFullDto getEventById(Long userId, Long eventId);

    List<ParticipationRequestDto> getRequests(Long userId, Long eventId);

    EventFullDto createEvent(Long userId, NewEventDto eventDto);

    EventFullDto updateEvent(Long userId, Long eventId, UpdateEventUserRequest eventDto);

    EventRequestStatusUpdateResult updateRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request);
}
