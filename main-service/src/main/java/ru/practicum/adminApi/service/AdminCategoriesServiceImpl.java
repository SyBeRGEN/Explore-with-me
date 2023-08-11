package ru.practicum.adminApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.category.NewCategoryDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.CategoryMapper;
import ru.practicum.base.model.Category;
import ru.practicum.base.repository.CategoryRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.UtilMergeProperty;
import ru.practicum.base.utils.Validator;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminCategoriesServiceImpl implements AdminCategoriesService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryDto createCategory(NewCategoryDto dto) {
        Validator.validateCreateCategory(dto);
        Category category = CategoryMapper.toEntity(dto);
        try {
            category = categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Добавлена категория: {}", category.getName());

        return CategoryMapper.toDto(category);
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(NewCategoryDto dto, Long catId) {
        Category categoryUpdate = CategoryMapper.toEntity(dto);
        Category categoryTarget = getCategory(catId);
        Validator.validateCreateCategory(dto);
        try {
            UtilMergeProperty.copyProperties(categoryUpdate, categoryTarget);
            categoryRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Обновлена категория: {}", categoryTarget.getName());

        return CategoryMapper.toDto(categoryTarget);
    }

    @Transactional
    @Override
    public void deleteCategory(Long catId) {
        if (eventRepository.existsByCategory(getCategory(catId))) {
            throw new ConflictException("Категория не является пустой");
        } else {
            log.info("Удалена категория id = {}", catId);

            categoryRepository.deleteById(catId);
        }
    }

    private Category getCategory(Long id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Категория с id = %s не найдена", id)));

        log.info("Получена категория: {}", category.getName());

        return category;
    }
}