package ru.practicum.publicApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.publicApi.service.PublicCompilationsService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class PublicCompilationsController {
    private final PublicCompilationsService service;

    @GetMapping
    public ResponseEntity<List<CompilationDto>> getCompilations(@RequestParam(value = "pinned", required = false) Boolean pinned,
                                                                @RequestParam(value = "from", defaultValue = "0") Long from,
                                                                @RequestParam(value = "size", defaultValue = "10") Long size) {
        log.info("Получен запрос GET /compilations c параметрами: pinned = {}, from = {}, size = {}", pinned, from, size);

        return new ResponseEntity<>(service.getCompilations(pinned, from, size), HttpStatus.OK);
    }

    @GetMapping("/{compId}")
    public ResponseEntity<CompilationDto> getCompilationById(@PathVariable Long compId) {
        log.info("Получен запрос GET /compilations/{}", compId);
        return new ResponseEntity<>(service.getCompilationById(compId), HttpStatus.OK);
    }
}
