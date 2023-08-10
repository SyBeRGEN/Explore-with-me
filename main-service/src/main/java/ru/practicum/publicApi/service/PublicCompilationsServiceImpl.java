package ru.practicum.publicApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.CompilationMapper;
import ru.practicum.base.model.Compilation;
import ru.practicum.base.repository.CompilationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PublicCompilationsServiceImpl implements PublicCompilationsService {
    private final CompilationRepository compilationRepository;

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Long from, Long size) {
        List<Compilation> compilations;
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));

        if (pinned != null) {
            compilations = compilationRepository.findByPinned(pinned, pageable);
        } else {
            compilations = compilationRepository.findAll(pageable).toList();
        }
        log.info("Получен список подборок размером: " + compilations.size());

        return CompilationMapper.toDtoList(compilations);
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Подборка с указанным id = %s не найден", compId)));
        log.info("Получена подборка с id = " + compId);

        return CompilationMapper.toDto(compilation);
    }
}
