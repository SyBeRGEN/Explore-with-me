package ru.practicum.privateApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.exceptions.NotPublishedException;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.model.Comment;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrivateCommentServiceImpl implements PrivateCommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto createComment(Long userId, CommentDto commentDto) {
        commentDto.setCommentatorId(userId);
        commentDto.setCreated(LocalDateTime.now());
        commentDto.setStatus(Status.PENDING);

        Comment comment = commentMapper.toEntity(commentDto);

        if (comment.getEvent().getState().equals(State.PUBLISHED)) {
            commentsRepository.save(comment);
        } else {
            throw new NotPublishedException("Событие еще не опубликовано");
        }
        log.info("Добавлен комментарий {}", commentDto.getText());

        return commentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
        log.info("Удален комментарий с id = {}", commentId);
    }

    @Override
    public CommentDto updateComment(Long userId, CommentDto commentDto) {
        Comment comment = commentsRepository.findById(commentDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с id = %s не найден", commentDto.getId())));

        if (userId.equals(comment.getCommentator().getId())) {
            comment.setText(commentDto.getText());
            comment.setCreated(LocalDateTime.now());
            comment.setStatus(Status.PENDING);
        } else {
            throw new ConflictException("Пользователь не является комментатором");
        }

        try {
            commentsRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }
        log.info("Обновлен комментарий {}", commentDto.getText());

        return commentMapper.toDto(comment);
    }

    @Override
    public List<CommentDto> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentsRepository.findByCommentator_IdOrderByCreatedAsc(userId);
        log.info("Получен список комментариев пользователя {}", userId);
        return comments
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

}
