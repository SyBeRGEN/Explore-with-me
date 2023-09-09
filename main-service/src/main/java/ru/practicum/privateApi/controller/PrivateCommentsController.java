package ru.practicum.privateApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.privateApi.service.PrivateCommentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users/{userId}/comments")
@Slf4j
@Valid
@RequiredArgsConstructor
public class PrivateCommentsController {
    private final PrivateCommentService service;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable Long userId,
                                                    @RequestBody CommentDto commentDto) {
        log.info("Получен запрос POST /users/{}/comments", userId);

        return new ResponseEntity<>(service.createComment(userId, commentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId,
                                              @PathVariable Long commentId) {
        log.info("Получен запрос DELETE /users/{}/comments/{}", userId, commentId);
        service.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long userId,
                                                    @RequestBody CommentDto commentDto) {
        log.info("Получен запрос PATCH /users/{}/comments", userId);

        return new ResponseEntity<>(service.updateComment(userId, commentDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByUserId(@PathVariable Long userId) {
        log.info("Получен запрос GET /users/{}/comments", userId);

        return new ResponseEntity<>(service.getCommentsByUserId(userId), HttpStatus.OK);
    }
}
