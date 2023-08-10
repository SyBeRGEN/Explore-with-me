package ru.practicum.adminApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.UpdateEventAdminRequest;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.EventMapper;
import ru.practicum.base.model.Event;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.StateActionAdmin;
import ru.practicum.base.utils.UtilMergeProperty;
import ru.practicum.base.utils.Validator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminEventsServiceImpl implements AdminEventsService {
    private final EventRepository eventRepository;

    @Override
    public List<EventFullDto> getEvents(List<Long> users, List<State> statesEnum, List<Long> categories,
                                        LocalDateTime rangeStart, LocalDateTime rangeEnd, Long from, Long size) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));
        List<Event> events = eventRepository.findEventsByParams(users, statesEnum, categories,
                rangeStart, rangeEnd, pageable);
        return events
                .stream()
                .map(EventMapper::toEventFullDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEvent) {
        if (updateEvent.getEventDate() != null) {
            Validator.checkEventDate(updateEvent.getEventDate());
        }
        Validator.validateUpdateEvent(updateEvent);
        Event eventUpdate = EventMapper.toEntity(updateEvent);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Событие с id = %s не найдено", eventId)));
        if (event.getState().equals(State.PUBLISHED)) {
            throw new ConflictException("Не удается опубликовать событие, потому что оно уже опубликовано: PUBLISHED");
        } else if (event.getState().equals(State.CANCELED)) {
            throw new ConflictException("Не удается опубликовать событие, потому что оно отменено: CANCELED");
        } else if (updateEvent.getStateAction() != null) {
            if (updateEvent.getStateAction().toString().equals(StateActionAdmin.PUBLISH_EVENT.toString())) {
                event.setState(State.PUBLISHED);
            }
            if (updateEvent.getStateAction().toString().equals(StateActionAdmin.REJECT_EVENT.toString())) {
                event.setState(State.CANCELED);
            }
        }

        UtilMergeProperty.copyProperties(eventUpdate, event);

        try {
            eventRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Обновлено событие: {}", event.getTitle());
        return EventMapper.toEventFullDto(event);
    }
}