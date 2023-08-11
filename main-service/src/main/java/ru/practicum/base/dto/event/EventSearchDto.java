package ru.practicum.base.dto.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO -> Полня подборка событий:
 * text -> Текст для поиска в содержимом аннотации и подробном описании события.
 * categories -> Список идентификаторов категорий в которых будет вестись поиск.
 * paid -> Поиск только платных/бесплатных событий.
 * rangeStart -> Дата и время не раньше которых должно произойти событие.
 * rangeEnd -> Дата и время не позже которых должно произойти событие.
 * onlyAvailable -> Только события у которых не исчерпан лимит запросов на участие.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSearchDto {
    private String text;
    private List<Long> categories;
    private Boolean paid;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private Boolean onlyAvailable;
}