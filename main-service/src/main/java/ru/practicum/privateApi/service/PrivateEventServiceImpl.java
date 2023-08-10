package ru.practicum.privateApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.event.*;
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.EventMapper;
import ru.practicum.base.mapper.RequestMapper;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.Request;
import ru.practicum.base.repository.CategoryRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.RequestRepository;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionUser;
import ru.practicum.base.utils.UtilMergeProperty;
import ru.practicum.base.utils.Validator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.base.utils.Status.CONFIRMED;
import static ru.practicum.base.utils.Status.REJECTED;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PrivateEventServiceImpl implements PrivateEventService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;

    @Override
    public List<EventShortDto> getEvents(Long userId, Long from, Long size) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));
        List<EventShortDto> eventShortDtoList = EventMapper.toEventShortDtoList(eventRepository.findAll(pageable).toList());
        log.info("Получен список событий с размером: {}", eventShortDtoList.size());

        return eventShortDtoList;
    }

    @Override
    public EventFullDto getEventById(Long userId, Long eventId) {
        Event event = eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Событие с id = %s и userId = %s не найдено", eventId, userId)));
        log.info("Получено событие с id: {}", event.getId());

        return EventMapper.toEventFullDto(event);
    }

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId, Long eventId) {
        if (!eventRepository.existsByIdAndInitiator_Id(eventId, userId)) {
            throw new NotFoundException(
                    String.format("Событие с id = %s и userId = %s не найдено", eventId, userId));
        }

        return RequestMapper.toDtoList(requestRepository.findByEvent_Id(eventId));
    }

    @Transactional
    @Override
    public EventFullDto createEvent(Long userId, NewEventDto eventDto) {
        Validator.validateCreateEvent(eventDto);
        Validator.checkEventDate(eventDto.getEventDate());

        Event event = EventMapper.toEntity(eventDto);
        event.setCategory(categoryRepository.findById(eventDto.getCategory())
                .orElseThrow(() -> new NotFoundException(String.format("Категория c id = %d не найдена",
                        eventDto.getCategory()))));
        event.setConfirmedRequests(0L);
        event.setPublishedOn(LocalDateTime.now());
        event.setInitiator(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", userId))));
        event.setViews(0L);

        try {
            event = eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Добавлено событие: {}", event.getTitle());

        return EventMapper.toEventFullDto(event);
    }

    @Transactional
    @Override
    public EventFullDto updateEvent(Long userId, Long eventId, UpdateEventUserRequest eventDto) {
        Validator.validateUpdateEvent(eventDto);
        Event eventTarget = eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Событие с id = %s и userId = %s не найдено", eventId, userId)));
        Event eventUpdate = EventMapper.toEntity(eventDto);
        Validator.checkEventDate(eventUpdate.getDate());

        if (eventDto.getCategory() != null) {
            eventUpdate.setCategory(categoryRepository.findById(eventDto.getCategory())
                    .orElseThrow(() -> new NotFoundException(String.format("Категория с id = %d не найдена",
                            eventDto.getCategory()))));
        }

        if (eventTarget.getState().equals(State.PUBLISHED)) {
            throw new ConflictException("Событие не должно быть опубликовано: PUBLISHED");
        }

        UtilMergeProperty.copyProperties(eventUpdate, eventTarget);

        if (eventDto.getStateAction() != null &&
                StateActionUser.CANCEL_REVIEW.toString().equals(eventDto.getStateAction().toString())) {
            eventTarget.setState(State.CANCELED);
        } else if (eventDto.getStateAction() != null &&
                StateActionUser.SEND_TO_REVIEW.toString().equals(eventDto.getStateAction().toString())) {
            eventTarget.setState(State.PENDING);
        }
        eventRepository.flush();

        log.info("Событие: {} было обновлено", eventTarget.getTitle());

        return EventMapper.toEventFullDto(eventTarget);
    }

    @Transactional
    @Override
    public EventRequestStatusUpdateResult updateRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request) {
        List<ParticipationRequestDto> confirmedRequests = List.of();
        List<ParticipationRequestDto> rejectedRequests = List.of();
        log.info("Был вызван метод updateRequestStatus с userId = {}, eventId = {}, request = {}", userId, eventId, request);

        Event event = eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Событие с id = %s и userId = %s не найдено", eventId, userId)));

        List<Long> requestIds = request.getRequestIds();
        List<Request> requests = requestRepository.findByIdIn(requestIds);

        String status = String.valueOf(request.getStatus());

        if (status.equals(REJECTED.toString())) {
            if (status.equals(REJECTED.toString())) {
                boolean isConfirmedRequestExists = requests.stream()
                        .anyMatch(r -> r.getStatus().equals(CONFIRMED));
                if (isConfirmedRequestExists) {
                    throw new ConflictException("Невозможно отклонить подтвержденные запросы");
                }
                rejectedRequests = requests.stream()
                        .peek(r -> r.setStatus(REJECTED))
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                return new EventRequestStatusUpdateResult(confirmedRequests, rejectedRequests);
            }
        }


        Long participantLimit = event.getParticipantLimit(); // Ограничение на количество участников
        Long approvedRequests = event.getConfirmedRequests(); // Подтвержденные участники
        long availableParticipants = participantLimit - approvedRequests; // Свободно мест осталось
        long potentialParticipants = requestIds.size(); // Потенциальных участников

        if (participantLimit > 0 && participantLimit.equals(approvedRequests)) {
            throw new ConflictException(String.format("Событие с id = %d достигло лимита участников", eventId));
        }

        if (status.equals(CONFIRMED.toString())) {
            if (participantLimit.equals(0L) || (potentialParticipants <= availableParticipants && !event.getRequestModeration())) {
                confirmedRequests = requests.stream()
                        .peek(r -> {
                            if (!r.getStatus().equals(CONFIRMED)) {
                                r.setStatus(CONFIRMED);
                            } else {
                                throw new ConflictException(String.format("Запрос с id = %d уже был подтвержден", r.getId()));
                            }
                        })
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                event.setConfirmedRequests(approvedRequests + potentialParticipants);
            } else {
                confirmedRequests = requests.stream()
                        .limit(availableParticipants)
                        .peek(r -> {
                            if (!r.getStatus().equals(CONFIRMED)) {
                                r.setStatus(CONFIRMED);
                            } else {
                                throw new ConflictException(String.format("Запрос с id = %d уже был подтвержден", r.getId()));
                            }
                        })
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                rejectedRequests = requests.stream()
                        .skip(availableParticipants)
                        .peek(r -> {
                            if (!r.getStatus().equals(REJECTED)) {
                                r.setStatus(REJECTED);
                            } else {
                                throw new ConflictException(String.format("Запрос с id = %d уже был отклонен", r.getId()));
                            }
                        })
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                event.setConfirmedRequests(approvedRequests + potentialParticipants);
            }
        }
        eventRepository.flush();
        requestRepository.flush();
        log.info("Статус запроса был успешно обновлен");

        return new EventRequestStatusUpdateResult(confirmedRequests, rejectedRequests);
    }


}
