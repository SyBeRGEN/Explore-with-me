package ru.practicum.publicApi.service;

import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface PublicEventsService {
    List<EventShortDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd, Boolean onlyAvailable, String sort, Long from, Long size,
                                  HttpServletRequest httpServletRequest);

    EventFullDto getEventById(Long id, HttpServletRequest httpServletRequest);
}
