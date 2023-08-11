package ru.practicum.base.dto.category;

import lombok.*;

import javax.validation.constraints.Size;

/**
 * DTO -> Категории:
 * id -> Идентификатор категории
 * name -> Название категории
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    @Size(min = 1, max = 50)
    private String name;
}
