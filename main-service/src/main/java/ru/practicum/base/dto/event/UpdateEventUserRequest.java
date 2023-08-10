package ru.practicum.base.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.base.utils.StateActionUser;

/**
 * Данные для изменения информации о событии:
 * annotation -> Новая аннотация
 * category -> Новая категория
 * description -> Новое описание
 * eventDate -> Новые дата и время на которые намечено событие. Дата и время указываются в формате "yyyy-MM-dd HH:mm:ss"
 * location -> Широта и долгота места проведения события
 * paid -> Новое значение флага о платности мероприятия
 * participantLimit -> Новый лимит пользователей
 * requestModeration -> Нужна ли пре-модерация заявок на участие
 * stateAction -> Изменение состояния события
 * title -> Новый заголовок
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends UpdateEventSuperRequest {
    private StateActionUser stateAction;
}
