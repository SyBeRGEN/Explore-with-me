package ru.practicum.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.adminApi.service.AdminCategoriesService;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.category.NewCategoryDto;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/categories")
public class AdminCategoriesController {

    private final AdminCategoriesService service;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid NewCategoryDto dto) {
        log.info("Получен запрос POST /admin/categories: {}", dto.getName());

        return new ResponseEntity<>(service.createCategory(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long catId) {
        log.info("Получен запрос DELETE /admin/categories/{}", catId);
        service.deleteCategory(catId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid NewCategoryDto dto,
                                                      @PathVariable Long catId) {
        log.info("Получен запрос PATCH /admin/categories/{}: {}", catId, dto.getName());

        return new ResponseEntity<>(service.updateCategory(dto, catId), HttpStatus.OK);
    }
}