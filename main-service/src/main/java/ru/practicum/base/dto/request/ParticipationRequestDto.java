package ru.practicum.base.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.base.utils.Status;

import java.time.LocalDateTime;

/**
 * DTO -> Заявка на участие в событии:
 * created -> Дата и время создания заявки
 * event -> Идентификатор события
 * id -> Идентификатор заявки
 * requester -> Идентификатор пользователя, отправившего заявку
 * status -> Статус заявки
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private Long event;
    private Long id;
    private Long requester;
    private Status status;
}