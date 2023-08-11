package ru.practicum.publicApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.CategoryMapper;
import ru.practicum.base.model.Category;
import ru.practicum.base.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PublicCategoriesServiceImpl implements PublicCategoriesService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategories(Long from, Long size) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));
        List<Category> categoryList = categoryRepository.findAll(pageable).toList();
        log.info("Получен список категорий размером: " + categoryList.size());

        return CategoryMapper.toDtoList(categoryList);
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Категория с указанным id = %s не найдена", catId)));
        log.info("Получена категория с id = " + catId);

        return CategoryMapper.toDto(category);
    }
}
