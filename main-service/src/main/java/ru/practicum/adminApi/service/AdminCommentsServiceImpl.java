package ru.practicum.adminApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.base.dto.comments.CommentDto;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.model.Comment;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.utils.Status;
import ru.practicum.base.utils.Validator;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminCommentsServiceImpl implements AdminCommentsService {
    private final CommentMapper commentMapper;
    private final CommentsRepository commentsRepository;

    @Override
    public List<CommentDto> getComments(String text, List<Long> events, LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd, Status status, Long from, Long size) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));
        Validator.validateTime(rangeStart, rangeEnd);

        List<Comment> comments;
        if (text != null && events != null && status != null) {
            // Поиск по тексту, эвентам и статусу
            comments = commentsRepository.findByTextLikeAndEvent_IdInAndStatus(text,
                    events, rangeStart, rangeEnd, status, pageable);
        } else if (text != null && events != null) {
            // Поиск по тексту и эвентам
            comments = commentsRepository.findByTextLikeAndEvent_IdIn(text, events, rangeStart, rangeEnd, pageable);
        } else if (text != null && status != null) {
            // Поиск по тексту и статусу
            comments = commentsRepository.findByTextLikeAndStatus(text, rangeStart, rangeEnd, status, pageable);
        } else if (events != null && status != null) {
            // Поиск по эвентам и статусу
            comments = commentsRepository.findByEvent_IdInAndStatus(events, rangeStart, rangeEnd, status, pageable);
        } else if (text != null) {
            // Поиск только по тексту
            comments = commentsRepository.findByTextLike(text, rangeStart, rangeEnd, pageable);
        } else if (events != null) {
            // Поиск только по эвентам
            comments = commentsRepository.findByEvent_IdIn(events, rangeStart, rangeEnd, pageable);
        } else if (status != null) {
            // Поиск только по статусу
            comments = commentsRepository.findByStatusIn(status, rangeStart, rangeEnd, pageable);
        } else {
            // Поиск по времени
            comments = commentsRepository.findByTime(rangeStart, rangeEnd, pageable);
        }

        return comments
                .stream()
                .map(commentMapper::toDto)
                .collect(toList());
    }

    @Override
    public CommentDto patchComment(Long commentId, Status status) {
        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с id = %s не найден", commentId)));
        if (comment.getStatus().equals(Status.PENDING)) {
            if (!status.equals(Status.PENDING)) {
                if (status.equals(Status.CONFIRMED)) {
                    comment.setStatus(Status.CONFIRMED);
                } else if (status.equals(Status.REJECTED)) {
                    comment.setStatus(Status.REJECTED);
                } else {
                    throw new ConflictException("Статус комментария уже " + status);
                }
            } else {
                throw new ConflictException("Нельзя снова переводить комментарий в статус PENDING");
            }
        } else {
            throw new ConflictException("Не удается опубликовать комментарий: PUBLISHED OR REJECTED");
        }


        try {
            commentsRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Обновлен статус комментария: {}", comment.getStatus());

        return commentMapper.toDto(comment);
    }


    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        Comment comment = commentsRepository.findById(commentDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с id = %s не найден", commentDto.getId())));

        ru.practicum.base.utils.Validator.validateUpdateAndCreateComment(commentDto);

        comment.setText(commentDto.getText());
        comment.setStatus(commentDto.getStatus());

        try {
            commentsRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Попытка создать дублирующуюся запись, которая уже существует");
        }

        log.info("Обновлен комментарий: {}", comment.getText());

        return commentMapper.toDto(comment);
    }
}
