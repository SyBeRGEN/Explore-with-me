package ru.practicum.adminApi.service;

import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.UpdateEventAdminRequest;
import ru.practicum.base.utils.State;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminEventsService {
    List<EventFullDto> getEvents(List<Long> users, List<State> statesEnum, List<Long> categories,
                                 LocalDateTime rangeStart, LocalDateTime rangeEnd, Long from, Long size);

    EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEvent);
}