package ru.practicum.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.adminApi.service.AdminEventsService;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.UpdateEventAdminRequest;
import ru.practicum.base.utils.State;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/events")
@Validated
public class AdminEventsController {

    public final AdminEventsService service;

    @GetMapping()
    public ResponseEntity<List<EventFullDto>> getEvents(@RequestParam(required = false) List<Long> users,
                                                        @RequestParam(required = false) List<String> states,
                                                        @RequestParam(required = false) List<Long> categories,
                                                        @RequestParam(required = false)
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                        @RequestParam(required = false)
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                        @RequestParam(defaultValue = "0") @PositiveOrZero Long from,
                                                        @RequestParam(defaultValue = "10") @Positive Long size) {
        log.info("Получен запрос GET /admin/events");

        List<State> statesEnum = null;
        if (states != null) {
            statesEnum = states
                    .stream()
                    .map(State::convertFromString)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(service.getEvents(users, statesEnum, categories, rangeStart, rangeEnd, from, size), HttpStatus.OK);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventFullDto> updateEvent(@PathVariable Long eventId,
                                                    @RequestBody UpdateEventAdminRequest updateEvent) {
        log.info("Получен запрос PATCH /admin/events/{} на изменение события.", eventId);

        return new ResponseEntity<>(service.updateEvent(eventId, updateEvent), HttpStatus.OK);
    }
}