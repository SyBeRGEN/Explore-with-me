package ru.practicum.publicApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.EndpointHitDto;
import ru.practicum.StatsClient;
import ru.practicum.ViewStatsOutputDto;
import ru.practicum.base.dto.event.EventFullDto;
import ru.practicum.base.dto.event.EventSearchDto;
import ru.practicum.base.dto.event.EventShortDto;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.exceptions.NotPublishedException;
import ru.practicum.base.mapper.EventMapper;
import ru.practicum.base.model.Event;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.State;
import ru.practicum.base.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class PublicEventsServiceImpl implements PublicEventsService {
    private final StatsClient statsClient;
    private final EventRepository eventRepository;
    @Value("${main.service.name}")
    private String serviceName;

    @Override
    public List<EventShortDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd, Boolean onlyAvailable, String sort, Long from, Long size,
                                         HttpServletRequest httpServletRequest) {
        Validator.validateEvents(categories);
        PageRequest pageable = pageableForEvent(sort, from, size);
        EventSearchDto eventSearchDto = createEventSearchDto(text, categories, rangeStart, rangeEnd, paid);
        List<EventShortDto> eventShortDtoList = EventMapper.toEventShortDtoList(
                eventRepository.findAllWithFilters(pageable, eventSearchDto).toList());

        log.info("Получен список событий размером: " + eventShortDtoList.size());

        saveRequest(httpServletRequest);

        return eventShortDtoList;
    }

    @Override
    public EventFullDto getEventById(Long eventId, HttpServletRequest httpServletRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Событие с указанным eventId = %s не найдено", eventId)));
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new NotPublishedException(String.format("Событие с eventId = %s еще не опубликовано", eventId));
        }
        saveRequest(httpServletRequest);

        log.info("Получено событие с id = " + eventId);
        List<ViewStatsOutputDto> viewsList = getRequestedViewStats(event.getCreatedOn(), event.getDate(), "/events/" + eventId);

        event.setViews(viewsList.get(0).getHits());
        eventRepository.flush();
        return EventMapper.toEventFullDto(event);
    }

    private PageRequest pageableForEvent(String sort, Long from, Long size) {
        PageRequest pageable = null;
        if (sort == null || sort.equalsIgnoreCase("EVENT_DATE")) {
            pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                    Sort.by(Sort.Direction.ASC, "EVENT_DATE"));
        } else if (sort.equalsIgnoreCase("VIEWS")) {
            pageable = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size),
                    Sort.Direction.ASC, "VIEWS");
        }
        return pageable;
    }

    private EventSearchDto createEventSearchDto(String text, List<Long> categories, LocalDateTime rangeStart,
                                                LocalDateTime rangeEnd, Boolean paid) {
        return EventSearchDto.builder()
                .text(text)
                .categories(categories)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .paid(paid)
                .build();
    }

    private void saveRequest(HttpServletRequest request) {
        EndpointHitDto endpointHit = EndpointHitDto.builder()
                .ip(request.getRemoteAddr())
                .uri(request.getRequestURI())
                .app(serviceName)
                .timestamp(LocalDateTime.now())
                .build();
        statsClient.save(endpointHit);
    }

    private List<ViewStatsOutputDto> getRequestedViewStats(LocalDateTime start, LocalDateTime end,
                                                           String uri) {
        ResponseEntity<?> responseEntity = statsClient.getStats(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), uri, true);

        return processList((ArrayList<LinkedHashMap>) responseEntity.getBody());
    }

    private List<ViewStatsOutputDto> processList(ArrayList<LinkedHashMap> inputList) {
        List<ViewStatsOutputDto> outputList = new ArrayList<>();

        for (LinkedHashMap map : inputList) {
            String app = (String) map.get("app");
            String uri = (String) map.get("uri");
            Integer hits = (Integer) map.get("hits");

            ViewStatsOutputDto dto = ViewStatsOutputDto.builder()
                    .app(app)
                    .uri(uri)
                    .hits(hits.longValue())
                    .build();

            outputList.add(dto);
        }

        return outputList;
    }
}
