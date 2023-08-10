package ru.practicum.publicApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.EventShortDto;
import ru.practicum.publicApi.service.PublicEventsService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/events")
public class PublicEventsController {
    private final PublicEventsService service;

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getEvents(@RequestParam(value = "text", required = false) String text,
                                                         @RequestParam(value = "categories", required = false) List<Long> categories,
                                                         @RequestParam(value = "paid", required = false) Boolean paid,
                                                         @RequestParam(value = "rangeStart", required = false)
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                         @RequestParam(value = "rangeEnd", required = false)
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                         @RequestParam(value = "onlyAvailable", defaultValue = "false") Boolean onlyAvailable,
                                                         @RequestParam(value = "sort", defaultValue = "EVENT_DATE") String sort,
                                                         @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Long from,
                                                         @RequestParam(value = "size", defaultValue = "10") @Positive Long size,
                                                         HttpServletRequest httpServletRequest) {
        log.info("Получен запрос GET /events c параметрами: text = {}, categories = {}, paid = {}, rangeStart = {}, " +
                        "rangeEnd = {}, onlyAvailable = {}, sort = {}, from = {}, size = {}", text, categories, paid,
                rangeStart, rangeEnd, onlyAvailable, sort, from, size);

        return new ResponseEntity<>(service.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable,
                sort, from, size, httpServletRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEventById(@PathVariable Long id,
                                                     HttpServletRequest httpServletRequest) {
        log.info("Получен запрос GET /events/{}", id);
        return new ResponseEntity<>(service.getEventById(id, httpServletRequest), HttpStatus.OK);
    }
}
