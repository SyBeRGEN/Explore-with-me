package ru.practicum.base.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#UserDto()}
     *   <li>{@link UserDto#setEmail(String)}
     *   <li>{@link UserDto#setId(Long)}
     *   <li>{@link UserDto#setName(String)}
     *   <li>{@link UserDto#getEmail()}
     *   <li>{@link UserDto#getId()}
     *   <li>{@link UserDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserDto actualUserDto = new UserDto();
        actualUserDto.setEmail("jane.doe@example.org");
        actualUserDto.setId(1L);
        actualUserDto.setName("Name");
        assertEquals("jane.doe@example.org", actualUserDto.getEmail());
        assertEquals(1L, actualUserDto.getId().longValue());
        assertEquals("Name", actualUserDto.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDto#UserDto(String, Long, String)}
     *   <li>{@link UserDto#setEmail(String)}
     *   <li>{@link UserDto#setId(Long)}
     *   <li>{@link UserDto#setName(String)}
     *   <li>{@link UserDto#getEmail()}
     *   <li>{@link UserDto#getId()}
     *   <li>{@link UserDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        UserDto actualUserDto = new UserDto("jane.doe@example.org", 1L, "Name");
        actualUserDto.setEmail("jane.doe@example.org");
        actualUserDto.setId(1L);
        actualUserDto.setName("Name");
        assertEquals("jane.doe@example.org", actualUserDto.getEmail());
        assertEquals(1L, actualUserDto.getId().longValue());
        assertEquals("Name", actualUserDto.getName());
    }
}

