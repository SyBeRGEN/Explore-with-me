package ru.practicum.privateApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.base.dto.event.*;
import ru.practicum.base.dto.request.ParticipationRequestDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.utils.Status;
import ru.practicum.privateApi.service.PrivateEventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users/{userId}/events")
@Slf4j
@RequiredArgsConstructor
public class PrivateEventController {
    private final PrivateEventService service;

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getEvents(@PathVariable Long userId,
                                                         @RequestParam(defaultValue = "0") Long from,
                                                         @RequestParam(defaultValue = "10") Long size) {
        log.info("Получен запрос GET /users{}/events c параметрами: from = {}, size = {}", userId, from, size);

        return new ResponseEntity<>(service.getEvents(userId, from, size), HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventFullDto> getEventById(@PathVariable Long userId,
                                                     @PathVariable Long eventId) {
        log.info("Получен запрос GET /users{}/events/{}", userId, eventId);

        return new ResponseEntity<>(service.getEventById(userId, eventId), HttpStatus.OK);
    }

    @GetMapping("/{eventId}/requests")
    public ResponseEntity<List<ParticipationRequestDto>> getRequests(@PathVariable Long userId,
                                                                     @PathVariable Long eventId) {
        log.info("Получен запрос GET /users/{}/events/{}/requests", userId, eventId);

        return new ResponseEntity<>(service.getRequests(userId, eventId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventFullDto> createEvent(@PathVariable Long userId,
                                                    @RequestBody @Valid NewEventDto eventDto) {
        log.info("Получен запрос POST /users/{}/events c новым событием: {}", userId, eventDto);

        return new ResponseEntity<>(service.createEvent(userId, eventDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventFullDto> updateEvent(@PathVariable Long userId,
                                                    @PathVariable Long eventId,
                                                    @RequestBody @Valid UpdateEventUserRequest eventDto) {
        log.info("Получен запрос PATCH /users/{}/events/{eventId}" +
                " c id = {}: {}", userId, eventId, eventDto);

        return new ResponseEntity<>(service.updateEvent(userId, eventId, eventDto), HttpStatus.OK);
    }

    @PatchMapping("/{eventId}/requests")
    public ResponseEntity<EventRequestStatusUpdateResult> updateRequestStatus(@PathVariable Long userId,
                                                                              @PathVariable Long eventId,
                                                                              @RequestBody EventRequestStatusUpdateRequest request) {
        log.info("Получен запрос PATCH /users/{}/events/{eventId}/requests" +
                " c id = {}: {}", userId, eventId, request);
        if (Status.containsCheck(request.getStatus().name()) == null) {
            throw new ConflictException("Такого статуса не существует");
        }

        return new ResponseEntity<>(service.updateRequestStatus(userId, eventId, request), HttpStatus.OK);
    }
}
