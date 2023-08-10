package ru.practicum.publicApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.publicApi.service.PublicCategoriesService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/categories")
public class PublicCategoriesController {
    private final PublicCategoriesService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(@RequestParam(value = "from", defaultValue = "0") Long from,
                                                           @RequestParam(value = "size", defaultValue = "10") Long size) {
        log.info("Получен запрос GET /categories c параметрами: from = {}, size = {}", from, size);

        return new ResponseEntity<>(service.getCategories(from, size), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long catId) {
        log.info("Получен запрос GET /categories/{}", catId);

        return new ResponseEntity<>(service.getCategoryById(catId), HttpStatus.OK);
    }
}
