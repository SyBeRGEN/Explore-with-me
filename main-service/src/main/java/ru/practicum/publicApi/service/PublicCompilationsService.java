package ru.practicum.publicApi.service;

import ru.practicum.base.dto.compilation.CompilationDto;

import java.util.List;

public interface PublicCompilationsService {
    List<CompilationDto> getCompilations(Boolean pinned, Long from, Long size);

    CompilationDto getCompilationById(Long compId);
}
