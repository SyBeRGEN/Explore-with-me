package ru.practicum.base.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.base.model.Comment;
import ru.practicum.base.utils.Status;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.commentator.id = :id order by c.created")
    List<Comment> findByCommentator_IdOrderByCreatedAsc(@Param("id") Long id);

    @Query("select c from Comment c " +
            "where lower(c.text) like concat('%', lower(:text), '%') " +
            "AND c.event.id in :events " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR c.created BETWEEN cast(:rangeStart as timestamp) " +
            "AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByTextLikeAndEvent_IdIn(@Param("text") String text,
                                              @Param("events") Collection<Long> events,
                                              @Param("rangeStart") LocalDateTime rangeStart,
                                              @Param("rangeEnd") LocalDateTime rangeEnd,
                                              Pageable pageable);

    @Query("select c from Comment c " +
            "where lower(c.text) like concat('%', lower(:text), '%') " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR " +
            "c.created BETWEEN cast(:rangeStart as timestamp) AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByTextLike(@Param("text") String text,
                                 @Param("rangeStart") LocalDateTime rangeStart,
                                 @Param("rangeEnd") LocalDateTime rangeEnd,
                                 Pageable pageable);

    @Query("select c from Comment c " +
            "where lower(c.text) like concat('%', lower(:text), '%') " +
            "and c.event.id in :events " +
            "AND c.status = :status " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR " +
            "c.created BETWEEN cast(:rangeStart as timestamp) AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByTextLikeAndEvent_IdInAndStatus(@Param("text") String text,
                                                       @Param("events") Collection<Long> events,
                                                       @Param("rangeStart") LocalDateTime rangeStart,
                                                       @Param("rangeEnd") LocalDateTime rangeEnd,
                                                       @Param("status") Status status,
                                                       Pageable pageable);

    @Query("select c from Comment c " +
            "where lower(c.text) like concat('%', lower(:text), '%') " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR " +
            "c.created BETWEEN cast(:rangeStart as timestamp) AND cast(:rangeEnd as timestamp ))" +
            "AND c.status = :status ")
    List<Comment> findByTextLikeAndStatus(@Param("text") String text,
                                          @Param("rangeStart") LocalDateTime rangeStart,
                                          @Param("rangeEnd") LocalDateTime rangeEnd,
                                          @Param("status") Status status,
                                          Pageable pageable);

    @Query("select c from Comment c where c.event.id in :events and c.status = :status " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR c.created BETWEEN cast(:rangeStart as timestamp) " +
            "AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByEvent_IdInAndStatus(@Param("events") Collection<Long> events,
                                            @Param("rangeStart") LocalDateTime rangeStart,
                                            @Param("rangeEnd") LocalDateTime rangeEnd,
                                            @Param("status") Status status,
                                            Pageable pageable);

    @Query("select c from Comment c where c.event.id in :events " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR c.created BETWEEN cast(:rangeStart as timestamp) " +
            "AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByEvent_IdIn(@Param("events") Collection<Long> events,
                                   @Param("rangeStart") LocalDateTime rangeStart,
                                   @Param("rangeEnd") LocalDateTime rangeEnd,
                                   Pageable pageable);

    @Query("select c from Comment c where c.status = :status " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR c.created BETWEEN cast(:rangeStart as timestamp) " +
            "AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByStatusIn(@Param("status") Status status,
                                 @Param("rangeStart") LocalDateTime rangeStart,
                                 @Param("rangeEnd") LocalDateTime rangeEnd,
                                 Pageable pageable);

    @Query("select c from Comment c where (cast(:rangeStart as timestamp ) IS NULL " +
            "OR c.created BETWEEN cast(:rangeStart as timestamp) " +
            "AND cast(:rangeEnd as timestamp ))")
    List<Comment> findByTime(@Param("rangeStart") LocalDateTime rangeStart,
                             @Param("rangeEnd") LocalDateTime rangeEnd,
                             Pageable pageable);
}
