package ru.practicum.base.dto.compilation;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * DTO -> Подборка событий:
 * events -> Список идентификаторов событий входящих в подборку
 * pinned -> Закреплена ли подборка на главной странице сайта
 * title -> Заголовок подборки
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCompilationDto {
    private List<Long> events;
    @Builder.Default
    private Boolean pinned = false;
    @Size(min = 1, max = 50)
    private String title;
}