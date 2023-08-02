package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsInputDto;
import ru.practicum.ViewStatsOutputDto;
import ru.practicum.service.StatsService;
import ru.practicum.utils.Validator;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StatsController {
    private final StatsService service;

    @PostMapping("/hit")
    public ResponseEntity<String> saveHit(@RequestBody EndpointHitDto endpointHitDto) {
        log.info("Получен запрос POST /hit");

        service.saveHit(endpointHitDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Успешно сохранено");
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStatsOutputDto>> getStats(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                             @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                             @RequestParam("uris") List<String> uris,
                                                             @RequestParam(value = "unique", defaultValue = "false") Boolean unique) {
        log.info("Получен запрос GET /stats");
        Validator.validateTime(start, end);

        ViewStatsInputDto viewStatsInputDto = new ViewStatsInputDto(start, end, uris, unique);

        return ResponseEntity.ok().body(service.getStats(viewStatsInputDto, PageRequest.of(0, 10)));
    }
}
