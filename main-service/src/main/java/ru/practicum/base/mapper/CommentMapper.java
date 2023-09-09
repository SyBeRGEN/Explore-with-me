package ru.practicum.base.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Comment;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class CommentMapper {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .created(comment.getCreated())
                .eventId(comment.getEvent().getId())
                .commentatorId(comment.getCommentator().getId())
                .status(comment.getStatus())
                .build();
    }

    public Comment toEntity(CommentDto dto) {
        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Событие с id = %s не найдено", dto.getEventId())));
        User commentator = userRepository.findById(dto.getCommentatorId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("Пользователь с id = %s не найден", dto.getCommentatorId())));

        return Comment.builder()
                .id(dto.getId())
                .text(dto.getText())
                .created(dto.getCreated())
                .event(event)
                .commentator(commentator)
                .status(dto.getStatus())
                .build();
    }
}