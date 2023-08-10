package ru.practicum.base.dto.category;

import lombok.*;

import javax.validation.constraints.Size;

/**
 * DTO -> Данные для добавления новой категории:
 * name -> Название категории
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCategoryDto {
    @Size(min = 1, max = 50)
    private String name;
}
