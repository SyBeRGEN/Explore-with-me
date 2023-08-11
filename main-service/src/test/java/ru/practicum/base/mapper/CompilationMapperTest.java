package ru.practicum.base.mapper;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Compilation;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.utils.State;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompilationMapperTest {
    /**
     * Method under test: {@link CompilationMapper#toEntity(NewCompilationDto)}
     */
    @Test
    void testToEntity() {
        Compilation actualToEntityResult = CompilationMapper.toEntity(new NewCompilationDto());
        assertNull(actualToEntityResult.getEvents());
        assertNull(actualToEntityResult.getTitle());
        assertFalse(actualToEntityResult.getPinned());
        assertNull(actualToEntityResult.getId());
    }

    /**
     * Method under test: {@link CompilationMapper#toDto(Compilation)}
     */
    @Test
    void testToDto() {
        Compilation compilation = new Compilation();
        ArrayList<Event> eventList = new ArrayList<>();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        CompilationDto actualToDtoResult = CompilationMapper.toDto(compilation);
        assertEquals(eventList, actualToDtoResult.getEvents());
        assertEquals("Dr", actualToDtoResult.getTitle());
        assertTrue(actualToDtoResult.getPinned());
        assertEquals(1L, actualToDtoResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompilationMapper#toDto(Compilation)}
     */
    @Test
    void testToDto2() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(user);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);

        Compilation compilation = new Compilation();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        CompilationDto actualToDtoResult = CompilationMapper.toDto(compilation);
        assertEquals(1, actualToDtoResult.getEvents().size());
        assertEquals("Dr", actualToDtoResult.getTitle());
        assertTrue(actualToDtoResult.getPinned());
        assertEquals(1L, actualToDtoResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompilationMapper#toDto(Compilation)}
     */
    @Test
    void testToDto3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(user);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("42");

        User user1 = new User();
        user1.setEmail("john.smith@example.org");
        user1.setId(2L);
        user1.setName("42");

        Location location1 = new Location();
        location1.setLat(0.5f);
        location1.setLon(0.5f);

        Event event1 = new Event();
        event1.setAnnotation("42");
        event1.setCategory(category1);
        event1.setConfirmedRequests(0L);
        event1.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDescription("Description");
        event1.setId(2L);
        event1.setInitiator(user1);
        event1.setLocation(location1);
        event1.setPaid(false);
        event1.setParticipantLimit(0L);
        event1.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setRequestModeration(false);
        event1.setState(State.PUBLISHED);
        event1.setTitle("Mr");
        event1.setViews(0L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event);

        Compilation compilation = new Compilation();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        CompilationDto actualToDtoResult = CompilationMapper.toDto(compilation);
        assertEquals(2, actualToDtoResult.getEvents().size());
        assertEquals("Dr", actualToDtoResult.getTitle());
        assertTrue(actualToDtoResult.getPinned());
        assertEquals(1L, actualToDtoResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompilationMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList() {
        assertTrue(CompilationMapper.toDtoList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link CompilationMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList2() {
        Compilation compilation = new Compilation();
        ArrayList<Event> eventList = new ArrayList<>();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");

        ArrayList<Compilation> compilationList = new ArrayList<>();
        compilationList.add(compilation);
        List<CompilationDto> actualToDtoListResult = CompilationMapper.toDtoList(compilationList);
        assertEquals(1, actualToDtoListResult.size());
        CompilationDto getResult = actualToDtoListResult.get(0);
        assertEquals(eventList, getResult.getEvents());
        assertEquals("Dr", getResult.getTitle());
        assertTrue(getResult.getPinned());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompilationMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList3() {
        Compilation compilation = new Compilation();
        ArrayList<Event> eventList = new ArrayList<>();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");

        Compilation compilation1 = new Compilation();
        compilation1.setEvents(new ArrayList<>());
        compilation1.setId(2L);
        compilation1.setPinned(false);
        compilation1.setTitle("Mr");

        ArrayList<Compilation> compilationList = new ArrayList<>();
        compilationList.add(compilation1);
        compilationList.add(compilation);
        List<CompilationDto> actualToDtoListResult = CompilationMapper.toDtoList(compilationList);
        assertEquals(2, actualToDtoListResult.size());
        CompilationDto getResult = actualToDtoListResult.get(0);
        assertEquals("Mr", getResult.getTitle());
        CompilationDto getResult1 = actualToDtoListResult.get(1);
        assertEquals("Dr", getResult1.getTitle());
        assertTrue(getResult1.getPinned());
        assertEquals(1L, getResult1.getId().longValue());
        assertEquals(eventList, getResult1.getEvents());
        assertFalse(getResult.getPinned());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals(eventList, getResult.getEvents());
    }

    /**
     * Method under test: {@link CompilationMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList4() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(user);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);

        Compilation compilation = new Compilation();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");

        ArrayList<Compilation> compilationList = new ArrayList<>();
        compilationList.add(compilation);
        List<CompilationDto> actualToDtoListResult = CompilationMapper.toDtoList(compilationList);
        assertEquals(1, actualToDtoListResult.size());
        CompilationDto getResult = actualToDtoListResult.get(0);
        assertEquals(1, getResult.getEvents().size());
        assertEquals("Dr", getResult.getTitle());
        assertTrue(getResult.getPinned());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompilationMapper#toDtoList(List)}
     */
    @Test
    void testToDtoList5() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(user);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("42");

        User user1 = new User();
        user1.setEmail("john.smith@example.org");
        user1.setId(2L);
        user1.setName("42");

        Location location1 = new Location();
        location1.setLat(0.5f);
        location1.setLon(0.5f);

        Event event1 = new Event();
        event1.setAnnotation("42");
        event1.setCategory(category1);
        event1.setConfirmedRequests(0L);
        event1.setCreatedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setDescription("Description");
        event1.setId(2L);
        event1.setInitiator(user1);
        event1.setLocation(location1);
        event1.setPaid(false);
        event1.setParticipantLimit(0L);
        event1.setPublishedOn(LocalDateTime.of(1, 1, 1, 1, 1));
        event1.setRequestModeration(false);
        event1.setState(State.PUBLISHED);
        event1.setTitle("Mr");
        event1.setViews(0L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event);

        Compilation compilation = new Compilation();
        compilation.setEvents(eventList);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");

        ArrayList<Compilation> compilationList = new ArrayList<>();
        compilationList.add(compilation);
        List<CompilationDto> actualToDtoListResult = CompilationMapper.toDtoList(compilationList);
        assertEquals(1, actualToDtoListResult.size());
        CompilationDto getResult = actualToDtoListResult.get(0);
        assertEquals(2, getResult.getEvents().size());
        assertEquals("Dr", getResult.getTitle());
        assertTrue(getResult.getPinned());
        assertEquals(1L, getResult.getId().longValue());
    }
}

