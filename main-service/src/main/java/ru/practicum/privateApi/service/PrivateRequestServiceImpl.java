package ru.practicum.privateApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.RequestMapper;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.Request;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.RequestRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PrivateRequestServiceImpl implements PrivateRequestService {
    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId) {
        if (userRepository.existsById(userId)) {
            return RequestMapper.toDtoList(requestRepository.findByRequester_Id(userId));
        } else {
            throw new NotFoundException(String.format("Пользователь c id = %s не найден", userId));
        }
    }

    @Transactional
    @Override
    public ParticipationRequestDto createRequest(Long userId, Long eventId) {
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Событие c id = %s не найдено", eventId)));
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %s не найден", userId)));

        if (requestRepository.existsByRequester_IdAndEvent_Id(userId, eventId)) {
            throw new ConflictException(String.format("Запрос c requesterId = %d и eventId = %d уже существует", userId, eventId));
        }
        if (userId.equals(event.getInitiator().getId())) {
            throw new ConflictException(String.format("Пользователь с id = %d является инициатором мероприятия", userId));
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new ConflictException(String.format("Событие с id = %d еще не опубликовано", eventId));
        }
        if (event.getParticipantLimit().equals(event.getConfirmedRequests()) && event.getParticipantLimit() != 0) {
            throw new ConflictException(String.format("Событие с id = %d достигнуто лимита участников", eventId));
        }
        if (!event.getRequestModeration()) {
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            eventRepository.save(event);
        }

        return RequestMapper.toParticipationRequestDto(requestRepository.save(RequestMapper.toRequest(event, user)));
    }

    @Transactional
    @Override
    public ParticipationRequestDto updateRequest(Long userId, Long requestId) {
        Request request = requestRepository.findByIdAndRequester_Id(requestId, userId)
                .orElseThrow(() -> new NotFoundException(String.format("Запрос c id = %d и " +
                        "requesterId = %d не найден", requestId, userId)));
        request.setStatus(Status.CANCELED);

        return RequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }
}
