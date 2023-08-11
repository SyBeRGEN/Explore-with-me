package ru.practicum.base.dto.user;

import lombok.*;

/**
 * DTO -> Пользователь (краткая информация):
 * id -> Идентификатор
 * name -> Имя
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserShortDto {
    private Long id;
    private String name;
}