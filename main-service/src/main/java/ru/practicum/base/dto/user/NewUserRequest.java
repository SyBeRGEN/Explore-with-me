package ru.practicum.base.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Данные нового пользователя:
 * email -> Почтовый адрес
 * name -> Имя
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserRequest {
    @Email
    @Size(min = 6, max = 254)
    private String email;
    @Size(min = 2, max = 250)
    private String name;
}