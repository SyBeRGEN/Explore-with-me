package ru.practicum.base.dto.event;

import lombok.*;
import ru.practicum.base.dto.request.ParticipationRequestDto;

import java.util.List;

/**
 * Результат подтверждения/отклонения заявок на участие в событии:
 * confirmedRequests -> Подтвержденные заявки на участие в событии
 * rejectedRequests -> Отклоненные заявки на участие в событии
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestStatusUpdateResult {
    private List<ParticipationRequestDto> confirmedRequests;
    private List<ParticipationRequestDto> rejectedRequests;
}