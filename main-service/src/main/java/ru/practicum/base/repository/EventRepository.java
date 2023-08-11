package ru.practicum.base.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;
import ru.practicum.base.utils.State;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventSearchRepository {
    @Query("SELECT e FROM Event e " +
            "WHERE (:users IS NULL OR e.initiator.id IN (:users)) " +
            "AND (:states IS NULL OR e.state IN (:states)) " +
            "AND (:categories IS NULL OR e.category.id IN (:categories)) " +
            "AND (cast(:rangeStart as timestamp ) IS NULL OR e.date " +
            "BETWEEN cast(:rangeStart as timestamp) AND cast(:rangeEnd as timestamp ))")
    List<Event> findEventsByParams(
            @Param("users") List<Long> users,
            @Param("states") List<State> states,
            @Param("categories") List<Long> categories,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("rangeEnd") LocalDateTime rangeEnd,
            Pageable pageable);

    Optional<Event> findByIdAndInitiator_Id(Long id, Long initiatorId);

    List<Event> findByIdIn(Collection<Long> ids);

    boolean existsByCategory(Category category);

    boolean existsByIdAndInitiator_Id(Long id, Long initiatorId);

}