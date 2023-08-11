package ru.practicum.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.adminApi.service.AdminUsersService;
import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.dto.user.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/users")
@Validated
public class AdminUsersController {

    private final AdminUsersService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false) List<Long> ids,
                                                  @RequestParam(defaultValue = "0") @PositiveOrZero Long from,
                                                  @RequestParam(defaultValue = "10") @Positive Long size) {
        log.info("Получен запрос GET /admin/users");

        return new ResponseEntity<>(service.getUsers(ids, from, size), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("Получен запрос POST /admin/users: {}", newUserRequest.getEmail());

        return new ResponseEntity<>(service.saveUser(newUserRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("Получен запрос DELETE /admin/users/{}", userId);
        service.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}