package ru.practicum.publicApi.service;

import ru.practicum.base.dto.category.CategoryDto;

import java.util.List;

public interface PublicCategoriesService {
    List<CategoryDto> getCategories(Long from, Long size);

    CategoryDto getCategoryById(Long catId);
}
