package ru.practicum.privateApi.service;

import ru.practicum.base.dto.request.ParticipationRequestDto;

import java.util.List;

public interface PrivateRequestService {
    List<ParticipationRequestDto> getRequests(Long userId);

    ParticipationRequestDto createRequest(Long userId, Long eventId);

    ParticipationRequestDto updateRequest(Long userId, Long requestsId);
}
