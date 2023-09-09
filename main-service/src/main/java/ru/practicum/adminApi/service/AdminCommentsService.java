package ru.practicum.adminApi.service;

import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.utils.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminCommentsService {
    List<CommentDto> getComments(String text, List<Long> events, LocalDateTime rangeStart,
                                 LocalDateTime rangeEnd, Status status, Long from, Long size);

    CommentDto patchComment(Long commentId, Status status);

    CommentDto updateComment(CommentDto commentDto);
}
