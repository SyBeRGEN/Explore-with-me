package ru.practicum.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.adminApi.service.AdminCompilationsService;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.compilation.UpdateCompilationRequest;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final AdminCompilationsService service;

    @PostMapping()
    public ResponseEntity<CompilationDto> saveCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.info("Получен запрос POST /admin/compilations: {}", newCompilationDto.getTitle());

        return new ResponseEntity<>(service.saveCompilation(newCompilationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable Long compId) {
        log.info("Получен запрос DELETE /admin/compilations/{}", compId);
        service.deleteCompilation(compId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<CompilationDto> updateCompilation(@PathVariable Long compId,
                                                            @RequestBody @Valid UpdateCompilationRequest updateCompilationRequest) {
        log.info("Получен запрос PATCH /admin/compilations/{}", compId);

        return new ResponseEntity<>(service.updateCompilation(compId, updateCompilationRequest), HttpStatus.OK);
    }
}