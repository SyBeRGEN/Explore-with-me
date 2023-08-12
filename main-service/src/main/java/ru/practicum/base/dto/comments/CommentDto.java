package ru.practicum.base.dto.comments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.base.utils.Status;

import java.time.LocalDateTime;

/**
 * DTO -> Комментарий:
 * id -> Идентификатор
 * text -> Текст комментария
 * created -> Дата и время создания комментария (в формате "yyyy-MM-dd HH:mm:ss")
 * eventId -> Идентификатор события, для которого написан комментарий
 * commentatorId -> Идентификатор комментатора, которым был написан комментарий
 * status -> Статус комментария
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private Long eventId;
    private Long commentatorId;
    private Status status;

}
