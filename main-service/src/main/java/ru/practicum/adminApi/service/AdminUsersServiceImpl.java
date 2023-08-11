package ru.practicum.adminApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.dto.user.UserDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.mapper.UserMapper;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.UserRepository;
import ru.practicum.base.utils.Validator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminUsersServiceImpl implements AdminUsersService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Long from, Long size) {
        List<User> users;
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));

        if (ids == null) {
            users = userRepository.findAll(pageable).toList();
        } else {
            users = userRepository.findByIdIn(ids, pageable);
        }

        log.info("Получен список пользователей с размером: {}", users.size());

        return UserMapper.toUserDtoList(users);
    }

    @Transactional
    @Override
    public UserDto saveUser(NewUserRequest newUserRequest) {
        Validator.validateCreateUser(newUserRequest);
        User user = UserMapper.toEntity(newUserRequest);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Email уже используется", e);
        }

        log.info("Добавлен пользователь: {}", user.getEmail());

        return UserMapper.toUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {

            log.info("Удален пользователь с id = {}", userId);

            userRepository.deleteById(userId);
        }
    }
}