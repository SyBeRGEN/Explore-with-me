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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.practicum.base.dto.user.NewUserRequest;
import ru.practicum.base.dto.user.UserDto;
import ru.practicum.base.model.User;
import ru.practicum.base.repository.UserRepository;

@ContextConfiguration(classes = {AdminUsersServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminUsersServiceImplTest {
    @Autowired
    private AdminUsersServiceImpl adminUsersServiceImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AdminUsersServiceImpl#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers() {
        when(userRepository.findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any()))
                .thenReturn(new ArrayList<>());
        assertTrue(adminUsersServiceImpl.getUsers(new ArrayList<>(), 1L, 3L).isEmpty());
        verify(userRepository).findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("id");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any())).thenReturn(userList);
        List<UserDto> actualUsers = adminUsersServiceImpl.getUsers(new ArrayList<>(), 1L, 3L);
        assertEquals(1, actualUsers.size());
        UserDto getResult = actualUsers.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("id", getResult.getName());
        assertEquals(1L, getResult.getId().longValue());
        verify(userRepository).findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers3() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("id");

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2L);
        user2.setName("Получен список пользователей с размером: {}");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);
        when(userRepository.findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any())).thenReturn(userList);
        List<UserDto> actualUsers = adminUsersServiceImpl.getUsers(new ArrayList<>(), 1L, 3L);
        assertEquals(2, actualUsers.size());
        UserDto getResult = actualUsers.get(0);
        assertEquals("Получен список пользователей с размером: {}", getResult.getName());
        UserDto getResult2 = actualUsers.get(1);
        assertEquals("id", getResult2.getName());
        assertEquals(1L, getResult2.getId().longValue());
        assertEquals("jane.doe@example.org", getResult2.getEmail());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals("john.smith@example.org", getResult.getEmail());
        verify(userRepository).findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers4() {
        assertThrows(ArithmeticException.class, () -> adminUsersServiceImpl.getUsers(new ArrayList<>(), 1L, 0L));
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers5() {
        when(userRepository.findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any()))
                .thenThrow(new DataIntegrityViolationException("id"));
        assertThrows(DataIntegrityViolationException.class,
                () -> adminUsersServiceImpl.getUsers(new ArrayList<>(), 1L, 3L));
        verify(userRepository).findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#getUsers(List, Long, Long)}
     */
    @Test
    void testGetUsers6() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getName()).thenReturn("Name");
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setName(Mockito.<String>any());
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("id");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any())).thenReturn(userList);
        List<UserDto> actualUsers = adminUsersServiceImpl.getUsers(new ArrayList<>(), 1L, 3L);
        assertEquals(1, actualUsers.size());
        UserDto getResult = actualUsers.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Name", getResult.getName());
        assertEquals(1L, getResult.getId().longValue());
        verify(userRepository).findByIdIn(Mockito.<Collection<Long>>any(), Mockito.<Pageable>any());
        verify(user).getId();
        verify(user).getEmail();
        verify(user).getName();
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#saveUser(NewUserRequest)}
     */
    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        UserDto actualSaveUserResult = adminUsersServiceImpl.saveUser(new NewUserRequest("jane.doe@example.org", "Name"));
        assertEquals("jane.doe@example.org", actualSaveUserResult.getEmail());
        assertEquals("Name", actualSaveUserResult.getName());
        assertEquals(1L, actualSaveUserResult.getId().longValue());
        verify(userRepository).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#saveUser(NewUserRequest)}
     */
    @Test
    void testSaveUser2() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getName()).thenReturn("Name");
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setName(Mockito.<String>any());
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        UserDto actualSaveUserResult = adminUsersServiceImpl.saveUser(new NewUserRequest("jane.doe@example.org", "Name"));
        assertEquals("jane.doe@example.org", actualSaveUserResult.getEmail());
        assertEquals("Name", actualSaveUserResult.getName());
        assertEquals(1L, actualSaveUserResult.getId().longValue());
        verify(userRepository).save(Mockito.<User>any());
        verify(user).getId();
        verify(user, atLeast(1)).getEmail();
        verify(user).getName();
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#saveUser(NewUserRequest)}
     */
    @Test
    void testSaveUser3() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getName()).thenReturn("Name");
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setName(Mockito.<String>any());
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        UserDto actualSaveUserResult = adminUsersServiceImpl
                .saveUser(new NewUserRequest("Добавлен пользователь: {}", "Name"));
        assertEquals("jane.doe@example.org", actualSaveUserResult.getEmail());
        assertEquals("Name", actualSaveUserResult.getName());
        assertEquals(1L, actualSaveUserResult.getId().longValue());
        verify(userRepository).save(Mockito.<User>any());
        verify(user).getId();
        verify(user, atLeast(1)).getEmail();
        verify(user).getName();
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(Mockito.<Long>any());
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        adminUsersServiceImpl.deleteUser(1L);
        verify(userRepository).existsById(Mockito.<Long>any());
        verify(userRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() {
        doThrow(new DataIntegrityViolationException("Удален пользователь с id = {}")).when(userRepository)
                .deleteById(Mockito.<Long>any());
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(true);
        assertThrows(DataIntegrityViolationException.class, () -> adminUsersServiceImpl.deleteUser(1L));
        verify(userRepository).existsById(Mockito.<Long>any());
        verify(userRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminUsersServiceImpl#deleteUser(Long)}
     */
    @Test
    void testDeleteUser3() {
        when(userRepository.existsById(Mockito.<Long>any())).thenReturn(false);
        adminUsersServiceImpl.deleteUser(1L);
        verify(userRepository).existsById(Mockito.<Long>any());
    }
}

