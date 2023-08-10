package ru.practicum.base.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.base.dto.category.CategoryDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.utils.State;

import java.time.LocalDateTime;

/**
 * DTO -> Полня подборка событий:
 * annotation -> Краткое описание
 * category -> Категория
 * confirmedRequests -> Количество одобренных заявок на участие в данном событии
 * createdOn -> Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
 * description -> Полное описание события
 * eventDate -> Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
 * id -> Идентификатор
 * initiator -> Пользователь (краткая информация)
 * location -> Широта и долгота места проведения события
 * paid -> Нужно ли оплачивать участие
 * participantLimit -> Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
 * publishedOn -> Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
 * requestsModeration -> Нужна ли пре-модерация заявок на участие
 * state -> Список состояний жизненного цикла события
 * title -> Заголовок
 * views -> Количество просмотрев события
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventFullDto {
    private String annotation;
    private CategoryDto category;
    private Long confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Long id;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Long participantLimit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Long views;
}