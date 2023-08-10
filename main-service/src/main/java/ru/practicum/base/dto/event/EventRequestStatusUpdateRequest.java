package ru.practicum.base.dto.event;

import lombok.*;
import ru.practicum.base.utils.Status;

import java.util.List;

/**
 * Изменение статуса запроса на участие в событии текущего пользователя:
 * requestIds -> Идентификаторы запросов на участие в событии текущего пользователя
 * status -> Новый статус запроса на участие в событии текущего пользователя
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private Status status;
}