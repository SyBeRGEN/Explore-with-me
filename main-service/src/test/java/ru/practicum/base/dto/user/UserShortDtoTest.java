package ru.practicum.base.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserShortDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserShortDto#UserShortDto()}
     *   <li>{@link UserShortDto#setId(Long)}
     *   <li>{@link UserShortDto#setName(String)}
     *   <li>{@link UserShortDto#getId()}
     *   <li>{@link UserShortDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserShortDto actualUserShortDto = new UserShortDto();
        actualUserShortDto.setId(1L);
        actualUserShortDto.setName("Name");
        assertEquals(1L, actualUserShortDto.getId().longValue());
        assertEquals("Name", actualUserShortDto.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserShortDto#UserShortDto(Long, String)}
     *   <li>{@link UserShortDto#setId(Long)}
     *   <li>{@link UserShortDto#setName(String)}
     *   <li>{@link UserShortDto#getId()}
     *   <li>{@link UserShortDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        UserShortDto actualUserShortDto = new UserShortDto(1L, "Name");
        actualUserShortDto.setId(1L);
        actualUserShortDto.setName("Name");
        assertEquals(1L, actualUserShortDto.getId().longValue());
        assertEquals("Name", actualUserShortDto.getName());
    }
}

