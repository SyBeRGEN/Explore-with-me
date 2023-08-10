package ru.practicum.base.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.user.UserShortDto;

import java.time.LocalDateTime;

/**
 * DTO -> Краткая информация о событии:
 * annotation -> Краткое описание
 * category -> Категория
 * confirmedRequests -> Количество одобренных заявок на участие в данном событии
 * eventDate -> Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
 * id -> Идентификатор
 * initiator -> Пользователь (краткая информация)
 * paid -> Нужно ли оплачивать участие
 * title -> Заголовок
 * views -> Количество просмотрев события
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventShortDto {
    private String annotation;
    private CategoryDto category;
    private Long confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Long id;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}