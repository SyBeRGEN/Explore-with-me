package ru.practicum.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.adminApi.service.AdminCommentsService;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.utils.Status;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class AdminCommentsController {
    private final AdminCommentsService service;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@RequestParam(value = "text", required = false) String text,
                                                        @RequestParam(value = "events", required = false) List<Long> events,
                                                        @RequestParam("rangeStart") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                        @RequestParam("rangeEnd") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                        @RequestParam(value = "status", required = false) Status status,
                                                        @RequestParam(defaultValue = "0") @PositiveOrZero Long from,
                                                        @RequestParam(defaultValue = "10") @Positive Long size) {
        log.info("Получен запрос GET /admin/comments");

        return new ResponseEntity<>(service.getComments(text, events, rangeStart, rangeEnd, status, from, size), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> patchComment(@PathVariable Long commentId,
                                                   @RequestBody CommentDto commentDto) {
        log.info("Получен запрос PATCH /admin/comments/" + commentId);

        return new ResponseEntity<>(service.patchComment(commentId, commentDto.getStatus()), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
        log.info("Получен запрос PATCH /admin/comments");

        return new ResponseEntity<>(service.updateComment(commentDto), HttpStatus.OK);
    }
}
