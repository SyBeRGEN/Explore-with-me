package ru.practicum.publicApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.base.dto.comments.CommentDto;
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
@Transactional(readOnly = true)
public class PublicCommentsServiceImpl implements PublicCommentsService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getComments(String text, List<Long> events, LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd, Long from, Long size) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                Sort.by(Sort.Direction.ASC, "id"));
        Validator.validateTime(rangeStart, rangeEnd);

        List<Comment> comments;
        if (text != null && events != null) {
            // Поиск по тексту, эвентам и статусу
            comments = commentsRepository.findByTextLikeAndEvent_IdInAndStatus(text,
                    events, rangeStart, rangeEnd, Status.CONFIRMED, pageable);
        } else if (text != null) {
            // Поиск по тексту и статусу
            comments = commentsRepository.findByTextLikeAndStatus(text, rangeStart, rangeEnd, Status.CONFIRMED, pageable);
        } else if (events != null) {
            // Поиск по эвентам и статусу
            comments = commentsRepository.findByEvent_IdInAndStatus(events, rangeStart, rangeEnd, Status.CONFIRMED, pageable);
        } else {
            // Поиск только по статусу
            comments = commentsRepository.findByStatusIn(Status.CONFIRMED, rangeStart, rangeEnd, pageable);
        }

        return comments
                .stream()
                .map(commentMapper::toDto)
                .collect(toList());
    }
}
