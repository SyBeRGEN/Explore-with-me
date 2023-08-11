package ru.practicum.publicApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.compilation.CompilationDto;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.exceptions.NotFoundException;
import ru.practicum.base.model.Category;
import ru.practicum.base.model.Compilation;
import ru.practicum.base.model.Event;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.CompilationRepository;
import ru.practicum.base.utils.State;

@ContextConfiguration(classes = {PublicCompilationsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PublicCompilationsServiceImplTest {
    @MockBean
    private CompilationRepository compilationRepository;

    @Autowired
    private PublicCompilationsServiceImpl publicCompilationsServiceImpl;

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations() {
        when(compilationRepository.findByPinned(Mockito.<Boolean>any(), Mockito.<Pageable>any()))
                .thenReturn(new ArrayList<>());
        assertTrue(publicCompilationsServiceImpl.getCompilations(true, 1L, 3L).isEmpty());
        verify(compilationRepository).findByPinned(Mockito.<Boolean>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations2() {
        Compilation compilation = new Compilation();
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");

        ArrayList<Compilation> compilationList = new ArrayList<>();
        compilationList.add(compilation);
        when(compilationRepository.findByPinned(Mockito.<Boolean>any(), Mockito.<Pageable>any()))
                .thenReturn(compilationList);
        List<CompilationDto> actualCompilations = publicCompilationsServiceImpl.getCompilations(true, 1L, 3L);
        assertEquals(1, actualCompilations.size());
        CompilationDto getResult = actualCompilations.get(0);
        assertEquals(events, getResult.getEvents());
        assertEquals("Dr", getResult.getTitle());
        assertTrue(getResult.getPinned());
        assertEquals(1L, getResult.getId().longValue());
        verify(compilationRepository).findByPinned(Mockito.<Boolean>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations3() {
        Compilation compilation = new Compilation();
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");

        Compilation compilation2 = new Compilation();
        compilation2.setEvents(new ArrayList<>());
        compilation2.setId(2L);
        compilation2.setPinned(false);
        compilation2.setTitle("Mr");

        ArrayList<Compilation> compilationList = new ArrayList<>();
        compilationList.add(compilation2);
        compilationList.add(compilation);
        when(compilationRepository.findByPinned(Mockito.<Boolean>any(), Mockito.<Pageable>any()))
                .thenReturn(compilationList);
        List<CompilationDto> actualCompilations = publicCompilationsServiceImpl.getCompilations(true, 1L, 3L);
        assertEquals(2, actualCompilations.size());
        CompilationDto getResult = actualCompilations.get(0);
        assertEquals("Mr", getResult.getTitle());
        CompilationDto getResult2 = actualCompilations.get(1);
        assertEquals("Dr", getResult2.getTitle());
        assertTrue(getResult2.getPinned());
        assertEquals(1L, getResult2.getId().longValue());
        assertEquals(events, getResult2.getEvents());
        assertFalse(getResult.getPinned());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals(events, getResult.getEvents());
        verify(compilationRepository).findByPinned(Mockito.<Boolean>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations4() {
        when(compilationRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(publicCompilationsServiceImpl.getCompilations(null, 1L, 3L).isEmpty());
        verify(compilationRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations5() {
        assertThrows(ArithmeticException.class, () -> publicCompilationsServiceImpl.getCompilations(null, 1L, 0L));
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilations(Boolean, Long, Long)}
     */
    @Test
    void testGetCompilations6() {
        when(compilationRepository.findAll(Mockito.<Pageable>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> publicCompilationsServiceImpl.getCompilations(null, 1L, 3L));
        verify(compilationRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById() {
        Compilation compilation = new Compilation();
        ArrayList<Event> events = new ArrayList<>();
        compilation.setEvents(events);
        compilation.setId(1L);
        compilation.setPinned(true);
        compilation.setTitle("Dr");
        Optional<Compilation> ofResult = Optional.of(compilation);
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualCompilationById = publicCompilationsServiceImpl.getCompilationById(1L);
        assertEquals(events, actualCompilationById.getEvents());
        assertEquals("Dr", actualCompilationById.getTitle());
        assertTrue(actualCompilationById.getPinned());
        assertEquals(1L, actualCompilationById.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById2() {
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
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualCompilationById = publicCompilationsServiceImpl.getCompilationById(1L);
        assertEquals(events, actualCompilationById.getEvents());
        assertEquals("Dr", actualCompilationById.getTitle());
        assertTrue(actualCompilationById.getPinned());
        assertEquals(1L, actualCompilationById.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation).getTitle();
        verify(compilation).getEvents();
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById3() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

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
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualCompilationById = publicCompilationsServiceImpl.getCompilationById(1L);
        assertEquals(1, actualCompilationById.getEvents().size());
        assertEquals("Dr", actualCompilationById.getTitle());
        assertTrue(actualCompilationById.getPinned());
        assertEquals(1L, actualCompilationById.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation).getTitle();
        verify(compilation).getEvents();
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById4() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        User initiator = new User();
        initiator.setEmail("jane.doe@example.org");
        initiator.setId(1L);
        initiator.setName("Name");

        Location location = new Location();
        location.setLat(10.0f);
        location.setLon(10.0f);

        Event event = new Event();
        event.setAnnotation("Annotation");
        event.setCategory(category);
        event.setConfirmedRequests(1L);
        event.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setDescription("The characteristics of someone or something");
        event.setId(1L);
        event.setInitiator(initiator);
        event.setLocation(location);
        event.setPaid(true);
        event.setParticipantLimit(1L);
        event.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event.setRequestModeration(true);
        event.setState(State.PENDING);
        event.setTitle("Dr");
        event.setViews(1L);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("42");

        User initiator2 = new User();
        initiator2.setEmail("john.smith@example.org");
        initiator2.setId(2L);
        initiator2.setName("42");

        Location location2 = new Location();
        location2.setLat(0.5f);
        location2.setLon(0.5f);

        Event event2 = new Event();
        event2.setAnnotation("42");
        event2.setCategory(category2);
        event2.setConfirmedRequests(0L);
        event2.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setDescription("Description");
        event2.setId(2L);
        event2.setInitiator(initiator2);
        event2.setLocation(location2);
        event2.setPaid(false);
        event2.setParticipantLimit(0L);
        event2.setPublishedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        event2.setRequestModeration(false);
        event2.setState(State.PUBLISHED);
        event2.setTitle("Mr");
        event2.setViews(0L);

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
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompilationDto actualCompilationById = publicCompilationsServiceImpl.getCompilationById(1L);
        assertEquals(2, actualCompilationById.getEvents().size());
        assertEquals("Dr", actualCompilationById.getTitle());
        assertTrue(actualCompilationById.getPinned());
        assertEquals(1L, actualCompilationById.getId().longValue());
        verify(compilationRepository).findById(Mockito.<Long>any());
        verify(compilation).getPinned();
        verify(compilation).getId();
        verify(compilation).getTitle();
        verify(compilation).getEvents();
        verify(compilation).setEvents(Mockito.<List<Event>>any());
        verify(compilation).setId(Mockito.<Long>any());
        verify(compilation).setPinned(Mockito.<Boolean>any());
        verify(compilation).setTitle(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PublicCompilationsServiceImpl#getCompilationById(Long)}
     */
    @Test
    void testGetCompilationById5() {
        when(compilationRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> publicCompilationsServiceImpl.getCompilationById(1L));
        verify(compilationRepository).findById(Mockito.<Long>any());
    }
}

