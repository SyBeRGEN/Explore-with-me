package ru.practicum.base.mapper;

import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.model.Compilation;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationMapper {
    public static Compilation toEntity(NewCompilationDto compilationDto) {
        return Compilation.builder()
                .pinned(compilationDto.getPinned())
                .title(compilationDto.getTitle())
                .build();
    }

    public static CompilationDto toDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(EventMapper.toEventShortDtoList((compilation.getEvents())))
                .build();
    }

    public static List<CompilationDto> toDtoList(List<Compilation> compilationList) {
        return compilationList
                .stream()
                .map(CompilationMapper::toDto)
                .collect(Collectors.toList());
    }
}
