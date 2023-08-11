package ru.practicum.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.practicum.base.dto.event.EventSearchDto;
import ru.practicum.base.model.Event;

@Repository
public interface EventSearchRepository {
    Page<Event> findAllWithFilters(Pageable pageable, EventSearchDto eventSearchDto);
}
