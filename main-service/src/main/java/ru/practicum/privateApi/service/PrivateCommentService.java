package ru.practicum.privateApi.service;

import ru.practicum.base.dto.comments.CommentDto;

import java.util.List;

public interface PrivateCommentService {
    CommentDto createComment(Long userId, CommentDto commentDto);

    void deleteComment(Long commentId);

    CommentDto updateComment(Long userId, CommentDto commentDto);

    List<CommentDto> getCommentsByUserId(Long userId);
}