package ru.practicum.publicApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.publicApi.service.PublicCommentsService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
@Valid
public class PublicCommentsController {
    private final PublicCommentsService service;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@RequestParam(required = false) String text,
                                                        @RequestParam(required = false) List<Long> events,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                        @RequestParam(defaultValue = "0") @PositiveOrZero Long from,
                                                        @RequestParam(defaultValue = "10") @Positive Long size) {
        log.info("Получен запрос GET /admin/comments");

        return new ResponseEntity<>(service.getComments(text, events, rangeStart, rangeEnd, from, size), HttpStatus.OK);
    }
}
