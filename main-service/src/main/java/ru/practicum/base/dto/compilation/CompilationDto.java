package ru.practicum.base.dto.compilation;

import lombok.*;
import ru.practicum.base.dto.event.EventShortDto;

import java.util.List;

/**
 * DTO -> Подборка событий:
 * events -> Список событий входящих в подборку
 * id -> Идентификатор
 * pinned -> Закреплена ли подборка на главной странице сайта
 * title -> Заголовок подборки
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationDto {
    private List<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    private String title;
}