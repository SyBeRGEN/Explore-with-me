package ru.practicum.adminApi.service;

import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.category.NewCategoryDto;

public interface AdminCategoriesService {
    CategoryDto createCategory(NewCategoryDto dto);

    CategoryDto updateCategory(NewCategoryDto dto, Long catId);

    void deleteCategory(Long catId);
}
