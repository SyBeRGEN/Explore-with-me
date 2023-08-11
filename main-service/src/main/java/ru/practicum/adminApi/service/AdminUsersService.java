package ru.practicum.adminApi.service;

import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.dto.user.UserDto;

import java.util.List;

public interface AdminUsersService {
    List<UserDto> getUsers(List<Long> ids, Long from, Long size);

    UserDto saveUser(NewUserRequest newUserRequest);

    void deleteUser(Long userId);
}