package ru.practicum.base.utils;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.category.NewCategoryDto;
import ru.practicum.base.dto.compilation.NewCompilationDto;
import ru.practicum.base.dto.compilation.UpdateCompilationRequest;
import ru.practicum.base.dto.event.NewEventDto;
import ru.practicum.base.dto.event.UpdateEventSuperRequest;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.exceptions.BadTimeException;
import ru.practicum.base.exceptions.RequiredParamsException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent() {
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateEvent(new NewEventDto()));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent2() {
        NewEventDto newEventDto = new NewEventDto();
        newEventDto.setAnnotation(null);
        newEventDto.setDescription("foo");
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateEvent(newEventDto));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent3() {
        NewEventDto newEventDto = new NewEventDto();
        newEventDto.setAnnotation("foo");
        newEventDto.setDescription("foo");
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateEvent(newEventDto));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent4() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(RequiredParamsException.class,
                () -> Validator.validateCreateEvent(new NewEventDto("Описание не может быть является обязательным полем", 1L,
                        "The characteristics of someone or something", eventDate, new Location(), true, 1L, true, "Dr")));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent5() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateEvent(new NewEventDto("Annotation", 1L,
                "The characteristics of someone or something", eventDate, new Location(), true, 1L, true, "Dr")));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent6() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateEvent(new NewEventDto("", 1L,
                "The characteristics of someone or something", eventDate, new Location(), true, 1L, true, "Dr")));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent7() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(RequiredParamsException.class,
                () -> Validator.validateCreateEvent(new NewEventDto("Описание не может быть является обязательным полем", 1L,
                        "", eventDate, new Location(), true, 1L, true, "Dr")));
    }

    /**
     * Method under test: {@link Validator#validateCreateEvent(NewEventDto)}
     */
    @Test
    void testValidateCreateEvent8() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        Location location = new Location();
        NewEventDto newEventDto = new NewEventDto("Описание не может быть является обязательным полем", 1L,
                "The characteristics of someone or something", eventDate, location, true, 1L, true, "Prof");

        Validator.validateCreateEvent(newEventDto);
        assertEquals("Описание не может быть является обязательным полем", newEventDto.getAnnotation());
        assertEquals("Prof", newEventDto.getTitle());
        assertTrue(newEventDto.getRequestModeration());
        assertEquals(1L, newEventDto.getParticipantLimit().longValue());
        assertTrue(newEventDto.getPaid());
        assertSame(location, newEventDto.getLocation());
        assertEquals(1L, newEventDto.getCategory().longValue());
        assertEquals("01:01", newEventDto.getEventDate().toLocalTime().toString());
        assertEquals("The characteristics of someone or something", newEventDto.getDescription());
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent() {
        UpdateEventSuperRequest updateEventSuperRequest = new UpdateEventSuperRequest();
        Validator.validateUpdateEvent(updateEventSuperRequest);
        assertNull(updateEventSuperRequest.getAnnotation());
        assertNull(updateEventSuperRequest.getTitle());
        assertNull(updateEventSuperRequest.getRequestModeration());
        assertNull(updateEventSuperRequest.getParticipantLimit());
        assertNull(updateEventSuperRequest.getPaid());
        assertNull(updateEventSuperRequest.getLocation());
        assertNull(updateEventSuperRequest.getEventDate());
        assertNull(updateEventSuperRequest.getDescription());
        assertNull(updateEventSuperRequest.getCategory());
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent2() {
        UpdateEventSuperRequest updateEventSuperRequest = new UpdateEventSuperRequest();
        updateEventSuperRequest.setAnnotation(null);
        updateEventSuperRequest.setDescription(null);
        updateEventSuperRequest.setTitle("foo");
        Validator.validateUpdateEvent(updateEventSuperRequest);
        assertEquals("foo", updateEventSuperRequest.getTitle());
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent3() {
        UpdateEventSuperRequest updateEventSuperRequest = new UpdateEventSuperRequest();
        updateEventSuperRequest.setAnnotation("foo");
        updateEventSuperRequest.setDescription(null);
        updateEventSuperRequest.setTitle(null);
        assertThrows(RequiredParamsException.class, () -> Validator.validateUpdateEvent(updateEventSuperRequest));
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent4() {
        UpdateEventSuperRequest updateEventSuperRequest = new UpdateEventSuperRequest();
        updateEventSuperRequest.setAnnotation(null);
        updateEventSuperRequest.setDescription("foo");
        updateEventSuperRequest.setTitle(null);
        assertThrows(RequiredParamsException.class, () -> Validator.validateUpdateEvent(updateEventSuperRequest));
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent5() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(RequiredParamsException.class,
                () -> Validator.validateUpdateEvent(new UpdateEventSuperRequest("Annotation", 1L,
                        "The characteristics of someone or something", eventDate, new Location(), true, 1L, true, "Dr")));
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent6() {
        UpdateEventSuperRequest updateEventSuperRequest = new UpdateEventSuperRequest();
        updateEventSuperRequest.setTitle("Dr");
        assertThrows(RequiredParamsException.class, () -> Validator.validateUpdateEvent(updateEventSuperRequest));
    }

    /**
     * Method under test: {@link Validator#validateUpdateEvent(UpdateEventSuperRequest)}
     */
    @Test
    void testValidateUpdateEvent7() {
        LocalDateTime eventDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(RequiredParamsException.class,
                () -> Validator
                        .validateUpdateEvent(new UpdateEventSuperRequest("Краткое описание события должно быть более 20 символов",
                                1L, "The characteristics of someone or something", eventDate, new Location(), true, 1L, true, "Dr")));
    }

    /**
     * Method under test: {@link Validator#checkEventDate(LocalDateTime)}
     */
    @Test
    void testCheckEventDate() {
        assertThrows(BadTimeException.class, () -> Validator.checkEventDate(LocalDateTime.of(1, 1, 1, 1, 1)));
    }

    /**
     * Method under test: {@link Validator#validateEvents(List)}
     */
    @Test
    void testValidateEvents() {
        ArrayList<Long> resultLongList = new ArrayList<>();
        resultLongList.add(0L);
        assertThrows(RequiredParamsException.class, () -> Validator.validateEvents(resultLongList));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser() {
        NewUserRequest newUserRequest = new NewUserRequest("jane.doe@example.org", "Name");

        Validator.validateCreateUser(newUserRequest);
        assertEquals("jane.doe@example.org", newUserRequest.getEmail());
        assertEquals("Name", newUserRequest.getName());
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser2() {
        NewUserRequest newUserRequest = new NewUserRequest("jane.doe@example.org", "Name");
        newUserRequest.setName(null);
        newUserRequest.setEmail(null);
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateUser(newUserRequest));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser3() {
        NewUserRequest newUserRequest = new NewUserRequest("jane.doe@example.org", "Name");
        newUserRequest.setName(null);
        newUserRequest.setEmail("foo");
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateUser(newUserRequest));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser4() {
        NewUserRequest newUserRequest = new NewUserRequest("jane.doe@example.org", "Name");
        newUserRequest.setName("foo");
        newUserRequest.setEmail("foo");
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateUser(newUserRequest));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser5() {
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateUser(new NewUserRequest("", "Name")));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser6() {
        assertThrows(RequiredParamsException.class,
                () -> Validator.validateCreateUser(new NewUserRequest("jane.doe@example.org", "@")));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser7() {
        assertThrows(RequiredParamsException.class,
                () -> Validator.validateCreateUser(new NewUserRequest("jane.doe@example.org", "")));
    }

    /**
     * Method under test: {@link Validator#validateCreateUser(NewUserRequest)}
     */
    @Test
    void testValidateCreateUser8() {
        NewUserRequest newUserRequest = new NewUserRequest("jane.doe@example.org", "Name");
        newUserRequest.setName("foo");
        newUserRequest.setEmail("Email должен быть не менее 6 и не более 254 символов");
        Validator.validateCreateUser(newUserRequest);
        assertEquals("Email должен быть не менее 6 и не более 254 символов", newUserRequest.getEmail());
        assertEquals("foo", newUserRequest.getName());
    }

    /**
     * Method under test: {@link Validator#validateCreateCategory(NewCategoryDto)}
     */
    @Test
    void testValidateCreateCategory() {
        NewCategoryDto newCategoryDto = new NewCategoryDto("Name");
        Validator.validateCreateCategory(newCategoryDto);
        assertEquals("Name", newCategoryDto.getName());
    }

    /**
     * Method under test: {@link Validator#validateCreateCategory(NewCategoryDto)}
     */
    @Test
    void testValidateCreateCategory2() {
        NewCategoryDto newCategoryDto = new NewCategoryDto("Name");
        newCategoryDto.setName(null);
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateCategory(newCategoryDto));
    }

    /**
     * Method under test: {@link Validator#validateCreateCategory(NewCategoryDto)}
     */
    @Test
    void testValidateCreateCategory3() {
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateCategory(new NewCategoryDto("")));
    }

    /**
     * Method under test: {@link Validator#validateCreateCategory(NewCategoryDto)}
     */
    @Test
    void testValidateCreateCategory4() {
        NewCategoryDto newCategoryDto = new NewCategoryDto("Name");
        newCategoryDto.setName(
                "Имя не может быть пустым либо состоять из пробеловИмя не может быть пустым либо состоять из пробелов");
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateCategory(newCategoryDto));
    }

    /**
     * Method under test: {@link Validator#validateCreateCompilation(NewCompilationDto)}
     */
    @Test
    void testValidateCreateCompilation() {
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateCompilation(new NewCompilationDto()));
    }

    /**
     * Method under test: {@link Validator#validateCreateCompilation(NewCompilationDto)}
     */
    @Test
    void testValidateCreateCompilation2() {
        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setTitle("foo");
        Validator.validateCreateCompilation(newCompilationDto);
        assertEquals("foo", newCompilationDto.getTitle());
        assertFalse(newCompilationDto.getPinned());
    }

    /**
     * Method under test: {@link Validator#validateCreateCompilation(NewCompilationDto)}
     */
    @Test
    void testValidateCreateCompilation3() {
        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setTitle("");
        assertThrows(RequiredParamsException.class, () -> Validator.validateCreateCompilation(newCompilationDto));
    }

    /**
     * Method under test: {@link Validator#validateUpdateCompilation(UpdateCompilationRequest)}
     */
    @Test
    void testValidateUpdateCompilation() {
        UpdateCompilationRequest updateCompilationRequest = new UpdateCompilationRequest();
        updateCompilationRequest.setTitle("Dr");
        Validator.validateUpdateCompilation(updateCompilationRequest);
        assertEquals("Dr", updateCompilationRequest.getTitle());
    }

    /**
     * Method under test: {@link Validator#validateUpdateCompilation(UpdateCompilationRequest)}
     */
    @Test
    void testValidateUpdateCompilation2() {
        UpdateCompilationRequest updateCompilationRequest = new UpdateCompilationRequest();
        updateCompilationRequest.setTitle("");
        assertThrows(RequiredParamsException.class, () -> Validator.validateUpdateCompilation(updateCompilationRequest));
    }
}

