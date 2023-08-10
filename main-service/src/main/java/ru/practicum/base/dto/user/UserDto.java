package ru.practicum.base.dto.user;

import lombok.*;

import javax.validation.constraints.Email;

/**
 * DTO -> Пользователь:
 * email -> Почтовый адрес
 * id -> Идентификатор
 * name -> Имя
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @Email
    private String email;
    private Long id;
    private String name;
}