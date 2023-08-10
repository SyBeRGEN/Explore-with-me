package ru.practicum.base.mapper;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.dto.user.UserDto;
import ru.practicum.base.dto.user.UserShortDto;
import ru.practicum.base.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    /**
     * Method under test: {@link UserMapper#toEntity(NewUserRequest)}
     */
    @Test
    void testToEntity() {
        User actualToEntityResult = UserMapper.toEntity(new NewUserRequest("jane.doe@example.org", "Name"));
        assertEquals("jane.doe@example.org", actualToEntityResult.getEmail());
        assertEquals("Name", actualToEntityResult.getName());
        assertNull(actualToEntityResult.getId());
    }

    /**
     * Method under test: {@link UserMapper#toUserDto(User)}
     */
    @Test
    void testToUserDto() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        UserDto actualToUserDtoResult = UserMapper.toUserDto(user);
        assertEquals("jane.doe@example.org", actualToUserDtoResult.getEmail());
        assertEquals("Name", actualToUserDtoResult.getName());
        assertEquals(1L, actualToUserDtoResult.getId().longValue());
    }

    /**
     * Method under test: {@link UserMapper#toUserShortDto(User)}
     */
    @Test
    void testToUserShortDto() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        UserShortDto actualToUserShortDtoResult = UserMapper.toUserShortDto(user);
        assertEquals(1L, actualToUserShortDtoResult.getId().longValue());
        assertEquals("Name", actualToUserShortDtoResult.getName());
    }

    /**
     * Method under test: {@link UserMapper#toUserDtoList(List)}
     */
    @Test
    void testToUserDtoList() {
        assertTrue(UserMapper.toUserDtoList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link UserMapper#toUserDtoList(List)}
     */
    @Test
    void testToUserDtoList2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserDto> actualToUserDtoListResult = UserMapper.toUserDtoList(userList);
        assertEquals(1, actualToUserDtoListResult.size());
        UserDto getResult = actualToUserDtoListResult.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Name", getResult.getName());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link UserMapper#toUserDtoList(List)}
     */
    @Test
    void testToUserDtoList3() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");

        User user1 = new User();
        user1.setEmail("john.smith@example.org");
        user1.setId(2L);
        user1.setName("42");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user);
        List<UserDto> actualToUserDtoListResult = UserMapper.toUserDtoList(userList);
        assertEquals(2, actualToUserDtoListResult.size());
        UserDto getResult = actualToUserDtoListResult.get(0);
        assertEquals("42", getResult.getName());
        UserDto getResult1 = actualToUserDtoListResult.get(1);
        assertEquals("Name", getResult1.getName());
        assertEquals(1L, getResult1.getId().longValue());
        assertEquals("jane.doe@example.org", getResult1.getEmail());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals("john.smith@example.org", getResult.getEmail());
    }
}

