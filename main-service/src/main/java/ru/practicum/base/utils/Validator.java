package ru.practicum.base.utils;

import ru.practicum.base.dto.category.NewCategoryDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.compilation.UpdateCompilationRequest;
import ru.practicum.base.dto.event.NewEventDto;
import ru.practicum.base.dto.event.UpdateEventSuperRequest;
import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.exceptions.BadTimeException;
import ru.practicum.base.exceptions.RequiredParamsException;

import java.time.LocalDateTime;
import java.util.List;

public class Validator {
    public static void validateCreateEvent(NewEventDto dto) {
        if (dto.getDescription() == null) {
            throw new RequiredParamsException("Описание не может быть является обязательным полем");
        }
        if (dto.getDescription().isBlank()) {
            throw new RequiredParamsException("Описание не может быть пустым либо состоять из пробелов");
        }
        if (dto.getAnnotation() == null) {
            throw new RequiredParamsException("Краткое описание события является обязательным полем");
        }
        if (dto.getAnnotation().isBlank()) {
            throw new RequiredParamsException("Краткое описание события не может быть пустым либо состоять из пробелов");
        }
        if (dto.getDescription().length() < 20) {
            throw new RequiredParamsException("Описание должно быть более 20 символов");
        }
        if (dto.getAnnotation().length() < 20) {
            throw new RequiredParamsException("Краткое описание события должно быть более 20 символов");
        }
        if (dto.getAnnotation().length() > 2000) {
            throw new RequiredParamsException("Краткое описание события должно быть не более 2000 символов");
        }
        if (dto.getTitle().length() < 3) {
            throw new RequiredParamsException("Заголовок события должен быть не менее 3 символов");
        }
        if (dto.getTitle().length() > 120) {
            throw new RequiredParamsException("Заголовок события должен быть не более 120 символов");
        }
    }

    public static void validateUpdateEvent(UpdateEventSuperRequest dto) {
        if (dto.getDescription() != null && dto.getDescription().length() > 7000) {
            throw new RequiredParamsException("Описание должно быть не более 7000 символов");
        }
        if (dto.getDescription() != null && dto.getDescription().length() < 20) {
            throw new RequiredParamsException("Описание должно быть более 20 символов");
        }
        if (dto.getAnnotation() != null && dto.getAnnotation().length() < 20) {
            throw new RequiredParamsException("Краткое описание события должно быть более 20 символов");
        }
        if (dto.getAnnotation() != null && dto.getAnnotation().length() > 2000) {
            throw new RequiredParamsException("Краткое описание события должно быть не более 2000 символов");
        }
        if (dto.getTitle() != null && dto.getTitle().length() < 3) {
            throw new RequiredParamsException("Заголовок события должен быть не менее 3 символов");
        }
        if (dto.getTitle() != null && dto.getTitle().length() > 120) {
            throw new RequiredParamsException("Заголовок события должен быть не более 120 символов");
        }
    }

    public static void checkEventDate(LocalDateTime eventDate) {
        if (eventDate != null && eventDate.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new BadTimeException("Дата и время на которые запланировано мероприятие не может быть раньше, чем" +
                    "через два часа с текущего момента: " + eventDate);
        }
    }

    public static void validateEvents(List<Long> categories) {
        if (categories != null && categories.contains(0L)) {
            throw new RequiredParamsException("Не существует категории с Id = 0");
        }
    }

    public static void validateCreateUser(NewUserRequest dto) {
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new RequiredParamsException("Email не может быть пустым либо состоять из пробелов");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new RequiredParamsException("Имя не может быть пустым либо состоять из пробелов");
        }
        if (dto.getName().length() < 2 || dto.getName().length() > 250) {
            throw new RequiredParamsException("Имя должно быть не менее 2 и не более 250 символов");
        }
        if (dto.getEmail().length() < 6 || dto.getEmail().length() > 254) {
            throw new RequiredParamsException("Email должен быть не менее 6 и не более 254 символов");
        }

        int atIndex = dto.getEmail().indexOf("@");

        if (atIndex != -1) {
            String localPart = dto.getEmail().substring(0, atIndex);
            String domainPart = dto.getEmail().substring(atIndex + 1);
            if (localPart.length() > 64) {
                throw new RequiredParamsException("Email.localPart должен быть не более 64 символов");
            }
            String[] domainParts = domainPart.split("\\.");
            for (String part : domainParts) {
                if (part.length() > 63) {
                    throw new RequiredParamsException("Каждая часть Email.domainPart должна быть не более 63 символов");
                }
            }
        }
    }

    public static void validateCreateCategory(NewCategoryDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new RequiredParamsException("Имя не может быть пустым либо состоять из пробелов");
        }
        if (dto.getName().length() > 50) {
            throw new RequiredParamsException("Имя должно быть не более 50 символов");
        }
    }

    public static void validateCreateCompilation(NewCompilationDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new RequiredParamsException("Заголовок не может быть пустым либо состоять из пробелов");
        }
        if (dto.getTitle().length() > 50) {
            throw new RequiredParamsException("Заголовок должно быть не более 50 символов");
        }
    }

    public static void validateUpdateCompilation(UpdateCompilationRequest dto) {
        if (dto.getTitle().isBlank()) {
            throw new RequiredParamsException("Заголовок не может быть пустым либо состоять из пробелов");
        }
        if (dto.getTitle().length() > 50) {
            throw new RequiredParamsException("Заголовок должно быть не более 50 символов");
        }
    }
}
