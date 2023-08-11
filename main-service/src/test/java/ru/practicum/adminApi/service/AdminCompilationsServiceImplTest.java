package ru.practicum.adminApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.compilation.UpdateCompilationRequest;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.exceptions.ConflictException;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Compilation;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CompilationRepository;
import ru.practicum.base.repository.EventRepository;
import ru.practicum.base.utils.State;

@ContextConfiguration(classes = {AdminCompilationsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminCompilationsServiceImplTest {
    @Autowired
    private AdminCompilationsServiceImpl adminCompilationsServiceImpl;

    @MockBean
    private CompilationRepository compilationRepository;

    @MockBean
    private EventRepository eventRepository;

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#saveCompilation(NewCompilationDto)}
     */
    @Test
    void testSaveCompilation() {
        Compilation compilation = new Compilation();
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        when(compilationRepository.save(Mockito.<Compilation>any())).thenReturn(compilation);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setTitle("Dr");
        ArrayList<Long> events = new ArrayList<>();
        newCompilationDto.setEvents(events);
        CompilationDto actualSaveCompilationResult = adminCompilationsServiceImpl.saveCompilation(newCompilationDto);
        assertEquals(events, actualSaveCompilationResult.getEvents());
        assertEquals("Dr", actualSaveCompilationResult.getTitle());
        assertTrue(actualSaveCompilationResult.getPinned());
        assertEquals(1L, actualSaveCompilationResult.getId().longValue());
        verify(compilationRepository).save(Mockito.<Compilation>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#saveCompilation(NewCompilationDto)}
     */
    @Test
    void testSaveCompilation2() {
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(new ArrayList<>());
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        when(compilationRepository.save(Mockito.<Compilation>any())).thenReturn(compilation);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setTitle("Dr");
        ArrayList<Long> events = new ArrayList<>();
        newCompilationDto.setEvents(events);
        CompilationDto actualSaveCompilationResult = adminCompilationsServiceImpl.saveCompilation(newCompilationDto);
        assertEquals(events, actualSaveCompilationResult.getEvents());
        assertEquals("Dr", actualSaveCompilationResult.getTitle());
        assertTrue(actualSaveCompilationResult.getPinned());
        assertEquals(1L, actualSaveCompilationResult.getId().longValue());
        verify(compilationRepository).save(Mockito.<Compilation>any());
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#saveCompilation(NewCompilationDto)}
     */
    @Test
    void testSaveCompilation3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Добавлена подборка: {}");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Добавлена подборка: {}");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Добавлена подборка: {}");
        event.setCategory(category);
        event.setConfirmedRequests(50L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(50L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(50L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(eventList);
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        when(compilationRepository.save(Mockito.<Compilation>any())).thenReturn(compilation);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setTitle("Dr");
        newCompilationDto.setEvents(new ArrayList<>());
        CompilationDto actualSaveCompilationResult = adminCompilationsServiceImpl.saveCompilation(newCompilationDto);
        assertEquals(1, actualSaveCompilationResult.getEvents().size());
        assertEquals("Dr", actualSaveCompilationResult.getTitle());
        assertTrue(actualSaveCompilationResult.getPinned());
        assertEquals(1L, actualSaveCompilationResult.getId().longValue());
        verify(compilationRepository).save(Mockito.<Compilation>any());
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#saveCompilation(NewCompilationDto)}
     */
    @Test
    void testSaveCompilation4() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Добавлена подборка: {}");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Добавлена подборка: {}");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Добавлена подборка: {}");
        event.setCategory(category);
        event.setConfirmedRequests(50L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(50L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(50L);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Name");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("Name");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("Annotation");
        event2.setCategory(category2);
        event2.setConfirmedRequests(2L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("Добавлена подборка: {}");
        event2.setId(2L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(false);
        event2.setParticipantLimit(2L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(false);
        event2.setState(State.PUBLISHED);
        event2.setTitle("Mr");
        event2.setViews(2L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event2);
        eventList.add(event);
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(eventList);
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        when(compilationRepository.save(Mockito.<Compilation>any())).thenReturn(compilation);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setTitle("Dr");
        newCompilationDto.setEvents(new ArrayList<>());
        CompilationDto actualSaveCompilationResult = adminCompilationsServiceImpl.saveCompilation(newCompilationDto);
        assertEquals(2, actualSaveCompilationResult.getEvents().size());
        assertEquals("Dr", actualSaveCompilationResult.getTitle());
        assertTrue(actualSaveCompilationResult.getPinned());
        assertEquals(1L, actualSaveCompilationResult.getId().longValue());
        verify(compilationRepository).save(Mockito.<Compilation>any());
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#deleteCompilation(Long)}
     */
    @Test
    void testDeleteCompilation() {
        doNothing().when(compilationRepository).deleteById(Mockito.<Long>any());
        when(compilationRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        adminCompilationsServiceImpl.deleteCompilation(1L);
        verify(compilationRepository).existsById(Mockito.<Long>any());
        verify(compilationRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#deleteCompilation(Long)}
     */
    @Test
    void testDeleteCompilation2() {
        doThrow(new DataIntegrityViolationException("Удалена подборка с id = {}")).when(compilationRepository)
                .deleteById(Mockito.<Long>any());
        when(compilationRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        assertThrows(DataIntegrityViolationException.class, () -> adminCompilationsServiceImpl.deleteCompilation(1L));
        verify(compilationRepository).existsById(Mockito.<Long>any());
        verify(compilationRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#deleteCompilation(Long)}
     */
    @Test
    void testDeleteCompilation3() {
        when(compilationRepository.existsById(Mockito.<Long>any())).thenReturn(false);
        adminCompilationsServiceImpl.deleteCompilation(1L);
        verify(compilationRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation() {
        Compilation compilation = new Compilation();
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doNothing().when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualUpdateCompilationResult = adminCompilationsServiceImpl.updateCompilation(1L,
                new UpdateCompilationRequest());
        assertEquals(events, actualUpdateCompilationResult.getEvents());
        assertEquals("Dr", actualUpdateCompilationResult.getTitle());
        assertTrue(actualUpdateCompilationResult.getPinned());
        assertEquals(1L, actualUpdateCompilationResult.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation2() {
        Compilation compilation = new Compilation();
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doThrow(new DataIntegrityViolationException("events")).when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminCompilationsServiceImpl.updateCompilation(1L, new UpdateCompilationRequest()));
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation3() {
        Compilation compilation = new Compilation();
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doThrow(new ConflictException("An error occurred")).when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ConflictException.class,
                () -> adminCompilationsServiceImpl.updateCompilation(1L, new UpdateCompilationRequest()));
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation4() {
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(new ArrayList<>());
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doNothing().when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualUpdateCompilationResult = adminCompilationsServiceImpl.updateCompilation(1L,
                new UpdateCompilationRequest());
        assertEquals(events, actualUpdateCompilationResult.getEvents());
        assertEquals("Dr", actualUpdateCompilationResult.getTitle());
        assertTrue(actualUpdateCompilationResult.getPinned());
        assertEquals(1L, actualUpdateCompilationResult.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation, atLeast(1)).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation5() {
        Category category = new Category();
        category.setId(1L);
        category.setName("events");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("events");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("events");
        event.setCategory(category);
        event.setConfirmedRequests(3L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(3L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(3L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(eventList);
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doNothing().when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualUpdateCompilationResult = adminCompilationsServiceImpl.updateCompilation(1L,
                new UpdateCompilationRequest());
        assertEquals(1, actualUpdateCompilationResult.getEvents().size());
        assertEquals("Dr", actualUpdateCompilationResult.getTitle());
        assertTrue(actualUpdateCompilationResult.getPinned());
        assertEquals(1L, actualUpdateCompilationResult.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation, atLeast(1)).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation6() {
        Category category = new Category();
        category.setId(1L);
        category.setName("events");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("events");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("events");
        event.setCategory(category);
        event.setConfirmedRequests(3L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(3L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(3L);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("pinned");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("pinned");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("pinned");
        event2.setCategory(category2);
        event2.setConfirmedRequests(1L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("events");
        event2.setId(2L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(false);
        event2.setParticipantLimit(1L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(false);
        event2.setState(State.PUBLISHED);
        event2.setTitle("Mr");
        event2.setViews(1L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event2);
        eventList.add(event);
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(eventList);
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doNothing().when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualUpdateCompilationResult = adminCompilationsServiceImpl.updateCompilation(1L,
                new UpdateCompilationRequest());
        assertEquals(2, actualUpdateCompilationResult.getEvents().size());
        assertEquals("Dr", actualUpdateCompilationResult.getTitle());
        assertTrue(actualUpdateCompilationResult.getPinned());
        assertEquals(1L, actualUpdateCompilationResult.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation, atLeast(1)).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation7() {
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                () -> adminCompilationsServiceImpl.updateCompilation(1L, new UpdateCompilationRequest()));
        verify(compilationRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation8() {
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(new ArrayList<>());
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doNothing().when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

        UpdateCompilationRequest updateCompilationRequest = new UpdateCompilationRequest();
        updateCompilationRequest.setEvents(new ArrayList<>());
        CompilationDto actualUpdateCompilationResult = adminCompilationsServiceImpl.updateCompilation(1L,
                updateCompilationRequest);
        assertEquals(events, actualUpdateCompilationResult.getEvents());
        assertEquals("Dr", actualUpdateCompilationResult.getTitle());
        assertTrue(actualUpdateCompilationResult.getPinned());
        assertEquals(1L, actualUpdateCompilationResult.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation, atLeast(1)).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation9() {
        Compilation compilation = mock(Compilation.class);
        when(compilation.getPinned()).thenReturn(true);
        when(compilation.getId()).thenReturn(1L);
        when(compilation.getTitle()).thenReturn("Dr");
        when(compilation.getEvents()).thenReturn(new ArrayList<>());
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        doNothing().when(compilationRepository).flush();
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any())).thenReturn(new ArrayList<>());

        UpdateCompilationRequest updateCompilationRequest = new UpdateCompilationRequest(new ArrayList<>(), true, "Dr");
        updateCompilationRequest.setEvents(new ArrayList<>());
        CompilationDto actualUpdateCompilationResult = adminCompilationsServiceImpl.updateCompilation(1L,
                updateCompilationRequest);
        assertEquals(events, actualUpdateCompilationResult.getEvents());
        assertEquals("Dr", actualUpdateCompilationResult.getTitle());
        assertTrue(actualUpdateCompilationResult.getPinned());
        assertEquals(1L, actualUpdateCompilationResult.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilationRepository).flush();
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation, atLeast(1)).getTitle();
        verify(compilation).getEvents();
        verify(compilation, atLeast(1)).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }

    /**
     * Method under test: {@link AdminCompilationsServiceImpl#updateCompilation(Long, UpdateCompilationRequest)}
     */
    @Test
    void testUpdateCompilation10() {
        Compilation compilation = mock(Compilation.class);
        doNothing().when(compilation).setEvents(Mockito.<List<Event>>any());
        doNothing().when(compilation).setId(Mockito.<Long>any());
        doNothing().when(compilation).setPinned(Mockito.<Boolean>any());
        doNothing().when(compilation).setTitle(Mockito.<String>any());
        compilation.setEvents(new ArrayList<>());
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(eventRepository.findByIdIn(Mockito.<Collection<Long>>any()))
                .thenThrow(new DataIntegrityViolationException("events"));

        UpdateCompilationRequest updateCompilationRequest = new UpdateCompilationRequest();
        updateCompilationRequest.setEvents(new ArrayList<>());
        assertThrows(DataIntegrityViolationException.class,
                () -> adminCompilationsServiceImpl.updateCompilation(1L, updateCompilationRequest));
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
        verify(eventRepository).findByIdIn(Mockito.<Collection<Long>>any());
    }
}

