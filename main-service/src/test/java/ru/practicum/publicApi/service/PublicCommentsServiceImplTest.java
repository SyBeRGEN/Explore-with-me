package ru.practicum.publicApi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.mapper.CommentMapper;
import ru.practicum.base.repository.CommentsRepository;
import ru.practicum.base.utils.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PublicCommentsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PublicCommentsServiceImplTest {
    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private CommentsRepository commentsRepository;

    @Autowired
    private PublicCommentsServiceImpl publicCommentsServiceImpl;

    /**
     * Method under test: {@link PublicCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments() {
        when(commentsRepository.findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(), Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any()))
                .thenReturn(new ArrayList<>());
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(publicCommentsServiceImpl
                .getComments("Text", events, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                .isEmpty());
        verify(commentsRepository).findByTextLikeAndEvent_IdInAndStatus(Mockito.<String>any(),
                Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(),
                Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments2() {
        when(commentsRepository.findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(publicCommentsServiceImpl
                .getComments(null, events, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                .isEmpty());
        verify(commentsRepository).findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments3() {
        when(commentsRepository.findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> events = new ArrayList<>();
        events.add(1L);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(publicCommentsServiceImpl
                .getComments(null, events, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                .isEmpty());
        verify(commentsRepository).findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments4() {
        when(commentsRepository.findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> events = new ArrayList<>();
        events.add(0L);
        events.add(1L);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(publicCommentsServiceImpl
                .getComments(null, events, rangeStart, LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 3L)
                .isEmpty());
        verify(commentsRepository).findByEvent_IdInAndStatus(Mockito.<Collection<Long>>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any(), Mockito.<Status>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCommentsServiceImpl#getComments(String, List, LocalDateTime, LocalDateTime, Long, Long)}
     */
    @Test
    void testGetComments5() {
        ArrayList<Long> events = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(ArithmeticException.class, () -> publicCommentsServiceImpl.getComments(null, events, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, 0L));
    }
}

