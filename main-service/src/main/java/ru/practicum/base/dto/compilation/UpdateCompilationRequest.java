package ru.practicum.base.dto.compilation;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Изменение информации о подборке событий:
 * events -> Список id событий подборки для полной замены текущего списка
 * pinned -> Закреплена ли подборка на главной странице сайта
 * title -> Заголовок подборки
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCompilationRequest {
    private List<Long> events;
    private Boolean pinned;
    @Size(min = 1, max = 50)
    private String title;
}
