package ru.practicum.publicApi.service;

import ru.practicum.base.dto.comments.CommentDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PublicCommentsService {
    List<CommentDto> getComments(String text, List<Long> events,
                                 LocalDateTime rangeStart, LocalDateTime rangeEnd, Long from, Long size);
}
