package ru.practicum.base.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.base.dto.location.Location;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO -> Новое событие:
 * annotation -> Краткое описание события
 * category -> id категории к которой относится событие
 * description -> Полное описание события
 * eventDate -> Дата и время на которые намечено событие. Дата и время указываются в формате "yyyy-MM-dd HH:mm:ss"
 * location -> Широта и долгота места проведения события
 * paid -> Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
 * participantLimit -> Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события.
 * Если false - то будут подтверждаться автоматически.
 * requestModeration -> Заголовок подборки
 * title -> Заголовок события
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewEventDto {
    @Size(min = 20, max = 2000)
    private String annotation;
    private Long category;
    @Size(min = 20, max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    @Builder.Default
    private Boolean paid = false;
    @Builder.Default
    private Long participantLimit = 0L;
    @Builder.Default
    private Boolean requestModeration = true;
    @Size(min = 3, max = 120)
    private String title;
}