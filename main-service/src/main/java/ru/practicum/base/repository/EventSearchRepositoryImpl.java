package ru.practicum.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.practicum.base.dto.event.EventSearchDto;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventSearchRepositoryImpl implements EventSearchRepository {
    private final CriteriaBuilder criteriaBuilder;
    private final EntityManager entityManager;

    public EventSearchRepositoryImpl(EntityManager entityManager) {
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.entityManager = entityManager;
    }

    @Override
    public Page<Event> findAllWithFilters(Pageable pageable, EventSearchDto eventSearchDto) {
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Predicate predicate = getPredicate(eventSearchDto, eventRoot);
        criteriaQuery.where(predicate);

        if (pageable.getSort().isUnsorted()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(eventRoot.get("createdOn")));
        }

        TypedQuery<Event> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Event> events = typedQuery.getResultList();

        return new PageImpl<>(events);
    }

    private Predicate getPredicate(EventSearchDto dto, Root<Event> eventRoot) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate annotationPredicate = null;
        if (Objects.nonNull(dto.getText())) {
            annotationPredicate = criteriaBuilder.like(criteriaBuilder.lower(eventRoot.get("annotation")),
                    "%" + dto.getText().toLowerCase() + "%");
        }
        if (Objects.nonNull(dto.getText()) && annotationPredicate == null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(eventRoot.get("description")),
                    "%" + dto.getText().toLowerCase() + "%"));
        } else if (Objects.nonNull(dto.getText())) {
            Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(eventRoot.get("description")),
                    "%" + dto.getText().toLowerCase() + "%");
            predicates.add(criteriaBuilder.or(annotationPredicate, descriptionPredicate));
        }

        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            Join<Event, Category> categoryJoin = eventRoot.join("category");
            predicates.add(categoryJoin.get("id").in(dto.getCategories()));
        }
        if (dto.getPaid() != null && dto.getPaid().equals(Boolean.TRUE)) {
            predicates.add(criteriaBuilder.equal(eventRoot.get("paid"), dto.getPaid()));
        }
        if (dto.getRangeStart() != null || dto.getRangeEnd() != null) {
            LocalDateTime rangeStart = dto.getRangeStart() != null
                    ? dto.getRangeStart()
                    : LocalDateTime.MIN;
            LocalDateTime rangeEnd = dto.getRangeEnd() != null
                    ? dto.getRangeEnd()
                    : LocalDateTime.MAX;
            predicates.add(criteriaBuilder.between(eventRoot.get("date"), rangeStart, rangeEnd));
        } else {
            predicates.add(criteriaBuilder.between(eventRoot.get("date"), LocalDateTime.now(), LocalDateTime.now().plusYears(100)));
        }

        if (dto.getOnlyAvailable() != null && dto.getOnlyAvailable()) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.isNull(eventRoot.get("participantLimit")),
                    criteriaBuilder.greaterThan(
                            criteriaBuilder.diff(eventRoot.get("participantLimit"), eventRoot.get("confirmedRequests")),
                            0L
                    )
            ));
        }
        return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
    }
}
