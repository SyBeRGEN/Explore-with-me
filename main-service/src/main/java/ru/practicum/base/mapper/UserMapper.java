package ru.practicum.base.mapper;

import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.dto.user.UserDto;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(NewUserRequest dto) {
        return User
                .builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public static UserDto toUserDto(User entity) {
        return UserDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    public static UserShortDto toUserShortDto(User entity) {
        return UserShortDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
