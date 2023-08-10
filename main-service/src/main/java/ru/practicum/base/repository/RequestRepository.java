package ru.practicum.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.base.model.Request;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByEvent_Id(Long id);

    List<Request> findByIdIn(Collection<Long> ids);

    List<Request> findByRequester_Id(Long id);

    boolean existsByRequester_IdAndEvent_Id(Long id, Long eventId);

    Optional<Request> findByIdAndRequester_Id(Long id, Long requesterId);
}
