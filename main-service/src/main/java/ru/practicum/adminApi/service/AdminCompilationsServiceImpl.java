package ru.practicum.adminApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.compilation.UpdateCompilationRequest;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.CompilationMapper;
import ru.practicum.base.model.Compilation;
import ru.practicum.base.model.Event;
import ru.practicum.base.repository.CompilationRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.Validator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminCompilationsServiceImpl implements AdminCompilationsService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public CompilationDto saveCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = CompilationMapper.toEntity(newCompilationDto);
        compilation.setEvents(getEvents(newCompilationDto.getEvents()));
        Validator.validateCreateCompilation(newCompilationDto);
        try {
            compilation = compilationRepository.save(compilation);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Добавлена подборка: {}", compilation.getTitle());

        return CompilationMapper.toDto(compilation);
    }

    @Transactional
    @Override
    public void deleteCompilation(Long compId) {
        if (compilationRepository.existsById(compId)) {
            log.info("Удалена подборка с id = {}", compId);

            compilationRepository.deleteById(compId);
        }
    }

    @Transactional
    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        if (updateCompilationRequest.getTitle() != null) {
            Validator.validateUpdateCompilation(updateCompilationRequest);
        }
        Compilation compilationTarget = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format("Подборка c id = %s не найдена", compId)));

        BeanUtils.copyProperties(updateCompilationRequest, compilationTarget, "events", "pinned", "title");

        compilationTarget.setEvents(getEvents(updateCompilationRequest.getEvents()));
        try {
            compilationRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Обновлена подборка: {}", compilationTarget.getTitle());
        return CompilationMapper.toDto(compilationTarget);
    }

    private List<Event> getEvents(List<Long> eventsId) {
        if (eventsId == null) {
            return List.of();
        }
        return eventRepository.findByIdIn(eventsId);
    }
}